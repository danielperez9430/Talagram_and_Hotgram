package com.persianswitch.a.a.b;

import com.persianswitch.a.a.c.b;
import com.persianswitch.a.a.d;
import com.persianswitch.a.a.k;
import com.persianswitch.a.a.l;
import com.persianswitch.a.a;
import com.persianswitch.a.ab;
import com.persianswitch.a.j;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

public final class s {
    public final a a;
    private ab b;
    private final j c;
    private final q d;
    private int e;
    private b f;
    private boolean g;
    private boolean h;
    private com.persianswitch.a.a.b.j i;

    public s(j arg2, a arg3) {
        super();
        this.c = arg2;
        this.a = arg3;
        this.d = new q(arg3, this.f());
    }

    public com.persianswitch.a.a.b.j a(int arg3, int arg4, int arg5, boolean arg6, boolean arg7) {
        j v3_2;
        e v4_1;
        try {
            b v3_1 = this.b(arg3, arg4, arg5, arg6, arg7);
            if(v3_1.c != null) {
                f v4 = new f(this, v3_1.c);
            }
            else {
                v3_1.b().setSoTimeout(arg4);
                v3_1.e.a().a(((long)arg4), TimeUnit.MILLISECONDS);
                v3_1.f.a().a(((long)arg5), TimeUnit.MILLISECONDS);
                v4_1 = new e(this, v3_1.e, v3_1.f);
            }

            v3_2 = this.c;
            __monitor_enter(v3_2);
        }
        catch(IOException v3) {
            goto label_34;
        }

        try {
            this.i = ((com.persianswitch.a.a.b.j)v4_1);
            __monitor_exit(v3_2);
            return ((com.persianswitch.a.a.b.j)v4_1);
        label_29:
            __monitor_exit(v3_2);
        }
        catch(Throwable v4_2) {
            goto label_29;
        }

        try {
            throw v4_2;
        }
        catch(IOException v3) {
        label_34:
            throw new p(v3);
        }
    }

    public void a(IOException arg7) {
        boolean v7_1;
        j v0 = this.c;
        __monitor_enter(v0);
        try {
            ab v2 = null;
            if((arg7 instanceof com.persianswitch.a.a.a.p)) {
                if(((com.persianswitch.a.a.a.p)arg7).a == com.persianswitch.a.a.a.a.k) {
                    ++this.e;
                }

                if(((com.persianswitch.a.a.a.p)arg7).a != com.persianswitch.a.a.a.a.k) {
                    goto label_33;
                }

                if(this.e <= 1) {
                    goto label_36;
                }

                goto label_33;
            }
            else {
                if(this.f != null && !this.f.d()) {
                    if(this.f.d == 0) {
                        if(this.b != null && arg7 != null) {
                            this.d.a(this.b, arg7);
                        }

                    label_33:
                        this.b = v2;
                    }

                    v7_1 = true;
                    goto label_37;
                }

            label_36:
                v7_1 = false;
            }

        label_37:
            __monitor_exit(v0);
        }
        catch(Throwable v7) {
            try {
            label_41:
                __monitor_exit(v0);
            }
            catch(Throwable v7) {
                goto label_41;
            }

            throw v7;
        }

        this.a(v7_1, false, true);
    }

    private b a(int arg9, int arg10, int arg11, boolean arg12) {
        ab v1_1;
        j v0 = this.c;
        __monitor_enter(v0);
        try {
            if(this.g) {
                goto label_76;
            }

            if(this.i != null) {
                goto label_72;
            }

            if(this.h) {
                goto label_68;
            }

            b v1 = this.f;
            if(v1 != null && !v1.i) {
                __monitor_exit(v0);
                return v1;
            }

            v1 = d.a.a(this.c, this.a, this);
            if(v1 != null) {
                this.f = v1;
                __monitor_exit(v0);
                return v1;
            }

            v1_1 = this.b;
            __monitor_exit(v0);
            if(v1_1 == null) {
                goto label_25;
            }

            goto label_37;
        }
        catch(Throwable v9) {
            goto label_81;
        }

    label_25:
        v1_1 = this.d.b();
        v0 = this.c;
        __monitor_enter(v0);
        try {
            this.b = v1_1;
            this.e = 0;
            __monitor_exit(v0);
            goto label_37;
        label_35:
            __monitor_exit(v0);
        }
        catch(Throwable v9) {
            goto label_35;
        }

        throw v9;
    label_37:
        b v0_1 = new b(v1_1);
        this.a(v0_1);
        j v1_2 = this.c;
        __monitor_enter(v1_2);
        try {
            d.a.b(this.c, v0_1);
            this.f = v0_1;
            if(this.h) {
                goto label_61;
            }

            __monitor_exit(v1_2);
        }
        catch(Throwable v9) {
            goto label_66;
        }

        v0_1.a(arg9, arg10, arg11, this.a.f(), arg12);
        this.f().b(v0_1.a());
        return v0_1;
        try {
        label_61:
            throw new IOException("Canceled");
        label_66:
            __monitor_exit(v1_2);
        }
        catch(Throwable v9) {
            goto label_66;
        }

        throw v9;
        try {
        label_68:
            throw new IOException("Canceled");
        label_72:
            throw new IllegalStateException("stream != null");
        label_76:
            throw new IllegalStateException("released");
        label_81:
            __monitor_exit(v0);
        }
        catch(Throwable v9) {
            goto label_81;
        }

        throw v9;
    }

    public void a(b arg2) {
        arg2.h.add(new WeakReference(this));
    }

    private void a(boolean arg3, boolean arg4, boolean arg5) {
        b v3_1;
        j v0 = this.c;
        __monitor_enter(v0);
        com.persianswitch.a.a.b.j v1 = null;
        if(arg5) {
            try {
                this.i = v1;
            label_8:
                if(arg4) {
                    this.g = true;
                }

                if(this.f != null) {
                    if(arg3) {
                        this.f.i = true;
                    }

                    if(this.i != null) {
                        goto label_42;
                    }

                    if(!this.g && !this.f.i) {
                        goto label_42;
                    }

                    this.b(this.f);
                    if(this.f.h.isEmpty()) {
                        this.f.j = System.nanoTime();
                        if(d.a.a(this.c, this.f)) {
                            v3_1 = this.f;
                        }
                        else {
                            goto label_39;
                        }
                    }
                    else {
                    label_39:
                        v3_1 = ((b)v1);
                    }

                    this.f = ((b)v1);
                }
                else {
                label_42:
                    v3_1 = ((b)v1);
                }

                __monitor_exit(v0);
                if(v3_1 == null) {
                    return;
                }

                goto label_45;
            label_48:
                __monitor_exit(v0);
                goto label_49;
            }
            catch(Throwable v3) {
                goto label_48;
            }
        }

        goto label_8;
    label_49:
        throw v3;
    label_45:
        l.a(v3_1.b());
    }

    public com.persianswitch.a.a.b.j a() {
        j v0 = this.c;
        __monitor_enter(v0);
        try {
            __monitor_exit(v0);
            return this.i;
        label_6:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_6;
        }

        throw v1;
    }

    public void a(boolean arg4, com.persianswitch.a.a.b.j arg5) {
        j v0 = this.c;
        __monitor_enter(v0);
        if(arg5 != null) {
            try {
                if(arg5 == this.i) {
                    if(!arg4) {
                        ++this.f.d;
                    }

                    __monitor_exit(v0);
                    goto label_12;
                }

                goto label_17;
            }
            catch(Throwable v4) {
                goto label_16;
            }

        label_12:
            this.a(arg4, false, true);
            return;
        }

        try {
        label_17:
            StringBuilder v1 = new StringBuilder();
            v1.append("expected ");
            v1.append(this.i);
            v1.append(" but was ");
            v1.append(arg5);
            throw new IllegalStateException(v1.toString());
        }
        catch(Throwable v4) {
        label_16:
            try {
                __monitor_exit(v0);
            }
            catch(Throwable v4) {
                goto label_16;
            }

            throw v4;
        }
    }

    public b b() {
        b v0_1;
        __monitor_enter(this);
        try {
            v0_1 = this.f;
        }
        catch(Throwable v0) {
            __monitor_exit(this);
            throw v0;
        }

        __monitor_exit(this);
        return v0_1;
    }

    private void b(b arg4) {
        int v0 = arg4.h.size();
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            if(arg4.h.get(v1).get() == this) {
                arg4.h.remove(v1);
                return;
            }
        }

        throw new IllegalStateException();
    }

    private b b(int arg4, int arg5, int arg6, boolean arg7, boolean arg8) {
        j v1;
        b v0;
        while(true) {
            v0 = this.a(arg4, arg5, arg6, arg7);
            v1 = this.c;
            __monitor_enter(v1);
            try {
                if(v0.d == 0) {
                    __monitor_exit(v1);
                    return v0;
                }

                __monitor_exit(v1);
            }
            catch(Throwable v4) {
                goto label_14;
            }

            if(v0.a(arg8)) {
                return v0;
            }

            this.d();
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

    public void c() {
        this.a(false, true, false);
    }

    public void d() {
        this.a(true, false, false);
    }

    public boolean e() {
        boolean v0 = this.b != null || (this.d.a()) ? true : false;
        return v0;
    }

    private k f() {
        return d.a.a(this.c);
    }

    public String toString() {
        return this.a.toString();
    }
}

