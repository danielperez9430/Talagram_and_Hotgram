package com.persianswitch.a.a.a;

import com.persianswitch.b.c;
import com.persianswitch.b.e;
import com.persianswitch.b.l;
import com.persianswitch.b.s;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map$Entry;
import java.util.Map;

final class h {
    final class a {
        f[] a;
        int b;
        int c;
        int d;
        private final List e;
        private final e f;
        private int g;
        private int h;

        a(int arg2, s arg3) {
            super();
            this.e = new ArrayList();
            this.a = new f[8];
            this.b = this.a.length - 1;
            this.c = 0;
            this.d = 0;
            this.g = arg2;
            this.h = arg2;
            this.f = l.a(arg3);
        }

        private void a(int arg6, f arg7) {
            this.e.add(arg7);
            int v0 = arg7.j;
            int v1 = -1;
            if(arg6 != v1) {
                v0 -= this.a[this.d(arg6)].j;
            }

            if(v0 > this.h) {
                this.e();
                return;
            }

            int v2 = this.b(this.d + v0 - this.h);
            if(arg6 == v1) {
                if(this.c + 1 > this.a.length) {
                    f[] v6 = new f[this.a.length * 2];
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
                this.a[arg6 + (this.d(arg6) + v2)] = arg7;
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
                if(this.f.e()) {
                    return;
                }

                int v0 = this.f.h() & 255;
                int v1 = 128;
                if(v0 == v1) {
                    break;
                }

                if((v0 & 128) == v1) {
                    this.c(this.a(v0, 127) - 1);
                    continue;
                }

                v1 = 64;
                if(v0 == v1) {
                    this.g();
                    continue;
                }

                if((v0 & 64) == v1) {
                    this.f(this.a(v0, 63) - 1);
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
                        this.e(this.a(v0, 15) - 1);
                        continue;
                    }
                }

                this.f();
            }

            throw new IOException("index == 0");
        }

        void a(int arg1) {
            this.g = arg1;
            this.h = arg1;
            this.d();
        }

        private int b(int arg6) {
            int v0 = 0;
            if(arg6 > 0) {
                int v1;
                for(v1 = this.a.length - 1; v1 >= this.b; --v1) {
                    if(arg6 <= 0) {
                        break;
                    }

                    arg6 -= this.a[v1].j;
                    this.d -= this.a[v1].j;
                    --this.c;
                    ++v0;
                }

                System.arraycopy(this.a, this.b + 1, this.a, this.b + 1 + v0, this.c);
                this.b += v0;
            }

            return v0;
        }

        public List b() {
            ArrayList v0 = new ArrayList(this.e);
            this.e.clear();
            return ((List)v0);
        }

        private void c(int arg4) {
            if(this.h(arg4)) {
                this.e.add(h.a()[arg4]);
            }
            else {
                int v0 = this.d(arg4 - h.a().length);
                if(v0 >= 0 && v0 <= this.a.length - 1) {
                    this.e.add(this.a[v0]);
                    return;
                }

                goto label_21;
            }

            return;
        label_21:
            StringBuilder v1 = new StringBuilder();
            v1.append("Header index too large ");
            v1.append(arg4 + 1);
            throw new IOException(v1.toString());
        }

        com.persianswitch.b.f c() {
            int v0 = this.h();
            int v1 = (v0 & 128) == 128 ? 1 : 0;
            v0 = this.a(v0, 127);
            if(v1 != 0) {
                return com.persianswitch.b.f.a(j.a().a(this.f.f(((long)v0))));
            }

            return this.f.c(((long)v0));
        }

        private int d(int arg2) {
            return this.b + 1 + arg2;
        }

        private void d() {
            if(this.h < this.d) {
                if(this.h == 0) {
                    this.e();
                }
                else {
                    this.b(this.d - this.h);
                }
            }
        }

        private void e() {
            this.e.clear();
            Arrays.fill(this.a, null);
            this.b = this.a.length - 1;
            this.c = 0;
            this.d = 0;
        }

        private void e(int arg4) {
            this.e.add(new f(this.g(arg4), this.c()));
        }

        private void f() {
            this.e.add(new f(h.a(this.c()), this.c()));
        }

        private void f(int arg3) {
            this.a(-1, new f(this.g(arg3), this.c()));
        }

        private com.persianswitch.b.f g(int arg3) {
            f v3 = this.h(arg3) ? h.a()[arg3] : this.a[this.d(arg3 - h.a().length)];
            return v3.h;
        }

        private void g() {
            this.a(-1, new f(h.a(this.c()), this.c()));
        }

        private boolean h(int arg3) {
            boolean v0 = true;
            if(arg3 < 0 || arg3 > h.a().length - 1) {
                v0 = false;
            }
            else {
            }

            return v0;
        }

        private int h() {
            return this.f.h() & 255;
        }
    }

    final class b {
        f[] a;
        int b;
        int c;
        int d;
        private final c e;
        private final Map f;
        private int g;
        private int h;

        b(int arg2, c arg3) {
            super();
            this.f = new LinkedHashMap();
            this.a = new f[8];
            this.b = this.a.length - 1;
            this.c = 0;
            this.d = 0;
            this.g = arg2;
            this.h = arg2;
            this.e = arg3;
        }

        b(c arg2) {
            this(4096, arg2);
        }

        private int a(int arg6) {
            int v0 = 0;
            if(arg6 > 0) {
                int v1;
                for(v1 = this.a.length - 1; v1 >= this.b; --v1) {
                    if(arg6 <= 0) {
                        break;
                    }

                    arg6 -= this.a[v1].j;
                    this.d -= this.a[v1].j;
                    --this.c;
                    ++v0;
                }

                System.arraycopy(this.a, this.b + 1, this.a, this.b + 1 + v0, this.c);
                Iterator v6 = this.f.entrySet().iterator();
                while(v6.hasNext()) {
                    Object v1_1 = v6.next();
                    ((Map$Entry)v1_1).setValue(Integer.valueOf(((Map$Entry)v1_1).getValue().intValue() + v0));
                }

                this.b += v0;
            }

            return v0;
        }

        private void a() {
            Arrays.fill(this.a, null);
            this.f.clear();
            this.b = this.a.length - 1;
            this.c = 0;
            this.d = 0;
        }

        com.persianswitch.b.f a(f arg5) {
            byte[] v0 = new byte[arg5.h.e() + 1 + arg5.i.e()];
            System.arraycopy(arg5.h.f(), 0, v0, 0, arg5.h.e());
            v0[arg5.h.e()] = 58;
            System.arraycopy(arg5.i.f(), 0, v0, arg5.h.e() + 1, arg5.i.e());
            return com.persianswitch.b.f.a(v0);
        }

        void a(int arg2, int arg3, int arg4) {
            c v3;
            if(arg2 < arg3) {
                v3 = this.e;
                arg2 |= arg4;
            }
            else {
                this.e.b(arg4 | arg3);
                arg2 -= arg3;
                while(true) {
                    arg3 = 128;
                    if(arg2 < arg3) {
                        break;
                    }

                    this.e.b(arg3 | arg2 & 127);
                    arg2 >>>= 7;
                }

                v3 = this.e;
            }

            v3.b(arg2);
        }

        void a(com.persianswitch.b.f arg4) {
            this.a(arg4.e(), 127, 0);
            this.e.a(arg4);
        }

        void a(List arg9) {
            int v0 = arg9.size();
            int v2;
            for(v2 = 0; v2 < v0; ++v2) {
                Object v3 = arg9.get(v2);
                com.persianswitch.b.f v4 = ((f)v3).h.d();
                com.persianswitch.b.f v5 = ((f)v3).i;
                Object v6 = h.b().get(v4);
                if(v6 != null) {
                    this.a(((Integer)v6).intValue() + 1, 15, 0);
                    this.a(v5);
                }
                else {
                    v6 = this.f.get(this.a(((f)v3)));
                    if(v6 != null) {
                        this.a(this.a.length - ((Integer)v6).intValue() + h.a().length, 127, 128);
                    }
                    else {
                        this.e.b(64);
                        this.a(v4);
                        this.a(v5);
                        this.b(((f)v3));
                    }
                }
            }
        }

        private void b(f arg7) {
            int v0 = arg7.j;
            if(v0 > this.h) {
                this.a();
                return;
            }

            this.a(this.d + v0 - this.h);
            if(this.c + 1 > this.a.length) {
                f[] v1 = new f[this.a.length * 2];
                System.arraycopy(this.a, 0, v1, this.a.length, this.a.length);
                Iterator v2 = this.f.entrySet().iterator();
                while(v2.hasNext()) {
                    Object v3 = v2.next();
                    ((Map$Entry)v3).setValue(Integer.valueOf(((Map$Entry)v3).getValue().intValue() + this.a.length));
                }

                this.b = this.a.length - 1;
                this.a = v1;
            }

            int v1_1 = this.b;
            this.b = v1_1 - 1;
            this.a[v1_1] = arg7;
            this.f.put(this.a(arg7), Integer.valueOf(v1_1));
            ++this.c;
            this.d += v0;
        }
    }

    private static final f[] a;
    private static final Map b;

    static {
        h.a = new f[]{new f(f.e, ""), new f(f.b, "GET"), new f(f.b, "POST"), new f(f.c, "/"), new f(f.c, "/index.html"), new f(f.d, "http"), new f(f.d, "https"), new f(f.a, "200"), new f(f.a, "204"), new f(f.a, "206"), new f(f.a, "304"), new f(f.a, "400"), new f(f.a, "404"), new f(f.a, "500"), new f("accept-charset", ""), new f("accept-encoding", "gzip, deflate"), new f("accept-language", ""), new f("accept-ranges", ""), new f("accept", ""), new f("access-control-allow-origin", ""), new f("age", ""), new f("allow", ""), new f("authorization", ""), new f("cache-control", ""), new f("content-disposition", ""), new f("content-encoding", ""), new f("content-language", ""), new f("content-length", ""), new f("content-location", ""), new f("content-range", ""), new f("content-type", ""), new f("cookie", ""), new f("date", ""), new f("etag", ""), new f("expect", ""), new f("expires", ""), new f("from", ""), new f("host", ""), new f("if-match", ""), new f("if-modified-since", ""), new f("if-none-match", ""), new f("if-range", ""), new f("if-unmodified-since", ""), new f("last-modified", ""), new f("link", ""), new f("location", ""), new f("max-forwards", ""), new f("proxy-authenticate", ""), new f("proxy-authorization", ""), new f("range", ""), new f("referer", ""), new f("refresh", ""), new f("retry-after", ""), new f("server", ""), new f("set-cookie", ""), new f("strict-transport-security", ""), new f("transfer-encoding", ""), new f("user-agent", ""), new f("vary", ""), new f("via", ""), new f("www-authenticate", "")};
        h.b = h.c();
    }

    static com.persianswitch.b.f a(com.persianswitch.b.f arg0) {
        return h.b(arg0);
    }

    static f[] a() {
        return h.a;
    }

    private static com.persianswitch.b.f b(com.persianswitch.b.f arg4) {
        int v0 = arg4.e();
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

    static Map b() {
        return h.b;
    }

    private static Map c() {
        LinkedHashMap v0 = new LinkedHashMap(h.a.length);
        int v1;
        for(v1 = 0; v1 < h.a.length; ++v1) {
            if(!((Map)v0).containsKey(h.a[v1].h)) {
                ((Map)v0).put(h.a[v1].h, Integer.valueOf(v1));
            }
        }

        return Collections.unmodifiableMap(((Map)v0));
    }
}

