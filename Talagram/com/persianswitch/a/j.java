package com.persianswitch.a;

import com.persianswitch.a.a.b.s;
import com.persianswitch.a.a.c.b;
import com.persianswitch.a.a.k;
import com.persianswitch.a.a.l;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public final class j {
    class com.persianswitch.a.j$1 implements Runnable {
        com.persianswitch.a.j$1(j arg1) {
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

    final k a;
    boolean b;
    private static final Executor d;
    private final int e;
    private final long f;
    private final Runnable g;
    private final Deque h;

    static {
        j.c = j.class.desiredAssertionStatus() ^ 1;
        j.d = new ThreadPoolExecutor(0, 2147483647, 60, TimeUnit.SECONDS, new SynchronousQueue(), l.a("OkHttp ConnectionPool", true));
    }

    public j() {
        this(5, 5, TimeUnit.MINUTES);
    }

    public j(int arg3, long arg4, TimeUnit arg6) {
        super();
        this.g = new com.persianswitch.a.j$1(this);
        this.h = new ArrayDeque();
        this.a = new k();
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

    private int a(b arg8, long arg9) {
        List v0 = arg8.h;
        int v2 = 0;
        do {
        label_3:
            if(v2 >= v0.size()) {
                goto label_34;
            }

            if(v0.get(v2).get() != null) {
                ++v2;
                goto label_3;
            }

            com.persianswitch.a.a.j v3 = com.persianswitch.a.a.j.c();
            v3.a(5, "A connection to " + arg8.a().a().a() + " was leaked. Did you forget to close a response body?", null);
            v0.remove(v2);
            arg8.i = true;
        }
        while(!v0.isEmpty());

        arg8.j = arg9 - this.f;
        return 0;
    label_34:
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
                if(this.a(((b)v7), arg12) > 0) {
                    ++v6;
                    continue;
                }

                ++v2;
                long v8 = arg12 - ((b)v7).j;
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

        l.a(((b)v5).b());
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

    b a(a arg5, s arg6) {
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
        label_10:
            if(!v0.hasNext()) {
                return null;
            }

            v1 = v0.next();
            if(((b)v1).h.size() >= ((b)v1).g) {
                goto label_10;
            }

            if(!arg5.equals(((b)v1).a().a)) {
                goto label_10;
            }
        }
        while(((b)v1).i);

        arg6.a(((b)v1));
        return ((b)v1);
    }

    void a(b arg3) {
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

    boolean b(b arg2) {
        if(!j.c) {
            if(Thread.holdsLock(this)) {
            }
            else {
                throw new AssertionError();
            }
        }

        if(!arg2.i) {
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

