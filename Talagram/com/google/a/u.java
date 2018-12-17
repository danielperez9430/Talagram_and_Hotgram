package com.google.a;

public abstract enum u {
    enum com.google.a.u$1 extends u {
        com.google.a.u$1(String arg2, int arg3) {
            super(arg2, arg3, null);
        }
    }

    enum com.google.a.u$2 extends u {
        com.google.a.u$2(String arg2, int arg3) {
            super(arg2, arg3, null);
        }
    }

    public static final enum u a;
    public static final enum u b;

    static {
        u.a = new com.google.a.u$1("DEFAULT", 0);
        u.b = new com.google.a.u$2("STRING", 1);
        u.c = new u[]{u.a, u.b};
    }

    private u(String arg1, int arg2) {
        super(arg1, arg2);
    }

    u(String arg1, int arg2, com.google.a.u$1 arg3) {
        this(arg1, arg2);
    }

    public static u valueOf(String arg1) {
        return Enum.valueOf(u.class, arg1);
    }

    public static u[] values() {
        // Method was not decompiled
    }
}

