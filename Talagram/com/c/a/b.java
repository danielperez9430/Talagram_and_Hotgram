package com.c.a;

public class b implements k {
    public b() {
        super();
    }

    public Float a(float arg1, Number arg2, Number arg3) {
        float v2 = arg2.floatValue();
        return Float.valueOf(v2 + arg1 * (arg3.floatValue() - v2));
    }

    public Object a(float arg1, Object arg2, Object arg3) {
        return this.a(arg1, ((Number)arg2), ((Number)arg3));
    }
}

