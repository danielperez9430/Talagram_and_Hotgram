package c.b.e.f;

import c.b.e.a.a;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicReferenceArray;

public final class b extends AtomicReferenceArray implements c.b.b.b, Runnable, Callable {
    final Runnable a;
    static final Object b;
    static final Object c;
    static final Object d;
    static final Object e;

    static {
        b.b = new Object();
        b.c = new Object();
        b.d = new Object();
        b.e = new Object();
    }

    public b(Runnable arg2, a arg3) {
        super(3);
        this.a = arg2;
        this.lazySet(0, arg3);
    }

    public void a() {
        Object v0;
        boolean v2;
        Object v1;
        do {
            v1 = this.get(1);
            if(v1 != b.e && v1 != b.c) {
                if(v1 == b.d) {
                }
                else {
                    v2 = this.get(2) != Thread.currentThread() ? true : false;
                    Object v4 = v2 ? b.d : b.c;
                    if(!this.compareAndSet(1, v1, v4)) {
                        continue;
                    }

                    break;
                }
            }

            goto label_25;
        }
        while(true);

        if(v1 == null) {
            goto label_25;
        }

        ((Future)v1).cancel(v2);
        do {
        label_25:
            v0 = this.get(0);
            if(v0 != b.e && v0 != b.b) {
                if(v0 == null) {
                }
                else {
                    if(!this.compareAndSet(0, v0, b.b)) {
                        continue;
                    }

                    break;
                }
            }

            return;
        }
        while(true);

        ((a)v0).c(((c.b.b.b)this));
    }

    public void a(Future arg4) {
        do {
            Object v1 = this.get(1);
            if(v1 == b.e) {
                return;
            }

            if(v1 == b.c) {
                arg4.cancel(false);
                return;
            }

            if(v1 == b.d) {
                arg4.cancel(true);
                return;
            }
        }
        while(!this.compareAndSet(1, v1, arg4));
    }

    public Object call() {
        this.run();
        return null;
    }

    public void run() {
        int v1 = 2;
        this.lazySet(v1, Thread.currentThread());
        Object v0 = null;
        try {
            this.a.run();
        }
        catch(Throwable v4) {
        }
        catch(Throwable v4) {
            try {
                c.b.f.a.a(v4);
            }
            catch(Throwable v4) {
                this.lazySet(v1, v0);
                v0 = this.get(0);
                if(v0 != b.b && (this.compareAndSet(0, v0, b.e)) && v0 != null) {
                    ((a)v0).c(((c.b.b.b)this));
                }

                while(true) {
                    v0 = this.get(1);
                    if(v0 != b.c && v0 != b.d && !this.compareAndSet(1, v0, b.e)) {
                        continue;
                    }

                    break;
                }

                throw v4;
            }
        }

        this.lazySet(v1, v0);
        v0 = this.get(0);
        if(v0 != b.b && (this.compareAndSet(0, v0, b.e)) && v0 != null) {
            ((a)v0).c(((c.b.b.b)this));
        }

        do {
            v0 = this.get(1);
            if(v0 == b.c) {
                return;
            }

            if(v0 == b.d) {
                return;
            }
        }
        while(!this.compareAndSet(1, v0, b.e));
    }
}

