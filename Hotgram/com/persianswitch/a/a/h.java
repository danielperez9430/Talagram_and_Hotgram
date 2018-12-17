package com.persianswitch.a.a;

public abstract class h implements Runnable {
    protected final String b;

    public h(String arg1, Object[] arg2) {
        super();
        this.b = l.a(arg1, arg2);
    }

    protected abstract void b();

    public final void run() {
        String v0 = Thread.currentThread().getName();
        Thread.currentThread().setName(this.b);
        try {
            this.b();
        }
        catch(Throwable v1) {
            Thread.currentThread().setName(v0);
            throw v1;
        }

        Thread.currentThread().setName(v0);
    }
}

