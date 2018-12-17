package android.support.v7.widget.a;

import android.graphics.Canvas;
import android.os.Build$VERSION;
import android.support.v4.view.t;
import android.support.v7.widget.RecyclerView;
import android.view.View;

class c implements b {
    static final b a;

    static {
        c.a = new c();
    }

    c() {
        super();
    }

    private static float a(RecyclerView arg5, View arg6) {
        int v0 = arg5.getChildCount();
        float v1 = 0f;
        int v2;
        for(v2 = 0; v2 < v0; ++v2) {
            View v3 = arg5.getChildAt(v2);
            if(v3 == arg6) {
            }
            else {
                float v3_1 = t.p(v3);
                if(v3_1 > v1) {
                    v1 = v3_1;
                }
            }
        }

        return v1;
    }

    public void a(Canvas arg1, RecyclerView arg2, View arg3, float arg4, float arg5, int arg6, boolean arg7) {
        if(Build$VERSION.SDK_INT >= 21 && (arg7) && arg3.getTag(android.support.v7.e.a$b.item_touch_helper_previous_elevation) == null) {
            Float v1 = Float.valueOf(t.p(arg3));
            t.k(arg3, c.a(arg2, arg3) + 1f);
            arg3.setTag(android.support.v7.e.a$b.item_touch_helper_previous_elevation, v1);
        }

        arg3.setTranslationX(arg4);
        arg3.setTranslationY(arg5);
    }

    public void a(View arg3) {
        if(Build$VERSION.SDK_INT >= 21) {
            Object v0 = arg3.getTag(android.support.v7.e.a$b.item_touch_helper_previous_elevation);
            if(v0 != null && ((v0 instanceof Float))) {
                t.k(arg3, ((Float)v0).floatValue());
            }

            arg3.setTag(android.support.v7.e.a$b.item_touch_helper_previous_elevation, null);
        }

        arg3.setTranslationX(0f);
        arg3.setTranslationY(0f);
    }

    public void b(Canvas arg1, RecyclerView arg2, View arg3, float arg4, float arg5, int arg6, boolean arg7) {
    }

    public void b(View arg1) {
    }
}

