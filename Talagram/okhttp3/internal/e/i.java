package okhttp3.internal.e;

import e.c;
import e.e;
import e.r;
import e.s;
import e.t;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public final class i {
    final class a implements r {
        boolean a;
        boolean b;
        private final c e;

        static {
            a.c = i.class.desiredAssertionStatus() ^ 1;
        }

        a(i arg1) {
            this.d = arg1;
            super();
            this.e = new c();
        }

        private void a(boolean arg12) {
            long v9;
            i v0 = this.d;
            __monitor_enter(v0);
            try {
                this.d.g.c();
            }
            catch(Throwable v12) {
                goto label_68;
            }

            try {
                while(true) {
                    if(this.d.b <= 0 && !this.b && !this.a && this.d.h == null) {
                        this.d.l();
                        continue;
                    }

                    goto label_19;
                }
            }
            catch(Throwable v12) {
                try {
                    this.d.g.b();
                    throw v12;
                label_19:
                    this.d.g.b();
                    this.d.k();
                    v9 = Math.min(this.d.b, this.e.b());
                    this.d.b -= v9;
                    __monitor_exit(v0);
                }
                catch(Throwable v12) {
                    goto label_68;
                }
            }

            this.d.g.c();
            try {
                g v5 = this.d.d;
                int v6 = this.d.c;
                boolean v7 = !arg12 || v9 != this.e.b() ? false : true;
                v5.a(v6, v7, this.e, v9);
            }
            catch(Throwable v12) {
                this.d.g.b();
                throw v12;
            }

            this.d.g.b();
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
            return this.d.g;
        }

        public void a_(c arg3, long arg4) {
            if(!a.c) {
                if(!Thread.holdsLock(this.d)) {
                }
                else {
                    throw new AssertionError();
                }
            }

            this.e.a_(arg3, arg4);
            while(this.e.b() >= 16384) {
                this.a(false);
            }
        }

        public void close() {
            if(!a.c) {
                if(!Thread.holdsLock(this.d)) {
                }
                else {
                    throw new AssertionError();
                }
            }

            i v0 = this.d;
            __monitor_enter(v0);
            try {
                if(this.a) {
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

            if(!this.d.e.b) {
                long v4 = 0;
                if(this.e.b() > v4) {
                    while(true) {
                        if(this.e.b() <= v4) {
                            goto label_38;
                        }

                        this.a(true);
                    }
                }

                this.d.d.a(this.d.c, true, null, 0);
            }

        label_38:
            i v2 = this.d;
            __monitor_enter(v2);
            try {
                this.a = true;
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

            this.d.d.b();
            this.d.j();
        }

        public void flush() {
            if(!a.c) {
                if(!Thread.holdsLock(this.d)) {
                }
                else {
                    throw new AssertionError();
                }
            }

            i v0 = this.d;
            __monitor_enter(v0);
            try {
                this.d.k();
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

            while(this.e.b() > 0) {
                this.a(false);
                this.d.d.b();
            }
        }
    }

    final class b implements s {
        boolean a;
        boolean b;
        private final c e;
        private final c f;
        private final long g;

        static {
            b.c = i.class.desiredAssertionStatus() ^ 1;
        }

        b(i arg1, long arg2) {
            this.d = arg1;
            super();
            this.e = new c();
            this.f = new c();
            this.g = arg2;
        }

        void a(e arg10, long arg11) {
            // Method was not decompiled
        }

        public long a(c arg8, long arg9) {
            long v8_1;
            long v0 = 0;
            if(arg9 >= v0) {
                i v2 = this.d;
                __monitor_enter(v2);
                try {
                    this.b();
                    this.c();
                    if(this.f.b() == v0) {
                        __monitor_exit(v2);
                        return -1;
                    }

                    v8_1 = this.f.a(arg8, Math.min(arg9, this.f.b()));
                    this.d.a += v8_1;
                    if(this.d.a >= (((long)(this.d.d.k.d() / 2)))) {
                        this.d.d.a(this.d.c, this.d.a);
                        this.d.a = v0;
                    }

                    __monitor_exit(v2);
                }
                catch(Throwable v8) {
                    try {
                    label_76:
                        __monitor_exit(v2);
                    }
                    catch(Throwable v8) {
                        goto label_76;
                    }

                    throw v8;
                }

                g v10 = this.d.d;
                __monitor_enter(v10);
                try {
                    this.d.d.i += v8_1;
                    if(this.d.d.i >= (((long)(this.d.d.k.d() / 2)))) {
                        this.d.d.a(0, this.d.d.i);
                        this.d.d.i = v0;
                    }

                    __monitor_exit(v10);
                    return v8_1;
                label_73:
                    __monitor_exit(v10);
                }
                catch(Throwable v8) {
                    goto label_73;
                }

                throw v8;
            }

            StringBuilder v0_1 = new StringBuilder();
            v0_1.append("byteCount < 0: ");
            v0_1.append(arg9);
            throw new IllegalArgumentException(v0_1.toString());
        }

        public t a() {
            return this.d.f;
        }

        private void b() {
            this.d.f.c();
            try {
                while(this.f.b() == 0) {
                    if(this.b) {
                        break;
                    }

                    if(this.a) {
                        break;
                    }

                    if(this.d.h != null) {
                        break;
                    }

                    this.d.l();
                }
            }
            catch(Throwable v0) {
                this.d.f.b();
                throw v0;
            }

            this.d.f.b();
        }

        private void c() {
            if(!this.a) {
                if(this.d.h == null) {
                    return;
                }

                throw new n(this.d.h);
            }

            throw new IOException("stream closed");
        }

        public void close() {
            i v0 = this.d;
            __monitor_enter(v0);
            try {
                this.a = true;
                this.f.s();
                this.d.notifyAll();
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

            this.d.j();
        }
    }

    class okhttp3.internal.e.i$c extends e.a {
        okhttp3.internal.e.i$c(i arg1) {
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
            this.a.b(okhttp3.internal.e.b.f);
        }

        public void b() {
            if(!this.j_()) {
                return;
            }

            throw this.a(null);
        }
    }

    long a;
    long b;
    final int c;
    final g d;
    final a e;
    final okhttp3.internal.e.i$c f;
    final okhttp3.internal.e.i$c g;
    okhttp3.internal.e.b h;
    private final List j;
    private List k;
    private boolean l;
    private final b m;

    static {
        i.i = i.class.desiredAssertionStatus() ^ 1;
    }

    i(int arg3, g arg4, boolean arg5, boolean arg6, List arg7) {
        super();
        this.a = 0;
        this.f = new okhttp3.internal.e.i$c(this);
        this.g = new okhttp3.internal.e.i$c(this);
        this.h = null;
        if(arg4 != null) {
            if(arg7 != null) {
                this.c = arg3;
                this.d = arg4;
                this.b = ((long)arg4.l.d());
                this.m = new b(this, ((long)arg4.k.d()));
                this.e = new a(this);
                this.m.b = arg6;
                this.e.b = arg5;
                this.j = arg7;
                return;
            }

            throw new NullPointerException("requestHeaders == null");
        }

        throw new NullPointerException("connection == null");
    }

    public void a(okhttp3.internal.e.b arg3) {
        if(!this.d(arg3)) {
            return;
        }

        this.d.b(this.c, arg3);
    }

    void a(long arg4) {
        this.b += arg4;
        if(arg4 > 0) {
            this.notifyAll();
        }
    }

    public int a() {
        return this.c;
    }

    void a(List arg4) {
        if(!i.i) {
            if(!Thread.holdsLock(this)) {
            }
            else {
                throw new AssertionError();
            }
        }

        __monitor_enter(this);
        boolean v0 = true;
        try {
            this.l = true;
            if(this.k == null) {
                this.k = arg4;
                v0 = this.b();
                this.notifyAll();
            }
            else {
                ArrayList v1 = new ArrayList();
                ((List)v1).addAll(this.k);
                ((List)v1).add(null);
                ((List)v1).addAll(((Collection)arg4));
                this.k = ((List)v1);
            }

            __monitor_exit(this);
            if(v0) {
                return;
            }
        }
        catch(Throwable v4) {
            try {
            label_32:
                __monitor_exit(this);
            }
            catch(Throwable v4) {
                goto label_32;
            }

            throw v4;
        }

        this.d.b(this.c);
    }

    void a(e arg4, int arg5) {
        if(!i.i) {
            if(!Thread.holdsLock(this)) {
            }
            else {
                throw new AssertionError();
            }
        }

        this.m.a(arg4, ((long)arg5));
    }

    public boolean b() {
        __monitor_enter(this);
        try {
            if(this.h == null) {
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
            if((this.m.b) || (this.m.a)) {
                if(!this.e.b && !this.e.a) {
                    goto label_22;
                }

                if(!this.l) {
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

    public void b(okhttp3.internal.e.b arg3) {
        if(!this.d(arg3)) {
            return;
        }

        this.d.a(this.c, arg3);
    }

    void c(okhttp3.internal.e.b arg2) {
        __monitor_enter(this);
        try {
            if(this.h == null) {
                this.h = arg2;
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
        boolean v0 = (this.c & 1) == 1 ? true : false;
        if(this.d.a == v0) {
        }
        else {
            v1 = false;
        }

        return v1;
    }

    private boolean d(okhttp3.internal.e.b arg3) {
        if(!i.i) {
            if(!Thread.holdsLock(this)) {
            }
            else {
                throw new AssertionError();
            }
        }

        __monitor_enter(this);
        try {
            if(this.h != null) {
                __monitor_exit(this);
                return 0;
            }

            if((this.m.b) && (this.e.b)) {
                __monitor_exit(this);
                return 0;
            }

            this.h = arg3;
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

        this.d.b(this.c);
        return 1;
    }

    public List d() {
        List v0_1;
        __monitor_enter(this);
        try {
            if(!this.c()) {
                goto label_27;
            }

            this.f.c();
        }
        catch(Throwable v0) {
            goto label_32;
        }

        try {
            while(true) {
                if(this.k == null && this.h == null) {
                    this.l();
                    continue;
                }

                goto label_11;
            }
        }
        catch(Throwable v0) {
            try {
                this.f.b();
                throw v0;
            label_11:
                this.f.b();
                v0_1 = this.k;
                if(v0_1 == null) {
                    goto label_19;
                }

                this.k = null;
            }
            catch(Throwable v0) {
                goto label_32;
            }
        }

        __monitor_exit(this);
        return v0_1;
        try {
        label_19:
            throw new n(this.h);
        label_27:
            throw new IllegalStateException("servers cannot read response headers");
        }
        catch(Throwable v0) {
        label_32:
            __monitor_exit(this);
            throw v0;
        }
    }

    public t e() {
        return this.f;
    }

    public t f() {
        return this.g;
    }

    public s g() {
        return this.m;
    }

    public r h() {
        __monitor_enter(this);
        try {
            if(!this.l) {
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

        return this.e;
    }

    void i() {
        if(!i.i) {
            if(!Thread.holdsLock(this)) {
            }
            else {
                throw new AssertionError();
            }
        }

        __monitor_enter(this);
        try {
            this.m.b = true;
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

        this.d.b(this.c);
    }

    void j() {
        int v0_1;
        if(!i.i) {
            if(!Thread.holdsLock(this)) {
            }
            else {
                throw new AssertionError();
            }
        }

        __monitor_enter(this);
        try {
            if((this.m.b) || !this.m.a) {
            label_23:
                v0_1 = 0;
            }
            else {
                if(!this.e.b && !this.e.a) {
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

        this.a(okhttp3.internal.e.b.f);
        return;
    label_30:
        if(!v1) {
            this.d.b(this.c);
        }
    }

    void k() {
        if(!this.e.a) {
            if(!this.e.b) {
                if(this.h == null) {
                    return;
                }

                throw new n(this.h);
            }

            throw new IOException("stream finished");
        }

        throw new IOException("stream closed");
    }

    void l() {
        try {
            this.wait();
            return;
        }
        catch(InterruptedException ) {
            throw new InterruptedIOException();
        }
    }
}

