package com.google.android.exoplayer2.drm;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.media.NotProvisionedException;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.util.Pair;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.util.EventDispatcher$Event;
import com.google.android.exoplayer2.util.EventDispatcher;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@TargetApi(value=18) class DefaultDrmSession implements DrmSession {
    @SuppressLint(value={"HandlerLeak"}) class PostRequestHandler extends Handler {
        public PostRequestHandler(DefaultDrmSession arg1, Looper arg2) {
            DefaultDrmSession.this = arg1;
            super(arg2);
        }

        private long getRetryDelayMillis(int arg3) {
            return ((long)Math.min((arg3 - 1) * 1000, 5000));
        }

        public void handleMessage(Message arg6) {
            Object v0 = arg6.obj;
            try {
                switch(arg6.what) {
                    case 0: {
                        goto label_14;
                    }
                    case 1: {
                        goto label_5;
                    }
                }

                throw new RuntimeException();
            label_5:
                byte[] v1_1 = DefaultDrmSession.this.callback.executeKeyRequest(DefaultDrmSession.this.uuid, v0.first, v0.second);
                goto label_27;
            label_14:
                v1_1 = DefaultDrmSession.this.callback.executeProvisionRequest(DefaultDrmSession.this.uuid, v0);
            }
            catch(Exception v1) {
                if(!this.maybeRetryRequest(arg6)) {
                    goto label_27;
                }

                return;
            }

        label_27:
            DefaultDrmSession.this.postResponseHandler.obtainMessage(arg6.what, Pair.create(v0, v1)).sendToTarget();
        }

        private boolean maybeRetryRequest(Message arg5) {
            int v0 = arg5.arg1 == 1 ? 1 : 0;
            if(v0 == 0) {
                return 0;
            }

            v0 = arg5.arg2 + 1;
            if(v0 > DefaultDrmSession.this.initialDrmRequestRetryCount) {
                return 0;
            }

            arg5 = Message.obtain(arg5);
            arg5.arg2 = v0;
            this.sendMessageDelayed(arg5, this.getRetryDelayMillis(v0));
            return 1;
        }

        void post(int arg2, Object arg3, boolean arg4) {
            this.obtainMessage(arg2, ((int)arg4), 0, arg3).sendToTarget();
        }
    }

    @SuppressLint(value={"HandlerLeak"}) class PostResponseHandler extends Handler {
        public PostResponseHandler(DefaultDrmSession arg1, Looper arg2) {
            DefaultDrmSession.this = arg1;
            super(arg2);
        }

        public void handleMessage(Message arg3) {
            Object v0 = arg3.obj;
            Object v1 = ((Pair)v0).first;
            v0 = ((Pair)v0).second;
            switch(arg3.what) {
                case 0: {
                    DefaultDrmSession.this.onProvisionResponse(v1, v0);
                    break;
                }
                case 1: {
                    DefaultDrmSession.this.onKeyResponse(v1, v0);
                    break;
                }
                default: {
                    break;
                }
            }
        }
    }

    public interface ProvisioningManager {
        void onProvisionCompleted();

        void onProvisionError(Exception arg1);

        void provisionRequired(DefaultDrmSession arg1);
    }

    private static final int MAX_LICENSE_DURATION_TO_RENEW = 60;
    private static final int MSG_KEYS = 1;
    private static final int MSG_PROVISION = 0;
    private static final String TAG = "DefaultDrmSession";
    final MediaDrmCallback callback;
    private Object currentKeyRequest;
    private Object currentProvisionRequest;
    private final EventDispatcher eventDispatcher;
    private final int initialDrmRequestRetryCount;
    private DrmSessionException lastException;
    private ExoMediaCrypto mediaCrypto;
    private final ExoMediaDrm mediaDrm;
    private final int mode;
    private byte[] offlineLicenseKeySetId;
    private int openCount;
    private final HashMap optionalKeyRequestParameters;
    private PostRequestHandler postRequestHandler;
    final PostResponseHandler postResponseHandler;
    private final ProvisioningManager provisioningManager;
    private HandlerThread requestHandlerThread;
    private final SchemeData schemeData;
    private byte[] sessionId;
    private int state;
    final UUID uuid;

    public DefaultDrmSession(UUID arg1, ExoMediaDrm arg2, ProvisioningManager arg3, SchemeData arg4, int arg5, byte[] arg6, HashMap arg7, MediaDrmCallback arg8, Looper arg9, EventDispatcher arg10, int arg11) {
        super();
        this.uuid = arg1;
        this.provisioningManager = arg3;
        this.mediaDrm = arg2;
        this.mode = arg5;
        this.offlineLicenseKeySetId = arg6;
        if(arg6 == null) {
        }
        else {
            arg4 = null;
        }

        this.schemeData = arg4;
        this.optionalKeyRequestParameters = arg7;
        this.callback = arg8;
        this.initialDrmRequestRetryCount = arg11;
        this.eventDispatcher = arg10;
        this.state = 2;
        this.postResponseHandler = new PostResponseHandler(this, arg9);
        this.requestHandlerThread = new HandlerThread("DrmRequestHandler");
        this.requestHandlerThread.start();
        this.postRequestHandler = new PostRequestHandler(this, this.requestHandlerThread.getLooper());
    }

    static void access$000(DefaultDrmSession arg0, Object arg1, Object arg2) {
        arg0.onProvisionResponse(arg1, arg2);
    }

    static void access$100(DefaultDrmSession arg0, Object arg1, Object arg2) {
        arg0.onKeyResponse(arg1, arg2);
    }

    static int access$200(DefaultDrmSession arg0) {
        return arg0.initialDrmRequestRetryCount;
    }

    public void acquire() {
        int v0 = this.openCount + 1;
        this.openCount = v0;
        if(v0 == 1) {
            if(this.state == 1) {
                return;
            }
            else if(this.openInternal(true)) {
                this.doLicense(true);
            }
        }
    }

    private void doLicense(boolean arg8) {
        int v0;
        int v1 = 2;
        switch(this.mode) {
            case 0: 
            case 1: {
                if(this.offlineLicenseKeySetId == null) {
                    v0 = 1;
                label_18:
                    this.postKeyRequest(v0, arg8);
                    return;
                }

                int v2 = 4;
                if(this.state != v2 && !this.restoreKeys()) {
                    return;
                }

                long v3 = this.getLicenseDurationRemainingSec();
                if(this.mode == 0 && v3 <= 60) {
                    Log.d("DefaultDrmSession", "Offline license has expired or will expire soon. Remaining seconds: " + v3);
                label_10:
                    this.postKeyRequest(v1, arg8);
                    return;
                }

                if(v3 <= 0) {
                    this.onError(new KeysExpiredException());
                    return;
                }

                this.state = v2;
                this.eventDispatcher.dispatch(-$$Lambda$tzysvANfjWo6mXRxYD2fQMdks_4.INSTANCE);
                break;
            }
            case 2: {
                if(this.offlineLicenseKeySetId == null) {
                    goto label_10;
                }

                if(!this.restoreKeys()) {
                    return;
                }

                goto label_10;
            }
            case 3: {
                if(!this.restoreKeys()) {
                    return;
                }

                v0 = 3;
                goto label_18;
            }
            default: {
                break;
            }
        }
    }

    public final DrmSessionException getError() {
        DrmSessionException v0 = this.state == 1 ? this.lastException : null;
        return v0;
    }

    private long getLicenseDurationRemainingSec() {
        if(!C.WIDEVINE_UUID.equals(this.uuid)) {
            return 9223372036854775807L;
        }

        Pair v0 = WidevineUtil.getLicenseDurationRemainingSec(((DrmSession)this));
        return Math.min(v0.first.longValue(), v0.second.longValue());
    }

    public final ExoMediaCrypto getMediaCrypto() {
        return this.mediaCrypto;
    }

    public byte[] getOfflineLicenseKeySetId() {
        return this.offlineLicenseKeySetId;
    }

    public final int getState() {
        return this.state;
    }

    public boolean hasInitData(byte[] arg2) {
        byte[] v0 = this.schemeData != null ? this.schemeData.data : null;
        return Arrays.equals(v0, arg2);
    }

    public boolean hasSessionId(byte[] arg2) {
        return Arrays.equals(this.sessionId, arg2);
    }

    private boolean isOpen() {
        boolean v0 = this.state == 3 || this.state == 4 ? true : false;
        return v0;
    }

    static void lambda$onError$0(Exception arg0, DefaultDrmSessionEventListener arg1) {
        arg1.onDrmSessionManagerError(arg0);
    }

    private void onError(Exception arg3) {
        this.lastException = new DrmSessionException(((Throwable)arg3));
        this.eventDispatcher.dispatch(new -$$Lambda$DefaultDrmSession$-nKOJC1w2998gRg4Cg4l2mjlp30(arg3));
        if(this.state != 4) {
            this.state = 1;
        }
    }

    private void onKeyResponse(Object arg2, Object arg3) {
        -$$Lambda$wyKVEWJALn1OyjwryLo2GUxlQ2M v3_1;
        EventDispatcher v2_1;
        if(arg2 == this.currentKeyRequest && (this.isOpen())) {
            this.currentKeyRequest = null;
            if((arg3 instanceof Exception)) {
                this.onKeysError(((Exception)arg3));
                return;
            }

            try {
                if(this.mode == 3) {
                    this.mediaDrm.provideKeyResponse(this.offlineLicenseKeySetId, ((byte[])arg3));
                    v2_1 = this.eventDispatcher;
                    -$$Lambda$tzysvANfjWo6mXRxYD2fQMdks_4 v3 = -$$Lambda$tzysvANfjWo6mXRxYD2fQMdks_4.INSTANCE;
                }
                else {
                    byte[] v2_2 = this.mediaDrm.provideKeyResponse(this.sessionId, ((byte[])arg3));
                    if((this.mode == 2 || this.mode == 0 && this.offlineLicenseKeySetId != null) && (v2_2 != null && v2_2.length != 0)) {
                        this.offlineLicenseKeySetId = v2_2;
                    }

                    this.state = 4;
                    v2_1 = this.eventDispatcher;
                    v3_1 = -$$Lambda$wyKVEWJALn1OyjwryLo2GUxlQ2M.INSTANCE;
                }

                v2_1.dispatch(((Event)v3_1));
            }
            catch(Exception v2) {
                this.onKeysError(v2);
            }
        }
    }

    private void onKeysError(Exception arg2) {
        if((arg2 instanceof NotProvisionedException)) {
            this.provisioningManager.provisionRequired(this);
        }
        else {
            this.onError(arg2);
        }
    }

    private void onKeysExpired() {
        if(this.state == 4) {
            this.state = 3;
            this.onError(new KeysExpiredException());
        }
    }

    public void onMediaDrmEvent(int arg2) {
        if(!this.isOpen()) {
            return;
        }

        switch(arg2) {
            case 1: {
                this.state = 3;
                this.provisioningManager.provisionRequired(this);
                break;
            }
            case 2: {
                this.doLicense(false);
                break;
            }
            case 3: {
                this.onKeysExpired();
                break;
            }
            default: {
                break;
            }
        }
    }

    public void onProvisionCompleted() {
        if(this.openInternal(false)) {
            this.doLicense(true);
        }
    }

    public void onProvisionError(Exception arg1) {
        this.onError(arg1);
    }

    private void onProvisionResponse(Object arg2, Object arg3) {
        if(arg2 == this.currentProvisionRequest && (this.state == 2 || (this.isOpen()))) {
            this.currentProvisionRequest = null;
            if((arg3 instanceof Exception)) {
                this.provisioningManager.onProvisionError(((Exception)arg3));
                return;
            }

            try {
                this.mediaDrm.provideProvisionResponse(((byte[])arg3));
            }
            catch(Exception v2) {
                this.provisioningManager.onProvisionError(v2);
                return;
            }

            this.provisioningManager.onProvisionCompleted();
            return;
        }
    }

    private boolean openInternal(boolean arg4) {
        if(this.isOpen()) {
            return 1;
        }

        try {
            this.sessionId = this.mediaDrm.openSession();
            this.mediaCrypto = this.mediaDrm.createMediaCrypto(this.sessionId);
            this.state = 3;
            return 1;
        }
        catch(Exception v4) {
            this.onError(v4);
        }
        catch(NotProvisionedException v0) {
            if(arg4) {
                this.provisioningManager.provisionRequired(this);
                return 0;
            }

            this.onError(((Exception)v0));
        }

        return 0;
    }

    private void postKeyRequest(int arg9, boolean arg10) {
        Object v0_2;
        byte[] v3;
        String v4;
        byte[] v0 = arg9 == 3 ? this.offlineLicenseKeySetId : this.sessionId;
        byte[] v2 = v0;
        Object v1 = null;
        if(this.schemeData != null) {
            v0 = this.schemeData.data;
            v4 = this.schemeData.mimeType;
            v3 = v0;
            String v0_1 = this.schemeData.licenseServerUrl;
        }
        else {
            v0_2 = v1;
            v3 = ((byte[])v0_2);
            v4 = ((String)v3);
        }

        try {
            this.currentKeyRequest = Pair.create(this.mediaDrm.getKeyRequest(v2, v3, v4, arg9, this.optionalKeyRequestParameters), v0_2);
            this.postRequestHandler.post(1, this.currentKeyRequest, arg10);
        }
        catch(Exception v9) {
            this.onKeysError(v9);
        }
    }

    public void provision() {
        this.currentProvisionRequest = this.mediaDrm.getProvisionRequest();
        this.postRequestHandler.post(0, this.currentProvisionRequest, true);
    }

    public Map queryKeyStatus() {
        Map v0 = this.sessionId == null ? null : this.mediaDrm.queryKeyStatus(this.sessionId);
        return v0;
    }

    public boolean release() {
        int v0 = this.openCount - 1;
        this.openCount = v0;
        if(v0 == 0) {
            this.state = 0;
            Object v2 = null;
            this.postResponseHandler.removeCallbacksAndMessages(v2);
            this.postRequestHandler.removeCallbacksAndMessages(v2);
            this.postRequestHandler = ((PostRequestHandler)v2);
            this.requestHandlerThread.quit();
            this.requestHandlerThread = ((HandlerThread)v2);
            this.mediaCrypto = ((ExoMediaCrypto)v2);
            this.lastException = ((DrmSessionException)v2);
            this.currentKeyRequest = v2;
            this.currentProvisionRequest = v2;
            if(this.sessionId != null) {
                this.mediaDrm.closeSession(this.sessionId);
                this.sessionId = ((byte[])v2);
            }

            return 1;
        }

        return 0;
    }

    private boolean restoreKeys() {
        try {
            this.mediaDrm.restoreKeys(this.sessionId, this.offlineLicenseKeySetId);
            return 1;
        }
        catch(Exception v0) {
            Log.e("DefaultDrmSession", "Error trying to restore Widevine keys.", ((Throwable)v0));
            this.onError(v0);
            return 0;
        }
    }
}

