package e;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;

public class a extends t {
    final class e.a$a extends Thread {
        e.a$a() {
            super("Okio Watchdog");
            this.setDaemon(true);
        }

        public void run() {
            a v1_1;
            Class v0;
            try {
                while(true) {
                label_0:
                    v0 = a.class;
                    __monitor_enter(v0);
                    break;
                }
            }
            catch(InterruptedException ) {
                goto label_0;
            }

            try {
                v1_1 = a.e();
                if(v1_1 == null) {
                    __monitor_exit(v0);
                    goto label_0;
                }

                if(v1_1 == a.b) {
                    a.b = null;
                    __monitor_exit(v0);
                    return;
                }

                __monitor_exit(v0);
            }
            catch(Throwable v1) {
                goto label_16;
            }

            try {
                v1_1.a();
            }
            catch(InterruptedException ) {
            }

            goto label_0;
            try {
            label_16:
                __monitor_exit(v0);
            }
            catch(Throwable v1) {
                goto label_16;
            }

            try {
                throw v1;
            }
            catch(InterruptedException ) {
                goto label_0;
            }
        }
    }

    private static final long a;
    @Nullable static a b;
    private static final long d;
    private boolean e;
    @Nullable private a f;
    private long g;

    static {
        a.a = TimeUnit.SECONDS.toMillis(60);
        a.d = TimeUnit.MILLISECONDS.toNanos(a.a);
    }

    public a() {
        super();
    }

    private static void a(a arg6, long arg7, boolean arg9) {
        Class v0 = a.class;
        __monitor_enter(v0);
        try {
            if(a.b == null) {
                a.b = new a();
                new e.a$a().start();
            }

            long v1 = System.nanoTime();
            long v3 = 0;
            if(arg7 != v3 && (arg9)) {
                arg6.g = Math.min(arg7, arg6.d() - v1) + v1;
            }
            else if(arg7 != v3) {
                arg6.g = arg7 + v1;
            }
            else if(arg9) {
                arg6.g = arg6.d();
            }
            else {
                goto label_48;
            }

            arg7 = arg6.b(v1);
            a v9 = a.b;
            while(v9.f != null) {
                if(arg7 < v9.f.b(v1)) {
                }
                else {
                    v9 = v9.f;
                    continue;
                }

                break;
            }

            arg6.f = v9.f;
            v9.f = arg6;
            if(v9 == a.b) {
                a.class.notify();
            }
        }
        catch(Throwable v6) {
            goto label_52;
        }

        __monitor_exit(v0);
        return;
        try {
        label_48:
            throw new AssertionError();
        }
        catch(Throwable v6) {
        label_52:
            __monitor_exit(v0);
            throw v6;
        }
    }

    private static boolean a(a arg3) {
        boolean v3_1;
        Class v0 = a.class;
        __monitor_enter(v0);
        try {
            a v1 = a.b;
            while(true) {
                if(v1 == null) {
                    break;
                }
                else if(v1.f == arg3) {
                    v1.f = arg3.f;
                    arg3.f = null;
                    v3_1 = false;
                }
                else {
                    v1 = v1.f;
                    continue;
                }

                goto label_11;
            }
        }
        catch(Throwable v3) {
            goto label_18;
        }

        v3_1 = true;
    label_11:
        __monitor_exit(v0);
        return v3_1;
    label_18:
        __monitor_exit(v0);
        throw v3;
    }

    public final r a(r arg2) {
        return new r(arg2) {
            public t a() {
                return this.b;
            }

            public void a_(c arg7, long arg8) {
                u.a(arg7.b, 0, arg8);
                while(true) {
                    long v0 = 0;
                    if(arg8 <= v0) {
                        return;
                    }

                    o v2 = arg7.a;
                    while(v0 < 65536) {
                        v0 += ((long)(v2.c - v2.b));
                        if(v0 >= arg8) {
                            v0 = arg8;
                        }
                        else {
                            v2 = v2.f;
                            continue;
                        }

                        break;
                    }

                    this.b.c();
                    try {
                        this.a.a_(arg7, v0);
                    }
                    catch(Throwable v7) {
                    }
                    catch(IOException v7_1) {
                        try {
                            throw this.b.b(v7_1);
                        }
                        catch(Throwable v7) {
                            this.b.a(false);
                            throw v7;
                        }
                    }

                    arg8 -= v0;
                    this.b.a(true);
                }
            }

            public void close() {
                this.b.c();
                try {
                    this.a.close();
                }
                catch(Throwable v0) {
                }
                catch(IOException v0_1) {
                    try {
                        throw this.b.b(v0_1);
                    }
                    catch(Throwable v0) {
                        this.b.a(false);
                        throw v0;
                    }
                }

                this.b.a(true);
            }

            public void flush() {
                this.b.c();
                try {
                    this.a.flush();
                }
                catch(Throwable v0) {
                }
                catch(IOException v0_1) {
                    try {
                        throw this.b.b(v0_1);
                    }
                    catch(Throwable v0) {
                        this.b.a(false);
                        throw v0;
                    }
                }

                this.b.a(true);
            }

            public String toString() {
                return "AsyncTimeout.sink(" + this.a + ")";
            }
        };
    }

    final void a(boolean arg2) {
        if(this.j_()) {
            if(!arg2) {
            }
            else {
                throw this.a(null);
            }
        }
    }

    public final s a(s arg2) {
        return new s(arg2) {
            public long a(c arg2, long arg3) {
                long v2_2;
                this.b.c();
                try {
                    v2_2 = this.a.a(arg2, arg3);
                }
                catch(Throwable v2) {
                }
                catch(IOException v2_1) {
                    try {
                        throw this.b.b(v2_1);
                    }
                    catch(Throwable v2) {
                        this.b.a(false);
                        throw v2;
                    }
                }

                this.b.a(true);
                return v2_2;
            }

            public t a() {
                return this.b;
            }

            public void close() {
                try {
                    this.a.close();
                }
                catch(Throwable v0) {
                }
                catch(IOException v0_1) {
                    try {
                        throw this.b.b(v0_1);
                    }
                    catch(Throwable v0) {
                        this.b.a(false);
                        throw v0;
                    }
                }

                this.b.a(true);
            }

            public String toString() {
                return "AsyncTimeout.source(" + this.a + ")";
            }
        };
    }

    protected IOException a(@Nullable IOException arg3) {
        InterruptedIOException v0 = new InterruptedIOException("timeout");
        if(arg3 != null) {
            v0.initCause(((Throwable)arg3));
        }

        return ((IOException)v0);
    }

    protected void a() {
    }

    private long b(long arg3) {
        return this.g - arg3;
    }

    final IOException b(IOException arg2) {
        if(!this.j_()) {
            return arg2;
        }

        return this.a(arg2);
    }

    public final void c() {
        if(!this.e) {
            long v0 = this.k_();
            boolean v2 = this.l_();
            if(v0 == 0 && !v2) {
                return;
            }

            this.e = true;
            a.a(this, v0, v2);
            return;
        }

        throw new IllegalStateException("Unbalanced enter/exit");
    }

    @Nullable static a e() {
        long v2;
        a v0 = a.b.f;
        a v1 = null;
        if(v0 == null) {
            v2 = System.nanoTime();
            a.class.wait(a.a);
            if(a.b.f == null && System.nanoTime() - v2 >= a.d) {
                v1 = a.b;
            }

            return v1;
        }

        v2 = v0.b(System.nanoTime());
        if(v2 > 0) {
            long v6 = v2 / 1000000;
            a.class.wait(v6, ((int)(v2 - 1000000 * v6)));
            return v1;
        }

        a.b.f = v0.f;
        v0.f = v1;
        return v0;
    }

    public final boolean j_() {
        if(!this.e) {
            return 0;
        }

        this.e = false;
        return a.a(this);
    }
}

