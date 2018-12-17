package okhttp3.internal.e;

import e.d;
import e.e;
import e.f;
import java.io.Closeable;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.Socket;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public final class g implements Closeable {
    public class a {
        Socket a;
        String b;
        e c;
        d d;
        b e;
        l f;
        boolean g;
        int h;

        public a(boolean arg2) {
            super();
            this.e = b.f;
            this.f = l.a;
            this.g = arg2;
        }

        public g a() {
            return new g(this);
        }

        public a a(int arg1) {
            this.h = arg1;
            return this;
        }

        public a a(b arg1) {
            this.e = arg1;
            return this;
        }

        public a a(Socket arg1, String arg2, e arg3, d arg4) {
            this.a = arg1;
            this.b = arg2;
            this.c = arg3;
            this.d = arg4;
            return this;
        }
    }

    public abstract class b {
        final class okhttp3.internal.e.g$b$1 extends b {
            okhttp3.internal.e.g$b$1() {
                super();
            }

            public void a(i arg2) {
                arg2.a(okhttp3.internal.e.b.e);
            }
        }

        public static final b f;

        static {
            b.f = new okhttp3.internal.e.g$b$1();
        }

        public b() {
            super();
        }

        public void a(g arg1) {
        }

        public abstract void a(i arg1);
    }

    final class c extends okhttp3.internal.b {
        final boolean a;
        final int c;
        final int d;

        c(g arg4, boolean arg5, int arg6, int arg7) {
            this.e = arg4;
            super("OkHttp %s ping %08x%08x", new Object[]{arg4.d, Integer.valueOf(arg6), Integer.valueOf(arg7)});
            this.a = arg5;
            this.c = arg6;
            this.d = arg7;
        }

        public void c() {
            this.e.a(this.a, this.c, this.d);
        }
    }

    class okhttp3.internal.e.g$d extends okhttp3.internal.b implements okhttp3.internal.e.h$b {
        final h a;

        okhttp3.internal.e.g$d(g arg4, h arg5) {
            this.c = arg4;
            super("OkHttp %s", new Object[]{arg4.d});
            this.a = arg5;
        }

        private void a(m arg7) {
            try {
                g.b(this.c).execute(new okhttp3.internal.b("OkHttp %s ACK Settings", new Object[]{this.c.d}, arg7) {
                    public void c() {
                        try {
                            this.c.c.o.a(this.a);
                        }
                        catch(IOException ) {
                            g.a(this.c.c);
                        }
                    }
                });
                return;
            }
            catch(RejectedExecutionException ) {
                return;
            }
        }

        public void a() {
        }

        public void a(int arg1, int arg2, int arg3, boolean arg4) {
        }

        public void a(int arg1, int arg2, List arg3) {
            this.c.a(arg2, arg3);
        }

        public void a(int arg5, long arg6) {
            if(arg5 == 0) {
                g v0 = this.c;
                __monitor_enter(v0);
                try {
                    this.c.j += arg6;
                    this.c.notifyAll();
                    __monitor_exit(v0);
                    return;
                label_13:
                    __monitor_exit(v0);
                }
                catch(Throwable v5) {
                    goto label_13;
                }

                throw v5;
            }
            else {
                i v5_1 = this.c.a(arg5);
                if(v5_1 != null) {
                    __monitor_enter(v5_1);
                    try {
                        v5_1.a(arg6);
                        __monitor_exit(v5_1);
                        return;
                    label_23:
                        __monitor_exit(v5_1);
                    }
                    catch(Throwable v6) {
                        goto label_23;
                    }

                    throw v6;
                }
            }
        }

        public void a(int arg2, okhttp3.internal.e.b arg3) {
            if(this.c.c(arg2)) {
                this.c.c(arg2, arg3);
                return;
            }

            i v2 = this.c.b(arg2);
            if(v2 != null) {
                v2.c(arg3);
            }
        }

        public void a(int arg4, okhttp3.internal.e.b arg5, f arg6) {
            Object[] v6;
            arg6.g();
            g v5 = this.c;
            __monitor_enter(v5);
            try {
                v6 = this.c.c.values().toArray(new i[this.c.c.size()]);
                this.c.g = true;
                __monitor_exit(v5);
            }
            catch(Throwable v4) {
                try {
                label_32:
                    __monitor_exit(v5);
                }
                catch(Throwable v4) {
                    goto label_32;
                }

                throw v4;
            }

            int v5_1 = v6.length;
            int v0;
            for(v0 = 0; v0 < v5_1; ++v0) {
                Object v1 = v6[v0];
                if(((i)v1).a() > arg4 && (((i)v1).c())) {
                    ((i)v1).c(okhttp3.internal.e.b.e);
                    this.c.b(((i)v1).a());
                }
            }
        }

        public void a(boolean arg4, int arg5, int arg6) {
            if(arg4) {
                g v4 = this.c;
                __monitor_enter(v4);
                try {
                    g.a(this.c, false);
                    this.c.notifyAll();
                    __monitor_exit(v4);
                    return;
                label_11:
                    __monitor_exit(v4);
                }
                catch(Throwable v5) {
                    goto label_11;
                }

                throw v5;
            }
            else {
                try {
                    g.b(this.c).execute(new c(this.c, true, arg5, arg6));
                    return;
                }
                catch(RejectedExecutionException ) {
                    return;
                }
            }
        }

        public void a(boolean arg10, int arg11, int arg12, List arg13) {
            i v0;
            if(this.c.c(arg11)) {
                this.c.a(arg11, arg13, arg10);
                return;
            }

            g v12 = this.c;
            __monitor_enter(v12);
            try {
                v0 = this.c.a(arg11);
                if(v0 == null) {
                    if(this.c.g) {
                        __monitor_exit(v12);
                        return;
                    }

                    if(arg11 <= this.c.e) {
                        __monitor_exit(v12);
                        return;
                    }

                    int v2 = 2;
                    if(arg11 % 2 == this.c.f % v2) {
                        __monitor_exit(v12);
                        return;
                    }

                    v0 = new i(arg11, this.c, false, arg10, arg13);
                    this.c.e = arg11;
                    this.c.c.put(Integer.valueOf(arg11), v0);
                    ExecutorService v10_1 = g.e();
                    Object[] v2_1 = new Object[v2];
                    v2_1[0] = this.c.d;
                    v2_1[1] = Integer.valueOf(arg11);
                    v10_1.execute(new okhttp3.internal.b("OkHttp %s stream %d", v2_1, v0) {
                        public void c() {
                            try {
                                this.c.c.b.a(this.a);
                                return;
                            }
                            catch(IOException v0) {
                                okhttp3.internal.g.f v1 = okhttp3.internal.g.f.c();
                                v1.a(4, "Http2Connection.Listener failure for " + this.c.c.d, ((Throwable)v0));
                                try {
                                    this.a.a(okhttp3.internal.e.b.b);
                                    return;
                                }
                                catch(IOException ) {
                                    return;
                                }
                            }
                        }
                    });
                    __monitor_exit(v12);
                    return;
                }

                __monitor_exit(v12);
            }
            catch(Throwable v10) {
                try {
                label_65:
                    __monitor_exit(v12);
                }
                catch(Throwable v10) {
                    goto label_65;
                }

                throw v10;
            }

            v0.a(arg13);
            if(arg10) {
                v0.i();
            }
        }

        public void a(boolean arg2, int arg3, e arg4, int arg5) {
            if(this.c.c(arg3)) {
                this.c.a(arg3, arg4, arg5, arg2);
                return;
            }

            i v0 = this.c.a(arg3);
            if(v0 == null) {
                this.c.a(arg3, okhttp3.internal.e.b.b);
                arg4.h(((long)arg5));
                return;
            }

            v0.a(arg4, arg5);
            if(arg2) {
                v0.i();
            }
        }

        public void a(boolean arg11, m arg12) {
            int v9;
            long v11_2;
            Object[] v5;
            g v0 = this.c;
            __monitor_enter(v0);
            try {
                int v1 = this.c.l.d();
                if(arg11) {
                    this.c.l.a();
                }

                this.c.l.a(arg12);
                this.a(arg12);
                int v11_1 = this.c.l.d();
                long v2 = 0;
                v5 = null;
                if(v11_1 == -1 || v11_1 == v1) {
                    v11_2 = v2;
                }
                else {
                    v11_2 = ((long)(v11_1 - v1));
                    if(!this.c.m) {
                        this.c.a(v11_2);
                        this.c.m = true;
                    }

                    if(this.c.c.isEmpty()) {
                        goto label_46;
                    }

                    v5 = this.c.c.values().toArray(new i[this.c.c.size()]);
                }

            label_46:
                ExecutorService v1_1 = g.e();
                Object[] v4 = new Object[1];
                v9 = 0;
                v4[0] = this.c.d;
                v1_1.execute(new okhttp3.internal.b("OkHttp %s settings", v4) {
                    public void c() {
                        this.a.c.b.a(this.a.c);
                    }
                });
                __monitor_exit(v0);
                if(v5 == null) {
                    return;
                }
            }
            catch(Throwable v11) {
                try {
                label_72:
                    __monitor_exit(v0);
                }
                catch(Throwable v11) {
                    goto label_72;
                }

                throw v11;
            }

            if(v11_2 != v2) {
                int v0_1 = v5.length;
                while(true) {
                    if(v9 < v0_1) {
                        Object v1_2 = v5[v9];
                        __monitor_enter(v1_2);
                        try {
                            ((i)v1_2).a(v11_2);
                            __monitor_exit(v1_2);
                            ++v9;
                            continue;
                        label_68:
                            __monitor_exit(v1_2);
                            break;
                        }
                        catch(Throwable v11) {
                            goto label_68;
                        }
                    }

                    return;
                }

                throw v11;
            }
        }

        protected void c() {
            g v1_1;
            okhttp3.internal.e.b v2_1;
            okhttp3.internal.e.b v0 = okhttp3.internal.e.b.c;
            okhttp3.internal.e.b v1 = okhttp3.internal.e.b.c;
            try {
                this.a.a(((okhttp3.internal.e.h$b)this));
                while(this.a.a(false, ((okhttp3.internal.e.h$b)this))) {
                }

                v2_1 = okhttp3.internal.e.b.a;
            }
            catch(IOException ) {
                goto label_17;
            }
            catch(Throwable v2) {
                goto label_16;
            }

            try {
                v0 = okhttp3.internal.e.b.f;
                goto label_11;
            }
            catch(Throwable v0_1) {
            }
            catch(IOException ) {
                v0 = v2_1;
                goto label_17;
                try {
                label_11:
                    v1_1 = this.c;
                    goto label_20;
                }
                catch(IOException ) {
                    goto label_21;
                }

                try {
                label_17:
                    v2_1 = okhttp3.internal.e.b.b;
                }
                catch(Throwable v2) {
                    goto label_16;
                }

                try {
                    v0 = okhttp3.internal.e.b.b;
                }
                catch(Throwable v0_1) {
                    okhttp3.internal.e.b v4 = v2_1;
                    v2 = v0_1;
                    v0 = v4;
                    goto label_28;
                }
            }

            try {
                v1_1 = this.c;
            label_20:
                v1_1.a(v2_1, v0);
                goto label_21;
            }
            catch(IOException ) {
            label_21:
                okhttp3.internal.c.a(this.a);
                return;
            }

        label_16:
            try {
            label_28:
                this.c.a(v0, v1);
                goto label_30;
            }
            catch(IOException ) {
            label_30:
                okhttp3.internal.c.a(this.a);
                throw v2;
            }
        }
    }

    final boolean a;
    final b b;
    final Map c;
    final String d;
    int e;
    int f;
    boolean g;
    final l h;
    long i;
    long j;
    m k;
    final m l;
    boolean m;
    final Socket n;
    final j o;
    final okhttp3.internal.e.g$d p;
    final Set q;
    private static final ExecutorService s;
    private final ScheduledExecutorService t;
    private final ExecutorService u;
    private boolean v;

    static {
        g.r = g.class.desiredAssertionStatus() ^ 1;
        g.s = new ThreadPoolExecutor(0, 2147483647, 60, TimeUnit.SECONDS, new SynchronousQueue(), okhttp3.internal.c.a("OkHttp Http2Connection", true));
    }

    g(a arg22) {
        g v0 = this;
        a v1 = arg22;
        super();
        v0.c = new LinkedHashMap();
        v0.i = 0;
        v0.k = new m();
        v0.l = new m();
        v0.m = false;
        v0.q = new LinkedHashSet();
        v0.h = v1.f;
        v0.a = v1.g;
        v0.b = v1.e;
        int v4 = 2;
        int v3 = v1.g ? 1 : 2;
        v0.f = v3;
        if(v1.g) {
            v0.f += v4;
        }

        v4 = 7;
        if(v1.g) {
            v0.k.a(v4, 16777216);
        }

        v0.d = v1.b;
        v0.t = new ScheduledThreadPoolExecutor(1, okhttp3.internal.c.a(okhttp3.internal.c.a("OkHttp %s Writer", new Object[]{v0.d}), false));
        if(v1.h != 0) {
            v0.t.scheduleAtFixedRate(new c(v0, false, 0, 0), ((long)v1.h), ((long)v1.h), TimeUnit.MILLISECONDS);
        }

        v0.u = new ThreadPoolExecutor(0, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue(), okhttp3.internal.c.a(okhttp3.internal.c.a("OkHttp %s Push Observer", new Object[]{v0.d}), true));
        v0.l.a(v4, 65535);
        v0.l.a(5, 16384);
        v0.j = ((long)v0.l.d());
        v0.n = v1.a;
        v0.o = new j(v1.d, v0.a);
        v0.p = new okhttp3.internal.e.g$d(v0, new h(v1.c, v0.a));
    }

    public int a() {
        int v0_1;
        __monitor_enter(this);
        try {
            v0_1 = this.l.c(2147483647);
        }
        catch(Throwable v0) {
            __monitor_exit(this);
            throw v0;
        }

        __monitor_exit(this);
        return v0_1;
    }

    static void a(g arg0) {
        arg0.f();
    }

    static boolean a(g arg0, boolean arg1) {
        arg0.v = arg1;
        return arg1;
    }

    public void a(okhttp3.internal.e.b arg5) {
        int v1;
        j v0 = this.o;
        __monitor_enter(v0);
        try {
            __monitor_enter(this);
        }
        catch(Throwable v5) {
            goto label_21;
        }

        try {
            if(!this.g) {
                goto label_9;
            }

            __monitor_exit(this);
        }
        catch(Throwable v5) {
            goto label_18;
        }

        try {
            __monitor_exit(v0);
            return;
        }
        catch(Throwable v5) {
            goto label_21;
        }

        try {
        label_9:
            this.g = true;
            v1 = this.e;
            __monitor_exit(this);
            goto label_12;
        }
        catch(Throwable v5) {
            try {
            label_18:
                __monitor_exit(this);
            }
            catch(Throwable v5) {
                goto label_18;
            }

            try {
                throw v5;
            label_12:
                this.o.a(v1, arg5, okhttp3.internal.c.a);
                __monitor_exit(v0);
                return;
            label_21:
                __monitor_exit(v0);
            }
            catch(Throwable v5) {
                goto label_21;
            }
        }

        throw v5;
    }

    void a(okhttp3.internal.e.b arg5, okhttp3.internal.e.b arg6) {
        if(!g.r) {
            if(!Thread.holdsLock(this)) {
            }
            else {
                throw new AssertionError();
            }
        }

        Object[] v0 = null;
        try {
            this.a(arg5);
            v5 = ((IOException)v0);
        }
        catch(IOException v5) {
        }

        __monitor_enter(this);
        try {
            if(!this.c.isEmpty()) {
                v0 = this.c.values().toArray(new i[this.c.size()]);
                this.c.clear();
            }

            __monitor_exit(this);
            if(v0 == null) {
                goto label_38;
            }
        }
        catch(Throwable v5_1) {
            try {
            label_56:
                __monitor_exit(this);
            }
            catch(Throwable v5_1) {
                goto label_56;
            }

            throw v5_1;
        }

        int v1 = v0.length;
        int v2;
        for(v2 = 0; v2 < v1; ++v2) {
            Object v3 = v0[v2];
            try {
                ((i)v3).a(arg6);
            }
            catch(IOException v3_1) {
                if(v5 == null) {
                    goto label_36;
                }

                v5 = v3_1;
            }

        label_36:
        }

        try {
        label_38:
            this.o.close();
        }
        catch(IOException v6) {
            if(v5 != null) {
                goto label_44;
            }

            v5 = v6;
        }

        try {
        label_44:
            this.n.close();
        }
        catch(IOException v5) {
        }

        this.t.shutdown();
        this.u.shutdown();
        if(v5 == null) {
            return;
        }

        throw v5;
    }

    i a(int arg2) {
        __monitor_enter(this);
        try {
            __monitor_exit(this);
            return this.c.get(Integer.valueOf(arg2));
        }
        catch(Throwable v2) {
            __monitor_exit(this);
            throw v2;
        }
    }

    public i a(List arg2, boolean arg3) {
        return this.b(0, arg2, arg3);
    }

    void a(int arg10, long arg11) {
        try {
            this.t.execute(new okhttp3.internal.b("OkHttp Window Update %s stream %d", new Object[]{this.d, Integer.valueOf(arg10)}, arg10, arg11) {
                public void c() {
                    try {
                        this.d.o.a(this.a, this.c);
                    }
                    catch(IOException ) {
                        g.a(this.d);
                    }
                }
            });
            return;
        }
        catch(RejectedExecutionException ) {
            return;
        }
    }

    void a(int arg10, e arg11, int arg12, boolean arg13) {
        e.c v5 = new e.c();
        long v0 = ((long)arg12);
        arg11.a(v0);
        arg11.a(v5, v0);
        if(v5.b() == v0) {
            this.u.execute(new okhttp3.internal.b("OkHttp %s Push Data[%s]", new Object[]{this.d, Integer.valueOf(arg10)}, arg10, v5, arg12, arg13) {
                public void c() {
                    g v0_1;
                    try {
                        boolean v0 = this.f.h.a(this.a, this.c, this.d, this.e);
                        if(v0) {
                            this.f.o.a(this.a, okhttp3.internal.e.b.f);
                        }

                        if((v0) || (this.e)) {
                            v0_1 = this.f;
                            __monitor_enter(v0_1);
                            goto label_18;
                        }
                    }
                    catch(IOException ) {
                    }

                    return;
                    try {
                    label_18:
                        this.f.q.remove(Integer.valueOf(this.a));
                        __monitor_exit(v0_1);
                        return;
                    label_26:
                        __monitor_exit(v0_1);
                    }
                    catch(Throwable v1) {
                        goto label_26;
                    }

                    try {
                        throw v1;
                    }
                    catch(IOException ) {
                        return;
                    }
                }
            });
            return;
        }

        StringBuilder v11 = new StringBuilder();
        v11.append(v5.b());
        v11.append(" != ");
        v11.append(arg12);
        throw new IOException(v11.toString());
    }

    void a(int arg9, List arg10) {
        __monitor_enter(this);
        try {
            if(this.q.contains(Integer.valueOf(arg9))) {
                this.a(arg9, okhttp3.internal.e.b.b);
                __monitor_exit(this);
                return;
            }

            this.q.add(Integer.valueOf(arg9));
            __monitor_exit(this);
        }
        catch(Throwable v9) {
            try {
            label_33:
                __monitor_exit(this);
            }
            catch(Throwable v9) {
                goto label_33;
            }

            throw v9;
        }

        try {
            this.u.execute(new okhttp3.internal.b("OkHttp %s Push Request[%s]", new Object[]{this.d, Integer.valueOf(arg9)}, arg9, arg10) {
                public void c() {
                    g v0;
                    if(this.d.h.a(this.a, this.c)) {
                        try {
                            this.d.o.a(this.a, okhttp3.internal.e.b.f);
                            v0 = this.d;
                            __monitor_enter(v0);
                        }
                        catch(IOException ) {
                            return;
                        }

                        try {
                            this.d.q.remove(Integer.valueOf(this.a));
                            __monitor_exit(v0);
                            return;
                        label_21:
                            __monitor_exit(v0);
                        }
                        catch(Throwable v1) {
                            goto label_21;
                        }

                        try {
                            throw v1;
                        }
                        catch(IOException ) {
                            return;
                        }
                    }
                }
            });
            return;
        }
        catch(RejectedExecutionException ) {
            return;
        }
    }

    void a(int arg9, okhttp3.internal.e.b arg10) {
        try {
            this.t.execute(new okhttp3.internal.b("OkHttp %s stream %d", new Object[]{this.d, Integer.valueOf(arg9)}, arg9, arg10) {
                public void c() {
                    try {
                        this.d.b(this.a, this.c);
                    }
                    catch(IOException ) {
                        g.a(this.d);
                    }
                }
            });
            return;
        }
        catch(RejectedExecutionException ) {
            return;
        }
    }

    void a(int arg10, List arg11, boolean arg12) {
        try {
            this.u.execute(new okhttp3.internal.b("OkHttp %s Push Headers[%s]", new Object[]{this.d, Integer.valueOf(arg10)}, arg10, arg11, arg12) {
                public void c() {
                    g v0_1;
                    boolean v0 = this.e.h.a(this.a, this.c, this.d);
                    if(v0) {
                        try {
                            this.e.o.a(this.a, okhttp3.internal.e.b.f);
                        label_12:
                            if((v0) || (this.d)) {
                                v0_1 = this.e;
                                __monitor_enter(v0_1);
                                goto label_17;
                            }
                        }
                        catch(IOException ) {
                        }

                        return;
                    }

                    goto label_12;
                    try {
                    label_17:
                        this.e.q.remove(Integer.valueOf(this.a));
                        __monitor_exit(v0_1);
                        return;
                    label_25:
                        __monitor_exit(v0_1);
                    }
                    catch(Throwable v1) {
                        goto label_25;
                    }

                    try {
                        throw v1;
                    }
                    catch(IOException ) {
                        return;
                    }
                }
            });
            return;
        }
        catch(RejectedExecutionException ) {
            return;
        }
    }

    public void a(int arg9, boolean arg10, e.c arg11, long arg12) {
        long v6;
        int v2;
        long v0 = 0;
        if(Long.compare(arg12, v0) == 0) {
            this.o.a(arg10, arg9, arg11, 0);
            return;
        }

        while(true) {
            if(arg12 <= v0) {
                return;
            }

            __monitor_enter(this);
            try {
                while(true) {
                    if(this.j > v0) {
                        goto label_21;
                    }

                    if(!this.c.containsKey(Integer.valueOf(arg9))) {
                        break;
                    }

                    this.wait();
                }

                throw new IOException("stream closed");
            }
            catch(InterruptedException ) {
                try {
                    throw new InterruptedIOException();
                label_21:
                    v2 = Math.min(((int)Math.min(arg12, this.j)), this.o.c());
                    v6 = ((long)v2);
                    this.j -= v6;
                    __monitor_exit(this);
                }
                catch(Throwable v9) {
                    break;
                }
            }

            arg12 -= v6;
            j v4 = this.o;
            boolean v5 = !arg10 || arg12 != v0 ? false : true;
            v4.a(v5, arg9, arg11, v2);
        }

    label_43:
        try {
            __monitor_exit(this);
        }
        catch(Throwable v9) {
            goto label_43;
        }

        throw v9;
    }

    void a(long arg4) {
        this.j += arg4;
        if(arg4 > 0) {
            this.notifyAll();
        }
    }

    void a(boolean arg6) {
        if(arg6) {
            this.o.a();
            this.o.b(this.k);
            int v6 = this.k.d();
            int v0 = 65535;
            if(v6 != v0) {
                this.o.a(0, ((long)(v6 - v0)));
            }
        }

        new Thread(this.p).start();
    }

    void a(boolean arg3, int arg4, int arg5) {
        if(!arg3) {
            __monitor_enter(this);
            try {
                boolean v0 = this.v;
                this.v = true;
                __monitor_exit(this);
                if(!v0) {
                    goto label_12;
                }
            }
            catch(Throwable v3) {
                try {
                label_10:
                    __monitor_exit(this);
                }
                catch(Throwable v3) {
                    goto label_10;
                }

                throw v3;
            }

            this.f();
            return;
        }

        try {
        label_12:
            this.o.a(arg3, arg4, arg5);
        }
        catch(IOException ) {
            this.f();
        }
    }

    static ScheduledExecutorService b(g arg0) {
        return arg0.t;
    }

    private i b(int arg11, List arg12, boolean arg13) {
        int v13;
        i v9;
        int v8;
        int v6 = (((int)arg13)) ^ 1;
        j v7 = this.o;
        __monitor_enter(v7);
        try {
            __monitor_enter(this);
        }
        catch(Throwable v11) {
            goto label_64;
        }

        try {
            if(this.f > 1073741823) {
                this.a(okhttp3.internal.e.b.e);
            }

            if(this.g) {
                goto label_57;
            }

            v8 = this.f;
            this.f += 2;
            v9 = new i(v8, this, v6, false, arg12);
            if(arg13) {
                long v2 = 0;
                if(this.j == v2) {
                    goto label_33;
                }
                else if(v9.b == v2) {
                    goto label_33;
                }
                else {
                    v13 = 0;
                }
            }
            else {
            label_33:
                v13 = 1;
            }

            if(v9.b()) {
                this.c.put(Integer.valueOf(v8), v9);
            }

            __monitor_exit(this);
            if(arg11 == 0) {
            }
            else {
                goto label_44;
            }
        }
        catch(Throwable v11) {
            goto label_61;
        }

        try {
            this.o.a(((boolean)v6), v8, arg11, arg12);
            goto label_48;
        label_44:
            if(!this.a) {
                this.o.a(arg11, v8, arg12);
            }
            else {
                goto label_53;
            }

        label_48:
            __monitor_exit(v7);
            if(v13 != 0) {
                goto label_50;
            }

            return v9;
        }
        catch(Throwable v11) {
            goto label_64;
        }

    label_50:
        this.o.b();
        return v9;
        try {
        label_53:
            throw new IllegalArgumentException("client streams shouldn\'t have associated stream IDs");
        }
        catch(Throwable v11) {
            goto label_64;
        }

        try {
        label_57:
            throw new okhttp3.internal.e.a();
        label_61:
            __monitor_exit(this);
        }
        catch(Throwable v11) {
            goto label_61;
        }

        try {
            throw v11;
        label_64:
            __monitor_exit(v7);
        }
        catch(Throwable v11) {
            goto label_64;
        }

        throw v11;
    }

    void b(int arg2, okhttp3.internal.e.b arg3) {
        this.o.a(arg2, arg3);
    }

    i b(int arg2) {
        Object v2_1;
        __monitor_enter(this);
        try {
            v2_1 = this.c.remove(Integer.valueOf(arg2));
            this.notifyAll();
        }
        catch(Throwable v2) {
            __monitor_exit(this);
            throw v2;
        }

        __monitor_exit(this);
        return ((i)v2_1);
    }

    public void b() {
        this.o.b();
    }

    public void c() {
        this.a(true);
    }

    void c(int arg9, okhttp3.internal.e.b arg10) {
        this.u.execute(new okhttp3.internal.b("OkHttp %s Push Reset[%s]", new Object[]{this.d, Integer.valueOf(arg9)}, arg9, arg10) {
            public void c() {
                this.d.h.a(this.a, this.c);
                g v0 = this.d;
                __monitor_enter(v0);
                try {
                    this.d.q.remove(Integer.valueOf(this.a));
                    __monitor_exit(v0);
                    return;
                label_15:
                    __monitor_exit(v0);
                }
                catch(Throwable v1) {
                    goto label_15;
                }

                throw v1;
            }
        });
    }

    boolean c(int arg2) {
        boolean v0 = true;
        if(arg2 == 0 || (arg2 & 1) != 0) {
            v0 = false;
        }
        else {
        }

        return v0;
    }

    public void close() {
        this.a(okhttp3.internal.e.b.a, okhttp3.internal.e.b.f);
    }

    public boolean d() {
        boolean v0_1;
        __monitor_enter(this);
        try {
            v0_1 = this.g;
        }
        catch(Throwable v0) {
            __monitor_exit(this);
            throw v0;
        }

        __monitor_exit(this);
        return v0_1;
    }

    static ExecutorService e() {
        return g.s;
    }

    private void f() {
        try {
            this.a(okhttp3.internal.e.b.b, okhttp3.internal.e.b.b);
            return;
        }
        catch(IOException ) {
            return;
        }
    }
}

