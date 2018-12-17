package com.google.android.exoplayer2.ext.opus;

import com.google.android.exoplayer2.audio.AudioDecoderException;

public final class OpusDecoderException extends AudioDecoderException {
    OpusDecoderException(String arg1) {
        super(arg1);
    }

    OpusDecoderException(String arg1, Throwable arg2) {
        super(arg1, arg2);
    }
}

