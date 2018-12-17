package com.google.android.exoplayer2.video;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Point;
import android.media.MediaCodec$OnFrameRenderedListener;
import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.Surface;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.decoder.DecoderInputBuffer;
import com.google.android.exoplayer2.drm.DrmInitData;
import com.google.android.exoplayer2.drm.DrmSessionManager;
import com.google.android.exoplayer2.mediacodec.MediaCodecInfo;
import com.google.android.exoplayer2.mediacodec.MediaCodecRenderer;
import com.google.android.exoplayer2.mediacodec.MediaCodecSelector;
import com.google.android.exoplayer2.mediacodec.MediaCodecUtil;
import com.google.android.exoplayer2.mediacodec.MediaFormatUtil;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.TraceUtil;
import com.google.android.exoplayer2.util.Util;
import java.nio.ByteBuffer;
import java.util.List;

@TargetApi(value=16) public class MediaCodecVideoRenderer extends MediaCodecRenderer {
    class com.google.android.exoplayer2.video.MediaCodecVideoRenderer$1 {
    }

    public final class CodecMaxValues {
        public final int height;
        public final int inputSize;
        public final int width;

        public CodecMaxValues(int arg1, int arg2, int arg3) {
            super();
            this.width = arg1;
            this.height = arg2;
            this.inputSize = arg3;
        }
    }

    @TargetApi(value=23) final class OnFrameRenderedListenerV23 implements MediaCodec$OnFrameRenderedListener {
        OnFrameRenderedListenerV23(MediaCodecVideoRenderer arg1, MediaCodec arg2, com.google.android.exoplayer2.video.MediaCodecVideoRenderer$1 arg3) {
            this(arg1, arg2);
        }

        private OnFrameRenderedListenerV23(MediaCodecVideoRenderer arg1, MediaCodec arg2) {
            MediaCodecVideoRenderer.this = arg1;
            super();
            arg2.setOnFrameRenderedListener(((MediaCodec$OnFrameRenderedListener)this), new Handler());
        }

        public void onFrameRendered(MediaCodec arg1, long arg2, long arg4) {
            if(this != MediaCodecVideoRenderer.this.tunnelingOnFrameRenderedListener) {
                return;
            }

            MediaCodecVideoRenderer.this.maybeNotifyRenderedFirstFrame();
        }
    }

    private static final String KEY_CROP_BOTTOM = "crop-bottom";
    private static final String KEY_CROP_LEFT = "crop-left";
    private static final String KEY_CROP_RIGHT = "crop-right";
    private static final String KEY_CROP_TOP = "crop-top";
    private static final int MAX_PENDING_OUTPUT_STREAM_OFFSET_COUNT = 10;
    private static final int[] STANDARD_LONG_EDGE_VIDEO_PX = null;
    private static final String TAG = "MediaCodecVideoRenderer";
    private final long allowedJoiningTimeMs;
    private int buffersInCodecCount;
    private CodecMaxValues codecMaxValues;
    private boolean codecNeedsSetOutputSurfaceWorkaround;
    private int consecutiveDroppedFrameCount;
    private final Context context;
    private int currentHeight;
    private float currentPixelWidthHeightRatio;
    private int currentUnappliedRotationDegrees;
    private int currentWidth;
    private final boolean deviceNeedsAutoFrcWorkaround;
    private static boolean deviceNeedsSetOutputSurfaceWorkaround;
    private long droppedFrameAccumulationStartTimeMs;
    private int droppedFrames;
    private Surface dummySurface;
    private static boolean evaluatedDeviceNeedsSetOutputSurfaceWorkaround;
    private final EventDispatcher eventDispatcher;
    private final VideoFrameReleaseTimeHelper frameReleaseTimeHelper;
    private long initialPositionUs;
    private long joiningDeadlineMs;
    private long lastInputTimeUs;
    private long lastRenderTimeUs;
    private final int maxDroppedFramesToNotify;
    private long outputStreamOffsetUs;
    private int pendingOutputStreamOffsetCount;
    private final long[] pendingOutputStreamOffsetsUs;
    private final long[] pendingOutputStreamSwitchTimesUs;
    private float pendingPixelWidthHeightRatio;
    private int pendingRotationDegrees;
    private boolean renderedFirstFrame;
    private int reportedHeight;
    private float reportedPixelWidthHeightRatio;
    private int reportedUnappliedRotationDegrees;
    private int reportedWidth;
    private int scalingMode;
    private Surface surface;
    private boolean tunneling;
    private int tunnelingAudioSessionId;
    OnFrameRenderedListenerV23 tunnelingOnFrameRenderedListener;

    static {
        MediaCodecVideoRenderer.STANDARD_LONG_EDGE_VIDEO_PX = new int[]{1920, 1600, 1440, 1280, 960, 854, 640, 540, 480};
    }

    public MediaCodecVideoRenderer(Context arg7, MediaCodecSelector arg8, long arg9, DrmSessionManager arg11, boolean arg12, Handler arg13, VideoRendererEventListener arg14, int arg15) {
        super(2, arg8, arg11, arg12, 30f);
        this.allowedJoiningTimeMs = arg9;
        this.maxDroppedFramesToNotify = arg15;
        this.context = arg7.getApplicationContext();
        this.frameReleaseTimeHelper = new VideoFrameReleaseTimeHelper(this.context);
        this.eventDispatcher = new EventDispatcher(arg13, arg14);
        this.deviceNeedsAutoFrcWorkaround = MediaCodecVideoRenderer.deviceNeedsAutoFrcWorkaround();
        this.pendingOutputStreamOffsetsUs = new long[10];
        this.pendingOutputStreamSwitchTimesUs = new long[10];
        this.outputStreamOffsetUs = -9223372036854775807L;
        this.lastInputTimeUs = -9223372036854775807L;
        this.joiningDeadlineMs = -9223372036854775807L;
        this.currentWidth = -1;
        this.currentHeight = -1;
        this.currentPixelWidthHeightRatio = -1f;
        this.pendingPixelWidthHeightRatio = -1f;
        this.scalingMode = 1;
        this.clearReportedVideoSize();
    }

    public MediaCodecVideoRenderer(Context arg3, MediaCodecSelector arg4) {
        this(arg3, arg4, 0);
    }

    public MediaCodecVideoRenderer(Context arg9, MediaCodecSelector arg10, long arg11) {
        this(arg9, arg10, arg11, null, null, -1);
    }

    public MediaCodecVideoRenderer(Context arg11, MediaCodecSelector arg12, long arg13, Handler arg15, VideoRendererEventListener arg16, int arg17) {
        this(arg11, arg12, arg13, null, false, arg15, arg16, arg17);
    }

    private static boolean areAdaptationCompatible(boolean arg2, Format arg3, Format arg4) {
        if(!arg3.sampleMimeType.equals(arg4.sampleMimeType) || arg3.rotationDegrees != arg4.rotationDegrees) {
        label_20:
            arg2 = false;
        }
        else {
            if(!arg2) {
                if(arg3.width != arg4.width) {
                }
                else if(arg3.height == arg4.height) {
                    goto label_14;
                }

                goto label_20;
            }

        label_14:
            if(!Util.areEqual(arg3.colorInfo, arg4.colorInfo)) {
                goto label_20;
            }

            arg2 = true;
        }

        return arg2;
    }

    protected int canKeepCodec(MediaCodec arg2, MediaCodecInfo arg3, Format arg4, Format arg5) {
        if((MediaCodecVideoRenderer.areAdaptationCompatible(arg3.adaptive, arg4, arg5)) && arg5.width <= this.codecMaxValues.width && arg5.height <= this.codecMaxValues.height && MediaCodecVideoRenderer.getMaxInputSize(arg3, arg5) <= this.codecMaxValues.inputSize) {
            int v2 = arg4.initializationDataEquals(arg5) ? 1 : 3;
            return v2;
        }

        return 0;
    }

    private void clearRenderedFirstFrame() {
        this.renderedFirstFrame = false;
        if(Util.SDK_INT >= 23 && (this.tunneling)) {
            MediaCodec v0 = this.getCodec();
            if(v0 != null) {
                this.tunnelingOnFrameRenderedListener = new OnFrameRenderedListenerV23(this, v0, null);
            }
        }
    }

    private void clearReportedVideoSize() {
        this.reportedWidth = -1;
        this.reportedHeight = -1;
        this.reportedPixelWidthHeightRatio = -1f;
        this.reportedUnappliedRotationDegrees = -1;
    }

    protected boolean codecNeedsSetOutputSurfaceWorkaround(String arg7) {
        int v1 = 0;
        if(Util.SDK_INT < 27) {
            if(arg7.startsWith("OMX.google")) {
            }
            else {
                Class v7 = MediaCodecVideoRenderer.class;
                __monitor_enter(v7);
                try {
                    if(!MediaCodecVideoRenderer.evaluatedDeviceNeedsSetOutputSurfaceWorkaround) {
                        String v0_1 = Util.DEVICE;
                        switch(v0_1.hashCode()) {
                            case -1696512866: {
                                if(v0_1.equals("XT1663")) {
                                    v1 = 115;
                                    goto label_608;
                                }
                                else {
                                    goto label_607;
                                }
                            }
                            case -1680025915: {
                                if(v0_1.equals("ComioS1")) {
                                    v1 = 15;
                                    goto label_608;
                                }
                                else {
                                    goto label_607;
                                }
                            }
                            case -1615810839: {
                                if(v0_1.equals("Phantom6")) {
                                    v1 = 83;
                                    goto label_608;
                                }
                                else {
                                    goto label_607;
                                }
                            }
                            case -1554255044: {
                                if(v0_1.equals("vernee_M5")) {
                                    v1 = 108;
                                    goto label_608;
                                }
                                else {
                                    goto label_607;
                                }
                            }
                            case -1481772737: {
                                if(v0_1.equals("panell_dl")) {
                                    v1 = 76;
                                    goto label_608;
                                }
                                else {
                                    goto label_607;
                                }
                            }
                            case -1481772730: {
                                if(v0_1.equals("panell_ds")) {
                                    v1 = 77;
                                    goto label_608;
                                }
                                else {
                                    goto label_607;
                                }
                            }
                            case -1481772729: {
                                if(v0_1.equals("panell_dt")) {
                                    v1 = 78;
                                    goto label_608;
                                }
                                else {
                                    goto label_607;
                                }
                            }
                            case -1320080169: {
                                if(v0_1.equals("GiONEE_GBL7319")) {
                                    v1 = 37;
                                    goto label_608;
                                }
                                else {
                                    goto label_607;
                                }
                            }
                            case -1217592143: {
                                if(v0_1.equals("BRAVIA_ATV2")) {
                                    v1 = 13;
                                    goto label_608;
                                }
                                else {
                                    goto label_607;
                                }
                            }
                            case -1180384755: {
                                if(v0_1.equals("iris60")) {
                                    v1 = 53;
                                    goto label_608;
                                }
                                else {
                                    goto label_607;
                                }
                            }
                            case -1139198265: {
                                if(v0_1.equals("Slate_Pro")) {
                                    v1 = 96;
                                    goto label_608;
                                }
                                else {
                                    goto label_607;
                                }
                            }
                            case -1052835013: {
                                if(v0_1.equals("namath")) {
                                    v1 = 67;
                                    goto label_608;
                                }
                                else {
                                    goto label_607;
                                }
                            }
                            case -993250464: {
                                if(v0_1.equals("A10-70F")) {
                                    v1 = 3;
                                    goto label_608;
                                }
                                else {
                                    goto label_607;
                                }
                            }
                            case -965403638: {
                                if(v0_1.equals("s905x018")) {
                                    v1 = 98;
                                    goto label_608;
                                }
                                else {
                                    goto label_607;
                                }
                            }
                            case -958336948: {
                                if(v0_1.equals("ELUGA_Ray_X")) {
                                    v1 = 26;
                                    goto label_608;
                                }
                                else {
                                    goto label_607;
                                }
                            }
                            case -879245230: {
                                if(v0_1.equals("tcl_eu")) {
                                    v1 = 104;
                                    goto label_608;
                                }
                                else {
                                    goto label_607;
                                }
                            }
                            case -842500323: {
                                if(v0_1.equals("nicklaus_f")) {
                                    v1 = 68;
                                    goto label_608;
                                }
                                else {
                                    goto label_607;
                                }
                            }
                            case -821392978: {
                                if(v0_1.equals("A7000-a")) {
                                    v1 = 6;
                                    goto label_608;
                                }
                                else {
                                    goto label_607;
                                }
                            }
                            case -797483286: {
                                if(v0_1.equals("SVP-DTV15")) {
                                    v1 = 97;
                                    goto label_608;
                                }
                                else {
                                    goto label_607;
                                }
                            }
                            case -794946968: {
                                if(v0_1.equals("watson")) {
                                    v1 = 109;
                                    goto label_608;
                                }
                                else {
                                    goto label_607;
                                }
                            }
                            case -788334647: {
                                if(v0_1.equals("whyred")) {
                                    v1 = 110;
                                    goto label_608;
                                }
                                else {
                                    goto label_607;
                                }
                            }
                            case -782144577: {
                                if(v0_1.equals("OnePlus5T")) {
                                    v1 = 71;
                                    goto label_608;
                                }
                                else {
                                    goto label_607;
                                }
                            }
                            case -575125681: {
                                if(v0_1.equals("GiONEE_CBL7513")) {
                                    v1 = 36;
                                    goto label_608;
                                }
                                else {
                                    goto label_607;
                                }
                            }
                            case -521118391: {
                                if(v0_1.equals("GIONEE_GBL7360")) {
                                    v1 = 38;
                                    goto label_608;
                                }
                                else {
                                    goto label_607;
                                }
                            }
                            case -430914369: {
                                if(v0_1.equals("Pixi4-7_3G")) {
                                    v1 = 84;
                                    goto label_608;
                                }
                                else {
                                    goto label_607;
                                }
                            }
                            case -290434366: {
                                if(v0_1.equals("taido_row")) {
                                    v1 = 99;
                                    goto label_608;
                                }
                                else {
                                    goto label_607;
                                }
                            }
                            case -282781963: {
                                if(v0_1.equals("BLACK-1X")) {
                                    v1 = 12;
                                    goto label_608;
                                }
                                else {
                                    goto label_607;
                                }
                            }
                            case -277133239: {
                                if(v0_1.equals("Z12_PRO")) {
                                    v1 = 116;
                                    goto label_608;
                                }
                                else {
                                    goto label_607;
                                }
                            }
                            case -173639913: {
                                if(v0_1.equals("ELUGA_A3_Pro")) {
                                    v1 = 23;
                                    goto label_608;
                                }
                                else {
                                    goto label_607;
                                }
                            }
                            case -56598463: {
                                if(v0_1.equals("woods_fn")) {
                                    v1 = 112;
                                    goto label_608;
                                }
                                else {
                                    goto label_607;
                                }
                            }
                            case 2126: {
                                if(v0_1.equals("C1")) {
                                    v1 = 14;
                                    goto label_608;
                                }
                                else {
                                    goto label_607;
                                }
                            }
                            case 2564: {
                                if(v0_1.equals("Q5")) {
                                    v1 = 92;
                                    goto label_608;
                                }
                                else {
                                    goto label_607;
                                }
                            }
                            case 2715: {
                                if(v0_1.equals("V1")) {
                                    v1 = 105;
                                    goto label_608;
                                }
                                else {
                                    goto label_607;
                                }
                            }
                            case 2719: {
                                if(v0_1.equals("V5")) {
                                    v1 = 107;
                                    goto label_608;
                                }
                                else {
                                    goto label_607;
                                }
                            }
                            case 3483: {
                                if(v0_1.equals("mh")) {
                                    v1 = 64;
                                    goto label_608;
                                }
                                else {
                                    goto label_607;
                                }
                            }
                            case 73405: {
                                if(v0_1.equals("JGZ")) {
                                    v1 = 56;
                                    goto label_608;
                                }
                                else {
                                    goto label_607;
                                }
                            }
                            case 75739: {
                                if(v0_1.equals("M5c")) {
                                    v1 = 60;
                                    goto label_608;
                                }
                                else {
                                    goto label_607;
                                }
                            }
                            case 76779: {
                                if(v0_1.equals("MX6")) {
                                    v1 = 66;
                                    goto label_608;
                                }
                                else {
                                    goto label_607;
                                }
                            }
                            case 78669: {
                                if(v0_1.equals("P85")) {
                                    v1 = 74;
                                    goto label_608;
                                }
                                else {
                                    goto label_607;
                                }
                            }
                            case 79305: {
                                if(v0_1.equals("PLE")) {
                                    v1 = 86;
                                    goto label_608;
                                }
                                else {
                                    goto label_607;
                                }
                            }
                            case 2464648: {
                                if(v0_1.equals("Q427")) {
                                    v1 = 90;
                                    goto label_608;
                                }
                                else {
                                    goto label_607;
                                }
                            }
                            case 2689555: {
                                if(v0_1.equals("XE2X")) {
                                    v1 = 114;
                                    goto label_608;
                                }
                                else {
                                    goto label_607;
                                }
                            }
                            case 3351335: {
                                if(v0_1.equals("mido")) {
                                    v1 = 65;
                                    goto label_608;
                                }
                                else {
                                    goto label_607;
                                }
                            }
                            case 3386211: {
                                if(v0_1.equals("p212")) {
                                    v1 = 72;
                                    goto label_608;
                                }
                                else {
                                    goto label_607;
                                }
                            }
                            case 41325051: {
                                if(v0_1.equals("MEIZU_M5")) {
                                    v1 = 63;
                                    goto label_608;
                                }
                                else {
                                    goto label_607;
                                }
                            }
                            case 55178625: {
                                if(v0_1.equals("Aura_Note_2")) {
                                    v1 = 11;
                                    goto label_608;
                                }
                                else {
                                    goto label_607;
                                }
                            }
                            case 61542055: {
                                if(v0_1.equals("A1601")) {
                                    v1 = 4;
                                    goto label_608;
                                }
                                else {
                                    goto label_607;
                                }
                            }
                            case 65355429: {
                                if(v0_1.equals("E5643")) {
                                    v1 = 22;
                                    goto label_608;
                                }
                                else {
                                    goto label_607;
                                }
                            }
                            case 66214468: {
                                if(v0_1.equals("F3111")) {
                                    v1 = 28;
                                    goto label_608;
                                }
                                else {
                                    goto label_607;
                                }
                            }
                            case 66214470: {
                                if(v0_1.equals("F3113")) {
                                    v1 = 29;
                                    goto label_608;
                                }
                                else {
                                    goto label_607;
                                }
                            }
                            case 66214473: {
                                if(v0_1.equals("F3116")) {
                                    v1 = 30;
                                    goto label_608;
                                }
                                else {
                                    goto label_607;
                                }
                            }
                            case 66215429: {
                                if(v0_1.equals("F3211")) {
                                    v1 = 31;
                                    goto label_608;
                                }
                                else {
                                    goto label_607;
                                }
                            }
                            case 66215431: {
                                if(v0_1.equals("F3213")) {
                                    v1 = 32;
                                    goto label_608;
                                }
                                else {
                                    goto label_607;
                                }
                            }
                            case 66215433: {
                                if(v0_1.equals("F3215")) {
                                    v1 = 33;
                                    goto label_608;
                                }
                                else {
                                    goto label_607;
                                }
                            }
                            case 66216390: {
                                if(v0_1.equals("F3311")) {
                                    v1 = 34;
                                }
                                else {
                                label_607:
                                    v1 = -1;
                                }

                                break;
                            }
                            case 76402249: {
                                if(v0_1.equals("PRO7S")) {
                                    v1 = 87;
                                    goto label_608;
                                }
                                else {
                                    goto label_607;
                                }
                            }
                            case 76404105: {
                                if(v0_1.equals("Q4260")) {
                                    v1 = 89;
                                    goto label_608;
                                }
                                else {
                                    goto label_607;
                                }
                            }
                            case 76404911: {
                                if(v0_1.equals("Q4310")) {
                                    v1 = 91;
                                    goto label_608;
                                }
                                else {
                                    goto label_607;
                                }
                            }
                            case 80963634: {
                                if(v0_1.equals("V23GB")) {
                                    v1 = 106;
                                    goto label_608;
                                }
                                else {
                                    goto label_607;
                                }
                            }
                            case 82882791: {
                                if(v0_1.equals("X3_HK")) {
                                    v1 = 113;
                                    goto label_608;
                                }
                                else {
                                    goto label_607;
                                }
                            }
                            case 102844228: {
                                if(v0_1.equals("le_x6")) {
                                    v1 = 58;
                                    goto label_608;
                                }
                                else {
                                    goto label_607;
                                }
                            }
                            case 165221241: {
                                if(v0_1.equals("A2016a40")) {
                                    v1 = 5;
                                    goto label_608;
                                }
                                else {
                                    goto label_607;
                                }
                            }
                            case 182191441: {
                                if(v0_1.equals("CPY83_I00")) {
                                    v1 = 18;
                                    goto label_608;
                                }
                                else {
                                    goto label_607;
                                }
                            }
                            case 245388979: {
                                if(v0_1.equals("marino_f")) {
                                    v1 = 62;
                                    goto label_608;
                                }
                                else {
                                    goto label_607;
                                }
                            }
                            case 287431619: {
                                if(v0_1.equals("griffin")) {
                                    v1 = 45;
                                    goto label_608;
                                }
                                else {
                                    goto label_607;
                                }
                            }
                            case 307593612: {
                                if(v0_1.equals("A7010a48")) {
                                    v1 = 8;
                                    goto label_608;
                                }
                                else {
                                    goto label_607;
                                }
                            }
                            case 308517133: {
                                if(v0_1.equals("A7020a48")) {
                                    v1 = 9;
                                    goto label_608;
                                }
                                else {
                                    goto label_607;
                                }
                            }
                            case 316215098: {
                                if(v0_1.equals("TB3-730F")) {
                                    v1 = 100;
                                    goto label_608;
                                }
                                else {
                                    goto label_607;
                                }
                            }
                            case 316215116: {
                                if(v0_1.equals("TB3-730X")) {
                                    v1 = 101;
                                    goto label_608;
                                }
                                else {
                                    goto label_607;
                                }
                            }
                            case 316246811: {
                                if(v0_1.equals("TB3-850F")) {
                                    v1 = 102;
                                    goto label_608;
                                }
                                else {
                                    goto label_607;
                                }
                            }
                            case 316246818: {
                                if(v0_1.equals("TB3-850M")) {
                                    v1 = 103;
                                    goto label_608;
                                }
                                else {
                                    goto label_607;
                                }
                            }
                            case 407160593: {
                                if(v0_1.equals("Pixi5-10_4G")) {
                                    v1 = 85;
                                    goto label_608;
                                }
                                else {
                                    goto label_607;
                                }
                            }
                            case 507412548: {
                                if(v0_1.equals("QM16XE_U")) {
                                    v1 = 93;
                                    goto label_608;
                                }
                                else {
                                    goto label_607;
                                }
                            }
                            case 793982701: {
                                if(v0_1.equals("GIONEE_WBL5708")) {
                                    v1 = 42;
                                    goto label_608;
                                }
                                else {
                                    goto label_607;
                                }
                            }
                            case 794038622: {
                                if(v0_1.equals("GIONEE_WBL7365")) {
                                    v1 = 43;
                                    goto label_608;
                                }
                                else {
                                    goto label_607;
                                }
                            }
                            case 794040393: {
                                if(v0_1.equals("GIONEE_WBL7519")) {
                                    v1 = 44;
                                    goto label_608;
                                }
                                else {
                                    goto label_607;
                                }
                            }
                            case 835649806: {
                                if(v0_1.equals("manning")) {
                                    v1 = 61;
                                    goto label_608;
                                }
                                else {
                                    goto label_607;
                                }
                            }
                            case 917340916: {
                                if(v0_1.equals("A7000plus")) {
                                    v1 = 7;
                                    goto label_608;
                                }
                                else {
                                    goto label_607;
                                }
                            }
                            case 958008161: {
                                if(v0_1.equals("j2xlteins")) {
                                    v1 = 55;
                                    goto label_608;
                                }
                                else {
                                    goto label_607;
                                }
                            }
                            case 1060579533: {
                                if(v0_1.equals("panell_d")) {
                                    v1 = 75;
                                    goto label_608;
                                }
                                else {
                                    goto label_607;
                                }
                            }
                            case 2029784656: {
                                if(v0_1.equals("HWBLN-H")) {
                                    v1 = 48;
                                    goto label_608;
                                }
                                else {
                                    goto label_607;
                                }
                            }
                            case 2030379515: {
                                if(v0_1.equals("HWCAM-H")) {
                                    v1 = 49;
                                    goto label_608;
                                }
                                else {
                                    goto label_607;
                                }
                            }
                            case 2047190025: {
                                if(v0_1.equals("ELUGA_Note")) {
                                    v1 = 24;
                                    goto label_608;
                                }
                                else {
                                    goto label_607;
                                }
                            }
                            case 2047252157: {
                                if(v0_1.equals("ELUGA_Prim")) {
                                    v1 = 25;
                                    goto label_608;
                                }
                                else {
                                    goto label_607;
                                }
                            }
                            case -1931988508: {
                                if(v0_1.equals("AquaPowerM")) {
                                    v1 = 10;
                                    goto label_608;
                                }
                                else {
                                    goto label_607;
                                }
                            }
                            case 2463773: {
                                if(v0_1.equals("Q350")) {
                                    v1 = 88;
                                    goto label_608;
                                }
                                else {
                                    goto label_607;
                                }
                            }
                            case 1977196784: {
                                if(v0_1.equals("Infinix-X572")) {
                                    v1 = 52;
                                    goto label_608;
                                }
                                else {
                                    goto label_607;
                                }
                            }
                            case -1936688065: {
                                if(v0_1.equals("PGN611")) {
                                    v1 = 82;
                                    goto label_608;
                                }
                                else {
                                    goto label_607;
                                }
                            }
                            case 2436959: {
                                if(v0_1.equals("P681")) {
                                    v1 = 73;
                                    goto label_608;
                                }
                                else {
                                    goto label_607;
                                }
                            }
                            case 1906253259: {
                                if(v0_1.equals("PB2-670M")) {
                                    v1 = 79;
                                    goto label_608;
                                }
                                else {
                                    goto label_607;
                                }
                            }
                            case -1936688066: {
                                if(v0_1.equals("PGN610")) {
                                    v1 = 81;
                                    goto label_608;
                                }
                                else {
                                    goto label_607;
                                }
                            }
                            case 1514185: {
                                if(v0_1.equals("1714")) {
                                    v1 = 2;
                                    goto label_608;
                                }
                                else {
                                    goto label_607;
                                }
                            }
                            case 1865889110: {
                                if(v0_1.equals("santoni")) {
                                    v1 = 95;
                                    goto label_608;
                                }
                                else {
                                    goto label_607;
                                }
                            }
                            case -1936688988: {
                                if(v0_1.equals("PGN528")) {
                                    v1 = 80;
                                    goto label_608;
                                }
                                else {
                                    goto label_607;
                                }
                            }
                            case 1514184: {
                                if(v0_1.equals("1713")) {
                                    v1 = 1;
                                    goto label_608;
                                }
                                else {
                                    goto label_607;
                                }
                            }
                            case 1709443163: {
                                if(v0_1.equals("iball8735_9806")) {
                                    v1 = 51;
                                    goto label_608;
                                }
                                else {
                                    goto label_607;
                                }
                            }
                            case -1978990237: {
                                if(v0_1.equals("NX573J")) {
                                    v1 = 70;
                                    goto label_608;
                                }
                                else {
                                    goto label_607;
                                }
                            }
                            case 1513190: {
                                if(v0_1.equals("1601")) {
                                    goto label_608;
                                }
                                else {
                                    goto label_607;
                                }
                            }
                            case 1691543273: {
                                if(v0_1.equals("CPH1609")) {
                                    v1 = 17;
                                    goto label_608;
                                }
                                else {
                                    goto label_607;
                                }
                            }
                            case -1978993182: {
                                if(v0_1.equals("NX541J")) {
                                    v1 = 69;
                                    goto label_608;
                                }
                                else {
                                    goto label_607;
                                }
                            }
                            case 101481: {
                                if(v0_1.equals("flo")) {
                                    v1 = 35;
                                    goto label_608;
                                }
                                else {
                                    goto label_607;
                                }
                            }
                            case 1522194893: {
                                if(v0_1.equals("woods_f")) {
                                    v1 = 111;
                                    goto label_608;
                                }
                                else {
                                    goto label_607;
                                }
                            }
                            case -2022874474: {
                                if(v0_1.equals("CP8676_I02")) {
                                    v1 = 16;
                                    goto label_608;
                                }
                                else {
                                    goto label_607;
                                }
                            }
                            case 99329: {
                                if(v0_1.equals("deb")) {
                                    v1 = 21;
                                    goto label_608;
                                }
                                else {
                                    goto label_607;
                                }
                            }
                            case 1349174697: {
                                if(v0_1.equals("htc_e56ml_dtul")) {
                                    v1 = 46;
                                    goto label_608;
                                }
                                else {
                                    goto label_607;
                                }
                            }
                            case -2097309513: {
                                if(v0_1.equals("K50a40")) {
                                    v1 = 57;
                                    goto label_608;
                                }
                                else {
                                    goto label_607;
                                }
                            }
                            case 98848: {
                                if(v0_1.equals("cv3")) {
                                    v1 = 20;
                                    goto label_608;
                                }
                                else {
                                    goto label_607;
                                }
                            }
                            case 1306947716: {
                                if(v0_1.equals("EverStar_S")) {
                                    v1 = 27;
                                    goto label_608;
                                }
                                else {
                                    goto label_607;
                                }
                            }
                            case -2144781160: {
                                if(v0_1.equals("GIONEE_SWW1631")) {
                                    v1 = 41;
                                    goto label_608;
                                }
                                else {
                                    goto label_607;
                                }
                            }
                            case 98846: {
                                if(v0_1.equals("cv1")) {
                                    v1 = 19;
                                    goto label_608;
                                }
                                else {
                                    goto label_607;
                                }
                            }
                            case 1280332038: {
                                if(v0_1.equals("hwALE-H")) {
                                    v1 = 47;
                                    goto label_608;
                                }
                                else {
                                    goto label_607;
                                }
                            }
                            case -2144781185: {
                                if(v0_1.equals("GIONEE_SWW1627")) {
                                    v1 = 40;
                                    goto label_608;
                                }
                                else {
                                    goto label_607;
                                }
                            }
                            case 88274: {
                                if(v0_1.equals("Z80")) {
                                    v1 = 117;
                                    goto label_608;
                                }
                                else {
                                    goto label_607;
                                }
                            }
                            case 1176899427: {
                                if(v0_1.equals("itel_S41")) {
                                    v1 = 54;
                                    goto label_608;
                                }
                                else {
                                    goto label_607;
                                }
                            }
                            case -2144781245: {
                                if(v0_1.equals("GIONEE_SWW1609")) {
                                    v1 = 39;
                                    goto label_608;
                                }
                                else {
                                    goto label_607;
                                }
                            }
                            case 80618: {
                                if(v0_1.equals("QX1")) {
                                    v1 = 94;
                                    goto label_608;
                                }
                                else {
                                    goto label_607;
                                }
                            }
                            case 1150207623: {
                                if(v0_1.equals("LS-5017")) {
                                    v1 = 59;
                                    goto label_608;
                                }
                                else {
                                    goto label_607;
                                }
                            }
                            case 2048319463: {
                                if(v0_1.equals("HWVNS-H")) {
                                    v1 = 50;
                                    goto label_608;
                                }
                                else {
                                    goto label_607;
                                }
                            }
                            default: {
                                goto label_607;
                            }
                        }

                    label_608:
                        switch(v1) {
                            case 0: 
                            case 1: 
                            case 2: 
                            case 3: 
                            case 4: 
                            case 5: 
                            case 6: 
                            case 7: 
                            case 8: 
                            case 9: 
                            case 10: 
                            case 11: 
                            case 12: 
                            case 13: 
                            case 14: 
                            case 15: 
                            case 16: 
                            case 17: 
                            case 18: 
                            case 19: 
                            case 20: 
                            case 21: 
                            case 22: 
                            case 23: 
                            case 24: 
                            case 25: 
                            case 26: 
                            case 27: 
                            case 28: 
                            case 29: 
                            case 30: 
                            case 31: 
                            case 32: 
                            case 33: 
                            case 34: 
                            case 35: 
                            case 36: 
                            case 37: 
                            case 38: 
                            case 39: 
                            case 40: 
                            case 41: 
                            case 42: 
                            case 43: 
                            case 44: 
                            case 45: 
                            case 46: 
                            case 47: 
                            case 48: 
                            case 49: 
                            case 50: 
                            case 51: 
                            case 52: 
                            case 53: 
                            case 54: 
                            case 55: 
                            case 56: 
                            case 57: 
                            case 58: 
                            case 59: 
                            case 60: 
                            case 61: 
                            case 62: 
                            case 63: 
                            case 64: 
                            case 65: 
                            case 66: 
                            case 67: 
                            case 68: 
                            case 69: 
                            case 70: 
                            case 71: 
                            case 72: 
                            case 73: 
                            case 74: 
                            case 75: 
                            case 76: 
                            case 77: 
                            case 78: 
                            case 79: 
                            case 80: 
                            case 81: 
                            case 82: 
                            case 83: 
                            case 84: 
                            case 85: 
                            case 86: 
                            case 87: 
                            case 88: 
                            case 89: 
                            case 90: 
                            case 91: 
                            case 92: 
                            case 93: 
                            case 94: 
                            case 95: 
                            case 96: 
                            case 97: 
                            case 98: 
                            case 99: 
                            case 100: 
                            case 101: 
                            case 102: 
                            case 103: 
                            case 104: 
                            case 105: 
                            case 106: 
                            case 107: 
                            case 108: 
                            case 109: 
                            case 110: 
                            case 111: 
                            case 112: 
                            case 113: 
                            case 114: 
                            case 115: 
                            case 116: 
                            case 117: {
                                MediaCodecVideoRenderer.deviceNeedsSetOutputSurfaceWorkaround = true;
                                break;
                            }
                            default: {
                                break;
                            }
                        }

                        MediaCodecVideoRenderer.evaluatedDeviceNeedsSetOutputSurfaceWorkaround = true;
                    }

                    __monitor_exit(v7);
                }
                catch(Throwable v0) {
                    try {
                    label_616:
                        __monitor_exit(v7);
                    }
                    catch(Throwable v0) {
                        goto label_616;
                    }

                    throw v0;
                }

                return MediaCodecVideoRenderer.deviceNeedsSetOutputSurfaceWorkaround;
            }
        }

        return 0;
    }

    protected void configureCodec(MediaCodecInfo arg8, MediaCodec arg9, Format arg10, MediaCrypto arg11, float arg12) {
        this.codecMaxValues = this.getCodecMaxValues(arg8, arg10, this.getStreamFormats());
        MediaFormat v10 = this.getMediaFormat(arg10, this.codecMaxValues, arg12, this.deviceNeedsAutoFrcWorkaround, this.tunnelingAudioSessionId);
        if(this.surface == null) {
            Assertions.checkState(this.shouldUseDummySurface(arg8));
            if(this.dummySurface == null) {
                this.dummySurface = DummySurface.newInstanceV17(this.context, arg8.secure);
            }

            this.surface = this.dummySurface;
        }

        arg9.configure(v10, this.surface, arg11, 0);
        if(Util.SDK_INT >= 23 && (this.tunneling)) {
            this.tunnelingOnFrameRenderedListener = new OnFrameRenderedListenerV23(this, arg9, null);
        }
    }

    @TargetApi(value=21) private static void configureTunnelingV21(MediaFormat arg2, int arg3) {
        arg2.setFeatureEnabled("tunneled-playback", true);
        arg2.setInteger("audio-session-id", arg3);
    }

    private static boolean deviceNeedsAutoFrcWorkaround() {
        boolean v0 = Util.SDK_INT > 22 || !"foster".equals(Util.DEVICE) || !"NVIDIA".equals(Util.MANUFACTURER) ? false : true;
        return v0;
    }

    protected void dropOutputBuffer(MediaCodec arg1, int arg2, long arg3) {
        TraceUtil.beginSection("dropVideoBuffer");
        arg1.releaseOutputBuffer(arg2, false);
        TraceUtil.endSection();
        this.updateDroppedBufferCounters(1);
    }

    protected void flushCodec() {
        super.flushCodec();
        this.buffersInCodecCount = 0;
    }

    private static Point getCodecMaxSize(MediaCodecInfo arg13, Format arg14) {
        int v7_1;
        Point v7;
        int v2 = 0;
        int v0 = arg14.height > arg14.width ? 1 : 0;
        int v1 = v0 != 0 ? arg14.height : arg14.width;
        int v3 = v0 != 0 ? arg14.width : arg14.height;
        float v4 = (((float)v3)) / (((float)v1));
        int[] v5 = MediaCodecVideoRenderer.STANDARD_LONG_EDGE_VIDEO_PX;
        int v6 = v5.length;
        while(true) {
            v7 = null;
            if(v2 < v6) {
                int v8 = v5[v2];
                int v9 = ((int)((((float)v8)) * v4));
                if(v8 > v1) {
                    if(v9 <= v3) {
                    }
                    else {
                        if(Util.SDK_INT >= 21) {
                            v7_1 = v0 != 0 ? v9 : v8;
                            if(v0 != 0) {
                            }
                            else {
                                v8 = v9;
                            }

                            v7 = arg13.alignVideoSizeV21(v7_1, v8);
                            if(!arg13.isVideoSizeAndRateSupportedV21(v7.x, v7.y, ((double)arg14.frameRate))) {
                                goto label_64;
                            }

                            return v7;
                        }
                        else {
                            v8 = Util.ceilDivide(v8, 16) * 16;
                            v7_1 = Util.ceilDivide(v9, 16) * 16;
                            if(v8 * v7_1 > MediaCodecUtil.maxH264DecodableFrameSize()) {
                                goto label_64;
                            }

                            int v14 = v0 != 0 ? v7_1 : v8;
                            if(v0 != 0) {
                                v7_1 = v8;
                            }

                            return new Point(v14, v7_1);
                        }

                    label_64:
                        ++v2;
                        continue;
                    }
                }
            }

            return v7;
        }

        return v7;
    }

    protected CodecMaxValues getCodecMaxValues(MediaCodecInfo arg12, Format arg13, Format[] arg14) {
        int v0 = arg13.width;
        int v1 = arg13.height;
        int v2 = MediaCodecVideoRenderer.getMaxInputSize(arg12, arg13);
        if(arg14.length == 1) {
            return new CodecMaxValues(v0, v1, v2);
        }

        int v3 = arg14.length;
        int v6 = v1;
        int v7 = v2;
        v1 = 0;
        v2 = v0;
        for(v0 = 0; v0 < v3; ++v0) {
            Format v8 = arg14[v0];
            if(MediaCodecVideoRenderer.areAdaptationCompatible(arg12.adaptive, arg13, v8)) {
                int v10 = -1;
                int v9 = v8.width == v10 || v8.height == v10 ? 1 : 0;
                v1 |= v9;
                v2 = Math.max(v2, v8.width);
                v6 = Math.max(v6, v8.height);
                v7 = Math.max(v7, MediaCodecVideoRenderer.getMaxInputSize(arg12, v8));
            }
        }

        if(v1 != 0) {
            Log.w("MediaCodecVideoRenderer", "Resolutions unknown. Codec max resolution: " + v2 + "x" + v6);
            Point v14 = MediaCodecVideoRenderer.getCodecMaxSize(arg12, arg13);
            if(v14 != null) {
                v2 = Math.max(v2, v14.x);
                v6 = Math.max(v6, v14.y);
                v7 = Math.max(v7, MediaCodecVideoRenderer.getMaxInputSize(arg12, arg13.sampleMimeType, v2, v6));
                Log.w("MediaCodecVideoRenderer", "Codec max resolution adjusted to: " + v2 + "x" + v6);
            }
        }

        return new CodecMaxValues(v2, v6, v7);
    }

    protected float getCodecOperatingRate(float arg6, Format arg7, Format[] arg8) {
        int v7 = arg8.length;
        float v0 = -1f;
        int v1 = 0;
        float v2 = -1f;
        while(v1 < v7) {
            float v3 = arg8[v1].frameRate;
            if(v3 != v0) {
                v2 = Math.max(v2, v3);
            }

            ++v1;
        }

        if(v2 == v0) {
        }
        else {
            v0 = v2 * arg6;
        }

        return v0;
    }

    private static int getMaxInputSize(MediaCodecInfo arg3, Format arg4) {
        if(arg4.maxInputSize != -1) {
            int v3 = arg4.initializationData.size();
            int v0 = 0;
            int v1 = 0;
            while(v0 < v3) {
                v1 += arg4.initializationData.get(v0).length;
                ++v0;
            }

            return arg4.maxInputSize + v1;
        }

        return MediaCodecVideoRenderer.getMaxInputSize(arg3, arg4.sampleMimeType, arg4.width, arg4.height);
    }

    private static int getMaxInputSize(MediaCodecInfo arg5, String arg6, int arg7, int arg8) {
        int v6;
        int v0 = -1;
        if(arg7 != v0) {
            if(arg8 == v0) {
            }
            else {
                int v3 = 4;
                switch(arg6.hashCode()) {
                    case 1187890754: {
                        if(arg6.equals("video/mp4v-es")) {
                            v6 = 1;
                            goto label_41;
                        }
                        else {
                            goto label_40;
                        }
                    }
                    case 1331836730: {
                        if(arg6.equals("video/avc")) {
                            v6 = 2;
                            goto label_41;
                        }
                        else {
                            goto label_40;
                        }
                    }
                    case 1599127256: {
                        if(arg6.equals("video/x-vnd.on2.vp8")) {
                            v6 = 3;
                        }
                        else {
                        label_40:
                            v6 = -1;
                        }

                        break;
                    }
                    case 1599127257: {
                        if(arg6.equals("video/x-vnd.on2.vp9")) {
                            v6 = 5;
                            goto label_41;
                        }
                        else {
                            goto label_40;
                        }
                    }
                    case -1664118616: {
                        if(arg6.equals("video/3gpp")) {
                            v6 = 0;
                            goto label_41;
                        }
                        else {
                            goto label_40;
                        }
                    }
                    case -1662541442: {
                        if(arg6.equals("video/hevc")) {
                            v6 = 4;
                            goto label_41;
                        }
                        else {
                            goto label_40;
                        }
                    }
                    default: {
                        goto label_40;
                    }
                }

            label_41:
                switch(v6) {
                    case 2: {
                        goto label_45;
                    }
                    case 0: 
                    case 1: 
                    case 3: {
                        goto label_72;
                    }
                    case 4: 
                    case 5: {
                        goto label_43;
                    }
                }

                return v0;
            label_72:
                arg7 *= arg8;
                goto label_73;
            label_43:
                arg7 *= arg8;
                goto label_74;
            label_45:
                if(!"BRAVIA 4K 2015".equals(Util.MODEL)) {
                    if("Amazon".equals(Util.MANUFACTURER)) {
                        if("KFSOWI".equals(Util.MODEL)) {
                            return v0;
                        }
                        else if(("AFTS".equals(Util.MODEL)) && (arg5.secure)) {
                            return v0;
                        }
                    }

                    arg7 = Util.ceilDivide(arg7, 16) * Util.ceilDivide(arg8, 16) * 16 * 16;
                label_73:
                    v3 = 2;
                label_74:
                    return arg7 * 3 / (v3 * 2);
                }

                return v0;
            }
        }

        return v0;
    }

    @SuppressLint(value={"InlinedApi"}) protected MediaFormat getMediaFormat(Format arg4, CodecMaxValues arg5, float arg6, boolean arg7, int arg8) {
        MediaFormat v0 = new MediaFormat();
        v0.setString("mime", arg4.sampleMimeType);
        v0.setInteger("width", arg4.width);
        v0.setInteger("height", arg4.height);
        MediaFormatUtil.setCsdBuffers(v0, arg4.initializationData);
        MediaFormatUtil.maybeSetFloat(v0, "frame-rate", arg4.frameRate);
        MediaFormatUtil.maybeSetInteger(v0, "rotation-degrees", arg4.rotationDegrees);
        MediaFormatUtil.maybeSetColorInfo(v0, arg4.colorInfo);
        v0.setInteger("max-width", arg5.width);
        v0.setInteger("max-height", arg5.height);
        MediaFormatUtil.maybeSetInteger(v0, "max-input-size", arg5.inputSize);
        if(Util.SDK_INT >= 23) {
            v0.setInteger("priority", 0);
            if(arg6 != -1f) {
                v0.setFloat("operating-rate", arg6);
            }
        }

        if(arg7) {
            v0.setInteger("auto-frc", 0);
        }

        if(arg8 != 0) {
            MediaCodecVideoRenderer.configureTunnelingV21(v0, arg8);
        }

        return v0;
    }

    public void handleMessage(int arg2, Object arg3) {
        if(arg2 == 1) {
            this.setSurface(((Surface)arg3));
        }
        else if(arg2 == 4) {
            this.scalingMode = ((Integer)arg3).intValue();
            MediaCodec v2 = this.getCodec();
            if(v2 != null) {
                v2.setVideoScalingMode(this.scalingMode);
            }
        }
        else {
            super.handleMessage(arg2, arg3);
        }
    }

    private static boolean isBufferLate(long arg3) {
        boolean v3 = arg3 < -30000 ? true : false;
        return v3;
    }

    private static boolean isBufferVeryLate(long arg3) {
        boolean v3 = arg3 < -500000 ? true : false;
        return v3;
    }

    public boolean isReady() {
        // Method was not decompiled
    }

    protected boolean maybeDropBuffersToKeyframe(MediaCodec arg1, int arg2, long arg3, long arg5) {
        int v1 = this.skipSource(arg5);
        if(v1 == 0) {
            return 0;
        }

        ++this.decoderCounters.droppedToKeyframeCount;
        this.updateDroppedBufferCounters(this.buffersInCodecCount + v1);
        this.flushCodec();
        return 1;
    }

    private void maybeNotifyDroppedFrames() {
        if(this.droppedFrames > 0) {
            long v0 = SystemClock.elapsedRealtime();
            this.eventDispatcher.droppedFrames(this.droppedFrames, v0 - this.droppedFrameAccumulationStartTimeMs);
            this.droppedFrames = 0;
            this.droppedFrameAccumulationStartTimeMs = v0;
        }
    }

    void maybeNotifyRenderedFirstFrame() {
        if(!this.renderedFirstFrame) {
            this.renderedFirstFrame = true;
            this.eventDispatcher.renderedFirstFrame(this.surface);
        }
    }

    private void maybeNotifyVideoSizeChanged() {
        int v1 = -1;
        if((this.currentWidth != v1 || this.currentHeight != v1) && (this.reportedWidth != this.currentWidth || this.reportedHeight != this.currentHeight || this.reportedUnappliedRotationDegrees != this.currentUnappliedRotationDegrees || this.reportedPixelWidthHeightRatio != this.currentPixelWidthHeightRatio)) {
            this.eventDispatcher.videoSizeChanged(this.currentWidth, this.currentHeight, this.currentUnappliedRotationDegrees, this.currentPixelWidthHeightRatio);
            this.reportedWidth = this.currentWidth;
            this.reportedHeight = this.currentHeight;
            this.reportedUnappliedRotationDegrees = this.currentUnappliedRotationDegrees;
            this.reportedPixelWidthHeightRatio = this.currentPixelWidthHeightRatio;
        }
    }

    private void maybeRenotifyRenderedFirstFrame() {
        if(this.renderedFirstFrame) {
            this.eventDispatcher.renderedFirstFrame(this.surface);
        }
    }

    private void maybeRenotifyVideoSizeChanged() {
        int v1 = -1;
        if(this.reportedWidth != v1 || this.reportedHeight != v1) {
            this.eventDispatcher.videoSizeChanged(this.reportedWidth, this.reportedHeight, this.reportedUnappliedRotationDegrees, this.reportedPixelWidthHeightRatio);
        }
    }

    protected void onCodecInitialized(String arg7, long arg8, long arg10) {
        this.eventDispatcher.decoderInitialized(arg7, arg8, arg10);
        this.codecNeedsSetOutputSurfaceWorkaround = this.codecNeedsSetOutputSurfaceWorkaround(arg7);
    }

    protected void onDisabled() {
        this.currentWidth = -1;
        this.currentHeight = -1;
        this.currentPixelWidthHeightRatio = -1f;
        this.pendingPixelWidthHeightRatio = -1f;
        this.outputStreamOffsetUs = -9223372036854775807L;
        this.lastInputTimeUs = -9223372036854775807L;
        this.pendingOutputStreamOffsetCount = 0;
        this.clearReportedVideoSize();
        this.clearRenderedFirstFrame();
        this.frameReleaseTimeHelper.disable();
        this.tunnelingOnFrameRenderedListener = null;
        this.tunneling = false;
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
        this.tunnelingAudioSessionId = this.getConfiguration().tunnelingAudioSessionId;
        arg2 = this.tunnelingAudioSessionId != 0 ? true : false;
        this.tunneling = arg2;
        this.eventDispatcher.enabled(this.decoderCounters);
        this.frameReleaseTimeHelper.enable();
    }

    protected void onInputFormatChanged(Format arg2) {
        super.onInputFormatChanged(arg2);
        this.eventDispatcher.inputFormatChanged(arg2);
        this.pendingPixelWidthHeightRatio = arg2.pixelWidthHeightRatio;
        this.pendingRotationDegrees = arg2.rotationDegrees;
    }

    protected void onOutputFormatChanged(MediaCodec arg5, MediaFormat arg6) {
        int v0 = !arg6.containsKey("crop-right") || !arg6.containsKey("crop-left") || !arg6.containsKey("crop-bottom") || !arg6.containsKey("crop-top") ? 0 : 1;
        int v2 = v0 != 0 ? arg6.getInteger("crop-right") - arg6.getInteger("crop-left") + 1 : arg6.getInteger("width");
        this.currentWidth = v2;
        v0 = v0 != 0 ? arg6.getInteger("crop-bottom") - arg6.getInteger("crop-top") + 1 : arg6.getInteger("height");
        this.currentHeight = v0;
        this.currentPixelWidthHeightRatio = this.pendingPixelWidthHeightRatio;
        if(Util.SDK_INT >= 21) {
            if(this.pendingRotationDegrees != 90 && this.pendingRotationDegrees != 270) {
                goto label_60;
            }

            int v6 = this.currentWidth;
            this.currentWidth = this.currentHeight;
            this.currentHeight = v6;
            this.currentPixelWidthHeightRatio = 1f / this.currentPixelWidthHeightRatio;
        }
        else {
            this.currentUnappliedRotationDegrees = this.pendingRotationDegrees;
        }

    label_60:
        arg5.setVideoScalingMode(this.scalingMode);
    }

    protected void onPositionReset(long arg5, boolean arg7) {
        super.onPositionReset(arg5, arg7);
        this.clearRenderedFirstFrame();
        arg5 = -9223372036854775807L;
        this.initialPositionUs = arg5;
        this.consecutiveDroppedFrameCount = 0;
        this.lastInputTimeUs = arg5;
        if(this.pendingOutputStreamOffsetCount != 0) {
            this.outputStreamOffsetUs = this.pendingOutputStreamOffsetsUs[this.pendingOutputStreamOffsetCount - 1];
            this.pendingOutputStreamOffsetCount = 0;
        }

        if(arg7) {
            this.setJoiningDeadlineMs();
        }
        else {
            this.joiningDeadlineMs = arg5;
        }
    }

    protected void onProcessedOutputBuffer(long arg6) {
        --this.buffersInCodecCount;
        while(this.pendingOutputStreamOffsetCount != 0) {
            if(arg6 < this.pendingOutputStreamSwitchTimesUs[0]) {
                return;
            }

            this.outputStreamOffsetUs = this.pendingOutputStreamOffsetsUs[0];
            --this.pendingOutputStreamOffsetCount;
            System.arraycopy(this.pendingOutputStreamOffsetsUs, 1, this.pendingOutputStreamOffsetsUs, 0, this.pendingOutputStreamOffsetCount);
            System.arraycopy(this.pendingOutputStreamSwitchTimesUs, 1, this.pendingOutputStreamSwitchTimesUs, 0, this.pendingOutputStreamOffsetCount);
        }
    }

    protected void onQueueInputBuffer(DecoderInputBuffer arg5) {
        ++this.buffersInCodecCount;
        this.lastInputTimeUs = Math.max(arg5.timeUs, this.lastInputTimeUs);
        if(Util.SDK_INT < 23 && (this.tunneling)) {
            this.maybeNotifyRenderedFirstFrame();
        }
    }

    protected void onStarted() {
        super.onStarted();
        this.droppedFrames = 0;
        this.droppedFrameAccumulationStartTimeMs = SystemClock.elapsedRealtime();
        this.lastRenderTimeUs = SystemClock.elapsedRealtime() * 1000;
    }

    protected void onStopped() {
        this.joiningDeadlineMs = -9223372036854775807L;
        this.maybeNotifyDroppedFrames();
        super.onStopped();
    }

    protected void onStreamChanged(Format[] arg6, long arg7) {
        if(this.outputStreamOffsetUs == -9223372036854775807L) {
            this.outputStreamOffsetUs = arg7;
        }
        else {
            if(this.pendingOutputStreamOffsetCount == this.pendingOutputStreamOffsetsUs.length) {
                Log.w("MediaCodecVideoRenderer", "Too many stream changes, so dropping offset: " + this.pendingOutputStreamOffsetsUs[this.pendingOutputStreamOffsetCount - 1]);
            }
            else {
                ++this.pendingOutputStreamOffsetCount;
            }

            this.pendingOutputStreamOffsetsUs[this.pendingOutputStreamOffsetCount - 1] = arg7;
            this.pendingOutputStreamSwitchTimesUs[this.pendingOutputStreamOffsetCount - 1] = this.lastInputTimeUs;
        }

        super.onStreamChanged(arg6, arg7);
    }

    protected boolean processOutputBuffer(long arg22, long arg24, MediaCodec arg26, ByteBuffer arg27, int arg28, int arg29, long arg30, boolean arg32) {
        MediaCodecVideoRenderer v7 = this;
        long v5 = arg22;
        long v8 = arg24;
        MediaCodec v10 = arg26;
        int v11 = arg28;
        long v0 = arg30;
        if(v7.initialPositionUs == -9223372036854775807L) {
            v7.initialPositionUs = v5;
        }

        long v12 = v0 - v7.outputStreamOffsetUs;
        if(arg32) {
            v7.skipOutputBuffer(v10, v11, v12);
            return 1;
        }

        long v2 = v0 - v5;
        if(v7.surface == v7.dummySurface) {
            if(MediaCodecVideoRenderer.isBufferLate(v2)) {
                v7.skipOutputBuffer(v10, v11, v12);
                return 1;
            }

            return 0;
        }

        long v19 = 1000;
        long v17 = SystemClock.elapsedRealtime() * v19;
        int v4 = this.getState() == 2 ? 1 : 0;
        if((v7.renderedFirstFrame) && (v4 == 0 || !v7.shouldForceRenderOutputBuffer(v2, v17 - v7.lastRenderTimeUs))) {
            if(v4 != 0) {
                if(v5 == v7.initialPositionUs) {
                }
                else {
                    long v14 = System.nanoTime();
                    v17 = v7.frameReleaseTimeHelper.adjustReleaseTime(v0, (v2 - (v17 - v8)) * v19 + v14);
                    v14 = (v17 - v14) / v19;
                    if((v7.shouldDropBuffersToKeyframe(v14, v8)) && (this.maybeDropBuffersToKeyframe(arg26, arg28, v12, arg22))) {
                        return 0;
                    }

                    if(v7.shouldDropOutputBuffer(v14, v8)) {
                        v7.dropOutputBuffer(v10, v11, v12);
                    }
                    else if(Util.SDK_INT >= 21) {
                        if(v14 < 50000) {
                            this.renderOutputBufferV21(arg26, arg28, v12, v17);
                        }
                        else {
                            return 0;
                        }
                    }
                    else if(v14 < 30000) {
                        if(v14 > 11000) {
                            v14 -= 10000;
                            try {
                                Thread.sleep(v14 / v19);
                            }
                            catch(InterruptedException ) {
                                Thread.currentThread().interrupt();
                                return 0;
                            }
                        }

                        v7.renderOutputBuffer(v10, v11, v12);
                    }
                    else {
                        return 0;
                    }

                    return 1;
                }
            }

            return 0;
        }

        if(Util.SDK_INT >= 21) {
            this.renderOutputBufferV21(arg26, arg28, v12, System.nanoTime());
        }
        else {
            v7.renderOutputBuffer(v10, v11, v12);
        }

        return 1;
    }

    protected void releaseCodec() {
        Surface v1 = null;
        try {
            super.releaseCodec();
        }
        catch(Throwable v2) {
            this.buffersInCodecCount = 0;
            if(this.dummySurface != null) {
                if(this.surface == this.dummySurface) {
                    this.surface = v1;
                }

                this.dummySurface.release();
                this.dummySurface = v1;
            }

            throw v2;
        }

        this.buffersInCodecCount = 0;
        if(this.dummySurface != null) {
            if(this.surface == this.dummySurface) {
                this.surface = v1;
            }

            this.dummySurface.release();
            this.dummySurface = v1;
        }
    }

    protected void renderOutputBuffer(MediaCodec arg3, int arg4, long arg5) {
        this.maybeNotifyVideoSizeChanged();
        TraceUtil.beginSection("releaseOutputBuffer");
        arg3.releaseOutputBuffer(arg4, true);
        TraceUtil.endSection();
        this.lastRenderTimeUs = SystemClock.elapsedRealtime() * 1000;
        ++this.decoderCounters.renderedOutputBufferCount;
        this.consecutiveDroppedFrameCount = 0;
        this.maybeNotifyRenderedFirstFrame();
    }

    @TargetApi(value=21) protected void renderOutputBufferV21(MediaCodec arg1, int arg2, long arg3, long arg5) {
        this.maybeNotifyVideoSizeChanged();
        TraceUtil.beginSection("releaseOutputBuffer");
        arg1.releaseOutputBuffer(arg2, arg5);
        TraceUtil.endSection();
        this.lastRenderTimeUs = SystemClock.elapsedRealtime() * 1000;
        ++this.decoderCounters.renderedOutputBufferCount;
        this.consecutiveDroppedFrameCount = 0;
        this.maybeNotifyRenderedFirstFrame();
    }

    private void setJoiningDeadlineMs() {
        long v0 = this.allowedJoiningTimeMs > 0 ? SystemClock.elapsedRealtime() + this.allowedJoiningTimeMs : -9223372036854775807L;
        this.joiningDeadlineMs = v0;
    }

    @TargetApi(value=23) private static void setOutputSurfaceV23(MediaCodec arg0, Surface arg1) {
        arg0.setOutputSurface(arg1);
    }

    private void setSurface(Surface arg6) {
        if(arg6 == null) {
            if(this.dummySurface == null) {
                MediaCodecInfo v0 = this.getCodecInfo();
                if(v0 == null) {
                    goto label_14;
                }
                else if(this.shouldUseDummySurface(v0)) {
                    this.dummySurface = DummySurface.newInstanceV17(this.context, v0.secure);
                }
                else {
                    goto label_14;
                }
            }

            arg6 = this.dummySurface;
        }

    label_14:
        if(this.surface != arg6) {
            this.surface = arg6;
            int v0_1 = this.getState();
            int v2 = 2;
            if(v0_1 == 1 || v0_1 == v2) {
                MediaCodec v1 = this.getCodec();
                if(Util.SDK_INT >= 23 && v1 != null && arg6 != null && !this.codecNeedsSetOutputSurfaceWorkaround) {
                    MediaCodecVideoRenderer.setOutputSurfaceV23(v1, arg6);
                    goto label_34;
                }

                this.releaseCodec();
                this.maybeInitCodec();
            }

        label_34:
            if(arg6 != null && arg6 != this.dummySurface) {
                this.maybeRenotifyVideoSizeChanged();
                this.clearRenderedFirstFrame();
                if(v0_1 == v2) {
                    this.setJoiningDeadlineMs();
                }
                else {
                }

                return;
            }

            this.clearReportedVideoSize();
            this.clearRenderedFirstFrame();
        }
        else {
            if(arg6 == null) {
                return;
            }

            if(arg6 == this.dummySurface) {
                return;
            }

            this.maybeRenotifyVideoSizeChanged();
            this.maybeRenotifyRenderedFirstFrame();
        }
    }

    protected boolean shouldDropBuffersToKeyframe(long arg1, long arg3) {
        return MediaCodecVideoRenderer.isBufferVeryLate(arg1);
    }

    protected boolean shouldDropOutputBuffer(long arg1, long arg3) {
        return MediaCodecVideoRenderer.isBufferLate(arg1);
    }

    protected boolean shouldForceRenderOutputBuffer(long arg2, long arg4) {
        boolean v2 = !MediaCodecVideoRenderer.isBufferLate(arg2) || arg4 <= 100000 ? false : true;
        return v2;
    }

    protected boolean shouldInitCodec(MediaCodecInfo arg2) {
        boolean v2 = this.surface != null || (this.shouldUseDummySurface(arg2)) ? true : false;
        return v2;
    }

    private boolean shouldUseDummySurface(MediaCodecInfo arg3) {
        boolean v3;
        if(Util.SDK_INT < 23 || (this.tunneling) || (this.codecNeedsSetOutputSurfaceWorkaround(arg3.name))) {
        label_15:
            v3 = false;
        }
        else {
            if((arg3.secure) && !DummySurface.isSecureSupported(this.context)) {
                goto label_15;
            }

            v3 = true;
        }

        return v3;
    }

    protected void skipOutputBuffer(MediaCodec arg1, int arg2, long arg3) {
        TraceUtil.beginSection("skipVideoBuffer");
        arg1.releaseOutputBuffer(arg2, false);
        TraceUtil.endSection();
        ++this.decoderCounters.skippedOutputBufferCount;
    }

    protected int supportsFormat(MediaCodecSelector arg8, DrmSessionManager arg9, Format arg10) {
        boolean v3_1;
        int v1 = 0;
        if(!MimeTypes.isVideo(arg10.sampleMimeType)) {
            return 0;
        }

        DrmInitData v0 = arg10.drmInitData;
        if(v0 != null) {
            int v2 = 0;
            int v3 = 0;
            while(v2 < v0.schemeDataCount) {
                v3 |= v0.get(v2).requiresSecureDecryption;
                ++v2;
            }
        }
        else {
            v3_1 = false;
        }

        List v2_1 = arg8.getDecoderInfos(arg10, v3_1);
        int v5 = 2;
        if(v2_1.isEmpty()) {
            if((((int)v3_1)) == 0 || (arg8.getDecoderInfos(arg10, false).isEmpty())) {
                v5 = 1;
            }
            else {
            }

            return v5;
        }

        if(!MediaCodecVideoRenderer.supportsFormatDrm(arg9, v0)) {
            return v5;
        }

        Object v8 = v2_1.get(0);
        boolean v9 = ((MediaCodecInfo)v8).isCodecSupported(arg10.codecs);
        if((v9) && arg10.width > 0 && arg10.height > 0) {
            if(Util.SDK_INT >= 21) {
                v9 = ((MediaCodecInfo)v8).isVideoSizeAndRateSupportedV21(arg10.width, arg10.height, ((double)arg10.frameRate));
            }
            else {
                v9 = arg10.width * arg10.height <= MediaCodecUtil.maxH264DecodableFrameSize() ? true : false;
                if(v9) {
                    goto label_77;
                }

                Log.d("MediaCodecVideoRenderer", "FalseCheck [legacyFrameSize, " + arg10.width + "x" + arg10.height + "] [" + Util.DEVICE_DEBUG_INFO + "]");
            }
        }

    label_77:
        int v10 = ((MediaCodecInfo)v8).adaptive ? 16 : 8;
        if(((MediaCodecInfo)v8).tunneling) {
            v1 = 32;
        }

        int v8_1 = v9 ? 4 : 3;
        return v8_1 | (v10 | v1);
    }

    protected void updateDroppedBufferCounters(int arg3) {
        this.decoderCounters.droppedBufferCount += arg3;
        this.droppedFrames += arg3;
        this.consecutiveDroppedFrameCount += arg3;
        this.decoderCounters.maxConsecutiveDroppedBufferCount = Math.max(this.consecutiveDroppedFrameCount, this.decoderCounters.maxConsecutiveDroppedBufferCount);
        if(this.droppedFrames >= this.maxDroppedFramesToNotify) {
            this.maybeNotifyDroppedFrames();
        }
    }
}

