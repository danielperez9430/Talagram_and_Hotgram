package com.google.ads.interactivemedia.v3.internal;

import android.util.Log;

final class eh {
    final class a {
        public final int a;
        public final long b;

        private a(int arg1, long arg2) {
            super();
            this.a = arg1;
            this.b = arg2;
        }

        public static a a(cd arg3, fp arg4) {
            arg3.c(arg4.a, 0, 8);
            arg4.c(0);
            return new a(arg4.m(), arg4.l());
        }
    }

    public static eg a(cd arg14) {
        // Method was not decompiled
    }

    public static void a(cd arg8, eg arg9) {
        a v2;
        fe.a(arg8);
        fe.a(arg9);
        arg8.a();
        int v1 = 8;
        fp v0 = new fp(v1);
        while(true) {
            v2 = a.a(arg8, v0);
            if(v2.a == ft.c("data")) {
                goto label_45;
            }

            int v4 = v2.a;
            StringBuilder v6 = new StringBuilder(39);
            v6.append("Ignoring unknown WAV chunk: ");
            v6.append(v4);
            Log.w("WavHeaderReader", v6.toString());
            long v5 = v2.b + 8;
            if(v2.a == ft.c("RIFF")) {
                v5 = 12;
            }

            if(v5 > 2147483647) {
                break;
            }

            arg8.b(((int)v5));
        }

        int v9 = v2.a;
        StringBuilder v1_1 = new StringBuilder(51);
        v1_1.append("Chunk is too large (~2GB+) to skip; id: ");
        v1_1.append(v9);
        throw new bl(v1_1.toString());
    label_45:
        arg8.b(v1);
        arg9.a(arg8.c(), v2.b);
    }
}

