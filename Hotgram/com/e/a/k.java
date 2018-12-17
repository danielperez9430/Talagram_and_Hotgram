package com.e.a;

public enum k {
    public static final enum k a;
    public static final enum k b;
    public static final enum k c;
    final int d;

    static {
        k.a = new k("NO_CACHE", 0, 1);
        k.b = new k("NO_STORE", 1, 2);
        k.c = new k("OFFLINE", 2, 4);
        k.e = new k[]{k.a, k.b, k.c};
    }

    private k(String arg1, int arg2, int arg3) {
        super(arg1, arg2);
        this.d = arg3;
    }

    public static k valueOf(String arg1) {
        return Enum.valueOf(k.class, arg1);
    }

    public static k[] values() {
        // Method was not decompiled
    }
}

