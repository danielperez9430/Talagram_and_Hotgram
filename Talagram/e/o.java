package e;

import javax.annotation.Nullable;

final class o {
    final byte[] a;
    int b;
    int c;
    boolean d;
    boolean e;
    o f;
    o g;

    o() {
        super();
        this.a = new byte[8192];
        this.e = true;
        this.d = false;
    }

    o(byte[] arg1, int arg2, int arg3, boolean arg4, boolean arg5) {
        super();
        this.a = arg1;
        this.b = arg2;
        this.c = arg3;
        this.d = arg4;
        this.e = arg5;
    }

    o a() {
        this.d = true;
        return new o(this.a, this.b, this.c, true, false);
    }

    public o a(int arg6) {
        o v0;
        if(arg6 > 0 && arg6 <= this.c - this.b) {
            if(arg6 >= 1024) {
                v0 = this.a();
            }
            else {
                v0 = p.a();
                System.arraycopy(this.a, this.b, v0.a, 0, arg6);
            }

            v0.c = v0.b + arg6;
            this.b += arg6;
            this.g.a(v0);
            return v0;
        }

        throw new IllegalArgumentException();
    }

    public o a(o arg2) {
        arg2.g = this;
        arg2.f = this.f;
        this.f.g = arg2;
        this.f = arg2;
        return arg2;
    }

    public void a(o arg6, int arg7) {
        if(arg6.e) {
            int v1 = 8192;
            if(arg6.c + arg7 > v1) {
                if(arg6.d) {
                    throw new IllegalArgumentException();
                }
                else if(arg6.c + arg7 - arg6.b <= v1) {
                    System.arraycopy(arg6.a, arg6.b, arg6.a, 0, arg6.c - arg6.b);
                    arg6.c -= arg6.b;
                    arg6.b = 0;
                }
                else {
                    throw new IllegalArgumentException();
                }
            }

            System.arraycopy(this.a, this.b, arg6.a, arg6.c, arg7);
            arg6.c += arg7;
            this.b += arg7;
            return;
        }

        throw new IllegalArgumentException();
    }

    @Nullable public o b() {
        o v1 = null;
        o v0 = this.f != this ? this.f : v1;
        this.g.f = this.f;
        this.f.g = this.g;
        this.f = v1;
        this.g = v1;
        return v0;
    }

    public void c() {
        if(this.g != this) {
            if(!this.g.e) {
                return;
            }

            int v0 = this.c - this.b;
            int v1 = 8192 - this.g.c;
            int v2 = this.g.d ? 0 : this.g.b;
            if(v0 > v1 + v2) {
                return;
            }

            this.a(this.g, v0);
            this.b();
            p.a(this);
            return;
        }

        throw new IllegalStateException();
    }
}

