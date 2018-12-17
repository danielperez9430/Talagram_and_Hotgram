package android.support.design.widget;

import android.content.Context;
import android.graphics.Rect;
import android.support.v4.b.a;
import android.support.v4.view.ab;
import android.support.v4.view.d;
import android.util.AttributeSet;
import android.view.View$MeasureSpec;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import java.util.List;

abstract class j extends t {
    final Rect a;
    final Rect b;
    private int c;
    private int d;

    public j() {
        super();
        this.a = new Rect();
        this.b = new Rect();
        this.c = 0;
    }

    public j(Context arg1, AttributeSet arg2) {
        super(arg1, arg2);
        this.a = new Rect();
        this.b = new Rect();
        this.c = 0;
    }

    public boolean a(CoordinatorLayout arg13, View arg14, int arg15, int arg16, int arg17, int arg18) {
        int v1 = arg14.getLayoutParams().height;
        int v2 = -1;
        if(v1 == v2 || v1 == -2) {
            View v3 = this.b(arg13.c(arg14));
            if(v3 != null) {
                if((android.support.v4.view.t.t(v3)) && !android.support.v4.view.t.t(arg14)) {
                    android.support.v4.view.t.b(arg14, true);
                    if(android.support.v4.view.t.t(arg14)) {
                        arg14.requestLayout();
                        return 1;
                    }
                }

                int v6 = View$MeasureSpec.getSize(arg17);
                if(v6 == 0) {
                    v6 = arg13.getHeight();
                }

                v6 = v6 - v3.getMeasuredHeight() + this.b(v3);
                v1 = v1 == v2 ? 1073741824 : -2147483648;
                arg13.a(arg14, arg15, arg16, View$MeasureSpec.makeMeasureSpec(v6, v1), arg18);
                return 1;
            }
        }

        return 0;
    }

    float a(View arg1) {
        return 1f;
    }

    final int a() {
        return this.c;
    }

    int b(View arg1) {
        return arg1.getMeasuredHeight();
    }

    abstract View b(List arg1);

    public final void b(int arg1) {
        this.d = arg1;
    }

    protected void b(CoordinatorLayout arg9, View arg10, int arg11) {
        int v9_1;
        View v0 = this.b(arg9.c(arg10));
        if(v0 != null) {
            ViewGroup$LayoutParams v1 = arg10.getLayoutParams();
            Rect v5 = this.a;
            v5.set(arg9.getPaddingLeft() + ((e)v1).leftMargin, v0.getBottom() + ((e)v1).topMargin, arg9.getWidth() - arg9.getPaddingRight() - ((e)v1).rightMargin, arg9.getHeight() + v0.getBottom() - arg9.getPaddingBottom() - ((e)v1).bottomMargin);
            ab v2 = arg9.getLastWindowInsets();
            if(v2 != null && (android.support.v4.view.t.t(((View)arg9))) && !android.support.v4.view.t.t(arg10)) {
                v5.left += v2.a();
                v5.right -= v2.c();
            }

            Rect v9 = this.b;
            d.a(j.c(((e)v1).c), arg10.getMeasuredWidth(), arg10.getMeasuredHeight(), v5, v9, arg11);
            arg11 = this.c(v0);
            arg10.layout(v9.left, v9.top - arg11, v9.right, v9.bottom - arg11);
            v9_1 = v9.top - v0.getBottom();
        }
        else {
            super.b(arg9, arg10, arg11);
            v9_1 = 0;
        }

        this.c = v9_1;
    }

    private static int c(int arg0) {
        if(arg0 == 0) {
            arg0 = 8388659;
        }

        return arg0;
    }

    final int c(View arg3) {
        int v1 = 0;
        if(this.d == 0) {
        }
        else {
            v1 = a.a(((int)(this.a(arg3) * (((float)this.d)))), 0, this.d);
        }

        return v1;
    }

    public final int d() {
        return this.d;
    }
}

