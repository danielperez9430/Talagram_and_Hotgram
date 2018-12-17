package okhttp3.internal.e;

import e.e;
import e.f;
import e.l;
import e.s;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

final class d {
    final class a {
        c[] a;
        int b;
        int c;
        int d;
        private final List e;
        private final e f;
        private final int g;
        private int h;

        a(int arg2, int arg3, s arg4) {
            super();
            this.e = new ArrayList();
            this.a = new c[8];
            this.b = this.a.length - 1;
            this.c = 0;
            this.d = 0;
            this.g = arg2;
            this.h = arg3;
            this.f = l.a(arg4);
        }

        a(int arg1, s arg2) {
            this(arg1, arg1, arg2);
        }

        private int a(int arg6) {
            int v0 = 0;
            if(arg6 > 0) {
                int v1;
                for(v1 = this.a.length - 1; v1 >= this.b; --v1) {
                    if(arg6 <= 0) {
                        break;
                    }

                    arg6 -= this.a[v1].i;
                    this.d -= this.a[v1].i;
                    --this.c;
                    ++v0;
                }

                System.arraycopy(this.a, this.b + 1, this.a, this.b + 1 + v0, this.c);
                this.b += v0;
            }

            return v0;
        }

        private void a(int arg6, c arg7) {
            this.e.add(arg7);
            int v0 = arg7.i;
            int v1 = -1;
            if(arg6 != v1) {
                v0 -= this.a[this.c(arg6)].i;
            }

            if(v0 > this.h) {
                this.e();
                return;
            }

            int v2 = this.a(this.d + v0 - this.h);
            if(arg6 == v1) {
                if(this.c + 1 > this.a.length) {
                    c[] v6 = new c[this.a.length * 2];
                    System.arraycopy(this.a, 0, v6, this.a.length, this.a.length);
                    this.b = this.a.length - 1;
                    this.a = v6;
                }

                arg6 = this.b;
                this.b = arg6 - 1;
                this.a[arg6] = arg7;
                ++this.c;
            }
            else {
                this.a[arg6 + (this.c(arg6) + v2)] = arg7;
            }

            this.d += v0;
        }

        int a(int arg3, int arg4) {
            int v0;
            arg3 &= arg4;
            if(arg3 < arg4) {
                return arg3;
            }

            for(arg3 = 0; true; arg3 += 7) {
                v0 = this.h();
                if((v0 & 128) == 0) {
                    break;
                }

                arg4 += (v0 & 127) << arg3;
            }

            return arg4 + (v0 << arg3);
        }

        void a() {
            while(true) {
                if(this.f.f()) {
                    return;
                }

                int v0 = this.f.i() & 255;
                int v1 = 128;
                if(v0 == v1) {
                    break;
                }

                if((v0 & 128) == v1) {
                    this.b(this.a(v0, 127) - 1);
                    continue;
                }

                v1 = 64;
                if(v0 == v1) {
                    this.g();
                    continue;
                }

                if((v0 & 64) == v1) {
                    this.e(this.a(v0, 63) - 1);
                    continue;
                }

                if((v0 & 32) == 32) {
                    this.h = this.a(v0, 31);
                    if(this.h >= 0 && this.h <= this.g) {
                        this.d();
                        continue;
                    }

                    StringBuilder v1_1 = new StringBuilder();
                    v1_1.append("Invalid dynamic table size update ");
                    v1_1.append(this.h);
                    throw new IOException(v1_1.toString());
                }

                if(v0 != 16) {
                    if(v0 == 0) {
                    }
                    else {
                        this.d(this.a(v0, 15) - 1);
                        continue;
                    }
                }

                this.f();
            }

            throw new IOException("index == 0");
        }

        private void b(int arg4) {
            if(this.g(arg4)) {
                this.e.add(d.a[arg4]);
            }
            else {
                int v0 = this.c(arg4 - d.a.length);
                if(v0 >= 0 && v0 < this.a.length) {
                    this.e.add(this.a[v0]);
                    return;
                }

                goto label_20;
            }

            return;
        label_20:
            StringBuilder v1 = new StringBuilder();
            v1.append("Header index too large ");
            v1.append(arg4 + 1);
            throw new IOException(v1.toString());
        }

        public List b() {
            ArrayList v0 = new ArrayList(this.e);
            this.e.clear();
            return ((List)v0);
        }

        private int c(int arg2) {
            return this.b + 1 + arg2;
        }

        f c() {
            int v0 = this.h();
            int v1 = (v0 & 128) == 128 ? 1 : 0;
            v0 = this.a(v0, 127);
            if(v1 != 0) {
                return f.a(k.a().a(this.f.g(((long)v0))));
            }

            return this.f.c(((long)v0));
        }

        private void d() {
            if(this.h < this.d) {
                if(this.h == 0) {
                    this.e();
                }
                else {
                    this.a(this.d - this.h);
                }
            }
        }

        private void d(int arg4) {
            this.e.add(new c(this.f(arg4), this.c()));
        }

        private void e() {
            Arrays.fill(this.a, null);
            this.b = this.a.length - 1;
            this.c = 0;
            this.d = 0;
        }

        private void e(int arg3) {
            this.a(-1, new c(this.f(arg3), this.c()));
        }

        private f f(int arg4) {
            c v4;
            if(this.g(arg4)) {
                v4 = d.a[arg4];
            }
            else {
                int v0 = this.c(arg4 - d.a.length);
                if(v0 >= 0 && v0 < this.a.length) {
                    v4 = this.a[v0];
                    goto label_4;
                }

                goto label_17;
            }

        label_4:
            return v4.g;
        label_17:
            StringBuilder v1 = new StringBuilder();
            v1.append("Header index too large ");
            v1.append(arg4 + 1);
            throw new IOException(v1.toString());
        }

        private void f() {
            this.e.add(new c(d.a(this.c()), this.c()));
        }

        private boolean g(int arg3) {
            boolean v0 = true;
            if(arg3 < 0 || arg3 > d.a.length - 1) {
                v0 = false;
            }
            else {
            }

            return v0;
        }

        private void g() {
            this.a(-1, new c(d.a(this.c()), this.c()));
        }

        private int h() {
            return this.f.i() & 255;
        }
    }

    final class b {
        int a;
        int b;
        c[] c;
        int d;
        int e;
        int f;
        private final e.c g;
        private final boolean h;
        private int i;
        private boolean j;

        b(int arg2, boolean arg3, e.c arg4) {
            super();
            this.i = 2147483647;
            this.c = new c[8];
            this.d = this.c.length - 1;
            this.e = 0;
            this.f = 0;
            this.a = arg2;
            this.b = arg2;
            this.h = arg3;
            this.g = arg4;
        }

        b(e.c arg3) {
            this(4096, true, arg3);
        }

        private void a() {
            Arrays.fill(this.c, null);
            this.d = this.c.length - 1;
            this.e = 0;
            this.f = 0;
        }

        private void a(c arg7) {
            int v0 = arg7.i;
            if(v0 > this.b) {
                this.a();
                return;
            }

            this.b(this.f + v0 - this.b);
            if(this.e + 1 > this.c.length) {
                c[] v1 = new c[this.c.length * 2];
                System.arraycopy(this.c, 0, v1, this.c.length, this.c.length);
                this.d = this.c.length - 1;
                this.c = v1;
            }

            int v1_1 = this.d;
            this.d = v1_1 - 1;
            this.c[v1_1] = arg7;
            ++this.e;
            this.f += v0;
        }

        void a(int arg2) {
            this.a = arg2;
            arg2 = Math.min(arg2, 16384);
            if(this.b == arg2) {
                return;
            }

            if(arg2 < this.b) {
                this.i = Math.min(this.i, arg2);
            }

            this.j = true;
            this.b = arg2;
            this.b();
        }

        void a(int arg2, int arg3, int arg4) {
            e.c v3;
            if(arg2 < arg3) {
                v3 = this.g;
                arg2 |= arg4;
            }
            else {
                this.g.b(arg4 | arg3);
                arg2 -= arg3;
                while(true) {
                    arg3 = 128;
                    if(arg2 < arg3) {
                        break;
                    }

                    this.g.b(arg3 | arg2 & 127);
                    arg2 >>>= 7;
                }

                v3 = this.g;
            }

            v3.b(arg2);
        }

        void a(f arg4) {
            int v2;
            int v0_1;
            int v1 = 127;
            if(!this.h || k.a().a(arg4) >= arg4.g()) {
                v0_1 = arg4.g();
                v2 = 0;
            }
            else {
                e.c v0 = new e.c();
                k.a().a(arg4, ((e.d)v0));
                arg4 = v0.o();
                v0_1 = arg4.g();
                v2 = 128;
            }

            this.a(v0_1, v1, v2);
            this.g.a(arg4);
        }

        void a(List arg14) {
            int v9;
            int v6_1;
            if(this.j) {
                int v3 = 32;
                int v4 = 31;
                if(this.i < this.b) {
                    this.a(this.i, v4, v3);
                }

                this.j = false;
                this.i = 2147483647;
                this.a(this.b, v4, v3);
            }

            int v0 = arg14.size();
            int v2;
            for(v2 = 0; v2 < v0; ++v2) {
                Object v3_1 = arg14.get(v2);
                f v4_1 = ((c)v3_1).g.f();
                f v5 = ((c)v3_1).h;
                Object v6 = d.b.get(v4_1);
                int v7 = -1;
                if(v6 != null) {
                    v6_1 = ((Integer)v6).intValue() + 1;
                    if(v6_1 > 1 && v6_1 < 8) {
                        if(okhttp3.internal.c.a(d.a[v6_1 - 1].h, v5)) {
                            v9 = v6_1;
                            goto label_55;
                        }
                        else if(okhttp3.internal.c.a(d.a[v6_1].h, v5)) {
                            v9 = v6_1;
                            ++v6_1;
                            goto label_55;
                        }
                    }

                    v9 = v6_1;
                    v6_1 = -1;
                }
                else {
                    v6_1 = -1;
                    v9 = -1;
                }

            label_55:
                if(v6_1 == v7) {
                    int v10 = this.d + 1;
                    int v8 = this.c.length;
                    while(v10 < v8) {
                        if(okhttp3.internal.c.a(this.c[v10].g, v4_1)) {
                            if(okhttp3.internal.c.a(this.c[v10].h, v5)) {
                                v6_1 = d.a.length + (v10 - this.d);
                                break;
                            }
                            else if(v9 == v7) {
                                v9 = v10 - this.d + d.a.length;
                            }
                        }

                        ++v10;
                    }
                }

                if(v6_1 != v7) {
                    this.a(v6_1, 127, 128);
                }
                else {
                    v6_1 = 64;
                    if(v9 == v7) {
                        this.g.b(v6_1);
                        this.a(v4_1);
                    }
                    else {
                        if((v4_1.a(c.a)) && !c.f.equals(v4_1)) {
                            this.a(v9, 15, 0);
                            this.a(v5);
                            goto label_111;
                        }

                        this.a(v9, 63, v6_1);
                    }

                    this.a(v5);
                    this.a(((c)v3_1));
                }

            label_111:
            }
        }

        private int b(int arg6) {
            int v0 = 0;
            if(arg6 > 0) {
                int v1;
                for(v1 = this.c.length - 1; v1 >= this.d; --v1) {
                    if(arg6 <= 0) {
                        break;
                    }

                    arg6 -= this.c[v1].i;
                    this.f -= this.c[v1].i;
                    --this.e;
                    ++v0;
                }

                System.arraycopy(this.c, this.d + 1, this.c, this.d + 1 + v0, this.e);
                Arrays.fill(this.c, this.d + 1, this.d + 1 + v0, null);
                this.d += v0;
            }

            return v0;
        }

        private void b() {
            if(this.b < this.f) {
                if(this.b == 0) {
                    this.a();
                }
                else {
                    this.b(this.f - this.b);
                }
            }
        }
    }

    static final c[] a;
    static final Map b;

    static {
        d.a = new c[]{new c(c.f, ""), new c(c.c, "GET"), new c(c.c, "POST"), new c(c.d, "/"), new c(c.d, "/index.html"), new c(c.e, "http"), new c(c.e, "https"), new c(c.b, "200"), new c(c.b, "204"), new c(c.b, "206"), new c(c.b, "304"), new c(c.b, "400"), new c(c.b, "404"), new c(c.b, "500"), new c("accept-charset", ""), new c("accept-encoding", "gzip, deflate"), new c("accept-language", ""), new c("accept-ranges", ""), new c("accept", ""), new c("access-control-allow-origin", ""), new c("age", ""), new c("allow", ""), new c("authorization", ""), new c("cache-control", ""), new c("content-disposition", ""), new c("content-encoding", ""), new c("content-language", ""), new c("content-length", ""), new c("content-location", ""), new c("content-range", ""), new c("content-type", ""), new c("cookie", ""), new c("date", ""), new c("etag", ""), new c("expect", ""), new c("expires", ""), new c("from", ""), new c("host", ""), new c("if-match", ""), new c("if-modified-since", ""), new c("if-none-match", ""), new c("if-range", ""), new c("if-unmodified-since", ""), new c("last-modified", ""), new c("link", ""), new c("location", ""), new c("max-forwards", ""), new c("proxy-authenticate", ""), new c("proxy-authorization", ""), new c("range", ""), new c("referer", ""), new c("refresh", ""), new c("retry-after", ""), new c("server", ""), new c("set-cookie", ""), new c("strict-transport-security", ""), new c("transfer-encoding", ""), new c("user-agent", ""), new c("vary", ""), new c("via", ""), new c("www-authenticate", "")};
        d.b = d.a();
    }

    private static Map a() {
        LinkedHashMap v0 = new LinkedHashMap(d.a.length);
        int v1;
        for(v1 = 0; v1 < d.a.length; ++v1) {
            if(!((Map)v0).containsKey(d.a[v1].g)) {
                ((Map)v0).put(d.a[v1].g, Integer.valueOf(v1));
            }
        }

        return Collections.unmodifiableMap(((Map)v0));
    }

    static f a(f arg4) {
        int v0 = arg4.g();
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            int v2 = arg4.a(v1);
            if(v2 >= 65) {
                if(v2 > 90) {
                }
                else {
                    StringBuilder v1_1 = new StringBuilder();
                    v1_1.append("PROTOCOL_ERROR response malformed: mixed case name: ");
                    v1_1.append(arg4.a());
                    throw new IOException(v1_1.toString());
                }
            }
        }

        return arg4;
    }
}

