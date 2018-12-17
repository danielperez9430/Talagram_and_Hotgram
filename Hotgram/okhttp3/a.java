package okhttp3;

import java.net.Proxy;
import java.net.ProxySelector;
import java.util.List;
import javax.annotation.Nullable;
import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;
import okhttp3.internal.c;

public final class a {
    final t a;
    final o b;
    final SocketFactory c;
    final b d;
    final List e;
    final List f;
    final ProxySelector g;
    @Nullable final Proxy h;
    @Nullable final SSLSocketFactory i;
    @Nullable final HostnameVerifier j;
    @Nullable final g k;

    public a(String arg3, int arg4, o arg5, SocketFactory arg6, @Nullable SSLSocketFactory arg7, @Nullable HostnameVerifier arg8, @Nullable g arg9, b arg10, @Nullable Proxy arg11, List arg12, List arg13, ProxySelector arg14) {
        super();
        okhttp3.t$a v0 = new okhttp3.t$a();
        String v1 = arg7 != null ? "https" : "http";
        this.a = v0.a(v1).d(arg3).a(arg4).c();
        if(arg5 != null) {
            this.b = arg5;
            if(arg6 != null) {
                this.c = arg6;
                if(arg10 != null) {
                    this.d = arg10;
                    if(arg12 != null) {
                        this.e = c.a(arg12);
                        if(arg13 != null) {
                            this.f = c.a(arg13);
                            if(arg14 != null) {
                                this.g = arg14;
                                this.h = arg11;
                                this.i = arg7;
                                this.j = arg8;
                                this.k = arg9;
                                return;
                            }

                            throw new NullPointerException("proxySelector == null");
                        }

                        throw new NullPointerException("connectionSpecs == null");
                    }

                    throw new NullPointerException("protocols == null");
                }

                throw new NullPointerException("proxyAuthenticator == null");
            }

            throw new NullPointerException("socketFactory == null");
        }

        throw new NullPointerException("dns == null");
    }

    public t a() {
        return this.a;
    }

    boolean a(a arg3) {
        boolean v3 = !this.b.equals(arg3.b) || !this.d.equals(arg3.d) || !this.e.equals(arg3.e) || !this.f.equals(arg3.f) || !this.g.equals(arg3.g) || !c.a(this.h, arg3.h) || !c.a(this.i, arg3.i) || !c.a(this.j, arg3.j) || !c.a(this.k, arg3.k) || this.a().g() != arg3.a().g() ? false : true;
        return v3;
    }

    public o b() {
        return this.b;
    }

    public SocketFactory c() {
        return this.c;
    }

    public b d() {
        return this.d;
    }

    public List e() {
        return this.e;
    }

    public boolean equals(@Nullable Object arg3) {
        boolean v3 = !(arg3 instanceof a) || !this.a.equals(((a)arg3).a) || !this.a(((a)arg3)) ? false : true;
        return v3;
    }

    public List f() {
        return this.f;
    }

    public ProxySelector g() {
        return this.g;
    }

    @Nullable public Proxy h() {
        return this.h;
    }

    public int hashCode() {
        int v1 = ((((((527 + this.a.hashCode()) * 31 + this.b.hashCode()) * 31 + this.d.hashCode()) * 31 + this.e.hashCode()) * 31 + this.f.hashCode()) * 31 + this.g.hashCode()) * 31;
        int v2 = 0;
        int v0 = this.h != null ? this.h.hashCode() : 0;
        v1 = (v1 + v0) * 31;
        v0 = this.i != null ? this.i.hashCode() : 0;
        v1 = (v1 + v0) * 31;
        v0 = this.j != null ? this.j.hashCode() : 0;
        v1 = (v1 + v0) * 31;
        if(this.k != null) {
            v2 = this.k.hashCode();
        }

        return v1 + v2;
    }

    @Nullable public SSLSocketFactory i() {
        return this.i;
    }

    @Nullable public HostnameVerifier j() {
        return this.j;
    }

    @Nullable public g k() {
        return this.k;
    }

    public String toString() {
        Proxy v1;
        StringBuilder v0 = new StringBuilder();
        v0.append("Address{");
        v0.append(this.a.f());
        v0.append(":");
        v0.append(this.a.g());
        if(this.h != null) {
            v0.append(", proxy=");
            v1 = this.h;
        }
        else {
            v0.append(", proxySelector=");
            ProxySelector v1_1 = this.g;
        }

        v0.append(v1);
        v0.append("}");
        return v0.toString();
    }
}

