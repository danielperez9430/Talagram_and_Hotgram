package com.google.android.exoplayer2.ext.ffmpeg;

import android.os.Handler;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.audio.AudioProcessor;
import com.google.android.exoplayer2.audio.AudioRendererEventListener;
import com.google.android.exoplayer2.audio.AudioSink;
import com.google.android.exoplayer2.audio.DefaultAudioSink;
import com.google.android.exoplayer2.audio.SimpleDecoderAudioRenderer;
import com.google.android.exoplayer2.decoder.SimpleDecoder;
import com.google.android.exoplayer2.drm.DrmSessionManager;
import com.google.android.exoplayer2.drm.ExoMediaCrypto;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.MimeTypes;
import java.util.Collections;

public final class FfmpegAudioRenderer extends SimpleDecoderAudioRenderer {
    private static final int DEFAULT_INPUT_BUFFER_SIZE = 5760;
    private static final int NUM_BUFFERS = 16;
    private FfmpegDecoder decoder;
    private final boolean enableFloatOutput;

    public FfmpegAudioRenderer() {
        this(null, null, new AudioProcessor[0]);
    }

    public FfmpegAudioRenderer(Handler arg3, AudioRendererEventListener arg4, AudioProcessor[] arg5) {
        this(arg3, arg4, new DefaultAudioSink(null, arg5), false);
    }

    public FfmpegAudioRenderer(Handler arg7, AudioRendererEventListener arg8, AudioSink arg9, boolean arg10) {
        super(arg7, arg8, null, false, arg9);
        this.enableFloatOutput = arg10;
    }

    protected SimpleDecoder createDecoder(Format arg1, ExoMediaCrypto arg2) {
        return this.createDecoder(arg1, arg2);
    }

    protected FfmpegDecoder createDecoder(Format arg7, ExoMediaCrypto arg8) {
        int v3 = arg7.maxInputSize != -1 ? arg7.maxInputSize : 5760;
        this.decoder = new FfmpegDecoder(16, 16, v3, arg7, this.shouldUseFloatOutput(arg7));
        return this.decoder;
    }

    public Format getOutputFormat() {
        Assertions.checkNotNull(this.decoder);
        return Format.createAudioSampleFormat(null, "audio/raw", null, -1, -1, this.decoder.getChannelCount(), this.decoder.getSampleRate(), this.decoder.getEncoding(), Collections.emptyList(), null, 0, null);
    }

    private boolean isOutputSupported(Format arg1) {
        boolean v1 = (this.shouldUseFloatOutput(arg1)) || (this.supportsOutputEncoding(2)) ? true : false;
        return v1;
    }

    private boolean shouldUseFloatOutput(Format arg8) {
        Assertions.checkNotNull(arg8.sampleMimeType);
        boolean v1 = false;
        if(this.enableFloatOutput) {
            int v0 = 4;
            if(!this.supportsOutputEncoding(v0)) {
            }
            else {
                String v2 = arg8.sampleMimeType;
                int v3 = -1;
                int v4 = v2.hashCode();
                if(v4 != 187078296) {
                    if(v4 != 187094639) {
                    }
                    else if(v2.equals("audio/raw")) {
                        v3 = 0;
                    }
                }
                else if(v2.equals("audio/ac3")) {
                    v3 = 1;
                }

                switch(v3) {
                    case 0: {
                        goto label_30;
                    }
                    case 1: {
                        return 0;
                    }
                }

                return 1;
            label_30:
                if(arg8.pcmEncoding != -2147483648 && arg8.pcmEncoding != 1073741824 && arg8.pcmEncoding != v0) {
                    return v1;
                }

                v1 = true;
            }
        }

        return v1;
    }

    protected int supportsFormatInternal(DrmSessionManager arg3, Format arg4) {
        Assertions.checkNotNull(arg4.sampleMimeType);
        if(!MimeTypes.isAudio(arg4.sampleMimeType)) {
            return 0;
        }

        if(FfmpegLibrary.supportsFormat(arg4.sampleMimeType, arg4.pcmEncoding)) {
            if(!this.isOutputSupported(arg4)) {
            }
            else if(!FfmpegAudioRenderer.supportsFormatDrm(arg3, arg4.drmInitData)) {
                return 2;
            }
            else {
                return 4;
            }
        }

        return 1;
    }

    public final int supportsMixedMimeTypeAdaptation() {
        return 8;
    }
}

