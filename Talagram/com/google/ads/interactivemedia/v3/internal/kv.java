package com.google.ads.interactivemedia.v3.internal;

final class kv {
    static int a(int arg3, String arg4) {
        if(arg3 >= 0) {
            return arg3;
        }

        StringBuilder v2 = new StringBuilder(String.valueOf(arg4).length() + 40);
        v2.append(arg4);
        v2.append(" cannot be negative but was: ");
        v2.append(arg3);
        throw new IllegalArgumentException(v2.toString());
    }

    static void a(Object arg2, Object arg3) {
        StringBuilder v1;
        if(arg2 != null) {
            if(arg3 != null) {
                return;
            }

            String v2 = String.valueOf(arg2);
            v1 = new StringBuilder(String.valueOf(v2).length() + 26);
            v1.append("null value in entry: ");
            v1.append(v2);
            v1.append("=null");
            throw new NullPointerException(v1.toString());
        }

        String v3 = String.valueOf(arg3);
        v1 = new StringBuilder(String.valueOf(v3).length() + 24);
        v1.append("null key in entry: null=");
        v1.append(v3);
        throw new NullPointerException(v1.toString());
    }
}

