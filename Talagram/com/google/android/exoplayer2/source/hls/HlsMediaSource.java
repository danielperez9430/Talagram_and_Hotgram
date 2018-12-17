package com.google.android.exoplayer2.source.hls;

import android.net.Uri;
import android.os.Handler;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerLibraryInfo;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.source.BaseMediaSource;
import com.google.android.exoplayer2.source.CompositeSequenceableLoaderFactory;
import com.google.android.exoplayer2.source.DefaultCompositeSequenceableLoaderFactory;
import com.google.android.exoplayer2.source.MediaPeriod;
import com.google.android.exoplayer2.source.MediaSource$MediaPeriodId;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.MediaSourceEventListener;
import com.google.android.exoplayer2.source.SinglePeriodTimeline;
import com.google.android.exoplayer2.source.ads.AdsMediaSource$MediaSourceFactory;
import com.google.android.exoplayer2.source.hls.playlist.DefaultHlsPlaylistTracker;
import com.google.android.exoplayer2.source.hls.playlist.HlsMediaPlaylist;
import com.google.android.exoplayer2.source.hls.playlist.HlsPlaylistParser;
import com.google.android.exoplayer2.source.hls.playlist.HlsPlaylistTracker$PrimaryPlaylistListener;
import com.google.android.exoplayer2.source.hls.playlist.HlsPlaylistTracker;
import com.google.android.exoplayer2.upstream.Allocator;
import com.google.android.exoplayer2.upstream.LoadErrorHandlingPolicy$-CC;
import com.google.android.exoplayer2.upstream.LoadErrorHandlingPolicy;
import com.google.android.exoplayer2.upstream.ParsingLoadable$Parser;
import com.google.android.exoplayer2.upstream.TransferListener;
import com.google.android.exoplayer2.util.Assertions;
import java.util.List;

public final class HlsMediaSource extends BaseMediaSource implements PrimaryPlaylistListener {
    class com.google.android.exoplayer2.source.hls.HlsMediaSource$1 {
    }

    public final class Factory implements MediaSourceFactory {
        private boolean allowChunklessPreparation;
        private LoadErrorHandlingPolicy chunkLoadErrorHandlingPolicy;
        private CompositeSequenceableLoaderFactory compositeSequenceableLoaderFactory;
        private HlsExtractorFactory extractorFactory;
        private final HlsDataSourceFactory hlsDataSourceFactory;
        private boolean isCreateCalled;
        private int minLoadableRetryCount;
        private Parser playlistParser;
        private HlsPlaylistTracker playlistTracker;
        private Object tag;

        public Factory(HlsDataSourceFactory arg1) {
            super();
            this.hlsDataSourceFactory = Assertions.checkNotNull(arg1);
            this.extractorFactory = HlsExtractorFactory.DEFAULT;
            this.chunkLoadErrorHandlingPolicy = LoadErrorHandlingPolicy$-CC.getDefault();
            this.minLoadableRetryCount = 3;
            this.compositeSequenceableLoaderFactory = new DefaultCompositeSequenceableLoaderFactory();
        }

        public Factory(com.google.android.exoplayer2.upstream.DataSource$Factory arg2) {
            this(new DefaultHlsDataSourceFactory(arg2));
        }

        public MediaSource createMediaSource(Uri arg1) {
            return this.createMediaSource(arg1);
        }

        public HlsMediaSource createMediaSource(Uri arg18) {
            HlsPlaylistParser v5_1;
            Factory v0 = this;
            v0.isCreateCalled = true;
            if(v0.playlistTracker == null) {
                HlsDataSourceFactory v2 = v0.hlsDataSourceFactory;
                LoadErrorHandlingPolicy v3 = LoadErrorHandlingPolicy$-CC.getDefault();
                int v4 = v0.minLoadableRetryCount;
                if(v0.playlistParser != null) {
                    Parser v5 = v0.playlistParser;
                }
                else {
                    v5_1 = new HlsPlaylistParser();
                }

                v0.playlistTracker = new DefaultHlsPlaylistTracker(v2, v3, v4, ((Parser)v5_1));
            }

            return new HlsMediaSource(arg18, v0.hlsDataSourceFactory, v0.extractorFactory, v0.compositeSequenceableLoaderFactory, v0.chunkLoadErrorHandlingPolicy, v0.minLoadableRetryCount, v0.playlistTracker, v0.allowChunklessPreparation, v0.tag, null);
        }

        @Deprecated public HlsMediaSource createMediaSource(Uri arg1, Handler arg2, MediaSourceEventListener arg3) {
            HlsMediaSource v1 = this.createMediaSource(arg1);
            if(arg2 != null && arg3 != null) {
                v1.addEventListener(arg2, arg3);
            }

            return v1;
        }

        public int[] getSupportedTypes() {
            return new int[]{2};
        }

        public Factory setAllowChunklessPreparation(boolean arg2) {
            Assertions.checkState(this.isCreateCalled ^ 1);
            this.allowChunklessPreparation = arg2;
            return this;
        }

        public Factory setChunkLoadErrorHandlingPolicy(LoadErrorHandlingPolicy arg2) {
            Assertions.checkState(this.isCreateCalled ^ 1);
            this.chunkLoadErrorHandlingPolicy = arg2;
            return this;
        }

        public Factory setCompositeSequenceableLoaderFactory(CompositeSequenceableLoaderFactory arg2) {
            Assertions.checkState(this.isCreateCalled ^ 1);
            this.compositeSequenceableLoaderFactory = Assertions.checkNotNull(arg2);
            return this;
        }

        public Factory setExtractorFactory(HlsExtractorFactory arg2) {
            Assertions.checkState(this.isCreateCalled ^ 1);
            this.extractorFactory = Assertions.checkNotNull(arg2);
            return this;
        }

        public Factory setMinLoadableRetryCount(int arg2) {
            Assertions.checkState(this.isCreateCalled ^ 1);
            this.minLoadableRetryCount = arg2;
            return this;
        }

        public Factory setPlaylistParser(Parser arg3) {
            boolean v1 = true;
            Assertions.checkState(this.isCreateCalled ^ 1);
            if(this.playlistTracker == null) {
            }
            else {
                v1 = false;
            }

            Assertions.checkState(v1, "A playlist tracker has already been set.");
            this.playlistParser = Assertions.checkNotNull(arg3);
            return this;
        }

        public Factory setPlaylistTracker(HlsPlaylistTracker arg3) {
            boolean v1 = true;
            Assertions.checkState(this.isCreateCalled ^ 1);
            if(this.playlistParser == null) {
            }
            else {
                v1 = false;
            }

            Assertions.checkState(v1, "A playlist parser has already been set.");
            this.playlistTracker = Assertions.checkNotNull(arg3);
            return this;
        }

        public Factory setTag(Object arg2) {
            Assertions.checkState(this.isCreateCalled ^ 1);
            this.tag = arg2;
            return this;
        }
    }

    public static final int DEFAULT_MIN_LOADABLE_RETRY_COUNT = 3;
    private final boolean allowChunklessPreparation;
    private final LoadErrorHandlingPolicy chunkLoadErrorHandlingPolicy;
    private final CompositeSequenceableLoaderFactory compositeSequenceableLoaderFactory;
    private final HlsDataSourceFactory dataSourceFactory;
    private final HlsExtractorFactory extractorFactory;
    private final Uri manifestUri;
    private TransferListener mediaTransferListener;
    private final int minLoadableRetryCount;
    private final HlsPlaylistTracker playlistTracker;
    private final Object tag;

    static {
        ExoPlayerLibraryInfo.registerModule("goog.exo.hls");
    }

    @Deprecated public HlsMediaSource(Uri arg13, HlsDataSourceFactory arg14, HlsExtractorFactory arg15, int arg16, Handler arg17, MediaSourceEventListener arg18, Parser arg19) {
        Handler v0 = arg17;
        MediaSourceEventListener v1 = arg18;
        this(arg13, arg14, arg15, new DefaultCompositeSequenceableLoaderFactory(), LoadErrorHandlingPolicy$-CC.getDefault(), arg16, new DefaultHlsPlaylistTracker(arg14, LoadErrorHandlingPolicy$-CC.getDefault(), arg16, arg19), false, null);
        if(v0 != null && v1 != null) {
            this.addEventListener(v0, v1);
        }
    }

    private HlsMediaSource(Uri arg1, HlsDataSourceFactory arg2, HlsExtractorFactory arg3, CompositeSequenceableLoaderFactory arg4, LoadErrorHandlingPolicy arg5, int arg6, HlsPlaylistTracker arg7, boolean arg8, Object arg9) {
        super();
        this.manifestUri = arg1;
        this.dataSourceFactory = arg2;
        this.extractorFactory = arg3;
        this.compositeSequenceableLoaderFactory = arg4;
        this.chunkLoadErrorHandlingPolicy = arg5;
        this.minLoadableRetryCount = arg6;
        this.playlistTracker = arg7;
        this.allowChunklessPreparation = arg8;
        this.tag = arg9;
    }

    HlsMediaSource(Uri arg1, HlsDataSourceFactory arg2, HlsExtractorFactory arg3, CompositeSequenceableLoaderFactory arg4, LoadErrorHandlingPolicy arg5, int arg6, HlsPlaylistTracker arg7, boolean arg8, Object arg9, com.google.android.exoplayer2.source.hls.HlsMediaSource$1 arg10) {
        this(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9);
    }

    @Deprecated public HlsMediaSource(Uri arg9, com.google.android.exoplayer2.upstream.DataSource$Factory arg10, int arg11, Handler arg12, MediaSourceEventListener arg13) {
        this(arg9, new DefaultHlsDataSourceFactory(arg10), HlsExtractorFactory.DEFAULT, arg11, arg12, arg13, new HlsPlaylistParser());
    }

    @Deprecated public HlsMediaSource(Uri arg7, com.google.android.exoplayer2.upstream.DataSource$Factory arg8, Handler arg9, MediaSourceEventListener arg10) {
        this(arg7, arg8, 3, arg9, arg10);
    }

    public MediaPeriod createPeriod(MediaPeriodId arg13, Allocator arg14) {
        boolean v0 = arg13.periodIndex == 0 ? true : false;
        Assertions.checkArgument(v0);
        return new HlsMediaPeriod(this.extractorFactory, this.playlistTracker, this.dataSourceFactory, this.mediaTransferListener, this.chunkLoadErrorHandlingPolicy, this.minLoadableRetryCount, this.createEventDispatcher(arg13), arg14, this.compositeSequenceableLoaderFactory, this.allowChunklessPreparation);
    }

    public void maybeThrowSourceInfoRefreshError() {
        this.playlistTracker.maybeThrowPrimaryPlaylistRefreshError();
    }

    public void onPrimaryPlaylistRefreshed(HlsMediaPlaylist arg24) {
        SinglePeriodTimeline v2_2;
        long v18;
        HlsMediaSource v0 = this;
        HlsMediaPlaylist v1 = arg24;
        long v3 = -9223372036854775807L;
        long v10 = v1.hasProgramDateTime ? C.usToMs(v1.startTimeUs) : v3;
        long v8 = v1.playlistType == 2 || v1.playlistType == 1 ? v10 : v3;
        long v12 = v1.startOffsetUs;
        if(v0.playlistTracker.isLive()) {
            long v16 = v1.startTimeUs - v0.playlistTracker.getInitialStartTimeUs();
            long v14 = v1.hasEndTag ? v16 + v1.durationUs : v3;
            List v2 = v1.segments;
            if(v12 == v3) {
                long v2_1 = v2.isEmpty() ? 0 : v2.get(Math.max(0, v2.size() - 3)).relativeStartTimeUs;
                v18 = v2_1;
            }
            else {
                v18 = v12;
            }

            v2_2 = new SinglePeriodTimeline(v8, v10, v14, v1.durationUs, v16, v18, true, v1.hasEndTag ^ 1, v0.tag);
        }
        else {
            v18 = v12 == v3 ? 0 : v12;
            v2_2 = new SinglePeriodTimeline(v8, v10, v1.durationUs, v1.durationUs, 0, v18, true, false, v0.tag);
        }

        v0.refreshSourceInfo(((Timeline)v2_2), new HlsManifest(v0.playlistTracker.getMasterPlaylist(), v1));
    }

    public void prepareSourceInternal(ExoPlayer arg1, boolean arg2, TransferListener arg3) {
        this.mediaTransferListener = arg3;
        this.playlistTracker.start(this.manifestUri, this.createEventDispatcher(null), ((PrimaryPlaylistListener)this));
    }

    public void releasePeriod(MediaPeriod arg1) {
        ((HlsMediaPeriod)arg1).release();
    }

    public void releaseSourceInternal() {
        if(this.playlistTracker != null) {
            this.playlistTracker.stop();
        }
    }
}

