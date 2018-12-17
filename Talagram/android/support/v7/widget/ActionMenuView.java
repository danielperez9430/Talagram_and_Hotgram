package android.support.v7.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.support.v7.view.menu.ActionMenuItemView;
import android.support.v7.view.menu.h$b;
import android.support.v7.view.menu.h;
import android.support.v7.view.menu.j;
import android.support.v7.view.menu.p;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View$MeasureSpec;
import android.view.View;
import android.view.ViewDebug$ExportedProperty;
import android.view.ViewGroup$LayoutParams;
import android.view.accessibility.AccessibilityEvent;

public class ActionMenuView extends ap implements b, p {
    public interface a {
        boolean c();

        boolean d();
    }

    class android.support.v7.widget.ActionMenuView$b implements android.support.v7.view.menu.o$a {
        android.support.v7.widget.ActionMenuView$b() {
            super();
        }

        public void a(h arg1, boolean arg2) {
        }

        public boolean a(h arg1) {
            return 0;
        }
    }

    public class c extends android.support.v7.widget.ap$a {
        @ViewDebug$ExportedProperty public boolean a;
        @ViewDebug$ExportedProperty public int b;
        @ViewDebug$ExportedProperty public int c;
        @ViewDebug$ExportedProperty public boolean d;
        @ViewDebug$ExportedProperty public boolean e;
        boolean f;

        public c(Context arg1, AttributeSet arg2) {
            super(arg1, arg2);
        }

        public c(c arg1) {
            super(((ViewGroup$LayoutParams)arg1));
            this.a = arg1.a;
        }

        public c(ViewGroup$LayoutParams arg1) {
            super(arg1);
        }

        public c(int arg1, int arg2) {
            super(arg1, arg2);
            this.a = false;
        }
    }

    class d implements android.support.v7.view.menu.h$a {
        d(ActionMenuView arg1) {
            this.a = arg1;
            super();
        }

        public void a(h arg2) {
            if(this.a.a != null) {
                this.a.a.a(arg2);
            }
        }

        public boolean a(h arg1, MenuItem arg2) {
            boolean v1 = this.a.b == null || !this.a.b.a(arg2) ? false : true;
            return v1;
        }
    }

    public interface e {
        boolean a(MenuItem arg1);
    }

    android.support.v7.view.menu.h$a a;
    e b;
    private h c;
    private Context d;
    private int e;
    private boolean f;
    private android.support.v7.widget.c g;
    private android.support.v7.view.menu.o$a h;
    private boolean i;
    private int j;
    private int k;
    private int l;

    public ActionMenuView(Context arg2) {
        this(arg2, null);
    }

    public ActionMenuView(Context arg3, AttributeSet arg4) {
        super(arg3, arg4);
        this.setBaselineAligned(false);
        float v0 = arg3.getResources().getDisplayMetrics().density;
        this.k = ((int)(56f * v0));
        this.l = ((int)(v0 * 4f));
        this.d = arg3;
        this.e = 0;
    }

    public void a(android.support.v7.view.menu.o$a arg1, android.support.v7.view.menu.h$a arg2) {
        this.h = arg1;
        this.a = arg2;
    }

    public boolean a() {
        return this.f;
    }

    static int a(View arg5, int arg6, int arg7, int arg8, int arg9) {
        ViewGroup$LayoutParams v0 = arg5.getLayoutParams();
        arg8 = View$MeasureSpec.makeMeasureSpec(View$MeasureSpec.getSize(arg8) - arg9, View$MeasureSpec.getMode(arg8));
        View v9 = (arg5 instanceof ActionMenuItemView) ? arg5 : null;
        boolean v1 = true;
        arg9 = v9 == null || !((ActionMenuItemView)v9).b() ? 0 : 1;
        int v3 = 2;
        if(arg7 > 0) {
            if(arg9 != 0 && arg7 < v3) {
                goto label_36;
            }

            arg5.measure(View$MeasureSpec.makeMeasureSpec(arg7 * arg6, -2147483648), arg8);
            arg7 = arg5.getMeasuredWidth();
            int v4 = arg7 / arg6;
            if(arg7 % arg6 != 0) {
                ++v4;
            }

            if(arg9 != 0 && v4 < v3) {
                goto label_37;
            }

            v3 = v4;
        }
        else {
        label_36:
            v3 = 0;
        }

    label_37:
        if((((c)v0).a) || arg9 == 0) {
            v1 = false;
        }
        else {
        }

        ((c)v0).d = v1;
        ((c)v0).b = v3;
        arg5.measure(View$MeasureSpec.makeMeasureSpec(arg6 * v3, 1073741824), arg8);
        return v3;
    }

    public c a(AttributeSet arg3) {
        return new c(this.getContext(), arg3);
    }

    protected c a(ViewGroup$LayoutParams arg2) {
        if(arg2 != null) {
            c v0 = (arg2 instanceof c) ? new c(((c)arg2)) : new c(arg2);
            if(v0.h <= 0) {
                v0.h = 16;
            }

            return v0;
        }

        return this.b();
    }

    public void a(h arg1) {
        this.c = arg1;
    }

    protected boolean a(int arg5) {
        int v0 = 0;
        if(arg5 == 0) {
            return 0;
        }

        View v1 = this.getChildAt(arg5 - 1);
        View v2 = this.getChildAt(arg5);
        if(arg5 < this.getChildCount() && ((v1 instanceof a))) {
            v0 = 0 | ((a)v1).d();
        }

        if(arg5 > 0 && ((v2 instanceof a))) {
            v0 |= ((a)v2).c();
        }

        return ((boolean)v0);
    }

    public boolean a(j arg3) {
        return this.c.a(((MenuItem)arg3), 0);
    }

    protected c b() {
        c v0 = new c(-2, -2);
        v0.h = 16;
        return v0;
    }

    public android.support.v7.widget.ap$a b(AttributeSet arg1) {
        return this.a(arg1);
    }

    protected android.support.v7.widget.ap$a b(ViewGroup$LayoutParams arg1) {
        return this.a(arg1);
    }

    private void c(int arg35, int arg36) {
        // Method was not decompiled
    }

    public c c() {
        c v0 = this.b();
        v0.a = true;
        return v0;
    }

    protected boolean checkLayoutParams(ViewGroup$LayoutParams arg1) {
        boolean v1 = arg1 == null || !(arg1 instanceof c) ? false : true;
        return v1;
    }

    public h d() {
        return this.c;
    }

    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent arg1) {
        return 0;
    }

    public boolean e() {
        boolean v0 = this.g == null || !this.g.d() ? false : true;
        return v0;
    }

    public boolean f() {
        boolean v0 = this.g == null || !this.g.e() ? false : true;
        return v0;
    }

    public boolean g() {
        boolean v0 = this.g == null || !this.g.h() ? false : true;
        return v0;
    }

    protected ViewGroup$LayoutParams generateDefaultLayoutParams() {
        return this.b();
    }

    public ViewGroup$LayoutParams generateLayoutParams(AttributeSet arg1) {
        return this.a(arg1);
    }

    protected ViewGroup$LayoutParams generateLayoutParams(ViewGroup$LayoutParams arg1) {
        return this.a(arg1);
    }

    public Menu getMenu() {
        android.support.v7.view.menu.o$a v1;
        if(this.c == null) {
            Context v0 = this.getContext();
            this.c = new h(v0);
            this.c.a(new d(this));
            this.g = new android.support.v7.widget.c(v0);
            this.g.c(true);
            android.support.v7.widget.c v0_1 = this.g;
            if(this.h != null) {
                v1 = this.h;
            }
            else {
                android.support.v7.widget.ActionMenuView$b v1_1 = new android.support.v7.widget.ActionMenuView$b();
            }

            v0_1.a(v1);
            this.c.a(this.g, this.d);
            this.g.a(this);
        }

        return this.c;
    }

    public Drawable getOverflowIcon() {
        this.getMenu();
        return this.g.c();
    }

    public int getPopupTheme() {
        return this.e;
    }

    public int getWindowAnimations() {
        return 0;
    }

    public boolean h() {
        boolean v0 = this.g == null || !this.g.i() ? false : true;
        return v0;
    }

    public void i() {
        if(this.g != null) {
            this.g.f();
        }
    }

    protected android.support.v7.widget.ap$a j() {
        return this.b();
    }

    public void onConfigurationChanged(Configuration arg2) {
        super.onConfigurationChanged(arg2);
        if(this.g != null) {
            this.g.b(false);
            if(this.g.h()) {
                this.g.e();
                this.g.d();
            }
        }
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.i();
    }

    protected void onLayout(boolean arg18, int arg19, int arg20, int arg21, int arg22) {
        ViewGroup$LayoutParams v7_1;
        View v6_1;
        int v7;
        int v15;
        ActionMenuView v0 = this;
        if(!v0.i) {
            super.onLayout(arg18, arg19, arg20, arg21, arg22);
            return;
        }

        int v1 = this.getChildCount();
        int v2 = (arg22 - arg20) / 2;
        int v3 = this.getDividerWidth();
        int v4 = arg21 - arg19;
        int v5 = v4 - this.getPaddingRight() - this.getPaddingLeft();
        boolean v6 = bs.a(((View)this));
        int v10 = v5;
        v5 = 0;
        int v8 = 0;
        int v9 = 0;
        while(true) {
            int v11 = 8;
            if(v5 >= v1) {
                break;
            }

            View v13 = v0.getChildAt(v5);
            if(v13.getVisibility() == v11) {
            }
            else {
                ViewGroup$LayoutParams v11_1 = v13.getLayoutParams();
                if(((c)v11_1).a) {
                    v8 = v13.getMeasuredWidth();
                    if(v0.a(v5)) {
                        v8 += v3;
                    }

                    int v14 = v13.getMeasuredHeight();
                    if(v6) {
                        v15 = this.getPaddingLeft() + ((c)v11_1).leftMargin;
                        v11 = v15 + v8;
                    }
                    else {
                        v11 = this.getWidth() - this.getPaddingRight() - ((c)v11_1).rightMargin;
                        v15 = v11 - v8;
                    }

                    v7 = v2 - v14 / 2;
                    v13.layout(v15, v7, v11, v14 + v7);
                    v10 -= v8;
                    v8 = 1;
                }
                else {
                    v10 -= v13.getMeasuredWidth() + ((c)v11_1).leftMargin + ((c)v11_1).rightMargin;
                    v0.a(v5);
                    ++v9;
                }
            }

            ++v5;
        }

        if(v1 == 1 && v8 == 0) {
            View v1_1 = v0.getChildAt(0);
            v3 = v1_1.getMeasuredWidth();
            v5 = v1_1.getMeasuredHeight();
            v4 = v4 / 2 - v3 / 2;
            v2 -= v5 / 2;
            v1_1.layout(v4, v2, v3 + v4, v5 + v2);
            return;
        }

        v9 -= v8 ^ 1;
        if(v9 > 0) {
            v7 = v10 / v9;
            v3 = 0;
        }
        else {
            v3 = 0;
            v7 = 0;
        }

        v4 = Math.max(v3, v7);
        if(v6) {
            v5 = this.getWidth() - this.getPaddingRight();
            goto label_91;
        }
        else {
            v5 = this.getPaddingLeft();
            while(true) {
                if(v3 < v1) {
                    v6_1 = v0.getChildAt(v3);
                    v7_1 = v6_1.getLayoutParams();
                    if(v6_1.getVisibility() != v11) {
                        if(((c)v7_1).a) {
                        }
                        else {
                            v5 += ((c)v7_1).leftMargin;
                            v8 = v6_1.getMeasuredWidth();
                            v9 = v6_1.getMeasuredHeight();
                            v10 = v2 - v9 / 2;
                            v6_1.layout(v5, v10, v5 + v8, v9 + v10);
                            v5 += v8 + ((c)v7_1).rightMargin + v4;
                        }
                    }

                    ++v3;
                    continue;
                }

                return;
            }

        label_91:
            while(v3 < v1) {
                v6_1 = v0.getChildAt(v3);
                v7_1 = v6_1.getLayoutParams();
                if(v6_1.getVisibility() != v11) {
                    if(((c)v7_1).a) {
                    }
                    else {
                        v5 -= ((c)v7_1).rightMargin;
                        v8 = v6_1.getMeasuredWidth();
                        v9 = v6_1.getMeasuredHeight();
                        v10 = v2 - v9 / 2;
                        v6_1.layout(v5 - v8, v10, v5, v9 + v10);
                        v5 -= v8 + ((c)v7_1).leftMargin + v4;
                    }
                }

                ++v3;
            }
        }
    }

    protected void onMeasure(int arg6, int arg7) {
        boolean v0 = this.i;
        boolean v1 = View$MeasureSpec.getMode(arg6) == 1073741824 ? true : false;
        this.i = v1;
        if(v0 != this.i) {
            this.j = 0;
        }

        int v0_1 = View$MeasureSpec.getSize(arg6);
        if((this.i) && this.c != null && v0_1 != this.j) {
            this.j = v0_1;
            this.c.b(true);
        }

        v0_1 = this.getChildCount();
        if(!this.i || v0_1 <= 0) {
            int v1_1;
            for(v1_1 = 0; v1_1 < v0_1; ++v1_1) {
                ViewGroup$LayoutParams v2 = this.getChildAt(v1_1).getLayoutParams();
                ((c)v2).rightMargin = 0;
                ((c)v2).leftMargin = 0;
            }

            super.onMeasure(arg6, arg7);
        }
        else {
            this.c(arg6, arg7);
        }
    }

    public void setExpandedActionViewsExclusive(boolean arg2) {
        this.g.d(arg2);
    }

    public void setOnMenuItemClickListener(e arg1) {
        this.b = arg1;
    }

    public void setOverflowIcon(Drawable arg2) {
        this.getMenu();
        this.g.a(arg2);
    }

    public void setOverflowReserved(boolean arg1) {
        this.f = arg1;
    }

    public void setPopupTheme(int arg3) {
        if(this.e != arg3) {
            this.e = arg3;
            this.d = arg3 == 0 ? this.getContext() : new ContextThemeWrapper(this.getContext(), arg3);
        }
    }

    public void setPresenter(android.support.v7.widget.c arg1) {
        this.g = arg1;
        this.g.a(this);
    }
}

