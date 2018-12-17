package okhttp3.internal.a;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import okhttp3.aa;
import okhttp3.ac;
import okhttp3.internal.c.d;
import okhttp3.internal.c.e;
import okhttp3.s;

public final class c {
    public class a {
        final long a;
        final aa b;
        final ac c;
        private Date d;
        private String e;
        private Date f;
        private String g;
        private Date h;
        private long i;
        private long j;
        private String k;
        private int l;

        public a(long arg4, aa arg6, ac arg7) {
            super();
            int v0 = -1;
            this.l = v0;
            this.a = arg4;
            this.b = arg6;
            this.c = arg7;
            if(arg7 != null) {
                this.i = arg7.j();
                this.j = arg7.k();
                s v4 = arg7.e();
                int v5 = 0;
                int v6 = v4.a();
                while(v5 < v6) {
                    String v7 = v4.a(v5);
                    String v1 = v4.b(v5);
                    if("Date".equalsIgnoreCase(v7)) {
                        this.d = d.a(v1);
                        this.e = v1;
                    }
                    else if("Expires".equalsIgnoreCase(v7)) {
                        this.h = d.a(v1);
                    }
                    else if("Last-Modified".equalsIgnoreCase(v7)) {
                        this.f = d.a(v1);
                        this.g = v1;
                    }
                    else if("ETag".equalsIgnoreCase(v7)) {
                        this.k = v1;
                    }
                    else if("Age".equalsIgnoreCase(v7)) {
                        this.l = e.b(v1, v0);
                    }

                    ++v5;
                }
            }
        }

        private static boolean a(aa arg1) {
            boolean v1 = arg1.a("If-Modified-Since") != null || arg1.a("If-None-Match") != null ? true : false;
            return v1;
        }

        public c a() {
            c v0 = this.b();
            if(v0.a != null && (this.b.f().i())) {
                v0 = new c(null, null);
            }

            return v0;
        }

        private c b() {
            String v1_1;
            String v0_2;
            ac v1 = null;
            if(this.c == null) {
                return new c(this.b, v1);
            }

            if((this.b.g()) && this.c.d() == null) {
                return new c(this.b, v1);
            }

            if(!c.a(this.c, this.b)) {
                return new c(this.b, v1);
            }

            okhttp3.d v0 = this.b.f();
            if(!v0.a()) {
                if(a.a(this.b)) {
                }
                else {
                    okhttp3.d v2 = this.c.i();
                    if(v2.j()) {
                        return new c(((aa)v1), this.c);
                    }
                    else {
                        long v3 = this.d();
                        long v5 = this.c();
                        int v8 = -1;
                        if(v0.c() != v8) {
                            v5 = Math.min(v5, TimeUnit.SECONDS.toMillis(((long)v0.c())));
                        }

                        long v9 = 0;
                        long v11 = v0.h() != v8 ? TimeUnit.SECONDS.toMillis(((long)v0.h())) : v9;
                        if(!v2.f() && v0.g() != v8) {
                            v9 = TimeUnit.SECONDS.toMillis(((long)v0.g()));
                        }

                        if(!v2.a()) {
                            v11 += v3;
                            if(v11 < v9 + v5) {
                                okhttp3.ac$a v0_1 = this.c.g();
                                if(v11 >= v5) {
                                    v0_1.a("Warning", "110 HttpURLConnection \"Response is stale\"");
                                }

                                if(v3 > 86400000 && (this.e())) {
                                    v0_1.a("Warning", "113 HttpURLConnection \"Heuristic expiration\"");
                                }

                                return new c(((aa)v1), v0_1.a());
                            }
                        }

                        if(this.k != null) {
                            v0_2 = "If-None-Match";
                            v1_1 = this.k;
                        }
                        else if(this.f != null) {
                            v0_2 = "If-Modified-Since";
                            v1_1 = this.g;
                        }
                        else if(this.d != null) {
                            v0_2 = "If-Modified-Since";
                            v1_1 = this.e;
                        }
                        else {
                            goto label_118;
                        }

                        okhttp3.s$a v2_1 = this.b.c().b();
                        okhttp3.internal.a.a.a(v2_1, v0_2, v1_1);
                        return new c(this.b.e().a(v2_1.a()).b(), this.c);
                    label_118:
                        return new c(this.b, v1);
                    }
                }
            }

            return new c(this.b, v1);
        }

        private long c() {
            long v3;
            okhttp3.d v0 = this.c.i();
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

            if(this.f != null && this.c.a().a().l() == null) {
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
            boolean v0 = this.c.i().c() != -1 || this.h != null ? false : true;
            return v0;
        }
    }

    @Nullable public final aa a;
    @Nullable public final ac b;

    c(aa arg1, ac arg2) {
        super();
        this.a = arg1;
        this.b = arg2;
    }

    public static boolean a(ac arg3, aa arg4) {
        boolean v1 = false;
        switch(arg3.b()) {
            case 302: 
            case 307: {
                if(arg3.a("Expires") != null) {
                    goto label_17;
                }

                if(arg3.i().c() != -1) {
                    goto label_17;
                }

                if(arg3.i().e()) {
                    goto label_17;
                }

                if(!arg3.i().d()) {
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
                if(arg3.i().b()) {
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

