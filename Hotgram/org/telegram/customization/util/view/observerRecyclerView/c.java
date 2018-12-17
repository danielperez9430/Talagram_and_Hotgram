package org.telegram.customization.util.view.observerRecyclerView;

public enum c {
    public static final enum c a;
    public static final enum c b;
    public static final enum c c;

    static {
        c.a = new c("STOP", 0);
        c.b = new c("UP", 1);
        c.c = new c("DOWN", 2);
        c.d = new c[]{c.a, c.b, c.c};
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

