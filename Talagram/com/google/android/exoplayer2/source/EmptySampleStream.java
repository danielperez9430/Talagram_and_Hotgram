package com.google.android.exoplayer2.source;

import com.google.android.exoplayer2.FormatHolder;
import com.google.android.exoplayer2.decoder.DecoderInputBuffer;

public final class EmptySampleStream implements SampleStream {
    public EmptySampleStream() {
        super();
    }

    public boolean isReady() {
        return 1;
    }

    public void maybeThrowError() {
    }

    public int readData(FormatHolder arg1, DecoderInputBuffer arg2, boolean arg3) {
        arg2.setFlags(4);
        return -4;
    }

    public int skipData(long arg1) {
        return 0;
    }
}

