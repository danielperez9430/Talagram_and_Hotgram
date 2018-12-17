package android.support.v7.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.support.v4.view.t;
import android.support.v4.view.x;
import android.support.v4.view.y;
import android.support.v7.a.a$j;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.MotionEvent;
import android.view.View$MeasureSpec;
import android.view.View;
import android.view.ViewGroup;

abstract class a extends ViewGroup {
    public class android.support.v7.widget.a$a implements y {
        int a;
        private boolean c;

        protected android.support.v7.widget.a$a(a arg1) {
            this.b = arg1;
            super();
            this.c = false;
        }

        public android.support.v7.widget.a$a a(x arg2, int arg3) {
            this.b.f = arg2;
            this.a = arg3;
            return this;
        }

        public void a(View arg2) {
            a.a(this.b, 0);
            this.c = false;
        }

        public void b(View arg2) {
            if(this.c) {
                return;
            }

            this.b.f = null;
            a.b(this.b, this.a);
        }

        public void c(View arg1) {
            this.c = true;
        }
    }

    protected final android.support.v7.widget.a$a a;
    protected final Context b;
    protected ActionMenuView c;
    protected c d;
    protected int e;
    protected x f;
    private boolean g;
    private boolean h;

    a(Context arg2) {
        this(arg2, null);
    }

    a(Context arg2, AttributeSet arg3) {
        this(arg2, arg3, 0);
    }

    a(Context arg3, AttributeSet arg4, int arg5) {
        super(arg3, arg4, arg5);
        this.a = new android.support.v7.widget.a$a(this);
        TypedValue v4 = new TypedValue();
        this.b = !arg3.getTheme().resolveAttribute(android.support.v7.a.a$a.actionBarPopupTheme, v4, true) || v4.resourceId == 0 ? arg3 : new ContextThemeWrapper(arg3, v4.resourceId);
    }

    protected static int a(int arg0, int arg1, boolean arg2) {
        if(arg2) {
            arg0 -= arg1;
        }
        else {
            arg0 += arg1;
        }

        return arg0;
    }

    static void a(a arg0, int arg1) {
        super.setVisibility(arg1);
    }

    protected int a(View arg2, int arg3, int arg4, int arg5) {
        arg2.measure(View$MeasureSpec.makeMeasureSpec(arg3, -2147483648), arg4);
        return Math.max(0, arg3 - arg2.getMeasuredWidth() - arg5);
    }

    protected int a(View arg3, int arg4, int arg5, int arg6, boolean arg7) {
        int v0 = arg3.getMeasuredWidth();
        int v1 = arg3.getMeasuredHeight();
        arg5 += (arg6 - v1) / 2;
        if(arg7) {
            arg3.layout(arg4 - v0, arg5, arg4, v1 + arg5);
        }
        else {
            arg3.layout(arg4, arg5, arg4 + v0, v1 + arg5);
        }

        if(arg7) {
            v0 = -v0;
        }

        return v0;
    }

    public x a(int arg3, long arg4) {
        x v0;
        if(this.f != null) {
            this.f.b();
        }

        if(arg3 == 0) {
            if(this.getVisibility() != 0) {
                this.setAlpha(0f);
            }

            v0 = t.m(((View)this)).a(1f);
        }
        else {
            v0 = t.m(((View)this)).a(0f);
        }

        v0.a(arg4);
        v0.a(this.a.a(v0, arg3));
        return v0;
    }

    public boolean a() {
        if(this.d != null) {
            return this.d.d();
        }

        return 0;
    }

    static void b(a arg0, int arg1) {
        super.setVisibility(arg1);
    }

    public int getAnimatedVisibility() {
        if(this.f != null) {
            return this.a.a;
        }

        return this.getVisibility();
    }

    public int getContentHeight() {
        return this.e;
    }

    protected void onConfigurationChanged(Configuration arg6) {
        super.onConfigurationChanged(arg6);
        TypedArray v0 = this.getContext().obtainStyledAttributes(null, j.ActionBar, android.support.v7.a.a$a.actionBarStyle, 0);
        this.setContentHeight(v0.getLayoutDimension(j.ActionBar_height, 0));
        v0.recycle();
        if(this.d != null) {
            this.d.a(arg6);
        }
    }

    public boolean onHoverEvent(MotionEvent arg6) {
        int v0 = arg6.getActionMasked();
        int v2 = 9;
        if(v0 == v2) {
            this.h = false;
        }

        if(!this.h) {
            boolean v6 = super.onHoverEvent(arg6);
            if(v0 == v2 && !v6) {
                this.h = true;
            }
        }

        if(v0 == 10 || v0 == 3) {
            this.h = false;
        }

        return 1;
    }

    public boolean onTouchEvent(MotionEvent arg5) {
        int v0 = arg5.getActionMasked();
        if(v0 == 0) {
            this.g = false;
        }

        if(!this.g) {
            boolean v5 = super.onTouchEvent(arg5);
            if(v0 == 0 && !v5) {
                this.g = true;
            }
        }

        if(v0 == 1 || v0 == 3) {
            this.g = false;
        }

        return 1;
    }

    public void setContentHeight(int arg1) {
        this.e = arg1;
        this.requestLayout();
    }

    public void setVisibility(int arg2) {
        if(arg2 != this.getVisibility()) {
            if(this.f != null) {
                this.f.b();
            }

            super.setVisibility(arg2);
        }
    }
}

