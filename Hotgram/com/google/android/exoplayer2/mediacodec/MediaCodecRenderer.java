package com.google.android.exoplayer2.mediacodec;

import android.annotation.TargetApi;
import android.media.MediaCodec$BufferInfo;
import android.media.MediaCodec$CodecException;
import android.media.MediaCodec$CryptoException;
import android.media.MediaCodec$CryptoInfo;
import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.os.Bundle;
import android.os.Looper;
import android.os.SystemClock;
import android.util.Log;
import com.google.android.exoplayer2.BaseRenderer;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.FormatHolder;
import com.google.android.exoplayer2.decoder.DecoderCounters;
import com.google.android.exoplayer2.decoder.DecoderInputBuffer;
import com.google.android.exoplayer2.drm.DrmInitData;
import com.google.android.exoplayer2.drm.DrmSession;
import com.google.android.exoplayer2.drm.DrmSessionManager;
import com.google.android.exoplayer2.drm.ExoMediaCrypto;
import com.google.android.exoplayer2.drm.FrameworkMediaCrypto;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.NalUnitUtil;
import com.google.android.exoplayer2.util.TraceUtil;
import com.google.android.exoplayer2.util.Util;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.nio.ByteBuffer;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

@TargetApi(value=16) public abstract class MediaCodecRenderer extends BaseRenderer {
    @Retention(value=RetentionPolicy.SOURCE) @interface AdaptationWorkaroundMode {
    }

    public class DecoderInitializationException extends Exception {
        private static final int CUSTOM_ERROR_CODE_BASE = -50000;
        private static final int DECODER_QUERY_ERROR = -49998;
        private static final int NO_SUITABLE_DECODER_ERROR = -49999;
        public final String decoderName;
        public final String diagnosticInfo;
        public final DecoderInitializationException fallbackDecoderInitializationException;
        public final String mimeType;
        public final boolean secureDecoderRequired;

        public DecoderInitializationException(Format arg11, Throwable arg12, boolean arg13, int arg14) {
            this("Decoder init failed: [" + arg14 + "], " + arg11, arg12, arg11.sampleMimeType, arg13, null, DecoderInitializationException.buildCustomDiagnosticInfo(arg14), null);
        }

        public DecoderInitializationException(Format arg11, Throwable arg12, boolean arg13, String arg14) {
            String v3 = "Decoder init failed: " + arg14 + ", " + arg11;
            String v5 = arg11.sampleMimeType;
            String v11 = Util.SDK_INT >= 21 ? DecoderInitializationException.getDiagnosticInfoV21(arg12) : null;
            String v8 = v11;
            this(v3, arg12, v5, arg13, arg14, v8, null);
        }

        private DecoderInitializationException(String arg1, Throwable arg2, String arg3, boolean arg4, String arg5, String arg6, DecoderInitializationException arg7) {
            super(arg1, arg2);
            this.mimeType = arg3;
            this.secureDecoderRequired = arg4;
            this.decoderName = arg5;
            this.diagnosticInfo = arg6;
            this.fallbackDecoderInitializationException = arg7;
        }

        static DecoderInitializationException access$000(DecoderInitializationException arg0, DecoderInitializationException arg1) {
            return arg0.copyWithFallbackException(arg1);
        }

        private static String buildCustomDiagnosticInfo(int arg3) {
            String v0 = arg3 < 0 ? "neg_" : "";
            return "com.google.android.exoplayer.MediaCodecTrackRenderer_" + v0 + Math.abs(arg3);
        }

        private DecoderInitializationException copyWithFallbackException(DecoderInitializationException arg10) {
            return new DecoderInitializationException(this.getMessage(), this.getCause(), this.mimeType, this.secureDecoderRequired, this.decoderName, this.diagnosticInfo, arg10);
        }

        @TargetApi(value=21) private static String getDiagnosticInfoV21(Throwable arg1) {
            if((arg1 instanceof MediaCodec$CodecException)) {
                return ((MediaCodec$CodecException)arg1).getDiagnosticInfo();
            }

            return null;
        }
    }

    @Retention(value=RetentionPolicy.SOURCE) @public interface KeepCodecResult {
    }

    @Retention(value=RetentionPolicy.SOURCE) @interface ReconfigurationState {
    }

    @Retention(value=RetentionPolicy.SOURCE) @interface ReinitializationState {
    }

    private static final byte[] ADAPTATION_WORKAROUND_BUFFER = null;
    private static final int ADAPTATION_WORKAROUND_MODE_ALWAYS = 2;
    private static final int ADAPTATION_WORKAROUND_MODE_NEVER = 0;
    private static final int ADAPTATION_WORKAROUND_MODE_SAME_RESOLUTION = 1;
    private static final int ADAPTATION_WORKAROUND_SLICE_WIDTH_HEIGHT = 32;
    protected static final float CODEC_OPERATING_RATE_UNSET = 0f;
    protected static final int KEEP_CODEC_RESULT_NO = 0;
    protected static final int KEEP_CODEC_RESULT_YES_WITHOUT_RECONFIGURATION = 1;
    protected static final int KEEP_CODEC_RESULT_YES_WITH_RECONFIGURATION = 3;
    private static final long MAX_CODEC_HOTSWAP_TIME_MS = 1000;
    private static final int RECONFIGURATION_STATE_NONE = 0;
    private static final int RECONFIGURATION_STATE_QUEUE_PENDING = 2;
    private static final int RECONFIGURATION_STATE_WRITE_PENDING = 1;
    private static final int REINITIALIZATION_STATE_NONE = 0;
    private static final int REINITIALIZATION_STATE_SIGNAL_END_OF_STREAM = 1;
    private static final int REINITIALIZATION_STATE_WAIT_END_OF_STREAM = 2;
    private static final String TAG = "MediaCodecRenderer";
    private final float assumedMinimumCodecOperatingRate;
    private ArrayDeque availableCodecInfos;
    private final DecoderInputBuffer buffer;
    private MediaCodec codec;
    private int codecAdaptationWorkaroundMode;
    private boolean codecConfiguredWithOperatingRate;
    private long codecHotswapDeadlineMs;
    private MediaCodecInfo codecInfo;
    private boolean codecNeedsAdaptationWorkaroundBuffer;
    private boolean codecNeedsDiscardToSpsWorkaround;
    private boolean codecNeedsEosFlushWorkaround;
    private boolean codecNeedsEosOutputExceptionWorkaround;
    private boolean codecNeedsEosPropagationWorkaround;
    private boolean codecNeedsFlushWorkaround;
    private boolean codecNeedsMonoChannelCountWorkaround;
    private float codecOperatingRate;
    private boolean codecReceivedBuffers;
    private boolean codecReceivedEos;
    private int codecReconfigurationState;
    private boolean codecReconfigured;
    private int codecReinitializationState;
    private final List decodeOnlyPresentationTimestamps;
    protected DecoderCounters decoderCounters;
    private DrmSession drmSession;
    private final DrmSessionManager drmSessionManager;
    private final DecoderInputBuffer flagsOnlyBuffer;
    private Format format;
    private final FormatHolder formatHolder;
    private ByteBuffer[] inputBuffers;
    private int inputIndex;
    private boolean inputStreamEnded;
    private final MediaCodecSelector mediaCodecSelector;
    private ByteBuffer outputBuffer;
    private final MediaCodec$BufferInfo outputBufferInfo;
    private ByteBuffer[] outputBuffers;
    private int outputIndex;
    private boolean outputStreamEnded;
    private DrmSession pendingDrmSession;
    private final boolean playClearSamplesWithoutKeys;
    private DecoderInitializationException preferredDecoderInitializationException;
    private float rendererOperatingRate;
    private boolean shouldSkipAdaptationWorkaroundOutputBuffer;
    private boolean shouldSkipOutputBuffer;
    private boolean waitingForFirstSyncFrame;
    private boolean waitingForKeys;

    static {
        MediaCodecRenderer.ADAPTATION_WORKAROUND_BUFFER = Util.getBytesFromHexString("0000016742C00BDA259000000168CE0F13200000016588840DCE7118A0002FBF1C31C3275D78");
    }

    public MediaCodecRenderer(int arg3, MediaCodecSelector arg4, DrmSessionManager arg5, boolean arg6, float arg7) {
        super(arg3);
        boolean v3 = Util.SDK_INT >= 16 ? true : false;
        Assertions.checkState(v3);
        this.mediaCodecSelector = Assertions.checkNotNull(arg4);
        this.drmSessionManager = arg5;
        this.playClearSamplesWithoutKeys = arg6;
        this.assumedMinimumCodecOperatingRate = arg7;
        this.buffer = new DecoderInputBuffer(0);
        this.flagsOnlyBuffer = DecoderInputBuffer.newFlagsOnlyInstance();
        this.formatHolder = new FormatHolder();
        this.decodeOnlyPresentationTimestamps = new ArrayList();
        this.outputBufferInfo = new MediaCodec$BufferInfo();
        this.codecReconfigurationState = 0;
        this.codecReinitializationState = 0;
        this.codecOperatingRate = -1f;
        this.rendererOperatingRate = 1f;
    }

    protected int canKeepCodec(MediaCodec arg1, MediaCodecInfo arg2, Format arg3, Format arg4) {
        return 0;
    }

    private int codecAdaptationWorkaroundMode(String arg3) {
        if(Util.SDK_INT <= 25 && ("OMX.Exynos.avc.dec.secure".equals(arg3)) && ((Util.MODEL.startsWith("SM-T585")) || (Util.MODEL.startsWith("SM-A510")) || (Util.MODEL.startsWith("SM-A520")) || (Util.MODEL.startsWith("SM-J700")))) {
            return 2;
        }

        if(Util.SDK_INT < 24 && (("OMX.Nvidia.h264.decode".equals(arg3)) || ("OMX.Nvidia.h264.decode.secure".equals(arg3))) && (("flounder".equals(Util.DEVICE)) || ("flounder_lte".equals(Util.DEVICE)) || ("grouper".equals(Util.DEVICE)) || ("tilapia".equals(Util.DEVICE)))) {
            return 1;
        }

        return 0;
    }

    private static boolean codecNeedsDiscardToSpsWorkaround(String arg2, Format arg3) {
        boolean v2 = Util.SDK_INT >= 21 || !arg3.initializationData.isEmpty() || !"OMX.MTK.VIDEO.DECODER.AVC".equals(arg2) ? false : true;
        return v2;
    }

    private static boolean codecNeedsEosFlushWorkaround(String arg2) {
        boolean v2;
        if(Util.SDK_INT > 23 || !"OMX.google.vorbis.decoder".equals(arg2)) {
            if(Util.SDK_INT <= 19 && ("hb2000".equals(Util.DEVICE)) && (("OMX.amlogic.avc.decoder.awesome".equals(arg2)) || ("OMX.amlogic.avc.decoder.awesome.secure".equals(arg2)))) {
            label_19:
                v2 = true;
                return v2;
            }

            v2 = false;
        }
        else {
            goto label_19;
        }

        return v2;
    }

    private static boolean codecNeedsEosOutputExceptionWorkaround(String arg2) {
        boolean v2 = Util.SDK_INT != 21 || !"OMX.google.aac.decoder".equals(arg2) ? false : true;
        return v2;
    }

    private static boolean codecNeedsEosPropagationWorkaround(MediaCodecInfo arg3) {
        boolean v3;
        String v0 = arg3.name;
        if(Util.SDK_INT > 17 || !"OMX.allwinner.video.decoder.avc".equals(v0)) {
            if(("Amazon".equals(Util.MANUFACTURER)) && ("AFTS".equals(Util.MODEL)) && (arg3.secure)) {
            label_20:
                v3 = true;
                return v3;
            }

            v3 = false;
        }
        else if("OMX.rk.video_decoder.avc".equals(v0)) {
            goto label_20;
        }
        else {
            goto label_20;
        }

        return v3;
    }

    private static boolean codecNeedsFlushWorkaround(String arg2) {
        boolean v2;
        int v1 = 18;
        if(Util.SDK_INT >= v1) {
            if(Util.SDK_INT == v1) {
                if("OMX.SEC.avc.dec".equals(arg2)) {
                }
                else if(!"OMX.SEC.avc.dec.secure".equals(arg2)) {
                    goto label_11;
                }

                goto label_27;
            }

        label_11:
            if(Util.SDK_INT == 19 && (Util.MODEL.startsWith("SM-G800"))) {
                if("OMX.Exynos.avc.dec".equals(arg2)) {
                    goto label_27;
                }
                else if("OMX.Exynos.avc.dec.secure".equals(arg2)) {
                    goto label_27;
                }
            }

            v2 = false;
        }
        else {
        label_27:
            v2 = true;
        }

        return v2;
    }

    private static boolean codecNeedsMonoChannelCountWorkaround(String arg3, Format arg4) {
        boolean v1 = true;
        if(Util.SDK_INT > 18 || arg4.channelCount != 1 || !"OMX.MTK.AUDIO.DECODER.MP3".equals(arg3)) {
            v1 = false;
        }
        else {
        }

        return v1;
    }

    protected abstract void configureCodec(MediaCodecInfo arg1, MediaCodec arg2, Format arg3, MediaCrypto arg4, float arg5);

    private boolean deviceNeedsDrmKeysToConfigureCodecWorkaround() {
        boolean v0;
        if("Amazon".equals(Util.MANUFACTURER)) {
            if(!"AFTM".equals(Util.MODEL) && !"AFTB".equals(Util.MODEL)) {
                goto label_14;
            }

            v0 = true;
        }
        else {
        label_14:
            v0 = false;
        }

        return v0;
    }

    private boolean drainOutputBuffer(long arg16, long arg18) {
        boolean v0_1;
        int v0;
        MediaCodecRenderer v12 = this;
        if(!this.hasOutputBuffer()) {
            if(!v12.codecNeedsEosOutputExceptionWorkaround || !v12.codecReceivedEos) {
                v0 = v12.codec.dequeueOutputBuffer(v12.outputBufferInfo, this.getDequeueOutputBufferTimeoutUs());
            }
            else {
                try {
                    v0 = v12.codec.dequeueOutputBuffer(v12.outputBufferInfo, this.getDequeueOutputBufferTimeoutUs());
                }
                catch(IllegalStateException ) {
                    this.processEndOfStream();
                    if(v12.outputStreamEnded) {
                        this.releaseCodec();
                    }

                    return 0;
                }
            }

            if(v0 >= 0) {
                if(v12.shouldSkipAdaptationWorkaroundOutputBuffer) {
                    v12.shouldSkipAdaptationWorkaroundOutputBuffer = false;
                    v12.codec.releaseOutputBuffer(v0, false);
                    return 1;
                }

                if(v12.outputBufferInfo.size == 0 && (v12.outputBufferInfo.flags & 4) != 0) {
                    this.processEndOfStream();
                    return 0;
                }

                v12.outputIndex = v0;
                v12.outputBuffer = this.getOutputBuffer(v0);
                if(v12.outputBuffer != null) {
                    v12.outputBuffer.position(v12.outputBufferInfo.offset);
                    v12.outputBuffer.limit(v12.outputBufferInfo.offset + v12.outputBufferInfo.size);
                }

                v12.shouldSkipOutputBuffer = this.shouldSkipOutputBuffer(v12.outputBufferInfo.presentationTimeUs);
                goto label_77;
            }

            if(v0 == -2) {
                this.processOutputFormat();
                return 1;
            }

            if(v0 == -3) {
                this.processOutputBuffersChanged();
                return 1;
            }

            if((v12.codecNeedsEosPropagationWorkaround) && ((v12.inputStreamEnded) || v12.codecReinitializationState == 2)) {
                this.processEndOfStream();
            }

            return 0;
        }

    label_77:
        if(!v12.codecNeedsEosOutputExceptionWorkaround || !v12.codecReceivedEos) {
            v0_1 = this.processOutputBuffer(arg16, arg18, v12.codec, v12.outputBuffer, v12.outputIndex, v12.outputBufferInfo.flags, v12.outputBufferInfo.presentationTimeUs, v12.shouldSkipOutputBuffer);
        }
        else {
            try {
                v0_1 = this.processOutputBuffer(arg16, arg18, v12.codec, v12.outputBuffer, v12.outputIndex, v12.outputBufferInfo.flags, v12.outputBufferInfo.presentationTimeUs, v12.shouldSkipOutputBuffer);
            }
            catch(IllegalStateException ) {
                this.processEndOfStream();
                if(v12.outputStreamEnded) {
                    this.releaseCodec();
                }

                return 0;
            }
        }

        if(v0_1) {
            this.onProcessedOutputBuffer(v12.outputBufferInfo.presentationTimeUs);
            v0 = (v12.outputBufferInfo.flags & 4) != 0 ? 1 : 0;
            this.resetOutputBuffer();
            if(v0 == 0) {
                return 1;
            }

            this.processEndOfStream();
        }

        return 0;
    }

    private boolean feedInputBuffer() {
        int v4;
        int v0;
        if(this.codec != null) {
            int v2 = 2;
            if(this.codecReinitializationState != v2) {
                if(this.inputStreamEnded) {
                }
                else {
                    if(this.inputIndex < 0) {
                        this.inputIndex = this.codec.dequeueInputBuffer(0);
                        if(this.inputIndex < 0) {
                            return 0;
                        }
                        else {
                            this.buffer.data = this.getInputBuffer(this.inputIndex);
                            this.buffer.clear();
                        }
                    }

                    if(this.codecReinitializationState == 1) {
                        if(this.codecNeedsEosPropagationWorkaround) {
                        }
                        else {
                            this.codecReceivedEos = true;
                            this.codec.queueInputBuffer(this.inputIndex, 0, 0, 0, 4);
                            this.resetInputBuffer();
                        }

                        this.codecReinitializationState = v2;
                        return 0;
                    }

                    if(this.codecNeedsAdaptationWorkaroundBuffer) {
                        this.codecNeedsAdaptationWorkaroundBuffer = false;
                        this.buffer.data.put(MediaCodecRenderer.ADAPTATION_WORKAROUND_BUFFER);
                        this.codec.queueInputBuffer(this.inputIndex, 0, MediaCodecRenderer.ADAPTATION_WORKAROUND_BUFFER.length, 0, 0);
                        this.resetInputBuffer();
                        this.codecReceivedBuffers = true;
                        return 1;
                    }

                    if(this.waitingForKeys) {
                        v0 = -4;
                        v4 = 0;
                    }
                    else {
                        if(this.codecReconfigurationState == 1) {
                            for(v0 = 0; v0 < this.format.initializationData.size(); ++v0) {
                                this.buffer.data.put(this.format.initializationData.get(v0));
                            }

                            this.codecReconfigurationState = v2;
                        }

                        v4 = this.buffer.data.position();
                        v0 = this.readSource(this.formatHolder, this.buffer, false);
                    }

                    if(v0 == -3) {
                        return 0;
                    }

                    if(v0 == -5) {
                        if(this.codecReconfigurationState == v2) {
                            this.buffer.clear();
                            this.codecReconfigurationState = 1;
                        }

                        this.onInputFormatChanged(this.formatHolder.format);
                        return 1;
                    }

                    if(this.buffer.isEndOfStream()) {
                        if(this.codecReconfigurationState == v2) {
                            this.buffer.clear();
                            this.codecReconfigurationState = 1;
                        }

                        this.inputStreamEnded = true;
                        if(!this.codecReceivedBuffers) {
                            this.processEndOfStream();
                            return 0;
                        }

                        try {
                            if(this.codecNeedsEosPropagationWorkaround) {
                            }
                            else {
                                this.codecReceivedEos = true;
                                this.codec.queueInputBuffer(this.inputIndex, 0, 0, 0, 4);
                                this.resetInputBuffer();
                            }
                        }
                        catch(MediaCodec$CryptoException v0_1) {
                            throw ExoPlaybackException.createForRenderer(((Exception)v0_1), this.getIndex());
                        }

                        return 0;
                    }

                    if((this.waitingForFirstSyncFrame) && !this.buffer.isKeyFrame()) {
                        this.buffer.clear();
                        if(this.codecReconfigurationState == v2) {
                            this.codecReconfigurationState = 1;
                        }

                        return 1;
                    }

                    this.waitingForFirstSyncFrame = false;
                    boolean v0_2 = this.buffer.isEncrypted();
                    this.waitingForKeys = this.shouldWaitForKeys(v0_2);
                    if(this.waitingForKeys) {
                        return 0;
                    }

                    if((this.codecNeedsDiscardToSpsWorkaround) && !v0_2) {
                        NalUnitUtil.discardToSps(this.buffer.data);
                        if(this.buffer.data.position() == 0) {
                            return 1;
                        }
                        else {
                            this.codecNeedsDiscardToSpsWorkaround = false;
                        }
                    }

                    try {
                        long v9 = this.buffer.timeUs;
                        if(this.buffer.isDecodeOnly()) {
                            this.decodeOnlyPresentationTimestamps.add(Long.valueOf(v9));
                        }

                        this.buffer.flip();
                        this.onQueueInputBuffer(this.buffer);
                        if(v0_2) {
                            this.codec.queueSecureInputBuffer(this.inputIndex, 0, MediaCodecRenderer.getFrameworkCryptoInfo(this.buffer, v4), v9, 0);
                        }
                        else {
                            this.codec.queueInputBuffer(this.inputIndex, 0, this.buffer.data.limit(), v9, 0);
                        }

                        this.resetInputBuffer();
                        this.codecReceivedBuffers = true;
                        this.codecReconfigurationState = 0;
                        ++this.decoderCounters.inputBufferCount;
                        return 1;
                    }
                    catch(MediaCodec$CryptoException v0_1) {
                        throw ExoPlaybackException.createForRenderer(((Exception)v0_1), this.getIndex());
                    }
                }
            }
        }

        return 0;
    }

    protected void flushCodec() {
        this.codecHotswapDeadlineMs = -9223372036854775807L;
        this.resetInputBuffer();
        this.resetOutputBuffer();
        this.waitingForFirstSyncFrame = true;
        this.waitingForKeys = false;
        this.shouldSkipOutputBuffer = false;
        this.decodeOnlyPresentationTimestamps.clear();
        this.codecNeedsAdaptationWorkaroundBuffer = false;
        this.shouldSkipAdaptationWorkaroundOutputBuffer = false;
        if(!this.codecNeedsFlushWorkaround) {
            if((this.codecNeedsEosFlushWorkaround) && (this.codecReceivedEos)) {
                goto label_27;
            }

            if(this.codecReinitializationState != 0) {
                goto label_27;
            }

            this.codec.flush();
            this.codecReceivedBuffers = false;
        }
        else {
        label_27:
            this.releaseCodec();
            this.maybeInitCodec();
        }

        if((this.codecReconfigured) && this.format != null) {
            this.codecReconfigurationState = 1;
        }
    }

    private List getAvailableCodecInfos(boolean arg4) {
        List v0 = this.getDecoderInfos(this.mediaCodecSelector, this.format, arg4);
        if((v0.isEmpty()) && (arg4)) {
            v0 = this.getDecoderInfos(this.mediaCodecSelector, this.format, false);
            if(!v0.isEmpty()) {
                Log.w("MediaCodecRenderer", "Drm session requires secure decoder for " + this.format.sampleMimeType + ", but no secure decoder available. Trying to proceed with " + v0 + ".");
            }
        }

        return v0;
    }

    protected final MediaCodec getCodec() {
        return this.codec;
    }

    private void getCodecBuffers(MediaCodec arg3) {
        if(Util.SDK_INT < 21) {
            this.inputBuffers = arg3.getInputBuffers();
            this.outputBuffers = arg3.getOutputBuffers();
        }
    }

    protected final MediaCodecInfo getCodecInfo() {
        return this.codecInfo;
    }

    protected float getCodecOperatingRate(float arg1, Format arg2, Format[] arg3) {
        return -1f;
    }

    protected List getDecoderInfos(MediaCodecSelector arg1, Format arg2, boolean arg3) {
        return arg1.getDecoderInfos(arg2, arg3);
    }

    protected long getDequeueOutputBufferTimeoutUs() {
        return 0;
    }

    private static MediaCodec$CryptoInfo getFrameworkCryptoInfo(DecoderInputBuffer arg3, int arg4) {
        MediaCodec$CryptoInfo v3 = arg3.cryptoInfo.getFrameworkCryptoInfoV16();
        if(arg4 == 0) {
            return v3;
        }

        if(v3.numBytesOfClearData == null) {
            v3.numBytesOfClearData = new int[1];
        }

        v3.numBytesOfClearData[0] += arg4;
        return v3;
    }

    private ByteBuffer getInputBuffer(int arg3) {
        if(Util.SDK_INT >= 21) {
            return this.codec.getInputBuffer(arg3);
        }

        return this.inputBuffers[arg3];
    }

    private ByteBuffer getOutputBuffer(int arg3) {
        if(Util.SDK_INT >= 21) {
            return this.codec.getOutputBuffer(arg3);
        }

        return this.outputBuffers[arg3];
    }

    private boolean hasOutputBuffer() {
        boolean v0 = this.outputIndex >= 0 ? true : false;
        return v0;
    }

    private void initCodec(MediaCodecInfo arg13, MediaCrypto arg14) {
        long v6;
        MediaCodec v5_1;
        long v3;
        String v1 = arg13.name;
        this.updateCodecOperatingRate();
        boolean v0 = this.codecOperatingRate > this.assumedMinimumCodecOperatingRate ? true : false;
        MediaCodec v2 = null;
        try {
            v3 = SystemClock.elapsedRealtime();
            TraceUtil.beginSection("createCodec:" + v1);
            v5_1 = MediaCodec.createByCodecName(v1);
        }
        catch(Exception v13) {
            v5_1 = v2;
            goto label_53;
        }

        try {
            TraceUtil.endSection();
            TraceUtil.beginSection("configureCodec");
            Format v9 = this.format;
            float v11 = v0 ? this.codecOperatingRate : -1f;
            this.configureCodec(arg13, v5_1, v9, arg14, v11);
            this.codecConfiguredWithOperatingRate = v0;
            TraceUtil.endSection();
            TraceUtil.beginSection("startCodec");
            v5_1.start();
            TraceUtil.endSection();
            v6 = SystemClock.elapsedRealtime();
            this.getCodecBuffers(v5_1);
            goto label_41;
        }
        catch(Exception v13) {
        }

    label_53:
        if(v5_1 != null) {
            this.resetCodecBuffers();
            v5_1.release();
        }

        throw v13;
    label_41:
        this.codec = v5_1;
        this.codecInfo = arg13;
        this.onCodecInitialized(v1, v6, v6 - v3);
    }

    private boolean initCodecWithFallback(MediaCrypto arg6, boolean arg7) {
        DecoderInitializationException v1 = null;
        if(this.availableCodecInfos == null) {
            try {
                this.availableCodecInfos = new ArrayDeque(this.getAvailableCodecInfos(arg7));
                this.preferredDecoderInitializationException = v1;
            }
            catch(DecoderQueryException v6) {
                throw new DecoderInitializationException(this.format, ((Throwable)v6), arg7, -49998);
            }
        }

        if(!this.availableCodecInfos.isEmpty()) {
            while(true) {
                Object v0 = this.availableCodecInfos.peekFirst();
                if(!this.shouldInitCodec(((MediaCodecInfo)v0))) {
                    return 0;
                }

                try {
                    this.initCodec(((MediaCodecInfo)v0), arg6);
                    arg6 = null;
                    return 1;
                }
                catch(Exception v1_1) {
                    Log.w("MediaCodecRenderer", "Failed to initialize decoder: " + v0, ((Throwable)v1_1));
                    this.availableCodecInfos.removeFirst();
                    DecoderInitializationException v2 = new DecoderInitializationException(this.format, ((Throwable)v1_1), arg7, ((MediaCodecInfo)v0).name);
                    this.preferredDecoderInitializationException = this.preferredDecoderInitializationException == null ? v2 : DecoderInitializationException.access$000(this.preferredDecoderInitializationException, v2);
                    if(!this.availableCodecInfos.isEmpty()) {
                        continue;
                    }

                    throw this.preferredDecoderInitializationException;
                }
            }

            return 0;
        }

        throw new DecoderInitializationException(this.format, ((Throwable)v1), arg7, -49999);
    }

    public boolean isEnded() {
        return this.outputStreamEnded;
    }

    public boolean isReady() {
        boolean v0;
        if(this.format == null || (this.waitingForKeys)) {
        label_16:
            v0 = false;
        }
        else {
            if(!this.isSourceReady() && !this.hasOutputBuffer()) {
                if(this.codecHotswapDeadlineMs == -9223372036854775807L) {
                }
                else if(SystemClock.elapsedRealtime() < this.codecHotswapDeadlineMs) {
                    goto label_14;
                }

                goto label_16;
            }

        label_14:
            v0 = true;
        }

        return v0;
    }

    protected final void maybeInitCodec() {
        if(this.codec == null) {
            if(this.format == null) {
            }
            else {
                this.drmSession = this.pendingDrmSession;
                String v0 = this.format.sampleMimeType;
                MediaCrypto v1 = null;
                boolean v2 = false;
                if(this.drmSession != null) {
                    ExoMediaCrypto v3 = this.drmSession.getMediaCrypto();
                    if(v3 != null) {
                        v1 = ((FrameworkMediaCrypto)v3).getWrappedMediaCrypto();
                        v2 = ((FrameworkMediaCrypto)v3).requiresSecureDecoderComponent(v0);
                    }
                    else if(this.drmSession.getError() != null) {
                    }
                    else {
                        return;
                    }

                    if(!this.deviceNeedsDrmKeysToConfigureCodecWorkaround()) {
                        goto label_38;
                    }

                    int v0_1 = this.drmSession.getState();
                    if(v0_1 != 1) {
                        if(v0_1 == 4) {
                            goto label_38;
                        }

                        return;
                    }

                    throw ExoPlaybackException.createForRenderer(this.drmSession.getError(), this.getIndex());
                }

                try {
                label_38:
                    if(this.initCodecWithFallback(v1, v2)) {
                        goto label_41;
                    }
                }
                catch(DecoderInitializationException v0_2) {
                    throw ExoPlaybackException.createForRenderer(((Exception)v0_2), this.getIndex());
                }

                return;
            label_41:
                v0 = this.codecInfo.name;
                this.codecAdaptationWorkaroundMode = this.codecAdaptationWorkaroundMode(v0);
                this.codecNeedsDiscardToSpsWorkaround = MediaCodecRenderer.codecNeedsDiscardToSpsWorkaround(v0, this.format);
                this.codecNeedsFlushWorkaround = MediaCodecRenderer.codecNeedsFlushWorkaround(v0);
                this.codecNeedsEosPropagationWorkaround = MediaCodecRenderer.codecNeedsEosPropagationWorkaround(this.codecInfo);
                this.codecNeedsEosFlushWorkaround = MediaCodecRenderer.codecNeedsEosFlushWorkaround(v0);
                this.codecNeedsEosOutputExceptionWorkaround = MediaCodecRenderer.codecNeedsEosOutputExceptionWorkaround(v0);
                this.codecNeedsMonoChannelCountWorkaround = MediaCodecRenderer.codecNeedsMonoChannelCountWorkaround(v0, this.format);
                long v0_3 = this.getState() == 2 ? SystemClock.elapsedRealtime() + 1000 : -9223372036854775807L;
                this.codecHotswapDeadlineMs = v0_3;
                this.resetInputBuffer();
                this.resetOutputBuffer();
                this.waitingForFirstSyncFrame = true;
                ++this.decoderCounters.decoderInitCount;
                return;
            }
        }
    }

    protected void onCodecInitialized(String arg1, long arg2, long arg4) {
    }

    protected void onDisabled() {
        Format v0 = null;
        this.format = v0;
        this.availableCodecInfos = ((ArrayDeque)v0);
        try {
            this.releaseCodec();
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
                    throw v1;
                }

                this.drmSession = ((DrmSession)v0);
                this.pendingDrmSession = ((DrmSession)v0);
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
                throw v1;
            }

            this.drmSession = ((DrmSession)v0);
            this.pendingDrmSession = ((DrmSession)v0);
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
                throw v1;
            }

            this.drmSession = ((DrmSession)v0);
            this.pendingDrmSession = ((DrmSession)v0);
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
            throw v1;
        }

        this.drmSession = ((DrmSession)v0);
        this.pendingDrmSession = ((DrmSession)v0);
    }

    protected void onEnabled(boolean arg1) {
        this.decoderCounters = new DecoderCounters();
    }

    protected void onInputFormatChanged(Format arg6) {
        Object v2;
        Format v0 = this.format;
        this.format = arg6;
        DrmInitData v6 = this.format.drmInitData;
        DrmSession v1 = null;
        if(v0 == null) {
            v2 = v1;
        }
        else {
            DrmInitData v2_1 = v0.drmInitData;
        }

        boolean v6_1 = Util.areEqual(v6, v2);
        int v2_2 = 1;
        if(((((int)v6_1)) ^ 1) != 0) {
            if(this.format.drmInitData == null) {
                this.pendingDrmSession = v1;
            }
            else if(this.drmSessionManager != null) {
                this.pendingDrmSession = this.drmSessionManager.acquireSession(Looper.myLooper(), this.format.drmInitData);
                if(this.pendingDrmSession == this.drmSession) {
                    this.drmSessionManager.releaseSession(this.pendingDrmSession);
                }
            }
            else {
                throw ExoPlaybackException.createForRenderer(new IllegalStateException("Media requires a DrmSessionManager"), this.getIndex());
            }
        }

        boolean v3 = false;
        if(this.pendingDrmSession != this.drmSession || this.codec == null) {
        label_72:
            v2_2 = 0;
        }
        else {
            int v6_2 = this.canKeepCodec(this.codec, this.codecInfo, v0, this.format);
            if(v6_2 != 3) {
                switch(v6_2) {
                    case 0: {
                        goto label_72;
                    }
                    case 1: {
                        goto label_73;
                    }
                }

                throw new IllegalStateException();
            }
            else {
                this.codecReconfigured = true;
                this.codecReconfigurationState = 1;
                if(this.codecAdaptationWorkaroundMode == 2 || this.codecAdaptationWorkaroundMode == 1 && this.format.width == v0.width && this.format.height == v0.height) {
                    v3 = true;
                }

                this.codecNeedsAdaptationWorkaroundBuffer = v3;
            }
        }

    label_73:
        if(v2_2 == 0) {
            this.reinitializeCodec();
        }
        else {
            this.updateCodecOperatingRate();
        }
    }

    protected void onOutputFormatChanged(MediaCodec arg1, MediaFormat arg2) {
    }

    protected void onPositionReset(long arg1, boolean arg3) {
        this.inputStreamEnded = false;
        this.outputStreamEnded = false;
        if(this.codec != null) {
            this.flushCodec();
        }
    }

    protected void onProcessedOutputBuffer(long arg1) {
    }

    protected void onQueueInputBuffer(DecoderInputBuffer arg1) {
    }

    protected void onStarted() {
    }

    protected void onStopped() {
    }

    private void processEndOfStream() {
        if(this.codecReinitializationState == 2) {
            this.releaseCodec();
            this.maybeInitCodec();
        }
        else {
            this.outputStreamEnded = true;
            this.renderToEndOfStream();
        }
    }

    protected abstract boolean processOutputBuffer(long arg1, long arg2, MediaCodec arg3, ByteBuffer arg4, int arg5, int arg6, long arg7, boolean arg8);

    private void processOutputBuffersChanged() {
        if(Util.SDK_INT < 21) {
            this.outputBuffers = this.codec.getOutputBuffers();
        }
    }

    private void processOutputFormat() {
        MediaFormat v0 = this.codec.getOutputFormat();
        if(this.codecAdaptationWorkaroundMode != 0) {
            int v3 = 32;
            if(v0.getInteger("width") == v3 && v0.getInteger("height") == v3) {
                this.shouldSkipAdaptationWorkaroundOutputBuffer = true;
                return;
            }
        }

        if(this.codecNeedsMonoChannelCountWorkaround) {
            v0.setInteger("channel-count", 1);
        }

        this.onOutputFormatChanged(this.codec, v0);
    }

    private void reinitializeCodec() {
        this.availableCodecInfos = null;
        if(this.codecReceivedBuffers) {
            this.codecReinitializationState = 1;
        }
        else {
            this.releaseCodec();
            this.maybeInitCodec();
        }
    }

    protected void releaseCodec() {
        this.codecHotswapDeadlineMs = -9223372036854775807L;
        this.resetInputBuffer();
        this.resetOutputBuffer();
        this.waitingForKeys = false;
        this.shouldSkipOutputBuffer = false;
        this.decodeOnlyPresentationTimestamps.clear();
        this.resetCodecBuffers();
        MediaCodecInfo v1 = null;
        this.codecInfo = v1;
        this.codecReconfigured = false;
        this.codecReceivedBuffers = false;
        this.codecNeedsDiscardToSpsWorkaround = false;
        this.codecNeedsFlushWorkaround = false;
        this.codecAdaptationWorkaroundMode = 0;
        this.codecNeedsEosPropagationWorkaround = false;
        this.codecNeedsEosFlushWorkaround = false;
        this.codecNeedsMonoChannelCountWorkaround = false;
        this.codecNeedsAdaptationWorkaroundBuffer = false;
        this.shouldSkipAdaptationWorkaroundOutputBuffer = false;
        this.codecReceivedEos = false;
        this.codecReconfigurationState = 0;
        this.codecReinitializationState = 0;
        this.codecConfiguredWithOperatingRate = false;
        if(this.codec != null) {
            ++this.decoderCounters.decoderReleaseCount;
            try {
                this.codec.stop();
            }
            catch(Throwable v0) {
                try {
                    this.codec.release();
                }
                catch(Throwable v0) {
                    this.codec = ((MediaCodec)v1);
                    if(this.drmSession != null && this.pendingDrmSession != this.drmSession) {
                        try {
                            this.drmSessionManager.releaseSession(this.drmSession);
                        }
                        catch(Throwable v0) {
                            this.drmSession = ((DrmSession)v1);
                            throw v0;
                        }

                        this.drmSession = ((DrmSession)v1);
                    }

                    throw v0;
                }

                this.codec = ((MediaCodec)v1);
                if(this.drmSession != null && this.pendingDrmSession != this.drmSession) {
                    try {
                        this.drmSessionManager.releaseSession(this.drmSession);
                    }
                    catch(Throwable v0) {
                        this.drmSession = ((DrmSession)v1);
                        throw v0;
                    }

                    this.drmSession = ((DrmSession)v1);
                }

                throw v0;
            }

            try {
                this.codec.release();
            }
            catch(Throwable v0) {
                this.codec = ((MediaCodec)v1);
                if(this.drmSession != null && this.pendingDrmSession != this.drmSession) {
                    try {
                        this.drmSessionManager.releaseSession(this.drmSession);
                    }
                    catch(Throwable v0) {
                        this.drmSession = ((DrmSession)v1);
                        throw v0;
                    }

                    this.drmSession = ((DrmSession)v1);
                }

                throw v0;
            }

            this.codec = ((MediaCodec)v1);
            if(this.drmSession == null) {
                return;
            }

            if(this.pendingDrmSession == this.drmSession) {
                return;
            }

            try {
                this.drmSessionManager.releaseSession(this.drmSession);
            }
            catch(Throwable v0) {
                this.drmSession = ((DrmSession)v1);
                throw v0;
            }

            this.drmSession = ((DrmSession)v1);
        }
    }

    public void render(long arg6, long arg8) {
        if(this.outputStreamEnded) {
            this.renderToEndOfStream();
            return;
        }

        int v1 = -4;
        int v2 = -5;
        if(this.format == null) {
            this.flagsOnlyBuffer.clear();
            int v0 = this.readSource(this.formatHolder, this.flagsOnlyBuffer, true);
            if(v0 == v2) {
                this.onInputFormatChanged(this.formatHolder.format);
            }
            else {
                if(v0 == v1) {
                    Assertions.checkState(this.flagsOnlyBuffer.isEndOfStream());
                    this.inputStreamEnded = true;
                    this.processEndOfStream();
                }

                return;
            }
        }

        this.maybeInitCodec();
        if(this.codec != null) {
            TraceUtil.beginSection("drainAndFeed");
            while(this.drainOutputBuffer(arg6, arg8)) {
            }

            while(this.feedInputBuffer()) {
            }

            TraceUtil.endSection();
        }
        else {
            this.decoderCounters.skippedInputBufferCount += this.skipSource(arg6);
            this.flagsOnlyBuffer.clear();
            int v6 = this.readSource(this.formatHolder, this.flagsOnlyBuffer, false);
            if(v6 == v2) {
                this.onInputFormatChanged(this.formatHolder.format);
                goto label_61;
            }

            if(v6 != v1) {
                goto label_61;
            }

            Assertions.checkState(this.flagsOnlyBuffer.isEndOfStream());
            this.inputStreamEnded = true;
            this.processEndOfStream();
        }

    label_61:
        this.decoderCounters.ensureUpdated();
    }

    protected void renderToEndOfStream() {
    }

    private void resetCodecBuffers() {
        if(Util.SDK_INT < 21) {
            this.inputBuffers = null;
            this.outputBuffers = null;
        }
    }

    private void resetInputBuffer() {
        this.inputIndex = -1;
        this.buffer.data = null;
    }

    private void resetOutputBuffer() {
        this.outputIndex = -1;
        this.outputBuffer = null;
    }

    public final void setOperatingRate(float arg1) {
        this.rendererOperatingRate = arg1;
        this.updateCodecOperatingRate();
    }

    protected boolean shouldInitCodec(MediaCodecInfo arg1) {
        return 1;
    }

    private boolean shouldSkipOutputBuffer(long arg7) {
        int v0 = this.decodeOnlyPresentationTimestamps.size();
        int v2;
        for(v2 = 0; v2 < v0; ++v2) {
            if(this.decodeOnlyPresentationTimestamps.get(v2).longValue() == arg7) {
                this.decodeOnlyPresentationTimestamps.remove(v2);
                return 1;
            }
        }

        return 0;
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
        try {
            return this.supportsFormat(this.mediaCodecSelector, this.drmSessionManager, arg3);
        }
        catch(DecoderQueryException v3) {
            throw ExoPlaybackException.createForRenderer(((Exception)v3), this.getIndex());
        }
    }

    protected abstract int supportsFormat(MediaCodecSelector arg1, DrmSessionManager arg2, Format arg3);

    public final int supportsMixedMimeTypeAdaptation() {
        return 8;
    }

    private void updateCodecOperatingRate() {
        if(this.format != null) {
            if(Util.SDK_INT < 23) {
            }
            else {
                float v0 = this.getCodecOperatingRate(this.rendererOperatingRate, this.format, this.getStreamFormats());
                if(this.codecOperatingRate == v0) {
                    return;
                }
                else {
                    this.codecOperatingRate = v0;
                    if(this.codec != null) {
                        if(this.codecReinitializationState != 0) {
                        }
                        else {
                            float v1 = -1f;
                            if(v0 == v1 && (this.codecConfiguredWithOperatingRate)) {
                                this.reinitializeCodec();
                                return;
                            }

                            if(v0 == v1) {
                                return;
                            }

                            if(!this.codecConfiguredWithOperatingRate && v0 <= this.assumedMinimumCodecOperatingRate) {
                                return;
                            }

                            Bundle v1_1 = new Bundle();
                            v1_1.putFloat("operating-rate", v0);
                            this.codec.setParameters(v1_1);
                            this.codecConfiguredWithOperatingRate = true;
                        }
                    }
                }
            }
        }
    }
}

