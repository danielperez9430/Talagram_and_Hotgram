package android.support.v7.widget;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup$LayoutParams;

public abstract class au {
    protected final i a;
    final Rect b;
    private int c;

    private au(i arg2) {
        super();
        this.c = -2147483648;
        this.b = new Rect();
        this.a = arg2;
    }

    au(i arg1, android.support.v7.widget.au$1 arg2) {
        this(arg1);
    }

    public static au a(i arg1) {
        return new au(arg1) {
            public int a(View arg3) {
                return this.a.h(arg3) - arg3.getLayoutParams().leftMargin;
            }

            public void a(int arg2) {
                this.a.j(arg2);
            }

            public int b(View arg3) {
                return this.a.j(arg3) + arg3.getLayoutParams().rightMargin;
            }

            public int c() {
                return this.a.C();
            }

            public int c(View arg4) {
                this.a.a(arg4, true, this.b);
                return this.b.right;
            }

            public int d() {
                return this.a.A() - this.a.E();
            }

            public int d(View arg4) {
                this.a.a(arg4, true, this.b);
                return this.b.left;
            }

            public int e() {
                return this.a.A();
            }

            public int e(View arg3) {
                ViewGroup$LayoutParams v0 = arg3.getLayoutParams();
                return this.a.f(arg3) + ((j)v0).leftMargin + ((j)v0).rightMargin;
            }

            public int f() {
                return this.a.A() - this.a.C() - this.a.E();
            }

            public int f(View arg3) {
                ViewGroup$LayoutParams v0 = arg3.getLayoutParams();
                return this.a.g(arg3) + ((j)v0).topMargin + ((j)v0).bottomMargin;
            }

            public int g() {
                return this.a.E();
            }

            public int h() {
                return this.a.y();
            }

            public int i() {
                return this.a.z();
            }
        };
    }

    public static au a(i arg0, int arg1) {
        switch(arg1) {
            case 0: {
                goto label_7;
            }
            case 1: {
                goto label_5;
            }
        }

        throw new IllegalArgumentException("invalid orientation");
    label_5:
        return au.b(arg0);
    label_7:
        return au.a(arg0);
    }

    public abstract int a(View arg1);

    public void a() {
        this.c = this.f();
    }

    public abstract void a(int arg1);

    public static au b(i arg1) {
        return new au(arg1) {
            public int a(View arg3) {
                return this.a.i(arg3) - arg3.getLayoutParams().topMargin;
            }

            public void a(int arg2) {
                this.a.k(arg2);
            }

            public int b(View arg3) {
                return this.a.k(arg3) + arg3.getLayoutParams().bottomMargin;
            }

            public int c() {
                return this.a.D();
            }

            public int c(View arg4) {
                this.a.a(arg4, true, this.b);
                return this.b.bottom;
            }

            public int d() {
                return this.a.B() - this.a.F();
            }

            public int d(View arg4) {
                this.a.a(arg4, true, this.b);
                return this.b.top;
            }

            public int e() {
                return this.a.B();
            }

            public int e(View arg3) {
                ViewGroup$LayoutParams v0 = arg3.getLayoutParams();
                return this.a.g(arg3) + ((j)v0).topMargin + ((j)v0).bottomMargin;
            }

            public int f() {
                return this.a.B() - this.a.D() - this.a.F();
            }

            public int f(View arg3) {
                ViewGroup$LayoutParams v0 = arg3.getLayoutParams();
                return this.a.f(arg3) + ((j)v0).leftMargin + ((j)v0).rightMargin;
            }

            public int g() {
                return this.a.F();
            }

            public int h() {
                return this.a.z();
            }

            public int i() {
                return this.a.y();
            }
        };
    }

    public int b() {
        int v0 = -2147483648 == this.c ? 0 : this.f() - this.c;
        return v0;
    }

    public abstract int b(View arg1);

    public abstract int c();

    public abstract int c(View arg1);

    public abstract int d();

    public abstract int d(View arg1);

    public abstract int e();

    public abstract int e(View arg1);

    public abstract int f();

    public abstract int f(View arg1);

    public abstract int g();

    public abstract int h();

    public abstract int i();
}

