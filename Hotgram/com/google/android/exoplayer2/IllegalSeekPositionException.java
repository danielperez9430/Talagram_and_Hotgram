package com.google.android.exoplayer2;

public final class IllegalSeekPositionException extends IllegalStateException {
    public final long positionMs;
    public final Timeline timeline;
    public final int windowIndex;

    public IllegalSeekPositionException(Timeline arg1, int arg2, long arg3) {
        super();
        this.timeline = arg1;
        this.windowIndex = arg2;
        this.positionMs = arg3;
    }
}

