package android.support.v4.widget;

import android.os.Build$VERSION;
import android.support.v4.view.d;
import android.support.v4.view.t;
import android.util.Log;
import android.view.View;
import android.widget.PopupWindow;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public final class o {
    private static Method a;
    private static boolean b;
    private static Field c;
    private static boolean d;

    public static void a(PopupWindow arg6, int arg7) {
        if(Build$VERSION.SDK_INT >= 23) {
            arg6.setWindowLayoutType(arg7);
            return;
        }

        if(!o.b) {
            try {
                o.a = PopupWindow.class.getDeclaredMethod("setWindowLayoutType", Integer.TYPE);
                o.a.setAccessible(true);
                goto label_18;
            }
            catch(Exception ) {
            label_18:
                o.b = true;
            }
        }

        if(o.a != null) {
            try {
                o.a.invoke(arg6, Integer.valueOf(arg7));
                return;
            }
            catch(Exception ) {
                return;
            }
        }
    }

    public static void a(PopupWindow arg2, View arg3, int arg4, int arg5, int arg6) {
        if(Build$VERSION.SDK_INT >= 19) {
            arg2.showAsDropDown(arg3, arg4, arg5, arg6);
        }
        else {
            if((d.a(arg6, t.f(arg3)) & 7) == 5) {
                arg4 -= arg2.getWidth() - arg3.getWidth();
            }

            arg2.showAsDropDown(arg3, arg4, arg5);
        }
    }

    public static void a(PopupWindow arg4, boolean arg5) {
        if(Build$VERSION.SDK_INT >= 23) {
            arg4.setOverlapAnchor(arg5);
            return;
        }

        if(Build$VERSION.SDK_INT >= 21) {
            if(!o.d) {
                try {
                    o.c = PopupWindow.class.getDeclaredField("mOverlapAnchor");
                    o.c.setAccessible(true);
                }
                catch(NoSuchFieldException v1) {
                    Log.i("PopupWindowCompatApi21", "Could not fetch mOverlapAnchor field from PopupWindow", ((Throwable)v1));
                }

                o.d = true;
            }

            if(o.c == null) {
                return;
            }

            try {
                o.c.set(arg4, Boolean.valueOf(arg5));
            }
            catch(IllegalAccessException v4) {
                Log.i("PopupWindowCompatApi21", "Could not set overlap anchor field in PopupWindow", ((Throwable)v4));
            }
        }
    }
}

