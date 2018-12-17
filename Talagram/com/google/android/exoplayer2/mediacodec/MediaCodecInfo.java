package com.google.android.exoplayer2.mediacodec;

import android.annotation.TargetApi;
import android.graphics.Point;
import android.media.MediaCodecInfo$AudioCapabilities;
import android.media.MediaCodecInfo$CodecCapabilities;
import android.media.MediaCodecInfo$CodecProfileLevel;
import android.media.MediaCodecInfo$VideoCapabilities;
import android.util.Log;
import android.util.Pair;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.Util;

@TargetApi(value=16) public final class MediaCodecInfo {
    public static final int MAX_SUPPORTED_INSTANCES_UNKNOWN = -1;
    public static final String TAG = "MediaCodecInfo";
    public final boolean adaptive;
    public final MediaCodecInfo$CodecCapabilities capabilities;
    public final String mimeType;
    public final String name;
    public final boolean passthrough;
    public final boolean secure;
    public final boolean tunneling;

    private MediaCodecInfo(String arg1, String arg2, MediaCodecInfo$CodecCapabilities arg3, boolean arg4, boolean arg5, boolean arg6) {
        super();
        this.name = Assertions.checkNotNull(arg1);
        this.mimeType = arg2;
        this.capabilities = arg3;
        this.passthrough = arg4;
        boolean v1 = true;
        arg4 = (arg5) || arg3 == null || !MediaCodecInfo.isAdaptive(arg3) ? false : true;
        this.adaptive = arg4;
        arg4 = arg3 == null || !MediaCodecInfo.isTunneling(arg3) ? false : true;
        this.tunneling = arg4;
        if(!arg6 && (arg3 == null || !MediaCodecInfo.isSecure(arg3))) {
            v1 = false;
        }

        this.secure = v1;
    }

    private static int adjustMaxInputChannelCount(String arg3, String arg4, int arg5) {
        int v4;
        if(arg5 <= 1 && (Util.SDK_INT < 26 || arg5 <= 0) && !"audio/mpeg".equals(arg4) && !"audio/3gpp".equals(arg4) && !"audio/amr-wb".equals(arg4) && !"audio/mp4a-latm".equals(arg4) && !"audio/vorbis".equals(arg4) && !"audio/opus".equals(arg4) && !"audio/raw".equals(arg4) && !"audio/flac".equals(arg4) && !"audio/g711-alaw".equals(arg4) && !"audio/g711-mlaw".equals(arg4) && !"audio/gsm".equals(arg4)) {
            if("audio/ac3".equals(arg4)) {
                v4 = 6;
            }
            else if("audio/eac3".equals(arg4)) {
                v4 = 16;
            }
            else {
                v4 = 30;
            }

            Log.w("MediaCodecInfo", "AssumedMaxChannelAdjustment: " + arg3 + ", [" + arg5 + " to " + v4 + "]");
            return v4;
        }

        return arg5;
    }

    @TargetApi(value=21) public Point alignVideoSizeV21(int arg4, int arg5) {
        MediaCodecInfo$VideoCapabilities v0;
        String v4;
        Point v1 = null;
        if(this.capabilities == null) {
            v4 = "align.caps";
        }
        else {
            v0 = this.capabilities.getVideoCapabilities();
            if(v0 == null) {
                v4 = "align.vCaps";
            }
            else {
                goto label_11;
            }
        }

        this.logNoSupport(v4);
        return v1;
    label_11:
        int v1_1 = v0.getWidthAlignment();
        int v0_1 = v0.getHeightAlignment();
        return new Point(Util.ceilDivide(arg4, v1_1) * v1_1, Util.ceilDivide(arg5, v0_1) * v0_1);
    }

    @TargetApi(value=21) private static boolean areSizeAndRateSupportedV21(MediaCodecInfo$VideoCapabilities arg3, int arg4, int arg5, double arg6) {
        boolean v3 = arg6 == -1 || arg6 <= 0 ? arg3.isSizeSupported(arg4, arg5) : arg3.areSizeAndRateSupported(arg4, arg5, arg6);
        return v3;
    }

    public int getMaxSupportedInstances() {
        int v0 = Util.SDK_INT < 23 || this.capabilities == null ? -1 : MediaCodecInfo.getMaxSupportedInstancesV23(this.capabilities);
        return v0;
    }

    @TargetApi(value=23) private static int getMaxSupportedInstancesV23(MediaCodecInfo$CodecCapabilities arg0) {
        return arg0.getMaxSupportedInstances();
    }

    public MediaCodecInfo$CodecProfileLevel[] getProfileLevels() {
        MediaCodecInfo$CodecProfileLevel[] v0 = this.capabilities == null || this.capabilities.profileLevels == null ? new MediaCodecInfo$CodecProfileLevel[0] : this.capabilities.profileLevels;
        return v0;
    }

    private static boolean isAdaptive(MediaCodecInfo$CodecCapabilities arg2) {
        boolean v2 = Util.SDK_INT < 19 || !MediaCodecInfo.isAdaptiveV19(arg2) ? false : true;
        return v2;
    }

    @TargetApi(value=19) private static boolean isAdaptiveV19(MediaCodecInfo$CodecCapabilities arg1) {
        return arg1.isFeatureSupported("adaptive-playback");
    }

    @TargetApi(value=21) public boolean isAudioChannelCountSupportedV21(int arg5) {
        String v5;
        if(this.capabilities == null) {
            v5 = "channelCount.caps";
        }
        else {
            MediaCodecInfo$AudioCapabilities v0 = this.capabilities.getAudioCapabilities();
            if(v0 == null) {
                v5 = "channelCount.aCaps";
            }
            else if(MediaCodecInfo.adjustMaxInputChannelCount(this.name, this.mimeType, v0.getMaxInputChannelCount()) < arg5) {
                v5 = "channelCount.support, " + arg5;
            }
            else {
                return 1;
            }
        }

        this.logNoSupport(v5);
        return 0;
    }

    @TargetApi(value=21) public boolean isAudioSampleRateSupportedV21(int arg4) {
        String v4;
        if(this.capabilities == null) {
            v4 = "sampleRate.caps";
        }
        else {
            MediaCodecInfo$AudioCapabilities v0 = this.capabilities.getAudioCapabilities();
            if(v0 == null) {
                v4 = "sampleRate.aCaps";
            }
            else if(!v0.isSampleRateSupported(arg4)) {
                v4 = "sampleRate.support, " + arg4;
            }
            else {
                return 1;
            }
        }

        this.logNoSupport(v4);
        return 0;
    }

    public boolean isCodecSupported(String arg11) {
        String v2;
        StringBuilder v0;
        if(arg11 != null) {
            if(this.mimeType == null) {
            }
            else {
                String v1 = MimeTypes.getMediaMimeType(arg11);
                if(v1 == null) {
                    return 1;
                }
                else {
                    if(!this.mimeType.equals(v1)) {
                        v0 = new StringBuilder();
                        v2 = "codec.mime ";
                    }
                    else {
                        Pair v2_1 = MediaCodecUtil.getCodecProfileAndLevel(arg11);
                        if(v2_1 == null) {
                            return 1;
                        }
                        else {
                            MediaCodecInfo$CodecProfileLevel[] v4 = this.getProfileLevels();
                            int v5 = v4.length;
                            int v6;
                            for(v6 = 0; v6 < v5; ++v6) {
                                MediaCodecInfo$CodecProfileLevel v7 = v4[v6];
                                if(v7.profile == v2_1.first.intValue() && v7.level >= v2_1.second.intValue()) {
                                    return 1;
                                }
                            }

                            v0 = new StringBuilder();
                            v2 = "codec.profileLevel, ";
                        }
                    }

                    v0.append(v2);
                    v0.append(arg11);
                    v0.append(", ");
                    v0.append(v1);
                    this.logNoSupport(v0.toString());
                    return 0;
                }
            }
        }

        return 1;
    }

    private static boolean isSecure(MediaCodecInfo$CodecCapabilities arg2) {
        boolean v2 = Util.SDK_INT < 21 || !MediaCodecInfo.isSecureV21(arg2) ? false : true;
        return v2;
    }

    @TargetApi(value=21) private static boolean isSecureV21(MediaCodecInfo$CodecCapabilities arg1) {
        return arg1.isFeatureSupported("secure-playback");
    }

    private static boolean isTunneling(MediaCodecInfo$CodecCapabilities arg2) {
        boolean v2 = Util.SDK_INT < 21 || !MediaCodecInfo.isTunnelingV21(arg2) ? false : true;
        return v2;
    }

    @TargetApi(value=21) private static boolean isTunnelingV21(MediaCodecInfo$CodecCapabilities arg1) {
        return arg1.isFeatureSupported("tunneled-playback");
    }

    @TargetApi(value=21) public boolean isVideoSizeAndRateSupportedV21(int arg4, int arg5, double arg6) {
        String v4;
        if(this.capabilities == null) {
            v4 = "sizeAndRate.caps";
        }
        else {
            MediaCodecInfo$VideoCapabilities v0 = this.capabilities.getVideoCapabilities();
            if(v0 == null) {
                v4 = "sizeAndRate.vCaps";
            }
            else {
                if(!MediaCodecInfo.areSizeAndRateSupportedV21(v0, arg4, arg5, arg6)) {
                    if(arg4 < arg5) {
                        if(!MediaCodecInfo.areSizeAndRateSupportedV21(v0, arg5, arg4, arg6)) {
                        }
                        else {
                            this.logAssumedSupport("sizeAndRate.rotated, " + arg4 + "x" + arg5 + "x" + arg6);
                            return 1;
                        }
                    }

                    v4 = "sizeAndRate.support, " + arg4 + "x" + arg5 + "x" + arg6;
                    goto label_4;
                }

                return 1;
            }
        }

    label_4:
        this.logNoSupport(v4);
        return 0;
    }

    private void logAssumedSupport(String arg4) {
        Log.d("MediaCodecInfo", "AssumedSupport [" + arg4 + "] [" + this.name + ", " + this.mimeType + "] [" + Util.DEVICE_DEBUG_INFO + "]");
    }

    private void logNoSupport(String arg4) {
        Log.d("MediaCodecInfo", "NoSupport [" + arg4 + "] [" + this.name + ", " + this.mimeType + "] [" + Util.DEVICE_DEBUG_INFO + "]");
    }

    public static MediaCodecInfo newInstance(String arg8, String arg9, MediaCodecInfo$CodecCapabilities arg10) {
        return new MediaCodecInfo(arg8, arg9, arg10, false, false, false);
    }

    public static MediaCodecInfo newInstance(String arg8, String arg9, MediaCodecInfo$CodecCapabilities arg10, boolean arg11, boolean arg12) {
        return new MediaCodecInfo(arg8, arg9, arg10, false, arg11, arg12);
    }

    public static MediaCodecInfo newPassthroughInstance(String arg8) {
        return new MediaCodecInfo(arg8, null, null, true, false, false);
    }

    public String toString() {
        return this.name;
    }
}

