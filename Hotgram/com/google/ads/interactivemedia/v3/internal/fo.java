package com.google.ads.interactivemedia.v3.internal;

public final class fo {
    public byte[] a;
    private int b;
    private int c;
    private int d;

    public fo(byte[] arg2) {
        this(arg2, arg2.length);
    }

    public fo() {
        super();
    }

    public fo(byte[] arg1, int arg2) {
        super();
        this.a = arg1;
        this.d = arg2;
    }

    public void a(int arg2) {
        this.b = arg2 / 8;
        this.c = arg2 - this.b * 8;
        this.g();
    }

    public void a(byte[] arg2) {
        this.a(arg2, arg2.length);
    }

    public int a() {
        return (this.d - this.b) * 8 - this.c;
    }

    public void a(byte[] arg1, int arg2) {
        this.a = arg1;
        this.b = 0;
        this.c = 0;
        this.d = arg2;
    }

    public void b(int arg3) {
        this.b += arg3 / 8;
        this.c += arg3 % 8;
        if(this.c > 7) {
            ++this.b;
            this.c += -8;
        }

        this.g();
    }

    public boolean b() {
        boolean v0 = true;
        if(this.c(1) == 1) {
        }
        else {
            v0 = false;
        }

        return v0;
    }

    public int c(int arg9) {
        int v4;
        int v3;
        int v0 = 0;
        if(arg9 == 0) {
            return 0;
        }

        int v1 = arg9 / 8;
        int v2 = 0;
        while(true) {
            v3 = 255;
            v4 = 8;
            if(v0 >= v1) {
                break;
            }

            v4 = this.c != 0 ? (this.a[this.b + 1] & v3) >>> v4 - this.c | (this.a[this.b] & v3) << this.c : this.a[this.b];
            arg9 += -8;
            v2 |= (v3 & v4) << arg9;
            ++this.b;
            ++v0;
        }

        if(arg9 > 0) {
            v0 = this.c + arg9;
            byte v9 = ((byte)(v3 >> 8 - arg9));
            if(v0 > v4) {
                arg9 = v9 & ((this.a[this.b] & v3) << v0 - 8 | (v3 & this.a[this.b + 1]) >> 16 - v0) | v2;
                goto label_61;
            }
            else {
                arg9 = v9 & (this.a[this.b] & v3) >> 8 - v0 | v2;
                if(v0 == v4) {
                label_61:
                    ++this.b;
                }
            }

            v2 = arg9;
            this.c = v0 % v4;
        }

        this.g();
        return v2;
    }

    public boolean c() {
        int v0 = this.b;
        int v1 = this.c;
        boolean v2 = false;
        int v3;
        for(v3 = 0; this.b < this.d; ++v3) {
            if(this.b()) {
                break;
            }
        }

        int v4 = this.b == this.d ? 1 : 0;
        this.b = v0;
        this.c = v1;
        if(v4 == 0 && this.a() >= v3 * 2 + 1) {
            v2 = true;
        }

        return v2;
    }

    public int d() {
        return this.f();
    }

    public int e() {
        int v0 = this.f();
        int v1 = v0 % 2 == 0 ? -1 : 1;
        return v1 * ((v0 + 1) / 2);
    }

    private int f() {
        int v0 = 0;
        int v1;
        for(v1 = 0; !this.b(); ++v1) {
        }

        int v3 = (1 << v1) - 1;
        if(v1 > 0) {
            v0 = this.c(v1);
        }

        return v3 + v0;
    }

    private void g() {
        boolean v0;
        if(this.b < 0 || this.c < 0 || this.c >= 8) {
        label_17:
            v0 = false;
        }
        else {
            if(this.b >= this.d) {
                if(this.b != this.d) {
                }
                else if(this.c == 0) {
                    goto label_15;
                }

                goto label_17;
            }

        label_15:
            v0 = true;
        }

        fe.b(v0);
    }
}

