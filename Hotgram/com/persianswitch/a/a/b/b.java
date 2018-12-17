package com.persianswitch.a.a.b;

import com.persianswitch.a.q;
import com.persianswitch.a.x;
import com.persianswitch.a.z;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public final class b {
    class com.persianswitch.a.a.b.b$1 {
    }

    public class a {
        final long a;
        final x b;
        final z c;
        private Date d;
        private String e;
        private Date f;
        private String g;
        private Date h;
        private long i;
        private long j;
        private String k;
        private int l;

        public a(long arg4, x arg6, z arg7) {
            super();
            int v0 = -1;
            this.l = v0;
            this.a = arg4;
            this.b = arg6;
            this.c = arg7;
            if(arg7 != null) {
                this.i = arg7.i();
                this.j = arg7.j();
                q v4 = arg7.e();
                int v5 = 0;
                int v6 = v4.a();
                while(v5 < v6) {
                    String v7 = v4.a(v5);
                    String v1 = v4.b(v5);
                    if("Date".equalsIgnoreCase(v7)) {
                        this.d = g.a(v1);
                        this.e = v1;
                    }
                    else if("Expires".equalsIgnoreCase(v7)) {
                        this.h = g.a(v1);
                    }
                    else if("Last-Modified".equalsIgnoreCase(v7)) {
                        this.f = g.a(v1);
                        this.g = v1;
                    }
                    else if("ETag".equalsIgnoreCase(v7)) {
                        this.k = v1;
                    }
                    else if("Age".equalsIgnoreCase(v7)) {
                        this.l = d.b(v1, v0);
                    }

                    ++v5;
                }
            }
        }

        private static boolean a(x arg1) {
            boolean v1 = arg1.a("If-Modified-Since") != null || arg1.a("If-None-Match") != null ? true : false;
            return v1;
        }

        public b a() {
            b v0 = this.b();
            if(v0.a != null && (this.b.f().i())) {
                v0 = new b(null, null, null);
            }

            return v0;
        }

        private b b() {
            String v3;
            String v2_1;
            z v1 = null;
            if(this.c == null) {
                return new b(this.b, v1, ((com.persianswitch.a.a.b.b$1)v1));
            }

            if((this.b.g()) && this.c.d() == null) {
                return new b(this.b, v1, ((com.persianswitch.a.a.b.b$1)v1));
            }

            if(!b.a(this.c, this.b)) {
                return new b(this.b, v1, ((com.persianswitch.a.a.b.b$1)v1));
            }

            com.persianswitch.a.d v0 = this.b.f();
            if(!v0.a()) {
                if(a.a(this.b)) {
                }
                else {
                    long v2 = this.d();
                    long v4 = this.c();
                    int v7 = -1;
                    if(v0.c() != v7) {
                        v4 = Math.min(v4, TimeUnit.SECONDS.toMillis(((long)v0.c())));
                    }

                    long v8 = 0;
                    long v10 = v0.h() != v7 ? TimeUnit.SECONDS.toMillis(((long)v0.h())) : v8;
                    com.persianswitch.a.d v6 = this.c.h();
                    if(!v6.f() && v0.g() != v7) {
                        v8 = TimeUnit.SECONDS.toMillis(((long)v0.g()));
                    }

                    if(!v6.a()) {
                        v10 += v2;
                        if(v10 < v8 + v4) {
                            com.persianswitch.a.z$a v0_1 = this.c.g();
                            if(v10 >= v4) {
                                v0_1.a("Warning", "110 HttpURLConnection \"Response is stale\"");
                            }

                            if(v2 > 86400000 && (this.e())) {
                                v0_1.a("Warning", "113 HttpURLConnection \"Heuristic expiration\"");
                            }

                            return new b(((x)v1), v0_1.a(), ((com.persianswitch.a.a.b.b$1)v1));
                        }
                    }

                    com.persianswitch.a.x$a v0_2 = this.b.e();
                    if(this.k != null) {
                        v2_1 = "If-None-Match";
                        v3 = this.k;
                        goto label_90;
                    }
                    else if(this.f != null) {
                        v2_1 = "If-Modified-Since";
                        v3 = this.g;
                        goto label_90;
                    }
                    else if(this.d != null) {
                        v2_1 = "If-Modified-Since";
                        v3 = this.e;
                    label_90:
                        v0_2.a(v2_1, v3);
                    }

                    x v0_3 = v0_2.a();
                    b v2_2 = a.a(v0_3) ? new b(v0_3, this.c, ((com.persianswitch.a.a.b.b$1)v1)) : new b(v0_3, v1, ((com.persianswitch.a.a.b.b$1)v1));
                    return v2_2;
                }
            }

            return new b(this.b, v1, ((com.persianswitch.a.a.b.b$1)v1));
        }

        private long c() {
            long v3;
            com.persianswitch.a.d v0 = this.c.h();
            if(v0.c() != -1) {
                return TimeUnit.SECONDS.toMillis(((long)v0.c()));
            }

            long v1 = 0;
            if(this.h != null) {
                v3 = this.d != null ? this.d.getTime() : this.j;
                v3 = this.h.getTime() - v3;
                if(v3 > v1) {
                    v1 = v3;
                }

                return v1;
            }

            if(this.f != null && this.c.a().a().k() == null) {
                v3 = this.d != null ? this.d.getTime() : this.i;
                v3 -= this.f.getTime();
                if(v3 <= v1) {
                    return v1;
                }

                v1 = v3 / 10;
            }

            return v1;
        }

        private long d() {
            long v1 = 0;
            if(this.d != null) {
                v1 = Math.max(v1, this.j - this.d.getTime());
            }

            if(this.l != -1) {
                v1 = Math.max(v1, TimeUnit.SECONDS.toMillis(((long)this.l)));
            }

            return v1 + (this.j - this.i) + (this.a - this.j);
        }

        private boolean e() {
            boolean v0 = this.c.h().c() != -1 || this.h != null ? false : true;
            return v0;
        }
    }

    public final x a;
    public final z b;

    private b(x arg1, z arg2) {
        super();
        this.a = arg1;
        this.b = arg2;
    }

    b(x arg1, z arg2, com.persianswitch.a.a.b.b$1 arg3) {
        this(arg1, arg2);
    }

    public static boolean a(z arg3, x arg4) {
        boolean v1 = false;
        switch(arg3.b()) {
            case 302: 
            case 307: {
                if(arg3.a("Expires") != null) {
                    goto label_17;
                }

                if(arg3.h().c() != -1) {
                    goto label_17;
                }

                if(arg3.h().e()) {
                    goto label_17;
                }

                if(!arg3.h().d()) {
                    return v1;
                }

                goto label_17;
            }
            case 200: 
            case 203: 
            case 204: 
            case 300: 
            case 301: 
            case 308: 
            case 404: 
            case 405: 
            case 410: 
            case 414: 
            case 501: {
            label_17:
                if(arg3.h().b()) {
                    return v1;
                }

                if(arg4.f().b()) {
                    return v1;
                }

                v1 = true;
                break;
            }
            default: {
                break;
            }
        }

        return v1;
    }
}

