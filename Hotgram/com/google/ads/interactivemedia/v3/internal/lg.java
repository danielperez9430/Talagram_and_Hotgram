package com.google.ads.interactivemedia.v3.internal;

public final class lg {
    public static Object[] a(Object[] arg0, int arg1) {
        return li.a(arg0, arg1);
    }

    static Object a(Object arg2, int arg3) {
        if(arg2 != null) {
            return arg2;
        }

        StringBuilder v1 = new StringBuilder(20);
        v1.append("at index ");
        v1.append(arg3);
        throw new NullPointerException(v1.toString());
    }

    static Object[] a(Object[] arg1) {
        return lg.b(arg1, arg1.length);
    }

    static Object[] b(Object[] arg2, int arg3) {
        int v0;
        for(v0 = 0; v0 < arg3; ++v0) {
            lg.a(arg2[v0], v0);
        }

        return arg2;
    }
}

