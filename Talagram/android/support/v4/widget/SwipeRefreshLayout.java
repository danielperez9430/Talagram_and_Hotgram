package android.support.v4.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build$VERSION;
import android.support.v4.view.j;
import android.support.v4.view.l;
import android.support.v4.view.m;
import android.support.v4.view.o;
import android.support.v4.view.t;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View$MeasureSpec;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.Animation$AnimationListener;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Transformation;
import android.widget.AbsListView;
import android.widget.ListView;

public class SwipeRefreshLayout extends ViewGroup implements j, m {
    class android.support.v4.widget.SwipeRefreshLayout$1 implements Animation$AnimationListener {
        android.support.v4.widget.SwipeRefreshLayout$1(SwipeRefreshLayout arg1) {
            this.a = arg1;
            super();
        }

        public void onAnimationEnd(Animation arg2) {
            if(this.a.b) {
                this.a.k.setAlpha(255);
                this.a.k.start();
                if((this.a.l) && this.a.a != null) {
                    this.a.a.o_();
                }

                this.a.c = this.a.e.getTop();
            }
            else {
                this.a.a();
            }
        }

        public void onAnimationRepeat(Animation arg1) {
        }

        public void onAnimationStart(Animation arg1) {
        }
    }

    class android.support.v4.widget.SwipeRefreshLayout$6 extends Animation {
        android.support.v4.widget.SwipeRefreshLayout$6(SwipeRefreshLayout arg1) {
            this.a = arg1;
            super();
        }

        public void applyTransformation(float arg3, Transformation arg4) {
            int v4 = !this.a.m ? this.a.i - Math.abs(this.a.h) : this.a.i;
            this.a.setTargetOffsetTopAndBottom(this.a.f + (((int)((((float)(v4 - this.a.f))) * arg3))) - this.a.e.getTop());
            this.a.k.b(1f - arg3);
        }
    }

    class android.support.v4.widget.SwipeRefreshLayout$7 extends Animation {
        android.support.v4.widget.SwipeRefreshLayout$7(SwipeRefreshLayout arg1) {
            this.a = arg1;
            super();
        }

        public void applyTransformation(float arg1, Transformation arg2) {
            this.a.a(arg1);
        }
    }

    public interface a {
        boolean a(SwipeRefreshLayout arg1, View arg2);
    }

    public interface b {
        void o_();
    }

    private boolean A;
    private int B;
    private boolean C;
    private final DecelerateInterpolator D;
    private static final int[] E = null;
    private int F;
    private Animation G;
    private Animation H;
    private Animation I;
    private Animation J;
    private Animation K;
    private int L;
    private a M;
    private Animation$AnimationListener N;
    private final Animation O;
    private final Animation P;
    b a;
    boolean b;
    int c;
    boolean d;
    c e;
    protected int f;
    float g;
    protected int h;
    int i;
    int j;
    d k;
    boolean l;
    boolean m;
    private static final String n = "SwipeRefreshLayout";
    private View o;
    private int p;
    private float q;
    private float r;
    private final o s;
    private final l t;
    private final int[] u;
    private final int[] v;
    private boolean w;
    private int x;
    private float y;
    private float z;

    static {
        SwipeRefreshLayout.E = new int[]{16842766};
    }

    public SwipeRefreshLayout(Context arg2) {
        this(arg2, null);
    }

    public SwipeRefreshLayout(Context arg5, AttributeSet arg6) {
        super(arg5, arg6);
        this.b = false;
        this.q = -1f;
        this.u = new int[2];
        this.v = new int[2];
        this.B = -1;
        this.F = -1;
        this.N = new android.support.v4.widget.SwipeRefreshLayout$1(this);
        this.O = new android.support.v4.widget.SwipeRefreshLayout$6(this);
        this.P = new android.support.v4.widget.SwipeRefreshLayout$7(this);
        this.p = ViewConfiguration.get(arg5).getScaledTouchSlop();
        this.x = this.getResources().getInteger(17694721);
        this.setWillNotDraw(false);
        this.D = new DecelerateInterpolator(2f);
        DisplayMetrics v1 = this.getResources().getDisplayMetrics();
        this.L = ((int)(v1.density * 40f));
        this.c();
        this.setChildrenDrawingOrderEnabled(true);
        this.i = ((int)(v1.density * 64f));
        this.q = ((float)this.i);
        this.s = new o(((ViewGroup)this));
        this.t = new l(((View)this));
        this.setNestedScrollingEnabled(true);
        int v1_1 = -this.L;
        this.c = v1_1;
        this.h = v1_1;
        this.a(1f);
        TypedArray v5 = arg5.obtainStyledAttributes(arg6, SwipeRefreshLayout.E);
        this.setEnabled(v5.getBoolean(0, true));
        v5.recycle();
    }

    void a(float arg4) {
        this.setTargetOffsetTopAndBottom(this.f + (((int)((((float)(this.h - this.f))) * arg4))) - this.e.getTop());
    }

    private Animation a(int arg2, int arg3) {
        android.support.v4.widget.SwipeRefreshLayout$4 v0 = new Animation(arg2, arg3) {
            public void applyTransformation(float arg4, Transformation arg5) {
                this.c.k.setAlpha(((int)((((float)this.a)) + (((float)(this.b - this.a))) * arg4)));
            }
        };
        ((Animation)v0).setDuration(300);
        this.e.a(null);
        this.e.clearAnimation();
        this.e.startAnimation(((Animation)v0));
        return ((Animation)v0);
    }

    private void a(int arg3, Animation$AnimationListener arg4) {
        this.f = arg3;
        this.O.reset();
        this.O.setDuration(200);
        this.O.setInterpolator(this.D);
        if(arg4 != null) {
            this.e.a(arg4);
        }

        this.e.clearAnimation();
        this.e.startAnimation(this.O);
    }

    private void a(MotionEvent arg4) {
        int v0 = arg4.getActionIndex();
        if(arg4.getPointerId(v0) == this.B) {
            v0 = v0 == 0 ? 1 : 0;
            this.B = arg4.getPointerId(v0);
        }
    }

    private void a(boolean arg2, boolean arg3) {
        if(this.b != arg2) {
            this.l = arg3;
            this.f();
            this.b = arg2;
            if(this.b) {
                this.a(this.c, this.N);
            }
            else {
                this.a(this.N);
            }
        }
    }

    void a(Animation$AnimationListener arg4) {
        this.H = new Animation() {
            public void applyTransformation(float arg2, Transformation arg3) {
                this.a.setAnimationProgress(1f - arg2);
            }
        };
        this.H.setDuration(150);
        this.e.a(arg4);
        this.e.clearAnimation();
        this.e.startAnimation(this.H);
    }

    private boolean a(Animation arg2) {
        boolean v2 = arg2 == null || !arg2.hasStarted() || (arg2.hasEnded()) ? false : true;
        return v2;
    }

    void a() {
        this.e.clearAnimation();
        this.k.stop();
        this.e.setVisibility(8);
        this.setColorViewAlpha(255);
        if(this.d) {
            this.setAnimationProgress(0f);
        }
        else {
            this.setTargetOffsetTopAndBottom(this.h - this.c);
        }

        this.c = this.e.getTop();
    }

    public void a(boolean arg1, int arg2, int arg3) {
        this.d = arg1;
        this.h = arg2;
        this.i = arg3;
        this.m = true;
        this.a();
        this.b = false;
    }

    private void b(float arg12) {
        int v4;
        this.k.a(true);
        float v1 = 1f;
        float v0 = Math.min(v1, Math.abs(arg12 / this.q));
        double v2 = ((double)v0);
        Double.isNaN(v2);
        float v2_1 = (((float)Math.max(v2 - 0.4, 0))) * 5f / 3f;
        float v3 = Math.abs(arg12) - this.q;
        if(this.j > 0) {
            v4 = this.j;
        }
        else if(this.m) {
            v4 = this.i - this.h;
        }
        else {
            v4 = this.i;
        }

        float v4_1 = ((float)v4);
        float v5 = 2f;
        double v7 = ((double)(Math.max(0f, Math.min(v3, v4_1 * v5) / v4_1) / 4f));
        double v9 = Math.pow(v7, 2);
        Double.isNaN(v7);
        v3 = (((float)(v7 - v9))) * v5;
        int v8 = this.h + (((int)(v4_1 * v0 + v4_1 * v3 * v5)));
        if(this.e.getVisibility() != 0) {
            this.e.setVisibility(0);
        }

        if(!this.d) {
            this.e.setScaleX(v1);
            this.e.setScaleY(v1);
        }

        if(this.d) {
            this.setAnimationProgress(Math.min(v1, arg12 / this.q));
        }

        if(arg12 < this.q) {
            if(this.k.getAlpha() > 76 && !this.a(this.I)) {
                this.d();
            }
        }
        else if(this.k.getAlpha() < 255 && !this.a(this.J)) {
            this.e();
        }

        this.k.a(0f, Math.min(0.8f, v2_1 * 0.8f));
        this.k.b(Math.min(v1, v2_1));
        this.k.c((v2_1 * 0.4f - 0.25f + v3 * v5) * 0.5f);
        this.setTargetOffsetTopAndBottom(v8 - this.c);
    }

    private void b(int arg3, Animation$AnimationListener arg4) {
        if(this.d) {
            this.c(arg3, arg4);
        }
        else {
            this.f = arg3;
            this.P.reset();
            this.P.setDuration(200);
            this.P.setInterpolator(this.D);
            if(arg4 != null) {
                this.e.a(arg4);
            }

            this.e.clearAnimation();
            this.e.startAnimation(this.P);
        }
    }

    private void b(Animation$AnimationListener arg4) {
        this.e.setVisibility(0);
        this.k.setAlpha(255);
        this.G = new Animation() {
            public void applyTransformation(float arg1, Transformation arg2) {
                this.a.setAnimationProgress(arg1);
            }
        };
        this.G.setDuration(((long)this.x));
        if(arg4 != null) {
            this.e.a(arg4);
        }

        this.e.clearAnimation();
        this.e.startAnimation(this.G);
    }

    public boolean b() {
        if(this.M != null) {
            return this.M.a(this, this.o);
        }

        int v1 = -1;
        if((this.o instanceof ListView)) {
            return n.b(this.o, v1);
        }

        return this.o.canScrollVertically(v1);
    }

    private void c() {
        this.e = new c(this.getContext(), -328966);
        this.k = new d(this.getContext());
        this.k.a(1);
        this.e.setImageDrawable(this.k);
        this.e.setVisibility(8);
        this.addView(this.e);
    }

    private void c(int arg3, Animation$AnimationListener arg4) {
        this.f = arg3;
        this.g = this.e.getScaleX();
        this.K = new Animation() {
            public void applyTransformation(float arg2, Transformation arg3) {
                this.a.setAnimationProgress(this.a.g + -this.a.g * arg2);
                this.a.a(arg2);
            }
        };
        this.K.setDuration(150);
        if(arg4 != null) {
            this.e.a(arg4);
        }

        this.e.clearAnimation();
        this.e.startAnimation(this.K);
    }

    private void c(float arg3) {
        android.support.v4.widget.SwipeRefreshLayout$5 v0_1;
        if(arg3 > this.q) {
            this.a(true, true);
        }
        else {
            this.b = false;
            this.k.a(0f, 0f);
            Animation$AnimationListener v0 = null;
            if(!this.d) {
                v0_1 = new Animation$AnimationListener() {
                    public void onAnimationEnd(Animation arg2) {
                        if(!this.a.d) {
                            this.a.a(null);
                        }
                    }

                    public void onAnimationRepeat(Animation arg1) {
                    }

                    public void onAnimationStart(Animation arg1) {
                    }
                };
            }

            this.b(this.c, ((Animation$AnimationListener)v0_1));
            this.k.a(false);
        }
    }

    private void d() {
        this.I = this.a(this.k.getAlpha(), 76);
    }

    private void d(float arg2) {
        if(arg2 - this.z > (((float)this.p)) && !this.A) {
            this.y = this.z + (((float)this.p));
            this.A = true;
            this.k.setAlpha(76);
        }
    }

    public boolean dispatchNestedFling(float arg2, float arg3, boolean arg4) {
        return this.t.a(arg2, arg3, arg4);
    }

    public boolean dispatchNestedPreFling(float arg2, float arg3) {
        return this.t.a(arg2, arg3);
    }

    public boolean dispatchNestedPreScroll(int arg2, int arg3, int[] arg4, int[] arg5) {
        return this.t.a(arg2, arg3, arg4, arg5);
    }

    public boolean dispatchNestedScroll(int arg7, int arg8, int arg9, int arg10, int[] arg11) {
        return this.t.a(arg7, arg8, arg9, arg10, arg11);
    }

    private void e() {
        this.J = this.a(this.k.getAlpha(), 255);
    }

    private void f() {
        if(this.o == null) {
            int v0 = 0;
            while(v0 < this.getChildCount()) {
                View v1 = this.getChildAt(v0);
                if(!v1.equals(this.e)) {
                    this.o = v1;
                }
                else {
                    ++v0;
                    continue;
                }

                return;
            }
        }
    }

    protected int getChildDrawingOrder(int arg2, int arg3) {
        if(this.F < 0) {
            return arg3;
        }

        if(arg3 == arg2 - 1) {
            return this.F;
        }

        if(arg3 >= this.F) {
            ++arg3;
        }

        return arg3;
    }

    public int getNestedScrollAxes() {
        return this.s.a();
    }

    public int getProgressCircleDiameter() {
        return this.L;
    }

    public int getProgressViewEndOffset() {
        return this.i;
    }

    public int getProgressViewStartOffset() {
        return this.h;
    }

    public boolean hasNestedScrollingParent() {
        return this.t.b();
    }

    public boolean isNestedScrollingEnabled() {
        return this.t.a();
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.a();
    }

    public boolean onInterceptTouchEvent(MotionEvent arg4) {
        this.f();
        int v0 = arg4.getActionMasked();
        if((this.C) && v0 == 0) {
            this.C = false;
        }

        if((this.isEnabled()) && !this.C && !this.b() && !this.b) {
            if(this.w) {
            }
            else {
                if(v0 != 6) {
                    int v1 = -1;
                    switch(v0) {
                        case 0: {
                            goto label_39;
                        }
                        case 2: {
                            goto label_23;
                        }
                        case 1: 
                        case 3: {
                            goto label_36;
                        }
                    }

                    goto label_55;
                label_36:
                    this.A = false;
                    this.B = v1;
                    goto label_55;
                label_39:
                    this.setTargetOffsetTopAndBottom(this.h - this.e.getTop());
                    this.B = arg4.getPointerId(0);
                    this.A = false;
                    v0 = arg4.findPointerIndex(this.B);
                    if(v0 < 0) {
                        return 0;
                    }
                    else {
                        this.z = arg4.getY(v0);
                        goto label_55;
                    label_23:
                        if(this.B == v1) {
                            Log.e(SwipeRefreshLayout.n, "Got ACTION_MOVE event but don\'t have an active pointer id.");
                            return 0;
                        }
                        else {
                            v0 = arg4.findPointerIndex(this.B);
                            if(v0 < 0) {
                                return 0;
                            }
                            else {
                                this.d(arg4.getY(v0));
                            }
                        }
                    }
                }
                else {
                    this.a(arg4);
                }

            label_55:
                return this.A;
            }
        }

        return 0;
    }

    protected void onLayout(boolean arg3, int arg4, int arg5, int arg6, int arg7) {
        int v3 = this.getMeasuredWidth();
        arg4 = this.getMeasuredHeight();
        if(this.getChildCount() == 0) {
            return;
        }

        if(this.o == null) {
            this.f();
        }

        if(this.o == null) {
            return;
        }

        View v5 = this.o;
        arg6 = this.getPaddingLeft();
        arg7 = this.getPaddingTop();
        v5.layout(arg6, arg7, v3 - this.getPaddingLeft() - this.getPaddingRight() + arg6, arg4 - this.getPaddingTop() - this.getPaddingBottom() + arg7);
        v3 /= 2;
        arg4 = this.e.getMeasuredWidth() / 2;
        this.e.layout(v3 - arg4, this.c, v3 + arg4, this.c + this.e.getMeasuredHeight());
    }

    public void onMeasure(int arg4, int arg5) {
        super.onMeasure(arg4, arg5);
        if(this.o == null) {
            this.f();
        }

        if(this.o == null) {
            return;
        }

        this.o.measure(View$MeasureSpec.makeMeasureSpec(this.getMeasuredWidth() - this.getPaddingLeft() - this.getPaddingRight(), 1073741824), View$MeasureSpec.makeMeasureSpec(this.getMeasuredHeight() - this.getPaddingTop() - this.getPaddingBottom(), 1073741824));
        this.e.measure(View$MeasureSpec.makeMeasureSpec(this.L, 1073741824), View$MeasureSpec.makeMeasureSpec(this.L, 1073741824));
        this.F = -1;
        arg4 = 0;
        while(arg4 < this.getChildCount()) {
            if(this.getChildAt(arg4) == this.e) {
                this.F = arg4;
            }
            else {
                ++arg4;
                continue;
            }

            return;
        }
    }

    public boolean onNestedFling(View arg1, float arg2, float arg3, boolean arg4) {
        return this.dispatchNestedFling(arg2, arg3, arg4);
    }

    public boolean onNestedPreFling(View arg1, float arg2, float arg3) {
        return this.dispatchNestedPreFling(arg2, arg3);
    }

    public void onNestedPreScroll(View arg4, int arg5, int arg6, int[] arg7) {
        if(arg6 > 0 && this.r > 0f) {
            float v1 = ((float)arg6);
            if(v1 > this.r) {
                arg7[1] = arg6 - (((int)this.r));
                this.r = 0f;
            }
            else {
                this.r -= v1;
                arg7[1] = arg6;
            }

            this.b(this.r);
        }

        if((this.m) && arg6 > 0 && this.r == 0f && Math.abs(arg6 - arg7[1]) > 0) {
            this.e.setVisibility(8);
        }

        int[] v4 = this.u;
        if(this.dispatchNestedPreScroll(arg5 - arg7[0], arg6 - arg7[1], v4, null)) {
            arg7[0] += v4[0];
            arg7[1] += v4[1];
        }
    }

    public void onNestedScroll(View arg7, int arg8, int arg9, int arg10, int arg11) {
        this.dispatchNestedScroll(arg8, arg9, arg10, arg11, this.v);
        arg11 += this.v[1];
        if(arg11 < 0 && !this.b()) {
            this.r += ((float)Math.abs(arg11));
            this.b(this.r);
        }
    }

    public void onNestedScrollAccepted(View arg2, View arg3, int arg4) {
        this.s.a(arg2, arg3, arg4);
        this.startNestedScroll(arg4 & 2);
        this.r = 0f;
        this.w = true;
    }

    public boolean onStartNestedScroll(View arg1, View arg2, int arg3) {
        boolean v1 = !this.isEnabled() || (this.C) || (this.b) || (arg3 & 2) == 0 ? false : true;
        return v1;
    }

    public void onStopNestedScroll(View arg2) {
        this.s.a(arg2);
        this.w = false;
        if(this.r > 0f) {
            this.c(this.r);
            this.r = 0f;
        }

        this.stopNestedScroll();
    }

    public boolean onTouchEvent(MotionEvent arg5) {
        float v5;
        int v0 = arg5.getActionMasked();
        if((this.C) && v0 == 0) {
            this.C = false;
        }

        if((this.isEnabled()) && !this.C && !this.b() && !this.b) {
            if(this.w) {
            }
            else {
                float v1 = 0.5f;
                switch(v0) {
                    case 0: {
                        goto label_69;
                    }
                    case 1: {
                        goto label_51;
                    }
                    case 2: {
                        goto label_32;
                    }
                    case 3: {
                        return 0;
                    }
                    case 5: {
                        goto label_22;
                    }
                    case 6: {
                        goto label_20;
                    }
                }

                return 1;
            label_51:
                v0 = arg5.findPointerIndex(this.B);
                if(v0 < 0) {
                    Log.e(SwipeRefreshLayout.n, "Got ACTION_UP event but don\'t have an active pointer id.");
                    return 0;
                }
                else {
                    if(this.A) {
                        v5 = (arg5.getY(v0) - this.y) * v1;
                        this.A = false;
                        this.c(v5);
                    }

                    this.B = -1;
                    return 0;
                label_20:
                    this.a(arg5);
                    return 1;
                label_69:
                    this.B = arg5.getPointerId(0);
                    this.A = false;
                    return 1;
                label_22:
                    v0 = arg5.getActionIndex();
                    if(v0 < 0) {
                        Log.e(SwipeRefreshLayout.n, "Got ACTION_POINTER_DOWN event but have an invalid action index.");
                        return 0;
                    }

                    this.B = arg5.getPointerId(v0);
                    return 1;
                label_32:
                    v0 = arg5.findPointerIndex(this.B);
                    if(v0 < 0) {
                        Log.e(SwipeRefreshLayout.n, "Got ACTION_MOVE event but have an invalid active pointer id.");
                        return 0;
                    }

                    v5 = arg5.getY(v0);
                    this.d(v5);
                    if(this.A) {
                        v5 = (v5 - this.y) * v1;
                        if(v5 > 0f) {
                            this.b(v5);
                        }
                        else {
                            return 0;
                        }
                    }

                    return 1;
                }
            }
        }

        return 0;
    }

    public void requestDisallowInterceptTouchEvent(boolean arg3) {
        if((Build$VERSION.SDK_INT >= 21 || !(this.o instanceof AbsListView)) && (this.o == null || (t.y(this.o)))) {
            super.requestDisallowInterceptTouchEvent(arg3);
        }
    }

    void setAnimationProgress(float arg2) {
        this.e.setScaleX(arg2);
        this.e.setScaleY(arg2);
    }

    @Deprecated public void setColorScheme(int[] arg1) {
        this.setColorSchemeResources(arg1);
    }

    public void setColorSchemeColors(int[] arg2) {
        this.f();
        this.k.a(arg2);
    }

    public void setColorSchemeResources(int[] arg5) {
        Context v0 = this.getContext();
        int[] v1 = new int[arg5.length];
        int v2;
        for(v2 = 0; v2 < arg5.length; ++v2) {
            v1[v2] = android.support.v4.content.a.c(v0, arg5[v2]);
        }

        this.setColorSchemeColors(v1);
    }

    private void setColorViewAlpha(int arg2) {
        this.e.getBackground().setAlpha(arg2);
        this.k.setAlpha(arg2);
    }

    public void setDistanceToTriggerSync(int arg1) {
        this.q = ((float)arg1);
    }

    public void setEnabled(boolean arg1) {
        super.setEnabled(arg1);
        if(!arg1) {
            this.a();
        }
    }

    public void setNestedScrollingEnabled(boolean arg2) {
        this.t.a(arg2);
    }

    public void setOnChildScrollUpCallback(a arg1) {
        this.M = arg1;
    }

    public void setOnRefreshListener(b arg1) {
        this.a = arg1;
    }

    @Deprecated public void setProgressBackgroundColor(int arg1) {
        this.setProgressBackgroundColorSchemeResource(arg1);
    }

    public void setProgressBackgroundColorSchemeColor(int arg2) {
        this.e.setBackgroundColor(arg2);
    }

    public void setProgressBackgroundColorSchemeResource(int arg2) {
        this.setProgressBackgroundColorSchemeColor(android.support.v4.content.a.c(this.getContext(), arg2));
    }

    public void setRefreshing(boolean arg3) {
        if(!arg3 || this.b == arg3) {
            this.a(arg3, false);
        }
        else {
            this.b = arg3;
            int v3 = !this.m ? this.i + this.h : this.i;
            this.setTargetOffsetTopAndBottom(v3 - this.c);
            this.l = false;
            this.b(this.N);
        }
    }

    public void setSize(int arg3) {
        if(arg3 != 0 && arg3 != 1) {
            return;
        }

        DisplayMetrics v0 = this.getResources().getDisplayMetrics();
        float v1 = arg3 == 0 ? 56f : 40f;
        this.L = ((int)(v0.density * v1));
        this.e.setImageDrawable(null);
        this.k.a(arg3);
        this.e.setImageDrawable(this.k);
    }

    public void setSlingshotDistance(int arg1) {
        this.j = arg1;
    }

    void setTargetOffsetTopAndBottom(int arg2) {
        this.e.bringToFront();
        t.e(this.e, arg2);
        this.c = this.e.getTop();
    }

    public boolean startNestedScroll(int arg2) {
        return this.t.b(arg2);
    }

    public void stopNestedScroll() {
        this.t.c();
    }
}

