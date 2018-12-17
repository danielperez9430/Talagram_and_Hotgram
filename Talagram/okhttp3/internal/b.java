package okhttp3.internal;

public abstract class b implements Runnable {
    protected final String b;

    public b(String arg1, Object[] arg2) {
        super();
        this.b = c.a(arg1, arg2);
    }

    protected abstract void c();

    public final void run() {
        String v0 = Thread.currentThread().getName();
        Thread.currentThread().setName(this.b);
        try {
            this.c();
        }
        catch(Throwable v1) {
            Thread.currentThread().setName(v0);
            throw v1;
        }

        Thread.currentThread().setName(v0);
    }
}

