package com.c.a;

import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class j implements Cloneable {
    class com.c.a.j$1 {
    }

    class a extends j {
        c h;
        float i;
        private com.c.b.a j;

        public a(com.c.b.c arg2, float[] arg3) {
            super(arg2, null);
            this.a(arg3);
            if((arg2 instanceof com.c.b.a)) {
                this.j = this.b;
            }
        }

        public a(String arg2, float[] arg3) {
            super(arg2, null);
            this.a(arg3);
        }

        public void a(float[] arg1) {
            super.a(arg1);
            this.h = this.e;
        }

        public j a() {
            return this.e();
        }

        void a(float arg2) {
            this.i = this.h.b(arg2);
        }

        void a(Class arg2) {
            if(this.b != null) {
                return;
            }

            super.a(arg2);
        }

        void b(Object arg4) {
            String v4_2;
            String v0;
            if(this.j != null) {
                this.j.a(arg4, this.i);
                return;
            }

            if(this.b != null) {
                this.b.a(arg4, Float.valueOf(this.i));
                return;
            }

            if(this.c != null) {
                try {
                    this.g[0] = Float.valueOf(this.i);
                    this.c.invoke(arg4, this.g);
                    return;
                }
                catch(IllegalAccessException v4) {
                    v0 = "PropertyValuesHolder";
                    v4_2 = v4.toString();
                }
                catch(InvocationTargetException v4_1) {
                    v0 = "PropertyValuesHolder";
                    v4_2 = v4_1.toString();
                }

                Log.e(v0, v4_2);
            }
        }

        public Object clone() {
            return this.e();
        }

        Object d() {
            return Float.valueOf(this.i);
        }

        public a e() {
            j v0 = super.a();
            ((a)v0).h = ((a)v0).e;
            return ((a)v0);
        }
    }

    class b extends j {
        e h;
        int i;
        private com.c.b.b j;

        public b(com.c.b.c arg2, int[] arg3) {
            super(arg2, null);
            this.a(arg3);
            if((arg2 instanceof com.c.b.b)) {
                this.j = this.b;
            }
        }

        public b(String arg2, int[] arg3) {
            super(arg2, null);
            this.a(arg3);
        }

        public void a(int[] arg1) {
            super.a(arg1);
            this.h = this.e;
        }

        public j a() {
            return this.e();
        }

        void a(float arg2) {
            this.i = this.h.b(arg2);
        }

        void a(Class arg2) {
            if(this.b != null) {
                return;
            }

            super.a(arg2);
        }

        void b(Object arg4) {
            String v4_2;
            String v0;
            if(this.j != null) {
                this.j.a(arg4, this.i);
                return;
            }

            if(this.b != null) {
                this.b.a(arg4, Integer.valueOf(this.i));
                return;
            }

            if(this.c != null) {
                try {
                    this.g[0] = Integer.valueOf(this.i);
                    this.c.invoke(arg4, this.g);
                    return;
                }
                catch(IllegalAccessException v4) {
                    v0 = "PropertyValuesHolder";
                    v4_2 = v4.toString();
                }
                catch(InvocationTargetException v4_1) {
                    v0 = "PropertyValuesHolder";
                    v4_2 = v4_1.toString();
                }

                Log.e(v0, v4_2);
            }
        }

        public Object clone() {
            return this.e();
        }

        Object d() {
            return Integer.valueOf(this.i);
        }

        public b e() {
            j v0 = super.a();
            ((b)v0).h = ((b)v0).e;
            return ((b)v0);
        }
    }

    String a;
    protected com.c.b.c b;
    Method c;
    Class d;
    g e;
    final ReentrantReadWriteLock f;
    final Object[] g;
    private Method h;
    private static final k i;
    private static final k j;
    private static Class[] k;
    private static Class[] l;
    private static Class[] m;
    private static final HashMap n;
    private static final HashMap o;
    private k p;
    private Object q;

    static {
        j.i = new d();
        j.j = new com.c.a.b();
        j.k = new Class[]{Float.TYPE, Float.class, Double.TYPE, Integer.TYPE, Double.class, Integer.class};
        j.l = new Class[]{Integer.TYPE, Integer.class, Float.TYPE, Double.TYPE, Float.class, Double.class};
        j.m = new Class[]{Double.TYPE, Double.class, Float.TYPE, Integer.TYPE, Float.class, Integer.class};
        j.n = new HashMap();
        j.o = new HashMap();
    }

    private j(com.c.b.c arg2) {
        super();
        this.c = null;
        this.h = null;
        this.e = null;
        this.f = new ReentrantReadWriteLock();
        this.g = new Object[1];
        this.b = arg2;
        if(arg2 != null) {
            this.a = arg2.a();
        }
    }

    j(com.c.b.c arg1, com.c.a.j$1 arg2) {
        this(arg1);
    }

    private j(String arg2) {
        super();
        this.c = null;
        this.h = null;
        this.e = null;
        this.f = new ReentrantReadWriteLock();
        this.g = new Object[1];
        this.a = arg2;
    }

    j(String arg1, com.c.a.j$1 arg2) {
        this(arg1);
    }

    public static j a(com.c.b.c arg1, float[] arg2) {
        return new a(arg1, arg2);
    }

    public static j a(com.c.b.c arg1, int[] arg2) {
        return new b(arg1, arg2);
    }

    public static j a(String arg1, float[] arg2) {
        return new a(arg1, arg2);
    }

    public static j a(String arg1, int[] arg2) {
        return new b(arg1, arg2);
    }

    static String a(String arg2, String arg3) {
        if(arg3 != null) {
            if(arg3.length() == 0) {
            }
            else {
                char v0 = Character.toUpperCase(arg3.charAt(0));
                arg3 = arg3.substring(1);
                arg2 = arg2 + v0 + arg3;
            }
        }

        return arg2;
    }

    private Method a(Class arg9, String arg10, Class arg11) {
        Method v7;
        Class[] v2;
        Method v9;
        Method v11_1;
        arg10 = j.a(arg10, this.a);
        Class[] v0 = null;
        if(arg11 == null) {
            try {
                v11_1 = arg9.getMethod(arg10, v0);
            }
            catch(NoSuchMethodException v11) {
                try {
                    v9 = arg9.getDeclaredMethod(arg10, v0);
                }
                catch(NoSuchMethodException ) {
                    v9 = ((Method)v0);
                    goto label_12;
                }

                try {
                    v9.setAccessible(true);
                }
                catch(NoSuchMethodException ) {
                label_12:
                    Log.e("PropertyValuesHolder", "Couldn\'t find no-arg method for property " + this.a + ": " + v11);
                }

                v11_1 = v9;
            }

            return v11_1;
        }

        Class[] v11_2 = new Class[1];
        if(this.d.equals(Float.class)) {
            v2 = j.k;
        }
        else if(this.d.equals(Integer.class)) {
            v2 = j.l;
        }
        else if(this.d.equals(Double.class)) {
            v2 = j.m;
        }
        else {
            v2 = new Class[]{this.d};
        }

        int v4 = v2.length;
        Method v5 = ((Method)v0);
        int v0_2;
        for(v0_2 = 0; v0_2 < v4; ++v0_2) {
            Class v6 = v2[v0_2];
            v11_2[0] = v6;
            try {
                v7 = arg9.getMethod(arg10, v11_2);
            }
            catch(NoSuchMethodException ) {
                goto label_59;
            }

            try {
                this.d = v6;
                return v7;
            }
            catch(NoSuchMethodException ) {
                v5 = v7;
            }

            try {
            label_59:
                v7 = arg9.getDeclaredMethod(arg10, v11_2);
            }
            catch(NoSuchMethodException ) {
                goto label_64;
            }

            try {
                v7.setAccessible(true);
                this.d = v6;
                return v7;
            }
            catch(NoSuchMethodException ) {
                v5 = v7;
            }

        label_64:
        }

        Log.e("PropertyValuesHolder", "Couldn\'t find setter/getter for property " + this.a + " with value type " + this.d);
        return v5;
    }

    private Method a(Class arg3, HashMap arg4, String arg5, Class arg6) {
        HashMap v0_1;
        Object v1;
        try {
            this.f.writeLock().lock();
            Object v0 = arg4.get(arg3);
            v1 = v0 != null ? ((HashMap)v0).get(this.a) : null;
            if(v1 == null) {
                Method v1_1 = this.a(arg3, arg5, arg6);
                if(v0 == null) {
                    v0_1 = new HashMap();
                    arg4.put(arg3, v0_1);
                }

                v0_1.put(this.a, v1_1);
            }
        }
        catch(Throwable v3) {
            this.f.writeLock().unlock();
            throw v3;
        }

        this.f.writeLock().unlock();
        return ((Method)v1);
    }

    public j a() {
        try {
            Object v0 = super.clone();
            ((j)v0).a = this.a;
            ((j)v0).b = this.b;
            ((j)v0).e = this.e.b();
            ((j)v0).p = this.p;
            return ((j)v0);
        }
        catch(CloneNotSupportedException ) {
            return null;
        }
    }

    void a(float arg2) {
        this.q = this.e.a(arg2);
    }

    public void a(com.c.b.c arg1) {
        this.b = arg1;
    }

    void a(Class arg4) {
        this.c = this.a(arg4, j.n, "set", this.d);
    }

    void a(Object arg6) {
        String v2_3;
        String v3;
        if(this.b == null) {
            goto label_34;
        }

        try {
            this.b.a(arg6);
            Iterator v0 = this.e.e.iterator();
            while(v0.hasNext()) {
                Object v1 = v0.next();
                if(((f)v1).a()) {
                    continue;
                }

                ((f)v1).a(this.b.a(arg6));
            }
        }
        catch(ClassCastException ) {
            goto label_17;
        }

        return;
    label_17:
        Log.e("PropertyValuesHolder", "No such property (" + this.b.a() + ") on target object " + arg6 + ". Trying reflection instead");
        this.b = null;
    label_34:
        Class v0_1 = arg6.getClass();
        if(this.c == null) {
            this.a(v0_1);
        }

        Iterator v1_2 = this.e.e.iterator();
        while(v1_2.hasNext()) {
            Object v2 = v1_2.next();
            if(((f)v2).a()) {
                continue;
            }

            if(this.h == null) {
                this.b(v0_1);
            }

            try {
                ((f)v2).a(this.h.invoke(arg6));
                continue;
            }
            catch(IllegalAccessException v2_1) {
                v3 = "PropertyValuesHolder";
                v2_3 = v2_1.toString();
            }
            catch(InvocationTargetException v2_2) {
                v3 = "PropertyValuesHolder";
                v2_3 = v2_2.toString();
            }

            Log.e(v3, v2_3);
        }
    }

    public void a(String arg1) {
        this.a = arg1;
    }

    public void a(float[] arg2) {
        this.d = Float.TYPE;
        this.e = g.a(arg2);
    }

    public void a(int[] arg2) {
        this.d = Integer.TYPE;
        this.e = g.a(arg2);
    }

    private void b(Class arg4) {
        this.h = this.a(arg4, j.o, "get", null);
    }

    void b() {
        k v0;
        if(this.p == null) {
            if(this.d == Integer.class) {
                v0 = j.i;
            }
            else if(this.d == Float.class) {
                v0 = j.j;
            }
            else {
                v0 = null;
            }

            this.p = v0;
        }

        if(this.p != null) {
            this.e.a(this.p);
        }
    }

    void b(Object arg4) {
        String v4_2;
        String v0;
        if(this.b != null) {
            this.b.a(arg4, this.d());
        }

        if(this.c != null) {
            try {
                this.g[0] = this.d();
                this.c.invoke(arg4, this.g);
                return;
            }
            catch(IllegalAccessException v4) {
                v0 = "PropertyValuesHolder";
                v4_2 = v4.toString();
            }
            catch(InvocationTargetException v4_1) {
                v0 = "PropertyValuesHolder";
                v4_2 = v4_1.toString();
            }

            Log.e(v0, v4_2);
        }
    }

    public String c() {
        return this.a;
    }

    public Object clone() {
        return this.a();
    }

    Object d() {
        return this.q;
    }

    public String toString() {
        return this.a + ": " + this.e.toString();
    }
}

