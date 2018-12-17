package com.persianswitch.a;

import com.persianswitch.a.a.l;
import java.net.Proxy;
import java.net.ProxySelector;
import java.util.List;
import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;

public final class a {
    final r a;
    final o b;
    final SocketFactory c;
    final b d;
    final List e;
    final List f;
    final ProxySelector g;
    final Proxy h;
    final SSLSocketFactory i;
    final HostnameVerifier j;
    final g k;

    public a(String arg3, int arg4, o arg5, SocketFactory arg6, SSLSocketFactory arg7, HostnameVerifier arg8, g arg9, b arg10, Proxy arg11, List arg12, List arg13, ProxySelector arg14) {
        super();
        com.persianswitch.a.r$a v0 = new com.persianswitch.a.r$a();
        String v1 = arg7 != null ? "https" : "http";
        this.a = v0.a(v1).b(arg3).a(arg4).c();
        if(arg5 != null) {
            this.b = arg5;
            if(arg6 != null) {
                this.c = arg6;
                if(arg10 != null) {
                    this.d = arg10;
                    if(arg12 != null) {
                        this.e = l.a(arg12);
                        if(arg13 != null) {
                            this.f = l.a(arg13);
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

    public r a() {
        return this.a;
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

    public boolean equals(Object arg4) {
        boolean v1 = false;
        if(((arg4 instanceof a)) && (this.a.equals(((a)arg4).a)) && (this.b.equals(((a)arg4).b)) && (this.d.equals(((a)arg4).d)) && (this.e.equals(((a)arg4).e)) && (this.f.equals(((a)arg4).f)) && (this.g.equals(((a)arg4).g)) && (l.a(this.h, ((a)arg4).h)) && (l.a(this.i, ((a)arg4).i)) && (l.a(this.j, ((a)arg4).j)) && (l.a(this.k, ((a)arg4).k))) {
            v1 = true;
        }

        return v1;
    }

    public List f() {
        return this.f;
    }

    public ProxySelector g() {
        return this.g;
    }

    public Proxy h() {
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

    public SSLSocketFactory i() {
        return this.i;
    }

    public HostnameVerifier j() {
        return this.j;
    }

    public g k() {
        return this.k;
    }
}

