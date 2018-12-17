package com.google.android.exoplayer2.drm;

import android.annotation.TargetApi;
import android.media.MediaCrypto;
import com.google.android.exoplayer2.util.Assertions;

@TargetApi(value=16) public final class FrameworkMediaCrypto implements ExoMediaCrypto {
    private final boolean forceAllowInsecureDecoderComponents;
    private final MediaCrypto mediaCrypto;

    public FrameworkMediaCrypto(MediaCrypto arg2) {
        this(arg2, false);
    }

    public FrameworkMediaCrypto(MediaCrypto arg1, boolean arg2) {
        super();
        this.mediaCrypto = Assertions.checkNotNull(arg1);
        this.forceAllowInsecureDecoderComponents = arg2;
    }

    public MediaCrypto getWrappedMediaCrypto() {
        return this.mediaCrypto;
    }

    public boolean requiresSecureDecoderComponent(String arg2) {
        boolean v2 = (this.forceAllowInsecureDecoderComponents) || !this.mediaCrypto.requiresSecureDecoderComponent(arg2) ? false : true;
        return v2;
    }
}

