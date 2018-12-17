package okhttp3;

import java.io.Closeable;
import javax.annotation.Nullable;

public final class ac implements Closeable {
    public class a {
        aa a;
        y b;
        int c;
        String d;
        @Nullable r e;
        okhttp3.s$a f;
        ad g;
        ac h;
        ac i;
        ac j;
        long k;
        long l;

        a(ac arg3) {
            super();
            this.c = -1;
            this.a = arg3.a;
            this.b = arg3.b;
            this.c = arg3.c;
            this.d = arg3.d;
            this.e = arg3.e;
            this.f = arg3.f.b();
            this.g = arg3.g;
            this.h = arg3.h;
            this.i = arg3.i;
            this.j = arg3.j;
            this.k = arg3.k;
            this.l = arg3.l;
        }

        public a() {
            super();
            this.c = -1;
            this.f = new okhttp3.s$a();
        }

        private void a(String arg2, ac arg3) {
            StringBuilder v0;
            if(arg3.g == null) {
                if(arg3.h == null) {
                    if(arg3.i == null) {
                        if(arg3.j == null) {
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

        public a a(int arg1) {
            this.c = arg1;
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

        public a a(String arg2, String arg3) {
            this.f.a(arg2, arg3);
            return this;
        }

        public a a(aa arg1) {
            this.a = arg1;
            return this;
        }

        public a a(@Nullable ac arg2) {
            if(arg2 != null) {
                this.a("networkResponse", arg2);
            }

            this.h = arg2;
            return this;
        }

        public a a(@Nullable ad arg1) {
            this.g = arg1;
            return this;
        }

        public a a(@Nullable r arg1) {
            this.e = arg1;
            return this;
        }

        public a a(s arg1) {
            this.f = arg1.b();
            return this;
        }

        public a a(y arg1) {
            this.b = arg1;
            return this;
        }

        public ac a() {
            if(this.a != null) {
                if(this.b != null) {
                    if(this.c >= 0) {
                        if(this.d != null) {
                            return new ac(this);
                        }

                        throw new IllegalStateException("message == null");
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

        public a b(long arg1) {
            this.l = arg1;
            return this;
        }

        public a b(@Nullable ac arg2) {
            if(arg2 != null) {
                this.a("cacheResponse", arg2);
            }

            this.i = arg2;
            return this;
        }

        public a c(@Nullable ac arg1) {
            if(arg1 != null) {
                this.d(arg1);
            }

            this.j = arg1;
            return this;
        }

        private void d(ac arg2) {
            if(arg2.g == null) {
                return;
            }

            throw new IllegalArgumentException("priorResponse.body != null");
        }
    }

    final aa a;
    final y b;
    final int c;
    final String d;
    @Nullable final r e;
    final s f;
    @Nullable final ad g;
    @Nullable final ac h;
    @Nullable final ac i;
    @Nullable final ac j;
    final long k;
    final long l;
    private volatile d m;

    ac(a arg3) {
        super();
        this.a = arg3.a;
        this.b = arg3.b;
        this.c = arg3.c;
        this.d = arg3.d;
        this.e = arg3.e;
        this.f = arg3.f.a();
        this.g = arg3.g;
        this.h = arg3.h;
        this.i = arg3.i;
        this.j = arg3.j;
        this.k = arg3.k;
        this.l = arg3.l;
    }

    @Nullable public String a(String arg2) {
        return this.a(arg2, null);
    }

    @Nullable public String a(String arg2, @Nullable String arg3) {
        arg2 = this.f.a(arg2);
        if(arg2 != null) {
        }
        else {
            arg2 = arg3;
        }

        return arg2;
    }

    public aa a() {
        return this.a;
    }

    public int b() {
        return this.c;
    }

    public boolean c() {
        boolean v0 = this.c < 200 || this.c >= 300 ? false : true;
        return v0;
    }

    public void close() {
        if(this.g != null) {
            this.g.close();
            return;
        }

        throw new IllegalStateException("response is not eligible for a body and must not be closed");
    }

    public r d() {
        return this.e;
    }

    public s e() {
        return this.f;
    }

    @Nullable public ad f() {
        return this.g;
    }

    public a g() {
        return new a(this);
    }

    @Nullable public ac h() {
        return this.j;
    }

    public d i() {
        d v0 = this.m;
        if(v0 != null) {
        }
        else {
            v0 = d.a(this.f);
            this.m = v0;
        }

        return v0;
    }

    public long j() {
        return this.k;
    }

    public long k() {
        return this.l;
    }

    public String toString() {
        return "Response{protocol=" + this.b + ", code=" + this.c + ", message=" + this.d + ", url=" + this.a.a() + '}';
    }
}

