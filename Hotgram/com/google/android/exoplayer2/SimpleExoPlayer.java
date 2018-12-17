package com.google.android.exoplayer2;

import android.annotation.TargetApi;
import android.graphics.Rect;
import android.graphics.SurfaceTexture;
import android.media.PlaybackParams;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder$Callback;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.TextureView$SurfaceTextureListener;
import android.view.TextureView;
import com.google.android.exoplayer2.analytics.AnalyticsCollector$Factory;
import com.google.android.exoplayer2.analytics.AnalyticsCollector;
import com.google.android.exoplayer2.analytics.AnalyticsListener;
import com.google.android.exoplayer2.audio.AudioAttributes$Builder;
import com.google.android.exoplayer2.audio.AudioAttributes;
import com.google.android.exoplayer2.audio.AudioListener;
import com.google.android.exoplayer2.audio.AudioRendererEventListener;
import com.google.android.exoplayer2.decoder.DecoderCounters;
import com.google.android.exoplayer2.drm.DefaultDrmSessionManager;
import com.google.android.exoplayer2.drm.DrmSessionManager;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.metadata.MetadataOutput;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.text.TextOutput;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.util.Clock;
import com.google.android.exoplayer2.util.Util;
import com.google.android.exoplayer2.video.VideoListener;
import com.google.android.exoplayer2.video.VideoRendererEventListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;

@TargetApi(value=16) public class SimpleExoPlayer implements ExoPlayer, AudioComponent, TextComponent, VideoComponent {
    class com.google.android.exoplayer2.SimpleExoPlayer$1 {
    }

    final class ComponentListener implements SurfaceHolder$Callback, TextureView$SurfaceTextureListener, AudioRendererEventListener, MetadataOutput, TextOutput, VideoRendererEventListener {
        ComponentListener(SimpleExoPlayer arg1, com.google.android.exoplayer2.SimpleExoPlayer$1 arg2) {
            this(arg1);
        }

        private ComponentListener(SimpleExoPlayer arg1) {
            SimpleExoPlayer.this = arg1;
            super();
        }

        public void onAudioDecoderInitialized(String arg9, long arg10, long arg12) {
            Iterator v0 = SimpleExoPlayer.this.audioDebugListeners.iterator();
            while(v0.hasNext()) {
                v0.next().onAudioDecoderInitialized(arg9, arg10, arg12);
            }
        }

        public void onAudioDisabled(DecoderCounters arg3) {
            Iterator v0 = SimpleExoPlayer.this.audioDebugListeners.iterator();
            while(v0.hasNext()) {
                v0.next().onAudioDisabled(arg3);
            }

            SimpleExoPlayer.this.audioFormat = null;
            SimpleExoPlayer.this.audioDecoderCounters = null;
            SimpleExoPlayer.this.audioSessionId = 0;
        }

        public void onAudioEnabled(DecoderCounters arg3) {
            SimpleExoPlayer.this.audioDecoderCounters = arg3;
            Iterator v0 = SimpleExoPlayer.this.audioDebugListeners.iterator();
            while(v0.hasNext()) {
                v0.next().onAudioEnabled(arg3);
            }
        }

        public void onAudioInputFormatChanged(Format arg3) {
            SimpleExoPlayer.this.audioFormat = arg3;
            Iterator v0 = SimpleExoPlayer.this.audioDebugListeners.iterator();
            while(v0.hasNext()) {
                v0.next().onAudioInputFormatChanged(arg3);
            }
        }

        public void onAudioSessionId(int arg4) {
            if(SimpleExoPlayer.this.audioSessionId == arg4) {
                return;
            }

            SimpleExoPlayer.this.audioSessionId = arg4;
            Iterator v0 = SimpleExoPlayer.this.audioListeners.iterator();
            while(v0.hasNext()) {
                Object v1 = v0.next();
                if(SimpleExoPlayer.this.audioDebugListeners.contains(v1)) {
                    continue;
                }

                ((AudioListener)v1).onAudioSessionId(arg4);
            }

            v0 = SimpleExoPlayer.this.audioDebugListeners.iterator();
            while(v0.hasNext()) {
                v0.next().onAudioSessionId(arg4);
            }
        }

        public void onAudioSinkUnderrun(int arg9, long arg10, long arg12) {
            Iterator v0 = SimpleExoPlayer.this.audioDebugListeners.iterator();
            while(v0.hasNext()) {
                v0.next().onAudioSinkUnderrun(arg9, arg10, arg12);
            }
        }

        public void onCues(List arg3) {
            SimpleExoPlayer.this.currentCues = arg3;
            Iterator v0 = SimpleExoPlayer.this.textOutputs.iterator();
            while(v0.hasNext()) {
                v0.next().onCues(arg3);
            }
        }

        public void onDroppedFrames(int arg3, long arg4) {
            Iterator v0 = SimpleExoPlayer.this.videoDebugListeners.iterator();
            while(v0.hasNext()) {
                v0.next().onDroppedFrames(arg3, arg4);
            }
        }

        public void onMetadata(Metadata arg3) {
            Iterator v0 = SimpleExoPlayer.this.metadataOutputs.iterator();
            while(v0.hasNext()) {
                v0.next().onMetadata(arg3);
            }
        }

        public void onRenderedFirstFrame(Surface arg3) {
            Iterator v0;
            if(SimpleExoPlayer.this.surface == arg3) {
                v0 = SimpleExoPlayer.this.videoListeners.iterator();
                while(v0.hasNext()) {
                    v0.next().onRenderedFirstFrame();
                }
            }

            v0 = SimpleExoPlayer.this.videoDebugListeners.iterator();
            while(v0.hasNext()) {
                v0.next().onRenderedFirstFrame(arg3);
            }
        }

        public void onSurfaceTextureAvailable(SurfaceTexture arg3, int arg4, int arg5) {
            if(SimpleExoPlayer.this.needSetSurface) {
                SimpleExoPlayer.this.setVideoSurfaceInternal(new Surface(arg3), true);
                SimpleExoPlayer.this.needSetSurface = false;
            }

            SimpleExoPlayer.this.maybeNotifySurfaceSizeChanged(arg4, arg5);
        }

        public boolean onSurfaceTextureDestroyed(SurfaceTexture arg4) {
            Iterator v0 = SimpleExoPlayer.this.videoListeners.iterator();
            do {
                if(!v0.hasNext()) {
                    goto label_10;
                }
            }
            while(!v0.next().onSurfaceDestroyed(arg4));

            return 0;
        label_10:
            SimpleExoPlayer.this.setVideoSurfaceInternal(null, true);
            SimpleExoPlayer.this.maybeNotifySurfaceSizeChanged(0, 0);
            SimpleExoPlayer.this.needSetSurface = true;
            return 1;
        }

        public void onSurfaceTextureSizeChanged(SurfaceTexture arg1, int arg2, int arg3) {
            SimpleExoPlayer.this.maybeNotifySurfaceSizeChanged(arg2, arg3);
        }

        public void onSurfaceTextureUpdated(SurfaceTexture arg3) {
            Iterator v0 = SimpleExoPlayer.this.videoListeners.iterator();
            while(v0.hasNext()) {
                v0.next().onSurfaceTextureUpdated(arg3);
            }
        }

        public void onVideoDecoderInitialized(String arg9, long arg10, long arg12) {
            Iterator v0 = SimpleExoPlayer.this.videoDebugListeners.iterator();
            while(v0.hasNext()) {
                v0.next().onVideoDecoderInitialized(arg9, arg10, arg12);
            }
        }

        public void onVideoDisabled(DecoderCounters arg3) {
            Iterator v0 = SimpleExoPlayer.this.videoDebugListeners.iterator();
            while(v0.hasNext()) {
                v0.next().onVideoDisabled(arg3);
            }

            SimpleExoPlayer.this.videoFormat = null;
            SimpleExoPlayer.this.videoDecoderCounters = null;
        }

        public void onVideoEnabled(DecoderCounters arg3) {
            SimpleExoPlayer.this.videoDecoderCounters = arg3;
            Iterator v0 = SimpleExoPlayer.this.videoDebugListeners.iterator();
            while(v0.hasNext()) {
                v0.next().onVideoEnabled(arg3);
            }
        }

        public void onVideoInputFormatChanged(Format arg3) {
            SimpleExoPlayer.this.videoFormat = arg3;
            Iterator v0 = SimpleExoPlayer.this.videoDebugListeners.iterator();
            while(v0.hasNext()) {
                v0.next().onVideoInputFormatChanged(arg3);
            }
        }

        public void onVideoSizeChanged(int arg4, int arg5, int arg6, float arg7) {
            Iterator v0 = SimpleExoPlayer.this.videoListeners.iterator();
            while(v0.hasNext()) {
                Object v1 = v0.next();
                if(SimpleExoPlayer.this.videoDebugListeners.contains(v1)) {
                    continue;
                }

                ((VideoListener)v1).onVideoSizeChanged(arg4, arg5, arg6, arg7);
            }

            v0 = SimpleExoPlayer.this.videoDebugListeners.iterator();
            while(v0.hasNext()) {
                v0.next().onVideoSizeChanged(arg4, arg5, arg6, arg7);
            }
        }

        public void surfaceChanged(SurfaceHolder arg1, int arg2, int arg3, int arg4) {
            SimpleExoPlayer.this.maybeNotifySurfaceSizeChanged(arg3, arg4);
        }

        public void surfaceCreated(SurfaceHolder arg3) {
            SimpleExoPlayer.this.setVideoSurfaceInternal(arg3.getSurface(), false);
        }

        public void surfaceDestroyed(SurfaceHolder arg3) {
            SimpleExoPlayer.this.setVideoSurfaceInternal(null, false);
            SimpleExoPlayer.this.maybeNotifySurfaceSizeChanged(0, 0);
        }
    }

    @Deprecated public interface com.google.android.exoplayer2.SimpleExoPlayer$VideoListener extends VideoListener {
    }

    private static final String TAG = "SimpleExoPlayer";
    private final AnalyticsCollector analyticsCollector;
    private AudioAttributes audioAttributes;
    private final CopyOnWriteArraySet audioDebugListeners;
    private DecoderCounters audioDecoderCounters;
    private Format audioFormat;
    private final CopyOnWriteArraySet audioListeners;
    private int audioSessionId;
    private float audioVolume;
    private final BandwidthMeter bandwidthMeter;
    private final ComponentListener componentListener;
    private List currentCues;
    private final Handler eventHandler;
    private MediaSource mediaSource;
    private final CopyOnWriteArraySet metadataOutputs;
    private boolean needSetSurface;
    private boolean ownsSurface;
    private final ExoPlayer player;
    protected final Renderer[] renderers;
    private Surface surface;
    private int surfaceHeight;
    private SurfaceHolder surfaceHolder;
    private int surfaceWidth;
    private final CopyOnWriteArraySet textOutputs;
    private TextureView textureView;
    private final CopyOnWriteArraySet videoDebugListeners;
    private DecoderCounters videoDecoderCounters;
    private Format videoFormat;
    private final CopyOnWriteArraySet videoListeners;
    private int videoScalingMode;

    protected SimpleExoPlayer(RenderersFactory arg10, TrackSelector arg11, LoadControl arg12, DrmSessionManager arg13, BandwidthMeter arg14, Factory arg15, Looper arg16) {
        this(arg10, arg11, arg12, arg13, arg14, arg15, Clock.DEFAULT, arg16);
    }

    protected SimpleExoPlayer(RenderersFactory arg13, TrackSelector arg14, LoadControl arg15, DrmSessionManager arg16, BandwidthMeter arg17, Factory arg18, Clock arg19, Looper arg20) {
        SimpleExoPlayer v7 = this;
        DrmSessionManager v8 = arg16;
        super();
        v7.needSetSurface = true;
        v7.bandwidthMeter = arg17;
        v7.componentListener = new ComponentListener(this, null);
        v7.videoListeners = new CopyOnWriteArraySet();
        v7.audioListeners = new CopyOnWriteArraySet();
        v7.textOutputs = new CopyOnWriteArraySet();
        v7.metadataOutputs = new CopyOnWriteArraySet();
        v7.videoDebugListeners = new CopyOnWriteArraySet();
        v7.audioDebugListeners = new CopyOnWriteArraySet();
        v7.eventHandler = new Handler(arg20);
        v7.renderers = arg13.createRenderers(v7.eventHandler, v7.componentListener, v7.componentListener, v7.componentListener, v7.componentListener, arg16);
        v7.audioVolume = 1f;
        v7.audioSessionId = 0;
        v7.audioAttributes = AudioAttributes.DEFAULT;
        v7.videoScalingMode = 1;
        v7.currentCues = Collections.emptyList();
        v7.player = this.createExoPlayerImpl(v7.renderers, arg14, arg15, arg17, arg19, arg20);
        v7.analyticsCollector = arg18.createAnalyticsCollector(v7.player, arg19);
        this.addListener(v7.analyticsCollector);
        v7.videoDebugListeners.add(v7.analyticsCollector);
        v7.videoListeners.add(v7.analyticsCollector);
        v7.audioDebugListeners.add(v7.analyticsCollector);
        v7.audioListeners.add(v7.analyticsCollector);
        this.addMetadataOutput(v7.analyticsCollector);
        arg17.addEventListener(v7.eventHandler, v7.analyticsCollector);
        if((v8 instanceof DefaultDrmSessionManager)) {
            v8.addListener(v7.eventHandler, v7.analyticsCollector);
        }
    }

    protected SimpleExoPlayer(RenderersFactory arg9, TrackSelector arg10, LoadControl arg11, BandwidthMeter arg12, DrmSessionManager arg13, Looper arg14) {
        this(arg9, arg10, arg11, arg13, arg12, new Factory(), arg14);
    }

    static Format access$1002(SimpleExoPlayer arg0, Format arg1) {
        arg0.audioFormat = arg1;
        return arg1;
    }

    static DecoderCounters access$102(SimpleExoPlayer arg0, DecoderCounters arg1) {
        arg0.videoDecoderCounters = arg1;
        return arg1;
    }

    static List access$1102(SimpleExoPlayer arg0, List arg1) {
        arg0.currentCues = arg1;
        return arg1;
    }

    static CopyOnWriteArraySet access$1200(SimpleExoPlayer arg0) {
        return arg0.textOutputs;
    }

    static CopyOnWriteArraySet access$1300(SimpleExoPlayer arg0) {
        return arg0.metadataOutputs;
    }

    static void access$1400(SimpleExoPlayer arg0, Surface arg1, boolean arg2) {
        arg0.setVideoSurfaceInternal(arg1, arg2);
    }

    static void access$1500(SimpleExoPlayer arg0, int arg1, int arg2) {
        arg0.maybeNotifySurfaceSizeChanged(arg1, arg2);
    }

    static boolean access$1600(SimpleExoPlayer arg0) {
        return arg0.needSetSurface;
    }

    static boolean access$1602(SimpleExoPlayer arg0, boolean arg1) {
        arg0.needSetSurface = arg1;
        return arg1;
    }

    static CopyOnWriteArraySet access$200(SimpleExoPlayer arg0) {
        return arg0.videoDebugListeners;
    }

    static Format access$302(SimpleExoPlayer arg0, Format arg1) {
        arg0.videoFormat = arg1;
        return arg1;
    }

    static CopyOnWriteArraySet access$400(SimpleExoPlayer arg0) {
        return arg0.videoListeners;
    }

    static Surface access$500(SimpleExoPlayer arg0) {
        return arg0.surface;
    }

    static DecoderCounters access$602(SimpleExoPlayer arg0, DecoderCounters arg1) {
        arg0.audioDecoderCounters = arg1;
        return arg1;
    }

    static CopyOnWriteArraySet access$700(SimpleExoPlayer arg0) {
        return arg0.audioDebugListeners;
    }

    static int access$800(SimpleExoPlayer arg0) {
        return arg0.audioSessionId;
    }

    static int access$802(SimpleExoPlayer arg0, int arg1) {
        arg0.audioSessionId = arg1;
        return arg1;
    }

    static CopyOnWriteArraySet access$900(SimpleExoPlayer arg0) {
        return arg0.audioListeners;
    }

    public void addAnalyticsListener(AnalyticsListener arg2) {
        this.analyticsCollector.addListener(arg2);
    }

    @Deprecated public void addAudioDebugListener(AudioRendererEventListener arg2) {
        this.audioDebugListeners.add(arg2);
    }

    public void addAudioListener(AudioListener arg2) {
        this.audioListeners.add(arg2);
    }

    public void addListener(EventListener arg2) {
        this.player.addListener(arg2);
    }

    public void addMetadataOutput(MetadataOutput arg2) {
        this.metadataOutputs.add(arg2);
    }

    public void addTextOutput(TextOutput arg2) {
        if(!this.currentCues.isEmpty()) {
            arg2.onCues(this.currentCues);
        }

        this.textOutputs.add(arg2);
    }

    @Deprecated public void addVideoDebugListener(VideoRendererEventListener arg2) {
        this.videoDebugListeners.add(arg2);
    }

    public void addVideoListener(VideoListener arg2) {
        this.videoListeners.add(arg2);
    }

    public void blockingSendMessages(ExoPlayerMessage[] arg2) {
        this.player.blockingSendMessages(arg2);
    }

    @Deprecated public void clearMetadataOutput(MetadataOutput arg1) {
        this.removeMetadataOutput(arg1);
    }

    @Deprecated public void clearTextOutput(TextOutput arg1) {
        this.removeTextOutput(arg1);
    }

    @Deprecated public void clearVideoListener(com.google.android.exoplayer2.SimpleExoPlayer$VideoListener arg1) {
        this.removeVideoListener(((VideoListener)arg1));
    }

    public void clearVideoSurface() {
        this.setVideoSurface(null);
    }

    public void clearVideoSurface(Surface arg2) {
        if(arg2 != null && arg2 == this.surface) {
            this.setVideoSurface(null);
        }
    }

    public void clearVideoSurfaceHolder(SurfaceHolder arg2) {
        if(arg2 != null && arg2 == this.surfaceHolder) {
            this.setVideoSurfaceHolder(null);
        }
    }

    public void clearVideoSurfaceView(SurfaceView arg1) {
        SurfaceHolder v1 = arg1 == null ? null : arg1.getHolder();
        this.clearVideoSurfaceHolder(v1);
    }

    public void clearVideoTextureView(TextureView arg2) {
        if(arg2 != null && arg2 == this.textureView) {
            this.setVideoTextureView(null);
        }
    }

    protected ExoPlayer createExoPlayerImpl(Renderer[] arg9, TrackSelector arg10, LoadControl arg11, BandwidthMeter arg12, Clock arg13, Looper arg14) {
        return new ExoPlayerImpl(arg9, arg10, arg11, arg12, arg13, arg14);
    }

    public PlayerMessage createMessage(Target arg2) {
        return this.player.createMessage(arg2);
    }

    public AnalyticsCollector getAnalyticsCollector() {
        return this.analyticsCollector;
    }

    public Looper getApplicationLooper() {
        return this.player.getApplicationLooper();
    }

    public AudioAttributes getAudioAttributes() {
        return this.audioAttributes;
    }

    public AudioComponent getAudioComponent() {
        return this;
    }

    public DecoderCounters getAudioDecoderCounters() {
        return this.audioDecoderCounters;
    }

    public Format getAudioFormat() {
        return this.audioFormat;
    }

    public int getAudioSessionId() {
        return this.audioSessionId;
    }

    @Deprecated public int getAudioStreamType() {
        return Util.getStreamTypeForAudioUsage(this.audioAttributes.usage);
    }

    public int getBufferedPercentage() {
        return this.player.getBufferedPercentage();
    }

    public long getBufferedPosition() {
        return this.player.getBufferedPosition();
    }

    public long getContentBufferedPosition() {
        return this.player.getContentBufferedPosition();
    }

    public long getContentPosition() {
        return this.player.getContentPosition();
    }

    public int getCurrentAdGroupIndex() {
        return this.player.getCurrentAdGroupIndex();
    }

    public int getCurrentAdIndexInAdGroup() {
        return this.player.getCurrentAdIndexInAdGroup();
    }

    public Object getCurrentManifest() {
        return this.player.getCurrentManifest();
    }

    public int getCurrentPeriodIndex() {
        return this.player.getCurrentPeriodIndex();
    }

    public long getCurrentPosition() {
        return this.player.getCurrentPosition();
    }

    public Object getCurrentTag() {
        return this.player.getCurrentTag();
    }

    public Timeline getCurrentTimeline() {
        return this.player.getCurrentTimeline();
    }

    public TrackGroupArray getCurrentTrackGroups() {
        return this.player.getCurrentTrackGroups();
    }

    public TrackSelectionArray getCurrentTrackSelections() {
        return this.player.getCurrentTrackSelections();
    }

    public int getCurrentWindowIndex() {
        return this.player.getCurrentWindowIndex();
    }

    public long getDuration() {
        return this.player.getDuration();
    }

    public int getNextWindowIndex() {
        return this.player.getNextWindowIndex();
    }

    public boolean getPlayWhenReady() {
        return this.player.getPlayWhenReady();
    }

    public ExoPlaybackException getPlaybackError() {
        return this.player.getPlaybackError();
    }

    public Looper getPlaybackLooper() {
        return this.player.getPlaybackLooper();
    }

    public PlaybackParameters getPlaybackParameters() {
        return this.player.getPlaybackParameters();
    }

    public int getPlaybackState() {
        return this.player.getPlaybackState();
    }

    public int getPreviousWindowIndex() {
        return this.player.getPreviousWindowIndex();
    }

    public int getRendererCount() {
        return this.player.getRendererCount();
    }

    public int getRendererType(int arg2) {
        return this.player.getRendererType(arg2);
    }

    public int getRepeatMode() {
        return this.player.getRepeatMode();
    }

    public SeekParameters getSeekParameters() {
        return this.player.getSeekParameters();
    }

    public boolean getShuffleModeEnabled() {
        return this.player.getShuffleModeEnabled();
    }

    public TextComponent getTextComponent() {
        return this;
    }

    public long getTotalBufferedDuration() {
        return this.player.getTotalBufferedDuration();
    }

    public VideoComponent getVideoComponent() {
        return this;
    }

    public DecoderCounters getVideoDecoderCounters() {
        return this.videoDecoderCounters;
    }

    public Format getVideoFormat() {
        return this.videoFormat;
    }

    public int getVideoScalingMode() {
        return this.videoScalingMode;
    }

    public float getVolume() {
        return this.audioVolume;
    }

    public boolean isCurrentWindowDynamic() {
        return this.player.isCurrentWindowDynamic();
    }

    public boolean isCurrentWindowSeekable() {
        return this.player.isCurrentWindowSeekable();
    }

    public boolean isLoading() {
        return this.player.isLoading();
    }

    public boolean isPlayingAd() {
        return this.player.isPlayingAd();
    }

    private void maybeNotifySurfaceSizeChanged(int arg3, int arg4) {
        if(arg3 != this.surfaceWidth || arg4 != this.surfaceHeight) {
            this.surfaceWidth = arg3;
            this.surfaceHeight = arg4;
            Iterator v0 = this.videoListeners.iterator();
            while(v0.hasNext()) {
                v0.next().onSurfaceSizeChanged(arg3, arg4);
            }
        }
    }

    public void prepare(MediaSource arg2) {
        this.prepare(arg2, true, true);
    }

    public void prepare(MediaSource arg3, boolean arg4, boolean arg5) {
        if(this.mediaSource != arg3) {
            if(this.mediaSource != null) {
                this.mediaSource.removeEventListener(this.analyticsCollector);
                this.analyticsCollector.resetForNewMediaSource();
            }

            arg3.addEventListener(this.eventHandler, this.analyticsCollector);
            this.mediaSource = arg3;
        }

        this.player.prepare(arg3, arg4, arg5);
    }

    public void release() {
        this.player.release();
        this.removeSurfaceCallbacks();
        if(this.surface != null) {
            if(this.ownsSurface) {
                this.surface.release();
            }

            this.surface = null;
        }

        if(this.mediaSource != null) {
            this.mediaSource.removeEventListener(this.analyticsCollector);
        }

        this.bandwidthMeter.removeEventListener(this.analyticsCollector);
        this.currentCues = Collections.emptyList();
    }

    public void removeAnalyticsListener(AnalyticsListener arg2) {
        this.analyticsCollector.removeListener(arg2);
    }

    @Deprecated public void removeAudioDebugListener(AudioRendererEventListener arg2) {
        this.audioDebugListeners.remove(arg2);
    }

    public void removeAudioListener(AudioListener arg2) {
        this.audioListeners.remove(arg2);
    }

    public void removeListener(EventListener arg2) {
        this.player.removeListener(arg2);
    }

    public void removeMetadataOutput(MetadataOutput arg2) {
        this.metadataOutputs.remove(arg2);
    }

    private void removeSurfaceCallbacks() {
        TextureView$SurfaceTextureListener v1 = null;
        if(this.textureView != null) {
            if(this.textureView.getSurfaceTextureListener() != this.componentListener) {
                Log.w("SimpleExoPlayer", "SurfaceTextureListener already unset or replaced.");
            }
            else {
                this.textureView.setSurfaceTextureListener(v1);
            }

            this.textureView = ((TextureView)v1);
        }

        if(this.surfaceHolder != null) {
            this.surfaceHolder.removeCallback(this.componentListener);
            this.surfaceHolder = ((SurfaceHolder)v1);
        }
    }

    public void removeTextOutput(TextOutput arg2) {
        this.textOutputs.remove(arg2);
    }

    @Deprecated public void removeVideoDebugListener(VideoRendererEventListener arg2) {
        this.videoDebugListeners.remove(arg2);
    }

    public void removeVideoListener(VideoListener arg2) {
        this.videoListeners.remove(arg2);
    }

    public void seekTo(int arg2, long arg3) {
        this.analyticsCollector.notifySeekStarted();
        this.player.seekTo(arg2, arg3);
    }

    public void seekTo(long arg2) {
        this.analyticsCollector.notifySeekStarted();
        this.player.seekTo(arg2);
    }

    public void seekToDefaultPosition() {
        this.analyticsCollector.notifySeekStarted();
        this.player.seekToDefaultPosition();
    }

    public void seekToDefaultPosition(int arg2) {
        this.analyticsCollector.notifySeekStarted();
        this.player.seekToDefaultPosition(arg2);
    }

    public void sendMessages(ExoPlayerMessage[] arg2) {
        this.player.sendMessages(arg2);
    }

    public void setAudioAttributes(AudioAttributes arg7) {
        if(Util.areEqual(this.audioAttributes, arg7)) {
            return;
        }

        this.audioAttributes = arg7;
        Renderer[] v0 = this.renderers;
        int v1 = v0.length;
        int v2;
        for(v2 = 0; v2 < v1; ++v2) {
            Renderer v3 = v0[v2];
            if(v3.getTrackType() == 1) {
                this.player.createMessage(((Target)v3)).setType(3).setPayload(arg7).send();
            }
        }

        Iterator v0_1 = this.audioListeners.iterator();
        while(v0_1.hasNext()) {
            v0_1.next().onAudioAttributesChanged(arg7);
        }
    }

    @Deprecated public void setAudioDebugListener(AudioRendererEventListener arg3) {
        this.audioDebugListeners.retainAll(Collections.singleton(this.analyticsCollector));
        if(arg3 != null) {
            this.addAudioDebugListener(arg3);
        }
    }

    @Deprecated public void setAudioStreamType(int arg3) {
        this.setAudioAttributes(new Builder().setUsage(Util.getAudioUsageForStreamType(arg3)).setContentType(Util.getAudioContentTypeForStreamType(arg3)).build());
    }

    @Deprecated public void setMetadataOutput(MetadataOutput arg3) {
        this.metadataOutputs.retainAll(Collections.singleton(this.analyticsCollector));
        if(arg3 != null) {
            this.addMetadataOutput(arg3);
        }
    }

    public void setPlayWhenReady(boolean arg2) {
        this.player.setPlayWhenReady(arg2);
    }

    public void setPlaybackParameters(PlaybackParameters arg2) {
        this.player.setPlaybackParameters(arg2);
    }

    @TargetApi(value=23) @Deprecated public void setPlaybackParams(PlaybackParams arg3) {
        PlaybackParameters v0;
        if(arg3 != null) {
            arg3.allowDefaults();
            v0 = new PlaybackParameters(arg3.getSpeed(), arg3.getPitch());
        }
        else {
            v0 = null;
        }

        this.setPlaybackParameters(v0);
    }

    public void setRepeatMode(int arg2) {
        this.player.setRepeatMode(arg2);
    }

    public void setSeekParameters(SeekParameters arg2) {
        this.player.setSeekParameters(arg2);
    }

    public void setShuffleModeEnabled(boolean arg2) {
        this.player.setShuffleModeEnabled(arg2);
    }

    @Deprecated public void setTextOutput(TextOutput arg2) {
        this.textOutputs.clear();
        if(arg2 != null) {
            this.addTextOutput(arg2);
        }
    }

    @Deprecated public void setVideoDebugListener(VideoRendererEventListener arg3) {
        this.videoDebugListeners.retainAll(Collections.singleton(this.analyticsCollector));
        if(arg3 != null) {
            this.addVideoDebugListener(arg3);
        }
    }

    @Deprecated public void setVideoListener(com.google.android.exoplayer2.SimpleExoPlayer$VideoListener arg2) {
        this.videoListeners.clear();
        if(arg2 != null) {
            this.addVideoListener(((VideoListener)arg2));
        }
    }

    public void setVideoScalingMode(int arg7) {
        this.videoScalingMode = arg7;
        Renderer[] v0 = this.renderers;
        int v1 = v0.length;
        int v2;
        for(v2 = 0; v2 < v1; ++v2) {
            Renderer v3 = v0[v2];
            if(v3.getTrackType() == 2) {
                this.player.createMessage(((Target)v3)).setType(4).setPayload(Integer.valueOf(arg7)).send();
            }
        }
    }

    public void setVideoSurface(Surface arg2) {
        this.removeSurfaceCallbacks();
        int v0 = 0;
        this.setVideoSurfaceInternal(arg2, false);
        if(arg2 == null) {
        }
        else {
            v0 = -1;
        }

        this.maybeNotifySurfaceSizeChanged(v0, v0);
    }

    public void setVideoSurfaceHolder(SurfaceHolder arg5) {
        this.removeSurfaceCallbacks();
        this.surfaceHolder = arg5;
        Surface v0 = null;
        if(arg5 != null) {
            arg5.addCallback(this.componentListener);
            Surface v2 = arg5.getSurface();
            if(v2 == null) {
                goto label_5;
            }
            else if(v2.isValid()) {
                this.setVideoSurfaceInternal(v2, false);
                Rect v5 = arg5.getSurfaceFrame();
                this.maybeNotifySurfaceSizeChanged(v5.width(), v5.height());
            }
            else {
                goto label_5;
            }
        }
        else {
        label_5:
            this.setVideoSurfaceInternal(v0, false);
            this.maybeNotifySurfaceSizeChanged(0, 0);
        }
    }

    private void setVideoSurfaceInternal(Surface arg8, boolean arg9) {
        ArrayList v0 = new ArrayList();
        Renderer[] v1 = this.renderers;
        int v2 = v1.length;
        int v3;
        for(v3 = 0; v3 < v2; ++v3) {
            Renderer v4 = v1[v3];
            if(v4.getTrackType() == 2) {
                ((List)v0).add(this.player.createMessage(((Target)v4)).setType(1).setPayload(arg8).send());
            }
        }

        if(this.surface != null && this.surface != arg8) {
            try {
                Iterator v0_1 = ((List)v0).iterator();
                while(v0_1.hasNext()) {
                    v0_1.next().blockUntilDelivered();
                }
            }
            catch(InterruptedException ) {
                Thread.currentThread().interrupt();
            }

            if(!this.ownsSurface) {
                goto label_35;
            }

            this.surface.release();
        }

    label_35:
        this.surface = arg8;
        this.ownsSurface = arg9;
    }

    public void setVideoSurfaceView(SurfaceView arg1) {
        SurfaceHolder v1 = arg1 == null ? null : arg1.getHolder();
        this.setVideoSurfaceHolder(v1);
    }

    public void setVideoTextureView(TextureView arg6) {
        SurfaceTexture v3;
        if(this.textureView == arg6) {
            return;
        }

        this.removeSurfaceCallbacks();
        this.textureView = arg6;
        this.needSetSurface = true;
        Surface v1 = null;
        if(arg6 != null) {
            if(arg6.getSurfaceTextureListener() != null) {
                Log.w("SimpleExoPlayer", "Replacing existing SurfaceTextureListener.");
            }

            arg6.setSurfaceTextureListener(this.componentListener);
            v3 = arg6.isAvailable() ? arg6.getSurfaceTexture() : ((SurfaceTexture)v1);
            if(v3 != null) {
                goto label_27;
            }

            goto label_10;
        }
        else {
        label_10:
            this.setVideoSurfaceInternal(v1, true);
            this.maybeNotifySurfaceSizeChanged(0, 0);
            return;
        label_27:
            this.setVideoSurfaceInternal(new Surface(v3), true);
            this.maybeNotifySurfaceSizeChanged(arg6.getWidth(), arg6.getHeight());
        }
    }

    public void setVolume(float arg7) {
        arg7 = Util.constrainValue(arg7, 0f, 1f);
        if(this.audioVolume == arg7) {
            return;
        }

        this.audioVolume = arg7;
        Renderer[] v0 = this.renderers;
        int v1 = v0.length;
        int v2;
        for(v2 = 0; v2 < v1; ++v2) {
            Renderer v3 = v0[v2];
            if(v3.getTrackType() == 1) {
                this.player.createMessage(((Target)v3)).setType(2).setPayload(Float.valueOf(arg7)).send();
            }
        }

        Iterator v0_1 = this.audioListeners.iterator();
        while(v0_1.hasNext()) {
            v0_1.next().onVolumeChanged(arg7);
        }
    }

    public void stop() {
        this.stop(false);
    }

    public void stop(boolean arg2) {
        this.player.stop(arg2);
        if(this.mediaSource != null) {
            this.mediaSource.removeEventListener(this.analyticsCollector);
            this.mediaSource = null;
            this.analyticsCollector.resetForNewMediaSource();
        }

        this.currentCues = Collections.emptyList();
    }
}

