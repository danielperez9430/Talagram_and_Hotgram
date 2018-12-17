package android.support.f;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.View;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class ag extends af {
    private static Method a;
    private static boolean b;

    ag() {
        super();
    }

    @SuppressLint(value={"PrivateApi"}) private void a() {
        if(!ag.b) {
            try {
                ag.a = View.class.getDeclaredMethod("setLeftTopRightBottom", Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE);
                ag.a.setAccessible(true);
            }
            catch(NoSuchMethodException v1) {
                Log.i("ViewUtilsApi22", "Failed to retrieve setLeftTopRightBottom method", ((Throwable)v1));
            }

            ag.b = true;
        }
    }

    public void a(View arg4, int arg5, int arg6, int arg7, int arg8) {
        this.a();
        if(ag.a != null) {
            try {
                ag.a.invoke(arg4, Integer.valueOf(arg5), Integer.valueOf(arg6), Integer.valueOf(arg7), Integer.valueOf(arg8));
                return;
            }
            catch(IllegalAccessException ) {
                return;
            }
            catch(InvocationTargetException v4) {
                throw new RuntimeException(v4.getCause());
            }
        }
    }
}

