package com.google.ads.interactivemedia.v3.internal;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashSet;
import java.util.Set;

public class lx {
    private static final ThreadLocal a;
    private boolean b;
    private boolean c;
    private boolean d;
    private Class e;
    private String[] f;

    static {
        lx.a = new ThreadLocal();
    }

    public lx() {
        super();
        this.b = true;
        this.c = false;
        this.d = false;
        this.e = null;
        this.f = null;
    }

    public static boolean a(Object arg2, Object arg3, String[] arg4) {
        return lx.a(arg2, arg3, false, null, arg4);
    }

    static md a(Object arg1, Object arg2) {
        return md.b(new mb(arg1), new mb(arg2));
    }

    static Set a() {
        return lx.a.get();
    }

    private void a(Object arg5, Object arg6, Class arg7) {
        int v0;
        if(lx.b(arg5, arg6)) {
            return;
        }

        try {
            lx.e(arg5, arg6);
            Field[] v7_1 = arg7.getDeclaredFields();
            AccessibleObject.setAccessible(((AccessibleObject[])v7_1), true);
            v0 = 0;
            while(true) {
            label_8:
                if(v0 < v7_1.length && (this.b)) {
                    Field v1 = v7_1[v0];
                    if(!lt.b(this.f, v1.getName()) && !v1.getName().contains("$")) {
                        if(!this.c && (Modifier.isTransient(v1.getModifiers()))) {
                            break;
                        }

                        if(Modifier.isStatic(v1.getModifiers())) {
                            break;
                        }

                        if(v1.isAnnotationPresent(ly.class)) {
                            break;
                        }

                        try {
                            this.d(v1.get(arg5), v1.get(arg6));
                            break;
                        }
                        catch(IllegalAccessException ) {
                            throw new InternalError("Unexpected IllegalAccessException");
                        }
                    }

                    break;
                }

                goto label_42;
            }
        }
        catch(Throwable v7) {
            goto label_45;
        }

        ++v0;
        goto label_8;
    label_42:
        lx.f(arg5, arg6);
        return;
    label_45:
        lx.f(arg5, arg6);
        throw v7;
    }

    public static boolean a(Object arg1, Object arg2, boolean arg3, Class arg4, boolean arg5, String[] arg6) {
        if(arg1 == arg2) {
            return 1;
        }

        if(arg1 != null) {
            if(arg2 == null) {
            }
            else {
                return new lx().a(arg6).a(arg4).a(arg3).b(arg5).c(arg1, arg2).b();
            }
        }

        return 0;
    }

    public lx a(boolean arg1) {
        this.c = arg1;
        return this;
    }

    public lx a(Class arg1) {
        this.e = arg1;
        return this;
    }

    public lx a(String[] arg1) {
        this.f = arg1;
        return this;
    }

    public static boolean a(Object arg6, Object arg7, boolean arg8, Class arg9, String[] arg10) {
        return lx.a(arg6, arg7, arg8, arg9, false, arg10);
    }

    public lx a(long[] arg6, long[] arg7) {
        if(!this.b) {
            return this;
        }

        if(arg6 == arg7) {
            return this;
        }

        int v0 = 0;
        if(arg6 != null) {
            if(arg7 == null) {
            }
            else {
                if(arg6.length != arg7.length) {
                    this.c(false);
                    return this;
                }

                while(v0 < arg6.length) {
                    if(!this.b) {
                        return this;
                    }

                    this.a(arg6[v0], arg7[v0]);
                    ++v0;
                }

                return this;
            }
        }

        this.c(false);
        return this;
    }

    public lx a(int[] arg4, int[] arg5) {
        if(!this.b) {
            return this;
        }

        if(arg4 == arg5) {
            return this;
        }

        int v0 = 0;
        if(arg4 != null) {
            if(arg5 == null) {
            }
            else {
                if(arg4.length != arg5.length) {
                    this.c(false);
                    return this;
                }

                while(v0 < arg4.length) {
                    if(!this.b) {
                        return this;
                    }

                    this.a(arg4[v0], arg5[v0]);
                    ++v0;
                }

                return this;
            }
        }

        this.c(false);
        return this;
    }

    public lx a(short[] arg4, short[] arg5) {
        if(!this.b) {
            return this;
        }

        if(arg4 == arg5) {
            return this;
        }

        int v0 = 0;
        if(arg4 != null) {
            if(arg5 == null) {
            }
            else {
                if(arg4.length != arg5.length) {
                    this.c(false);
                    return this;
                }

                while(v0 < arg4.length) {
                    if(!this.b) {
                        return this;
                    }

                    this.a(arg4[v0], arg5[v0]);
                    ++v0;
                }

                return this;
            }
        }

        this.c(false);
        return this;
    }

    public lx a(char[] arg4, char[] arg5) {
        if(!this.b) {
            return this;
        }

        if(arg4 == arg5) {
            return this;
        }

        int v0 = 0;
        if(arg4 != null) {
            if(arg5 == null) {
            }
            else {
                if(arg4.length != arg5.length) {
                    this.c(false);
                    return this;
                }

                while(v0 < arg4.length) {
                    if(!this.b) {
                        return this;
                    }

                    this.a(arg4[v0], arg5[v0]);
                    ++v0;
                }

                return this;
            }
        }

        this.c(false);
        return this;
    }

    public lx a(byte[] arg4, byte[] arg5) {
        if(!this.b) {
            return this;
        }

        if(arg4 == arg5) {
            return this;
        }

        int v0 = 0;
        if(arg4 != null) {
            if(arg5 == null) {
            }
            else {
                if(arg4.length != arg5.length) {
                    this.c(false);
                    return this;
                }

                while(v0 < arg4.length) {
                    if(!this.b) {
                        return this;
                    }

                    this.a(arg4[v0], arg5[v0]);
                    ++v0;
                }

                return this;
            }
        }

        this.c(false);
        return this;
    }

    public lx a(double[] arg6, double[] arg7) {
        if(!this.b) {
            return this;
        }

        if(arg6 == arg7) {
            return this;
        }

        int v0 = 0;
        if(arg6 != null) {
            if(arg7 == null) {
            }
            else {
                if(arg6.length != arg7.length) {
                    this.c(false);
                    return this;
                }

                while(v0 < arg6.length) {
                    if(!this.b) {
                        return this;
                    }

                    this.a(arg6[v0], arg7[v0]);
                    ++v0;
                }

                return this;
            }
        }

        this.c(false);
        return this;
    }

    public lx a(float[] arg4, float[] arg5) {
        if(!this.b) {
            return this;
        }

        if(arg4 == arg5) {
            return this;
        }

        int v0 = 0;
        if(arg4 != null) {
            if(arg5 == null) {
            }
            else {
                if(arg4.length != arg5.length) {
                    this.c(false);
                    return this;
                }

                while(v0 < arg4.length) {
                    if(!this.b) {
                        return this;
                    }

                    this.a(arg4[v0], arg5[v0]);
                    ++v0;
                }

                return this;
            }
        }

        this.c(false);
        return this;
    }

    public lx a(boolean[] arg4, boolean[] arg5) {
        if(!this.b) {
            return this;
        }

        if(arg4 == arg5) {
            return this;
        }

        int v0 = 0;
        if(arg4 != null) {
            if(arg5 == null) {
            }
            else {
                if(arg4.length != arg5.length) {
                    this.c(false);
                    return this;
                }

                while(v0 < arg4.length) {
                    if(!this.b) {
                        return this;
                    }

                    this.a(arg4[v0], arg5[v0]);
                    ++v0;
                }

                return this;
            }
        }

        this.c(false);
        return this;
    }

    public lx a(Object[] arg4, Object[] arg5) {
        if(!this.b) {
            return this;
        }

        if(arg4 == arg5) {
            return this;
        }

        int v0 = 0;
        if(arg4 != null) {
            if(arg5 == null) {
            }
            else {
                if(arg4.length != arg5.length) {
                    this.c(false);
                    return this;
                }

                while(v0 < arg4.length) {
                    if(!this.b) {
                        return this;
                    }

                    this.d(arg4[v0], arg5[v0]);
                    ++v0;
                }

                return this;
            }
        }

        this.c(false);
        return this;
    }

    public lx a(byte arg2, byte arg3) {
        if(!this.b) {
            return this;
        }

        boolean v2 = arg2 == arg3 ? true : false;
        this.b = v2;
        return this;
    }

    public lx a(char arg2, char arg3) {
        if(!this.b) {
            return this;
        }

        boolean v2 = arg2 == arg3 ? true : false;
        this.b = v2;
        return this;
    }

    public lx a(double arg2, double arg4) {
        if(!this.b) {
            return this;
        }

        return this.a(Double.doubleToLongBits(arg2), Double.doubleToLongBits(arg4));
    }

    public lx a(long arg2, long arg4) {
        if(!this.b) {
            return this;
        }

        boolean v2 = arg2 == arg4 ? true : false;
        this.b = v2;
        return this;
    }

    public lx a(float arg2, float arg3) {
        if(!this.b) {
            return this;
        }

        return this.a(Float.floatToIntBits(arg2), Float.floatToIntBits(arg3));
    }

    public lx a(int arg2, int arg3) {
        if(!this.b) {
            return this;
        }

        boolean v2 = arg2 == arg3 ? true : false;
        this.b = v2;
        return this;
    }

    public lx a(short arg2, short arg3) {
        if(!this.b) {
            return this;
        }

        boolean v2 = arg2 == arg3 ? true : false;
        this.b = v2;
        return this;
    }

    public lx a(boolean arg2, boolean arg3) {
        if(!this.b) {
            return this;
        }

        arg2 = arg2 == arg3 ? true : false;
        this.b = arg2;
        return this;
    }

    static boolean b(Object arg2, Object arg3) {
        boolean v2_1;
        Set v0 = lx.a();
        md v2 = lx.a(arg2, arg3);
        md v3 = md.b(v2.a(), v2.b());
        if(v0 != null) {
            if(!v0.contains(v2) && !v0.contains(v3)) {
                goto label_12;
            }

            v2_1 = true;
        }
        else {
        label_12:
            v2_1 = false;
        }

        return v2_1;
    }

    public boolean b() {
        return this.b;
    }

    public lx b(boolean arg1) {
        this.d = arg1;
        return this;
    }

    public lx c(Object arg5, Object arg6) {
        if(!this.b) {
            return this;
        }

        if(arg5 == arg6) {
            return this;
        }

        if(arg5 != null) {
            if(arg6 == null) {
            }
            else {
                Class v1 = arg5.getClass();
                Class v2 = arg6.getClass();
                if(v1.isInstance(arg6)) {
                    if(!v2.isInstance(arg5)) {
                        goto label_21;
                    }
                }
                else if(!v2.isInstance(arg5)) {
                    goto label_36;
                }
                else if(!v1.isInstance(arg6)) {
                }
                else {
                label_21:
                    v1 = v2;
                }

                try {
                    if(v1.isArray()) {
                        this.d(arg5, arg6);
                    }
                    else {
                        while(true) {
                            this.a(arg5, arg6, v1);
                            if(v1.getSuperclass() != null && v1 != this.e) {
                                v1 = v1.getSuperclass();
                                continue;
                            }

                            return this;
                        }
                    }
                }
                catch(IllegalArgumentException ) {
                    this.b = false;
                    return this;
                }

                return this;
            label_36:
                this.b = false;
                return this;
            }
        }

        this.b = false;
        return this;
    }

    protected void c(boolean arg1) {
        this.b = arg1;
    }

    public lx d(Object arg3, Object arg4) {
        if(!this.b) {
            return this;
        }

        if(arg3 == arg4) {
            return this;
        }

        if(arg3 != null) {
            if(arg4 == null) {
            }
            else {
                Class v0 = arg3.getClass();
                if(!v0.isArray()) {
                    if((this.d) && !lu.a(v0)) {
                        this.c(arg3, arg4);
                        return this;
                    }

                    this.b = arg3.equals(arg4);
                }
                else {
                    this.g(arg3, arg4);
                }

                return this;
            }
        }

        this.c(false);
        return this;
    }

    private static void e(Object arg2, Object arg3) {
        Set v0 = lx.a();
        if(v0 == null) {
            HashSet v0_1 = new HashSet();
            lx.a.set(v0_1);
        }

        v0.add(lx.a(arg2, arg3));
    }

    private static void f(Object arg1, Object arg2) {
        Set v0 = lx.a();
        if(v0 != null) {
            v0.remove(lx.a(arg1, arg2));
            if(v0.isEmpty()) {
                lx.a.remove();
            }
        }
    }

    private void g(Object arg3, Object arg4) {
        if(arg3.getClass() != arg4.getClass()) {
            this.c(false);
        }
        else if((arg3 instanceof long[])) {
            this.a(((long[])arg3), ((long[])arg4));
        }
        else if((arg3 instanceof int[])) {
            this.a(((int[])arg3), ((int[])arg4));
        }
        else if((arg3 instanceof short[])) {
            this.a(((short[])arg3), ((short[])arg4));
        }
        else if((arg3 instanceof char[])) {
            this.a(((char[])arg3), ((char[])arg4));
        }
        else if((arg3 instanceof byte[])) {
            this.a(((byte[])arg3), ((byte[])arg4));
        }
        else if((arg3 instanceof double[])) {
            this.a(((double[])arg3), ((double[])arg4));
        }
        else if((arg3 instanceof float[])) {
            this.a(((float[])arg3), ((float[])arg4));
        }
        else if((arg3 instanceof boolean[])) {
            this.a(((boolean[])arg3), ((boolean[])arg4));
        }
        else {
            this.a(((Object[])arg3), ((Object[])arg4));
        }
    }
}

