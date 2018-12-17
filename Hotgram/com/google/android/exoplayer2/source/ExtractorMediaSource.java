package com.google.android.exoplayer2.source;

import android.net.Uri;
import android.os.Handler;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ads.AdsMediaSource$MediaSourceFactory;
import com.google.android.exoplayer2.upstream.Allocator;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.TransferListener;
import com.google.android.exoplayer2.util.Assertions;
import java.io.IOException;

public final class ExtractorMediaSource extends BaseMediaSource implements Listener {
    class com.google.android.exoplayer2.source.ExtractorMediaSource$1 {
    }

    @Deprecated public interface EventListener {
        void onLoadError(IOException arg1);
    }

    final class EventListenerWrapper extends DefaultMediaSourceEventListener {
        private final EventListener eventListener;

        public EventListenerWrapper(EventListener arg1) {
            super();
            this.eventListener = Assertions.checkNotNull(arg1);
        }

        public void onLoadError(int arg1, MediaPeriodId arg2, LoadEventInfo arg3, MediaLoadData arg4, IOException arg5, boolean arg6) {
            this.eventListener.onLoadError(arg5);
        }
    }

    public final class Factory implements MediaSourceFactory {
        private int continueLoadingCheckIntervalBytes;
        private String customCacheKey;
        private final com.google.android.exoplayer2.upstream.DataSource$Factory dataSourceFactory;
        private ExtractorsFactory extractorsFactory;
        private boolean isCreateCalled;
        private int minLoadableRetryCount;
        private Object tag;

        public Factory(com.google.android.exoplayer2.upstream.DataSource$Factory arg1) {
            super();
            this.dataSourceFactory = arg1;
            this.minLoadableRetryCount = -1;
            this.continueLoadingCheckIntervalBytes = 1048576;
        }

        public ExtractorMediaSource createMediaSource(Uri arg11) {
            this.isCreateCalled = true;
            if(this.extractorsFactory == null) {
                this.extractorsFactory = new DefaultExtractorsFactory();
            }

            return new ExtractorMediaSource(arg11, this.dataSourceFactory, this.extractorsFactory, this.minLoadableRetryCount, this.customCacheKey, this.continueLoadingCheckIntervalBytes, this.tag, null);
        }

        @Deprecated public ExtractorMediaSource createMediaSource(Uri arg1, Handler arg2, MediaSourceEventListener arg3) {
            ExtractorMediaSource v1 = this.createMediaSource(arg1);
            if(arg2 != null && arg3 != null) {
                v1.addEventListener(arg2, arg3);
            }

            return v1;
        }

        public MediaSource createMediaSource(Uri arg1) {
            return this.createMediaSource(arg1);
        }

        public int[] getSupportedTypes() {
            return new int[]{3};
        }

        public Factory setContinueLoadingCheckIntervalBytes(int arg2) {
            Assertions.checkState(this.isCreateCalled ^ 1);
            this.continueLoadingCheckIntervalBytes = arg2;
            return this;
        }

        public Factory setCustomCacheKey(String arg2) {
            Assertions.checkState(this.isCreateCalled ^ 1);
            this.customCacheKey = arg2;
            return this;
        }

        public Factory setExtractorsFactory(ExtractorsFactory arg2) {
            Assertions.checkState(this.isCreateCalled ^ 1);
            this.extractorsFactory = arg2;
            return this;
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
    }

    public static final int DEFAULT_LOADING_CHECK_INTERVAL_BYTES = 1048576;
    public static final int DEFAULT_MIN_LOADABLE_RETRY_COUNT_LIVE = 6;
    public static final int DEFAULT_MIN_LOADABLE_RETRY_COUNT_ON_DEMAND = 3;
    public static final int MIN_RETRY_COUNT_DEFAULT_FOR_MEDIA = -1;
    private final int continueLoadingCheckIntervalBytes;
    private final String customCacheKey;
    private final com.google.android.exoplayer2.upstream.DataSource$Factory dataSourceFactory;
    private final ExtractorsFactory extractorsFactory;
    private final int minLoadableRetryCount;
    private final Object tag;
    private long timelineDurationUs;
    private boolean timelineIsSeekable;
    private TransferListener transferListener;
    private final Uri uri;

    @Deprecated public ExtractorMediaSource(Uri arg11, com.google.android.exoplayer2.upstream.DataSource$Factory arg12, ExtractorsFactory arg13, int arg14, Handler arg15, EventListener arg16, String arg17, int arg18) {
        Handler v0 = arg15;
        EventListener v1 = arg16;
        this(arg11, arg12, arg13, arg14, arg17, arg18, null);
        if(v1 != null && v0 != null) {
            this.addEventListener(arg15, new EventListenerWrapper(v1));
        }
    }

    private ExtractorMediaSource(Uri arg1, com.google.android.exoplayer2.upstream.DataSource$Factory arg2, ExtractorsFactory arg3, int arg4, String arg5, int arg6, Object arg7) {
        super();
        this.uri = arg1;
        this.dataSourceFactory = arg2;
        this.extractorsFactory = arg3;
        this.minLoadableRetryCount = arg4;
        this.customCacheKey = arg5;
        this.continueLoadingCheckIntervalBytes = arg6;
        this.timelineDurationUs = -9223372036854775807L;
        this.tag = arg7;
    }

    ExtractorMediaSource(Uri arg1, com.google.android.exoplayer2.upstream.DataSource$Factory arg2, ExtractorsFactory arg3, int arg4, String arg5, int arg6, Object arg7, com.google.android.exoplayer2.source.ExtractorMediaSource$1 arg8) {
        this(arg1, arg2, arg3, arg4, arg5, arg6, arg7);
    }

    @Deprecated public ExtractorMediaSource(Uri arg8, com.google.android.exoplayer2.upstream.DataSource$Factory arg9, ExtractorsFactory arg10, Handler arg11, EventListener arg12) {
        this(arg8, arg9, arg10, arg11, arg12, null);
    }

    @Deprecated public ExtractorMediaSource(Uri arg10, com.google.android.exoplayer2.upstream.DataSource$Factory arg11, ExtractorsFactory arg12, Handler arg13, EventListener arg14, String arg15) {
        this(arg10, arg11, arg12, -1, arg13, arg14, arg15, 1048576);
    }

    public MediaPeriod createPeriod(MediaPeriodId arg12, Allocator arg13) {
        boolean v0 = arg12.periodIndex == 0 ? true : false;
        Assertions.checkArgument(v0);
        DataSource v3 = this.dataSourceFactory.createDataSource();
        if(this.transferListener != null) {
            v3.addTransferListener(this.transferListener);
        }

        return new ExtractorMediaPeriod(this.uri, v3, this.extractorsFactory.createExtractors(), this.minLoadableRetryCount, this.createEventDispatcher(arg12), this, arg13, this.customCacheKey, this.continueLoadingCheckIntervalBytes);
    }

    public void maybeThrowSourceInfoRefreshError() {
    }

    private void notifySourceInfoRefreshed(long arg7, boolean arg9) {
        this.timelineDurationUs = arg7;
        this.timelineIsSeekable = arg9;
        this.refreshSourceInfo(new SinglePeriodTimeline(this.timelineDurationUs, this.timelineIsSeekable, false, this.tag), null);
    }

    public void onSourceInfoRefreshed(long arg4, boolean arg6) {
        if(arg4 == -9223372036854775807L) {
            arg4 = this.timelineDurationUs;
        }

        if(this.timelineDurationUs == arg4 && this.timelineIsSeekable == arg6) {
            return;
        }

        this.notifySourceInfoRefreshed(arg4, arg6);
    }

    public void prepareSourceInternal(ExoPlayer arg1, boolean arg2, TransferListener arg3) {
        this.transferListener = arg3;
        this.notifySourceInfoRefreshed(this.timelineDurationUs, false);
    }

    public void releasePeriod(MediaPeriod arg1) {
        ((ExtractorMediaPeriod)arg1).release();
    }

    public void releaseSourceInternal() {
    }
}

