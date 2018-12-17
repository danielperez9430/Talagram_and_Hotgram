package com.google.android.exoplayer2.upstream.crypto;

final class CryptoUtil {
    private CryptoUtil() {
        super();
    }

    public static long getFNV64Hash(String arg7) {
        long v0 = 0;
        if(arg7 == null) {
            return v0;
        }

        int v2;
        for(v2 = 0; v2 < arg7.length(); ++v2) {
            v0 ^= ((long)arg7.charAt(v2));
            v0 += (v0 << 1) + (v0 << 4) + (v0 << 5) + (v0 << 7) + (v0 << 8) + (v0 << 40);
        }

        return v0;
    }
}

