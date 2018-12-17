package androidx.work;

import android.os.Build$VERSION;

public final class c {
    public final class a {
        boolean a;
        boolean b;
        k c;
        boolean d;
        boolean e;
        d f;

        public a() {
            super();
            this.a = false;
            this.b = false;
            this.c = k.a;
            this.d = false;
            this.e = false;
            this.f = new d();
        }

        public c a() {
            return new c(this);
        }
    }

    public static final c a;
    private k b;
    private boolean c;
    private boolean d;
    private boolean e;
    private boolean f;
    private d g;

    static {
        c.a = new a().a();
    }

    public c() {
        super();
    }

    c(a arg3) {
        super();
        this.c = arg3.a;
        boolean v0 = Build$VERSION.SDK_INT < 23 || !arg3.b ? false : true;
        this.d = v0;
        this.b = arg3.c;
        this.e = arg3.d;
        this.f = arg3.e;
        d v3 = Build$VERSION.SDK_INT >= 24 ? arg3.f : new d();
        this.g = v3;
    }

    public c(c arg2) {
        super();
        this.c = arg2.c;
        this.d = arg2.d;
        this.b = arg2.b;
        this.e = arg2.e;
        this.f = arg2.f;
        this.g = arg2.g;
    }

    public k a() {
        return this.b;
    }

    public void a(d arg1) {
        this.g = arg1;
    }

    public void a(k arg1) {
        this.b = arg1;
    }

    public void a(boolean arg1) {
        this.c = arg1;
    }

    public void b(boolean arg1) {
        this.d = arg1;
    }

    public boolean b() {
        return this.c;
    }

    public void c(boolean arg1) {
        this.e = arg1;
    }

    public boolean c() {
        return this.d;
    }

    public void d(boolean arg1) {
        this.f = arg1;
    }

    public boolean d() {
        return this.e;
    }

    public boolean e() {
        return this.f;
    }

    public boolean equals(Object arg5) {
        boolean v0 = true;
        if(this == (((c)arg5))) {
            return 1;
        }

        if(arg5 != null) {
            if(this.getClass() != arg5.getClass()) {
            }
            else {
                if(this.b != ((c)arg5).b || this.c != ((c)arg5).c || this.d != ((c)arg5).d || this.e != ((c)arg5).e || this.f != ((c)arg5).f) {
                label_34:
                    v0 = false;
                }
                else if(this.g != null) {
                    if(this.g.equals(((c)arg5).g)) {
                    }
                    else {
                        goto label_34;
                    }
                }
                else if(((c)arg5).g == null) {
                }
                else {
                    goto label_34;
                }

                return v0;
            }
        }

        return 0;
    }

    public d f() {
        return this.g;
    }

    public boolean g() {
        boolean v0 = this.g == null || this.g.a() <= 0 ? false : true;
        return v0;
    }

    public int hashCode() {
        int v0 = ((((this.b.hashCode() * 31 + this.c) * 31 + this.d) * 31 + this.e) * 31 + this.f) * 31;
        int v1 = this.g != null ? this.g.hashCode() : 0;
        return v0 + v1;
    }
}

