package com.d.a.b;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import com.d.a.b.a.g;
import com.d.a.b.d.b$a;
import com.d.a.b.d.b;
import com.d.a.c.d;
import java.io.InputStream;
import java.util.concurrent.Executor;

public final class e {
    class com.d.a.b.e$1 {
        static {
            com.d.a.b.e$1.a = new int[a.values().length];
            try {
                com.d.a.b.e$1.a[a.a.ordinal()] = 1;
                goto label_9;
            }
            catch(NoSuchFieldError ) {
                try {
                label_9:
                    com.d.a.b.e$1.a[a.b.ordinal()] = 2;
                    return;
                }
                catch(NoSuchFieldError ) {
                    return;
                }
            }
        }
    }

    public class com.d.a.b.e$a {
        public static final g a;
        private Context b;
        private int c;
        private int d;
        private int e;
        private int f;
        private com.d.a.b.g.a g;
        private Executor h;
        private Executor i;
        private boolean j;
        private boolean k;
        private int l;
        private int m;
        private boolean n;
        private g o;
        private int p;
        private long q;
        private int r;
        private com.d.a.a.b.a s;
        private com.d.a.a.a.a t;
        private com.d.a.a.a.b.a u;
        private b v;
        private com.d.a.b.b.b w;
        private c x;
        private boolean y;

        static {
            com.d.a.b.e$a.a = g.a;
        }

        public com.d.a.b.e$a(Context arg5) {
            super();
            this.c = 0;
            this.d = 0;
            this.e = 0;
            this.f = 0;
            this.g = null;
            this.h = null;
            this.i = null;
            this.j = false;
            this.k = false;
            this.l = 3;
            this.m = 3;
            this.n = false;
            this.o = com.d.a.b.e$a.a;
            this.p = 0;
            this.q = 0;
            this.r = 0;
            this.s = null;
            this.t = null;
            this.u = null;
            this.v = null;
            this.x = null;
            this.y = false;
            this.b = arg5.getApplicationContext();
        }

        static Context a(com.d.a.b.e$a arg0) {
            return arg0.b;
        }

        public com.d.a.b.e$a a() {
            this.n = true;
            return this;
        }

        public com.d.a.b.e$a a(int arg3) {
            if(this.h != null || this.i != null) {
                com.d.a.c.c.c("threadPoolSize(), threadPriority() and tasksProcessingOrder() calls can overlap taskExecutor() and taskExecutorForCachedImages() calls.", new Object[0]);
            }

            int v0 = 1;
            if(arg3 >= 1) {
                v0 = 10;
                if(arg3 > v0) {
                    goto label_10;
                }
                else {
                    goto label_15;
                }
            }
            else {
            label_10:
                this.m = v0;
                return this;
            label_15:
                this.m = arg3;
            }

            return this;
        }

        public com.d.a.b.e$a a(com.d.a.a.a.b.a arg3) {
            if(this.t != null) {
                com.d.a.c.c.c("diskCache() and diskCacheFileNameGenerator() calls overlap each other", new Object[0]);
            }

            this.u = arg3;
            return this;
        }

        public com.d.a.b.e$a a(g arg3) {
            if(this.h != null || this.i != null) {
                com.d.a.c.c.c("threadPoolSize(), threadPriority() and tasksProcessingOrder() calls can overlap taskExecutor() and taskExecutorForCachedImages() calls.", new Object[0]);
            }

            this.o = arg3;
            return this;
        }

        static int b(com.d.a.b.e$a arg0) {
            return arg0.c;
        }

        public com.d.a.b.e$a b() {
            this.y = true;
            return this;
        }

        public com.d.a.b.e$a b(int arg3) {
            if(arg3 > 0) {
                if(this.t != null) {
                    com.d.a.c.c.c("diskCache(), diskCacheSize() and diskCacheFileCount calls overlap each other", new Object[0]);
                }

                this.q = ((long)arg3);
                return this;
            }

            throw new IllegalArgumentException("maxCacheSize must be a positive number");
        }

        static int c(com.d.a.b.e$a arg0) {
            return arg0.d;
        }

        public e c() {
            this.d();
            return new e(this, null);
        }

        static int d(com.d.a.b.e$a arg0) {
            return arg0.e;
        }

        private void d() {
            if(this.h == null) {
                this.h = com.d.a.b.a.a(this.l, this.m, this.o);
            }
            else {
                this.j = true;
            }

            if(this.i == null) {
                this.i = com.d.a.b.a.a(this.l, this.m, this.o);
            }
            else {
                this.k = true;
            }

            if(this.t == null) {
                if(this.u == null) {
                    this.u = com.d.a.b.a.b();
                }

                this.t = com.d.a.b.a.a(this.b, this.u, this.q, this.r);
            }

            if(this.s == null) {
                this.s = com.d.a.b.a.a(this.b, this.p);
            }

            if(this.n) {
                this.s = new com.d.a.a.b.a.a(this.s, d.a());
            }

            if(this.v == null) {
                this.v = com.d.a.b.a.a(this.b);
            }

            if(this.w == null) {
                this.w = com.d.a.b.a.a(this.y);
            }

            if(this.x == null) {
                this.x = c.t();
            }
        }

        static int e(com.d.a.b.e$a arg0) {
            return arg0.f;
        }

        static com.d.a.b.g.a f(com.d.a.b.e$a arg0) {
            return arg0.g;
        }

        static Executor g(com.d.a.b.e$a arg0) {
            return arg0.h;
        }

        static Executor h(com.d.a.b.e$a arg0) {
            return arg0.i;
        }

        static int i(com.d.a.b.e$a arg0) {
            return arg0.l;
        }

        static int j(com.d.a.b.e$a arg0) {
            return arg0.m;
        }

        static g k(com.d.a.b.e$a arg0) {
            return arg0.o;
        }

        static com.d.a.a.a.a l(com.d.a.b.e$a arg0) {
            return arg0.t;
        }

        static com.d.a.a.b.a m(com.d.a.b.e$a arg0) {
            return arg0.s;
        }

        static c n(com.d.a.b.e$a arg0) {
            return arg0.x;
        }

        static b o(com.d.a.b.e$a arg0) {
            return arg0.v;
        }

        static com.d.a.b.b.b p(com.d.a.b.e$a arg0) {
            return arg0.w;
        }

        static boolean q(com.d.a.b.e$a arg0) {
            return arg0.j;
        }

        static boolean r(com.d.a.b.e$a arg0) {
            return arg0.k;
        }

        static boolean s(com.d.a.b.e$a arg0) {
            return arg0.y;
        }
    }

    class com.d.a.b.e$b implements b {
        private final b a;

        public com.d.a.b.e$b(b arg1) {
            super();
            this.a = arg1;
        }

        public InputStream a(String arg3, Object arg4) {
            switch(com.d.a.b.e$1.a[a.a(arg3).ordinal()]) {
                case 1: 
                case 2: {
                    goto label_8;
                }
            }

            return this.a.a(arg3, arg4);
        label_8:
            throw new IllegalStateException();
        }
    }

    class com.d.a.b.e$c implements b {
        private final b a;

        public com.d.a.b.e$c(b arg1) {
            super();
            this.a = arg1;
        }

        public InputStream a(String arg2, Object arg3) {
            InputStream v3 = this.a.a(arg2, arg3);
            switch(com.d.a.b.e$1.a[a.a(arg2).ordinal()]) {
                case 1: 
                case 2: {
                    goto label_8;
                }
            }

            return v3;
        label_8:
            return new com.d.a.b.a.c(v3);
        }
    }

    final Resources a;
    final int b;
    final int c;
    final int d;
    final int e;
    final com.d.a.b.g.a f;
    final Executor g;
    final Executor h;
    final boolean i;
    final boolean j;
    final int k;
    final int l;
    final g m;
    final com.d.a.a.b.a n;
    final com.d.a.a.a.a o;
    final b p;
    final com.d.a.b.b.b q;
    final c r;
    final b s;
    final b t;

    private e(com.d.a.b.e$a arg3) {
        super();
        this.a = com.d.a.b.e$a.a(arg3).getResources();
        this.b = com.d.a.b.e$a.b(arg3);
        this.c = com.d.a.b.e$a.c(arg3);
        this.d = com.d.a.b.e$a.d(arg3);
        this.e = com.d.a.b.e$a.e(arg3);
        this.f = com.d.a.b.e$a.f(arg3);
        this.g = com.d.a.b.e$a.g(arg3);
        this.h = com.d.a.b.e$a.h(arg3);
        this.k = com.d.a.b.e$a.i(arg3);
        this.l = com.d.a.b.e$a.j(arg3);
        this.m = com.d.a.b.e$a.k(arg3);
        this.o = com.d.a.b.e$a.l(arg3);
        this.n = com.d.a.b.e$a.m(arg3);
        this.r = com.d.a.b.e$a.n(arg3);
        this.p = com.d.a.b.e$a.o(arg3);
        this.q = com.d.a.b.e$a.p(arg3);
        this.i = com.d.a.b.e$a.q(arg3);
        this.j = com.d.a.b.e$a.r(arg3);
        this.s = new com.d.a.b.e$b(this.p);
        this.t = new com.d.a.b.e$c(this.p);
        com.d.a.c.c.a(com.d.a.b.e$a.s(arg3));
    }

    e(com.d.a.b.e$a arg1, com.d.a.b.e$1 arg2) {
        this(arg1);
    }

    com.d.a.b.a.e a() {
        DisplayMetrics v0 = this.a.getDisplayMetrics();
        int v1 = this.b;
        if(v1 <= 0) {
            v1 = v0.widthPixels;
        }

        int v2 = this.c;
        if(v2 <= 0) {
            v2 = v0.heightPixels;
        }

        return new com.d.a.b.a.e(v1, v2);
    }
}

