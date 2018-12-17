package com.google.android.exoplayer2;

import com.google.android.exoplayer2.source.MediaSource$MediaPeriodId;

final class MediaPeriodInfo {
    public final long contentPositionUs;
    public final long durationUs;
    public final MediaPeriodId id;
    public final boolean isFinal;
    public final boolean isLastInTimelinePeriod;
    public final long startPositionUs;

    MediaPeriodInfo(MediaPeriodId arg1, long arg2, long arg4, long arg6, boolean arg8, boolean arg9) {
        super();
        this.id = arg1;
        this.startPositionUs = arg2;
        this.contentPositionUs = arg4;
        this.durationUs = arg6;
        this.isLastInTimelinePeriod = arg8;
        this.isFinal = arg9;
    }

    public MediaPeriodInfo copyWithPeriodIndex(int arg12) {
        return new MediaPeriodInfo(this.id.copyWithPeriodIndex(arg12), this.startPositionUs, this.contentPositionUs, this.durationUs, this.isLastInTimelinePeriod, this.isFinal);
    }

    public MediaPeriodInfo copyWithStartPositionUs(long arg12) {
        return new MediaPeriodInfo(this.id, arg12, this.contentPositionUs, this.durationUs, this.isLastInTimelinePeriod, this.isFinal);
    }
}

