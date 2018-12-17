package android.support.v4.view.b;

import android.view.animation.Interpolator;

abstract class d implements Interpolator {
    private final float[] a;
    private final float b;

    protected d(float[] arg2) {
        super();
        this.a = arg2;
        this.b = 1f / (((float)(this.a.length - 1)));
    }

    public float getInterpolation(float arg5) {
        float v0 = 1f;
        if(arg5 >= v0) {
            return v0;
        }

        if(arg5 <= 0f) {
            return 0;
        }

        int v0_1 = Math.min(((int)((((float)(this.a.length - 1))) * arg5)), this.a.length - 2);
        return this.a[v0_1] + (arg5 - (((float)v0_1)) * this.b) / this.b * (this.a[v0_1 + 1] - this.a[v0_1]);
    }
}

