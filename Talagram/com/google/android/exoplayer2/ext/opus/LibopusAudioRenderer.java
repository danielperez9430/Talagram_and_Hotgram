package com.google.android.exoplayer2.ext.opus;

import android.os.Handler;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.audio.AudioProcessor;
import com.google.android.exoplayer2.audio.AudioRendererEventListener;
import com.google.android.exoplayer2.audio.SimpleDecoderAudioRenderer;
import com.google.android.exoplayer2.decoder.SimpleDecoder;
import com.google.android.exoplayer2.drm.DrmSessionManager;
import com.google.android.exoplayer2.drm.ExoMediaCrypto;

public final class LibopusAudioRenderer extends SimpleDecoderAudioRenderer {
    private static final int DEFAULT_INPUT_BUFFER_SIZE = 5760;
    private static final int NUM_BUFFERS = 16;
    private OpusDecoder decoder;

    public LibopusAudioRenderer() {
        this(null, null, new AudioProcessor[0]);
    }

    public LibopusAudioRenderer(Handler arg1, AudioRendererEventListener arg2, AudioProcessor[] arg3) {
        super(arg1, arg2, arg3);
    }

    public LibopusAudioRenderer(Handler arg8, AudioRendererEventListener arg9, DrmSessionManager arg10, boolean arg11, AudioProcessor[] arg12) {
        super(arg8, arg9, null, arg10, arg11, arg12);
    }

    protected SimpleDecoder createDecoder(Format arg1, ExoMediaCrypto arg2) {
        return this.createDecoder(arg1, arg2);
    }

    protected OpusDecoder createDecoder(Format arg8, ExoMediaCrypto arg9) {
        int v4 = arg8.maxInputSize != -1 ? arg8.maxInputSize : 5760;
        this.decoder = new OpusDecoder(16, 16, v4, arg8.initializationData, arg9);
        return this.decoder;
    }

    protected Format getOutputFormat() {
        return Format.createAudioSampleFormat(null, "audio/raw", null, -1, -1, this.decoder.getChannelCount(), this.decoder.getSampleRate(), 2, null, null, 0, null);
    }

    protected int supportsFormatInternal(DrmSessionManager arg3, Format arg4) {
        if(!"audio/opus".equalsIgnoreCase(arg4.sampleMimeType)) {
            return 0;
        }

        int v0 = 2;
        if(!this.supportsOutputEncoding(v0)) {
            return 1;
        }

        if(!LibopusAudioRenderer.supportsFormatDrm(arg3, arg4.drmInitData)) {
            return v0;
        }

        return 4;
    }
}

