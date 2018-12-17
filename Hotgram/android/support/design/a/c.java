package android.support.design.a;

import android.animation.TypeEvaluator;

public class c implements TypeEvaluator {
    private static final c a;

    static {
        c.a = new c();
    }

    public c() {
        super();
    }

    public static c a() {
        return c.a;
    }

    public Integer a(float arg12, Integer arg13, Integer arg14) {
        int v13 = arg13.intValue();
        float v0 = (((float)(v13 >> 24 & 255))) / 255f;
        int v14 = arg14.intValue();
        float v2 = ((float)Math.pow(((double)((((float)(v13 >> 16 & 255))) / 255f)), 2.2));
        float v3 = ((float)Math.pow(((double)((((float)(v13 >> 8 & 255))) / 255f)), 2.2));
        float v13_1 = ((float)Math.pow(((double)((((float)(v13 & 255))) / 255f)), 2.2));
        return Integer.valueOf(Math.round((((float)Math.pow(((double)(v2 + ((((float)Math.pow(((double)((((float)(v14 >> 16 & 255))) / 255f)), 2.2))) - v2) * arg12)), 0.454545))) * 255f) << 16 | Math.round((v0 + ((((float)(v14 >> 24 & 255))) / 255f - v0) * arg12) * 255f) << 24 | Math.round((((float)Math.pow(((double)(v3 + ((((float)Math.pow(((double)((((float)(v14 >> 8 & 255))) / 255f)), 2.2))) - v3) * arg12)), 0.454545))) * 255f) << 8 | Math.round((((float)Math.pow(((double)(v13_1 + arg12 * ((((float)Math.pow(((double)((((float)(v14 & 255))) / 255f)), 2.2))) - v13_1))), 0.454545))) * 255f));
    }

    public Object evaluate(float arg1, Object arg2, Object arg3) {
        return this.a(arg1, ((Integer)arg2), ((Integer)arg3));
    }
}

