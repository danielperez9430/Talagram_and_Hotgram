package com.c.a;

import android.view.animation.Interpolator;
import java.util.ArrayList;

class c extends g {
    private float g;
    private float h;
    private float i;
    private boolean j;

    public c(a[] arg1) {
        super(((f[])arg1));
        this.j = true;
    }

    public c a() {
        ArrayList v0 = this.e;
        int v1 = this.e.size();
        a[] v2 = new a[v1];
        int v3;
        for(v3 = 0; v3 < v1; ++v3) {
            v2[v3] = v0.get(v3).e();
        }

        return new c(v2);
    }

    public Object a(float arg1) {
        return Float.valueOf(this.b(arg1));
    }

    public float b(float arg6) {
        Interpolator v1_2;
        float v4;
        float v0_1;
        float v3;
        float v2;
        Object v1_1;
        Object v0;
        Object v6;
        int v1 = 2;
        if(this.a == v1) {
            if(this.j) {
                this.j = false;
                this.g = this.e.get(0).f();
                this.h = this.e.get(1).f();
                this.i = this.h - this.g;
            }

            if(this.d != null) {
                arg6 = this.d.getInterpolation(arg6);
            }

            if(this.f == null) {
                return this.g + arg6 * this.i;
            }

            v6 = this.f.a(arg6, Float.valueOf(this.g), Float.valueOf(this.h));
        }
        else {
            if(arg6 <= 0f) {
                v0 = this.e.get(0);
                v1_1 = this.e.get(1);
                v2 = ((a)v0).f();
                v3 = ((a)v1_1).f();
                v0_1 = ((a)v0).c();
                v4 = ((a)v1_1).c();
                v1_2 = ((a)v1_1).d();
                if(v1_2 != null) {
                    arg6 = v1_2.getInterpolation(arg6);
                }

                arg6 = (arg6 - v0_1) / (v4 - v0_1);
                if(this.f == null) {
                    v2 += arg6 * (v3 - v2);
                }
                else {
                    v2 = this.f.a(arg6, Float.valueOf(v2), Float.valueOf(v3)).floatValue();
                }

                return v2;
            }

            if(arg6 >= 1f) {
                v0 = this.e.get(this.a - v1);
                v1_1 = this.e.get(this.a - 1);
                v2 = ((a)v0).f();
                v3 = ((a)v1_1).f();
                v0_1 = ((a)v0).c();
                v4 = ((a)v1_1).c();
                v1_2 = ((a)v1_1).d();
                if(v1_2 != null) {
                    arg6 = v1_2.getInterpolation(arg6);
                }

                arg6 = (arg6 - v0_1) / (v4 - v0_1);
                if(this.f == null) {
                    v2 += arg6 * (v3 - v2);
                }
                else {
                    v2 = this.f.a(arg6, Float.valueOf(v2), Float.valueOf(v3)).floatValue();
                }

                return v2;
            }

            v1_1 = this.e.get(0);
            int v0_2 = 1;
            while(v0_2 < this.a) {
                Object v2_1 = this.e.get(v0_2);
                if(arg6 < ((a)v2_1).c()) {
                    Interpolator v0_3 = ((a)v2_1).d();
                    if(v0_3 != null) {
                        arg6 = v0_3.getInterpolation(arg6);
                    }

                    arg6 = (arg6 - ((a)v1_1).c()) / (((a)v2_1).c() - ((a)v1_1).c());
                    v0_1 = ((a)v1_1).f();
                    float v1_3 = ((a)v2_1).f();
                    if(this.f == null) {
                        v0_1 += arg6 * (v1_3 - v0_1);
                    }
                    else {
                        v0_1 = this.f.a(arg6, Float.valueOf(v0_1), Float.valueOf(v1_3)).floatValue();
                    }

                    return v0_1;
                }

                ++v0_2;
                v1_1 = v2_1;
            }

            v6 = this.e.get(this.a - 1).b();
        }

        return ((Number)v6).floatValue();
    }

    public g b() {
        return this.a();
    }

    public Object clone() {
        return this.a();
    }
}

