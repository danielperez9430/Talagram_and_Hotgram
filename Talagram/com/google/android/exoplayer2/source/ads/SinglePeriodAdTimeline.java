package com.google.android.exoplayer2.source.ads;

import com.google.android.exoplayer2.Timeline$Period;
import com.google.android.exoplayer2.Timeline$Window;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.source.ForwardingTimeline;
import com.google.android.exoplayer2.util.Assertions;

public final class SinglePeriodAdTimeline extends ForwardingTimeline {
    private final AdPlaybackState adPlaybackState;

    public SinglePeriodAdTimeline(Timeline arg4, AdPlaybackState arg5) {
        super(arg4);
        boolean v1 = false;
        boolean v0 = arg4.getPeriodCount() == 1 ? true : false;
        Assertions.checkState(v0);
        if(arg4.getWindowCount() == 1) {
            v1 = true;
        }

        Assertions.checkState(v1);
        this.adPlaybackState = arg5;
    }

    public Period getPeriod(int arg11, Period arg12, boolean arg13) {
        this.timeline.getPeriod(arg11, arg12, arg13);
        arg12.set(arg12.id, arg12.uid, arg12.windowIndex, arg12.durationUs, arg12.getPositionInWindowUs(), this.adPlaybackState);
        return arg12;
    }

    public Window getWindow(int arg2, Window arg3, boolean arg4, long arg5) {
        Window v2 = super.getWindow(arg2, arg3, arg4, arg5);
        if(v2.durationUs == -9223372036854775807L) {
            v2.durationUs = this.adPlaybackState.contentDurationUs;
        }

        return v2;
    }
}

