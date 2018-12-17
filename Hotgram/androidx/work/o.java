package androidx.work;

public enum o {
    public static final enum o a;
    public static final enum o b;
    public static final enum o c;
    public static final enum o d;
    public static final enum o e;
    public static final enum o f;

    static {
        o.a = new o("ENQUEUED", 0);
        o.b = new o("RUNNING", 1);
        o.c = new o("SUCCEEDED", 2);
        o.d = new o("FAILED", 3);
        o.e = new o("BLOCKED", 4);
        o.f = new o("CANCELLED", 5);
        o.g = new o[]{o.a, o.b, o.c, o.d, o.e, o.f};
    }

    private o(String arg1, int arg2) {
        super(arg1, arg2);
    }

    public boolean a() {
        boolean v0 = this == o.c || this == o.d || this == o.f ? true : false;
        return v0;
    }

    public static o valueOf(String arg1) {
        return Enum.valueOf(o.class, arg1);
    }

    public static o[] values() {
        // Method was not decompiled
    }
}

