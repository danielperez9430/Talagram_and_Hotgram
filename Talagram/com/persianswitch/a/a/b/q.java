package com.persianswitch.a.a.b;

import com.persianswitch.a.a.k;
import com.persianswitch.a.a;
import com.persianswitch.a.ab;
import com.persianswitch.a.r;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy$Type;
import java.net.Proxy;
import java.net.SocketAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

public final class q {
    private final a a;
    private final k b;
    private Proxy c;
    private InetSocketAddress d;
    private List e;
    private int f;
    private List g;
    private int h;
    private final List i;

    public q(a arg2, k arg3) {
        super();
        this.e = Collections.emptyList();
        this.g = Collections.emptyList();
        this.i = new ArrayList();
        this.a = arg2;
        this.b = arg3;
        this.a(arg2.a(), arg2.h());
    }

    private void a(r arg1, Proxy arg2) {
        if(arg2 != null) {
            this.e = Collections.singletonList(arg2);
        }
        else {
            this.e = new ArrayList();
            List v1 = this.a.g().select(arg1.a());
            if(v1 != null) {
                this.e.addAll(((Collection)v1));
            }

            this.e.removeAll(Collections.singleton(Proxy.NO_PROXY));
            this.e.add(Proxy.NO_PROXY);
        }

        this.f = 0;
    }

    static String a(InetSocketAddress arg1) {
        InetAddress v0 = arg1.getAddress();
        if(v0 == null) {
            return arg1.getHostName();
        }

        return v0.getHostAddress();
    }

    private void a(Proxy arg8) {
        int v0_1;
        String v1;
        this.g = new ArrayList();
        if(arg8.type() == Proxy$Type.DIRECT || arg8.type() == Proxy$Type.SOCKS) {
            v1 = this.a.a().f();
            v0_1 = this.a.a().g();
        }
        else {
            SocketAddress v0 = arg8.address();
            if((v0 instanceof InetSocketAddress)) {
                v1 = q.a(((InetSocketAddress)v0));
                v0_1 = ((InetSocketAddress)v0).getPort();
            }
            else {
                StringBuilder v1_1 = new StringBuilder();
                v1_1.append("Proxy.address() is not an InetSocketAddress: ");
                v1_1.append(v0.getClass());
                throw new IllegalArgumentException(v1_1.toString());
            }
        }

        if(v0_1 >= 1 && v0_1 <= 65535) {
            if(arg8.type() == Proxy$Type.SOCKS) {
                this.g.add(InetSocketAddress.createUnresolved(v1, v0_1));
            }
            else {
                List v8 = this.a.b().a(v1);
                int v1_2 = v8.size();
                int v2;
                for(v2 = 0; v2 < v1_2; ++v2) {
                    this.g.add(new InetSocketAddress(v8.get(v2), v0_1));
                }
            }

            this.h = 0;
            return;
        }

        StringBuilder v2_1 = new StringBuilder();
        v2_1.append("No route to ");
        v2_1.append(v1);
        v2_1.append(":");
        v2_1.append(v0_1);
        v2_1.append("; port is out of range");
        throw new SocketException(v2_1.toString());
    }

    public void a(ab arg4, IOException arg5) {
        if(arg4.b().type() != Proxy$Type.DIRECT && this.a.g() != null) {
            this.a.g().connectFailed(this.a.a().a(), arg4.b().address(), arg5);
        }

        this.b.a(arg4);
    }

    public boolean a() {
        boolean v0 = (this.e()) || (this.c()) || (this.g()) ? true : false;
        return v0;
    }

    public ab b() {
        if(!this.e()) {
            if(this.c()) {
                this.c = this.d();
            }
            else if(this.g()) {
                return this.h();
            }
            else {
                throw new NoSuchElementException();
            }
        }

        this.d = this.f();
        ab v0 = new ab(this.a, this.c, this.d);
        if(this.b.c(v0)) {
            this.i.add(v0);
            v0 = this.b();
        }

        return v0;
    }

    private boolean c() {
        boolean v0 = this.f < this.e.size() ? true : false;
        return v0;
    }

    private Proxy d() {
        if(this.c()) {
            List v0 = this.e;
            int v1 = this.f;
            this.f = v1 + 1;
            Object v0_1 = v0.get(v1);
            this.a(((Proxy)v0_1));
            return ((Proxy)v0_1);
        }

        StringBuilder v1_1 = new StringBuilder();
        v1_1.append("No route to ");
        v1_1.append(this.a.a().f());
        v1_1.append("; exhausted proxy configurations: ");
        v1_1.append(this.e);
        throw new SocketException(v1_1.toString());
    }

    private boolean e() {
        boolean v0 = this.h < this.g.size() ? true : false;
        return v0;
    }

    private InetSocketAddress f() {
        if(this.e()) {
            List v0 = this.g;
            int v1 = this.h;
            this.h = v1 + 1;
            return v0.get(v1);
        }

        StringBuilder v1_1 = new StringBuilder();
        v1_1.append("No route to ");
        v1_1.append(this.a.a().f());
        v1_1.append("; exhausted inet socket addresses: ");
        v1_1.append(this.g);
        throw new SocketException(v1_1.toString());
    }

    private boolean g() {
        return this.i.isEmpty() ^ 1;
    }

    private ab h() {
        return this.i.remove(0);
    }
}

