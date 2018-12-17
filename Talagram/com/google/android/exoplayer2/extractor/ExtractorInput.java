package com.google.android.exoplayer2.extractor;

public interface ExtractorInput {
    void advancePeekPosition(int arg1);

    boolean advancePeekPosition(int arg1, boolean arg2);

    long getLength();

    long getPeekPosition();

    long getPosition();

    void peekFully(byte[] arg1, int arg2, int arg3);

    boolean peekFully(byte[] arg1, int arg2, int arg3, boolean arg4);

    int read(byte[] arg1, int arg2, int arg3);

    void readFully(byte[] arg1, int arg2, int arg3);

    boolean readFully(byte[] arg1, int arg2, int arg3, boolean arg4);

    void resetPeekPosition();

    void setRetryPosition(long arg1, Throwable arg2);

    int skip(int arg1);

    void skipFully(int arg1);

    boolean skipFully(int arg1, boolean arg2);
}

