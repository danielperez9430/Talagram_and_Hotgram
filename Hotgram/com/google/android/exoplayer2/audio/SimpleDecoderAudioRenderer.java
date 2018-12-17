package com.google.android.exoplayer2.audio;

import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import com.google.android.exoplayer2.BaseRenderer;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.FormatHolder;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.decoder.DecoderCounters;
import com.google.android.exoplayer2.decoder.DecoderInputBuffer;
import com.google.android.exoplayer2.decoder.SimpleDecoder;
import com.google.android.exoplayer2.decoder.SimpleOutputBuffer;
import com.google.android.exoplayer2.drm.DrmInitData;
import com.google.android.exoplayer2.drm.DrmSession;
import com.google.android.exoplayer2.drm.DrmSessionManager;
import com.google.android.exoplayer2.drm.ExoMediaCrypto;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.MediaClock;
import com.google.android.exoplayer2.util.TraceUtil;
import com.google.android.exoplayer2.util.Util;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public abstract class SimpleDecoderAudioRenderer extends BaseRenderer implements MediaClock {
    class com.google.android.exoplayer2.audio.SimpleDecoderAudioRenderer$1 {
    }

    final class AudioSinkListener implements Listener {
        AudioSinkListener(SimpleDecoderAudioRenderer arg1, com.google.android.exoplayer2.audio.SimpleDecoderAudioRenderer$1 arg2) {
            this(arg1);
        }

        private AudioSinkListener(SimpleDecoderAudioRenderer arg1) {
            SimpleDecoderAudioRenderer.this = arg1;
            super();
        }

        public void onAudioSessionId(int arg2) {
            SimpleDecoderAudioRenderer.this.eventDispatcher.audioSessionId(arg2);
            SimpleDecoderAudioRenderer.this.onAudioSessionId(arg2);
        }

        public void onPositionDiscontinuity() {
            SimpleDecoderAudioRenderer.this.onAudioTrackPositionDiscontinuity();
            SimpleDecoderAudioRenderer.this.allowPositionDiscontinuity = true;
        }

        public void onUnderrun(int arg9, long arg10, long arg12) {
            SimpleDecoderAudioRenderer.this.eventDispatcher.audioTrackUnderrun(arg9, arg10, arg12);
            SimpleDecoderAudioRenderer.this.onAudioTrackUnderrun(arg9, arg10, arg12);
        }
    }

    @Retention(value=RetentionPolicy.SOURCE) @interface ReinitializationState {
    }

    private static final int REINITIALIZATION_STATE_NONE = 0;
    private static final int REINITIALIZATION_STATE_SIGNAL_END_OF_STREAM = 1;
    private static final int REINITIALIZATION_STATE_WAIT_END_OF_STREAM = 2;
    private boolean allowFirstBufferPositionDiscontinuity;
    private boolean allowPositionDiscontinuity;
    private final AudioSink audioSink;
    private boolean audioTrackNeedsConfigure;
    private long currentPositionUs;
    private SimpleDecoder decoder;
    private DecoderCounters decoderCounters;
    private boolean decoderReceivedBuffers;
    private int decoderReinitializationState;
    private DrmSession drmSession;
    private final DrmSessionManager drmSessionManager;
    private int encoderDelay;
    private int encoderPadding;
    private final EventDispatcher eventDispatcher;
    private final DecoderInputBuffer flagsOnlyBuffer;
    private final FormatHolder formatHolder;
    private DecoderInputBuffer inputBuffer;
    private Format inputFormat;
    private boolean inputStreamEnded;
    private SimpleOutputBuffer outputBuffer;
    private boolean outputStreamEnded;
    private DrmSession pendingDrmSession;
    private final boolean playClearSamplesWithoutKeys;
    private boolean waitingForKeys;

    public SimpleDecoderAudioRenderer() {
        this(null, null, new AudioProcessor[0]);
    }

    public SimpleDecoderAudioRenderer(Handler arg8, AudioRendererEventListener arg9, AudioProcessor[] arg10) {
        this(arg8, arg9, null, null, false, arg10);
    }

    public SimpleDecoderAudioRenderer(Handler arg9, AudioRendererEventListener arg10, AudioCapabilities arg11) {
        this(arg9, arg10, arg11, null, false, new AudioProcessor[0]);
    }

    public SimpleDecoderAudioRenderer(Handler arg7, AudioRendererEventListener arg8, AudioCapabilities arg9, DrmSessionManager arg10, boolean arg11, AudioProcessor[] arg12) {
        this(arg7, arg8, arg10, arg11, new DefaultAudioSink(arg9, arg12));
    }

    public SimpleDecoderAudioRenderer(Handler arg2, AudioRendererEventListener arg3, DrmSessionManager arg4, boolean arg5, AudioSink arg6) {
        super(1);
        this.drmSessionManager = arg4;
        this.playClearSamplesWithoutKeys = arg5;
        this.eventDispatcher = new EventDispatcher(arg2, arg3);
        this.audioSink = arg6;
        arg6.setListener(new AudioSinkListener(this, null));
        this.formatHolder = new FormatHolder();
        this.flagsOnlyBuffer = DecoderInputBuffer.newFlagsOnlyInstance();
        this.decoderReinitializationState = 0;
        this.audioTrackNeedsConfigure = true;
    }

    static EventDispatcher access$100(SimpleDecoderAudioRenderer arg0) {
        return arg0.eventDispatcher;
    }

    static boolean access$202(SimpleDecoderAudioRenderer arg0, boolean arg1) {
        arg0.allowPositionDiscontinuity = arg1;
        return arg1;
    }

    protected abstract SimpleDecoder createDecoder(Format arg1, ExoMediaCrypto arg2);

    private boolean drainOutputBuffer() {
        if(this.outputBuffer == null) {
            this.outputBuffer = this.decoder.dequeueOutputBuffer();
            if(this.outputBuffer == null) {
                return 0;
            }
            else {
                this.decoderCounters.skippedOutputBufferCount += this.outputBuffer.skippedOutputBufferCount;
            }
        }

        SimpleOutputBuffer v2 = null;
        if(this.outputBuffer.isEndOfStream()) {
            if(this.decoderReinitializationState == 2) {
                this.releaseDecoder();
                this.maybeInitDecoder();
                this.audioTrackNeedsConfigure = true;
            }
            else {
                this.outputBuffer.release();
                this.outputBuffer = v2;
                this.processEndOfStream();
            }

            return 0;
        }

        if(this.audioTrackNeedsConfigure) {
            Format v0 = this.getOutputFormat();
            this.audioSink.configure(v0.pcmEncoding, v0.channelCount, v0.sampleRate, 0, null, this.encoderDelay, this.encoderPadding);
            this.audioTrackNeedsConfigure = false;
        }

        if(this.audioSink.handleBuffer(this.outputBuffer.data, this.outputBuffer.timeUs)) {
            ++this.decoderCounters.renderedOutputBufferCount;
            this.outputBuffer.release();
            this.outputBuffer = v2;
            return 1;
        }

        return 0;
    }

    private boolean feedInputBuffer() {
        if(this.decoder != null) {
            int v2 = 2;
            if(this.decoderReinitializationState != v2) {
                if(this.inputStreamEnded) {
                }
                else {
                    if(this.inputBuffer == null) {
                        this.inputBuffer = this.decoder.dequeueInputBuffer();
                        if(this.inputBuffer == null) {
                            return 0;
                        }
                    }

                    DecoderInputBuffer v3 = null;
                    if(this.decoderReinitializationState == 1) {
                        this.inputBuffer.setFlags(4);
                        this.decoder.queueInputBuffer(this.inputBuffer);
                        this.inputBuffer = v3;
                        this.decoderReinitializationState = v2;
                        return 0;
                    }

                    int v0 = this.waitingForKeys ? -4 : this.readSource(this.formatHolder, this.inputBuffer, false);
                    if(v0 == -3) {
                        return 0;
                    }

                    if(v0 == -5) {
                        this.onInputFormatChanged(this.formatHolder.format);
                        return 1;
                    }

                    if(this.inputBuffer.isEndOfStream()) {
                        this.inputStreamEnded = true;
                        this.decoder.queueInputBuffer(this.inputBuffer);
                        this.inputBuffer = v3;
                        return 0;
                    }

                    this.waitingForKeys = this.shouldWaitForKeys(this.inputBuffer.isEncrypted());
                    if(this.waitingForKeys) {
                        return 0;
                    }

                    this.inputBuffer.flip();
                    this.onQueueInputBuffer(this.inputBuffer);
                    this.decoder.queueInputBuffer(this.inputBuffer);
                    this.decoderReceivedBuffers = true;
                    ++this.decoderCounters.inputBufferCount;
                    this.inputBuffer = v3;
                    return 1;
                }
            }
        }

        return 0;
    }

    private void flushDecoder() {
        this.waitingForKeys = false;
        if(this.decoderReinitializationState != 0) {
            this.releaseDecoder();
            this.maybeInitDecoder();
        }
        else {
            DecoderInputBuffer v1 = null;
            this.inputBuffer = v1;
            if(this.outputBuffer != null) {
                this.outputBuffer.release();
                this.outputBuffer = ((SimpleOutputBuffer)v1);
            }

            this.decoder.flush();
            this.decoderReceivedBuffers = false;
        }
    }

    public MediaClock getMediaClock() {
        return this;
    }

    protected Format getOutputFormat() {
        return Format.createAudioSampleFormat(null, "audio/raw", null, -1, -1, this.inputFormat.channelCount, this.inputFormat.sampleRate, 2, null, null, 0, null);
    }

    public PlaybackParameters getPlaybackParameters() {
        return this.audioSink.getPlaybackParameters();
    }

    public long getPositionUs() {
        if(this.getState() == 2) {
            this.updateCurrentPosition();
        }

        return this.currentPositionUs;
    }

    public void handleMessage(int arg1, Object arg2) {
        switch(arg1) {
            case 2: {
                this.audioSink.setVolume(((Float)arg2).floatValue());
                break;
            }
            case 3: {
                this.audioSink.setAudioAttributes(((AudioAttributes)arg2));
                break;
            }
            default: {
                super.handleMessage(arg1, arg2);
                break;
            }
        }
    }

    public boolean isEnded() {
        boolean v0 = !this.outputStreamEnded || !this.audioSink.isEnded() ? false : true;
        return v0;
    }

    public boolean isReady() {
        boolean v0;
        if(!this.audioSink.hasPendingData()) {
            if(this.inputFormat != null && !this.waitingForKeys) {
                if(this.isSourceReady()) {
                    goto label_14;
                }
                else if(this.outputBuffer != null) {
                    goto label_14;
                }
            }

            v0 = false;
        }
        else {
        label_14:
            v0 = true;
        }

        return v0;
    }

    private void maybeInitDecoder() {
        if(this.decoder != null) {
            return;
        }

        this.drmSession = this.pendingDrmSession;
        ExoMediaCrypto v0 = null;
        if(this.drmSession != null) {
            v0 = this.drmSession.getMediaCrypto();
            if(v0 == null) {
                if(this.drmSession.getError() != null) {
                }
                else {
                    return;
                }
            }
        }

        try {
            long v1 = SystemClock.elapsedRealtime();
            TraceUtil.beginSection("createAudioDecoder");
            this.decoder = this.createDecoder(this.inputFormat, v0);
            TraceUtil.endSection();
            long v5 = SystemClock.elapsedRealtime();
            this.eventDispatcher.decoderInitialized(this.decoder.getName(), v5, v5 - v1);
            ++this.decoderCounters.decoderInitCount;
            return;
        }
        catch(AudioDecoderException v0_1) {
            throw ExoPlaybackException.createForRenderer(((Exception)v0_1), this.getIndex());
        }
    }

    protected void onAudioSessionId(int arg1) {
    }

    protected void onAudioTrackPositionDiscontinuity() {
    }

    protected void onAudioTrackUnderrun(int arg1, long arg2, long arg4) {
    }

    protected void onDisabled() {
        Format v0 = null;
        this.inputFormat = v0;
        this.audioTrackNeedsConfigure = true;
        this.waitingForKeys = false;
        try {
            this.releaseDecoder();
            this.audioSink.release();
        }
        catch(Throwable v1) {
            try {
                if(this.drmSession != null) {
                    this.drmSessionManager.releaseSession(this.drmSession);
                }
            }
            catch(Throwable v1) {
                try {
                    if(this.pendingDrmSession != null && this.pendingDrmSession != this.drmSession) {
                        this.drmSessionManager.releaseSession(this.pendingDrmSession);
                    }
                }
                catch(Throwable v1) {
                    this.drmSession = ((DrmSession)v0);
                    this.pendingDrmSession = ((DrmSession)v0);
                    this.decoderCounters.ensureUpdated();
                    this.eventDispatcher.disabled(this.decoderCounters);
                    throw v1;
                }

                this.drmSession = ((DrmSession)v0);
                this.pendingDrmSession = ((DrmSession)v0);
                this.decoderCounters.ensureUpdated();
                this.eventDispatcher.disabled(this.decoderCounters);
                throw v1;
            }

            try {
                if(this.pendingDrmSession != null && this.pendingDrmSession != this.drmSession) {
                    this.drmSessionManager.releaseSession(this.pendingDrmSession);
                }
            }
            catch(Throwable v1) {
                this.drmSession = ((DrmSession)v0);
                this.pendingDrmSession = ((DrmSession)v0);
                this.decoderCounters.ensureUpdated();
                this.eventDispatcher.disabled(this.decoderCounters);
                throw v1;
            }

            this.drmSession = ((DrmSession)v0);
            this.pendingDrmSession = ((DrmSession)v0);
            this.decoderCounters.ensureUpdated();
            this.eventDispatcher.disabled(this.decoderCounters);
            throw v1;
        }

        try {
            if(this.drmSession != null) {
                this.drmSessionManager.releaseSession(this.drmSession);
            }
        }
        catch(Throwable v1) {
            try {
                if(this.pendingDrmSession != null && this.pendingDrmSession != this.drmSession) {
                    this.drmSessionManager.releaseSession(this.pendingDrmSession);
                }
            }
            catch(Throwable v1) {
                this.drmSession = ((DrmSession)v0);
                this.pendingDrmSession = ((DrmSession)v0);
                this.decoderCounters.ensureUpdated();
                this.eventDispatcher.disabled(this.decoderCounters);
                throw v1;
            }

            this.drmSession = ((DrmSession)v0);
            this.pendingDrmSession = ((DrmSession)v0);
            this.decoderCounters.ensureUpdated();
            this.eventDispatcher.disabled(this.decoderCounters);
            throw v1;
        }

        try {
            if(this.pendingDrmSession != null && this.pendingDrmSession != this.drmSession) {
                this.drmSessionManager.releaseSession(this.pendingDrmSession);
            }
        }
        catch(Throwable v1) {
            this.drmSession = ((DrmSession)v0);
            this.pendingDrmSession = ((DrmSession)v0);
            this.decoderCounters.ensureUpdated();
            this.eventDispatcher.disabled(this.decoderCounters);
            throw v1;
        }

        this.drmSession = ((DrmSession)v0);
        this.pendingDrmSession = ((DrmSession)v0);
        this.decoderCounters.ensureUpdated();
        this.eventDispatcher.disabled(this.decoderCounters);
    }

    protected void onEnabled(boolean arg2) {
        this.decoderCounters = new DecoderCounters();
        this.eventDispatcher.enabled(this.decoderCounters);
        int v2 = this.getConfiguration().tunnelingAudioSessionId;
        if(v2 != 0) {
            this.audioSink.enableTunnelingV21(v2);
        }
        else {
            this.audioSink.disableTunneling();
        }
    }

    private void onInputFormatChanged(Format arg5) {
        Format v0 = this.inputFormat;
        this.inputFormat = arg5;
        DrmInitData v1 = this.inputFormat.drmInitData;
        DrmSession v2 = null;
        if(v0 == null) {
            Object v0_1 = v2;
        }
        else {
            DrmInitData v0_2 = v0.drmInitData;
        }

        if((Util.areEqual(v1, v0_2) ^ 1) != 0) {
            if(this.inputFormat.drmInitData == null) {
                this.pendingDrmSession = v2;
            }
            else if(this.drmSessionManager != null) {
                this.pendingDrmSession = this.drmSessionManager.acquireSession(Looper.myLooper(), this.inputFormat.drmInitData);
                if(this.pendingDrmSession == this.drmSession) {
                    this.drmSessionManager.releaseSession(this.pendingDrmSession);
                }
            }
            else {
                throw ExoPlaybackException.createForRenderer(new IllegalStateException("Media requires a DrmSessionManager"), this.getIndex());
            }
        }

        if(this.decoderReceivedBuffers) {
            this.decoderReinitializationState = 1;
        }
        else {
            this.releaseDecoder();
            this.maybeInitDecoder();
            this.audioTrackNeedsConfigure = true;
        }

        this.encoderDelay = arg5.encoderDelay;
        this.encoderPadding = arg5.encoderPadding;
        this.eventDispatcher.inputFormatChanged(arg5);
    }

    protected void onPositionReset(long arg1, boolean arg3) {
        this.audioSink.reset();
        this.currentPositionUs = arg1;
        this.allowFirstBufferPositionDiscontinuity = true;
        this.allowPositionDiscontinuity = true;
        this.inputStreamEnded = false;
        this.outputStreamEnded = false;
        if(this.decoder != null) {
            this.flushDecoder();
        }
    }

    private void onQueueInputBuffer(DecoderInputBuffer arg6) {
        if((this.allowFirstBufferPositionDiscontinuity) && !arg6.isDecodeOnly()) {
            if(Math.abs(arg6.timeUs - this.currentPositionUs) > 500000) {
                this.currentPositionUs = arg6.timeUs;
            }

            this.allowFirstBufferPositionDiscontinuity = false;
        }
    }

    protected void onStarted() {
        this.audioSink.play();
    }

    protected void onStopped() {
        this.updateCurrentPosition();
        this.audioSink.pause();
    }

    private void processEndOfStream() {
        this.outputStreamEnded = true;
        try {
            this.audioSink.playToEndOfStream();
            return;
        }
        catch(WriteException v0) {
            throw ExoPlaybackException.createForRenderer(((Exception)v0), this.getIndex());
        }
    }

    private void releaseDecoder() {
        if(this.decoder == null) {
            return;
        }

        this.inputBuffer = null;
        this.outputBuffer = null;
        this.decoder.release();
        this.decoder = null;
        ++this.decoderCounters.decoderReleaseCount;
        this.decoderReinitializationState = 0;
        this.decoderReceivedBuffers = false;
    }

    public void render(long arg1, long arg3) {
        if(this.outputStreamEnded) {
            try {
                this.audioSink.playToEndOfStream();
                return;
            }
            catch(WriteException v1) {
                throw ExoPlaybackException.createForRenderer(((Exception)v1), this.getIndex());
            }
        }

        if(this.inputFormat == null) {
            this.flagsOnlyBuffer.clear();
            int v1_1 = this.readSource(this.formatHolder, this.flagsOnlyBuffer, true);
            if(v1_1 == -5) {
                this.onInputFormatChanged(this.formatHolder.format);
            }
            else {
                if(v1_1 == -4) {
                    Assertions.checkState(this.flagsOnlyBuffer.isEndOfStream());
                    this.inputStreamEnded = true;
                    this.processEndOfStream();
                }

                return;
            }
        }

        this.maybeInitDecoder();
        if(this.decoder != null) {
            try {
                TraceUtil.beginSection("drainAndFeed");
                while(this.drainOutputBuffer()) {
                }

                while(this.feedInputBuffer()) {
                }

                TraceUtil.endSection();
            }
            catch(WriteException v1) {
                goto label_49;
            }

            this.decoderCounters.ensureUpdated();
            return;
        label_49:
            throw ExoPlaybackException.createForRenderer(((Exception)v1), this.getIndex());
        }
    }

    public PlaybackParameters setPlaybackParameters(PlaybackParameters arg2) {
        return this.audioSink.setPlaybackParameters(arg2);
    }

    private boolean shouldWaitForKeys(boolean arg4) {
        if(this.drmSession != null && ((arg4) || !this.playClearSamplesWithoutKeys)) {
            int v4 = this.drmSession.getState();
            boolean v0 = true;
            if(v4 != 1) {
                if(v4 != 4) {
                }
                else {
                    v0 = false;
                }

                return v0;
            }

            throw ExoPlaybackException.createForRenderer(this.drmSession.getError(), this.getIndex());
        }

        return 0;
    }

    public final int supportsFormat(Format arg3) {
        int v3 = this.supportsFormatInternal(this.drmSessionManager, arg3);
        if(v3 <= 2) {
            return v3;
        }

        int v0 = Util.SDK_INT >= 21 ? 32 : 0;
        return v3 | (v0 | 8);
    }

    protected abstract int supportsFormatInternal(DrmSessionManager arg1, Format arg2);

    protected final boolean supportsOutputEncoding(int arg2) {
        return this.audioSink.isEncodingSupported(arg2);
    }

    private void updateCurrentPosition() {
        long v0 = this.audioSink.getCurrentPositionUs(this.isEnded());
        if(v0 != -9223372036854775808L) {
            if(this.allowPositionDiscontinuity) {
            }
            else {
                v0 = Math.max(this.currentPositionUs, v0);
            }

            this.currentPositionUs = v0;
            this.allowPositionDiscontinuity = false;
        }
    }
}

