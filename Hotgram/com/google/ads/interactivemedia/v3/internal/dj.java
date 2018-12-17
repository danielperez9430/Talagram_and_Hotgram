package com.google.ads.interactivemedia.v3.internal;

final class dj {
    private final b a;
    private final fp b;
    private final a c;
    private int d;
    private long e;

    dj() {
        super();
        this.a = new b();
        this.b = new fp(282);
        this.c = new a();
        this.d = -1;
    }

    public long a(cd arg7) {
        // Method was not decompiled
    }

    public long a(cd arg5, long arg6) {
        dl.a(arg5);
        b v0 = this.a;
        fp v1;
        for(v1 = this.b; true; v1 = this.b) {
            dl.a(arg5, v0, v1, false);
            if(this.a.c >= arg6) {
                break;
            }

            arg5.b(this.a.h + this.a.i);
            this.e = this.a.c;
            v0 = this.a;
        }

        long v0_1 = 0;
        if(this.e != v0_1) {
            arg5.a();
            long v5 = this.e;
            this.e = v0_1;
            this.d = -1;
            return v5;
        }

        throw new bl();
    }

    public void a() {
        this.a.a();
        this.b.a();
        this.d = -1;
    }

    public boolean a(cd arg7, fp arg8) {
        int v4;
        int v3;
        boolean v2 = arg7 == null || arg8 == null ? false : true;
        fe.b(v2);
        int v2_1 = 0;
        while(v2_1 == 0) {
            if(this.d < 0) {
                if(!dl.a(arg7, this.a, this.b, true)) {
                    return 0;
                }
                else {
                    v3 = this.a.h;
                    if((this.a.b & 1) != 1 || arg8.c() != 0) {
                        v4 = 0;
                    }
                    else {
                        dl.a(this.a, 0, this.c);
                        v4 = this.c.b;
                        v3 += this.c.a;
                    }

                    arg7.b(v3);
                    this.d = v4;
                }
            }

            dl.a(this.a, this.d, this.c);
            v3 = this.d + this.c.b;
            if(this.c.a > 0) {
                arg7.b(arg8.a, arg8.c(), this.c.a);
                arg8.b(arg8.c() + this.c.a);
                v2_1 = this.a.j[v3 - 1] != 255 ? 1 : 0;
            }

            if(v3 == this.a.g) {
                v3 = -1;
            }

            this.d = v3;
        }

        return 1;
    }
}

