package com.google.android.exoplayer2.source;

import com.google.android.exoplayer2.SeekParameters;
import com.google.android.exoplayer2.trackselection.TrackSelection;

public interface MediaPeriod extends SequenceableLoader {
    public interface Callback extends com.google.android.exoplayer2.source.SequenceableLoader$Callback {
        void onPrepared(MediaPeriod arg1);
    }

    boolean continueLoading(long arg1);

    void discardBuffer(long arg1, boolean arg2);

    long getAdjustedSeekPositionUs(long arg1, SeekParameters arg2);

    long getBufferedPositionUs();

    long getNextLoadPositionUs();

    TrackGroupArray getTrackGroups();

    void maybeThrowPrepareError();

    void prepare(Callback arg1, long arg2);

    long readDiscontinuity();

    void reevaluateBuffer(long arg1);

    long seekToUs(long arg1);

    long selectTracks(TrackSelection[] arg1, boolean[] arg2, SampleStream[] arg3, boolean[] arg4, long arg5);
}

