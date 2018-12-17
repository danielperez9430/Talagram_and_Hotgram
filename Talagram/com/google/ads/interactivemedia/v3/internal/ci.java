package com.google.ads.interactivemedia.v3.internal;

import java.io.EOFException;
import java.nio.ByteBuffer;
import java.util.concurrent.LinkedBlockingDeque;

final class ci {
    class com.google.ads.interactivemedia.v3.internal.ci$1 {
    }

    final class a {
        private int a;
        private long[] b;
        private int[] c;
        private int[] d;
        private long[] e;
        private byte[][] f;
        private int g;
        private int h;
        private int i;
        private int j;

        public a() {
            super();
            this.a = 1000;
            this.b = new long[this.a];
            this.e = new long[this.a];
            this.d = new int[this.a];
            this.c = new int[this.a];
            this.f = new byte[this.a][];
        }

        public void a() {
            this.h = 0;
            this.i = 0;
            this.j = 0;
            this.g = 0;
        }

        public void a(long arg4, int arg6, long arg7, int arg9, byte[] arg10) {
            __monitor_enter(this);
            try {
                this.e[this.j] = arg4;
                this.b[this.j] = arg7;
                this.c[this.j] = arg9;
                this.d[this.j] = arg6;
                this.f[this.j] = arg10;
                ++this.g;
                if(this.g == this.a) {
                    int v4_1 = this.a + 1000;
                    long[] v5 = new long[v4_1];
                    long[] v7 = new long[v4_1];
                    int[] v8 = new int[v4_1];
                    int[] v9 = new int[v4_1];
                    byte[][] v10 = new byte[v4_1][];
                    int v0 = this.a - this.i;
                    System.arraycopy(this.b, this.i, v5, 0, v0);
                    System.arraycopy(this.e, this.i, v7, 0, v0);
                    System.arraycopy(this.d, this.i, v8, 0, v0);
                    System.arraycopy(this.c, this.i, v9, 0, v0);
                    System.arraycopy(this.f, this.i, v10, 0, v0);
                    int v1 = this.i;
                    System.arraycopy(this.b, 0, v5, v0, v1);
                    System.arraycopy(this.e, 0, v7, v0, v1);
                    System.arraycopy(this.d, 0, v8, v0, v1);
                    System.arraycopy(this.c, 0, v9, v0, v1);
                    System.arraycopy(this.f, 0, v10, v0, v1);
                    this.b = v5;
                    this.e = v7;
                    this.d = v8;
                    this.c = v9;
                    this.f = v10;
                    this.i = 0;
                    this.j = this.a;
                    this.g = this.a;
                    this.a = v4_1;
                }
                else {
                    ++this.j;
                    if(this.j == this.a) {
                        this.j = 0;
                    }
                }
            }
            catch(Throwable v4) {
                __monitor_exit(this);
                throw v4;
            }

            __monitor_exit(this);
        }

        public long a(long arg10) {
            long v0_1;
            int v0;
            long v1;
            __monitor_enter(this);
            try {
                v1 = -1;
                if(this.g != 0) {
                    if(arg10 < this.e[this.i]) {
                    }
                    else {
                        v0 = this.j == 0 ? this.a : this.j;
                        if(arg10 <= this.e[v0 - 1]) {
                            goto label_21;
                        }

                        goto label_18;
                    }
                }

                goto label_60;
            }
            catch(Throwable v10) {
                goto label_63;
            }

        label_18:
            __monitor_exit(this);
            return v1;
            try {
            label_21:
                int v3 = this.i;
                int v4 = -1;
                v0 = -1;
                int v5 = 0;
                while(v3 != this.j) {
                    if(this.e[v3] > arg10) {
                    }
                    else {
                        if((this.d[v3] & 1) != 0) {
                            v0 = v5;
                        }

                        v3 = (v3 + 1) % this.a;
                        ++v5;
                        continue;
                    }

                    break;
                }
            }
            catch(Throwable v10) {
                goto label_63;
            }

            if(v0 == v4) {
                __monitor_exit(this);
                return v1;
            }

            try {
                this.g -= v0;
                this.i = (this.i + v0) % this.a;
                this.h += v0;
                v0_1 = this.b[this.i];
            }
            catch(Throwable v10) {
                goto label_63;
            }

            __monitor_exit(this);
            return v0_1;
        label_63:
            __monitor_exit(this);
            throw v10;
        label_60:
            __monitor_exit(this);
            return v1;
        }

        public boolean a(bm arg4, b arg5) {
            __monitor_enter(this);
            try {
                if(this.g == 0) {
                }
                else {
                    goto label_6;
                }
            }
            catch(Throwable v4) {
                goto label_29;
            }

            boolean v4_1 = false;
            goto label_4;
            try {
            label_6:
                arg4.e = this.e[this.i];
                arg4.c = this.c[this.i];
                arg4.d = this.d[this.i];
                arg5.a = this.b[this.i];
                arg5.b = this.f[this.i];
                v4_1 = true;
            }
            catch(Throwable v4) {
            label_29:
                __monitor_exit(this);
                throw v4;
            }

        label_4:
            __monitor_exit(this);
            return v4_1;
        }

        public long b() {
            long v1;
            __monitor_enter(this);
            try {
                --this.g;
                int v0_1 = this.i;
                this.i = v0_1 + 1;
                ++this.h;
                if(this.i == this.a) {
                    this.i = 0;
                }

                v1 = this.g > 0 ? this.b[this.i] : (((long)this.c[v0_1])) + this.b[v0_1];
            }
            catch(Throwable v0) {
                __monitor_exit(this);
                throw v0;
            }

            __monitor_exit(this);
            return v1;
        }
    }

    final class b {
        public long a;
        public byte[] b;

        b(com.google.ads.interactivemedia.v3.internal.ci$1 arg1) {
            this();
        }

        private b() {
            super();
        }
    }

    private final eq a;
    private final int b;
    private final a c;
    private final LinkedBlockingDeque d;
    private final b e;
    private final fp f;
    private long g;
    private long h;
    private ep i;
    private int j;

    public ci(eq arg2) {
        super();
        this.a = arg2;
        this.b = arg2.b();
        this.c = new a();
        this.d = new LinkedBlockingDeque();
        this.e = new b(null);
        this.f = new fp(32);
        this.j = this.b;
    }

    private int a(int arg3) {
        if(this.j == this.b) {
            this.j = 0;
            this.i = this.a.a();
            this.d.add(this.i);
        }

        return Math.min(arg3, this.b - this.j);
    }

    private void a(long arg5, ByteBuffer arg7, int arg8) {
        while(arg8 > 0) {
            this.b(arg5);
            int v0 = ((int)(arg5 - this.g));
            int v1 = Math.min(arg8, this.b - v0);
            Object v2 = this.d.peek();
            arg7.put(((ep)v2).a, ((ep)v2).a(v0), v1);
            arg5 += ((long)v1);
            arg8 -= v1;
        }
    }

    private void a(long arg6, byte[] arg8, int arg9) {
        int v0;
        for(v0 = 0; v0 < arg9; v0 += v2) {
            this.b(arg6);
            int v1 = ((int)(arg6 - this.g));
            int v2 = Math.min(arg9 - v0, this.b - v1);
            Object v3 = this.d.peek();
            System.arraycopy(((ep)v3).a, ((ep)v3).a(v1), arg8, v0, v2);
            arg6 += ((long)v2);
        }
    }

    private void a(bm arg14, b arg15) {
        int v7;
        long v0 = arg15.a;
        this.a(v0, this.f.a, 1);
        ++v0;
        int v4 = 0;
        int v2 = this.f.a[0];
        int v5 = (v2 & 128) != 0 ? 1 : 0;
        v2 &= 127;
        if(arg14.a.a == null) {
            arg14.a.a = new byte[16];
        }

        this.a(v0, arg14.a.a, v2);
        v0 += ((long)v2);
        if(v5 != 0) {
            this.a(v0, this.f.a, 2);
            v0 += 2;
            this.f.c(0);
            v7 = this.f.g();
        }
        else {
            v7 = 1;
        }

        int[] v2_1 = arg14.a.d;
        if(v2_1 == null || v2_1.length < v7) {
            v2_1 = new int[v7];
        }

        int[] v8 = v2_1;
        v2_1 = arg14.a.e;
        if(v2_1 == null || v2_1.length < v7) {
            v2_1 = new int[v7];
        }

        int[] v9 = v2_1;
        if(v5 != 0) {
            v2 = v7 * 6;
            ci.b(this.f, v2);
            this.a(v0, this.f.a, v2);
            v0 += ((long)v2);
            this.f.c(0);
            while(v4 < v7) {
                v8[v4] = this.f.g();
                v9[v4] = this.f.s();
                ++v4;
            }
        }
        else {
            v8[0] = 0;
            v9[0] = arg14.c - (((int)(v0 - arg15.a)));
        }

        arg14.a.a(v7, v8, v9, arg15.b, arg14.a.a, 1);
        int v0_1 = ((int)(v0 - arg15.a));
        arg15.a += ((long)v0_1);
        arg14.c -= v0_1;
    }

    public int a(cd arg4, int arg5, boolean arg6) {
        int v4 = arg4.a(this.i.a, this.i.a(this.j), this.a(arg5));
        arg5 = -1;
        if(v4 == arg5) {
            if(arg6) {
                return arg5;
            }

            throw new EOFException();
        }

        this.j += v4;
        this.h += ((long)v4);
        return v4;
    }

    public void a() {
        this.c.a();
        this.a.a(this.d.toArray(new ep[this.d.size()]));
        this.d.clear();
        this.g = 0;
        this.h = 0;
        this.i = null;
        this.j = this.b;
    }

    public void a(long arg9, int arg11, long arg12, int arg14, byte[] arg15) {
        this.c.a(arg9, arg11, arg12, arg14, arg15);
    }

    public void a(fp arg6, int arg7) {
        while(arg7 > 0) {
            int v0 = this.a(arg7);
            arg6.a(this.i.a, this.i.a(this.j), v0);
            this.j += v0;
            this.h += ((long)v0);
            arg7 -= v0;
        }
    }

    public boolean a(long arg4) {
        arg4 = this.c.a(arg4);
        if(arg4 == -1) {
            return 0;
        }

        this.b(arg4);
        return 1;
    }

    public boolean a(bm arg3) {
        return this.c.a(arg3, this.e);
    }

    private void b(long arg5) {
        int v5 = (((int)(arg5 - this.g))) / this.b;
        int v6;
        for(v6 = 0; v6 < v5; ++v6) {
            this.a.a(this.d.remove());
            this.g += ((long)this.b);
        }
    }

    private static void b(fp arg1, int arg2) {
        if(arg1.c() < arg2) {
            arg1.a(new byte[arg2], arg2);
        }
    }

    public void b() {
        this.b(this.c.b());
    }

    public boolean b(bm arg4) {
        if(!this.c.a(arg4, this.e)) {
            return 0;
        }

        if(arg4.a()) {
            this.a(arg4, this.e);
        }

        arg4.a(arg4.c);
        this.a(this.e.a, arg4.b, arg4.c);
        this.b(this.c.b());
        return 1;
    }

    public long c() {
        return this.h;
    }
}

