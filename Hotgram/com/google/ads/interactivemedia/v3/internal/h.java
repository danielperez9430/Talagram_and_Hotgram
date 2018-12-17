package com.google.ads.interactivemedia.v3.internal;

public enum h {
    public static final enum h a;
    public static final enum h b;
    public static final enum h c;
    private final String d;

    static {
        h.a = new h("NATIVE", 0, "native");
        h.b = new h("JAVASCRIPT", 1, "javascript");
        h.c = new h("NONE", 2, "none");
        h.e = new h[]{h.a, h.b, h.c};
    }

    private h(String arg1, int arg2, String arg3) {
        super(arg1, arg2);
        this.d = arg3;
    }

    public String toString() {
        return this.d;
    }

    public static h valueOf(String arg1) {
        return Enum.valueOf(h.class, arg1);
    }

    public static h[] values() {
        // Method was not decompiled
    }
}

