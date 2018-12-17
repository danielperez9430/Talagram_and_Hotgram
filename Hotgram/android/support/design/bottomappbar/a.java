package android.support.design.bottomappbar;

import android.support.design.g.b;
import android.support.design.g.d;

public class a extends b {
    private float a;
    private float b;
    private float c;
    private float d;
    private float e;

    float a() {
        return this.e;
    }

    void a(float arg1) {
        this.e = arg1;
    }

    public void a(float arg21, float arg22, d arg23) {
        a v0 = this;
        float v1 = arg21;
        d v9 = arg23;
        if(v0.c == 0f) {
            v9.b(v1, 0f);
            return;
        }

        float v11 = 2f;
        float v12 = (v0.b * v11 + v0.c) / v11;
        float v13 = arg22 * v0.a;
        float v14 = v1 / v11 + v0.e;
        float v15 = v0.d * arg22 + (1f - arg22) * v12;
        if(v15 / v12 >= 1f) {
            v9.b(v1, 0f);
            return;
        }

        float v2 = v12 + v13;
        float v3 = v15 + v13;
        v2 = ((float)Math.sqrt(((double)(v2 * v2 - v3 * v3))));
        float v4 = v14 - v2;
        float v16 = v14 + v2;
        float v8 = ((float)Math.toDegrees(Math.atan(((double)(v2 / v3)))));
        float v17 = 90f - v8;
        v3 = v4 - v13;
        v9.b(v3, 0f);
        float v18 = v13 * v11;
        arg23.a(v3, 0f, v4 + v13, v18, 270f, v8);
        arg23.a(v14 - v12, -v12 - v15, v14 + v12, v12 - v15, 180f - v17, v17 * v11 - 180f);
        arg23.a(v16 - v13, 0f, v16 + v13, v18, 270f - v8, v8);
        v9.b(v1, 0f);
    }

    float b() {
        return this.d;
    }

    void b(float arg1) {
        this.d = arg1;
    }

    float c() {
        return this.c;
    }

    void c(float arg1) {
        this.c = arg1;
    }

    float d() {
        return this.b;
    }

    void d(float arg1) {
        this.b = arg1;
    }

    float e() {
        return this.a;
    }

    void e(float arg1) {
        this.a = arg1;
    }
}

