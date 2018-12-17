package com.google.android.exoplayer2.source.smoothstreaming;

import android.net.Uri;
import android.os.Handler;
import android.os.SystemClock;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerLibraryInfo;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.source.BaseMediaSource;
import com.google.android.exoplayer2.source.CompositeSequenceableLoaderFactory;
import com.google.android.exoplayer2.source.DefaultCompositeSequenceableLoaderFactory;
import com.google.android.exoplayer2.source.MediaPeriod;
import com.google.android.exoplayer2.source.MediaSource$MediaPeriodId;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.MediaSourceEventListener$EventDispatcher;
import com.google.android.exoplayer2.source.MediaSourceEventListener;
import com.google.android.exoplayer2.source.SinglePeriodTimeline;
import com.google.android.exoplayer2.source.ads.AdsMediaSource$MediaSourceFactory;
import com.google.android.exoplayer2.source.smoothstreaming.manifest.SsManifest$StreamElement;
import com.google.android.exoplayer2.source.smoothstreaming.manifest.SsManifest;
import com.google.android.exoplayer2.source.smoothstreaming.manifest.SsManifestParser;
import com.google.android.exoplayer2.source.smoothstreaming.manifest.SsUtil;
import com.google.android.exoplayer2.upstream.Allocator;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.Loader$Callback;
import com.google.android.exoplayer2.upstream.Loader$LoadErrorAction;
import com.google.android.exoplayer2.upstream.Loader$Loadable;
import com.google.android.exoplayer2.upstream.Loader;
import com.google.android.exoplayer2.upstream.LoaderErrorThrower$Dummy;
import com.google.android.exoplayer2.upstream.LoaderErrorThrower;
import com.google.android.exoplayer2.upstream.ParsingLoadable$Parser;
import com.google.android.exoplayer2.upstream.ParsingLoadable;
import com.google.android.exoplayer2.upstream.TransferListener;
import com.google.android.exoplayer2.util.Assertions;
import java.io.IOException;
import java.util.ArrayList;

public final class SsMediaSource extends BaseMediaSource implements Callback {
    public final class Factory implements MediaSourceFactory {
        private final com.google.android.exoplayer2.source.smoothstreaming.SsChunkSource$Factory chunkSourceFactory;
        private CompositeSequenceableLoaderFactory compositeSequenceableLoaderFactory;
        private boolean isCreateCalled;
        private long livePresentationDelayMs;
        private final com.google.android.exoplayer2.upstream.DataSource$Factory manifestDataSourceFactory;
        private Parser manifestParser;
        private int minLoadableRetryCount;
        private Object tag;

        public Factory(com.google.android.exoplayer2.source.smoothstreaming.SsChunkSource$Factory arg1, com.google.android.exoplayer2.upstream.DataSource$Factory arg2) {
            super();
            this.chunkSourceFactory = Assertions.checkNotNull(arg1);
            this.manifestDataSourceFactory = arg2;
            this.minLoadableRetryCount = 3;
            this.livePresentationDelayMs = 30000;
            this.compositeSequenceableLoaderFactory = new DefaultCompositeSequenceableLoaderFactory();
        }

        public Factory(com.google.android.exoplayer2.upstream.DataSource$Factory arg2) {
            this(new com.google.android.exoplayer2.source.smoothstreaming.DefaultSsChunkSource$Factory(arg2), arg2);
        }

        public MediaSource createMediaSource(Uri arg1) {
            return this.createMediaSource(arg1);
        }

        public SsMediaSource createMediaSource(Uri arg14) {
            this.isCreateCalled = true;
            if(this.manifestParser == null) {
                this.manifestParser = new SsManifestParser();
            }

            return new SsMediaSource(null, Assertions.checkNotNull(arg14), this.manifestDataSourceFactory, this.manifestParser, this.chunkSourceFactory, this.compositeSequenceableLoaderFactory, this.minLoadableRetryCount, this.livePresentationDelayMs, this.tag, null);
        }

        @Deprecated public SsMediaSource createMediaSource(Uri arg1, Handler arg2, MediaSourceEventListener arg3) {
            SsMediaSource v1 = this.createMediaSource(arg1);
            if(arg2 != null && arg3 != null) {
                v1.addEventListener(arg2, arg3);
            }

            return v1;
        }

        public SsMediaSource createMediaSource(SsManifest arg15) {
            Assertions.checkArgument(arg15.isLive ^ 1);
            this.isCreateCalled = true;
            return new SsMediaSource(arg15, null, null, null, this.chunkSourceFactory, this.compositeSequenceableLoaderFactory, this.minLoadableRetryCount, this.livePresentationDelayMs, this.tag, null);
        }

        @Deprecated public SsMediaSource createMediaSource(SsManifest arg1, Handler arg2, MediaSourceEventListener arg3) {
            SsMediaSource v1 = this.createMediaSource(arg1);
            if(arg2 != null && arg3 != null) {
                v1.addEventListener(arg2, arg3);
            }

            return v1;
        }

        public int[] getSupportedTypes() {
            return new int[]{1};
        }

        public Factory setCompositeSequenceableLoaderFactory(CompositeSequenceableLoaderFactory arg2) {
            Assertions.checkState(this.isCreateCalled ^ 1);
            this.compositeSequenceableLoaderFactory = Assertions.checkNotNull(arg2);
            return this;
        }

        public Factory setLivePresentationDelayMs(long arg2) {
            Assertions.checkState(this.isCreateCalled ^ 1);
            this.livePresentationDelayMs = arg2;
            return this;
        }

        public Factory setManifestParser(Parser arg2) {
            Assertions.checkState(this.isCreateCalled ^ 1);
            this.manifestParser = Assertions.checkNotNull(arg2);
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

    public static final long DEFAULT_LIVE_PRESENTATION_DELAY_MS = 30000;
    public static final int DEFAULT_MIN_LOADABLE_RETRY_COUNT = 3;
    private static final int MINIMUM_MANIFEST_REFRESH_PERIOD_MS = 5000;
    private static final long MIN_LIVE_DEFAULT_START_POSITION_US = 5000000;
    private final com.google.android.exoplayer2.source.smoothstreaming.SsChunkSource$Factory chunkSourceFactory;
    private final CompositeSequenceableLoaderFactory compositeSequenceableLoaderFactory;
    private final long livePresentationDelayMs;
    private SsManifest manifest;
    private DataSource manifestDataSource;
    private final com.google.android.exoplayer2.upstream.DataSource$Factory manifestDataSourceFactory;
    private final EventDispatcher manifestEventDispatcher;
    private long manifestLoadStartTimestamp;
    private Loader manifestLoader;
    private LoaderErrorThrower manifestLoaderErrorThrower;
    private final Parser manifestParser;
    private Handler manifestRefreshHandler;
    private final Uri manifestUri;
    private final ArrayList mediaPeriods;
    private TransferListener mediaTransferListener;
    private final int minLoadableRetryCount;
    private final boolean sideloadedManifest;
    private final Object tag;

    static {
        ExoPlayerLibraryInfo.registerModule("goog.exo.smoothstreaming");
    }

    @Deprecated public SsMediaSource(Uri arg11, com.google.android.exoplayer2.upstream.DataSource$Factory arg12, com.google.android.exoplayer2.source.smoothstreaming.SsChunkSource$Factory arg13, int arg14, long arg15, Handler arg17, MediaSourceEventListener arg18) {
        this(arg11, arg12, new SsManifestParser(), arg13, arg14, arg15, arg17, arg18);
    }

    @Deprecated public SsMediaSource(Uri arg14, com.google.android.exoplayer2.upstream.DataSource$Factory arg15, Parser arg16, com.google.android.exoplayer2.source.smoothstreaming.SsChunkSource$Factory arg17, int arg18, long arg19, Handler arg21, MediaSourceEventListener arg22) {
        Handler v0 = arg21;
        MediaSourceEventListener v1 = arg22;
        this(null, arg14, arg15, arg16, arg17, new DefaultCompositeSequenceableLoaderFactory(), arg18, arg19, null);
        if(v0 != null && v1 != null) {
            this.addEventListener(v0, v1);
        }
    }

    @Deprecated public SsMediaSource(Uri arg10, com.google.android.exoplayer2.upstream.DataSource$Factory arg11, com.google.android.exoplayer2.source.smoothstreaming.SsChunkSource$Factory arg12, Handler arg13, MediaSourceEventListener arg14) {
        this(arg10, arg11, arg12, 3, 30000, arg13, arg14);
    }

    private SsMediaSource(SsManifest arg4, Uri arg5, com.google.android.exoplayer2.upstream.DataSource$Factory arg6, Parser arg7, com.google.android.exoplayer2.source.smoothstreaming.SsChunkSource$Factory arg8, CompositeSequenceableLoaderFactory arg9, int arg10, long arg11, Object arg13) {
        super();
        boolean v0 = false;
        boolean v2 = arg4 == null || !arg4.isLive ? true : false;
        Assertions.checkState(v2);
        this.manifest = arg4;
        MediaPeriodId v2_1 = null;
        arg5 = arg5 == null ? ((Uri)v2_1) : SsUtil.fixManifestUri(arg5);
        this.manifestUri = arg5;
        this.manifestDataSourceFactory = arg6;
        this.manifestParser = arg7;
        this.chunkSourceFactory = arg8;
        this.compositeSequenceableLoaderFactory = arg9;
        this.minLoadableRetryCount = arg10;
        this.livePresentationDelayMs = arg11;
        this.manifestEventDispatcher = this.createEventDispatcher(v2_1);
        this.tag = arg13;
        if(arg4 != null) {
            v0 = true;
        }

        this.sideloadedManifest = v0;
        this.mediaPeriods = new ArrayList();
    }

    SsMediaSource(SsManifest arg1, Uri arg2, com.google.android.exoplayer2.upstream.DataSource$Factory arg3, Parser arg4, com.google.android.exoplayer2.source.smoothstreaming.SsChunkSource$Factory arg5, CompositeSequenceableLoaderFactory arg6, int arg7, long arg8, Object arg10, com.google.android.exoplayer2.source.smoothstreaming.SsMediaSource$1 arg11) {
        this(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg10);
    }

    @Deprecated public SsMediaSource(SsManifest arg14, com.google.android.exoplayer2.source.smoothstreaming.SsChunkSource$Factory arg15, int arg16, Handler arg17, MediaSourceEventListener arg18) {
        Handler v0 = arg17;
        MediaSourceEventListener v1 = arg18;
        this(arg14, null, null, null, arg15, new DefaultCompositeSequenceableLoaderFactory(), arg16, 30000, null);
        if(v0 != null && v1 != null) {
            this.addEventListener(v0, v1);
        }
    }

    @Deprecated public SsMediaSource(SsManifest arg7, com.google.android.exoplayer2.source.smoothstreaming.SsChunkSource$Factory arg8, Handler arg9, MediaSourceEventListener arg10) {
        this(arg7, arg8, 3, arg9, arg10);
    }

    static void access$100(SsMediaSource arg0) {
        arg0.startLoadingManifest();
    }

    public MediaPeriod createPeriod(MediaPeriodId arg11, Allocator arg12) {
        boolean v0 = arg11.periodIndex == 0 ? true : false;
        Assertions.checkArgument(v0);
        SsMediaPeriod v11 = new SsMediaPeriod(this.manifest, this.chunkSourceFactory, this.mediaTransferListener, this.compositeSequenceableLoaderFactory, this.minLoadableRetryCount, this.createEventDispatcher(arg11), this.manifestLoaderErrorThrower, arg12);
        this.mediaPeriods.add(v11);
        return ((MediaPeriod)v11);
    }

    public void maybeThrowSourceInfoRefreshError() {
        this.manifestLoaderErrorThrower.maybeThrowError();
    }

    public void onLoadCanceled(Loadable arg1, long arg2, long arg4, boolean arg6) {
        this.onLoadCanceled(((ParsingLoadable)arg1), arg2, arg4, arg6);
    }

    public void onLoadCanceled(ParsingLoadable arg13, long arg14, long arg16, boolean arg18) {
        this.manifestEventDispatcher.loadCanceled(arg13.dataSpec, arg13.getUri(), arg13.type, arg14, arg16, arg13.bytesLoaded());
    }

    public void onLoadCompleted(Loadable arg1, long arg2, long arg4) {
        this.onLoadCompleted(((ParsingLoadable)arg1), arg2, arg4);
    }

    public void onLoadCompleted(ParsingLoadable arg11, long arg12, long arg14) {
        this.manifestEventDispatcher.loadCompleted(arg11.dataSpec, arg11.getUri(), arg11.type, arg12, arg14, arg11.bytesLoaded());
        this.manifest = arg11.getResult();
        this.manifestLoadStartTimestamp = arg12 - arg14;
        this.processManifest();
        this.scheduleManifestRefresh();
    }

    public LoadErrorAction onLoadError(Loadable arg1, long arg2, long arg4, IOException arg6, int arg7) {
        return this.onLoadError(((ParsingLoadable)arg1), arg2, arg4, arg6, arg7);
    }

    public LoadErrorAction onLoadError(ParsingLoadable arg15, long arg16, long arg18, IOException arg20, int arg21) {
        boolean v12 = arg20 instanceof ParserException;
        this.manifestEventDispatcher.loadError(arg15.dataSpec, arg15.getUri(), arg15.type, arg16, arg18, arg15.bytesLoaded(), arg20, v12);
        LoadErrorAction v0 = v12 ? Loader.DONT_RETRY_FATAL : Loader.RETRY;
        return v0;
    }

    public void prepareSourceInternal(ExoPlayer arg1, boolean arg2, TransferListener arg3) {
        this.mediaTransferListener = arg3;
        if(this.sideloadedManifest) {
            this.manifestLoaderErrorThrower = new Dummy();
            this.processManifest();
        }
        else {
            this.manifestDataSource = this.manifestDataSourceFactory.createDataSource();
            this.manifestLoader = new Loader("Loader:Manifest");
            this.manifestLoaderErrorThrower = this.manifestLoader;
            this.manifestRefreshHandler = new Handler();
            this.startLoadingManifest();
        }
    }

    private void processManifest() {
        SinglePeriodTimeline v1;
        SsMediaSource v0 = this;
        int v2;
        for(v2 = 0; v2 < v0.mediaPeriods.size(); ++v2) {
            v0.mediaPeriods.get(v2).updateManifest(v0.manifest);
        }

        StreamElement[] v4 = v0.manifest.streamElements;
        int v5 = v4.length;
        long v6 = 9223372036854775807L;
        long v8 = -9223372036854775808L;
        long v13 = v6;
        for(v2 = 0; v2 < v5; ++v2) {
            StreamElement v3 = v4[v2];
            if(v3.chunkCount > 0) {
                long v10 = Math.min(v13, v3.getStartTimeUs(0));
                v8 = Math.max(v8, v3.getStartTimeUs(v3.chunkCount - 1) + v3.getChunkDurationUs(v3.chunkCount - 1));
                v13 = v10;
            }
        }

        long v2_1 = 0;
        long v4_1 = -9223372036854775807L;
        if(Long.compare(v13, v6) == 0) {
            long v7 = v0.manifest.isLive ? v4_1 : v2_1;
            v1 = new SinglePeriodTimeline(v7, 0, 0, 0, true, v0.manifest.isLive, v0.tag);
        }
        else {
            if(v0.manifest.isLive) {
                if(v0.manifest.dvrWindowLengthUs != v4_1 && v0.manifest.dvrWindowLengthUs > v2_1) {
                    v13 = Math.max(v13, v8 - v0.manifest.dvrWindowLengthUs);
                }

                long v20 = v13;
                long v18 = v8 - v20;
                long v1_1 = v18 - C.msToUs(v0.livePresentationDelayMs);
                long v3_1 = 5000000;
                if(v1_1 < v3_1) {
                    v1_1 = Math.min(v3_1, v18 / 2);
                }

                v1 = new SinglePeriodTimeline(-9223372036854775807L, v18, v20, v1_1, true, true, v0.tag);
                goto label_116;
            }

            long v11 = v0.manifest.durationUs != v4_1 ? v0.manifest.durationUs : v8 - v13;
            v1 = new SinglePeriodTimeline(v13 + v11, v11, v13, 0, true, false, v0.tag);
        }

    label_116:
        v0.refreshSourceInfo(((Timeline)v1), v0.manifest);
    }

    public void releasePeriod(MediaPeriod arg2) {
        arg2.release();
        this.mediaPeriods.remove(arg2);
    }

    public void releaseSourceInternal() {
        DataSource v1 = null;
        SsManifest v0 = this.sideloadedManifest ? this.manifest : ((SsManifest)v1);
        this.manifest = v0;
        this.manifestDataSource = v1;
        this.manifestLoadStartTimestamp = 0;
        if(this.manifestLoader != null) {
            this.manifestLoader.release();
            this.manifestLoader = ((Loader)v1);
        }

        if(this.manifestRefreshHandler != null) {
            this.manifestRefreshHandler.removeCallbacksAndMessages(v1);
            this.manifestRefreshHandler = ((Handler)v1);
        }
    }

    private void scheduleManifestRefresh() {
        if(!this.manifest.isLive) {
            return;
        }

        this.manifestRefreshHandler.postDelayed(new Runnable() {
            public void run() {
                SsMediaSource.this.startLoadingManifest();
            }
        }, Math.max(0, this.manifestLoadStartTimestamp + 5000 - SystemClock.elapsedRealtime()));
    }

    private void startLoadingManifest() {
        ParsingLoadable v0 = new ParsingLoadable(this.manifestDataSource, this.manifestUri, 4, this.manifestParser);
        this.manifestEventDispatcher.loadStarted(v0.dataSpec, v0.dataSpec.uri, v0.type, this.manifestLoader.startLoading(((Loadable)v0), ((Callback)this), this.minLoadableRetryCount));
    }
}

