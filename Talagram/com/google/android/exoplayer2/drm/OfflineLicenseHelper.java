package com.google.android.exoplayer2.drm;

import android.os.ConditionVariable;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Pair;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.upstream.HttpDataSource$Factory;
import com.google.android.exoplayer2.util.Assertions;
import java.util.HashMap;
import java.util.UUID;

public final class OfflineLicenseHelper {
    private final ConditionVariable conditionVariable;
    private final DefaultDrmSessionManager drmSessionManager;
    private final HandlerThread handlerThread;

    public OfflineLicenseHelper(UUID arg3, ExoMediaDrm arg4, MediaDrmCallback arg5, HashMap arg6) {
        super();
        this.handlerThread = new HandlerThread("OfflineLicenseHelper");
        this.handlerThread.start();
        this.conditionVariable = new ConditionVariable();
        com.google.android.exoplayer2.drm.OfflineLicenseHelper$1 v0 = new DefaultDrmSessionEventListener() {
            public void onDrmKeysLoaded() {
                OfflineLicenseHelper.access$000(OfflineLicenseHelper.this).open();
            }

            public void onDrmKeysRemoved() {
                OfflineLicenseHelper.access$000(OfflineLicenseHelper.this).open();
            }

            public void onDrmKeysRestored() {
                OfflineLicenseHelper.access$000(OfflineLicenseHelper.this).open();
            }

            public void onDrmSessionManagerError(Exception arg1) {
                OfflineLicenseHelper.access$000(OfflineLicenseHelper.this).open();
            }
        };
        this.drmSessionManager = new DefaultDrmSessionManager(arg3, arg4, arg5, arg6);
        this.drmSessionManager.addListener(new Handler(this.handlerThread.getLooper()), ((DefaultDrmSessionEventListener)v0));
    }

    static ConditionVariable access$000(OfflineLicenseHelper arg0) {
        return arg0.conditionVariable;
    }

    private byte[] blockingKeyRequest(int arg2, byte[] arg3, DrmInitData arg4) {
        DrmSession v2 = this.openBlockingKeyRequest(arg2, arg3, arg4);
        DrmSessionException v3 = v2.getError();
        byte[] v4 = v2.getOfflineLicenseKeySetId();
        this.drmSessionManager.releaseSession(v2);
        if(v3 == null) {
            return v4;
        }

        throw v3;
    }

    public byte[] downloadLicense(DrmInitData arg3) {
        byte[] v3_1;
        __monitor_enter(this);
        boolean v0 = arg3 != null ? true : false;
        try {
            Assertions.checkArgument(v0);
            v3_1 = this.blockingKeyRequest(2, null, arg3);
        }
        catch(Throwable v3) {
            __monitor_exit(this);
            throw v3;
        }

        __monitor_exit(this);
        return v3_1;
    }

    public Pair getLicenseDurationRemainingSec(byte[] arg4) {
        Pair v4_2;
        Pair v1;
        DrmSessionException v0;
        __monitor_enter(this);
        try {
            Assertions.checkNotNull(arg4);
            DrmSession v4_1 = this.openBlockingKeyRequest(1, arg4, null);
            v0 = v4_1.getError();
            v1 = WidevineUtil.getLicenseDurationRemainingSec(v4_1);
            this.drmSessionManager.releaseSession(v4_1);
            if(v0 == null) {
                goto label_20;
            }

            if(!(v0.getCause() instanceof KeysExpiredException)) {
                goto label_19;
            }

            v4_2 = Pair.create(Long.valueOf(0), Long.valueOf(0));
        }
        catch(Throwable v4) {
            goto label_23;
        }

        __monitor_exit(this);
        return v4_2;
        try {
        label_19:
            throw v0;
        }
        catch(Throwable v4) {
        label_23:
            __monitor_exit(this);
            throw v4;
        }

    label_20:
        __monitor_exit(this);
        return v1;
    }

    public byte[] getPropertyByteArray(String arg2) {
        byte[] v2_1;
        __monitor_enter(this);
        try {
            v2_1 = this.drmSessionManager.getPropertyByteArray(arg2);
        }
        catch(Throwable v2) {
            __monitor_exit(this);
            throw v2;
        }

        __monitor_exit(this);
        return v2_1;
    }

    public String getPropertyString(String arg2) {
        __monitor_enter(this);
        try {
            arg2 = this.drmSessionManager.getPropertyString(arg2);
        }
        catch(Throwable v2) {
            __monitor_exit(this);
            throw v2;
        }

        __monitor_exit(this);
        return arg2;
    }

    public static OfflineLicenseHelper newWidevineInstance(String arg2, Factory arg3) {
        return OfflineLicenseHelper.newWidevineInstance(arg2, false, arg3, null);
    }

    public static OfflineLicenseHelper newWidevineInstance(String arg4, boolean arg5, Factory arg6, HashMap arg7) {
        return new OfflineLicenseHelper(C.WIDEVINE_UUID, FrameworkMediaDrm.newInstance(C.WIDEVINE_UUID), new HttpMediaDrmCallback(arg4, arg5, arg6), arg7);
    }

    public static OfflineLicenseHelper newWidevineInstance(String arg1, boolean arg2, Factory arg3) {
        return OfflineLicenseHelper.newWidevineInstance(arg1, arg2, arg3, null);
    }

    private DrmSession openBlockingKeyRequest(int arg2, byte[] arg3, DrmInitData arg4) {
        this.drmSessionManager.setMode(arg2, arg3);
        this.conditionVariable.close();
        DrmSession v2 = this.drmSessionManager.acquireSession(this.handlerThread.getLooper(), arg4);
        this.conditionVariable.block();
        return v2;
    }

    public void release() {
        this.handlerThread.quit();
    }

    public void releaseLicense(byte[] arg3) {
        __monitor_enter(this);
        try {
            Assertions.checkNotNull(arg3);
            this.blockingKeyRequest(3, arg3, null);
        }
        catch(Throwable v3) {
            __monitor_exit(this);
            throw v3;
        }

        __monitor_exit(this);
    }

    public byte[] renewLicense(byte[] arg3) {
        __monitor_enter(this);
        try {
            Assertions.checkNotNull(arg3);
            arg3 = this.blockingKeyRequest(2, arg3, null);
        }
        catch(Throwable v3) {
            __monitor_exit(this);
            throw v3;
        }

        __monitor_exit(this);
        return arg3;
    }

    public void setPropertyByteArray(String arg2, byte[] arg3) {
        __monitor_enter(this);
        try {
            this.drmSessionManager.setPropertyByteArray(arg2, arg3);
        }
        catch(Throwable v2) {
            __monitor_exit(this);
            throw v2;
        }

        __monitor_exit(this);
    }

    public void setPropertyString(String arg2, String arg3) {
        __monitor_enter(this);
        try {
            this.drmSessionManager.setPropertyString(arg2, arg3);
        }
        catch(Throwable v2) {
            __monitor_exit(this);
            throw v2;
        }

        __monitor_exit(this);
    }
}

