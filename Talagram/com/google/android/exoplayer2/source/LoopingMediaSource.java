package com.google.android.exoplayer2.source;

import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.upstream.Allocator;
import com.google.android.exoplayer2.upstream.TransferListener;
import com.google.android.exoplayer2.util.Assertions;

public final class LoopingMediaSource extends CompositeMediaSource {
    final class InfinitelyLoopingTimeline extends ForwardingTimeline {
        public InfinitelyLoopingTimeline(Timeline arg1) {
            super(arg1);
        }

        public int getNextWindowIndex(int arg2, int arg3, boolean arg4) {
            arg2 = this.timeline.getNextWindowIndex(arg2, arg3, arg4);
            if(arg2 == -1) {
                arg2 = this.getFirstWindowIndex(arg4);
            }

            return arg2;
        }

        public int getPreviousWindowIndex(int arg2, int arg3, boolean arg4) {
            arg2 = this.timeline.getPreviousWindowIndex(arg2, arg3, arg4);
            if(arg2 == -1) {
                arg2 = this.getLastWindowIndex(arg4);
            }

            return arg2;
        }
    }

    final class LoopingTimeline extends AbstractConcatenatedTimeline {
        private final int childPeriodCount;
        private final Timeline childTimeline;
        private final int childWindowCount;
        private final int loopCount;

        public LoopingTimeline(Timeline arg3, int arg4) {
            boolean v1 = false;
            super(false, new UnshuffledShuffleOrder(arg4));
            this.childTimeline = arg3;
            this.childPeriodCount = arg3.getPeriodCount();
            this.childWindowCount = arg3.getWindowCount();
            this.loopCount = arg4;
            if(this.childPeriodCount > 0) {
                if(arg4 <= 2147483647 / this.childPeriodCount) {
                    v1 = true;
                }

                Assertions.checkState(v1, "LoopingMediaSource contains too many periods");
            }
        }

        protected int getChildIndexByChildUid(Object arg2) {
            if(!(arg2 instanceof Integer)) {
                return -1;
            }

            return ((Integer)arg2).intValue();
        }

        protected int getChildIndexByPeriodIndex(int arg2) {
            return arg2 / this.childPeriodCount;
        }

        protected int getChildIndexByWindowIndex(int arg2) {
            return arg2 / this.childWindowCount;
        }

        protected Object getChildUidByChildIndex(int arg1) {
            return Integer.valueOf(arg1);
        }

        protected int getFirstPeriodIndexByChildIndex(int arg2) {
            return arg2 * this.childPeriodCount;
        }

        protected int getFirstWindowIndexByChildIndex(int arg2) {
            return arg2 * this.childWindowCount;
        }

        public int getPeriodCount() {
            return this.childPeriodCount * this.loopCount;
        }

        protected Timeline getTimelineByChildIndex(int arg1) {
            return this.childTimeline;
        }

        public int getWindowCount() {
            return this.childWindowCount * this.loopCount;
        }
    }

    private int childPeriodCount;
    private final MediaSource childSource;
    private final int loopCount;

    public LoopingMediaSource(MediaSource arg2) {
        this(arg2, 2147483647);
    }

    public LoopingMediaSource(MediaSource arg2, int arg3) {
        super();
        boolean v0 = arg3 > 0 ? true : false;
        Assertions.checkArgument(v0);
        this.childSource = arg2;
        this.loopCount = arg3;
    }

    public MediaPeriod createPeriod(MediaPeriodId arg4, Allocator arg5) {
        MediaSource v0;
        if(this.loopCount != 2147483647) {
            v0 = this.childSource;
            arg4 = arg4.copyWithPeriodIndex(arg4.periodIndex % this.childPeriodCount);
        }
        else {
            v0 = this.childSource;
        }

        MediaPeriod v4 = v0.createPeriod(arg4, arg5);
        return v4;
    }

    protected void onChildSourceInfoRefreshed(Object arg1, MediaSource arg2, Timeline arg3, Object arg4) {
        this.onChildSourceInfoRefreshed(((Void)arg1), arg2, arg3, arg4);
    }

    protected void onChildSourceInfoRefreshed(Void arg1, MediaSource arg2, Timeline arg3, Object arg4) {
        InfinitelyLoopingTimeline v1_1;
        this.childPeriodCount = arg3.getPeriodCount();
        if(this.loopCount != 2147483647) {
            LoopingTimeline v1 = new LoopingTimeline(arg3, this.loopCount);
        }
        else {
            v1_1 = new InfinitelyLoopingTimeline(arg3);
        }

        this.refreshSourceInfo(((Timeline)v1_1), arg4);
    }

    public void prepareSourceInternal(ExoPlayer arg1, boolean arg2, TransferListener arg3) {
        super.prepareSourceInternal(arg1, arg2, arg3);
        this.prepareChildSource(null, this.childSource);
    }

    public void releasePeriod(MediaPeriod arg2) {
        this.childSource.releasePeriod(arg2);
    }

    public void releaseSourceInternal() {
        super.releaseSourceInternal();
        this.childPeriodCount = 0;
    }
}

