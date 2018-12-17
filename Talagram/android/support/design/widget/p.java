package android.support.design.widget;

import android.animation.Animator$AnimatorListener;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.util.StateSet;
import java.util.ArrayList;

public final class p {
    class android.support.design.widget.p$1 extends AnimatorListenerAdapter {
        android.support.design.widget.p$1(p arg1) {
            this.a = arg1;
            super();
        }

        public void onAnimationEnd(Animator arg2) {
            if(this.a.a == arg2) {
                this.a.a = null;
            }
        }
    }

    class a {
        final int[] a;
        final ValueAnimator b;

        a(int[] arg1, ValueAnimator arg2) {
            super();
            this.a = arg1;
            this.b = arg2;
        }
    }

    ValueAnimator a;
    private final ArrayList b;
    private a c;
    private final Animator$AnimatorListener d;

    public p() {
        super();
        this.b = new ArrayList();
        this.c = null;
        this.a = null;
        this.d = new android.support.design.widget.p$1(this);
    }

    public void a(int[] arg2, ValueAnimator arg3) {
        a v0 = new a(arg2, arg3);
        arg3.addListener(this.d);
        this.b.add(v0);
    }

    public void a(int[] arg5) {
        Object v2;
        int v0 = this.b.size();
        int v1 = 0;
        while(true) {
            if(v1 < v0) {
                v2 = this.b.get(v1);
                if(StateSet.stateSetMatches(((a)v2).a, arg5)) {
                }
                else {
                    ++v1;
                    continue;
                }
            }
            else {
                break;
            }

            goto label_13;
        }

        v2 = null;
    label_13:
        if(v2 == this.c) {
            return;
        }

        if(this.c != null) {
            this.b();
        }

        this.c = ((a)v2);
        if(v2 != null) {
            this.a(((a)v2));
        }
    }

    public void a() {
        if(this.a != null) {
            this.a.end();
            this.a = null;
        }
    }

    private void a(a arg1) {
        this.a = arg1.b;
        this.a.start();
    }

    private void b() {
        if(this.a != null) {
            this.a.cancel();
            this.a = null;
        }
    }
}

