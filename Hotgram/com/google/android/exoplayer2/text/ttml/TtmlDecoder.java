package com.google.android.exoplayer2.text.ttml;

import android.text.Layout$Alignment;
import android.util.Log;
import com.google.android.exoplayer2.text.SimpleSubtitleDecoder;
import com.google.android.exoplayer2.text.Subtitle;
import com.google.android.exoplayer2.text.SubtitleDecoderException;
import com.google.android.exoplayer2.util.ColorParser;
import com.google.android.exoplayer2.util.Util;
import com.google.android.exoplayer2.util.XmlPullParserUtil;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

public final class TtmlDecoder extends SimpleSubtitleDecoder {
    final class CellResolution {
        final int columns;
        final int rows;

        CellResolution(int arg1, int arg2) {
            super();
            this.columns = arg1;
            this.rows = arg2;
        }
    }

    final class FrameAndTickRate {
        final float effectiveFrameRate;
        final int subFrameRate;
        final int tickRate;

        FrameAndTickRate(float arg1, int arg2, int arg3) {
            super();
            this.effectiveFrameRate = arg1;
            this.subFrameRate = arg2;
            this.tickRate = arg3;
        }
    }

    private static final String ATTR_BEGIN = "begin";
    private static final String ATTR_DURATION = "dur";
    private static final String ATTR_END = "end";
    private static final String ATTR_REGION = "region";
    private static final String ATTR_STYLE = "style";
    private static final Pattern CELL_RESOLUTION = null;
    private static final Pattern CLOCK_TIME = null;
    private static final CellResolution DEFAULT_CELL_RESOLUTION = null;
    private static final FrameAndTickRate DEFAULT_FRAME_AND_TICK_RATE = null;
    private static final int DEFAULT_FRAME_RATE = 30;
    private static final Pattern FONT_SIZE = null;
    private static final Pattern OFFSET_TIME = null;
    private static final Pattern PERCENTAGE_COORDINATES = null;
    private static final String TAG = "TtmlDecoder";
    private static final String TTP = "http://www.w3.org/ns/ttml#parameter";
    private final XmlPullParserFactory xmlParserFactory;

    static {
        TtmlDecoder.CLOCK_TIME = Pattern.compile("^([0-9][0-9]+):([0-9][0-9]):([0-9][0-9])(?:(\\.[0-9]+)|:([0-9][0-9])(?:\\.([0-9]+))?)?$");
        TtmlDecoder.OFFSET_TIME = Pattern.compile("^([0-9]+(?:\\.[0-9]+)?)(h|m|s|ms|f|t)$");
        TtmlDecoder.FONT_SIZE = Pattern.compile("^(([0-9]*.)?[0-9]+)(px|em|%)$");
        TtmlDecoder.PERCENTAGE_COORDINATES = Pattern.compile("^(\\d+\\.?\\d*?)% (\\d+\\.?\\d*?)%$");
        TtmlDecoder.CELL_RESOLUTION = Pattern.compile("^(\\d+) (\\d+)$");
        TtmlDecoder.DEFAULT_FRAME_AND_TICK_RATE = new FrameAndTickRate(30f, 1, 1);
        TtmlDecoder.DEFAULT_CELL_RESOLUTION = new CellResolution(32, 15);
    }

    public TtmlDecoder() {
        super("TtmlDecoder");
        try {
            this.xmlParserFactory = XmlPullParserFactory.newInstance();
            this.xmlParserFactory.setNamespaceAware(true);
            return;
        }
        catch(XmlPullParserException v0) {
            throw new RuntimeException("Couldn\'t create XmlPullParserFactory instance", ((Throwable)v0));
        }
    }

    private TtmlStyle createIfNull(TtmlStyle arg1) {
        if(arg1 == null) {
            arg1 = new TtmlStyle();
        }

        return arg1;
    }

    protected Subtitle decode(byte[] arg1, int arg2, boolean arg3) {
        return this.decode(arg1, arg2, arg3);
    }

    protected TtmlSubtitle decode(byte[] arg11, int arg12, boolean arg13) {
        TtmlSubtitle v4_1;
        try {
            XmlPullParser v13 = this.xmlParserFactory.newPullParser();
            HashMap v0 = new HashMap();
            HashMap v1 = new HashMap();
            String v4 = null;
            ((Map)v1).put("", new TtmlRegion(v4));
            int v3 = 0;
            v13.setInput(new ByteArrayInputStream(arg11, 0, arg12), v4);
            ArrayDeque v11_2 = new ArrayDeque();
            arg12 = v13.getEventType();
            FrameAndTickRate v2 = TtmlDecoder.DEFAULT_FRAME_AND_TICK_RATE;
            CellResolution v5 = TtmlDecoder.DEFAULT_CELL_RESOLUTION;
            while(arg12 != 1) {
                Object v6 = v11_2.peek();
                int v7 = 3;
                int v8 = 2;
                if(v3 != 0) {
                    goto label_77;
                }

                String v9 = v13.getName();
                if(arg12 != v8) {
                    goto label_61;
                }

                if("tt".equals(v9)) {
                    v2 = this.parseFrameAndTickRates(v13);
                    v5 = this.parseCellResolution(v13, TtmlDecoder.DEFAULT_CELL_RESOLUTION);
                }

                if(!TtmlDecoder.isSupportedTag(v9)) {
                    Log.i("TtmlDecoder", "Ignoring unsupported tag: " + v13.getName());
                    goto label_78;
                }

                if("head".equals(v9)) {
                    this.parseHeader(v13, ((Map)v0), ((Map)v1), v5);
                    goto label_82;
                }

                try {
                    TtmlNode v12_1 = this.parseNode(v13, ((TtmlNode)v6), ((Map)v1), v2);
                    v11_2.push(v12_1);
                    if(v6 == null) {
                        goto label_82;
                    }

                    ((TtmlNode)v6).addChild(v12_1);
                    goto label_82;
                }
                catch(SubtitleDecoderException v12) {
                    try {
                        Log.w("TtmlDecoder", "Suppressing parser error", ((Throwable)v12));
                        goto label_78;
                    label_61:
                        if(arg12 == 4) {
                            ((TtmlNode)v6).addChild(TtmlNode.buildTextNode(v13.getText()));
                            goto label_82;
                        }

                        if(arg12 != v7) {
                            goto label_82;
                        }

                        if(v13.getName().equals("tt")) {
                            v4_1 = new TtmlSubtitle(v11_2.peek(), ((Map)v0), ((Map)v1));
                        }

                        v11_2.pop();
                        goto label_82;
                    label_77:
                        if(arg12 == v8) {
                        label_78:
                            ++v3;
                            goto label_82;
                        }

                        if(arg12 == v7) {
                            --v3;
                        }

                    label_82:
                        v13.next();
                        arg12 = v13.getEventType();
                        continue;
                    }
                    catch(IOException v11) {
                        goto label_90;
                    }
                    catch(XmlPullParserException v11_1) {
                        goto label_95;
                    }
                }
            }
        }
        catch(IOException v11) {
            goto label_90;
        }
        catch(XmlPullParserException v11_1) {
            goto label_95;
        }

        return v4_1;
    label_95:
        throw new SubtitleDecoderException("Unable to decode source", ((Throwable)v11_1));
    label_90:
        throw new IllegalStateException("Unexpected error when reading input.", ((Throwable)v11));
    }

    private static boolean isSupportedTag(String arg1) {
        boolean v1 = (arg1.equals("tt")) || (arg1.equals("head")) || (arg1.equals("body")) || (arg1.equals("div")) || (arg1.equals("p")) || (arg1.equals("span")) || (arg1.equals("br")) || (arg1.equals("style")) || (arg1.equals("styling")) || (arg1.equals("layout")) || (arg1.equals("region")) || (arg1.equals("metadata")) || (arg1.equals("smpte:image")) || (arg1.equals("smpte:data")) || (arg1.equals("smpte:information")) ? true : false;
        return v1;
    }

    private CellResolution parseCellResolution(XmlPullParser arg6, CellResolution arg7) {
        StringBuilder v1;
        String v0_1;
        String v6 = arg6.getAttributeValue("http://www.w3.org/ns/ttml#parameter", "cellResolution");
        if(v6 == null) {
            return arg7;
        }

        Matcher v0 = TtmlDecoder.CELL_RESOLUTION.matcher(((CharSequence)v6));
        if(!v0.matches()) {
            v0_1 = "TtmlDecoder";
            v1 = new StringBuilder();
            goto label_12;
        }

        try {
            int v1_1 = Integer.parseInt(v0.group(1));
            int v0_2 = Integer.parseInt(v0.group(2));
            if(v1_1 != 0 && v0_2 != 0) {
                return new CellResolution(v1_1, v0_2);
            }

            StringBuilder v3 = new StringBuilder();
            v3.append("Invalid cell resolution ");
            v3.append(v1_1);
            v3.append(" ");
            v3.append(v0_2);
            throw new SubtitleDecoderException(v3.toString());
        }
        catch(NumberFormatException ) {
            v0_1 = "TtmlDecoder";
            v1 = new StringBuilder();
        }

    label_12:
        v1.append("Ignoring malformed cell resolution: ");
        v1.append(v6);
        Log.w(v0_1, v1.toString());
        return arg7;
    }

    private static void parseFontSize(String arg7, TtmlStyle arg8) {
        Matcher v0_1;
        String[] v0 = Util.split(arg7, "\\s+");
        int v2 = 2;
        if(v0.length == 1) {
            v0_1 = TtmlDecoder.FONT_SIZE.matcher(((CharSequence)arg7));
        }
        else if(v0.length == v2) {
            v0_1 = TtmlDecoder.FONT_SIZE.matcher(v0[1]);
            Log.w("TtmlDecoder", "Multiple values in fontSize attribute. Picking the second value for vertical font size and ignoring the first.");
        }
        else {
            goto label_77;
        }

        if(!v0_1.matches()) {
            goto label_66;
        }

        int v7 = 3;
        String v1 = v0_1.group(v7);
        int v4 = -1;
        int v5 = v1.hashCode();
        if(v5 != 37) {
            if(v5 != 3240) {
                if(v5 != 3592) {
                }
                else if(v1.equals("px")) {
                    v4 = 0;
                }
            }
            else if(v1.equals("em")) {
                v4 = 1;
            }
        }
        else if(v1.equals("%")) {
            v4 = 2;
        }

        switch(v4) {
            case 0: {
                goto label_60;
            }
            case 1: {
                goto label_58;
            }
            case 2: {
                goto label_56;
            }
        }

        StringBuilder v8 = new StringBuilder();
        v8.append("Invalid unit for fontSize: \'");
        v8.append(v1);
        v8.append("\'.");
        throw new SubtitleDecoderException(v8.toString());
    label_56:
        arg8.setFontSizeUnit(v7);
        goto label_61;
    label_58:
        arg8.setFontSizeUnit(v2);
        goto label_61;
    label_60:
        arg8.setFontSizeUnit(1);
    label_61:
        arg8.setFontSize(Float.valueOf(v0_1.group(1)).floatValue());
        return;
    label_66:
        StringBuilder v0_2 = new StringBuilder();
        v0_2.append("Invalid expression for fontSize: \'");
        v0_2.append(arg7);
        v0_2.append("\'.");
        throw new SubtitleDecoderException(v0_2.toString());
    label_77:
        v8 = new StringBuilder();
        v8.append("Invalid number of entries for fontSize: ");
        v8.append(v0.length);
        v8.append(".");
        throw new SubtitleDecoderException(v8.toString());
    }

    private FrameAndTickRate parseFrameAndTickRates(XmlPullParser arg7) {
        String v0 = arg7.getAttributeValue("http://www.w3.org/ns/ttml#parameter", "frameRate");
        int v0_1 = v0 != null ? Integer.parseInt(v0) : 30;
        float v1 = 1f;
        String v2 = arg7.getAttributeValue("http://www.w3.org/ns/ttml#parameter", "frameRateMultiplier");
        if(v2 != null) {
            String[] v1_1 = Util.split(v2, " ");
            if(v1_1.length == 2) {
                v1 = (((float)Integer.parseInt(v1_1[0]))) / (((float)Integer.parseInt(v1_1[1])));
            }
            else {
                throw new SubtitleDecoderException("frameRateMultiplier doesn\'t have 2 parts");
            }
        }

        int v2_1 = TtmlDecoder.DEFAULT_FRAME_AND_TICK_RATE.subFrameRate;
        String v3 = arg7.getAttributeValue("http://www.w3.org/ns/ttml#parameter", "subFrameRate");
        if(v3 != null) {
            v2_1 = Integer.parseInt(v3);
        }

        int v3_1 = TtmlDecoder.DEFAULT_FRAME_AND_TICK_RATE.tickRate;
        String v7 = arg7.getAttributeValue("http://www.w3.org/ns/ttml#parameter", "tickRate");
        if(v7 != null) {
            v3_1 = Integer.parseInt(v7);
        }

        return new FrameAndTickRate((((float)v0_1)) * v1, v2_1, v3_1);
    }

    private Map parseHeader(XmlPullParser arg6, Map arg7, Map arg8, CellResolution arg9) {
        do {
            arg6.next();
            if(XmlPullParserUtil.isStartTag(arg6, "style")) {
                String v0 = XmlPullParserUtil.getAttributeValue(arg6, "style");
                TtmlStyle v1 = this.parseStyleAttributes(arg6, new TtmlStyle());
                if(v0 != null) {
                    String[] v0_1 = this.parseStyleIds(v0);
                    int v2 = v0_1.length;
                    int v3;
                    for(v3 = 0; v3 < v2; ++v3) {
                        v1.chain(arg7.get(v0_1[v3]));
                    }
                }

                if(v1.getId() == null) {
                    continue;
                }

                arg7.put(v1.getId(), v1);
            }
            else {
                if(!XmlPullParserUtil.isStartTag(arg6, "region")) {
                    continue;
                }

                TtmlRegion v0_2 = this.parseRegionAttributes(arg6, arg9);
                if(v0_2 == null) {
                    continue;
                }

                arg8.put(v0_2.id, v0_2);
            }
        }
        while(!XmlPullParserUtil.isEndTag(arg6, "head"));

        return arg7;
    }

    private TtmlNode parseNode(XmlPullParser arg20, TtmlNode arg21, Map arg22, FrameAndTickRate arg23) {
        long v7_2;
        int v7_1;
        TtmlDecoder v0 = this;
        XmlPullParser v1 = arg20;
        TtmlNode v2 = arg21;
        FrameAndTickRate v3 = arg23;
        int v5 = arg20.getAttributeCount();
        TtmlStyle v12 = v0.parseStyleAttributes(v1, null);
        String[] v17 = null;
        long v10 = -9223372036854775807L;
        long v13 = -9223372036854775807L;
        long v15 = -9223372036854775807L;
        String v6 = "";
        int v4;
        for(v4 = 0; v4 < v5; ++v4) {
            String v7 = v1.getAttributeName(v4);
            String v8 = v1.getAttributeValue(v4);
            switch(v7.hashCode()) {
                case -934795532: {
                    if(!v7.equals("region")) {
                        goto label_45;
                    }

                    v7_1 = 4;
                    break;
                }
                case 99841: {
                    if(!v7.equals("dur")) {
                        goto label_45;
                    }

                    v7_1 = 2;
                    break;
                }
                case 100571: {
                    if(v7.equals("end")) {
                        v7_1 = 1;
                        goto label_46;
                    }

                label_45:
                    v7_1 = -1;
                    break;
                }
                case 93616297: {
                    if(!v7.equals("begin")) {
                        goto label_45;
                    }

                    v7_1 = 0;
                    break;
                }
                case 109780401: {
                    if(!v7.equals("style")) {
                        goto label_45;
                    }

                    v7_1 = 3;
                    break;
                }
                default: {
                    goto label_45;
                }
            }

        label_46:
            switch(v7_1) {
                case 0: {
                    v10 = TtmlDecoder.parseTimeExpression(v8, v3);
                    break;
                }
                case 1: {
                    v13 = TtmlDecoder.parseTimeExpression(v8, v3);
                    break;
                }
                case 2: {
                    v15 = TtmlDecoder.parseTimeExpression(v8, v3);
                    break;
                }
                case 3: {
                    String[] v8_1 = v0.parseStyleIds(v8);
                    if(v8_1.length <= 0) {
                        goto label_71;
                    }

                    v17 = v8_1;
                    break;
                }
                case 4: {
                    if(!arg22.containsKey(v8)) {
                        goto label_71;
                    }

                    v6 = v8;
                    break;
                }
                default: {
                    break;
                }
            }

        label_71:
        }

        if(v2 != null) {
            v7_2 = -9223372036854775807L;
            if(v2.startTimeUs != v7_2) {
                if(v10 != v7_2) {
                    v10 += v2.startTimeUs;
                }

                if(v13 == v7_2) {
                    goto label_85;
                }

                v13 += v2.startTimeUs;
            }
        }
        else {
            v7_2 = -9223372036854775807L;
        }

    label_85:
        if(v13 == v7_2) {
            if(v15 != v7_2) {
                v13 = v15 + v10;
            }
            else if(v2 != null && v2.endTimeUs != v7_2) {
                v13 = v2.endTimeUs;
            }
        }

        return TtmlNode.buildNode(arg20.getName(), v10, v13, v12, v17, v6);
    }

    private TtmlRegion parseRegionAttributes(XmlPullParser arg12, CellResolution arg13) {
        String v13_1;
        float v4;
        int v6_1;
        float v8_2;
        float v9;
        StringBuilder v13;
        String v12;
        float v3_1;
        int v7;
        float v5;
        float v6;
        String v2 = XmlPullParserUtil.getAttributeValue(arg12, "id");
        TtmlRegion v0 = null;
        if(v2 == null) {
            return v0;
        }

        String v1 = XmlPullParserUtil.getAttributeValue(arg12, "origin");
        if(v1 != null) {
            Matcher v3 = TtmlDecoder.PERCENTAGE_COORDINATES.matcher(((CharSequence)v1));
            if(!v3.matches()) {
                goto label_106;
            }

            try {
                v6 = 100f;
                v5 = Float.parseFloat(v3.group(1)) / v6;
                v7 = 2;
                v3_1 = Float.parseFloat(v3.group(v7));
            }
            catch(NumberFormatException ) {
                v12 = "TtmlDecoder";
                v13 = new StringBuilder();
                v2 = "Ignoring region with malformed origin: ";
                goto label_88;
            }

            v3_1 /= v6;
            String v8 = XmlPullParserUtil.getAttributeValue(arg12, "extent");
            if(v8 != null) {
                Matcher v8_1 = TtmlDecoder.PERCENTAGE_COORDINATES.matcher(((CharSequence)v8));
                if(v8_1.matches()) {
                    try {
                        v9 = Float.parseFloat(v8_1.group(1)) / v6;
                        v8_2 = Float.parseFloat(v8_1.group(v7));
                    }
                    catch(NumberFormatException ) {
                        v12 = "TtmlDecoder";
                        v13 = new StringBuilder();
                        v2 = "Ignoring region with malformed extent: ";
                        goto label_88;
                    }

                    v8_2 /= v6;
                    v12 = XmlPullParserUtil.getAttributeValue(arg12, "displayAlign");
                    if(v12 != null) {
                        v12 = Util.toLowerInvariant(v12);
                        int v1_1 = -1;
                        v6_1 = v12.hashCode();
                        if(v6_1 != -1364013995) {
                            if(v6_1 != 92734940) {
                            }
                            else if(v12.equals("after")) {
                                v1_1 = 1;
                            }
                        }
                        else if(v12.equals("center")) {
                            v1_1 = 0;
                        }

                        switch(v1_1) {
                            case 0: {
                                goto label_61;
                            }
                            case 1: {
                                goto label_57;
                            }
                        }

                        goto label_67;
                    label_57:
                        v4 = v3_1 + v8_2;
                        v6_1 = 2;
                        goto label_69;
                    label_61:
                        v4 = v3_1 + v8_2 / 2f;
                        v6_1 = 1;
                    }
                    else {
                    label_67:
                        v4 = v3_1;
                        v6_1 = 0;
                    }

                label_69:
                    return new TtmlRegion(v2, v5, v4, 0, v6_1, v9, 1, 1f / (((float)arg13.rows)));
                }

                v12 = "TtmlDecoder";
                v13 = new StringBuilder();
                v2 = "Ignoring region with unsupported extent: ";
            }
            else {
                v12 = "TtmlDecoder";
                v13_1 = "Ignoring region without an extent";
                goto label_91;
            label_106:
                v12 = "TtmlDecoder";
                v13 = new StringBuilder();
                v2 = "Ignoring region with unsupported origin: ";
            }

        label_88:
            v13.append(v2);
            v13.append(v1);
            v13_1 = v13.toString();
        }
        else {
            v12 = "TtmlDecoder";
            v13_1 = "Ignoring region without an origin";
        }

    label_91:
        Log.w(v12, v13_1);
        return v0;
    }

    private TtmlStyle parseStyleAttributes(XmlPullParser arg12, TtmlStyle arg13) {
        TtmlStyle v4_2;
        String v6_1;
        StringBuilder v5;
        int v4_1;
        int v0 = arg12.getAttributeCount();
        TtmlStyle v2 = arg13;
        int v13;
        for(v13 = 0; v13 < v0; ++v13) {
            String v3 = arg12.getAttributeValue(v13);
            String v4 = arg12.getAttributeName(v13);
            int v6 = 4;
            int v7 = 2;
            switch(v4.hashCode()) {
                case -1550943582: {
                    if(!v4.equals("fontStyle")) {
                        goto label_60;
                    }

                    v4_1 = 6;
                    break;
                }
                case -1224696685: {
                    if(!v4.equals("fontFamily")) {
                        goto label_60;
                    }

                    v4_1 = 3;
                    break;
                }
                case -1065511464: {
                    if(!v4.equals("textAlign")) {
                        goto label_60;
                    }

                    v4_1 = 7;
                    break;
                }
                case -879295043: {
                    if(!v4.equals("textDecoration")) {
                        goto label_60;
                    }

                    v4_1 = 8;
                    break;
                }
                case -734428249: {
                    if(!v4.equals("fontWeight")) {
                        goto label_60;
                    }

                    v4_1 = 5;
                    break;
                }
                case 3355: {
                    if(!v4.equals("id")) {
                        goto label_60;
                    }

                    v4_1 = 0;
                    break;
                }
                case 94842723: {
                    if(!v4.equals("color")) {
                        goto label_60;
                    }

                    v4_1 = 2;
                    break;
                }
                case 365601008: {
                    if(!v4.equals("fontSize")) {
                        goto label_60;
                    }

                    v4_1 = 4;
                    break;
                }
                case 1287124693: {
                    if(v4.equals("backgroundColor")) {
                        v4_1 = 1;
                        goto label_61;
                    }

                label_60:
                    v4_1 = -1;
                    break;
                }
                default: {
                    goto label_60;
                }
            }

        label_61:
            switch(v4_1) {
                case 0: {
                    goto label_194;
                }
                case 1: {
                    goto label_185;
                }
                case 2: {
                    goto label_176;
                }
                case 3: {
                    goto label_173;
                }
                case 4: {
                    goto label_159;
                }
                case 5: {
                    goto label_154;
                }
                case 6: {
                    goto label_149;
                }
                case 7: {
                    goto label_108;
                }
                case 8: {
                    goto label_63;
                }
            }

            goto label_200;
        label_194:
            if(!"style".equals(arg12.getName())) {
                goto label_200;
            }

            v2 = this.createIfNull(v2).setId(v3);
            goto label_200;
        label_149:
            v2 = this.createIfNull(v2).setItalic("italic".equalsIgnoreCase(v3));
            goto label_200;
        label_185:
            v2 = this.createIfNull(v2);
            try {
                v2.setBackgroundColor(ColorParser.parseTtmlColor(v3));
                goto label_200;
            }
            catch(IllegalArgumentException ) {
                v4 = "TtmlDecoder";
                v5 = new StringBuilder();
                v6_1 = "Failed parsing background value: ";
                goto label_168;
            }

        label_154:
            v2 = this.createIfNull(v2).setBold("bold".equalsIgnoreCase(v3));
            goto label_200;
        label_108:
            v3 = Util.toLowerInvariant(v3);
            switch(v3.hashCode()) {
                case -1364013995: {
                    if(!v3.equals("center")) {
                        goto label_136;
                    }

                    break;
                }
                case 100571: {
                    if(!v3.equals("end")) {
                        goto label_136;
                    }

                    v6 = 3;
                    break;
                }
                case 3317767: {
                    if(!v3.equals("left")) {
                        goto label_136;
                    }

                    v6 = 0;
                    break;
                }
                case 108511772: {
                    if(!v3.equals("right")) {
                        goto label_136;
                    }

                    v6 = 2;
                    break;
                }
                case 109757538: {
                    if(v3.equals("start")) {
                        v6 = 1;
                        goto label_137;
                    }

                label_136:
                    v6 = -1;
                    break;
                }
                default: {
                    goto label_136;
                }
            }

        label_137:
            switch(v6) {
                case 0: 
                case 1: {
                    goto label_145;
                }
                case 2: 
                case 3: {
                    goto label_142;
                }
                case 4: {
                    goto label_139;
                }
            }

            goto label_200;
        label_145:
            v2 = this.createIfNull(v2);
            Layout$Alignment v3_1 = Layout$Alignment.ALIGN_NORMAL;
            goto label_147;
        label_139:
            v2 = this.createIfNull(v2);
            v3_1 = Layout$Alignment.ALIGN_CENTER;
            goto label_147;
        label_142:
            v2 = this.createIfNull(v2);
            v3_1 = Layout$Alignment.ALIGN_OPPOSITE;
        label_147:
            v2 = v2.setTextAlign(v3_1);
            goto label_200;
        label_173:
            v2 = this.createIfNull(v2).setFontFamily(v3);
            goto label_200;
            try {
            label_159:
                v4_2 = this.createIfNull(v2);
            }
            catch(SubtitleDecoderException ) {
                goto label_164;
            }

            try {
                TtmlDecoder.parseFontSize(v3, v4_2);
                v2 = v4_2;
                goto label_200;
            }
            catch(SubtitleDecoderException ) {
                v2 = v4_2;
            }

        label_164:
            v4 = "TtmlDecoder";
            v5 = new StringBuilder();
            v6_1 = "Failed parsing fontSize value: ";
            goto label_168;
        label_63:
            v3 = Util.toLowerInvariant(v3);
            v4_1 = v3.hashCode();
            if(v4_1 != -1461280213) {
                if(v4_1 != -1026963764) {
                    if(v4_1 != 913457136) {
                        if(v4_1 != 1679736913) {
                            goto label_93;
                        }
                        else if(v3.equals("linethrough")) {
                            v7 = 0;
                        }
                        else {
                            goto label_93;
                        }
                    }
                    else if(v3.equals("nolinethrough")) {
                        v7 = 1;
                    }
                    else {
                        goto label_93;
                    }
                }
                else if(v3.equals("underline")) {
                }
                else {
                    goto label_93;
                }
            }
            else if(v3.equals("nounderline")) {
                v7 = 3;
            }
            else {
            label_93:
                v7 = -1;
            }

            switch(v7) {
                case 0: {
                    goto label_105;
                }
                case 1: {
                    goto label_102;
                }
                case 2: {
                    goto label_99;
                }
                case 3: {
                    goto label_96;
                }
            }

            goto label_200;
        label_99:
            v2 = this.createIfNull(v2).setUnderline(true);
            goto label_200;
        label_102:
            v2 = this.createIfNull(v2).setLinethrough(false);
            goto label_200;
        label_105:
            v2 = this.createIfNull(v2).setLinethrough(true);
            goto label_200;
        label_96:
            v2 = this.createIfNull(v2).setUnderline(false);
            goto label_200;
        label_176:
            v2 = this.createIfNull(v2);
            try {
                v2.setFontColor(ColorParser.parseTtmlColor(v3));
                goto label_200;
            }
            catch(IllegalArgumentException ) {
                v4 = "TtmlDecoder";
                v5 = new StringBuilder();
                v6_1 = "Failed parsing color value: ";
            }

        label_168:
            v5.append(v6_1);
            v5.append(v3);
            Log.w(v4, v5.toString());
        label_200:
        }

        return v2;
    }

    private String[] parseStyleIds(String arg2) {
        arg2 = arg2.trim();
        String[] v2 = arg2.isEmpty() ? new String[0] : Util.split(arg2, "\\s+");
        return v2;
    }

    private static long parseTimeExpression(String arg14, FrameAndTickRate arg15) {
        double v14;
        Matcher v0 = TtmlDecoder.CLOCK_TIME.matcher(((CharSequence)arg14));
        double v2 = 1000000;
        int v4 = 5;
        int v5 = 4;
        int v6 = 3;
        int v7 = 2;
        if(v0.matches()) {
            double v8 = ((double)(Long.parseLong(v0.group(1)) * 3600));
            double v10 = ((double)(Long.parseLong(v0.group(v7)) * 60));
            Double.isNaN(v8);
            Double.isNaN(v10);
            double v6_1 = ((double)Long.parseLong(v0.group(v6)));
            Double.isNaN(v6_1);
            v8 = v8 + v10 + v6_1;
            arg14 = v0.group(v5);
            double v5_1 = 0;
            v10 = arg14 != null ? Double.parseDouble(arg14) : v5_1;
            v8 += v10;
            arg14 = v0.group(v4);
            v10 = arg14 != null ? ((double)((((float)Long.parseLong(arg14))) / arg15.effectiveFrameRate)) : v5_1;
            v8 += v10;
            arg14 = v0.group(6);
            if(arg14 != null) {
                double v0_1 = ((double)Long.parseLong(arg14));
                double v4_1 = ((double)arg15.subFrameRate);
                Double.isNaN(v0_1);
                Double.isNaN(v4_1);
                v14 = ((double)arg15.effectiveFrameRate);
                Double.isNaN(v14);
                v5_1 = v0_1 / v4_1 / v14;
            }

            return ((long)((v8 + v5_1) * v2));
        }

        v0 = TtmlDecoder.OFFSET_TIME.matcher(((CharSequence)arg14));
        if(!v0.matches()) {
            goto label_134;
        }

        double v9 = Double.parseDouble(v0.group(1));
        arg14 = v0.group(v7);
        int v1 = arg14.hashCode();
        if(v1 != 102) {
            if(v1 != 104) {
                if(v1 != 109) {
                    if(v1 != 3494) {
                        switch(v1) {
                            case 115: {
                                goto label_89;
                            }
                            case 116: {
                                goto label_85;
                            }
                        }

                        goto label_114;
                    label_85:
                        if(arg14.equals("t")) {
                            goto label_115;
                        label_89:
                            if(arg14.equals("s")) {
                                v4 = 2;
                            }
                            else {
                                goto label_114;
                            }
                        }
                        else {
                            goto label_114;
                        }
                    }
                    else if(arg14.equals("ms")) {
                        v4 = 3;
                    }
                    else {
                        goto label_114;
                    }
                }
                else if(arg14.equals("m")) {
                    v4 = 1;
                }
                else {
                    goto label_114;
                }
            }
            else if(arg14.equals("h")) {
                v4 = 0;
            }
            else {
                goto label_114;
            }
        }
        else if(arg14.equals("f")) {
            v4 = 4;
        }
        else {
        label_114:
            v4 = -1;
        }

    label_115:
        switch(v4) {
            case 0: {
                goto label_129;
            }
            case 1: {
                goto label_127;
            }
            case 3: {
                goto label_124;
            }
            case 4: {
                goto label_120;
            }
            case 5: {
                goto label_117;
            }
        }

        goto label_131;
    label_129:
        v14 = 3600;
        goto label_130;
    label_117:
        v14 = ((double)arg15.tickRate);
        goto label_122;
    label_120:
        v14 = ((double)arg15.effectiveFrameRate);
    label_122:
        Double.isNaN(v14);
        goto label_125;
    label_124:
        v14 = 1000;
    label_125:
        v9 /= v14;
        goto label_131;
    label_127:
        v14 = 60;
    label_130:
        v9 *= v14;
    label_131:
        return ((long)(v9 * v2));
    label_134:
        StringBuilder v0_2 = new StringBuilder();
        v0_2.append("Malformed time expression: ");
        v0_2.append(arg14);
        throw new SubtitleDecoderException(v0_2.toString());
    }
}

