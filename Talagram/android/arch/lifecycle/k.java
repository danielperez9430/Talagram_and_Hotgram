package android.arch.lifecycle;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class k {
    private static Map a;
    private static Map b;

    static {
        k.a = new HashMap();
        k.b = new HashMap();
    }

    static GenericLifecycleObserver a(Object arg4) {
        if((arg4 instanceof FullLifecycleObserver)) {
            return new FullLifecycleObserverAdapter(((FullLifecycleObserver)arg4));
        }

        if((arg4 instanceof GenericLifecycleObserver)) {
            return arg4;
        }

        Class v0 = arg4.getClass();
        if(k.b(v0) == 2) {
            Object v0_1 = k.b.get(v0);
            int v2 = 0;
            if(((List)v0_1).size() == 1) {
                return new SingleGeneratedAdapterObserver(k.a(((List)v0_1).get(0), arg4));
            }

            c[] v1 = new c[((List)v0_1).size()];
            while(v2 < ((List)v0_1).size()) {
                v1[v2] = k.a(((List)v0_1).get(v2), arg4);
                ++v2;
            }

            return new CompositeGeneratedAdaptersObserver(v1);
        }

        return new ReflectiveGenericLifecycleObserver(arg4);
    }

    private static c a(Constructor arg2, Object arg3) {
        try {
            return arg2.newInstance(arg3);
        }
        catch(InvocationTargetException v2) {
            throw new RuntimeException(((Throwable)v2));
        }
        catch(InstantiationException v2_1) {
            throw new RuntimeException(((Throwable)v2_1));
        }
        catch(IllegalAccessException v2_2) {
            throw new RuntimeException(((Throwable)v2_2));
        }
    }

    public static String a(String arg3) {
        return arg3.replace(".", "_") + "_LifecycleAdapter";
    }

    private static Constructor a(Class arg4) {
        Constructor v4_1;
        try {
            Package v0 = arg4.getPackage();
            String v1 = arg4.getCanonicalName();
            String v0_1 = v0 != null ? v0.getName() : "";
            if(v0_1.isEmpty()) {
            }
            else {
                v1 = v1.substring(v0_1.length() + 1);
            }

            v1 = k.a(v1);
            if(v0_1.isEmpty()) {
            }
            else {
                v1 = v0_1 + "." + v1;
            }

            v4_1 = Class.forName(v1).getDeclaredConstructor(arg4);
            if(!v4_1.isAccessible()) {
                v4_1.setAccessible(true);
            }
        }
        catch(NoSuchMethodException v4) {
            throw new RuntimeException(((Throwable)v4));
        }
        catch(ClassNotFoundException ) {
            return null;
        }

        return v4_1;
    }

    private static int b(Class arg3) {
        if(k.a.containsKey(arg3)) {
            return k.a.get(arg3).intValue();
        }

        int v0 = k.c(arg3);
        k.a.put(arg3, Integer.valueOf(v0));
        return v0;
    }

    private static int c(Class arg8) {
        if(arg8.getCanonicalName() == null) {
            return 1;
        }

        Constructor v0 = k.a(arg8);
        int v2 = 2;
        if(v0 != null) {
            k.b.put(arg8, Collections.singletonList(v0));
            return v2;
        }

        if(a.a.a(arg8)) {
            return 1;
        }

        Class v0_1 = arg8.getSuperclass();
        ArrayList v3 = null;
        if(k.d(v0_1)) {
            if(k.b(v0_1) == 1) {
                return 1;
            }
            else {
                v3 = new ArrayList(k.b.get(v0_1));
            }
        }

        Class[] v0_2 = arg8.getInterfaces();
        int v4 = v0_2.length;
        int v5;
        for(v5 = 0; v5 < v4; ++v5) {
            Class v6 = v0_2[v5];
            if(!k.d(v6)) {
            }
            else if(k.b(v6) == 1) {
                return 1;
            }
            else {
                if(v3 == null) {
                    v3 = new ArrayList();
                }

                ((List)v3).addAll(k.b.get(v6));
            }
        }

        if(v3 != null) {
            k.b.put(arg8, v3);
            return v2;
        }

        return 1;
    }

    private static boolean d(Class arg1) {
        boolean v1 = arg1 == null || !f.class.isAssignableFrom(arg1) ? false : true;
        return v1;
    }
}

