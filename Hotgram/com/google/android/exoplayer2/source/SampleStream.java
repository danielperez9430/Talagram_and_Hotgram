package com.google.android.exoplayer2.source;

import com.google.android.exoplayer2.FormatHolder;
import com.google.android.exoplayer2.decoder.DecoderInputBuffer;

public interface SampleStream {
    boolean isReady();

    void maybeThrowError();

    int readData(FormatHolder arg1, DecoderInputBuffer arg2, boolean arg3);

    int skipData(long arg1);
}

