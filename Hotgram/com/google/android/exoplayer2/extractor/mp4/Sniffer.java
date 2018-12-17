package com.google.android.exoplayer2.extractor.mp4;

import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.util.Util;

final class Sniffer {
    private static final int[] COMPATIBLE_BRANDS = null;
    private static final int SEARCH_LENGTH = 4096;

    static {
        Sniffer.COMPATIBLE_BRANDS = new int[]{Util.getIntegerCodeForString("isom"), Util.getIntegerCodeForString("iso2"), Util.getIntegerCodeForString("iso3"), Util.getIntegerCodeForString("iso4"), Util.getIntegerCodeForString("iso5"), Util.getIntegerCodeForString("iso6"), Util.getIntegerCodeForString("avc1"), Util.getIntegerCodeForString("hvc1"), Util.getIntegerCodeForString("hev1"), Util.getIntegerCodeForString("mp41"), Util.getIntegerCodeForString("mp42"), Util.getIntegerCodeForString("3g2a"), Util.getIntegerCodeForString("3g2b"), Util.getIntegerCodeForString("3gr6"), Util.getIntegerCodeForString("3gs6"), Util.getIntegerCodeForString("3ge6"), Util.getIntegerCodeForString("3gg6"), Util.getIntegerCodeForString("M4V "), Util.getIntegerCodeForString("M4A "), Util.getIntegerCodeForString("f4v "), Util.getIntegerCodeForString("kddi"), Util.getIntegerCodeForString("M4VP"), Util.getIntegerCodeForString("qt  "), Util.getIntegerCodeForString("MSNV")};
    }

    private Sniffer() {
        super();
    }

    private static boolean isCompatibleBrand(int arg6) {
        if(arg6 >>> 8 == Util.getIntegerCodeForString("3gp")) {
            return 1;
        }

        int[] v0 = Sniffer.COMPATIBLE_BRANDS;
        int v1 = v0.length;
        int v4;
        for(v4 = 0; v4 < v1; ++v4) {
            if(v0[v4] == arg6) {
                return 1;
            }
        }

        return 0;
    }

    public static boolean sniffFragmented(ExtractorInput arg1) {
        return Sniffer.sniffInternal(arg1, true);
    }

    private static boolean sniffInternal(ExtractorInput arg17, boolean arg18) {
        // Method was not decompiled
    }

    public static boolean sniffUnfragmented(ExtractorInput arg1) {
        return Sniffer.sniffInternal(arg1, false);
    }
}

