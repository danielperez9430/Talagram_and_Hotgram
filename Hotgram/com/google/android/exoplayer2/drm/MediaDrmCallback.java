package com.google.android.exoplayer2.drm;

import java.util.UUID;

public interface MediaDrmCallback {
    byte[] executeKeyRequest(UUID arg1, KeyRequest arg2, String arg3);

    byte[] executeProvisionRequest(UUID arg1, ProvisionRequest arg2);
}

