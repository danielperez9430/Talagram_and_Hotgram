package com.google.android.exoplayer2.drm;

import com.google.android.exoplayer2.util.Assertions;
import java.util.UUID;

public final class LocalMediaDrmCallback implements MediaDrmCallback {
    private final byte[] keyResponse;

    public LocalMediaDrmCallback(byte[] arg1) {
        super();
        this.keyResponse = Assertions.checkNotNull(arg1);
    }

    public byte[] executeKeyRequest(UUID arg1, KeyRequest arg2, String arg3) {
        return this.keyResponse;
    }

    public byte[] executeProvisionRequest(UUID arg1, ProvisionRequest arg2) {
        throw new UnsupportedOperationException();
    }
}

