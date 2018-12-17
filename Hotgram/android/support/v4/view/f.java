package android.support.v4.view;

import android.os.Build$VERSION;
import android.util.Log;
import android.view.LayoutInflater$Factory2;
import android.view.LayoutInflater$Factory;
import android.view.LayoutInflater;
import java.lang.reflect.Field;

public final class f {
    private static Field a;
    private static boolean b;

    public static void a(LayoutInflater arg2, LayoutInflater$Factory2 arg3) {
        arg2.setFactory2(arg3);
        if(Build$VERSION.SDK_INT < 21) {
            LayoutInflater$Factory v0 = arg2.getFactory();
            if((v0 instanceof LayoutInflater$Factory2)) {
                f.b(arg2, ((LayoutInflater$Factory2)v0));
            }
            else {
                f.b(arg2, arg3);
            }
        }
    }

    private static void b(LayoutInflater arg5, LayoutInflater$Factory2 arg6) {
        if(!f.b) {
            try {
                f.a = LayoutInflater.class.getDeclaredField("mFactory2");
                f.a.setAccessible(true);
            }
            catch(NoSuchFieldException v1) {
                Log.e("LayoutInflaterCompatHC", "forceSetFactory2 Could not find field \'mFactory2\' on class " + LayoutInflater.class.getName() + "; inflation may have unexpected results.", ((Throwable)v1));
            }

            f.b = true;
        }

        if(f.a != null) {
            try {
                f.a.set(arg5, arg6);
            }
            catch(IllegalAccessException v6) {
                Log.e("LayoutInflaterCompatHC", "forceSetFactory2 could not set the Factory2 on LayoutInflater " + arg5 + "; inflation may have unexpected results.", ((Throwable)v6));
            }
        }
    }
}

