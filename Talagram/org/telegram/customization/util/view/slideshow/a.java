package org.telegram.customization.util.view.slideshow;

import android.content.Context;
import android.view.animation.Interpolator;
import android.widget.Scroller;

public class a extends Scroller {
    private double a;

    public a(Context arg1, Interpolator arg2) {
        super(arg1, arg2);
        this.a = 1;
    }

    public void a(double arg1) {
        this.a = arg1;
    }

    public void startScroll(int arg9, int arg10, int arg11, int arg12, int arg13) {
        double v0 = ((double)arg13);
        double v2 = this.a;
        Double.isNaN(v0);
        super.startScroll(arg9, arg10, arg11, arg12, ((int)(v0 * v2)));
    }
}

