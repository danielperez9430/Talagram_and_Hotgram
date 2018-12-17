package android.arch.lifecycle;

import android.arch.a.b.b$d;
import java.util.Iterator;

public abstract class LiveData {
    class android.arch.lifecycle.LiveData$1 implements Runnable {
        android.arch.lifecycle.LiveData$1(LiveData arg1) {
            this.a = arg1;
            super();
        }

        public void run() {
            Object v1_1;
            Object v0 = LiveData.a(this.a);
            __monitor_enter(v0);
            try {
                v1_1 = LiveData.b(this.a);
                LiveData.a(this.a, LiveData.e());
                __monitor_exit(v0);
            }
            catch(Throwable v1) {
                try {
                label_13:
                    __monitor_exit(v0);
                }
                catch(Throwable v1) {
                    goto label_13;
                }

                throw v1;
            }

            this.a.a(v1_1);
        }
    }

    class LifecycleBoundObserver extends a implements GenericLifecycleObserver {
        final g a;

        LifecycleBoundObserver(LiveData arg1, g arg2, n arg3) {
            this.b = arg1;
            super(arg1, arg3);
            this.a = arg2;
        }

        public void a(g arg1, android.arch.lifecycle.d$a arg2) {
            if(this.a.getLifecycle().a() == b.a) {
                this.b.a(this.c);
                return;
            }

            this.a(this.a());
        }

        boolean a() {
            return this.a.getLifecycle().a().a(b.d);
        }

        boolean a(g arg2) {
            boolean v2 = this.a == arg2 ? true : false;
            return v2;
        }

        void b() {
            this.a.getLifecycle().b(((f)this));
        }
    }

    abstract class a {
        final n c;
        boolean d;
        int e;

        a(LiveData arg1, n arg2) {
            this.f = arg1;
            super();
            this.e = -1;
            this.c = arg2;
        }

        abstract boolean a();

        void a(boolean arg5) {
            if(arg5 == this.d) {
                return;
            }

            this.d = arg5;
            int v0 = 1;
            int v5 = LiveData.c(this.f) == 0 ? 1 : 0;
            LiveData v1 = this.f;
            int v2 = LiveData.c(v1);
            if(this.d) {
            }
            else {
                v0 = -1;
            }

            LiveData.a(v1, v2 + v0);
            if(v5 != 0 && (this.d)) {
                this.f.b();
            }

            if(LiveData.c(this.f) == 0 && !this.d) {
                this.f.c();
            }

            if(this.d) {
                LiveData.a(this.f, this);
            }
        }

        boolean a(g arg1) {
            return 0;
        }

        void b() {
        }
    }

    private final Object a;
    private static final Object b;
    private android.arch.a.b.b c;
    private int d;
    private volatile Object e;
    private volatile Object f;
    private int g;
    private boolean h;
    private boolean i;
    private final Runnable j;

    static {
        LiveData.b = new Object();
    }

    public LiveData() {
        super();
        this.a = new Object();
        this.c = new android.arch.a.b.b();
        this.d = 0;
        this.e = LiveData.b;
        this.f = LiveData.b;
        this.g = -1;
        this.j = new android.arch.lifecycle.LiveData$1(this);
    }

    static int a(LiveData arg0, int arg1) {
        arg0.d = arg1;
        return arg1;
    }

    static Object a(LiveData arg0) {
        return arg0.a;
    }

    static Object a(LiveData arg0, Object arg1) {
        arg0.f = arg1;
        return arg1;
    }

    private void a(a arg3) {
        if(!arg3.d) {
            return;
        }

        if(!arg3.a()) {
            arg3.a(false);
            return;
        }

        if(arg3.e >= this.g) {
            return;
        }

        arg3.e = this.g;
        arg3.c.a(this.e);
    }

    static void a(LiveData arg0, a arg1) {
        arg0.b(arg1);
    }

    private static void a(String arg3) {
        if(android.arch.a.a.a.a().b()) {
            return;
        }

        StringBuilder v1 = new StringBuilder();
        v1.append("Cannot invoke ");
        v1.append(arg3);
        v1.append(" on a background");
        v1.append(" thread");
        throw new IllegalStateException(v1.toString());
    }

    public Object a() {
        Object v0 = this.e;
        if(v0 != LiveData.b) {
            return v0;
        }

        return null;
    }

    public void a(g arg3, n arg4) {
        if(arg3.getLifecycle().a() == b.a) {
            return;
        }

        LifecycleBoundObserver v0 = new LifecycleBoundObserver(this, arg3, arg4);
        Object v4 = this.c.a(arg4, v0);
        if(v4 != null) {
            if(((a)v4).a(arg3)) {
            }
            else {
                throw new IllegalArgumentException("Cannot add the same observer with different lifecycles");
            }
        }

        if(v4 != null) {
            return;
        }

        arg3.getLifecycle().a(((f)v0));
    }

    public void a(n arg2) {
        LiveData.a("removeObserver");
        Object v2 = this.c.b(arg2);
        if(v2 == null) {
            return;
        }

        ((a)v2).b();
        ((a)v2).a(false);
    }

    protected void a(Object arg2) {
        LiveData.a("setValue");
        ++this.g;
        this.e = arg2;
        this.b(null);
    }

    private void b(a arg4) {
        if(this.h) {
            this.i = true;
            return;
        }

        this.h = true;
        do {
            this.i = false;
            if(0 != 0) {
                this.a(arg4);
                arg4 = null;
            }
            else {
                d v1 = this.c.c();
                do {
                    if(((Iterator)v1).hasNext()) {
                        this.a(((Iterator)v1).next().getValue());
                        if(!this.i) {
                            continue;
                        }
                    }

                    break;
                }
                while(true);
            }
        }
        while(this.i);

        this.h = false;
    }

    static Object b(LiveData arg0) {
        return arg0.f;
    }

    protected void b() {
    }

    static int c(LiveData arg0) {
        return arg0.d;
    }

    protected void c() {
    }

    public boolean d() {
        boolean v0 = this.d > 0 ? true : false;
        return v0;
    }

    static Object e() {
        return LiveData.b;
    }
}

