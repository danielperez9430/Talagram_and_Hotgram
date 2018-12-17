package android.support.v4.e;

import java.util.Locale;

public final class e {
    class a implements c {
        static final a a;
        static final a b;
        private final boolean c;

        static {
            a.a = new a(true);
            a.b = new a(false);
        }

        private a(boolean arg1) {
            super();
            this.c = arg1;
        }

        public int a(CharSequence arg5, int arg6, int arg7) {
            arg7 += arg6;
            int v1 = 0;
            while(arg6 < arg7) {
                switch(e.a(Character.getDirectionality(arg5.charAt(arg6)))) {
                    case 0: {
                        goto label_13;
                    }
                    case 1: {
                        goto label_10;
                    }
                }

                goto label_17;
            label_10:
                if(this.c) {
                    goto label_16;
                }

                return 1;
            label_13:
                if(this.c) {
                    return 0;
                }

            label_16:
                v1 = 1;
            label_17:
                ++arg6;
            }

            if(v1 != 0) {
                return this.c;
            }

            return 2;
        }
    }

    class b implements c {
        static final b a;

        static {
            b.a = new b();
        }

        private b() {
            super();
        }

        public int a(CharSequence arg3, int arg4, int arg5) {
            arg5 += arg4;
            int v0 = 2;
            int v1 = 2;
            while(arg4 < arg5) {
                if(v1 != v0) {
                    return v1;
                }

                v1 = e.b(Character.getDirectionality(arg3.charAt(arg4)));
                ++arg4;
            }

            return v1;
        }
    }

    interface c {
        int a(CharSequence arg1, int arg2, int arg3);
    }

    abstract class d implements android.support.v4.e.d {
        private final c a;

        d(c arg1) {
            super();
            this.a = arg1;
        }

        protected abstract boolean a();

        public boolean a(CharSequence arg2, int arg3, int arg4) {
            if(arg2 != null && arg3 >= 0 && arg4 >= 0 && arg2.length() - arg4 >= arg3) {
                if(this.a == null) {
                    return this.a();
                }
                else {
                    return this.b(arg2, arg3, arg4);
                }
            }

            throw new IllegalArgumentException();
        }

        private boolean b(CharSequence arg2, int arg3, int arg4) {
            switch(this.a.a(arg2, arg3, arg4)) {
                case 0: {
                    return 1;
                }
                case 1: {
                    return 0;
                }
            }

            return this.a();
        }
    }

    class android.support.v4.e.e$e extends d {
        private final boolean a;

        android.support.v4.e.e$e(c arg1, boolean arg2) {
            super(arg1);
            this.a = arg2;
        }

        protected boolean a() {
            return this.a;
        }
    }

    class f extends d {
        static final f a;

        static {
            f.a = new f();
        }

        f() {
            super(null);
        }

        protected boolean a() {
            boolean v1 = true;
            if(android.support.v4.e.f.a(Locale.getDefault()) == 1) {
            }
            else {
                v1 = false;
            }

            return v1;
        }
    }

    public static final android.support.v4.e.d a;
    public static final android.support.v4.e.d b;
    public static final android.support.v4.e.d c;
    public static final android.support.v4.e.d d;
    public static final android.support.v4.e.d e;
    public static final android.support.v4.e.d f;

    static {
        e.a = new android.support.v4.e.e$e(null, false);
        e.b = new android.support.v4.e.e$e(null, true);
        e.c = new android.support.v4.e.e$e(b.a, false);
        e.d = new android.support.v4.e.e$e(b.a, true);
        e.e = new android.support.v4.e.e$e(a.a, false);
        e.f = f.a;
    }

    static int a(int arg0) {
        switch(arg0) {
            case 0: {
                return 1;
            }
            case 1: 
            case 2: {
                return 0;
            }
        }

        return 2;
    }

    static int b(int arg0) {
        switch(arg0) {
            case 0: {
                return 1;
            }
            case 1: 
            case 2: {
                return 0;
            }
        }

        switch(arg0) {
            case 14: 
            case 15: {
                return 1;
            }
            case 16: 
            case 17: {
                return 0;
            }
        }

        return 2;
    }
}

