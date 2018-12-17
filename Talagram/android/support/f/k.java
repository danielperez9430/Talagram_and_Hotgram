package android.support.f;

import android.animation.TypeEvaluator;
import android.graphics.Rect;

class k implements TypeEvaluator {
    private Rect a;

    k() {
        super();
    }

    public Rect a(float arg6, Rect arg7, Rect arg8) {
        int v0 = arg7.left + (((int)((((float)(arg8.left - arg7.left))) * arg6)));
        int v1 = arg7.top + (((int)((((float)(arg8.top - arg7.top))) * arg6)));
        int v2 = arg7.right + (((int)((((float)(arg8.right - arg7.right))) * arg6)));
        int v3 = arg7.bottom + (((int)((((float)(arg8.bottom - arg7.bottom))) * arg6)));
        if(this.a == null) {
            return new Rect(v0, v1, v2, v3);
        }

        this.a.set(v0, v1, v2, v3);
        return this.a;
    }

    public Object evaluate(float arg1, Object arg2, Object arg3) {
        return this.a(arg1, ((Rect)arg2), ((Rect)arg3));
    }
}

