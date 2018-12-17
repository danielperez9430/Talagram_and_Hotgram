package com.google.android.exoplayer2.source;

import com.google.android.exoplayer2.Timeline$Period;
import com.google.android.exoplayer2.Timeline$Window;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.util.Assertions;

public final class SinglePeriodTimeline extends Timeline {
    private static final Object UID;
    private final boolean isDynamic;
    private final boolean isSeekable;
    private final long periodDurationUs;
    private final long presentationStartTimeMs;
    private final Object tag;
    private final long windowDefaultStartPositionUs;
    private final long windowDurationUs;
    private final long windowPositionInPeriodUs;
    private final long windowStartTimeMs;

    static {
        SinglePeriodTimeline.UID = new Object();
    }

    public SinglePeriodTimeline(long arg1, long arg3, long arg5, long arg7, long arg9, long arg11, boolean arg13, boolean arg14, Object arg15) {
        super();
        this.presentationStartTimeMs = arg1;
        this.windowStartTimeMs = arg3;
        this.periodDurationUs = arg5;
        this.windowDurationUs = arg7;
        this.windowPositionInPeriodUs = arg9;
        this.windowDefaultStartPositionUs = arg11;
        this.isSeekable = arg13;
        this.isDynamic = arg14;
        this.tag = arg15;
    }

    public SinglePeriodTimeline(long arg17, long arg19, long arg21, long arg23, boolean arg25, boolean arg26, Object arg27) {
        this(-9223372036854775807L, -9223372036854775807L, arg17, arg19, arg21, arg23, arg25, arg26, arg27);
    }

    public SinglePeriodTimeline(long arg7, boolean arg9, boolean arg10) {
        this(arg7, arg9, arg10, null);
    }

    public SinglePeriodTimeline(long arg13, boolean arg15, boolean arg16, Object arg17) {
        this(arg13, arg13, 0, 0, arg15, arg16, arg17);
    }

    public int getIndexOfPeriod(Object arg2) {
        int v2 = SinglePeriodTimeline.UID.equals(arg2) ? 0 : -1;
        return v2;
    }

    public Period getPeriod(int arg9, Period arg10, boolean arg11) {
        Assertions.checkIndex(arg9, 0, 1);
        Object v9 = arg11 ? SinglePeriodTimeline.UID : null;
        Object v2 = v9;
        return arg10.set(null, v2, 0, this.periodDurationUs, -this.windowPositionInPeriodUs);
    }

    public int getPeriodCount() {
        return 1;
    }

    public Object getUidOfPeriod(int arg3) {
        Assertions.checkIndex(arg3, 0, 1);
        return SinglePeriodTimeline.UID;
    }

    public Window getWindow(int arg21, Window arg22, boolean arg23, long arg24) {
        long v12;
        SinglePeriodTimeline v0 = this;
        Assertions.checkIndex(arg21, 0, 1);
        Object v3 = arg23 ? v0.tag : null;
        Object v5 = v3;
        long v3_1 = v0.windowDefaultStartPositionUs;
        long v7 = -9223372036854775807L;
        if(!v0.isDynamic || arg24 == 0) {
        label_26:
            v12 = v3_1;
        }
        else {
            if(v0.windowDurationUs != v7) {
                v3_1 += arg24;
                if(v3_1 > v0.windowDurationUs) {
                }
                else {
                    goto label_26;
                }
            }

            v12 = v7;
        }

        return arg22.set(v5, v0.presentationStartTimeMs, v0.windowStartTimeMs, v0.isSeekable, v0.isDynamic, v12, v0.windowDurationUs, 0, 0, v0.windowPositionInPeriodUs);
    }

    public int getWindowCount() {
        return 1;
    }
}

