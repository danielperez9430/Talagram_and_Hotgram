package e;

import java.io.InterruptedIOException;
import java.util.concurrent.TimeUnit;

public class t {
    final class e.t$1 extends t {
        e.t$1() {
            super();
        }

        public t a(long arg1) {
            return this;
        }

        public t a(long arg1, TimeUnit arg3) {
            return this;
        }

        public void g() {
        }
    }

    private boolean a;
    private long b;
    public static final t c;
    private long d;

    static {
        t.c = new e.t$1();
    }

    public t() {
        super();
    }

    public t a(long arg2) {
        this.a = true;
        this.b = arg2;
        return this;
    }

    public t a(long arg4, TimeUnit arg6) {
        if(arg4 >= 0) {
            if(arg6 != null) {
                this.d = arg6.toNanos(arg4);
                return this;
            }

            throw new IllegalArgumentException("unit == null");
        }

        StringBuilder v0 = new StringBuilder();
        v0.append("timeout < 0: ");
        v0.append(arg4);
        throw new IllegalArgumentException(v0.toString());
    }

    public long d() {
        if(this.a) {
            return this.b;
        }

        throw new IllegalStateException("No deadline");
    }

    public t f() {
        this.a = false;
        return this;
    }

    public void g() {
        if(!Thread.interrupted()) {
            if(this.a) {
                if(this.b - System.nanoTime() > 0) {
                }
                else {
                    throw new InterruptedIOException("deadline reached");
                }
            }

            return;
        }

        throw new InterruptedIOException("thread interrupted");
    }

    public long k_() {
        return this.d;
    }

    public boolean l_() {
        return this.a;
    }

    public t m_() {
        this.d = 0;
        return this;
    }
}

