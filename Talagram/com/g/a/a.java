package com.g.a;

public class a implements Comparable {
    public enum com.g.a.a$a {
        public static final enum com.g.a.a$a a;
        public static final enum com.g.a.a$a b;
        public static final enum com.g.a.a$a c;
        public static final enum com.g.a.a$a d;

        static {
            com.g.a.a$a.a = new com.g.a.a$a("LOW", 0);
            com.g.a.a$a.b = new com.g.a.a$a("NORMAL", 1);
            com.g.a.a$a.c = new com.g.a.a$a("HIGH", 2);
            com.g.a.a$a.d = new com.g.a.a$a("IMMEDIATE", 3);
            com.g.a.a$a.e = new com.g.a.a$a[]{com.g.a.a$a.a, com.g.a.a$a.b, com.g.a.a$a.c, com.g.a.a$a.d};
        }

        private com.g.a.a$a(String arg1, int arg2) {
            super(arg1, arg2);
        }

        public static com.g.a.a$a valueOf(String arg1) {
            return Enum.valueOf(com.g.a.a$a.class, arg1);
        }

        public static com.g.a.a$a[] values() {
            // Method was not decompiled
        }
    }

    private int a;
    private com.g.a.a$a b;

    public int a(a arg3) {
        com.g.a.a$a v0 = this.a();
        com.g.a.a$a v1 = arg3.a();
        int v0_1 = v0 == v1 ? this.a - arg3.a : v1.ordinal() - v0.ordinal();
        return v0_1;
    }

    public com.g.a.a$a a() {
        return this.b;
    }

    public int compareTo(Object arg1) {
        return this.a(((a)arg1));
    }
}

