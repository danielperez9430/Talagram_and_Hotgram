package com.google.android.exoplayer2;

import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.TextureView;
import com.google.android.exoplayer2.audio.AudioAttributes;
import com.google.android.exoplayer2.audio.AudioListener;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.text.TextOutput;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.video.VideoListener;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public interface Player {
    public interface AudioComponent {
        void addAudioListener(AudioListener arg1);

        AudioAttributes getAudioAttributes();

        int getAudioSessionId();

        float getVolume();

        void removeAudioListener(AudioListener arg1);

        void setAudioAttributes(AudioAttributes arg1);

        void setVolume(float arg1);
    }

    @Deprecated public abstract class DefaultEventListener implements EventListener {
        public DefaultEventListener() {
            super();
        }

        public void onLoadingChanged(boolean arg1) {
            Player$EventListener$-CC.$default$onLoadingChanged(((EventListener)this), arg1);
        }

        public void onPlaybackParametersChanged(PlaybackParameters arg1) {
            Player$EventListener$-CC.$default$onPlaybackParametersChanged(((EventListener)this), arg1);
        }

        public void onPlayerError(ExoPlaybackException arg1) {
            Player$EventListener$-CC.$default$onPlayerError(((EventListener)this), arg1);
        }

        public void onPlayerStateChanged(boolean arg1, int arg2) {
            Player$EventListener$-CC.$default$onPlayerStateChanged(((EventListener)this), arg1, arg2);
        }

        public void onPositionDiscontinuity(int arg1) {
            Player$EventListener$-CC.$default$onPositionDiscontinuity(((EventListener)this), arg1);
        }

        public void onRepeatModeChanged(int arg1) {
            Player$EventListener$-CC.$default$onRepeatModeChanged(((EventListener)this), arg1);
        }

        public void onSeekProcessed() {
            Player$EventListener$-CC.$default$onSeekProcessed(((EventListener)this));
        }

        public void onShuffleModeEnabledChanged(boolean arg1) {
            Player$EventListener$-CC.$default$onShuffleModeEnabledChanged(((EventListener)this), arg1);
        }

        @Deprecated public void onTimelineChanged(Timeline arg1, Object arg2) {
        }

        public void onTimelineChanged(Timeline arg1, Object arg2, int arg3) {
            this.onTimelineChanged(arg1, arg2);
        }

        public void onTracksChanged(TrackGroupArray arg1, TrackSelectionArray arg2) {
            Player$EventListener$-CC.$default$onTracksChanged(((EventListener)this), arg1, arg2);
        }
    }

    @Retention(value=RetentionPolicy.SOURCE) @public interface DiscontinuityReason {
    }

    public interface EventListener {
        void onLoadingChanged(boolean arg1);

        void onPlaybackParametersChanged(PlaybackParameters arg1);

        void onPlayerError(ExoPlaybackException arg1);

        void onPlayerStateChanged(boolean arg1, int arg2);

        void onPositionDiscontinuity(int arg1);

        void onRepeatModeChanged(int arg1);

        void onSeekProcessed();

        void onShuffleModeEnabledChanged(boolean arg1);

        void onTimelineChanged(Timeline arg1, Object arg2, int arg3);

        void onTracksChanged(TrackGroupArray arg1, TrackSelectionArray arg2);
    }

    @Retention(value=RetentionPolicy.SOURCE) @public interface RepeatMode {
    }

    public interface TextComponent {
        void addTextOutput(TextOutput arg1);

        void removeTextOutput(TextOutput arg1);
    }

    @Retention(value=RetentionPolicy.SOURCE) @public interface TimelineChangeReason {
    }

    public interface VideoComponent {
        void addVideoListener(VideoListener arg1);

        void clearVideoSurface();

        void clearVideoSurface(Surface arg1);

        void clearVideoSurfaceHolder(SurfaceHolder arg1);

        void clearVideoSurfaceView(SurfaceView arg1);

        void clearVideoTextureView(TextureView arg1);

        int getVideoScalingMode();

        void removeVideoListener(VideoListener arg1);

        void setVideoScalingMode(int arg1);

        void setVideoSurface(Surface arg1);

        void setVideoSurfaceHolder(SurfaceHolder arg1);

        void setVideoSurfaceView(SurfaceView arg1);

        void setVideoTextureView(TextureView arg1);
    }

    public static final int DISCONTINUITY_REASON_AD_INSERTION = 3;
    public static final int DISCONTINUITY_REASON_INTERNAL = 4;
    public static final int DISCONTINUITY_REASON_PERIOD_TRANSITION = 0;
    public static final int DISCONTINUITY_REASON_SEEK = 1;
    public static final int DISCONTINUITY_REASON_SEEK_ADJUSTMENT = 2;
    public static final int REPEAT_MODE_ALL = 2;
    public static final int REPEAT_MODE_OFF = 0;
    public static final int REPEAT_MODE_ONE = 1;
    public static final int STATE_BUFFERING = 2;
    public static final int STATE_ENDED = 4;
    public static final int STATE_IDLE = 1;
    public static final int STATE_READY = 3;
    public static final int TIMELINE_CHANGE_REASON_DYNAMIC = 2;
    public static final int TIMELINE_CHANGE_REASON_PREPARED = 0;
    public static final int TIMELINE_CHANGE_REASON_RESET = 1;

    void addListener(EventListener arg1);

    AudioComponent getAudioComponent();

    int getBufferedPercentage();

    long getBufferedPosition();

    long getContentBufferedPosition();

    long getContentPosition();

    int getCurrentAdGroupIndex();

    int getCurrentAdIndexInAdGroup();

    Object getCurrentManifest();

    int getCurrentPeriodIndex();

    long getCurrentPosition();

    Object getCurrentTag();

    Timeline getCurrentTimeline();

    TrackGroupArray getCurrentTrackGroups();

    TrackSelectionArray getCurrentTrackSelections();

    int getCurrentWindowIndex();

    long getDuration();

    int getNextWindowIndex();

    boolean getPlayWhenReady();

    ExoPlaybackException getPlaybackError();

    PlaybackParameters getPlaybackParameters();

    int getPlaybackState();

    int getPreviousWindowIndex();

    int getRendererCount();

    int getRendererType(int arg1);

    int getRepeatMode();

    boolean getShuffleModeEnabled();

    TextComponent getTextComponent();

    long getTotalBufferedDuration();

    VideoComponent getVideoComponent();

    boolean isCurrentWindowDynamic();

    boolean isCurrentWindowSeekable();

    boolean isLoading();

    boolean isPlayingAd();

    void release();

    void removeListener(EventListener arg1);

    void seekTo(int arg1, long arg2);

    void seekTo(long arg1);

    void seekToDefaultPosition();

    void seekToDefaultPosition(int arg1);

    void setPlayWhenReady(boolean arg1);

    void setPlaybackParameters(PlaybackParameters arg1);

    void setRepeatMode(int arg1);

    void setShuffleModeEnabled(boolean arg1);

    void stop();

    void stop(boolean arg1);
}

