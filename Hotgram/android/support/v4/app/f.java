package android.support.v4.app;

import android.os.Build$VERSION;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import java.lang.reflect.Method;

public final class f {
    class a {
        private static Method a;
        private static boolean b;
        private static Method c;
        private static boolean d;

        public static IBinder a(Bundle arg6, String arg7) {
            if(!a.b) {
                try {
                    a.a = Bundle.class.getMethod("getIBinder", String.class);
                    a.a.setAccessible(true);
                }
                catch(NoSuchMethodException v0) {
                    Log.i("BundleCompatBaseImpl", "Failed to retrieve getIBinder method", ((Throwable)v0));
                }

                a.b = true;
            }

            Method v3 = null;
            if(a.a != null) {
                try {
                    return a.a.invoke(arg6, arg7);
                }
                catch(IllegalArgumentException v6) {
                    Log.i("BundleCompatBaseImpl", "Failed to invoke getIBinder via reflection", ((Throwable)v6));
                    a.a = v3;
                }
            }

            return ((IBinder)v3);
        }

        public static void a(Bundle arg7, String arg8, IBinder arg9) {
            int v2 = 2;
            if(!a.d) {
                try {
                    Class v0_1 = Bundle.class;
                    a.c = v0_1.getMethod("putIBinder", String.class, IBinder.class);
                    a.c.setAccessible(true);
                }
                catch(NoSuchMethodException v0) {
                    Log.i("BundleCompatBaseImpl", "Failed to retrieve putIBinder method", ((Throwable)v0));
                }

                a.d = true;
            }

            if(a.c != null) {
                try {
                    Method v0_2 = a.c;
                    v0_2.invoke(arg7, arg8, arg9);
                }
                catch(IllegalArgumentException v7) {
                    Log.i("BundleCompatBaseImpl", "Failed to invoke putIBinder via reflection", ((Throwable)v7));
                    a.c = null;
                }
            }
        }
    }

    public static IBinder a(Bundle arg2, String arg3) {
        if(Build$VERSION.SDK_INT >= 18) {
            return arg2.getBinder(arg3);
        }

        return a.a(arg2, arg3);
    }

    public static void a(Bundle arg2, String arg3, IBinder arg4) {
        if(Build$VERSION.SDK_INT >= 18) {
            arg2.putBinder(arg3, arg4);
        }
        else {
            a.a(arg2, arg3, arg4);
        }
    }
}

