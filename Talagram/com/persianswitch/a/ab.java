package com.persianswitch.a;

import java.net.InetSocketAddress;
import java.net.Proxy$Type;
import java.net.Proxy;

public final class ab {
    final a a;
    final Proxy b;
    final InetSocketAddress c;

    public ab(a arg1, Proxy arg2, InetSocketAddress arg3) {
        super();
        if(arg1 != null) {
            if(arg2 != null) {
                if(arg3 != null) {
                    this.a = arg1;
                    this.b = arg2;
                    this.c = arg3;
                    return;
                }

                throw new NullPointerException("inetSocketAddress == null");
            }

            throw new NullPointerException("proxy == null");
        }

        throw new NullPointerException("address == null");
    }

    public a a() {
        return this.a;
    }

    public Proxy b() {
        return this.b;
    }

    public InetSocketAddress c() {
        return this.c;
    }

    public boolean d() {
        boolean v0 = this.a.i == null || this.b.type() != Proxy$Type.HTTP ? false : true;
        return v0;
    }

    public boolean equals(Object arg4) {
        boolean v1 = false;
        if(((arg4 instanceof ab)) && (this.a.equals(((ab)arg4).a)) && (this.b.equals(((ab)arg4).b)) && (this.c.equals(((ab)arg4).c))) {
            v1 = true;
        }

        return v1;
    }

    public int hashCode() {
        return ((527 + this.a.hashCode()) * 31 + this.b.hashCode()) * 31 + this.c.hashCode();
    }
}

