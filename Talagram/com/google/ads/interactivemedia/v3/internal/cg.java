package com.google.ads.interactivemedia.v3.internal;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class cg {
    public final int a;
    public final int b;
    private static final Pattern c;

    static {
        cg.c = Pattern.compile("^ [0-9a-fA-F]{8} ([0-9a-fA-F]{8}) ([0-9a-fA-F]{8})");
    }

    private cg(int arg1, int arg2) {
        super();
        this.a = arg1;
        this.b = arg2;
    }

    public static cg a(int arg2) {
        int v0 = arg2 >> 12;
        arg2 &= 4095;
        cg v2 = v0 != 0 || arg2 != 0 ? new cg(v0, arg2) : null;
        return v2;
    }

    public static cg a(String arg3, String arg4) {
        cg v0 = null;
        if(!"iTunSMPB".equals(arg3)) {
            return v0;
        }

        Matcher v3 = cg.c.matcher(((CharSequence)arg4));
        if(v3.find()) {
            try {
                int v4 = Integer.parseInt(v3.group(1), 16);
                int v3_1 = Integer.parseInt(v3.group(2), 16);
                if(v4 == 0 && v3_1 == 0) {
                    return v0;
                }

                return new cg(v4, v3_1);
            }
            catch(NumberFormatException ) {
                return v0;
            }
        }

        return v0;
    }
}

