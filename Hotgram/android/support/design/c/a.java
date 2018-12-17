package android.support.design.c;

import android.animation.Animator$AnimatorListener;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Build$VERSION;
import android.view.View;
import android.view.ViewAnimationUtils;

public final class a {
    public static Animator$AnimatorListener a(d arg1) {
        return new AnimatorListenerAdapter(arg1) {
            public void onAnimationEnd(Animator arg1) {
                this.a.b();
            }

            public void onAnimationStart(Animator arg1) {
                this.a.a();
            }
        };
    }

    public static Animator a(d arg6, float arg7, float arg8, float arg9) {
        ObjectAnimator v0 = ObjectAnimator.ofObject(arg6, b.a, android.support.design.c.d$a.a, new android.support.design.c.d$d[]{new android.support.design.c.d$d(arg7, arg8, arg9)});
        if(Build$VERSION.SDK_INT >= 21) {
            android.support.design.c.d$d v1 = arg6.getRevealInfo();
            if(v1 != null) {
                Animator v6 = ViewAnimationUtils.createCircularReveal(((View)arg6), ((int)arg7), ((int)arg8), v1.c, arg9);
                AnimatorSet v7 = new AnimatorSet();
                v7.playTogether(new Animator[]{v0, v6});
                return ((Animator)v7);
            }

            throw new IllegalStateException("Caller must set a non-null RevealInfo before calling this.");
        }

        return ((Animator)v0);
    }
}

