package android.support.f;

import android.util.Log;
import android.view.View;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class ae extends ah {
    private static Method a;
    private static boolean b;
    private static Method c;
    private static boolean d;

    ae() {
        super();
    }

    private void a() {
        if(!ae.b) {
            try {
                ae.a = View.class.getDeclaredMethod("setTransitionAlpha", Float.TYPE);
                ae.a.setAccessible(true);
            }
            catch(NoSuchMethodException v1) {
                Log.i("ViewUtilsApi19", "Failed to retrieve setTransitionAlpha method", ((Throwable)v1));
            }

            ae.b = true;
        }
    }

    public float a(View arg3) {
        this.b();
        if(ae.c == null) {
            goto label_14;
        }

        try {
            return ae.c.invoke(arg3).floatValue();
        }
        catch(IllegalAccessException ) {
        label_14:
            return super.a(arg3);
        }
        catch(InvocationTargetException v3) {
            throw new RuntimeException(v3.getCause());
        }
    }

    public void a(View arg4, float arg5) {
        this.a();
        if(ae.a != null) {
            try {
                ae.a.invoke(arg4, Float.valueOf(arg5));
                return;
            }
            catch(IllegalAccessException ) {
                return;
            }
            catch(InvocationTargetException v4) {
                throw new RuntimeException(v4.getCause());
            label_16:
                arg4.setAlpha(arg5);
                return;
            }
        }
        else {
            goto label_16;
        }
    }

    private void b() {
        if(!ae.d) {
            try {
                ae.c = View.class.getDeclaredMethod("getTransitionAlpha");
                ae.c.setAccessible(true);
            }
            catch(NoSuchMethodException v1) {
                Log.i("ViewUtilsApi19", "Failed to retrieve getTransitionAlpha method", ((Throwable)v1));
            }

            ae.d = true;
        }
    }

    public void b(View arg1) {
    }

    public void c(View arg1) {
    }
}

