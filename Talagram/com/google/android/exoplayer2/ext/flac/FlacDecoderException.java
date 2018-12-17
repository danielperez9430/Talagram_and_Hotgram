package com.google.android.exoplayer2.ext.flac;

import com.google.android.exoplayer2.audio.AudioDecoderException;

public final class FlacDecoderException extends AudioDecoderException {
    FlacDecoderException(String arg1) {
        super(arg1);
    }

    FlacDecoderException(String arg1, Throwable arg2) {
        super(arg1, arg2);
    }
}

