package com.d.a.b.a;

public class b {
    public enum a {
        public static final enum a a;
        public static final enum a b;
        public static final enum a c;
        public static final enum a d;
        public static final enum a e;

        static {
            a.a = new a("IO_ERROR", 0);
            a.b = new a("DECODING_ERROR", 1);
            a.c = new a("NETWORK_DENIED", 2);
            a.d = new a("OUT_OF_MEMORY", 3);
            a.e = new a("UNKNOWN", 4);
            a.f = new a[]{a.a, a.b, a.c, a.d, a.e};
        }

        private a(String arg1, int arg2) {
            super(arg1, arg2);
        }

        public static a valueOf(String arg1) {
            return Enum.valueOf(a.class, arg1);
        }

        public static a[] values() {
            // Method was not decompiled
        }
    }

    private final a a;
    private final Throwable b;

    public b(a arg1, Throwable arg2) {
        super();
        this.a = arg1;
        this.b = arg2;
    }

    public Throwable a() {
        return this.b;
    }
}

