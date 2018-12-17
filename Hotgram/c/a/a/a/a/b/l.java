package c.a.a.a.a.b;

public enum l {
    public static final enum l a;
    public static final enum l b;
    public static final enum l c;
    public static final enum l d;
    private final int e;

    static {
        l.a = new l("DEVELOPER", 0, 1);
        l.b = new l("USER_SIDELOAD", 1, 2);
        l.c = new l("TEST_DISTRIBUTION", 2, 3);
        l.d = new l("APP_STORE", 3, 4);
        l.f = new l[]{l.a, l.b, l.c, l.d};
    }

    private l(String arg1, int arg2, int arg3) {
        super(arg1, arg2);
        this.e = arg3;
    }

    public static l a(String arg1) {
        if("io.crash.air".equals(arg1)) {
            return l.c;
        }

        if(arg1 != null) {
            return l.d;
        }

        return l.a;
    }

    public int a() {
        return this.e;
    }

    public String toString() {
        return Integer.toString(this.e);
    }

    public static l valueOf(String arg1) {
        return Enum.valueOf(l.class, arg1);
    }

    public static l[] values() {
        // Method was not decompiled
    }
}

