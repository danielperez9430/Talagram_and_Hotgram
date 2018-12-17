package com.persianswitch.a.a;

import com.persianswitch.b.d;
import com.persianswitch.b.r;
import com.persianswitch.b.t;
import java.io.Closeable;
import java.io.File;
import java.io.Flushable;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.concurrent.Executor;
import java.util.regex.Pattern;

public final class c implements Closeable, Flushable {
    final class com.persianswitch.a.a.c$1 implements r {
        com.persianswitch.a.a.c$1() {
            super();
        }

        public t a() {
            return t.b;
        }

        public void a_(com.persianswitch.b.c arg1, long arg2) {
            arg1.g(arg2);
        }

        public void close() {
        }

        public void flush() {
        }
    }

    public final class a {
        private final b b;
        private final boolean[] c;
        private boolean d;

        static b a(a arg0) {
            return arg0.b;
        }

        void a() {
            if(b.a(this.b) == this) {
                int v0 = 0;
                while(true) {
                    if(v0 < c.a(this.a)) {
                        try {
                            c.b(this.a).a(b.d(this.b)[v0]);
                            goto label_13;
                        }
                        catch(IOException ) {
                        label_13:
                            ++v0;
                            continue;
                        }
                    }
                    else {
                        break;
                    }

                    return;
                }

                b.a(this.b, null);
            }
        }

        static boolean[] b(a arg0) {
            return arg0.c;
        }

        public void b() {
            c v0 = this.a;
            __monitor_enter(v0);
            try {
                if(!this.d) {
                    if(b.a(this.b) == this) {
                        c.a(this.a, this, false);
                    }

                    this.d = true;
                    __monitor_exit(v0);
                    return;
                }

                throw new IllegalStateException();
            label_18:
                __monitor_exit(v0);
            }
            catch(Throwable v1) {
                goto label_18;
            }

            throw v1;
        }
    }

    final class b {
        private final String a;
        private final long[] b;
        private final File[] c;
        private final File[] d;
        private boolean e;
        private a f;
        private long g;

        static a a(b arg0) {
            return arg0.f;
        }

        static a a(b arg0, a arg1) {
            arg0.f = arg1;
            return arg1;
        }

        static boolean a(b arg0, boolean arg1) {
            arg0.e = arg1;
            return arg1;
        }

        void a(d arg7) {
            long[] v0 = this.b;
            int v1 = v0.length;
            int v2;
            for(v2 = 0; v2 < v1; ++v2) {
                arg7.i(32).k(v0[v2]);
            }
        }

        static long a(b arg0, long arg1) {
            arg0.g = arg1;
            return arg1;
        }

        static long[] b(b arg0) {
            return arg0.b;
        }

        static File[] c(b arg0) {
            return arg0.c;
        }

        static File[] d(b arg0) {
            return arg0.d;
        }

        static String e(b arg0) {
            return arg0.a;
        }

        static boolean f(b arg0) {
            return arg0.e;
        }
    }

    static final Pattern a;
    private final com.persianswitch.a.a.c.a c;
    private long d;
    private final int e;
    private long f;
    private d g;
    private final LinkedHashMap h;
    private int i;
    private boolean j;
    private boolean k;
    private boolean l;
    private long m;
    private final Executor n;
    private final Runnable o;
    private static final r p;

    static {
        c.b = c.class.desiredAssertionStatus() ^ 1;
        c.a = Pattern.compile("[a-z0-9_-]{1,120}");
        c.p = new com.persianswitch.a.a.c$1();
    }

    static int a(c arg0) {
        return arg0.e;
    }

    private void a(a arg10, boolean arg11) {
        int v2;
        int v1;
        b v0;
        __monitor_enter(this);
        try {
            v0 = a.a(arg10);
            if(b.a(v0) != arg10) {
                goto label_116;
            }

            v1 = 0;
            if((arg11) && !b.f(v0)) {
                v2 = 0;
                while(true) {
                label_9:
                    if(v2 < this.e) {
                        if(!a.b(arg10)[v2]) {
                            goto label_24;
                        }
                        else if(!this.c.b(b.d(v0)[v2])) {
                            arg10.b();
                            goto label_20;
                        }
                        else {
                            goto label_22;
                        }
                    }

                    goto label_34;
                }
            }

            goto label_34;
        }
        catch(Throwable v10) {
            goto label_120;
        }

    label_20:
        __monitor_exit(this);
        return;
    label_22:
        ++v2;
        goto label_9;
        try {
        label_24:
            arg10.b();
            StringBuilder v11 = new StringBuilder();
            v11.append("Newly created entry didn\'t create value for index ");
            v11.append(v2);
            throw new IllegalStateException(v11.toString());
        label_34:
            while(v1 < this.e) {
                File v10_1 = b.d(v0)[v1];
                if(!arg11) {
                    this.c.a(v10_1);
                }
                else if(this.c.b(v10_1)) {
                    File v2_1 = b.c(v0)[v1];
                    this.c.a(v10_1, v2_1);
                    long v3 = b.b(v0)[v1];
                    long v5 = this.c.c(v2_1);
                    b.b(v0)[v1] = v5;
                    this.f = this.f - v3 + v5;
                }

                ++v1;
            }

            ++this.i;
            b.a(v0, null);
            v2 = 10;
            int v3_1 = 32;
            if((b.f(v0) | (((int)arg11))) != 0) {
                b.a(v0, true);
                this.g.b("CLEAN").i(v3_1);
                this.g.b(b.e(v0));
                v0.a(this.g);
                this.g.i(v2);
                if(arg11) {
                    long v10_2 = this.m;
                    this.m = 1 + v10_2;
                    b.a(v0, v10_2);
                }
            }
            else {
                this.h.remove(b.e(v0));
                this.g.b("REMOVE").i(v3_1);
                this.g.b(b.e(v0));
                this.g.i(v2);
            }

            this.g.flush();
            if(this.f > this.d || (this.b())) {
                this.n.execute(this.o);
            }
        }
        catch(Throwable v10) {
            goto label_120;
        }

        __monitor_exit(this);
        return;
        try {
        label_116:
            throw new IllegalStateException();
        }
        catch(Throwable v10) {
        label_120:
            __monitor_exit(this);
            throw v10;
        }
    }

    static void a(c arg0, a arg1, boolean arg2) {
        arg0.a(arg1, arg2);
    }

    private boolean a(b arg7) {
        if(b.a(arg7) != null) {
            b.a(arg7).a();
        }

        int v0;
        for(v0 = 0; v0 < this.e; ++v0) {
            this.c.a(b.c(arg7)[v0]);
            this.f -= b.b(arg7)[v0];
            b.b(arg7)[v0] = 0;
        }

        ++this.i;
        this.g.b("REMOVE").i(32).b(b.e(arg7)).i(10);
        this.h.remove(b.e(arg7));
        if(this.b()) {
            this.n.execute(this.o);
        }

        return 1;
    }

    public boolean a() {
        boolean v0_1;
        __monitor_enter(this);
        try {
            v0_1 = this.k;
        }
        catch(Throwable v0) {
            __monitor_exit(this);
            throw v0;
        }

        __monitor_exit(this);
        return v0_1;
    }

    private boolean b() {
        boolean v0 = this.i < 2000 || this.i < this.h.size() ? false : true;
        return v0;
    }

    static com.persianswitch.a.a.c.a b(c arg0) {
        return arg0.c;
    }

    private void c() {
        __monitor_enter(this);
        try {
            if(this.a()) {
                goto label_5;
            }
        }
        catch(Throwable v0) {
            goto label_10;
        }

        __monitor_exit(this);
        return;
        try {
        label_5:
            throw new IllegalStateException("cache is closed");
        }
        catch(Throwable v0) {
        label_10:
            __monitor_exit(this);
            throw v0;
        }
    }

    public void close() {
        __monitor_enter(this);
        try {
            if(this.j) {
                if(this.k) {
                }
                else {
                    Object[] v0_1 = this.h.values().toArray(new b[this.h.size()]);
                    int v2 = v0_1.length;
                    int v3;
                    for(v3 = 0; v3 < v2; ++v3) {
                        Object v4 = v0_1[v3];
                        if(b.a(((b)v4)) != null) {
                            b.a(((b)v4)).b();
                        }
                    }

                    this.d();
                    this.g.close();
                    this.g = null;
                    this.k = true;
                    goto label_29;
                }
            }

            goto label_31;
        }
        catch(Throwable v0) {
            goto label_35;
        }

    label_29:
        __monitor_exit(this);
        return;
        try {
        label_31:
            this.k = true;
        }
        catch(Throwable v0) {
        label_35:
            __monitor_exit(this);
            throw v0;
        }

        __monitor_exit(this);
    }

    private void d() {
        while(this.f > this.d) {
            this.a(this.h.values().iterator().next());
        }

        this.l = false;
    }

    public void flush() {
        __monitor_enter(this);
        try {
            if(this.j) {
                goto label_5;
            }
        }
        catch(Throwable v0) {
            goto label_12;
        }

        __monitor_exit(this);
        return;
        try {
        label_5:
            this.c();
            this.d();
            this.g.flush();
        }
        catch(Throwable v0) {
        label_12:
            __monitor_exit(this);
            throw v0;
        }

        __monitor_exit(this);
    }
}

