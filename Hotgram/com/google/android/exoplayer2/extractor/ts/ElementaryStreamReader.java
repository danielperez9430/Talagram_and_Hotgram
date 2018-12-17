package com.google.android.exoplayer2.extractor.ts;

import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.util.ParsableByteArray;

public interface ElementaryStreamReader {
    void consume(ParsableByteArray arg1);

    void createTracks(ExtractorOutput arg1, TrackIdGenerator arg2);

    void packetFinished();

    void packetStarted(long arg1, boolean arg2);

    void seek();
}

