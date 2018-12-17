package android.support.design.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

class t extends b {
    private u a;
    private int b;
    private int c;

    public t() {
        super();
        this.b = 0;
        this.c = 0;
    }

    public t(Context arg1, AttributeSet arg2) {
        super(arg1, arg2);
        this.b = 0;
        this.c = 0;
    }

    public boolean a(int arg2) {
        if(this.a != null) {
            return this.a.a(arg2);
        }

        this.b = arg2;
        return 0;
    }

    public boolean a(CoordinatorLayout arg1, View arg2, int arg3) {
        this.b(arg1, arg2, arg3);
        if(this.a == null) {
            this.a = new u(arg2);
        }

        this.a.a();
        if(this.b != 0) {
            this.a.a(this.b);
            this.b = 0;
        }

        if(this.c != 0) {
            this.a.b(this.c);
            this.c = 0;
        }

        return 1;
    }

    protected void b(CoordinatorLayout arg1, View arg2, int arg3) {
        arg1.a(arg2, arg3);
    }

    public int b() {
        int v0 = this.a != null ? this.a.b() : 0;
        return v0;
    }
}

