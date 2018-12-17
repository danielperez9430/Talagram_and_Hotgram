package com.google.android.exoplayer2.audio;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageManager;
import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.os.Handler;
import android.view.Surface;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.decoder.DecoderInputBuffer;
import com.google.android.exoplayer2.drm.DrmInitData;
import com.google.android.exoplayer2.drm.DrmSessionManager;
import com.google.android.exoplayer2.mediacodec.MediaCodecInfo;
import com.google.android.exoplayer2.mediacodec.MediaCodecRenderer;
import com.google.android.exoplayer2.mediacodec.MediaCodecSelector;
import com.google.android.exoplayer2.mediacodec.MediaFormatUtil;
import com.google.android.exoplayer2.util.MediaClock;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.Util;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.List;

@TargetApi(value=16) public class MediaCodecAudioRenderer extends MediaCodecRenderer implements MediaClock {
    class com.google.android.exoplayer2.audio.MediaCodecAudioRenderer$1 {
    }

    final class AudioSinkListener implements Listener {
        AudioSinkListener(MediaCodecAudioRenderer arg1, com.google.android.exoplayer2.audio.MediaCodecAudioRenderer$1 arg2) {
            this(arg1);
        }

        private AudioSinkListener(MediaCodecAudioRenderer arg1) {
            MediaCodecAudioRenderer.this = arg1;
            super();
        }

        public void onAudioSessionId(int arg2) {
            MediaCodecAudioRenderer.this.eventDispatcher.audioSessionId(arg2);
            MediaCodecAudioRenderer.this.onAudioSessionId(arg2);
        }

        public void onPositionDiscontinuity() {
            MediaCodecAudioRenderer.this.onAudioTrackPositionDiscontinuity();
            MediaCodecAudioRenderer.this.allowPositionDiscontinuity = true;
        }

        public void onUnderrun(int arg9, long arg10, long arg12) {
            MediaCodecAudioRenderer.this.eventDispatcher.audioTrackUnderrun(arg9, arg10, arg12);
            MediaCodecAudioRenderer.this.onAudioTrackUnderrun(arg9, arg10, arg12);
        }
    }

    private boolean allowFirstBufferPositionDiscontinuity;
    private boolean allowPositionDiscontinuity;
    private final AudioSink audioSink;
    private int channelCount;
    private int codecMaxInputSize;
    private boolean codecNeedsDiscardChannelsWorkaround;
    private final Context context;
    private long currentPositionUs;
    private int encoderDelay;
    private int encoderPadding;
    private final EventDispatcher eventDispatcher;
    private boolean passthroughEnabled;
    private MediaFormat passthroughMediaFormat;
    private int pcmEncoding;

    public MediaCodecAudioRenderer(Context arg9, MediaCodecSelector arg10, DrmSessionManager arg11, boolean arg12, Handler arg13, AudioRendererEventListener arg14, AudioCapabilities arg15, AudioProcessor[] arg16) {
        this(arg9, arg10, arg11, arg12, arg13, arg14, new DefaultAudioSink(arg15, arg16));
    }

    public MediaCodecAudioRenderer(Context arg3, MediaCodecSelector arg4) {
        this(arg3, arg4, null, false);
    }

    public MediaCodecAudioRenderer(Context arg8, MediaCodecSelector arg9, DrmSessionManager arg10, boolean arg11) {
        this(arg8, arg9, arg10, arg11, null, null);
    }

    public MediaCodecAudioRenderer(Context arg8, MediaCodecSelector arg9, Handler arg10, AudioRendererEventListener arg11) {
        this(arg8, arg9, null, false, arg10, arg11);
    }

    public MediaCodecAudioRenderer(Context arg11, MediaCodecSelector arg12, DrmSessionManager arg13, boolean arg14, Handler arg15, AudioRendererEventListener arg16) {
        this(arg11, arg12, arg13, arg14, arg15, arg16, null, new AudioProcessor[0]);
    }

    public MediaCodecAudioRenderer(Context arg7, MediaCodecSelector arg8, DrmSessionManager arg9, boolean arg10, Handler arg11, AudioRendererEventListener arg12, AudioSink arg13) {
        super(1, arg8, arg9, arg10, 44100f);
        this.context = arg7.getApplicationContext();
        this.audioSink = arg13;
        this.eventDispatcher = new EventDispatcher(arg11, arg12);
        arg13.setListener(new AudioSinkListener(this, null));
    }

    static EventDispatcher access$100(MediaCodecAudioRenderer arg0) {
        return arg0.eventDispatcher;
    }

    static boolean access$202(MediaCodecAudioRenderer arg0, boolean arg1) {
        arg0.allowPositionDiscontinuity = arg1;
        return arg1;
    }

    protected boolean allowPassthrough(String arg2) {
        int v2 = MimeTypes.getEncoding(arg2);
        boolean v2_1 = v2 == 0 || !this.audioSink.isEncodingSupported(v2) ? false : true;
        return v2_1;
    }

    private static boolean areAdaptationCompatible(Format arg2, Format arg3) {
        boolean v2 = !arg2.sampleMimeType.equals(arg3.sampleMimeType) || arg2.channelCount != arg3.channelCount || arg2.sampleRate != arg3.sampleRate || arg2.encoderDelay != 0 || arg2.encoderPadding != 0 || arg3.encoderDelay != 0 || arg3.encoderPadding != 0 || !arg2.initializationDataEquals(arg3) ? false : true;
        return v2;
    }

    protected int canKeepCodec(MediaCodec arg1, MediaCodecInfo arg2, Format arg3, Format arg4) {
        return 0;
    }

    private static boolean codecNeedsDiscardChannelsWorkaround(String arg2) {
        boolean v2;
        if(Util.SDK_INT >= 24 || !"OMX.SEC.aac.dec".equals(arg2) || !"samsung".equals(Util.MANUFACTURER)) {
        label_24:
            v2 = false;
        }
        else {
            if(!Util.DEVICE.startsWith("zeroflte") && !Util.DEVICE.startsWith("herolte") && !Util.DEVICE.startsWith("heroqlte")) {
                goto label_24;
            }

            v2 = true;
        }

        return v2;
    }

    protected void configureCodec(MediaCodecInfo arg2, MediaCodec arg3, Format arg4, MediaCrypto arg5, float arg6) {
        this.codecMaxInputSize = this.getCodecMaxInputSize(arg2, arg4, this.getStreamFormats());
        this.codecNeedsDiscardChannelsWorkaround = MediaCodecAudioRenderer.codecNeedsDiscardChannelsWorkaround(arg2.name);
        this.passthroughEnabled = arg2.passthrough;
        String v2 = arg2.mimeType == null ? "audio/raw" : arg2.mimeType;
        MediaFormat v2_1 = this.getMediaFormat(arg4, v2, this.codecMaxInputSize, arg6);
        Surface v0 = null;
        arg3.configure(v2_1, v0, arg5, 0);
        if(this.passthroughEnabled) {
            this.passthroughMediaFormat = v2_1;
            this.passthroughMediaFormat.setString("mime", arg4.sampleMimeType);
        }
        else {
            this.passthroughMediaFormat = ((MediaFormat)v0);
        }
    }

    private int getCodecMaxInputSize(MediaCodecInfo arg3, Format arg4) {
        if(Util.SDK_INT < 24 && ("OMX.google.raw.decoder".equals(arg3.name))) {
            int v3 = 1;
            if(Util.SDK_INT == 23) {
                PackageManager v0 = this.context.getPackageManager();
                if(v0 != null && (v0.hasSystemFeature("android.software.leanback"))) {
                    v3 = 0;
                }
            }

            if(v3 == 0) {
                goto label_21;
            }

            return -1;
        }

    label_21:
        return arg4.maxInputSize;
    }

    protected int getCodecMaxInputSize(MediaCodecInfo arg1, Format arg2, Format[] arg3) {
        return this.getCodecMaxInputSize(arg1, arg2);
    }

    protected float getCodecOperatingRate(float arg2, Format arg3, Format[] arg4) {
        if(arg3.sampleRate == -1) {
            arg2 = -1f;
        }
        else {
            arg2 *= ((float)arg3.sampleRate);
        }

        return arg2;
    }

    protected List getDecoderInfos(MediaCodecSelector arg2, Format arg3, boolean arg4) {
        if(this.allowPassthrough(arg3.sampleMimeType)) {
            MediaCodecInfo v0 = arg2.getPassthroughDecoderInfo();
            if(v0 != null) {
                return Collections.singletonList(v0);
            }
        }

        return super.getDecoderInfos(arg2, arg3, arg4);
    }

    public MediaClock getMediaClock() {
        return this;
    }

    @SuppressLint(value={"InlinedApi"}) protected MediaFormat getMediaFormat(Format arg3, String arg4, int arg5, float arg6) {
        MediaFormat v0 = new MediaFormat();
        v0.setString("mime", arg4);
        v0.setInteger("channel-count", arg3.channelCount);
        v0.setInteger("sample-rate", arg3.sampleRate);
        MediaFormatUtil.setCsdBuffers(v0, arg3.initializationData);
        MediaFormatUtil.maybeSetInteger(v0, "max-input-size", arg5);
        if(Util.SDK_INT >= 23) {
            v0.setInteger("priority", 0);
            if(arg6 != -1f) {
                v0.setFloat("operating-rate", arg6);
            }
        }

        return v0;
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
        boolean v0 = !super.isEnded() || !this.audioSink.isEnded() ? false : true;
        return v0;
    }

    public boolean isReady() {
        boolean v0 = (this.audioSink.hasPendingData()) || (super.isReady()) ? true : false;
        return v0;
    }

    protected void onAudioSessionId(int arg1) {
    }

    protected void onAudioTrackPositionDiscontinuity() {
    }

    protected void onAudioTrackUnderrun(int arg1, long arg2, long arg4) {
    }

    protected void onCodecInitialized(String arg7, long arg8, long arg10) {
        this.eventDispatcher.decoderInitialized(arg7, arg8, arg10);
    }

    protected void onDisabled() {
        try {
            this.audioSink.release();
        }
        catch(Throwable v0) {
            try {
                super.onDisabled();
            }
            catch(Throwable v0) {
                this.decoderCounters.ensureUpdated();
                this.eventDispatcher.disabled(this.decoderCounters);
                throw v0;
            }

            this.decoderCounters.ensureUpdated();
            this.eventDispatcher.disabled(this.decoderCounters);
            throw v0;
        }

        try {
            super.onDisabled();
        }
        catch(Throwable v0) {
            this.decoderCounters.ensureUpdated();
            this.eventDispatcher.disabled(this.decoderCounters);
            throw v0;
        }

        this.decoderCounters.ensureUpdated();
        this.eventDispatcher.disabled(this.decoderCounters);
    }

    protected void onEnabled(boolean arg2) {
        super.onEnabled(arg2);
        this.eventDispatcher.enabled(this.decoderCounters);
        int v2 = this.getConfiguration().tunnelingAudioSessionId;
        if(v2 != 0) {
            this.audioSink.enableTunnelingV21(v2);
        }
        else {
            this.audioSink.disableTunneling();
        }
    }

    protected void onInputFormatChanged(Format arg3) {
        super.onInputFormatChanged(arg3);
        this.eventDispatcher.inputFormatChanged(arg3);
        int v0 = "audio/raw".equals(arg3.sampleMimeType) ? arg3.pcmEncoding : 2;
        this.pcmEncoding = v0;
        this.channelCount = arg3.channelCount;
        this.encoderDelay = arg3.encoderDelay;
        this.encoderPadding = arg3.encoderPadding;
    }

    protected void onOutputFormatChanged(MediaCodec arg9, MediaFormat arg10) {
        int[] v9_1;
        int v9;
        if(this.passthroughMediaFormat != null) {
            v9 = MimeTypes.getEncoding(this.passthroughMediaFormat.getString("mime"));
            arg10 = this.passthroughMediaFormat;
        }
        else {
            v9 = this.pcmEncoding;
        }

        int v1 = v9;
        int v2 = arg10.getInteger("channel-count");
        int v3 = arg10.getInteger("sample-rate");
        if(this.codecNeedsDiscardChannelsWorkaround) {
            v9 = 6;
            if(v2 != v9) {
                goto label_29;
            }
            else if(this.channelCount < v9) {
                v9_1 = new int[this.channelCount];
                int v10;
                for(v10 = 0; v10 < this.channelCount; ++v10) {
                    v9_1[v10] = v10;
                }
            }
            else {
                goto label_29;
            }
        }
        else {
        label_29:
            v9_1 = null;
        }

        int[] v5 = v9_1;
        try {
            this.audioSink.configure(v1, v2, v3, 0, v5, this.encoderDelay, this.encoderPadding);
            return;
        }
        catch(ConfigurationException v9_2) {
            throw ExoPlaybackException.createForRenderer(((Exception)v9_2), this.getIndex());
        }
    }

    protected void onPositionReset(long arg1, boolean arg3) {
        super.onPositionReset(arg1, arg3);
        this.audioSink.reset();
        this.currentPositionUs = arg1;
        this.allowFirstBufferPositionDiscontinuity = true;
        this.allowPositionDiscontinuity = true;
    }

    protected void onQueueInputBuffer(DecoderInputBuffer arg6) {
        if((this.allowFirstBufferPositionDiscontinuity) && !arg6.isDecodeOnly()) {
            if(Math.abs(arg6.timeUs - this.currentPositionUs) > 500000) {
                this.currentPositionUs = arg6.timeUs;
            }

            this.allowFirstBufferPositionDiscontinuity = false;
        }
    }

    protected void onStarted() {
        super.onStarted();
        this.audioSink.play();
    }

    protected void onStopped() {
        this.updateCurrentPosition();
        this.audioSink.pause();
        super.onStopped();
    }

    protected boolean processOutputBuffer(long arg1, long arg3, MediaCodec arg5, ByteBuffer arg6, int arg7, int arg8, long arg9, boolean arg11) {
        if((this.passthroughEnabled) && (arg8 & 2) != 0) {
            arg5.releaseOutputBuffer(arg7, false);
            return 1;
        }

        if(arg11) {
            arg5.releaseOutputBuffer(arg7, false);
            ++this.decoderCounters.skippedOutputBufferCount;
            this.audioSink.handleDiscontinuity();
            return 1;
        }

        try {
            if(!this.audioSink.handleBuffer(arg6, arg9)) {
                return 0;
            }

            arg5.releaseOutputBuffer(arg7, false);
            ++this.decoderCounters.renderedOutputBufferCount;
            return 1;
        }
        catch(WriteException v1) {
            throw ExoPlaybackException.createForRenderer(((Exception)v1), this.getIndex());
        }

        return 0;
    }

    protected void renderToEndOfStream() {
        try {
            this.audioSink.playToEndOfStream();
            return;
        }
        catch(WriteException v0) {
            throw ExoPlaybackException.createForRenderer(((Exception)v0), this.getIndex());
        }
    }

    public PlaybackParameters setPlaybackParameters(PlaybackParameters arg2) {
        return this.audioSink.setPlaybackParameters(arg2);
    }

    protected int supportsFormat(MediaCodecSelector arg11, DrmSessionManager arg12, Format arg13) {
        int v8;
        int v6;
        String v0 = arg13.sampleMimeType;
        int v2 = 0;
        if(!MimeTypes.isAudio(v0)) {
            return 0;
        }

        int v3 = 21;
        int v1 = Util.SDK_INT >= v3 ? 32 : 0;
        boolean v12 = MediaCodecAudioRenderer.supportsFormatDrm(arg12, arg13.drmInitData);
        int v4 = 4;
        if((v12) && (this.allowPassthrough(v0)) && arg11.getPassthroughDecoderInfo() != null) {
            return v1 | 8 | v4;
        }

        int v5 = 1;
        if(!"audio/raw".equals(v0) || (this.audioSink.isEncodingSupported(arg13.pcmEncoding))) {
            v6 = 2;
            if(!this.audioSink.isEncodingSupported(v6)) {
                return 1;
            }
        }
        else {
            return 1;
        }

        DrmInitData v0_1 = arg13.drmInitData;
        if(v0_1 != null) {
            int v7 = 0;
            v8 = 0;
            while(v7 < v0_1.schemeDataCount) {
                v8 |= v0_1.get(v7).requiresSecureDecryption;
                ++v7;
            }
        }
        else {
            boolean v8_1 = false;
        }

        List v0_2 = arg11.getDecoderInfos(arg13, ((boolean)v8));
        if(v0_2.isEmpty()) {
            if(v8 != 0 && !arg11.getDecoderInfos(arg13, false).isEmpty()) {
                v5 = 2;
            }

            return v5;
        }

        if(!v12) {
            return v6;
        }

        Object v11 = v0_2.get(0);
        if(Util.SDK_INT >= v3) {
            int v0_3 = -1;
            if(arg13.sampleRate != v0_3 && !((MediaCodecInfo)v11).isAudioSampleRateSupportedV21(arg13.sampleRate)) {
                goto label_73;
            }

            if(arg13.channelCount == v0_3) {
                goto label_72;
            }

            if(!((MediaCodecInfo)v11).isAudioChannelCountSupportedV21(arg13.channelCount)) {
                goto label_73;
            }

            goto label_72;
        }
        else {
        label_72:
            v2 = 1;
        }

    label_73:
        if(v2 != 0) {
        }
        else {
            v4 = 3;
        }

        return v1 | 8 | v4;
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

