package com.google.android.exoplayer2.audio;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.media.AudioAttributes$Builder;
import android.media.AudioFormat$Builder;
import android.media.AudioFormat;
import android.media.AudioTrack;
import android.os.ConditionVariable;
import android.os.SystemClock;
import android.util.Log;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public final class DefaultAudioSink implements AudioSink {
    public interface AudioProcessorChain {
        PlaybackParameters applyPlaybackParameters(PlaybackParameters arg1);

        AudioProcessor[] getAudioProcessors();

        long getMediaDuration(long arg1);

        long getSkippedOutputFrameCount();
    }

    public class DefaultAudioProcessorChain implements AudioProcessorChain {
        private final AudioProcessor[] audioProcessors;
        private final SilenceSkippingAudioProcessor silenceSkippingAudioProcessor;
        private final SonicAudioProcessor sonicAudioProcessor;

        public DefaultAudioProcessorChain(AudioProcessor[] arg4) {
            super();
            this.audioProcessors = Arrays.copyOf(((Object[])arg4), arg4.length + 2);
            this.silenceSkippingAudioProcessor = new SilenceSkippingAudioProcessor();
            this.sonicAudioProcessor = new SonicAudioProcessor();
            this.audioProcessors[arg4.length] = this.silenceSkippingAudioProcessor;
            this.audioProcessors[arg4.length + 1] = this.sonicAudioProcessor;
        }

        public PlaybackParameters applyPlaybackParameters(PlaybackParameters arg5) {
            this.silenceSkippingAudioProcessor.setEnabled(arg5.skipSilence);
            return new PlaybackParameters(this.sonicAudioProcessor.setSpeed(arg5.speed), this.sonicAudioProcessor.setPitch(arg5.pitch), arg5.skipSilence);
        }

        public AudioProcessor[] getAudioProcessors() {
            return this.audioProcessors;
        }

        public long getMediaDuration(long arg2) {
            return this.sonicAudioProcessor.scaleDurationForSpeedup(arg2);
        }

        public long getSkippedOutputFrameCount() {
            return this.silenceSkippingAudioProcessor.getSkippedFrames();
        }
    }

    public final class InvalidAudioTrackTimestampException extends RuntimeException {
        private InvalidAudioTrackTimestampException(String arg1) {
            super(arg1);
        }

        InvalidAudioTrackTimestampException(String arg1, com.google.android.exoplayer2.audio.DefaultAudioSink$1 arg2) {
            this(arg1);
        }
    }

    final class PlaybackParametersCheckpoint {
        private final long mediaTimeUs;
        private final PlaybackParameters playbackParameters;
        private final long positionUs;

        PlaybackParametersCheckpoint(PlaybackParameters arg1, long arg2, long arg4, com.google.android.exoplayer2.audio.DefaultAudioSink$1 arg6) {
            this(arg1, arg2, arg4);
        }

        private PlaybackParametersCheckpoint(PlaybackParameters arg1, long arg2, long arg4) {
            super();
            this.playbackParameters = arg1;
            this.mediaTimeUs = arg2;
            this.positionUs = arg4;
        }

        static PlaybackParameters access$200(PlaybackParametersCheckpoint arg0) {
            return arg0.playbackParameters;
        }

        static long access$400(PlaybackParametersCheckpoint arg2) {
            return arg2.positionUs;
        }

        static long access$500(PlaybackParametersCheckpoint arg2) {
            return arg2.mediaTimeUs;
        }
    }

    final class PositionTrackerListener implements Listener {
        PositionTrackerListener(DefaultAudioSink arg1, com.google.android.exoplayer2.audio.DefaultAudioSink$1 arg2) {
            this(arg1);
        }

        private PositionTrackerListener(DefaultAudioSink arg1) {
            DefaultAudioSink.this = arg1;
            super();
        }

        public void onInvalidLatency(long arg4) {
            Log.w("AudioTrack", "Ignoring impossibly large audio latency: " + arg4);
        }

        public void onPositionFramesMismatch(long arg3, long arg5, long arg7, long arg9) {
            String v3 = "Spurious audio timestamp (frame position mismatch): " + arg3 + ", " + arg5 + ", " + arg7 + ", " + arg9 + ", " + DefaultAudioSink.this.getSubmittedFrames() + ", " + DefaultAudioSink.this.getWrittenFrames();
            if(!DefaultAudioSink.failOnSpuriousAudioTimestamp) {
                Log.w("AudioTrack", v3);
                return;
            }

            throw new InvalidAudioTrackTimestampException(v3, null);
        }

        public void onSystemTimeUsMismatch(long arg3, long arg5, long arg7, long arg9) {
            String v3 = "Spurious audio timestamp (system clock mismatch): " + arg3 + ", " + arg5 + ", " + arg7 + ", " + arg9 + ", " + DefaultAudioSink.this.getSubmittedFrames() + ", " + DefaultAudioSink.this.getWrittenFrames();
            if(!DefaultAudioSink.failOnSpuriousAudioTimestamp) {
                Log.w("AudioTrack", v3);
                return;
            }

            throw new InvalidAudioTrackTimestampException(v3, null);
        }

        public void onUnderrun(int arg11, long arg12) {
            if(DefaultAudioSink.this.listener != null) {
                DefaultAudioSink.this.listener.onUnderrun(arg11, arg12, SystemClock.elapsedRealtime() - DefaultAudioSink.this.lastFeedElapsedRealtimeMs);
            }
        }
    }

    @Retention(value=RetentionPolicy.SOURCE) @interface StartMediaTimeState {
    }

    private static final int BUFFER_MULTIPLICATION_FACTOR = 4;
    private static final int ERROR_BAD_VALUE = -2;
    private static final long MAX_BUFFER_DURATION_US = 750000;
    private static final long MIN_BUFFER_DURATION_US = 250000;
    private static final int MODE_STATIC = 0;
    private static final int MODE_STREAM = 1;
    private static final long PASSTHROUGH_BUFFER_DURATION_US = 250000;
    private static final int START_IN_SYNC = 1;
    private static final int START_NEED_SYNC = 2;
    private static final int START_NOT_SET = 0;
    private static final int STATE_INITIALIZED = 1;
    private static final String TAG = "AudioTrack";
    @SuppressLint(value={"InlinedApi"}) private static final int WRITE_NON_BLOCKING = 1;
    private AudioProcessor[] activeAudioProcessors;
    private PlaybackParameters afterDrainPlaybackParameters;
    private AudioAttributes audioAttributes;
    private final AudioCapabilities audioCapabilities;
    private final AudioProcessorChain audioProcessorChain;
    private int audioSessionId;
    private AudioTrack audioTrack;
    private final AudioTrackPositionTracker audioTrackPositionTracker;
    private ByteBuffer avSyncHeader;
    private int bufferSize;
    private int bytesUntilNextAvSync;
    private boolean canApplyPlaybackParameters;
    private final ChannelMappingAudioProcessor channelMappingAudioProcessor;
    private int drainingAudioProcessorIndex;
    private final boolean enableConvertHighResIntPcmToFloat;
    public static boolean enablePreV21AudioSessionWorkaround = false;
    public static boolean failOnSpuriousAudioTimestamp = false;
    private int framesPerEncodedSample;
    private boolean handledEndOfStream;
    private ByteBuffer inputBuffer;
    private int inputSampleRate;
    private boolean isInputPcm;
    private AudioTrack keepSessionIdAudioTrack;
    private long lastFeedElapsedRealtimeMs;
    private com.google.android.exoplayer2.audio.AudioSink$Listener listener;
    private ByteBuffer outputBuffer;
    private ByteBuffer[] outputBuffers;
    private int outputChannelConfig;
    private int outputEncoding;
    private int outputPcmFrameSize;
    private int outputSampleRate;
    private int pcmFrameSize;
    private PlaybackParameters playbackParameters;
    private final ArrayDeque playbackParametersCheckpoints;
    private long playbackParametersOffsetUs;
    private long playbackParametersPositionUs;
    private boolean playing;
    private byte[] preV21OutputBuffer;
    private int preV21OutputBufferOffset;
    private boolean processingEnabled;
    private final ConditionVariable releasingConditionVariable;
    private boolean shouldConvertHighResIntPcmToFloat;
    private int startMediaTimeState;
    private long startMediaTimeUs;
    private long submittedEncodedFrames;
    private long submittedPcmBytes;
    private final AudioProcessor[] toFloatPcmAvailableAudioProcessors;
    private final AudioProcessor[] toIntPcmAvailableAudioProcessors;
    private final TrimmingAudioProcessor trimmingAudioProcessor;
    private boolean tunneling;
    private float volume;
    private long writtenEncodedFrames;
    private long writtenPcmBytes;

    static {
    }

    public DefaultAudioSink(AudioCapabilities arg5, AudioProcessorChain arg6, boolean arg7) {
        super();
        this.audioCapabilities = arg5;
        this.audioProcessorChain = Assertions.checkNotNull(arg6);
        this.enableConvertHighResIntPcmToFloat = arg7;
        this.releasingConditionVariable = new ConditionVariable(true);
        this.audioTrackPositionTracker = new AudioTrackPositionTracker(new PositionTrackerListener(this, null));
        this.channelMappingAudioProcessor = new ChannelMappingAudioProcessor();
        this.trimmingAudioProcessor = new TrimmingAudioProcessor();
        ArrayList v5 = new ArrayList();
        Collections.addAll(((Collection)v5), new AudioProcessor[]{new ResamplingAudioProcessor(), this.channelMappingAudioProcessor, this.trimmingAudioProcessor});
        Collections.addAll(((Collection)v5), arg6.getAudioProcessors());
        this.toIntPcmAvailableAudioProcessors = v5.toArray(new AudioProcessor[v5.size()]);
        this.toFloatPcmAvailableAudioProcessors = new AudioProcessor[]{new FloatResamplingAudioProcessor()};
        this.volume = 1f;
        this.startMediaTimeState = 0;
        this.audioAttributes = AudioAttributes.DEFAULT;
        this.audioSessionId = 0;
        this.playbackParameters = PlaybackParameters.DEFAULT;
        this.drainingAudioProcessorIndex = -1;
        this.activeAudioProcessors = new AudioProcessor[0];
        this.outputBuffers = new ByteBuffer[0];
        this.playbackParametersCheckpoints = new ArrayDeque();
    }

    public DefaultAudioSink(AudioCapabilities arg2, AudioProcessor[] arg3) {
        this(arg2, arg3, false);
    }

    public DefaultAudioSink(AudioCapabilities arg2, AudioProcessor[] arg3, boolean arg4) {
        this(arg2, new DefaultAudioProcessorChain(arg3), arg4);
    }

    static long access$1000(DefaultAudioSink arg2) {
        return arg2.lastFeedElapsedRealtimeMs;
    }

    static ConditionVariable access$300(DefaultAudioSink arg0) {
        return arg0.releasingConditionVariable;
    }

    static long access$600(DefaultAudioSink arg2) {
        return arg2.getSubmittedFrames();
    }

    static long access$700(DefaultAudioSink arg2) {
        return arg2.getWrittenFrames();
    }

    static com.google.android.exoplayer2.audio.AudioSink$Listener access$900(DefaultAudioSink arg0) {
        return arg0.listener;
    }

    private long applySkipping(long arg3) {
        return arg3 + this.framesToDurationUs(this.audioProcessorChain.getSkippedOutputFrameCount());
    }

    private long applySpeedup(long arg6) {
        long v0_1;
        Object v0;
        for(v0 = null; !this.playbackParametersCheckpoints.isEmpty(); v0 = this.playbackParametersCheckpoints.remove()) {
            if(arg6 < PlaybackParametersCheckpoint.access$400(this.playbackParametersCheckpoints.getFirst())) {
                break;
            }
        }

        if(v0 != null) {
            this.playbackParameters = PlaybackParametersCheckpoint.access$200(((PlaybackParametersCheckpoint)v0));
            this.playbackParametersPositionUs = PlaybackParametersCheckpoint.access$400(((PlaybackParametersCheckpoint)v0));
            this.playbackParametersOffsetUs = PlaybackParametersCheckpoint.access$500(((PlaybackParametersCheckpoint)v0)) - this.startMediaTimeUs;
        }

        if(this.playbackParameters.speed == 1f) {
            return arg6 + this.playbackParametersOffsetUs - this.playbackParametersPositionUs;
        }

        if(this.playbackParametersCheckpoints.isEmpty()) {
            v0_1 = this.playbackParametersOffsetUs;
            arg6 = this.audioProcessorChain.getMediaDuration(arg6 - this.playbackParametersPositionUs);
        }
        else {
            v0_1 = this.playbackParametersOffsetUs;
            arg6 = Util.getMediaDurationForPlayoutDuration(arg6 - this.playbackParametersPositionUs, this.playbackParameters.speed);
        }

        return v0_1 + arg6;
    }

    public void configure(int arg9, int arg10, int arg11, int arg12, int[] arg13, int arg14, int arg15) {
        boolean v6;
        this.inputSampleRate = arg11;
        this.isInputPcm = Util.isEncodingLinearPcm(arg9);
        boolean v1 = true;
        boolean v0 = !this.enableConvertHighResIntPcmToFloat || !this.isEncodingSupported(1073741824) || !Util.isEncodingHighResolutionIntegerPcm(arg9) ? false : true;
        this.shouldConvertHighResIntPcmToFloat = v0;
        if(this.isInputPcm) {
            this.pcmFrameSize = Util.getPcmFrameSize(arg9, arg10);
        }

        int v3 = 4;
        v0 = !this.isInputPcm || arg9 == v3 ? false : true;
        boolean v4 = !v0 || (this.shouldConvertHighResIntPcmToFloat) ? false : true;
        this.canApplyPlaybackParameters = v4;
        if(v0) {
            this.trimmingAudioProcessor.setTrimFrameCount(arg14, arg15);
            this.channelMappingAudioProcessor.setChannelMap(arg13);
            AudioProcessor[] v13 = this.getAvailableAudioProcessors();
            arg14 = v13.length;
            int v4_1 = arg9;
            arg15 = arg11;
            arg9 = 0;
            arg11 = 0;
            while(arg9 < arg14) {
                AudioProcessor v5 = v13[arg9];
                try {
                    v6 = v5.configure(arg15, arg10, v4_1);
                }
                catch(UnhandledFormatException v9) {
                    throw new ConfigurationException(((Throwable)v9));
                }

                arg11 |= ((int)v6);
                if(v5.isActive()) {
                    arg10 = v5.getOutputChannelCount();
                    arg15 = v5.getOutputSampleRateHz();
                    v4_1 = v5.getOutputEncoding();
                }

                ++arg9;
            }

            arg9 = v4_1;
        }
        else {
            arg15 = arg11;
            arg11 = 0;
        }

        int v13_1 = 252;
        switch(arg10) {
            case 1: {
                goto label_89;
            }
            case 2: {
                goto label_88;
            }
            case 3: {
                goto label_86;
            }
            case 4: {
                goto label_84;
            }
            case 5: {
                goto label_82;
            }
            case 6: {
                goto label_80;
            }
            case 7: {
                goto label_78;
            }
            case 8: {
                goto label_76;
            }
        }

        StringBuilder v11 = new StringBuilder();
        v11.append("Unsupported channel count: ");
        v11.append(arg10);
        throw new ConfigurationException(v11.toString());
    label_82:
        v3 = 220;
        goto label_89;
    label_84:
        v3 = 204;
        goto label_89;
    label_86:
        v3 = 28;
        goto label_89;
    label_88:
        v3 = 12;
        goto label_89;
    label_76:
        v3 = C.CHANNEL_OUT_7POINT1_SURROUND;
        goto label_89;
    label_78:
        v3 = 1276;
        goto label_89;
    label_80:
        v3 = 252;
    label_89:
        int v6_1 = 7;
        int v7 = 5;
        if(Util.SDK_INT > 23 || !"foster".equals(Util.DEVICE) || !"NVIDIA".equals(Util.MANUFACTURER)) {
        label_109:
            v13_1 = v3;
        }
        else if(arg10 != 3 && arg10 != v7) {
            if(arg10 != v6_1) {
                goto label_109;
            }
            else {
                v13_1 = C.CHANNEL_OUT_7POINT1_SURROUND;
            }
        }

        if(Util.SDK_INT <= 25 && ("fugu".equals(Util.DEVICE)) && !this.isInputPcm && arg10 == 1) {
            v13_1 = 12;
        }

        if(arg11 == 0 && (this.isInitialized()) && this.outputEncoding == arg9 && this.outputSampleRate == arg15 && this.outputChannelConfig == v13_1) {
            return;
        }

        this.reset();
        this.processingEnabled = v0;
        this.outputSampleRate = arg15;
        this.outputChannelConfig = v13_1;
        this.outputEncoding = arg9;
        arg9 = this.isInputPcm ? Util.getPcmFrameSize(this.outputEncoding, arg10) : -1;
        this.outputPcmFrameSize = arg9;
        if(arg12 != 0) {
            this.bufferSize = arg12;
        }
        else {
            if(this.isInputPcm) {
                arg9 = AudioTrack.getMinBufferSize(arg15, v13_1, this.outputEncoding);
                if(arg9 != -2) {
                }
                else {
                    v1 = false;
                }

                Assertions.checkState(v1);
                arg9 = Util.constrainValue(arg9 * 4, (((int)this.durationUsToFrames(250000))) * this.outputPcmFrameSize, ((int)Math.max(((long)arg9), this.durationUsToFrames(750000) * (((long)this.outputPcmFrameSize)))));
            }
            else {
                if(this.outputEncoding != v7) {
                    if(this.outputEncoding == 6) {
                    }
                    else {
                        arg9 = this.outputEncoding == v6_1 ? 49152 : 294912;
                        goto label_184;
                    }
                }

                arg9 = 20480;
            }

        label_184:
            this.bufferSize = arg9;
        }
    }

    @TargetApi(value=21) private AudioTrack createAudioTrackV21() {
        android.media.AudioAttributes v0 = this.tunneling ? new AudioAttributes$Builder().setContentType(3).setFlags(16).setUsage(1).build() : this.audioAttributes.getAudioAttributesV21();
        android.media.AudioAttributes v2 = v0;
        AudioFormat v3 = new AudioFormat$Builder().setChannelMask(this.outputChannelConfig).setEncoding(this.outputEncoding).setSampleRate(this.outputSampleRate).build();
        int v6 = this.audioSessionId != 0 ? this.audioSessionId : 0;
        return new AudioTrack(v2, v3, this.bufferSize, 1, v6);
    }

    public void disableTunneling() {
        if(this.tunneling) {
            this.tunneling = false;
            this.audioSessionId = 0;
            this.reset();
        }
    }

    private boolean drainAudioProcessorsToEndOfStream() {
        int v0;
        int v1 = -1;
        if(this.drainingAudioProcessorIndex != v1) {
            v0 = 0;
        label_15:
            long v6 = -9223372036854775807L;
            if(this.drainingAudioProcessorIndex < this.activeAudioProcessors.length) {
                AudioProcessor v4 = this.activeAudioProcessors[this.drainingAudioProcessorIndex];
                if(v0 != 0) {
                    v4.queueEndOfStream();
                }

                this.processBuffers(v6);
                if(!v4.isEnded()) {
                    return 0;
                }

                v0 = this.drainingAudioProcessorIndex + 1;
            }
            else {
                if(this.outputBuffer != null) {
                    this.writeBuffer(this.outputBuffer, v6);
                    if(this.outputBuffer != null) {
                        return 0;
                    }
                }

                this.drainingAudioProcessorIndex = v1;
                return 1;
            }
        }
        else if(this.processingEnabled) {
            v0 = 0;
        }
        else {
            v0 = this.activeAudioProcessors.length;
        }

        this.drainingAudioProcessorIndex = v0;
        v0 = 1;
        goto label_15;
    }

    private long durationUsToFrames(long arg3) {
        return arg3 * (((long)this.outputSampleRate)) / 1000000;
    }

    public void enableTunnelingV21(int arg4) {
        boolean v0 = Util.SDK_INT >= 21 ? true : false;
        Assertions.checkState(v0);
        if(!this.tunneling || this.audioSessionId != arg4) {
            this.tunneling = true;
            this.audioSessionId = arg4;
            this.reset();
        }
    }

    private void flushAudioProcessors() {
        int v0;
        for(v0 = 0; v0 < this.activeAudioProcessors.length; ++v0) {
            AudioProcessor v1 = this.activeAudioProcessors[v0];
            v1.flush();
            this.outputBuffers[v0] = v1.getOutput();
        }
    }

    private long framesToDurationUs(long arg3) {
        return arg3 * 1000000 / (((long)this.outputSampleRate));
    }

    private AudioProcessor[] getAvailableAudioProcessors() {
        AudioProcessor[] v0 = this.shouldConvertHighResIntPcmToFloat ? this.toFloatPcmAvailableAudioProcessors : this.toIntPcmAvailableAudioProcessors;
        return v0;
    }

    public long getCurrentPositionUs(boolean arg5) {
        if(this.isInitialized()) {
            if(this.startMediaTimeState == 0) {
            }
            else {
                return this.startMediaTimeUs + this.applySkipping(this.applySpeedup(Math.min(this.audioTrackPositionTracker.getCurrentPositionUs(arg5), this.framesToDurationUs(this.getWrittenFrames()))));
            }
        }

        return -9223372036854775808L;
    }

    private static int getFramesPerEncodedSample(int arg2, ByteBuffer arg3) {
        if(arg2 != 7) {
            if(arg2 == 8) {
            }
            else if(arg2 == 5) {
                return Ac3Util.getAc3SyncframeAudioSampleCount();
            }
            else if(arg2 == 6) {
                return Ac3Util.parseEAc3SyncframeAudioSampleCount(arg3);
            }
            else if(arg2 == 14) {
                arg2 = Ac3Util.findTrueHdSyncframeOffset(arg3);
                return arg2 == -1 ? 0 : Ac3Util.parseTrueHdSyncframeAudioSampleCount(arg3, arg2) * 16;
            }
            else {
                StringBuilder v0 = new StringBuilder();
                v0.append("Unexpected audio encoding: ");
                v0.append(arg2);
                throw new IllegalStateException(v0.toString());
            }
        }

        return DtsUtil.parseDtsAudioSampleCount(arg3);
    }

    public PlaybackParameters getPlaybackParameters() {
        return this.playbackParameters;
    }

    private long getSubmittedFrames() {
        long v0 = this.isInputPcm ? this.submittedPcmBytes / (((long)this.pcmFrameSize)) : this.submittedEncodedFrames;
        return v0;
    }

    private long getWrittenFrames() {
        long v0 = this.isInputPcm ? this.writtenPcmBytes / (((long)this.outputPcmFrameSize)) : this.writtenEncodedFrames;
        return v0;
    }

    public boolean handleBuffer(ByteBuffer arg19, long arg20) {
        DefaultAudioSink v0 = this;
        ByteBuffer v1 = arg19;
        long v2 = arg20;
        boolean v4 = v0.inputBuffer == null || v1 == v0.inputBuffer ? true : false;
        Assertions.checkArgument(v4);
        if(!this.isInitialized()) {
            this.initialize();
            if(v0.playing) {
                this.play();
            }
        }

        if(!v0.audioTrackPositionTracker.mayHandleBuffer(this.getWrittenFrames())) {
            return 0;
        }

        PlaybackParameters v7 = null;
        if(v0.inputBuffer == null) {
            if(!arg19.hasRemaining()) {
                return 1;
            }
            else {
                if(!v0.isInputPcm && v0.framesPerEncodedSample == 0) {
                    v0.framesPerEncodedSample = DefaultAudioSink.getFramesPerEncodedSample(v0.outputEncoding, v1);
                    if(v0.framesPerEncodedSample == 0) {
                        return 1;
                    }
                }

                long v8 = 0;
                if(v0.afterDrainPlaybackParameters != null) {
                    if(!this.drainAudioProcessorsToEndOfStream()) {
                        return 0;
                    }
                    else {
                        PlaybackParameters v4_1 = v0.afterDrainPlaybackParameters;
                        v0.afterDrainPlaybackParameters = v7;
                        v0.playbackParametersCheckpoints.add(new PlaybackParametersCheckpoint(v0.audioProcessorChain.applyPlaybackParameters(v4_1), Math.max(v8, v2), v0.framesToDurationUs(this.getWrittenFrames()), null));
                        this.setupAudioProcessors();
                    }
                }

                if(v0.startMediaTimeState == 0) {
                    v0.startMediaTimeUs = Math.max(v8, v2);
                    v0.startMediaTimeState = 1;
                }
                else {
                    long v5 = v0.startMediaTimeUs + v0.inputFramesToDurationUs(this.getSubmittedFrames());
                    int v9 = 2;
                    if(v0.startMediaTimeState == 1 && Math.abs(v5 - v2) > 200000) {
                        Log.e("AudioTrack", "Discontinuity detected [expected " + v5 + ", got " + v2 + "]");
                        v0.startMediaTimeState = v9;
                    }

                    if(v0.startMediaTimeState != v9) {
                        goto label_107;
                    }

                    v0.startMediaTimeUs += v2 - v5;
                    v0.startMediaTimeState = 1;
                    if(v0.listener == null) {
                        goto label_107;
                    }

                    v0.listener.onPositionDiscontinuity();
                }

            label_107:
                if(v0.isInputPcm) {
                    v0.submittedPcmBytes += ((long)arg19.remaining());
                }
                else {
                    v0.submittedEncodedFrames += ((long)v0.framesPerEncodedSample);
                }

                v0.inputBuffer = v1;
            }
        }

        if(v0.processingEnabled) {
            v0.processBuffers(v2);
        }
        else {
            v0.writeBuffer(v0.inputBuffer, v2);
        }

        if(!v0.inputBuffer.hasRemaining()) {
            v0.inputBuffer = ((ByteBuffer)v7);
            return 1;
        }

        if(v0.audioTrackPositionTracker.isStalled(this.getWrittenFrames())) {
            Log.w("AudioTrack", "Resetting stalled audio track");
            this.reset();
            return 1;
        }

        return 0;
    }

    public void handleDiscontinuity() {
        if(this.startMediaTimeState == 1) {
            this.startMediaTimeState = 2;
        }
    }

    public boolean hasPendingData() {
        boolean v0 = !this.isInitialized() || !this.audioTrackPositionTracker.hasPendingData(this.getWrittenFrames()) ? false : true;
        return v0;
    }

    private void initialize() {
        this.releasingConditionVariable.block();
        this.audioTrack = this.initializeAudioTrack();
        int v0 = this.audioTrack.getAudioSessionId();
        if((DefaultAudioSink.enablePreV21AudioSessionWorkaround) && Util.SDK_INT < 21) {
            if(this.keepSessionIdAudioTrack != null && v0 != this.keepSessionIdAudioTrack.getAudioSessionId()) {
                this.releaseKeepSessionIdAudioTrack();
            }

            if(this.keepSessionIdAudioTrack != null) {
                goto label_21;
            }

            this.keepSessionIdAudioTrack = this.initializeKeepSessionIdAudioTrack(v0);
        }

    label_21:
        if(this.audioSessionId != v0) {
            this.audioSessionId = v0;
            if(this.listener != null) {
                this.listener.onAudioSessionId(v0);
            }
        }

        PlaybackParameters v0_1 = this.canApplyPlaybackParameters ? this.audioProcessorChain.applyPlaybackParameters(this.playbackParameters) : PlaybackParameters.DEFAULT;
        this.playbackParameters = v0_1;
        this.setupAudioProcessors();
        this.audioTrackPositionTracker.setAudioTrack(this.audioTrack, this.outputEncoding, this.outputPcmFrameSize, this.bufferSize);
        this.setVolumeInternal();
    }

    private AudioTrack initializeAudioTrack() {
        AudioTrack v0;
        if(Util.SDK_INT >= 21) {
            v0 = this.createAudioTrackV21();
        }
        else {
            int v2 = Util.getStreamTypeForAudioUsage(this.audioAttributes.usage);
            v0 = this.audioSessionId == 0 ? new AudioTrack(v2, this.outputSampleRate, this.outputChannelConfig, this.outputEncoding, this.bufferSize, 1) : new AudioTrack(v2, this.outputSampleRate, this.outputChannelConfig, this.outputEncoding, this.bufferSize, 1, this.audioSessionId);
        }

        int v1 = v0.getState();
        if(v1 == 1) {
            return v0;
        }

        try {
            v0.release();
            goto label_35;
        }
        catch(Exception ) {
        label_35:
            throw new InitializationException(v1, this.outputSampleRate, this.outputChannelConfig, this.bufferSize);
        }
    }

    private AudioTrack initializeKeepSessionIdAudioTrack(int arg10) {
        return new AudioTrack(3, 4000, 4, 2, 2, 0, arg10);
    }

    private long inputFramesToDurationUs(long arg3) {
        return arg3 * 1000000 / (((long)this.inputSampleRate));
    }

    public boolean isEncodingSupported(int arg4) {
        boolean v1 = false;
        if(Util.isEncodingLinearPcm(arg4)) {
            if(arg4 != 4 || Util.SDK_INT >= 21) {
                v1 = true;
            }

            return v1;
        }

        if(this.audioCapabilities != null && (this.audioCapabilities.supportsEncoding(arg4))) {
            v1 = true;
        }

        return v1;
    }

    public boolean isEnded() {
        boolean v0;
        if(this.isInitialized()) {
            if((this.handledEndOfStream) && !this.hasPendingData()) {
                goto label_9;
            }

            v0 = false;
        }
        else {
        label_9:
            v0 = true;
        }

        return v0;
    }

    private boolean isInitialized() {
        boolean v0 = this.audioTrack != null ? true : false;
        return v0;
    }

    public void pause() {
        this.playing = false;
        if((this.isInitialized()) && (this.audioTrackPositionTracker.pause())) {
            this.audioTrack.pause();
        }
    }

    public void play() {
        this.playing = true;
        if(this.isInitialized()) {
            this.audioTrackPositionTracker.start();
            this.audioTrack.play();
        }
    }

    public void playToEndOfStream() {
        if(!this.handledEndOfStream) {
            if(!this.isInitialized()) {
            }
            else if(this.drainAudioProcessorsToEndOfStream()) {
                this.audioTrackPositionTracker.handleEndOfStream(this.getWrittenFrames());
                this.audioTrack.stop();
                this.bytesUntilNextAvSync = 0;
                this.handledEndOfStream = true;
            }
        }
    }

    private void processBuffers(long arg6) {
        ByteBuffer v2;
        int v0 = this.activeAudioProcessors.length;
        int v1;
        for(v1 = v0; v1 >= 0; --v1) {
            if(v1 > 0) {
                v2 = this.outputBuffers[v1 - 1];
            }
            else if(this.inputBuffer != null) {
                v2 = this.inputBuffer;
            }
            else {
                v2 = AudioProcessor.EMPTY_BUFFER;
            }

            if(v1 == v0) {
                this.writeBuffer(v2, arg6);
            }
            else {
                AudioProcessor v3 = this.activeAudioProcessors[v1];
                v3.queueInput(v2);
                ByteBuffer v3_1 = v3.getOutput();
                this.outputBuffers[v1] = v3_1;
                if(v3_1.hasRemaining()) {
                    ++v1;
                    continue;
                }
            }

            if(v2.hasRemaining()) {
                return;
            }
        }
    }

    public void release() {
        this.reset();
        this.releaseKeepSessionIdAudioTrack();
        AudioProcessor[] v0 = this.toIntPcmAvailableAudioProcessors;
        int v1 = v0.length;
        int v3;
        for(v3 = 0; v3 < v1; ++v3) {
            v0[v3].reset();
        }

        v0 = this.toFloatPcmAvailableAudioProcessors;
        v1 = v0.length;
        for(v3 = 0; v3 < v1; ++v3) {
            v0[v3].reset();
        }

        this.audioSessionId = 0;
        this.playing = false;
    }

    private void releaseKeepSessionIdAudioTrack() {
        if(this.keepSessionIdAudioTrack == null) {
            return;
        }

        AudioTrack v0 = this.keepSessionIdAudioTrack;
        this.keepSessionIdAudioTrack = null;
        new Thread(v0) {
            public void run() {
                this.val$toRelease.release();
            }
        }.start();
    }

    public void reset() {
        if(this.isInitialized()) {
            long v0 = 0;
            this.submittedPcmBytes = v0;
            this.submittedEncodedFrames = v0;
            this.writtenPcmBytes = v0;
            this.writtenEncodedFrames = v0;
            this.framesPerEncodedSample = 0;
            PlaybackParameters v4 = null;
            if(this.afterDrainPlaybackParameters != null) {
                this.playbackParameters = this.afterDrainPlaybackParameters;
                this.afterDrainPlaybackParameters = v4;
            }
            else if(!this.playbackParametersCheckpoints.isEmpty()) {
                this.playbackParameters = PlaybackParametersCheckpoint.access$200(this.playbackParametersCheckpoints.getLast());
            }

            this.playbackParametersCheckpoints.clear();
            this.playbackParametersOffsetUs = v0;
            this.playbackParametersPositionUs = v0;
            this.inputBuffer = ((ByteBuffer)v4);
            this.outputBuffer = ((ByteBuffer)v4);
            this.flushAudioProcessors();
            this.handledEndOfStream = false;
            this.drainingAudioProcessorIndex = -1;
            this.avSyncHeader = ((ByteBuffer)v4);
            this.bytesUntilNextAvSync = 0;
            this.startMediaTimeState = 0;
            if(this.audioTrackPositionTracker.isPlaying()) {
                this.audioTrack.pause();
            }

            AudioTrack v0_1 = this.audioTrack;
            this.audioTrack = ((AudioTrack)v4);
            this.audioTrackPositionTracker.reset();
            this.releasingConditionVariable.close();
            new Thread(v0_1) {
                public void run() {
                    try {
                        this.val$toRelease.flush();
                        this.val$toRelease.release();
                    }
                    catch(Throwable v0) {
                        DefaultAudioSink.this.releasingConditionVariable.open();
                        throw v0;
                    }

                    DefaultAudioSink.this.releasingConditionVariable.open();
                }
            }.start();
        }
    }

    public void setAudioAttributes(AudioAttributes arg2) {
        if(this.audioAttributes.equals(arg2)) {
            return;
        }

        this.audioAttributes = arg2;
        if(this.tunneling) {
            return;
        }

        this.reset();
        this.audioSessionId = 0;
    }

    public void setAudioSessionId(int arg2) {
        if(this.audioSessionId != arg2) {
            this.audioSessionId = arg2;
            this.reset();
        }
    }

    public void setListener(com.google.android.exoplayer2.audio.AudioSink$Listener arg1) {
        this.listener = arg1;
    }

    public PlaybackParameters setPlaybackParameters(PlaybackParameters arg2) {
        PlaybackParameters v0;
        if((this.isInitialized()) && !this.canApplyPlaybackParameters) {
            this.playbackParameters = PlaybackParameters.DEFAULT;
            return this.playbackParameters;
        }

        if(this.afterDrainPlaybackParameters != null) {
            v0 = this.afterDrainPlaybackParameters;
        }
        else if(!this.playbackParametersCheckpoints.isEmpty()) {
            v0 = PlaybackParametersCheckpoint.access$200(this.playbackParametersCheckpoints.getLast());
        }
        else {
            v0 = this.playbackParameters;
        }

        if(!arg2.equals(v0)) {
            if(this.isInitialized()) {
                this.afterDrainPlaybackParameters = arg2;
            }
            else {
                this.playbackParameters = this.audioProcessorChain.applyPlaybackParameters(arg2);
            }
        }

        return this.playbackParameters;
    }

    public void setVolume(float arg2) {
        if(this.volume != arg2) {
            this.volume = arg2;
            this.setVolumeInternal();
        }
    }

    private void setVolumeInternal() {
        if(!this.isInitialized()) {
        }
        else if(Util.SDK_INT >= 21) {
            DefaultAudioSink.setVolumeInternalV21(this.audioTrack, this.volume);
        }
        else {
            DefaultAudioSink.setVolumeInternalV3(this.audioTrack, this.volume);
        }
    }

    @TargetApi(value=21) private static void setVolumeInternalV21(AudioTrack arg0, float arg1) {
        arg0.setVolume(arg1);
    }

    private static void setVolumeInternalV3(AudioTrack arg0, float arg1) {
        arg0.setStereoVolume(arg1, arg1);
    }

    private void setupAudioProcessors() {
        ArrayList v0 = new ArrayList();
        AudioProcessor[] v1 = this.getAvailableAudioProcessors();
        int v2 = v1.length;
        int v3;
        for(v3 = 0; v3 < v2; ++v3) {
            AudioProcessor v4 = v1[v3];
            if(v4.isActive()) {
                v0.add(v4);
            }
            else {
                v4.flush();
            }
        }

        int v1_1 = v0.size();
        this.activeAudioProcessors = v0.toArray(new AudioProcessor[v1_1]);
        this.outputBuffers = new ByteBuffer[v1_1];
        this.flushAudioProcessors();
    }

    private void writeBuffer(ByteBuffer arg13, long arg14) {
        int v0_1;
        if(!arg13.hasRemaining()) {
            return;
        }

        boolean v1 = true;
        int v2 = 21;
        int v3 = 0;
        if(this.outputBuffer != null) {
            boolean v0 = this.outputBuffer == arg13 ? true : false;
            Assertions.checkArgument(v0);
        }
        else {
            this.outputBuffer = arg13;
            if(Util.SDK_INT >= v2) {
                goto label_31;
            }

            v0_1 = arg13.remaining();
            if(this.preV21OutputBuffer == null || this.preV21OutputBuffer.length < v0_1) {
                this.preV21OutputBuffer = new byte[v0_1];
            }

            int v4 = arg13.position();
            arg13.get(this.preV21OutputBuffer, 0, v0_1);
            arg13.position(v4);
            this.preV21OutputBufferOffset = 0;
        }

    label_31:
        v0_1 = arg13.remaining();
        if(Util.SDK_INT < v2) {
            int v14 = this.audioTrackPositionTracker.getAvailableBufferSize(this.writtenPcmBytes);
            if(v14 > 0) {
                v3 = this.audioTrack.write(this.preV21OutputBuffer, this.preV21OutputBufferOffset, Math.min(v0_1, v14));
                if(v3 > 0) {
                    this.preV21OutputBufferOffset += v3;
                    arg13.position(arg13.position() + v3);
                }
            }
        }
        else if(this.tunneling) {
            if(arg14 != -9223372036854775807L) {
            }
            else {
                v1 = false;
            }

            Assertions.checkState(v1);
            v3 = this.writeNonBlockingWithAvSyncV21(this.audioTrack, arg13, v0_1, arg14);
        }
        else {
            v3 = DefaultAudioSink.writeNonBlockingV21(this.audioTrack, arg13, v0_1);
        }

        this.lastFeedElapsedRealtimeMs = SystemClock.elapsedRealtime();
        if(v3 >= 0) {
            if(this.isInputPcm) {
                this.writtenPcmBytes += ((long)v3);
            }

            if(v3 == v0_1) {
                if(!this.isInputPcm) {
                    this.writtenEncodedFrames += ((long)this.framesPerEncodedSample);
                }

                this.outputBuffer = null;
            }

            return;
        }

        throw new WriteException(v3);
    }

    @TargetApi(value=21) private static int writeNonBlockingV21(AudioTrack arg1, ByteBuffer arg2, int arg3) {
        return arg1.write(arg2, arg3, 1);
    }

    @TargetApi(value=21) private int writeNonBlockingWithAvSyncV21(AudioTrack arg6, ByteBuffer arg7, int arg8, long arg9) {
        if(this.avSyncHeader == null) {
            this.avSyncHeader = ByteBuffer.allocate(16);
            this.avSyncHeader.order(ByteOrder.BIG_ENDIAN);
            this.avSyncHeader.putInt(1431633921);
        }

        if(this.bytesUntilNextAvSync == 0) {
            this.avSyncHeader.putInt(4, arg8);
            this.avSyncHeader.putLong(8, arg9 * 1000);
            this.avSyncHeader.position(0);
            this.bytesUntilNextAvSync = arg8;
        }

        int v9 = this.avSyncHeader.remaining();
        if(v9 > 0) {
            int v10 = arg6.write(this.avSyncHeader, v9, 1);
            if(v10 < 0) {
                this.bytesUntilNextAvSync = 0;
                return v10;
            }
            else if(v10 < v9) {
                return 0;
            }
        }

        int v6 = DefaultAudioSink.writeNonBlockingV21(arg6, arg7, arg8);
        if(v6 < 0) {
            this.bytesUntilNextAvSync = 0;
            return v6;
        }

        this.bytesUntilNextAvSync -= v6;
        return v6;
    }
}

