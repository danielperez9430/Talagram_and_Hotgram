package com.google.ads.interactivemedia.v3.internal;

public final class fl {
    public static boolean a(String arg1) {
        return fl.c(arg1).equals("audio");
    }

    public static boolean b(String arg1) {
        return fl.c(arg1).equals("video");
    }

    private static String c(String arg3) {
        int v0 = arg3.indexOf(47);
        if(v0 == -1) {
            String v1 = "Invalid mime type: ";
            arg3 = String.valueOf(arg3);
            arg3 = arg3.length() != 0 ? v1.concat(arg3) : new String(v1);
            throw new IllegalArgumentException(arg3);
        }

        return arg3.substring(0, v0);
    }
}

