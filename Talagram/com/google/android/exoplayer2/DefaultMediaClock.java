package com.google.android.exoplayer2;

import com.google.android.exoplayer2.util.Clock;
import com.google.android.exoplayer2.util.MediaClock;
import com.google.android.exoplayer2.util.StandaloneMediaClock;

final class DefaultMediaClock implements MediaClock {
    public interface PlaybackParameterListener {
        void onPlaybackParametersChanged(PlaybackParameters arg1);
    }

    private final PlaybackParameterListener listener;
    private MediaClock rendererClock;
    private Renderer rendererClockSource;
    private final StandaloneMediaClock standaloneMediaClock;

    public DefaultMediaClock(PlaybackParameterListener arg1, Clock arg2) {
        super();
        this.listener = arg1;
        this.standaloneMediaClock = new StandaloneMediaClock(arg2);
    }

    private void ensureSynced() {
        this.standaloneMediaClock.resetPosition(this.rendererClock.getPositionUs());
        PlaybackParameters v0 = this.rendererClock.getPlaybackParameters();
        if(!v0.equals(this.standaloneMediaClock.getPlaybackParameters())) {
            this.standaloneMediaClock.setPlaybackParameters(v0);
            this.listener.onPlaybackParametersChanged(v0);
        }
    }

    public PlaybackParameters getPlaybackParameters() {
        PlaybackParameters v0 = this.rendererClock != null ? this.rendererClock.getPlaybackParameters() : this.standaloneMediaClock.getPlaybackParameters();
        return v0;
    }

    public long getPositionUs() {
        if(this.isUsingRendererClock()) {
            return this.rendererClock.getPositionUs();
        }

        return this.standaloneMediaClock.getPositionUs();
    }

    private boolean isUsingRendererClock() {
        boolean v0;
        if(this.rendererClockSource == null || (this.rendererClockSource.isEnded())) {
        label_13:
            v0 = false;
        }
        else {
            if(!this.rendererClockSource.isReady() && (this.rendererClockSource.hasReadStreamToEnd())) {
                goto label_13;
            }

            v0 = true;
        }

        return v0;
    }

    public void onRendererDisabled(Renderer arg2) {
        if(arg2 == this.rendererClockSource) {
            this.rendererClock = null;
            this.rendererClockSource = null;
        }
    }

    public void onRendererEnabled(Renderer arg3) {
        MediaClock v0 = arg3.getMediaClock();
        if(v0 != null && v0 != this.rendererClock) {
            if(this.rendererClock == null) {
                this.rendererClock = v0;
                this.rendererClockSource = arg3;
                this.rendererClock.setPlaybackParameters(this.standaloneMediaClock.getPlaybackParameters());
                this.ensureSynced();
            }
            else {
                throw ExoPlaybackException.createForUnexpected(new IllegalStateException("Multiple renderer media clocks enabled."));
            }
        }
    }

    public void resetPosition(long arg2) {
        this.standaloneMediaClock.resetPosition(arg2);
    }

    public PlaybackParameters setPlaybackParameters(PlaybackParameters arg2) {
        if(this.rendererClock != null) {
            arg2 = this.rendererClock.setPlaybackParameters(arg2);
        }

        this.standaloneMediaClock.setPlaybackParameters(arg2);
        this.listener.onPlaybackParametersChanged(arg2);
        return arg2;
    }

    public void start() {
        this.standaloneMediaClock.start();
    }

    public void stop() {
        this.standaloneMediaClock.stop();
    }

    public long syncAndGetPositionUs() {
        if(this.isUsingRendererClock()) {
            this.ensureSynced();
            return this.rendererClock.getPositionUs();
        }

        return this.standaloneMediaClock.getPositionUs();
    }
}

