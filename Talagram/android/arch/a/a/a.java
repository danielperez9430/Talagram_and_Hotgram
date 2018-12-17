package android.arch.a.a;

import java.util.concurrent.Executor;

public class a extends c {
    final class android.arch.a.a.a$1 implements Executor {
        android.arch.a.a.a$1() {
            super();
        }

        public void execute(Runnable arg2) {
            a.a().b(arg2);
        }
    }

    final class android.arch.a.a.a$2 implements Executor {
        android.arch.a.a.a$2() {
            super();
        }

        public void execute(Runnable arg2) {
            a.a().a(arg2);
        }
    }

    private static volatile a a;
    private c b;
    private c c;
    private static final Executor d;
    private static final Executor e;

    static {
        a.d = new android.arch.a.a.a$1();
        a.e = new android.arch.a.a.a$2();
    }

    private a() {
        super();
        this.c = new b();
        this.b = this.c;
    }

    public void a(Runnable arg2) {
        this.b.a(arg2);
    }

    public static a a() {
        if(a.a != null) {
            return a.a;
        }

        Class v0 = a.class;
        __monitor_enter(v0);
        try {
            if(a.a == null) {
                a.a = new a();
            }

            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            try {
            label_15:
                __monitor_exit(v0);
            }
            catch(Throwable v1) {
                goto label_15;
            }

            throw v1;
        }

        return a.a;
    }

    public boolean b() {
        return this.b.b();
    }

    public void b(Runnable arg2) {
        this.b.b(arg2);
    }
}

