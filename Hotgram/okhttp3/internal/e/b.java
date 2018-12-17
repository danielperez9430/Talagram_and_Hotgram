package okhttp3.internal.e;

public enum b {
    public static final enum b a;
    public static final enum b b;
    public static final enum b c;
    public static final enum b d;
    public static final enum b e;
    public static final enum b f;
    public static final enum b g;
    public static final enum b h;
    public static final enum b i;
    public static final enum b j;
    public static final enum b k;
    public final int l;

    static {
        b.a = new b("NO_ERROR", 0, 0);
        b.b = new b("PROTOCOL_ERROR", 1, 1);
        b.c = new b("INTERNAL_ERROR", 2, 2);
        b.d = new b("FLOW_CONTROL_ERROR", 3, 3);
        b.e = new b("REFUSED_STREAM", 4, 7);
        b.f = new b("CANCEL", 5, 8);
        b.g = new b("COMPRESSION_ERROR", 6, 9);
        b.h = new b("CONNECT_ERROR", 7, 10);
        b.i = new b("ENHANCE_YOUR_CALM", 8, 11);
        b.j = new b("INADEQUATE_SECURITY", 9, 12);
        b.k = new b("HTTP_1_1_REQUIRED", 10, 13);
        b.m = new b[]{b.a, b.b, b.c, b.d, b.e, b.f, b.g, b.h, b.i, b.j, b.k};
    }

    private b(String arg1, int arg2, int arg3) {
        super(arg1, arg2);
        this.l = arg3;
    }

    public static b a(int arg5) {
        b[] v0 = b.values();
        int v1 = v0.length;
        int v2;
        for(v2 = 0; v2 < v1; ++v2) {
            b v3 = v0[v2];
            if(v3.l == arg5) {
                return v3;
            }
        }

        return null;
    }

    public static b valueOf(String arg1) {
        return Enum.valueOf(b.class, arg1);
    }

    public static b[] values() {
        // Method was not decompiled
    }
}

