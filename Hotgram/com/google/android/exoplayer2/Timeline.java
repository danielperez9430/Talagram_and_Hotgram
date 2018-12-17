package com.google.android.exoplayer2;

import android.util.Pair;
import com.google.android.exoplayer2.source.ads.AdPlaybackState$AdGroup;
import com.google.android.exoplayer2.source.ads.AdPlaybackState;
import com.google.android.exoplayer2.util.Assertions;

public abstract class Timeline {
    final class com.google.android.exoplayer2.Timeline$1 extends Timeline {
        com.google.android.exoplayer2.Timeline$1() {
            super();
        }

        public int getIndexOfPeriod(Object arg1) {
            return -1;
        }

        public Period getPeriod(int arg1, Period arg2, boolean arg3) {
            throw new IndexOutOfBoundsException();
        }

        public int getPeriodCount() {
            return 0;
        }

        public Object getUidOfPeriod(int arg1) {
            throw new IndexOutOfBoundsException();
        }

        public Window getWindow(int arg1, Window arg2, boolean arg3, long arg4) {
            throw new IndexOutOfBoundsException();
        }

        public int getWindowCount() {
            return 0;
        }
    }

    public final class Period {
        private AdPlaybackState adPlaybackState;
        public long durationUs;
        public Object id;
        private long positionInWindowUs;
        public Object uid;
        public int windowIndex;

        public Period() {
            super();
        }

        public int getAdCountInAdGroup(int arg2) {
            return this.adPlaybackState.adGroups[arg2].count;
        }

        public long getAdDurationUs(int arg3, int arg4) {
            AdGroup v3 = this.adPlaybackState.adGroups[arg3];
            long v0 = v3.count != -1 ? v3.durationsUs[arg4] : -9223372036854775807L;
            return v0;
        }

        public int getAdGroupCount() {
            return this.adPlaybackState.adGroupCount;
        }

        public int getAdGroupIndexAfterPositionUs(long arg2) {
            return this.adPlaybackState.getAdGroupIndexAfterPositionUs(arg2);
        }

        public int getAdGroupIndexForPositionUs(long arg2) {
            return this.adPlaybackState.getAdGroupIndexForPositionUs(arg2);
        }

        public long getAdGroupTimeUs(int arg4) {
            return this.adPlaybackState.adGroupTimesUs[arg4];
        }

        public long getAdResumePositionUs() {
            return this.adPlaybackState.adResumePositionUs;
        }

        public long getDurationMs() {
            return C.usToMs(this.durationUs);
        }

        public long getDurationUs() {
            return this.durationUs;
        }

        public int getFirstAdIndexToPlay(int arg2) {
            return this.adPlaybackState.adGroups[arg2].getFirstAdIndexToPlay();
        }

        public int getNextAdIndexToPlay(int arg2, int arg3) {
            return this.adPlaybackState.adGroups[arg2].getNextAdIndexToPlay(arg3);
        }

        public long getPositionInWindowMs() {
            return C.usToMs(this.positionInWindowUs);
        }

        public long getPositionInWindowUs() {
            return this.positionInWindowUs;
        }

        public boolean hasPlayedAdGroup(int arg2) {
            return this.adPlaybackState.adGroups[arg2].hasUnplayedAds() ^ 1;
        }

        public boolean isAdAvailable(int arg3, int arg4) {
            AdGroup v3 = this.adPlaybackState.adGroups[arg3];
            boolean v3_1 = v3.count == -1 || v3.states[arg4] == 0 ? false : true;
            return v3_1;
        }

        public Period set(Object arg10, Object arg11, int arg12, long arg13, long arg15) {
            return this.set(arg10, arg11, arg12, arg13, arg15, AdPlaybackState.NONE);
        }

        public Period set(Object arg1, Object arg2, int arg3, long arg4, long arg6, AdPlaybackState arg8) {
            this.id = arg1;
            this.uid = arg2;
            this.windowIndex = arg3;
            this.durationUs = arg4;
            this.positionInWindowUs = arg6;
            this.adPlaybackState = arg8;
            return this;
        }
    }

    public final class Window {
        public long defaultPositionUs;
        public long durationUs;
        public int firstPeriodIndex;
        public boolean isDynamic;
        public boolean isSeekable;
        public int lastPeriodIndex;
        public long positionInFirstPeriodUs;
        public long presentationStartTimeMs;
        public Object tag;
        public long windowStartTimeMs;

        public Window() {
            super();
        }

        public long getDefaultPositionMs() {
            return C.usToMs(this.defaultPositionUs);
        }

        public long getDefaultPositionUs() {
            return this.defaultPositionUs;
        }

        public long getDurationMs() {
            return C.usToMs(this.durationUs);
        }

        public long getDurationUs() {
            return this.durationUs;
        }

        public long getPositionInFirstPeriodMs() {
            return C.usToMs(this.positionInFirstPeriodUs);
        }

        public long getPositionInFirstPeriodUs() {
            return this.positionInFirstPeriodUs;
        }

        public Window set(Object arg1, long arg2, long arg4, boolean arg6, boolean arg7, long arg8, long arg10, int arg12, int arg13, long arg14) {
            this.tag = arg1;
            this.presentationStartTimeMs = arg2;
            this.windowStartTimeMs = arg4;
            this.isSeekable = arg6;
            this.isDynamic = arg7;
            this.defaultPositionUs = arg8;
            this.durationUs = arg10;
            this.firstPeriodIndex = arg12;
            this.lastPeriodIndex = arg13;
            this.positionInFirstPeriodUs = arg14;
            return this;
        }
    }

    public static final Timeline EMPTY;

    static {
        Timeline.EMPTY = new com.google.android.exoplayer2.Timeline$1();
    }

    public Timeline() {
        super();
    }

    public int getFirstWindowIndex(boolean arg1) {
        int v1 = this.isEmpty() ? -1 : 0;
        return v1;
    }

    public abstract int getIndexOfPeriod(Object arg1);

    public int getLastWindowIndex(boolean arg1) {
        int v1 = this.isEmpty() ? -1 : this.getWindowCount() - 1;
        return v1;
    }

    public final int getNextPeriodIndex(int arg2, Period arg3, Window arg4, int arg5, boolean arg6) {
        int v3 = this.getPeriod(arg2, arg3).windowIndex;
        if(this.getWindow(v3, arg4).lastPeriodIndex == arg2) {
            arg2 = this.getNextWindowIndex(v3, arg5, arg6);
            v3 = -1;
            if(arg2 == v3) {
                return v3;
            }

            return this.getWindow(arg2, arg4).firstPeriodIndex;
        }

        return arg2 + 1;
    }

    public int getNextWindowIndex(int arg1, int arg2, boolean arg3) {
        switch(arg2) {
            case 0: {
                goto label_10;
            }
            case 1: {
                return arg1;
            }
            case 2: {
                goto label_4;
            }
        }

        throw new IllegalStateException();
    label_4:
        if(arg1 == this.getLastWindowIndex(arg3)) {
            arg1 = this.getFirstWindowIndex(arg3);
        }
        else {
            ++arg1;
        }

        return arg1;
    label_10:
        if(arg1 == this.getLastWindowIndex(arg3)) {
            arg1 = -1;
        }
        else {
            ++arg1;
        }

        return arg1;
    }

    public abstract Period getPeriod(int arg1, Period arg2, boolean arg3);

    public final Period getPeriod(int arg2, Period arg3) {
        return this.getPeriod(arg2, arg3, false);
    }

    public Period getPeriodByUid(Object arg2, Period arg3) {
        return this.getPeriod(this.getIndexOfPeriod(arg2), arg3, true);
    }

    public abstract int getPeriodCount();

    public final Pair getPeriodPosition(Window arg9, Period arg10, int arg11, long arg12, long arg14) {
        Assertions.checkIndex(arg11, 0, this.getWindowCount());
        this.getWindow(arg11, arg9, false, arg14);
        arg14 = -9223372036854775807L;
        if(arg12 == arg14) {
            arg12 = arg9.getDefaultPositionUs();
            if(arg12 == arg14) {
                return null;
            }
        }

        arg11 = arg9.firstPeriodIndex;
        long v0 = arg9.getPositionInFirstPeriodUs() + arg12;
        while(true) {
            arg12 = this.getPeriod(arg11, arg10).getDurationUs();
            if(arg12 != arg14 && v0 >= arg12 && arg11 < arg9.lastPeriodIndex) {
                v0 -= arg12;
                ++arg11;
                continue;
            }

            break;
        }

        return Pair.create(Integer.valueOf(arg11), Long.valueOf(v0));
    }

    public final Pair getPeriodPosition(Window arg9, Period arg10, int arg11, long arg12) {
        return this.getPeriodPosition(arg9, arg10, arg11, arg12, 0);
    }

    public int getPreviousWindowIndex(int arg1, int arg2, boolean arg3) {
        switch(arg2) {
            case 0: {
                goto label_10;
            }
            case 1: {
                return arg1;
            }
            case 2: {
                goto label_4;
            }
        }

        throw new IllegalStateException();
    label_4:
        if(arg1 == this.getFirstWindowIndex(arg3)) {
            arg1 = this.getLastWindowIndex(arg3);
        }
        else {
            --arg1;
        }

        return arg1;
    label_10:
        if(arg1 == this.getFirstWindowIndex(arg3)) {
            arg1 = -1;
        }
        else {
            --arg1;
        }

        return arg1;
    }

    public abstract Object getUidOfPeriod(int arg1);

    public final Window getWindow(int arg2, Window arg3) {
        return this.getWindow(arg2, arg3, false);
    }

    public abstract Window getWindow(int arg1, Window arg2, boolean arg3, long arg4);

    public final Window getWindow(int arg7, Window arg8, boolean arg9) {
        return this.getWindow(arg7, arg8, arg9, 0);
    }

    public abstract int getWindowCount();

    public final boolean isEmpty() {
        boolean v0 = this.getWindowCount() == 0 ? true : false;
        return v0;
    }

    public final boolean isLastPeriod(int arg1, Period arg2, Window arg3, int arg4, boolean arg5) {
        boolean v1 = this.getNextPeriodIndex(arg1, arg2, arg3, arg4, arg5) == -1 ? true : false;
        return v1;
    }
}

