package g.a.a.a;

import android.animation.Animator$AnimatorListener;
import android.animation.Animator;
import android.animation.ValueAnimator$AnimatorUpdateListener;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region$Op;
import android.graphics.drawable.Drawable;
import android.os.Build$VERSION;
import android.text.TextUtils;
import android.view.KeyEvent$DispatcherState;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View$AccessibilityDelegate;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import android.view.ViewTreeObserver;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeInfo;
import g.a.a.a.a.d;

public class b {
    class g.a.a.a.b$1 implements Runnable {
        g.a.a.a.b$1(b arg1) {
            this.a = arg1;
            super();
        }

        public void run() {
            this.a.b(9);
            this.a.j();
        }
    }

    class a implements Animator$AnimatorListener {
        a() {
            super();
        }

        public void onAnimationCancel(Animator arg1) {
        }

        public void onAnimationEnd(Animator arg1) {
        }

        public void onAnimationRepeat(Animator arg1) {
        }

        public void onAnimationStart(Animator arg1) {
        }
    }

    public class g.a.a.a.b$b extends d {
        public g.a.a.a.b$b(Activity arg2) {
            this(arg2, 0);
        }

        public g.a.a.a.b$b(Activity arg2, int arg3) {
            this(new g.a.a.a.a(arg2), arg3);
        }

        public g.a.a.a.b$b(g.a.a.a.d arg1, int arg2) {
            super(arg1);
            this.a(arg2);
        }
    }

    public interface c {
        void onPromptStateChanged(b arg1, int arg2);
    }

    class g.a.a.a.b$d extends View {
        class g.a.a.a.b$d$a extends View$AccessibilityDelegate {
            g.a.a.a.b$d$a(g.a.a.a.b$d arg1) {
                this.a = arg1;
                super();
            }

            public void onInitializeAccessibilityNodeInfo(View arg6, AccessibilityNodeInfo arg7) {
                super.onInitializeAccessibilityNodeInfo(arg6, arg7);
                Package v0 = this.a.getClass().getPackage();
                if(v0 != null) {
                    arg7.setPackageName(v0.getName());
                }

                arg7.setSource(arg6);
                arg7.setClickable(true);
                arg7.setEnabled(true);
                arg7.setChecked(false);
                arg7.setFocusable(true);
                arg7.setFocused(true);
                if(Build$VERSION.SDK_INT >= 17) {
                    arg7.setLabelFor(this.a.h.b());
                }

                if(Build$VERSION.SDK_INT >= 19) {
                    arg7.setDismissable(true);
                }

                arg7.setContentDescription(String.format("%s. %s", this.a.h.e(), this.a.h.j()));
                arg7.setText(String.format("%s. %s", this.a.h.e(), this.a.h.j()));
            }

            public void onPopulateAccessibilityEvent(View arg2, AccessibilityEvent arg3) {
                super.onPopulateAccessibilityEvent(arg2, arg3);
                CharSequence v2 = this.a.h.e();
                if(!TextUtils.isEmpty(v2)) {
                    arg3.getText().add(v2);
                }

                v2 = this.a.h.j();
                if(!TextUtils.isEmpty(v2)) {
                    arg3.getText().add(v2);
                }
            }
        }

        public interface g.a.a.a.b$d$b {
            void a();

            void b();
        }

        Drawable a;
        float b;
        float c;
        g.a.a.a.b$d$b d;
        Rect e;
        View f;
        b g;
        d h;
        boolean i;
        AccessibilityManager j;

        public g.a.a.a.b$d(Context arg4) {
            super(arg4);
            this.e = new Rect();
            this.setId(g.a.a.a.c$b.material_target_prompt_view);
            this.setFocusableInTouchMode(true);
            this.requestFocus();
            if(Build$VERSION.SDK_INT < 18) {
                this.setLayerType(1, null);
            }

            this.setAccessibilityDelegate(new g.a.a.a.b$d$a(this));
            this.j = arg4.getSystemService("accessibility");
            if(this.j.isEnabled()) {
                this.setClickable(true);
            }
        }

        public boolean dispatchKeyEventPreIme(KeyEvent arg4) {
            if((this.h.F()) && arg4.getKeyCode() == 4) {
                KeyEvent$DispatcherState v0 = this.getKeyDispatcherState();
                if(v0 != null) {
                    boolean v2 = true;
                    if(arg4.getAction() == 0 && arg4.getRepeatCount() == 0) {
                        v0.startTracking(arg4, this);
                        return 1;
                    }

                    if(arg4.getAction() != 1) {
                        goto label_33;
                    }

                    if(arg4.isCanceled()) {
                        goto label_33;
                    }

                    if(!v0.isTracking(arg4)) {
                        goto label_33;
                    }

                    if(this.d != null) {
                        this.d.b();
                    }

                    if(!this.h.z()) {
                        if(super.dispatchKeyEventPreIme(arg4)) {
                        }
                        else {
                            v2 = false;
                        }
                    }

                    return v2;
                }
            }

        label_33:
            return super.dispatchKeyEventPreIme(arg4);
        }

        public CharSequence getAccessibilityClassName() {
            return g.a.a.a.b$d.class.getName();
        }

        protected void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            this.g.k();
        }

        public void onDraw(Canvas arg3) {
            if(this.i) {
                arg3.clipRect(this.e);
            }

            Path v0 = this.h.H().a();
            if(v0 != null) {
                arg3.save();
                arg3.clipPath(v0, Region$Op.DIFFERENCE);
            }

            this.h.G().a(arg3);
            if(v0 != null) {
                arg3.restore();
            }

            this.h.H().a(arg3);
            if(this.a != null) {
                arg3.translate(this.b, this.c);
                this.a.draw(arg3);
                goto label_26;
            }
            else if(this.f != null) {
                arg3.translate(this.b, this.c);
                this.f.draw(arg3);
            label_26:
                arg3.translate(-this.b, -this.c);
            }

            this.h.I().a(arg3);
        }

        public boolean onHoverEvent(MotionEvent arg4) {
            if((this.j.isTouchExplorationEnabled()) && arg4.getPointerCount() == 1) {
                int v0 = arg4.getAction();
                if(v0 != 7) {
                    switch(v0) {
                        case 9: {
                            goto label_13;
                        }
                        case 10: {
                            goto label_11;
                        }
                    }

                    goto label_17;
                label_11:
                    arg4.setAction(1);
                    goto label_17;
                label_13:
                    v0 = 0;
                    goto label_16;
                }
                else {
                    v0 = 2;
                label_16:
                    arg4.setAction(v0);
                }

            label_17:
                return this.onTouchEvent(arg4);
            }

            return super.onHoverEvent(arg4);
        }

        protected void onMeasure(int arg1, int arg2) {
            ViewParent v1 = this.getParent();
            this.setMeasuredDimension(((View)v1).getMeasuredWidth(), ((View)v1).getMeasuredHeight());
        }

        public boolean onTouchEvent(MotionEvent arg5) {
            boolean v5_1;
            boolean v1;
            float v0 = arg5.getX();
            float v5 = arg5.getY();
            if((this.i) && !this.e.contains(((int)v0), ((int)v5))) {
                goto label_15;
            }
            else if(this.h.G().a_(v0, v5)) {
                v1 = true;
            }
            else {
            label_15:
                v1 = false;
            }

            if(!v1 || !this.h.H().a_(v0, v5)) {
                if(!v1) {
                    v1 = this.h.B();
                }

                v5_1 = v1;
                if(this.d == null) {
                    return v5_1;
                }

                this.d.b();
            }
            else {
                v5_1 = this.h.u();
                if(this.d != null) {
                    this.d.a();
                }
            }

            return v5_1;
        }
    }

    g.a.a.a.b$d a;
    ValueAnimator b;
    ValueAnimator c;
    ValueAnimator d;
    float e;
    int f;
    final float g;
    final Runnable h;
    final ViewTreeObserver$OnGlobalLayoutListener i;

    b(d arg4) {
        super();
        this.h = new g.a.a.a.b$1(this);
        g.a.a.a.d v0 = arg4.a();
        this.a = new g.a.a.a.b$d(v0.b());
        this.a.g = this;
        this.a.h = arg4;
        this.a.d = new g.a.a.a.b$d$b() {
            public void a() {
                if(!this.a.d()) {
                    this.a.b(3);
                    if(this.a.a.h.A()) {
                        this.a.i();
                    }
                }
            }

            public void b() {
                if(!this.a.d()) {
                    this.a.b(8);
                    if(this.a.a.h.z()) {
                        this.a.j();
                    }
                }
            }
        };
        Rect v4 = new Rect();
        v0.a().getWindowVisibleDisplayFrame(v4);
        this.g = ((float)v4.top);
        this.i = new ViewTreeObserver$OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                boolean v0_1;
                View v0 = this.a.a.h.b();
                if(v0 != null) {
                    if(Build$VERSION.SDK_INT >= 19) {
                        v0_1 = v0.isAttachedToWindow();
                    }
                    else if(v0.getWindowToken() != null) {
                        v0_1 = true;
                    }
                    else {
                        v0_1 = false;
                    }

                    if(v0_1) {
                        goto label_17;
                    }

                    return;
                }

            label_17:
                this.a.n();
                if(this.a.b == null) {
                    this.a.a(1f, 1f);
                }
            }
        };
    }

    public static b a(d arg1) {
        return new b(arg1);
    }

    public void a() {
        if(this.c()) {
            return;
        }

        ViewGroup v0 = this.a.h.a().a();
        if((this.d()) || v0.findViewById(g.a.a.a.c$b.material_target_prompt_view) != null) {
            this.a(this.f);
        }

        v0.addView(this.a);
        this.g();
        this.b(1);
        this.n();
        this.l();
    }

    void a(float arg3, float arg4) {
        if(this.a.getParent() == null) {
            return;
        }

        this.a.h.I().b(this.a.h, arg3, arg4);
        if(this.a.a != null) {
            this.a.a.setAlpha(((int)(255f * arg4)));
        }

        this.a.h.H().b(this.a.h, arg3, arg4);
        this.a.h.G().b(this.a.h, arg3, arg4);
        this.a.invalidate();
    }

    void a(int arg3) {
        this.k();
        this.h();
        ViewParent v0 = this.a.getParent();
        if(v0 != null) {
            ((ViewGroup)v0).removeView(this.a);
        }

        if(this.d()) {
            this.b(arg3);
        }
    }

    protected void b(int arg2) {
        this.f = arg2;
        this.a.h.a(this, arg2);
        this.a.h.b(this, arg2);
    }

    public void b() {
        this.a.removeCallbacks(this.h);
    }

    boolean c() {
        boolean v1 = true;
        if(this.f != 1) {
            if(this.f == 2) {
            }
            else {
                v1 = false;
            }
        }

        return v1;
    }

    boolean d() {
        boolean v0 = this.f == 5 || this.f == 7 ? true : false;
        return v0;
    }

    boolean e() {
        boolean v0 = this.f == 6 || this.f == 4 ? true : false;
        return v0;
    }

    boolean f() {
        boolean v0 = this.f == 0 || (this.d()) || (this.e()) ? true : false;
        return v0;
    }

    void g() {
        ViewTreeObserver v0 = this.a.getParent().getViewTreeObserver();
        if(v0.isAlive()) {
            v0.addOnGlobalLayoutListener(this.i);
        }
    }

    void h() {
        if(this.a.getParent() == null) {
            return;
        }

        ViewTreeObserver v0 = this.a.getParent().getViewTreeObserver();
        if(v0.isAlive()) {
            if(Build$VERSION.SDK_INT >= 16) {
                v0.removeOnGlobalLayoutListener(this.i);
            }
            else {
                v0.removeGlobalOnLayoutListener(this.i);
            }
        }
    }

    public void i() {
        if(this.f()) {
            return;
        }

        this.b();
        this.k();
        this.b = ValueAnimator.ofFloat(new float[]{1f, 0f});
        this.b.setDuration(225);
        this.b.setInterpolator(this.a.h.r());
        this.b.addUpdateListener(new ValueAnimator$AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator arg5) {
                float v5 = arg5.getAnimatedValue().floatValue();
                this.a.a((1f - v5) / 4f + 1f, v5);
            }
        });
        this.b.addListener(new a() {
            public void onAnimationEnd(Animator arg2) {
                this.a.a(4);
                this.a.a.sendAccessibilityEvent(32);
            }
        });
        this.b(7);
        this.b.start();
    }

    public void j() {
        if(this.f()) {
            return;
        }

        this.b();
        this.k();
        this.b = ValueAnimator.ofFloat(new float[]{1f, 0f});
        this.b.setDuration(225);
        this.b.setInterpolator(this.a.h.r());
        this.b.addUpdateListener(new ValueAnimator$AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator arg2) {
                float v2 = arg2.getAnimatedValue().floatValue();
                this.a.a(v2, v2);
            }
        });
        this.b.addListener(new a() {
            public void onAnimationEnd(Animator arg2) {
                this.a.a(6);
                this.a.a.sendAccessibilityEvent(32);
            }
        });
        this.b(5);
        this.b.start();
    }

    void k() {
        ValueAnimator v1 = null;
        if(this.b != null) {
            this.b.removeAllUpdateListeners();
            this.b.removeAllListeners();
            this.b.cancel();
            this.b = v1;
        }

        if(this.d != null) {
            this.d.removeAllUpdateListeners();
            this.d.cancel();
            this.d = v1;
        }

        if(this.c != null) {
            this.c.removeAllUpdateListeners();
            this.c.cancel();
            this.c = v1;
        }
    }

    void l() {
        this.a(0f, 0f);
        this.k();
        this.b = ValueAnimator.ofFloat(new float[]{0f, 1f});
        this.b.setInterpolator(this.a.h.r());
        this.b.setDuration(225);
        this.b.addUpdateListener(new ValueAnimator$AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator arg2) {
                float v2 = arg2.getAnimatedValue().floatValue();
                this.a.a(v2, v2);
            }
        });
        this.b.addListener(new a() {
            public void onAnimationEnd(Animator arg2) {
                arg2.removeAllListeners();
                this.a.a(1f, 1f);
                this.a.k();
                if(this.a.a.h.s()) {
                    this.a.m();
                }

                this.a.b(2);
                this.a.a.requestFocus();
                this.a.a.sendAccessibilityEvent(8);
            }
        });
        this.b.start();
    }

    void m() {
        this.k();
        this.c = ValueAnimator.ofFloat(new float[]{1f, 1.1f, 1f});
        this.c.setInterpolator(this.a.h.r());
        this.c.setDuration(1000);
        this.c.setStartDelay(225);
        this.c.setRepeatCount(-1);
        this.c.addUpdateListener(new ValueAnimator$AnimatorUpdateListener() {
            boolean a;

            public void onAnimationUpdate(ValueAnimator arg4) {
                float v4 = arg4.getAnimatedValue().floatValue();
                boolean v0 = this.a;
                if(v4 < this.b.e && (this.a)) {
                    v0 = false;
                }
                else if(v4 > this.b.e && !this.a) {
                    v0 = true;
                }

                if(v0 != this.a && !v0) {
                    this.b.d.start();
                }

                this.a = v0;
                this.b.e = v4;
                this.b.a.h.H().b(this.b.a.h, v4, 1f);
                this.b.a.invalidate();
            }
        });
        this.c.start();
        this.d = ValueAnimator.ofFloat(new float[]{1.1f, 1.6f});
        this.d.setInterpolator(this.a.h.r());
        this.d.setDuration(500);
        this.d.addUpdateListener(new ValueAnimator$AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator arg4) {
                float v4 = arg4.getAnimatedValue().floatValue();
                this.a.a.h.H().b(v4, (1.6f - v4) * 2f);
            }
        });
    }

    void n() {
        View v0 = this.a.h.d();
        this.a.f = v0 == null ? this.a.h.b() : v0;
        this.p();
        v0 = this.a.h.b();
        if(v0 != null) {
            int[] v1 = new int[2];
            this.a.getLocationInWindow(v1);
            this.a.h.H().a(this.a.h, v0, v1);
        }
        else {
            PointF v0_1 = this.a.h.c();
            this.a.h.H().a(this.a.h, v0_1.x, v0_1.y);
        }

        this.a.h.I().a(this.a.h, this.a.i, this.a.e);
        this.a.h.G().a(this.a.h, this.a.i, this.a.e);
        this.o();
    }

    void o() {
        float v0_1;
        g.a.a.a.b$d v2;
        this.a.a = this.a.h.t();
        int v1 = 2;
        if(this.a.a != null) {
            RectF v0 = this.a.h.H().b();
            this.a.b = v0.centerX() - (((float)(this.a.a.getIntrinsicWidth() / v1)));
            v2 = this.a;
            v0_1 = v0.centerY() - (((float)(this.a.a.getIntrinsicHeight() / v1)));
            goto label_30;
        }
        else if(this.a.f != null) {
            int[] v0_2 = new int[v1];
            this.a.getLocationInWindow(v0_2);
            int[] v1_1 = new int[v1];
            this.a.f.getLocationInWindow(v1_1);
            this.a.b = ((float)(v1_1[0] - v0_2[0] - this.a.f.getScrollX()));
            v2 = this.a;
            v0_1 = ((float)(v1_1[1] - v0_2[1] - this.a.f.getScrollY()));
        label_30:
            v2.c = v0_1;
        }
    }

    void p() {
        View v0 = this.a.h.E();
        if(v0 != null) {
            this.a.i = true;
            this.a.e.set(0, 0, 0, 0);
            Point v1 = new Point();
            v0.getGlobalVisibleRect(this.a.e, v1);
            if(v1.y == 0) {
                this.a.e.top = ((int)((((float)this.a.e.top)) + this.g));
            }
        }
        else {
            this.a.getParent().getGlobalVisibleRect(this.a.e, new Point());
            this.a.i = false;
        }
    }
}

