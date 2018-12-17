package com.google.android.exoplayer2;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.util.Pair;
import com.google.android.exoplayer2.source.MediaSource$MediaPeriodId;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectorResult;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Clock;
import com.google.android.exoplayer2.util.Util;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

final class ExoPlayerImpl implements ExoPlayer {
    final class PlaybackInfoUpdate {
        private final boolean isLoadingChanged;
        private final Set listeners;
        private final boolean playWhenReady;
        private final PlaybackInfo playbackInfo;
        private final boolean playbackStateOrPlayWhenReadyChanged;
        private final boolean positionDiscontinuity;
        private final int positionDiscontinuityReason;
        private final boolean seekProcessed;
        private final int timelineChangeReason;
        private final boolean timelineOrManifestChanged;
        private final TrackSelector trackSelector;
        private final boolean trackSelectorResultChanged;

        public PlaybackInfoUpdate(PlaybackInfo arg1, PlaybackInfo arg2, Set arg3, TrackSelector arg4, boolean arg5, int arg6, int arg7, boolean arg8, boolean arg9, boolean arg10) {
            super();
            this.playbackInfo = arg1;
            this.listeners = arg3;
            this.trackSelector = arg4;
            this.positionDiscontinuity = arg5;
            this.positionDiscontinuityReason = arg6;
            this.timelineChangeReason = arg7;
            this.seekProcessed = arg8;
            this.playWhenReady = arg9;
            boolean v3 = false;
            arg5 = (arg10) || arg2.playbackState != arg1.playbackState ? true : false;
            this.playbackStateOrPlayWhenReadyChanged = arg5;
            arg5 = arg2.timeline != arg1.timeline || arg2.manifest != arg1.manifest ? true : false;
            this.timelineOrManifestChanged = arg5;
            arg5 = arg2.isLoading != arg1.isLoading ? true : false;
            this.isLoadingChanged = arg5;
            if(arg2.trackSelectorResult != arg1.trackSelectorResult) {
                v3 = true;
            }

            this.trackSelectorResultChanged = v3;
        }

        public void notifyListeners() {
            Iterator v0;
            if((this.timelineOrManifestChanged) || this.timelineChangeReason == 0) {
                v0 = this.listeners.iterator();
                while(v0.hasNext()) {
                    v0.next().onTimelineChanged(this.playbackInfo.timeline, this.playbackInfo.manifest, this.timelineChangeReason);
                }
            }

            if(this.positionDiscontinuity) {
                v0 = this.listeners.iterator();
                while(v0.hasNext()) {
                    v0.next().onPositionDiscontinuity(this.positionDiscontinuityReason);
                }
            }

            if(this.trackSelectorResultChanged) {
                this.trackSelector.onSelectionActivated(this.playbackInfo.trackSelectorResult.info);
                v0 = this.listeners.iterator();
                while(v0.hasNext()) {
                    v0.next().onTracksChanged(this.playbackInfo.trackGroups, this.playbackInfo.trackSelectorResult.selections);
                }
            }

            if(this.isLoadingChanged) {
                v0 = this.listeners.iterator();
                while(v0.hasNext()) {
                    v0.next().onLoadingChanged(this.playbackInfo.isLoading);
                }
            }

            if(this.playbackStateOrPlayWhenReadyChanged) {
                v0 = this.listeners.iterator();
                while(v0.hasNext()) {
                    v0.next().onPlayerStateChanged(this.playWhenReady, this.playbackInfo.playbackState);
                }
            }

            if(this.seekProcessed) {
                v0 = this.listeners.iterator();
                while(v0.hasNext()) {
                    v0.next().onSeekProcessed();
                }
            }
        }
    }

    private static final String TAG = "ExoPlayerImpl";
    private final TrackSelectorResult emptyTrackSelectorResult;
    private final Handler eventHandler;
    private boolean hasPendingPrepare;
    private boolean hasPendingSeek;
    private final ExoPlayerImplInternal internalPlayer;
    private final Handler internalPlayerHandler;
    private final CopyOnWriteArraySet listeners;
    private int maskingPeriodIndex;
    private int maskingWindowIndex;
    private long maskingWindowPositionMs;
    private int pendingOperationAcks;
    private final ArrayDeque pendingPlaybackInfoUpdates;
    private final Period period;
    private boolean playWhenReady;
    private ExoPlaybackException playbackError;
    private PlaybackInfo playbackInfo;
    private PlaybackParameters playbackParameters;
    private final Renderer[] renderers;
    private int repeatMode;
    private SeekParameters seekParameters;
    private boolean shuffleModeEnabled;
    private final TrackSelector trackSelector;
    private final Window window;

    @SuppressLint(value={"HandlerLeak"}) public ExoPlayerImpl(Renderer[] arg15, TrackSelector arg16, LoadControl arg17, BandwidthMeter arg18, Clock arg19, Looper arg20) {
        ExoPlayerImpl v12 = this;
        Renderer[] v1 = arg15;
        super();
        Log.i("ExoPlayerImpl", "Init " + Integer.toHexString(System.identityHashCode(this)) + " [" + "ExoPlayerLib/2.8.3" + "] [" + Util.DEVICE_DEBUG_INFO + "]");
        boolean v0 = v1.length > 0 ? true : false;
        Assertions.checkState(v0);
        v12.renderers = Assertions.checkNotNull(arg15);
        v12.trackSelector = Assertions.checkNotNull(arg16);
        v12.playWhenReady = false;
        v12.repeatMode = 0;
        v12.shuffleModeEnabled = false;
        v12.listeners = new CopyOnWriteArraySet();
        v12.emptyTrackSelectorResult = new TrackSelectorResult(new RendererConfiguration[v1.length], new TrackSelection[v1.length], null);
        v12.window = new Window();
        v12.period = new Period();
        v12.playbackParameters = PlaybackParameters.DEFAULT;
        v12.seekParameters = SeekParameters.DEFAULT;
        v12.eventHandler = new Handler(arg20) {
            public void handleMessage(Message arg2) {
                ExoPlayerImpl.this.handleEvent(arg2);
            }
        };
        v12.playbackInfo = PlaybackInfo.createDummy(0, v12.emptyTrackSelectorResult);
        v12.pendingPlaybackInfoUpdates = new ArrayDeque();
        v12.internalPlayer = new ExoPlayerImplInternal(arg15, arg16, v12.emptyTrackSelectorResult, arg17, arg18, v12.playWhenReady, v12.repeatMode, v12.shuffleModeEnabled, v12.eventHandler, this, arg19);
        v12.internalPlayerHandler = new Handler(v12.internalPlayer.getPlaybackLooper());
    }

    public void addListener(EventListener arg2) {
        this.listeners.add(arg2);
    }

    public void blockingSendMessages(ExoPlayerMessage[] arg8) {
        ArrayList v0 = new ArrayList();
        int v1 = arg8.length;
        int v3;
        for(v3 = 0; v3 < v1; ++v3) {
            ((List)v0).add(this.createMessage(arg8[v3].target).setType(arg8[v3].messageType).setPayload(arg8[v3].message).send());
        }

        Iterator v8 = ((List)v0).iterator();
        int v0_1;
        for(v0_1 = 0; v8.hasNext(); v0_1 = v4) {
            Object v1_1 = v8.next();
            int v4 = v0_1;
            v0_1 = 1;
            while(v0_1 != 0) {
                try {
                    ((PlayerMessage)v1_1).blockUntilDelivered();
                    v0_1 = 0;
                }
                catch(InterruptedException ) {
                    v4 = 1;
                }
            }
        }

        if(v0_1 != 0) {
            Thread.currentThread().interrupt();
        }
    }

    public PlayerMessage createMessage(Target arg8) {
        return new PlayerMessage(this.internalPlayer, arg8, this.playbackInfo.timeline, this.getCurrentWindowIndex(), this.internalPlayerHandler);
    }

    public Looper getApplicationLooper() {
        return this.eventHandler.getLooper();
    }

    public AudioComponent getAudioComponent() {
        return null;
    }

    public int getBufferedPercentage() {
        long v0 = this.getBufferedPosition();
        long v2 = this.getDuration();
        long v4 = -9223372036854775807L;
        int v7 = 100;
        if(Long.compare(v0, v4) == 0 || v2 == v4) {
            v7 = 0;
        }
        else if(v2 == 0) {
        }
        else {
            v7 = Util.constrainValue(((int)(v0 * 100 / v2)), 0, v7);
        }

        return v7;
    }

    public long getBufferedPosition() {
        if(this.isPlayingAd()) {
            long v0 = this.playbackInfo.loadingMediaPeriodId.equals(this.playbackInfo.periodId) ? C.usToMs(this.playbackInfo.bufferedPositionUs) : this.getDuration();
            return v0;
        }

        return this.getContentBufferedPosition();
    }

    public long getContentBufferedPosition() {
        if(this.shouldMaskPosition()) {
            return this.maskingWindowPositionMs;
        }

        if(this.playbackInfo.loadingMediaPeriodId.windowSequenceNumber != this.playbackInfo.periodId.windowSequenceNumber) {
            return this.playbackInfo.timeline.getWindow(this.getCurrentWindowIndex(), this.window).getDurationMs();
        }

        long v0 = this.playbackInfo.bufferedPositionUs;
        if(this.playbackInfo.loadingMediaPeriodId.isAd()) {
            Period v0_1 = this.playbackInfo.timeline.getPeriod(this.playbackInfo.loadingMediaPeriodId.periodIndex, this.period);
            long v1 = v0_1.getAdGroupTimeUs(this.playbackInfo.loadingMediaPeriodId.adGroupIndex);
            v0 = v1 == -9223372036854775808L ? v0_1.durationUs : v1;
        }

        return this.periodPositionUsToWindowPositionMs(this.playbackInfo.loadingMediaPeriodId, v0);
    }

    public long getContentPosition() {
        if(this.isPlayingAd()) {
            this.playbackInfo.timeline.getPeriod(this.playbackInfo.periodId.periodIndex, this.period);
            return this.period.getPositionInWindowMs() + C.usToMs(this.playbackInfo.contentPositionUs);
        }

        return this.getCurrentPosition();
    }

    public int getCurrentAdGroupIndex() {
        int v0 = this.isPlayingAd() ? this.playbackInfo.periodId.adGroupIndex : -1;
        return v0;
    }

    public int getCurrentAdIndexInAdGroup() {
        int v0 = this.isPlayingAd() ? this.playbackInfo.periodId.adIndexInAdGroup : -1;
        return v0;
    }

    public Object getCurrentManifest() {
        return this.playbackInfo.manifest;
    }

    public int getCurrentPeriodIndex() {
        if(this.shouldMaskPosition()) {
            return this.maskingPeriodIndex;
        }

        return this.playbackInfo.periodId.periodIndex;
    }

    public long getCurrentPosition() {
        if(this.shouldMaskPosition()) {
            return this.maskingWindowPositionMs;
        }

        if(this.playbackInfo.periodId.isAd()) {
            return C.usToMs(this.playbackInfo.positionUs);
        }

        return this.periodPositionUsToWindowPositionMs(this.playbackInfo.periodId, this.playbackInfo.positionUs);
    }

    public Object getCurrentTag() {
        int v0 = this.getCurrentWindowIndex();
        Object v0_1 = v0 > this.playbackInfo.timeline.getWindowCount() ? null : this.playbackInfo.timeline.getWindow(v0, this.window, true).tag;
        return v0_1;
    }

    public Timeline getCurrentTimeline() {
        return this.playbackInfo.timeline;
    }

    public TrackGroupArray getCurrentTrackGroups() {
        return this.playbackInfo.trackGroups;
    }

    public TrackSelectionArray getCurrentTrackSelections() {
        return this.playbackInfo.trackSelectorResult.selections;
    }

    public int getCurrentWindowIndex() {
        if(this.shouldMaskPosition()) {
            return this.maskingWindowIndex;
        }

        return this.playbackInfo.timeline.getPeriod(this.playbackInfo.periodId.periodIndex, this.period).windowIndex;
    }

    public long getDuration() {
        Timeline v0 = this.playbackInfo.timeline;
        if(v0.isEmpty()) {
            return -9223372036854775807L;
        }

        if(this.isPlayingAd()) {
            MediaPeriodId v1 = this.playbackInfo.periodId;
            v0.getPeriod(v1.periodIndex, this.period);
            return C.usToMs(this.period.getAdDurationUs(v1.adGroupIndex, v1.adIndexInAdGroup));
        }

        return v0.getWindow(this.getCurrentWindowIndex(), this.window).getDurationMs();
    }

    public int getNextWindowIndex() {
        Timeline v0 = this.playbackInfo.timeline;
        int v0_1 = v0.isEmpty() ? -1 : v0.getNextWindowIndex(this.getCurrentWindowIndex(), this.repeatMode, this.shuffleModeEnabled);
        return v0_1;
    }

    public boolean getPlayWhenReady() {
        return this.playWhenReady;
    }

    public ExoPlaybackException getPlaybackError() {
        return this.playbackError;
    }

    public Looper getPlaybackLooper() {
        return this.internalPlayer.getPlaybackLooper();
    }

    public PlaybackParameters getPlaybackParameters() {
        return this.playbackParameters;
    }

    public int getPlaybackState() {
        return this.playbackInfo.playbackState;
    }

    public int getPreviousWindowIndex() {
        Timeline v0 = this.playbackInfo.timeline;
        int v0_1 = v0.isEmpty() ? -1 : v0.getPreviousWindowIndex(this.getCurrentWindowIndex(), this.repeatMode, this.shuffleModeEnabled);
        return v0_1;
    }

    public int getRendererCount() {
        return this.renderers.length;
    }

    public int getRendererType(int arg2) {
        return this.renderers[arg2].getTrackType();
    }

    public int getRepeatMode() {
        return this.repeatMode;
    }

    private PlaybackInfo getResetPlaybackInfo(boolean arg24, boolean arg25, int arg26) {
        long v2;
        ExoPlayerImpl v0 = this;
        if(arg24) {
            v0.maskingWindowIndex = 0;
            v0.maskingPeriodIndex = 0;
            v2 = 0;
        }
        else {
            v0.maskingWindowIndex = this.getCurrentWindowIndex();
            v0.maskingPeriodIndex = this.getCurrentPeriodIndex();
            v2 = this.getCurrentPosition();
        }

        v0.maskingWindowPositionMs = v2;
        PlaybackInfo v2_1 = null;
        Timeline v3 = arg25 ? Timeline.EMPTY : v0.playbackInfo.timeline;
        Timeline v5 = v3;
        Object v3_1 = arg25 ? null : v0.playbackInfo.manifest;
        Object v6 = v3_1;
        MediaPeriodId v7 = v0.playbackInfo.periodId;
        long v8 = v0.playbackInfo.startPositionUs;
        long v10 = v0.playbackInfo.contentPositionUs;
        TrackGroupArray v3_2 = arg25 ? TrackGroupArray.EMPTY : v0.playbackInfo.trackGroups;
        TrackGroupArray v14 = v3_2;
        TrackSelectorResult v1 = arg25 ? v0.emptyTrackSelectorResult : v0.playbackInfo.trackSelectorResult;
        TrackSelectorResult v15 = v1;
        super(v5, v6, v7, v8, v10, arg26, false, v14, v15, v0.playbackInfo.periodId, v0.playbackInfo.startPositionUs, 0, v0.playbackInfo.startPositionUs);
        return v2_1;
    }

    public SeekParameters getSeekParameters() {
        return this.seekParameters;
    }

    public boolean getShuffleModeEnabled() {
        return this.shuffleModeEnabled;
    }

    public TextComponent getTextComponent() {
        return null;
    }

    public long getTotalBufferedDuration() {
        return Math.max(0, C.usToMs(this.playbackInfo.totalBufferedDurationUs));
    }

    public VideoComponent getVideoComponent() {
        return null;
    }

    void handleEvent(Message arg5) {
        Iterator v0;
        Object v5;
        switch(arg5.what) {
            case 0: {
                Object v0_1 = arg5.obj;
                int v1 = arg5.arg1;
                boolean v2 = arg5.arg2 != -1 ? true : false;
                this.handlePlaybackInfo(((PlaybackInfo)v0_1), v1, v2, arg5.arg2);
                break;
            }
            case 1: {
                v5 = arg5.obj;
                if(this.playbackParameters.equals(v5)) {
                    return;
                }

                this.playbackParameters = ((PlaybackParameters)v5);
                v0 = this.listeners.iterator();
                while(v0.hasNext()) {
                    v0.next().onPlaybackParametersChanged(((PlaybackParameters)v5));
                }
            }
            case 2: {
                v5 = arg5.obj;
                this.playbackError = ((ExoPlaybackException)v5);
                v0 = this.listeners.iterator();
                while(v0.hasNext()) {
                    v0.next().onPlayerError(((ExoPlaybackException)v5));
                }
            }
            default: {
                throw new IllegalStateException();
            }
        }
    }

    private void handlePlaybackInfo(PlaybackInfo arg8, int arg9, boolean arg10, int arg11) {
        this.pendingOperationAcks -= arg9;
        if(this.pendingOperationAcks == 0) {
            if(arg8.startPositionUs == -9223372036854775807L) {
                arg8 = arg8.fromNewPosition(arg8.periodId, 0, arg8.contentPositionUs);
            }

            PlaybackInfo v1 = arg8;
            if((!this.playbackInfo.timeline.isEmpty() || (this.hasPendingPrepare)) && (v1.timeline.isEmpty())) {
                this.maskingPeriodIndex = 0;
                this.maskingWindowIndex = 0;
                this.maskingWindowPositionMs = 0;
            }

            int v4 = this.hasPendingPrepare ? 0 : 2;
            boolean v5 = this.hasPendingSeek;
            this.hasPendingPrepare = false;
            this.hasPendingSeek = false;
            this.updatePlaybackInfo(v1, arg10, arg11, v4, v5, false);
        }
    }

    public boolean isCurrentWindowDynamic() {
        Timeline v0 = this.playbackInfo.timeline;
        boolean v0_1 = (v0.isEmpty()) || !v0.getWindow(this.getCurrentWindowIndex(), this.window).isDynamic ? false : true;
        return v0_1;
    }

    public boolean isCurrentWindowSeekable() {
        Timeline v0 = this.playbackInfo.timeline;
        boolean v0_1 = (v0.isEmpty()) || !v0.getWindow(this.getCurrentWindowIndex(), this.window).isSeekable ? false : true;
        return v0_1;
    }

    public boolean isLoading() {
        return this.playbackInfo.isLoading;
    }

    public boolean isPlayingAd() {
        boolean v0 = (this.shouldMaskPosition()) || !this.playbackInfo.periodId.isAd() ? false : true;
        return v0;
    }

    private long periodPositionUsToWindowPositionMs(MediaPeriodId arg3, long arg4) {
        arg4 = C.usToMs(arg4);
        this.playbackInfo.timeline.getPeriod(arg3.periodIndex, this.period);
        return arg4 + this.period.getPositionInWindowMs();
    }

    public void prepare(MediaSource arg2) {
        this.prepare(arg2, true, true);
    }

    public void prepare(MediaSource arg9, boolean arg10, boolean arg11) {
        this.playbackError = null;
        PlaybackInfo v2 = this.getResetPlaybackInfo(arg10, arg11, 2);
        this.hasPendingPrepare = true;
        ++this.pendingOperationAcks;
        this.internalPlayer.prepare(arg9, arg10, arg11);
        this.updatePlaybackInfo(v2, false, 4, 1, false, false);
    }

    public void release() {
        Log.i("ExoPlayerImpl", "Release " + Integer.toHexString(System.identityHashCode(this)) + " [" + "ExoPlayerLib/2.8.3" + "] [" + Util.DEVICE_DEBUG_INFO + "] [" + ExoPlayerLibraryInfo.registeredModules() + "]");
        this.internalPlayer.release();
        this.eventHandler.removeCallbacksAndMessages(null);
    }

    public void removeListener(EventListener arg2) {
        this.listeners.remove(arg2);
    }

    public void seekTo(int arg11, long arg12) {
        Timeline v0 = this.playbackInfo.timeline;
        if(arg11 >= 0 && ((v0.isEmpty()) || arg11 < v0.getWindowCount())) {
            this.hasPendingSeek = true;
            ++this.pendingOperationAcks;
            if(this.isPlayingAd()) {
                Log.w("ExoPlayerImpl", "seekTo ignored because an ad is playing");
                this.eventHandler.obtainMessage(0, 1, -1, this.playbackInfo).sendToTarget();
                return;
            }

            this.maskingWindowIndex = arg11;
            long v3 = -9223372036854775807L;
            if(v0.isEmpty()) {
                v3 = arg12 == v3 ? 0 : arg12;
                this.maskingWindowPositionMs = v3;
                this.maskingPeriodIndex = 0;
            }
            else {
                long v1 = arg12 == v3 ? v0.getWindow(arg11, this.window).getDefaultPositionUs() : C.msToUs(arg12);
                long v8 = v1;
                Pair v1_1 = v0.getPeriodPosition(this.window, this.period, arg11, v8);
                this.maskingWindowPositionMs = C.usToMs(v8);
                this.maskingPeriodIndex = v1_1.first.intValue();
            }

            this.internalPlayer.seekTo(v0, arg11, C.msToUs(arg12));
            Iterator v11 = this.listeners.iterator();
            while(v11.hasNext()) {
                v11.next().onPositionDiscontinuity(1);
            }

            return;
        }

        throw new IllegalSeekPositionException(v0, arg11, arg12);
    }

    public void seekTo(long arg2) {
        this.seekTo(this.getCurrentWindowIndex(), arg2);
    }

    public void seekToDefaultPosition() {
        this.seekToDefaultPosition(this.getCurrentWindowIndex());
    }

    public void seekToDefaultPosition(int arg3) {
        this.seekTo(arg3, -9223372036854775807L);
    }

    public void sendMessages(ExoPlayerMessage[] arg6) {
        int v0 = arg6.length;
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            this.createMessage(arg6[v1].target).setType(arg6[v1].messageType).setPayload(arg6[v1].message).send();
        }
    }

    public void setPlayWhenReady(boolean arg9) {
        if(this.playWhenReady != arg9) {
            this.playWhenReady = arg9;
            this.internalPlayer.setPlayWhenReady(arg9);
            this.updatePlaybackInfo(this.playbackInfo, false, 4, 1, false, true);
        }
    }

    public void setPlaybackParameters(PlaybackParameters arg2) {
        if(arg2 == null) {
            arg2 = PlaybackParameters.DEFAULT;
        }

        this.internalPlayer.setPlaybackParameters(arg2);
    }

    public void setRepeatMode(int arg3) {
        if(this.repeatMode != arg3) {
            this.repeatMode = arg3;
            this.internalPlayer.setRepeatMode(arg3);
            Iterator v0 = this.listeners.iterator();
            while(v0.hasNext()) {
                v0.next().onRepeatModeChanged(arg3);
            }
        }
    }

    public void setSeekParameters(SeekParameters arg2) {
        if(arg2 == null) {
            arg2 = SeekParameters.DEFAULT;
        }

        if(!this.seekParameters.equals(arg2)) {
            this.seekParameters = arg2;
            this.internalPlayer.setSeekParameters(arg2);
        }
    }

    public void setShuffleModeEnabled(boolean arg3) {
        if(this.shuffleModeEnabled != arg3) {
            this.shuffleModeEnabled = arg3;
            this.internalPlayer.setShuffleModeEnabled(arg3);
            Iterator v0 = this.listeners.iterator();
            while(v0.hasNext()) {
                v0.next().onShuffleModeEnabledChanged(arg3);
            }
        }
    }

    private boolean shouldMaskPosition() {
        boolean v0 = (this.playbackInfo.timeline.isEmpty()) || this.pendingOperationAcks > 0 ? true : false;
        return v0;
    }

    public void stop() {
        this.stop(false);
    }

    public void stop(boolean arg9) {
        if(arg9) {
            this.playbackError = null;
        }

        PlaybackInfo v2 = this.getResetPlaybackInfo(arg9, arg9, 1);
        ++this.pendingOperationAcks;
        this.internalPlayer.stop(arg9);
        this.updatePlaybackInfo(v2, false, 4, 1, false, false);
    }

    private void updatePlaybackInfo(PlaybackInfo arg16, boolean arg17, int arg18, int arg19, boolean arg20, boolean arg21) {
        ExoPlayerImpl v0 = this;
        int v1 = v0.pendingPlaybackInfoUpdates.isEmpty() ^ 1;
        v0.pendingPlaybackInfoUpdates.addLast(new PlaybackInfoUpdate(arg16, v0.playbackInfo, v0.listeners, v0.trackSelector, arg17, arg18, arg19, arg20, v0.playWhenReady, arg21));
        v0.playbackInfo = arg16;
        if(v1 != 0) {
            return;
        }

        while(!v0.pendingPlaybackInfoUpdates.isEmpty()) {
            v0.pendingPlaybackInfoUpdates.peekFirst().notifyListeners();
            v0.pendingPlaybackInfoUpdates.removeFirst();
        }
    }
}

