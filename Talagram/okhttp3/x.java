package okhttp3;

import java.net.Proxy;
import java.net.ProxySelector;
import java.net.Socket;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import okhttp3.internal.a.e;
import okhttp3.internal.b.c;
import okhttp3.internal.b.d;
import okhttp3.internal.b.g;
import okhttp3.internal.g.f;

public class x implements Cloneable, a {
    final class okhttp3.x$1 extends okhttp3.internal.a {
        okhttp3.x$1() {
            super();
        }

        public int a(okhttp3.ac$a arg1) {
            return arg1.c;
        }

        public Socket a(j arg1, okhttp3.a arg2, g arg3) {
            return arg1.a(arg2, arg3);
        }

        public c a(j arg1, okhttp3.a arg2, g arg3, ae arg4) {
            return arg1.a(arg2, arg3, arg4);
        }

        public d a(j arg1) {
            return arg1.a;
        }

        public void a(k arg1, SSLSocket arg2, boolean arg3) {
            arg1.a(arg2, arg3);
        }

        public void a(okhttp3.s$a arg1, String arg2) {
            arg1.a(arg2);
        }

        public void a(okhttp3.s$a arg1, String arg2, String arg3) {
            arg1.b(arg2, arg3);
        }

        public boolean a(okhttp3.a arg1, okhttp3.a arg2) {
            return arg1.a(arg2);
        }

        public boolean a(j arg1, c arg2) {
            return arg1.b(arg2);
        }

        public void b(j arg1, c arg2) {
            arg1.a(arg2);
        }
    }

    public final class okhttp3.x$a {
        int A;
        n a;
        @Nullable Proxy b;
        List c;
        List d;
        final List e;
        final List f;
        okhttp3.p$a g;
        ProxySelector h;
        m i;
        @Nullable okhttp3.c j;
        @Nullable e k;
        SocketFactory l;
        @Nullable SSLSocketFactory m;
        @Nullable okhttp3.internal.h.c n;
        HostnameVerifier o;
        okhttp3.g p;
        b q;
        b r;
        j s;
        o t;
        boolean u;
        boolean v;
        boolean w;
        int x;
        int y;
        int z;

        public okhttp3.x$a() {
            super();
            this.e = new ArrayList();
            this.f = new ArrayList();
            this.a = new n();
            this.c = x.a;
            this.d = x.b;
            this.g = p.a(p.a);
            this.h = ProxySelector.getDefault();
            this.i = m.a;
            this.l = SocketFactory.getDefault();
            this.o = okhttp3.internal.h.d.a;
            this.p = okhttp3.g.a;
            this.q = b.a;
            this.r = b.a;
            this.s = new j();
            this.t = o.a;
            this.u = true;
            this.v = true;
            this.w = true;
            this.x = 10000;
            this.y = 10000;
            this.z = 10000;
            this.A = 0;
        }

        public x a() {
            return new x(this);
        }

        public okhttp3.x$a a(n arg2) {
            if(arg2 != null) {
                this.a = arg2;
                return this;
            }

            throw new IllegalArgumentException("dispatcher == null");
        }

        public okhttp3.x$a a(j arg2) {
            if(arg2 != null) {
                this.s = arg2;
                return this;
            }

            throw new NullPointerException("connectionPool == null");
        }

        public okhttp3.x$a a(u arg2) {
            if(arg2 != null) {
                this.e.add(arg2);
                return this;
            }

            throw new IllegalArgumentException("interceptor == null");
        }

        public okhttp3.x$a a(long arg2, TimeUnit arg4) {
            this.x = okhttp3.internal.c.a("timeout", arg2, arg4);
            return this;
        }

        public okhttp3.x$a b(long arg2, TimeUnit arg4) {
            this.y = okhttp3.internal.c.a("timeout", arg2, arg4);
            return this;
        }
    }

    final int A;
    final int B;
    final int C;
    static final List a;
    static final List b;
    final n c;
    @Nullable final Proxy d;
    final List e;
    final List f;
    final List g;
    final List h;
    final okhttp3.p$a i;
    final ProxySelector j;
    final m k;
    @Nullable final okhttp3.c l;
    @Nullable final e m;
    final SocketFactory n;
    @Nullable final SSLSocketFactory o;
    @Nullable final okhttp3.internal.h.c p;
    final HostnameVerifier q;
    final okhttp3.g r;
    final b s;
    final b t;
    final j u;
    final o v;
    final boolean w;
    final boolean x;
    final boolean y;
    final int z;

    static {
        x.a = okhttp3.internal.c.a(new y[]{y.d, y.b});
        x.b = okhttp3.internal.c.a(new k[]{k.a, k.c});
        okhttp3.internal.a.a = new okhttp3.x$1();
    }

    public x() {
        this(new okhttp3.x$a());
    }

    x(okhttp3.x$a arg5) {
        StringBuilder v0_4;
        okhttp3.internal.h.c v0_2;
        int v2;
        super();
        this.c = arg5.a;
        this.d = arg5.b;
        this.e = arg5.c;
        this.f = arg5.d;
        this.g = okhttp3.internal.c.a(arg5.e);
        this.h = okhttp3.internal.c.a(arg5.f);
        this.i = arg5.g;
        this.j = arg5.h;
        this.k = arg5.i;
        this.l = arg5.j;
        this.m = arg5.k;
        this.n = arg5.l;
        Iterator v0 = this.f.iterator();
        do {
        label_30:
            v2 = 0;
            while(true) {
            label_31:
                if(!v0.hasNext()) {
                    goto label_39;
                }

                Object v3 = v0.next();
                if(v2 == 0 && !((k)v3).a()) {
                    goto label_30;
                }

                break;
            }
        }
        while(true);

        v2 = 1;
        goto label_31;
    label_39:
        if(arg5.m != null || v2 == 0) {
            this.o = arg5.m;
            v0_2 = arg5.n;
        }
        else {
            X509TrustManager v0_1 = this.z();
            this.o = this.a(v0_1);
            v0_2 = okhttp3.internal.h.c.a(v0_1);
        }

        this.p = v0_2;
        this.q = arg5.o;
        this.r = arg5.p.a(this.p);
        this.s = arg5.q;
        this.t = arg5.r;
        this.u = arg5.s;
        this.v = arg5.t;
        this.w = arg5.u;
        this.x = arg5.v;
        this.y = arg5.w;
        this.z = arg5.x;
        this.A = arg5.y;
        this.B = arg5.z;
        this.C = arg5.A;
        Object v0_3 = null;
        if(!this.g.contains(v0_3)) {
            if(!this.h.contains(v0_3)) {
                return;
            }

            v0_4 = new StringBuilder();
            v0_4.append("Null network interceptor: ");
            v0_4.append(this.h);
            throw new IllegalStateException(v0_4.toString());
        }

        v0_4 = new StringBuilder();
        v0_4.append("Null interceptor: ");
        v0_4.append(this.g);
        throw new IllegalStateException(v0_4.toString());
    }

    private SSLSocketFactory a(X509TrustManager arg4) {
        try {
            SSLContext v0 = f.c().n_();
            v0.init(null, new TrustManager[]{arg4}, null);
            return v0.getSocketFactory();
        }
        catch(GeneralSecurityException v4) {
            throw okhttp3.internal.c.a("No System TLS", ((Exception)v4));
        }
    }

    public int a() {
        return this.z;
    }

    public okhttp3.e a(aa arg2) {
        return z.a(this, arg2, false);
    }

    public int b() {
        return this.A;
    }

    public int c() {
        return this.B;
    }

    public int d() {
        return this.C;
    }

    public Proxy e() {
        return this.d;
    }

    public ProxySelector f() {
        return this.j;
    }

    public m g() {
        return this.k;
    }

    e h() {
        e v0 = this.l != null ? this.l.a : this.m;
        return v0;
    }

    public o i() {
        return this.v;
    }

    public SocketFactory j() {
        return this.n;
    }

    public SSLSocketFactory k() {
        return this.o;
    }

    public HostnameVerifier l() {
        return this.q;
    }

    public okhttp3.g m() {
        return this.r;
    }

    public b n() {
        return this.t;
    }

    public b o() {
        return this.s;
    }

    public j p() {
        return this.u;
    }

    public boolean q() {
        return this.w;
    }

    public boolean r() {
        return this.x;
    }

    public boolean s() {
        return this.y;
    }

    public n t() {
        return this.c;
    }

    public List u() {
        return this.e;
    }

    public List v() {
        return this.f;
    }

    public List w() {
        return this.g;
    }

    public List x() {
        return this.h;
    }

    public okhttp3.p$a y() {
        return this.i;
    }

    private X509TrustManager z() {
        try {
            TrustManagerFactory v0_1 = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            v0_1.init(null);
            TrustManager[] v0_2 = v0_1.getTrustManagers();
            if(v0_2.length == 1 && ((v0_2[0] instanceof X509TrustManager))) {
                return v0_2[0];
            }

            StringBuilder v2 = new StringBuilder();
            v2.append("Unexpected default trust managers:");
            v2.append(Arrays.toString(((Object[])v0_2)));
            throw new IllegalStateException(v2.toString());
        }
        catch(GeneralSecurityException v0) {
            throw okhttp3.internal.c.a("No System TLS", ((Exception)v0));
        }
    }
}

