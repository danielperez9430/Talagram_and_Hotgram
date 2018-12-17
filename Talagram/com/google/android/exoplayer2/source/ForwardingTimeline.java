package com.google.android.exoplayer2.source;

import com.google.android.exoplayer2.Timeline$Period;
import com.google.android.exoplayer2.Timeline$Window;
import com.google.android.exoplayer2.Timeline;

public abstract class ForwardingTimeline extends Timeline {
    protected final Timeline timeline;

    public ForwardingTimeline(Timeline arg1) {
        super();
        this.timeline = arg1;
    }

    public int getFirstWindowIndex(boolean arg2) {
        return this.timeline.getFirstWindowIndex(arg2);
    }

    public int getIndexOfPeriod(Object arg2) {
        return this.timeline.getIndexOfPeriod(arg2);
    }

    public int getLastWindowIndex(boolean arg2) {
        return this.timeline.getLastWindowIndex(arg2);
    }

    public int getNextWindowIndex(int arg2, int arg3, boolean arg4) {
        return this.timeline.getNextWindowIndex(arg2, arg3, arg4);
    }

    public Period getPeriod(int arg2, Period arg3, boolean arg4) {
        return this.timeline.getPeriod(arg2, arg3, arg4);
    }

    public int getPeriodCount() {
        return this.timeline.getPeriodCount();
    }

    public int getPreviousWindowIndex(int arg2, int arg3, boolean arg4) {
        return this.timeline.getPreviousWindowIndex(arg2, arg3, arg4);
    }

    public Object getUidOfPeriod(int arg2) {
        return this.timeline.getUidOfPeriod(arg2);
    }

    public Window getWindow(int arg7, Window arg8, boolean arg9, long arg10) {
        return this.timeline.getWindow(arg7, arg8, arg9, arg10);
    }

    public int getWindowCount() {
        return this.timeline.getWindowCount();
    }
}

