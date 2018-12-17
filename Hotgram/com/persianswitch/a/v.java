package com.persianswitch.a;

import java.io.IOException;

public enum v {
    public static final enum v a;
    public static final enum v b;
    public static final enum v c;
    public static final enum v d;
    private final String e;

    static {
        v.a = new v("HTTP_1_0", 0, "http/1.0");
        v.b = new v("HTTP_1_1", 1, "http/1.1");
        v.c = new v("SPDY_3", 2, "spdy/3.1");
        v.d = new v("HTTP_2", 3, "h2");
        v.f = new v[]{v.a, v.b, v.c, v.d};
    }

    private v(String arg1, int arg2, String arg3) {
        super(arg1, arg2);
        this.e = arg3;
    }

    public static v a(String arg3) {
        if(arg3.equals(v.a.e)) {
            return v.a;
        }

        if(arg3.equals(v.b.e)) {
            return v.b;
        }

        if(arg3.equals(v.d.e)) {
            return v.d;
        }

        if(arg3.equals(v.c.e)) {
            return v.c;
        }

        StringBuilder v1 = new StringBuilder();
        v1.append("Unexpected protocol: ");
        v1.append(arg3);
        throw new IOException(v1.toString());
    }

    public String toString() {
        return this.e;
    }

    public static v valueOf(String arg1) {
        return Enum.valueOf(v.class, arg1);
    }

    public static v[] values() {
        // Method was not decompiled
    }
}

