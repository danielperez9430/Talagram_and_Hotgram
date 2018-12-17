package com.google.a.d;

public enum b {
    public static final enum b a;
    public static final enum b b;
    public static final enum b c;
    public static final enum b d;
    public static final enum b e;
    public static final enum b f;
    public static final enum b g;
    public static final enum b h;
    public static final enum b i;
    public static final enum b j;

    static {
        b.a = new b("BEGIN_ARRAY", 0);
        b.b = new b("END_ARRAY", 1);
        b.c = new b("BEGIN_OBJECT", 2);
        b.d = new b("END_OBJECT", 3);
        b.e = new b("NAME", 4);
        b.f = new b("STRING", 5);
        b.g = new b("NUMBER", 6);
        b.h = new b("BOOLEAN", 7);
        b.i = new b("NULL", 8);
        b.j = new b("END_DOCUMENT", 9);
        b.k = new b[]{b.a, b.b, b.c, b.d, b.e, b.f, b.g, b.h, b.i, b.j};
    }

    private b(String arg1, int arg2) {
        super(arg1, arg2);
    }

    public static b valueOf(String arg1) {
        return Enum.valueOf(b.class, arg1);
    }

    public static b[] values() {
        // Method was not decompiled
    }
}

