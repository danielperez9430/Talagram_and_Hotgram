package com.d.a.b.a;

public enum g {
    public static final enum g a;
    public static final enum g b;

    static {
        g.a = new g("FIFO", 0);
        g.b = new g("LIFO", 1);
        g.c = new g[]{g.a, g.b};
    }

    private g(String arg1, int arg2) {
        super(arg1, arg2);
    }

    public static g valueOf(String arg1) {
        return Enum.valueOf(g.class, arg1);
    }

    public static g[] values() {
        // Method was not decompiled
    }
}

