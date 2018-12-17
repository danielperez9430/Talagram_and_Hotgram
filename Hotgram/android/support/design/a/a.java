package android.support.design.a;

import android.animation.TimeInterpolator;
import android.support.v4.view.b.b;
import android.support.v4.view.b.c;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;

public class a {
    public static final TimeInterpolator a;
    public static final TimeInterpolator b;
    public static final TimeInterpolator c;
    public static final TimeInterpolator d;
    public static final TimeInterpolator e;

    static {
        a.a = new LinearInterpolator();
        a.b = new b();
        a.c = new android.support.v4.view.b.a();
        a.d = new c();
        a.e = new DecelerateInterpolator();
    }

    public static float a(float arg0, float arg1, float arg2) {
        return arg0 + arg2 * (arg1 - arg0);
    }

    public static int a(int arg0, int arg1, float arg2) {
        return arg0 + Math.round(arg2 * (((float)(arg1 - arg0))));
    }
}

