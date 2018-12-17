package android.support.v7.widget;

import android.graphics.Rect;
import android.os.Build$VERSION;
import android.support.v4.view.t;
import android.util.Log;
import android.view.View;
import java.lang.reflect.Method;

public class bs {
    private static Method a;

    static {
        if(Build$VERSION.SDK_INT >= 18) {
            try {
                bs.a = View.class.getDeclaredMethod("computeFitSystemWindows", Rect.class, Rect.class);
                if(bs.a.isAccessible()) {
                    return;
                }

                bs.a.setAccessible(true);
            }
            catch(NoSuchMethodException ) {
                Log.d("ViewUtils", "Could not find method computeFitSystemWindows. Oh well.");
            }
        }
    }

    public static boolean a(View arg1) {
        boolean v0 = true;
        if(t.f(arg1) == 1) {
        }
        else {
            v0 = false;
        }

        return v0;
    }

    public static void a(View arg3, Rect arg4, Rect arg5) {
        if(bs.a != null) {
            try {
                bs.a.invoke(arg3, arg4, arg5);
            }
            catch(Exception v3) {
                Log.d("ViewUtils", "Could not invoke computeFitSystemWindows", ((Throwable)v3));
            }
        }
    }

    public static void b(View arg4) {
        if(Build$VERSION.SDK_INT >= 16) {
            try {
                Method v0 = arg4.getClass().getMethod("makeOptionalFitsSystemWindows");
                if(!v0.isAccessible()) {
                    v0.setAccessible(true);
                }

                v0.invoke(arg4);
            }
            catch(IllegalAccessException v4) {
                Log.d("ViewUtils", "Could not invoke makeOptionalFitsSystemWindows", ((Throwable)v4));
            }
            catch(NoSuchMethodException ) {
                Log.d("ViewUtils", "Could not find method makeOptionalFitsSystemWindows. Oh well...");
            }
        }
    }
}

