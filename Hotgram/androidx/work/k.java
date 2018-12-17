package androidx.work;

public enum k {
    public static final enum k a;
    public static final enum k b;
    public static final enum k c;
    public static final enum k d;
    public static final enum k e;

    static {
        k.a = new k("NOT_REQUIRED", 0);
        k.b = new k("CONNECTED", 1);
        k.c = new k("UNMETERED", 2);
        k.d = new k("NOT_ROAMING", 3);
        k.e = new k("METERED", 4);
        k.f = new k[]{k.a, k.b, k.c, k.d, k.e};
    }

    private k(String arg1, int arg2) {
        super(arg1, arg2);
    }

    public static k valueOf(String arg1) {
        return Enum.valueOf(k.class, arg1);
    }

    public static k[] values() {
        // Method was not decompiled
    }
}

