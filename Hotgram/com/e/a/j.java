package com.e.a;

public enum j {
    public static final enum j a;
    public static final enum j b;
    final int c;

    static {
        j.a = new j("NO_CACHE", 0, 1);
        j.b = new j("NO_STORE", 1, 2);
        j.d = new j[]{j.a, j.b};
    }

    private j(String arg1, int arg2, int arg3) {
        super(arg1, arg2);
        this.c = arg3;
    }

    static boolean a(int arg1) {
        boolean v1 = (arg1 & j.a.c) == 0 ? true : false;
        return v1;
    }

    public static j valueOf(String arg1) {
        return Enum.valueOf(j.class, arg1);
    }

    public static j[] values() {
        // Method was not decompiled
    }
}

