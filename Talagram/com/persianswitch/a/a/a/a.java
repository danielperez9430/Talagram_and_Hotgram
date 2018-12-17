package com.persianswitch.a.a.a;

public enum a {
    public static final enum a a;
    public static final enum a b;
    public static final enum a c;
    public static final enum a d;
    public static final enum a e;
    public static final enum a f;
    public static final enum a g;
    public static final enum a h;
    public static final enum a i;
    public static final enum a j;
    public static final enum a k;
    public static final enum a l;
    public static final enum a m;
    public static final enum a n;
    public static final enum a o;
    public static final enum a p;
    public static final enum a q;
    public static final enum a r;
    public final int s;
    public final int t;
    public final int u;

    static {
        a.a = new a("NO_ERROR", 0, 0, -1, 0);
        a.b = new a("PROTOCOL_ERROR", 1, 1, 1, 1);
        a.c = new a("INVALID_STREAM", 2, 1, 2, -1);
        a.d = new a("UNSUPPORTED_VERSION", 3, 1, 4, -1);
        a.e = new a("STREAM_IN_USE", 4, 1, 8, -1);
        a.f = new a("STREAM_ALREADY_CLOSED", 5, 1, 9, -1);
        a.g = new a("INTERNAL_ERROR", 6, 2, 6, 2);
        a.h = new a("FLOW_CONTROL_ERROR", 7, 3, 7, -1);
        a.i = new a("STREAM_CLOSED", 8, 5, -1, -1);
        a.j = new a("FRAME_TOO_LARGE", 9, 6, 11, -1);
        a.k = new a("REFUSED_STREAM", 10, 7, 3, -1);
        a.l = new a("CANCEL", 11, 8, 5, -1);
        a.m = new a("COMPRESSION_ERROR", 12, 9, -1, -1);
        a.n = new a("CONNECT_ERROR", 13, 10, -1, -1);
        a.o = new a("ENHANCE_YOUR_CALM", 14, 11, -1, -1);
        a.p = new a("INADEQUATE_SECURITY", 15, 12, -1, -1);
        a.q = new a("HTTP_1_1_REQUIRED", 16, 13, -1, -1);
        a.r = new a("INVALID_CREDENTIALS", 17, -1, 10, -1);
        a.v = new a[]{a.a, a.b, a.c, a.d, a.e, a.f, a.g, a.h, a.i, a.j, a.k, a.l, a.m, a.n, a.o, a.p, a.q, a.r};
    }

    private a(String arg1, int arg2, int arg3, int arg4, int arg5) {
        super(arg1, arg2);
        this.s = arg3;
        this.t = arg4;
        this.u = arg5;
    }

    public static a a(int arg5) {
        a[] v0 = a.values();
        int v1 = v0.length;
        int v2;
        for(v2 = 0; v2 < v1; ++v2) {
            a v3 = v0[v2];
            if(v3.t == arg5) {
                return v3;
            }
        }

        return null;
    }

    public static a b(int arg5) {
        a[] v0 = a.values();
        int v1 = v0.length;
        int v2;
        for(v2 = 0; v2 < v1; ++v2) {
            a v3 = v0[v2];
            if(v3.s == arg5) {
                return v3;
            }
        }

        return null;
    }

    public static a c(int arg5) {
        a[] v0 = a.values();
        int v1 = v0.length;
        int v2;
        for(v2 = 0; v2 < v1; ++v2) {
            a v3 = v0[v2];
            if(v3.u == arg5) {
                return v3;
            }
        }

        return null;
    }

    public static a valueOf(String arg1) {
        return Enum.valueOf(a.class, arg1);
    }

    public static a[] values() {
        // Method was not decompiled
    }
}

