package com.google.android.exoplayer2.source.ads;

import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.view.ViewGroup;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.Timeline$Period;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.source.CompositeMediaSource;
import com.google.android.exoplayer2.source.DeferredMediaPeriod$PrepareErrorListener;
import com.google.android.exoplayer2.source.DeferredMediaPeriod;
import com.google.android.exoplayer2.source.MediaPeriod;
import com.google.android.exoplayer2.source.MediaSource$MediaPeriodId;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.MediaSourceEventListener$EventDispatcher;
import com.google.android.exoplayer2.upstream.Allocator;
import com.google.android.exoplayer2.upstream.DataSource$Factory;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.TransferListener;
import com.google.android.exoplayer2.util.Assertions;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class AdsMediaSource extends CompositeMediaSource {
    public final class AdLoadException extends IOException {
        @Retention(value=RetentionPolicy.SOURCE) @public interface Type {
        }

        public static final int TYPE_AD = 0;
        public static final int TYPE_AD_GROUP = 1;
        public static final int TYPE_ALL_ADS = 2;
        public static final int TYPE_UNEXPECTED = 3;
        public final int type;

        private AdLoadException(int arg1, Exception arg2) {
            super(((Throwable)arg2));
            this.type = arg1;
        }

        public static AdLoadException createForAd(Exception arg2) {
            return new AdLoadException(0, arg2);
        }

        public static AdLoadException createForAdGroup(Exception arg4, int arg5) {
            StringBuilder v2 = new StringBuilder();
            v2.append("Failed to load ad group ");
            v2.append(arg5);
            return new AdLoadException(1, new IOException(v2.toString(), ((Throwable)arg4)));
        }

        public static AdLoadException createForAllAds(Exception arg2) {
            return new AdLoadException(2, arg2);
        }

        public static AdLoadException createForUnexpected(RuntimeException arg2) {
            return new AdLoadException(3, ((Exception)arg2));
        }

        public RuntimeException getRuntimeExceptionForUnexpected() {
            boolean v0 = this.type == 3 ? true : false;
            Assertions.checkState(v0);
            return this.getCause();
        }
    }

    final class AdPrepareErrorListener implements PrepareErrorListener {
        private final int adGroupIndex;
        private final int adIndexInAdGroup;
        private final Uri adUri;

        public AdPrepareErrorListener(AdsMediaSource arg1, Uri arg2, int arg3, int arg4) {
            AdsMediaSource.this = arg1;
            super();
            this.adUri = arg2;
            this.adGroupIndex = arg3;
            this.adIndexInAdGroup = arg4;
        }

        static int access$800(AdPrepareErrorListener arg0) {
            return arg0.adGroupIndex;
        }

        static int access$900(AdPrepareErrorListener arg0) {
            return arg0.adIndexInAdGroup;
        }

        public void onPrepareError(MediaPeriodId arg14, IOException arg15) {
            AdsMediaSource.this.createEventDispatcher(arg14).loadError(new DataSpec(this.adUri), this.adUri, 6, -1, 0, 0, AdLoadException.createForAd(((Exception)arg15)), true);
            AdsMediaSource.this.mainHandler.post(new Runnable(arg15) {
                public void run() {
                    this.this$1.this$0.adsLoader.handlePrepareError(this.this$1.adGroupIndex, this.this$1.adIndexInAdGroup, this.val$exception);
                }
            });
        }
    }

    final class ComponentListener implements EventListener {
        private final Handler playerHandler;
        private volatile boolean released;

        public ComponentListener(AdsMediaSource arg1) {
            AdsMediaSource.this = arg1;
            super();
            this.playerHandler = new Handler();
        }

        static boolean access$200(ComponentListener arg0) {
            return arg0.released;
        }

        public void onAdClicked() {
            if(this.released) {
                return;
            }

            if(AdsMediaSource.this.eventHandler != null && AdsMediaSource.this.eventListener != null) {
                AdsMediaSource.this.eventHandler.post(new Runnable() {
                    public void run() {
                        if(!this.this$1.released) {
                            this.this$1.this$0.eventListener.onAdClicked();
                        }
                    }
                });
            }
        }

        public void onAdLoadError(AdLoadException arg16, DataSpec arg17) {
            ComponentListener v0 = this;
            if(v0.released) {
                return;
            }

            v0.this$0.createEventDispatcher(null).loadError(arg17, arg17.uri, 6, -1, 0, 0, arg16, true);
            if(v0.this$0.eventHandler != null && v0.this$0.eventListener != null) {
                v0.this$0.eventHandler.post(new Runnable(arg16) {
                    public void run() {
                        if(!this.this$1.released) {
                            if(this.val$error.type == 3) {
                                this.this$1.this$0.eventListener.onInternalAdLoadError(this.val$error.getRuntimeExceptionForUnexpected());
                            }
                            else {
                                this.this$1.this$0.eventListener.onAdLoadError(this.val$error);
                            }
                        }
                    }
                });
            }
        }

        public void onAdPlaybackState(AdPlaybackState arg3) {
            if(this.released) {
                return;
            }

            this.playerHandler.post(new Runnable(arg3) {
                public void run() {
                    if(this.this$1.released) {
                        return;
                    }

                    this.this$1.this$0.onAdPlaybackState(this.val$adPlaybackState);
                }
            });
        }

        public void onAdTapped() {
            if(this.released) {
                return;
            }

            if(AdsMediaSource.this.eventHandler != null && AdsMediaSource.this.eventListener != null) {
                AdsMediaSource.this.eventHandler.post(new Runnable() {
                    public void run() {
                        if(!this.this$1.released) {
                            this.this$1.this$0.eventListener.onAdTapped();
                        }
                    }
                });
            }
        }

        public void release() {
            this.released = true;
            this.playerHandler.removeCallbacksAndMessages(null);
        }
    }

    @Deprecated public interface com.google.android.exoplayer2.source.ads.AdsMediaSource$EventListener {
        void onAdClicked();

        void onAdLoadError(IOException arg1);

        void onAdTapped();

        void onInternalAdLoadError(RuntimeException arg1);
    }

    public interface MediaSourceFactory {
        MediaSource createMediaSource(Uri arg1);

        int[] getSupportedTypes();
    }

    private static final MediaPeriodId DUMMY_CONTENT_MEDIA_PERIOD_ID;
    private long[][] adDurationsUs;
    private MediaSource[][] adGroupMediaSources;
    private final MediaSourceFactory adMediaSourceFactory;
    private AdPlaybackState adPlaybackState;
    private final ViewGroup adUiViewGroup;
    private final AdsLoader adsLoader;
    private ComponentListener componentListener;
    private Object contentManifest;
    private final MediaSource contentMediaSource;
    private Timeline contentTimeline;
    private final Map deferredMediaPeriodByAdMediaSource;
    private final Handler eventHandler;
    private final com.google.android.exoplayer2.source.ads.AdsMediaSource$EventListener eventListener;
    private final Handler mainHandler;
    private final Period period;

    static {
        AdsMediaSource.DUMMY_CONTENT_MEDIA_PERIOD_ID = new MediaPeriodId(0);
    }

    public AdsMediaSource(MediaSource arg8, MediaSourceFactory arg9, AdsLoader arg10, ViewGroup arg11) {
        this(arg8, arg9, arg10, arg11, null, null);
    }

    @Deprecated public AdsMediaSource(MediaSource arg1, MediaSourceFactory arg2, AdsLoader arg3, ViewGroup arg4, Handler arg5, com.google.android.exoplayer2.source.ads.AdsMediaSource$EventListener arg6) {
        super();
        this.contentMediaSource = arg1;
        this.adMediaSourceFactory = arg2;
        this.adsLoader = arg3;
        this.adUiViewGroup = arg4;
        this.eventHandler = arg5;
        this.eventListener = arg6;
        this.mainHandler = new Handler(Looper.getMainLooper());
        this.deferredMediaPeriodByAdMediaSource = new HashMap();
        this.period = new Period();
        this.adGroupMediaSources = new MediaSource[0][];
        this.adDurationsUs = new long[0][];
        arg3.setSupportedContentTypes(arg2.getSupportedTypes());
    }

    public AdsMediaSource(MediaSource arg8, Factory arg9, AdsLoader arg10, ViewGroup arg11) {
        this(arg8, new com.google.android.exoplayer2.source.ExtractorMediaSource$Factory(arg9), arg10, arg11, null, null);
    }

    @Deprecated public AdsMediaSource(MediaSource arg8, Factory arg9, AdsLoader arg10, ViewGroup arg11, Handler arg12, com.google.android.exoplayer2.source.ads.AdsMediaSource$EventListener arg13) {
        this(arg8, new com.google.android.exoplayer2.source.ExtractorMediaSource$Factory(arg9), arg10, arg11, arg12, arg13);
    }

    static ViewGroup access$000(AdsMediaSource arg0) {
        return arg0.adUiViewGroup;
    }

    static AdsLoader access$100(AdsMediaSource arg0) {
        return arg0.adsLoader;
    }

    static Handler access$1000(AdsMediaSource arg0) {
        return arg0.mainHandler;
    }

    static void access$300(AdsMediaSource arg0, AdPlaybackState arg1) {
        arg0.onAdPlaybackState(arg1);
    }

    static Handler access$400(AdsMediaSource arg0) {
        return arg0.eventHandler;
    }

    static com.google.android.exoplayer2.source.ads.AdsMediaSource$EventListener access$500(AdsMediaSource arg0) {
        return arg0.eventListener;
    }

    static EventDispatcher access$600(AdsMediaSource arg0, MediaPeriodId arg1) {
        return arg0.createEventDispatcher(arg1);
    }

    static EventDispatcher access$700(AdsMediaSource arg0, MediaPeriodId arg1) {
        return arg0.createEventDispatcher(arg1);
    }

    public MediaPeriod createPeriod(MediaPeriodId arg10, Allocator arg11) {
        MediaSource v3;
        if(this.adPlaybackState.adGroupCount > 0 && (arg10.isAd())) {
            int v0 = arg10.adGroupIndex;
            int v1 = arg10.adIndexInAdGroup;
            Uri v2 = this.adPlaybackState.adGroups[v0].uris[v1];
            if(this.adGroupMediaSources[v0].length <= v1) {
                v3 = this.adMediaSourceFactory.createMediaSource(v2);
                int v4 = this.adGroupMediaSources[v0].length;
                if(v1 >= v4) {
                    int v5 = v1 + 1;
                    this.adGroupMediaSources[v0] = Arrays.copyOf(this.adGroupMediaSources[v0], v5);
                    this.adDurationsUs[v0] = Arrays.copyOf(this.adDurationsUs[v0], v5);
                    Arrays.fill(this.adDurationsUs[v0], v4, v5, -9223372036854775807L);
                }

                this.adGroupMediaSources[v0][v1] = v3;
                this.deferredMediaPeriodByAdMediaSource.put(v3, new ArrayList());
                this.prepareChildSource(arg10, v3);
            }

            v3 = this.adGroupMediaSources[v0][v1];
            DeferredMediaPeriod v4_1 = new DeferredMediaPeriod(v3, arg10, arg11);
            v4_1.setPrepareErrorListener(new AdPrepareErrorListener(this, v2, v0, v1));
            Object v11 = this.deferredMediaPeriodByAdMediaSource.get(v3);
            if(v11 == null) {
                v4_1.createPeriod(new MediaPeriodId(0, arg10.windowSequenceNumber));
            }
            else {
                ((List)v11).add(v4_1);
            }

            return ((MediaPeriod)v4_1);
        }

        DeferredMediaPeriod v0_1 = new DeferredMediaPeriod(this.contentMediaSource, arg10, arg11);
        v0_1.createPeriod(arg10);
        return ((MediaPeriod)v0_1);
    }

    protected MediaPeriodId getMediaPeriodIdForChildMediaPeriodId(MediaPeriodId arg2, MediaPeriodId arg3) {
        if(arg2.isAd()) {
        }
        else {
            arg2 = arg3;
        }

        return arg2;
    }

    protected MediaPeriodId getMediaPeriodIdForChildMediaPeriodId(Object arg1, MediaPeriodId arg2) {
        return this.getMediaPeriodIdForChildMediaPeriodId(((MediaPeriodId)arg1), arg2);
    }

    private void maybeUpdateSourceInfo() {
        SinglePeriodAdTimeline v0_1;
        if(this.adPlaybackState != null && this.contentTimeline != null) {
            this.adPlaybackState = this.adPlaybackState.withAdDurationsUs(this.adDurationsUs);
            if(this.adPlaybackState.adGroupCount == 0) {
                Timeline v0 = this.contentTimeline;
            }
            else {
                v0_1 = new SinglePeriodAdTimeline(this.contentTimeline, this.adPlaybackState);
            }

            this.refreshSourceInfo(((Timeline)v0_1), this.contentManifest);
        }
    }

    private void onAdPlaybackState(AdPlaybackState arg4) {
        if(this.adPlaybackState == null) {
            this.adGroupMediaSources = new MediaSource[arg4.adGroupCount][];
            Arrays.fill(this.adGroupMediaSources, new MediaSource[0]);
            this.adDurationsUs = new long[arg4.adGroupCount][];
            Arrays.fill(this.adDurationsUs, new long[0]);
        }

        this.adPlaybackState = arg4;
        this.maybeUpdateSourceInfo();
    }

    private void onAdSourceInfoRefreshed(MediaSource arg4, int arg5, int arg6, Timeline arg7) {
        boolean v1 = true;
        if(arg7.getPeriodCount() == 1) {
        }
        else {
            v1 = false;
        }

        Assertions.checkArgument(v1);
        this.adDurationsUs[arg5][arg6] = arg7.getPeriod(0, this.period).getDurationUs();
        Object v4 = this.deferredMediaPeriodByAdMediaSource.remove(arg4);
        if(v4 != null) {
            for(arg5 = 0; arg5 < ((List)v4).size(); ++arg5) {
                Object v6 = ((List)v4).get(arg5);
                ((DeferredMediaPeriod)v6).createPeriod(new MediaPeriodId(0, ((DeferredMediaPeriod)v6).id.windowSequenceNumber));
            }
        }

        this.maybeUpdateSourceInfo();
    }

    protected void onChildSourceInfoRefreshed(MediaPeriodId arg2, MediaSource arg3, Timeline arg4, Object arg5) {
        if(arg2.isAd()) {
            this.onAdSourceInfoRefreshed(arg3, arg2.adGroupIndex, arg2.adIndexInAdGroup, arg4);
        }
        else {
            this.onContentSourceInfoRefreshed(arg4, arg5);
        }
    }

    protected void onChildSourceInfoRefreshed(Object arg1, MediaSource arg2, Timeline arg3, Object arg4) {
        this.onChildSourceInfoRefreshed(((MediaPeriodId)arg1), arg2, arg3, arg4);
    }

    private void onContentSourceInfoRefreshed(Timeline arg1, Object arg2) {
        this.contentTimeline = arg1;
        this.contentManifest = arg2;
        this.maybeUpdateSourceInfo();
    }

    public void prepareSourceInternal(ExoPlayer arg2, boolean arg3, TransferListener arg4) {
        super.prepareSourceInternal(arg2, arg3, arg4);
        Assertions.checkArgument(arg3);
        ComponentListener v3 = new ComponentListener(this);
        this.componentListener = v3;
        this.prepareChildSource(AdsMediaSource.DUMMY_CONTENT_MEDIA_PERIOD_ID, this.contentMediaSource);
        this.mainHandler.post(new Runnable(arg2, v3) {
            public void run() {
                AdsMediaSource.this.adsLoader.attachPlayer(this.val$player, this.val$componentListener, AdsMediaSource.this.adUiViewGroup);
            }
        });
    }

    public void releasePeriod(MediaPeriod arg3) {
        Object v0 = this.deferredMediaPeriodByAdMediaSource.get(((DeferredMediaPeriod)arg3).mediaSource);
        if(v0 != null) {
            ((List)v0).remove(arg3);
        }

        ((DeferredMediaPeriod)arg3).releasePeriod();
    }

    public void releaseSourceInternal() {
        super.releaseSourceInternal();
        this.componentListener.release();
        this.componentListener = null;
        this.deferredMediaPeriodByAdMediaSource.clear();
        this.contentTimeline = null;
        this.contentManifest = null;
        this.adPlaybackState = null;
        this.adGroupMediaSources = new MediaSource[0][];
        this.adDurationsUs = new long[0][];
        this.mainHandler.post(new Runnable() {
            public void run() {
                AdsMediaSource.this.adsLoader.detachPlayer();
            }
        });
    }
}

