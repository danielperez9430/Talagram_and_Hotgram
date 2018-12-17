package com.google.android.exoplayer2.source.dash.manifest;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.util.Pair;
import android.util.Xml;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.drm.DrmInitData$SchemeData;
import com.google.android.exoplayer2.drm.DrmInitData;
import com.google.android.exoplayer2.extractor.mp4.PsshAtomUtil;
import com.google.android.exoplayer2.metadata.emsg.EventMessage;
import com.google.android.exoplayer2.upstream.ParsingLoadable$Parser;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.UriUtil;
import com.google.android.exoplayer2.util.Util;
import com.google.android.exoplayer2.util.XmlPullParserUtil;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.xml.sax.helpers.DefaultHandler;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import org.xmlpull.v1.XmlSerializer;

public class DashManifestParser extends DefaultHandler implements Parser {
    public final class RepresentationInfo {
        public final String baseUrl;
        public final ArrayList drmSchemeDatas;
        public final String drmSchemeType;
        public final Format format;
        public final ArrayList inbandEventStreams;
        public final long revisionId;
        public final SegmentBase segmentBase;

        public RepresentationInfo(Format arg1, String arg2, SegmentBase arg3, String arg4, ArrayList arg5, ArrayList arg6, long arg7) {
            super();
            this.format = arg1;
            this.baseUrl = arg2;
            this.segmentBase = arg3;
            this.drmSchemeType = arg4;
            this.drmSchemeDatas = arg5;
            this.inbandEventStreams = arg6;
            this.revisionId = arg7;
        }
    }

    private static final Pattern CEA_608_ACCESSIBILITY_PATTERN = null;
    private static final Pattern CEA_708_ACCESSIBILITY_PATTERN = null;
    private static final Pattern FRAME_RATE_PATTERN = null;
    private static final String TAG = "MpdParser";
    private final String contentId;
    private final XmlPullParserFactory xmlParserFactory;

    static {
        DashManifestParser.FRAME_RATE_PATTERN = Pattern.compile("(\\d+)(?:/(\\d+))?");
        DashManifestParser.CEA_608_ACCESSIBILITY_PATTERN = Pattern.compile("CC([1-4])=.*");
        DashManifestParser.CEA_708_ACCESSIBILITY_PATTERN = Pattern.compile("([1-9]|[1-5][0-9]|6[0-3])=.*");
    }

    public DashManifestParser() {
        this(null);
    }

    public DashManifestParser(String arg3) {
        super();
        this.contentId = arg3;
        try {
            this.xmlParserFactory = XmlPullParserFactory.newInstance();
            return;
        }
        catch(XmlPullParserException v3) {
            throw new RuntimeException("Couldn\'t create XmlPullParserFactory instance", ((Throwable)v3));
        }
    }

    protected AdaptationSet buildAdaptationSet(int arg8, int arg9, List arg10, List arg11, List arg12) {
        return new AdaptationSet(arg8, arg9, arg10, arg11, arg12);
    }

    protected EventMessage buildEvent(String arg12, String arg13, long arg14, long arg16, byte[] arg18, long arg19) {
        return new EventMessage(arg12, arg13, arg16, arg14, arg18, arg19);
    }

    protected EventStream buildEventStream(String arg9, String arg10, long arg11, long[] arg13, EventMessage[] arg14) {
        return new EventStream(arg9, arg10, arg11, arg13, arg14);
    }

    protected Format buildFormat(String arg12, String arg13, String arg14, int arg15, int arg16, float arg17, int arg18, int arg19, int arg20, String arg21, int arg22, List arg23, String arg24, List arg25) {
        int v8;
        int v0_1;
        String v3;
        String v0 = DashManifestParser.getSampleMimeType(arg14, arg24);
        if(v0 != null) {
            if("audio/eac3".equals(v0)) {
                v0 = DashManifestParser.parseEac3SupplementalProperties(arg25);
            }

            v3 = v0;
            if(MimeTypes.isVideo(v3)) {
                return Format.createVideoContainerFormat(arg12, arg13, arg14, v3, arg24, arg20, arg15, arg16, arg17, null, arg22);
            }

            if(MimeTypes.isAudio(v3)) {
                return Format.createAudioContainerFormat(arg12, arg13, arg14, v3, arg24, arg20, arg18, arg19, null, arg22, arg21);
            }

            if(!DashManifestParser.mimeTypeIsRawText(v3)) {
                goto label_62;
            }

            if("application/cea-608".equals(v3)) {
                v0_1 = DashManifestParser.parseCea608AccessibilityChannel(arg23);
                goto label_43;
            }
            else if("application/cea-708".equals(v3)) {
                v0_1 = DashManifestParser.parseCea708AccessibilityChannel(arg23);
            label_43:
                v8 = v0_1;
            }
            else {
                v8 = -1;
            }

            return Format.createTextContainerFormat(arg12, arg13, arg14, v3, arg24, arg20, arg22, arg21, v8);
        }
        else {
            v3 = v0;
        }

    label_62:
        return Format.createContainerFormat(arg12, arg13, arg14, v3, arg24, arg20, arg22, arg21);
    }

    protected DashManifest buildMediaPresentationDescription(long arg21, long arg23, long arg25, boolean arg27, long arg28, long arg30, long arg32, long arg34, UtcTimingElement arg36, Uri arg37, List arg38) {
        return new DashManifest(arg21, arg23, arg25, arg27, arg28, arg30, arg32, arg34, arg36, arg37, arg38);
    }

    protected Period buildPeriod(String arg8, long arg9, List arg11, List arg12) {
        return new Period(arg8, arg9, arg11, arg12);
    }

    protected RangedUri buildRangedUri(String arg8, long arg9, long arg11) {
        return new RangedUri(arg8, arg9, arg11);
    }

    protected Representation buildRepresentation(RepresentationInfo arg9, String arg10, String arg11, ArrayList arg12, ArrayList arg13) {
        Format v0 = arg9.format;
        if(arg9.drmSchemeType != null) {
            arg11 = arg9.drmSchemeType;
        }

        ArrayList v1 = arg9.drmSchemeDatas;
        v1.addAll(((Collection)arg12));
        if(!v1.isEmpty()) {
            DashManifestParser.filterRedundantIncompleteSchemeDatas(v1);
            v0 = v0.copyWithDrmInitData(new DrmInitData(arg11, ((List)v1)));
        }

        ArrayList v7 = arg9.inbandEventStreams;
        v7.addAll(((Collection)arg13));
        return Representation.newInstance(arg10, arg9.revisionId, v0, arg9.baseUrl, arg9.segmentBase, ((List)v7));
    }

    protected SegmentList buildSegmentList(RangedUri arg14, long arg15, long arg17, long arg19, long arg21, List arg23, List arg24) {
        return new SegmentList(arg14, arg15, arg17, arg19, arg21, arg23, arg24);
    }

    protected SegmentTemplate buildSegmentTemplate(RangedUri arg15, long arg16, long arg18, long arg20, long arg22, List arg24, UrlTemplate arg25, UrlTemplate arg26) {
        return new SegmentTemplate(arg15, arg16, arg18, arg20, arg22, arg24, arg25, arg26);
    }

    protected SegmentTimelineElement buildSegmentTimelineElement(long arg2, long arg4) {
        return new SegmentTimelineElement(arg2, arg4);
    }

    protected SingleSegmentBase buildSingleSegmentBase(RangedUri arg12, long arg13, long arg15, long arg17, long arg19) {
        return new SingleSegmentBase(arg12, arg13, arg15, arg17, arg19);
    }

    protected UtcTimingElement buildUtcTimingElement(String arg2, String arg3) {
        return new UtcTimingElement(arg2, arg3);
    }

    private static int checkContentTypeConsistency(int arg1, int arg2) {
        int v0 = -1;
        if(arg1 == v0) {
            return arg2;
        }

        if(arg2 == v0) {
            return arg1;
        }

        boolean v2 = arg1 == arg2 ? true : false;
        Assertions.checkState(v2);
        return arg1;
    }

    private static String checkLanguageConsistency(String arg0, String arg1) {
        if(arg0 == null) {
            return arg1;
        }

        if(arg1 == null) {
            return arg0;
        }

        Assertions.checkState(arg0.equals(arg1));
        return arg0;
    }

    private static void filterRedundantIncompleteSchemeDatas(ArrayList arg4) {
        int v0;
        for(v0 = arg4.size() - 1; v0 >= 0; --v0) {
            Object v1 = arg4.get(v0);
            if(!((SchemeData)v1).hasData()) {
                int v2 = 0;
                while(v2 < arg4.size()) {
                    if(arg4.get(v2).canReplace(((SchemeData)v1))) {
                        arg4.remove(v0);
                    }
                    else {
                        ++v2;
                        continue;
                    }

                    break;
                }
            }
        }
    }

    protected int getContentType(Format arg3) {
        String v3 = arg3.sampleMimeType;
        int v1 = -1;
        if(TextUtils.isEmpty(((CharSequence)v3))) {
            return v1;
        }

        if(MimeTypes.isVideo(v3)) {
            return 2;
        }

        if(MimeTypes.isAudio(v3)) {
            return 1;
        }

        if(DashManifestParser.mimeTypeIsRawText(v3)) {
            return 3;
        }

        return v1;
    }

    private static String getSampleMimeType(String arg2, String arg3) {
        if(MimeTypes.isAudio(arg2)) {
            return MimeTypes.getAudioMediaMimeType(arg3);
        }

        if(MimeTypes.isVideo(arg2)) {
            return MimeTypes.getVideoMediaMimeType(arg3);
        }

        if(DashManifestParser.mimeTypeIsRawText(arg2)) {
            return arg2;
        }

        String v1 = null;
        if("application/mp4".equals(arg2)) {
            if("stpp".equals(arg3)) {
                return "application/ttml+xml";
            }
            else if("wvtt".equals(arg3)) {
                return "application/x-mp4-vtt";
            }
        }
        else if(("application/x-rawcc".equals(arg2)) && arg3 != null) {
            if(arg3.contains("cea708")) {
                return "application/cea-708";
            }
            else {
                if(!arg3.contains("eia608") && !arg3.contains("cea608")) {
                    return v1;
                }

                return "application/cea-608";
            }
        }

        return v1;
    }

    private static boolean mimeTypeIsRawText(String arg1) {
        boolean v1 = (MimeTypes.isText(arg1)) || ("application/ttml+xml".equals(arg1)) || ("application/x-mp4-vtt".equals(arg1)) || ("application/cea-708".equals(arg1)) || ("application/cea-608".equals(arg1)) ? true : false;
        return v1;
    }

    public DashManifest parse(Uri arg3, InputStream arg4) {
        try {
            XmlPullParser v0 = this.xmlParserFactory.newPullParser();
            v0.setInput(arg4, null);
            if(v0.next() == 2 && ("MPD".equals(v0.getName()))) {
                return this.parseMediaPresentationDescription(v0, arg3.toString());
            }

            throw new ParserException("inputStream does not contain a valid media presentation description");
        }
        catch(XmlPullParserException v3) {
            throw new ParserException(((Throwable)v3));
        }
    }

    public Object parse(Uri arg1, InputStream arg2) {
        return this.parse(arg1, arg2);
    }

    protected AdaptationSet parseAdaptationSet(XmlPullParser arg40, String arg41, SegmentBase arg42) {
        int v10_1;
        XmlPullParser v1;
        String v38;
        ArrayList v37;
        ArrayList v7_1;
        ArrayList v35;
        ArrayList v34;
        ArrayList v6_1;
        String v31;
        String v32;
        SegmentList v0_4;
        int v2_1;
        int v8_1;
        DashManifestParser v15 = this;
        XmlPullParser v14 = arg40;
        int v16 = DashManifestParser.parseInt(v14, "id", -1);
        int v0 = this.parseContentType(arg40);
        String v13 = null;
        String v17 = v14.getAttributeValue(v13, "mimeType");
        String v18 = v14.getAttributeValue(v13, "codecs");
        int v19 = DashManifestParser.parseInt(v14, "width", -1);
        int v20 = DashManifestParser.parseInt(v14, "height", -1);
        float v21 = DashManifestParser.parseFrameRate(v14, -1f);
        int v22 = DashManifestParser.parseInt(v14, "audioSamplingRate", -1);
        String v2 = v14.getAttributeValue(v13, "lang");
        String v23 = v14.getAttributeValue(v13, "label");
        ArrayList v12 = new ArrayList();
        ArrayList v11 = new ArrayList();
        ArrayList v10 = new ArrayList();
        ArrayList v9 = new ArrayList();
        ArrayList v8 = new ArrayList();
        String v7 = arg41;
        SegmentBase v28 = arg42;
        int v5 = v0;
        String v6 = v2;
        Object v29 = v13;
        int v25 = 0;
        int v26 = 0;
        int v27 = -1;
        while(true) {
            arg40.next();
            if(!XmlPullParserUtil.isStartTag(v14, "BaseURL")) {
                if(XmlPullParserUtil.isStartTag(v14, "ContentProtection")) {
                    Pair v0_1 = this.parseContentProtection(arg40);
                    if(v0_1.first != null) {
                        v29 = v0_1.first;
                    }

                    if(v0_1.second == null) {
                        goto label_84;
                    }

                    v12.add(v0_1.second);
                }
                else {
                    if(XmlPullParserUtil.isStartTag(v14, "ContentComponent")) {
                        v31 = DashManifestParser.checkLanguageConsistency(v6, v14.getAttributeValue(v13, "lang"));
                        v32 = v7;
                        v6_1 = v8;
                        v34 = v9;
                        v35 = v10;
                        v7_1 = v11;
                        v37 = v12;
                        v38 = v13;
                        v1 = v14;
                        v8_1 = DashManifestParser.checkContentTypeConsistency(v5, this.parseContentType(arg40));
                        goto label_220;
                    }

                    if(XmlPullParserUtil.isStartTag(v14, "Role")) {
                        v26 |= this.parseRole(arg40);
                        goto label_84;
                    }

                    if(!XmlPullParserUtil.isStartTag(v14, "AudioChannelConfiguration")) {
                        goto label_124;
                    }

                    v27 = this.parseAudioChannelConfiguration(arg40);
                }

            label_84:
                v31 = v6;
                v32 = v7;
                v6_1 = v8;
                v34 = v9;
                v35 = v10;
                v7_1 = v11;
                v37 = v12;
                v38 = v13;
                v1 = v14;
            label_59:
                v8_1 = v5;
                goto label_220;
            label_124:
                if(XmlPullParserUtil.isStartTag(v14, "Accessibility")) {
                    v10.add(DashManifestParser.parseDescriptor(v14, "Accessibility"));
                    goto label_61;
                }

                if(XmlPullParserUtil.isStartTag(v14, "SupplementalProperty")) {
                    v9.add(DashManifestParser.parseDescriptor(v14, "SupplementalProperty"));
                label_61:
                    v2_1 = v5;
                    v31 = v6;
                    v32 = v7;
                    v6_1 = v8;
                    v34 = v9;
                    v35 = v10;
                    v7_1 = v11;
                    v37 = v12;
                    v38 = v13;
                    v1 = v14;
                    goto label_219;
                }

                if(XmlPullParserUtil.isStartTag(v14, "Representation")) {
                    v31 = v6;
                    v32 = v7;
                    v34 = v9;
                    v35 = v10;
                    v37 = v12;
                    v38 = v13;
                    RepresentationInfo v0_2 = this.parseRepresentation(arg40, v7, v23, v17, v18, v19, v20, v21, v27, v22, v31, v26, v35, v28);
                    int v1_1 = DashManifestParser.checkContentTypeConsistency(v5, v15.getContentType(v0_2.format));
                    v6_1 = v8;
                    ((List)v6_1).add(v0_2);
                    v8_1 = v1_1;
                    v7_1 = v11;
                    v1 = arg40;
                    goto label_220;
                }

                v2_1 = v5;
                v31 = v6;
                v32 = v7;
                v6_1 = v8;
                v34 = v9;
                v35 = v10;
                ArrayList v36 = v11;
                v37 = v12;
                v38 = v13;
                v1 = arg40;
                if(XmlPullParserUtil.isStartTag(v1, "SegmentBase")) {
                    SingleSegmentBase v0_3 = v15.parseSegmentBase(v1, v28);
                }
                else if(XmlPullParserUtil.isStartTag(v1, "SegmentList")) {
                    v0_4 = v15.parseSegmentList(v1, v28);
                }
                else if(XmlPullParserUtil.isStartTag(v1, "SegmentTemplate")) {
                    SegmentTemplate v0_5 = v15.parseSegmentTemplate(v1, v28);
                }
                else {
                    goto label_207;
                }

                SingleSegmentBase v28_1 = ((SingleSegmentBase)v0_4);
                v8_1 = v2_1;
                v7_1 = v36;
                goto label_220;
            label_207:
                if(XmlPullParserUtil.isStartTag(v1, "InbandEventStream")) {
                    v7_1 = v36;
                    v7_1.add(DashManifestParser.parseDescriptor(v1, "InbandEventStream"));
                    goto label_219;
                }

                v7_1 = v36;
                if(XmlPullParserUtil.isStartTag(arg40)) {
                    this.parseAdaptationSetChild(arg40);
                }

            label_219:
                v8_1 = v2_1;
            }
            else if(v25 == 0) {
                v32 = DashManifestParser.parseBaseUrl(v14, v7);
                v31 = v6;
                v6_1 = v8;
                v34 = v9;
                v35 = v10;
                v7_1 = v11;
                v37 = v12;
                v38 = v13;
                v1 = v14;
                v25 = 1;
                goto label_59;
            }
            else {
                goto label_61;
            }

        label_220:
            if(XmlPullParserUtil.isEndTag(v1, "AdaptationSet")) {
                v9 = new ArrayList(((List)v6_1).size());
                v10_1 = 0;
                break;
            }

            v14 = v1;
            v11 = v7_1;
            v5 = v8_1;
            v7 = v32;
            v9 = v34;
            v10 = v35;
            v12 = v37;
            v13 = v38;
            v8 = v6_1;
            v6 = v31;
        }

        while(v10_1 < ((List)v6_1).size()) {
            ((List)v9).add(this.buildRepresentation(((List)v6_1).get(v10_1), v15.contentId, v29, v37, v7_1));
            ++v10_1;
        }

        return this.buildAdaptationSet(v16, v8_1, v9, v35, v34);
    }

    protected void parseAdaptationSetChild(XmlPullParser arg1) {
    }

    protected int parseAudioChannelConfiguration(XmlPullParser arg4) {
        String v0 = DashManifestParser.parseString(arg4, "schemeIdUri", null);
        int v2 = -1;
        if("urn:mpeg:dash:23003:3:audio_channel_configuration:2011".equals(v0)) {
            v2 = DashManifestParser.parseInt(arg4, "value", v2);
        }
        else if("tag:dolby.com,2014:dash:audio_channel_configuration:2011".equals(v0)) {
            v2 = DashManifestParser.parseDolbyChannelConfiguration(arg4);
        }

        do {
            arg4.next();
        }
        while(!XmlPullParserUtil.isEndTag(arg4, "AudioChannelConfiguration"));

        return v2;
    }

    protected static String parseBaseUrl(XmlPullParser arg0, String arg1) {
        arg0.next();
        return UriUtil.resolve(arg1, arg0.getText());
    }

    protected static int parseCea608AccessibilityChannel(List arg5) {
        int v0;
        for(v0 = 0; v0 < arg5.size(); ++v0) {
            Object v1 = arg5.get(v0);
            if(("urn:scte:dash:cc:cea-608:2015".equals(((Descriptor)v1).schemeIdUri)) && ((Descriptor)v1).value != null) {
                Matcher v2 = DashManifestParser.CEA_608_ACCESSIBILITY_PATTERN.matcher(((Descriptor)v1).value);
                if(v2.matches()) {
                    return Integer.parseInt(v2.group(1));
                }
                else {
                    Log.w("MpdParser", "Unable to parse CEA-608 channel number from: " + ((Descriptor)v1).value);
                }
            }
        }

        return -1;
    }

    protected static int parseCea708AccessibilityChannel(List arg5) {
        int v0;
        for(v0 = 0; v0 < arg5.size(); ++v0) {
            Object v1 = arg5.get(v0);
            if(("urn:scte:dash:cc:cea-708:2015".equals(((Descriptor)v1).schemeIdUri)) && ((Descriptor)v1).value != null) {
                Matcher v2 = DashManifestParser.CEA_708_ACCESSIBILITY_PATTERN.matcher(((Descriptor)v1).value);
                if(v2.matches()) {
                    return Integer.parseInt(v2.group(1));
                }
                else {
                    Log.w("MpdParser", "Unable to parse CEA-708 service block number from: " + ((Descriptor)v1).value);
                }
            }
        }

        return -1;
    }

    protected Pair parseContentProtection(XmlPullParser arg17) {
        Object v0_2;
        UUID v10_1;
        byte[] v13;
        String v6_2;
        boolean v8;
        String v7_1;
        byte[] v5;
        UUID v1_2;
        int v1_1;
        XmlPullParser v0 = arg17;
        String v2 = null;
        String v1 = v0.getAttributeValue(v2, "schemeIdUri");
        if(v1 != null) {
            v1 = Util.toLowerInvariant(v1);
            int v6 = v1.hashCode();
            if(v6 != 489446379) {
                if(v6 != 755418770) {
                    if(v6 != 1812765994) {
                        goto label_32;
                    }
                    else if(v1.equals("urn:mpeg:dash:mp4protection:2011")) {
                        v1_1 = 0;
                    }
                    else {
                        goto label_32;
                    }
                }
                else if(v1.equals("urn:uuid:edef8ba9-79d6-4ace-a3c8-27dcd51d21ed")) {
                    v1_1 = 2;
                }
                else {
                    goto label_32;
                }
            }
            else if(v1.equals("urn:uuid:9a04f079-9840-4286-ab92-e65be0885f95")) {
                v1_1 = 1;
            }
            else {
            label_32:
                v1_1 = -1;
            }

            switch(v1_1) {
                case 0: {
                    goto label_40;
                }
                case 1: {
                    goto label_37;
                }
                case 2: {
                    goto label_35;
                }
            }

            goto label_75;
        label_35:
            v1_2 = C.WIDEVINE_UUID;
            goto label_38;
        label_37:
            v1_2 = C.PLAYREADY_UUID;
        label_38:
            v5 = ((byte[])v2);
            goto label_77;
        label_40:
            v1 = v0.getAttributeValue(v2, "value");
            String v5_1 = v0.getAttributeValue(v2, "cenc:default_KID");
            if(!TextUtils.isEmpty(((CharSequence)v5_1)) && !"00000000-0000-0000-0000-000000000000".equals(v5_1)) {
                String[] v5_2 = v5_1.split("\\s+");
                UUID[] v6_1 = new UUID[v5_2.length];
                int v7;
                for(v7 = 0; v7 < v5_2.length; ++v7) {
                    v6_1[v7] = UUID.fromString(v5_2[v7]);
                }

                v5 = PsshAtomUtil.buildPsshAtom(C.COMMON_PSSH_UUID, v6_1, ((byte[])v2));
                v7_1 = v2;
                v8 = false;
                v6_2 = v1;
                v1_2 = C.COMMON_PSSH_UUID;
                goto label_80;
            }

            v6_2 = v1;
            v1_2 = ((UUID)v2);
            v5 = ((byte[])v1_2);
            v7_1 = ((String)v5);
            goto label_79;
        }
        else {
        label_75:
            v1_2 = ((UUID)v2);
            v5 = ((byte[])v1_2);
        label_77:
            Object v6_3 = v5;
            v7_1 = ((String)v6_3);
        label_79:
            v8 = false;
        }

        while(true) {
        label_80:
            arg17.next();
            if(XmlPullParserUtil.isStartTag(v0, "ms:laurl")) {
                v7_1 = v0.getAttributeValue(v2, "licenseUrl");
                goto label_86;
            }
            else if(XmlPullParserUtil.isStartTag(v0, "widevine:license")) {
                String v8_1 = v0.getAttributeValue(v2, "robustness_level");
                if(v8_1 != null && (v8_1.startsWith("HW"))) {
                    v8 = true;
                    goto label_86;
                }

                v8 = false;
                goto label_86;
            }
            else {
                if(v5 == null) {
                    int v10 = 4;
                    if((XmlPullParserUtil.isStartTag(v0, "cenc:pssh")) && arg17.next() == v10) {
                        byte[] v1_3 = Base64.decode(arg17.getText(), 0);
                        UUID v5_3 = PsshAtomUtil.parseUuid(v1_3);
                        if(v5_3 == null) {
                            Log.w("MpdParser", "Skipping malformed cenc:pssh data");
                            v13 = ((byte[])v2);
                        }
                        else {
                            v13 = v1_3;
                        }

                        v10_1 = v5_3;
                        goto label_88;
                    }

                    if(!C.PLAYREADY_UUID.equals(v1_2)) {
                        goto label_86;
                    }

                    if(!XmlPullParserUtil.isStartTag(v0, "mspr:pro")) {
                        goto label_86;
                    }

                    if(arg17.next() != v10) {
                        goto label_86;
                    }

                    v5 = PsshAtomUtil.buildPsshAtom(C.PLAYREADY_UUID, Base64.decode(arg17.getText(), 0));
                }

            label_86:
                v10_1 = v1_2;
                v13 = v5;
            }

        label_88:
            String v11 = v7_1;
            boolean v14 = v8;
            if(XmlPullParserUtil.isEndTag(v0, "ContentProtection")) {
                if(v10_1 != null) {
                    SchemeData v0_1 = new SchemeData(v10_1, v11, "video/mp4", v13, v14);
                }
                else {
                    v0_2 = v2;
                }

                return Pair.create(v6_2, v0_2);
            }

            v1_2 = v10_1;
            v7_1 = v11;
            v5 = v13;
            v8 = v14;
        }
    }

    protected int parseContentType(XmlPullParser arg3) {
        String v3 = arg3.getAttributeValue(null, "contentType");
        int v1 = -1;
        if(TextUtils.isEmpty(((CharSequence)v3))) {
        }
        else if("audio".equals(v3)) {
            v1 = 1;
        }
        else if("video".equals(v3)) {
            v1 = 2;
        }
        else if("text".equals(v3)) {
            v1 = 3;
        }

        return v1;
    }

    protected static long parseDateTime(XmlPullParser arg1, String arg2, long arg3) {
        String v1 = arg1.getAttributeValue(null, arg2);
        if(v1 == null) {
            return arg3;
        }

        return Util.parseXsDateTime(v1);
    }

    protected static Descriptor parseDescriptor(XmlPullParser arg4, String arg5) {
        String v0 = DashManifestParser.parseString(arg4, "schemeIdUri", "");
        String v1 = DashManifestParser.parseString(arg4, "value", null);
        String v2 = DashManifestParser.parseString(arg4, "id", null);
        do {
            arg4.next();
        }
        while(!XmlPullParserUtil.isEndTag(arg4, arg5));

        return new Descriptor(v0, v1, v2);
    }

    protected static int parseDolbyChannelConfiguration(XmlPullParser arg5) {
        int v5_1;
        String v5 = Util.toLowerInvariant(arg5.getAttributeValue(null, "value"));
        int v0 = -1;
        if(v5 == null) {
            return v0;
        }

        int v1 = v5.hashCode();
        int v3 = 2;
        if(v1 != 1596796) {
            if(v1 != 2937391) {
                if(v1 != 3094035) {
                    if(v1 != 3133436) {
                        goto label_39;
                    }
                    else if(v5.equals("fa01")) {
                        v5_1 = 3;
                    }
                    else {
                        goto label_39;
                    }
                }
                else if(v5.equals("f801")) {
                    v5_1 = 2;
                }
                else {
                    goto label_39;
                }
            }
            else if(v5.equals("a000")) {
                v5_1 = 1;
            }
            else {
                goto label_39;
            }
        }
        else if(v5.equals("4000")) {
            v5_1 = 0;
        }
        else {
        label_39:
            v5_1 = -1;
        }

        switch(v5_1) {
            case 0: {
                return 1;
            }
            case 1: {
                return v3;
            }
            case 2: {
                return 6;
            }
            case 3: {
                return 8;
            }
        }

        return v0;
    }

    protected static long parseDuration(XmlPullParser arg1, String arg2, long arg3) {
        String v1 = arg1.getAttributeValue(null, arg2);
        if(v1 == null) {
            return arg3;
        }

        return Util.parseXsDuration(v1);
    }

    protected static String parseEac3SupplementalProperties(List arg4) {
        int v0;
        for(v0 = 0; v0 < arg4.size(); ++v0) {
            Object v1 = arg4.get(v0);
            if(("tag:dolby.com,2014:dash:DolbyDigitalPlusExtensionType:2014".equals(((Descriptor)v1).schemeIdUri)) && ("ec+3".equals(((Descriptor)v1).value))) {
                return "audio/eac3-joc";
            }
        }

        return "audio/eac3";
    }

    protected EventMessage parseEvent(XmlPullParser arg18, String arg19, String arg20, long arg21, ByteArrayOutputStream arg23) {
        return this.buildEvent(arg19, arg20, DashManifestParser.parseLong(arg18, "id", 0), Util.scaleLargeTimestamp(DashManifestParser.parseLong(arg18, "duration", -9223372036854775807L), 1000, arg21), this.parseEventObject(arg18, arg23), Util.scaleLargeTimestamp(DashManifestParser.parseLong(arg18, "presentationTime", 0), 1000000, arg21));
    }

    protected byte[] parseEventObject(XmlPullParser arg7, ByteArrayOutputStream arg8) {
        int v3;
        arg8.reset();
        XmlSerializer v0 = Xml.newSerializer();
        String v1 = null;
        v0.setOutput(((OutputStream)arg8), v1);
        while(true) {
        label_4:
            arg7.nextToken();
            if(XmlPullParserUtil.isEndTag(arg7, "Event")) {
                break;
            }

            v3 = 0;
            switch(arg7.getEventType()) {
                case 0: {
                    goto label_50;
                }
                case 1: {
                    goto label_48;
                }
                case 2: {
                    goto label_37;
                }
                case 3: {
                    goto label_33;
                }
                case 4: {
                    goto label_30;
                }
                case 5: {
                    goto label_27;
                }
                case 6: {
                    goto label_24;
                }
                case 7: {
                    goto label_21;
                }
                case 8: {
                    goto label_18;
                }
                case 9: {
                    goto label_15;
                }
                case 10: {
                    goto label_12;
                }
            }

            continue;
        label_33:
            v0.endTag(arg7.getNamespace(), arg7.getName());
            continue;
        label_50:
            v0.startDocument(v1, Boolean.valueOf(false));
            continue;
        label_18:
            v0.processingInstruction(arg7.getText());
            continue;
        label_37:
            v0.startTag(arg7.getNamespace(), arg7.getName());
            goto label_40;
        label_21:
            v0.ignorableWhitespace(arg7.getText());
            continue;
        label_24:
            v0.entityRef(arg7.getText());
            continue;
        label_27:
            v0.cdsect(arg7.getText());
            continue;
        label_12:
            v0.docdecl(arg7.getText());
            continue;
        label_30:
            v0.text(arg7.getText());
            continue;
        label_15:
            v0.comment(arg7.getText());
            continue;
        label_48:
            v0.endDocument();
        }

        v0.flush();
        return arg8.toByteArray();
        while(true) {
        label_40:
            if(v3 >= arg7.getAttributeCount()) {
                goto label_4;
            }

            v0.attribute(arg7.getAttributeNamespace(v3), arg7.getAttributeName(v3), arg7.getAttributeValue(v3));
            ++v3;
        }
    }

    protected EventStream parseEventStream(XmlPullParser arg14) {
        String v0 = DashManifestParser.parseString(arg14, "schemeIdUri", "");
        String v1 = DashManifestParser.parseString(arg14, "value", "");
        long v9 = DashManifestParser.parseLong(arg14, "timescale", 1);
        ArrayList v11 = new ArrayList();
        ByteArrayOutputStream v12 = new ByteArrayOutputStream(512);
        do {
            arg14.next();
            if(XmlPullParserUtil.isStartTag(arg14, "Event")) {
                ((List)v11).add(this.parseEvent(arg14, v0, v1, v9, v12));
            }
        }
        while(!XmlPullParserUtil.isEndTag(arg14, "EventStream"));

        long[] v7 = new long[((List)v11).size()];
        EventMessage[] v8 = new EventMessage[((List)v11).size()];
        int v14;
        for(v14 = 0; v14 < ((List)v11).size(); ++v14) {
            Object v2 = ((List)v11).get(v14);
            v7[v14] = ((EventMessage)v2).presentationTimeUs;
            v8[v14] = ((EventMessage)v2);
        }

        return this.buildEventStream(v0, v1, v9, v7, v8);
    }

    protected static float parseFrameRate(XmlPullParser arg2, float arg3) {
        String v2 = arg2.getAttributeValue(null, "frameRate");
        if(v2 != null) {
            Matcher v2_1 = DashManifestParser.FRAME_RATE_PATTERN.matcher(((CharSequence)v2));
            if(v2_1.matches()) {
                int v3 = Integer.parseInt(v2_1.group(1));
                v2 = v2_1.group(2);
                arg3 = !TextUtils.isEmpty(((CharSequence)v2)) ? (((float)v3)) / (((float)Integer.parseInt(v2))) : ((float)v3);
            }
        }

        return arg3;
    }

    protected RangedUri parseInitialization(XmlPullParser arg3) {
        return this.parseRangedUrl(arg3, "sourceURL", "range");
    }

    protected static int parseInt(XmlPullParser arg1, String arg2, int arg3) {
        String v1 = arg1.getAttributeValue(null, arg2);
        if(v1 == null) {
        }
        else {
            arg3 = Integer.parseInt(v1);
        }

        return arg3;
    }

    protected static long parseLong(XmlPullParser arg1, String arg2, long arg3) {
        String v1 = arg1.getAttributeValue(null, arg2);
        if(v1 == null) {
        }
        else {
            arg3 = Long.parseLong(v1);
        }

        return arg3;
    }

    protected DashManifest parseMediaPresentationDescription(XmlPullParser arg34, String arg35) {
        long v29;
        int v28;
        long v31;
        XmlPullParser v0 = arg34;
        long v2 = -9223372036854775807L;
        long v5 = DashManifestParser.parseDateTime(v0, "availabilityStartTime", v2);
        long v7 = DashManifestParser.parseDuration(v0, "mediaPresentationDuration", v2);
        long v9 = DashManifestParser.parseDuration(v0, "minBufferTime", v2);
        String v1 = v0.getAttributeValue(null, "type");
        int v12 = 0;
        boolean v1_1 = v1 == null || !"dynamic".equals(v1) ? false : true;
        long v13 = v1_1 ? DashManifestParser.parseDuration(v0, "minimumUpdatePeriod", v2) : v2;
        long v15 = v1_1 ? DashManifestParser.parseDuration(v0, "timeShiftBufferDepth", v2) : v2;
        long v17 = v1_1 ? DashManifestParser.parseDuration(v0, "suggestedPresentationDelay", v2) : v2;
        long v19 = DashManifestParser.parseDateTime(v0, "publishTime", v2);
        ArrayList v4 = new ArrayList();
        long v21 = v1_1 ? v2 : 0;
        String v11 = arg35;
        v2 = v21;
        int v21_1 = 0;
        Uri v22 = null;
        UtcTimingElement v23 = null;
        while(true) {
            arg34.next();
            long v26 = v15;
            if(XmlPullParserUtil.isStartTag(v0, "BaseURL")) {
                if(v12 == 0) {
                    v11 = DashManifestParser.parseBaseUrl(v0, v11);
                    v31 = v13;
                    v12 = 1;
                }
                else {
                    goto label_57;
                }
            }
            else if(XmlPullParserUtil.isStartTag(v0, "UTCTiming")) {
                v31 = v13;
                v23 = this.parseUtcTiming(arg34);
            }
            else if(XmlPullParserUtil.isStartTag(v0, "Location")) {
                v31 = v13;
                v22 = Uri.parse(arg34.nextText());
            }
            else if(!XmlPullParserUtil.isStartTag(v0, "Period")) {
            label_57:
                v29 = v2;
                v28 = v12;
                v31 = v13;
                v12 = v28;
                v2 = v29;
            }
            else if(v21_1 == 0) {
                v28 = v12;
                Pair v12_1 = this.parsePeriod(v0, v11, v2);
                v29 = v2;
                Object v2_1 = v12_1.first;
                v31 = v13;
                if(((Period)v2_1).startMs != -9223372036854775807L) {
                    long v12_2 = v12_1.second.longValue();
                    if(v12_2 == -9223372036854775807L) {
                        v12_2 = -9223372036854775807L;
                    }
                    else {
                        v12_2 += ((Period)v2_1).startMs;
                    }

                    ((List)v4).add(v2_1);
                    v2 = v12_2;
                    v12 = v28;
                }
                else if(v1_1) {
                    v12 = v28;
                    v2 = v29;
                    v21_1 = 1;
                }
                else {
                    StringBuilder v1_2 = new StringBuilder();
                    v1_2.append("Unable to determine start of period ");
                    v1_2.append(((List)v4).size());
                    throw new ParserException(v1_2.toString());
                }
            }
            else {
                goto label_57;
            }

            if(XmlPullParserUtil.isEndTag(v0, "MPD")) {
                v13 = -9223372036854775807L;
                if(v7 == v13) {
                    if(v2 != v13) {
                        v7 = v2;
                    }
                    else if(v1_1) {
                    }
                    else {
                        throw new ParserException("Unable to determine duration of static manifest.");
                    }
                }

                if(!((List)v4).isEmpty()) {
                    return this.buildMediaPresentationDescription(v5, v7, v9, v1_1, v31, v26, v17, v19, v23, v22, v4);
                }

                throw new ParserException("No periods found.");
            }

            v15 = v26;
            v13 = v31;
        }
    }

    protected Pair parsePeriod(XmlPullParser arg10, String arg11, long arg12) {
        String v1 = null;
        String v3 = arg10.getAttributeValue(v1, "id");
        long v4 = DashManifestParser.parseDuration(arg10, "start", arg12);
        arg12 = DashManifestParser.parseDuration(arg10, "duration", -9223372036854775807L);
        ArrayList v6 = new ArrayList();
        ArrayList v7 = new ArrayList();
        int v0 = 0;
        SegmentBase v2 = ((SegmentBase)v1);
        do {
            arg10.next();
            if(XmlPullParserUtil.isStartTag(arg10, "BaseURL")) {
                if(v0 == 0) {
                    arg11 = DashManifestParser.parseBaseUrl(arg10, arg11);
                    v0 = 1;
                }
            }
            else if(XmlPullParserUtil.isStartTag(arg10, "AdaptationSet")) {
                ((List)v6).add(this.parseAdaptationSet(arg10, arg11, v2));
            }
            else if(XmlPullParserUtil.isStartTag(arg10, "EventStream")) {
                ((List)v7).add(this.parseEventStream(arg10));
            }
            else if(XmlPullParserUtil.isStartTag(arg10, "SegmentBase")) {
                SingleSegmentBase v2_1 = this.parseSegmentBase(arg10, ((SingleSegmentBase)v1));
            }
            else if(XmlPullParserUtil.isStartTag(arg10, "SegmentList")) {
                SegmentList v2_2 = this.parseSegmentList(arg10, ((SegmentList)v1));
            }
            else if(XmlPullParserUtil.isStartTag(arg10, "SegmentTemplate")) {
                SegmentTemplate v2_3 = this.parseSegmentTemplate(arg10, ((SegmentTemplate)v1));
            }
        }
        while(!XmlPullParserUtil.isEndTag(arg10, "Period"));

        return Pair.create(this.buildPeriod(v3, v4, ((List)v6), ((List)v7)), Long.valueOf(arg12));
    }

    protected RangedUri parseRangedUrl(XmlPullParser arg8, String arg9, String arg10) {
        long v5;
        long v0;
        String v2 = arg8.getAttributeValue(null, arg9);
        String v8 = arg8.getAttributeValue(null, arg10);
        long v9 = -1;
        if(v8 != null) {
            String[] v8_1 = v8.split("-");
            v0 = Long.parseLong(v8_1[0]);
            if(v8_1.length == 2) {
                v5 = Long.parseLong(v8_1[1]) - v0 + 1;
            }
            else {
                goto label_22;
            }
        }
        else {
            v0 = 0;
        label_22:
            v5 = v9;
        }

        return this.buildRangedUri(v2, v0, v5);
    }

    protected RepresentationInfo parseRepresentation(XmlPullParser arg25, String arg26, String arg27, String arg28, String arg29, int arg30, int arg31, float arg32, int arg33, int arg34, String arg35, int arg36, List arg37, SegmentBase arg38) {
        SegmentTemplate v10_3;
        String v19;
        DashManifestParser v15 = this;
        XmlPullParser v0 = arg25;
        String v1 = v0.getAttributeValue(null, "id");
        int v9 = DashManifestParser.parseInt(v0, "bandwidth", -1);
        String v3 = DashManifestParser.parseString(v0, "mimeType", arg28);
        String v13 = DashManifestParser.parseString(v0, "codecs", arg29);
        int v4 = DashManifestParser.parseInt(v0, "width", arg30);
        int v5 = DashManifestParser.parseInt(v0, "height", arg31);
        float v6 = DashManifestParser.parseFrameRate(v0, arg32);
        int v8 = DashManifestParser.parseInt(v0, "audioSamplingRate", arg34);
        ArrayList v14 = new ArrayList();
        ArrayList v12 = new ArrayList();
        ArrayList v11 = new ArrayList();
        int v7 = 0;
        int v16 = arg33;
        SegmentBase v10 = arg38;
        Object v17 = null;
        String v2 = arg26;
        while(true) {
            arg25.next();
            String v18 = v13;
            if(XmlPullParserUtil.isStartTag(v0, "BaseURL")) {
                if(v7 == 0) {
                    v2 = DashManifestParser.parseBaseUrl(v0, v2);
                    v7 = 1;
                }
                else {
                }

                goto label_44;
            }
            else if(XmlPullParserUtil.isStartTag(v0, "AudioChannelConfiguration")) {
                v19 = v2;
                v16 = this.parseAudioChannelConfiguration(arg25);
            }
            else if(XmlPullParserUtil.isStartTag(v0, "SegmentBase")) {
                SingleSegmentBase v10_1 = v15.parseSegmentBase(v0, ((SingleSegmentBase)v10));
                goto label_44;
            }
            else if(XmlPullParserUtil.isStartTag(v0, "SegmentList")) {
                SegmentList v10_2 = v15.parseSegmentList(v0, ((SegmentList)v10));
                goto label_44;
            }
            else if(XmlPullParserUtil.isStartTag(v0, "SegmentTemplate")) {
                v10_3 = v15.parseSegmentTemplate(v0, ((SegmentTemplate)v10));
            label_44:
                v19 = v2;
            }
            else if(XmlPullParserUtil.isStartTag(v0, "ContentProtection")) {
                Pair v13_1 = this.parseContentProtection(arg25);
                v19 = v2;
                if(v13_1.first != null) {
                    v17 = v13_1.first;
                }

                if(v13_1.second == null) {
                    goto label_96;
                }

                v14.add(v13_1.second);
            }
            else {
                v19 = v2;
                if(XmlPullParserUtil.isStartTag(v0, "InbandEventStream")) {
                    v12.add(DashManifestParser.parseDescriptor(v0, "InbandEventStream"));
                    goto label_96;
                }

                if(!XmlPullParserUtil.isStartTag(v0, "SupplementalProperty")) {
                    goto label_96;
                }

                v11.add(DashManifestParser.parseDescriptor(v0, "SupplementalProperty"));
            }

        label_96:
            Object v20 = v17;
            SegmentBase v17_1 = ((SegmentBase)v10_3);
            if(XmlPullParserUtil.isEndTag(v0, "Representation")) {
                ArrayList v22 = v12;
                ArrayList v23 = v14;
                Format v0_1 = this.buildFormat(v1, arg27, v3, v4, v5, v6, v16, v8, v9, arg35, arg36, arg37, v18, v11);
                if(v17_1 != null) {
                }
                else {
                    SingleSegmentBase v17_2 = new SingleSegmentBase();
                }

                return new RepresentationInfo(v0_1, v19, v17_1, v20, v23, v22, -1);
            }

            v10 = v17_1;
            v13 = v18;
            v2 = v19;
            v17 = v20;
        }
    }

    protected int parseRole(XmlPullParser arg4) {
        String v0 = DashManifestParser.parseString(arg4, "schemeIdUri", null);
        String v1 = DashManifestParser.parseString(arg4, "value", null);
        do {
            arg4.next();
        }
        while(!XmlPullParserUtil.isEndTag(arg4, "Role"));

        int v4 = !"urn:mpeg:dash:role:2011".equals(v0) || !"main".equals(v1) ? 0 : 1;
        return v4;
    }

    protected SingleSegmentBase parseSegmentBase(XmlPullParser arg18, SingleSegmentBase arg19) {
        RangedUri v13_1;
        long v15;
        XmlPullParser v0 = arg18;
        SingleSegmentBase v1 = arg19;
        String v2 = "timescale";
        long v3 = 1;
        long v5 = v1 != null ? v1.timescale : v3;
        long v9 = DashManifestParser.parseLong(v0, v2, v5);
        v2 = "presentationTimeOffset";
        v5 = 0;
        long v7 = v1 != null ? v1.presentationTimeOffset : v5;
        long v11 = DashManifestParser.parseLong(v0, v2, v7);
        v7 = v1 != null ? v1.indexStart : v5;
        if(v1 != null) {
            v5 = v1.indexLength;
        }

        String v13 = null;
        v2 = v0.getAttributeValue(v13, "indexRange");
        if(v2 != null) {
            String[] v2_1 = v2.split("-");
            v5 = Long.parseLong(v2_1[0]);
            v15 = Long.parseLong(v2_1[1]) - v5 + v3;
        }
        else {
            v15 = v5;
            v5 = v7;
        }

        if(v1 != null) {
            v13_1 = v1.initialization;
        }

        do {
            arg18.next();
            if(XmlPullParserUtil.isStartTag(v0, "Initialization")) {
                v13_1 = this.parseInitialization(arg18);
            }
        }
        while(!XmlPullParserUtil.isEndTag(v0, "SegmentBase"));

        return this.buildSingleSegmentBase(((RangedUri)v13), v9, v11, v5, v15);
    }

    protected SegmentList parseSegmentList(XmlPullParser arg20, SegmentList arg21) {
        XmlPullParser v0 = arg20;
        SegmentList v1 = arg21;
        String v2 = "timescale";
        long v3 = 1;
        long v5 = v1 != null ? v1.timescale : v3;
        long v9 = DashManifestParser.parseLong(v0, v2, v5);
        v2 = "presentationTimeOffset";
        v5 = v1 != null ? v1.presentationTimeOffset : 0;
        long v11 = DashManifestParser.parseLong(v0, v2, v5);
        v2 = "duration";
        v5 = v1 != null ? v1.duration : -9223372036854775807L;
        long v15 = DashManifestParser.parseLong(v0, v2, v5);
        v2 = "startNumber";
        if(v1 != null) {
            v3 = v1.startNumber;
        }

        long v13 = DashManifestParser.parseLong(v0, v2, v3);
        ArrayList v2_1 = null;
        RangedUri v3_1 = ((RangedUri)v2_1);
        List v4 = ((List)v3_1);
        do {
            arg20.next();
            if(XmlPullParserUtil.isStartTag(v0, "Initialization")) {
                v3_1 = this.parseInitialization(arg20);
            }
            else if(XmlPullParserUtil.isStartTag(v0, "SegmentTimeline")) {
                v4 = this.parseSegmentTimeline(arg20);
            }
            else if(XmlPullParserUtil.isStartTag(v0, "SegmentURL")) {
                if(v2_1 == null) {
                    v2_1 = new ArrayList();
                }

                ((List)v2_1).add(this.parseSegmentUrl(arg20));
            }
        }
        while(!XmlPullParserUtil.isEndTag(v0, "SegmentList"));

        if(v1 != null) {
            if(v3_1 != null) {
            }
            else {
                v3_1 = v1.initialization;
            }

            if(v4 != null) {
            }
            else {
                v4 = v1.segmentTimeline;
            }

            if(v2_1 != null) {
                goto label_60;
            }

            List v2_2 = v1.mediaSegments;
        }

    label_60:
        return this.buildSegmentList(v3_1, v9, v11, v13, v15, v4, v2_1);
    }

    protected SegmentTemplate parseSegmentTemplate(XmlPullParser arg18, SegmentTemplate arg19) {
        DashManifestParser v13 = this;
        XmlPullParser v0 = arg18;
        SegmentTemplate v1 = arg19;
        String v2 = "timescale";
        long v3 = 1;
        long v5 = v1 != null ? v1.timescale : v3;
        v5 = DashManifestParser.parseLong(v0, v2, v5);
        v2 = "presentationTimeOffset";
        long v7 = v1 != null ? v1.presentationTimeOffset : 0;
        v7 = DashManifestParser.parseLong(v0, v2, v7);
        v2 = "duration";
        long v9 = v1 != null ? v1.duration : -9223372036854775807L;
        v9 = DashManifestParser.parseLong(v0, v2, v9);
        v2 = "startNumber";
        if(v1 != null) {
            v3 = v1.startNumber;
        }

        long v11 = DashManifestParser.parseLong(v0, v2, v3);
        v2 = "media";
        RangedUri v3_1 = null;
        UrlTemplate v4 = v1 != null ? v1.mediaTemplate : ((UrlTemplate)v3_1);
        UrlTemplate v14 = v13.parseUrlTemplate(v0, v2, v4);
        v2 = "initialization";
        v4 = v1 != null ? v1.initializationTemplate : ((UrlTemplate)v3_1);
        UrlTemplate v15 = v13.parseUrlTemplate(v0, v2, v4);
        List v2_1 = ((List)v3_1);
        do {
            arg18.next();
            if(XmlPullParserUtil.isStartTag(v0, "Initialization")) {
                v3_1 = this.parseInitialization(arg18);
            }
            else if(XmlPullParserUtil.isStartTag(v0, "SegmentTimeline")) {
                v2_1 = this.parseSegmentTimeline(arg18);
            }
        }
        while(!XmlPullParserUtil.isEndTag(v0, "SegmentTemplate"));

        if(v1 != null) {
            if(v3_1 != null) {
            }
            else {
                v3_1 = v1.initialization;
            }

            if(v2_1 != null) {
                goto label_60;
            }

            v2_1 = v1.segmentTimeline;
        }

    label_60:
        return this.buildSegmentTemplate(v3_1, v5, v7, v11, v9, v2_1, v15, v14);
    }

    protected List parseSegmentTimeline(XmlPullParser arg9) {
        ArrayList v0 = new ArrayList();
        long v1 = 0;
        do {
            arg9.next();
            if(XmlPullParserUtil.isStartTag(arg9, "S")) {
                v1 = DashManifestParser.parseLong(arg9, "t", v1);
                long v3 = DashManifestParser.parseLong(arg9, "d", -9223372036854775807L);
                int v6 = 0;
                int v5 = DashManifestParser.parseInt(arg9, "r", 0) + 1;
                while(v6 < v5) {
                    ((List)v0).add(this.buildSegmentTimelineElement(v1, v3));
                    v1 += v3;
                    ++v6;
                }
            }
        }
        while(!XmlPullParserUtil.isEndTag(arg9, "SegmentTimeline"));

        return ((List)v0);
    }

    protected RangedUri parseSegmentUrl(XmlPullParser arg3) {
        return this.parseRangedUrl(arg3, "media", "mediaRange");
    }

    protected static String parseString(XmlPullParser arg1, String arg2, String arg3) {
        String v1 = arg1.getAttributeValue(null, arg2);
        if(v1 == null) {
            v1 = arg3;
        }

        return v1;
    }

    protected UrlTemplate parseUrlTemplate(XmlPullParser arg2, String arg3, UrlTemplate arg4) {
        String v2 = arg2.getAttributeValue(null, arg3);
        if(v2 != null) {
            return UrlTemplate.compile(v2);
        }

        return arg4;
    }

    protected UtcTimingElement parseUtcTiming(XmlPullParser arg4) {
        return this.buildUtcTimingElement(arg4.getAttributeValue(null, "schemeIdUri"), arg4.getAttributeValue(null, "value"));
    }
}

