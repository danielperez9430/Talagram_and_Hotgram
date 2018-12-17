package android.support.f;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.support.v4.view.t;
import android.view.View;
import android.view.ViewGroup;

public class d extends ai {
    class a extends AnimatorListenerAdapter {
        private final View a;
        private boolean b;

        a(View arg2) {
            super();
            this.b = false;
            this.a = arg2;
        }

        public void onAnimationEnd(Animator arg3) {
            ad.a(this.a, 1f);
            if(this.b) {
                this.a.setLayerType(0, null);
            }
        }

        public void onAnimationStart(Animator arg3) {
            if((t.u(this.a)) && this.a.getLayerType() == 0) {
                this.b = true;
                this.a.setLayerType(2, null);
            }
        }
    }

    public d(int arg1) {
        super();
        this.a(arg1);
    }

    public d() {
        super();
    }

    private static float a(s arg1, float arg2) {
        if(arg1 != null) {
            Object v1 = arg1.a.get("android:fade:transitionAlpha");
            if(v1 != null) {
                arg2 = ((Float)v1).floatValue();
            }
        }

        return arg2;
    }

    private Animator a(View arg3, float arg4, float arg5) {
        if(arg4 == arg5) {
            return null;
        }

        ad.a(arg3, arg4);
        ObjectAnimator v4 = ObjectAnimator.ofFloat(arg3, ad.a, new float[]{arg5});
        v4.addListener(new a(arg3));
        this.a(new n(arg3) {
            public void a(m arg3) {
                ad.a(this.a, 1f);
                ad.e(this.a);
                arg3.b(((c)this));
            }
        });
        return ((Animator)v4);
    }

    public Animator a(ViewGroup arg2, View arg3, s arg4, s arg5) {
        float v2 = 0f;
        float v4 = d.a(arg4, 0f);
        float v5 = 1f;
        if(v4 == v5) {
        }
        else {
            v2 = v4;
        }

        return this.a(arg3, v2, v5);
    }

    public void a(s arg3) {
        super.a(arg3);
        arg3.a.put("android:fade:transitionAlpha", Float.valueOf(ad.c(arg3.b)));
    }

    public Animator b(ViewGroup arg1, View arg2, s arg3, s arg4) {
        ad.d(arg2);
        return this.a(arg2, d.a(arg3, 1f), 0f);
    }
}

