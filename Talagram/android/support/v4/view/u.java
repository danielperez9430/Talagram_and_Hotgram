package android.support.v4.view;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build$VERSION;
import android.util.Log;
import android.util.TypedValue;
import android.view.ViewConfiguration;
import java.lang.reflect.Method;

public final class u {
    private static Method a;

    static {
        if(Build$VERSION.SDK_INT == 25) {
            try {
                u.a = ViewConfiguration.class.getDeclaredMethod("getScaledScrollFactor");
            }
            catch(Exception ) {
                Log.i("ViewConfigCompat", "Could not find method getScaledScrollFactor() on ViewConfiguration");
            }
        }
    }

    public static float a(ViewConfiguration arg2, Context arg3) {
        if(Build$VERSION.SDK_INT >= 26) {
            return arg2.getScaledHorizontalScrollFactor();
        }

        return u.d(arg2, arg3);
    }

    public static int a(ViewConfiguration arg2) {
        if(Build$VERSION.SDK_INT >= 28) {
            return arg2.getScaledHoverSlop();
        }

        return arg2.getScaledTouchSlop() / 2;
    }

    public static float b(ViewConfiguration arg2, Context arg3) {
        if(Build$VERSION.SDK_INT >= 26) {
            return arg2.getScaledVerticalScrollFactor();
        }

        return u.d(arg2, arg3);
    }

    public static boolean c(ViewConfiguration arg2, Context arg3) {
        if(Build$VERSION.SDK_INT >= 28) {
            return arg2.shouldShowMenuShortcutsWhenKeyboardPresent();
        }

        Resources v2 = arg3.getResources();
        int v3 = v2.getIdentifier("config_showMenuShortcutsWhenKeyboardPresent", "bool", "android");
        boolean v2_1 = v3 == 0 || !v2.getBoolean(v3) ? false : true;
        return v2_1;
    }

    private static float d(ViewConfiguration arg3, Context arg4) {
        if(Build$VERSION.SDK_INT >= 25 && u.a != null) {
            try {
                return ((float)u.a.invoke(arg3).intValue());
            }
            catch(Exception ) {
                Log.i("ViewConfigCompat", "Could not find method getScaledScrollFactor() on ViewConfiguration");
            }
        }

        TypedValue v3 = new TypedValue();
        if(arg4.getTheme().resolveAttribute(16842829, v3, true)) {
            return v3.getDimension(arg4.getResources().getDisplayMetrics());
        }

        return 0;
    }
}

