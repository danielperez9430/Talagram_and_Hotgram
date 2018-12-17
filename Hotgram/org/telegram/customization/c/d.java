package org.telegram.customization.c;

import android.content.Context;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.telegram.customization.dynamicadapter.data.ObjBase;
import org.telegram.customization.dynamicadapter.data.SlsBaseMessage;

public class d {
    private static volatile HashMap a;
    private static volatile a b;
    private static volatile c c;

    static {
        d.c = new c();
    }

    public static List a(int arg1) {
        if(d.k(arg1)) {
            return d.a.get(Integer.valueOf(arg1)).b();
        }

        return new ArrayList();
    }

    public static c a() {
        if(d.c == null) {
            d.c = new c();
        }

        return d.c;
    }

    public static SlsBaseMessage a(int arg1, int arg2) {
        if(d.k(arg1)) {
            return d.a.get(Integer.valueOf(arg1)).a(arg2);
        }

        return null;
    }

    public static void a(int arg1, long arg2) {
        if(d.k(arg1)) {
            d.a.get(Integer.valueOf(arg1)).a(arg2);
        }
    }

    public static void a(int arg2, Context arg3, SlsBaseMessage arg4) {
        if(d.k(arg2)) {
            d.a.get(Integer.valueOf(arg2)).a(arg3, arg4, d.b(), true);
        }
    }

    public static void a(int arg1, String arg2) {
        if(d.k(arg1)) {
            d.a.get(Integer.valueOf(arg1)).b(arg2);
        }
    }

    public static void a(int arg1, ArrayList arg2) {
        if(arg2 != null) {
            Iterator v2 = arg2.iterator();
            while(v2.hasNext()) {
                d.a(v2.next(), arg1);
            }
        }
    }

    public static void a(ObjBase arg1, int arg2) {
        if(SlsBaseMessage.isMediaAvailable(arg1)) {
            d.a(arg2, null, ((SlsBaseMessage)arg1));
        }
    }

    public static void a(int arg1, boolean arg2) {
        if(d.k(arg1)) {
            d.a.get(Integer.valueOf(arg1)).b(arg2);
        }
    }

    static a b() {
        if(d.b == null) {
            d.b = new a() {
                public void a() {
                    d.c().a();
                }
            };
        }

        return d.b;
    }

    public static long b(int arg2) {
        if(d.k(arg2)) {
            return d.a.get(Integer.valueOf(arg2)).c();
        }

        return 0;
    }

    public static void b(int arg1, int arg2) {
        if(d.k(arg1)) {
            d.a.get(Integer.valueOf(arg1)).c(arg2);
        }
    }

    public static void b(int arg1, long arg2) {
        if(d.k(arg1)) {
            d.a.get(Integer.valueOf(arg1)).b(arg2);
        }
    }

    static c c() {
        return d.c;
    }

    public static void c(int arg2) {
        if(d.k(arg2)) {
            d.a.get(Integer.valueOf(arg2)).a(d.b());
        }
        else {
            d.a.put(Integer.valueOf(arg2), new b());
        }
    }

    public static int d(int arg1) {
        if(d.k(arg1)) {
            return d.a.get(Integer.valueOf(arg1)).e();
        }

        return 0;
    }

    public static boolean e(int arg1) {
        if(d.k(arg1)) {
            return d.a.get(Integer.valueOf(arg1)).f();
        }

        return 0;
    }

    public static String f(int arg1) {
        if(d.k(arg1)) {
            return d.a.get(Integer.valueOf(arg1)).g();
        }

        return "";
    }

    public static boolean g(int arg1) {
        if(d.k(arg1)) {
            return d.a.get(Integer.valueOf(arg1)).k();
        }

        return 0;
    }

    public static int h(int arg1) {
        if(d.k(arg1)) {
            return d.a.get(Integer.valueOf(arg1)).h();
        }

        return 20;
    }

    public static long i(int arg2) {
        if(d.k(arg2)) {
            return d.a.get(Integer.valueOf(arg2)).i();
        }

        return 0;
    }

    public static long j(int arg2) {
        if(d.k(arg2)) {
            return d.a.get(Integer.valueOf(arg2)).j();
        }

        return 0;
    }

    private static boolean k(int arg3) {
        if(d.a == null) {
            d.a = new HashMap();
        }

        if(!d.a.containsKey(Integer.valueOf(arg3))) {
            d.a.put(Integer.valueOf(arg3), new b());
        }

        if(d.a.get(Integer.valueOf(arg3)) == null) {
            d.a.remove(Integer.valueOf(arg3));
            d.a.put(Integer.valueOf(arg3), new b());
        }

        d.a.get(Integer.valueOf(arg3)).b(arg3);
        return 1;
    }
}

