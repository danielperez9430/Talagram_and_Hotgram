package com.google.ads.interactivemedia.v3.internal;

abstract class co {
    public final class a extends bl {
        public a(String arg1) {
            super(arg1);
        }
    }

    protected final ck a;
    private long b;

    protected co(ck arg3) {
        super();
        this.a = arg3;
        this.b = -1;
    }

    public final long a() {
        return this.b;
    }

    public final void a(long arg1) {
        this.b = arg1;
    }

    protected abstract void a(fp arg1, long arg2);

    protected abstract boolean a(fp arg1);

    public final void b(fp arg2, long arg3) {
        if(this.a(arg2)) {
            this.a(arg2, arg3);
        }
    }
}

