package com.google.android.exoplayer2.extractor.ogg;

import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.extractor.SeekMap;

interface OggSeeker {
    SeekMap createSeekMap();

    long read(ExtractorInput arg1);

    long startSeek(long arg1);
}

