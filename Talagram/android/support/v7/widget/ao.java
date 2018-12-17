package android.support.v7.widget;

import android.view.View;

class ao {
    boolean a;
    int b;
    int c;
    int d;
    int e;
    int f;
    int g;
    boolean h;
    boolean i;

    ao() {
        super();
        this.a = true;
        this.f = 0;
        this.g = 0;
    }

    View a(p arg3) {
        View v3 = arg3.c(this.c);
        this.c += this.d;
        return v3;
    }

    boolean a(t arg2) {
        boolean v2 = this.c < 0 || this.c >= arg2.e() ? false : true;
        return v2;
    }

    public String toString() {
        return "LayoutState{mAvailable=" + this.b + ", mCurrentPosition=" + this.c + ", mItemDirection=" + this.d + ", mLayoutDirection=" + this.e + ", mStartLine=" + this.f + ", mEndLine=" + this.g + '}';
    }
}

