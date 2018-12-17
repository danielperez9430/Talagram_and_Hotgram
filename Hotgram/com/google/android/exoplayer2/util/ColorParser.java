package com.google.android.exoplayer2.util;

import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class ColorParser {
    private static final Map COLOR_MAP = null;
    private static final String RGB = "rgb";
    private static final String RGBA = "rgba";
    private static final Pattern RGBA_PATTERN_FLOAT_ALPHA;
    private static final Pattern RGBA_PATTERN_INT_ALPHA;
    private static final Pattern RGB_PATTERN;

    static {
        ColorParser.RGB_PATTERN = Pattern.compile("^rgb\\((\\d{1,3}),(\\d{1,3}),(\\d{1,3})\\)$");
        ColorParser.RGBA_PATTERN_INT_ALPHA = Pattern.compile("^rgba\\((\\d{1,3}),(\\d{1,3}),(\\d{1,3}),(\\d{1,3})\\)$");
        ColorParser.RGBA_PATTERN_FLOAT_ALPHA = Pattern.compile("^rgba\\((\\d{1,3}),(\\d{1,3}),(\\d{1,3}),(\\d*\\.?\\d*?)\\)$");
        ColorParser.COLOR_MAP = new HashMap();
        ColorParser.COLOR_MAP.put("aliceblue", Integer.valueOf(-984833));
        ColorParser.COLOR_MAP.put("antiquewhite", Integer.valueOf(-332841));
        ColorParser.COLOR_MAP.put("aqua", Integer.valueOf(-16711681));
        ColorParser.COLOR_MAP.put("aquamarine", Integer.valueOf(-8388652));
        ColorParser.COLOR_MAP.put("azure", Integer.valueOf(-983041));
        ColorParser.COLOR_MAP.put("beige", Integer.valueOf(-657956));
        ColorParser.COLOR_MAP.put("bisque", Integer.valueOf(-6972));
        ColorParser.COLOR_MAP.put("black", Integer.valueOf(-16777216));
        ColorParser.COLOR_MAP.put("blanchedalmond", Integer.valueOf(-5171));
        ColorParser.COLOR_MAP.put("blue", Integer.valueOf(-16776961));
        ColorParser.COLOR_MAP.put("blueviolet", Integer.valueOf(-7722014));
        ColorParser.COLOR_MAP.put("brown", Integer.valueOf(-5952982));
        ColorParser.COLOR_MAP.put("burlywood", Integer.valueOf(-2180985));
        ColorParser.COLOR_MAP.put("cadetblue", Integer.valueOf(-10510688));
        ColorParser.COLOR_MAP.put("chartreuse", Integer.valueOf(-8388864));
        ColorParser.COLOR_MAP.put("chocolate", Integer.valueOf(-2987746));
        ColorParser.COLOR_MAP.put("coral", Integer.valueOf(-32944));
        ColorParser.COLOR_MAP.put("cornflowerblue", Integer.valueOf(-10185235));
        ColorParser.COLOR_MAP.put("cornsilk", Integer.valueOf(-1828));
        ColorParser.COLOR_MAP.put("crimson", Integer.valueOf(-2354116));
        ColorParser.COLOR_MAP.put("cyan", Integer.valueOf(-16711681));
        ColorParser.COLOR_MAP.put("darkblue", Integer.valueOf(-16777077));
        ColorParser.COLOR_MAP.put("darkcyan", Integer.valueOf(-16741493));
        ColorParser.COLOR_MAP.put("darkgoldenrod", Integer.valueOf(-4684277));
        ColorParser.COLOR_MAP.put("darkgray", Integer.valueOf(-5658199));
        ColorParser.COLOR_MAP.put("darkgreen", Integer.valueOf(-16751616));
        ColorParser.COLOR_MAP.put("darkgrey", Integer.valueOf(-5658199));
        ColorParser.COLOR_MAP.put("darkkhaki", Integer.valueOf(-4343957));
        ColorParser.COLOR_MAP.put("darkmagenta", Integer.valueOf(-7667573));
        ColorParser.COLOR_MAP.put("darkolivegreen", Integer.valueOf(-11179217));
        ColorParser.COLOR_MAP.put("darkorange", Integer.valueOf(-29696));
        ColorParser.COLOR_MAP.put("darkorchid", Integer.valueOf(-6737204));
        ColorParser.COLOR_MAP.put("darkred", Integer.valueOf(-7667712));
        ColorParser.COLOR_MAP.put("darksalmon", Integer.valueOf(-1468806));
        ColorParser.COLOR_MAP.put("darkseagreen", Integer.valueOf(-7357297));
        ColorParser.COLOR_MAP.put("darkslateblue", Integer.valueOf(-12042869));
        ColorParser.COLOR_MAP.put("darkslategray", Integer.valueOf(-13676721));
        ColorParser.COLOR_MAP.put("darkslategrey", Integer.valueOf(-13676721));
        ColorParser.COLOR_MAP.put("darkturquoise", Integer.valueOf(-16724271));
        ColorParser.COLOR_MAP.put("darkviolet", Integer.valueOf(-7077677));
        ColorParser.COLOR_MAP.put("deeppink", Integer.valueOf(-60269));
        ColorParser.COLOR_MAP.put("deepskyblue", Integer.valueOf(-16728065));
        ColorParser.COLOR_MAP.put("dimgray", Integer.valueOf(-9868951));
        ColorParser.COLOR_MAP.put("dimgrey", Integer.valueOf(-9868951));
        ColorParser.COLOR_MAP.put("dodgerblue", Integer.valueOf(-14774017));
        ColorParser.COLOR_MAP.put("firebrick", Integer.valueOf(-5103070));
        ColorParser.COLOR_MAP.put("floralwhite", Integer.valueOf(-1296));
        ColorParser.COLOR_MAP.put("forestgreen", Integer.valueOf(-14513374));
        ColorParser.COLOR_MAP.put("fuchsia", Integer.valueOf(-65281));
        ColorParser.COLOR_MAP.put("gainsboro", Integer.valueOf(-2302756));
        ColorParser.COLOR_MAP.put("ghostwhite", Integer.valueOf(-460545));
        ColorParser.COLOR_MAP.put("gold", Integer.valueOf(-10496));
        ColorParser.COLOR_MAP.put("goldenrod", Integer.valueOf(-2448096));
        ColorParser.COLOR_MAP.put("gray", Integer.valueOf(-8355712));
        ColorParser.COLOR_MAP.put("green", Integer.valueOf(-16744448));
        ColorParser.COLOR_MAP.put("greenyellow", Integer.valueOf(-5374161));
        ColorParser.COLOR_MAP.put("grey", Integer.valueOf(-8355712));
        ColorParser.COLOR_MAP.put("honeydew", Integer.valueOf(-983056));
        ColorParser.COLOR_MAP.put("hotpink", Integer.valueOf(-38476));
        ColorParser.COLOR_MAP.put("indianred", Integer.valueOf(-3318692));
        ColorParser.COLOR_MAP.put("indigo", Integer.valueOf(-11861886));
        ColorParser.COLOR_MAP.put("ivory", Integer.valueOf(-16));
        ColorParser.COLOR_MAP.put("khaki", Integer.valueOf(-989556));
        ColorParser.COLOR_MAP.put("lavender", Integer.valueOf(-1644806));
        ColorParser.COLOR_MAP.put("lavenderblush", Integer.valueOf(-3851));
        ColorParser.COLOR_MAP.put("lawngreen", Integer.valueOf(-8586240));
        ColorParser.COLOR_MAP.put("lemonchiffon", Integer.valueOf(-1331));
        ColorParser.COLOR_MAP.put("lightblue", Integer.valueOf(-5383962));
        ColorParser.COLOR_MAP.put("lightcoral", Integer.valueOf(-1015680));
        ColorParser.COLOR_MAP.put("lightcyan", Integer.valueOf(-2031617));
        ColorParser.COLOR_MAP.put("lightgoldenrodyellow", Integer.valueOf(-329006));
        ColorParser.COLOR_MAP.put("lightgray", Integer.valueOf(-2894893));
        ColorParser.COLOR_MAP.put("lightgreen", Integer.valueOf(-7278960));
        ColorParser.COLOR_MAP.put("lightgrey", Integer.valueOf(-2894893));
        ColorParser.COLOR_MAP.put("lightpink", Integer.valueOf(-18751));
        ColorParser.COLOR_MAP.put("lightsalmon", Integer.valueOf(-24454));
        ColorParser.COLOR_MAP.put("lightseagreen", Integer.valueOf(-14634326));
        ColorParser.COLOR_MAP.put("lightskyblue", Integer.valueOf(-7876870));
        ColorParser.COLOR_MAP.put("lightslategray", Integer.valueOf(-8943463));
        ColorParser.COLOR_MAP.put("lightslategrey", Integer.valueOf(-8943463));
        ColorParser.COLOR_MAP.put("lightsteelblue", Integer.valueOf(-5192482));
        ColorParser.COLOR_MAP.put("lightyellow", Integer.valueOf(-32));
        ColorParser.COLOR_MAP.put("lime", Integer.valueOf(-16711936));
        ColorParser.COLOR_MAP.put("limegreen", Integer.valueOf(-13447886));
        ColorParser.COLOR_MAP.put("linen", Integer.valueOf(-331546));
        ColorParser.COLOR_MAP.put("magenta", Integer.valueOf(-65281));
        ColorParser.COLOR_MAP.put("maroon", Integer.valueOf(-8388608));
        ColorParser.COLOR_MAP.put("mediumaquamarine", Integer.valueOf(-10039894));
        ColorParser.COLOR_MAP.put("mediumblue", Integer.valueOf(-16777011));
        ColorParser.COLOR_MAP.put("mediumorchid", Integer.valueOf(-4565549));
        ColorParser.COLOR_MAP.put("mediumpurple", Integer.valueOf(-7114533));
        ColorParser.COLOR_MAP.put("mediumseagreen", Integer.valueOf(-12799119));
        ColorParser.COLOR_MAP.put("mediumslateblue", Integer.valueOf(-8689426));
        ColorParser.COLOR_MAP.put("mediumspringgreen", Integer.valueOf(-16713062));
        ColorParser.COLOR_MAP.put("mediumturquoise", Integer.valueOf(-12004916));
        ColorParser.COLOR_MAP.put("mediumvioletred", Integer.valueOf(-3730043));
        ColorParser.COLOR_MAP.put("midnightblue", Integer.valueOf(-15132304));
        ColorParser.COLOR_MAP.put("mintcream", Integer.valueOf(-655366));
        ColorParser.COLOR_MAP.put("mistyrose", Integer.valueOf(-6943));
        ColorParser.COLOR_MAP.put("moccasin", Integer.valueOf(-6987));
        ColorParser.COLOR_MAP.put("navajowhite", Integer.valueOf(-8531));
        ColorParser.COLOR_MAP.put("navy", Integer.valueOf(-16777088));
        ColorParser.COLOR_MAP.put("oldlace", Integer.valueOf(-133658));
        ColorParser.COLOR_MAP.put("olive", Integer.valueOf(-8355840));
        ColorParser.COLOR_MAP.put("olivedrab", Integer.valueOf(-9728477));
        ColorParser.COLOR_MAP.put("orange", Integer.valueOf(-23296));
        ColorParser.COLOR_MAP.put("orangered", Integer.valueOf(-47872));
        ColorParser.COLOR_MAP.put("orchid", Integer.valueOf(-2461482));
        ColorParser.COLOR_MAP.put("palegoldenrod", Integer.valueOf(-1120086));
        ColorParser.COLOR_MAP.put("palegreen", Integer.valueOf(-6751336));
        ColorParser.COLOR_MAP.put("paleturquoise", Integer.valueOf(-5247250));
        ColorParser.COLOR_MAP.put("palevioletred", Integer.valueOf(-2396013));
        ColorParser.COLOR_MAP.put("papayawhip", Integer.valueOf(-4139));
        ColorParser.COLOR_MAP.put("peachpuff", Integer.valueOf(-9543));
        ColorParser.COLOR_MAP.put("peru", Integer.valueOf(-3308225));
        ColorParser.COLOR_MAP.put("pink", Integer.valueOf(-16181));
        ColorParser.COLOR_MAP.put("plum", Integer.valueOf(-2252579));
        ColorParser.COLOR_MAP.put("powderblue", Integer.valueOf(-5185306));
        ColorParser.COLOR_MAP.put("purple", Integer.valueOf(-8388480));
        ColorParser.COLOR_MAP.put("rebeccapurple", Integer.valueOf(-10079335));
        ColorParser.COLOR_MAP.put("red", Integer.valueOf(-65536));
        ColorParser.COLOR_MAP.put("rosybrown", Integer.valueOf(-4419697));
        ColorParser.COLOR_MAP.put("royalblue", Integer.valueOf(-12490271));
        ColorParser.COLOR_MAP.put("saddlebrown", Integer.valueOf(-7650029));
        ColorParser.COLOR_MAP.put("salmon", Integer.valueOf(-360334));
        ColorParser.COLOR_MAP.put("sandybrown", Integer.valueOf(-744352));
        ColorParser.COLOR_MAP.put("seagreen", Integer.valueOf(-13726889));
        ColorParser.COLOR_MAP.put("seashell", Integer.valueOf(-2578));
        ColorParser.COLOR_MAP.put("sienna", Integer.valueOf(-6270419));
        ColorParser.COLOR_MAP.put("silver", Integer.valueOf(-4144960));
        ColorParser.COLOR_MAP.put("skyblue", Integer.valueOf(-7876885));
        ColorParser.COLOR_MAP.put("slateblue", Integer.valueOf(-9807155));
        ColorParser.COLOR_MAP.put("slategray", Integer.valueOf(-9404272));
        ColorParser.COLOR_MAP.put("slategrey", Integer.valueOf(-9404272));
        ColorParser.COLOR_MAP.put("snow", Integer.valueOf(-1286));
        ColorParser.COLOR_MAP.put("springgreen", Integer.valueOf(-16711809));
        ColorParser.COLOR_MAP.put("steelblue", Integer.valueOf(-12156236));
        ColorParser.COLOR_MAP.put("tan", Integer.valueOf(-2968436));
        ColorParser.COLOR_MAP.put("teal", Integer.valueOf(-16744320));
        ColorParser.COLOR_MAP.put("thistle", Integer.valueOf(-2572328));
        ColorParser.COLOR_MAP.put("tomato", Integer.valueOf(-40121));
        ColorParser.COLOR_MAP.put("transparent", Integer.valueOf(0));
        ColorParser.COLOR_MAP.put("turquoise", Integer.valueOf(-12525360));
        ColorParser.COLOR_MAP.put("violet", Integer.valueOf(-1146130));
        ColorParser.COLOR_MAP.put("wheat", Integer.valueOf(-663885));
        ColorParser.COLOR_MAP.put("white", Integer.valueOf(-1));
        ColorParser.COLOR_MAP.put("whitesmoke", Integer.valueOf(-657931));
        ColorParser.COLOR_MAP.put("yellow", Integer.valueOf(-256));
        ColorParser.COLOR_MAP.put("yellowgreen", Integer.valueOf(-6632142));
    }

    private ColorParser() {
        super();
    }

    private static int argb(int arg0, int arg1, int arg2, int arg3) {
        return arg0 << 24 | arg1 << 16 | arg2 << 8 | arg3;
    }

    private static int parseColorInternal(String arg5, boolean arg6) {
        Matcher v5_1;
        int v5;
        Assertions.checkArgument(TextUtils.isEmpty(((CharSequence)arg5)) ^ 1);
        arg5 = arg5.replace(" ", "");
        if(arg5.charAt(0) != 35) {
            goto label_32;
        }

        int v6 = ((int)Long.parseLong(arg5.substring(1), 16));
        if(arg5.length() == 7) {
            v5 = -16777216 | v6;
        }
        else if(arg5.length() == 9) {
            v5 = (v6 & 255) << 24 | v6 >>> 8;
        }
        else {
            goto label_29;
        }

        return v5;
    label_29:
        throw new IllegalArgumentException();
    label_32:
        int v2 = 3;
        int v3 = 2;
        int v4 = 10;
        if(arg5.startsWith("rgba")) {
            Pattern v0 = arg6 ? ColorParser.RGBA_PATTERN_FLOAT_ALPHA : ColorParser.RGBA_PATTERN_INT_ALPHA;
            v5_1 = v0.matcher(((CharSequence)arg5));
            if(!v5_1.matches()) {
                goto label_84;
            }

            int v0_1 = 4;
            v6 = arg6 ? ((int)(Float.parseFloat(v5_1.group(v0_1)) * 255f)) : Integer.parseInt(v5_1.group(v0_1), v4);
            return ColorParser.argb(v6, Integer.parseInt(v5_1.group(1), v4), Integer.parseInt(v5_1.group(v3), v4), Integer.parseInt(v5_1.group(v2), v4));
        }
        else {
            if(arg5.startsWith("rgb")) {
                v5_1 = ColorParser.RGB_PATTERN.matcher(((CharSequence)arg5));
                if(!v5_1.matches()) {
                    goto label_84;
                }

                return ColorParser.rgb(Integer.parseInt(v5_1.group(1), v4), Integer.parseInt(v5_1.group(v3), v4), Integer.parseInt(v5_1.group(v2), v4));
            }

            Object v5_2 = ColorParser.COLOR_MAP.get(Util.toLowerInvariant(arg5));
            if(v5_2 == null) {
                goto label_84;
            }

            return ((Integer)v5_2).intValue();
        }

    label_84:
        throw new IllegalArgumentException();
    }

    public static int parseCssColor(String arg1) {
        return ColorParser.parseColorInternal(arg1, true);
    }

    public static int parseTtmlColor(String arg1) {
        return ColorParser.parseColorInternal(arg1, false);
    }

    private static int rgb(int arg1, int arg2, int arg3) {
        return ColorParser.argb(255, arg1, arg2, arg3);
    }
}

