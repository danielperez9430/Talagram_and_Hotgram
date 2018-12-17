package com.google.android.exoplayer2.source.smoothstreaming.manifest;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Pair;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.drm.DrmInitData$SchemeData;
import com.google.android.exoplayer2.drm.DrmInitData;
import com.google.android.exoplayer2.extractor.mp4.PsshAtomUtil;
import com.google.android.exoplayer2.upstream.ParsingLoadable$Parser;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.CodecSpecificDataUtil;
import com.google.android.exoplayer2.util.Util;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

public class SsManifestParser implements Parser {
    abstract class ElementParser {
        private final String baseUri;
        private final List normalizedAttributes;
        private final ElementParser parent;
        private final String tag;

        public ElementParser(ElementParser arg1, String arg2, String arg3) {
            super();
            this.parent = arg1;
            this.baseUri = arg2;
            this.tag = arg3;
            this.normalizedAttributes = new LinkedList();
        }

        protected void addChild(Object arg1) {
        }

        protected abstract Object build();

        protected final Object getNormalizedAttribute(String arg4) {
            int v0;
            for(v0 = 0; v0 < this.normalizedAttributes.size(); ++v0) {
                Object v1 = this.normalizedAttributes.get(v0);
                if(((Pair)v1).first.equals(arg4)) {
                    return ((Pair)v1).second;
                }
            }

            Object v4 = this.parent == null ? null : this.parent.getNormalizedAttribute(arg4);
            return v4;
        }

        protected boolean handleChildInline(String arg1) {
            return 0;
        }

        private ElementParser newChildParser(ElementParser arg2, String arg3, String arg4) {
            if("QualityLevel".equals(arg3)) {
                return new QualityLevelParser(arg2, arg4);
            }

            if("Protection".equals(arg3)) {
                return new ProtectionParser(arg2, arg4);
            }

            if("StreamIndex".equals(arg3)) {
                return new StreamIndexParser(arg2, arg4);
            }

            return null;
        }

        public final Object parse(XmlPullParser arg6) {
            String v2;
            int v0 = 0;
            int v1 = 0;
        label_2:
            switch(arg6.getEventType()) {
                case 1: {
                    break;
                }
                case 2: {
                    v2 = arg6.getName();
                    if(this.tag.equals(v2)) {
                        this.parseStartTag(arg6);
                        v0 = 1;
                        goto label_45;
                    }

                    if(v0 == 0) {
                        goto label_45;
                    }

                    if(v1 > 0) {
                        ++v1;
                        goto label_45;
                    }

                    if(this.handleChildInline(v2)) {
                        this.parseStartTag(arg6);
                        goto label_45;
                    }

                    ElementParser v2_1 = this.newChildParser(this, v2, this.baseUri);
                    if(v2_1 == null) {
                        v1 = 1;
                        goto label_45;
                    }

                    this.addChild(v2_1.parse(arg6));
                    goto label_45;
                }
                case 3: {
                    if(v0 != 0) {
                        if(v1 > 0) {
                            --v1;
                            goto label_45;
                        }

                        v2 = arg6.getName();
                        this.parseEndTag(arg6);
                        if(!this.handleChildInline(v2)) {
                            return this.build();
                        }
                    }

                label_45:
                    arg6.next();
                    goto label_2;
                }
                case 4: {
                    if(v0 == 0) {
                        goto label_45;
                    }

                    if(v1 != 0) {
                        goto label_45;
                    }

                    this.parseText(arg6);
                    goto label_45;
                }
                default: {
                    goto label_45;
                }
            }

            return null;
        }

        protected final boolean parseBoolean(XmlPullParser arg2, String arg3, boolean arg4) {
            String v2 = arg2.getAttributeValue(null, arg3);
            if(v2 != null) {
                return Boolean.parseBoolean(v2);
            }

            return arg4;
        }

        protected void parseEndTag(XmlPullParser arg1) {
        }

        protected final int parseInt(XmlPullParser arg2, String arg3, int arg4) {
            String v2 = arg2.getAttributeValue(null, arg3);
            if(v2 != null) {
                try {
                    return Integer.parseInt(v2);
                }
                catch(NumberFormatException v2_1) {
                    throw new ParserException(((Throwable)v2_1));
                }
            }

            return arg4;
        }

        protected final long parseLong(XmlPullParser arg2, String arg3, long arg4) {
            String v2 = arg2.getAttributeValue(null, arg3);
            if(v2 != null) {
                try {
                    return Long.parseLong(v2);
                }
                catch(NumberFormatException v2_1) {
                    throw new ParserException(((Throwable)v2_1));
                }
            }

            return arg4;
        }

        protected final int parseRequiredInt(XmlPullParser arg2, String arg3) {
            String v2 = arg2.getAttributeValue(null, arg3);
            if(v2 != null) {
                try {
                    return Integer.parseInt(v2);
                }
                catch(NumberFormatException v2_1) {
                    throw new ParserException(((Throwable)v2_1));
                }
            }

            throw new MissingFieldException(arg3);
        }

        protected final long parseRequiredLong(XmlPullParser arg2, String arg3) {
            String v2 = arg2.getAttributeValue(null, arg3);
            if(v2 != null) {
                try {
                    return Long.parseLong(v2);
                }
                catch(NumberFormatException v2_1) {
                    throw new ParserException(((Throwable)v2_1));
                }
            }

            throw new MissingFieldException(arg3);
        }

        protected final String parseRequiredString(XmlPullParser arg2, String arg3) {
            String v2 = arg2.getAttributeValue(null, arg3);
            if(v2 != null) {
                return v2;
            }

            throw new MissingFieldException(arg3);
        }

        protected void parseStartTag(XmlPullParser arg1) {
        }

        protected void parseText(XmlPullParser arg1) {
        }

        protected final void putNormalizedAttribute(String arg2, Object arg3) {
            this.normalizedAttributes.add(Pair.create(arg2, arg3));
        }
    }

    public class MissingFieldException extends ParserException {
        public MissingFieldException(String arg3) {
            super("Missing required field: " + arg3);
        }
    }

    class ProtectionParser extends ElementParser {
        public static final String KEY_SYSTEM_ID = "SystemID";
        public static final String TAG = "Protection";
        public static final String TAG_PROTECTION_HEADER = "ProtectionHeader";
        private boolean inProtectionHeader;
        private byte[] initData;
        private UUID uuid;

        public ProtectionParser(ElementParser arg2, String arg3) {
            super(arg2, arg3, "Protection");
        }

        public Object build() {
            return new ProtectionElement(this.uuid, PsshAtomUtil.buildPsshAtom(this.uuid, this.initData));
        }

        public boolean handleChildInline(String arg2) {
            return "ProtectionHeader".equals(arg2);
        }

        public void parseEndTag(XmlPullParser arg2) {
            if("ProtectionHeader".equals(arg2.getName())) {
                this.inProtectionHeader = false;
            }
        }

        public void parseStartTag(XmlPullParser arg3) {
            if("ProtectionHeader".equals(arg3.getName())) {
                this.inProtectionHeader = true;
                this.uuid = UUID.fromString(ProtectionParser.stripCurlyBraces(arg3.getAttributeValue(null, "SystemID")));
            }
        }

        public void parseText(XmlPullParser arg2) {
            if(this.inProtectionHeader) {
                this.initData = Base64.decode(arg2.getText(), 0);
            }
        }

        private static String stripCurlyBraces(String arg3) {
            if(arg3.charAt(0) == 123 && arg3.charAt(arg3.length() - 1) == 125) {
                arg3 = arg3.substring(1, arg3.length() - 1);
            }

            return arg3;
        }
    }

    class QualityLevelParser extends ElementParser {
        private static final String KEY_BITRATE = "Bitrate";
        private static final String KEY_CHANNELS = "Channels";
        private static final String KEY_CODEC_PRIVATE_DATA = "CodecPrivateData";
        private static final String KEY_FOUR_CC = "FourCC";
        private static final String KEY_INDEX = "Index";
        private static final String KEY_LANGUAGE = "Language";
        private static final String KEY_MAX_HEIGHT = "MaxHeight";
        private static final String KEY_MAX_WIDTH = "MaxWidth";
        private static final String KEY_NAME = "Name";
        private static final String KEY_SAMPLING_RATE = "SamplingRate";
        private static final String KEY_TYPE = "Type";
        public static final String TAG = "QualityLevel";
        private Format format;

        public QualityLevelParser(ElementParser arg2, String arg3) {
            super(arg2, arg3, "QualityLevel");
        }

        public Object build() {
            return this.format;
        }

        private static List buildCodecSpecificData(String arg2) {
            ArrayList v0 = new ArrayList();
            if(!TextUtils.isEmpty(((CharSequence)arg2))) {
                byte[] v2 = Util.getBytesFromHexString(arg2);
                byte[][] v1 = CodecSpecificDataUtil.splitNalUnits(v2);
                if(v1 == null) {
                    v0.add(v2);
                }
                else {
                    Collections.addAll(((Collection)v0), ((Object[])v1));
                }
            }

            return ((List)v0);
        }

        private static String fourCCToMimeType(String arg1) {
            if(!arg1.equalsIgnoreCase("H264") && !arg1.equalsIgnoreCase("X264") && !arg1.equalsIgnoreCase("AVC1")) {
                if(arg1.equalsIgnoreCase("DAVC")) {
                }
                else {
                    if(!arg1.equalsIgnoreCase("AAC") && !arg1.equalsIgnoreCase("AACL") && !arg1.equalsIgnoreCase("AACH")) {
                        if(arg1.equalsIgnoreCase("AACP")) {
                        }
                        else if(arg1.equalsIgnoreCase("TTML")) {
                            return "application/ttml+xml";
                        }
                        else {
                            if(!arg1.equalsIgnoreCase("ac-3")) {
                                if(arg1.equalsIgnoreCase("dac3")) {
                                }
                                else {
                                    if(!arg1.equalsIgnoreCase("ec-3")) {
                                        if(arg1.equalsIgnoreCase("dec3")) {
                                        }
                                        else if(arg1.equalsIgnoreCase("dtsc")) {
                                            return "audio/vnd.dts";
                                        }
                                        else {
                                            if(!arg1.equalsIgnoreCase("dtsh")) {
                                                if(arg1.equalsIgnoreCase("dtsl")) {
                                                }
                                                else if(arg1.equalsIgnoreCase("dtse")) {
                                                    return "audio/vnd.dts.hd;profile=lbr";
                                                }
                                                else if(arg1.equalsIgnoreCase("opus")) {
                                                    return "audio/opus";
                                                }
                                                else {
                                                    return null;
                                                }
                                            }

                                            return "audio/vnd.dts.hd";
                                        }
                                    }

                                    return "audio/eac3";
                                }
                            }

                            return "audio/ac3";
                        }
                    }

                    return "audio/mp4a-latm";
                }
            }

            return "video/avc";
        }

        public void parseStartTag(XmlPullParser arg15) {
            Format v15;
            int v0 = this.getNormalizedAttribute("Type").intValue();
            String v2 = null;
            String v3 = arg15.getAttributeValue(v2, "Index");
            Object v4 = this.getNormalizedAttribute("Name");
            int v8 = this.parseRequiredInt(arg15, "Bitrate");
            String v6 = QualityLevelParser.fourCCToMimeType(this.parseRequiredString(arg15, "FourCC"));
            if(v0 == 2) {
                v15 = Format.createVideoContainerFormat(v3, ((String)v4), "video/mp4", v6, null, v8, this.parseRequiredInt(arg15, "MaxWidth"), this.parseRequiredInt(arg15, "MaxHeight"), -1f, QualityLevelParser.buildCodecSpecificData(arg15.getAttributeValue(v2, "CodecPrivateData")), 0);
            }
            else if(v0 == 1) {
                if(v6 == null) {
                    v6 = "audio/mp4a-latm";
                }

                int v9 = this.parseRequiredInt(arg15, "Channels");
                int v10 = this.parseRequiredInt(arg15, "SamplingRate");
                List v15_1 = QualityLevelParser.buildCodecSpecificData(arg15.getAttributeValue(v2, "CodecPrivateData"));
                if((v15_1.isEmpty()) && ("audio/mp4a-latm".equals(v6))) {
                    v15_1 = Collections.singletonList(CodecSpecificDataUtil.buildAacLcAudioSpecificConfig(v10, v9));
                }

                v15 = Format.createAudioContainerFormat(v3, ((String)v4), "audio/mp4", v6, null, v8, v9, v10, v15_1, 0, this.getNormalizedAttribute("Language"));
            }
            else {
                if(v0 == 3) {
                    v15 = Format.createTextContainerFormat(v3, ((String)v4), "application/mp4", v6, null, v8, 0, this.getNormalizedAttribute("Language"));
                    goto label_28;
                }

                v15 = Format.createContainerFormat(v3, ((String)v4), "application/mp4", v6, null, v8, 0, null);
            }

        label_28:
            this.format = v15;
        }
    }

    class SmoothStreamingMediaParser extends ElementParser {
        private static final String KEY_DURATION = "Duration";
        private static final String KEY_DVR_WINDOW_LENGTH = "DVRWindowLength";
        private static final String KEY_IS_LIVE = "IsLive";
        private static final String KEY_LOOKAHEAD_COUNT = "LookaheadCount";
        private static final String KEY_MAJOR_VERSION = "MajorVersion";
        private static final String KEY_MINOR_VERSION = "MinorVersion";
        private static final String KEY_TIME_SCALE = "TimeScale";
        public static final String TAG = "SmoothStreamingMedia";
        private long duration;
        private long dvrWindowLength;
        private boolean isLive;
        private int lookAheadCount;
        private int majorVersion;
        private int minorVersion;
        private ProtectionElement protectionElement;
        private final List streamElements;
        private long timescale;

        public SmoothStreamingMediaParser(ElementParser arg2, String arg3) {
            super(arg2, arg3, "SmoothStreamingMedia");
            this.lookAheadCount = -1;
            this.protectionElement = null;
            this.streamElements = new LinkedList();
        }

        public void addChild(Object arg2) {
            if((arg2 instanceof StreamElement)) {
                this.streamElements.add(arg2);
            }
            else if((arg2 instanceof ProtectionElement)) {
                boolean v0 = this.protectionElement == null ? true : false;
                Assertions.checkState(v0);
                this.protectionElement = ((ProtectionElement)arg2);
            }
        }

        public Object build() {
            StreamElement[] v13 = new StreamElement[this.streamElements.size()];
            this.streamElements.toArray(((Object[])v13));
            if(this.protectionElement != null) {
                DrmInitData v0 = new DrmInitData(new SchemeData[]{new SchemeData(this.protectionElement.uuid, "video/mp4", this.protectionElement.data)});
                int v1 = v13.length;
                int v2;
                for(v2 = 0; v2 < v1; ++v2) {
                    StreamElement v4 = v13[v2];
                    int v5;
                    for(v5 = 0; v5 < v4.formats.length; ++v5) {
                        v4.formats[v5] = v4.formats[v5].copyWithDrmInitData(v0);
                    }
                }
            }

            return new SsManifest(this.majorVersion, this.minorVersion, this.timescale, this.duration, this.dvrWindowLength, this.lookAheadCount, this.isLive, this.protectionElement, v13);
        }

        public void parseStartTag(XmlPullParser arg4) {
            this.majorVersion = this.parseRequiredInt(arg4, "MajorVersion");
            this.minorVersion = this.parseRequiredInt(arg4, "MinorVersion");
            this.timescale = this.parseLong(arg4, "TimeScale", 10000000);
            this.duration = this.parseRequiredLong(arg4, "Duration");
            this.dvrWindowLength = this.parseLong(arg4, "DVRWindowLength", 0);
            this.lookAheadCount = this.parseInt(arg4, "LookaheadCount", -1);
            this.isLive = this.parseBoolean(arg4, "IsLive", false);
            this.putNormalizedAttribute("TimeScale", Long.valueOf(this.timescale));
        }
    }

    class StreamIndexParser extends ElementParser {
        private static final String KEY_DISPLAY_HEIGHT = "DisplayHeight";
        private static final String KEY_DISPLAY_WIDTH = "DisplayWidth";
        private static final String KEY_FRAGMENT_DURATION = "d";
        private static final String KEY_FRAGMENT_REPEAT_COUNT = "r";
        private static final String KEY_FRAGMENT_START_TIME = "t";
        private static final String KEY_LANGUAGE = "Language";
        private static final String KEY_MAX_HEIGHT = "MaxHeight";
        private static final String KEY_MAX_WIDTH = "MaxWidth";
        private static final String KEY_NAME = "Name";
        private static final String KEY_SUB_TYPE = "Subtype";
        private static final String KEY_TIME_SCALE = "TimeScale";
        private static final String KEY_TYPE = "Type";
        private static final String KEY_TYPE_AUDIO = "audio";
        private static final String KEY_TYPE_TEXT = "text";
        private static final String KEY_TYPE_VIDEO = "video";
        private static final String KEY_URL = "Url";
        public static final String TAG = "StreamIndex";
        private static final String TAG_STREAM_FRAGMENT = "c";
        private final String baseUri;
        private int displayHeight;
        private int displayWidth;
        private final List formats;
        private String language;
        private long lastChunkDuration;
        private int maxHeight;
        private int maxWidth;
        private String name;
        private ArrayList startTimes;
        private String subType;
        private long timescale;
        private int type;
        private String url;

        public StreamIndexParser(ElementParser arg2, String arg3) {
            super(arg2, arg3, "StreamIndex");
            this.baseUri = arg3;
            this.formats = new LinkedList();
        }

        public void addChild(Object arg2) {
            if((arg2 instanceof Format)) {
                this.formats.add(arg2);
            }
        }

        public Object build() {
            Format[] v1 = new Format[this.formats.size()];
            this.formats.toArray(((Object[])v1));
            return new StreamElement(this.baseUri, this.url, this.type, this.subType, this.timescale, this.name, this.maxWidth, this.maxHeight, this.displayWidth, this.displayHeight, this.language, v1, this.startTimes, this.lastChunkDuration);
        }

        public boolean handleChildInline(String arg2) {
            return "c".equals(arg2);
        }

        public void parseStartTag(XmlPullParser arg3) {
            if("c".equals(arg3.getName())) {
                this.parseStreamFragmentStartTag(arg3);
            }
            else {
                this.parseStreamElementStartTag(arg3);
            }
        }

        private void parseStreamElementStartTag(XmlPullParser arg5) {
            this.type = this.parseType(arg5);
            this.putNormalizedAttribute("Type", Integer.valueOf(this.type));
            String v1 = null;
            String v0 = this.type == 3 ? this.parseRequiredString(arg5, "Subtype") : arg5.getAttributeValue(v1, "Subtype");
            this.subType = v0;
            this.name = arg5.getAttributeValue(v1, "Name");
            this.url = this.parseRequiredString(arg5, "Url");
            this.maxWidth = this.parseInt(arg5, "MaxWidth", -1);
            this.maxHeight = this.parseInt(arg5, "MaxHeight", -1);
            this.displayWidth = this.parseInt(arg5, "DisplayWidth", -1);
            this.displayHeight = this.parseInt(arg5, "DisplayHeight", -1);
            this.language = arg5.getAttributeValue(v1, "Language");
            this.putNormalizedAttribute("Language", this.language);
            this.timescale = ((long)this.parseInt(arg5, "TimeScale", -1));
            if(this.timescale == -1) {
                this.timescale = this.getNormalizedAttribute("TimeScale").longValue();
            }

            this.startTimes = new ArrayList();
        }

        private void parseStreamFragmentStartTag(XmlPullParser arg10) {
            int v0 = this.startTimes.size();
            long v2 = -9223372036854775807L;
            long v4 = this.parseLong(arg10, "t", v2);
            int v6 = 1;
            if(Long.compare(v4, v2) == 0) {
                if(v0 == 0) {
                    v4 = 0;
                }
                else if(this.lastChunkDuration != -1) {
                    v4 = this.lastChunkDuration + this.startTimes.get(v0 - 1).longValue();
                }
                else {
                    throw new ParserException("Unable to infer start time");
                }
            }

            this.startTimes.add(Long.valueOf(v4));
            this.lastChunkDuration = this.parseLong(arg10, "d", v2);
            long v0_1 = this.parseLong(arg10, "r", 1);
            if(v0_1 > 1) {
                if(this.lastChunkDuration != v2) {
                }
                else {
                    throw new ParserException("Repeated chunk with unspecified duration");
                }
            }

            while(true) {
                v2 = ((long)v6);
                if(v2 >= v0_1) {
                    return;
                }

                this.startTimes.add(Long.valueOf(this.lastChunkDuration * v2 + v4));
                ++v6;
            }
        }

        private int parseType(XmlPullParser arg4) {
            String v4 = arg4.getAttributeValue(null, "Type");
            if(v4 != null) {
                if("audio".equalsIgnoreCase(v4)) {
                    return 1;
                }

                if("video".equalsIgnoreCase(v4)) {
                    return 2;
                }

                if("text".equalsIgnoreCase(v4)) {
                    return 3;
                }

                StringBuilder v1 = new StringBuilder();
                v1.append("Invalid key value[");
                v1.append(v4);
                v1.append("]");
                throw new ParserException(v1.toString());
            }

            throw new MissingFieldException("Type");
        }
    }

    private final XmlPullParserFactory xmlParserFactory;

    public SsManifestParser() {
        super();
        try {
            this.xmlParserFactory = XmlPullParserFactory.newInstance();
            return;
        }
        catch(XmlPullParserException v0) {
            throw new RuntimeException("Couldn\'t create XmlPullParserFactory instance", ((Throwable)v0));
        }
    }

    public SsManifest parse(Uri arg3, InputStream arg4) {
        try {
            XmlPullParser v0 = this.xmlParserFactory.newPullParser();
            v0.setInput(arg4, null);
            return new SmoothStreamingMediaParser(null, arg3.toString()).parse(v0);
        }
        catch(XmlPullParserException v3) {
            throw new ParserException(((Throwable)v3));
        }
    }

    public Object parse(Uri arg1, InputStream arg2) {
        return this.parse(arg1, arg2);
    }
}

