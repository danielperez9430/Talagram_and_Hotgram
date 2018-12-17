package c.b.e.b;

import c.b.d.d;
import c.b.d.f;
import c.b.d.g;
import java.util.Comparator;
import java.util.concurrent.Callable;

public final class a {
    final class c.b.e.b.a$a implements c.b.d.a {
        c.b.e.b.a$a() {
            super();
        }

        public void a() {
        }

        public String toString() {
            return "EmptyAction";
        }
    }

    final class b implements d {
        b() {
            super();
        }

        public void a(Object arg1) {
        }

        public String toString() {
            return "EmptyConsumer";
        }
    }

    final class c implements f {
        c() {
            super();
        }
    }

    final class c.b.e.b.a$d implements Runnable {
        c.b.e.b.a$d() {
            super();
        }

        public void run() {
        }

        public String toString() {
            return "EmptyRunnable";
        }
    }

    final class e implements d {
        e() {
            super();
        }

        public void a(Object arg1) {
            this.a(((Throwable)arg1));
        }

        public void a(Throwable arg1) {
            c.b.f.a.a(arg1);
        }
    }

    final class c.b.e.b.a$f implements g {
        c.b.e.b.a$f() {
            super();
        }
    }

    final class c.b.e.b.a$g implements c.b.d.e {
        c.b.e.b.a$g() {
            super();
        }

        public Object a(Object arg1) {
            return arg1;
        }

        public String toString() {
            return "IdentityFunction";
        }
    }

    final class h implements d {
        h() {
            super();
        }

        public void a(Object arg1) {
            this.a(((org.b.a)arg1));
        }

        public void a(org.b.a arg3) {
            arg3.a(9223372036854775807L);
        }
    }

    final class i implements Comparator {
        i() {
            super();
        }

        public int compare(Object arg1, Object arg2) {
            return ((Comparable)arg1).compareTo(arg2);
        }
    }

    final class j implements Callable {
        j() {
            super();
        }

        public Object call() {
            return null;
        }
    }

    final class k implements d {
        k() {
            super();
        }

        public void a(Object arg1) {
            this.a(((Throwable)arg1));
        }

        public void a(Throwable arg2) {
            c.b.f.a.a(new c.b.c.d(arg2));
        }
    }

    final class l implements g {
        l() {
            super();
        }
    }

    static final c.b.d.e a;
    public static final Runnable b;
    public static final c.b.d.a c;
    static final d d;
    public static final d e;
    public static final d f;
    public static final f g;
    static final g h;
    static final g i;
    static final Callable j;
    static final Comparator k;
    public static final d l;

    static {
        a.a = new c.b.e.b.a$g();
        a.b = new c.b.e.b.a$d();
        a.c = new c.b.e.b.a$a();
        a.d = new b();
        a.e = new e();
        a.f = new k();
        a.g = new c();
        a.h = new l();
        a.i = new c.b.e.b.a$f();
        a.j = new j();
        a.k = new i();
        a.l = new h();
    }

    public static d a() {
        return a.d;
    }
}

