package com.persianswitch.a.a.a;

import com.persianswitch.b.c;
import com.persianswitch.b.r;
import com.persianswitch.b.s;
import com.persianswitch.b.t;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public final class e {
    class com.persianswitch.a.a.a.e$1 {
    }

    final class a implements r {
        private final c c;
        private boolean d;
        private boolean e;

        static {
            a.a = e.class.desiredAssertionStatus() ^ 1;
        }

        a(e arg1) {
            this.b = arg1;
            super();
            this.c = new c();
        }

        static boolean a(a arg0, boolean arg1) {
            arg0.e = arg1;
            return arg1;
        }

        static boolean a(a arg0) {
            return arg0.e;
        }

        private void a(boolean arg12) {
            long v9;
            e v0 = this.b;
            __monitor_enter(v0);
            try {
                e.g(this.b).c();
            }
            catch(Throwable v12) {
                goto label_68;
            }

            try {
                while(true) {
                    if(this.b.b <= 0 && !this.e && !this.d && e.d(this.b) == null) {
                        e.e(this.b);
                        continue;
                    }

                    goto label_19;
                }
            }
            catch(Throwable v12) {
                try {
                    e.g(this.b).b();
                    throw v12;
                label_19:
                    e.g(this.b).b();
                    e.h(this.b);
                    v9 = Math.min(this.b.b, this.c.b());
                    this.b.b -= v9;
                    __monitor_exit(v0);
                }
                catch(Throwable v12) {
                    goto label_68;
                }
            }

            e.g(this.b).c();
            try {
                d v5 = e.a(this.b);
                int v6 = e.b(this.b);
                boolean v7 = !arg12 || v9 != this.c.b() ? false : true;
                v5.a(v6, v7, this.c, v9);
            }
            catch(Throwable v12) {
                e.g(this.b).b();
                throw v12;
            }

            e.g(this.b).b();
            return;
            try {
            label_68:
                __monitor_exit(v0);
            }
            catch(Throwable v12) {
                goto label_68;
            }

            throw v12;
        }

        public t a() {
            return e.g(this.b);
        }

        public void a_(c arg3, long arg4) {
            if(!a.a) {
                if(!Thread.holdsLock(this.b)) {
                }
                else {
                    throw new AssertionError();
                }
            }

            this.c.a_(arg3, arg4);
            while(this.c.b() >= 16384) {
                this.a(false);
            }
        }

        static boolean b(a arg0) {
            return arg0.d;
        }

        public void close() {
            if(!a.a) {
                if(!Thread.holdsLock(this.b)) {
                }
                else {
                    throw new AssertionError();
                }
            }

            e v0 = this.b;
            __monitor_enter(v0);
            try {
                if(this.d) {
                    __monitor_exit(v0);
                    return;
                }

                __monitor_exit(v0);
            }
            catch(Throwable v1) {
                try {
                label_52:
                    __monitor_exit(v0);
                }
                catch(Throwable v1) {
                    goto label_52;
                }

                throw v1;
            }

            if(!this.b.c.e) {
                long v4 = 0;
                if(this.c.b() > v4) {
                    while(true) {
                        if(this.c.b() <= v4) {
                            goto label_38;
                        }

                        this.a(true);
                    }
                }

                e.a(this.b).a(e.b(this.b), true, null, 0);
            }

        label_38:
            e v2 = this.b;
            __monitor_enter(v2);
            try {
                this.d = true;
                __monitor_exit(v2);
            }
            catch(Throwable v0_1) {
                try {
                label_49:
                    __monitor_exit(v2);
                }
                catch(Throwable v0_1) {
                    goto label_49;
                }

                throw v0_1;
            }

            e.a(this.b).c();
            e.f(this.b);
        }

        public void flush() {
            if(!a.a) {
                if(!Thread.holdsLock(this.b)) {
                }
                else {
                    throw new AssertionError();
                }
            }

            e v0 = this.b;
            __monitor_enter(v0);
            try {
                e.h(this.b);
                __monitor_exit(v0);
            }
            catch(Throwable v1) {
                try {
                label_26:
                    __monitor_exit(v0);
                }
                catch(Throwable v1) {
                    goto label_26;
                }

                throw v1;
            }

            while(this.c.b() > 0) {
                this.a(false);
                e.a(this.b).c();
            }
        }
    }

    final class b implements s {
        private final c c;
        private final c d;
        private final long e;
        private boolean f;
        private boolean g;

        static {
            b.a = e.class.desiredAssertionStatus() ^ 1;
        }

        b(e arg1, long arg2, com.persianswitch.a.a.a.e$1 arg4) {
            this(arg1, arg2);
        }

        private b(e arg1, long arg2) {
            this.b = arg1;
            super();
            this.c = new c();
            this.d = new c();
            this.e = arg2;
        }

        static boolean a(b arg0, boolean arg1) {
            arg0.g = arg1;
            return arg1;
        }

        static boolean a(b arg0) {
            return arg0.g;
        }

        void a(com.persianswitch.b.e arg10, long arg11) {
            // Method was not decompiled
        }

        public long a(c arg9, long arg10) {
            long v9_1;
            long v0 = 0;
            if(arg10 >= v0) {
                e v2 = this.b;
                __monitor_enter(v2);
                try {
                    this.b();
                    this.c();
                    if(this.d.b() == v0) {
                        __monitor_exit(v2);
                        return -1;
                    }

                    v9_1 = this.d.a(arg9, Math.min(arg10, this.d.b()));
                    this.b.a += v9_1;
                    int v5 = 65536;
                    if(this.b.a >= (((long)(e.a(this.b).e.f(v5) / 2)))) {
                        e.a(this.b).a(e.b(this.b), this.b.a);
                        this.b.a = v0;
                    }

                    __monitor_exit(v2);
                }
                catch(Throwable v9) {
                    try {
                    label_77:
                        __monitor_exit(v2);
                    }
                    catch(Throwable v9) {
                        goto label_77;
                    }

                    throw v9;
                }

                d v11 = e.a(this.b);
                __monitor_enter(v11);
                try {
                    d v2_1 = e.a(this.b);
                    v2_1.c += v9_1;
                    if(e.a(this.b).c >= (((long)(e.a(this.b).e.f(v5) / 2)))) {
                        e.a(this.b).a(0, e.a(this.b).c);
                        e.a(this.b).c = v0;
                    }

                    __monitor_exit(v11);
                    return v9_1;
                label_74:
                    __monitor_exit(v11);
                }
                catch(Throwable v9) {
                    goto label_74;
                }

                throw v9;
            }

            StringBuilder v0_1 = new StringBuilder();
            v0_1.append("byteCount < 0: ");
            v0_1.append(arg10);
            throw new IllegalArgumentException(v0_1.toString());
        }

        public t a() {
            return e.c(this.b);
        }

        static boolean b(b arg0) {
            return arg0.f;
        }

        private void b() {
            e.c(this.b).c();
            try {
                while(this.d.b() == 0) {
                    if(this.g) {
                        break;
                    }

                    if(this.f) {
                        break;
                    }

                    if(e.d(this.b) != null) {
                        break;
                    }

                    e.e(this.b);
                }
            }
            catch(Throwable v0) {
                e.c(this.b).b();
                throw v0;
            }

            e.c(this.b).b();
        }

        private void c() {
            if(!this.f) {
                if(e.d(this.b) == null) {
                    return;
                }

                throw new p(e.d(this.b));
            }

            throw new IOException("stream closed");
        }

        public void close() {
            e v0 = this.b;
            __monitor_enter(v0);
            try {
                this.f = true;
                this.d.r();
                this.b.notifyAll();
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

            e.f(this.b);
        }
    }

    class com.persianswitch.a.a.a.e$c extends com.persianswitch.b.a {
        com.persianswitch.a.a.a.e$c(e arg1) {
            this.a = arg1;
            super();
        }

        protected IOException a(IOException arg3) {
            SocketTimeoutException v0 = new SocketTimeoutException("timeout");
            if(arg3 != null) {
                v0.initCause(((Throwable)arg3));
            }

            return ((IOException)v0);
        }

        protected void a() {
            this.a.b(com.persianswitch.a.a.a.a.l);
        }

        public void b() {
            if(!this.c_()) {
                return;
            }

            throw this.a(null);
        }
    }

    long a;
    long b;
    final a c;
    private final int e;
    private final d f;
    private final List g;
    private List h;
    private final b i;
    private final com.persianswitch.a.a.a.e$c j;
    private final com.persianswitch.a.a.a.e$c k;
    private com.persianswitch.a.a.a.a l;

    static {
        e.d = e.class.desiredAssertionStatus() ^ 1;
    }

    e(int arg5, d arg6, boolean arg7, boolean arg8, List arg9) {
        super();
        this.a = 0;
        this.j = new com.persianswitch.a.a.a.e$c(this);
        this.k = new com.persianswitch.a.a.a.e$c(this);
        com.persianswitch.a.a.a.a v0 = null;
        this.l = v0;
        if(arg6 != null) {
            if(arg9 != null) {
                this.e = arg5;
                this.f = arg6;
                this.b = ((long)arg6.f.f(65536));
                this.i = new b(this, ((long)arg6.e.f(65536)), ((com.persianswitch.a.a.a.e$1)v0));
                this.c = new a(this);
                b.a(this.i, arg8);
                a.a(this.c, arg7);
                this.g = arg9;
                return;
            }

            throw new NullPointerException("requestHeaders == null");
        }

        throw new NullPointerException("connection == null");
    }

    public void a(com.persianswitch.a.a.a.a arg3) {
        if(!this.d(arg3)) {
            return;
        }

        this.f.b(this.e, arg3);
    }

    void a(long arg4) {
        this.b += arg4;
        if(arg4 > 0) {
            this.notifyAll();
        }
    }

    public int a() {
        return this.e;
    }

    void a(com.persianswitch.b.e arg4, int arg5) {
        if(!e.d) {
            if(!Thread.holdsLock(this)) {
            }
            else {
                throw new AssertionError();
            }
        }

        this.i.a(arg4, ((long)arg5));
    }

    void a(List arg4, g arg5) {
        if(!e.d) {
            if(!Thread.holdsLock(this)) {
            }
            else {
                throw new AssertionError();
            }
        }

        com.persianswitch.a.a.a.a v0 = null;
        boolean v1 = true;
        __monitor_enter(this);
        try {
            if(this.h == null) {
                if(arg5.c()) {
                    v0 = com.persianswitch.a.a.a.a.b;
                }
                else {
                    this.h = arg4;
                    v1 = this.b();
                    this.notifyAll();
                }
            }
            else if(arg5.d()) {
                v0 = com.persianswitch.a.a.a.a.e;
            }
            else {
                ArrayList v5 = new ArrayList();
                ((List)v5).addAll(this.h);
                ((List)v5).addAll(((Collection)arg4));
                this.h = ((List)v5);
            }

            __monitor_exit(this);
            if(v0 == null) {
                goto label_35;
            }
        }
        catch(Throwable v4) {
            try {
            label_41:
                __monitor_exit(this);
            }
            catch(Throwable v4) {
                goto label_41;
            }

            throw v4;
        }

        this.b(v0);
        return;
    label_35:
        if(!v1) {
            this.f.b(this.e);
        }
    }

    static d a(e arg0) {
        return arg0.f;
    }

    public boolean b() {
        __monitor_enter(this);
        try {
            if(this.l == null) {
                goto label_6;
            }
        }
        catch(Throwable v0) {
            goto label_26;
        }

        __monitor_exit(this);
        return 0;
        try {
        label_6:
            if((b.a(this.i)) || (b.b(this.i))) {
                if(!a.a(this.c) && !a.b(this.c)) {
                    goto label_22;
                }

                if(this.h == null) {
                    goto label_22;
                }

                goto label_20;
            }

            goto label_22;
        }
        catch(Throwable v0) {
        label_26:
            __monitor_exit(this);
            throw v0;
        }

    label_20:
        __monitor_exit(this);
        return 0;
    label_22:
        __monitor_exit(this);
        return 1;
    }

    public void b(com.persianswitch.a.a.a.a arg3) {
        if(!this.d(arg3)) {
            return;
        }

        this.f.a(this.e, arg3);
    }

    static int b(e arg0) {
        return arg0.e;
    }

    void c(com.persianswitch.a.a.a.a arg2) {
        __monitor_enter(this);
        try {
            if(this.l == null) {
                this.l = arg2;
                this.notifyAll();
            }
        }
        catch(Throwable v2) {
            __monitor_exit(this);
            throw v2;
        }

        __monitor_exit(this);
    }

    public boolean c() {
        boolean v1 = true;
        boolean v0 = (this.e & 1) == 1 ? true : false;
        if(this.f.b == v0) {
        }
        else {
            v1 = false;
        }

        return v1;
    }

    static com.persianswitch.a.a.a.e$c c(e arg0) {
        return arg0.j;
    }

    static com.persianswitch.a.a.a.a d(e arg0) {
        return arg0.l;
    }

    private boolean d(com.persianswitch.a.a.a.a arg3) {
        if(!e.d) {
            if(!Thread.holdsLock(this)) {
            }
            else {
                throw new AssertionError();
            }
        }

        __monitor_enter(this);
        try {
            if(this.l != null) {
                __monitor_exit(this);
                return 0;
            }

            if((b.a(this.i)) && (a.a(this.c))) {
                __monitor_exit(this);
                return 0;
            }

            this.l = arg3;
            this.notifyAll();
            __monitor_exit(this);
        }
        catch(Throwable v3) {
            try {
            label_31:
                __monitor_exit(this);
            }
            catch(Throwable v3) {
                goto label_31;
            }

            throw v3;
        }

        this.f.b(this.e);
        return 1;
    }

    public List d() {
        List v0_1;
        __monitor_enter(this);
        try {
            this.j.c();
        }
        catch(Throwable v0) {
            goto label_25;
        }

        try {
            while(true) {
                if(this.h == null && this.l == null) {
                    this.l();
                    continue;
                }

                goto label_9;
            }
        }
        catch(Throwable v0) {
            try {
                this.j.b();
                throw v0;
            label_9:
                this.j.b();
                if(this.h == null) {
                    goto label_16;
                }

                v0_1 = this.h;
            }
            catch(Throwable v0) {
                goto label_25;
            }
        }

        __monitor_exit(this);
        return v0_1;
        try {
        label_16:
            throw new p(this.l);
        }
        catch(Throwable v0) {
        label_25:
            __monitor_exit(this);
            throw v0;
        }
    }

    static void e(e arg0) {
        arg0.l();
    }

    public t e() {
        return this.j;
    }

    static void f(e arg0) {
        arg0.j();
    }

    public t f() {
        return this.k;
    }

    static com.persianswitch.a.a.a.e$c g(e arg0) {
        return arg0.k;
    }

    public s g() {
        return this.i;
    }

    static void h(e arg0) {
        arg0.k();
    }

    public r h() {
        __monitor_enter(this);
        try {
            if(this.h == null) {
                if(this.c()) {
                }
                else {
                    throw new IllegalStateException("reply before requesting the sink");
                }
            }

            __monitor_exit(this);
        }
        catch(Throwable v0) {
            try {
            label_14:
                __monitor_exit(this);
            }
            catch(Throwable v0) {
                goto label_14;
            }

            throw v0;
        }

        return this.c;
    }

    void i() {
        if(!e.d) {
            if(!Thread.holdsLock(this)) {
            }
            else {
                throw new AssertionError();
            }
        }

        __monitor_enter(this);
        try {
            b.a(this.i, true);
            boolean v0_1 = this.b();
            this.notifyAll();
            __monitor_exit(this);
            if(v0_1) {
                return;
            }
        }
        catch(Throwable v0) {
            try {
            label_21:
                __monitor_exit(this);
            }
            catch(Throwable v0) {
                goto label_21;
            }

            throw v0;
        }

        this.f.b(this.e);
    }

    private void j() {
        int v0_1;
        if(!e.d) {
            if(!Thread.holdsLock(this)) {
            }
            else {
                throw new AssertionError();
            }
        }

        __monitor_enter(this);
        try {
            if((b.a(this.i)) || !b.b(this.i)) {
            label_23:
                v0_1 = 0;
            }
            else {
                if(!a.a(this.c) && !a.b(this.c)) {
                    goto label_23;
                }

                v0_1 = 1;
            }

            boolean v1 = this.b();
            __monitor_exit(this);
            if(v0_1 == 0) {
                goto label_30;
            }
        }
        catch(Throwable v0) {
            try {
            label_36:
                __monitor_exit(this);
            }
            catch(Throwable v0) {
                goto label_36;
            }

            throw v0;
        }

        this.a(com.persianswitch.a.a.a.a.l);
        return;
    label_30:
        if(!v1) {
            this.f.b(this.e);
        }
    }

    private void k() {
        if(!a.b(this.c)) {
            if(!a.a(this.c)) {
                if(this.l == null) {
                    return;
                }

                throw new p(this.l);
            }

            throw new IOException("stream finished");
        }

        throw new IOException("stream closed");
    }

    private void l() {
        try {
            this.wait();
            return;
        }
        catch(InterruptedException ) {
            throw new InterruptedIOException();
        }
    }
}

