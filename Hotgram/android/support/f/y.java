package android.support.f;

import android.animation.Animator;
import android.animation.LayoutTransition;
import android.util.Log;
import android.view.ViewGroup;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class y {
    private static LayoutTransition a;
    private static Field b;
    private static boolean c;
    private static Method d;
    private static boolean e;

    static void a(ViewGroup arg5, boolean arg6) {
        boolean v2 = false;
        Animator v3 = null;
        if(y.a == null) {
            y.a = new LayoutTransition() {
                public boolean isChangingLayout() {
                    return 1;
                }
            };
            y.a.setAnimator(2, v3);
            y.a.setAnimator(0, v3);
            y.a.setAnimator(1, v3);
            y.a.setAnimator(3, v3);
            y.a.setAnimator(4, v3);
        }

        if(arg6) {
            LayoutTransition v6 = arg5.getLayoutTransition();
            if(v6 != null) {
                if(v6.isRunning()) {
                    y.a(v6);
                }

                if(v6 == y.a) {
                    goto label_31;
                }

                arg5.setTag(a.transition_layout_save, v6);
            }

        label_31:
            v6 = y.a;
            goto label_32;
        }

        arg5.setLayoutTransition(((LayoutTransition)v3));
        if(!y.c) {
            try {
                y.b = ViewGroup.class.getDeclaredField("mLayoutSuppressed");
                y.b.setAccessible(true);
            }
            catch(NoSuchFieldException ) {
                Log.i("ViewGroupUtilsApi14", "Failed to access mLayoutSuppressed field by reflection");
            }

            y.c = true;
        }

        if(y.b == null) {
            goto label_63;
        }

        try {
            arg6 = y.b.getBoolean(arg5);
            if(arg6) {
                goto label_53;
            }

            goto label_58;
        }
        catch(IllegalAccessException ) {
            goto label_60;
        }

        try {
        label_53:
            y.b.setBoolean(arg5, false);
            goto label_58;
        }
        catch(IllegalAccessException ) {
            v2 = arg6;
        }

    label_60:
        Log.i("ViewGroupUtilsApi14", "Failed to get mLayoutSuppressed field by reflection");
        goto label_63;
    label_58:
        v2 = arg6;
    label_63:
        if(v2) {
            arg5.requestLayout();
        }

        Object v6_1 = arg5.getTag(a.transition_layout_save);
        if(v6_1 != null) {
            arg5.setTag(a.transition_layout_save, v3);
        label_32:
            arg5.setLayoutTransition(((LayoutTransition)v6_1));
        }
    }

    private static void a(LayoutTransition arg5) {
        String v0;
        String v5;
        if(!y.e) {
            try {
                y.d = LayoutTransition.class.getDeclaredMethod("cancel");
                y.d.setAccessible(true);
            }
            catch(NoSuchMethodException ) {
                Log.i("ViewGroupUtilsApi14", "Failed to access cancel method by reflection");
            }

            y.e = true;
        }

        if(y.d != null) {
            try {
                y.d.invoke(arg5);
                return;
            }
            catch(InvocationTargetException ) {
                v5 = "ViewGroupUtilsApi14";
                v0 = "Failed to invoke cancel method by reflection";
            }
            catch(IllegalAccessException ) {
                v5 = "ViewGroupUtilsApi14";
                v0 = "Failed to access cancel method by reflection";
            }

            Log.i(v5, v0);
        }
    }
}

