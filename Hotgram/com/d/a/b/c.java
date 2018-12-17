package com.d.a.b;

import android.content.res.Resources;
import android.graphics.Bitmap$Config;
import android.graphics.BitmapFactory$Options;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import com.d.a.b.a.d;

public final class c {
    class com.d.a.b.c$1 {
    }

    public class a {
        private int a;
        private int b;
        private int c;
        private Drawable d;
        private Drawable e;
        private Drawable f;
        private boolean g;
        private boolean h;
        private boolean i;
        private d j;
        private BitmapFactory$Options k;
        private int l;
        private boolean m;
        private Object n;
        private com.d.a.b.g.a o;
        private com.d.a.b.g.a p;
        private com.d.a.b.c.a q;
        private Handler r;
        private boolean s;

        public a() {
            super();
            this.a = 0;
            this.b = 0;
            this.c = 0;
            this.d = null;
            this.e = null;
            this.f = null;
            this.g = false;
            this.h = false;
            this.i = false;
            this.j = d.c;
            this.k = new BitmapFactory$Options();
            this.l = 0;
            this.m = false;
            this.n = null;
            this.o = null;
            this.p = null;
            this.q = com.d.a.b.a.c();
            this.r = null;
            this.s = false;
        }

        static int a(a arg0) {
            return arg0.a;
        }

        public c a() {
            return new c(this, null);
        }

        public a a(int arg1) {
            this.a = arg1;
            return this;
        }

        public a a(Bitmap$Config arg2) {
            if(arg2 != null) {
                this.k.inPreferredConfig = arg2;
                return this;
            }

            throw new IllegalArgumentException("bitmapConfig can\'t be null");
        }

        public a a(d arg1) {
            this.j = arg1;
            return this;
        }

        public a a(c arg2) {
            this.a = c.a(arg2);
            this.b = c.b(arg2);
            this.c = c.c(arg2);
            this.d = c.d(arg2);
            this.e = c.e(arg2);
            this.f = c.f(arg2);
            this.g = c.g(arg2);
            this.h = c.h(arg2);
            this.i = c.i(arg2);
            this.j = c.j(arg2);
            this.k = c.k(arg2);
            this.l = c.l(arg2);
            this.m = c.m(arg2);
            this.n = c.n(arg2);
            this.o = c.o(arg2);
            this.p = c.p(arg2);
            this.q = c.q(arg2);
            this.r = c.r(arg2);
            this.s = c.s(arg2);
            return this;
        }

        public a a(boolean arg1) {
            this.h = arg1;
            return this;
        }

        static int b(a arg0) {
            return arg0.b;
        }

        public a b(int arg1) {
            this.b = arg1;
            return this;
        }

        public a b(boolean arg1) {
            this.i = arg1;
            return this;
        }

        static int c(a arg0) {
            return arg0.c;
        }

        public a c(int arg1) {
            this.c = arg1;
            return this;
        }

        public a c(boolean arg1) {
            this.m = arg1;
            return this;
        }

        static Drawable d(a arg0) {
            return arg0.d;
        }

        static Drawable e(a arg0) {
            return arg0.e;
        }

        static Drawable f(a arg0) {
            return arg0.f;
        }

        static boolean g(a arg0) {
            return arg0.g;
        }

        static boolean h(a arg0) {
            return arg0.h;
        }

        static boolean i(a arg0) {
            return arg0.i;
        }

        static d j(a arg0) {
            return arg0.j;
        }

        static BitmapFactory$Options k(a arg0) {
            return arg0.k;
        }

        static int l(a arg0) {
            return arg0.l;
        }

        static boolean m(a arg0) {
            return arg0.m;
        }

        static Object n(a arg0) {
            return arg0.n;
        }

        static com.d.a.b.g.a o(a arg0) {
            return arg0.o;
        }

        static com.d.a.b.g.a p(a arg0) {
            return arg0.p;
        }

        static com.d.a.b.c.a q(a arg0) {
            return arg0.q;
        }

        static Handler r(a arg0) {
            return arg0.r;
        }

        static boolean s(a arg0) {
            return arg0.s;
        }
    }

    private final int a;
    private final int b;
    private final int c;
    private final Drawable d;
    private final Drawable e;
    private final Drawable f;
    private final boolean g;
    private final boolean h;
    private final boolean i;
    private final d j;
    private final BitmapFactory$Options k;
    private final int l;
    private final boolean m;
    private final Object n;
    private final com.d.a.b.g.a o;
    private final com.d.a.b.g.a p;
    private final com.d.a.b.c.a q;
    private final Handler r;
    private final boolean s;

    private c(a arg2) {
        super();
        this.a = a.a(arg2);
        this.b = a.b(arg2);
        this.c = a.c(arg2);
        this.d = a.d(arg2);
        this.e = a.e(arg2);
        this.f = a.f(arg2);
        this.g = a.g(arg2);
        this.h = a.h(arg2);
        this.i = a.i(arg2);
        this.j = a.j(arg2);
        this.k = a.k(arg2);
        this.l = a.l(arg2);
        this.m = a.m(arg2);
        this.n = a.n(arg2);
        this.o = a.o(arg2);
        this.p = a.p(arg2);
        this.q = a.q(arg2);
        this.r = a.r(arg2);
        this.s = a.s(arg2);
    }

    c(a arg1, com.d.a.b.c$1 arg2) {
        this(arg1);
    }

    static int a(c arg0) {
        return arg0.a;
    }

    public Drawable a(Resources arg2) {
        Drawable v2 = this.a != 0 ? arg2.getDrawable(this.a) : this.d;
        return v2;
    }

    public boolean a() {
        boolean v0 = this.d != null || this.a != 0 ? true : false;
        return v0;
    }

    static int b(c arg0) {
        return arg0.b;
    }

    public Drawable b(Resources arg2) {
        Drawable v2 = this.b != 0 ? arg2.getDrawable(this.b) : this.e;
        return v2;
    }

    public boolean b() {
        boolean v0 = this.e != null || this.b != 0 ? true : false;
        return v0;
    }

    static int c(c arg0) {
        return arg0.c;
    }

    public Drawable c(Resources arg2) {
        Drawable v2 = this.c != 0 ? arg2.getDrawable(this.c) : this.f;
        return v2;
    }

    public boolean c() {
        boolean v0 = this.f != null || this.c != 0 ? true : false;
        return v0;
    }

    static Drawable d(c arg0) {
        return arg0.d;
    }

    public boolean d() {
        boolean v0 = this.o != null ? true : false;
        return v0;
    }

    static Drawable e(c arg0) {
        return arg0.e;
    }

    public boolean e() {
        boolean v0 = this.p != null ? true : false;
        return v0;
    }

    static Drawable f(c arg0) {
        return arg0.f;
    }

    public boolean f() {
        boolean v0 = this.l > 0 ? true : false;
        return v0;
    }

    static boolean g(c arg0) {
        return arg0.g;
    }

    public boolean g() {
        return this.g;
    }

    static boolean h(c arg0) {
        return arg0.h;
    }

    public boolean h() {
        return this.h;
    }

    static boolean i(c arg0) {
        return arg0.i;
    }

    public boolean i() {
        return this.i;
    }

    public d j() {
        return this.j;
    }

    static d j(c arg0) {
        return arg0.j;
    }

    public BitmapFactory$Options k() {
        return this.k;
    }

    static BitmapFactory$Options k(c arg0) {
        return arg0.k;
    }

    static int l(c arg0) {
        return arg0.l;
    }

    public int l() {
        return this.l;
    }

    public boolean m() {
        return this.m;
    }

    static boolean m(c arg0) {
        return arg0.m;
    }

    public Object n() {
        return this.n;
    }

    static Object n(c arg0) {
        return arg0.n;
    }

    static com.d.a.b.g.a o(c arg0) {
        return arg0.o;
    }

    public com.d.a.b.g.a o() {
        return this.o;
    }

    static com.d.a.b.g.a p(c arg0) {
        return arg0.p;
    }

    public com.d.a.b.g.a p() {
        return this.p;
    }

    public com.d.a.b.c.a q() {
        return this.q;
    }

    static com.d.a.b.c.a q(c arg0) {
        return arg0.q;
    }

    static Handler r(c arg0) {
        return arg0.r;
    }

    public Handler r() {
        return this.r;
    }

    static boolean s(c arg0) {
        return arg0.s;
    }

    boolean s() {
        return this.s;
    }

    public static c t() {
        return new a().a();
    }
}

