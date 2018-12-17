package com.persianswitch.a.a.a;

import com.persianswitch.a.a.h;
import com.persianswitch.a.a.j;
import com.persianswitch.a.v;
import com.persianswitch.b.e;
import com.persianswitch.b.f;
import java.io.Closeable;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public final class d implements Closeable {
    public class a {
        private Socket a;
        private String b;
        private e c;
        private com.persianswitch.b.d d;
        private b e;
        private v f;
        private m g;
        private boolean h;

        public a(boolean arg2) {
            super();
            this.e = b.a;
            this.f = v.c;
            this.g = m.a;
            this.h = arg2;
        }

        static v a(a arg0) {
            return arg0.f;
        }

        public a a(b arg1) {
            this.e = arg1;
            return this;
        }

        public a a(v arg1) {
            this.f = arg1;
            return this;
        }

        public a a(Socket arg1, String arg2, e arg3, com.persianswitch.b.d arg4) {
            this.a = arg1;
            this.b = arg2;
            this.c = arg3;
            this.d = arg4;
            return this;
        }

        public d a() {
            return new d(this, null);
        }

        static m b(a arg0) {
            return arg0.g;
        }

        static boolean c(a arg0) {
            return arg0.h;
        }

        static b d(a arg0) {
            return arg0.e;
        }

        static String e(a arg0) {
            return arg0.b;
        }

        static Socket f(a arg0) {
            return arg0.a;
        }

        static com.persianswitch.b.d g(a arg0) {
            return arg0.d;
        }

        static e h(a arg0) {
            return arg0.c;
        }
    }

    public abstract class b {
        final class com.persianswitch.a.a.a.d$b$1 extends b {
            com.persianswitch.a.a.a.d$b$1() {
                super();
            }

            public void a(com.persianswitch.a.a.a.e arg2) {
                arg2.a(com.persianswitch.a.a.a.a.k);
            }
        }

        public static final b a;

        static {
            b.a = new com.persianswitch.a.a.a.d$b$1();
        }

        public b() {
            super();
        }

        public void a(d arg1) {
        }

        public abstract void a(com.persianswitch.a.a.a.e arg1);
    }

    class c extends h implements com.persianswitch.a.a.a.b$a {
        final com.persianswitch.a.a.a.b a;

        c(d arg1, com.persianswitch.a.a.a.b arg2, com.persianswitch.a.a.a.d$1 arg3) {
            this(arg1, arg2);
        }

        private c(d arg4, com.persianswitch.a.a.a.b arg5) {
            this.c = arg4;
            super("OkHttp %s", new Object[]{d.a(arg4)});
            this.a = arg5;
        }

        private void a(n arg7) {
            d.e().execute(new h("OkHttp %s ACK Settings", new Object[]{d.a(this.c)}, arg7) {
                public void b() {
                    try {
                        this.c.c.i.a(this.a);
                        return;
                    }
                    catch(IOException ) {
                        return;
                    }
                }
            });
        }

        public void a() {
        }

        public void a(int arg1, int arg2, int arg3, boolean arg4) {
        }

        public void a(int arg1, int arg2, List arg3) {
            d.a(this.c, arg2, arg3);
        }

        public void a(int arg5, long arg6) {
            if(arg5 == 0) {
                d v0 = this.c;
                __monitor_enter(v0);
                try {
                    this.c.d += arg6;
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
                com.persianswitch.a.a.a.e v5_1 = this.c.a(arg5);
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

        public void a(int arg2, com.persianswitch.a.a.a.a arg3) {
            if(d.a(this.c, arg2)) {
                d.a(this.c, arg2, arg3);
                return;
            }

            com.persianswitch.a.a.a.e v2 = this.c.b(arg2);
            if(v2 != null) {
                v2.c(arg3);
            }
        }

        public void a(int arg4, com.persianswitch.a.a.a.a arg5, f arg6) {
            Object[] v6;
            arg6.e();
            d v5 = this.c;
            __monitor_enter(v5);
            try {
                v6 = d.e(this.c).values().toArray(new com.persianswitch.a.a.a.e[d.e(this.c).size()]);
                d.b(this.c, true);
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
                if(((com.persianswitch.a.a.a.e)v1).a() > arg4 && (((com.persianswitch.a.a.a.e)v1).c())) {
                    ((com.persianswitch.a.a.a.e)v1).c(com.persianswitch.a.a.a.a.k);
                    this.c.b(((com.persianswitch.a.a.a.e)v1).a());
                }
            }
        }

        public void a(boolean arg3, int arg4, int arg5) {
            if(arg3) {
                l v3 = d.c(this.c, arg4);
                if(v3 != null) {
                    v3.b();
                }
            }
            else {
                d.b(this.c, true, arg4, arg5, null);
            }
        }

        public void a(boolean arg2, int arg3, e arg4, int arg5) {
            if(d.a(this.c, arg3)) {
                d.a(this.c, arg3, arg4, arg5, arg2);
                return;
            }

            com.persianswitch.a.a.a.e v0 = this.c.a(arg3);
            if(v0 == null) {
                this.c.a(arg3, com.persianswitch.a.a.a.a.c);
                arg4.g(((long)arg5));
                return;
            }

            v0.a(arg4, arg5);
            if(arg2) {
                v0.i();
            }
        }

        public void a(boolean arg11, n arg12) {
            int v9;
            long v11_2;
            Object[] v5;
            d v0 = this.c;
            __monitor_enter(v0);
            try {
                int v2 = 65536;
                int v1 = this.c.f.f(v2);
                if(arg11) {
                    this.c.f.a();
                }

                this.c.f.a(arg12);
                if(this.c.a() == v.d) {
                    this.a(arg12);
                }

                int v11_1 = this.c.f.f(v2);
                long v2_1 = 0;
                v5 = null;
                if(v11_1 == -1 || v11_1 == v1) {
                    v11_2 = v2_1;
                }
                else {
                    v11_2 = ((long)(v11_1 - v1));
                    if(!d.g(this.c)) {
                        this.c.a(v11_2);
                        d.a(this.c, true);
                    }

                    if(d.e(this.c).isEmpty()) {
                        goto label_51;
                    }

                    v5 = d.e(this.c).values().toArray(new com.persianswitch.a.a.a.e[d.e(this.c).size()]);
                }

            label_51:
                ExecutorService v1_1 = d.e();
                Object[] v4 = new Object[1];
                v9 = 0;
                v4[0] = d.a(this.c);
                v1_1.execute(new h("OkHttp %s settings", v4) {
                    public void b() {
                        d.f(this.a.c).a(this.a.c);
                    }
                });
                __monitor_exit(v0);
                if(v5 == null) {
                    return;
                }
            }
            catch(Throwable v11) {
                try {
                label_77:
                    __monitor_exit(v0);
                }
                catch(Throwable v11) {
                    goto label_77;
                }

                throw v11;
            }

            if(v11_2 != v2_1) {
                int v0_1 = v5.length;
                while(true) {
                    if(v9 < v0_1) {
                        Object v1_2 = v5[v9];
                        __monitor_enter(v1_2);
                        try {
                            ((com.persianswitch.a.a.a.e)v1_2).a(v11_2);
                            __monitor_exit(v1_2);
                            ++v9;
                            continue;
                        label_73:
                            __monitor_exit(v1_2);
                            break;
                        }
                        catch(Throwable v11) {
                            goto label_73;
                        }
                    }

                    return;
                }

                throw v11;
            }
        }

        public void a(boolean arg9, boolean arg10, int arg11, int arg12, List arg13, g arg14) {
            com.persianswitch.a.a.a.e v0;
            if(d.a(this.c, arg11)) {
                d.a(this.c, arg11, arg13, arg10);
                return;
            }

            d v12 = this.c;
            __monitor_enter(v12);
            try {
                if(d.b(this.c)) {
                    __monitor_exit(v12);
                    return;
                }

                v0 = this.c.a(arg11);
                if(v0 == null) {
                    if(arg14.a()) {
                        this.c.a(arg11, com.persianswitch.a.a.a.a.c);
                        __monitor_exit(v12);
                        return;
                    }

                    if(arg11 <= d.c(this.c)) {
                        __monitor_exit(v12);
                        return;
                    }

                    int v1 = 2;
                    if(arg11 % 2 == d.d(this.c) % v1) {
                        __monitor_exit(v12);
                        return;
                    }

                    com.persianswitch.a.a.a.e v14 = new com.persianswitch.a.a.a.e(arg11, this.c, arg9, arg10, arg13);
                    d.b(this.c, arg11);
                    d.e(this.c).put(Integer.valueOf(arg11), v14);
                    ExecutorService v9_1 = d.e();
                    Object[] v0_1 = new Object[v1];
                    v0_1[0] = d.a(this.c);
                    v0_1[1] = Integer.valueOf(arg11);
                    v9_1.execute(new h("OkHttp %s stream %d", v0_1, v14) {
                        public void b() {
                            try {
                                d.f(this.c.c).a(this.a);
                                return;
                            }
                            catch(IOException v0) {
                                j v1 = j.c();
                                v1.a(4, "FramedConnection.Listener failure for " + d.a(this.c.c), ((Throwable)v0));
                                try {
                                    this.a.a(com.persianswitch.a.a.a.a.b);
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
            catch(Throwable v9) {
                try {
                label_79:
                    __monitor_exit(v12);
                }
                catch(Throwable v9) {
                    goto label_79;
                }

                throw v9;
            }

            if(arg14.b()) {
                v0.b(com.persianswitch.a.a.a.a.b);
                this.c.b(arg11);
                return;
            }

            v0.a(arg13, arg14);
            if(arg10) {
                v0.i();
            }
        }

        protected void b() {
            d v1_1;
            com.persianswitch.a.a.a.a v2_1;
            com.persianswitch.a.a.a.a v0 = com.persianswitch.a.a.a.a.g;
            com.persianswitch.a.a.a.a v1 = com.persianswitch.a.a.a.a.g;
            try {
                if(!this.c.b) {
                    this.a.a();
                }

                while(this.a.a(((com.persianswitch.a.a.a.b$a)this))) {
                }

                v2_1 = com.persianswitch.a.a.a.a.a;
            }
            catch(Throwable v2) {
                goto label_18;
            }
            catch(IOException ) {
                goto label_19;
            }

            try {
                v0 = com.persianswitch.a.a.a.a.l;
                goto label_13;
            }
            catch(Throwable v0_1) {
            }
            catch(IOException ) {
                v0 = v2_1;
                goto label_19;
                try {
                label_13:
                    v1_1 = this.c;
                    goto label_22;
                }
                catch(IOException ) {
                    goto label_23;
                }

                try {
                label_19:
                    v2_1 = com.persianswitch.a.a.a.a.b;
                }
                catch(Throwable v2) {
                    goto label_18;
                }

                try {
                    v0 = com.persianswitch.a.a.a.a.b;
                }
                catch(Throwable v0_1) {
                    com.persianswitch.a.a.a.a v4 = v2_1;
                    v2 = v0_1;
                    v0 = v4;
                    goto label_30;
                }
            }

            try {
                v1_1 = this.c;
            label_22:
                d.a(v1_1, v2_1, v0);
                goto label_23;
            }
            catch(IOException ) {
            label_23:
                com.persianswitch.a.a.l.a(this.a);
                return;
            }

        label_18:
            try {
            label_30:
                d.a(this.c, v0, v1);
                goto label_32;
            }
            catch(IOException ) {
            label_32:
                com.persianswitch.a.a.l.a(this.a);
                throw v2;
            }
        }
    }

    final v a;
    final boolean b;
    long c;
    long d;
    n e;
    final n f;
    final q g;
    final Socket h;
    final com.persianswitch.a.a.a.c i;
    final c j;
    private static final ExecutorService l;
    private final b m;
    private final Map n;
    private final String o;
    private int p;
    private int q;
    private boolean r;
    private final ExecutorService s;
    private Map t;
    private final m u;
    private int v;
    private boolean w;
    private final Set x;

    static {
        d.k = d.class.desiredAssertionStatus() ^ 1;
        d.l = new ThreadPoolExecutor(0, 2147483647, 60, TimeUnit.SECONDS, new SynchronousQueue(), com.persianswitch.a.a.l.a("OkHttp FramedConnection", true));
    }

    private d(a arg15) {
        super();
        this.n = new HashMap();
        this.c = 0;
        this.e = new n();
        this.f = new n();
        this.w = false;
        this.x = new LinkedHashSet();
        this.a = a.a(arg15);
        this.u = a.b(arg15);
        this.b = a.c(arg15);
        this.m = a.d(arg15);
        int v2 = 2;
        int v1 = a.c(arg15) ? 1 : 2;
        this.q = v1;
        if((a.c(arg15)) && this.a == v.d) {
            this.q += v2;
        }

        if(a.c(arg15)) {
            v2 = 1;
        }

        this.v = v2;
        v2 = 7;
        if(a.c(arg15)) {
            this.e.a(v2, 0, 16777216);
        }

        this.o = a.e(arg15);
        ExecutorService v5 = null;
        if(this.a == v.d) {
            this.g = new i();
            this.s = new ThreadPoolExecutor(0, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue(), com.persianswitch.a.a.l.a(com.persianswitch.a.a.l.a("OkHttp %s Push Observer", new Object[]{this.o}), true));
            this.f.a(v2, 0, 65535);
            this.f.a(5, 0, 16384);
        }
        else if(this.a == v.c) {
            this.g = new o();
            this.s = v5;
        }
        else {
            goto label_112;
        }

        this.d = ((long)this.f.f(65536));
        this.h = a.f(arg15);
        this.i = this.g.a(a.g(arg15), this.b);
        this.j = new c(this, this.g.a(a.h(arg15), this.b), ((com.persianswitch.a.a.a.d$1)v5));
        return;
    label_112:
        throw new AssertionError(this.a);
    }

    d(a arg1, com.persianswitch.a.a.a.d$1 arg2) {
        this(arg1);
    }

    private com.persianswitch.a.a.a.e a(int arg12, List arg13, boolean arg14, boolean arg15) {
        com.persianswitch.a.a.a.e v10;
        int v9;
        int v6 = (((int)arg14)) ^ 1;
        int v7 = 1;
        int v15 = (((int)arg15)) ^ 1;
        com.persianswitch.a.a.a.c v8 = this.i;
        __monitor_enter(v8);
        try {
            __monitor_enter(this);
        }
        catch(Throwable v12) {
            goto label_65;
        }

        try {
            if(this.r) {
                goto label_57;
            }

            v9 = this.q;
            this.q += 2;
            v10 = new com.persianswitch.a.a.a.e(v9, this, v6, v15, arg13);
            if(arg14) {
                long v2 = 0;
                if(this.d != v2) {
                    if(v10.b == v2) {
                    }
                    else {
                        v7 = 0;
                    }
                }
            }

            if(v10.b()) {
                this.n.put(Integer.valueOf(v9), v10);
            }

            __monitor_exit(this);
            if(arg12 == 0) {
            }
            else {
                goto label_44;
            }
        }
        catch(Throwable v12) {
            goto label_62;
        }

        try {
            this.i.a(v6, v15, v9, arg12, arg13);
            goto label_48;
        label_44:
            if(!this.b) {
                this.i.a(arg12, v9, arg13);
            }
            else {
                goto label_53;
            }

        label_48:
            __monitor_exit(v8);
            if(v7 != 0) {
                goto label_50;
            }

            return v10;
        }
        catch(Throwable v12) {
            goto label_65;
        }

    label_50:
        this.i.b();
        return v10;
        try {
        label_53:
            throw new IllegalArgumentException("client streams shouldn\'t have associated stream IDs");
        }
        catch(Throwable v12) {
            goto label_65;
        }

        try {
        label_57:
            throw new IOException("shutdown");
        label_62:
            __monitor_exit(this);
        }
        catch(Throwable v12) {
            goto label_62;
        }

        try {
            throw v12;
        label_65:
            __monitor_exit(v8);
        }
        catch(Throwable v12) {
            goto label_65;
        }

        throw v12;
    }

    static String a(d arg0) {
        return arg0.o;
    }

    private void a(int arg10, e arg11, int arg12, boolean arg13) {
        com.persianswitch.b.c v5 = new com.persianswitch.b.c();
        long v0 = ((long)arg12);
        arg11.a(v0);
        arg11.a(v5, v0);
        if(v5.b() == v0) {
            this.s.execute(new h("OkHttp %s Push Data[%s]", new Object[]{this.o, Integer.valueOf(arg10)}, arg10, v5, arg12, arg13) {
                public void b() {
                    d v0_1;
                    try {
                        boolean v0 = d.h(this.f).a(this.a, this.c, this.d, this.e);
                        if(v0) {
                            this.f.i.a(this.a, com.persianswitch.a.a.a.a.l);
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
                        d.i(this.f).remove(Integer.valueOf(this.a));
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

    private void a(int arg9, List arg10) {
        __monitor_enter(this);
        try {
            if(this.x.contains(Integer.valueOf(arg9))) {
                this.a(arg9, com.persianswitch.a.a.a.a.b);
                __monitor_exit(this);
                return;
            }

            this.x.add(Integer.valueOf(arg9));
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

        this.s.execute(new h("OkHttp %s Push Request[%s]", new Object[]{this.o, Integer.valueOf(arg9)}, arg9, arg10) {
            public void b() {
                d v0;
                if(d.h(this.d).a(this.a, this.c)) {
                    try {
                        this.d.i.a(this.a, com.persianswitch.a.a.a.a.l);
                        v0 = this.d;
                        __monitor_enter(v0);
                    }
                    catch(IOException ) {
                        return;
                    }

                    try {
                        d.i(this.d).remove(Integer.valueOf(this.a));
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
    }

    void a(int arg9, com.persianswitch.a.a.a.a arg10) {
        d.l.submit(new h("OkHttp %s stream %d", new Object[]{this.o, Integer.valueOf(arg9)}, arg9, arg10) {
            public void b() {
                try {
                    this.d.b(this.a, this.c);
                    return;
                }
                catch(IOException ) {
                    return;
                }
            }
        });
    }

    private void a(int arg10, List arg11, boolean arg12) {
        this.s.execute(new h("OkHttp %s Push Headers[%s]", new Object[]{this.o, Integer.valueOf(arg10)}, arg10, arg11, arg12) {
            public void b() {
                d v0_1;
                boolean v0 = d.h(this.e).a(this.a, this.c, this.d);
                if(v0) {
                    try {
                        this.e.i.a(this.a, com.persianswitch.a.a.a.a.l);
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
                    d.i(this.e).remove(Integer.valueOf(this.a));
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
    }

    private void a(com.persianswitch.a.a.a.a arg7, com.persianswitch.a.a.a.a arg8) {
        int v2_1;
        Object[] v0_1;
        Object[] v1;
        if(!d.k) {
            if(!Thread.holdsLock(this)) {
            }
            else {
                throw new AssertionError();
            }
        }

        Map v0 = null;
        try {
            this.a(arg7);
            v7 = ((IOException)v0);
        }
        catch(IOException v7) {
        }

        __monitor_enter(this);
        try {
            if(!this.n.isEmpty()) {
                v1 = this.n.values().toArray(new com.persianswitch.a.a.a.e[this.n.size()]);
                this.n.clear();
            }
            else {
                v1 = ((Object[])v0);
            }

            if(this.t != null) {
                Object[] v2 = this.t.values().toArray(new l[this.t.size()]);
                this.t = v0;
                v0_1 = v2;
            }

            __monitor_exit(this);
            v2_1 = 0;
            if(v1 == null) {
                goto label_53;
            }
        }
        catch(Throwable v7_1) {
            try {
            label_74:
                __monitor_exit(this);
            }
            catch(Throwable v7_1) {
                goto label_74;
            }

            throw v7_1;
        }

        int v3 = v1.length;
        IOException v4 = v7;
        int v7_2;
        for(v7_2 = 0; v7_2 < v3; ++v7_2) {
            Object v5 = v1[v7_2];
            try {
                ((com.persianswitch.a.a.a.e)v5).a(arg8);
            }
            catch(IOException v5_1) {
                if(v4 == null) {
                    goto label_50;
                }

                v4 = v5_1;
            }

        label_50:
        }

        v7 = v4;
    label_53:
        if(v0_1 != null) {
            int v8 = v0_1.length;
            while(v2_1 < v8) {
                v0_1[v2_1].c();
                ++v2_1;
            }
        }

        try {
            this.i.close();
        }
        catch(IOException v8_1) {
            if(v7 != null) {
                goto label_66;
            }

            v7 = v8_1;
        }

        try {
        label_66:
            this.h.close();
        }
        catch(IOException v7) {
        }

        if(v7 == null) {
            return;
        }

        throw v7;
    }

    public void a(com.persianswitch.a.a.a.a arg5) {
        int v1;
        com.persianswitch.a.a.a.c v0 = this.i;
        __monitor_enter(v0);
        try {
            __monitor_enter(this);
        }
        catch(Throwable v5) {
            goto label_21;
        }

        try {
            if(!this.r) {
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
            this.r = true;
            v1 = this.p;
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
                this.i.a(v1, arg5, com.persianswitch.a.a.l.a);
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

    static void a(d arg0, int arg1, com.persianswitch.a.a.a.a arg2) {
        arg0.c(arg1, arg2);
    }

    static void a(d arg0, int arg1, e arg2, int arg3, boolean arg4) {
        arg0.a(arg1, arg2, arg3, arg4);
    }

    static void a(d arg0, int arg1, List arg2) {
        arg0.a(arg1, arg2);
    }

    static void a(d arg0, int arg1, List arg2, boolean arg3) {
        arg0.a(arg1, arg2, arg3);
    }

    static void a(d arg0, com.persianswitch.a.a.a.a arg1, com.persianswitch.a.a.a.a arg2) {
        arg0.a(arg1, arg2);
    }

    static void a(d arg0, boolean arg1, int arg2, int arg3, l arg4) {
        arg0.b(arg1, arg2, arg3, arg4);
    }

    private void a(boolean arg11, int arg12, int arg13, l arg14) {
        d.l.execute(new h("OkHttp %s ping %08x%08x", new Object[]{this.o, Integer.valueOf(arg12), Integer.valueOf(arg13)}, arg11, arg12, arg13, arg14) {
            public void b() {
                try {
                    d.a(this.f, this.a, this.c, this.d, this.e);
                    return;
                }
                catch(IOException ) {
                    return;
                }
            }
        });
    }

    static boolean a(d arg0, int arg1) {
        return arg0.d(arg1);
    }

    static boolean a(d arg0, boolean arg1) {
        arg0.w = arg1;
        return arg1;
    }

    com.persianswitch.a.a.a.e a(int arg2) {
        __monitor_enter(this);
        try {
            __monitor_exit(this);
            return this.n.get(Integer.valueOf(arg2));
        }
        catch(Throwable v2) {
            __monitor_exit(this);
            throw v2;
        }
    }

    public com.persianswitch.a.a.a.e a(List arg2, boolean arg3, boolean arg4) {
        return this.a(0, arg2, arg3, arg4);
    }

    public v a() {
        return this.a;
    }

    void a(int arg10, long arg11) {
        d.l.execute(new h("OkHttp Window Update %s stream %d", new Object[]{this.o, Integer.valueOf(arg10)}, arg10, arg11) {
            public void b() {
                try {
                    this.d.i.a(this.a, this.c);
                    return;
                }
                catch(IOException ) {
                    return;
                }
            }
        });
    }

    public void a(int arg9, boolean arg10, com.persianswitch.b.c arg11, long arg12) {
        long v6;
        int v2;
        long v0 = 0;
        if(Long.compare(arg12, v0) == 0) {
            this.i.a(arg10, arg9, arg11, 0);
            return;
        }

        while(true) {
            if(arg12 <= v0) {
                return;
            }

            __monitor_enter(this);
            try {
                while(true) {
                    if(this.d > v0) {
                        goto label_21;
                    }

                    if(!this.n.containsKey(Integer.valueOf(arg9))) {
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
                    v2 = Math.min(((int)Math.min(arg12, this.d)), this.i.c());
                    v6 = ((long)v2);
                    this.d -= v6;
                    __monitor_exit(this);
                }
                catch(Throwable v9) {
                    break;
                }
            }

            arg12 -= v6;
            com.persianswitch.a.a.a.c v4 = this.i;
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
        this.d += arg4;
        if(arg4 > 0) {
            this.notifyAll();
        }
    }

    void a(boolean arg6) {
        if(arg6) {
            this.i.a();
            this.i.b(this.e);
            int v0 = 65536;
            int v6 = this.e.f(v0);
            if(v6 != v0) {
                this.i.a(0, ((long)(v6 - v0)));
            }
        }

        new Thread(this.j).start();
    }

    private void b(boolean arg2, int arg3, int arg4, l arg5) {
        com.persianswitch.a.a.a.c v0 = this.i;
        __monitor_enter(v0);
        if(arg5 != null) {
            try {
                arg5.a();
            label_7:
                this.i.a(arg2, arg3, arg4);
                __monitor_exit(v0);
                return;
            label_11:
                __monitor_exit(v0);
                goto label_12;
            }
            catch(Throwable v2) {
                goto label_11;
            }
        }

        goto label_7;
    label_12:
        throw v2;
    }

    static int b(d arg0, int arg1) {
        arg0.p = arg1;
        return arg1;
    }

    static void b(d arg0, boolean arg1, int arg2, int arg3, l arg4) {
        arg0.a(arg1, arg2, arg3, arg4);
    }

    static boolean b(d arg0) {
        return arg0.r;
    }

    static boolean b(d arg0, boolean arg1) {
        arg0.r = arg1;
        return arg1;
    }

    void b(int arg2, com.persianswitch.a.a.a.a arg3) {
        this.i.a(arg2, arg3);
    }

    public int b() {
        int v0_1;
        __monitor_enter(this);
        try {
            v0_1 = this.f.d(2147483647);
        }
        catch(Throwable v0) {
            __monitor_exit(this);
            throw v0;
        }

        __monitor_exit(this);
        return v0_1;
    }

    com.persianswitch.a.a.a.e b(int arg2) {
        Object v2_1;
        __monitor_enter(this);
        try {
            v2_1 = this.n.remove(Integer.valueOf(arg2));
            this.notifyAll();
        }
        catch(Throwable v2) {
            __monitor_exit(this);
            throw v2;
        }

        __monitor_exit(this);
        return ((com.persianswitch.a.a.a.e)v2_1);
    }

    private void c(int arg9, com.persianswitch.a.a.a.a arg10) {
        this.s.execute(new h("OkHttp %s Push Reset[%s]", new Object[]{this.o, Integer.valueOf(arg9)}, arg9, arg10) {
            public void b() {
                d.h(this.d).a(this.a, this.c);
                d v0 = this.d;
                __monitor_enter(v0);
                try {
                    d.i(this.d).remove(Integer.valueOf(this.a));
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

    static int c(d arg0) {
        return arg0.p;
    }

    private l c(int arg2) {
        Object v2_1;
        __monitor_enter(this);
        try {
            if(this.t != null) {
                v2_1 = this.t.remove(Integer.valueOf(arg2));
            }
            else {
                goto label_7;
            }

            goto label_8;
        }
        catch(Throwable v2) {
            __monitor_exit(this);
            throw v2;
        }

    label_7:
        l v2_2 = null;
    label_8:
        __monitor_exit(this);
        return ((l)v2_1);
    }

    static l c(d arg0, int arg1) {
        return arg0.c(arg1);
    }

    public void c() {
        this.i.b();
    }

    public void close() {
        this.a(com.persianswitch.a.a.a.a.a, com.persianswitch.a.a.a.a.l);
    }

    private boolean d(int arg4) {
        boolean v2 = true;
        if(this.a != v.d || arg4 == 0 || (arg4 & 1) != 0) {
            v2 = false;
        }
        else {
        }

        return v2;
    }

    static int d(d arg0) {
        return arg0.q;
    }

    public void d() {
        this.a(true);
    }

    static Map e(d arg0) {
        return arg0.n;
    }

    static ExecutorService e() {
        return d.l;
    }

    static b f(d arg0) {
        return arg0.m;
    }

    static boolean g(d arg0) {
        return arg0.w;
    }

    static m h(d arg0) {
        return arg0.u;
    }

    static Set i(d arg0) {
        return arg0.x;
    }
}

