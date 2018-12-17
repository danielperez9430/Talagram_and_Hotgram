package com.d.a.c;

import com.d.a.b.a.e;
import java.util.Comparator;

public final class d {
    public static String a(String arg1, e arg2) {
        return arg1 + "_" + arg2.a() + "x" + arg2.b();
    }

    public static Comparator a() {
        return new Comparator() {
            public int a(String arg3, String arg4) {
                return arg3.substring(0, arg3.lastIndexOf("_")).compareTo(arg4.substring(0, arg4.lastIndexOf("_")));
            }

            public int compare(Object arg1, Object arg2) {
                return this.a(((String)arg1), ((String)arg2));
            }
        };
    }
}

