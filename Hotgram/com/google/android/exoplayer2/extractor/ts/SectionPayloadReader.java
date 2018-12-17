package com.google.android.exoplayer2.extractor.ts;

import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.TimestampAdjuster;

public interface SectionPayloadReader {
    void consume(ParsableByteArray arg1);

    void init(TimestampAdjuster arg1, ExtractorOutput arg2, TrackIdGenerator arg3);
}

