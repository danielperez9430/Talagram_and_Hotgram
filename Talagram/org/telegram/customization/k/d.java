package org.telegram.customization.k;

public enum d {
    public static final enum d a;
    public static final enum d b;
    public static final enum d c;
    public static final enum d d;

    static {
        d.a = new d("GoToProfileTab", 0);
        d.b = new d("GoToHomeTab", 1);
        d.c = new d("GoToSearchTab", 2);
        d.d = new d("OnImageSelect", 3);
        d.e = new d[]{d.a, d.b, d.c, d.d};
    }

    private d(String arg1, int arg2) {
        super(arg1, arg2);
    }

    public static d valueOf(String arg1) {
        return Enum.valueOf(d.class, arg1);
    }

    public static d[] values() {
        // Method was not decompiled
    }
}

