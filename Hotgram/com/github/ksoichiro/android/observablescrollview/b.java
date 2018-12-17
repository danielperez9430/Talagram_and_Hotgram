package com.github.ksoichiro.android.observablescrollview;

public enum b {
    public static final enum b a;
    public static final enum b b;
    public static final enum b c;

    static {
        b.a = new b("STOP", 0);
        b.b = new b("UP", 1);
        b.c = new b("DOWN", 2);
        b.d = new b[]{b.a, b.b, b.c};
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

