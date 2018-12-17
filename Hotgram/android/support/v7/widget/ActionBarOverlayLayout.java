package android.support.v7.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build$VERSION;
import android.support.v4.view.m;
import android.support.v4.view.o;
import android.support.v4.view.t;
import android.support.v7.a.a$f;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.view.ViewGroup$MarginLayoutParams;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.Window$Callback;
import android.widget.OverScroller;

public class ActionBarOverlayLayout extends ViewGroup implements m, af {
    class android.support.v7.widget.ActionBarOverlayLayout$1 extends AnimatorListenerAdapter {
        android.support.v7.widget.ActionBarOverlayLayout$1(ActionBarOverlayLayout arg1) {
            this.a = arg1;
            super();
        }

        public void onAnimationCancel(Animator arg2) {
            this.a.c = null;
            this.a.b = false;
        }

        public void onAnimationEnd(Animator arg2) {
            this.a.c = null;
            this.a.b = false;
        }
    }

    class android.support.v7.widget.ActionBarOverlayLayout$2 implements Runnable {
        android.support.v7.widget.ActionBarOverlayLayout$2(ActionBarOverlayLayout arg1) {
            this.a = arg1;
            super();
        }

        public void run() {
            this.a.d();
            this.a.c = this.a.a.animate().translationY(0f).setListener(this.a.d);
        }
    }

    class android.support.v7.widget.ActionBarOverlayLayout$3 implements Runnable {
        android.support.v7.widget.ActionBarOverlayLayout$3(ActionBarOverlayLayout arg1) {
            this.a = arg1;
            super();
        }

        public void run() {
            this.a.d();
            this.a.c = this.a.a.animate().translationY(((float)(-this.a.a.getHeight()))).setListener(this.a.d);
        }
    }

    public interface a {
        void a(int arg1);

        void g(boolean arg1);

        void j();

        void k();

        void l();

        void m();
    }

    public class b extends ViewGroup$MarginLayoutParams {
        public b(Context arg1, AttributeSet arg2) {
            super(arg1, arg2);
        }

        public b(int arg1, int arg2) {
            super(arg1, arg2);
        }

        public b(ViewGroup$LayoutParams arg1) {
            super(arg1);
        }
    }

    private final Runnable A;
    private final o B;
    ActionBarContainer a;
    boolean b;
    ViewPropertyAnimator c;
    final AnimatorListenerAdapter d;
    static final int[] e;
    private int f;
    private int g;
    private ContentFrameLayout h;
    private ag i;
    private Drawable j;
    private boolean k;
    private boolean l;
    private boolean m;
    private boolean n;
    private int o;
    private int p;
    private final Rect q;
    private final Rect r;
    private final Rect s;
    private final Rect t;
    private final Rect u;
    private final Rect v;
    private final Rect w;
    private a x;
    private OverScroller y;
    private final Runnable z;

    static {
        ActionBarOverlayLayout.e = new int[]{android.support.v7.a.a$a.actionBarSize, 16842841};
    }

    public ActionBarOverlayLayout(Context arg2) {
        this(arg2, null);
    }

    public ActionBarOverlayLayout(Context arg1, AttributeSet arg2) {
        super(arg1, arg2);
        this.g = 0;
        this.q = new Rect();
        this.r = new Rect();
        this.s = new Rect();
        this.t = new Rect();
        this.u = new Rect();
        this.v = new Rect();
        this.w = new Rect();
        this.d = new android.support.v7.widget.ActionBarOverlayLayout$1(this);
        this.z = new android.support.v7.widget.ActionBarOverlayLayout$2(this);
        this.A = new android.support.v7.widget.ActionBarOverlayLayout$3(this);
        this.a(arg1);
        this.B = new o(((ViewGroup)this));
    }

    public boolean a() {
        return this.l;
    }

    private void a(Context arg5) {
        TypedArray v0 = this.getContext().getTheme().obtainStyledAttributes(ActionBarOverlayLayout.e);
        boolean v1 = false;
        this.f = v0.getDimensionPixelSize(0, 0);
        this.j = v0.getDrawable(1);
        boolean v3 = this.j == null ? true : false;
        this.setWillNotDraw(v3);
        v0.recycle();
        if(arg5.getApplicationInfo().targetSdkVersion < 19) {
            v1 = true;
        }

        this.k = v1;
        this.y = new OverScroller(arg5);
    }

    private ag a(View arg4) {
        if((arg4 instanceof ag)) {
            return arg4;
        }

        if((arg4 instanceof Toolbar)) {
            return ((Toolbar)arg4).getWrapper();
        }

        StringBuilder v1 = new StringBuilder();
        v1.append("Can\'t make a decor toolbar out of ");
        v1.append(arg4.getClass().getSimpleName());
        throw new IllegalStateException(v1.toString());
    }

    private boolean a(float arg10, float arg11) {
        this.y.fling(0, 0, 0, ((int)arg11), 0, 0, -2147483648, 2147483647);
        boolean v10 = this.y.getFinalY() > this.a.getHeight() ? true : false;
        return v10;
    }

    private boolean a(View arg3, Rect arg4, boolean arg5, boolean arg6, boolean arg7, boolean arg8) {
        ViewGroup$LayoutParams v3 = arg3.getLayoutParams();
        if(!arg5 || ((b)v3).leftMargin == arg4.left) {
            arg5 = false;
        }
        else {
            ((b)v3).leftMargin = arg4.left;
            arg5 = true;
        }

        if((arg6) && ((b)v3).topMargin != arg4.top) {
            ((b)v3).topMargin = arg4.top;
            arg5 = true;
        }

        if((arg8) && ((b)v3).rightMargin != arg4.right) {
            ((b)v3).rightMargin = arg4.right;
            arg5 = true;
        }

        if((arg7) && ((b)v3).bottomMargin != arg4.bottom) {
            ((b)v3).bottomMargin = arg4.bottom;
            arg5 = true;
        }

        return arg5;
    }

    public b a(AttributeSet arg3) {
        return new b(this.getContext(), arg3);
    }

    public void a(int arg2) {
        this.c();
        if(arg2 == 2) {
            this.i.f();
        }
        else if(arg2 == 5) {
            this.i.g();
        }
        else if(arg2 != 109) {
        }
        else {
            this.setOverlayMode(true);
        }
    }

    public void a(Menu arg2, android.support.v7.view.menu.o$a arg3) {
        this.c();
        this.i.a(arg2, arg3);
    }

    protected b b() {
        return new b(-1, -1);
    }

    void c() {
        if(this.h == null) {
            this.h = this.findViewById(f.action_bar_activity_content);
            this.a = this.findViewById(f.action_bar_container);
            this.i = this.a(this.findViewById(f.action_bar));
        }
    }

    protected boolean checkLayoutParams(ViewGroup$LayoutParams arg1) {
        return arg1 instanceof b;
    }

    void d() {
        this.removeCallbacks(this.z);
        this.removeCallbacks(this.A);
        if(this.c != null) {
            this.c.cancel();
        }
    }

    public void draw(Canvas arg6) {
        super.draw(arg6);
        if(this.j != null && !this.k) {
            int v0 = this.a.getVisibility() == 0 ? ((int)((((float)this.a.getBottom())) + this.a.getTranslationY() + 0.5f)) : 0;
            this.j.setBounds(0, v0, this.getWidth(), this.j.getIntrinsicHeight() + v0);
            this.j.draw(arg6);
        }
    }

    public boolean e() {
        this.c();
        return this.i.h();
    }

    public boolean f() {
        this.c();
        return this.i.i();
    }

    protected boolean fitSystemWindows(Rect arg9) {
        this.c();
        t.r(((View)this));
        boolean v0 = this.a(this.a, arg9, true, true, false, true);
        this.t.set(arg9);
        bs.a(((View)this), this.t, this.q);
        if(!this.u.equals(this.t)) {
            this.u.set(this.t);
            v0 = true;
        }

        if(!this.r.equals(this.q)) {
            this.r.set(this.q);
            v0 = true;
        }

        if(v0) {
            this.requestLayout();
        }

        return 1;
    }

    public boolean g() {
        this.c();
        return this.i.j();
    }

    protected ViewGroup$LayoutParams generateDefaultLayoutParams() {
        return this.b();
    }

    public ViewGroup$LayoutParams generateLayoutParams(AttributeSet arg1) {
        return this.a(arg1);
    }

    protected ViewGroup$LayoutParams generateLayoutParams(ViewGroup$LayoutParams arg2) {
        return new b(arg2);
    }

    public int getActionBarHideOffset() {
        int v0 = this.a != null ? -(((int)this.a.getTranslationY())) : 0;
        return v0;
    }

    public int getNestedScrollAxes() {
        return this.B.a();
    }

    public CharSequence getTitle() {
        this.c();
        return this.i.e();
    }

    public boolean h() {
        this.c();
        return this.i.k();
    }

    public boolean i() {
        this.c();
        return this.i.l();
    }

    public void j() {
        this.c();
        this.i.m();
    }

    public void k() {
        this.c();
        this.i.n();
    }

    private void l() {
        this.d();
        this.postDelayed(this.z, 600);
    }

    private void m() {
        this.d();
        this.postDelayed(this.A, 600);
    }

    private void n() {
        this.d();
        this.z.run();
    }

    private void o() {
        this.d();
        this.A.run();
    }

    protected void onConfigurationChanged(Configuration arg1) {
        super.onConfigurationChanged(arg1);
        this.a(this.getContext());
        t.s(((View)this));
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.d();
    }

    protected void onLayout(boolean arg5, int arg6, int arg7, int arg8, int arg9) {
        int v5 = this.getChildCount();
        arg6 = this.getPaddingLeft();
        this.getPaddingRight();
        arg7 = this.getPaddingTop();
        this.getPaddingBottom();
        for(arg8 = 0; arg8 < v5; ++arg8) {
            View v9 = this.getChildAt(arg8);
            if(v9.getVisibility() != 8) {
                ViewGroup$LayoutParams v0 = v9.getLayoutParams();
                int v1 = v9.getMeasuredWidth();
                int v2 = v9.getMeasuredHeight();
                int v3 = ((b)v0).leftMargin + arg6;
                int v0_1 = ((b)v0).topMargin + arg7;
                v9.layout(v3, v0_1, v1 + v3, v2 + v0_1);
            }
        }
    }

    protected void onMeasure(int arg14, int arg15) {
        Rect v4_1;
        int v5;
        this.c();
        this.measureChildWithMargins(this.a, arg14, 0, arg15, 0);
        ViewGroup$LayoutParams v0 = this.a.getLayoutParams();
        int v1 = Math.max(0, this.a.getMeasuredWidth() + ((b)v0).leftMargin + ((b)v0).rightMargin);
        int v0_1 = Math.max(0, this.a.getMeasuredHeight() + ((b)v0).topMargin + ((b)v0).bottomMargin);
        int v3 = View.combineMeasuredStates(0, this.a.getMeasuredState());
        int v4 = (t.r(((View)this)) & 256) != 0 ? 1 : 0;
        if(v4 != 0) {
            v5 = this.f;
            if((this.m) && this.a.getTabContainer() != null) {
                v5 += this.f;
            }
        }
        else if(this.a.getVisibility() != 8) {
            v5 = this.a.getMeasuredHeight();
        }
        else {
            v5 = 0;
        }

        this.s.set(this.q);
        this.v.set(this.t);
        if((this.l) || v4 != 0) {
            this.v.top += v5;
            v4_1 = this.v;
        }
        else {
            this.s.top += v5;
            v4_1 = this.s;
        }

        v4_1.bottom = v4_1.bottom;
        this.a(this.h, this.s, true, true, true, true);
        if(!this.w.equals(this.v)) {
            this.w.set(this.v);
            this.h.a(this.v);
        }

        this.measureChildWithMargins(this.h, arg14, 0, arg15, 0);
        ViewGroup$LayoutParams v2 = this.h.getLayoutParams();
        v1 = Math.max(v1, this.h.getMeasuredWidth() + ((b)v2).leftMargin + ((b)v2).rightMargin);
        v0_1 = Math.max(v0_1, this.h.getMeasuredHeight() + ((b)v2).topMargin + ((b)v2).bottomMargin);
        int v2_1 = View.combineMeasuredStates(v3, this.h.getMeasuredState());
        this.setMeasuredDimension(View.resolveSizeAndState(Math.max(v1 + (this.getPaddingLeft() + this.getPaddingRight()), this.getSuggestedMinimumWidth()), arg14, v2_1), View.resolveSizeAndState(Math.max(v0_1 + (this.getPaddingTop() + this.getPaddingBottom()), this.getSuggestedMinimumHeight()), arg15, v2_1 << 16));
    }

    public boolean onNestedFling(View arg1, float arg2, float arg3, boolean arg4) {
        if(this.n) {
            if(!arg4) {
            }
            else {
                if(this.a(arg2, arg3)) {
                    this.o();
                }
                else {
                    this.n();
                }

                this.b = true;
                return 1;
            }
        }

        return 0;
    }

    public boolean onNestedPreFling(View arg1, float arg2, float arg3) {
        return 0;
    }

    public void onNestedPreScroll(View arg1, int arg2, int arg3, int[] arg4) {
    }

    public void onNestedScroll(View arg1, int arg2, int arg3, int arg4, int arg5) {
        this.o += arg3;
        this.setActionBarHideOffset(this.o);
    }

    public void onNestedScrollAccepted(View arg2, View arg3, int arg4) {
        this.B.a(arg2, arg3, arg4);
        this.o = this.getActionBarHideOffset();
        this.d();
        if(this.x != null) {
            this.x.l();
        }
    }

    public boolean onStartNestedScroll(View arg1, View arg2, int arg3) {
        if((arg3 & 2) != 0) {
            if(this.a.getVisibility() != 0) {
            }
            else {
                return this.n;
            }
        }

        return 0;
    }

    public void onStopNestedScroll(View arg2) {
        if((this.n) && !this.b) {
            if(this.o <= this.a.getHeight()) {
                this.l();
            }
            else {
                this.m();
            }
        }

        if(this.x != null) {
            this.x.m();
        }
    }

    public void onWindowSystemUiVisibilityChanged(int arg5) {
        if(Build$VERSION.SDK_INT >= 16) {
            super.onWindowSystemUiVisibilityChanged(arg5);
        }

        this.c();
        int v0 = this.p ^ arg5;
        this.p = arg5;
        int v2 = 0;
        int v1 = (arg5 & 4) == 0 ? 1 : 0;
        if((arg5 & 256) != 0) {
            v2 = 1;
        }

        if(this.x != null) {
            this.x.g(v2 ^ 1);
            if(v1 == 0) {
                if(v2 == 0) {
                }
                else {
                    this.x.k();
                    goto label_31;
                }
            }

            this.x.j();
        }

    label_31:
        if((v0 & 256) != 0 && this.x != null) {
            t.s(((View)this));
        }
    }

    protected void onWindowVisibilityChanged(int arg2) {
        super.onWindowVisibilityChanged(arg2);
        this.g = arg2;
        if(this.x != null) {
            this.x.a(arg2);
        }
    }

    public void setActionBarHideOffset(int arg2) {
        this.d();
        this.a.setTranslationY(((float)(-Math.max(0, Math.min(arg2, this.a.getHeight())))));
    }

    public void setActionBarVisibilityCallback(a arg2) {
        this.x = arg2;
        if(this.getWindowToken() != null) {
            this.x.a(this.g);
            if(this.p != 0) {
                this.onWindowSystemUiVisibilityChanged(this.p);
                t.s(((View)this));
            }
        }
    }

    public void setHasNonEmbeddedTabs(boolean arg1) {
        this.m = arg1;
    }

    public void setHideOnContentScrollEnabled(boolean arg2) {
        if(arg2 != this.n) {
            this.n = arg2;
            if(!arg2) {
                this.d();
                this.setActionBarHideOffset(0);
            }
        }
    }

    public void setIcon(int arg2) {
        this.c();
        this.i.a(arg2);
    }

    public void setIcon(Drawable arg2) {
        this.c();
        this.i.a(arg2);
    }

    public void setLogo(int arg2) {
        this.c();
        this.i.b(arg2);
    }

    public void setOverlayMode(boolean arg2) {
        this.l = arg2;
        arg2 = !arg2 || this.getContext().getApplicationInfo().targetSdkVersion >= 19 ? false : true;
        this.k = arg2;
    }

    public void setShowingForActionMode(boolean arg1) {
    }

    public void setUiOptions(int arg1) {
    }

    public void setWindowCallback(Window$Callback arg2) {
        this.c();
        this.i.a(arg2);
    }

    public void setWindowTitle(CharSequence arg2) {
        this.c();
        this.i.a(arg2);
    }

    public boolean shouldDelayChildPressedState() {
        return 0;
    }
}

