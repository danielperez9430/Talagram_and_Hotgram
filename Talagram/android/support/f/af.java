package android.support.f;

import android.graphics.Matrix;
import android.util.Log;
import android.view.View;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class af extends ae {
    private static Method a;
    private static boolean b;
    private static Method c;
    private static boolean d;

    af() {
        super();
    }

    private void a() {
        if(!af.b) {
            try {
                af.a = View.class.getDeclaredMethod("transformMatrixToGlobal", Matrix.class);
                af.a.setAccessible(true);
            }
            catch(NoSuchMethodException v1) {
                Log.i("ViewUtilsApi21", "Failed to retrieve transformMatrixToGlobal method", ((Throwable)v1));
            }

            af.b = true;
        }
    }

    public void a(View arg4, Matrix arg5) {
        this.a();
        if(af.a != null) {
            try {
                af.a.invoke(arg4, arg5);
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

    private void b() {
        if(!af.d) {
            try {
                af.c = View.class.getDeclaredMethod("transformMatrixToLocal", Matrix.class);
                af.c.setAccessible(true);
            }
            catch(NoSuchMethodException v1) {
                Log.i("ViewUtilsApi21", "Failed to retrieve transformMatrixToLocal method", ((Throwable)v1));
            }

            af.d = true;
        }
    }

    public void b(View arg4, Matrix arg5) {
        this.b();
        if(af.c != null) {
            try {
                af.c.invoke(arg4, arg5);
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

