package androidx.work;

public enum h {
    public static final enum h a;
    public static final enum h b;
    public static final enum h c;

    static {
        h.a = new h("REPLACE", 0);
        h.b = new h("KEEP", 1);
        h.c = new h("APPEND", 2);
        h.d = new h[]{h.a, h.b, h.c};
    }

    private h(String arg1, int arg2) {
        super(arg1, arg2);
    }

    public static h valueOf(String arg1) {
        return Enum.valueOf(h.class, arg1);
    }

    public static h[] values() {
        // Method was not decompiled
    }
}

