package com.google.android.exoplayer2;

import android.os.Handler$Callback;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.util.Pair;
import com.google.android.exoplayer2.source.MediaPeriod$Callback;
import com.google.android.exoplayer2.source.MediaPeriod;
import com.google.android.exoplayer2.source.MediaSource$MediaPeriodId;
import com.google.android.exoplayer2.source.MediaSource$SourceInfoRefreshListener;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.SampleStream;
import com.google.android.exoplayer2.source.SequenceableLoader;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelector$InvalidationListener;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectorResult;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Clock;
import com.google.android.exoplayer2.util.HandlerWrapper;
import com.google.android.exoplayer2.util.TraceUtil;
import com.google.android.exoplayer2.util.Util;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

final class ExoPlayerImplInternal implements Handler$Callback, PlaybackParameterListener, Sender, Callback, SourceInfoRefreshListener, InvalidationListener {
    final class MediaSourceRefreshInfo {
        public final Object manifest;
        public final MediaSource source;
        public final Timeline timeline;

        public MediaSourceRefreshInfo(MediaSource arg1, Timeline arg2, Object arg3) {
            super();
            this.source = arg1;
            this.timeline = arg2;
            this.manifest = arg3;
        }
    }

    final class PendingMessageInfo implements Comparable {
        public final PlayerMessage message;
        public int resolvedPeriodIndex;
        public long resolvedPeriodTimeUs;
        public Object resolvedPeriodUid;

        public PendingMessageInfo(PlayerMessage arg1) {
            super();
            this.message = arg1;
        }

        public int compareTo(PendingMessageInfo arg5) {
            int v2 = 1;
            int v0 = this.resolvedPeriodUid == null ? 1 : 0;
            int v3 = arg5.resolvedPeriodUid == null ? 1 : 0;
            if(v0 != v3) {
                if(this.resolvedPeriodUid != null) {
                    v2 = -1;
                }

                return v2;
            }

            if(this.resolvedPeriodUid == null) {
                return 0;
            }

            v0 = this.resolvedPeriodIndex - arg5.resolvedPeriodIndex;
            if(v0 != 0) {
                return v0;
            }

            return Util.compareLong(this.resolvedPeriodTimeUs, arg5.resolvedPeriodTimeUs);
        }

        public int compareTo(Object arg1) {
            return this.compareTo(((PendingMessageInfo)arg1));
        }

        public void setResolvedPosition(int arg1, long arg2, Object arg4) {
            this.resolvedPeriodIndex = arg1;
            this.resolvedPeriodTimeUs = arg2;
            this.resolvedPeriodUid = arg4;
        }
    }

    final class PlaybackInfoUpdate {
        private int discontinuityReason;
        private PlaybackInfo lastPlaybackInfo;
        private int operationAcks;
        private boolean positionDiscontinuity;

        PlaybackInfoUpdate(com.google.android.exoplayer2.ExoPlayerImplInternal$1 arg1) {
            this();
        }

        private PlaybackInfoUpdate() {
            super();
        }

        static int access$100(PlaybackInfoUpdate arg0) {
            return arg0.operationAcks;
        }

        static boolean access$200(PlaybackInfoUpdate arg0) {
            return arg0.positionDiscontinuity;
        }

        static int access$300(PlaybackInfoUpdate arg0) {
            return arg0.discontinuityReason;
        }

        public boolean hasPendingUpdate(PlaybackInfo arg2) {
            boolean v2 = arg2 != this.lastPlaybackInfo || this.operationAcks > 0 || (this.positionDiscontinuity) ? true : false;
            return v2;
        }

        public void incrementPendingOperationAcks(int arg2) {
            this.operationAcks += arg2;
        }

        public void reset(PlaybackInfo arg1) {
            this.lastPlaybackInfo = arg1;
            this.operationAcks = 0;
            this.positionDiscontinuity = false;
        }

        public void setPositionDiscontinuity(int arg4) {
            boolean v1 = true;
            if(this.positionDiscontinuity) {
                int v2 = 4;
                if(this.discontinuityReason != v2) {
                    if(arg4 == v2) {
                    }
                    else {
                        v1 = false;
                    }

                    Assertions.checkArgument(v1);
                    return;
                }
            }

            this.positionDiscontinuity = true;
            this.discontinuityReason = arg4;
        }
    }

    final class SeekPosition {
        public final Timeline timeline;
        public final int windowIndex;
        public final long windowPositionUs;

        public SeekPosition(Timeline arg1, int arg2, long arg3) {
            super();
            this.timeline = arg1;
            this.windowIndex = arg2;
            this.windowPositionUs = arg3;
        }
    }

    private static final int IDLE_INTERVAL_MS = 1000;
    private static final int MSG_DO_SOME_WORK = 2;
    public static final int MSG_ERROR = 2;
    private static final int MSG_PERIOD_PREPARED = 9;
    public static final int MSG_PLAYBACK_INFO_CHANGED = 0;
    public static final int MSG_PLAYBACK_PARAMETERS_CHANGED = 1;
    private static final int MSG_PLAYBACK_PARAMETERS_CHANGED_INTERNAL = 16;
    private static final int MSG_PREPARE = 0;
    private static final int MSG_REFRESH_SOURCE_INFO = 8;
    private static final int MSG_RELEASE = 7;
    private static final int MSG_SEEK_TO = 3;
    private static final int MSG_SEND_MESSAGE = 14;
    private static final int MSG_SEND_MESSAGE_TO_TARGET_THREAD = 15;
    private static final int MSG_SET_PLAYBACK_PARAMETERS = 4;
    private static final int MSG_SET_PLAY_WHEN_READY = 1;
    private static final int MSG_SET_REPEAT_MODE = 12;
    private static final int MSG_SET_SEEK_PARAMETERS = 5;
    private static final int MSG_SET_SHUFFLE_ENABLED = 13;
    private static final int MSG_SOURCE_CONTINUE_LOADING_REQUESTED = 10;
    private static final int MSG_STOP = 6;
    private static final int MSG_TRACK_SELECTION_INVALIDATED = 11;
    private static final int PREPARING_SOURCE_INTERVAL_MS = 10;
    private static final int RENDERING_INTERVAL_MS = 10;
    private static final String TAG = "ExoPlayerImplInternal";
    private final long backBufferDurationUs;
    private final BandwidthMeter bandwidthMeter;
    private final Clock clock;
    private final TrackSelectorResult emptyTrackSelectorResult;
    private Renderer[] enabledRenderers;
    private final Handler eventHandler;
    private final HandlerWrapper handler;
    private final HandlerThread internalPlaybackThread;
    private final LoadControl loadControl;
    private final DefaultMediaClock mediaClock;
    private MediaSource mediaSource;
    private int nextPendingMessageIndex;
    private SeekPosition pendingInitialSeekPosition;
    private final ArrayList pendingMessages;
    private int pendingPrepareCount;
    private final Period period;
    private boolean playWhenReady;
    private PlaybackInfo playbackInfo;
    private final PlaybackInfoUpdate playbackInfoUpdate;
    private final ExoPlayer player;
    private final MediaPeriodQueue queue;
    private boolean rebuffering;
    private boolean released;
    private final RendererCapabilities[] rendererCapabilities;
    private long rendererPositionUs;
    private final Renderer[] renderers;
    private int repeatMode;
    private final boolean retainBackBufferFromKeyframe;
    private SeekParameters seekParameters;
    private boolean shuffleModeEnabled;
    private final TrackSelector trackSelector;
    private final Window window;

    public ExoPlayerImplInternal(Renderer[] arg1, TrackSelector arg2, TrackSelectorResult arg3, LoadControl arg4, BandwidthMeter arg5, boolean arg6, int arg7, boolean arg8, Handler arg9, ExoPlayer arg10, Clock arg11) {
        super();
        this.renderers = arg1;
        this.trackSelector = arg2;
        this.emptyTrackSelectorResult = arg3;
        this.loadControl = arg4;
        this.bandwidthMeter = arg5;
        this.playWhenReady = arg6;
        this.repeatMode = arg7;
        this.shuffleModeEnabled = arg8;
        this.eventHandler = arg9;
        this.player = arg10;
        this.clock = arg11;
        this.queue = new MediaPeriodQueue();
        this.backBufferDurationUs = arg4.getBackBufferDurationUs();
        this.retainBackBufferFromKeyframe = arg4.retainBackBufferFromKeyframe();
        this.seekParameters = SeekParameters.DEFAULT;
        this.playbackInfo = PlaybackInfo.createDummy(-9223372036854775807L, arg3);
        this.playbackInfoUpdate = new PlaybackInfoUpdate(null);
        this.rendererCapabilities = new RendererCapabilities[arg1.length];
        int v4;
        for(v4 = 0; v4 < arg1.length; ++v4) {
            arg1[v4].setIndex(v4);
            this.rendererCapabilities[v4] = arg1[v4].getCapabilities();
        }

        this.mediaClock = new DefaultMediaClock(((PlaybackParameterListener)this), arg11);
        this.pendingMessages = new ArrayList();
        this.enabledRenderers = new Renderer[0];
        this.window = new Window();
        this.period = new Period();
        arg2.init(((InvalidationListener)this), arg5);
        this.internalPlaybackThread = new HandlerThread("ExoPlayerImplInternal:Handler", -16);
        this.internalPlaybackThread.start();
        this.handler = arg11.createHandler(this.internalPlaybackThread.getLooper(), ((Handler$Callback)this));
    }

    static void access$400(ExoPlayerImplInternal arg0, PlayerMessage arg1) {
        arg0.deliverMessage(arg1);
    }

    private void deliverMessage(PlayerMessage arg5) {
        if(arg5.isCanceled()) {
            return;
        }

        try {
            arg5.getTarget().handleMessage(arg5.getType(), arg5.getPayload());
        }
        catch(Throwable v1) {
            arg5.markAsProcessed(true);
            throw v1;
        }

        arg5.markAsProcessed(true);
    }

    private void disableRenderer(Renderer arg2) {
        this.mediaClock.onRendererDisabled(arg2);
        this.ensureStopped(arg2);
        arg2.disable();
    }

    private void doSomeWork() {
        int v8;
        ExoPlayerImplInternal v0 = this;
        long v1 = v0.clock.uptimeMillis();
        this.updatePeriods();
        long v4 = 10;
        if(!v0.queue.hasPlayingPeriod()) {
            this.maybeThrowPeriodPrepareError();
            v0.scheduleNextWork(v1, v4);
            return;
        }

        MediaPeriodHolder v3 = v0.queue.getPlayingPeriod();
        TraceUtil.beginSection("doSomeWork");
        this.updatePlaybackPositions();
        long v6 = SystemClock.elapsedRealtime() * 1000;
        v3.mediaPeriod.discardBuffer(v0.playbackInfo.positionUs - v0.backBufferDurationUs, v0.retainBackBufferFromKeyframe);
        Renderer[] v10 = v0.enabledRenderers;
        int v11 = v10.length;
        boolean v12 = true;
        int v14 = 0;
        int v15 = 1;
        while(v14 < v11) {
            Renderer v13 = v10[v14];
            v13.render(v0.rendererPositionUs, v6);
            v15 = v15 == 0 || !v13.isEnded() ? 0 : 1;
            v8 = (v13.isReady()) || (v13.isEnded()) || (v0.rendererWaitingForNextStream(v13)) ? 1 : 0;
            if(v8 == 0) {
                v13.maybeThrowStreamError();
            }

            v12 = !v12 || v8 == 0 ? false : true;
            ++v14;
        }

        if(!v12) {
            this.maybeThrowPeriodPrepareError();
        }

        v6 = v3.info.durationUs;
        v8 = 4;
        int v9 = 3;
        int v10_1 = 2;
        if(v15 != 0) {
            if(v6 != -9223372036854775807L && v6 > v0.playbackInfo.positionUs) {
                goto label_80;
            }

            if(!v3.info.isFinal) {
                goto label_80;
            }

            v0.setState(v8);
            goto label_78;
        }
        else {
        label_80:
            if(v0.playbackInfo.playbackState == v10_1 && (v0.shouldTransitionToReadyState(v12))) {
                v0.setState(v9);
                if(v0.playWhenReady) {
                    this.startRenderers();
                }
                else {
                }

                goto label_104;
            }

            if(v0.playbackInfo.playbackState != v9) {
                goto label_104;
            }

            if(v0.enabledRenderers.length != 0) {
                if(!v12) {
                    goto label_100;
                }

                goto label_104;
            }
            else if(this.isTimelineReady()) {
                goto label_104;
            }

        label_100:
            v0.rebuffering = v0.playWhenReady;
            v0.setState(v10_1);
        label_78:
            this.stopRenderers();
        }

    label_104:
        if(v0.playbackInfo.playbackState == v10_1) {
            Renderer[] v3_1 = v0.enabledRenderers;
            int v6_1 = v3_1.length;
            int v7;
            for(v7 = 0; v7 < v6_1; ++v7) {
                v3_1[v7].maybeThrowStreamError();
            }
        }

        if((v0.playWhenReady) && v0.playbackInfo.playbackState == v9 || v0.playbackInfo.playbackState == v10_1) {
            v0.scheduleNextWork(v1, v4);
        }
        else {
            if(v0.enabledRenderers.length != 0 && v0.playbackInfo.playbackState != v8) {
                v0.scheduleNextWork(v1, 1000);
                goto label_136;
            }

            v0.handler.removeMessages(v10_1);
        }

    label_136:
        TraceUtil.endSection();
    }

    private void enableRenderer(int arg12, boolean arg13, int arg14) {
        MediaPeriodHolder v0 = this.queue.getPlayingPeriod();
        Renderer v1 = this.renderers[arg12];
        this.enabledRenderers[arg14] = v1;
        if(v1.getState() == 0) {
            RendererConfiguration v3 = v0.trackSelectorResult.rendererConfigurations[arg12];
            Format[] v4 = ExoPlayerImplInternal.getFormats(v0.trackSelectorResult.selections.get(arg12));
            arg14 = !this.playWhenReady || this.playbackInfo.playbackState != 3 ? 0 : 1;
            boolean v8 = (arg13) || arg14 == 0 ? false : true;
            v1.enable(v3, v4, v0.sampleStreams[arg12], this.rendererPositionUs, v8, v0.getRendererOffset());
            this.mediaClock.onRendererEnabled(v1);
            if(arg14 == 0) {
                return;
            }

            v1.start();
        }
    }

    private void enableRenderers(boolean[] arg5, int arg6) {
        this.enabledRenderers = new Renderer[arg6];
        MediaPeriodHolder v6 = this.queue.getPlayingPeriod();
        int v0 = 0;
        int v1 = 0;
        while(v0 < this.renderers.length) {
            if(v6.trackSelectorResult.isRendererEnabled(v0)) {
                this.enableRenderer(v0, arg5[v0], v1);
                ++v1;
            }

            ++v0;
        }
    }

    private void ensureStopped(Renderer arg3) {
        if(arg3.getState() == 2) {
            arg3.stop();
        }
    }

    private MediaPeriodId getFirstMediaPeriodId() {
        Timeline v0 = this.playbackInfo.timeline;
        if(v0.isEmpty()) {
            return PlaybackInfo.DUMMY_MEDIA_PERIOD_ID;
        }

        return new MediaPeriodId(v0.getWindow(v0.getFirstWindowIndex(this.shuffleModeEnabled), this.window).firstPeriodIndex);
    }

    private static Format[] getFormats(TrackSelection arg4) {
        int v0 = 0;
        int v1 = arg4 != null ? arg4.length() : 0;
        Format[] v2 = new Format[v1];
        while(v0 < v1) {
            v2[v0] = arg4.getFormat(v0);
            ++v0;
        }

        return v2;
    }

    private Pair getPeriodPosition(Timeline arg7, int arg8, long arg9) {
        return arg7.getPeriodPosition(this.window, this.period, arg8, arg9);
    }

    public Looper getPlaybackLooper() {
        return this.internalPlaybackThread.getLooper();
    }

    private void handleContinueLoadingRequested(MediaPeriod arg3) {
        if(!this.queue.isLoading(arg3)) {
            return;
        }

        this.queue.reevaluateBuffer(this.rendererPositionUs);
        this.maybeContinueLoading();
    }

    public boolean handleMessage(Message arg6) {
        Handler v2;
        int v0 = 2;
        try {
            switch(arg6.what) {
                case 0: {
                    goto label_63;
                }
                case 1: {
                    goto label_56;
                }
                case 2: {
                    goto label_54;
                }
                case 3: {
                    goto label_51;
                }
                case 4: {
                    goto label_48;
                }
                case 5: {
                    goto label_45;
                }
                case 6: {
                    goto label_38;
                }
                case 7: {
                    goto label_36;
                }
                case 8: {
                    goto label_33;
                }
                case 9: {
                    goto label_30;
                }
                case 10: {
                    goto label_27;
                }
                case 11: {
                    goto label_25;
                }
                case 12: {
                    goto label_22;
                }
                case 13: {
                    goto label_15;
                }
                case 14: {
                    goto label_12;
                }
                case 15: {
                    goto label_9;
                }
                case 16: {
                    goto label_6;
                }
            }

            return 0;
        label_33:
            this.handleSourceInfoRefreshed(arg6.obj);
            goto label_75;
        label_36:
            this.releaseInternal();
            return 1;
        label_38:
            boolean v6_3 = arg6.arg1 != 0 ? true : false;
            this.stopInternal(v6_3, true);
            goto label_75;
        label_6:
            this.handlePlaybackParameters(arg6.obj);
            goto label_75;
        label_9:
            this.sendMessageToTargetThread(arg6.obj);
            goto label_75;
        label_12:
            this.sendMessageInternal(arg6.obj);
            goto label_75;
        label_45:
            this.setSeekParametersInternal(arg6.obj);
            goto label_75;
        label_15:
            v6_3 = arg6.arg1 != 0 ? true : false;
            this.setShuffleModeEnabledInternal(v6_3);
            goto label_75;
        label_48:
            this.setPlaybackParametersInternal(arg6.obj);
            goto label_75;
        label_51:
            this.seekToInternal(arg6.obj);
            goto label_75;
        label_54:
            this.doSomeWork();
            goto label_75;
        label_22:
            this.setRepeatModeInternal(arg6.arg1);
            goto label_75;
        label_56:
            v6_3 = arg6.arg1 != 0 ? true : false;
            this.setPlayWhenReadyInternal(v6_3);
            goto label_75;
        label_25:
            this.reselectTracksInternal();
            goto label_75;
        label_27:
            this.handleContinueLoadingRequested(arg6.obj);
            goto label_75;
        label_30:
            this.handlePeriodPrepared(arg6.obj);
            goto label_75;
        label_63:
            Object v3 = arg6.obj;
            boolean v4 = arg6.arg1 != 0 ? true : false;
            v6_3 = arg6.arg2 != 0 ? true : false;
            this.prepareInternal(((MediaSource)v3), v4, v6_3);
        label_75:
            this.maybeNotifyPlaybackInfoChanged();
            return 1;
        }
        catch(RuntimeException v6) {
            Log.e("ExoPlayerImplInternal", "Internal runtime error.", ((Throwable)v6));
            this.stopInternal(false, false);
            v2 = this.eventHandler;
            v6_2 = ExoPlaybackException.createForUnexpected(v6);
        }
        catch(IOException v6_1) {
            Log.e("ExoPlayerImplInternal", "Source error.", ((Throwable)v6_1));
            this.stopInternal(false, false);
            v2 = this.eventHandler;
            v6_2 = ExoPlaybackException.createForSource(v6_1);
        }
        catch(ExoPlaybackException v6_2) {
            Log.e("ExoPlayerImplInternal", "Playback error.", ((Throwable)v6_2));
            this.stopInternal(false, false);
            v2 = this.eventHandler;
        }

        v2.obtainMessage(v0, v6_2).sendToTarget();
        this.maybeNotifyPlaybackInfoChanged();
        return 1;
    }

    private void handlePeriodPrepared(MediaPeriod arg3) {
        if(!this.queue.isLoading(arg3)) {
            return;
        }

        MediaPeriodHolder v3 = this.queue.getLoadingPeriod();
        v3.handlePrepared(this.mediaClock.getPlaybackParameters().speed);
        this.updateLoadControlTrackSelection(v3.trackGroups, v3.trackSelectorResult);
        if(!this.queue.hasPlayingPeriod()) {
            this.resetRendererPosition(this.queue.advancePlayingPeriod().info.startPositionUs);
            this.updatePlayingPeriodRenderers(null);
        }

        this.maybeContinueLoading();
    }

    private void handlePlaybackParameters(PlaybackParameters arg6) {
        this.eventHandler.obtainMessage(1, arg6).sendToTarget();
        this.updateTrackSelectionPlaybackSpeed(arg6.speed);
        Renderer[] v0 = this.renderers;
        int v1 = v0.length;
        int v2;
        for(v2 = 0; v2 < v1; ++v2) {
            Renderer v3 = v0[v2];
            if(v3 != null) {
                v3.setOperatingRate(arg6.speed);
            }
        }
    }

    private void handleSourceInfoRefreshEndedPlayback() {
        this.setState(4);
        this.resetInternal(false, true, false);
    }

    private void handleSourceInfoRefreshed(MediaSourceRefreshInfo arg19) {
        PlaybackInfo v0_5;
        MediaPeriodInfo v3_1;
        long v11;
        PlaybackInfo v9;
        MediaPeriodId v10;
        long v13;
        int v2_1;
        Pair v0_3;
        ExoPlayerImplInternal v1 = this;
        MediaSourceRefreshInfo v0 = arg19;
        if(v0.source != v1.mediaSource) {
            return;
        }

        Timeline v2 = v1.playbackInfo.timeline;
        Timeline v3 = v0.timeline;
        Object v0_1 = v0.manifest;
        v1.queue.setTimeline(v3);
        v1.playbackInfo = v1.playbackInfo.copyWithTimeline(v3, v0_1);
        this.resolvePendingMessagePositions();
        long v5 = -9223372036854775807L;
        long v7 = 0;
        if(v1.pendingPrepareCount > 0) {
            v1.playbackInfoUpdate.incrementPendingOperationAcks(v1.pendingPrepareCount);
            v1.pendingPrepareCount = 0;
            if(v1.pendingInitialSeekPosition != null) {
                try {
                    v0_3 = v1.resolveSeekPosition(v1.pendingInitialSeekPosition, true);
                }
                catch(IllegalSeekPositionException v0_2) {
                    v1.playbackInfo = v1.playbackInfo.fromNewPosition(this.getFirstMediaPeriodId(), -9223372036854775807L, -9223372036854775807L);
                    throw v0_2;
                }

                v1.pendingInitialSeekPosition = null;
                if(v0_3 == null) {
                    goto label_57;
                }
                else {
                    v2_1 = v0_3.first.intValue();
                    v13 = v0_3.second.longValue();
                    v10 = v1.queue.resolveMediaPeriodIdForAds(v2_1, v13);
                    v9 = v1.playbackInfo;
                    if(v10.isAd()) {
                        goto label_71;
                    }
                    else {
                        goto label_73;
                    }
                }
            }
            else if(v1.playbackInfo.startPositionUs == v5) {
                if(v3.isEmpty()) {
                label_57:
                    this.handleSourceInfoRefreshEndedPlayback();
                }
                else {
                    v0_3 = v1.getPeriodPosition(v3, v3.getFirstWindowIndex(v1.shuffleModeEnabled), v5);
                    v2_1 = v0_3.first.intValue();
                    v13 = v0_3.second.longValue();
                    v10 = v1.queue.resolveMediaPeriodIdForAds(v2_1, v13);
                    v9 = v1.playbackInfo;
                    if(v10.isAd()) {
                    label_71:
                        v11 = v7;
                        goto label_74;
                    }
                    else {
                    label_73:
                        v11 = v13;
                    label_74:
                        v1.playbackInfo = v9.fromNewPosition(v10, v11, v13);
                    }
                }
            }

            return;
        }

        if(v2.isEmpty()) {
            if(!v3.isEmpty()) {
                v0_3 = v1.getPeriodPosition(v3, v3.getFirstWindowIndex(v1.shuffleModeEnabled), v5);
                v2_1 = v0_3.first.intValue();
                v13 = v0_3.second.longValue();
                v10 = v1.queue.resolveMediaPeriodIdForAds(v2_1, v13);
                v9 = v1.playbackInfo;
                v11 = v10.isAd() ? v7 : v13;
                v1.playbackInfo = v9.fromNewPosition(v10, v11, v13);
            }

            return;
        }

        MediaPeriodHolder v0_4 = v1.queue.getFrontPeriod();
        int v9_1 = v1.playbackInfo.periodId.periodIndex;
        v13 = v1.playbackInfo.contentPositionUs;
        Object v10_1 = v0_4 == null ? v2.getUidOfPeriod(v9_1) : v0_4.uid;
        int v10_2 = v3.getIndexOfPeriod(v10_1);
        int v11_1 = -1;
        if(v10_2 == v11_1) {
            v2_1 = v1.resolveSubsequentPeriod(v9_1, v2, v3);
            if(v2_1 == v11_1) {
                this.handleSourceInfoRefreshEndedPlayback();
                return;
            }
            else {
                Pair v2_2 = v1.getPeriodPosition(v3, v3.getPeriod(v2_1, v1.period).windowIndex, v5);
                int v4 = v2_2.first.intValue();
                v5 = v2_2.second.longValue();
                MediaPeriodId v13_1 = v1.queue.resolveMediaPeriodIdForAds(v4, v5);
                if(v0_4 != null) {
                    Object v2_3 = v3.getUidOfPeriod(v4);
                    do {
                    label_129:
                        v3_1 = v0_4.info.copyWithPeriodIndex(v11_1);
                        while(true) {
                        label_131:
                            v0_4.info = v3_1;
                            if(v0_4.next != null) {
                                v0_4 = v0_4.next;
                                if(!v0_4.uid.equals(v2_3)) {
                                    goto label_129;
                                }

                                break;
                            }

                            goto label_142;
                        }
                    }
                    while(true);

                    v3_1 = v1.queue.getUpdatedMediaPeriodInfo(v0_4.info, v4);
                    goto label_131;
                }

            label_142:
                if(v13_1.isAd()) {
                }
                else {
                    v7 = v5;
                }

                v0_5 = v1.playbackInfo.fromNewPosition(v13_1, v1.seekToPeriodPosition(v13_1, v7), v5);
            }
        }
        else {
            if(v10_2 != v9_1) {
                v1.playbackInfo = v1.playbackInfo.copyWithPeriodIndex(v10_2);
            }

            MediaPeriodId v0_6 = v1.playbackInfo.periodId;
            if(v0_6.isAd()) {
                MediaPeriodId v12 = v1.queue.resolveMediaPeriodIdForAds(v10_2, v13);
                if(!v12.equals(v0_6)) {
                    if(v12.isAd()) {
                    }
                    else {
                        v7 = v13;
                    }

                    v0_5 = v1.playbackInfo.fromNewPosition(v12, v1.seekToPeriodPosition(v12, v7), v13);
                    goto label_150;
                }
            }

            goto label_175;
        }

    label_150:
        v1.playbackInfo = v0_5;
        return;
    label_175:
        if(!v1.queue.updateQueuedPeriods(v0_6, v1.rendererPositionUs)) {
            v1.seekToCurrentPosition(false);
        }

        this.updateLoadingMediaPeriodId();
    }

    private boolean isTimelineReady() {
        boolean v0_1;
        MediaPeriodHolder v0 = this.queue.getPlayingPeriod();
        long v1 = v0.info.durationUs;
        if(v1 == -9223372036854775807L || this.playbackInfo.positionUs < v1) {
        label_22:
            v0_1 = true;
        }
        else {
            if(v0.next != null) {
                if(v0.next.prepared) {
                    goto label_22;
                }
                else if(v0.next.info.id.isAd()) {
                    goto label_22;
                }
            }

            v0_1 = false;
        }

        return v0_1;
    }

    private void maybeContinueLoading() {
        MediaPeriodHolder v0 = this.queue.getLoadingPeriod();
        long v1 = v0.getNextLoadPositionUs();
        if(v1 == -9223372036854775808L) {
            this.setIsLoading(false);
            return;
        }

        boolean v1_1 = this.loadControl.shouldContinueLoading(v1 - v0.toPeriodTime(this.rendererPositionUs), this.mediaClock.getPlaybackParameters().speed);
        this.setIsLoading(v1_1);
        if(v1_1) {
            v0.continueLoading(this.rendererPositionUs);
        }
    }

    private void maybeNotifyPlaybackInfoChanged() {
        if(this.playbackInfoUpdate.hasPendingUpdate(this.playbackInfo)) {
            Handler v0 = this.eventHandler;
            int v2 = PlaybackInfoUpdate.access$100(this.playbackInfoUpdate);
            int v3 = PlaybackInfoUpdate.access$200(this.playbackInfoUpdate) ? PlaybackInfoUpdate.access$300(this.playbackInfoUpdate) : -1;
            v0.obtainMessage(0, v2, v3, this.playbackInfo).sendToTarget();
            this.playbackInfoUpdate.reset(this.playbackInfo);
        }
    }

    private void maybeThrowPeriodPrepareError() {
        MediaPeriodHolder v0 = this.queue.getLoadingPeriod();
        MediaPeriodHolder v1 = this.queue.getReadingPeriod();
        if(v0 != null && !v0.prepared && (v1 == null || v1.next == v0)) {
            Renderer[] v1_1 = this.enabledRenderers;
            int v2 = v1_1.length;
            int v3;
            for(v3 = 0; v3 < v2; ++v3) {
                if(!v1_1[v3].hasReadStreamToEnd()) {
                    return;
                }
            }

            v0.mediaPeriod.maybeThrowPrepareError();
        }
    }

    private void maybeTriggerPendingMessages(long arg7, long arg9) {
        Object v1;
        if(!this.pendingMessages.isEmpty()) {
            if(this.playbackInfo.periodId.isAd()) {
            }
            else {
                if(this.playbackInfo.startPositionUs == arg7) {
                    --arg7;
                }

                int v0 = this.playbackInfo.periodId.periodIndex;
                Object v2 = null;
                if(this.nextPendingMessageIndex > 0) {
                label_19:
                    v1 = this.pendingMessages.get(this.nextPendingMessageIndex - 1);
                label_25:
                    if(v1 != null) {
                        if(((PendingMessageInfo)v1).resolvedPeriodIndex <= v0) {
                            if(((PendingMessageInfo)v1).resolvedPeriodIndex != v0) {
                            }
                            else if(((PendingMessageInfo)v1).resolvedPeriodTimeUs > arg7) {
                                goto label_32;
                            }

                            goto label_38;
                        }

                    label_32:
                        --this.nextPendingMessageIndex;
                        if(this.nextPendingMessageIndex <= 0) {
                            goto label_24;
                        }

                        goto label_19;
                    }

                label_38:
                    if(this.nextPendingMessageIndex < this.pendingMessages.size()) {
                    label_42:
                        v1 = this.pendingMessages.get(this.nextPendingMessageIndex);
                    label_47:
                        if(v1 != null && ((PendingMessageInfo)v1).resolvedPeriodUid != null) {
                            if(((PendingMessageInfo)v1).resolvedPeriodIndex >= v0) {
                                if(((PendingMessageInfo)v1).resolvedPeriodIndex != v0) {
                                }
                                else if(((PendingMessageInfo)v1).resolvedPeriodTimeUs <= arg7) {
                                    goto label_56;
                                }

                                goto label_64;
                            }

                        label_56:
                            ++this.nextPendingMessageIndex;
                            if(this.nextPendingMessageIndex >= this.pendingMessages.size()) {
                                goto label_46;
                            }

                            goto label_42;
                        }

                        while(true) {
                        label_64:
                            if(v1 == null) {
                                return;
                            }

                            if(((PendingMessageInfo)v1).resolvedPeriodUid == null) {
                                return;
                            }

                            if(((PendingMessageInfo)v1).resolvedPeriodIndex != v0) {
                                return;
                            }

                            if(((PendingMessageInfo)v1).resolvedPeriodTimeUs <= arg7) {
                                return;
                            }

                            if(((PendingMessageInfo)v1).resolvedPeriodTimeUs > arg9) {
                                return;
                            }

                            this.sendMessageToTarget(((PendingMessageInfo)v1).message);
                            if((((PendingMessageInfo)v1).message.getDeleteAfterDelivery()) || (((PendingMessageInfo)v1).message.isCanceled())) {
                                this.pendingMessages.remove(this.nextPendingMessageIndex);
                            }
                            else {
                                ++this.nextPendingMessageIndex;
                            }

                            if(this.nextPendingMessageIndex < this.pendingMessages.size()) {
                                v1 = this.pendingMessages.get(this.nextPendingMessageIndex);
                                continue;
                            }

                            v1 = v2;
                        }
                    }

                label_46:
                    v1 = v2;
                    goto label_47;
                }

            label_24:
                v1 = v2;
                goto label_25;
            }
        }
    }

    private void maybeUpdateLoadingPeriod() {
        this.queue.reevaluateBuffer(this.rendererPositionUs);
        if(this.queue.shouldLoadNextMediaPeriod()) {
            MediaPeriodInfo v0 = this.queue.getNextMediaPeriodInfo(this.rendererPositionUs, this.playbackInfo);
            if(v0 == null) {
                this.mediaSource.maybeThrowSourceInfoRefreshError();
            }
            else {
                this.queue.enqueueNextMediaPeriod(this.rendererCapabilities, this.trackSelector, this.loadControl.getAllocator(), this.mediaSource, this.playbackInfo.timeline.getUidOfPeriod(v0.id.periodIndex), v0).prepare(((Callback)this), v0.startPositionUs);
                this.setIsLoading(true);
                this.updateLoadingMediaPeriodId();
            }
        }
    }

    public void onContinueLoadingRequested(MediaPeriod arg3) {
        this.handler.obtainMessage(10, arg3).sendToTarget();
    }

    public void onContinueLoadingRequested(SequenceableLoader arg1) {
        this.onContinueLoadingRequested(((MediaPeriod)arg1));
    }

    public void onPlaybackParametersChanged(PlaybackParameters arg3) {
        this.handler.obtainMessage(16, arg3).sendToTarget();
    }

    public void onPrepared(MediaPeriod arg3) {
        this.handler.obtainMessage(9, arg3).sendToTarget();
    }

    public void onSourceInfoRefreshed(MediaSource arg3, Timeline arg4, Object arg5) {
        this.handler.obtainMessage(8, new MediaSourceRefreshInfo(arg3, arg4, arg5)).sendToTarget();
    }

    public void onTrackSelectionsInvalidated() {
        this.handler.sendEmptyMessage(11);
    }

    public void prepare(MediaSource arg3, boolean arg4, boolean arg5) {
        this.handler.obtainMessage(0, ((int)arg4), ((int)arg5), arg3).sendToTarget();
    }

    private void prepareInternal(MediaSource arg3, boolean arg4, boolean arg5) {
        ++this.pendingPrepareCount;
        this.resetInternal(true, arg4, arg5);
        this.loadControl.onPrepared();
        this.mediaSource = arg3;
        this.setState(2);
        arg3.prepareSource(this.player, true, ((SourceInfoRefreshListener)this), this.bandwidthMeter.getTransferListener());
        this.handler.sendEmptyMessage(2);
    }

    public void release() {
        __monitor_enter(this);
        try {
            if(!this.released) {
                goto label_5;
            }
        }
        catch(Throwable v0) {
            goto label_21;
        }

        __monitor_exit(this);
        return;
        try {
        label_5:
            this.handler.sendEmptyMessage(7);
            int v0_1 = 0;
            while(!this.released) {
                try {
                    this.wait();
                }
                catch(InterruptedException ) {
                    v0_1 = 1;
                }
            }
        }
        catch(Throwable v0) {
            goto label_21;
        }

        if(v0_1 != 0) {
            try {
                Thread.currentThread().interrupt();
            }
            catch(Throwable v0) {
                goto label_21;
            }
        }

        __monitor_exit(this);
        return;
    label_21:
        __monitor_exit(this);
        throw v0;
    }

    private void releaseInternal() {
        this.resetInternal(true, true, true);
        this.loadControl.onReleased();
        this.setState(1);
        this.internalPlaybackThread.quit();
        __monitor_enter(this);
        try {
            this.released = true;
            this.notifyAll();
            __monitor_exit(this);
            return;
        label_13:
            __monitor_exit(this);
        }
        catch(Throwable v0) {
            goto label_13;
        }

        throw v0;
    }

    private boolean rendererWaitingForNextStream(Renderer arg3) {
        MediaPeriodHolder v0 = this.queue.getReadingPeriod();
        boolean v3 = v0.next == null || !v0.next.prepared || !arg3.hasReadStreamToEnd() ? false : true;
        return v3;
    }

    private void reselectTracksInternal() {
        int v7_1;
        int v5;
        boolean[] v2_2;
        if(!this.queue.hasPlayingPeriod()) {
            return;
        }

        float v0 = this.mediaClock.getPlaybackParameters().speed;
        MediaPeriodHolder v1 = this.queue.getPlayingPeriod();
        MediaPeriodHolder v2 = this.queue.getReadingPeriod();
        int v4 = 1;
        while(v1 != null) {
            if(!v1.prepared) {
            }
            else if(v1.selectTracks(v0)) {
                int v0_1 = 4;
                if(v4 != 0) {
                    v1 = this.queue.getPlayingPeriod();
                    boolean v2_1 = this.queue.removeAfter(v1);
                    boolean[] v4_1 = new boolean[this.renderers.length];
                    long v7 = v1.applyTrackSelection(this.playbackInfo.positionUs, v2_1, v4_1);
                    this.updateLoadControlTrackSelection(v1.trackGroups, v1.trackSelectorResult);
                    if(this.playbackInfo.playbackState != v0_1 && v7 != this.playbackInfo.positionUs) {
                        this.playbackInfo = this.playbackInfo.fromNewPosition(this.playbackInfo.periodId, v7, this.playbackInfo.contentPositionUs);
                        this.playbackInfoUpdate.setPositionDiscontinuity(v0_1);
                        this.resetRendererPosition(v7);
                    }

                    v2_2 = new boolean[this.renderers.length];
                    v5 = 0;
                    v7_1 = 0;
                    goto label_57;
                }
                else {
                    this.queue.removeAfter(v1);
                    if(!v1.prepared) {
                        goto label_104;
                    }

                    v1.applyTrackSelection(Math.max(v1.info.startPositionUs, v1.toPeriodTime(this.rendererPositionUs)), false);
                    this.updateLoadControlTrackSelection(v1.trackGroups, v1.trackSelectorResult);
                }

                goto label_104;
            }
            else {
                if(v1 == v2) {
                    v4 = 0;
                }

                v1 = v1.next;
                continue;
            }

            return;
        }

        return;
    label_57:
        while(v5 < this.renderers.length) {
            Renderer v8 = this.renderers[v5];
            boolean v9 = v8.getState() != 0 ? true : false;
            v2_2[v5] = v9;
            SampleStream v9_1 = v1.sampleStreams[v5];
            if(v9_1 != null) {
                ++v7_1;
            }

            if(v2_2[v5]) {
                if(v9_1 != v8.getStream()) {
                    this.disableRenderer(v8);
                }
                else if(v4_1[v5]) {
                    v8.resetPosition(this.rendererPositionUs);
                }
            }

            ++v5;
        }

        this.playbackInfo = this.playbackInfo.copyWithTrackInfo(v1.trackGroups, v1.trackSelectorResult);
        this.enableRenderers(v2_2, v7_1);
    label_104:
        this.updateLoadingMediaPeriodId();
        if(this.playbackInfo.playbackState != v0_1) {
            this.maybeContinueLoading();
            this.updatePlaybackPositions();
            this.handler.sendEmptyMessage(2);
        }
    }

    private void resetInternal(boolean arg25, boolean arg26, boolean arg27) {
        ExoPlayerImplInternal v1 = this;
        v1.handler.removeMessages(2);
        v1.rebuffering = false;
        v1.mediaClock.stop();
        v1.rendererPositionUs = 0;
        Renderer[] v5 = v1.enabledRenderers;
        int v6 = v5.length;
        int v7;
        for(v7 = 0; v7 < v6; ++v7) {
            Renderer v0 = v5[v7];
            try {
                v1.disableRenderer(v0);
            }
            catch(RuntimeException v0_1) {
                Log.e("ExoPlayerImplInternal", "Stop failed.", v0_1);
            }
        }

        v1.enabledRenderers = new Renderer[0];
        v1.queue.clear((((int)arg26)) ^ 1);
        v1.setIsLoading(false);
        SeekPosition v0_2 = null;
        if(arg26) {
            v1.pendingInitialSeekPosition = v0_2;
        }

        if(arg27) {
            v1.queue.setTimeline(Timeline.EMPTY);
            Iterator v5_1 = v1.pendingMessages.iterator();
            while(v5_1.hasNext()) {
                v5_1.next().message.markAsProcessed(false);
            }

            v1.pendingMessages.clear();
            v1.nextPendingMessageIndex = 0;
        }

        MediaPeriodId v4 = arg26 ? this.getFirstMediaPeriodId() : v1.playbackInfo.periodId;
        MediaPeriodId v17 = v4;
        long v4_1 = -9223372036854775807L;
        long v22 = arg26 ? v4_1 : v1.playbackInfo.positionUs;
        if(!arg26) {
            v4_1 = v1.playbackInfo.contentPositionUs;
        }

        long v11 = v4_1;
        PlaybackInfo v2 = null;
        Timeline v4_2 = arg27 ? Timeline.EMPTY : v1.playbackInfo.timeline;
        Timeline v6_1 = v4_2;
        Object v7_1 = arg27 ? v0_2 : v1.playbackInfo.manifest;
        int v13 = v1.playbackInfo.playbackState;
        TrackGroupArray v4_3 = arg27 ? TrackGroupArray.EMPTY : v1.playbackInfo.trackGroups;
        TrackGroupArray v15 = v4_3;
        TrackSelectorResult v3 = arg27 ? v1.emptyTrackSelectorResult : v1.playbackInfo.trackSelectorResult;
        TrackSelectorResult v16 = v3;
        super(v6_1, v7_1, v17, v22, v11, v13, false, v15, v16, v17, v22, 0, v22);
        v1.playbackInfo = v2;
        if((arg25) && v1.mediaSource != null) {
            v1.mediaSource.releaseSource(((SourceInfoRefreshListener)v1));
            v1.mediaSource = ((MediaSource)v0_2);
        }
    }

    private void resetRendererPosition(long arg5) {
        if(!this.queue.hasPlayingPeriod()) {
        }
        else {
            arg5 = this.queue.getPlayingPeriod().toRendererTime(arg5);
        }

        this.rendererPositionUs = arg5;
        this.mediaClock.resetPosition(this.rendererPositionUs);
        Renderer[] v5 = this.enabledRenderers;
        int v6 = v5.length;
        int v0;
        for(v0 = 0; v0 < v6; ++v0) {
            v5[v0].resetPosition(this.rendererPositionUs);
        }
    }

    private boolean resolvePendingMessagePosition(PendingMessageInfo arg7) {
        if(arg7.resolvedPeriodUid == null) {
            Pair v0 = this.resolveSeekPosition(new SeekPosition(arg7.message.getTimeline(), arg7.message.getWindowIndex(), C.msToUs(arg7.message.getPositionMs())), false);
            if(v0 == null) {
                return 0;
            }
            else {
                arg7.setResolvedPosition(v0.first.intValue(), v0.second.longValue(), this.playbackInfo.timeline.getUidOfPeriod(v0.first.intValue()));
            }
        }
        else {
            int v0_1 = this.playbackInfo.timeline.getIndexOfPeriod(arg7.resolvedPeriodUid);
            if(v0_1 == -1) {
                return 0;
            }
            else {
                arg7.resolvedPeriodIndex = v0_1;
            }
        }

        return 1;
    }

    private void resolvePendingMessagePositions() {
        int v0;
        for(v0 = this.pendingMessages.size() - 1; v0 >= 0; --v0) {
            if(!this.resolvePendingMessagePosition(this.pendingMessages.get(v0))) {
                this.pendingMessages.get(v0).message.markAsProcessed(false);
                this.pendingMessages.remove(v0);
            }
        }

        Collections.sort(this.pendingMessages);
    }

    private Pair resolveSeekPosition(SeekPosition arg11, boolean arg12) {
        Pair v2;
        Timeline v0 = this.playbackInfo.timeline;
        Timeline v1 = arg11.timeline;
        Pair v3 = null;
        if(v0.isEmpty()) {
            return v3;
        }

        if(v1.isEmpty()) {
            v1 = v0;
        }

        try {
            v2 = v1.getPeriodPosition(this.window, this.period, arg11.windowIndex, arg11.windowPositionUs);
            if(v0 != v1) {
                goto label_18;
            }
        }
        catch(IndexOutOfBoundsException ) {
            throw new IllegalSeekPositionException(v0, arg11.windowIndex, arg11.windowPositionUs);
        }

        return v2;
    label_18:
        int v11 = v0.getIndexOfPeriod(v1.getUidOfPeriod(v2.first.intValue()));
        int v4 = -1;
        if(v11 != v4) {
            return Pair.create(Integer.valueOf(v11), v2.second);
        }

        if(arg12) {
            v11 = this.resolveSubsequentPeriod(v2.first.intValue(), v1, v0);
            if(v11 != v4) {
                return this.getPeriodPosition(v0, v0.getPeriod(v11, this.period).windowIndex, -9223372036854775807L);
            }
        }

        return v3;
    }

    private int resolveSubsequentPeriod(int arg10, Timeline arg11, Timeline arg12) {
        int v0 = arg11.getPeriodCount();
        int v1 = -1;
        int v2 = 0;
        int v4 = arg10;
        arg10 = -1;
        while(v2 < v0) {
            if(arg10 != v1) {
                return arg10;
            }

            v4 = arg11.getNextPeriodIndex(v4, this.period, this.window, this.repeatMode, this.shuffleModeEnabled);
            if(v4 == v1) {
            }
            else {
                arg10 = arg12.getIndexOfPeriod(arg11.getUidOfPeriod(v4));
                ++v2;
                continue;
            }

            return arg10;
        }

        return arg10;
    }

    private void scheduleNextWork(long arg3, long arg5) {
        this.handler.removeMessages(2);
        this.handler.sendEmptyMessageAtTime(2, arg3 + arg5);
    }

    public void seekTo(Timeline arg3, int arg4, long arg5) {
        this.handler.obtainMessage(3, new SeekPosition(arg3, arg4, arg5)).sendToTarget();
    }

    private void seekToCurrentPosition(boolean arg8) {
        MediaPeriodId v2 = this.queue.getPlayingPeriod().info.id;
        long v3 = this.seekToPeriodPosition(v2, this.playbackInfo.positionUs, true);
        if(v3 != this.playbackInfo.positionUs) {
            this.playbackInfo = this.playbackInfo.fromNewPosition(v2, v3, this.playbackInfo.contentPositionUs);
            if(arg8) {
                this.playbackInfoUpdate.setPositionDiscontinuity(4);
            }
        }
    }

    private void seekToInternal(SeekPosition arg21) {
        long v16;
        long v3_1;
        int v2_1;
        int v9;
        long v18;
        long v12;
        MediaPeriodId v15;
        ExoPlayerImplInternal v1 = this;
        SeekPosition v0 = arg21;
        int v3 = 1;
        v1.playbackInfoUpdate.incrementPendingOperationAcks(1);
        Pair v2 = v1.resolveSeekPosition(v0, true);
        long v4 = 0;
        long v7 = -9223372036854775807L;
        if(v2 == null) {
            v15 = this.getFirstMediaPeriodId();
            v12 = v7;
            v18 = v12;
            goto label_14;
        }
        else {
            v9 = v2.first.intValue();
            long v10 = v2.second.longValue();
            MediaPeriodId v9_1 = v1.queue.resolveMediaPeriodIdForAds(v9, v10);
            if(v9_1.isAd()) {
                v12 = v4;
                v15 = v9_1;
                v18 = v10;
            label_14:
                v2_1 = 1;
            }
            else {
                v12 = v2.second.longValue();
                v2_1 = v0.windowPositionUs == v7 ? 1 : 0;
                v15 = v9_1;
                v18 = v10;
            }
        }

        v9 = 2;
        try {
            if(v1.mediaSource == null) {
                goto label_84;
            }
            else if(v1.pendingPrepareCount > 0) {
                goto label_84;
            }
            else if(v12 == v7) {
                v1.setState(4);
                v1.resetInternal(false, true, false);
                goto label_85;
            }
            else if(v15.equals(v1.playbackInfo.periodId)) {
                MediaPeriodHolder v0_2 = v1.queue.getPlayingPeriod();
                v4 = v0_2 == null || v12 == v4 ? v12 : v0_2.mediaPeriod.getAdjustedSeekPositionUs(v12, v1.seekParameters);
                if(C.usToMs(v4) != C.usToMs(v1.playbackInfo.positionUs)) {
                    goto label_77;
                }

                v3_1 = v1.playbackInfo.positionUs;
            }
            else {
                goto label_76;
            }
        }
        catch(Throwable v0_1) {
            goto label_97;
        }

        v1.playbackInfo = v1.playbackInfo.fromNewPosition(v15, v3_1, v18);
        if(v2_1 != 0) {
            v1.playbackInfoUpdate.setPositionDiscontinuity(v9);
        }

        return;
    label_76:
        v4 = v12;
        try {
        label_77:
            v4 = v1.seekToPeriodPosition(v15, v4);
            if(v12 != v4) {
            }
            else {
                v3 = 0;
            }

            v2_1 |= v3;
            v16 = v4;
            goto label_86;
        label_84:
            v1.pendingInitialSeekPosition = v0;
        }
        catch(Throwable v0_1) {
        label_97:
            v1.playbackInfo = v1.playbackInfo.fromNewPosition(v15, v12, v18);
            if(v2_1 != 0) {
                v1.playbackInfoUpdate.setPositionDiscontinuity(v9);
            }

            throw v0_1;
        }

    label_85:
        v16 = v12;
    label_86:
        v1.playbackInfo = v1.playbackInfo.fromNewPosition(v15, v16, v18);
        if(v2_1 != 0) {
            v1.playbackInfoUpdate.setPositionDiscontinuity(v9);
        }
    }

    private long seekToPeriodPosition(MediaPeriodId arg3, long arg4) {
        boolean v0 = this.queue.getPlayingPeriod() != this.queue.getReadingPeriod() ? true : false;
        return this.seekToPeriodPosition(arg3, arg4, v0);
    }

    private long seekToPeriodPosition(MediaPeriodId arg6, long arg7, boolean arg9) {
        this.stopRenderers();
        this.rebuffering = false;
        int v1 = 2;
        this.setState(v1);
        MediaPeriodHolder v2 = this.queue.getPlayingPeriod();
        MediaPeriodHolder v3;
        for(v3 = v2; v3 != null; v3 = this.queue.advancePlayingPeriod()) {
            if((arg6.equals(v3.info.id)) && (v3.prepared)) {
                this.queue.removeAfter(v3);
                break;
            }
        }

        if(v2 != v3 || (arg9)) {
            Renderer[] v6 = this.enabledRenderers;
            int v9 = v6.length;
            int v2_1;
            for(v2_1 = 0; v2_1 < v9; ++v2_1) {
                this.disableRenderer(v6[v2_1]);
            }

            this.enabledRenderers = new Renderer[0];
            v2 = null;
        }

        if(v3 != null) {
            this.updatePlayingPeriodRenderers(v2);
            if(v3.hasEnabledTracks) {
                arg7 = v3.mediaPeriod.seekToUs(arg7);
                v3.mediaPeriod.discardBuffer(arg7 - this.backBufferDurationUs, this.retainBackBufferFromKeyframe);
            }

            this.resetRendererPosition(arg7);
            this.maybeContinueLoading();
        }
        else {
            this.queue.clear(true);
            this.resetRendererPosition(arg7);
        }

        this.updateLoadingMediaPeriodId();
        this.handler.sendEmptyMessage(v1);
        return arg7;
    }

    public void sendMessage(PlayerMessage arg3) {
        __monitor_enter(this);
        try {
            if(!this.released) {
                goto label_10;
            }

            Log.w("ExoPlayerImplInternal", "Ignoring messages sent after release.");
            arg3.markAsProcessed(false);
        }
        catch(Throwable v3) {
            goto label_17;
        }

        __monitor_exit(this);
        return;
        try {
        label_10:
            this.handler.obtainMessage(14, arg3).sendToTarget();
        }
        catch(Throwable v3) {
        label_17:
            __monitor_exit(this);
            throw v3;
        }

        __monitor_exit(this);
    }

    private void sendMessageInternal(PlayerMessage arg6) {
        if(arg6.getPositionMs() == -9223372036854775807L) {
            this.sendMessageToTarget(arg6);
        }
        else {
            if(this.mediaSource != null) {
                if(this.pendingPrepareCount > 0) {
                }
                else {
                    PendingMessageInfo v0 = new PendingMessageInfo(arg6);
                    if(this.resolvePendingMessagePosition(v0)) {
                        this.pendingMessages.add(v0);
                        Collections.sort(this.pendingMessages);
                    }
                    else {
                        arg6.markAsProcessed(false);
                    }

                    return;
                }
            }

            this.pendingMessages.add(new PendingMessageInfo(arg6));
        }
    }

    private void sendMessageToTarget(PlayerMessage arg3) {
        if(arg3.getHandler().getLooper() == this.handler.getLooper()) {
            this.deliverMessage(arg3);
            int v1 = 2;
            if(this.playbackInfo.playbackState != 3 && this.playbackInfo.playbackState != v1) {
                return;
            }

            this.handler.sendEmptyMessage(v1);
        }
        else {
            this.handler.obtainMessage(15, arg3).sendToTarget();
        }
    }

    private void sendMessageToTargetThread(PlayerMessage arg3) {
        arg3.getHandler().post(new Runnable(arg3) {
            public void run() {
                try {
                    ExoPlayerImplInternal.this.deliverMessage(this.val$message);
                    return;
                }
                catch(ExoPlaybackException v0) {
                    Log.e("ExoPlayerImplInternal", "Unexpected error delivering message on external thread.", ((Throwable)v0));
                    throw new RuntimeException(((Throwable)v0));
                }
            }
        });
    }

    private void setIsLoading(boolean arg2) {
        if(this.playbackInfo.isLoading != arg2) {
            this.playbackInfo = this.playbackInfo.copyWithIsLoading(arg2);
        }
    }

    public void setPlayWhenReady(boolean arg4) {
        this.handler.obtainMessage(1, ((int)arg4), 0).sendToTarget();
    }

    private void setPlayWhenReadyInternal(boolean arg3) {
        this.rebuffering = false;
        this.playWhenReady = arg3;
        if(!arg3) {
            this.stopRenderers();
            this.updatePlaybackPositions();
        }
        else {
            int v1 = 2;
            if(this.playbackInfo.playbackState == 3) {
                this.startRenderers();
            }
            else if(this.playbackInfo.playbackState == v1) {
            }
            else {
                return;
            }

            this.handler.sendEmptyMessage(v1);
        }
    }

    public void setPlaybackParameters(PlaybackParameters arg3) {
        this.handler.obtainMessage(4, arg3).sendToTarget();
    }

    private void setPlaybackParametersInternal(PlaybackParameters arg2) {
        this.mediaClock.setPlaybackParameters(arg2);
    }

    public void setRepeatMode(int arg4) {
        this.handler.obtainMessage(12, arg4, 0).sendToTarget();
    }

    private void setRepeatModeInternal(int arg2) {
        this.repeatMode = arg2;
        if(!this.queue.updateRepeatMode(arg2)) {
            this.seekToCurrentPosition(true);
        }

        this.updateLoadingMediaPeriodId();
    }

    public void setSeekParameters(SeekParameters arg3) {
        this.handler.obtainMessage(5, arg3).sendToTarget();
    }

    private void setSeekParametersInternal(SeekParameters arg1) {
        this.seekParameters = arg1;
    }

    public void setShuffleModeEnabled(boolean arg4) {
        this.handler.obtainMessage(13, ((int)arg4), 0).sendToTarget();
    }

    private void setShuffleModeEnabledInternal(boolean arg2) {
        this.shuffleModeEnabled = arg2;
        if(!this.queue.updateShuffleModeEnabled(arg2)) {
            this.seekToCurrentPosition(true);
        }

        this.updateLoadingMediaPeriodId();
    }

    private void setState(int arg2) {
        if(this.playbackInfo.playbackState != arg2) {
            this.playbackInfo = this.playbackInfo.copyWithPlaybackState(arg2);
        }
    }

    private boolean shouldTransitionToReadyState(boolean arg8) {
        if(this.enabledRenderers.length == 0) {
            return this.isTimelineReady();
        }

        boolean v0 = false;
        if(!arg8) {
            return 0;
        }

        if(!this.playbackInfo.isLoading) {
            return 1;
        }

        MediaPeriodHolder v8 = this.queue.getLoadingPeriod();
        long v2 = v8.getBufferedPositionUs(v8.info.isFinal ^ 1);
        if(v2 == -9223372036854775808L || (this.loadControl.shouldStartPlayback(v2 - v8.toPeriodTime(this.rendererPositionUs), this.mediaClock.getPlaybackParameters().speed, this.rebuffering))) {
            v0 = true;
        }

        return v0;
    }

    private void startRenderers() {
        int v0 = 0;
        this.rebuffering = false;
        this.mediaClock.start();
        Renderer[] v1 = this.enabledRenderers;
        int v2 = v1.length;
        while(v0 < v2) {
            v1[v0].start();
            ++v0;
        }
    }

    public void stop(boolean arg4) {
        this.handler.obtainMessage(6, ((int)arg4), 0).sendToTarget();
    }

    private void stopInternal(boolean arg3, boolean arg4) {
        this.resetInternal(true, arg3, arg3);
        this.playbackInfoUpdate.incrementPendingOperationAcks(this.pendingPrepareCount + (((int)arg4)));
        this.pendingPrepareCount = 0;
        this.loadControl.onStopped();
        this.setState(1);
    }

    private void stopRenderers() {
        this.mediaClock.stop();
        Renderer[] v0 = this.enabledRenderers;
        int v1 = v0.length;
        int v2;
        for(v2 = 0; v2 < v1; ++v2) {
            this.ensureStopped(v0[v2]);
        }
    }

    private void updateLoadControlTrackSelection(TrackGroupArray arg3, TrackSelectorResult arg4) {
        this.loadControl.onTracksSelected(this.renderers, arg3, arg4.selections);
    }

    private void updateLoadingMediaPeriodId() {
        MediaPeriodHolder v0 = this.queue.getLoadingPeriod();
        MediaPeriodId v0_1 = v0 == null ? this.playbackInfo.periodId : v0.info.id;
        this.playbackInfo = this.playbackInfo.copyWithLoadingMediaPeriodId(v0_1);
    }

    private void updatePeriods() {
        if(this.mediaSource == null) {
            return;
        }

        if(this.pendingPrepareCount > 0) {
            this.mediaSource.maybeThrowSourceInfoRefreshError();
            return;
        }

        this.maybeUpdateLoadingPeriod();
        MediaPeriodHolder v0 = this.queue.getLoadingPeriod();
        int v1 = 0;
        if(v0 == null || (v0.isFullyBuffered())) {
            this.setIsLoading(false);
        }
        else if(!this.playbackInfo.isLoading) {
            this.maybeContinueLoading();
        }

        if(!this.queue.hasPlayingPeriod()) {
            return;
        }

        v0 = this.queue.getPlayingPeriod();
        MediaPeriodHolder v2 = this.queue.getReadingPeriod();
        int v4;
        for(v4 = 0; this.playWhenReady; v4 = 1) {
            if(v0 == v2) {
                break;
            }

            if(this.rendererPositionUs < v0.next.rendererPositionOffsetUs) {
                break;
            }

            if(v4 != 0) {
                this.maybeNotifyPlaybackInfoChanged();
            }

            v4 = v0.info.isLastInTimelinePeriod ? 0 : 3;
            MediaPeriodHolder v5 = this.queue.advancePlayingPeriod();
            this.updatePlayingPeriodRenderers(v0);
            this.playbackInfo = this.playbackInfo.fromNewPosition(v5.info.id, v5.info.startPositionUs, v5.info.contentPositionUs);
            this.playbackInfoUpdate.setPositionDiscontinuity(v4);
            this.updatePlaybackPositions();
            v0 = v5;
        }

        if(!v2.info.isFinal) {
            if(v2.next != null) {
                if(!v2.next.prepared) {
                }
                else {
                    int v0_1 = 0;
                    while(true) {
                        if(v0_1 < this.renderers.length) {
                            Renderer v4_1 = this.renderers[v0_1];
                            SampleStream v5_1 = v2.sampleStreams[v0_1];
                            if(v4_1.getStream() == v5_1) {
                                if(v5_1 != null && !v4_1.hasReadStreamToEnd()) {
                                    return;
                                }

                                ++v0_1;
                                continue;
                            }

                            return;
                        }
                        else {
                            goto label_107;
                        }
                    }

                    return;
                label_107:
                    TrackSelectorResult v0_2 = v2.trackSelectorResult;
                    v2 = this.queue.advanceReadingPeriod();
                    TrackSelectorResult v4_2 = v2.trackSelectorResult;
                    int v5_2 = v2.mediaPeriod.readDiscontinuity() != -9223372036854775807L ? 1 : 0;
                    int v6;
                    for(v6 = 0; v6 < this.renderers.length; ++v6) {
                        Renderer v7 = this.renderers[v6];
                        if(!v0_2.isRendererEnabled(v6)) {
                        }
                        else {
                            if(v5_2 == 0) {
                                if(!v7.isCurrentStreamFinal()) {
                                    TrackSelection v8 = v4_2.selections.get(v6);
                                    boolean v9 = v4_2.isRendererEnabled(v6);
                                    int v10 = this.rendererCapabilities[v6].getTrackType() == 5 ? 1 : 0;
                                    RendererConfiguration v11 = v0_2.rendererConfigurations[v6];
                                    RendererConfiguration v12 = v4_2.rendererConfigurations[v6];
                                    if(!v9) {
                                        goto label_128;
                                    }

                                    if(!v12.equals(v11)) {
                                        goto label_128;
                                    }

                                    if(v10 != 0) {
                                        goto label_128;
                                    }

                                    v7.replaceStream(ExoPlayerImplInternal.getFormats(v8), v2.sampleStreams[v6], v2.getRendererOffset());
                                }
                                else {
                                }

                                goto label_156;
                            }

                        label_128:
                            v7.setCurrentStreamFinal();
                        }

                    label_156:
                    }
                }
            }

            return;
        }

        while(v1 < this.renderers.length) {
            Renderer v0_3 = this.renderers[v1];
            SampleStream v3 = v2.sampleStreams[v1];
            if(v3 != null && v0_3.getStream() == v3 && (v0_3.hasReadStreamToEnd())) {
                v0_3.setCurrentStreamFinal();
            }

            ++v1;
        }
    }

    private void updatePlaybackPositions() {
        if(!this.queue.hasPlayingPeriod()) {
            return;
        }

        MediaPeriodHolder v0 = this.queue.getPlayingPeriod();
        long v4 = v0.mediaPeriod.readDiscontinuity();
        if(v4 != -9223372036854775807L) {
            this.resetRendererPosition(v4);
            if(v4 != this.playbackInfo.positionUs) {
                this.playbackInfo = this.playbackInfo.fromNewPosition(this.playbackInfo.periodId, v4, this.playbackInfo.contentPositionUs);
                this.playbackInfoUpdate.setPositionDiscontinuity(4);
            }
        }
        else {
            this.rendererPositionUs = this.mediaClock.syncAndGetPositionUs();
            long v0_1 = v0.toPeriodTime(this.rendererPositionUs);
            this.maybeTriggerPendingMessages(this.playbackInfo.positionUs, v0_1);
            this.playbackInfo.positionUs = v0_1;
        }

        v0 = this.queue.getLoadingPeriod();
        this.playbackInfo.bufferedPositionUs = v0.getBufferedPositionUs(true);
        this.playbackInfo.totalBufferedDurationUs = this.playbackInfo.bufferedPositionUs - v0.toPeriodTime(this.rendererPositionUs);
    }

    private void updatePlayingPeriodRenderers(MediaPeriodHolder arg9) {
        MediaPeriodHolder v0 = this.queue.getPlayingPeriod();
        if(v0 != null) {
            if(arg9 == v0) {
            }
            else {
                boolean[] v1 = new boolean[this.renderers.length];
                int v3 = 0;
                int v4 = 0;
                while(v3 < this.renderers.length) {
                    Renderer v5 = this.renderers[v3];
                    boolean v6 = v5.getState() != 0 ? true : false;
                    v1[v3] = v6;
                    if(v0.trackSelectorResult.isRendererEnabled(v3)) {
                        ++v4;
                    }

                    if(v1[v3]) {
                        if(v0.trackSelectorResult.isRendererEnabled(v3)) {
                            if(!v5.isCurrentStreamFinal()) {
                            }
                            else if(v5.getStream() == arg9.sampleStreams[v3]) {
                                goto label_37;
                            }

                            goto label_38;
                        }

                    label_37:
                        this.disableRenderer(v5);
                    }

                label_38:
                    ++v3;
                }

                this.playbackInfo = this.playbackInfo.copyWithTrackInfo(v0.trackGroups, v0.trackSelectorResult);
                this.enableRenderers(v1, v4);
            }
        }
    }

    private void updateTrackSelectionPlaybackSpeed(float arg6) {
        MediaPeriodHolder v0;
        for(v0 = this.queue.getFrontPeriod(); v0 != null; v0 = v0.next) {
            if(v0.trackSelectorResult != null) {
                TrackSelection[] v1 = v0.trackSelectorResult.selections.getAll();
                int v2 = v1.length;
                int v3;
                for(v3 = 0; v3 < v2; ++v3) {
                    TrackSelection v4 = v1[v3];
                    if(v4 != null) {
                        v4.onPlaybackSpeed(arg6);
                    }
                }
            }
        }
    }
}

