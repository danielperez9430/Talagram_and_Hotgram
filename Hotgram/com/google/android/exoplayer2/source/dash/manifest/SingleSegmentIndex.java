package com.google.android.exoplayer2.source.dash.manifest;

import com.google.android.exoplayer2.source.dash.DashSegmentIndex;

final class SingleSegmentIndex implements DashSegmentIndex {
    private final RangedUri uri;

    public SingleSegmentIndex(RangedUri arg1) {
        super();
        this.uri = arg1;
    }

    public long getDurationUs(long arg1, long arg3) {
        return arg3;
    }

    public long getFirstSegmentNum() {
        return 0;
    }

    public int getSegmentCount(long arg1) {
        return 1;
    }

    public long getSegmentNum(long arg1, long arg3) {
        return 0;
    }

    public RangedUri getSegmentUrl(long arg1) {
        return this.uri;
    }

    public long getTimeUs(long arg1) {
        return 0;
    }

    public boolean isExplicit() {
        return 1;
    }
}

