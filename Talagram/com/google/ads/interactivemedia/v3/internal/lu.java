package com.google.ads.interactivemedia.v3.internal;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map$Entry;
import java.util.Map;

public class lu {
    public static final String a;
    public static final String b;
    private static final Map c;
    private static final Map d;
    private static final Map e;
    private static final Map f;
    private static final Map g;

    static {
        lu.a = String.valueOf('.');
        lu.b = String.valueOf('$');
        lu.c = new HashMap();
        lu.c.put("boolean", Boolean.TYPE);
        lu.c.put("byte", Byte.TYPE);
        lu.c.put("char", Character.TYPE);
        lu.c.put("short", Short.TYPE);
        lu.c.put("int", Integer.TYPE);
        lu.c.put("long", Long.TYPE);
        lu.c.put("double", Double.TYPE);
        lu.c.put("float", Float.TYPE);
        lu.c.put("void", Void.TYPE);
        lu.d = new HashMap();
        lu.d.put(Boolean.TYPE, Boolean.class);
        lu.d.put(Byte.TYPE, Byte.class);
        lu.d.put(Character.TYPE, Character.class);
        lu.d.put(Short.TYPE, Short.class);
        lu.d.put(Integer.TYPE, Integer.class);
        lu.d.put(Long.TYPE, Long.class);
        lu.d.put(Double.TYPE, Double.class);
        lu.d.put(Float.TYPE, Float.class);
        lu.d.put(Void.TYPE, Void.TYPE);
        lu.e = new HashMap();
        Iterator v0 = lu.d.entrySet().iterator();
        while(v0.hasNext()) {
            Object v1 = v0.next();
            Object v2 = ((Map$Entry)v1).getKey();
            v1 = ((Map$Entry)v1).getValue();
            if(v2.equals(v1)) {
                continue;
            }

            lu.e.put(v1, v2);
        }

        HashMap v0_1 = new HashMap();
        ((Map)v0_1).put("int", "I");
        ((Map)v0_1).put("boolean", "Z");
        ((Map)v0_1).put("float", "F");
        ((Map)v0_1).put("long", "J");
        ((Map)v0_1).put("short", "S");
        ((Map)v0_1).put("byte", "B");
        ((Map)v0_1).put("double", "D");
        ((Map)v0_1).put("char", "C");
        HashMap v1_1 = new HashMap();
        Iterator v2_1 = ((Map)v0_1).entrySet().iterator();
        while(v2_1.hasNext()) {
            Object v3 = v2_1.next();
            ((Map)v1_1).put(((Map$Entry)v3).getValue(), ((Map$Entry)v3).getKey());
        }

        lu.f = Collections.unmodifiableMap(((Map)v0_1));
        lu.g = Collections.unmodifiableMap(((Map)v1_1));
    }

    public static boolean a(Class arg2) {
        boolean v0 = false;
        if(arg2 == null) {
            return 0;
        }

        if((arg2.isPrimitive()) || (lu.b(arg2))) {
            v0 = true;
        }

        return v0;
    }

    public static boolean b(Class arg1) {
        return lu.e.containsKey(arg1);
    }
}

