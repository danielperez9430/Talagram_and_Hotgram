package com.google.android.exoplayer2.source.hls.playlist;

import android.net.Uri;
import android.util.Base64;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.drm.DrmInitData$SchemeData;
import com.google.android.exoplayer2.drm.DrmInitData;
import com.google.android.exoplayer2.source.UnrecognizedInputFormatException;
import com.google.android.exoplayer2.upstream.ParsingLoadable$Parser;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.Util;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class HlsPlaylistParser implements Parser {
    class LineIterator {
        private final Queue extraLines;
        private String next;
        private final BufferedReader reader;

        public LineIterator(Queue arg1, BufferedReader arg2) {
            super();
            this.extraLines = arg1;
            this.reader = arg2;
        }

        public boolean hasNext() {
            if(this.next != null) {
                return 1;
            }

            if(!this.extraLines.isEmpty()) {
                this.next = this.extraLines.poll();
                return 1;
            }

            do {
                String v0 = this.reader.readLine();
                this.next = v0;
                if(v0 == null) {
                    return 0;
                }

                this.next = this.next.trim();
            }
            while(this.next.isEmpty());

            return 1;
        }

        public String next() {
            String v0;
            String v1 = null;
            if(this.hasNext()) {
                v0 = this.next;
                this.next = v1;
            }
            else {
                v0 = v1;
            }

            return v0;
        }
    }

    private static final String ATTR_CLOSED_CAPTIONS_NONE = "CLOSED-CAPTIONS=NONE";
    private static final String BOOLEAN_FALSE = "NO";
    private static final String BOOLEAN_TRUE = "YES";
    private static final String KEYFORMAT_IDENTITY = "identity";
    private static final String KEYFORMAT_WIDEVINE_PSSH_BINARY = "urn:uuid:edef8ba9-79d6-4ace-a3c8-27dcd51d21ed";
    private static final String KEYFORMAT_WIDEVINE_PSSH_JSON = "com.widevine";
    private static final String METHOD_AES_128 = "AES-128";
    private static final String METHOD_NONE = "NONE";
    private static final String METHOD_SAMPLE_AES = "SAMPLE-AES";
    private static final String METHOD_SAMPLE_AES_CENC = "SAMPLE-AES-CENC";
    private static final String METHOD_SAMPLE_AES_CTR = "SAMPLE-AES-CTR";
    private static final String PLAYLIST_HEADER = "#EXTM3U";
    private static final Pattern REGEX_ATTR_BYTERANGE = null;
    private static final Pattern REGEX_AUDIO = null;
    private static final Pattern REGEX_AUTOSELECT = null;
    private static final Pattern REGEX_AVERAGE_BANDWIDTH = null;
    private static final Pattern REGEX_BANDWIDTH = null;
    private static final Pattern REGEX_BYTERANGE = null;
    private static final Pattern REGEX_CODECS = null;
    private static final Pattern REGEX_DEFAULT = null;
    private static final Pattern REGEX_FORCED = null;
    private static final Pattern REGEX_FRAME_RATE = null;
    private static final Pattern REGEX_GROUP_ID = null;
    private static final Pattern REGEX_INSTREAM_ID = null;
    private static final Pattern REGEX_IV = null;
    private static final Pattern REGEX_KEYFORMAT = null;
    private static final Pattern REGEX_LANGUAGE = null;
    private static final Pattern REGEX_MEDIA_DURATION = null;
    private static final Pattern REGEX_MEDIA_SEQUENCE = null;
    private static final Pattern REGEX_MEDIA_TITLE = null;
    private static final Pattern REGEX_METHOD = null;
    private static final Pattern REGEX_NAME = null;
    private static final Pattern REGEX_PLAYLIST_TYPE = null;
    private static final Pattern REGEX_RESOLUTION = null;
    private static final Pattern REGEX_TARGET_DURATION = null;
    private static final Pattern REGEX_TIME_OFFSET = null;
    private static final Pattern REGEX_TYPE = null;
    private static final Pattern REGEX_URI = null;
    private static final Pattern REGEX_VERSION = null;
    private static final String TAG_BYTERANGE = "#EXT-X-BYTERANGE";
    private static final String TAG_DISCONTINUITY = "#EXT-X-DISCONTINUITY";
    private static final String TAG_DISCONTINUITY_SEQUENCE = "#EXT-X-DISCONTINUITY-SEQUENCE";
    private static final String TAG_ENDLIST = "#EXT-X-ENDLIST";
    private static final String TAG_GAP = "#EXT-X-GAP";
    private static final String TAG_INDEPENDENT_SEGMENTS = "#EXT-X-INDEPENDENT-SEGMENTS";
    private static final String TAG_INIT_SEGMENT = "#EXT-X-MAP";
    private static final String TAG_KEY = "#EXT-X-KEY";
    private static final String TAG_MEDIA = "#EXT-X-MEDIA";
    private static final String TAG_MEDIA_DURATION = "#EXTINF";
    private static final String TAG_MEDIA_SEQUENCE = "#EXT-X-MEDIA-SEQUENCE";
    private static final String TAG_PLAYLIST_TYPE = "#EXT-X-PLAYLIST-TYPE";
    private static final String TAG_PREFIX = "#EXT";
    private static final String TAG_PROGRAM_DATE_TIME = "#EXT-X-PROGRAM-DATE-TIME";
    private static final String TAG_START = "#EXT-X-START";
    private static final String TAG_STREAM_INF = "#EXT-X-STREAM-INF";
    private static final String TAG_TARGET_DURATION = "#EXT-X-TARGETDURATION";
    private static final String TAG_VERSION = "#EXT-X-VERSION";
    private static final String TYPE_AUDIO = "AUDIO";
    private static final String TYPE_CLOSED_CAPTIONS = "CLOSED-CAPTIONS";
    private static final String TYPE_SUBTITLES = "SUBTITLES";
    private static final String TYPE_VIDEO = "VIDEO";

    static {
        HlsPlaylistParser.REGEX_AVERAGE_BANDWIDTH = Pattern.compile("AVERAGE-BANDWIDTH=(\\d+)\\b");
        HlsPlaylistParser.REGEX_AUDIO = Pattern.compile("AUDIO=\"(.+?)\"");
        HlsPlaylistParser.REGEX_BANDWIDTH = Pattern.compile("[^-]BANDWIDTH=(\\d+)\\b");
        HlsPlaylistParser.REGEX_CODECS = Pattern.compile("CODECS=\"(.+?)\"");
        HlsPlaylistParser.REGEX_RESOLUTION = Pattern.compile("RESOLUTION=(\\d+x\\d+)");
        HlsPlaylistParser.REGEX_FRAME_RATE = Pattern.compile("FRAME-RATE=([\\d\\.]+)\\b");
        HlsPlaylistParser.REGEX_TARGET_DURATION = Pattern.compile("#EXT-X-TARGETDURATION:(\\d+)\\b");
        HlsPlaylistParser.REGEX_VERSION = Pattern.compile("#EXT-X-VERSION:(\\d+)\\b");
        HlsPlaylistParser.REGEX_PLAYLIST_TYPE = Pattern.compile("#EXT-X-PLAYLIST-TYPE:(.+)\\b");
        HlsPlaylistParser.REGEX_MEDIA_SEQUENCE = Pattern.compile("#EXT-X-MEDIA-SEQUENCE:(\\d+)\\b");
        HlsPlaylistParser.REGEX_MEDIA_DURATION = Pattern.compile("#EXTINF:([\\d\\.]+)\\b");
        HlsPlaylistParser.REGEX_MEDIA_TITLE = Pattern.compile("#EXTINF:[\\d\\.]+\\b,(.+)");
        HlsPlaylistParser.REGEX_TIME_OFFSET = Pattern.compile("TIME-OFFSET=(-?[\\d\\.]+)\\b");
        HlsPlaylistParser.REGEX_BYTERANGE = Pattern.compile("#EXT-X-BYTERANGE:(\\d+(?:@\\d+)?)\\b");
        HlsPlaylistParser.REGEX_ATTR_BYTERANGE = Pattern.compile("BYTERANGE=\"(\\d+(?:@\\d+)?)\\b\"");
        HlsPlaylistParser.REGEX_METHOD = Pattern.compile("METHOD=(NONE|AES-128|SAMPLE-AES|SAMPLE-AES-CENC|SAMPLE-AES-CTR)\\s*(,|$)");
        HlsPlaylistParser.REGEX_KEYFORMAT = Pattern.compile("KEYFORMAT=\"(.+?)\"");
        HlsPlaylistParser.REGEX_URI = Pattern.compile("URI=\"(.+?)\"");
        HlsPlaylistParser.REGEX_IV = Pattern.compile("IV=([^,.*]+)");
        HlsPlaylistParser.REGEX_TYPE = Pattern.compile("TYPE=(AUDIO|VIDEO|SUBTITLES|CLOSED-CAPTIONS)");
        HlsPlaylistParser.REGEX_LANGUAGE = Pattern.compile("LANGUAGE=\"(.+?)\"");
        HlsPlaylistParser.REGEX_NAME = Pattern.compile("NAME=\"(.+?)\"");
        HlsPlaylistParser.REGEX_GROUP_ID = Pattern.compile("GROUP-ID=\"(.+?)\"");
        HlsPlaylistParser.REGEX_INSTREAM_ID = Pattern.compile("INSTREAM-ID=\"((?:CC|SERVICE)\\d+)\"");
        HlsPlaylistParser.REGEX_AUTOSELECT = HlsPlaylistParser.compileBooleanAttrPattern("AUTOSELECT");
        HlsPlaylistParser.REGEX_DEFAULT = HlsPlaylistParser.compileBooleanAttrPattern("DEFAULT");
        HlsPlaylistParser.REGEX_FORCED = HlsPlaylistParser.compileBooleanAttrPattern("FORCED");
    }

    public HlsPlaylistParser() {
        super();
    }

    private static boolean checkPlaylistHeader(BufferedReader arg5) {
        int v0 = arg5.read();
        if(v0 == 239) {
            if(arg5.read() == 187) {
                if(arg5.read() != 191) {
                }
                else {
                    v0 = arg5.read();
                    goto label_14;
                }
            }

            return 0;
        }

    label_14:
        v0 = HlsPlaylistParser.skipIgnorableWhitespace(arg5, true, v0);
        int v2 = "#EXTM3U".length();
        int v3 = v0;
        for(v0 = 0; v0 < v2; ++v0) {
            if(v3 != "#EXTM3U".charAt(v0)) {
                return 0;
            }

            v3 = arg5.read();
        }

        return Util.isLinebreak(HlsPlaylistParser.skipIgnorableWhitespace(arg5, false, v3));
    }

    private static Pattern compileBooleanAttrPattern(String arg1) {
        StringBuilder v0 = new StringBuilder();
        v0.append(arg1);
        v0.append("=(");
        v0.append("NO");
        v0.append("|");
        v0.append("YES");
        v0.append(")");
        return Pattern.compile(v0.toString());
    }

    public HlsPlaylist parse(Uri arg4, InputStream arg5) {
        HlsMediaPlaylist v4_2;
        HlsMasterPlaylist v4_1;
        String v1;
        BufferedReader v0 = new BufferedReader(new InputStreamReader(arg5));
        ArrayDeque v5 = new ArrayDeque();
        try {
            if(!HlsPlaylistParser.checkPlaylistHeader(v0)) {
                goto label_63;
            }

            while(true) {
            label_8:
                v1 = v0.readLine();
                if(v1 == null) {
                    goto label_58;
                }

                v1 = v1.trim();
                if(v1.isEmpty()) {
                    continue;
                }

                if(!v1.startsWith("#EXT-X-STREAM-INF")) {
                    goto label_24;
                }

                ((Queue)v5).add(v1);
                v4_1 = HlsPlaylistParser.parseMasterPlaylist(new LineIterator(((Queue)v5), v0), arg4.toString());
                break;
            }
        }
        catch(Throwable v4) {
            goto label_68;
        }

        Util.closeQuietly(((Closeable)v0));
        return ((HlsPlaylist)v4_1);
        try {
        label_24:
            if(!v1.startsWith("#EXT-X-TARGETDURATION") && !v1.startsWith("#EXT-X-MEDIA-SEQUENCE") && !v1.startsWith("#EXTINF") && !v1.startsWith("#EXT-X-KEY") && !v1.startsWith("#EXT-X-BYTERANGE") && !v1.equals("#EXT-X-DISCONTINUITY") && !v1.equals("#EXT-X-DISCONTINUITY-SEQUENCE")) {
                if(v1.equals("#EXT-X-ENDLIST")) {
                }
                else {
                    ((Queue)v5).add(v1);
                    goto label_8;
                }
            }

            ((Queue)v5).add(v1);
            v4_2 = HlsPlaylistParser.parseMediaPlaylist(new LineIterator(((Queue)v5), v0), arg4.toString());
        }
        catch(Throwable v4) {
            goto label_68;
        }

        Util.closeQuietly(((Closeable)v0));
        return ((HlsPlaylist)v4_2);
    label_58:
        Util.closeQuietly(((Closeable)v0));
        throw new ParserException("Failed to parse the playlist, could not identify any tags.");
        try {
        label_63:
            throw new UnrecognizedInputFormatException("Input does not start with the #EXTM3U header.", arg4);
        }
        catch(Throwable v4) {
        label_68:
            Util.closeQuietly(((Closeable)v0));
            throw v4;
        }
    }

    public Object parse(Uri arg1, InputStream arg2) {
        return this.parse(arg1, arg2);
    }

    private static double parseDoubleAttr(String arg0, Pattern arg1) {
        return Double.parseDouble(HlsPlaylistParser.parseStringAttr(arg0, arg1));
    }

    private static int parseIntAttr(String arg0, Pattern arg1) {
        return Integer.parseInt(HlsPlaylistParser.parseStringAttr(arg0, arg1));
    }

    private static long parseLongAttr(String arg0, Pattern arg1) {
        return Long.parseLong(HlsPlaylistParser.parseStringAttr(arg0, arg1));
    }

    private static HlsMasterPlaylist parseMasterPlaylist(LineIterator arg29, String arg30) {
        ArrayList v9_2;
        String v19_1;
        String v2_2;
        int v2_1;
        boolean v28;
        int v21;
        int v20;
        int v12;
        HashSet v0 = new HashSet();
        HashMap v1 = new HashMap();
        ArrayList v5 = new ArrayList();
        ArrayList v6 = new ArrayList();
        ArrayList v7 = new ArrayList();
        ArrayList v2 = new ArrayList();
        ArrayList v4 = new ArrayList();
        int v9 = 0;
        boolean v10 = false;
        while(arg29.hasNext()) {
            String v11 = arg29.next();
            if(v11.startsWith("#EXT")) {
                v4.add(v11);
            }

            if(v11.equals("#EXT-X-INDEPENDENT-SEGMENTS")) {
                v10 = true;
                continue;
            }

            if(v11.startsWith("#EXT-X-MEDIA")) {
                v2.add(v11);
                continue;
            }

            if(!v11.startsWith("#EXT-X-STREAM-INF")) {
                continue;
            }

            v9 |= v11.contains("CLOSED-CAPTIONS=NONE");
            int v13 = HlsPlaylistParser.parseIntAttr(v11, HlsPlaylistParser.REGEX_BANDWIDTH);
            String v14 = HlsPlaylistParser.parseOptionalStringAttr(v11, HlsPlaylistParser.REGEX_AVERAGE_BANDWIDTH);
            if(v14 != null) {
                v13 = Integer.parseInt(v14);
            }

            int v19 = v13;
            String v13_1 = HlsPlaylistParser.parseOptionalStringAttr(v11, HlsPlaylistParser.REGEX_CODECS);
            v14 = HlsPlaylistParser.parseOptionalStringAttr(v11, HlsPlaylistParser.REGEX_RESOLUTION);
            if(v14 != null) {
                String[] v14_1 = v14.split("x");
                int v15 = Integer.parseInt(v14_1[0]);
                int v14_2 = Integer.parseInt(v14_1[1]);
                if(v15 <= 0 || v14_2 <= 0) {
                    v12 = -1;
                    v14_2 = -1;
                }
                else {
                    v12 = v15;
                }

                v20 = v12;
                v21 = v14_2;
            }
            else {
                v20 = -1;
                v21 = -1;
            }

            v14 = HlsPlaylistParser.parseOptionalStringAttr(v11, HlsPlaylistParser.REGEX_FRAME_RATE);
            float v22 = v14 != null ? Float.parseFloat(v14) : -1f;
            v11 = HlsPlaylistParser.parseOptionalStringAttr(v11, HlsPlaylistParser.REGEX_AUDIO);
            if(v11 != null && v13_1 != null) {
                v1.put(v11, Util.getCodecsOfType(v13_1, 1));
            }

            v11 = arg29.next();
            if(!v0.add(v11)) {
                continue;
            }

            v5.add(new HlsUrl(v11, Format.createVideoContainerFormat(Integer.toString(v5.size()), null, "application/x-mpegURL", null, v13_1, v19, v20, v21, v22, null, 0)));
        }

        int v11_1 = 0;
        Format v13_2 = null;
        ArrayList v14_3 = null;
        while(v11_1 < v2.size()) {
            Object v15_1 = v2.get(v11_1);
            int v25 = HlsPlaylistParser.parseSelectionFlags(((String)v15_1));
            String v0_1 = HlsPlaylistParser.parseOptionalStringAttr(((String)v15_1), HlsPlaylistParser.REGEX_URI);
            String v17 = HlsPlaylistParser.parseStringAttr(((String)v15_1), HlsPlaylistParser.REGEX_NAME);
            String v26 = HlsPlaylistParser.parseOptionalStringAttr(((String)v15_1), HlsPlaylistParser.REGEX_LANGUAGE);
            String v3 = HlsPlaylistParser.parseOptionalStringAttr(((String)v15_1), HlsPlaylistParser.REGEX_GROUP_ID);
            String v8 = HlsPlaylistParser.parseStringAttr(((String)v15_1), HlsPlaylistParser.REGEX_TYPE);
            v12 = v8.hashCode();
            ArrayList v27 = v2;
            v28 = v10;
            int v10_1 = 2;
            if(v12 != -959297733) {
                if(v12 != -333210994) {
                    if(v12 != 62628790) {
                        goto label_144;
                    }
                    else if(v8.equals("AUDIO")) {
                        v2_1 = 0;
                    }
                    else {
                        goto label_144;
                    }
                }
                else if(v8.equals("CLOSED-CAPTIONS")) {
                    v2_1 = 2;
                }
                else {
                    goto label_144;
                }
            }
            else if(v8.equals("SUBTITLES")) {
                v2_1 = 1;
            }
            else {
            label_144:
                v2_1 = -1;
            }

            switch(v2_1) {
                case 0: {
                    Object v20_1 = v1.get(v3);
                    v19_1 = v20_1 != null ? MimeTypes.getMediaMimeType(((String)v20_1)) : null;
                    Format v2_3 = Format.createAudioContainerFormat(v17, v17, "application/x-mpegURL", v19_1, ((String)v20_1), -1, -1, -1, null, v25, v26);
                    if(v0_1 == null) {
                        v13_2 = v2_3;
                        goto label_206;
                    }

                    v6.add(new HlsUrl(v0_1, v2_3));
                    break;
                }
                case 1: {
                    v7.add(new HlsUrl(v0_1, Format.createTextContainerFormat(v17, v17, "application/x-mpegURL", "text/vtt", null, -1, v25, v26)));
                    break;
                }
                case 2: {
                    v0_1 = HlsPlaylistParser.parseStringAttr(((String)v15_1), HlsPlaylistParser.REGEX_INSTREAM_ID);
                    if(v0_1.startsWith("CC")) {
                        v2_2 = "application/cea-608";
                        v0_1 = v0_1.substring(v10_1);
                    }
                    else {
                        v2_2 = "application/cea-708";
                        v0_1 = v0_1.substring(7);
                    }

                    int v24 = Integer.parseInt(v0_1);
                    v19_1 = v2_2;
                    if(v14_3 == null) {
                        v14_3 = new ArrayList();
                    }

                    ((List)v14_3).add(Format.createTextContainerFormat(v17, v17, null, v19_1, null, -1, v25, v26, v24));
                    break;
                }
                default: {
                    break;
                }
            }

        label_206:
            ++v11_1;
            v2 = v27;
            v10 = v28;
        }

        v28 = v10;
        if(v9 != 0) {
            List v9_1 = Collections.emptyList();
        }
        else {
            v9_2 = v14_3;
        }

        return new HlsMasterPlaylist(arg30, ((List)v4), ((List)v5), ((List)v6), ((List)v7), v13_2, ((List)v9_2), v28);
    }

    private static HlsMediaPlaylist parseMediaPlaylist(LineIterator arg52, String arg53) {
        String v38;
        long v4_2;
        String[] v4_1;
        String v4;
        String v6;
        ArrayList v15 = new ArrayList();
        ArrayList v3 = new ArrayList();
        String v31 = "";
        long v11 = -9223372036854775807L;
        long v21 = v11;
        int v0 = 0;
        int v1 = 0;
        String v2 = null;
        long v13 = 0;
        boolean v16 = false;
        int v17 = 0;
        long v18 = 0;
        int v20 = 1;
        boolean v23 = false;
        boolean v24 = false;
        DrmInitData v25 = null;
        long v26 = 0;
        long v28 = 0;
        boolean v43 = false;
        long v44 = -1;
        long v46 = 0;
        String v48 = null;
        Segment v49 = null;
        while(true) {
            long v50 = 0;
            do {
            label_30:
                if(!arg52.hasNext()) {
                    goto label_268;
                }

                v6 = arg52.next();
                if(v6.startsWith("#EXT")) {
                    ((List)v3).add(v6);
                }

                if(v6.startsWith("#EXT-X-PLAYLIST-TYPE")) {
                    v4 = HlsPlaylistParser.parseStringAttr(v6, HlsPlaylistParser.REGEX_PLAYLIST_TYPE);
                    if("VOD".equals(v4)) {
                        v1 = 1;
                        goto label_30;
                    }

                    if(!"EVENT".equals(v4)) {
                        goto label_30;
                    }

                    v1 = 2;
                    goto label_30;
                }

                double v32 = 1000000;
                if(v6.startsWith("#EXT-X-START")) {
                    v11 = ((long)(HlsPlaylistParser.parseDoubleAttr(v6, HlsPlaylistParser.REGEX_TIME_OFFSET) * v32));
                    goto label_30;
                }

                if(v6.startsWith("#EXT-X-MAP")) {
                    String v33 = HlsPlaylistParser.parseStringAttr(v6, HlsPlaylistParser.REGEX_URI);
                    v4 = HlsPlaylistParser.parseOptionalStringAttr(v6, HlsPlaylistParser.REGEX_ATTR_BYTERANGE);
                    if(v4 != null) {
                        v4_1 = v4.split("@");
                        v44 = Long.parseLong(v4_1[0]);
                        if(v4_1.length > 1) {
                            v26 = Long.parseLong(v4_1[1]);
                        }
                    }

                    v49 = new Segment(v33, v26, v44);
                    v26 = 0;
                    v44 = -1;
                    goto label_30;
                }

                if(v6.startsWith("#EXT-X-TARGETDURATION")) {
                    v21 = 1000000 * (((long)HlsPlaylistParser.parseIntAttr(v6, HlsPlaylistParser.REGEX_TARGET_DURATION)));
                    goto label_30;
                }

                if(v6.startsWith("#EXT-X-MEDIA-SEQUENCE")) {
                    v28 = HlsPlaylistParser.parseLongAttr(v6, HlsPlaylistParser.REGEX_MEDIA_SEQUENCE);
                    v18 = v28;
                    goto label_30;
                }

                if(v6.startsWith("#EXT-X-VERSION")) {
                    v20 = HlsPlaylistParser.parseIntAttr(v6, HlsPlaylistParser.REGEX_VERSION);
                    goto label_30;
                }

                if(v6.startsWith("#EXTINF")) {
                    v4_2 = ((long)(HlsPlaylistParser.parseDoubleAttr(v6, HlsPlaylistParser.REGEX_MEDIA_DURATION) * v32));
                    v31 = HlsPlaylistParser.parseOptionalStringAttr(v6, HlsPlaylistParser.REGEX_MEDIA_TITLE, "");
                    v50 = v4_2;
                    goto label_30;
                }

                if(v6.startsWith("#EXT-X-KEY")) {
                    v2 = HlsPlaylistParser.parseOptionalStringAttr(v6, HlsPlaylistParser.REGEX_METHOD);
                    v4 = HlsPlaylistParser.parseOptionalStringAttr(v6, HlsPlaylistParser.REGEX_KEYFORMAT);
                    if(!"NONE".equals(v2)) {
                        String v5 = HlsPlaylistParser.parseOptionalStringAttr(v6, HlsPlaylistParser.REGEX_IV);
                        if(("identity".equals(v4)) || v4 == null) {
                            if("AES-128".equals(v2)) {
                                v2 = HlsPlaylistParser.parseStringAttr(v6, HlsPlaylistParser.REGEX_URI);
                                v48 = v5;
                                goto label_30;
                            }

                        label_165:
                            v48 = v5;
                        }
                        else if(v2 != null) {
                            SchemeData v4_3 = HlsPlaylistParser.parseWidevineSchemeData(v6, v4);
                            if(v4_3 != null) {
                                v2 = ("SAMPLE-AES-CENC".equals(v2)) || ("SAMPLE-AES-CTR".equals(v2)) ? "cenc" : "cbcs";
                                v48 = v5;
                                v25 = new DrmInitData(v2, new SchemeData[]{v4_3});
                            }
                            else {
                                goto label_165;
                            }
                        }
                        else {
                            goto label_165;
                        }

                        v2 = null;
                        goto label_30;
                    }

                    v2 = null;
                    v48 = null;
                    goto label_30;
                }

                if(v6.startsWith("#EXT-X-BYTERANGE")) {
                    v4_1 = HlsPlaylistParser.parseStringAttr(v6, HlsPlaylistParser.REGEX_BYTERANGE).split("@");
                    v44 = Long.parseLong(v4_1[0]);
                    if(v4_1.length <= 1) {
                        goto label_30;
                    }

                    v26 = Long.parseLong(v4_1[1]);
                    goto label_30;
                }

                int v5_1 = 58;
                if(v6.startsWith("#EXT-X-DISCONTINUITY-SEQUENCE")) {
                    v17 = Integer.parseInt(v6.substring(v6.indexOf(v5_1) + 1));
                    v16 = true;
                    goto label_30;
                }

                if(v6.equals("#EXT-X-DISCONTINUITY")) {
                    ++v0;
                    goto label_30;
                }

                if(v6.startsWith("#EXT-X-PROGRAM-DATE-TIME")) {
                    if(v13 != 0) {
                        goto label_30;
                    }

                    v13 = C.msToUs(Util.parseXsDateTime(v6.substring(v6.indexOf(v5_1) + 1))) - v46;
                    goto label_30;
                }

                if(v6.equals("#EXT-X-GAP")) {
                    v43 = true;
                    goto label_30;
                }

                if(v6.equals("#EXT-X-INDEPENDENT-SEGMENTS")) {
                    v23 = true;
                    goto label_30;
                }

                if(v6.equals("#EXT-X-ENDLIST")) {
                    v24 = true;
                    goto label_30;
                }
            }
            while(v6.startsWith("#"));

            if(v2 == null) {
                v38 = null;
            }
            else if(v48 != null) {
                v38 = v48;
            }
            else {
                v38 = Long.toHexString(v28);
            }

            v4_2 = v28 + 1;
            if(v44 == -1) {
                v26 = 0;
            }

            ((List)v15).add(new Segment(v6, v49, v31, v50, v0, v46, v2, v38, v26, v44, v43));
            v46 += v50;
            v31 = "";
            long v6_1 = -1;
            if(v44 != v6_1) {
                v26 += v44;
            }

            v28 = v4_2;
            v44 = v6_1;
            v43 = false;
        }

    label_268:
        HlsMediaPlaylist v26_1 = null;
        boolean v27 = v13 != 0 ? true : false;
        super(v1, arg53, ((List)v3), v11, v13, v16, v17, v18, v20, v21, v23, v24, v27, v25, v15);
        return v26_1;
    }

    private static boolean parseOptionalBooleanAttribute(String arg0, Pattern arg1, boolean arg2) {
        Matcher v0 = arg1.matcher(((CharSequence)arg0));
        if(v0.find()) {
            return v0.group(1).equals("YES");
        }

        return arg2;
    }

    private static String parseOptionalStringAttr(String arg1, Pattern arg2) {
        return HlsPlaylistParser.parseOptionalStringAttr(arg1, arg2, null);
    }

    private static String parseOptionalStringAttr(String arg0, Pattern arg1, String arg2) {
        Matcher v0 = arg1.matcher(((CharSequence)arg0));
        if(v0.find()) {
            arg2 = v0.group(1);
        }

        return arg2;
    }

    private static int parseSelectionFlags(String arg3) {
        int v0 = HlsPlaylistParser.parseOptionalBooleanAttribute(arg3, HlsPlaylistParser.REGEX_DEFAULT, false) ? 1 : 0;
        if(HlsPlaylistParser.parseOptionalBooleanAttribute(arg3, HlsPlaylistParser.REGEX_FORCED, false)) {
            v0 |= 2;
        }

        if(HlsPlaylistParser.parseOptionalBooleanAttribute(arg3, HlsPlaylistParser.REGEX_AUTOSELECT, false)) {
            v0 |= 4;
        }

        return v0;
    }

    private static String parseStringAttr(String arg3, Pattern arg4) {
        Matcher v0 = arg4.matcher(((CharSequence)arg3));
        if((v0.find()) && v0.groupCount() == 1) {
            return v0.group(1);
        }

        StringBuilder v1 = new StringBuilder();
        v1.append("Couldn\'t match ");
        v1.append(arg4.pattern());
        v1.append(" in ");
        v1.append(arg3);
        throw new ParserException(v1.toString());
    }

    private static SchemeData parseWidevineSchemeData(String arg3, String arg4) {
        if("urn:uuid:edef8ba9-79d6-4ace-a3c8-27dcd51d21ed".equals(arg4)) {
            arg3 = HlsPlaylistParser.parseStringAttr(arg3, HlsPlaylistParser.REGEX_URI);
            return new SchemeData(C.WIDEVINE_UUID, "video/mp4", Base64.decode(arg3.substring(arg3.indexOf(44)), 0));
        }

        if("com.widevine".equals(arg4)) {
            try {
                return new SchemeData(C.WIDEVINE_UUID, "hls", arg3.getBytes("UTF-8"));
            }
            catch(UnsupportedEncodingException v3) {
                throw new ParserException(((Throwable)v3));
            }
        }

        return null;
    }

    private static int skipIgnorableWhitespace(BufferedReader arg1, boolean arg2, int arg3) {
        while(arg3 != -1) {
            if(!Character.isWhitespace(arg3)) {
                return arg3;
            }

            if(!arg2 && (Util.isLinebreak(arg3))) {
                return arg3;
            }

            arg3 = arg1.read();
        }

        return arg3;
    }
}

