package android.arch.lifecycle;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map$Entry;
import java.util.Map;

class a {
    class android.arch.lifecycle.a$a {
        final Map a;
        final Map b;

        android.arch.lifecycle.a$a(Map arg5) {
            super();
            this.b = arg5;
            this.a = new HashMap();
            Iterator v5 = arg5.entrySet().iterator();
            while(v5.hasNext()) {
                Object v0 = v5.next();
                Object v1 = ((Map$Entry)v0).getValue();
                Object v2 = this.a.get(v1);
                if(v2 == null) {
                    ArrayList v2_1 = new ArrayList();
                    this.a.put(v1, v2_1);
                }

                ((List)v2).add(((Map$Entry)v0).getKey());
            }
        }

        private static void a(List arg2, g arg3, android.arch.lifecycle.d$a arg4, Object arg5) {
            if(arg2 != null) {
                int v0;
                for(v0 = arg2.size() - 1; v0 >= 0; --v0) {
                    arg2.get(v0).a(arg3, arg4, arg5);
                }
            }
        }

        void a(g arg3, android.arch.lifecycle.d$a arg4, Object arg5) {
            android.arch.lifecycle.a$a.a(this.a.get(arg4), arg3, arg4, arg5);
            android.arch.lifecycle.a$a.a(this.a.get(android.arch.lifecycle.d$a.ON_ANY), arg3, arg4, arg5);
        }
    }

    class b {
        final int a;
        final Method b;

        b(int arg1, Method arg2) {
            super();
            this.a = arg1;
            this.b = arg2;
            this.b.setAccessible(true);
        }

        void a(g arg5, android.arch.lifecycle.d$a arg6, Object arg7) {
            try {
                switch(this.a) {
                    case 0: {
                        this.b.invoke(arg7);
                        return;
                    }
                    case 1: {
                        this.b.invoke(arg7, arg5);
                        return;
                        throw new RuntimeException("Failed to call observer method", v5_1.getCause());
                        throw new RuntimeException(((Throwable)v5));
                    }
                    case 2: {
                        this.b.invoke(arg7, arg5, arg6);
                        return;
                    }
                    default: {
                        return;
                    }
                }
            }
            catch(IllegalAccessException v5) {
            }
            catch(InvocationTargetException v5_1) {
            }
        }

        public boolean equals(Object arg5) {
            boolean v0 = true;
            if(this == (((b)arg5))) {
                return 1;
            }

            if(arg5 != null) {
                if(this.getClass() != arg5.getClass()) {
                }
                else {
                    if(this.a != ((b)arg5).a || !this.b.getName().equals(((b)arg5).b.getName())) {
                        v0 = false;
                    }
                    else {
                    }

                    return v0;
                }
            }

            return 0;
        }

        public int hashCode() {
            return this.a * 31 + this.b.getName().hashCode();
        }
    }

    static a a;
    private final Map b;
    private final Map c;

    static {
        a.a = new a();
    }

    a() {
        super();
        this.b = new HashMap();
        this.c = new HashMap();
    }

    private android.arch.lifecycle.a$a a(Class arg12, Method[] arg13) {
        int v8;
        Class v0 = arg12.getSuperclass();
        HashMap v1 = new HashMap();
        if(v0 != null) {
            android.arch.lifecycle.a$a v0_1 = this.b(v0);
            if(v0_1 != null) {
                ((Map)v1).putAll(v0_1.b);
            }
        }

        Class[] v0_2 = arg12.getInterfaces();
        int v2 = v0_2.length;
        int v4;
        for(v4 = 0; v4 < v2; ++v4) {
            Iterator v5 = this.b(v0_2[v4]).b.entrySet().iterator();
            while(v5.hasNext()) {
                Object v6 = v5.next();
                this.a(((Map)v1), ((Map$Entry)v6).getKey(), ((Map$Entry)v6).getValue(), arg12);
            }
        }

        if(arg13 != null) {
        }
        else {
            arg13 = this.c(arg12);
        }

        int v0_3 = arg13.length;
        v2 = 0;
        boolean v4_1 = false;
        while(true) {
            if(v2 >= v0_3) {
                goto label_86;
            }

            Method v5_1 = arg13[v2];
            Annotation v6_1 = v5_1.getAnnotation(o.class);
            if(v6_1 == null) {
            }
            else {
                Class[] v4_2 = v5_1.getParameterTypes();
                if(v4_2.length <= 0) {
                    v8 = 0;
                }
                else if(v4_2[0].isAssignableFrom(g.class)) {
                    v8 = 1;
                }
                else {
                    throw new IllegalArgumentException("invalid parameter type. Must be one and instanceof LifecycleOwner");
                }

                android.arch.lifecycle.d$a v6_2 = ((o)v6_1).a();
                int v10 = 2;
                if(v4_2.length > 1) {
                    if(!v4_2[1].isAssignableFrom(android.arch.lifecycle.d$a.class)) {
                        throw new IllegalArgumentException("invalid parameter type. second arg must be an event");
                    }
                    else if(v6_2 == android.arch.lifecycle.d$a.ON_ANY) {
                        v8 = 2;
                    }
                    else {
                        throw new IllegalArgumentException("Second arg is supported only for ON_ANY value");
                    }
                }

                if(v4_2.length > v10) {
                    break;
                }

                this.a(((Map)v1), new b(v8, v5_1), v6_2, arg12);
                v4_1 = true;
            }

            ++v2;
        }

        throw new IllegalArgumentException("cannot have more than 2 params");
    label_86:
        android.arch.lifecycle.a$a v13 = new android.arch.lifecycle.a$a(((Map)v1));
        this.b.put(arg12, v13);
        this.c.put(arg12, Boolean.valueOf(v4_1));
        return v13;
    }

    private void a(Map arg4, b arg5, android.arch.lifecycle.d$a arg6, Class arg7) {
        Object v0 = arg4.get(arg5);
        if(v0 != null) {
            if(arg6 == (((android.arch.lifecycle.d$a)v0))) {
            }
            else {
                Method v4 = arg5.b;
                StringBuilder v1 = new StringBuilder();
                v1.append("Method ");
                v1.append(v4.getName());
                v1.append(" in ");
                v1.append(arg7.getName());
                v1.append(" already declared with different @OnLifecycleEvent value: previous value ");
                v1.append(v0);
                v1.append(", new value ");
                v1.append(arg6);
                throw new IllegalArgumentException(v1.toString());
            }
        }

        if(v0 == null) {
            arg4.put(arg5, arg6);
        }
    }

    boolean a(Class arg7) {
        if(this.c.containsKey(arg7)) {
            return this.c.get(arg7).booleanValue();
        }

        Method[] v0 = this.c(arg7);
        int v1 = v0.length;
        int v3;
        for(v3 = 0; v3 < v1; ++v3) {
            if(v0[v3].getAnnotation(o.class) != null) {
                this.a(arg7, v0);
                return 1;
            }
        }

        this.c.put(arg7, Boolean.valueOf(false));
        return 0;
    }

    android.arch.lifecycle.a$a b(Class arg2) {
        Object v0 = this.b.get(arg2);
        if(v0 != null) {
            return ((android.arch.lifecycle.a$a)v0);
        }

        return this.a(arg2, null);
    }

    private Method[] c(Class arg3) {
        try {
            return arg3.getDeclaredMethods();
        }
        catch(NoClassDefFoundError v3) {
            throw new IllegalArgumentException("The observer class has some methods that use newer APIs which are not available in the current OS version. Lifecycles cannot access even other methods so you should make sure that your observer classes only access framework classes that are available in your min API level OR use lifecycle:compiler annotation processor.", ((Throwable)v3));
        }
    }
}

