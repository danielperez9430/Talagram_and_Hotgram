package com.google.android.exoplayer2.extractor.mkv;

import com.google.android.exoplayer2.extractor.ExtractorInput;

interface EbmlReader {
    void init(EbmlReaderOutput arg1);

    boolean read(ExtractorInput arg1);

    void reset();
}

