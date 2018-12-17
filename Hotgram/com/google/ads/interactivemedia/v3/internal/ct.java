package com.google.ads.interactivemedia.v3.internal;

final class ct implements a {
    private final long[] a;
    private final long[] b;
    private final long c;

    private ct(long[] arg1, long[] arg2, long arg3) {
        super();
        this.a = arg1;
        this.b = arg2;
        this.c = arg3;
    }

    public static ct a(fm arg18, fp arg19, long arg20, long arg22) {
        fm v0 = arg18;
        fp v1 = arg19;
        long v2 = arg22;
        v1.d(10);
        int v4 = arg19.m();
        ct v5 = null;
        if(v4 <= 0) {
            return v5;
        }

        int v6 = v0.d;
        long v7 = ((long)v4);
        long v9 = 1000000;
        v4 = v6 >= 32000 ? 1152 : 576;
        long v6_1 = ft.a(v7, v9 * (((long)v4)), ((long)v6));
        v4 = arg19.g();
        int v8 = arg19.g();
        int v9_1 = arg19.g();
        v1.d(2);
        long v10 = arg20 + (((long)v0.c));
        int v0_1 = v4 + 1;
        long[] v12 = new long[v0_1];
        long[] v0_2 = new long[v0_1];
        v12[0] = 0;
        v0_2[0] = v10;
        int v13 = 1;
        while(v13 < v12.length) {
            switch(v9_1) {
                case 1: {
                    goto label_47;
                }
                case 2: {
                    goto label_45;
                }
                case 3: {
                    goto label_43;
                }
                case 4: {
                    goto label_41;
                }
            }

            return v5;
        label_41:
            int v14 = arg19.s();
            goto label_48;
        label_43:
            v14 = arg19.j();
            goto label_48;
        label_45:
            v14 = arg19.g();
            goto label_48;
        label_47:
            v14 = arg19.f();
        label_48:
            v10 += ((long)(v14 * v8));
            long v16 = v6_1;
            v12[v13] = (((long)v13)) * v6_1 / (((long)v4));
            long v5_1 = v2 == -1 ? v10 : Math.min(v2, v10);
            v0_2[v13] = v5_1;
            ++v13;
            v6_1 = v16;
            v5 = null;
        }

        return new ct(v12, v0_2, v6_1);
    }

    public long a(long arg4) {
        return this.a[ft.a(this.b, arg4, true, true)];
    }

    public boolean a() {
        return 1;
    }

    public long b() {
        return this.c;
    }

    public long b(long arg4) {
        return this.b[ft.a(this.a, arg4, true, true)];
    }
}

