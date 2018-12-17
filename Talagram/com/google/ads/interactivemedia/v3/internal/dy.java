package com.google.ads.interactivemedia.v3.internal;

import android.util.Log;

final class dy extends du {
    private final fp b;
    private boolean c;
    private long d;
    private int e;
    private int f;

    public dy(ck arg2) {
        super(arg2);
        arg2.a(bj.a());
        this.b = new fp(10);
    }

    public void a() {
        this.c = false;
    }

    public void a(long arg1, boolean arg3) {
        if(!arg3) {
            return;
        }

        this.c = true;
        this.d = arg1;
        this.e = 0;
        this.f = 0;
    }

    public void a(fp arg8) {
        if(!this.c) {
            return;
        }

        int v0 = arg8.b();
        int v2 = 10;
        if(this.f < v2) {
            int v1 = Math.min(v0, 10 - this.f);
            System.arraycopy(arg8.a, arg8.d(), this.b.a, this.f, v1);
            if(this.f + v1 == v2) {
                this.b.c(0);
                if(73 == this.b.f() && 68 == this.b.f()) {
                    if(51 != this.b.f()) {
                    }
                    else {
                        this.b.d(3);
                        this.e = this.b.r() + v2;
                        goto label_48;
                    }
                }

                Log.w("Id3Reader", "Discarding invalid ID3 tag");
                this.c = false;
                return;
            }
        }

    label_48:
        v0 = Math.min(v0, this.e - this.f);
        this.a.a(arg8, v0);
        this.f += v0;
    }

    public void b() {
        if((this.c) && this.e != 0) {
            if(this.f != this.e) {
            }
            else {
                this.a.a(this.d, 1, this.e, 0, null);
                this.c = false;
            }
        }
    }
}

