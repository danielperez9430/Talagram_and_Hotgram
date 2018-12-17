package com.google.android.exoplayer2.source;

import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.Timeline$Period;
import com.google.android.exoplayer2.Timeline$Window;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.upstream.Allocator;
import com.google.android.exoplayer2.upstream.TransferListener;
import com.google.android.exoplayer2.util.Assertions;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;

public final class ClippingMediaSource extends CompositeMediaSource {
    final class ClippingTimeline extends ForwardingTimeline {
        private final long durationUs;
        private final long endUs;
        private final boolean isDynamic;
        private final long startUs;

        public ClippingTimeline(Timeline arg10, long arg11, long arg13) {
            super(arg10);
            boolean v1 = true;
            if(arg10.getPeriodCount() == 1) {
                Window v10 = arg10.getWindow(0, new Window(), false);
                long v3 = 0;
                arg11 = Math.max(v3, arg11);
                arg13 = arg13 == -9223372036854775808L ? v10.durationUs : Math.max(v3, arg13);
                long v7 = -9223372036854775807L;
                if(v10.durationUs != v7) {
                    if(arg13 > v10.durationUs) {
                        arg13 = v10.durationUs;
                    }

                    if(arg11 != v3) {
                        if(v10.isSeekable) {
                        }
                        else {
                            throw new IllegalClippingException(1);
                        }
                    }

                    if(arg11 <= arg13) {
                        goto label_34;
                    }

                    throw new IllegalClippingException(2);
                }

            label_34:
                this.startUs = arg11;
                this.endUs = arg13;
                arg11 = arg13 == v7 ? v7 : arg13 - arg11;
                this.durationUs = arg11;
                if(!v10.isDynamic) {
                label_50:
                    v1 = false;
                }
                else if(arg13 != v7) {
                    if(v10.durationUs == v7) {
                        goto label_50;
                    }
                    else if(arg13 == v10.durationUs) {
                    }
                    else {
                        goto label_50;
                    }
                }

                this.isDynamic = v1;
                return;
            }

            throw new IllegalClippingException(0);
        }

        public Period getPeriod(int arg13, Period arg14, boolean arg15) {
            this.timeline.getPeriod(0, arg14, arg15);
            long v10 = arg14.getPositionInWindowUs() - this.startUs;
            long v2 = -9223372036854775807L;
            long v8 = this.durationUs == v2 ? v2 : this.durationUs - v10;
            return arg14.set(arg14.id, arg14.uid, 0, v8, v10);
        }

        public Window getWindow(int arg7, Window arg8, boolean arg9, long arg10) {
            long v9;
            this.timeline.getWindow(0, arg8, arg9, 0);
            arg8.positionInFirstPeriodUs += this.startUs;
            arg8.durationUs = this.durationUs;
            arg8.isDynamic = this.isDynamic;
            long v0 = -9223372036854775807L;
            if(arg8.defaultPositionUs != v0) {
                arg8.defaultPositionUs = Math.max(arg8.defaultPositionUs, this.startUs);
                v9 = this.endUs == v0 ? arg8.defaultPositionUs : Math.min(arg8.defaultPositionUs, this.endUs);
                arg8.defaultPositionUs = v9;
                arg8.defaultPositionUs -= this.startUs;
            }

            v9 = C.usToMs(this.startUs);
            if(arg8.presentationStartTimeMs != v0) {
                arg8.presentationStartTimeMs += v9;
            }

            if(arg8.windowStartTimeMs != v0) {
                arg8.windowStartTimeMs += v9;
            }

            return arg8;
        }
    }

    public final class IllegalClippingException extends IOException {
        @Retention(value=RetentionPolicy.SOURCE) @public interface Reason {
        }

        public static final int REASON_INVALID_PERIOD_COUNT = 0;
        public static final int REASON_NOT_SEEKABLE_TO_START = 1;
        public static final int REASON_START_EXCEEDS_END = 2;
        public final int reason;

        public IllegalClippingException(int arg3) {
            super("Illegal clipping: " + IllegalClippingException.getReasonDescription(arg3));
            this.reason = arg3;
        }

        private static String getReasonDescription(int arg0) {
            switch(arg0) {
                case 0: {
                    return "invalid period count";
                }
                case 1: {
                    return "not seekable to start";
                }
                case 2: {
                    return "start exceeds end";
                }
            }

            return "unknown";
        }
    }

    private final boolean allowDynamicClippingUpdates;
    private IllegalClippingException clippingError;
    private ClippingTimeline clippingTimeline;
    private final boolean enableInitialDiscontinuity;
    private final long endUs;
    private Object manifest;
    private final ArrayList mediaPeriods;
    private final MediaSource mediaSource;
    private long periodEndUs;
    private long periodStartUs;
    private final boolean relativeToDefaultPosition;
    private final long startUs;
    private final Window window;

    public ClippingMediaSource(MediaSource arg10, long arg11) {
        this(arg10, 0, arg11, true, false, true);
    }

    public ClippingMediaSource(MediaSource arg4, long arg5, long arg7, boolean arg9, boolean arg10, boolean arg11) {
        super();
        boolean v0 = arg5 >= 0 ? true : false;
        Assertions.checkArgument(v0);
        this.mediaSource = Assertions.checkNotNull(arg4);
        this.startUs = arg5;
        this.endUs = arg7;
        this.enableInitialDiscontinuity = arg9;
        this.allowDynamicClippingUpdates = arg10;
        this.relativeToDefaultPosition = arg11;
        this.mediaPeriods = new ArrayList();
        this.window = new Window();
    }

    public ClippingMediaSource(MediaSource arg10, long arg11, long arg13) {
        this(arg10, arg11, arg13, true, false, false);
    }

    @Deprecated public ClippingMediaSource(MediaSource arg10, long arg11, long arg13, boolean arg15) {
        this(arg10, arg11, arg13, arg15, false, false);
    }

    public MediaPeriod createPeriod(MediaPeriodId arg9, Allocator arg10) {
        ClippingMediaPeriod v7 = new ClippingMediaPeriod(this.mediaSource.createPeriod(arg9, arg10), this.enableInitialDiscontinuity, this.periodStartUs, this.periodEndUs);
        this.mediaPeriods.add(v7);
        return ((MediaPeriod)v7);
    }

    protected long getMediaTimeForChildMediaTime(Object arg1, long arg2) {
        return this.getMediaTimeForChildMediaTime(((Void)arg1), arg2);
    }

    protected long getMediaTimeForChildMediaTime(Void arg7, long arg8) {
        long v0 = -9223372036854775807L;
        if(arg8 == v0) {
            return v0;
        }

        v0 = C.usToMs(this.startUs);
        long v7 = Math.max(0, arg8 - v0);
        if(this.endUs != -9223372036854775808L) {
            v7 = Math.min(C.usToMs(this.endUs) - v0, v7);
        }

        return v7;
    }

    public void maybeThrowSourceInfoRefreshError() {
        if(this.clippingError == null) {
            super.maybeThrowSourceInfoRefreshError();
            return;
        }

        throw this.clippingError;
    }

    protected void onChildSourceInfoRefreshed(Object arg1, MediaSource arg2, Timeline arg3, Object arg4) {
        this.onChildSourceInfoRefreshed(((Void)arg1), arg2, arg3, arg4);
    }

    protected void onChildSourceInfoRefreshed(Void arg1, MediaSource arg2, Timeline arg3, Object arg4) {
        if(this.clippingError != null) {
            return;
        }

        this.manifest = arg4;
        this.refreshClippedTimeline(arg3);
    }

    public void prepareSourceInternal(ExoPlayer arg1, boolean arg2, TransferListener arg3) {
        super.prepareSourceInternal(arg1, arg2, arg3);
        this.prepareChildSource(null, this.mediaSource);
    }

    private void refreshClippedTimeline(Timeline arg16) {
        ClippingMediaSource v1 = this;
        int v2 = 0;
        arg16.getWindow(0, v1.window);
        long v5 = v1.window.getPositionInFirstPeriodUs();
        long v7 = -9223372036854775808L;
        if(v1.clippingTimeline == null || (v1.mediaPeriods.isEmpty()) || (v1.allowDynamicClippingUpdates)) {
            long v9 = v1.startUs;
            long v11 = v1.endUs;
            if(v1.relativeToDefaultPosition) {
                long v13 = v1.window.getDefaultPositionUs();
                v9 += v13;
                v11 += v13;
            }

            v1.periodStartUs = v5 + v9;
            if(v1.endUs == v7) {
            }
            else {
                v7 = v5 + v11;
            }

            v1.periodEndUs = v7;
            int v0 = v1.mediaPeriods.size();
            while(v2 < v0) {
                v1.mediaPeriods.get(v2).updateClipping(v1.periodStartUs, v1.periodEndUs);
                ++v2;
            }

            v5 = v9;
            v7 = v11;
        }
        else {
            long v2_1 = v1.periodStartUs - v5;
            if(v1.endUs == v7) {
            }
            else {
                v7 = v1.periodEndUs - v5;
            }

            v5 = v2_1;
        }

        try {
            v1.clippingTimeline = new ClippingTimeline(arg16, v5, v7);
        }
        catch(IllegalClippingException v0_1) {
            v1.clippingError = v0_1;
            return;
        }

        this.refreshSourceInfo(v1.clippingTimeline, v1.manifest);
    }

    public void releasePeriod(MediaPeriod arg2) {
        Assertions.checkState(this.mediaPeriods.remove(arg2));
        this.mediaSource.releasePeriod(((ClippingMediaPeriod)arg2).mediaPeriod);
        if((this.mediaPeriods.isEmpty()) && !this.allowDynamicClippingUpdates) {
            this.refreshClippedTimeline(this.clippingTimeline.timeline);
        }
    }

    public void releaseSourceInternal() {
        super.releaseSourceInternal();
        this.clippingError = null;
        this.clippingTimeline = null;
    }
}

