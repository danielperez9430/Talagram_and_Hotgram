package android.support.design.f;

import android.annotation.TargetApi;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build$VERSION;
import android.util.StateSet;

public class a {
    public static final boolean a;
    private static final int[] b;
    private static final int[] c;
    private static final int[] d;
    private static final int[] e;
    private static final int[] f;
    private static final int[] g;
    private static final int[] h;
    private static final int[] i;
    private static final int[] j;

    static {
        boolean v0 = Build$VERSION.SDK_INT >= 21 ? true : false;
        a.a = v0;
        a.b = new int[]{16842919};
        a.c = new int[]{16843623, 16842908};
        a.d = new int[]{16842908};
        a.e = new int[]{16843623};
        a.f = new int[]{16842913, 16842919};
        a.g = new int[]{16842913, 16843623, 16842908};
        a.h = new int[]{16842913, 16842908};
        a.i = new int[]{16842913, 16843623};
        a.j = new int[]{16842913};
    }

    public static ColorStateList a(ColorStateList arg6) {
        int v2 = 2;
        if(a.a) {
            int[][] v0 = new int[v2][];
            int[] v2_1 = new int[v2];
            v0[0] = a.j;
            v2_1[0] = a.a(arg6, a.f);
            v0[1] = StateSet.NOTHING;
            v2_1[1] = a.a(arg6, a.b);
            return new ColorStateList(v0, v2_1);
        }

        int[][] v4 = new int[10][];
        int[] v0_1 = new int[10];
        v4[0] = a.f;
        v0_1[0] = a.a(arg6, a.f);
        v4[1] = a.g;
        v0_1[1] = a.a(arg6, a.g);
        v4[v2] = a.h;
        v0_1[v2] = a.a(arg6, a.h);
        v4[3] = a.i;
        v0_1[3] = a.a(arg6, a.i);
        v4[4] = a.j;
        v0_1[4] = 0;
        v4[5] = a.b;
        v0_1[5] = a.a(arg6, a.b);
        v4[6] = a.c;
        v0_1[6] = a.a(arg6, a.c);
        v4[7] = a.d;
        v0_1[7] = a.a(arg6, a.d);
        v4[8] = a.e;
        v0_1[8] = a.a(arg6, a.e);
        v4[9] = StateSet.NOTHING;
        v0_1[9] = 0;
        return new ColorStateList(v4, v0_1);
    }

    @TargetApi(value=21) private static int a(int arg2) {
        return android.support.v4.graphics.a.c(arg2, Math.min(Color.alpha(arg2) * 2, 255));
    }

    private static int a(ColorStateList arg1, int[] arg2) {
        int v1 = arg1 != null ? arg1.getColorForState(arg2, arg1.getDefaultColor()) : 0;
        if(a.a) {
            v1 = a.a(v1);
        }

        return v1;
    }
}

