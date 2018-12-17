package android.arch.lifecycle;

import android.os.Handler;

public class r {
    class a implements Runnable {
        final android.arch.lifecycle.d$a a;
        private final h b;
        private boolean c;

        a(h arg2, android.arch.lifecycle.d$a arg3) {
            super();
            this.c = false;
            this.b = arg2;
            this.a = arg3;
        }

        public void run() {
            if(!this.c) {
                this.b.a(this.a);
                this.c = true;
            }
        }
    }

    private final h a;
    private final Handler b;
    private a c;

    public r(g arg2) {
        super();
        this.a = new h(arg2);
        this.b = new Handler();
    }

    private void a(android.arch.lifecycle.d$a arg3) {
        if(this.c != null) {
            this.c.run();
        }

        this.c = new a(this.a, arg3);
        this.b.postAtFrontOfQueue(this.c);
    }

    public void a() {
        this.a(android.arch.lifecycle.d$a.ON_CREATE);
    }

    public void b() {
        this.a(android.arch.lifecycle.d$a.ON_START);
    }

    public void c() {
        this.a(android.arch.lifecycle.d$a.ON_START);
    }

    public void d() {
        this.a(android.arch.lifecycle.d$a.ON_STOP);
        this.a(android.arch.lifecycle.d$a.ON_DESTROY);
    }

    public d e() {
        return this.a;
    }
}

