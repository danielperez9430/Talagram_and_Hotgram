package okhttp3;

import java.lang.ref.Reference;
import java.net.Socket;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import okhttp3.internal.b.d;
import okhttp3.internal.b.g$a;
import okhttp3.internal.b.g;
import okhttp3.internal.c;
import okhttp3.internal.g.f;

public final class j {
    class okhttp3.j$1 implements Runnable {
        okhttp3.j$1(j arg1) {
            this.a = arg1;
            super();
        }

        public void run() {
            while(true) {
                long v0 = this.a.a(System.nanoTime());
                if(v0 == -1) {
                    return;
                }

                if(v0 <= 0) {
                    continue;
                }

                long v4 = v0 / 1000000;
                v0 -= 1000000 * v4;
                j v2 = this.a;
                __monitor_enter(v2);
                try {
                    this.a.wait(v4, ((int)v0));
                    goto label_20;
                }
                catch(Throwable v0_1) {
                label_19:
                    try {
                        __monitor_exit(v2);
                    }
                    catch(Throwable v0_1) {
                        goto label_19;
                    }

                    throw v0_1;
                }
                catch(InterruptedException ) {
                    try {
                    label_20:
                        __monitor_exit(v2);
                        continue;
                    }
                    catch(Throwable v0_1) {
                        goto label_19;
                    }

                    return;
                }
            }
        }
    }

    final d a;
    boolean b;
    private static final Executor d;
    private final int e;
    private final long f;
    private final Runnable g;
    private final Deque h;

    static {
        j.c = j.class.desiredAssertionStatus() ^ 1;
        j.d = new ThreadPoolExecutor(0, 2147483647, 60, TimeUnit.SECONDS, new SynchronousQueue(), c.a("OkHttp ConnectionPool", true));
    }

    public j() {
        this(5, 5, TimeUnit.MINUTES);
    }

    public j(int arg3, long arg4, TimeUnit arg6) {
        super();
        this.g = new okhttp3.j$1(this);
        this.h = new ArrayDeque();
        this.a = new d();
        this.e = arg3;
        this.f = arg6.toNanos(arg4);
        if(arg4 > 0) {
            return;
        }

        StringBuilder v6 = new StringBuilder();
        v6.append("keepAliveDuration <= 0: ");
        v6.append(arg4);
        throw new IllegalArgumentException(v6.toString());
    }

    private int a(okhttp3.internal.b.c arg7, long arg8) {
        List v0 = arg7.d;
        int v2 = 0;
        do {
        label_3:
            if(v2 >= v0.size()) {
                goto label_33;
            }

            Object v3 = v0.get(v2);
            if(((Reference)v3).get() != null) {
                ++v2;
                goto label_3;
            }

            StringBuilder v4 = new StringBuilder();
            v4.append("A connection to ");
            v4.append(arg7.a().a().a());
            v4.append(" was leaked. Did you forget to close a response body?");
            f.c().a(v4.toString(), ((a)v3).a);
            v0.remove(v2);
            arg7.a = true;
        }
        while(!v0.isEmpty());

        arg7.e = arg8 - this.f;
        return 0;
    label_33:
        return v0.size();
    }

    long a(long arg12) {
        Object v5;
        __monitor_enter(this);
        try {
            Iterator v0 = this.h.iterator();
            long v3 = -9223372036854775808L;
            v5 = null;
            int v2 = 0;
            int v6 = 0;
            while(v0.hasNext()) {
                Object v7 = v0.next();
                if(this.a(((okhttp3.internal.b.c)v7), arg12) > 0) {
                    ++v6;
                    continue;
                }

                ++v2;
                long v8 = arg12 - ((okhttp3.internal.b.c)v7).e;
                if(v8 <= v3) {
                    continue;
                }

                v5 = v7;
                v3 = v8;
            }

            if(v3 < this.f) {
                if(v2 > this.e) {
                }
                else if(v2 > 0) {
                    __monitor_exit(this);
                    return this.f - v3;
                }
                else if(v6 > 0) {
                    __monitor_exit(this);
                    return this.f;
                }
                else {
                    this.b = false;
                    __monitor_exit(this);
                    return -1;
                }
            }

            this.h.remove(v5);
            __monitor_exit(this);
        }
        catch(Throwable v12) {
            goto label_51;
        }

        c.a(((okhttp3.internal.b.c)v5).c());
        return 0;
        try {
        label_51:
            __monitor_exit(this);
        }
        catch(Throwable v12) {
            goto label_51;
        }

        throw v12;
    }

    @Nullable Socket a(okhttp3.a arg4, g arg5) {
        Object v1;
        ae v2;
        if(!j.c) {
            if(Thread.holdsLock(this)) {
            }
            else {
                throw new AssertionError();
            }
        }

        Iterator v0 = this.h.iterator();
        do {
        label_10:
            v2 = null;
            if(!v0.hasNext()) {
                goto label_22;
            }

            v1 = v0.next();
            if(!((okhttp3.internal.b.c)v1).a(arg4, v2)) {
                goto label_10;
            }

            if(!((okhttp3.internal.b.c)v1).e()) {
                goto label_10;
            }
        }
        while(v1 == arg5.c());

        return arg5.a(((okhttp3.internal.b.c)v1));
    label_22:
        return ((Socket)v2);
    }

    @Nullable okhttp3.internal.b.c a(okhttp3.a arg4, g arg5, ae arg6) {
        Object v1;
        if(!j.c) {
            if(Thread.holdsLock(this)) {
            }
            else {
                throw new AssertionError();
            }
        }

        Iterator v0 = this.h.iterator();
        do {
            if(!v0.hasNext()) {
                return null;
            }

            v1 = v0.next();
        }
        while(!((okhttp3.internal.b.c)v1).a(arg4, arg6));

        arg5.a(((okhttp3.internal.b.c)v1), true);
        return ((okhttp3.internal.b.c)v1);
    }

    void a(okhttp3.internal.b.c arg3) {
        if(!j.c) {
            if(Thread.holdsLock(this)) {
            }
            else {
                throw new AssertionError();
            }
        }

        if(!this.b) {
            this.b = true;
            j.d.execute(this.g);
        }

        this.h.add(arg3);
    }

    boolean b(okhttp3.internal.b.c arg2) {
        if(!j.c) {
            if(Thread.holdsLock(this)) {
            }
            else {
                throw new AssertionError();
            }
        }

        if(!arg2.a) {
            if(this.e == 0) {
            }
            else {
                this.notifyAll();
                return 0;
            }
        }

        this.h.remove(arg2);
        return 1;
    }
}

