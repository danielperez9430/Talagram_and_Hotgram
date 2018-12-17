package com.google.android.exoplayer2.ext.flac;

import android.os.Handler;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.audio.AudioProcessor;
import com.google.android.exoplayer2.audio.AudioRendererEventListener;
import com.google.android.exoplayer2.audio.SimpleDecoderAudioRenderer;
import com.google.android.exoplayer2.decoder.SimpleDecoder;
import com.google.android.exoplayer2.drm.DrmSessionManager;
import com.google.android.exoplayer2.drm.ExoMediaCrypto;

public class LibflacAudioRenderer extends SimpleDecoderAudioRenderer {
    private static final int NUM_BUFFERS = 16;

    public LibflacAudioRenderer() {
        this(null, null, new AudioProcessor[0]);
    }

    public LibflacAudioRenderer(Handler arg1, AudioRendererEventListener arg2, AudioProcessor[] arg3) {
        super(arg1, arg2, arg3);
    }

    protected SimpleDecoder createDecoder(Format arg1, ExoMediaCrypto arg2) {
        return this.createDecoder(arg1, arg2);
    }

    protected FlacDecoder createDecoder(Format arg3, ExoMediaCrypto arg4) {
        return new FlacDecoder(16, 16, arg3.maxInputSize, arg3.initializationData);
    }

    protected int supportsFormatInternal(DrmSessionManager arg3, Format arg4) {
        if(!"audio/flac".equalsIgnoreCase(arg4.sampleMimeType)) {
            return 0;
        }

        int v0 = 2;
        if(!this.supportsOutputEncoding(v0)) {
            return 1;
        }

        if(!LibflacAudioRenderer.supportsFormatDrm(arg3, arg4.drmInitData)) {
            return v0;
        }

        return 4;
    }
}

