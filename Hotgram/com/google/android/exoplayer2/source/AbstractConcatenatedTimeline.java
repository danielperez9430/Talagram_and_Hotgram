package com.google.android.exoplayer2.source;

import android.util.Pair;
import com.google.android.exoplayer2.Timeline$Period;
import com.google.android.exoplayer2.Timeline$Window;
import com.google.android.exoplayer2.Timeline;

abstract class AbstractConcatenatedTimeline extends Timeline {
    private final int childCount;
    private final boolean isAtomic;
    private final ShuffleOrder shuffleOrder;

    public AbstractConcatenatedTimeline(boolean arg1, ShuffleOrder arg2) {
        super();
        this.isAtomic = arg1;
        this.shuffleOrder = arg2;
        this.childCount = arg2.getLength();
    }

    protected abstract int getChildIndexByChildUid(Object arg1);

    protected abstract int getChildIndexByPeriodIndex(int arg1);

    protected abstract int getChildIndexByWindowIndex(int arg1);

    protected abstract Object getChildUidByChildIndex(int arg1);

    protected abstract int getFirstPeriodIndexByChildIndex(int arg1);

    public int getFirstWindowIndex(boolean arg4) {
        int v1 = -1;
        if(this.childCount == 0) {
            return v1;
        }

        int v2 = 0;
        if(0 != 0) {
            v2 = this.shuffleOrder.getFirstIndex();
        }

        do {
            if(!this.getTimelineByChildIndex(v2).isEmpty()) {
                goto label_17;
            }

            v2 = this.getNextChildIndex(v2, false);
        }
        while(v2 != v1);

        return v1;
    label_17:
        return this.getFirstWindowIndexByChildIndex(v2) + this.getTimelineByChildIndex(v2).getFirstWindowIndex(false);
    }

    protected abstract int getFirstWindowIndexByChildIndex(int arg1);

    public final int getIndexOfPeriod(Object arg4) {
        int v1 = -1;
        if(!(arg4 instanceof Pair)) {
            return v1;
        }

        Object v0 = ((Pair)arg4).first;
        arg4 = ((Pair)arg4).second;
        int v0_1 = this.getChildIndexByChildUid(v0);
        if(v0_1 == v1) {
            return v1;
        }

        int v4 = this.getTimelineByChildIndex(v0_1).getIndexOfPeriod(arg4);
        if(v4 == v1) {
        }
        else {
            v1 = this.getFirstPeriodIndexByChildIndex(v0_1) + v4;
        }

        return v1;
    }

    public int getLastWindowIndex(boolean arg4) {
        int v1 = -1;
        if(this.childCount == 0) {
            return v1;
        }

        int v0 = 0 != 0 ? this.shuffleOrder.getLastIndex() : this.childCount - 1;
        do {
            if(!this.getTimelineByChildIndex(v0).isEmpty()) {
                goto label_19;
            }

            v0 = this.getPreviousChildIndex(v0, false);
        }
        while(v0 != v1);

        return v1;
    label_19:
        return this.getFirstWindowIndexByChildIndex(v0) + this.getTimelineByChildIndex(v0).getLastWindowIndex(false);
    }

    private int getNextChildIndex(int arg1, boolean arg2) {
        if(arg2) {
            arg1 = this.shuffleOrder.getNextIndex(arg1);
        }
        else if(arg1 < this.childCount - 1) {
            ++arg1;
        }
        else {
            arg1 = -1;
        }

        return arg1;
    }

    public int getNextWindowIndex(int arg6, int arg7, boolean arg8) {
        int v1 = 0;
        int v2 = 2;
        if((this.isAtomic) && arg7 == 1) {
            arg7 = 2;
        }

        int v0 = this.getChildIndexByWindowIndex(arg6);
        int v3 = this.getFirstWindowIndexByChildIndex(v0);
        Timeline v4 = this.getTimelineByChildIndex(v0);
        arg6 -= v3;
        if(arg7 == v2) {
        }
        else {
            v1 = arg7;
        }

        arg6 = v4.getNextWindowIndex(arg6, v1, false);
        v1 = -1;
        if(arg6 != v1) {
            return v3 + arg6;
        }

        for(arg6 = this.getNextChildIndex(v0, false); arg6 != v1; arg6 = this.getNextChildIndex(arg6, false)) {
            if(!this.getTimelineByChildIndex(arg6).isEmpty()) {
                break;
            }
        }

        if(arg6 != v1) {
            return this.getFirstWindowIndexByChildIndex(arg6) + this.getTimelineByChildIndex(arg6).getFirstWindowIndex(false);
        }

        if(arg7 == v2) {
            return this.getFirstWindowIndex(false);
        }

        return v1;
    }

    public final Period getPeriod(int arg5, Period arg6, boolean arg7) {
        int v0 = this.getChildIndexByPeriodIndex(arg5);
        int v1 = this.getFirstWindowIndexByChildIndex(v0);
        this.getTimelineByChildIndex(v0).getPeriod(arg5 - this.getFirstPeriodIndexByChildIndex(v0), arg6, arg7);
        arg6.windowIndex += v1;
        if(arg7) {
            arg6.uid = Pair.create(this.getChildUidByChildIndex(v0), arg6.uid);
        }

        return arg6;
    }

    public final Period getPeriodByUid(Object arg4, Period arg5) {
        Object v1 = arg4.first;
        Object v0 = arg4.second;
        int v1_1 = this.getChildIndexByChildUid(v1);
        int v2 = this.getFirstWindowIndexByChildIndex(v1_1);
        this.getTimelineByChildIndex(v1_1).getPeriodByUid(v0, arg5);
        arg5.windowIndex += v2;
        arg5.uid = arg4;
        return arg5;
    }

    private int getPreviousChildIndex(int arg1, boolean arg2) {
        if(arg2) {
            arg1 = this.shuffleOrder.getPreviousIndex(arg1);
        }
        else if(arg1 > 0) {
            --arg1;
        }
        else {
            arg1 = -1;
        }

        return arg1;
    }

    public int getPreviousWindowIndex(int arg6, int arg7, boolean arg8) {
        int v1 = 0;
        int v2 = 2;
        if((this.isAtomic) && arg7 == 1) {
            arg7 = 2;
        }

        int v0 = this.getChildIndexByWindowIndex(arg6);
        int v3 = this.getFirstWindowIndexByChildIndex(v0);
        Timeline v4 = this.getTimelineByChildIndex(v0);
        arg6 -= v3;
        if(arg7 == v2) {
        }
        else {
            v1 = arg7;
        }

        arg6 = v4.getPreviousWindowIndex(arg6, v1, false);
        v1 = -1;
        if(arg6 != v1) {
            return v3 + arg6;
        }

        for(arg6 = this.getPreviousChildIndex(v0, false); arg6 != v1; arg6 = this.getPreviousChildIndex(arg6, false)) {
            if(!this.getTimelineByChildIndex(arg6).isEmpty()) {
                break;
            }
        }

        if(arg6 != v1) {
            return this.getFirstWindowIndexByChildIndex(arg6) + this.getTimelineByChildIndex(arg6).getLastWindowIndex(false);
        }

        if(arg7 == v2) {
            return this.getLastWindowIndex(false);
        }

        return v1;
    }

    protected abstract Timeline getTimelineByChildIndex(int arg1);

    public final Object getUidOfPeriod(int arg4) {
        int v0 = this.getChildIndexByPeriodIndex(arg4);
        return Pair.create(this.getChildUidByChildIndex(v0), this.getTimelineByChildIndex(v0).getUidOfPeriod(arg4 - this.getFirstPeriodIndexByChildIndex(v0)));
    }

    public final Window getWindow(int arg10, Window arg11, boolean arg12, long arg13) {
        int v0 = this.getChildIndexByWindowIndex(arg10);
        int v1 = this.getFirstWindowIndexByChildIndex(v0);
        int v2 = this.getFirstPeriodIndexByChildIndex(v0);
        this.getTimelineByChildIndex(v0).getWindow(arg10 - v1, arg11, arg12, arg13);
        arg11.firstPeriodIndex += v2;
        arg11.lastPeriodIndex += v2;
        return arg11;
    }
}

