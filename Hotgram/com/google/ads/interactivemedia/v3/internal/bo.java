package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;
import java.util.Arrays;

public abstract class bo extends bq {
    private final a[] a;
    private int[] b;
    private int[] c;
    private a d;
    private int e;
    private long f;

    public bo(bn[] arg4) {
        super();
        this.a = new a[arg4.length];
        int v0;
        for(v0 = 0; v0 < arg4.length; ++v0) {
            this.a[v0] = arg4[v0].a();
        }
    }

    private void a(a arg2) {
        try {
            arg2.b();
            return;
        }
        catch(IOException v2) {
            throw new az(((Throwable)v2));
        }
    }

    protected abstract void a(long arg1);

    protected final int a(long arg7, bk arg9, bm arg10) {
        return this.d.a(this.e, arg7, arg9, arg10);
    }

    protected void a(int arg2, long arg3, boolean arg5) {
        arg3 = this.e(arg3);
        this.d = this.a[this.b[arg2]];
        this.e = this.c[arg2];
        this.d.a(this.e, arg3);
        this.a(arg3);
    }

    protected abstract void a(long arg1, long arg2, boolean arg3);

    protected abstract boolean a(bj arg1);

    private long b(long arg6) {
        long v0 = this.d.b(this.e);
        if(v0 != -9223372036854775808L) {
            this.a(v0);
            return v0;
        }

        return arg6;
    }

    protected final bj b(int arg3) {
        return this.a[this.b[arg3]].a(this.c[arg3]);
    }

    protected final void b(long arg9, long arg11) {
        arg9 = this.e(arg9);
        this.a(this.b(arg9), arg11, this.d.b(this.e, arg9));
    }

    protected final boolean c(long arg19) {
        int[] v17;
        long v15;
        bo v1 = this;
        int v3 = 0;
        int v4 = 1;
        while(v3 < v1.a.length) {
            v4 &= v1.a[v3].a(arg19);
            ++v3;
        }

        if(v4 == 0) {
            return 0;
        }

        v3 = 0;
        v4 = 0;
        while(v3 < v1.a.length) {
            v4 += v1.a[v3].c();
            ++v3;
        }

        int[] v3_1 = new int[v4];
        int[] v4_1 = new int[v4];
        int v7 = v1.a.length;
        long v8 = 0;
        int v5 = 0;
        int v6 = 0;
        while(v5 < v7) {
            a v10 = v1.a[v5];
            int v11 = v10.c();
            long v12 = v8;
            int v8_1 = v6;
            v6 = 0;
            while(v6 < v11) {
                bj v9 = v10.a(v6);
                try {
                    v15 = -1;
                    if(!v1.a(v9)) {
                        goto label_62;
                    }
                }
                catch(b v0) {
                    throw new az(v0);
                }

                v3_1[v8_1] = v5;
                v4_1[v8_1] = v6;
                ++v8_1;
                if(v12 == v15) {
                    goto label_62;
                }
                else {
                    v17 = v3_1;
                    long v2 = v9.e;
                    if(v2 == v15) {
                        v12 = v15;
                    }
                    else if(v2 == -2) {
                    }
                    else {
                        v12 = Math.max(v12, v2);
                        goto label_63;
                    label_62:
                        v17 = v3_1;
                    }
                }

            label_63:
                ++v6;
                v3_1 = v17;
            }

            ++v5;
            v6 = v8_1;
            v8 = v12;
        }

        v1.f = v8;
        v1.b = Arrays.copyOf(v3_1, v6);
        v1.c = Arrays.copyOf(v4_1, v6);
        return 1;
    }

    protected void d(long arg2) {
        arg2 = this.e(arg2);
        this.d.b(arg2);
        this.b(arg2);
    }

    protected long e(long arg1) {
        return arg1;
    }

    protected void g() {
        this.d.c(this.e);
        this.d = null;
    }

    protected long q() {
        return this.d.d();
    }

    protected long r() {
        return this.f;
    }

    protected void s() {
        if(this.d != null) {
            this.a(this.d);
        }
        else {
            int v0 = this.a.length;
            int v1;
            for(v1 = 0; v1 < v0; ++v1) {
                this.a(this.a[v1]);
            }
        }
    }

    protected void t() {
        int v0 = this.a.length;
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            this.a[v1].e();
        }
    }

    protected final int u() {
        return this.c.length;
    }
}

