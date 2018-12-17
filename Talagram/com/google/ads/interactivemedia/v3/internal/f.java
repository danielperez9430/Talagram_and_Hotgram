package com.google.ads.interactivemedia.v3.internal;

public enum f {
    public static final enum f a;
    public static final enum f b;
    private final String c;

    static {
        f.a = new f("HTML", 0, "html");
        f.b = new f("NATIVE", 1, "native");
        f.d = new f[]{f.a, f.b};
    }

    private f(String arg1, int arg2, String arg3) {
        super(arg1, arg2);
        this.c = arg3;
    }

    public String toString() {
        return this.c;
    }

    public static f valueOf(String arg1) {
        return Enum.valueOf(f.class, arg1);
    }

    public static f[] values() {
        // Method was not decompiled
    }
}

