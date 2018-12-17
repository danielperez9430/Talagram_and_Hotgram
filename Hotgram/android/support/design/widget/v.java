package android.support.design.widget;

import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.animation.StateListAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.design.a$g;
import android.support.design.internal.b;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewOutlineProvider;

class v {
    private static final int[] a;

    static {
        v.a = new int[]{16843848};
    }

    static void a(View arg1) {
        arg1.setOutlineProvider(ViewOutlineProvider.BOUNDS);
    }

    static void a(View arg8, AttributeSet arg9, int arg10, int arg11) {
        Context v6 = arg8.getContext();
        TypedArray v9 = b.a(v6, arg9, v.a, arg10, arg11, new int[0]);
        try {
            if(v9.hasValue(0)) {
                arg8.setStateListAnimator(AnimatorInflater.loadStateListAnimator(v6, v9.getResourceId(0, 0)));
            }
        }
        catch(Throwable v8) {
            v9.recycle();
            throw v8;
        }

        v9.recycle();
    }

    static void a(View arg11, float arg12) {
        int v0 = arg11.getResources().getInteger(g.app_bar_elevation_anim_duration);
        StateListAnimator v1 = new StateListAnimator();
        long v9 = ((long)v0);
        v1.addState(new int[]{16842766, android.support.design.a$b.state_liftable, -android.support.design.a$b.state_lifted}, ObjectAnimator.ofFloat(arg11, "elevation", new float[]{0f}).setDuration(v9));
        v1.addState(new int[]{16842766}, ObjectAnimator.ofFloat(arg11, "elevation", new float[]{arg12}).setDuration(v9));
        v1.addState(new int[0], ObjectAnimator.ofFloat(arg11, "elevation", new float[]{0f}).setDuration(0));
        arg11.setStateListAnimator(v1);
    }
}

