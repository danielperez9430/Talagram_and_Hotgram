package com.google.android.exoplayer2.ext.ffmpeg;

import com.google.android.exoplayer2.audio.AudioDecoderException;

public final class FfmpegDecoderException extends AudioDecoderException {
    FfmpegDecoderException(String arg1) {
        super(arg1);
    }

    FfmpegDecoderException(String arg1, Throwable arg2) {
        super(arg1, arg2);
    }
}

