package c.a.a.a.a.c;

public enum e {
    public static final enum e a;
    public static final enum e b;
    public static final enum e c;
    public static final enum e d;

    static {
        e.a = new e("LOW", 0);
        e.b = new e("NORMAL", 1);
        e.c = new e("HIGH", 2);
        e.d = new e("IMMEDIATE", 3);
        e.e = new e[]{e.a, e.b, e.c, e.d};
    }

    private e(String arg1, int arg2) {
        super(arg1, arg2);
    }

    static int a(i arg1, Object arg2) {
        e v2 = (arg2 instanceof i) ? ((i)arg2).b() : e.b;
        return v2.ordinal() - arg1.b().ordinal();
    }

    public static e valueOf(String arg1) {
        return Enum.valueOf(e.class, arg1);
    }

    public static e[] values() {
        // Method was not decompiled
    }
}

