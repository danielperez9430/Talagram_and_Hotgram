package com.google.android.exoplayer2.extractor;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public interface Extractor {
    @Retention(value=RetentionPolicy.SOURCE) @public interface ReadResult {
    }

    public static final int RESULT_CONTINUE = 0;
    public static final int RESULT_END_OF_INPUT = -1;
    public static final int RESULT_SEEK = 1;

    void init(ExtractorOutput arg1);

    int read(ExtractorInput arg1, PositionHolder arg2);

    void release();

    void seek(long arg1, long arg2);

    boolean sniff(ExtractorInput arg1);
}

