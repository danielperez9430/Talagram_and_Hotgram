package okhttp3.internal.a;

import java.io.Closeable;
import java.io.File;
import java.io.Flushable;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.concurrent.Executor;
import java.util.regex.Pattern;

public final class d implements Closeable, Flushable {
    public final class a {
        final b a;
        final boolean[] b;
        private boolean d;

        void a() {
            if(this.a.f == this) {
                int v0 = 0;
                while(true) {
                    if(v0 < this.c.c) {
                        try {
                            this.c.b.a(this.a.d[v0]);
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

                this.a.f = null;
            }
        }

        public void b() {
            d v0 = this.c;
            __monitor_enter(v0);
            try {
                if(!this.d) {
                    if(this.a.f == this) {
                        this.c.a(this, false);
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
        final String a;
        final long[] b;
        final File[] c;
        final File[] d;
        boolean e;
        a f;
        long g;

        void a(e.d arg7) {
            long[] v0 = this.b;
            int v1 = v0.length;
            int v2;
            for(v2 = 0; v2 < v1; ++v2) {
                arg7.i(32).l(v0[v2]);
            }
        }
    }

    static final Pattern a;
    final okhttp3.internal.f.a b;
    final int c;
    e.d d;
    final LinkedHashMap e;
    int f;
    boolean g;
    boolean h;
    boolean i;
    private long k;
    private long l;
    private long m;
    private final Executor n;
    private final Runnable o;

    static {
        d.j = d.class.desiredAssertionStatus() ^ 1;
        d.a = Pattern.compile("[a-z0-9_-]{1,120}");
    }

    void a(a arg10, boolean arg11) {
        int v2;
        int v1;
        b v0;
        __monitor_enter(this);
        try {
            v0 = arg10.a;
            if(v0.f != arg10) {
                goto label_116;
            }

            v1 = 0;
            if((arg11) && !v0.e) {
                v2 = 0;
                while(true) {
                label_9:
                    if(v2 < this.c) {
                        if(!arg10.b[v2]) {
                            goto label_24;
                        }
                        else if(!this.b.b(v0.d[v2])) {
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
            while(v1 < this.c) {
                File v10_1 = v0.d[v1];
                if(!arg11) {
                    this.b.a(v10_1);
                }
                else if(this.b.b(v10_1)) {
                    File v2_1 = v0.c[v1];
                    this.b.a(v10_1, v2_1);
                    long v3 = v0.b[v1];
                    long v5 = this.b.c(v2_1);
                    v0.b[v1] = v5;
                    this.l = this.l - v3 + v5;
                }

                ++v1;
            }

            ++this.f;
            v0.f = null;
            v2 = 10;
            int v3_1 = 32;
            if((v0.e | (((int)arg11))) != 0) {
                v0.e = true;
                this.d.b("CLEAN").i(v3_1);
                this.d.b(v0.a);
                v0.a(this.d);
                this.d.i(v2);
                if(arg11) {
                    long v10_2 = this.m;
                    this.m = 1 + v10_2;
                    v0.g = v10_2;
                }
            }
            else {
                this.e.remove(v0.a);
                this.d.b("REMOVE").i(v3_1);
                this.d.b(v0.a);
                this.d.i(v2);
            }

            this.d.flush();
            if(this.l > this.k || (this.a())) {
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

    boolean a() {
        boolean v0 = this.f < 2000 || this.f < this.e.size() ? false : true;
        return v0;
    }

    boolean a(b arg7) {
        if(arg7.f != null) {
            arg7.f.a();
        }

        int v0;
        for(v0 = 0; v0 < this.c; ++v0) {
            this.b.a(arg7.c[v0]);
            this.l -= arg7.b[v0];
            arg7.b[v0] = 0;
        }

        ++this.f;
        this.d.b("REMOVE").i(32).b(arg7.a).i(10);
        this.e.remove(arg7.a);
        if(this.a()) {
            this.n.execute(this.o);
        }

        return 1;
    }

    public boolean b() {
        boolean v0_1;
        __monitor_enter(this);
        try {
            v0_1 = this.h;
        }
        catch(Throwable v0) {
            __monitor_exit(this);
            throw v0;
        }

        __monitor_exit(this);
        return v0_1;
    }

    void c() {
        while(this.l > this.k) {
            this.a(this.e.values().iterator().next());
        }

        this.i = false;
    }

    public void close() {
        __monitor_enter(this);
        try {
            if(this.g) {
                if(this.h) {
                }
                else {
                    Object[] v0_1 = this.e.values().toArray(new b[this.e.size()]);
                    int v2 = v0_1.length;
                    int v3;
                    for(v3 = 0; v3 < v2; ++v3) {
                        Object v4 = v0_1[v3];
                        if(((b)v4).f != null) {
                            ((b)v4).f.b();
                        }
                    }

                    this.c();
                    this.d.close();
                    this.d = null;
                    this.h = true;
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
            this.h = true;
        }
        catch(Throwable v0) {
        label_35:
            __monitor_exit(this);
            throw v0;
        }

        __monitor_exit(this);
    }

    private void d() {
        __monitor_enter(this);
        try {
            if(this.b()) {
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

    public void flush() {
        __monitor_enter(this);
        try {
            if(this.g) {
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
            this.d();
            this.c();
            this.d.flush();
        }
        catch(Throwable v0) {
        label_12:
            __monitor_exit(this);
            throw v0;
        }

        __monitor_exit(this);
    }
}

