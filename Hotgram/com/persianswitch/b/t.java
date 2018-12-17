package com.persianswitch.b;

import java.io.InterruptedIOException;
import java.util.concurrent.TimeUnit;

public class t {
    final class com.persianswitch.b.t$1 extends t {
        com.persianswitch.b.t$1() {
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
    public static final t b;
    private long c;
    private long d;

    static {
        t.b = new com.persianswitch.b.t$1();
    }

    public t() {
        super();
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

    public t a(long arg2) {
        this.a = true;
        this.c = arg2;
        return this;
    }

    public long d() {
        if(this.a) {
            return this.c;
        }

        throw new IllegalStateException("No deadline");
    }

    public long d_() {
        return this.d;
    }

    public boolean e_() {
        return this.a;
    }

    public t f() {
        this.a = false;
        return this;
    }

    public t f_() {
        this.d = 0;
        return this;
    }

    public void g() {
        if(!Thread.interrupted()) {
            if(this.a) {
                if(this.c - System.nanoTime() > 0) {
                }
                else {
                    throw new InterruptedIOException("deadline reached");
                }
            }

            return;
        }

        throw new InterruptedIOException("thread interrupted");
    }
}

