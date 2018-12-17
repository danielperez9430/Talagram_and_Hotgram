package android.support.f;

import android.graphics.Matrix;
import android.view.View;
import android.view.ViewParent;

class ah {
    ah() {
        super();
    }

    public void a(View arg2, float arg3) {
        Object v0 = arg2.getTag(a.save_non_transition_alpha);
        if(v0 != null) {
            arg2.setAlpha(((Float)v0).floatValue() * arg3);
        }
        else {
            arg2.setAlpha(arg3);
        }
    }

    public void a(View arg1, int arg2, int arg3, int arg4, int arg5) {
        arg1.setLeft(arg2);
        arg1.setTop(arg3);
        arg1.setRight(arg4);
        arg1.setBottom(arg5);
    }

    public void a(View arg3, Matrix arg4) {
        ViewParent v0 = arg3.getParent();
        if((v0 instanceof View)) {
            this.a(((View)v0), arg4);
            arg4.preTranslate(((float)(-((View)v0).getScrollX())), ((float)(-((View)v0).getScrollY())));
        }

        arg4.preTranslate(((float)arg3.getLeft()), ((float)arg3.getTop()));
        Matrix v3 = arg3.getMatrix();
        if(!v3.isIdentity()) {
            arg4.preConcat(v3);
        }
    }

    public float a(View arg2) {
        Object v0 = arg2.getTag(a.save_non_transition_alpha);
        if(v0 != null) {
            return arg2.getAlpha() / ((Float)v0).floatValue();
        }

        return arg2.getAlpha();
    }

    public void b(View arg3, Matrix arg4) {
        ViewParent v0 = arg3.getParent();
        if((v0 instanceof View)) {
            this.b(((View)v0), arg4);
            arg4.postTranslate(((float)((View)v0).getScrollX()), ((float)((View)v0).getScrollY()));
        }

        arg4.postTranslate(((float)arg3.getLeft()), ((float)arg3.getTop()));
        Matrix v3 = arg3.getMatrix();
        if(!v3.isIdentity()) {
            Matrix v0_1 = new Matrix();
            if(v3.invert(v0_1)) {
                arg4.postConcat(v0_1);
            }
        }
    }

    public void b(View arg3) {
        if(arg3.getTag(a.save_non_transition_alpha) == null) {
            arg3.setTag(a.save_non_transition_alpha, Float.valueOf(arg3.getAlpha()));
        }
    }

    public void c(View arg3) {
        if(arg3.getVisibility() == 0) {
            arg3.setTag(a.save_non_transition_alpha, null);
        }
    }
}

