package android.support.design.behavior;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.support.design.a.a;
import android.support.design.widget.CoordinatorLayout$b;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewPropertyAnimator;

public class HideBottomViewOnScrollBehavior extends b {
    private int a;
    private int b;
    private ViewPropertyAnimator c;

    public HideBottomViewOnScrollBehavior() {
        super();
        this.a = 0;
        this.b = 2;
    }

    public HideBottomViewOnScrollBehavior(Context arg1, AttributeSet arg2) {
        super(arg1, arg2);
        this.a = 0;
        this.b = 2;
    }

    static ViewPropertyAnimator a(HideBottomViewOnScrollBehavior arg0, ViewPropertyAnimator arg1) {
        arg0.c = arg1;
        return arg1;
    }

    private void a(View arg1, int arg2, long arg3, TimeInterpolator arg5) {
        this.c = arg1.animate().translationY(((float)arg2)).setInterpolator(arg5).setDuration(arg3).setListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator arg2) {
                HideBottomViewOnScrollBehavior.a(this.a, null);
            }
        });
    }

    public void a(CoordinatorLayout arg1, View arg2, View arg3, int arg4, int arg5, int arg6, int arg7) {
        if(this.b != 1 && arg5 > 0) {
            this.b(arg2);
        }
        else if(this.b != 2 && arg5 < 0) {
            this.a(arg2);
        }
    }

    protected void a(View arg8) {
        if(this.c != null) {
            this.c.cancel();
            arg8.clearAnimation();
        }

        this.b = 2;
        this.a(arg8, 0, 225, a.d);
    }

    public boolean a(CoordinatorLayout arg2, View arg3, int arg4) {
        this.a = arg3.getMeasuredHeight();
        return super.a(arg2, arg3, arg4);
    }

    public boolean a(CoordinatorLayout arg1, View arg2, View arg3, View arg4, int arg5) {
        boolean v1 = arg5 == 2 ? true : false;
        return v1;
    }

    protected void b(View arg8) {
        if(this.c != null) {
            this.c.cancel();
            arg8.clearAnimation();
        }

        this.b = 1;
        this.a(arg8, this.a, 175, a.c);
    }
}

