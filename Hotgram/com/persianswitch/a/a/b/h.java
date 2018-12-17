package com.persianswitch.a.a.b;

import com.persianswitch.a.a.d;
import com.persianswitch.a.a;
import com.persianswitch.a.aa;
import com.persianswitch.a.g;
import com.persianswitch.a.q;
import com.persianswitch.a.r;
import com.persianswitch.a.t;
import com.persianswitch.a.u;
import com.persianswitch.a.v;
import com.persianswitch.a.x;
import com.persianswitch.a.z;
import com.persianswitch.b.c;
import com.persianswitch.b.e;
import com.persianswitch.b.l;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;

public final class h {
    final class com.persianswitch.a.a.b.h$1 extends aa {
        com.persianswitch.a.a.b.h$1() {
            super();
        }

        public t a() {
            return null;
        }

        public long b() {
            return 0;
        }

        public e d() {
            return new c();
        }
    }

    final u a;
    final s b;
    final z c;
    final r d;
    private static final aa e;
    private boolean f;

    static {
        h.e = new com.persianswitch.a.a.b.h$1();
    }

    public h(u arg2, r arg3, s arg4, z arg5) {
        super();
        this.a = arg2;
        this.d = arg3;
        if(arg4 != null) {
        }
        else {
            arg4 = new s(arg2.o(), h.a(arg2, arg3));
        }

        this.b = arg4;
        this.c = arg5;
    }

    private static a a(u arg16, r arg17) {
        g v10;
        SSLSocketFactory v8;
        HostnameVerifier v9;
        SSLSocketFactory v1 = null;
        if(arg17.c()) {
            v1 = arg16.j();
            v9 = arg16.k();
            v8 = v1;
            v10 = arg16.l();
        }
        else {
            v8 = v1;
            v9 = ((HostnameVerifier)v8);
            v10 = ((g)v9);
        }

        return new a(arg17.f(), arg17.g(), arg16.h(), arg16.i(), v8, v9, v10, arg16.n(), arg16.d(), arg16.t(), arg16.u(), arg16.e());
    }

    private com.persianswitch.a.a.b.a a(z arg3, x arg4, com.persianswitch.a.a.e arg5) {
        com.persianswitch.a.a.b.a v0 = null;
        if(arg5 == null) {
            return v0;
        }

        if(!b.a(arg3, arg4)) {
            if(i.a(arg4.b())) {
                try {
                    arg5.b(arg4);
                    return v0;
                }
                catch(IOException ) {
                    return v0;
                }
            }

            return v0;
        }

        return arg5.a(arg3);
    }

    private j a(x arg7) {
        return this.b.a(this.a.a(), this.a.b(), this.a.c(), this.a.r(), arg7.b().equals("GET") ^ 1);
    }

    private static q a(q arg7, q arg8) {
        com.persianswitch.a.q$a v0 = new com.persianswitch.a.q$a();
        int v1 = arg7.a();
        int v2 = 0;
        int v3;
        for(v3 = 0; v3 < v1; ++v3) {
            String v4 = arg7.a(v3);
            String v5 = arg7.b(v3);
            if(!"Warning".equalsIgnoreCase(v4) || !v5.startsWith("1")) {
                if((k.a(v4)) && arg8.a(v4) != null) {
                    goto label_21;
                }

                d.a.a(v0, v4, v5);
            }
            else {
            }

        label_21:
        }

        int v7 = arg8.a();
        while(v2 < v7) {
            String v1_1 = arg8.a(v2);
            if("Content-Length".equalsIgnoreCase(v1_1)) {
            }
            else if(k.a(v1_1)) {
                d.a.a(v0, v1_1, arg8.b(v2));
            }

            ++v2;
        }

        return v0.a();
    }

    private z a(com.persianswitch.a.a.b.a arg4, z arg5) {
        if(arg4 == null) {
            return arg5;
        }

        com.persianswitch.b.r v0 = arg4.a();
        if(v0 == null) {
            return arg5;
        }

        return arg5.g().a(new m(arg5.e(), l.a(new com.persianswitch.b.s(arg5.f().d(), arg4, l.a(v0)) {
            boolean a;

            public long a(c arg9, long arg10) {
                long v1;
                try {
                    arg10 = this.b.a(arg9, arg10);
                    v1 = -1;
                    if(arg10 != v1) {
                        goto label_11;
                    }
                }
                catch(IOException v9) {
                    if(!this.a) {
                        this.a = true;
                        this.c.b();
                    }

                    throw v9;
                }

                if(!this.a) {
                    this.a = true;
                    this.d.close();
                }

                return v1;
            label_11:
                arg9.a(this.d.c(), arg9.b() - arg10, arg10);
                this.d.u();
                return arg10;
            }

            public com.persianswitch.b.t a() {
                return this.b.a();
            }

            public void close() {
                if(!this.a && !com.persianswitch.a.a.l.a(((com.persianswitch.b.s)this), 100, TimeUnit.MILLISECONDS)) {
                    this.a = true;
                    this.c.b();
                }

                this.b.close();
            }
        }))).a();
    }

    private String a(List arg6) {
        StringBuilder v0 = new StringBuilder();
        int v1 = arg6.size();
        int v2;
        for(v2 = 0; v2 < v1; ++v2) {
            if(v2 > 0) {
                v0.append("; ");
            }

            Object v3 = arg6.get(v2);
            v0.append(((com.persianswitch.a.l)v3).a());
            v0.append('=');
            v0.append(((com.persianswitch.a.l)v3).b());
        }

        return v0.toString();
    }

    private static boolean a(z arg4, z arg5) {
        if(arg5.b() == 304) {
            return 1;
        }

        Date v4 = arg4.e().b("Last-Modified");
        if(v4 != null) {
            Date v5 = arg5.e().b("Last-Modified");
            if(v5 != null && v5.getTime() < v4.getTime()) {
                return 1;
            }
        }

        return 0;
    }

    public s a(z arg2) {
        if(arg2 != null) {
            com.persianswitch.a.a.l.a(arg2.f());
        }
        else {
            this.b.a(null);
        }

        return this.b;
    }

    public z a(x arg8, com.persianswitch.a.a.b.l arg9) {
        z v8_1;
        j v0_2;
        x v0 = this.b(arg8);
        com.persianswitch.a.a.e v1 = d.a.a(this.a);
        z v2 = null;
        z v3 = v1 != null ? v1.a(v0) : v2;
        b v0_1 = new com.persianswitch.a.a.b.b$a(System.currentTimeMillis(), v0, v3).a();
        x v4 = v0_1.a;
        z v5 = v0_1.b;
        if(v1 != null) {
            v1.a(v0_1);
        }

        if(v3 != null && v5 == null) {
            com.persianswitch.a.a.l.a(v3.f());
        }

        if(v4 == null && v5 == null) {
            return new com.persianswitch.a.z$a().a(arg8).c(h.c(this.c)).a(v.b).a(504).a("Unsatisfiable Request (only-if-cached)").a(h.e).a(-1).b(System.currentTimeMillis()).a();
        }

        if(v4 == null) {
            return this.d(v5.g().a(arg8).c(h.c(this.c)).b(h.c(v5)).a());
        }

        try {
            v0_2 = this.a(v4);
        }
        catch(Throwable v8) {
            v0_2 = ((j)v2);
            goto label_114;
        }

        try {
            v0_2.a(this);
            if(v0_2 != null) {
                goto label_60;
            }

            goto label_57;
        }
        catch(Throwable v8) {
        }

    label_114:
        if(v0_2 == null && v3 != null) {
            com.persianswitch.a.a.l.a(v3.f());
        }

        throw v8;
    label_57:
        if(v3 != null) {
            com.persianswitch.a.a.l.a(v3.f());
        }

    label_60:
        z v9 = arg9.a(v4, this.b.b(), this.b, v0_2);
        this.a(v9.e());
        if(v5 != null) {
            if(h.a(v5, v9)) {
                v8_1 = v5.g().a(arg8).c(h.c(this.c)).a(h.a(v5.e(), v9.e())).b(h.c(v5)).a(h.c(v9)).a();
                v9.f().close();
                this.b.c();
                v1.a();
                v1.a(v5, v8_1);
                return this.d(v8_1);
            }
            else {
                com.persianswitch.a.a.l.a(v5.f());
            }
        }

        v8_1 = v9.g().a(arg8).c(h.c(this.c)).b(h.c(v5)).a(h.c(v9)).a();
        if(h.b(v8_1)) {
            v8_1 = this.d(this.a(this.a(v8_1, v9.a(), v1), v8_1));
        }

        return v8_1;
    }

    public void a(q arg3) {
        if(this.a.f() == com.persianswitch.a.m.a) {
            return;
        }

        List v3 = com.persianswitch.a.l.a(this.d, arg3);
        if(v3.isEmpty()) {
            return;
        }

        this.a.f().a(this.d, v3);
    }

    private x b(x arg5) {
        com.persianswitch.a.x$a v0 = arg5.e();
        if(arg5.a("Host") == null) {
            v0.a("Host", com.persianswitch.a.a.l.a(arg5.a(), false));
        }

        if(arg5.a("Connection") == null) {
            v0.a("Connection", "Keep-Alive");
        }

        if(arg5.a("Accept-Encoding") == null) {
            this.f = true;
            v0.a("Accept-Encoding", "gzip");
        }

        List v1 = this.a.f().a(arg5.a());
        if(!v1.isEmpty()) {
            v0.a("Cookie", this.a(v1));
        }

        if(arg5.a("User-Agent") == null) {
            v0.a("User-Agent", com.persianswitch.a.a.m.a());
        }

        return v0.a();
    }

    public static boolean b(z arg8) {
        if(arg8.a().b().equals("HEAD")) {
            return 0;
        }

        int v0 = arg8.b();
        if((v0 < 100 || v0 >= 200) && (v0 != 204 && v0 != 304)) {
            return 1;
        }

        if(k.a(arg8) == -1) {
            if("chunked".equalsIgnoreCase(arg8.a("Transfer-Encoding"))) {
            }
            else {
                return 0;
            }
        }

        return 1;
    }

    private static z c(z arg1) {
        if(arg1 != null && arg1.f() != null) {
            arg1 = arg1.g().a(null).a();
        }

        return arg1;
    }

    private z d(z arg4) {
        if(this.f) {
            if(!"gzip".equalsIgnoreCase(arg4.a("Content-Encoding"))) {
            }
            else if(arg4.f() == null) {
                return arg4;
            }
            else {
                com.persianswitch.b.j v0 = new com.persianswitch.b.j(arg4.f().d());
                q v1 = arg4.e().b().b("Content-Encoding").b("Content-Length").a();
                arg4 = arg4.g().a(v1).a(new m(v1, l.a(((com.persianswitch.b.s)v0)))).a();
            }
        }

        return arg4;
    }
}

