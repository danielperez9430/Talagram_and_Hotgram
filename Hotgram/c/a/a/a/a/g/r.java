package c.a.a.a.a.g;

public enum r {
    public static final enum r a;
    public static final enum r b;
    public static final enum r c;

    static {
        r.a = new r("USE_CACHE", 0);
        r.b = new r("SKIP_CACHE_LOOKUP", 1);
        r.c = new r("IGNORE_CACHE_EXPIRATION", 2);
        r.d = new r[]{r.a, r.b, r.c};
    }

    private r(String arg1, int arg2) {
        super(arg1, arg2);
    }

    public static r valueOf(String arg1) {
        return Enum.valueOf(r.class, arg1);
    }

    public static r[] values() {
        // Method was not decompiled
    }
}

