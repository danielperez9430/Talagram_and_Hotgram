package android.support.v4.graphics;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.CancellationSignal;
import android.support.v4.content.a.c$c;
import android.support.v4.d.b$b;
import android.support.v4.f.m;
import android.util.Log;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.List;

class e extends h {
    private static final Class a;
    private static final Constructor b;
    private static final Method c;
    private static final Method d;

    static {
        Constructor v4_1;
        Constructor v2_1;
        Constructor v0;
        Class v1_1;
        try {
            v1_1 = Class.forName("android.graphics.FontFamily");
            Constructor v3 = v1_1.getConstructor();
            Method v4 = v1_1.getMethod("addFontWeightStyle", ByteBuffer.class, Integer.TYPE, List.class, Integer.TYPE, Boolean.TYPE);
            Method v2 = Typeface.class.getMethod("createFromFamiliesWithDefault", Array.newInstance(v1_1, 1).getClass());
            v0 = v3;
        }
        catch(NoSuchMethodException v1) {
            Log.e("TypefaceCompatApi24Impl", v1.getClass().getName(), ((Throwable)v1));
            Constructor v1_2 = v0;
            v2_1 = v1_2;
            v4_1 = v2_1;
        }

        e.b = v0;
        e.a = v1_1;
        e.c = ((Method)v4_1);
        e.d = ((Method)v2_1);
    }

    e() {
        super();
    }

    public static boolean a() {
        if(e.c == null) {
            Log.w("TypefaceCompatApi24Impl", "Unable to collect necessary private methods.Fallback to legacy implementation.");
        }

        boolean v0 = e.c != null ? true : false;
        return v0;
    }

    private static Typeface a(Object arg4) {
        try {
            Object v0 = Array.newInstance(e.a, 1);
            Array.set(v0, 0, arg4);
            return e.d.invoke(null, v0);
        }
        catch(InvocationTargetException v4) {
            throw new RuntimeException(((Throwable)v4));
        }
    }

    private static boolean a(Object arg3, ByteBuffer arg4, int arg5, int arg6, boolean arg7) {
        try {
            return e.c.invoke(arg3, arg4, Integer.valueOf(arg5), null, Integer.valueOf(arg6), Boolean.valueOf(arg7)).booleanValue();
        }
        catch(InvocationTargetException v3) {
            throw new RuntimeException(((Throwable)v3));
        }
    }

    public Typeface a(Context arg9, CancellationSignal arg10, b[] arg11, int arg12) {
        Object v0 = e.b();
        m v1 = new m();
        int v2 = arg11.length;
        int v3;
        for(v3 = 0; v3 < v2; ++v3) {
            b v4 = arg11[v3];
            Uri v5 = v4.a();
            Object v6 = v1.get(v5);
            if(v6 == null) {
                ByteBuffer v6_1 = i.a(arg9, arg10, v5);
                v1.put(v5, v6_1);
            }

            if(!e.a(v0, ((ByteBuffer)v6), v4.b(), v4.c(), v4.d())) {
                return null;
            }
        }

        return Typeface.create(e.a(v0), arg12);
    }

    public Typeface a(Context arg8, android.support.v4.content.a.c$b arg9, Resources arg10, int arg11) {
        Object v11 = e.b();
        c[] v9 = arg9.a();
        int v0 = v9.length;
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            c v2 = v9[v1];
            ByteBuffer v3 = i.a(arg8, arg10, v2.f());
            Typeface v4 = null;
            if(v3 == null) {
                return v4;
            }

            if(!e.a(v11, v3, v2.e(), v2.b(), v2.c())) {
                return v4;
            }
        }

        return e.a(v11);
    }

    private static Object b() {
        try {
            return e.b.newInstance();
        }
        catch(InvocationTargetException v0) {
            throw new RuntimeException(((Throwable)v0));
        }
    }
}

