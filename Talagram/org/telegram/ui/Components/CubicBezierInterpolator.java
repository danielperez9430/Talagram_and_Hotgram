package org.telegram.ui.Components;

import android.graphics.PointF;
import android.view.animation.Interpolator;

public class CubicBezierInterpolator implements Interpolator {
    public static final CubicBezierInterpolator DEFAULT;
    public static final CubicBezierInterpolator EASE_BOTH;
    public static final CubicBezierInterpolator EASE_IN;
    public static final CubicBezierInterpolator EASE_OUT;
    public static final CubicBezierInterpolator EASE_OUT_QUINT;
    protected PointF a;
    protected PointF b;
    protected PointF c;
    protected PointF end;
    protected PointF start;

    static {
        CubicBezierInterpolator.DEFAULT = new CubicBezierInterpolator(0.25, 0.1, 0.25, 1);
        CubicBezierInterpolator.EASE_OUT = new CubicBezierInterpolator(0, 0, 0.58, 1);
        CubicBezierInterpolator.EASE_OUT_QUINT = new CubicBezierInterpolator(0.23, 1, 0.32, 1);
        CubicBezierInterpolator.EASE_IN = new CubicBezierInterpolator(0.42, 0, 1, 1);
        CubicBezierInterpolator.EASE_BOTH = new CubicBezierInterpolator(0.42, 0, 0.58, 1);
    }

    public CubicBezierInterpolator(double arg1, double arg3, double arg5, double arg7) {
        this(((float)arg1), ((float)arg3), ((float)arg5), ((float)arg7));
    }

    public CubicBezierInterpolator(float arg2, float arg3, float arg4, float arg5) {
        this(new PointF(arg2, arg3), new PointF(arg4, arg5));
    }

    public CubicBezierInterpolator(PointF arg4, PointF arg5) {
        super();
        this.a = new PointF();
        this.b = new PointF();
        this.c = new PointF();
        if(arg4.x >= 0f) {
            float v2 = 1f;
            if(arg4.x <= v2) {
                if(arg5.x >= 0f && arg5.x <= v2) {
                    this.start = arg4;
                    this.end = arg5;
                    return;
                }

                throw new IllegalArgumentException("endX value must be in the range [0, 1]");
            }
        }

        throw new IllegalArgumentException("startX value must be in the range [0, 1]");
    }

    private float getBezierCoordinateX(float arg5) {
        this.c.x = this.start.x * 3f;
        this.b.x = (this.end.x - this.start.x) * 3f - this.c.x;
        this.a.x = 1f - this.c.x - this.b.x;
        return arg5 * (this.c.x + (this.b.x + this.a.x * arg5) * arg5);
    }

    protected float getBezierCoordinateY(float arg5) {
        this.c.y = this.start.y * 3f;
        this.b.y = (this.end.y - this.start.y) * 3f - this.c.y;
        this.a.y = 1f - this.c.y - this.b.y;
        return arg5 * (this.c.y + (this.b.y + this.a.y * arg5) * arg5);
    }

    public float getInterpolation(float arg1) {
        return this.getBezierCoordinateY(this.getXForTime(arg1));
    }

    private float getXDerivate(float arg5) {
        return this.c.x + arg5 * (this.b.x * 2f + this.a.x * 3f * arg5);
    }

    protected float getXForTime(float arg9) {
        int v0 = 1;
        float v1 = arg9;
        while(v0 < 14) {
            float v2 = this.getBezierCoordinateX(v1) - arg9;
            if((((double)Math.abs(v2))) < 0.001) {
            }
            else {
                v1 -= v2 / this.getXDerivate(v1);
                ++v0;
                continue;
            }

            return v1;
        }

        return v1;
    }
}

