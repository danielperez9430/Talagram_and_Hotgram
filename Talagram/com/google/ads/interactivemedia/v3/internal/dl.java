package com.google.ads.interactivemedia.v3.internal;

import java.io.EOFException;

final class dl {
    public class a {
        public int a;
        public int b;

        public a() {
            super();
        }
    }

    public final class b {
        public int a;
        public int b;
        public long c;
        public long d;
        public long e;
        public long f;
        public int g;
        public int h;
        public int i;
        public final int[] j;

        public b() {
            super();
            this.j = new int[255];
        }

        public void a() {
            this.a = 0;
            this.b = 0;
            this.c = 0;
            this.d = 0;
            this.e = 0;
            this.f = 0;
            this.g = 0;
            this.h = 0;
            this.i = 0;
        }
    }

    private static final int a;

    static {
        dl.a = ft.c("OggS");
    }

    public static void a(cd arg7) {
        int v3;
        byte[] v0 = new byte[2048];
        int v1 = v0.length;
        while(true) {
            if(arg7.d() != -1 && arg7.c() + (((long)v1)) > arg7.d()) {
                v1 = ((int)(arg7.d() - arg7.c()));
                if(v1 >= 4) {
                }
                else {
                    throw new EOFException();
                }
            }

            int v2 = 0;
            arg7.b(v0, 0, v1, false);
            while(true) {
                v3 = v1 - 3;
                if(v2 >= v3) {
                    break;
                }

                if(v0[v2] == 79) {
                    int v4 = 103;
                    if(v0[v2 + 1] == v4 && v0[v2 + 2] == v4 && v0[v2 + 3] == 83) {
                        arg7.b(v2);
                        return;
                    }
                }

                ++v2;
            }

            arg7.b(v3);
        }
    }

    public static boolean a(cd arg8, b arg9, fp arg10, boolean arg11) {
        // Method was not decompiled
    }

    public static void a(b arg3, int arg4, a arg5) {
        arg5.b = 0;
        arg5.a = 0;
        do {
            if(arg5.b + arg4 >= arg3.g) {
                return;
            }

            int[] v0 = arg3.j;
            int v1 = arg5.b;
            arg5.b = v1 + 1;
            int v0_1 = v0[v1 + arg4];
            arg5.a += v0_1;
        }
        while(v0_1 == 255);
    }

    public static int a(byte arg0, int arg1, int arg2) {
        return arg0 >> arg2 & 255 >>> 8 - arg1;
    }
}

