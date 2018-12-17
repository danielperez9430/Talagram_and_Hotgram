package com.google.ads.interactivemedia.v3.internal;

import java.util.Arrays;

public final class ev implements eq {
    private final int a;
    private final byte[] b;
    private int c;
    private int d;
    private ep[] e;

    public ev(int arg2) {
        this(arg2, 0);
    }

    public ev(int arg6, int arg7) {
        super();
        int v0 = 0;
        boolean v1 = true;
        boolean v2 = arg6 > 0 ? true : false;
        fe.a(v2);
        if(arg7 >= 0) {
        }
        else {
            v1 = false;
        }

        fe.a(v1);
        this.a = arg6;
        this.d = arg7;
        this.e = new ep[arg7 + 100];
        if(arg7 > 0) {
            this.b = new byte[arg7 * arg6];
            while(v0 < arg7) {
                this.e[v0] = new ep(this.b, v0 * arg6);
                ++v0;
            }
        }
        else {
            this.b = null;
        }
    }

    public ep a() {
        ep v0_2;
        __monitor_enter(this);
        try {
            ++this.c;
            if(this.d > 0) {
                ep[] v0_1 = this.e;
                int v1 = this.d - 1;
                this.d = v1;
                v0_2 = v0_1[v1];
                this.e[this.d] = null;
            }
            else {
                v0_2 = new ep(new byte[this.a], 0);
            }
        }
        catch(Throwable v0) {
            __monitor_exit(this);
            throw v0;
        }

        __monitor_exit(this);
        return v0_2;
    }

    public void a(int arg7) {
        int v0;
        __monitor_enter(this);
        try {
            v0 = 0;
            arg7 = Math.max(0, ft.a(arg7, this.a) - this.c);
            if(arg7 < this.d) {
                goto label_11;
            }
        }
        catch(Throwable v7) {
            goto label_52;
        }

        __monitor_exit(this);
        return;
        try {
        label_11:
            if(this.b != null) {
                int v1 = this.d - 1;
                while(v0 <= v1) {
                    ep v2 = this.e[v0];
                    if(v2.a == this.b) {
                        ++v0;
                    }
                    else {
                        ep v3 = this.e[v1];
                        if(v3.a != this.b) {
                            --v1;
                        }
                        else {
                            this.e[v0] = v3;
                            this.e[v1] = v2;
                            --v1;
                            ++v0;
                        }
                    }
                }

                arg7 = Math.max(arg7, v0);
                if(arg7 >= this.d) {
                    goto label_42;
                }
            }

            goto label_44;
        }
        catch(Throwable v7) {
            goto label_52;
        }

    label_42:
        __monitor_exit(this);
        return;
        try {
        label_44:
            Arrays.fill(this.e, arg7, this.d, null);
            this.d = arg7;
        }
        catch(Throwable v7) {
        label_52:
            __monitor_exit(this);
            throw v7;
        }

        __monitor_exit(this);
    }

    public void a(ep arg4) {
        __monitor_enter(this);
        try {
            boolean v0 = arg4.a == this.b || arg4.a.length == this.a ? true : false;
            fe.a(v0);
            --this.c;
            if(this.d == this.e.length) {
                this.e = Arrays.copyOf(this.e, this.e.length * 2);
            }

            ep[] v0_1 = this.e;
            int v1 = this.d;
            this.d = v1 + 1;
            v0_1[v1] = arg4;
            this.notifyAll();
        }
        catch(Throwable v4) {
            __monitor_exit(this);
            throw v4;
        }

        __monitor_exit(this);
    }

    public void a(ep[] arg8) {
        __monitor_enter(this);
        try {
            if(this.d + arg8.length >= this.e.length) {
                this.e = Arrays.copyOf(this.e, Math.max(this.e.length * 2, this.d + arg8.length));
            }

            int v0 = arg8.length;
            int v2;
            for(v2 = 0; v2 < v0; ++v2) {
                ep v3 = arg8[v2];
                boolean v4 = v3.a == this.b || v3.a.length == this.a ? true : false;
                fe.a(v4);
                ep[] v4_1 = this.e;
                int v5 = this.d;
                this.d = v5 + 1;
                v4_1[v5] = v3;
            }

            this.c -= arg8.length;
            this.notifyAll();
        }
        catch(Throwable v8) {
            goto label_49;
        }

        __monitor_exit(this);
        return;
    label_49:
        __monitor_exit(this);
        throw v8;
    }

    public int b() {
        return this.a;
    }

    public void b(int arg2) {
        __monitor_enter(this);
        try {
            while(this.c() > arg2) {
                this.wait();
            }
        }
        catch(Throwable v2) {
            __monitor_exit(this);
            throw v2;
        }

        __monitor_exit(this);
    }

    public int c() {
        int v1;
        int v0_1;
        __monitor_enter(this);
        try {
            v0_1 = this.c;
            v1 = this.a;
        }
        catch(Throwable v0) {
            __monitor_exit(this);
            throw v0;
        }

        __monitor_exit(this);
        return v0_1 * v1;
    }
}

