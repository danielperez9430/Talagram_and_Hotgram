package com.google.android.exoplayer2.audio;

import android.media.AudioTrack;
import android.os.SystemClock;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;

final class AudioTrackPositionTracker {
    public interface Listener {
        void onInvalidLatency(long arg1);

        void onPositionFramesMismatch(long arg1, long arg2, long arg3, long arg4);

        void onSystemTimeUsMismatch(long arg1, long arg2, long arg3, long arg4);

        void onUnderrun(int arg1, long arg2);
    }

    @Retention(value=RetentionPolicy.SOURCE) @interface PlayState {
    }

    private static final long FORCE_RESET_WORKAROUND_TIMEOUT_MS = 200;
    private static final long MAX_AUDIO_TIMESTAMP_OFFSET_US = 5000000;
    private static final long MAX_LATENCY_US = 5000000;
    private static final int MAX_PLAYHEAD_OFFSET_COUNT = 10;
    private static final int MIN_LATENCY_SAMPLE_INTERVAL_US = 500000;
    private static final int MIN_PLAYHEAD_OFFSET_SAMPLE_INTERVAL_US = 30000;
    private static final int PLAYSTATE_PAUSED = 2;
    private static final int PLAYSTATE_PLAYING = 3;
    private static final int PLAYSTATE_STOPPED = 1;
    private AudioTimestampPoller audioTimestampPoller;
    private AudioTrack audioTrack;
    private int bufferSize;
    private long bufferSizeUs;
    private long endPlaybackHeadPosition;
    private long forceResetWorkaroundTimeMs;
    private Method getLatencyMethod;
    private boolean hasData;
    private boolean isOutputPcm;
    private long lastLatencySampleTimeUs;
    private long lastPlayheadSampleTimeUs;
    private long lastRawPlaybackHeadPosition;
    private long latencyUs;
    private final Listener listener;
    private boolean needsPassthroughWorkarounds;
    private int nextPlayheadOffsetIndex;
    private int outputPcmFrameSize;
    private int outputSampleRate;
    private long passthroughWorkaroundPauseOffset;
    private int playheadOffsetCount;
    private final long[] playheadOffsets;
    private long rawPlaybackHeadWrapCount;
    private long smoothedPlayheadOffsetUs;
    private long stopPlaybackHeadPosition;
    private long stopTimestampUs;

    public AudioTrackPositionTracker(Listener arg3) {
        super();
        this.listener = Assertions.checkNotNull(arg3);
        if(Util.SDK_INT >= 18) {
            try {
                this.getLatencyMethod = AudioTrack.class.getMethod("getLatency", null);
                goto label_11;
            }
            catch(NoSuchMethodException ) {
            label_11:
                this.playheadOffsets = new long[10];
                return;
            }
        }

        goto label_11;
    }

    private boolean forceHasPendingData() {
        boolean v0 = !this.needsPassthroughWorkarounds || Assertions.checkNotNull(this.audioTrack).getPlayState() != 2 || this.getPlaybackHeadPosition() != 0 ? false : true;
        return v0;
    }

    private long framesToDurationUs(long arg3) {
        return arg3 * 1000000 / (((long)this.outputSampleRate));
    }

    public int getAvailableBufferSize(long arg5) {
        return this.bufferSize - (((int)(arg5 - this.getPlaybackHeadPosition() * (((long)this.outputPcmFrameSize)))));
    }

    public long getCurrentPositionUs(boolean arg8) {
        if(Assertions.checkNotNull(this.audioTrack).getPlayState() == 3) {
            this.maybeSampleSyncParams();
        }

        long v0 = System.nanoTime() / 1000;
        Object v2 = Assertions.checkNotNull(this.audioTimestampPoller);
        if(((AudioTimestampPoller)v2).hasTimestamp()) {
            long v3 = this.framesToDurationUs(((AudioTimestampPoller)v2).getTimestampPositionFrames());
            if(!((AudioTimestampPoller)v2).isTimestampAdvancing()) {
                return v3;
            }

            return v3 + (v0 - ((AudioTimestampPoller)v2).getTimestampSystemTimeUs());
        }

        if(this.playheadOffsetCount == 0) {
            v0 = this.getPlaybackHeadPositionUs();
        }
        else {
            v0 += this.smoothedPlayheadOffsetUs;
        }

        if(!arg8) {
            v0 -= this.latencyUs;
        }

        return v0;
    }

    private long getPlaybackHeadPosition() {
        Object v0 = Assertions.checkNotNull(this.audioTrack);
        long v3 = -9223372036854775807L;
        if(this.stopTimestampUs != v3) {
            return Math.min(this.endPlaybackHeadPosition, this.stopPlaybackHeadPosition + (SystemClock.elapsedRealtime() * 1000 - this.stopTimestampUs) * (((long)this.outputSampleRate)) / 1000000);
        }

        int v1 = ((AudioTrack)v0).getPlayState();
        long v5 = 0;
        if(v1 == 1) {
            return v5;
        }

        long v7 = 4294967295L & (((long)((AudioTrack)v0).getPlaybackHeadPosition()));
        if(this.needsPassthroughWorkarounds) {
            if(v1 == 2 && v7 == v5) {
                this.passthroughWorkaroundPauseOffset = this.lastRawPlaybackHeadPosition;
            }

            v7 += this.passthroughWorkaroundPauseOffset;
        }

        if(Util.SDK_INT <= 28) {
            if(v7 == v5 && this.lastRawPlaybackHeadPosition > v5 && v1 == 3) {
                if(this.forceResetWorkaroundTimeMs == v3) {
                    this.forceResetWorkaroundTimeMs = SystemClock.elapsedRealtime();
                }

                return this.lastRawPlaybackHeadPosition;
            }

            this.forceResetWorkaroundTimeMs = v3;
        }

        if(this.lastRawPlaybackHeadPosition > v7) {
            ++this.rawPlaybackHeadWrapCount;
        }

        this.lastRawPlaybackHeadPosition = v7;
        return v7 + (this.rawPlaybackHeadWrapCount << 32);
    }

    private long getPlaybackHeadPositionUs() {
        return this.framesToDurationUs(this.getPlaybackHeadPosition());
    }

    public void handleEndOfStream(long arg5) {
        this.stopPlaybackHeadPosition = this.getPlaybackHeadPosition();
        this.stopTimestampUs = SystemClock.elapsedRealtime() * 1000;
        this.endPlaybackHeadPosition = arg5;
    }

    public boolean hasPendingData(long arg4) {
        boolean v4 = arg4 > this.getPlaybackHeadPosition() || (this.forceHasPendingData()) ? true : false;
        return v4;
    }

    public boolean isPlaying() {
        boolean v0 = Assertions.checkNotNull(this.audioTrack).getPlayState() == 3 ? true : false;
        return v0;
    }

    public boolean isStalled(long arg6) {
        boolean v6 = this.forceResetWorkaroundTimeMs == -9223372036854775807L || arg6 <= 0 || SystemClock.elapsedRealtime() - this.forceResetWorkaroundTimeMs < 200 ? false : true;
        return v6;
    }

    public boolean mayHandleBuffer(long arg9) {
        int v0 = Assertions.checkNotNull(this.audioTrack).getPlayState();
        if(this.needsPassthroughWorkarounds) {
            if(v0 == 2) {
                this.hasData = false;
                return 0;
            }
            else if(v0 == 1 && this.getPlaybackHeadPosition() == 0) {
                return 0;
            }
        }

        boolean v1 = this.hasData;
        this.hasData = this.hasPendingData(arg9);
        if((v1) && !this.hasData && v0 != 1 && this.listener != null) {
            this.listener.onUnderrun(this.bufferSize, C.usToMs(this.bufferSizeUs));
        }

        return 1;
    }

    private void maybePollAndCheckTimestamp(long arg12, long arg14) {
        Object v0 = Assertions.checkNotNull(this.audioTimestampPoller);
        if(!((AudioTimestampPoller)v0).maybePollTimestamp(arg12)) {
            return;
        }

        long v5 = ((AudioTimestampPoller)v0).getTimestampSystemTimeUs();
        long v3 = ((AudioTimestampPoller)v0).getTimestampPositionFrames();
        long v7 = 5000000;
        if(Math.abs(v5 - arg12) > v7) {
            this.listener.onSystemTimeUsMismatch(v3, v5, arg12, arg14);
            goto label_15;
        }
        else if(Math.abs(this.framesToDurationUs(v3) - arg14) > v7) {
            this.listener.onPositionFramesMismatch(v3, v5, arg12, arg14);
        label_15:
            ((AudioTimestampPoller)v0).rejectTimestamp();
        }
        else {
            ((AudioTimestampPoller)v0).acceptTimestamp();
        }
    }

    private void maybeSampleSyncParams() {
        long v0 = this.getPlaybackHeadPositionUs();
        long v2 = 0;
        if(v0 == v2) {
            return;
        }

        long v4 = System.nanoTime() / 1000;
        if(v4 - this.lastPlayheadSampleTimeUs >= 30000) {
            this.playheadOffsets[this.nextPlayheadOffsetIndex] = v0 - v4;
            this.nextPlayheadOffsetIndex = (this.nextPlayheadOffsetIndex + 1) % 10;
            if(this.playheadOffsetCount < 10) {
                ++this.playheadOffsetCount;
            }

            this.lastPlayheadSampleTimeUs = v4;
            this.smoothedPlayheadOffsetUs = v2;
            int v2_1;
            for(v2_1 = 0; v2_1 < this.playheadOffsetCount; ++v2_1) {
                this.smoothedPlayheadOffsetUs += this.playheadOffsets[v2_1] / (((long)this.playheadOffsetCount));
            }
        }

        if(this.needsPassthroughWorkarounds) {
            return;
        }

        this.maybePollAndCheckTimestamp(v4, v0);
        this.maybeUpdateLatency(v4);
    }

    private void maybeUpdateLatency(long arg8) {
        if((this.isOutputPcm) && this.getLatencyMethod != null && arg8 - this.lastLatencySampleTimeUs >= 500000) {
            try {
                this.latencyUs = (((long)Util.castNonNull(this.getLatencyMethod.invoke(Assertions.checkNotNull(this.audioTrack))).intValue())) * 1000 - this.bufferSizeUs;
                long v2 = 0;
                this.latencyUs = Math.max(this.latencyUs, v2);
                if(this.latencyUs <= 5000000) {
                    goto label_37;
                }

                this.listener.onInvalidLatency(this.latencyUs);
                this.latencyUs = v2;
            }
            catch(Exception ) {
                this.getLatencyMethod = null;
            }

        label_37:
            this.lastLatencySampleTimeUs = arg8;
        }
    }

    private static boolean needsPassthroughWorkarounds(int arg2) {
        boolean v2;
        if(Util.SDK_INT < 23) {
            if(arg2 != 5 && arg2 != 6) {
                goto label_9;
            }

            v2 = true;
        }
        else {
        label_9:
            v2 = false;
        }

        return v2;
    }

    public boolean pause() {
        this.resetSyncParams();
        if(this.stopTimestampUs == -9223372036854775807L) {
            Assertions.checkNotNull(this.audioTimestampPoller).reset();
            return 1;
        }

        return 0;
    }

    public void reset() {
        this.resetSyncParams();
        this.audioTrack = null;
        this.audioTimestampPoller = null;
    }

    private void resetSyncParams() {
        this.smoothedPlayheadOffsetUs = 0;
        this.playheadOffsetCount = 0;
        this.nextPlayheadOffsetIndex = 0;
        this.lastPlayheadSampleTimeUs = 0;
    }

    public void setAudioTrack(AudioTrack arg3, int arg4, int arg5, int arg6) {
        this.audioTrack = arg3;
        this.outputPcmFrameSize = arg5;
        this.bufferSize = arg6;
        this.audioTimestampPoller = new AudioTimestampPoller(arg3);
        this.outputSampleRate = arg3.getSampleRate();
        this.needsPassthroughWorkarounds = AudioTrackPositionTracker.needsPassthroughWorkarounds(arg4);
        this.isOutputPcm = Util.isEncodingLinearPcm(arg4);
        long v0 = -9223372036854775807L;
        long v3 = this.isOutputPcm ? this.framesToDurationUs(((long)(arg6 / arg5))) : v0;
        this.bufferSizeUs = v3;
        this.lastRawPlaybackHeadPosition = 0;
        this.rawPlaybackHeadWrapCount = 0;
        this.passthroughWorkaroundPauseOffset = 0;
        this.hasData = false;
        this.stopTimestampUs = v0;
        this.forceResetWorkaroundTimeMs = v0;
        this.latencyUs = 0;
    }

    public void start() {
        Assertions.checkNotNull(this.audioTimestampPoller).reset();
    }
}

