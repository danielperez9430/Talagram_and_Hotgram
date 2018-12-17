package com.google.android.exoplayer2.mediacodec;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.media.MediaCodecInfo$CodecCapabilities;
import android.media.MediaCodecInfo$CodecProfileLevel;
import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import android.util.SparseIntArray;
import com.google.android.exoplayer2.util.Util;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressLint(value={"InlinedApi"}) @TargetApi(value=16) public final class MediaCodecUtil {
    class com.google.android.exoplayer2.mediacodec.MediaCodecUtil$1 {
    }

    final class CodecKey {
        public final String mimeType;
        public final boolean secure;

        public CodecKey(String arg1, boolean arg2) {
            super();
            this.mimeType = arg1;
            this.secure = arg2;
        }

        public boolean equals(Object arg5) {
            boolean v0 = true;
            if(this == (((CodecKey)arg5))) {
                return 1;
            }

            if(arg5 != null) {
                if(arg5.getClass() != CodecKey.class) {
                }
                else {
                    if(!TextUtils.equals(this.mimeType, ((CodecKey)arg5).mimeType) || this.secure != ((CodecKey)arg5).secure) {
                        v0 = false;
                    }
                    else {
                    }

                    return v0;
                }
            }

            return 0;
        }

        public int hashCode() {
            int v0 = this.mimeType == null ? 0 : this.mimeType.hashCode();
            v0 = (v0 + 31) * 31;
            int v1 = this.secure ? 1231 : 1237;
            return v0 + v1;
        }
    }

    public class DecoderQueryException extends Exception {
        DecoderQueryException(Throwable arg1, com.google.android.exoplayer2.mediacodec.MediaCodecUtil$1 arg2) {
            this(arg1);
        }

        private DecoderQueryException(Throwable arg2) {
            super("Failed to query underlying media codecs", arg2);
        }
    }

    interface MediaCodecListCompat {
        int getCodecCount();

        MediaCodecInfo getCodecInfoAt(int arg1);

        boolean isSecurePlaybackSupported(String arg1, MediaCodecInfo$CodecCapabilities arg2);

        boolean secureDecodersExplicit();
    }

    final class MediaCodecListCompatV16 implements MediaCodecListCompat {
        MediaCodecListCompatV16(com.google.android.exoplayer2.mediacodec.MediaCodecUtil$1 arg1) {
            this();
        }

        private MediaCodecListCompatV16() {
            super();
        }

        public int getCodecCount() {
            return MediaCodecList.getCodecCount();
        }

        public MediaCodecInfo getCodecInfoAt(int arg1) {
            return MediaCodecList.getCodecInfoAt(arg1);
        }

        public boolean isSecurePlaybackSupported(String arg1, MediaCodecInfo$CodecCapabilities arg2) {
            return "video/avc".equals(arg1);
        }

        public boolean secureDecodersExplicit() {
            return 0;
        }
    }

    @TargetApi(value=21) final class MediaCodecListCompatV21 implements MediaCodecListCompat {
        private final int codecKind;
        private MediaCodecInfo[] mediaCodecInfos;

        public MediaCodecListCompatV21(boolean arg1) {
            super();
            this.codecKind = ((int)arg1);
        }

        private void ensureMediaCodecInfosInitialized() {
            if(this.mediaCodecInfos == null) {
                this.mediaCodecInfos = new MediaCodecList(this.codecKind).getCodecInfos();
            }
        }

        public int getCodecCount() {
            this.ensureMediaCodecInfosInitialized();
            return this.mediaCodecInfos.length;
        }

        public MediaCodecInfo getCodecInfoAt(int arg2) {
            this.ensureMediaCodecInfosInitialized();
            return this.mediaCodecInfos[arg2];
        }

        public boolean isSecurePlaybackSupported(String arg1, MediaCodecInfo$CodecCapabilities arg2) {
            return arg2.isFeatureSupported("secure-playback");
        }

        public boolean secureDecodersExplicit() {
            return 1;
        }
    }

    final class RawAudioCodecComparator implements Comparator {
        RawAudioCodecComparator(com.google.android.exoplayer2.mediacodec.MediaCodecUtil$1 arg1) {
            this();
        }

        private RawAudioCodecComparator() {
            super();
        }

        public int compare(com.google.android.exoplayer2.mediacodec.MediaCodecInfo arg1, com.google.android.exoplayer2.mediacodec.MediaCodecInfo arg2) {
            return RawAudioCodecComparator.scoreMediaCodecInfo(arg1) - RawAudioCodecComparator.scoreMediaCodecInfo(arg2);
        }

        public int compare(Object arg1, Object arg2) {
            return this.compare(((com.google.android.exoplayer2.mediacodec.MediaCodecInfo)arg1), ((com.google.android.exoplayer2.mediacodec.MediaCodecInfo)arg2));
        }

        private static int scoreMediaCodecInfo(com.google.android.exoplayer2.mediacodec.MediaCodecInfo arg2) {
            String v2 = arg2.name;
            if(!v2.startsWith("OMX.google")) {
                if(v2.startsWith("c2.android")) {
                }
                else {
                    if(Util.SDK_INT < 26 && (v2.equals("OMX.MTK.AUDIO.DECODER.RAW"))) {
                        return 1;
                    }

                    return 0;
                }
            }

            return -1;
        }
    }

    private static final SparseIntArray AVC_LEVEL_NUMBER_TO_CONST = null;
    private static final SparseIntArray AVC_PROFILE_NUMBER_TO_CONST = null;
    private static final String CODEC_ID_AVC1 = "avc1";
    private static final String CODEC_ID_AVC2 = "avc2";
    private static final String CODEC_ID_HEV1 = "hev1";
    private static final String CODEC_ID_HVC1 = "hvc1";
    private static final Map HEVC_CODEC_STRING_TO_PROFILE_LEVEL = null;
    private static final Pattern PROFILE_PATTERN = null;
    private static final RawAudioCodecComparator RAW_AUDIO_CODEC_COMPARATOR = null;
    private static final String TAG = "MediaCodecUtil";
    private static final HashMap decoderInfosCache;
    private static int maxH264DecodableFrameSize;

    static {
        MediaCodecUtil.PROFILE_PATTERN = Pattern.compile("^\\D?(\\d+)$");
        MediaCodecUtil.RAW_AUDIO_CODEC_COMPARATOR = new RawAudioCodecComparator(null);
        MediaCodecUtil.decoderInfosCache = new HashMap();
        MediaCodecUtil.maxH264DecodableFrameSize = -1;
        MediaCodecUtil.AVC_PROFILE_NUMBER_TO_CONST = new SparseIntArray();
        MediaCodecUtil.AVC_PROFILE_NUMBER_TO_CONST.put(66, 1);
        MediaCodecUtil.AVC_PROFILE_NUMBER_TO_CONST.put(77, 2);
        MediaCodecUtil.AVC_PROFILE_NUMBER_TO_CONST.put(88, 4);
        MediaCodecUtil.AVC_PROFILE_NUMBER_TO_CONST.put(100, 8);
        MediaCodecUtil.AVC_LEVEL_NUMBER_TO_CONST = new SparseIntArray();
        MediaCodecUtil.AVC_LEVEL_NUMBER_TO_CONST.put(10, 1);
        MediaCodecUtil.AVC_LEVEL_NUMBER_TO_CONST.put(11, 4);
        MediaCodecUtil.AVC_LEVEL_NUMBER_TO_CONST.put(12, 8);
        MediaCodecUtil.AVC_LEVEL_NUMBER_TO_CONST.put(13, 16);
        MediaCodecUtil.AVC_LEVEL_NUMBER_TO_CONST.put(20, 32);
        MediaCodecUtil.AVC_LEVEL_NUMBER_TO_CONST.put(21, 64);
        MediaCodecUtil.AVC_LEVEL_NUMBER_TO_CONST.put(22, 128);
        MediaCodecUtil.AVC_LEVEL_NUMBER_TO_CONST.put(30, 256);
        MediaCodecUtil.AVC_LEVEL_NUMBER_TO_CONST.put(31, 512);
        MediaCodecUtil.AVC_LEVEL_NUMBER_TO_CONST.put(32, 1024);
        MediaCodecUtil.AVC_LEVEL_NUMBER_TO_CONST.put(40, 2048);
        MediaCodecUtil.AVC_LEVEL_NUMBER_TO_CONST.put(41, 4096);
        MediaCodecUtil.AVC_LEVEL_NUMBER_TO_CONST.put(42, 8192);
        MediaCodecUtil.AVC_LEVEL_NUMBER_TO_CONST.put(50, 16384);
        MediaCodecUtil.AVC_LEVEL_NUMBER_TO_CONST.put(51, 32768);
        MediaCodecUtil.AVC_LEVEL_NUMBER_TO_CONST.put(52, 65536);
        MediaCodecUtil.HEVC_CODEC_STRING_TO_PROFILE_LEVEL = new HashMap();
        MediaCodecUtil.HEVC_CODEC_STRING_TO_PROFILE_LEVEL.put("L30", Integer.valueOf(1));
        MediaCodecUtil.HEVC_CODEC_STRING_TO_PROFILE_LEVEL.put("L60", Integer.valueOf(4));
        MediaCodecUtil.HEVC_CODEC_STRING_TO_PROFILE_LEVEL.put("L63", Integer.valueOf(16));
        MediaCodecUtil.HEVC_CODEC_STRING_TO_PROFILE_LEVEL.put("L90", Integer.valueOf(64));
        MediaCodecUtil.HEVC_CODEC_STRING_TO_PROFILE_LEVEL.put("L93", Integer.valueOf(256));
        MediaCodecUtil.HEVC_CODEC_STRING_TO_PROFILE_LEVEL.put("L120", Integer.valueOf(1024));
        MediaCodecUtil.HEVC_CODEC_STRING_TO_PROFILE_LEVEL.put("L123", Integer.valueOf(4096));
        MediaCodecUtil.HEVC_CODEC_STRING_TO_PROFILE_LEVEL.put("L150", Integer.valueOf(16384));
        MediaCodecUtil.HEVC_CODEC_STRING_TO_PROFILE_LEVEL.put("L153", Integer.valueOf(65536));
        MediaCodecUtil.HEVC_CODEC_STRING_TO_PROFILE_LEVEL.put("L156", Integer.valueOf(262144));
        MediaCodecUtil.HEVC_CODEC_STRING_TO_PROFILE_LEVEL.put("L180", Integer.valueOf(1048576));
        MediaCodecUtil.HEVC_CODEC_STRING_TO_PROFILE_LEVEL.put("L183", Integer.valueOf(4194304));
        MediaCodecUtil.HEVC_CODEC_STRING_TO_PROFILE_LEVEL.put("L186", Integer.valueOf(16777216));
        MediaCodecUtil.HEVC_CODEC_STRING_TO_PROFILE_LEVEL.put("H30", Integer.valueOf(2));
        MediaCodecUtil.HEVC_CODEC_STRING_TO_PROFILE_LEVEL.put("H60", Integer.valueOf(8));
        MediaCodecUtil.HEVC_CODEC_STRING_TO_PROFILE_LEVEL.put("H63", Integer.valueOf(32));
        MediaCodecUtil.HEVC_CODEC_STRING_TO_PROFILE_LEVEL.put("H90", Integer.valueOf(128));
        MediaCodecUtil.HEVC_CODEC_STRING_TO_PROFILE_LEVEL.put("H93", Integer.valueOf(512));
        MediaCodecUtil.HEVC_CODEC_STRING_TO_PROFILE_LEVEL.put("H120", Integer.valueOf(2048));
        MediaCodecUtil.HEVC_CODEC_STRING_TO_PROFILE_LEVEL.put("H123", Integer.valueOf(8192));
        MediaCodecUtil.HEVC_CODEC_STRING_TO_PROFILE_LEVEL.put("H150", Integer.valueOf(32768));
        MediaCodecUtil.HEVC_CODEC_STRING_TO_PROFILE_LEVEL.put("H153", Integer.valueOf(131072));
        MediaCodecUtil.HEVC_CODEC_STRING_TO_PROFILE_LEVEL.put("H156", Integer.valueOf(524288));
        MediaCodecUtil.HEVC_CODEC_STRING_TO_PROFILE_LEVEL.put("H180", Integer.valueOf(2097152));
        MediaCodecUtil.HEVC_CODEC_STRING_TO_PROFILE_LEVEL.put("H183", Integer.valueOf(8388608));
        MediaCodecUtil.HEVC_CODEC_STRING_TO_PROFILE_LEVEL.put("H186", Integer.valueOf(33554432));
    }

    private MediaCodecUtil() {
        super();
    }

    private static void applyWorkarounds(String arg1, List arg2) {
        if("audio/raw".equals(arg1)) {
            Collections.sort(arg2, MediaCodecUtil.RAW_AUDIO_CODEC_COMPARATOR);
        }
    }

    private static int avcLevelToMaxFrameSize(int arg5) {
        int v0 = 9437184;
        int v1 = 2097152;
        int v2 = 414720;
        int v3 = 25344;
        int v4 = 101376;
        switch(arg5) {
            case 1: 
            case 2: {
                return v3;
            }
            case 8: 
            case 16: 
            case 32: {
                return v4;
            }
            case 64: {
                return 202752;
            }
            case 128: 
            case 256: {
                return v2;
            }
            case 512: {
                return 921600;
            }
            case 1024: {
                return 1310720;
            }
            case 2048: 
            case 4096: {
                return v1;
            }
            case 8192: {
                return 2228224;
            }
            case 16384: {
                return 5652480;
            }
            case 32768: 
            case 65536: {
                return v0;
            }
        }

        return -1;
    }

    private static boolean codecNeedsDisableAdaptationWorkaround(String arg2) {
        boolean v2;
        if(Util.SDK_INT <= 22) {
            if(!"ODROID-XU3".equals(Util.MODEL) && !"Nexus 10".equals(Util.MODEL)) {
                goto label_19;
            }

            if(!"OMX.Exynos.AVC.Decoder".equals(arg2) && !"OMX.Exynos.AVC.Decoder.secure".equals(arg2)) {
                goto label_19;
            }

            v2 = true;
        }
        else {
        label_19:
            v2 = false;
        }

        return v2;
    }

    private static Pair getAvcProfileAndLevel(String arg5, String[] arg6) {
        Integer v0_1;
        Integer v6;
        int v1 = 2;
        Pair v2 = null;
        if(arg6.length < v1) {
            Log.w("MediaCodecUtil", "Ignoring malformed AVC codec string: " + arg5);
            return v2;
        }

        try {
            if(arg6[1].length() == 6) {
                Integer v1_1 = Integer.valueOf(Integer.parseInt(arg6[1].substring(0, v1), 16));
                v6 = Integer.valueOf(Integer.parseInt(arg6[1].substring(4), 16));
                v0_1 = v1_1;
            }
            else if(arg6.length >= 3) {
                v0_1 = Integer.valueOf(Integer.parseInt(arg6[1]));
                v6 = Integer.valueOf(Integer.parseInt(arg6[v1]));
            }
            else {
                goto label_72;
            }
        }
        catch(NumberFormatException ) {
            goto label_81;
        }

        int v3 = -1;
        int v5 = MediaCodecUtil.AVC_PROFILE_NUMBER_TO_CONST.get(v0_1.intValue(), v3);
        if(v5 == v3) {
            Log.w("MediaCodecUtil", "Unknown AVC profile: " + v0_1);
            return v2;
        }

        int v0_2 = MediaCodecUtil.AVC_LEVEL_NUMBER_TO_CONST.get(v6.intValue(), v3);
        if(v0_2 == v3) {
            Log.w("MediaCodecUtil", "Unknown AVC level: " + v6);
            return v2;
        }

        return new Pair(Integer.valueOf(v5), Integer.valueOf(v0_2));
        try {
        label_72:
            Log.w("MediaCodecUtil", "Ignoring malformed AVC codec string: " + arg5);
            return v2;
        }
        catch(NumberFormatException ) {
        label_81:
            Log.w("MediaCodecUtil", "Ignoring malformed AVC codec string: " + arg5);
            return v2;
        }
    }

    public static Pair getCodecProfileAndLevel(String arg6) {
        Pair v0 = null;
        if(arg6 == null) {
            return v0;
        }

        String[] v1 = arg6.split("\\.");
        int v2 = 0;
        String v3 = v1[0];
        switch(v3.hashCode()) {
            case 3006243: {
                if(!v3.equals("avc1")) {
                    goto label_30;
                }

                v2 = 2;
                break;
            }
            case 3006244: {
                if(!v3.equals("avc2")) {
                    goto label_30;
                }

                v2 = 3;
                break;
            }
            case 3199032: {
                if(v3.equals("hev1")) {
                    goto label_31;
                }

            label_30:
                v2 = -1;
                break;
            }
            case 3214780: {
                if(!v3.equals("hvc1")) {
                    goto label_30;
                }

                v2 = 1;
                break;
            }
            default: {
                goto label_30;
            }
        }

    label_31:
        switch(v2) {
            case 0: 
            case 1: {
                goto label_35;
            }
            case 2: 
            case 3: {
                goto label_33;
            }
        }

        return v0;
    label_33:
        return MediaCodecUtil.getAvcProfileAndLevel(arg6, v1);
    label_35:
        return MediaCodecUtil.getHevcProfileAndLevel(arg6, v1);
    }

    public static com.google.android.exoplayer2.mediacodec.MediaCodecInfo getDecoderInfo(String arg0, boolean arg1) {
        Object v0_2;
        List v0 = MediaCodecUtil.getDecoderInfos(arg0, arg1);
        if(v0.isEmpty()) {
            com.google.android.exoplayer2.mediacodec.MediaCodecInfo v0_1 = null;
        }
        else {
            v0_2 = v0.get(0);
        }

        return ((com.google.android.exoplayer2.mediacodec.MediaCodecInfo)v0_2);
    }

    public static List getDecoderInfos(String arg6, boolean arg7) {
        List v6_1;
        MediaCodecListCompatV16 v2_2;
        Object v2;
        CodecKey v1;
        Class v0 = MediaCodecUtil.class;
        __monitor_enter(v0);
        try {
            v1 = new CodecKey(arg6, arg7);
            v2 = MediaCodecUtil.decoderInfosCache.get(v1);
            if(v2 == null) {
                goto label_9;
            }
        }
        catch(Throwable v6) {
            goto label_62;
        }

        __monitor_exit(v0);
        return ((List)v2);
        try {
        label_9:
            com.google.android.exoplayer2.mediacodec.MediaCodecUtil$1 v3 = null;
            int v4 = 21;
            if(Util.SDK_INT >= v4) {
                MediaCodecListCompatV21 v2_1 = new MediaCodecListCompatV21(arg7);
            }
            else {
                v2_2 = new MediaCodecListCompatV16(v3);
            }

            ArrayList v5 = MediaCodecUtil.getDecoderInfosInternal(v1, ((MediaCodecListCompat)v2_2), arg6);
            if((arg7) && (v5.isEmpty()) && v4 <= Util.SDK_INT && Util.SDK_INT <= 23) {
                v2_2 = new MediaCodecListCompatV16(v3);
                v5 = MediaCodecUtil.getDecoderInfosInternal(v1, ((MediaCodecListCompat)v2_2), arg6);
                if(!v5.isEmpty()) {
                    Log.w("MediaCodecUtil", "MediaCodecList API didn\'t list secure decoder for: " + arg6 + ". Assuming: " + v5.get(0).name);
                }
            }

            if("audio/eac3-joc".equals(arg6)) {
                v5.addAll(MediaCodecUtil.getDecoderInfosInternal(new CodecKey("audio/eac3", v1.secure), ((MediaCodecListCompat)v2_2), arg6));
            }

            MediaCodecUtil.applyWorkarounds(arg6, ((List)v5));
            v6_1 = Collections.unmodifiableList(((List)v5));
            MediaCodecUtil.decoderInfosCache.put(v1, v6_1);
        }
        catch(Throwable v6) {
        label_62:
            __monitor_exit(v0);
            throw v6;
        }

        __monitor_exit(v0);
        return v6_1;
    }

    private static ArrayList getDecoderInfosInternal(CodecKey arg17, MediaCodecListCompat arg18, String arg19) {
        int v16;
        boolean v2_1;
        MediaCodecInfo$CodecCapabilities v0_1;
        String v15;
        int v14;
        String v10;
        MediaCodecInfo v9;
        int v8;
        int v5;
        String v4;
        ArrayList v3;
        CodecKey v1 = arg17;
        MediaCodecListCompat v2 = arg18;
        try {
            v3 = new ArrayList();
            v4 = v1.mimeType;
            v5 = arg18.getCodecCount();
            boolean v6 = arg18.secureDecodersExplicit();
            v8 = 0;
            while(true) {
            label_8:
                if(v8 >= v5) {
                    return v3;
                }

                v9 = v2.getCodecInfoAt(v8);
                v10 = v9.getName();
                if(!MediaCodecUtil.isCodecUsableDecoder(v9, v10, v6, arg19)) {
                    goto label_91;
                }

                String[] v12 = v9.getSupportedTypes();
                int v13 = v12.length;
                v14 = 0;
                while(true) {
                label_17:
                    if(v14 >= v13) {
                        goto label_91;
                    }

                    v15 = v12[v14];
                    if(!v15.equalsIgnoreCase(v4)) {
                        goto label_86;
                    }

                    break;
                }
            }
        }
        catch(Exception v0) {
            goto label_101;
        }

        try {
            v0_1 = v9.getCapabilitiesForType(v15);
            boolean v7 = v2.isSecurePlaybackSupported(v4, v0_1);
            v2_1 = MediaCodecUtil.codecNeedsDisableAdaptationWorkaround(v10);
            if(!v6) {
                goto label_33;
            }
        }
        catch(Exception v0) {
            v16 = v5;
            goto label_56;
        }

        v16 = v5;
        try {
            if(v1.secure != v7) {
                goto label_34;
            label_33:
                v16 = v5;
            label_34:
                if(!v6 && !v1.secure) {
                    goto label_38;
                }
            }
            else {
            label_38:
                v3.add(com.google.android.exoplayer2.mediacodec.MediaCodecInfo.newInstance(v10, v4, v0_1, v2_1, false));
                goto label_87;
            }

            if(v6) {
                goto label_87;
            }

            if(!v7) {
                goto label_87;
            }

            StringBuilder v7_1 = new StringBuilder();
            v7_1.append(v10);
            v7_1.append(".secure");
            v3.add(com.google.android.exoplayer2.mediacodec.MediaCodecInfo.newInstance(v7_1.toString(), v4, v0_1, v2_1, true));
            return v3;
        label_32:
        }
        catch(Exception v0) {
            goto label_32;
        }

        try {
        label_56:
            if(Util.SDK_INT <= 23 && !v3.isEmpty()) {
                Log.e("MediaCodecUtil", "Skipping codec " + v10 + " (failed to query capabilities)");
                goto label_87;
            }

            Log.e("MediaCodecUtil", "Failed to query codec " + v10 + " (" + v15 + ")");
            throw v0;
        }
        catch(Exception v0) {
            goto label_101;
        }

    label_86:
        v16 = v5;
    label_87:
        ++v14;
        v5 = v16;
        v2 = arg18;
        goto label_17;
    label_91:
        ++v8;
        v5 = v5;
        v2 = arg18;
        goto label_8;
        return v3;
    label_101:
        throw new DecoderQueryException(((Throwable)v0), null);
    }

    private static Pair getHevcProfileAndLevel(String arg5, String[] arg6) {
        int v5;
        Pair v1 = null;
        if(arg6.length < 4) {
            Log.w("MediaCodecUtil", "Ignoring malformed HEVC codec string: " + arg5);
            return v1;
        }

        Matcher v0_1 = MediaCodecUtil.PROFILE_PATTERN.matcher(arg6[1]);
        if(!v0_1.matches()) {
            Log.w("MediaCodecUtil", "Ignoring malformed HEVC codec string: " + arg5);
            return v1;
        }

        arg5 = v0_1.group(1);
        if("1".equals(arg5)) {
            v5 = 1;
        }
        else if("2".equals(arg5)) {
            v5 = 2;
        }
        else {
            goto label_57;
        }

        Object v6 = MediaCodecUtil.HEVC_CODEC_STRING_TO_PROFILE_LEVEL.get(arg6[3]);
        if(v6 == null) {
            Log.w("MediaCodecUtil", "Unknown HEVC level string: " + v0_1.group(1));
            return v1;
        }

        return new Pair(Integer.valueOf(v5), v6);
    label_57:
        Log.w("MediaCodecUtil", "Unknown HEVC profile string: " + arg5);
        return v1;
    }

    public static com.google.android.exoplayer2.mediacodec.MediaCodecInfo getPassthroughDecoderInfo() {
        com.google.android.exoplayer2.mediacodec.MediaCodecInfo v0 = MediaCodecUtil.getDecoderInfo("audio/raw", false);
        return v0 == null ? null : com.google.android.exoplayer2.mediacodec.MediaCodecInfo.newPassthroughInstance(v0.name);
    }

    private static boolean isCodecUsableDecoder(MediaCodecInfo arg2, String arg3, boolean arg4, String arg5) {
        if(!arg2.isEncoder() && ((arg4) || !arg3.endsWith(".secure"))) {
            if(Util.SDK_INT < 21 && (("CIPAACDecoder".equals(arg3)) || ("CIPMP3Decoder".equals(arg3)) || ("CIPVorbisDecoder".equals(arg3)) || ("CIPAMRNBDecoder".equals(arg3)) || ("AACDecoder".equals(arg3)) || ("MP3Decoder".equals(arg3)))) {
                return 0;
            }

            int v4 = 18;
            if(Util.SDK_INT < v4 && ("OMX.SEC.MP3.Decoder".equals(arg3))) {
                return 0;
            }

            if(Util.SDK_INT < v4 && ("OMX.MTK.AUDIO.DECODER.AAC".equals(arg3))) {
                if(!"a70".equals(Util.DEVICE)) {
                    if(!"Xiaomi".equals(Util.MANUFACTURER)) {
                    }
                    else if(Util.DEVICE.startsWith("HM")) {
                        return 0;
                    }

                    goto label_55;
                }

                return 0;
            }

        label_55:
            v4 = 16;
            if(Util.SDK_INT == v4 && ("OMX.qcom.audio.decoder.mp3".equals(arg3)) && (("dlxu".equals(Util.DEVICE)) || ("protou".equals(Util.DEVICE)) || ("ville".equals(Util.DEVICE)) || ("villeplus".equals(Util.DEVICE)) || ("villec2".equals(Util.DEVICE)) || (Util.DEVICE.startsWith("gee")) || ("C6602".equals(Util.DEVICE)) || ("C6603".equals(Util.DEVICE)) || ("C6606".equals(Util.DEVICE)) || ("C6616".equals(Util.DEVICE)) || ("L36h".equals(Util.DEVICE)) || ("SO-02E".equals(Util.DEVICE)))) {
                return 0;
            }

            if(Util.SDK_INT == v4 && ("OMX.qcom.audio.decoder.aac".equals(arg3)) && (("C1504".equals(Util.DEVICE)) || ("C1505".equals(Util.DEVICE)) || ("C1604".equals(Util.DEVICE)) || ("C1605".equals(Util.DEVICE)))) {
                return 0;
            }

            if(Util.SDK_INT < 24 && (("OMX.SEC.aac.dec".equals(arg3)) || ("OMX.Exynos.AAC.Decoder".equals(arg3))) && ("samsung".equals(Util.MANUFACTURER)) && ((Util.DEVICE.startsWith("zeroflte")) || (Util.DEVICE.startsWith("zerolte")) || (Util.DEVICE.startsWith("zenlte")) || ("SC-05G".equals(Util.DEVICE)) || ("marinelteatt".equals(Util.DEVICE)) || ("404SC".equals(Util.DEVICE)) || ("SC-04G".equals(Util.DEVICE)) || ("SCV31".equals(Util.DEVICE)))) {
                return 0;
            }

            v4 = 19;
            if(Util.SDK_INT <= v4 && ("OMX.SEC.vp8.dec".equals(arg3)) && ("samsung".equals(Util.MANUFACTURER)) && ((Util.DEVICE.startsWith("d2")) || (Util.DEVICE.startsWith("serrano")) || (Util.DEVICE.startsWith("jflte")) || (Util.DEVICE.startsWith("santos")) || (Util.DEVICE.startsWith("t0")))) {
                return 0;
            }

            if(Util.SDK_INT <= v4 && (Util.DEVICE.startsWith("jflte")) && ("OMX.qcom.video.decoder.vp8".equals(arg3))) {
                return 0;
            }

            if(("audio/eac3-joc".equals(arg5)) && ("OMX.MTK.AUDIO.DECODER.DSPAC3".equals(arg3))) {
                return 0;
            }

            return 1;
        }

        return 0;
    }

    public static int maxH264DecodableFrameSize() {
        if(MediaCodecUtil.maxH264DecodableFrameSize == -1) {
            int v1 = 0;
            com.google.android.exoplayer2.mediacodec.MediaCodecInfo v0 = MediaCodecUtil.getDecoderInfo("video/avc", false);
            if(v0 != null) {
                MediaCodecInfo$CodecProfileLevel[] v0_1 = v0.getProfileLevels();
                int v2 = v0_1.length;
                int v3 = 0;
                while(v1 < v2) {
                    v3 = Math.max(MediaCodecUtil.avcLevelToMaxFrameSize(v0_1[v1].level), v3);
                    ++v1;
                }

                int v0_2 = Util.SDK_INT >= 21 ? 345600 : 172800;
                v1 = Math.max(v3, v0_2);
            }

            MediaCodecUtil.maxH264DecodableFrameSize = v1;
        }

        return MediaCodecUtil.maxH264DecodableFrameSize;
    }

    public static void warmDecoderInfoCache(String arg1, boolean arg2) {
        try {
            MediaCodecUtil.getDecoderInfos(arg1, arg2);
        }
        catch(DecoderQueryException v1) {
            Log.e("MediaCodecUtil", "Codec warming failed", ((Throwable)v1));
        }
    }
}

