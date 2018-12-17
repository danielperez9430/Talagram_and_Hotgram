package org.telegram.news.c;

import android.content.Context;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import org.telegram.customization.c.c;

public class b {
    private static volatile HashMap a;
    private static volatile a b;
    private static volatile c c;

    static {
        b.c = new c();
    }

    public static ArrayList a(int arg1) {
        if(b.e(arg1)) {
            return b.a.get(Integer.valueOf(arg1)).b();
        }

        return new ArrayList();
    }

    public static c a() {
        if(b.c == null) {
            b.c = new c();
        }

        return b.c;
    }

    public static void a(int arg1, Context arg2, ArrayList arg3) {
        if(b.e(arg1)) {
            b.a.get(Integer.valueOf(arg1)).a(arg2, arg3, b.b());
        }
    }

    public static void a(int arg1, Context arg2, org.telegram.news.b.b arg3) {
        if(b.e(arg1)) {
            b.a.get(Integer.valueOf(arg1)).a(arg2, arg3, b.b());
        }
    }

    public static void a(int arg1, Context arg2, org.telegram.news.b.b[] arg3) {
        ArrayList v0 = new ArrayList();
        Collections.addAll(((Collection)v0), ((Object[])arg3));
        b.a(arg1, arg2, v0);
    }

    public static void a(int arg1, boolean arg2) {
        if(b.e(arg1)) {
            b.a.get(Integer.valueOf(arg1)).a(arg2);
        }
    }

    static a b() {
        if(b.b == null) {
            b.b = new a() {
                public void a() {
                    b.c().a();
                }
            };
        }

        return b.b;
    }

    public static String b(int arg1) {
        if(b.e(arg1)) {
            return b.a.get(Integer.valueOf(arg1)).c();
        }

        return "";
    }

    static c c() {
        return b.c;
    }

    public static void c(int arg2) {
        if(b.e(arg2)) {
            b.a.get(Integer.valueOf(arg2)).a(b.b());
        }
        else {
            b.a.put(Integer.valueOf(arg2), new org.telegram.news.c.c());
        }
    }

    public static boolean d(int arg1) {
        if(b.e(arg1)) {
            return b.a.get(Integer.valueOf(arg1)).f();
        }

        return 0;
    }

    private static boolean e(int arg3) {
        if(b.a == null) {
            b.a = new HashMap();
        }

        if(!b.a.containsKey(Integer.valueOf(arg3))) {
            b.a.put(Integer.valueOf(arg3), new org.telegram.news.c.c());
        }

        if(b.a.get(Integer.valueOf(arg3)) == null) {
            b.a.remove(Integer.valueOf(arg3));
            b.a.put(Integer.valueOf(arg3), new org.telegram.news.c.c());
        }

        b.a.get(Integer.valueOf(arg3)).b(arg3);
        return 1;
    }
}

