package com.d.a.b.d;

import java.io.InputStream;
import java.util.Locale;

public interface b {
    public enum a {
        public static final enum a a;
        public static final enum a b;
        public static final enum a c;
        public static final enum a d;
        public static final enum a e;
        public static final enum a f;
        public static final enum a g;
        private String h;
        private String i;

        static {
            a.a = new a("HTTP", 0, "http");
            a.b = new a("HTTPS", 1, "https");
            a.c = new a("FILE", 2, "file");
            a.d = new a("CONTENT", 3, "content");
            a.e = new a("ASSETS", 4, "assets");
            a.f = new a("DRAWABLE", 5, "drawable");
            a.g = new a("UNKNOWN", 6, "");
            a.j = new a[]{a.a, a.b, a.c, a.d, a.e, a.f, a.g};
        }

        private a(String arg1, int arg2, String arg3) {
            super(arg1, arg2);
            this.h = arg3;
            this.i = arg3 + "://";
        }

        public static a a(String arg5) {
            if(arg5 != null) {
                a[] v0 = a.values();
                int v1 = v0.length;
                int v2 = 0;
                while(v2 < v1) {
                    a v3 = v0[v2];
                    if(v3.d(arg5)) {
                        return v3;
                    }
                    else {
                        ++v2;
                        continue;
                    }

                    break;
                }
            }

            return a.g;
        }

        public String b(String arg3) {
            return this.i + arg3;
        }

        public String c(String arg4) {
            if(this.d(arg4)) {
                return arg4.substring(this.i.length());
            }

            throw new IllegalArgumentException(String.format("URI [%1$s] doesn\'t have expected scheme [%2$s]", arg4, this.h));
        }

        private boolean d(String arg2) {
            return arg2.toLowerCase(Locale.US).startsWith(this.i);
        }

        public static a valueOf(String arg1) {
            return Enum.valueOf(a.class, arg1);
        }

        public static a[] values() {
            // Method was not decompiled
        }
    }

    InputStream a(String arg1, Object arg2);
}

