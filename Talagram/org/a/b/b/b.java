package org.a.b.b;

import java.util.Hashtable;
import java.util.StringTokenizer;
import org.a.a.a$a;
import org.a.a.a.c;
import org.a.a.d;

public final class b {
    Class a;
    ClassLoader b;
    String c;
    int d;
    static Hashtable e;
    static Class f;
    private static Object[] g;

    static {
        b.e = new Hashtable();
        b.e.put("void", Void.TYPE);
        b.e.put("boolean", Boolean.TYPE);
        b.e.put("byte", Byte.TYPE);
        b.e.put("char", Character.TYPE);
        b.e.put("short", Short.TYPE);
        b.e.put("int", Integer.TYPE);
        b.e.put("long", Long.TYPE);
        b.e.put("float", Float.TYPE);
        b.e.put("double", Double.TYPE);
        b.g = new Object[0];
    }

    public b(String arg1, Class arg2) {
        super();
        this.c = arg1;
        this.a = arg2;
        this.d = 0;
        this.b = arg2.getClassLoader();
    }

    public a a(String arg4, d arg5, int arg6) {
        int v1 = this.d;
        this.d = v1 + 1;
        return new org.a.b.b.c$a(v1, arg4, arg5, this.a(arg6, -1));
    }

    public c a(String arg12, String arg13, String arg14, String arg15, String arg16, String arg17, String arg18) {
        b v0 = this;
        int v3 = Integer.parseInt(arg12, 16);
        Class v5 = b.a(arg14, v0.b);
        StringTokenizer v1 = new StringTokenizer(arg15, ":");
        int v2 = v1.countTokens();
        Class[] v6 = new Class[v2];
        int v4 = 0;
        int v7;
        for(v7 = 0; v7 < v2; ++v7) {
            v6[v7] = b.a(v1.nextToken(), v0.b);
        }

        v1 = new StringTokenizer(arg16, ":");
        v2 = v1.countTokens();
        String[] v7_1 = new String[v2];
        int v8;
        for(v8 = 0; v8 < v2; ++v8) {
            v7_1[v8] = v1.nextToken();
        }

        v1 = new StringTokenizer(arg17, ":");
        v2 = v1.countTokens();
        Class[] v8_1 = new Class[v2];
        while(v4 < v2) {
            v8_1[v4] = b.a(v1.nextToken(), v0.b);
            ++v4;
        }

        return new e(v3, arg13, v5, v6, v7_1, v8_1, b.a(arg18, v0.b));
    }

    public static org.a.a.a a(a arg2, Object arg3, Object arg4) {
        return new org.a.b.b.c(arg2, arg3, arg4, b.g);
    }

    public static org.a.a.a a(a arg3, Object arg4, Object arg5, Object arg6) {
        return new org.a.b.b.c(arg3, arg4, arg5, new Object[]{arg6});
    }

    public static org.a.a.a a(a arg1, Object arg2, Object arg3, Object[] arg4) {
        return new org.a.b.b.c(arg1, arg2, arg3, arg4);
    }

    static Class a(String arg1) {
        try {
            return Class.forName(arg1);
        }
        catch(ClassNotFoundException v1) {
            throw new NoClassDefFoundError(((Throwable)v1).getMessage());
        }
    }

    static Class a(String arg1, ClassLoader arg2) {
        Class v1;
        if(arg1.equals("*")) {
            return null;
        }

        Object v0 = b.e.get(arg1);
        if(v0 != null) {
            return ((Class)v0);
        }

        if(arg2 != null) {
            goto label_12;
        }

        try {
            return Class.forName(arg1);
        label_12:
            return Class.forName(arg1, false, arg2);
        }
        catch(ClassNotFoundException ) {
            if(b.f == null) {
                v1 = b.a("java.lang.ClassNotFoundException");
                b.f = v1;
            }
            else {
                v1 = b.f;
            }

            return v1;
        }
    }

    public static org.a.a.a a(a arg3, Object arg4, Object arg5, Object arg6, Object arg7) {
        return new org.a.b.b.c(arg3, arg4, arg5, new Object[]{arg6, arg7});
    }

    public org.a.a.a.d a(int arg3, int arg4) {
        return new g(this.a, this.c, arg3);
    }
}

