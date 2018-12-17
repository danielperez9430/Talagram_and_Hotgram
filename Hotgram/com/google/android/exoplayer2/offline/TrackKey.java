package com.google.android.exoplayer2.offline;

public final class TrackKey {
    public final int groupIndex;
    public final int periodIndex;
    public final int trackIndex;

    public TrackKey(int arg1, int arg2, int arg3) {
        super();
        this.periodIndex = arg1;
        this.groupIndex = arg2;
        this.trackIndex = arg3;
    }
}

