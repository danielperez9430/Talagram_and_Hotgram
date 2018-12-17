package com.google.android.exoplayer2.text.webvtt;

import android.text.TextUtils;
import com.google.android.exoplayer2.util.ColorParser;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

final class CssParser {
    private static final String BLOCK_END = "}";
    private static final String BLOCK_START = "{";
    private static final String PROPERTY_BGCOLOR = "background-color";
    private static final String PROPERTY_FONT_FAMILY = "font-family";
    private static final String PROPERTY_FONT_STYLE = "font-style";
    private static final String PROPERTY_FONT_WEIGHT = "font-weight";
    private static final String PROPERTY_TEXT_DECORATION = "text-decoration";
    private static final String VALUE_BOLD = "bold";
    private static final String VALUE_ITALIC = "italic";
    private static final String VALUE_UNDERLINE = "underline";
    private static final Pattern VOICE_NAME_PATTERN;
    private final StringBuilder stringBuilder;
    private final ParsableByteArray styleInput;

    static {
        CssParser.VOICE_NAME_PATTERN = Pattern.compile("\\[voice=\"([^\"]*)\"\\]");
    }

    public CssParser() {
        super();
        this.styleInput = new ParsableByteArray();
        this.stringBuilder = new StringBuilder();
    }

    private void applySelectorToStyle(WebvttCssStyle arg7, String arg8) {
        if("".equals(arg8)) {
            return;
        }

        int v0 = arg8.indexOf(91);
        int v1 = -1;
        if(v0 != v1) {
            Matcher v4 = CssParser.VOICE_NAME_PATTERN.matcher(arg8.substring(v0));
            if(v4.matches()) {
                arg7.setTargetVoice(v4.group(1));
            }

            arg8 = arg8.substring(0, v0);
        }

        String[] v8 = Util.split(arg8, "\\.");
        String v0_1 = v8[0];
        int v4_1 = v0_1.indexOf(35);
        if(v4_1 != v1) {
            arg7.setTargetTagName(v0_1.substring(0, v4_1));
            arg7.setTargetId(v0_1.substring(v4_1 + 1));
        }
        else {
            arg7.setTargetTagName(v0_1);
        }

        if(v8.length > 1) {
            arg7.setTargetClasses(Arrays.copyOfRange(((Object[])v8), 1, v8.length));
        }
    }

    private static boolean maybeSkipComment(ParsableByteArray arg6) {
        int v0 = arg6.getPosition();
        int v1 = arg6.limit();
        byte[] v2 = arg6.data;
        if(v0 + 2 <= v1) {
            int v3 = v0 + 1;
            int v4 = 47;
            if(v2[v0] == v4) {
                v0 = v3 + 1;
                int v5 = 42;
                if(v2[v3] != v5) {
                    return 0;
                }

                while(true) {
                    v3 = v0 + 1;
                    if(v3 >= v1) {
                        break;
                    }

                    if((((char)v2[v0])) == v5 && (((char)v2[v3])) == v4) {
                        v0 = v3 + 1;
                        v1 = v0;
                        continue;
                    }

                    v0 = v3;
                }

                arg6.skipBytes(v1 - arg6.getPosition());
                return 1;
            }
        }

        return 0;
    }

    private static boolean maybeSkipWhitespace(ParsableByteArray arg1) {
        switch(CssParser.peekCharAtPosition(arg1, arg1.getPosition())) {
            case 9: 
            case 10: 
            case 12: 
            case 13: 
            case 32: {
                goto label_5;
            }
        }

        return 0;
    label_5:
        arg1.skipBytes(1);
        return 1;
    }

    public WebvttCssStyle parseBlock(ParsableByteArray arg7) {
        this.stringBuilder.setLength(0);
        int v0 = arg7.getPosition();
        CssParser.skipStyleBlock(arg7);
        this.styleInput.reset(arg7.data, arg7.getPosition());
        this.styleInput.setPosition(v0);
        String v7 = CssParser.parseSelector(this.styleInput, this.stringBuilder);
        WebvttCssStyle v0_1 = null;
        if(v7 != null) {
            if(!"{".equals(CssParser.parseNextToken(this.styleInput, this.stringBuilder))) {
            }
            else {
                WebvttCssStyle v2 = new WebvttCssStyle();
                this.applySelectorToStyle(v2, v7);
                Object v3 = v0_1;
                int v7_1;
                for(v7_1 = 0; v7_1 == 0; v7_1 = v4) {
                    v7_1 = this.styleInput.getPosition();
                    String v3_1 = CssParser.parseNextToken(this.styleInput, this.stringBuilder);
                    int v4 = v3_1 == null || ("}".equals(v3_1)) ? 1 : 0;
                    if(v4 == 0) {
                        this.styleInput.setPosition(v7_1);
                        CssParser.parseStyleDeclaration(this.styleInput, v2, this.stringBuilder);
                    }
                }

                if(!"}".equals(v3)) {
                    return v0_1;
                }

                v0_1 = v2;
            }
        }

        return v0_1;
    }

    private static String parseIdentifier(ParsableByteArray arg5, StringBuilder arg6) {
        int v0 = 0;
        arg6.setLength(0);
        int v1 = arg5.getPosition();
        int v2 = arg5.limit();
        while(v1 < v2) {
            if(v0 != 0) {
                break;
            }

            char v3 = ((char)arg5.data[v1]);
            if((v3 < 65 || v3 > 90) && (v3 < 97 || v3 > 122) && (v3 < 48 || v3 > 57) && v3 != 35 && v3 != 45 && v3 != 46 && v3 != 95) {
                v0 = 1;
                continue;
            }

            ++v1;
            arg6.append(v3);
        }

        arg5.skipBytes(v1 - arg5.getPosition());
        return arg6.toString();
    }

    static String parseNextToken(ParsableByteArray arg1, StringBuilder arg2) {
        CssParser.skipWhitespaceAndComments(arg1);
        if(arg1.bytesLeft() == 0) {
            return null;
        }

        String v2 = CssParser.parseIdentifier(arg1, arg2);
        if(!"".equals(v2)) {
            return v2;
        }

        return "" + (((char)arg1.readUnsignedByte()));
    }

    private static String parsePropertyValue(ParsableByteArray arg5, StringBuilder arg6) {
        StringBuilder v0 = new StringBuilder();
        int v1;
        for(v1 = 0; v1 == 0; v1 = 1) {
            int v2 = arg5.getPosition();
            String v3 = CssParser.parseNextToken(arg5, arg6);
            if(v3 == null) {
                return null;
            }

            if(!"}".equals(v3)) {
                if(";".equals(v3)) {
                }
                else {
                    v0.append(v3);
                    continue;
                }
            }

            arg5.setPosition(v2);
        }

        return v0.toString();
    }

    private static String parseSelector(ParsableByteArray arg4, StringBuilder arg5) {
        CssParser.skipWhitespaceAndComments(arg4);
        int v1 = 5;
        String v2 = null;
        if(arg4.bytesLeft() < v1) {
            return v2;
        }

        if(!"::cue".equals(arg4.readString(v1))) {
            return v2;
        }

        int v0 = arg4.getPosition();
        String v1_1 = CssParser.parseNextToken(arg4, arg5);
        if(v1_1 == null) {
            return v2;
        }

        if("{".equals(v1_1)) {
            arg4.setPosition(v0);
            return "";
        }

        String v0_1 = "(".equals(v1_1) ? CssParser.readCueTarget(arg4) : v2;
        String v4 = CssParser.parseNextToken(arg4, arg5);
        if(")".equals(v4)) {
            if(v4 == null) {
            }
            else {
                return v0_1;
            }
        }

        return v2;
    }

    private static void parseStyleDeclaration(ParsableByteArray arg4, WebvttCssStyle arg5, StringBuilder arg6) {
        CssParser.skipWhitespaceAndComments(arg4);
        String v0 = CssParser.parseIdentifier(arg4, arg6);
        if("".equals(v0)) {
            return;
        }

        if(!":".equals(CssParser.parseNextToken(arg4, arg6))) {
            return;
        }

        CssParser.skipWhitespaceAndComments(arg4);
        String v1 = CssParser.parsePropertyValue(arg4, arg6);
        if(v1 != null) {
            if("".equals(v1)) {
            }
            else {
                int v2 = arg4.getPosition();
                String v6 = CssParser.parseNextToken(arg4, arg6);
                if(";".equals(v6)) {
                }
                else if("}".equals(v6)) {
                    arg4.setPosition(v2);
                }
                else {
                    return;
                }

                if("color".equals(v0)) {
                    arg5.setFontColor(ColorParser.parseCssColor(v1));
                    return;
                }

                if("background-color".equals(v0)) {
                    arg5.setBackgroundColor(ColorParser.parseCssColor(v1));
                    return;
                }

                if("text-decoration".equals(v0)) {
                    if(!"underline".equals(v1)) {
                        return;
                    }

                    arg5.setUnderline(true);
                    return;
                }

                if("font-family".equals(v0)) {
                    arg5.setFontFamily(v1);
                    return;
                }

                if("font-weight".equals(v0)) {
                    if(!"bold".equals(v1)) {
                        return;
                    }

                    arg5.setBold(true);
                    return;
                }

                if(!"font-style".equals(v0)) {
                    return;
                }

                if(!"italic".equals(v1)) {
                    return;
                }

                arg5.setItalic(true);
            }
        }
    }

    private static char peekCharAtPosition(ParsableByteArray arg0, int arg1) {
        return ((char)arg0.data[arg1]);
    }

    private static String readCueTarget(ParsableByteArray arg5) {
        int v0 = arg5.getPosition();
        int v1 = arg5.limit();
        int v3 = 0;
        while(v0 < v1) {
            if(v3 != 0) {
                break;
            }

            int v4 = v0 + 1;
            v3 = (((char)arg5.data[v0])) == 41 ? 1 : 0;
            v0 = v4;
        }

        return arg5.readString(v0 - 1 - arg5.getPosition()).trim();
    }

    static void skipStyleBlock(ParsableByteArray arg1) {
        do {
        }
        while(!TextUtils.isEmpty(arg1.readLine()));
    }

    static void skipWhitespaceAndComments(ParsableByteArray arg3) {
        int v1;
        while(true) {
        label_1:
            v1 = 1;
            while(true) {
            label_2:
                if(arg3.bytesLeft() > 0 && v1 != 0) {
                    if(CssParser.maybeSkipWhitespace(arg3)) {
                        goto label_1;
                    }

                    if(CssParser.maybeSkipComment(arg3)) {
                        goto label_1;
                    }
                    else {
                        break;
                    }
                }

                return;
            }
        }

        v1 = 0;
        goto label_2;
    }
}

