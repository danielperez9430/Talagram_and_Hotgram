package net.hockeyapp.android.c;

public enum e {
    public static final enum e a;
    public static final enum e b;
    public static final enum e c;
    private final int d;

    static {
        e.a = new e("DONT_SHOW", 0, 0);
        e.b = new e("OPTIONAL", 1, 1);
        e.c = new e("REQUIRED", 2, 2);
        e.e = new e[]{e.a, e.b, e.c};
    }

    private e(String arg1, int arg2, int arg3) {
        super(arg1, arg2);
        this.d = arg3;
    }

    public static e valueOf(String arg1) {
        return Enum.valueOf(e.class, arg1);
    }

    public static e[] values() {
        // Method was not decompiled
    }
}

