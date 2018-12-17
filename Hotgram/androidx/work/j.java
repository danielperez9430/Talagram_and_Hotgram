package androidx.work;

import android.util.Log;

public class j {
    private static int a = 4;

    static {
    }

    public static void a(String arg2, String arg3, Throwable[] arg4) {
        if(j.a <= 2) {
            if(arg4 != null && arg4.length >= 1) {
                Log.v(arg2, arg3, arg4[0]);
                return;
            }

            Log.v(arg2, arg3);
        }
    }

    public static void a(int arg0) {
        j.a = arg0;
    }

    public static void b(String arg2, String arg3, Throwable[] arg4) {
        if(j.a <= 3) {
            if(arg4 != null && arg4.length >= 1) {
                Log.d(arg2, arg3, arg4[0]);
                return;
            }

            Log.d(arg2, arg3);
        }
    }

    public static void c(String arg2, String arg3, Throwable[] arg4) {
        if(j.a <= 4) {
            if(arg4 != null && arg4.length >= 1) {
                Log.i(arg2, arg3, arg4[0]);
                return;
            }

            Log.i(arg2, arg3);
        }
    }

    public static void d(String arg2, String arg3, Throwable[] arg4) {
        if(j.a <= 5) {
            if(arg4 != null && arg4.length >= 1) {
                Log.w(arg2, arg3, arg4[0]);
                return;
            }

            Log.w(arg2, arg3);
        }
    }

    public static void e(String arg2, String arg3, Throwable[] arg4) {
        if(j.a <= 6) {
            if(arg4 != null && arg4.length >= 1) {
                Log.e(arg2, arg3, arg4[0]);
                return;
            }

            Log.e(arg2, arg3);
        }
    }
}

