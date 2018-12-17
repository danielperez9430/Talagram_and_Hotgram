package c.a.a.a;

import android.content.Context;
import c.a.a.a.a.b.p;
import c.a.a.a.a.c.d;
import java.io.File;
import java.util.Collection;

public abstract class i implements Comparable {
    c e;
    h f;
    Context g;
    f h;
    p i;
    final d j;

    public i() {
        super();
        this.f = new h(this);
        this.j = this.getClass().getAnnotation(d.class);
    }

    public abstract String a();

    void a(Context arg3, c arg4, f arg5, p arg6) {
        this.e = arg4;
        this.g = new c.a.a.a.d(arg3, this.b(), this.s());
        this.h = arg5;
        this.i = arg6;
    }

    public int a(i arg4) {
        if(this.b(arg4)) {
            return 1;
        }

        int v2 = -1;
        if(arg4.b(this)) {
            return v2;
        }

        if((this.t()) && !arg4.t()) {
            return 1;
        }

        if(!this.t() && (arg4.t())) {
            return v2;
        }

        return 0;
    }

    protected void a(Object arg1) {
    }

    public abstract String b();

    boolean b(i arg7) {
        if(this.t()) {
            Class[] v0 = this.j.a();
            int v2 = v0.length;
            int v3 = 0;
            while(v3 < v2) {
                if(v0[v3].isAssignableFrom(arg7.getClass())) {
                    return 1;
                }
                else {
                    ++v3;
                    continue;
                }

                return 0;
            }
        }

        return 0;
    }

    protected void b(Object arg1) {
    }

    protected boolean b_() {
        return 1;
    }

    public int compareTo(Object arg1) {
        return this.a(((i)arg1));
    }

    protected abstract Object e();

    final void o() {
        this.f.a(this.e.f(), new Void[]{null});
    }

    protected p p() {
        return this.i;
    }

    public Context q() {
        return this.g;
    }

    public c r() {
        return this.e;
    }

    public String s() {
        return ".Fabric" + File.separator + this.b();
    }

    boolean t() {
        boolean v0 = this.j != null ? true : false;
        return v0;
    }

    protected Collection u() {
        return this.f.c();
    }
}

