package com.google.android.exoplayer2.extractor.flv;

import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.util.ParsableByteArray;

abstract class TagPayloadReader {
    public final class UnsupportedFormatException extends ParserException {
        public UnsupportedFormatException(String arg1) {
            super(arg1);
        }
    }

    protected final TrackOutput output;

    protected TagPayloadReader(TrackOutput arg1) {
        super();
        this.output = arg1;
    }

    public final void consume(ParsableByteArray arg2, long arg3) {
        if(this.parseHeader(arg2)) {
            this.parsePayload(arg2, arg3);
        }
    }

    protected abstract boolean parseHeader(ParsableByteArray arg1);

    protected abstract void parsePayload(ParsableByteArray arg1, long arg2);

    public abstract void seek();
}

