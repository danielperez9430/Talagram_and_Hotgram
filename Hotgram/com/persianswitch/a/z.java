package com.persianswitch.a;

import java.io.Closeable;

public final class z implements Closeable {
    class com.persianswitch.a.z$1 {
    }

    public class a {
        private x a;
        private v b;
        private int c;
        private String d;
        private p e;
        private com.persianswitch.a.q$a f;
        private aa g;
        private z h;
        private z i;
        private z j;
        private long k;
        private long l;

        public a() {
            super();
            this.c = -1;
            this.f = new com.persianswitch.a.q$a();
        }

        a(z arg1, com.persianswitch.a.z$1 arg2) {
            this(arg1);
        }

        private a(z arg3) {
            super();
            this.c = -1;
            this.a = z.a(arg3);
            this.b = z.b(arg3);
            this.c = z.c(arg3);
            this.d = z.d(arg3);
            this.e = z.e(arg3);
            this.f = z.f(arg3).b();
            this.g = z.g(arg3);
            this.h = z.h(arg3);
            this.i = z.i(arg3);
            this.j = z.j(arg3);
            this.k = z.k(arg3);
            this.l = z.l(arg3);
        }

        public a a(String arg2, String arg3) {
            this.f.a(arg2, arg3);
            return this;
        }

        public z a() {
            if(this.a != null) {
                if(this.b != null) {
                    if(this.c >= 0) {
                        return new z(this, null);
                    }

                    StringBuilder v1 = new StringBuilder();
                    v1.append("code < 0: ");
                    v1.append(this.c);
                    throw new IllegalStateException(v1.toString());
                }

                throw new IllegalStateException("protocol == null");
            }

            throw new IllegalStateException("request == null");
        }

        public a a(aa arg1) {
            this.g = arg1;
            return this;
        }

        public a a(q arg1) {
            this.f = arg1.b();
            return this;
        }

        public a a(long arg1) {
            this.k = arg1;
            return this;
        }

        public a a(String arg1) {
            this.d = arg1;
            return this;
        }

        public a a(int arg1) {
            this.c = arg1;
            return this;
        }

        public a a(v arg1) {
            this.b = arg1;
            return this;
        }

        public a a(x arg1) {
            this.a = arg1;
            return this;
        }

        public a a(z arg2) {
            if(arg2 != null) {
                this.a("networkResponse", arg2);
            }

            this.h = arg2;
            return this;
        }

        static x a(a arg0) {
            return arg0.a;
        }

        private void a(String arg2, z arg3) {
            StringBuilder v0;
            if(z.g(arg3) == null) {
                if(z.h(arg3) == null) {
                    if(z.i(arg3) == null) {
                        if(z.j(arg3) == null) {
                            return;
                        }

                        v0 = new StringBuilder();
                        v0.append(arg2);
                        v0.append(".priorResponse != null");
                        throw new IllegalArgumentException(v0.toString());
                    }

                    v0 = new StringBuilder();
                    v0.append(arg2);
                    v0.append(".cacheResponse != null");
                    throw new IllegalArgumentException(v0.toString());
                }

                v0 = new StringBuilder();
                v0.append(arg2);
                v0.append(".networkResponse != null");
                throw new IllegalArgumentException(v0.toString());
            }

            v0 = new StringBuilder();
            v0.append(arg2);
            v0.append(".body != null");
            throw new IllegalArgumentException(v0.toString());
        }

        public a a(p arg1) {
            this.e = arg1;
            return this;
        }

        public a b(long arg1) {
            this.l = arg1;
            return this;
        }

        public a b(z arg2) {
            if(arg2 != null) {
                this.a("cacheResponse", arg2);
            }

            this.i = arg2;
            return this;
        }

        static v b(a arg0) {
            return arg0.b;
        }

        public a c(z arg1) {
            if(arg1 != null) {
                this.d(arg1);
            }

            this.j = arg1;
            return this;
        }

        static int c(a arg0) {
            return arg0.c;
        }

        static String d(a arg0) {
            return arg0.d;
        }

        private void d(z arg2) {
            if(z.g(arg2) == null) {
                return;
            }

            throw new IllegalArgumentException("priorResponse.body != null");
        }

        static p e(a arg0) {
            return arg0.e;
        }

        static com.persianswitch.a.q$a f(a arg0) {
            return arg0.f;
        }

        static aa g(a arg0) {
            return arg0.g;
        }

        static z h(a arg0) {
            return arg0.h;
        }

        static z i(a arg0) {
            return arg0.i;
        }

        static z j(a arg0) {
            return arg0.j;
        }

        static long k(a arg2) {
            return arg2.k;
        }

        static long l(a arg2) {
            return arg2.l;
        }
    }

    private final x a;
    private final v b;
    private final int c;
    private final String d;
    private final p e;
    private final q f;
    private final aa g;
    private final z h;
    private final z i;
    private final z j;
    private final long k;
    private final long l;
    private volatile d m;

    private z(a arg3) {
        super();
        this.a = a.a(arg3);
        this.b = a.b(arg3);
        this.c = a.c(arg3);
        this.d = a.d(arg3);
        this.e = a.e(arg3);
        this.f = a.f(arg3).a();
        this.g = a.g(arg3);
        this.h = a.h(arg3);
        this.i = a.i(arg3);
        this.j = a.j(arg3);
        this.k = a.k(arg3);
        this.l = a.l(arg3);
    }

    z(a arg1, com.persianswitch.a.z$1 arg2) {
        this(arg1);
    }

    public String a(String arg2) {
        return this.a(arg2, null);
    }

    public x a() {
        return this.a;
    }

    static x a(z arg0) {
        return arg0.a;
    }

    public String a(String arg2, String arg3) {
        arg2 = this.f.a(arg2);
        if(arg2 != null) {
        }
        else {
            arg2 = arg3;
        }

        return arg2;
    }

    public int b() {
        return this.c;
    }

    static v b(z arg0) {
        return arg0.b;
    }

    static int c(z arg0) {
        return arg0.c;
    }

    public boolean c() {
        boolean v0 = this.c < 200 || this.c >= 300 ? false : true;
        return v0;
    }

    public void close() {
        this.g.close();
    }

    public p d() {
        return this.e;
    }

    static String d(z arg0) {
        return arg0.d;
    }

    public q e() {
        return this.f;
    }

    static p e(z arg0) {
        return arg0.e;
    }

    public aa f() {
        return this.g;
    }

    static q f(z arg0) {
        return arg0.f;
    }

    public a g() {
        return new a(this, null);
    }

    static aa g(z arg0) {
        return arg0.g;
    }

    public d h() {
        d v0 = this.m;
        if(v0 != null) {
        }
        else {
            v0 = d.a(this.f);
            this.m = v0;
        }

        return v0;
    }

    static z h(z arg0) {
        return arg0.h;
    }

    public long i() {
        return this.k;
    }

    static z i(z arg0) {
        return arg0.i;
    }

    public long j() {
        return this.l;
    }

    static z j(z arg0) {
        return arg0.j;
    }

    static long k(z arg2) {
        return arg2.k;
    }

    static long l(z arg2) {
        return arg2.l;
    }

    public String toString() {
        return "Response{protocol=" + this.b + ", code=" + this.c + ", message=" + this.d + ", url=" + this.a.a() + '}';
    }
}

