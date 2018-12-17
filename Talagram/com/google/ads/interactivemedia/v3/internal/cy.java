package com.google.ads.interactivemedia.v3.internal;

final class cy {
    public final class a {
        public final long[] a;
        public final int[] b;
        public final int c;
        public final long[] d;
        public final int[] e;

        public a(long[] arg1, int[] arg2, int arg3, long[] arg4, int[] arg5) {
            super();
            this.a = arg1;
            this.b = arg2;
            this.c = arg3;
            this.d = arg4;
            this.e = arg5;
        }
    }

    public static a a(int arg17, long[] arg18, int[] arg19, long arg20) {
        int[] v1 = arg19;
        int v2 = 8192 / arg17;
        int v3 = v1.length;
        int v4 = 0;
        int v5 = 0;
        int v6 = 0;
        while(v5 < v3) {
            v6 += ft.a(v1[v5], v2);
            ++v5;
        }

        long[] v8 = new long[v6];
        int[] v9 = new int[v6];
        long[] v11 = new long[v6];
        int[] v12 = new int[v6];
        v3 = 0;
        v5 = 0;
        int v10 = 0;
        while(v4 < v1.length) {
            v6 = v1[v4];
            long v13 = arg18[v4];
            while(v6 > 0) {
                int v15 = Math.min(v2, v6);
                v8[v3] = v13;
                v9[v3] = arg17 * v15;
                v10 = Math.max(v10, v9[v3]);
                v11[v3] = (((long)v5)) * arg20;
                v12[v3] = 1;
                v13 += ((long)v9[v3]);
                v5 += v15;
                v6 -= v15;
                ++v3;
            }

            ++v4;
            v1 = arg19;
        }

        return new a(v8, v9, v10, v11, v12);
    }
}

