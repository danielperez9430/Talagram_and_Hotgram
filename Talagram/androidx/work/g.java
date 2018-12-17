package androidx.work;

public enum g {
    public static final enum g a;
    public static final enum g b;

    static {
        g.a = new g("REPLACE", 0);
        g.b = new g("KEEP", 1);
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

