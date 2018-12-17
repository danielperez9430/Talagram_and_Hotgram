package com.google.ads.interactivemedia.v3.internal;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class he {
    private static final Map a;
    private static final Map b;

    static {
        HashMap v0 = new HashMap(16);
        HashMap v2 = new HashMap(16);
        he.a(((Map)v0), ((Map)v2), Boolean.TYPE, Boolean.class);
        he.a(((Map)v0), ((Map)v2), Byte.TYPE, Byte.class);
        he.a(((Map)v0), ((Map)v2), Character.TYPE, Character.class);
        he.a(((Map)v0), ((Map)v2), Double.TYPE, Double.class);
        he.a(((Map)v0), ((Map)v2), Float.TYPE, Float.class);
        he.a(((Map)v0), ((Map)v2), Integer.TYPE, Integer.class);
        he.a(((Map)v0), ((Map)v2), Long.TYPE, Long.class);
        he.a(((Map)v0), ((Map)v2), Short.TYPE, Short.class);
        he.a(((Map)v0), ((Map)v2), Void.TYPE, Void.class);
        he.a = Collections.unmodifiableMap(((Map)v0));
        he.b = Collections.unmodifiableMap(((Map)v2));
    }

    public static Class a(Class arg2) {
        Object v2;
        Object v0 = he.a.get(gw.a(arg2));
        if(v0 == null) {
        }
        else {
            v2 = v0;
        }

        return ((Class)v2);
    }

    private static void a(Map arg0, Map arg1, Class arg2, Class arg3) {
        arg0.put(arg2, arg3);
        arg1.put(arg3, arg2);
    }

    public static boolean a(Type arg1) {
        return he.a.containsKey(arg1);
    }
}

