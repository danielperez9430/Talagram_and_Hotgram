package com.google.firebase.components;

import com.google.firebase.c.a;

final class p implements a {
    private static final Object a;
    private volatile Object b;
    private volatile a c;

    static {
        p.a = new Object();
    }

    p(d arg2, b arg3) {
        super();
        this.b = p.a;
        this.c = new q(arg2, arg3);
    }

    static final Object a(d arg0, b arg1) {
        return arg0.a(arg1);
    }

    public final Object a() {
        Object v0 = this.b;
        if(v0 == p.a) {
            __monitor_enter(this);
            try {
                v0 = this.b;
                if(v0 == p.a) {
                    v0 = this.c.a();
                    this.b = v0;
                    this.c = null;
                }

                __monitor_exit(this);
                return v0;
            label_15:
                __monitor_exit(this);
            }
            catch(Throwable v0_1) {
                goto label_15;
            }

            throw v0_1;
        }

        return v0;
    }
}

