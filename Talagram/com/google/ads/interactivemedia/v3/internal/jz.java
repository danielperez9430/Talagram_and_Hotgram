package com.google.ads.interactivemedia.v3.internal;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import dalvik.system.DexClassLoader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.ArrayList;

public abstract class jz extends jy {
    class a extends Exception {
        public a() {
            super();
        }

        public a(Throwable arg1) {
            super(arg1);
        }
    }

    static boolean d = false;
    private static Method e;
    private static Method f;
    private static Method g;
    private static Method h;
    private static Method i;
    private static Method j;
    private static Method k;
    private static Method l;
    private static Method m;
    private static Method n;
    private static Method o;
    private static Method p;
    private static Method q;
    private static String r;
    private static String s;
    private static String t;
    private static long u;
    private static kf v;

    static {
    }

    protected jz(Context arg1, kd arg2, ke arg3) {
        super(arg1, arg2, arg3);
    }

    static String a() {
        if(jz.r != null) {
            return jz.r;
        }

        throw new a();
    }

    static String a(Context arg5, kd arg6) {
        if(jz.s != null) {
            return jz.s;
        }

        if(jz.h != null) {
            try {
                Object v5_2 = jz.h.invoke(null, arg5);
                if(v5_2 != null) {
                    jz.s = arg6.a(((ByteBuffer)v5_2).array(), true);
                    return jz.s;
                }

                throw new a();
            }
            catch(InvocationTargetException v5) {
                throw new a(((Throwable)v5));
            }
            catch(IllegalAccessException v5_1) {
                throw new a(((Throwable)v5_1));
            }
        }

        throw new a();
    }

    static ArrayList a(MotionEvent arg4, DisplayMetrics arg5) {
        if(jz.i != null && arg4 != null) {
            try {
                return jz.i.invoke(null, arg4, arg5);
            }
            catch(InvocationTargetException v4) {
                throw new a(((Throwable)v4));
            }
            catch(IllegalAccessException v4_1) {
                throw new a(((Throwable)v4_1));
            }
        }

        throw new a();
    }

    protected static void a(String arg3, Context arg4, kd arg5) {
        Class v0 = jz.class;
        __monitor_enter(v0);
        try {
            if(!jz.d) {
                try {
                    jz.v = new kf(arg5, null);
                    jz.r = arg3;
                    jz.k(arg4);
                    jz.u = jz.b().longValue();
                    jz.d = true;
                }
                catch(UnsupportedOperationException ) {
                }
            }
        }
        catch(Throwable v3) {
            __monitor_exit(v0);
            throw v3;
        }

        __monitor_exit(v0);
    }

    static Long b() {
        if(jz.e != null) {
            try {
                return jz.e.invoke(null);
            }
            catch(InvocationTargetException v0) {
                throw new a(((Throwable)v0));
            }
            catch(IllegalAccessException v0_1) {
                throw new a(((Throwable)v0_1));
            }
        }

        throw new a();
    }

    static String b(Context arg5, kd arg6) {
        if(jz.t != null) {
            return jz.t;
        }

        if(jz.k != null) {
            try {
                Object v5_2 = jz.k.invoke(null, arg5);
                if(v5_2 != null) {
                    jz.t = arg6.a(((ByteBuffer)v5_2).array(), true);
                    return jz.t;
                }

                throw new a();
            }
            catch(InvocationTargetException v5) {
                throw new a(((Throwable)v5));
            }
            catch(IllegalAccessException v5_1) {
                throw new a(((Throwable)v5_1));
            }
        }

        throw new a();
    }

    private static String b(byte[] arg2, String arg3) {
        try {
            return new String(jz.v.a(arg2, arg3), "UTF-8");
        }
        catch(UnsupportedEncodingException v2) {
            throw new a(((Throwable)v2));
        }
        catch(com.google.ads.interactivemedia.v3.internal.kf$a v2_1) {
            throw new a(((Throwable)v2_1));
        }
    }

    protected void b(Context arg9) {
        try {
            this.a(1, jz.c());
            goto label_3;
        }
        catch(IOException ) {
        }
        catch(a ) {
        label_3:
            int v1 = 2;
            try {
                this.a(v1, jz.a());
                goto label_6;
            }
            catch(a ) {
                try {
                label_6:
                    long v1_1 = jz.b().longValue();
                    this.a(25, v1_1);
                    if(jz.u != 0) {
                        this.a(17, v1_1 - jz.u);
                        this.a(23, jz.u);
                    }

                    goto label_21;
                }
                catch(a ) {
                label_21:
                    try {
                        ArrayList v2 = jz.g(arg9);
                        this.a(31, v2.get(0).longValue());
                        this.a(32, v2.get(1).longValue());
                        goto label_31;
                    }
                    catch(IOException ) {
                    }
                    catch(a ) {
                    label_31:
                        int v2_1 = 33;
                        try {
                            this.a(v2_1, jz.d().longValue());
                            goto label_35;
                        }
                        catch(IOException ) {
                        }
                        catch(a ) {
                        label_35:
                            v2_1 = 27;
                            try {
                                this.a(v2_1, jz.a(arg9, this.c));
                                goto label_39;
                            }
                            catch(IOException ) {
                            }
                            catch(a ) {
                            label_39:
                                v2_1 = 29;
                                try {
                                    this.a(v2_1, jz.b(arg9, this.c));
                                    goto label_43;
                                }
                                catch(a ) {
                                    try {
                                    label_43:
                                        int[] v2_2 = jz.h(arg9);
                                        this.a(5, ((long)v2_2[0]));
                                        this.a(6, ((long)v2_2[1]));
                                        goto label_52;
                                    }
                                    catch(a ) {
                                        try {
                                        label_52:
                                            this.a(12, ((long)jz.i(arg9)));
                                            goto label_56;
                                        }
                                        catch(a ) {
                                            try {
                                            label_56:
                                                this.a(3, ((long)jz.j(arg9)));
                                                goto label_60;
                                            }
                                            catch(a ) {
                                            label_60:
                                                int v0 = 34;
                                                try {
                                                    this.a(v0, jz.e(arg9));
                                                    goto label_63;
                                                }
                                                catch(IOException ) {
                                                }
                                                catch(a ) {
                                                label_63:
                                                    v0 = 35;
                                                    try {
                                                        this.a(v0, jz.f(arg9).longValue());
                                                        return;
                                                    }
                                                    catch(IOException ) {
                                                        return;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    static String c() {
        if(jz.g != null) {
            try {
                return jz.g.invoke(null);
            }
            catch(InvocationTargetException v0) {
                throw new a(((Throwable)v0));
            }
            catch(IllegalAccessException v0_1) {
                throw new a(((Throwable)v0_1));
            }
        }

        throw new a();
    }

    protected void c(Context arg7) {
        int v0 = 2;
        try {
            this.a(v0, jz.a());
            goto label_3;
        }
        catch(IOException ) {
        }
        catch(a ) {
        label_3:
            try {
                this.a(1, jz.c());
                goto label_6;
            }
            catch(IOException ) {
            }
            catch(a ) {
            label_6:
                int v2 = 25;
                try {
                    this.a(v2, jz.b().longValue());
                    goto label_10;
                }
                catch(a ) {
                    try {
                    label_10:
                        ArrayList v2_1 = jz.a(this.a, this.b);
                        this.a(14, v2_1.get(0).longValue());
                        this.a(15, v2_1.get(1).longValue());
                        if(v2_1.size() >= 3) {
                            this.a(16, v2_1.get(v0).longValue());
                        }

                        goto label_29;
                    }
                    catch(a ) {
                    label_29:
                        v0 = 34;
                        try {
                            this.a(v0, jz.e(arg7));
                            goto label_32;
                        }
                        catch(IOException ) {
                        }
                        catch(a ) {
                        label_32:
                            v0 = 35;
                            try {
                                this.a(v0, jz.f(arg7).longValue());
                                return;
                            }
                            catch(IOException ) {
                                return;
                            }
                        }
                    }
                }
            }
        }
    }

    static Long d() {
        if(jz.f != null) {
            try {
                return jz.f.invoke(null);
            }
            catch(InvocationTargetException v0) {
                throw new a(((Throwable)v0));
            }
            catch(IllegalAccessException v0_1) {
                throw new a(((Throwable)v0_1));
            }
        }

        throw new a();
    }

    static String d(Context arg4) {
        if(jz.j != null) {
            try {
                Object v4_2 = jz.j.invoke(null, arg4);
                if(v4_2 != null) {
                    return ((String)v4_2);
                }

                throw new a();
            }
            catch(InvocationTargetException v4) {
                throw new a(((Throwable)v4));
            }
            catch(IllegalAccessException v4_1) {
                throw new a(((Throwable)v4_1));
            }
        }

        throw new a();
    }

    static String e(Context arg4) {
        if(jz.n != null) {
            try {
                return jz.n.invoke(null, arg4);
            }
            catch(InvocationTargetException v4) {
                throw new a(((Throwable)v4));
            }
            catch(IllegalAccessException v4_1) {
                throw new a(((Throwable)v4_1));
            }
        }

        throw new a();
    }

    static Long f(Context arg4) {
        if(jz.o != null) {
            try {
                return jz.o.invoke(null, arg4);
            }
            catch(InvocationTargetException v4) {
                throw new a(((Throwable)v4));
            }
            catch(IllegalAccessException v4_1) {
                throw new a(((Throwable)v4_1));
            }
        }

        throw new a();
    }

    static ArrayList g(Context arg4) {
        if(jz.l != null) {
            try {
                Object v4_2 = jz.l.invoke(null, arg4);
                if(v4_2 != null && ((ArrayList)v4_2).size() == 2) {
                    return ((ArrayList)v4_2);
                }

                throw new a();
            }
            catch(InvocationTargetException v4) {
                throw new a(((Throwable)v4));
            }
            catch(IllegalAccessException v4_1) {
                throw new a(((Throwable)v4_1));
            }
        }

        throw new a();
    }

    static int[] h(Context arg4) {
        if(jz.m != null) {
            try {
                return jz.m.invoke(null, arg4);
            }
            catch(InvocationTargetException v4) {
                throw new a(((Throwable)v4));
            }
            catch(IllegalAccessException v4_1) {
                throw new a(((Throwable)v4_1));
            }
        }

        throw new a();
    }

    static int i(Context arg4) {
        if(jz.p != null) {
            try {
                return jz.p.invoke(null, arg4).intValue();
            }
            catch(InvocationTargetException v4) {
                throw new a(((Throwable)v4));
            }
            catch(IllegalAccessException v4_1) {
                throw new a(((Throwable)v4_1));
            }
        }

        throw new a();
    }

    static int j(Context arg4) {
        if(jz.q != null) {
            try {
                return jz.q.invoke(null, arg4).intValue();
            }
            catch(InvocationTargetException v4) {
                throw new a(((Throwable)v4));
            }
            catch(IllegalAccessException v4_1) {
                throw new a(((Throwable)v4_1));
            }
        }

        throw new a();
    }

    private static void k(Context arg19) {
        Class v18;
        String v2_1;
        File v17;
        File v16;
        Class v3;
        Class v15;
        Class v14;
        Class v13;
        Class v12;
        Class v11;
        Class v10;
        Class v9;
        Class v8;
        Class v7;
        Class v6_1;
        Class v4;
        DexClassLoader v1_1;
        File v5;
        File v2;
        byte[] v0_6;
        try {
            v0_6 = jz.v.a(kh.a());
            byte[] v1 = jz.v.a(v0_6, kh.b());
            v2 = arg19.getCacheDir();
            if(v2 == null) {
                v2 = arg19.getDir("dex", 0);
                if(v2 != null) {
                }
                else {
                    throw new a();
                }
            }
            else {
            }

            v5 = File.createTempFile("ads", ".jar", v2);
            FileOutputStream v6 = new FileOutputStream(v5);
            v6.write(v1, 0, v1.length);
            v6.close();
        }
        catch(com.google.ads.interactivemedia.v3.internal.kf$a v0) {
            goto label_213;
        }
        catch(ClassNotFoundException v0_1) {
            goto label_217;
        }
        catch(IOException v0_2) {
            goto label_221;
        }
        catch(NullPointerException v0_3) {
            goto label_205;
        }
        catch(FileNotFoundException v0_4) {
            goto label_225;
        }
        catch(NoSuchMethodException v0_5) {
            goto label_209;
        }

        try {
            v1_1 = new DexClassLoader(v5.getAbsolutePath(), v2.getAbsolutePath(), null, arg19.getClassLoader());
            v4 = v1_1.loadClass(jz.b(v0_6, kh.k()));
            v6_1 = v1_1.loadClass(jz.b(v0_6, kh.y()));
            v7 = v1_1.loadClass(jz.b(v0_6, kh.s()));
            v8 = v1_1.loadClass(jz.b(v0_6, kh.o()));
            v9 = v1_1.loadClass(jz.b(v0_6, kh.A()));
            v10 = v1_1.loadClass(jz.b(v0_6, kh.m()));
            v11 = v1_1.loadClass(jz.b(v0_6, kh.w()));
            v12 = v1_1.loadClass(jz.b(v0_6, kh.u()));
            v13 = v1_1.loadClass(jz.b(v0_6, kh.i()));
            v14 = v1_1.loadClass(jz.b(v0_6, kh.g()));
            v15 = v1_1.loadClass(jz.b(v0_6, kh.e()));
            v3 = v1_1.loadClass(jz.b(v0_6, kh.q()));
            v16 = v2;
        }
        catch(Throwable v0_7) {
            v17 = v5;
            goto label_193;
        }

        try {
            Class v1_2 = v1_1.loadClass(jz.b(v0_6, kh.c()));
            v2_1 = jz.b(v0_6, kh.l());
            v18 = v1_2;
            v17 = v5;
            v5 = null;
        }
        catch(Throwable v0_7) {
            v17 = v5;
            goto label_189;
        }

        try {
            jz.e = v4.getMethod(v2_1);
            jz.f = v6_1.getMethod(jz.b(v0_6, kh.z()));
            jz.g = v7.getMethod(jz.b(v0_6, kh.t()));
            jz.h = v8.getMethod(jz.b(v0_6, kh.p()), Context.class);
            jz.i = v9.getMethod(jz.b(v0_6, kh.B()), MotionEvent.class, DisplayMetrics.class);
            jz.j = v10.getMethod(jz.b(v0_6, kh.n()), Context.class);
            jz.k = v11.getMethod(jz.b(v0_6, kh.x()), Context.class);
            jz.l = v12.getMethod(jz.b(v0_6, kh.v()), Context.class);
            jz.m = v13.getMethod(jz.b(v0_6, kh.j()), Context.class);
            jz.n = v14.getMethod(jz.b(v0_6, kh.h()), Context.class);
            jz.o = v15.getMethod(jz.b(v0_6, kh.f()), Context.class);
            jz.p = v3.getMethod(jz.b(v0_6, kh.r()), Context.class);
            jz.q = v18.getMethod(jz.b(v0_6, kh.d()), Context.class);
            goto label_175;
        }
        catch(Throwable v0_7) {
            try {
            label_189:
                v2 = v16;
            label_193:
                String v1_3 = v17.getName();
                v17.delete();
                new File(v2, v1_3.replace(".jar", ".dex")).delete();
                throw v0_7;
            label_175:
                String v0_8 = v17.getName();
                v17.delete();
                new File(v16, v0_8.replace(".jar", ".dex")).delete();
                return;
            }
            catch(NullPointerException v0_3) {
            label_205:
                throw new a(((Throwable)v0_3));
            }
            catch(NoSuchMethodException v0_5) {
            label_209:
                throw new a(((Throwable)v0_5));
            }
            catch(com.google.ads.interactivemedia.v3.internal.kf$a v0) {
            label_213:
                throw new a(((Throwable)v0));
            }
            catch(ClassNotFoundException v0_1) {
            label_217:
                throw new a(((Throwable)v0_1));
            }
            catch(IOException v0_2) {
            label_221:
                throw new a(((Throwable)v0_2));
            }
            catch(FileNotFoundException v0_4) {
            label_225:
                throw new a(((Throwable)v0_4));
            }
        }
    }
}

