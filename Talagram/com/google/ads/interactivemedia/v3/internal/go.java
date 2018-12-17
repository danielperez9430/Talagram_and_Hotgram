package com.google.ads.interactivemedia.v3.internal;

public abstract enum go {
    enum com.google.ads.interactivemedia.v3.internal.go$1 extends go {
        com.google.ads.interactivemedia.v3.internal.go$1(String arg2, int arg3) {
            super(arg2, arg3, null);
        }
    }

    enum com.google.ads.interactivemedia.v3.internal.go$2 extends go {
        com.google.ads.interactivemedia.v3.internal.go$2(String arg2, int arg3) {
            super(arg2, arg3, null);
        }
    }

    public static final enum go a;
    public static final enum go b;

    static {
        go.a = new com.google.ads.interactivemedia.v3.internal.go$1("DEFAULT", 0);
        go.b = new com.google.ads.interactivemedia.v3.internal.go$2("STRING", 1);
        go.c = new go[]{go.a, go.b};
    }

    private go(String arg1, int arg2) {
        super(arg1, arg2);
    }

    go(String arg1, int arg2, com.google.ads.interactivemedia.v3.internal.go$1 arg3) {
        this(arg1, arg2);
    }

    public static go valueOf(String arg1) {
        return Enum.valueOf(go.class, arg1);
    }

    public static go[] values() {
        // Method was not decompiled
    }
}

