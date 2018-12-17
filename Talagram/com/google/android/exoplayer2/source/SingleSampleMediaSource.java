package com.google.android.exoplayer2.source;

import android.net.Uri;
import android.os.Handler;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.upstream.Allocator;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.TransferListener;
import com.google.android.exoplayer2.util.Assertions;
import java.io.IOException;

public final class SingleSampleMediaSource extends BaseMediaSource {
    class com.google.android.exoplayer2.source.SingleSampleMediaSource$1 {
    }

    @Deprecated public interface EventListener {
        void onLoadError(int arg1, IOException arg2);
    }

    final class EventListenerWrapper extends DefaultMediaSourceEventListener {
        private final EventListener eventListener;
        private final int eventSourceId;

        public EventListenerWrapper(EventListener arg1, int arg2) {
            super();
            this.eventListener = Assertions.checkNotNull(arg1);
            this.eventSourceId = arg2;
        }

        public void onLoadError(int arg1, MediaPeriodId arg2, LoadEventInfo arg3, MediaLoadData arg4, IOException arg5, boolean arg6) {
            this.eventListener.onLoadError(this.eventSourceId, arg5);
        }
    }

    public final class Factory {
        private final com.google.android.exoplayer2.upstream.DataSource$Factory dataSourceFactory;
        private boolean isCreateCalled;
        private int minLoadableRetryCount;
        private Object tag;
        private boolean treatLoadErrorsAsEndOfStream;

        public Factory(com.google.android.exoplayer2.upstream.DataSource$Factory arg1) {
            super();
            this.dataSourceFactory = Assertions.checkNotNull(arg1);
            this.minLoadableRetryCount = 3;
        }

        public SingleSampleMediaSource createMediaSource(Uri arg12, Format arg13, long arg14) {
            this.isCreateCalled = true;
            return new SingleSampleMediaSource(arg12, this.dataSourceFactory, arg13, arg14, this.minLoadableRetryCount, this.treatLoadErrorsAsEndOfStream, this.tag, null);
        }

        @Deprecated public SingleSampleMediaSource createMediaSource(Uri arg1, Format arg2, long arg3, Handler arg5, MediaSourceEventListener arg6) {
            SingleSampleMediaSource v1 = this.createMediaSource(arg1, arg2, arg3);
            if(arg5 != null && arg6 != null) {
                v1.addEventListener(arg5, arg6);
            }

            return v1;
        }

        public Factory setMinLoadableRetryCount(int arg2) {
            Assertions.checkState(this.isCreateCalled ^ 1);
            this.minLoadableRetryCount = arg2;
            return this;
        }

        public Factory setTag(Object arg2) {
            Assertions.checkState(this.isCreateCalled ^ 1);
            this.tag = arg2;
            return this;
        }

        public Factory setTreatLoadErrorsAsEndOfStream(boolean arg2) {
            Assertions.checkState(this.isCreateCalled ^ 1);
            this.treatLoadErrorsAsEndOfStream = arg2;
            return this;
        }
    }

    public static final int DEFAULT_MIN_LOADABLE_RETRY_COUNT = 3;
    private final com.google.android.exoplayer2.upstream.DataSource$Factory dataSourceFactory;
    private final DataSpec dataSpec;
    private final long durationUs;
    private final Format format;
    private final int minLoadableRetryCount;
    private final Timeline timeline;
    private TransferListener transferListener;
    private final boolean treatLoadErrorsAsEndOfStream;

    @Deprecated public SingleSampleMediaSource(Uri arg8, com.google.android.exoplayer2.upstream.DataSource$Factory arg9, Format arg10, long arg11) {
        this(arg8, arg9, arg10, arg11, 3);
    }

    @Deprecated public SingleSampleMediaSource(Uri arg10, com.google.android.exoplayer2.upstream.DataSource$Factory arg11, Format arg12, long arg13, int arg15) {
        this(arg10, arg11, arg12, arg13, arg15, false, null);
    }

    private SingleSampleMediaSource(Uri arg7, com.google.android.exoplayer2.upstream.DataSource$Factory arg8, Format arg9, long arg10, int arg12, boolean arg13, Object arg14) {
        super();
        this.dataSourceFactory = arg8;
        this.format = arg9;
        this.durationUs = arg10;
        this.minLoadableRetryCount = arg12;
        this.treatLoadErrorsAsEndOfStream = arg13;
        this.dataSpec = new DataSpec(arg7);
        this.timeline = new SinglePeriodTimeline(arg10, true, false, arg14);
    }

    @Deprecated public SingleSampleMediaSource(Uri arg12, com.google.android.exoplayer2.upstream.DataSource$Factory arg13, Format arg14, long arg15, int arg17, Handler arg18, EventListener arg19, int arg20, boolean arg21) {
        Handler v0 = arg18;
        EventListener v1 = arg19;
        this(arg12, arg13, arg14, arg15, arg17, arg21, null);
        if(v0 != null && v1 != null) {
            this.addEventListener(v0, new EventListenerWrapper(v1, arg20));
        }
    }

    SingleSampleMediaSource(Uri arg1, com.google.android.exoplayer2.upstream.DataSource$Factory arg2, Format arg3, long arg4, int arg6, boolean arg7, Object arg8, com.google.android.exoplayer2.source.SingleSampleMediaSource$1 arg9) {
        this(arg1, arg2, arg3, arg4, arg6, arg7, arg8);
    }

    public MediaPeriod createPeriod(MediaPeriodId arg11, Allocator arg12) {
        boolean v12 = arg11.periodIndex == 0 ? true : false;
        Assertions.checkArgument(v12);
        return new SingleSampleMediaPeriod(this.dataSpec, this.dataSourceFactory, this.transferListener, this.format, this.durationUs, this.minLoadableRetryCount, this.createEventDispatcher(arg11), this.treatLoadErrorsAsEndOfStream);
    }

    public void maybeThrowSourceInfoRefreshError() {
    }

    public void prepareSourceInternal(ExoPlayer arg1, boolean arg2, TransferListener arg3) {
        this.transferListener = arg3;
        this.refreshSourceInfo(this.timeline, null);
    }

    public void releasePeriod(MediaPeriod arg1) {
        ((SingleSampleMediaPeriod)arg1).release();
    }

    public void releaseSourceInternal() {
    }
}

