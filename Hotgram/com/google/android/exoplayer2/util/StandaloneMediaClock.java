package com.google.android.exoplayer2.util;

import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.PlaybackParameters;

public final class StandaloneMediaClock implements MediaClock {
    private long baseElapsedMs;
    private long baseUs;
    private final Clock clock;
    private PlaybackParameters playbackParameters;
    private boolean started;

    public StandaloneMediaClock(Clock arg1) {
        super();
        this.clock = arg1;
        this.playbackParameters = PlaybackParameters.DEFAULT;
    }

    public PlaybackParameters getPlaybackParameters() {
        return this.playbackParameters;
    }

    public long getPositionUs() {
        long v0 = this.baseUs;
        if(this.started) {
            long v2 = this.clock.elapsedRealtime() - this.baseElapsedMs;
            v2 = this.playbackParameters.speed == 1f ? C.msToUs(v2) : this.playbackParameters.getMediaTimeUsForPlayoutTimeMs(v2);
            v0 += v2;
        }

        return v0;
    }

    public void resetPosition(long arg1) {
        this.baseUs = arg1;
        if(this.started) {
            this.baseElapsedMs = this.clock.elapsedRealtime();
        }
    }

    public PlaybackParameters setPlaybackParameters(PlaybackParameters arg3) {
        if(this.started) {
            this.resetPosition(this.getPositionUs());
        }

        this.playbackParameters = arg3;
        return arg3;
    }

    public void start() {
        if(!this.started) {
            this.baseElapsedMs = this.clock.elapsedRealtime();
            this.started = true;
        }
    }

    public void stop() {
        if(this.started) {
            this.resetPosition(this.getPositionUs());
            this.started = false;
        }
    }
}

