package com.google.ads.interactivemedia.v3.internal;

public final class dr implements cc {
    private static final int a;
    private final long b;
    private final fp c;
    private ds d;
    private boolean e;

    static {
        dr.a = ft.c("ID3");
    }

    public dr() {
        this(0);
    }

    public dr(long arg1) {
        super();
        this.b = arg1;
        this.c = new fp(200);
    }

    public int a(cd arg4, ch arg5) {
        int v4 = arg4.a(this.c.a, 0, 200);
        int v5 = -1;
        if(v4 == v5) {
            return v5;
        }

        this.c.c(0);
        this.c.b(v4);
        if(!this.e) {
            this.d.a(this.b, true);
            this.e = true;
        }

        this.d.a(this.c);
        return 0;
    }

    public void a(ce arg4) {
        this.d = new ds(arg4.d(0), arg4.d(1));
        arg4.f();
        arg4.a(cj.f);
    }

    public boolean a(cd arg13) {
        int v5;
        int v7;
        int v1 = 10;
        fp v0 = new fp(v1);
        fo v2 = new fo(v0.a);
        int v4 = 0;
        while(true) {
            arg13.c(v0.a, 0, v1);
            v0.c(0);
            v7 = 14;
            int v8 = 6;
            if(v0.j() != dr.a) {
                break;
            }

            v5 = (v0.a[v8] & 127) << 21 | (v0.a[7] & 127) << v7 | (v0.a[8] & 127) << 7 | v0.a[9] & 127;
            v4 += v5 + 10;
            arg13.c(v5);
        }

        arg13.a();
        arg13.c(v4);
        v1 = v4;
        while(true) {
            v5 = 0;
            int v6;
            for(v6 = 0; true; v6 += v9) {
                arg13.c(v0.a, 0, 2);
                v0.c(0);
                if((v0.g() & 65526) != 65520) {
                    break;
                }

                ++v5;
                int v10 = 4;
                if(v5 >= v10 && v6 > 188) {
                    return 1;
                }

                arg13.c(v0.a, 0, v10);
                v2.a(v7);
                int v9 = v2.c(13);
                if(v9 <= v8) {
                    return 0;
                }

                arg13.c(v9 - 6);
            }

            arg13.a();
            ++v1;
            if(v1 - v4 >= 8192) {
                return 0;
            }

            arg13.c(v1);
        }
    }

    public void b() {
        this.e = false;
        this.d.a();
    }

    public void c() {
    }
}

