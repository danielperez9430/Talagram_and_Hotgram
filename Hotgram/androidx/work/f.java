package androidx.work;

import android.content.Context;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class f implements t {
    public f() {
        super();
    }

    public s a(Context arg10, String arg11, u arg12) {
        Object v5_2;
        Class v2;
        s v0 = null;
        try {
            v2 = Class.forName(arg11).asSubclass(s.class);
            int v3 = 2;
        }
        catch(ClassNotFoundException ) {
            j.e("DefaultWorkerFactory", "Class not found: " + arg11, new Throwable[0]);
            return v0;
        }

        try {
            Constructor v5_1 = v2.getDeclaredConstructor(Context.class, u.class);
            v5_2 = v5_1.newInstance(arg10, arg12);
        }
        catch(Exception v10) {
        }
        catch(NoSuchMethodException ) {
            try {
                v5_2 = v2.getDeclaredConstructor().newInstance();
                v2 = NonBlockingWorker.class;
                Method v2_1 = v2.getDeclaredMethod("internalInit", Context.class, u.class);
                v2_1.setAccessible(true);
                v2_1.invoke(v5_2, arg10, arg12);
            }
            catch(Exception v10) {
                j.e("DefaultWorkerFactory", "Could not instantiate " + arg11, new Throwable[]{v10});
                return v0;
            }
        }

        return ((s)v5_2);
    }
}

