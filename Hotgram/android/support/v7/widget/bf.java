package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.v4.graphics.a;
import android.util.TypedValue;

class bf {
    static final int[] a;
    static final int[] b;
    static final int[] c;
    static final int[] d;
    static final int[] e;
    static final int[] f;
    static final int[] g;
    static final int[] h;
    private static final ThreadLocal i;
    private static final int[] j;

    static {
        bf.i = new ThreadLocal();
        bf.a = new int[]{-16842910};
        bf.b = new int[]{16842908};
        bf.c = new int[]{16843518};
        bf.d = new int[]{16842919};
        bf.e = new int[]{16842912};
        bf.f = new int[]{16842913};
        bf.g = new int[]{-16842919, -16842908};
        bf.h = new int[0];
        bf.j = new int[1];
    }

    public static int a(Context arg2, int arg3) {
        bf.j[0] = arg3;
        bk v2 = bk.a(arg2, null, bf.j);
        try {
            arg3 = v2.b(0, 0);
        }
        catch(Throwable v3) {
            v2.a();
            throw v3;
        }

        v2.a();
        return arg3;
    }

    static int a(Context arg0, int arg1, float arg2) {
        int v0 = bf.a(arg0, arg1);
        return a.c(v0, Math.round((((float)Color.alpha(v0))) * arg2));
    }

    private static TypedValue a() {
        Object v0 = bf.i.get();
        if(v0 == null) {
            TypedValue v0_1 = new TypedValue();
            bf.i.set(v0_1);
        }

        return ((TypedValue)v0);
    }

    public static ColorStateList b(Context arg2, int arg3) {
        ColorStateList v3_1;
        bf.j[0] = arg3;
        bk v2 = bk.a(arg2, null, bf.j);
        try {
            v3_1 = v2.e(0);
        }
        catch(Throwable v3) {
            v2.a();
            throw v3;
        }

        v2.a();
        return v3_1;
    }

    public static int c(Context arg4, int arg5) {
        ColorStateList v0 = bf.b(arg4, arg5);
        if(v0 != null && (v0.isStateful())) {
            return v0.getColorForState(bf.a, v0.getDefaultColor());
        }

        TypedValue v0_1 = bf.a();
        arg4.getTheme().resolveAttribute(16842803, v0_1, true);
        return bf.a(arg4, arg5, v0_1.getFloat());
    }
}

