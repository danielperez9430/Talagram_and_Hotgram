package com.d.a.c;

import android.util.Log;
import com.d.a.b.d;

public final class c {
    private static volatile boolean a = false;
    private static volatile boolean b = true;

    static {
    }

    public static void a(Throwable arg3) {
        c.a(6, arg3, null, new Object[0]);
    }

    public static void a(String arg2, Object[] arg3) {
        if(c.a) {
            c.a(3, null, arg2, arg3);
        }
    }

    public static void a(boolean arg0) {
        c.a = arg0;
    }

    @Deprecated public static void a() {
        c.b(false);
    }

    private static void a(int arg2, Throwable arg3, String arg4, Object[] arg5) {
        if(!c.b) {
            return;
        }

        if(arg5.length > 0) {
            arg4 = String.format(arg4, arg5);
        }

        if(arg3 == null) {
        }
        else {
            if(arg4 == null) {
                arg4 = arg3.getMessage();
            }

            arg4 = String.format("%1$s\n%2$s", arg4, Log.getStackTraceString(arg3));
        }

        Log.println(arg2, d.a, arg4);
    }

    public static void b(boolean arg0) {
        c.b = arg0;
    }

    public static void b(String arg2, Object[] arg3) {
        c.a(4, null, arg2, arg3);
    }

    public static void c(String arg2, Object[] arg3) {
        c.a(5, null, arg2, arg3);
    }

    public static void d(String arg2, Object[] arg3) {
        c.a(6, null, arg2, arg3);
    }
}

