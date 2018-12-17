package com.google.ads.interactivemedia.v3.internal;

public enum hy {
    public static final enum hy a;
    public static final enum hy b;
    public static final enum hy c;
    public static final enum hy d;
    public static final enum hy e;
    public static final enum hy f;
    public static final enum hy g;
    public static final enum hy h;
    public static final enum hy i;
    public static final enum hy j;

    static {
        hy.a = new hy("BEGIN_ARRAY", 0);
        hy.b = new hy("END_ARRAY", 1);
        hy.c = new hy("BEGIN_OBJECT", 2);
        hy.d = new hy("END_OBJECT", 3);
        hy.e = new hy("NAME", 4);
        hy.f = new hy("STRING", 5);
        hy.g = new hy("NUMBER", 6);
        hy.h = new hy("BOOLEAN", 7);
        hy.i = new hy("NULL", 8);
        hy.j = new hy("END_DOCUMENT", 9);
        hy.k = new hy[]{hy.a, hy.b, hy.c, hy.d, hy.e, hy.f, hy.g, hy.h, hy.i, hy.j};
    }

    private hy(String arg1, int arg2) {
        super(arg1, arg2);
    }

    public static hy valueOf(String arg1) {
        return Enum.valueOf(hy.class, arg1);
    }

    public static hy[] values() {
        // Method was not decompiled
    }
}

