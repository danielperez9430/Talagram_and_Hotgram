package net.hockeyapp.android.e;

import android.util.Log;

public class e {
    private static int a = 6;

    static {
    }

    public static void a(String arg1) {
        e.b(null, arg1);
    }

    public static void a(String arg1, Throwable arg2) {
        e.a(null, arg1, arg2);
    }

    public static void a(String arg2, String arg3) {
        arg2 = e.d(arg2);
        if(e.a <= 2) {
            Log.v(arg2, arg3);
        }
    }

    public static void a(String arg2, String arg3, Throwable arg4) {
        arg2 = e.d(arg2);
        if(e.a <= 3) {
            Log.d(arg2, arg3, arg4);
        }
    }

    public static void b(String arg1, Throwable arg2) {
        e.b(null, arg1, arg2);
    }

    public static void b(String arg1) {
        e.c(null, arg1);
    }

    public static void b(String arg2, String arg3, Throwable arg4) {
        arg2 = e.d(arg2);
        if(e.a <= 6) {
            Log.e(arg2, arg3, arg4);
        }
    }

    public static void b(String arg2, String arg3) {
        arg2 = e.d(arg2);
        if(e.a <= 3) {
            Log.d(arg2, arg3);
        }
    }

    public static void c(String arg1) {
        e.d(null, arg1);
    }

    public static void c(String arg2, String arg3) {
        arg2 = e.d(arg2);
        if(e.a <= 5) {
            Log.w(arg2, arg3);
        }
    }

    public static void d(String arg2, String arg3) {
        arg2 = e.d(arg2);
        if(e.a <= 6) {
            Log.e(arg2, arg3);
        }
    }

    static String d(String arg2) {
        if(arg2 == null || arg2.length() == 0 || arg2.length() > 23) {
            arg2 = "HockeyApp";
        }

        return arg2;
    }
}

