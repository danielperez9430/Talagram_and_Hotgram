package androidx.work;

import android.support.v4.f.j;
import androidx.a.a.c;

public abstract class s extends NonBlockingWorker {
    public enum a {
        public static final enum a a;
        public static final enum a b;
        public static final enum a c;

        static {
            a.a = new a("SUCCESS", 0);
            a.b = new a("FAILURE", 1);
            a.c = new a("RETRY", 2);
            a.d = new a[]{a.a, a.b, a.c};
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

    c a;

    @Deprecated public s() {
        super();
    }

    public com.google.common.a.a.a f() {
        this.a = c.d();
        this.k().execute(new Runnable() {
            public void run() {
                a v0 = this.a.m();
                this.a.a(v0);
                this.a.a.a(new j(v0, this.a.g()));
            }
        });
        return this.a;
    }

    public abstract a m();
}

