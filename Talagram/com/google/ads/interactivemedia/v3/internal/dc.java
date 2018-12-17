package com.google.ads.interactivemedia.v3.internal;

final class dc {
    private static final int[] a;

    static {
        dc.a = new int[]{ft.c("isom"), ft.c("iso2"), ft.c("iso3"), ft.c("iso4"), ft.c("iso5"), ft.c("iso6"), ft.c("avc1"), ft.c("hvc1"), ft.c("hev1"), ft.c("mp41"), ft.c("mp42"), ft.c("3g2a"), ft.c("3g2b"), ft.c("3gr6"), ft.c("3gs6"), ft.c("3ge6"), ft.c("3gg6"), ft.c("M4V "), ft.c("M4A "), ft.c("f4v "), ft.c("kddi"), ft.c("M4VP"), ft.c("qt  "), ft.c("MSNV")};
    }

    private static boolean a(int arg6) {
        if(arg6 >>> 8 == ft.c("3gp")) {
            return 1;
        }

        int[] v0 = dc.a;
        int v1 = v0.length;
        int v4;
        for(v4 = 0; v4 < v1; ++v4) {
            if(v0[v4] == arg6) {
                return 1;
            }
        }

        return 0;
    }

    public static boolean a(cd arg1) {
        return dc.a(arg1, true);
    }

    private static boolean a(cd arg17, boolean arg18) {
        // Method was not decompiled
    }

    public static boolean b(cd arg1) {
        return dc.a(arg1, false);
    }
}

