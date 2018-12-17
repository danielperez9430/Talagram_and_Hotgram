package android.arch.lifecycle;

public abstract class d {
    public enum a {
        public static final enum a ON_ANY;
        public static final enum a ON_CREATE;
        public static final enum a ON_DESTROY;
        public static final enum a ON_PAUSE;
        public static final enum a ON_RESUME;
        public static final enum a ON_START;
        public static final enum a ON_STOP;

        static {
            a.ON_CREATE = new a("ON_CREATE", 0);
            a.ON_START = new a("ON_START", 1);
            a.ON_RESUME = new a("ON_RESUME", 2);
            a.ON_PAUSE = new a("ON_PAUSE", 3);
            a.ON_STOP = new a("ON_STOP", 4);
            a.ON_DESTROY = new a("ON_DESTROY", 5);
            a.ON_ANY = new a("ON_ANY", 6);
            a.$VALUES = new a[]{a.ON_CREATE, a.ON_START, a.ON_RESUME, a.ON_PAUSE, a.ON_STOP, a.ON_DESTROY, a.ON_ANY};
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

    public enum b {
        public static final enum b a;
        public static final enum b b;
        public static final enum b c;
        public static final enum b d;
        public static final enum b e;

        static {
            b.a = new b("DESTROYED", 0);
            b.b = new b("INITIALIZED", 1);
            b.c = new b("CREATED", 2);
            b.d = new b("STARTED", 3);
            b.e = new b("RESUMED", 4);
            b.f = new b[]{b.a, b.b, b.c, b.d, b.e};
        }

        private b(String arg1, int arg2) {
            super(arg1, arg2);
        }

        public boolean a(b arg1) {
            boolean v1 = this.compareTo(((Enum)arg1)) >= 0 ? true : false;
            return v1;
        }

        public static b valueOf(String arg1) {
            return Enum.valueOf(b.class, arg1);
        }

        public static b[] values() {
            // Method was not decompiled
        }
    }

    public d() {
        super();
    }

    public abstract b a();

    public abstract void a(f arg1);

    public abstract void b(f arg1);
}

