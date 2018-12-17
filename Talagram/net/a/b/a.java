package net.a.b;

public enum a {
    public static final enum a a;
    public static final enum a b;
    public static final enum a c;

    static {
        a.a = new a("START", 0);
        a.b = new a("SUCCESS", 1);
        a.c = new a("FAILURE", 2);
        a.d = new a[]{a.a, a.b, a.c};
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

