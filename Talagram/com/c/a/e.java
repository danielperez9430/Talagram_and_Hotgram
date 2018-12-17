package com.c.a;

import android.view.animation.Interpolator;
import java.util.ArrayList;

class e extends g {
    private int g;
    private int h;
    private int i;
    private boolean j;

    public e(b[] arg1) {
        super(((f[])arg1));
        this.j = true;
    }

    public e a() {
        ArrayList v0 = this.e;
        int v1 = this.e.size();
        b[] v2 = new b[v1];
        int v3;
        for(v3 = 0; v3 < v1; ++v3) {
            v2[v3] = v0.get(v3).e();
        }

        return new e(v2);
    }

    public Object a(float arg1) {
        return Integer.valueOf(this.b(arg1));
    }

    public int b(float arg6) {
        Interpolator v1_2;
        float v4;
        float v0_1;
        int v3;
        int v2;
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
                return this.g + (((int)(arg6 * (((float)this.i)))));
            }

            v6 = this.f.a(arg6, Integer.valueOf(this.g), Integer.valueOf(this.h));
        }
        else {
            if(arg6 <= 0f) {
                v0 = this.e.get(0);
                v1_1 = this.e.get(1);
                v2 = ((b)v0).f();
                v3 = ((b)v1_1).f();
                v0_1 = ((b)v0).c();
                v4 = ((b)v1_1).c();
                v1_2 = ((b)v1_1).d();
                if(v1_2 != null) {
                    arg6 = v1_2.getInterpolation(arg6);
                }

                arg6 = (arg6 - v0_1) / (v4 - v0_1);
                if(this.f == null) {
                    v2 += ((int)(arg6 * (((float)(v3 - v2)))));
                }
                else {
                    v2 = this.f.a(arg6, Integer.valueOf(v2), Integer.valueOf(v3)).intValue();
                }

                return v2;
            }

            if(arg6 >= 1f) {
                v0 = this.e.get(this.a - v1);
                v1_1 = this.e.get(this.a - 1);
                v2 = ((b)v0).f();
                v3 = ((b)v1_1).f();
                v0_1 = ((b)v0).c();
                v4 = ((b)v1_1).c();
                v1_2 = ((b)v1_1).d();
                if(v1_2 != null) {
                    arg6 = v1_2.getInterpolation(arg6);
                }

                arg6 = (arg6 - v0_1) / (v4 - v0_1);
                if(this.f == null) {
                    v2 += ((int)(arg6 * (((float)(v3 - v2)))));
                }
                else {
                    v2 = this.f.a(arg6, Integer.valueOf(v2), Integer.valueOf(v3)).intValue();
                }

                return v2;
            }

            v1_1 = this.e.get(0);
            int v0_2 = 1;
            while(v0_2 < this.a) {
                Object v2_1 = this.e.get(v0_2);
                if(arg6 < ((b)v2_1).c()) {
                    Interpolator v0_3 = ((b)v2_1).d();
                    if(v0_3 != null) {
                        arg6 = v0_3.getInterpolation(arg6);
                    }

                    arg6 = (arg6 - ((b)v1_1).c()) / (((b)v2_1).c() - ((b)v1_1).c());
                    v0_2 = ((b)v1_1).f();
                    v1 = ((b)v2_1).f();
                    if(this.f == null) {
                        v0_2 += ((int)(arg6 * (((float)(v1 - v0_2)))));
                    }
                    else {
                        v0_2 = this.f.a(arg6, Integer.valueOf(v0_2), Integer.valueOf(v1)).intValue();
                    }

                    return v0_2;
                }

                ++v0_2;
                v1_1 = v2_1;
            }

            v6 = this.e.get(this.a - 1).b();
        }

        return ((Number)v6).intValue();
    }

    public g b() {
        return this.a();
    }

    public Object clone() {
        return this.a();
    }
}

