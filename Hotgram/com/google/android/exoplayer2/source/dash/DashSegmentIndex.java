package com.google.android.exoplayer2.source.dash;

import com.google.android.exoplayer2.source.dash.manifest.RangedUri;

public interface DashSegmentIndex {
    public static final int INDEX_UNBOUNDED = -1;

    long getDurationUs(long arg1, long arg2);

    long getFirstSegmentNum();

    int getSegmentCount(long arg1);

    long getSegmentNum(long arg1, long arg2);

    RangedUri getSegmentUrl(long arg1);

    long getTimeUs(long arg1);

    boolean isExplicit();
}

