package c.a.a.a.a.e;

public enum c {
    public static final enum c a;
    public static final enum c b;
    public static final enum c c;
    public static final enum c d;

    static {
        c.a = new c("GET", 0);
        c.b = new c("POST", 1);
        c.c = new c("PUT", 2);
        c.d = new c("DELETE", 3);
        c.e = new c[]{c.a, c.b, c.c, c.d};
    }

    private c(String arg1, int arg2) {
        super(arg1, arg2);
    }

    public static c valueOf(String arg1) {
        return Enum.valueOf(c.class, arg1);
    }

    public static c[] values() {
        // Method was not decompiled
    }
}

