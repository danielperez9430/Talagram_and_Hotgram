package com.google.ads.interactivemedia.v3.internal;

public class di implements cc {
    private dm a;

    public di() {
        super();
    }

    public int a(cd arg2, ch arg3) {
        return this.a.a(arg2, arg3);
    }

    public void a(ce arg3) {
        ck v0 = arg3.d(0);
        arg3.f();
        this.a.a(arg3, v0);
    }

    public boolean a(cd arg7) {
        do v7_2;
        try {
            fp v1 = new fp(new byte[27], 0);
            b v2 = new b();
            if((dl.a(arg7, v2, v1, true)) && (v2.b & 2) == 2) {
                int v4 = 7;
                if(v2.i < v4) {
                }
                else {
                    v1.a();
                    arg7.c(v1.a, 0, v4);
                    if(dh.a(v1)) {
                        dh v7_1 = new dh();
                    }
                    else {
                        v1.a();
                        if(do.a(v1)) {
                            v7_2 = new do();
                        }
                        else {
                            return 0;
                        }
                    }

                    this.a = ((dm)v7_2);
                    return 1;
                }
            }

            return 0;
        }
        catch(Throwable v7) {
            throw v7;
        }
        catch(bl ) {
            return 0;
        }

        return 1;
    }

    public void b() {
        this.a.b();
    }

    public void c() {
    }
}

