package c.b.e.a;

import c.b.e.c.a;

public enum c implements a {
    public static final enum c a;
    public static final enum c b;

    static {
        c.a = new c("INSTANCE", 0);
        c.b = new c("NEVER", 1);
        c.c = new c[]{c.a, c.b};
    }

    private c(String arg1, int arg2) {
        super(arg1, arg2);
    }

    public void a() {
    }

    public static c valueOf(String arg1) {
        return Enum.valueOf(c.class, arg1);
    }

    public static c[] values() {
        // Method was not decompiled
    }
}

