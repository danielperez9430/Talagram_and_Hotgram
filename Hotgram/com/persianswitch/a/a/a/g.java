package com.persianswitch.a.a.a;

public enum g {
    public static final enum g a;
    public static final enum g b;
    public static final enum g c;
    public static final enum g d;

    static {
        g.a = new g("SPDY_SYN_STREAM", 0);
        g.b = new g("SPDY_REPLY", 1);
        g.c = new g("SPDY_HEADERS", 2);
        g.d = new g("HTTP_20_HEADERS", 3);
        g.e = new g[]{g.a, g.b, g.c, g.d};
    }

    private g(String arg1, int arg2) {
        super(arg1, arg2);
    }

    public boolean a() {
        boolean v0 = this == g.b || this == g.c ? true : false;
        return v0;
    }

    public boolean b() {
        boolean v0 = this == g.a ? true : false;
        return v0;
    }

    public boolean c() {
        boolean v0 = this == g.c ? true : false;
        return v0;
    }

    public boolean d() {
        boolean v0 = this == g.b ? true : false;
        return v0;
    }

    public static g valueOf(String arg1) {
        return Enum.valueOf(g.class, arg1);
    }

    public static g[] values() {
        // Method was not decompiled
    }
}

