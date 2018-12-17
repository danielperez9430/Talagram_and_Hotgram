package org.telegram.customization.compression.a;

public enum b {
    enum a {
        public static final enum a a;
        public static final enum a b;
        public static final enum a c;
        public static final enum a d;
        public final String e;
        public final String f;

        static {
            a.a = new a("WINDOWS", 0, "win32", "so");
            a.b = new a("LINUX", 1, "linux", "so");
            a.c = new a("MAC", 2, "darwin", "dylib");
            a.d = new a("SOLARIS", 3, "solaris", "so");
            a.g = new a[]{a.a, a.b, a.c, a.d};
        }

        private a(String arg1, int arg2, String arg3, String arg4) {
            super(arg1, arg2);
            this.e = arg3;
            this.f = arg4;
        }

        public static a valueOf(String arg1) {
            return Enum.valueOf(a.class, arg1);
        }

        public static a[] values() {
            // Method was not decompiled
        }
    }

    private static boolean a;

    static {
        b.b = new b[0];
        b.a = false;
    }

    public static boolean a() {
        boolean v1_1;
        Class v0 = b.class;
        __monitor_enter(v0);
        try {
            v1_1 = b.a;
        }
        catch(Throwable v1) {
            __monitor_exit(v0);
            throw v1;
        }

        __monitor_exit(v0);
        return v1_1;
    }

    public static void b() {
        Class v0 = b.class;
        __monitor_enter(v0);
        try {
            if(!b.a) {
                goto label_6;
            }
        }
        catch(Throwable v1) {
            goto label_19;
        }

        __monitor_exit(v0);
        return;
        try {
        label_6:
            String v1_1 = b.e();
            StringBuilder v3 = new StringBuilder();
            v3.append("Unsupported OS/arch, cannot find ");
            v3.append(v1_1);
            v3.append(". Please try building from source.");
            throw new UnsupportedOperationException(v3.toString());
        }
        catch(Throwable v1) {
        label_19:
            __monitor_exit(v0);
            throw v1;
        }
    }

    private static String c() {
        return System.getProperty("os.arch");
    }

    private static a d() {
        String v0 = System.getProperty("os.name");
        if(v0.contains("Linux")) {
            return a.b;
        }

        if(v0.contains("Mac")) {
            return a.c;
        }

        if(v0.contains("Windows")) {
            return a.a;
        }

        if(!v0.contains("Solaris")) {
            if(v0.contains("SunOS")) {
            }
            else {
                StringBuilder v2 = new StringBuilder();
                v2.append("Unsupported operating system: ");
                v2.append(v0);
                throw new UnsupportedOperationException(v2.toString());
            }
        }

        return a.d;
    }

    private static String e() {
        a v0 = b.d();
        return "/" + v0.e + "/" + b.c() + "/liblz4-java." + v0.f;
    }

    public static b valueOf(String arg1) {
        return Enum.valueOf(b.class, arg1);
    }

    public static b[] values() {
        // Method was not decompiled
    }
}

