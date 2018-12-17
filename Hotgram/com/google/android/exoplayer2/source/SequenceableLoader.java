package com.google.android.exoplayer2.source;

public interface SequenceableLoader {
    public interface Callback {
        void onContinueLoadingRequested(SequenceableLoader arg1);
    }

    boolean continueLoading(long arg1);

    long getBufferedPositionUs();

    long getNextLoadPositionUs();

    void reevaluateBuffer(long arg1);
}

