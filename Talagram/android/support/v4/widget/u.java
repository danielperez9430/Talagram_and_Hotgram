package android.support.v4.widget;

import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

public class u {
    private static final ThreadLocal a;
    private static final ThreadLocal b;

    static {
        u.a = new ThreadLocal();
        u.b = new ThreadLocal();
    }

    static void a(ViewGroup arg3, View arg4, Rect arg5) {
        RectF v3_1;
        Object v0 = u.a.get();
        if(v0 == null) {
            Matrix v0_1 = new Matrix();
            u.a.set(v0_1);
        }
        else {
            ((Matrix)v0).reset();
        }

        u.a(((ViewParent)arg3), arg4, ((Matrix)v0));
        Object v3 = u.b.get();
        if(v3 == null) {
            v3_1 = new RectF();
            u.b.set(v3_1);
        }

        v3_1.set(arg5);
        ((Matrix)v0).mapRect(v3_1);
        arg5.set(((int)(v3_1.left + 0.5f)), ((int)(v3_1.top + 0.5f)), ((int)(v3_1.right + 0.5f)), ((int)(v3_1.bottom + 0.5f)));
    }

    private static void a(ViewParent arg2, View arg3, Matrix arg4) {
        ViewParent v0 = arg3.getParent();
        if(((v0 instanceof View)) && v0 != arg2) {
            u.a(arg2, ((View)v0), arg4);
            arg4.preTranslate(((float)(-((View)v0).getScrollX())), ((float)(-((View)v0).getScrollY())));
        }

        arg4.preTranslate(((float)arg3.getLeft()), ((float)arg3.getTop()));
        if(!arg3.getMatrix().isIdentity()) {
            arg4.preConcat(arg3.getMatrix());
        }
    }

    public static void b(ViewGroup arg3, View arg4, Rect arg5) {
        arg5.set(0, 0, arg4.getWidth(), arg4.getHeight());
        u.a(arg3, arg4, arg5);
    }
}

