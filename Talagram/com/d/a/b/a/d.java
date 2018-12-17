package com.d.a.b.a;

public enum d {
    public static final enum d a;
    public static final enum d b;
    public static final enum d c;
    public static final enum d d;
    public static final enum d e;
    public static final enum d f;

    static {
        d.a = new d("NONE", 0);
        d.b = new d("NONE_SAFE", 1);
        d.c = new d("IN_SAMPLE_POWER_OF_2", 2);
        d.d = new d("IN_SAMPLE_INT", 3);
        d.e = new d("EXACTLY", 4);
        d.f = new d("EXACTLY_STRETCHED", 5);
        d.g = new d[]{d.a, d.b, d.c, d.d, d.e, d.f};
    }

    private d(String arg1, int arg2) {
        super(arg1, arg2);
    }

    public static d valueOf(String arg1) {
        return Enum.valueOf(d.class, arg1);
    }

    public static d[] values() {
        // Method was not decompiled
    }
}

