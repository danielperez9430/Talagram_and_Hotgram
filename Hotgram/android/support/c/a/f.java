package android.support.c.a;

import android.animation.TypeEvaluator;

public class f implements TypeEvaluator {
    private static final f a;

    static {
        f.a = new f();
    }

    public f() {
        super();
    }

    public static f a() {
        return f.a;
    }

    public Object evaluate(float arg12, Object arg13, Object arg14) {
        int v13 = ((Integer)arg13).intValue();
        float v0 = (((float)(v13 >> 24 & 255))) / 255f;
        int v14 = ((Integer)arg14).intValue();
        float v2 = ((float)Math.pow(((double)((((float)(v13 >> 16 & 255))) / 255f)), 2.2));
        float v3 = ((float)Math.pow(((double)((((float)(v13 >> 8 & 255))) / 255f)), 2.2));
        float v13_1 = ((float)Math.pow(((double)((((float)(v13 & 255))) / 255f)), 2.2));
        return Integer.valueOf(Math.round((((float)Math.pow(((double)(v2 + ((((float)Math.pow(((double)((((float)(v14 >> 16 & 255))) / 255f)), 2.2))) - v2) * arg12)), 0.454545))) * 255f) << 16 | Math.round((v0 + ((((float)(v14 >> 24 & 255))) / 255f - v0) * arg12) * 255f) << 24 | Math.round((((float)Math.pow(((double)(v3 + ((((float)Math.pow(((double)((((float)(v14 >> 8 & 255))) / 255f)), 2.2))) - v3) * arg12)), 0.454545))) * 255f) << 8 | Math.round((((float)Math.pow(((double)(v13_1 + arg12 * ((((float)Math.pow(((double)((((float)(v14 & 255))) / 255f)), 2.2))) - v13_1))), 0.454545))) * 255f));
    }
}

