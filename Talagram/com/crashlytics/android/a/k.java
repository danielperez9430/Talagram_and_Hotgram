package com.crashlytics.android.a;

import android.content.Context;
import android.os.Bundle;
import java.lang.reflect.Method;

public class k implements q {
    private final Method a;
    private final Object b;

    public k(Object arg1, Method arg2) {
        super();
        this.b = arg1;
        this.a = arg2;
    }

    public static q a(Context arg3) {
        Class v0 = k.b(arg3);
        q v1 = null;
        if(v0 == null) {
            return v1;
        }

        Object v2 = k.a(arg3, v0);
        if(v2 == null) {
            return v1;
        }

        Method v3 = k.b(arg3, v0);
        if(v3 == null) {
            return v1;
        }

        return new k(v2, v3);
    }

    private static Object a(Context arg5, Class arg6) {
        try {
            return arg6.getDeclaredMethod("getInstance", Context.class).invoke(arg6, arg5);
        }
        catch(Exception ) {
            return null;
        }
    }

    public void a(String arg2, Bundle arg3) {
        this.a("fab", arg2, arg3);
    }

    public void a(String arg5, String arg6, Bundle arg7) {
        try {
            this.a.invoke(this.b, arg5, arg6, arg7);
            return;
        }
        catch(Exception ) {
            return;
        }
    }

    private static Class b(Context arg1) {
        try {
            return arg1.getClassLoader().loadClass("com.google.android.gms.measurement.AppMeasurement");
        }
        catch(Exception ) {
            return null;
        }
    }

    private static Method b(Context arg3, Class arg4) {
        try {
            return arg4.getDeclaredMethod("logEventInternal", String.class, String.class, Bundle.class);
        }
        catch(Exception ) {
            return null;
        }
    }
}

