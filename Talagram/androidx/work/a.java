package androidx.work;

public enum a {
    public static final enum a a;
    public static final enum a b;

    static {
        a.a = new a("EXPONENTIAL", 0);
        a.b = new a("LINEAR", 1);
        a.c = new a[]{a.a, a.b};
    }

    private a(String arg1, int arg2) {
        super(arg1, arg2);
    }

    public static a valueOf(String arg1) {
        return Enum.valueOf(a.class, arg1);
    }

    public static a[] values() {
        // Method was not decompiled
    }
}

