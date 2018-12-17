package com.google.android.exoplayer2.drm;

import com.google.android.exoplayer2.util.Assertions;
import java.util.Map;

public final class ErrorStateDrmSession implements DrmSession {
    private final DrmSessionException error;

    public ErrorStateDrmSession(DrmSessionException arg1) {
        super();
        this.error = Assertions.checkNotNull(arg1);
    }

    public DrmSessionException getError() {
        return this.error;
    }

    public ExoMediaCrypto getMediaCrypto() {
        return null;
    }

    public byte[] getOfflineLicenseKeySetId() {
        return null;
    }

    public int getState() {
        return 1;
    }

    public Map queryKeyStatus() {
        return null;
    }
}

