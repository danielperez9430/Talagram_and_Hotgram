package com.google.android.exoplayer2.drm;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.extractor.mp4.PsshAtomUtil;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.EventDispatcher;
import com.google.android.exoplayer2.util.Util;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@TargetApi(value=18) public class DefaultDrmSessionManager implements ProvisioningManager, DrmSessionManager {
    class com.google.android.exoplayer2.drm.DefaultDrmSessionManager$1 {
    }

    @Deprecated public interface EventListener extends DefaultDrmSessionEventListener {
    }

    class MediaDrmEventListener implements OnEventListener {
        MediaDrmEventListener(DefaultDrmSessionManager arg1, com.google.android.exoplayer2.drm.DefaultDrmSessionManager$1 arg2) {
            this(arg1);
        }

        private MediaDrmEventListener(DefaultDrmSessionManager arg1) {
            DefaultDrmSessionManager.this = arg1;
            super();
        }

        public void onEvent(ExoMediaDrm arg1, byte[] arg2, int arg3, int arg4, byte[] arg5) {
            if(DefaultDrmSessionManager.this.mode == 0) {
                DefaultDrmSessionManager.this.mediaDrmHandler.obtainMessage(arg3, arg2).sendToTarget();
            }
        }
    }

    @SuppressLint(value={"HandlerLeak"}) class MediaDrmHandler extends Handler {
        public MediaDrmHandler(DefaultDrmSessionManager arg1, Looper arg2) {
            DefaultDrmSessionManager.this = arg1;
            super(arg2);
        }

        public void handleMessage(Message arg5) {
            Object v2;
            Object v0 = arg5.obj;
            Iterator v1 = DefaultDrmSessionManager.this.sessions.iterator();
            do {
                if(v1.hasNext()) {
                    v2 = v1.next();
                    if(!((DefaultDrmSession)v2).hasSessionId(((byte[])v0))) {
                        continue;
                    }

                    break;
                }

                return;
            }
            while(true);

            ((DefaultDrmSession)v2).onMediaDrmEvent(arg5.what);
        }
    }

    public final class MissingSchemeDataException extends Exception {
        MissingSchemeDataException(UUID arg1, com.google.android.exoplayer2.drm.DefaultDrmSessionManager$1 arg2) {
            this(arg1);
        }

        private MissingSchemeDataException(UUID arg3) {
            super("Media does not support uuid: " + arg3);
        }
    }

    @Retention(value=RetentionPolicy.SOURCE) @public interface Mode {
    }

    public static final int INITIAL_DRM_REQUEST_RETRY_COUNT = 3;
    public static final int MODE_DOWNLOAD = 2;
    public static final int MODE_PLAYBACK = 0;
    public static final int MODE_QUERY = 1;
    public static final int MODE_RELEASE = 3;
    public static final String PLAYREADY_CUSTOM_DATA_KEY = "PRCustomData";
    private static final String TAG = "DefaultDrmSessionMgr";
    private final MediaDrmCallback callback;
    private final EventDispatcher eventDispatcher;
    private final int initialDrmRequestRetryCount;
    private final ExoMediaDrm mediaDrm;
    volatile MediaDrmHandler mediaDrmHandler;
    private int mode;
    private final boolean multiSession;
    private byte[] offlineLicenseKeySetId;
    private final HashMap optionalKeyRequestParameters;
    private Looper playbackLooper;
    private final List provisioningSessions;
    private final List sessions;
    private final UUID uuid;

    public DefaultDrmSessionManager(UUID arg8, ExoMediaDrm arg9, MediaDrmCallback arg10, HashMap arg11) {
        this(arg8, arg9, arg10, arg11, false, 3);
    }

    public DefaultDrmSessionManager(UUID arg3, ExoMediaDrm arg4, MediaDrmCallback arg5, HashMap arg6, boolean arg7, int arg8) {
        super();
        Assertions.checkNotNull(arg3);
        Assertions.checkNotNull(arg4);
        Assertions.checkArgument(C.COMMON_PSSH_UUID.equals(arg3) ^ 1, "Use C.CLEARKEY_UUID instead");
        this.uuid = arg3;
        this.mediaDrm = arg4;
        this.callback = arg5;
        this.optionalKeyRequestParameters = arg6;
        this.eventDispatcher = new EventDispatcher();
        this.multiSession = arg7;
        this.initialDrmRequestRetryCount = arg8;
        this.mode = 0;
        this.sessions = new ArrayList();
        this.provisioningSessions = new ArrayList();
        if(arg7) {
            arg4.setPropertyString("sessionSharing", "enable");
        }

        arg4.setOnEventListener(new MediaDrmEventListener(this, null));
    }

    @Deprecated public DefaultDrmSessionManager(UUID arg1, ExoMediaDrm arg2, MediaDrmCallback arg3, HashMap arg4, Handler arg5, DefaultDrmSessionEventListener arg6) {
        this(arg1, arg2, arg3, arg4);
        if(arg5 != null && arg6 != null) {
            this.addListener(arg5, arg6);
        }
    }

    @Deprecated public DefaultDrmSessionManager(UUID arg7, ExoMediaDrm arg8, MediaDrmCallback arg9, HashMap arg10, Handler arg11, DefaultDrmSessionEventListener arg12, boolean arg13) {
        this(arg7, arg8, arg9, arg10, arg13);
        if(arg11 != null && arg12 != null) {
            this.addListener(arg11, arg12);
        }
    }

    public DefaultDrmSessionManager(UUID arg8, ExoMediaDrm arg9, MediaDrmCallback arg10, HashMap arg11, boolean arg12) {
        this(arg8, arg9, arg10, arg11, arg12, 3);
    }

    @Deprecated public DefaultDrmSessionManager(UUID arg8, ExoMediaDrm arg9, MediaDrmCallback arg10, HashMap arg11, Handler arg12, DefaultDrmSessionEventListener arg13, boolean arg14, int arg15) {
        this(arg8, arg9, arg10, arg11, arg14, arg15);
        if(arg12 != null && arg13 != null) {
            this.addListener(arg12, arg13);
        }
    }

    static List access$200(DefaultDrmSessionManager arg0) {
        return arg0.sessions;
    }

    static int access$300(DefaultDrmSessionManager arg0) {
        return arg0.mode;
    }

    public DrmSession acquireSession(Looper arg14, DrmInitData arg15) {
        DefaultDrmSession v15_2;
        Object v2_1;
        Object v1;
        SchemeData v5;
        boolean v0 = this.playbackLooper == null || this.playbackLooper == arg14 ? true : false;
        Assertions.checkState(v0);
        if(this.sessions.isEmpty()) {
            this.playbackLooper = arg14;
            if(this.mediaDrmHandler == null) {
                this.mediaDrmHandler = new MediaDrmHandler(this, arg14);
            }
        }

        com.google.android.exoplayer2.drm.DefaultDrmSessionManager$1 v2 = null;
        if(this.offlineLicenseKeySetId == null) {
            SchemeData v15 = DefaultDrmSessionManager.getSchemeData(arg15, this.uuid, false);
            if(v15 == null) {
                MissingSchemeDataException v14 = new MissingSchemeDataException(this.uuid, v2);
                this.eventDispatcher.dispatch(new -$$Lambda$DefaultDrmSessionManager$lsU4S5fVqixyNsHyDBIvI3jEzVc(v14));
                return new ErrorStateDrmSession(new DrmSessionException(((Throwable)v14)));
            }
            else {
                v5 = v15;
            }
        }
        else {
            v5 = ((SchemeData)v2);
        }

        if(this.multiSession) {
            byte[] v15_1 = v5 != null ? v5.data : ((byte[])v2);
            Iterator v0_1 = this.sessions.iterator();
            do {
                if(!v0_1.hasNext()) {
                    goto label_62;
                }

                v1 = v0_1.next();
            }
            while(!((DefaultDrmSession)v1).hasInitData(v15_1));

            v2_1 = v1;
        }
        else if(this.sessions.isEmpty()) {
        }
        else {
            v2_1 = this.sessions.get(0);
        }

    label_62:
        if(v2 == null) {
            v15_2 = new DefaultDrmSession(this.uuid, this.mediaDrm, this, v5, this.mode, this.offlineLicenseKeySetId, this.optionalKeyRequestParameters, this.callback, arg14, this.eventDispatcher, this.initialDrmRequestRetryCount);
            this.sessions.add(v15_2);
        }
        else {
            Object v15_3 = v2;
        }

        v15_2.acquire();
        return ((DrmSession)v15_2);
    }

    public final void addListener(Handler arg2, DefaultDrmSessionEventListener arg3) {
        this.eventDispatcher.addListener(arg2, arg3);
    }

    public boolean canAcquireSession(DrmInitData arg6) {
        boolean v1 = true;
        if(this.offlineLicenseKeySetId != null) {
            return 1;
        }

        if(DefaultDrmSessionManager.getSchemeData(arg6, this.uuid, true) == null) {
            if(arg6.schemeDataCount == 1 && (arg6.get(0).matches(C.COMMON_PSSH_UUID))) {
                Log.w("DefaultDrmSessionMgr", "DrmInitData only contains common PSSH SchemeData. Assuming support for: " + this.uuid);
                goto label_25;
            }

            return 0;
        }

    label_25:
        String v6 = arg6.schemeType;
        if(v6 != null) {
            if("cenc".equals(v6)) {
            }
            else {
                if(!"cbc1".equals(v6) && !"cbcs".equals(v6)) {
                    if("cens".equals(v6)) {
                    }
                    else {
                        return 1;
                    }
                }

                if(Util.SDK_INT >= 25) {
                    return v1;
                }

                v1 = false;
            }
        }

        return v1;
    }

    public final byte[] getPropertyByteArray(String arg2) {
        return this.mediaDrm.getPropertyByteArray(arg2);
    }

    public final String getPropertyString(String arg2) {
        return this.mediaDrm.getPropertyString(arg2);
    }

    private static SchemeData getSchemeData(DrmInitData arg6, UUID arg7, boolean arg8) {
        ArrayList v0 = new ArrayList(arg6.schemeDataCount);
        int v2;
        for(v2 = 0; true; ++v2) {
            int v4 = 1;
            if(v2 >= arg6.schemeDataCount) {
                break;
            }

            SchemeData v3 = arg6.get(v2);
            if(!v3.matches(arg7) && (!C.CLEARKEY_UUID.equals(arg7) || !v3.matches(C.COMMON_PSSH_UUID))) {
                v4 = 0;
            }

            if(v4 != 0 && (v3.data != null || (arg8))) {
                ((List)v0).add(v3);
            }
        }

        if(((List)v0).isEmpty()) {
            return null;
        }

        if(C.WIDEVINE_UUID.equals(arg7)) {
            int v6;
            for(v6 = 0; v6 < ((List)v0).size(); ++v6) {
                Object v7 = ((List)v0).get(v6);
                int v8 = ((SchemeData)v7).hasData() ? PsshAtomUtil.parseVersion(((SchemeData)v7).data) : -1;
                int v3_1 = 23;
                if(Util.SDK_INT < v3_1 && v8 == 0) {
                    return ((SchemeData)v7);
                }

                if(Util.SDK_INT >= v3_1 && v8 == 1) {
                    return ((SchemeData)v7);
                }
            }
        }

        return ((List)v0).get(0);
    }

    static void lambda$acquireSession$0(MissingSchemeDataException arg0, DefaultDrmSessionEventListener arg1) {
        arg1.onDrmSessionManagerError(((Exception)arg0));
    }

    public static DefaultDrmSessionManager newFrameworkInstance(UUID arg8, MediaDrmCallback arg9, HashMap arg10) {
        return new DefaultDrmSessionManager(arg8, FrameworkMediaDrm.newInstance(arg8), arg9, arg10, false, 3);
    }

    @Deprecated public static DefaultDrmSessionManager newFrameworkInstance(UUID arg0, MediaDrmCallback arg1, HashMap arg2, Handler arg3, DefaultDrmSessionEventListener arg4) {
        DefaultDrmSessionManager v0 = DefaultDrmSessionManager.newFrameworkInstance(arg0, arg1, arg2);
        if(arg3 != null && arg4 != null) {
            v0.addListener(arg3, arg4);
        }

        return v0;
    }

    public static DefaultDrmSessionManager newPlayReadyInstance(MediaDrmCallback arg2, String arg3) {
        HashMap v0;
        if(!TextUtils.isEmpty(((CharSequence)arg3))) {
            v0 = new HashMap();
            v0.put("PRCustomData", arg3);
        }
        else {
            v0 = null;
        }

        return DefaultDrmSessionManager.newFrameworkInstance(C.PLAYREADY_UUID, arg2, v0);
    }

    @Deprecated public static DefaultDrmSessionManager newPlayReadyInstance(MediaDrmCallback arg0, String arg1, Handler arg2, DefaultDrmSessionEventListener arg3) {
        DefaultDrmSessionManager v0 = DefaultDrmSessionManager.newPlayReadyInstance(arg0, arg1);
        if(arg2 != null && arg3 != null) {
            v0.addListener(arg2, arg3);
        }

        return v0;
    }

    public static DefaultDrmSessionManager newWidevineInstance(MediaDrmCallback arg1, HashMap arg2) {
        return DefaultDrmSessionManager.newFrameworkInstance(C.WIDEVINE_UUID, arg1, arg2);
    }

    @Deprecated public static DefaultDrmSessionManager newWidevineInstance(MediaDrmCallback arg0, HashMap arg1, Handler arg2, DefaultDrmSessionEventListener arg3) {
        DefaultDrmSessionManager v0 = DefaultDrmSessionManager.newWidevineInstance(arg0, arg1);
        if(arg2 != null && arg3 != null) {
            v0.addListener(arg2, arg3);
        }

        return v0;
    }

    public void onProvisionCompleted() {
        Iterator v0 = this.provisioningSessions.iterator();
        while(v0.hasNext()) {
            v0.next().onProvisionCompleted();
        }

        this.provisioningSessions.clear();
    }

    public void onProvisionError(Exception arg3) {
        Iterator v0 = this.provisioningSessions.iterator();
        while(v0.hasNext()) {
            v0.next().onProvisionError(arg3);
        }

        this.provisioningSessions.clear();
    }

    public void provisionRequired(DefaultDrmSession arg3) {
        this.provisioningSessions.add(arg3);
        if(this.provisioningSessions.size() == 1) {
            arg3.provision();
        }
    }

    public void releaseSession(DrmSession arg4) {
        if((arg4 instanceof ErrorStateDrmSession)) {
            return;
        }

        if(((DefaultDrmSession)arg4).release()) {
            this.sessions.remove(arg4);
            if(this.provisioningSessions.size() > 1 && this.provisioningSessions.get(0) == arg4) {
                this.provisioningSessions.get(1).provision();
            }

            this.provisioningSessions.remove(arg4);
        }
    }

    public final void removeListener(DefaultDrmSessionEventListener arg2) {
        this.eventDispatcher.removeListener(arg2);
    }

    public void setMode(int arg2, byte[] arg3) {
        Assertions.checkState(this.sessions.isEmpty());
        if(arg2 == 1 || arg2 == 3) {
            Assertions.checkNotNull(arg3);
        }

        this.mode = arg2;
        this.offlineLicenseKeySetId = arg3;
    }

    public final void setPropertyByteArray(String arg2, byte[] arg3) {
        this.mediaDrm.setPropertyByteArray(arg2, arg3);
    }

    public final void setPropertyString(String arg2, String arg3) {
        this.mediaDrm.setPropertyString(arg2, arg3);
    }
}

