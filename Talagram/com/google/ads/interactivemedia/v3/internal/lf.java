package com.google.ads.interactivemedia.v3.internal;

import java.util.Iterator;
import java.util.Map$Entry;
import java.util.Map;

public final class lf {
    class com.google.ads.interactivemedia.v3.internal.lf$1 extends lm {
        Object a(Object arg1) {
            return this.a(((Map$Entry)arg1));
        }

        Object a(Map$Entry arg1) {
            return arg1.getKey();
        }
    }

    abstract enum a implements kn {
        enum com.google.ads.interactivemedia.v3.internal.lf$a$1 extends a {
            com.google.ads.interactivemedia.v3.internal.lf$a$1(String arg2, int arg3) {
                super(arg2, arg3, null);
            }

            public Object a(Object arg1) {
                return this.a(((Map$Entry)arg1));
            }

            public Object a(Map$Entry arg1) {
                return arg1.getKey();
            }
        }

        enum com.google.ads.interactivemedia.v3.internal.lf$a$2 extends a {
            com.google.ads.interactivemedia.v3.internal.lf$a$2(String arg2, int arg3) {
                super(arg2, arg3, null);
            }

            public Object a(Object arg1) {
                return this.a(((Map$Entry)arg1));
            }

            public Object a(Map$Entry arg1) {
                return arg1.getValue();
            }
        }

        public static final enum a a;
        public static final enum a b;

        static {
            a.a = new com.google.ads.interactivemedia.v3.internal.lf$a$1("KEY", 0);
            a.b = new com.google.ads.interactivemedia.v3.internal.lf$a$2("VALUE", 1);
            a.c = new a[]{a.a, a.b};
        }

        private a(String arg1, int arg2) {
            super(arg1, arg2);
        }

        a(String arg1, int arg2, com.google.ads.interactivemedia.v3.internal.lf$1 arg3) {
            this(arg1, arg2);
        }

        public static a valueOf(String arg1) {
            return Enum.valueOf(a.class, arg1);
        }

        public static a[] values() {
            // Method was not decompiled
        }
    }

    static boolean a(Map arg1, Object arg2) {
        if(arg1 == (((Map)arg2))) {
            return 1;
        }

        if((arg2 instanceof Map)) {
            return arg1.entrySet().equals(((Map)arg2).entrySet());
        }

        return 0;
    }

    static String a(Map arg4) {
        StringBuilder v0 = kw.a(arg4.size());
        v0.append('{');
        Iterator v4 = arg4.entrySet().iterator();
        int v1 = 1;
        while(v4.hasNext()) {
            Object v2 = v4.next();
            if(v1 == 0) {
                v0.append(", ");
            }

            v1 = 0;
            v0.append(((Map$Entry)v2).getKey());
            v0.append('=');
            v0.append(((Map$Entry)v2).getValue());
        }

        v0.append('}');
        return v0.toString();
    }

    static kn a() {
        return a.b;
    }
}

