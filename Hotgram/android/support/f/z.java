package android.support.f;

import android.util.Log;
import android.view.ViewGroup;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class z {
    private static Method a;
    private static boolean b;

    static void a(ViewGroup arg3, boolean arg4) {
        String v0;
        String v4;
        z.a();
        if(z.a != null) {
            try {
                z.a.invoke(arg3, Boolean.valueOf(arg4));
                return;
            }
            catch(InvocationTargetException v3) {
                v4 = "ViewUtilsApi18";
                v0 = "Error invoking suppressLayout method";
            }
            catch(IllegalAccessException v3_1) {
                v4 = "ViewUtilsApi18";
                v0 = "Failed to invoke suppressLayout method";
            }

            Log.i(v4, v0, ((Throwable)v3));
        }
    }

    private static void a() {
        if(!z.b) {
            try {
                z.a = ViewGroup.class.getDeclaredMethod("suppressLayout", Boolean.TYPE);
                z.a.setAccessible(true);
            }
            catch(NoSuchMethodException v1) {
                Log.i("ViewUtilsApi18", "Failed to retrieve suppressLayout method", ((Throwable)v1));
            }

            z.b = true;
        }
    }
}

