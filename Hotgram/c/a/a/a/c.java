package c.a.a.a;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import c.a.a.a.a.b.p;
import c.a.a.a.a.c.d;
import c.a.a.a.a.c.k;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;

public class c {
    public class a {
        private final Context a;
        private i[] b;
        private k c;
        private Handler d;
        private l e;
        private boolean f;
        private String g;
        private String h;
        private f i;

        public a(Context arg2) {
            super();
            if(arg2 != null) {
                this.a = arg2;
                return;
            }

            throw new IllegalArgumentException("Context must not be null.");
        }

        public c a() {
            HashMap v0_1;
            if(this.c == null) {
                this.c = k.a();
            }

            if(this.d == null) {
                this.d = new Handler(Looper.getMainLooper());
            }

            if(this.e == null) {
                b v0 = this.f ? new b(3) : new b();
                this.e = ((l)v0);
            }

            if(this.h == null) {
                this.h = this.a.getPackageName();
            }

            if(this.i == null) {
                this.i = f.d;
            }

            if(this.b == null) {
                v0_1 = new HashMap();
            }
            else {
                Map v0_2 = c.a(Arrays.asList(this.b));
            }

            HashMap v3 = v0_1;
            Context v2 = this.a.getApplicationContext();
            return new c(v2, ((Map)v3), this.c, this.d, this.e, this.f, this.i, new p(v2, this.h, this.g, ((Map)v3).values()), c.c(this.a));
        }

        public a a(i[] arg2) {
            if(this.b == null) {
                this.b = arg2;
                return this;
            }

            throw new IllegalStateException("Kits already set.");
        }
    }

    static volatile c a;
    static final l b;
    final l c;
    final boolean d;
    private final Context e;
    private final Map f;
    private final ExecutorService g;
    private final Handler h;
    private final f i;
    private final f j;
    private final p k;
    private c.a.a.a.a l;
    private WeakReference m;
    private AtomicBoolean n;

    static {
        c.b = new b();
    }

    c(Context arg1, Map arg2, k arg3, Handler arg4, l arg5, boolean arg6, f arg7, p arg8, Activity arg9) {
        super();
        this.e = arg1;
        this.f = arg2;
        this.g = ((ExecutorService)arg3);
        this.h = arg4;
        this.c = arg5;
        this.d = arg6;
        this.i = arg7;
        this.n = new AtomicBoolean(false);
        this.j = this.a(arg2.size());
        this.k = arg8;
        this.a(arg9);
    }

    f a(int arg2) {
        return new f(arg2) {
            final CountDownLatch a;

            public void a(Exception arg2) {
                c.b(this.c).a(arg2);
            }

            public void a(Object arg5) {
                this.a.countDown();
                if(this.a.getCount() == 0) {
                    c.a(this.c).set(true);
                    c.b(this.c).a(this.c);
                }
            }
        };
    }

    public c a(Activity arg2) {
        this.m = new WeakReference(arg2);
        return this;
    }

    static c a() {
        if(c.a != null) {
            return c.a;
        }

        throw new IllegalStateException("Must Initialize Fabric before using singleton()");
    }

    public static c a(Context arg2, i[] arg3) {
        if(c.a == null) {
            Class v0 = c.class;
            __monitor_enter(v0);
            try {
                if(c.a == null) {
                    c.c(new a(arg2).a(arg3).a());
                }

                __monitor_exit(v0);
                goto label_16;
            label_14:
                __monitor_exit(v0);
            }
            catch(Throwable v2) {
                goto label_14;
            }

            throw v2;
        }

    label_16:
        return c.a;
    }

    public static i a(Class arg1) {
        return c.a().f.get(arg1);
    }

    static Map a(Collection arg0) {
        return c.b(arg0);
    }

    static AtomicBoolean a(c arg0) {
        return arg0.n;
    }

    private static void a(Map arg2, Collection arg3) {
        Iterator v3 = arg3.iterator();
        while(v3.hasNext()) {
            Object v0 = v3.next();
            arg2.put(v0.getClass(), v0);
            if(!(v0 instanceof j)) {
                continue;
            }

            c.a(arg2, ((j)v0).c());
        }
    }

    void a(Context arg7) {
        StringBuilder v7;
        Future v0 = this.b(arg7);
        Collection v1 = this.g();
        m v2 = new m(v0, v1);
        ArrayList v0_1 = new ArrayList(v1);
        Collections.sort(((List)v0_1));
        v2.a(arg7, this, f.d, this.k);
        Iterator v1_1 = ((List)v0_1).iterator();
        while(v1_1.hasNext()) {
            v1_1.next().a(arg7, this, this.j, this.k);
        }

        v2.o();
        if(c.h().a("Fabric", 3)) {
            v7 = new StringBuilder("Initializing ");
            v7.append(this.d());
            v7.append(" [Version: ");
            v7.append(this.c());
            v7.append("], with the following kits:\n");
        }
        else {
            v7 = null;
        }

        Iterator v0_2 = ((List)v0_1).iterator();
        while(v0_2.hasNext()) {
            Object v1_2 = v0_2.next();
            ((i)v1_2).f.a(v2.f);
            this.a(this.f, ((i)v1_2));
            ((i)v1_2).o();
            if(v7 == null) {
                continue;
            }

            v7.append(((i)v1_2).b());
            v7.append(" [Version: ");
            v7.append(((i)v1_2).a());
            v7.append("]\n");
        }

        if(v7 != null) {
            c.h().a("Fabric", v7.toString());
        }
    }

    void a(Map arg8, i arg9) {
        d v0 = arg9.j;
        if(v0 != null) {
            Class[] v0_1 = v0.a();
            int v1 = v0_1.length;
            int v2 = 0;
            while(true) {
                if(v2 < v1) {
                    Class v3 = v0_1[v2];
                    if(v3.isInterface()) {
                        Iterator v4 = arg8.values().iterator();
                        while(v4.hasNext()) {
                            Object v5 = v4.next();
                            if(!v3.isAssignableFrom(v5.getClass())) {
                                continue;
                            }

                            arg9.f.a(((i)v5).f);
                        }
                    }
                    else if(arg8.get(v3) != null) {
                        arg9.f.a(arg8.get(v3).f);
                    }
                    else {
                        break;
                    }

                    ++v2;
                    continue;
                }

                return;
            }

            throw new c.a.a.a.a.c.m("Referenced Kit was null, does the kit exist?");
        }
    }

    private static Map b(Collection arg2) {
        HashMap v0 = new HashMap(arg2.size());
        c.a(((Map)v0), arg2);
        return ((Map)v0);
    }

    static f b(c arg0) {
        return arg0.i;
    }

    Future b(Context arg2) {
        return this.f().submit(new e(arg2.getPackageCodePath()));
    }

    public Activity b() {
        if(this.m != null) {
            return this.m.get();
        }

        return null;
    }

    private static void c(c arg0) {
        c.a = arg0;
        arg0.j();
    }

    static Activity c(Context arg0) {
        return c.d(arg0);
    }

    public String c() {
        return "1.4.1.19";
    }

    private static Activity d(Context arg1) {
        if((arg1 instanceof Activity)) {
            return arg1;
        }

        return null;
    }

    public String d() {
        return "io.fabric.sdk.android:fabric";
    }

    public c.a.a.a.a e() {
        return this.l;
    }

    public ExecutorService f() {
        return this.g;
    }

    public Collection g() {
        return this.f.values();
    }

    public static l h() {
        if(c.a == null) {
            return c.b;
        }

        return c.a.c;
    }

    public static boolean i() {
        if(c.a == null) {
            return 0;
        }

        return c.a.d;
    }

    private void j() {
        this.l = new c.a.a.a.a(this.e);
        this.l.a(new c.a.a.a.a$b() {
            public void a(Activity arg2) {
                this.a.a(arg2);
            }

            public void a(Activity arg1, Bundle arg2) {
                this.a.a(arg1);
            }

            public void b(Activity arg2) {
                this.a.a(arg2);
            }
        });
        this.a(this.e);
    }
}

