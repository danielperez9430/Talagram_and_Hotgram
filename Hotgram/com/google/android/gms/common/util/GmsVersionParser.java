package com.google.android.gms.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class GmsVersionParser {
    public static final int UNKNOWN = -1;
    private static Pattern zzzy;

    static {
    }

    private GmsVersionParser() {
        super();
    }

    public static int parseBuildMajorVersion(int arg1) {
        int v0 = -1;
        if(arg1 == v0) {
            return v0;
        }

        return arg1 / 100000;
    }

    public static long parseBuildNumber(String arg4) {
        long v0 = -1;
        if(arg4 == null) {
            return v0;
        }

        Matcher v4 = GmsVersionParser.zzdc().matcher(((CharSequence)arg4));
        if(!v4.find()) {
            return v0;
        }

        arg4 = v4.group(2);
        try {
            return Long.parseLong(arg4);
        }
        catch(NumberFormatException ) {
            return v0;
        }
    }

    public static int parseBuildType(String arg4) {
        long v0 = GmsVersionParser.parseVariantCode(arg4);
        if(v0 == -1) {
            return -1;
        }

        return ((int)(v0 / 10000));
    }

    public static int parseBuildVersion(int arg1) {
        int v0 = -1;
        if(arg1 == v0) {
            return v0;
        }

        return arg1 / 1000;
    }

    public static int parseScreenDensity(String arg4) {
        long v0 = GmsVersionParser.parseVariantCode(arg4);
        if(v0 == -1) {
            return -1;
        }

        return ((int)(v0 % 100));
    }

    public static int parseTargetArchitecture(String arg4) {
        long v0 = GmsVersionParser.parseVariantCode(arg4);
        if(v0 == -1) {
            return -1;
        }

        return ((int)(v0 / 100 % 100));
    }

    public static long parseVariantCode(String arg4) {
        long v0 = -1;
        if(arg4 == null) {
            return v0;
        }

        Matcher v4 = GmsVersionParser.zzdc().matcher(((CharSequence)arg4));
        if(!v4.find()) {
            return v0;
        }

        arg4 = v4.group(1);
        try {
            return Long.parseLong(arg4);
        }
        catch(NumberFormatException ) {
            return v0;
        }
    }

    private static Pattern zzdc() {
        if(GmsVersionParser.zzzy == null) {
            GmsVersionParser.zzzy = Pattern.compile("\\((?:eng-)?(\\d+)-(.+?)[-)$]");
        }

        return GmsVersionParser.zzzy;
    }
}

