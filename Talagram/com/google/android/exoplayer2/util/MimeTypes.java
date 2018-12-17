package com.google.android.exoplayer2.util;

import android.text.TextUtils;
import java.util.ArrayList;

public final class MimeTypes {
    final class CustomMimeType {
        public final String codecPrefix;
        public final String mimeType;
        public final int trackType;

        public CustomMimeType(String arg1, String arg2, int arg3) {
            super();
            this.mimeType = arg1;
            this.codecPrefix = arg2;
            this.trackType = arg3;
        }
    }

    public static final String APPLICATION_CAMERA_MOTION = "application/x-camera-motion";
    public static final String APPLICATION_CEA608 = "application/cea-608";
    public static final String APPLICATION_CEA708 = "application/cea-708";
    public static final String APPLICATION_DVBSUBS = "application/dvbsubs";
    public static final String APPLICATION_EMSG = "application/x-emsg";
    public static final String APPLICATION_EXIF = "application/x-exif";
    public static final String APPLICATION_ID3 = "application/id3";
    public static final String APPLICATION_M3U8 = "application/x-mpegURL";
    public static final String APPLICATION_MP4 = "application/mp4";
    public static final String APPLICATION_MP4CEA608 = "application/x-mp4-cea-608";
    public static final String APPLICATION_MP4VTT = "application/x-mp4-vtt";
    public static final String APPLICATION_MPD = "application/dash+xml";
    public static final String APPLICATION_PGS = "application/pgs";
    public static final String APPLICATION_RAWCC = "application/x-rawcc";
    public static final String APPLICATION_SCTE35 = "application/x-scte35";
    public static final String APPLICATION_SS = "application/vnd.ms-sstr+xml";
    public static final String APPLICATION_SUBRIP = "application/x-subrip";
    public static final String APPLICATION_TTML = "application/ttml+xml";
    public static final String APPLICATION_TX3G = "application/x-quicktime-tx3g";
    public static final String APPLICATION_VOBSUB = "application/vobsub";
    public static final String APPLICATION_WEBM = "application/webm";
    public static final String AUDIO_AAC = "audio/mp4a-latm";
    public static final String AUDIO_AC3 = "audio/ac3";
    public static final String AUDIO_ALAC = "audio/alac";
    public static final String AUDIO_ALAW = "audio/g711-alaw";
    public static final String AUDIO_AMR_NB = "audio/3gpp";
    public static final String AUDIO_AMR_WB = "audio/amr-wb";
    public static final String AUDIO_DTS = "audio/vnd.dts";
    public static final String AUDIO_DTS_EXPRESS = "audio/vnd.dts.hd;profile=lbr";
    public static final String AUDIO_DTS_HD = "audio/vnd.dts.hd";
    public static final String AUDIO_E_AC3 = "audio/eac3";
    public static final String AUDIO_E_AC3_JOC = "audio/eac3-joc";
    public static final String AUDIO_FLAC = "audio/flac";
    public static final String AUDIO_MLAW = "audio/g711-mlaw";
    public static final String AUDIO_MP4 = "audio/mp4";
    public static final String AUDIO_MPEG = "audio/mpeg";
    public static final String AUDIO_MPEG_L1 = "audio/mpeg-L1";
    public static final String AUDIO_MPEG_L2 = "audio/mpeg-L2";
    public static final String AUDIO_MSGSM = "audio/gsm";
    public static final String AUDIO_OPUS = "audio/opus";
    public static final String AUDIO_RAW = "audio/raw";
    public static final String AUDIO_TRUEHD = "audio/true-hd";
    public static final String AUDIO_UNKNOWN = "audio/x-unknown";
    public static final String AUDIO_VORBIS = "audio/vorbis";
    public static final String AUDIO_WEBM = "audio/webm";
    public static final String BASE_TYPE_APPLICATION = "application";
    public static final String BASE_TYPE_AUDIO = "audio";
    public static final String BASE_TYPE_TEXT = "text";
    public static final String BASE_TYPE_VIDEO = "video";
    public static final String TEXT_SSA = "text/x-ssa";
    public static final String TEXT_VTT = "text/vtt";
    public static final String VIDEO_H263 = "video/3gpp";
    public static final String VIDEO_H264 = "video/avc";
    public static final String VIDEO_H265 = "video/hevc";
    public static final String VIDEO_MP4 = "video/mp4";
    public static final String VIDEO_MP4V = "video/mp4v-es";
    public static final String VIDEO_MPEG = "video/mpeg";
    public static final String VIDEO_MPEG2 = "video/mpeg2";
    public static final String VIDEO_UNKNOWN = "video/x-unknown";
    public static final String VIDEO_VC1 = "video/wvc1";
    public static final String VIDEO_VP8 = "video/x-vnd.on2.vp8";
    public static final String VIDEO_VP9 = "video/x-vnd.on2.vp9";
    public static final String VIDEO_WEBM = "video/webm";
    private static final ArrayList customMimeTypes;

    static {
        MimeTypes.customMimeTypes = new ArrayList();
    }

    private MimeTypes() {
        super();
    }

    public static String getAudioMediaMimeType(String arg5) {
        String v0 = null;
        if(arg5 == null) {
            return v0;
        }

        String[] v5 = Util.splitCodecs(arg5);
        int v1 = v5.length;
        int v2;
        for(v2 = 0; v2 < v1; ++v2) {
            String v3 = MimeTypes.getMediaMimeType(v5[v2]);
            if(v3 != null && (MimeTypes.isAudio(v3))) {
                return v3;
            }
        }

        return v0;
    }

    private static String getCustomMimeTypeForCodec(String arg4) {
        int v0 = MimeTypes.customMimeTypes.size();
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            Object v2 = MimeTypes.customMimeTypes.get(v1);
            if(arg4.startsWith(((CustomMimeType)v2).codecPrefix)) {
                return ((CustomMimeType)v2).mimeType;
            }
        }

        return null;
    }

    public static int getEncoding(String arg3) {
        int v3;
        int v1 = 5;
        switch(arg3.hashCode()) {
            case 187078296: {
                if(!arg3.equals("audio/ac3")) {
                    goto label_35;
                }

                v3 = 0;
                break;
            }
            case 1504578661: {
                if(arg3.equals("audio/eac3")) {
                    v3 = 1;
                    goto label_36;
                }

            label_35:
                v3 = -1;
                break;
            }
            case 1505942594: {
                if(!arg3.equals("audio/vnd.dts.hd")) {
                    goto label_35;
                }

                v3 = 4;
                break;
            }
            case 1556697186: {
                if(!arg3.equals("audio/true-hd")) {
                    goto label_35;
                }

                v3 = 5;
                break;
            }
            case -2123537834: {
                if(!arg3.equals("audio/eac3-joc")) {
                    goto label_35;
                }

                v3 = 2;
                break;
            }
            case -1095064472: {
                if(!arg3.equals("audio/vnd.dts")) {
                    goto label_35;
                }

                v3 = 3;
                break;
            }
            default: {
                goto label_35;
            }
        }

    label_36:
        switch(v3) {
            case 0: {
                return v1;
            }
            case 1: 
            case 2: {
                return 6;
            }
            case 3: {
                return 7;
            }
            case 4: {
                return 8;
            }
            case 5: {
                return 14;
            }
        }

        return 0;
    }

    public static String getMediaMimeType(String arg3) {
        String v0 = null;
        if(arg3 == null) {
            return v0;
        }

        arg3 = arg3.trim();
        if(!arg3.startsWith("avc1")) {
            if(arg3.startsWith("avc3")) {
            }
            else {
                if(!arg3.startsWith("hev1")) {
                    if(arg3.startsWith("hvc1")) {
                    }
                    else {
                        if(!arg3.startsWith("vp9")) {
                            if(arg3.startsWith("vp09")) {
                            }
                            else {
                                if(!arg3.startsWith("vp8")) {
                                    if(arg3.startsWith("vp08")) {
                                    }
                                    else if(arg3.startsWith("mp4a")) {
                                        if(arg3.startsWith("mp4a.")) {
                                            arg3 = arg3.substring(5);
                                            int v2 = 2;
                                            if(arg3.length() >= v2) {
                                                try {
                                                    arg3 = MimeTypes.getMimeTypeFromMp4ObjectType(Integer.parseInt(Util.toUpperInvariant(arg3.substring(0, v2)), 16));
                                                }
                                                catch(NumberFormatException ) {
                                                label_50:
                                                    arg3 = v0;
                                                }
                                            }
                                            else {
                                                goto label_50;
                                            }
                                        }
                                        else {
                                            goto label_50;
                                        }

                                        if(arg3 == null) {
                                            arg3 = "audio/mp4a-latm";
                                        }

                                        return arg3;
                                    }
                                    else {
                                        if(!arg3.startsWith("ac-3")) {
                                            if(arg3.startsWith("dac3")) {
                                            }
                                            else {
                                                if(!arg3.startsWith("ec-3")) {
                                                    if(arg3.startsWith("dec3")) {
                                                    }
                                                    else if(arg3.startsWith("ec+3")) {
                                                        return "audio/eac3-joc";
                                                    }
                                                    else {
                                                        if(!arg3.startsWith("dtsc")) {
                                                            if(arg3.startsWith("dtse")) {
                                                            }
                                                            else {
                                                                if(!arg3.startsWith("dtsh")) {
                                                                    if(arg3.startsWith("dtsl")) {
                                                                    }
                                                                    else if(arg3.startsWith("opus")) {
                                                                        return "audio/opus";
                                                                    }
                                                                    else if(arg3.startsWith("vorbis")) {
                                                                        return "audio/vorbis";
                                                                    }
                                                                    else {
                                                                        return MimeTypes.getCustomMimeTypeForCodec(arg3);
                                                                    }
                                                                }

                                                                return "audio/vnd.dts.hd";
                                                            }
                                                        }

                                                        return "audio/vnd.dts";
                                                    }
                                                }

                                                return "audio/eac3";
                                            }
                                        }

                                        return "audio/ac3";
                                    }
                                }

                                return "video/x-vnd.on2.vp8";
                            }
                        }

                        return "video/x-vnd.on2.vp9";
                    }
                }

                return "video/hevc";
            }
        }

        return "video/avc";
    }

    public static String getMimeTypeFromMp4ObjectType(int arg1) {
        if(arg1 != 35) {
            if(arg1 != 64) {
                if(arg1 == 163) {
                    return "video/wvc1";
                }
                else if(arg1 != 177) {
                    switch(arg1) {
                        case 32: {
                            return "video/mp4v-es";
                        }
                        case 33: {
                            return "video/avc";
                        }
                    }

                    switch(arg1) {
                        case 96: 
                        case 97: 
                        case 98: 
                        case 99: 
                        case 100: 
                        case 101: {
                            return "video/mpeg2";
                        }
                        case 102: 
                        case 103: 
                        case 104: {
                            return "audio/mp4a-latm";
                        }
                        case 106: {
                            return "video/mpeg";
                        }
                        case 105: 
                        case 107: {
                            return "audio/mpeg";
                        }
                    }

                    switch(arg1) {
                        case 165: {
                            return "audio/ac3";
                        }
                        case 166: {
                            return "audio/eac3";
                        }
                    }

                    switch(arg1) {
                        case 170: 
                        case 171: {
                            return "audio/vnd.dts.hd";
                        }
                        case 169: 
                        case 172: {
                            return "audio/vnd.dts";
                        }
                        case 173: {
                            return "audio/opus";
                        }
                    }

                    return null;
                }
                else {
                    return "video/x-vnd.on2.vp9";
                }
            }

            return "audio/mp4a-latm";
        }

        return "video/hevc";
    }

    private static String getTopLevelType(String arg3) {
        if(arg3 == null) {
            return null;
        }

        int v0 = arg3.indexOf(47);
        if(v0 != -1) {
            return arg3.substring(0, v0);
        }

        StringBuilder v1 = new StringBuilder();
        v1.append("Invalid mime type: ");
        v1.append(arg3);
        throw new IllegalArgumentException(v1.toString());
    }

    public static int getTrackType(String arg1) {
        if(TextUtils.isEmpty(((CharSequence)arg1))) {
            return -1;
        }

        if(MimeTypes.isAudio(arg1)) {
            return 1;
        }

        if(MimeTypes.isVideo(arg1)) {
            return 2;
        }

        if(!MimeTypes.isText(arg1) && !"application/cea-608".equals(arg1) && !"application/cea-708".equals(arg1) && !"application/x-mp4-cea-608".equals(arg1) && !"application/x-subrip".equals(arg1) && !"application/ttml+xml".equals(arg1) && !"application/x-quicktime-tx3g".equals(arg1) && !"application/x-mp4-vtt".equals(arg1) && !"application/x-rawcc".equals(arg1) && !"application/vobsub".equals(arg1) && !"application/pgs".equals(arg1)) {
            if("application/dvbsubs".equals(arg1)) {
            }
            else {
                if(!"application/id3".equals(arg1) && !"application/x-emsg".equals(arg1) && !"application/x-scte35".equals(arg1)) {
                    if("application/x-camera-motion".equals(arg1)) {
                    }
                    else {
                        return MimeTypes.getTrackTypeForCustomMimeType(arg1);
                    }
                }

                return 4;
            }
        }

        return 3;
    }

    private static int getTrackTypeForCustomMimeType(String arg4) {
        int v0 = MimeTypes.customMimeTypes.size();
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            Object v2 = MimeTypes.customMimeTypes.get(v1);
            if(arg4.equals(((CustomMimeType)v2).mimeType)) {
                return ((CustomMimeType)v2).trackType;
            }
        }

        return -1;
    }

    public static int getTrackTypeOfCodec(String arg0) {
        return MimeTypes.getTrackType(MimeTypes.getMediaMimeType(arg0));
    }

    public static String getVideoMediaMimeType(String arg5) {
        String v0 = null;
        if(arg5 == null) {
            return v0;
        }

        String[] v5 = Util.splitCodecs(arg5);
        int v1 = v5.length;
        int v2;
        for(v2 = 0; v2 < v1; ++v2) {
            String v3 = MimeTypes.getMediaMimeType(v5[v2]);
            if(v3 != null && (MimeTypes.isVideo(v3))) {
                return v3;
            }
        }

        return v0;
    }

    public static boolean isApplication(String arg1) {
        return "application".equals(MimeTypes.getTopLevelType(arg1));
    }

    public static boolean isAudio(String arg1) {
        return "audio".equals(MimeTypes.getTopLevelType(arg1));
    }

    public static boolean isText(String arg1) {
        return "text".equals(MimeTypes.getTopLevelType(arg1));
    }

    public static boolean isVideo(String arg1) {
        return "video".equals(MimeTypes.getTopLevelType(arg1));
    }

    public static void registerCustomMimeType(String arg2, String arg3, int arg4) {
        CustomMimeType v0 = new CustomMimeType(arg2, arg3, arg4);
        int v3 = MimeTypes.customMimeTypes.size();
        arg4 = 0;
        while(arg4 < v3) {
            if(arg2.equals(MimeTypes.customMimeTypes.get(arg4).mimeType)) {
                MimeTypes.customMimeTypes.remove(arg4);
            }
            else {
                ++arg4;
                continue;
            }

            break;
        }

        MimeTypes.customMimeTypes.add(v0);
    }
}

