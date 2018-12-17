package com.google.android.exoplayer2.drm;

import android.annotation.TargetApi;
import android.os.Looper;

@TargetApi(value=16) public interface DrmSessionManager {
    DrmSession acquireSession(Looper arg1, DrmInitData arg2);

    boolean canAcquireSession(DrmInitData arg1);

    void releaseSession(DrmSession arg1);
}

