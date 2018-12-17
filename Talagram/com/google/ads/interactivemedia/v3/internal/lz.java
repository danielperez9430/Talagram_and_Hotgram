package com.google.ads.interactivemedia.v3.internal;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashSet;
import java.util.Set;

public class lz {
    private static final ThreadLocal a;
    private final int b;
    private int c;

    static {
        lz.a = new ThreadLocal();
    }

    public lz() {
        super();
        this.c = 0;
        this.b = 37;
        this.c = 17;
    }

    public lz(int arg6, int arg7) {
        super();
        this.c = 0;
        boolean v2 = true;
        boolean v1 = arg6 % 2 != 0 ? true : false;
        lv.a(v1, "HashCodeBuilder requires an odd initial value", new Object[0]);
        if(arg7 % 2 != 0) {
        }
        else {
            v2 = false;
        }

        lv.a(v2, "HashCodeBuilder requires an odd multiplier", new Object[0]);
        this.b = arg7;
        this.c = arg6;
    }

    public static int a(Object arg6, String[] arg7) {
        return lz.a(17, 37, arg6, false, null, arg7);
    }

    public static int a(int arg3, int arg4, Object arg5, boolean arg6, Class arg7, String[] arg8) {
        boolean v1 = arg5 != null ? true : false;
        lv.a(v1, "The object to build a hash code for must not be null", new Object[0]);
        lz v0 = new lz(arg3, arg4);
        Class v3 = arg5.getClass();
        while(true) {
            lz.a(arg5, v3, v0, arg6, arg8);
            if(v3.getSuperclass() != null && v3 != arg7) {
                v3 = v3.getSuperclass();
                continue;
            }

            break;
        }

        return v0.b();
    }

    private static void a(Object arg5, Class arg6, lz arg7, boolean arg8, String[] arg9) {
        int v1;
        if(lz.a(arg5)) {
            return;
        }

        try {
            lz.c(arg5);
            Field[] v6_1 = arg6.getDeclaredFields();
            AccessibleObject.setAccessible(((AccessibleObject[])v6_1), true);
            int v0 = v6_1.length;
            v1 = 0;
            while(true) {
            label_9:
                if(v1 >= v0) {
                    goto label_37;
                }

                Field v2 = v6_1[v1];
                if(!lt.b(((Object[])arg9), v2.getName()) && !v2.getName().contains("$")) {
                    if(!arg8 && (Modifier.isTransient(v2.getModifiers()))) {
                        break;
                    }

                    if(Modifier.isStatic(v2.getModifiers())) {
                        break;
                    }

                    if(v2.isAnnotationPresent(ma.class)) {
                        break;
                    }

                    try {
                        arg7.b(v2.get(arg5));
                        break;
                    }
                    catch(IllegalAccessException ) {
                        throw new InternalError("Unexpected IllegalAccessException");
                    }
                }

                break;
            }
        }
        catch(Throwable v6) {
            goto label_40;
        }

        ++v1;
        goto label_9;
    label_37:
        lz.d(arg5);
        return;
    label_40:
        lz.d(arg5);
        throw v6;
    }

    static Set a() {
        return lz.a.get();
    }

    static boolean a(Object arg2) {
        Set v0 = lz.a();
        boolean v2 = v0 == null || !v0.contains(new mb(arg2)) ? false : true;
        return v2;
    }

    public lz a(long[] arg5) {
        if(arg5 == null) {
            this.c *= this.b;
        }
        else {
            int v0 = arg5.length;
            int v1;
            for(v1 = 0; v1 < v0; ++v1) {
                this.a(arg5[v1]);
            }
        }

        return this;
    }

    public lz a(int[] arg4) {
        if(arg4 == null) {
            this.c *= this.b;
        }
        else {
            int v0 = arg4.length;
            int v1;
            for(v1 = 0; v1 < v0; ++v1) {
                this.a(arg4[v1]);
            }
        }

        return this;
    }

    public lz a(short[] arg4) {
        if(arg4 == null) {
            this.c *= this.b;
        }
        else {
            int v0 = arg4.length;
            int v1;
            for(v1 = 0; v1 < v0; ++v1) {
                this.a(arg4[v1]);
            }
        }

        return this;
    }

    public lz a(char[] arg4) {
        if(arg4 == null) {
            this.c *= this.b;
        }
        else {
            int v0 = arg4.length;
            int v1;
            for(v1 = 0; v1 < v0; ++v1) {
                this.a(arg4[v1]);
            }
        }

        return this;
    }

    public lz a(byte[] arg4) {
        if(arg4 == null) {
            this.c *= this.b;
        }
        else {
            int v0 = arg4.length;
            int v1;
            for(v1 = 0; v1 < v0; ++v1) {
                this.a(arg4[v1]);
            }
        }

        return this;
    }

    public lz a(double[] arg5) {
        if(arg5 == null) {
            this.c *= this.b;
        }
        else {
            int v0 = arg5.length;
            int v1;
            for(v1 = 0; v1 < v0; ++v1) {
                this.a(arg5[v1]);
            }
        }

        return this;
    }

    public lz a(float[] arg4) {
        if(arg4 == null) {
            this.c *= this.b;
        }
        else {
            int v0 = arg4.length;
            int v1;
            for(v1 = 0; v1 < v0; ++v1) {
                this.a(arg4[v1]);
            }
        }

        return this;
    }

    public lz a(boolean[] arg4) {
        if(arg4 == null) {
            this.c *= this.b;
        }
        else {
            int v0 = arg4.length;
            int v1;
            for(v1 = 0; v1 < v0; ++v1) {
                this.a(arg4[v1]);
            }
        }

        return this;
    }

    public lz a(Object[] arg4) {
        if(arg4 == null) {
            this.c *= this.b;
        }
        else {
            int v0 = arg4.length;
            int v1;
            for(v1 = 0; v1 < v0; ++v1) {
                this.b(arg4[v1]);
            }
        }

        return this;
    }

    public lz a(byte arg3) {
        this.c = this.c * this.b + arg3;
        return this;
    }

    public lz a(char arg3) {
        this.c = this.c * this.b + arg3;
        return this;
    }

    public lz a(double arg1) {
        return this.a(Double.doubleToLongBits(arg1));
    }

    public lz a(long arg4) {
        this.c = this.c * this.b + (((int)(arg4 ^ arg4 >> 32)));
        return this;
    }

    public lz a(float arg3) {
        this.c = this.c * this.b + Float.floatToIntBits(arg3);
        return this;
    }

    public lz a(int arg3) {
        this.c = this.c * this.b + arg3;
        return this;
    }

    public lz a(short arg3) {
        this.c = this.c * this.b + arg3;
        return this;
    }

    public lz a(boolean arg3) {
        this.c = this.c * this.b + ((((int)arg3)) ^ 1);
        return this;
    }

    public int b() {
        return this.c;
    }

    public lz b(Object arg3) {
        if(arg3 == null) {
            this.c *= this.b;
        }
        else if(arg3.getClass().isArray()) {
            this.e(arg3);
        }
        else {
            this.c = this.c * this.b + arg3.hashCode();
        }

        return this;
    }

    private static void c(Object arg2) {
        HashSet v0_1;
        Set v0 = lz.a();
        if(v0 == null) {
            v0_1 = new HashSet();
            lz.a.set(v0_1);
        }

        ((Set)v0_1).add(new mb(arg2));
    }

    private static void d(Object arg2) {
        Set v0 = lz.a();
        if(v0 != null) {
            v0.remove(new mb(arg2));
            if(v0.isEmpty()) {
                lz.a.remove();
            }
        }
    }

    private void e(Object arg2) {
        if((arg2 instanceof long[])) {
            this.a(((long[])arg2));
        }
        else if((arg2 instanceof int[])) {
            this.a(((int[])arg2));
        }
        else if((arg2 instanceof short[])) {
            this.a(((short[])arg2));
        }
        else if((arg2 instanceof char[])) {
            this.a(((char[])arg2));
        }
        else if((arg2 instanceof byte[])) {
            this.a(((byte[])arg2));
        }
        else if((arg2 instanceof double[])) {
            this.a(((double[])arg2));
        }
        else if((arg2 instanceof float[])) {
            this.a(((float[])arg2));
        }
        else if((arg2 instanceof boolean[])) {
            this.a(((boolean[])arg2));
        }
        else {
            this.a(((Object[])arg2));
        }
    }

    public int hashCode() {
        return this.b();
    }
}

