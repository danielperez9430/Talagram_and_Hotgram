package com.persianswitch.a;

import com.persianswitch.a.a.b.s;
import com.persianswitch.a.a.c.b;
import com.persianswitch.a.a.d;
import com.persianswitch.a.a.e;
import com.persianswitch.a.a.k;
import com.persianswitch.a.a.l;
import java.net.Proxy;
import java.net.ProxySelector;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

public class u implements Cloneable {
    final class com.persianswitch.a.u$1 extends d {
        com.persianswitch.a.u$1() {
            super();
        }

        public b a(j arg1, a arg2, s arg3) {
            return arg1.a(arg2, arg3);
        }

        public e a(u arg1) {
            return arg1.g();
        }

        public k a(j arg1) {
            return arg1.a;
        }

        public void a(com.persianswitch.a.k arg1, SSLSocket arg2, boolean arg3) {
            arg1.a(arg2, arg3);
        }

        public void a(com.persianswitch.a.q$a arg1, String arg2) {
            arg1.a(arg2);
        }

        public void a(com.persianswitch.a.q$a arg1, String arg2, String arg3) {
            arg1.b(arg2, arg3);
        }

        public boolean a(j arg1, b arg2) {
            return arg1.b(arg2);
        }

        public void b(j arg1, b arg2) {
            arg1.a(arg2);
        }
    }

    public final class com.persianswitch.a.u$a {
        n a;
        Proxy b;
        List c;
        List d;
        final List e;
        final List f;
        ProxySelector g;
        m h;
        c i;
        e j;
        SocketFactory k;
        SSLSocketFactory l;
        com.persianswitch.a.a.d.b m;
        HostnameVerifier n;
        g o;
        com.persianswitch.a.b p;
        com.persianswitch.a.b q;
        j r;
        o s;
        boolean t;
        boolean u;
        boolean v;
        int w;
        int x;
        int y;

        public com.persianswitch.a.u$a() {
            super();
            this.e = new ArrayList();
            this.f = new ArrayList();
            this.a = new n();
            this.c = u.x();
            this.d = u.y();
            this.g = ProxySelector.getDefault();
            this.h = m.a;
            this.k = SocketFactory.getDefault();
            this.n = com.persianswitch.a.a.d.d.a;
            this.o = g.a;
            this.p = com.persianswitch.a.b.a;
            this.q = com.persianswitch.a.b.a;
            this.r = new j();
            this.s = o.a;
            this.t = true;
            this.u = true;
            this.v = true;
            this.w = 10000;
            this.x = 10000;
            this.y = 10000;
        }

        public com.persianswitch.a.u$a a(long arg7, TimeUnit arg9) {
            long v0 = 0;
            if(arg7 >= v0) {
                if(arg9 != null) {
                    long v2 = arg9.toMillis(arg7);
                    if(v2 <= 2147483647) {
                        if(v2 == v0) {
                            if(arg7 <= v0) {
                            }
                            else {
                                throw new IllegalArgumentException("Timeout too small.");
                            }
                        }

                        this.w = ((int)v2);
                        return this;
                    }

                    throw new IllegalArgumentException("Timeout too large.");
                }

                throw new NullPointerException("unit == null");
            }

            throw new IllegalArgumentException("timeout < 0");
        }

        public com.persianswitch.a.u$a a(HostnameVerifier arg2) {
            if(arg2 != null) {
                this.n = arg2;
                return this;
            }

            throw new NullPointerException("hostnameVerifier == null");
        }

        public com.persianswitch.a.u$a a(SSLSocketFactory arg1, X509TrustManager arg2) {
            if(arg1 != null) {
                if(arg2 != null) {
                    this.l = arg1;
                    this.m = com.persianswitch.a.a.d.b.a(arg2);
                    return this;
                }

                throw new NullPointerException("trustManager == null");
            }

            throw new NullPointerException("sslSocketFactory == null");
        }

        public com.persianswitch.a.u$a a(boolean arg1) {
            this.v = arg1;
            return this;
        }

        public u a() {
            return new u(this, null);
        }

        public com.persianswitch.a.u$a b(long arg7, TimeUnit arg9) {
            long v0 = 0;
            if(arg7 >= v0) {
                if(arg9 != null) {
                    long v2 = arg9.toMillis(arg7);
                    if(v2 <= 2147483647) {
                        if(v2 == v0) {
                            if(arg7 <= v0) {
                            }
                            else {
                                throw new IllegalArgumentException("Timeout too small.");
                            }
                        }

                        this.x = ((int)v2);
                        return this;
                    }

                    throw new IllegalArgumentException("Timeout too large.");
                }

                throw new NullPointerException("unit == null");
            }

            throw new IllegalArgumentException("timeout < 0");
        }

        public com.persianswitch.a.u$a c(long arg7, TimeUnit arg9) {
            long v0 = 0;
            if(arg7 >= v0) {
                if(arg9 != null) {
                    long v2 = arg9.toMillis(arg7);
                    if(v2 <= 2147483647) {
                        if(v2 == v0) {
                            if(arg7 <= v0) {
                            }
                            else {
                                throw new IllegalArgumentException("Timeout too small.");
                            }
                        }

                        this.y = ((int)v2);
                        return this;
                    }

                    throw new IllegalArgumentException("Timeout too large.");
                }

                throw new NullPointerException("unit == null");
            }

            throw new IllegalArgumentException("timeout < 0");
        }
    }

    private static final List A;
    final n a;
    final Proxy b;
    final List c;
    final List d;
    final List e;
    final List f;
    final ProxySelector g;
    final m h;
    final c i;
    final e j;
    final SocketFactory k;
    final SSLSocketFactory l;
    final com.persianswitch.a.a.d.b m;
    final HostnameVerifier n;
    final g o;
    final com.persianswitch.a.b p;
    final com.persianswitch.a.b q;
    final j r;
    final o s;
    final boolean t;
    final boolean u;
    final boolean v;
    final int w;
    final int x;
    final int y;
    private static final List z;

    static {
        u.z = l.a(new v[]{v.d, v.c, v.b});
        ArrayList v0 = new ArrayList(Arrays.asList(new com.persianswitch.a.k[]{com.persianswitch.a.k.a, com.persianswitch.a.k.b}));
        if(com.persianswitch.a.a.j.c().a()) {
            ((List)v0).add(com.persianswitch.a.k.c);
        }

        u.A = l.a(((List)v0));
        d.a = new com.persianswitch.a.u$1();
    }

    public u() {
        this(new com.persianswitch.a.u$a());
    }

    private u(com.persianswitch.a.u$a arg5) {
        com.persianswitch.a.a.d.b v0_2;
        int v2;
        super();
        this.a = arg5.a;
        this.b = arg5.b;
        this.c = arg5.c;
        this.d = arg5.d;
        this.e = l.a(arg5.e);
        this.f = l.a(arg5.f);
        this.g = arg5.g;
        this.h = arg5.h;
        this.i = arg5.i;
        this.j = arg5.j;
        this.k = arg5.k;
        Iterator v0 = this.d.iterator();
        do {
        label_28:
            v2 = 0;
            while(true) {
            label_29:
                if(!v0.hasNext()) {
                    goto label_37;
                }

                Object v3 = v0.next();
                if(v2 == 0 && !((com.persianswitch.a.k)v3).a()) {
                    goto label_28;
                }

                break;
            }
        }
        while(true);

        v2 = 1;
        goto label_29;
    label_37:
        if(arg5.l != null || v2 == 0) {
            this.l = arg5.l;
            v0_2 = arg5.m;
        }
        else {
            X509TrustManager v0_1 = this.z();
            this.l = this.a(v0_1);
            v0_2 = com.persianswitch.a.a.d.b.a(v0_1);
        }

        this.m = v0_2;
        this.n = arg5.n;
        this.o = arg5.o.a(this.m);
        this.p = arg5.p;
        this.q = arg5.q;
        this.r = arg5.r;
        this.s = arg5.s;
        this.t = arg5.t;
        this.u = arg5.u;
        this.v = arg5.v;
        this.w = arg5.w;
        this.x = arg5.x;
        this.y = arg5.y;
    }

    u(com.persianswitch.a.u$a arg1, com.persianswitch.a.u$1 arg2) {
        this(arg1);
    }

    public int a() {
        return this.w;
    }

    private SSLSocketFactory a(X509TrustManager arg4) {
        try {
            SSLContext v0 = SSLContext.getInstance("TLS");
            v0.init(null, new TrustManager[]{arg4}, null);
            return v0.getSocketFactory();
        }
        catch(GeneralSecurityException ) {
            throw new AssertionError();
        }
    }

    public com.persianswitch.a.e a(x arg2) {
        return new w(this, arg2);
    }

    public int b() {
        return this.x;
    }

    public int c() {
        return this.y;
    }

    public Proxy d() {
        return this.b;
    }

    public ProxySelector e() {
        return this.g;
    }

    public m f() {
        return this.h;
    }

    e g() {
        e v0 = this.i != null ? this.i.a : this.j;
        return v0;
    }

    public o h() {
        return this.s;
    }

    public SocketFactory i() {
        return this.k;
    }

    public SSLSocketFactory j() {
        return this.l;
    }

    public HostnameVerifier k() {
        return this.n;
    }

    public g l() {
        return this.o;
    }

    public com.persianswitch.a.b m() {
        return this.q;
    }

    public com.persianswitch.a.b n() {
        return this.p;
    }

    public j o() {
        return this.r;
    }

    public boolean p() {
        return this.t;
    }

    public boolean q() {
        return this.u;
    }

    public boolean r() {
        return this.v;
    }

    public n s() {
        return this.a;
    }

    public List t() {
        return this.c;
    }

    public List u() {
        return this.d;
    }

    public List v() {
        return this.e;
    }

    public List w() {
        return this.f;
    }

    static List x() {
        return u.z;
    }

    static List y() {
        return u.A;
    }

    private X509TrustManager z() {
        try {
            TrustManagerFactory v0 = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            v0.init(null);
            TrustManager[] v0_1 = v0.getTrustManagers();
            if(v0_1.length == 1 && ((v0_1[0] instanceof X509TrustManager))) {
                return v0_1[0];
            }

            StringBuilder v2 = new StringBuilder();
            v2.append("Unexpected default trust managers:");
            v2.append(Arrays.toString(((Object[])v0_1)));
            throw new IllegalStateException(v2.toString());
        }
        catch(GeneralSecurityException ) {
            throw new AssertionError();
        }
    }
}

