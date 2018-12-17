package com.c.a;

import android.view.animation.Interpolator;
import java.util.ArrayList;
import java.util.Arrays;

class g {
    int a;
    f b;
    f c;
    Interpolator d;
    ArrayList e;
    k f;

    public g(f[] arg2) {
        super();
        this.a = arg2.length;
        this.e = new ArrayList();
        this.e.addAll(Arrays.asList(((Object[])arg2)));
        this.b = this.e.get(0);
        this.c = this.e.get(this.a - 1);
        this.d = this.c.d();
    }

    public static g a(float[] arg6) {
        int v0 = arg6.length;
        a[] v1 = new a[Math.max(v0, 2)];
        int v4 = 1;
        if(v0 == 1) {
            v1[0] = f.b(0f);
            v1[1] = f.a(1f, arg6[0]);
        }
        else {
            v1[0] = f.a(0f, arg6[0]);
            while(v4 < v0) {
                v1[v4] = f.a((((float)v4)) / (((float)(v0 - 1))), arg6[v4]);
                ++v4;
            }
        }

        return new c(v1);
    }

    public static g a(int[] arg6) {
        int v0 = arg6.length;
        b[] v1 = new b[Math.max(v0, 2)];
        int v4 = 1;
        if(v0 == 1) {
            v1[0] = f.a(0f);
            v1[1] = f.a(1f, arg6[0]);
        }
        else {
            v1[0] = f.a(0f, arg6[0]);
            while(v4 < v0) {
                v1[v4] = f.a((((float)v4)) / (((float)(v0 - 1))), arg6[v4]);
                ++v4;
            }
        }

        return new e(v1);
    }

    public Object a(float arg5) {
        // Method was not decompiled
    }

    public void a(k arg1) {
        this.f = arg1;
    }

    public g b() {
        ArrayList v0 = this.e;
        int v1 = this.e.size();
        f[] v2 = new f[v1];
        int v3;
        for(v3 = 0; v3 < v1; ++v3) {
            v2[v3] = v0.get(v3).e();
        }

        return new g(v2);
    }

    public Object clone() {
        return this.b();
    }

    public String toString() {
        String v0 = " ";
        int v1;
        for(v1 = 0; v1 < this.a; ++v1) {
            v0 = v0 + this.e.get(v1).b() + "  ";
        }

        return v0;
    }
}

