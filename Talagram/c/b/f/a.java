package c.b.f;

import c.b.c.f;
import c.b.c;
import c.b.d.b;
import c.b.d.d;
import c.b.d.e;

public final class a {
    static volatile d a;
    static volatile e b;
    static volatile e c;
    static volatile b d;

    public static Runnable a(Runnable arg1) {
        c.b.e.b.b.a(arg1, "run is null");
        e v0 = a.b;
        if(v0 == null) {
            return arg1;
        }

        return a.a(v0, arg1);
    }

    public static void a(Throwable arg2) {
        NullPointerException v2;
        d v0 = a.a;
        if(arg2 == null) {
            v2 = new NullPointerException("onError called with null. Null values are generally not allowed in 2.x operators and sources.");
        }
        else if(!a.b(arg2)) {
            f v2_1 = new f(arg2);
        }

        if(v0 != null) {
            try {
                v0.a(v2);
                return;
            }
            catch(Throwable v0_1) {
                v0_1.printStackTrace();
                a.c(v0_1);
            }
        }

        ((Throwable)v2).printStackTrace();
        a.c(((Throwable)v2));
    }

    public static c.b.a a(c.b.a arg1) {
        Object v1;
        e v0 = a.c;
        if(v0 != null) {
            v1 = a.a(v0, arg1);
        }

        return ((c.b.a)v1);
    }

    static Object a(e arg0, Object arg1) {
        try {
            return arg0.a(arg1);
        }
        catch(Throwable v0) {
            throw c.b.e.g.a.a(v0);
        }
    }

    public static c a(c.b.a arg1, c arg2) {
        b v0 = a.d;
        if(v0 != null) {
            return a.a(v0, arg1, arg2);
        }

        return arg2;
    }

    static Object a(b arg0, Object arg1, Object arg2) {
        try {
            return arg0.a(arg1, arg2);
        }
        catch(Throwable v0) {
            throw c.b.e.g.a.a(v0);
        }
    }

    static boolean b(Throwable arg2) {
        if((arg2 instanceof c.b.c.d)) {
            return 1;
        }

        if((arg2 instanceof c.b.c.c)) {
            return 1;
        }

        if((arg2 instanceof IllegalStateException)) {
            return 1;
        }

        if((arg2 instanceof NullPointerException)) {
            return 1;
        }

        if((arg2 instanceof IllegalArgumentException)) {
            return 1;
        }

        if((arg2 instanceof c.b.c.a)) {
            return 1;
        }

        return 0;
    }

    static void c(Throwable arg2) {
        Thread v0 = Thread.currentThread();
        v0.getUncaughtExceptionHandler().uncaughtException(v0, arg2);
    }
}

