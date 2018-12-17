package android.support.design.widget;

import android.support.v4.view.t;
import android.view.View;

class u {
    private final View a;
    private int b;
    private int c;
    private int d;
    private int e;

    public u(View arg1) {
        super();
        this.a = arg1;
    }

    public void a() {
        this.b = this.a.getTop();
        this.c = this.a.getLeft();
        this.c();
    }

    public boolean a(int arg2) {
        if(this.d != arg2) {
            this.d = arg2;
            this.c();
            return 1;
        }

        return 0;
    }

    public int b() {
        return this.d;
    }

    public boolean b(int arg2) {
        if(this.e != arg2) {
            this.e = arg2;
            this.c();
            return 1;
        }

        return 0;
    }

    private void c() {
        t.e(this.a, this.d - (this.a.getTop() - this.b));
        t.f(this.a, this.e - (this.a.getLeft() - this.c));
    }
}

