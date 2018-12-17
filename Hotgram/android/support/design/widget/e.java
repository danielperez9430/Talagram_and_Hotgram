package android.support.design.widget;

import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

public class e {
    private static final ThreadLocal a;
    private static final ThreadLocal b;

    static {
        e.a = new ThreadLocal();
        e.b = new ThreadLocal();
    }

    public static void a(ViewGroup arg3, View arg4, Rect arg5) {
        Matrix v0_1;
        Object v0 = e.a.get();
        if(v0 == null) {
            v0_1 = new Matrix();
            e.a.set(v0_1);
        }
        else {
            ((Matrix)v0).reset();
        }

        e.a(((ViewParent)arg3), arg4, v0_1);
        Object v3 = e.b.get();
        if(v3 == null) {
            RectF v3_1 = new RectF();
            e.b.set(v3_1);
        }

        ((RectF)v3).set(arg5);
        v0_1.mapRect(((RectF)v3));
        arg5.set(((int)(((RectF)v3).left + 0.5f)), ((int)(((RectF)v3).top + 0.5f)), ((int)(((RectF)v3).right + 0.5f)), ((int)(((RectF)v3).bottom + 0.5f)));
    }

    private static void a(ViewParent arg2, View arg3, Matrix arg4) {
        ViewParent v0 = arg3.getParent();
        if(((v0 instanceof View)) && v0 != arg2) {
            e.a(arg2, ((View)v0), arg4);
            arg4.preTranslate(((float)(-((View)v0).getScrollX())), ((float)(-((View)v0).getScrollY())));
        }

        arg4.preTranslate(((float)arg3.getLeft()), ((float)arg3.getTop()));
        if(!arg3.getMatrix().isIdentity()) {
            arg4.preConcat(arg3.getMatrix());
        }
    }

    public static void b(ViewGroup arg3, View arg4, Rect arg5) {
        arg5.set(0, 0, arg4.getWidth(), arg4.getHeight());
        e.a(arg3, arg4, arg5);
    }
}

