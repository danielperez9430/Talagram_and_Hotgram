package okhttp3.internal.b;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.Socket;
import java.util.List;
import okhttp3.ae;
import okhttp3.e;
import okhttp3.i;
import okhttp3.internal.e.b;
import okhttp3.internal.e.n;
import okhttp3.j;
import okhttp3.p;
import okhttp3.x;

public final class g {
    public final class a extends WeakReference {
        public final Object a;

        a(g arg1, Object arg2) {
            super(arg1);
            this.a = arg2;
        }
    }

    public final okhttp3.a a;
    public final e b;
    public final p c;
    private okhttp3.internal.b.f$a e;
    private ae f;
    private final j g;
    private final Object h;
    private final f i;
    private int j;
    private c k;
    private boolean l;
    private boolean m;
    private boolean n;
    private okhttp3.internal.c.c o;

    static {
        g.d = g.class.desiredAssertionStatus() ^ 1;
    }

    public g(j arg2, okhttp3.a arg3, e arg4, p arg5, Object arg6) {
        super();
        this.g = arg2;
        this.a = arg3;
        this.b = arg4;
        this.c = arg5;
        this.i = new f(arg3, this.i(), arg4, arg5);
        this.h = arg6;
    }

    public Socket a(c arg4) {
        if(!g.d) {
            if(Thread.holdsLock(this.g)) {
            }
            else {
                throw new AssertionError();
            }
        }

        if(this.o == null && this.k.d.size() == 1) {
            Object v0 = this.k.d.get(0);
            Socket v1 = this.a(true, false, false);
            this.k = arg4;
            arg4.d.add(v0);
            return v1;
        }

        throw new IllegalStateException();
    }

    public void a(c arg2, boolean arg3) {
        if(!g.d) {
            if(Thread.holdsLock(this.g)) {
            }
            else {
                throw new AssertionError();
            }
        }

        if(this.k == null) {
            this.k = arg2;
            this.l = arg3;
            arg2.d.add(new a(this, this.h));
            return;
        }

        throw new IllegalStateException();
    }

    private Socket a(boolean arg2, boolean arg3, boolean arg4) {
        Socket v2;
        if(!g.d) {
            if(Thread.holdsLock(this.g)) {
            }
            else {
                throw new AssertionError();
            }
        }

        okhttp3.internal.c.c v0 = null;
        if(arg4) {
            this.o = v0;
        }

        if(arg3) {
            this.m = true;
        }

        if(this.k != null) {
            if(arg2) {
                this.k.a = true;
            }

            if(this.o != null) {
                goto label_47;
            }

            if(!this.m && !this.k.a) {
                goto label_47;
            }

            this.b(this.k);
            if(this.k.d.isEmpty()) {
                this.k.e = System.nanoTime();
                if(okhttp3.internal.a.a.a(this.g, this.k)) {
                    v2 = this.k.c();
                }
                else {
                    goto label_44;
                }
            }
            else {
            label_44:
                v2 = ((Socket)v0);
            }

            this.k = ((c)v0);
        }
        else {
        label_47:
            v2 = ((Socket)v0);
        }

        return v2;
    }

    private c a(int arg19, int arg20, int arg21, int arg22, boolean arg23) {
        int v2_1;
        int v0_2;
        ae v9;
        c v8;
        c v4;
        ae v5;
        Socket v3;
        g v1 = this;
        j v2 = v1.g;
        __monitor_enter(v2);
        try {
            if(v1.m) {
                goto label_154;
            }

            if(v1.o != null) {
                goto label_150;
            }

            if(v1.n) {
                goto label_146;
            }

            c v0_1 = v1.k;
            v3 = this.h();
            v5 = null;
            if(v1.k != null) {
                v0_1 = v1.k;
                v4 = ((c)v5);
            }
            else {
                v4 = v0_1;
                v0_1 = ((c)v5);
            }

            if(!v1.l) {
                v4 = ((c)v5);
            }

            if(v0_1 == null) {
                okhttp3.internal.a.a.a(v1.g, v1.a, v1, v5);
                if(v1.k != null) {
                    v8 = v1.k;
                    v9 = v5;
                    v0_2 = 1;
                }
                else {
                    v9 = v1.f;
                    v8 = v0_1;
                    goto label_42;
                }
            }
            else {
                v8 = v0_1;
                v9 = v5;
            label_42:
                v0_2 = 0;
            }

            __monitor_exit(v2);
        }
        catch(Throwable v0) {
            goto label_159;
        }

        okhttp3.internal.c.a(v3);
        if(v4 != null) {
            v1.c.b(v1.b, ((i)v4));
        }

        if(v0_2 != 0) {
            v1.c.a(v1.b, ((i)v8));
        }

        if(v8 != null) {
            return v8;
        }

        if(v9 == null) {
            if(v1.e != null && (v1.e.a())) {
                goto label_66;
            }

            v1.e = v1.i.b();
            v2_1 = 1;
        }
        else {
        label_66:
            v2_1 = 0;
        }

        j v3_1 = v1.g;
        __monitor_enter(v3_1);
        try {
            if(v1.n) {
                goto label_139;
            }

            if(v2_1 != 0) {
                List v2_2 = v1.e.c();
                int v4_1 = v2_2.size();
                int v10 = 0;
                while(v10 < v4_1) {
                    Object v11 = v2_2.get(v10);
                    okhttp3.internal.a.a.a(v1.g, v1.a, v1, ((ae)v11));
                    if(v1.k != null) {
                        v8 = v1.k;
                        v1.f = ((ae)v11);
                        v0_2 = 1;
                    }
                    else {
                        ++v10;
                        continue;
                    }

                    break;
                }
            }

            if(v0_2 == 0) {
                if(v9 == null) {
                    v9 = v1.e.b();
                }

                v1.f = v9;
                v1.j = 0;
                v8 = new c(v1.g, v9);
                v1.a(v8, false);
            }

            __monitor_exit(v3_1);
            if(v0_2 == 0) {
                goto label_106;
            }

            goto label_102;
        }
        catch(Throwable v0) {
            goto label_144;
        }

    label_106:
        v8.a(arg19, arg20, arg21, arg22, arg23, v1.b, v1.c);
        this.i().b(v8.a());
        v2 = v1.g;
        __monitor_enter(v2);
        try {
            v1.l = true;
            okhttp3.internal.a.a.b(v1.g, v8);
            if(v8.e()) {
                Socket v5_1 = okhttp3.internal.a.a.a(v1.g, v1.a, v1);
                v8 = v1.k;
            }

            __monitor_exit(v2);
        }
        catch(Throwable v0) {
            try {
            label_137:
                __monitor_exit(v2);
            }
            catch(Throwable v0) {
                goto label_137;
            }

            throw v0;
        }

        okhttp3.internal.c.a(((Socket)v5));
    label_102:
        v1.c.a(v1.b, ((i)v8));
        return v8;
        try {
        label_139:
            throw new IOException("Canceled");
        label_144:
            __monitor_exit(v3_1);
        }
        catch(Throwable v0) {
            goto label_144;
        }

        throw v0;
        try {
        label_146:
            throw new IOException("Canceled");
        label_150:
            throw new IllegalStateException("codec != null");
        label_154:
            throw new IllegalStateException("released");
        label_159:
            __monitor_exit(v2);
        }
        catch(Throwable v0) {
            goto label_159;
        }

        throw v0;
    }

    private c a(int arg4, int arg5, int arg6, int arg7, boolean arg8, boolean arg9) {
        j v1;
        c v0;
        while(true) {
            v0 = this.a(arg4, arg5, arg6, arg7, arg8);
            v1 = this.g;
            __monitor_enter(v1);
            try {
                if(v0.b == 0) {
                    __monitor_exit(v1);
                    return v0;
                }

                __monitor_exit(v1);
            }
            catch(Throwable v4) {
                goto label_14;
            }

            if(v0.a(arg9)) {
                return v0;
            }

            this.e();
        }

        return v0;
        try {
        label_14:
            __monitor_exit(v1);
        }
        catch(Throwable v4) {
            goto label_14;
        }

        throw v4;
    }

    public okhttp3.internal.c.c a() {
        j v0 = this.g;
        __monitor_enter(v0);
        try {
            __monitor_exit(v0);
            return this.o;
        label_6:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_6;
        }

        throw v1;
    }

    public okhttp3.internal.c.c a(x arg8, okhttp3.u$a arg9, boolean arg10) {
        j v9;
        okhttp3.internal.c.c v8_1;
        int v1 = arg9.b();
        int v2 = arg9.c();
        int v3 = arg9.d();
        int v4 = arg8.d();
        boolean v5 = arg8.s();
        g v0 = this;
        boolean v6 = arg10;
        try {
            v8_1 = v0.a(v1, v2, v3, v4, v5, v6).a(arg8, arg9, this);
            v9 = this.g;
            __monitor_enter(v9);
        }
        catch(IOException v8) {
            goto label_20;
        }

        try {
            this.o = v8_1;
            __monitor_exit(v9);
            return v8_1;
        label_15:
            __monitor_exit(v9);
        }
        catch(Throwable v8_2) {
            goto label_15;
        }

        try {
            throw v8_2;
        }
        catch(IOException v8) {
        label_20:
            throw new okhttp3.internal.b.e(v8);
        }
    }

    public void a(IOException arg7) {
        Socket v7_2;
        c v1;
        boolean v7_1;
        j v0 = this.g;
        __monitor_enter(v0);
        try {
            ae v3 = null;
            if((arg7 instanceof n)) {
                if(((n)arg7).a == b.e) {
                    ++this.j;
                }

                if(((n)arg7).a != b.e) {
                    goto label_35;
                }

                if(this.j <= 1) {
                    goto label_38;
                }

                goto label_35;
            }
            else {
                if(this.k != null && (!this.k.e() || ((arg7 instanceof okhttp3.internal.e.a)))) {
                    if(this.k.b == 0) {
                        if(this.f != null && arg7 != null) {
                            this.i.a(this.f, arg7);
                        }

                    label_35:
                        this.f = v3;
                    }

                    v7_1 = true;
                    goto label_39;
                }

            label_38:
                v7_1 = false;
            }

        label_39:
            v1 = this.k;
            v7_2 = this.a(v7_1, false, true);
            if(this.k != null || !this.l) {
                v1 = ((c)v3);
            }

            __monitor_exit(v0);
        }
        catch(Throwable v7) {
            try {
            label_54:
                __monitor_exit(v0);
            }
            catch(Throwable v7) {
                goto label_54;
            }

            throw v7;
        }

        okhttp3.internal.c.a(v7_2);
        if(v1 != null) {
            this.c.b(this.b, ((i)v1));
        }
    }

    public void a(boolean arg3, okhttp3.internal.c.c arg4, long arg5, IOException arg7) {
        Socket v3_1;
        c v6;
        this.c.b(this.b, arg5);
        j v5 = this.g;
        __monitor_enter(v5);
        if(arg4 != null) {
            try {
                if(arg4 == this.o) {
                    if(!arg3) {
                        ++this.k.b;
                    }

                    v6 = this.k;
                    v3_1 = this.a(arg3, false, true);
                    if(this.k != null) {
                        v6 = null;
                    }

                    boolean v4 = this.m;
                    __monitor_exit(v5);
                    goto label_22;
                }

                goto label_39;
            }
            catch(Throwable v3) {
                goto label_38;
            }

        label_22:
            okhttp3.internal.c.a(v3_1);
            if(v6 != null) {
                this.c.b(this.b, ((i)v6));
            }

            if(arg7 != null) {
                this.c.a(this.b, arg7);
            }
            else if(v4) {
                this.c.g(this.b);
            }

            return;
        }

        try {
        label_39:
            StringBuilder v6_1 = new StringBuilder();
            v6_1.append("expected ");
            v6_1.append(this.o);
            v6_1.append(" but was ");
            v6_1.append(arg4);
            throw new IllegalStateException(v6_1.toString());
        }
        catch(Throwable v3) {
        label_38:
            try {
                __monitor_exit(v5);
            }
            catch(Throwable v3) {
                goto label_38;
            }

            throw v3;
        }
    }

    private void b(c arg4) {
        int v0 = arg4.d.size();
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            if(arg4.d.get(v1).get() == this) {
                arg4.d.remove(v1);
                return;
            }
        }

        throw new IllegalStateException();
    }

    public ae b() {
        return this.f;
    }

    public c c() {
        c v0_1;
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

    public void d() {
        Socket v2;
        c v1_1;
        j v0 = this.g;
        __monitor_enter(v0);
        try {
            v1_1 = this.k;
            v2 = this.a(false, true, false);
            if(this.k != null) {
                v1_1 = null;
            }

            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            try {
            label_17:
                __monitor_exit(v0);
            }
            catch(Throwable v1) {
                goto label_17;
            }

            throw v1;
        }

        okhttp3.internal.c.a(v2);
        if(v1_1 != null) {
            this.c.b(this.b, ((i)v1_1));
        }
    }

    public void e() {
        Socket v2;
        c v1_1;
        j v0 = this.g;
        __monitor_enter(v0);
        try {
            v1_1 = this.k;
            v2 = this.a(true, false, false);
            if(this.k != null) {
                v1_1 = null;
            }

            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            try {
            label_17:
                __monitor_exit(v0);
            }
            catch(Throwable v1) {
                goto label_17;
            }

            throw v1;
        }

        okhttp3.internal.c.a(v2);
        if(v1_1 != null) {
            this.c.b(this.b, ((i)v1_1));
        }
    }

    public void f() {
        c v2;
        okhttp3.internal.c.c v1_1;
        j v0 = this.g;
        __monitor_enter(v0);
        try {
            this.n = true;
            v1_1 = this.o;
            v2 = this.k;
            __monitor_exit(v0);
            if(v1_1 == null) {
                goto label_10;
            }
        }
        catch(Throwable v1) {
            try {
            label_14:
                __monitor_exit(v0);
            }
            catch(Throwable v1) {
                goto label_14;
            }

            throw v1;
        }

        v1_1.c();
        return;
    label_10:
        if(v2 != null) {
            v2.b();
        }
    }

    public boolean g() {
        boolean v0;
        if(this.f == null) {
            if(this.e != null && (this.e.a())) {
                goto label_13;
            }

            if(this.i.a()) {
                goto label_13;
            }

            v0 = false;
        }
        else {
        label_13:
            v0 = true;
        }

        return v0;
    }

    private Socket h() {
        if(!g.d) {
            if(Thread.holdsLock(this.g)) {
            }
            else {
                throw new AssertionError();
            }
        }

        c v0 = this.k;
        if(v0 != null && (v0.a)) {
            return this.a(false, false, true);
        }

        return null;
    }

    private d i() {
        return okhttp3.internal.a.a.a(this.g);
    }

    public String toString() {
        c v0 = this.c();
        String v0_1 = v0 != null ? v0.toString() : this.a.toString();
        return v0_1;
    }
}

