package com.google.android.exoplayer2.extractor.mkv;

import com.google.android.exoplayer2.extractor.ExtractorInput;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

interface EbmlReaderOutput {
    @Retention(value=RetentionPolicy.SOURCE) @public interface ElementType {
    }

    public static final int TYPE_BINARY = 4;
    public static final int TYPE_FLOAT = 5;
    public static final int TYPE_MASTER = 1;
    public static final int TYPE_STRING = 3;
    public static final int TYPE_UNKNOWN = 0;
    public static final int TYPE_UNSIGNED_INT = 2;

    void binaryElement(int arg1, int arg2, ExtractorInput arg3);

    void endMasterElement(int arg1);

    void floatElement(int arg1, double arg2);

    int getElementType(int arg1);

    void integerElement(int arg1, long arg2);

    boolean isLevel1Element(int arg1);

    void startMasterElement(int arg1, long arg2, long arg3);

    void stringElement(int arg1, String arg2);
}

