package c.b.a.a;

import c.b.c.b;
import c.b.d.e;
import c.b.d;
import java.util.concurrent.Callable;

public final class a {
    private static volatile e a;
    private static volatile e b;

    static d a(e arg0, Callable arg1) {
        Object v0 = a.a(arg0, arg1);
        if(v0 != null) {
            return ((d)v0);
        }

        throw new NullPointerException("Scheduler Callable returned null");
    }

    static Object a(e arg0, Object arg1) {
        try {
            return arg0.a(arg1);
        }
        catch(Throwable v0) {
            throw b.a(v0);
        }
    }

    public static d a(d arg1) {
        if(arg1 != null) {
            e v0 = a.b;
            if(v0 == null) {
                return arg1;
            }

            return a.a(v0, arg1);
        }

        throw new NullPointerException("scheduler == null");
    }

    public static d a(Callable arg1) {
        if(arg1 != null) {
            e v0 = a.a;
            if(v0 == null) {
                return a.b(arg1);
            }

            return a.a(v0, arg1);
        }

        throw new NullPointerException("scheduler == null");
    }

    static d b(Callable arg1) {
        try {
            Object v1_1 = arg1.call();
            if(v1_1 != null) {
                return ((d)v1_1);
            }

            throw new NullPointerException("Scheduler Callable returned null");
        }
        catch(Throwable v1) {
            throw b.a(v1);
        }
    }
}

