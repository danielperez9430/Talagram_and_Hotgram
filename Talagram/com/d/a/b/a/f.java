package com.d.a.b.a;

public enum f {
    public static final enum f a;
    public static final enum f b;
    public static final enum f c;

    static {
        f.a = new f("NETWORK", 0);
        f.b = new f("DISC_CACHE", 1);
        f.c = new f("MEMORY_CACHE", 2);
        f.d = new f[]{f.a, f.b, f.c};
    }

    private f(String arg1, int arg2) {
        super(arg1, arg2);
    }

    public static f valueOf(String arg1) {
        return Enum.valueOf(f.class, arg1);
    }

    public static f[] values() {
        // Method was not decompiled
    }
}

