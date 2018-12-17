package c.b.b;

import java.util.ArrayList;
import java.util.List;

public final class a implements b, c.b.e.a.a {
    c.b.e.g.b a;
    volatile boolean b;

    public a() {
        super();
    }

    public void a() {
        c.b.e.g.b v0_1;
        if(this.b) {
            return;
        }

        __monitor_enter(this);
        try {
            if(this.b) {
                __monitor_exit(this);
                return;
            }

            this.b = true;
            v0_1 = this.a;
            this.a = null;
            __monitor_exit(this);
        }
        catch(Throwable v0) {
            try {
            label_17:
                __monitor_exit(this);
            }
            catch(Throwable v0) {
                goto label_17;
            }

            throw v0;
        }

        this.a(v0_1);
    }

    void a(c.b.e.g.b arg7) {
        if(arg7 == null) {
            return;
        }

        Object[] v7 = arg7.b();
        int v1 = v7.length;
        ArrayList v3 = null;
        int v0;
        for(v0 = 0; v0 < v1; ++v0) {
            Object v4 = v7[v0];
            if((v4 instanceof b)) {
                try {
                    ((b)v4).a();
                }
                catch(Throwable v4_1) {
                    c.b.c.b.b(v4_1);
                    if(v3 == null) {
                        v3 = new ArrayList();
                    }

                    ((List)v3).add(v4_1);
                }
            }
        }

        if(v3 != null) {
            if(((List)v3).size() == 1) {
                throw c.b.e.g.a.a(((List)v3).get(0));
            }

            throw new c.b.c.a(((Iterable)v3));
        }
    }

    public boolean a(b arg2) {
        c.b.e.b.b.a(arg2, "d is null");
        if(!this.b) {
            __monitor_enter(this);
            try {
                if(!this.b) {
                    c.b.e.g.b v0 = this.a;
                    if(v0 == null) {
                        v0 = new c.b.e.g.b();
                        this.a = v0;
                    }

                    v0.a(arg2);
                    __monitor_exit(this);
                    return 1;
                }
                else {
                    __monitor_exit(this);
                    goto label_21;
                label_19:
                    __monitor_exit(this);
                    goto label_20;
                }

                goto label_21;
            }
            catch(Throwable v2) {
                goto label_19;
            }

        label_20:
            throw v2;
        }

    label_21:
        arg2.a();
        return 0;
    }

    public boolean b(b arg2) {
        if(this.c(arg2)) {
            arg2.a();
            return 1;
        }

        return 0;
    }

    public boolean c(b arg3) {
        c.b.e.b.b.a(arg3, "Disposable item is null");
        if(this.b) {
            return 0;
        }

        __monitor_enter(this);
        try {
            if(this.b) {
                __monitor_exit(this);
                return 0;
            }

            c.b.e.g.b v0 = this.a;
            if(v0 != null) {
                if(!v0.b(arg3)) {
                }
                else {
                    __monitor_exit(this);
                    return 1;
                }
            }

            __monitor_exit(this);
            return 0;
        label_22:
            __monitor_exit(this);
        }
        catch(Throwable v3) {
            goto label_22;
        }

        throw v3;
    }
}

