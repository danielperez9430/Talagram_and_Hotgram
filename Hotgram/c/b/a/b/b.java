package c.b.a.b;

import android.os.Handler;
import android.os.Message;
import c.b.b.c;
import c.b.d;
import java.util.concurrent.TimeUnit;

final class b extends d {
    final class a extends c.b.d$b {
        private final Handler a;
        private volatile boolean b;

        a(Handler arg1) {
            super();
            this.a = arg1;
        }

        public c.b.b.b a(Runnable arg3, long arg4, TimeUnit arg6) {
            if(arg3 != null) {
                if(arg6 != null) {
                    if(this.b) {
                        return c.a();
                    }

                    c.b.a.b.b$b v0 = new c.b.a.b.b$b(this.a, c.b.f.a.a(arg3));
                    Message v3 = Message.obtain(this.a, ((Runnable)v0));
                    v3.obj = this;
                    this.a.sendMessageDelayed(v3, arg6.toMillis(arg4));
                    if(this.b) {
                        this.a.removeCallbacks(((Runnable)v0));
                        return c.a();
                    }

                    return ((c.b.b.b)v0);
                }

                throw new NullPointerException("unit == null");
            }

            throw new NullPointerException("run == null");
        }

        public void a() {
            this.b = true;
            this.a.removeCallbacksAndMessages(this);
        }
    }

    final class c.b.a.b.b$b implements c.b.b.b, Runnable {
        private final Handler a;
        private final Runnable b;
        private volatile boolean c;

        c.b.a.b.b$b(Handler arg1, Runnable arg2) {
            super();
            this.a = arg1;
            this.b = arg2;
        }

        public void a() {
            this.c = true;
            this.a.removeCallbacks(((Runnable)this));
        }

        public void run() {
            try {
                this.b.run();
            }
            catch(Throwable v0) {
                c.b.f.a.a(v0);
            }
        }
    }

    private final Handler b;

    b(Handler arg1) {
        super();
        this.b = arg1;
    }

    public c.b.b.b a(Runnable arg3, long arg4, TimeUnit arg6) {
        if(arg3 != null) {
            if(arg6 != null) {
                c.b.a.b.b$b v0 = new c.b.a.b.b$b(this.b, c.b.f.a.a(arg3));
                this.b.postDelayed(((Runnable)v0), arg6.toMillis(arg4));
                return ((c.b.b.b)v0);
            }

            throw new NullPointerException("unit == null");
        }

        throw new NullPointerException("run == null");
    }

    public c.b.d$b a() {
        return new a(this.b);
    }
}

