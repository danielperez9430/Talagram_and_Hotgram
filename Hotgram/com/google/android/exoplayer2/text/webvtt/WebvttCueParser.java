package com.google.android.exoplayer2.text.webvtt;

import android.text.Layout$Alignment;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.AlignmentSpan$Standard;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.TypefaceSpan;
import android.text.style.UnderlineSpan;
import android.util.Log;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class WebvttCueParser {
    final class StartTag {
        private static final String[] NO_CLASSES;
        public final String[] classes;
        public final String name;
        public final int position;
        public final String voice;

        static {
            StartTag.NO_CLASSES = new String[0];
        }

        private StartTag(String arg1, int arg2, String arg3, String[] arg4) {
            super();
            this.position = arg2;
            this.name = arg1;
            this.voice = arg3;
            this.classes = arg4;
        }

        public static StartTag buildStartTag(String arg4, int arg5) {
            Object[] v4_1;
            String v1;
            String v0_1;
            arg4 = arg4.trim();
            if(arg4.isEmpty()) {
                return null;
            }

            int v0 = arg4.indexOf(" ");
            if(v0 == -1) {
                v0_1 = "";
            }
            else {
                v1 = arg4.substring(v0).trim();
                arg4 = arg4.substring(0, v0);
                v0_1 = v1;
            }

            String[] v4 = Util.split(arg4, "\\.");
            v1 = v4[0];
            if(v4.length > 1) {
                v4_1 = Arrays.copyOfRange(((Object[])v4), 1, v4.length);
            }
            else {
                v4 = StartTag.NO_CLASSES;
            }

            return new StartTag(v1, arg5, v0_1, ((String[])v4_1));
        }

        public static StartTag buildWholeCueVirtualTag() {
            return new StartTag("", 0, "", new String[0]);
        }
    }

    final class StyleMatch implements Comparable {
        public final int score;
        public final WebvttCssStyle style;

        public StyleMatch(int arg1, WebvttCssStyle arg2) {
            super();
            this.score = arg1;
            this.style = arg2;
        }

        public int compareTo(StyleMatch arg2) {
            return this.score - arg2.score;
        }

        public int compareTo(Object arg1) {
            return this.compareTo(((StyleMatch)arg1));
        }
    }

    private static final char CHAR_AMPERSAND = '&';
    private static final char CHAR_GREATER_THAN = '>';
    private static final char CHAR_LESS_THAN = '<';
    private static final char CHAR_SEMI_COLON = ';';
    private static final char CHAR_SLASH = '/';
    private static final char CHAR_SPACE = ' ';
    public static final Pattern CUE_HEADER_PATTERN = null;
    private static final Pattern CUE_SETTING_PATTERN = null;
    private static final String ENTITY_AMPERSAND = "amp";
    private static final String ENTITY_GREATER_THAN = "gt";
    private static final String ENTITY_LESS_THAN = "lt";
    private static final String ENTITY_NON_BREAK_SPACE = "nbsp";
    private static final int STYLE_BOLD = 1;
    private static final int STYLE_ITALIC = 2;
    private static final String TAG = "WebvttCueParser";
    private static final String TAG_BOLD = "b";
    private static final String TAG_CLASS = "c";
    private static final String TAG_ITALIC = "i";
    private static final String TAG_LANG = "lang";
    private static final String TAG_UNDERLINE = "u";
    private static final String TAG_VOICE = "v";
    private final StringBuilder textBuilder;

    static {
        WebvttCueParser.CUE_HEADER_PATTERN = Pattern.compile("^(\\S+)\\s+-->\\s+(\\S+)(.*)?$");
        WebvttCueParser.CUE_SETTING_PATTERN = Pattern.compile("(\\S+?):(\\S+)");
    }

    public WebvttCueParser() {
        super();
        this.textBuilder = new StringBuilder();
    }

    private static void applyEntity(String arg2, SpannableStringBuilder arg3) {
        int v0 = arg2.hashCode();
        if(v0 != 3309) {
            if(v0 != 3464) {
                if(v0 != 96708) {
                    if(v0 != 3374865) {
                        goto label_30;
                    }
                    else if(arg2.equals("nbsp")) {
                        v0 = 2;
                    }
                    else {
                        goto label_30;
                    }
                }
                else if(arg2.equals("amp")) {
                    v0 = 3;
                }
                else {
                    goto label_30;
                }
            }
            else if(arg2.equals("lt")) {
                v0 = 0;
            }
            else {
                goto label_30;
            }
        }
        else if(arg2.equals("gt")) {
            v0 = 1;
        }
        else {
        label_30:
            v0 = -1;
        }

        switch(v0) {
            case 0: {
                goto label_49;
            }
            case 1: {
                goto label_47;
            }
            case 2: {
                goto label_45;
            }
            case 3: {
                goto label_43;
            }
        }

        Log.w("WebvttCueParser", "ignoring unsupported entity: \'&" + arg2 + ";\'");
        return;
    label_49:
        char v2 = '<';
        goto label_50;
    label_43:
        v2 = '&';
        goto label_50;
    label_45:
        v2 = ' ';
        goto label_50;
    label_47:
        v2 = '>';
    label_50:
        arg3.append(v2);
    }

    private static void applySpansForTag(String arg7, StartTag arg8, SpannableStringBuilder arg9, List arg10, List arg11) {
        int v2_1;
        int v0 = arg8.position;
        int v1 = arg9.length();
        String v2 = arg8.name;
        int v4 = 0;
        int v5 = 2;
        switch(v2.hashCode()) {
            case 0: {
                if(!v2.equals("")) {
                    goto label_44;
                }

                v2_1 = 6;
                break;
            }
            case 98: {
                if(!v2.equals("b")) {
                    goto label_44;
                }

                v2_1 = 0;
                break;
            }
            case 99: {
                if(!v2.equals("c")) {
                    goto label_44;
                }

                v2_1 = 3;
                break;
            }
            case 105: {
                if(!v2.equals("i")) {
                    goto label_44;
                }

                v2_1 = 1;
                break;
            }
            case 117: {
                if(!v2.equals("u")) {
                    goto label_44;
                }

                v2_1 = 2;
                break;
            }
            case 118: {
                if(v2.equals("v")) {
                    v2_1 = 5;
                    goto label_45;
                }

            label_44:
                v2_1 = -1;
                break;
            }
            case 3314158: {
                if(!v2.equals("lang")) {
                    goto label_44;
                }

                v2_1 = 4;
                break;
            }
            default: {
                goto label_44;
            }
        }

    label_45:
        int v3 = 33;
        switch(v2_1) {
            case 0: {
                goto label_54;
            }
            case 1: {
                goto label_51;
            }
            case 2: {
                goto label_48;
            }
            case 3: 
            case 4: 
            case 5: 
            case 6: {
                goto label_57;
            }
        }

        return;
    label_51:
        StyleSpan v2_2 = new StyleSpan(v5);
        goto label_56;
    label_54:
        v2_2 = new StyleSpan(1);
        goto label_56;
    label_48:
        UnderlineSpan v2_3 = new UnderlineSpan();
    label_56:
        arg9.setSpan(v2_2, v0, v1, v3);
    label_57:
        arg11.clear();
        WebvttCueParser.getApplicableStyles(arg10, arg7, arg8, arg11);
        int v7 = arg11.size();
        while(v4 < v7) {
            WebvttCueParser.applyStyleToText(arg9, arg11.get(v4).style, v0, v1);
            ++v4;
        }
    }

    private static void applyStyleToText(SpannableStringBuilder arg3, WebvttCssStyle arg4, int arg5, int arg6) {
        if(arg4 == null) {
            return;
        }

        int v2 = 33;
        if(arg4.getStyle() != -1) {
            arg3.setSpan(new StyleSpan(arg4.getStyle()), arg5, arg6, v2);
        }

        if(arg4.isLinethrough()) {
            arg3.setSpan(new StrikethroughSpan(), arg5, arg6, v2);
        }

        if(arg4.isUnderline()) {
            arg3.setSpan(new UnderlineSpan(), arg5, arg6, v2);
        }

        if(arg4.hasFontColor()) {
            arg3.setSpan(new ForegroundColorSpan(arg4.getFontColor()), arg5, arg6, v2);
        }

        if(arg4.hasBackgroundColor()) {
            arg3.setSpan(new BackgroundColorSpan(arg4.getBackgroundColor()), arg5, arg6, v2);
        }

        if(arg4.getFontFamily() != null) {
            arg3.setSpan(new TypefaceSpan(arg4.getFontFamily()), arg5, arg6, v2);
        }

        if(arg4.getTextAlign() != null) {
            arg3.setSpan(new AlignmentSpan$Standard(arg4.getTextAlign()), arg5, arg6, v2);
        }

        switch(arg4.getFontSizeUnit()) {
            case 1: {
                goto label_57;
            }
            case 2: {
                goto label_53;
            }
            case 3: {
                goto label_47;
            }
        }

        return;
    label_53:
        RelativeSizeSpan v0 = new RelativeSizeSpan(arg4.getFontSize());
        goto label_62;
    label_57:
        AbsoluteSizeSpan v0_1 = new AbsoluteSizeSpan(((int)arg4.getFontSize()), true);
        goto label_62;
    label_47:
        v0 = new RelativeSizeSpan(arg4.getFontSize() / 100f);
    label_62:
        arg3.setSpan(v0_1, arg5, arg6, v2);
    }

    private static int findEndOfTag(String arg1, int arg2) {
        arg2 = arg1.indexOf(62, arg2);
        int v1 = arg2 == -1 ? arg1.length() : arg2 + 1;
        return v1;
    }

    private static void getApplicableStyles(List arg6, String arg7, StartTag arg8, List arg9) {
        int v0 = arg6.size();
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            Object v2 = arg6.get(v1);
            int v3 = ((WebvttCssStyle)v2).getSpecificityScore(arg7, arg8.name, arg8.classes, arg8.voice);
            if(v3 > 0) {
                arg9.add(new StyleMatch(v3, ((WebvttCssStyle)v2)));
            }
        }

        Collections.sort(arg9);
    }

    private static String getTagName(String arg1) {
        arg1 = arg1.trim();
        if(arg1.isEmpty()) {
            return null;
        }

        return Util.splitAtFirst(arg1, "[ \\.]")[0];
    }

    private static boolean isSupportedTag(String arg3) {
        int v3;
        switch(arg3.hashCode()) {
            case 98: {
                if(!arg3.equals("b")) {
                    goto label_35;
                }

                v3 = 0;
                break;
            }
            case 99: {
                if(!arg3.equals("c")) {
                    goto label_35;
                }

                v3 = 1;
                break;
            }
            case 105: {
                if(!arg3.equals("i")) {
                    goto label_35;
                }

                v3 = 2;
                break;
            }
            case 117: {
                if(arg3.equals("u")) {
                    v3 = 4;
                    goto label_36;
                }

            label_35:
                v3 = -1;
                break;
            }
            case 118: {
                if(!arg3.equals("v")) {
                    goto label_35;
                }

                v3 = 5;
                break;
            }
            case 3314158: {
                if(!arg3.equals("lang")) {
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
            case 0: 
            case 1: 
            case 2: 
            case 3: 
            case 4: 
            case 5: {
                return 1;
            }
        }

        return 0;
    }

    private static boolean parseCue(String arg5, Matcher arg6, ParsableByteArray arg7, Builder arg8, StringBuilder arg9, List arg10) {
        try {
            arg8.setStartTime(WebvttParserUtil.parseTimestampUs(arg6.group(1))).setEndTime(WebvttParserUtil.parseTimestampUs(arg6.group(2)));
        }
        catch(NumberFormatException ) {
            Log.w("WebvttCueParser", "Skipping cue with bad header: " + arg6.group());
            return 0;
        }

        WebvttCueParser.parseCueSettingsList(arg6.group(3), arg8);
        arg9.setLength(0);
        while(true) {
            String v6 = arg7.readLine();
            if(TextUtils.isEmpty(((CharSequence)v6))) {
                break;
            }

            if(arg9.length() > 0) {
                arg9.append("\n");
            }

            arg9.append(v6.trim());
        }

        WebvttCueParser.parseCueText(arg5, arg9.toString(), arg8, arg10);
        return 1;
    }

    public boolean parseCue(ParsableByteArray arg11, Builder arg12, List arg13) {
        String v0 = arg11.readLine();
        if(v0 == null) {
            return 0;
        }

        Matcher v4 = WebvttCueParser.CUE_HEADER_PATTERN.matcher(((CharSequence)v0));
        if(v4.matches()) {
            return WebvttCueParser.parseCue(null, v4, arg11, arg12, this.textBuilder, arg13);
        }

        String v2 = arg11.readLine();
        if(v2 == null) {
            return 0;
        }

        Matcher v5 = WebvttCueParser.CUE_HEADER_PATTERN.matcher(((CharSequence)v2));
        if(v5.matches()) {
            return WebvttCueParser.parseCue(v0.trim(), v5, arg11, arg12, this.textBuilder, arg13);
        }

        return 0;
    }

    static void parseCueSettingsList(String arg5, Builder arg6) {
        Matcher v5 = WebvttCueParser.CUE_SETTING_PATTERN.matcher(((CharSequence)arg5));
        while(v5.find()) {
            String v0 = v5.group(1);
            String v1 = v5.group(2);
            try {
                if("line".equals(v0)) {
                    WebvttCueParser.parseLineAttribute(v1, arg6);
                    continue;
                }

                if("align".equals(v0)) {
                    arg6.setTextAlignment(WebvttCueParser.parseTextAlignment(v1));
                    continue;
                }

                if("position".equals(v0)) {
                    WebvttCueParser.parsePositionAttribute(v1, arg6);
                    continue;
                }

                if("size".equals(v0)) {
                    arg6.setWidth(WebvttParserUtil.parsePercentage(v1));
                    continue;
                }

                Log.w("WebvttCueParser", "Unknown cue setting " + v0 + ":" + v1);
            }
            catch(NumberFormatException ) {
                Log.w("WebvttCueParser", "Skipping bad cue setting: " + v5.group());
            }
        }
    }

    static void parseCueText(String arg11, String arg12, Builder arg13, List arg14) {
        int v6;
        int v8;
        int v7;
        int v5_1;
        SpannableStringBuilder v0 = new SpannableStringBuilder();
        ArrayDeque v1 = new ArrayDeque();
        ArrayList v2 = new ArrayList();
        int v4 = 0;
        while(true) {
        label_8:
            if(v4 < arg12.length()) {
                char v5 = arg12.charAt(v4);
                if(v5 == 38) {
                    ++v4;
                    v6 = arg12.indexOf(59, v4);
                    v7 = arg12.indexOf(32, v4);
                    v8 = -1;
                    if(v6 == v8) {
                        v6 = v7;
                    }
                    else if(v7 == v8) {
                    }
                    else {
                        v6 = Math.min(v6, v7);
                    }

                    if(v6 != v8) {
                        WebvttCueParser.applyEntity(arg12.substring(v4, v6), v0);
                        if(v6 == v7) {
                            v0.append(" ");
                        }

                        v4 = v6 + 1;
                        continue;
                    }

                    v0.append(v5);
                    continue;
                }
                else if(v5 != 60) {
                    v0.append(v5);
                    ++v4;
                    continue;
                }
                else {
                    v5_1 = v4 + 1;
                    if(v5_1 >= arg12.length()) {
                    }
                    else {
                        v7 = 47;
                        v8 = 1;
                        v6 = arg12.charAt(v5_1) == v7 ? 1 : 0;
                        v5_1 = WebvttCueParser.findEndOfTag(arg12, v5_1);
                        int v9 = v5_1 - 2;
                        v7 = arg12.charAt(v9) == v7 ? 1 : 0;
                        if(v6 != 0) {
                            v8 = 2;
                        }

                        v4 += v8;
                        if(v7 != 0) {
                        }
                        else {
                            v9 = v5_1 - 1;
                        }

                        String v4_1 = arg12.substring(v4, v9);
                        String v8_1 = WebvttCueParser.getTagName(v4_1);
                        if(v8_1 == null) {
                            goto label_62;
                        }

                        if(!WebvttCueParser.isSupportedTag(v8_1)) {
                            goto label_62;
                        }

                        if(v6 == 0) {
                            if(v7 == 0) {
                                v1.push(StartTag.buildStartTag(v4_1, v0.length()));
                            }
                            else {
                            }

                            goto label_62;
                        }

                        break;
                    }

                    goto label_62;
                }
            }

            goto label_87;
        }

        do {
            if(v1.isEmpty()) {
                break;
            }

            Object v4_2 = v1.pop();
            WebvttCueParser.applySpansForTag(arg11, ((StartTag)v4_2), v0, arg14, ((List)v2));
        }
        while(!((StartTag)v4_2).name.equals(v8_1));

    label_62:
        v4 = v5_1;
        goto label_8;
    label_87:
        while(!v1.isEmpty()) {
            WebvttCueParser.applySpansForTag(arg11, v1.pop(), v0, arg14, ((List)v2));
        }

        WebvttCueParser.applySpansForTag(arg11, StartTag.buildWholeCueVirtualTag(), v0, arg14, ((List)v2));
        arg13.setText(v0);
    }

    private static void parseLineAttribute(String arg3, Builder arg4) {
        int v0 = arg3.indexOf(44);
        if(v0 != -1) {
            arg4.setLineAnchor(WebvttCueParser.parsePositionAnchor(arg3.substring(v0 + 1)));
            arg3 = arg3.substring(0, v0);
        }
        else {
            arg4.setLineAnchor(-2147483648);
        }

        if(arg3.endsWith("%")) {
            arg4.setLine(WebvttParserUtil.parsePercentage(arg3)).setLineType(0);
        }
        else {
            int v3 = Integer.parseInt(arg3);
            if(v3 < 0) {
                --v3;
            }

            arg4.setLine(((float)v3)).setLineType(1);
        }
    }

    private static int parsePositionAnchor(String arg5) {
        int v0 = arg5.hashCode();
        int v2 = 2;
        if(v0 != -1364013995) {
            if(v0 != -1074341483) {
                if(v0 != 100571) {
                    if(v0 != 109757538) {
                        goto label_33;
                    }
                    else if(arg5.equals("start")) {
                        v0 = 0;
                    }
                    else {
                        goto label_33;
                    }
                }
                else if(arg5.equals("end")) {
                    v0 = 3;
                }
                else {
                    goto label_33;
                }
            }
            else if(arg5.equals("middle")) {
                v0 = 2;
            }
            else {
                goto label_33;
            }
        }
        else if(arg5.equals("center")) {
            v0 = 1;
        }
        else {
        label_33:
            v0 = -1;
        }

        switch(v0) {
            case 0: {
                return 0;
            }
            case 1: 
            case 2: {
                return 1;
            }
            case 3: {
                return v2;
            }
        }

        Log.w("WebvttCueParser", "Invalid anchor value: " + arg5);
        return -2147483648;
    }

    private static void parsePositionAttribute(String arg2, Builder arg3) {
        int v0 = arg2.indexOf(44);
        if(v0 != -1) {
            arg3.setPositionAnchor(WebvttCueParser.parsePositionAnchor(arg2.substring(v0 + 1)));
            arg2 = arg2.substring(0, v0);
        }
        else {
            arg3.setPositionAnchor(-2147483648);
        }

        arg3.setPosition(WebvttParserUtil.parsePercentage(arg2));
    }

    private static Layout$Alignment parseTextAlignment(String arg3) {
        int v0;
        switch(arg3.hashCode()) {
            case -1364013995: {
                if(!arg3.equals("center")) {
                    goto label_33;
                }

                v0 = 2;
                break;
            }
            case -1074341483: {
                if(!arg3.equals("middle")) {
                    goto label_33;
                }

                v0 = 3;
                break;
            }
            case 100571: {
                if(!arg3.equals("end")) {
                    goto label_33;
                }

                v0 = 4;
                break;
            }
            case 3317767: {
                if(arg3.equals("left")) {
                    v0 = 1;
                    goto label_34;
                }

            label_33:
                v0 = -1;
                break;
            }
            case 108511772: {
                if(!arg3.equals("right")) {
                    goto label_33;
                }

                v0 = 5;
                break;
            }
            case 109757538: {
                if(!arg3.equals("start")) {
                    goto label_33;
                }

                v0 = 0;
                break;
            }
            default: {
                goto label_33;
            }
        }

    label_34:
        switch(v0) {
            case 0: 
            case 1: {
                goto label_49;
            }
            case 2: 
            case 3: {
                goto label_47;
            }
            case 4: 
            case 5: {
                goto label_45;
            }
        }

        Log.w("WebvttCueParser", "Invalid alignment value: " + arg3);
        return null;
    label_49:
        return Layout$Alignment.ALIGN_NORMAL;
    label_45:
        return Layout$Alignment.ALIGN_OPPOSITE;
    label_47:
        return Layout$Alignment.ALIGN_CENTER;
    }
}

