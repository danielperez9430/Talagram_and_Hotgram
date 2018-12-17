package com.google.android.gms.common.util;

import android.util.Base64;

public final class Base64Utils {
    public Base64Utils() {
        super();
    }

    public static byte[] decode(String arg1) {
        if(arg1 == null) {
            return null;
        }

        return Base64.decode(arg1, 0);
    }

    public static byte[] decodeUrlSafe(String arg1) {
        if(arg1 == null) {
            return null;
        }

        return Base64.decode(arg1, 10);
    }

    public static byte[] decodeUrlSafeNoPadding(String arg1) {
        if(arg1 == null) {
            return null;
        }

        return Base64.decode(arg1, 11);
    }

    public static byte[] decodeUrlSafeNoPadding(byte[] arg1) {
        if(arg1 == null) {
            return null;
        }

        return Base64.decode(arg1, 11);
    }

    public static String encode(byte[] arg1) {
        if(arg1 == null) {
            return null;
        }

        return Base64.encodeToString(arg1, 0);
    }

    public static String encodeUrlSafe(byte[] arg1) {
        if(arg1 == null) {
            return null;
        }

        return Base64.encodeToString(arg1, 10);
    }

    public static String encodeUrlSafeNoPadding(byte[] arg1) {
        if(arg1 == null) {
            return null;
        }

        return Base64.encodeToString(arg1, 11);
    }
}

