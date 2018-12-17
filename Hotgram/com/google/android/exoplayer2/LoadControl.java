package com.google.android.exoplayer2;

import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.upstream.Allocator;

public interface LoadControl {
    Allocator getAllocator();

    long getBackBufferDurationUs();

    void onPrepared();

    void onReleased();

    void onStopped();

    void onTracksSelected(Renderer[] arg1, TrackGroupArray arg2, TrackSelectionArray arg3);

    boolean retainBackBufferFromKeyframe();

    boolean shouldContinueLoading(long arg1, float arg2);

    boolean shouldStartPlayback(long arg1, float arg2, boolean arg3);
}

