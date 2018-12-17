package android.support.v4.view;

import android.graphics.Rect;
import android.os.Build$VERSION;
import android.view.Gravity;

public final class d {
    public static int a(int arg2, int arg3) {
        if(Build$VERSION.SDK_INT >= 17) {
            return Gravity.getAbsoluteGravity(arg2, arg3);
        }

        return arg2 & -8388609;
    }

    public static void a(int arg2, int arg3, int arg4, Rect arg5, Rect arg6, int arg7) {
        if(Build$VERSION.SDK_INT >= 17) {
            Gravity.apply(arg2, arg3, arg4, arg5, arg6, arg7);
        }
        else {
            Gravity.apply(arg2, arg3, arg4, arg5, arg6);
        }
    }
}

