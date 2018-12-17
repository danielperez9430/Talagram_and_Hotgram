package com.google.android.exoplayer2.audio;

import android.annotation.TargetApi;
import android.media.AudioTimestamp;
import android.media.AudioTrack;
import com.google.android.exoplayer2.util.Util;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

final class AudioTimestampPoller {
    @TargetApi(value=19) final class AudioTimestampV19 {
        private final AudioTimestamp audioTimestamp;
        private final AudioTrack audioTrack;
        private long lastTimestampPositionFrames;
        private long lastTimestampRawPositionFrames;
        private long rawTimestampFramePositionWrapCount;

        public AudioTimestampV19(AudioTrack arg1) {
            super();
            this.audioTrack = arg1;
            this.audioTimestamp = new AudioTimestamp();
        }

        public long getTimestampPositionFrames() {
            return this.lastTimestampPositionFrames;
        }

        public long getTimestampSystemTimeUs() {
            return this.audioTimestamp.nanoTime / 1000;
        }

        public boolean maybeUpdateTimestamp() {
            boolean v0 = this.audioTrack.getTimestamp(this.audioTimestamp);
            if(v0) {
                long v1 = this.audioTimestamp.framePosition;
                if(this.lastTimestampRawPositionFrames > v1) {
                    ++this.rawTimestampFramePositionWrapCount;
                }

                this.lastTimestampRawPositionFrames = v1;
                this.lastTimestampPositionFrames = v1 + (this.rawTimestampFramePositionWrapCount << 32);
            }

            return v0;
        }
    }

    @Retention(value=RetentionPolicy.SOURCE) @interface State {
    }

    private static final int ERROR_POLL_INTERVAL_US = 500000;
    private static final int FAST_POLL_INTERVAL_US = 5000;
    private static final int INITIALIZING_DURATION_US = 500000;
    private static final int SLOW_POLL_INTERVAL_US = 10000000;
    private static final int STATE_ERROR = 4;
    private static final int STATE_INITIALIZING = 0;
    private static final int STATE_NO_TIMESTAMP = 3;
    private static final int STATE_TIMESTAMP = 1;
    private static final int STATE_TIMESTAMP_ADVANCING = 2;
    private final AudioTimestampV19 audioTimestamp;
    private long initialTimestampPositionFrames;
    private long initializeSystemTimeUs;
    private long lastTimestampSampleTimeUs;
    private long sampleIntervalUs;
    private int state;

    public AudioTimestampPoller(AudioTrack arg3) {
        super();
        if(Util.SDK_INT >= 19) {
            this.audioTimestamp = new AudioTimestampV19(arg3);
            this.reset();
        }
        else {
            this.audioTimestamp = null;
            this.updateState(3);
        }
    }

    public void acceptTimestamp() {
        if(this.state == 4) {
            this.reset();
        }
    }

    public long getTimestampPositionFrames() {
        long v0 = this.audioTimestamp != null ? this.audioTimestamp.getTimestampPositionFrames() : -1;
        return v0;
    }

    public long getTimestampSystemTimeUs() {
        long v0 = this.audioTimestamp != null ? this.audioTimestamp.getTimestampSystemTimeUs() : -9223372036854775807L;
        return v0;
    }

    public boolean hasTimestamp() {
        boolean v1 = true;
        if(this.state != 1) {
            if(this.state == 2) {
            }
            else {
                v1 = false;
            }
        }

        return v1;
    }

    public boolean isTimestampAdvancing() {
        boolean v0 = this.state == 2 ? true : false;
        return v0;
    }

    public boolean maybePollTimestamp(long arg7) {
        int v7;
        if(this.audioTimestamp != null) {
            if(arg7 - this.lastTimestampSampleTimeUs < this.sampleIntervalUs) {
            }
            else {
                this.lastTimestampSampleTimeUs = arg7;
                boolean v0 = this.audioTimestamp.maybeUpdateTimestamp();
                switch(this.state) {
                    case 0: {
                        if(!v0) {
                            if(arg7 - this.initializeSystemTimeUs > 500000) {
                                v7 = 3;
                            label_26:
                                this.updateState(v7);
                            }
                            else {
                            }

                            return v0;
                        }
                        else if(this.audioTimestamp.getTimestampSystemTimeUs() >= this.initializeSystemTimeUs) {
                            this.initialTimestampPositionFrames = this.audioTimestamp.getTimestampPositionFrames();
                            v7 = 1;
                            goto label_26;
                        }
                        else {
                            return false;
                        }
                    }
                    case 1: {
                        if(!v0) {
                            goto label_28;
                        }
                        else if(this.audioTimestamp.getTimestampPositionFrames() > this.initialTimestampPositionFrames) {
                            v7 = 2;
                            goto label_26;
                        }
                        else {
                            return v0;
                        }
                    }
                    case 2: {
                        if(!v0) {
                            goto label_28;
                        }
                        else {
                            return v0;
                        }
                    }
                    case 3: {
                        if(v0) {
                        label_28:
                            this.reset();
                        }
                        else {
                        }

                        break;
                    }
                    case 4: {
                        break;
                    }
                    default: {
                        throw new IllegalStateException();
                    }
                }

                return v0;
            }
        }

        return 0;
    }

    public void rejectTimestamp() {
        this.updateState(4);
    }

    public void reset() {
        if(this.audioTimestamp != null) {
            this.updateState(0);
        }
    }

    private void updateState(int arg7) {
        this.state = arg7;
        long v0 = 5000;
        switch(arg7) {
            case 0: {
                goto label_10;
            }
            case 1: {
                goto label_18;
            }
            case 2: 
            case 3: {
                goto label_8;
            }
            case 4: {
                goto label_6;
            }
        }

        throw new IllegalStateException();
    label_6:
        v0 = 500000;
        goto label_18;
    label_8:
        v0 = 10000000;
        goto label_18;
    label_10:
        this.lastTimestampSampleTimeUs = 0;
        this.initialTimestampPositionFrames = -1;
        this.initializeSystemTimeUs = System.nanoTime() / 1000;
    label_18:
        this.sampleIntervalUs = v0;
    }
}

