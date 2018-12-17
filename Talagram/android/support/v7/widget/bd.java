package android.support.v7.widget;

import android.view.View;

public abstract class bd extends f {
    boolean h;

    public bd() {
        super();
        this.h = true;
    }

    public final void a(w arg1, boolean arg2) {
        this.d(arg1, arg2);
        this.f(arg1);
    }

    public abstract boolean a(w arg1);

    public abstract boolean a(w arg1, int arg2, int arg3, int arg4, int arg5);

    public boolean a(w arg7, c arg8, c arg9) {
        int v2 = arg8.a;
        int v3 = arg8.b;
        View v8 = arg7.itemView;
        int v0 = arg9 == null ? v8.getLeft() : arg9.a;
        int v4 = v0;
        int v9 = arg9 == null ? v8.getTop() : arg9.b;
        int v5 = v9;
        if(!arg7.isRemoved() && (v2 != v4 || v3 != v5)) {
            v8.layout(v4, v5, v8.getWidth() + v4, v8.getHeight() + v5);
            return this.a(arg7, v2, v3, v4, v5);
        }

        return this.a(arg7);
    }

    public abstract boolean a(w arg1, w arg2, int arg3, int arg4, int arg5, int arg6);

    public boolean a(w arg8, w arg9, c arg10, c arg11) {
        int v5;
        int v6;
        int v3 = arg10.a;
        int v4 = arg10.b;
        if(arg9.shouldIgnore()) {
            int v11 = arg10.a;
            v6 = arg10.b;
            v5 = v11;
        }
        else {
            v5 = arg11.a;
            v6 = arg11.b;
        }

        return this.a(arg8, arg9, v3, v4, v5, v6);
    }

    public final void b(w arg1, boolean arg2) {
        this.c(arg1, arg2);
    }

    public abstract boolean b(w arg1);

    public boolean b(w arg9, c arg10, c arg11) {
        if(arg10 != null && (arg10.a != arg11.a || arg10.b != arg11.b)) {
            return this.a(arg9, arg10.a, arg10.b, arg11.a, arg11.b);
        }

        return this.b(arg9);
    }

    public void c(w arg1, boolean arg2) {
    }

    public boolean c(w arg7, c arg8, c arg9) {
        if(arg8.a == arg9.a) {
            if(arg8.b != arg9.b) {
            }
            else {
                this.j(arg7);
                return 0;
            }
        }

        return this.a(arg7, arg8.a, arg8.b, arg9.a, arg9.b);
    }

    public void d(w arg1, boolean arg2) {
    }

    public boolean h(w arg2) {
        boolean v2 = !this.h || (arg2.isInvalid()) ? true : false;
        return v2;
    }

    public final void i(w arg1) {
        this.p(arg1);
        this.f(arg1);
    }

    public final void j(w arg1) {
        this.t(arg1);
        this.f(arg1);
    }

    public final void k(w arg1) {
        this.r(arg1);
        this.f(arg1);
    }

    public final void l(w arg1) {
        this.o(arg1);
    }

    public final void m(w arg1) {
        this.s(arg1);
    }

    public final void n(w arg1) {
        this.q(arg1);
    }

    public void o(w arg1) {
    }

    public void p(w arg1) {
    }

    public void q(w arg1) {
    }

    public void r(w arg1) {
    }

    public void s(w arg1) {
    }

    public void t(w arg1) {
    }
}

