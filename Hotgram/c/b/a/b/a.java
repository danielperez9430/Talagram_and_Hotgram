package c.b.a.b;

import android.os.Handler;
import android.os.Looper;
import c.b.d;
import java.util.concurrent.Callable;

public final class a {
    final class c.b.a.b.a$1 implements Callable {
        c.b.a.b.a$1() {
            super();
        }

        public d a() {
            return c.b.a.b.a$a.a;
        }

        public Object call() {
            return this.a();
        }
    }

    final class c.b.a.b.a$a {
        static final d a;

        static {
            c.b.a.b.a$a.a = new b(new Handler(Looper.getMainLooper()));
        }
    }

    private static final d a;

    static {
        a.a = c.b.a.a.a.a(new c.b.a.b.a$1());
    }

    public static d a() {
        return c.b.a.a.a.a(a.a);
    }
}

