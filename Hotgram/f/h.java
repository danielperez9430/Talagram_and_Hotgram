package f;

import e.c;
import e.e;
import e.l;
import java.io.IOException;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import okhttp3.aa;
import okhttp3.ac;
import okhttp3.ad;
import okhttp3.f;
import okhttp3.v;

final class h implements b {
    final class a extends ad {
        IOException a;
        private final ad b;

        a(ad arg1) {
            super();
            this.b = arg1;
        }

        public v a() {
            return this.b.a();
        }

        public long b() {
            return this.b.b();
        }

        public e c() {
            return l.a(new e.h(this.b.c()) {
                public long a(c arg1, long arg2) {
                    try {
                        return super.a(arg1, arg2);
                    }
                    catch(IOException v1) {
                        this.a.a = v1;
                        throw v1;
                    }
                }
            });
        }

        public void close() {
            this.b.close();
        }

        void g() {
            if(this.a == null) {
                return;
            }

            throw this.a;
        }
    }

    final class f.h$b extends ad {
        private final v a;
        private final long b;

        f.h$b(v arg1, long arg2) {
            super();
            this.a = arg1;
            this.b = arg2;
        }

        public v a() {
            return this.a;
        }

        public long b() {
            return this.b;
        }

        public e c() {
            throw new IllegalStateException("Cannot read raw response body of a converted body.");
        }
    }

    private final n a;
    @Nullable private final Object[] b;
    private volatile boolean c;
    @Nullable @GuardedBy(value="this") private okhttp3.e d;
    @Nullable @GuardedBy(value="this") private Throwable e;
    @GuardedBy(value="this") private boolean f;

    h(n arg1, @Nullable Object[] arg2) {
        super();
        this.a = arg1;
        this.b = arg2;
    }

    f.l a(ac arg6) {
        f.l v6_2;
        ad v0 = arg6.f();
        arg6 = arg6.g().a(new f.h$b(v0.a(), v0.b())).a();
        int v1 = arg6.b();
        if(v1 >= 200) {
            if(v1 >= 300) {
            }
            else {
                if(v1 != 204) {
                    if(v1 == 205) {
                    }
                    else {
                        a v1_1 = new a(v0);
                        try {
                            return f.l.a(this.a.a(((ad)v1_1)), arg6);
                        }
                        catch(RuntimeException v6) {
                            v1_1.g();
                            throw v6;
                        }
                    }
                }

                v0.close();
                return f.l.a(null, arg6);
            }
        }

        try {
            v6_2 = f.l.a(o.a(v0), arg6);
        }
        catch(Throwable v6_1) {
            v0.close();
            throw v6_1;
        }

        v0.close();
        return v6_2;
    }

    public void a(d arg4) {
        Throwable v1;
        okhttp3.e v0;
        o.a(arg4, "callback == null");
        __monitor_enter(this);
        try {
            if(this.f) {
                goto label_29;
            }

            this.f = true;
            v0 = this.d;
            v1 = this.e;
            if(v0 != null) {
                goto label_18;
            }
        }
        catch(Throwable v4) {
            goto label_34;
        }

        if(v1 != null) {
            goto label_18;
        }

        try {
            okhttp3.e v2 = this.e();
            this.d = v2;
            v0 = v2;
            goto label_18;
        }
        catch(Throwable v4) {
        label_35:
            throw v4;
        }
        catch(Throwable v1) {
            try {
                o.a(v1);
                this.e = v1;
            label_18:
                __monitor_exit(this);
                if(v1 == null) {
                    goto label_22;
                }
            }
            catch(Throwable v4) {
                goto label_34;
            }

            arg4.a(((b)this), v1);
            return;
        label_22:
            if(this.c) {
                v0.b();
            }

            v0.a(new f(arg4) {
                private void a(Throwable arg3) {
                    try {
                        this.a.a(this.b, arg3);
                    }
                    catch(Throwable arg3) {
                        arg3.printStackTrace();
                    }
                }

                public void a(okhttp3.e arg1, IOException arg2) {
                    this.a(((Throwable)arg2));
                }

                public void a(okhttp3.e arg2, ac arg3) {
                    f.l v2_1;
                    try {
                        v2_1 = this.b.a(arg3);
                    }
                    catch(Throwable v2) {
                        this.a(v2);
                        return;
                    }

                    try {
                        this.a.a(this.b, v2_1);
                    }
                    catch(Throwable v2) {
                        v2.printStackTrace();
                    }
                }
            });
            return;
            try {
            label_29:
                throw new IllegalStateException("Already executed.");
            label_34:
                __monitor_exit(this);
                goto label_35;
            }
            catch(Throwable v4) {
                goto label_34;
            }
        }
    }

    public boolean a() {
        boolean v1 = true;
        if(this.c) {
            return 1;
        }

        __monitor_enter(this);
        try {
            if(this.d == null || !this.d.c()) {
                v1 = false;
            }
            else {
            }

            __monitor_exit(this);
            return v1;
        label_15:
            __monitor_exit(this);
        }
        catch(Throwable v0) {
            goto label_15;
        }

        throw v0;
    }

    public b b() {
        return this.d();
    }

    public aa c() {
        aa v0_2;
        okhttp3.e v0_1;
        __monitor_enter(this);
        try {
            v0_1 = this.d;
            if(v0_1 == null) {
                goto label_6;
            }

            v0_2 = v0_1.a();
        }
        catch(Throwable v0) {
            goto label_39;
        }

        __monitor_exit(this);
        return v0_2;
        try {
        label_6:
            if(this.e != null) {
                if(!(this.e instanceof IOException)) {
                    if((this.e instanceof RuntimeException)) {
                        throw this.e;
                    }

                    throw this.e;
                }

                throw new RuntimeException("Unable to create request.", this.e);
            }

            try {
                v0_1 = this.e();
                this.d = v0_1;
                v0_2 = v0_1.a();
            }
            catch(IOException v0_3) {
                try {
                    this.e = ((Throwable)v0_3);
                    throw new RuntimeException("Unable to create request.", ((Throwable)v0_3));
                }
                catch(Throwable v0) {
                label_39:
                    __monitor_exit(this);
                    throw v0;
                }
            }
            catch(Error v0_4) {
                try {
                    o.a(((Throwable)v0_4));
                    this.e = ((Throwable)v0_4);
                    throw v0_4;
                }
                catch(Throwable v0) {
                    goto label_39;
                }
            }
        }
        catch(Throwable v0) {
            goto label_39;
        }

        __monitor_exit(this);
        return v0_2;
    }

    public Object clone() {
        return this.d();
    }

    public h d() {
        return new h(this.a, this.b);
    }

    private okhttp3.e e() {
        okhttp3.e v0 = this.a.a(this.b);
        if(v0 != null) {
            return v0;
        }

        throw new NullPointerException("Call.Factory returned null.");
    }
}

