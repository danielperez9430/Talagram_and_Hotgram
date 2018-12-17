package android.support.v4.graphics;

import android.graphics.Typeface;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class g extends f {
    public g() {
        super();
    }

    protected Typeface a(Object arg6) {
        try {
            Object v0 = Array.newInstance(this.a, 1);
            Array.set(v0, 0, arg6);
            return this.g.invoke(null, v0, "sans-serif", Integer.valueOf(-1), Integer.valueOf(-1));
        }
        catch(InvocationTargetException v6) {
            throw new RuntimeException(((Throwable)v6));
        }
    }

    protected Method f(Class arg6) {
        Method v6 = Typeface.class.getDeclaredMethod("createFromFamiliesWithDefault", Array.newInstance(arg6, 1).getClass(), String.class, Integer.TYPE, Integer.TYPE);
        v6.setAccessible(true);
        return v6;
    }
}

