package android.support.design.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator$AnimatorUpdateListener;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build$VERSION;
import android.os.Handler$Callback;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.design.a$k;
import android.support.v4.view.t;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.view.ViewGroup$MarginLayoutParams;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityManager;
import android.widget.FrameLayout;
import java.util.List;

public abstract class BaseTransientBottomBar {
    final class android.support.design.widget.BaseTransientBottomBar$1 implements Handler$Callback {
        android.support.design.widget.BaseTransientBottomBar$1() {
            super();
        }

        public boolean handleMessage(Message arg3) {
            switch(arg3.what) {
                case 0: {
                    goto label_9;
                }
                case 1: {
                    goto label_5;
                }
            }

            return 0;
        label_5:
            arg3.obj.b(arg3.arg1);
            return 1;
        label_9:
            arg3.obj.c();
            return 1;
        }
    }

    public class Behavior extends SwipeDismissBehavior {
        private final b g;

        public Behavior() {
            super();
            this.g = new b(((SwipeDismissBehavior)this));
        }

        static void a(Behavior arg0, BaseTransientBottomBar arg1) {
            arg0.a(arg1);
        }

        private void a(BaseTransientBottomBar arg2) {
            this.g.a(arg2);
        }

        public boolean a(View arg2) {
            return this.g.a(arg2);
        }

        public boolean b(CoordinatorLayout arg2, View arg3, MotionEvent arg4) {
            this.g.a(arg2, arg3, arg4);
            return super.b(arg2, arg3, arg4);
        }
    }

    public abstract class a {
        public a() {
            super();
        }

        public void a(Object arg1, int arg2) {
        }

        public void a(Object arg1) {
        }
    }

    public class b {
        private android.support.design.widget.o$a a;

        public b(SwipeDismissBehavior arg2) {
            super();
            arg2.a(0.1f);
            arg2.b(0.6f);
            arg2.a(0);
        }

        public void a(BaseTransientBottomBar arg1) {
            this.a = arg1.c;
        }

        public void a(CoordinatorLayout arg3, View arg4, MotionEvent arg5) {
            int v0 = arg5.getActionMasked();
            if(v0 != 3) {
                switch(v0) {
                    case 0: {
                        goto label_5;
                    }
                    case 1: {
                        goto label_15;
                    }
                }

                return;
            label_5:
                if(arg3.a(arg4, ((int)arg5.getX()), ((int)arg5.getY()))) {
                    o.a().c(this.a);
                }
            }
            else {
            label_15:
                o.a().d(this.a);
            }
        }

        public boolean a(View arg1) {
            return arg1 instanceof e;
        }
    }

    public interface c {
        void a(View arg1);

        void b(View arg1);
    }

    public interface d {
        void a(View arg1, int arg2, int arg3, int arg4, int arg5);
    }

    public class e extends FrameLayout {
        private final AccessibilityManager a;
        private final android.support.v4.view.a.b$a b;
        private d c;
        private c d;

        protected e(Context arg2) {
            this(arg2, null);
        }

        protected e(Context arg3, AttributeSet arg4) {
            super(arg3, arg4);
            TypedArray v4 = arg3.obtainStyledAttributes(arg4, k.SnackbarLayout);
            if(v4.hasValue(k.SnackbarLayout_elevation)) {
                t.k(((View)this), ((float)v4.getDimensionPixelSize(k.SnackbarLayout_elevation, 0)));
            }

            v4.recycle();
            this.a = arg3.getSystemService("accessibility");
            this.b = new android.support.v4.view.a.b$a() {
                public void a(boolean arg2) {
                    e.a(this.a, arg2);
                }
            };
            android.support.v4.view.a.b.a(this.a, this.b);
            this.setClickableOrFocusableBasedOnAccessibility(this.a.isTouchExplorationEnabled());
        }

        static void a(e arg0, boolean arg1) {
            arg0.setClickableOrFocusableBasedOnAccessibility(arg1);
        }

        protected void onAttachedToWindow() {
            super.onAttachedToWindow();
            if(this.d != null) {
                this.d.a(((View)this));
            }

            t.s(((View)this));
        }

        protected void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            if(this.d != null) {
                this.d.b(((View)this));
            }

            android.support.v4.view.a.b.b(this.a, this.b);
        }

        protected void onLayout(boolean arg7, int arg8, int arg9, int arg10, int arg11) {
            super.onLayout(arg7, arg8, arg9, arg10, arg11);
            if(this.c != null) {
                this.c.a(this, arg8, arg9, arg10, arg11);
            }
        }

        private void setClickableOrFocusableBasedOnAccessibility(boolean arg2) {
            this.setClickable((((int)arg2)) ^ 1);
            this.setFocusable(arg2);
        }

        void setOnAttachStateChangeListener(c arg1) {
            this.d = arg1;
        }

        void setOnLayoutChangeListener(d arg1) {
            this.c = arg1;
        }
    }

    static final Handler a;
    protected final e b;
    final android.support.design.widget.o$a c;
    private static final boolean d;
    private static final int[] e;
    private final ViewGroup f;
    private final android.support.design.h.a g;
    private List h;
    private Behavior i;
    private final AccessibilityManager j;

    static {
        boolean v0 = Build$VERSION.SDK_INT < 16 || Build$VERSION.SDK_INT > 19 ? false : true;
        BaseTransientBottomBar.d = v0;
        BaseTransientBottomBar.e = new int[]{android.support.design.a$b.snackbarStyle};
        BaseTransientBottomBar.a = new Handler(Looper.getMainLooper(), new android.support.design.widget.BaseTransientBottomBar$1());
    }

    static android.support.design.h.a a(BaseTransientBottomBar arg0) {
        return arg0.g;
    }

    protected void a(int arg3) {
        o.a().a(this.c, arg3);
    }

    public boolean a() {
        return o.a().e(this.c);
    }

    protected SwipeDismissBehavior b() {
        return new Behavior();
    }

    final void b(int arg2) {
        if(!this.f() || this.b.getVisibility() != 0) {
            this.c(arg2);
        }
        else {
            this.d(arg2);
        }
    }

    void c(int arg3) {
        o.a().a(this.c);
        if(this.h != null) {
            int v0;
            for(v0 = this.h.size() - 1; v0 >= 0; --v0) {
                this.h.get(v0).a(this, arg3);
            }
        }

        ViewParent v3 = this.b.getParent();
        if((v3 instanceof ViewGroup)) {
            ((ViewGroup)v3).removeView(this.b);
        }
    }

    final void c() {
        SwipeDismissBehavior v1;
        if(this.b.getParent() == null) {
            ViewGroup$LayoutParams v0 = this.b.getLayoutParams();
            if((v0 instanceof android.support.design.widget.CoordinatorLayout$e)) {
                if(this.i == null) {
                    v1 = this.b();
                }
                else {
                    Behavior v1_1 = this.i;
                }

                if((v1 instanceof Behavior)) {
                    Behavior.a(v1, this);
                }

                v1.a(new android.support.design.widget.SwipeDismissBehavior$a() {
                    public void a(int arg2) {
                        switch(arg2) {
                            case 0: {
                                o.a().d(this.a.c);
                                break;
                            }
                            case 1: 
                            case 2: {
                                o.a().c(this.a.c);
                                break;
                            }
                            default: {
                                break;
                            }
                        }
                    }

                    public void a(View arg2) {
                        arg2.setVisibility(8);
                        this.a.a(0);
                    }
                });
                ((android.support.design.widget.CoordinatorLayout$e)v0).a(((android.support.design.widget.CoordinatorLayout$b)v1));
                ((android.support.design.widget.CoordinatorLayout$e)v0).g = 80;
            }

            this.f.addView(this.b);
        }

        this.b.setOnAttachStateChangeListener(new c() {
            public void a(View arg1) {
            }

            public void b(View arg2) {
                if(this.a.a()) {
                    BaseTransientBottomBar.a.post(new Runnable() {
                        public void run() {
                            this.a.a.c(3);
                        }
                    });
                }
            }
        });
        if(!t.A(this.b)) {
            this.b.setOnLayoutChangeListener(new d() {
                public void a(View arg1, int arg2, int arg3, int arg4, int arg5) {
                    this.a.b.setOnLayoutChangeListener(null);
                    if(this.a.f()) {
                        this.a.d();
                    }
                    else {
                        this.a.e();
                    }
                }
            });
        }
        else if(this.f()) {
            this.d();
        }
        else {
            this.e();
        }
    }

    private void d(int arg5) {
        ValueAnimator v0 = new ValueAnimator();
        v0.setIntValues(new int[]{0, this.h()});
        v0.setInterpolator(android.support.design.a.a.b);
        v0.setDuration(250);
        v0.addListener(new AnimatorListenerAdapter(arg5) {
            public void onAnimationEnd(Animator arg2) {
                this.b.c(this.a);
            }

            public void onAnimationStart(Animator arg3) {
                BaseTransientBottomBar.a(this.b).b(0, 180);
            }
        });
        v0.addUpdateListener(new ValueAnimator$AnimatorUpdateListener() {
            private int b;

            public void onAnimationUpdate(ValueAnimator arg3) {
                int v3 = arg3.getAnimatedValue().intValue();
                if(BaseTransientBottomBar.g()) {
                    t.e(this.a.b, v3 - this.b);
                }
                else {
                    this.a.b.setTranslationY(((float)v3));
                }

                this.b = v3;
            }
        });
        v0.start();
    }

    void d() {
        int v0 = this.h();
        if(BaseTransientBottomBar.d) {
            t.e(this.b, v0);
        }
        else {
            this.b.setTranslationY(((float)v0));
        }

        ValueAnimator v1 = new ValueAnimator();
        v1.setIntValues(new int[]{v0, 0});
        v1.setInterpolator(android.support.design.a.a.b);
        v1.setDuration(250);
        v1.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator arg1) {
                this.a.e();
            }

            public void onAnimationStart(Animator arg3) {
                BaseTransientBottomBar.a(this.a).a(70, 180);
            }
        });
        v1.addUpdateListener(new ValueAnimator$AnimatorUpdateListener(v0) {
            private int c;

            public void onAnimationUpdate(ValueAnimator arg3) {
                int v3 = arg3.getAnimatedValue().intValue();
                if(BaseTransientBottomBar.g()) {
                    t.e(this.b.b, v3 - this.c);
                }
                else {
                    this.b.b.setTranslationY(((float)v3));
                }

                this.c = v3;
            }
        });
        v1.start();
    }

    void e() {
        o.a().b(this.c);
        if(this.h != null) {
            int v0;
            for(v0 = this.h.size() - 1; v0 >= 0; --v0) {
                this.h.get(v0).a(this);
            }
        }
    }

    boolean f() {
        boolean v1 = true;
        List v0 = this.j.getEnabledAccessibilityServiceList(1);
        if(v0 == null || !v0.isEmpty()) {
            v1 = false;
        }
        else {
        }

        return v1;
    }

    static boolean g() {
        return BaseTransientBottomBar.d;
    }

    private int h() {
        int v0 = this.b.getHeight();
        ViewGroup$LayoutParams v1 = this.b.getLayoutParams();
        if((v1 instanceof ViewGroup$MarginLayoutParams)) {
            v0 += ((ViewGroup$MarginLayoutParams)v1).bottomMargin;
        }

        return v0;
    }
}

