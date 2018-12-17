package okhttp3.internal.b;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy$Type;
import java.net.Proxy;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import okhttp3.ae;
import okhttp3.e;
import okhttp3.internal.c;
import okhttp3.p;
import okhttp3.t;

public final class f {
    public final class a {
        private final List a;
        private int b;

        a(List arg2) {
            super();
            this.b = 0;
            this.a = arg2;
        }

        public boolean a() {
            boolean v0 = this.b < this.a.size() ? true : false;
            return v0;
        }

        public ae b() {
            if(this.a()) {
                List v0 = this.a;
                int v1 = this.b;
                this.b = v1 + 1;
                return v0.get(v1);
            }

            throw new NoSuchElementException();
        }

        public List c() {
            return new ArrayList(this.a);
        }
    }

    private final okhttp3.a a;
    private final d b;
    private final e c;
    private final p d;
    private List e;
    private int f;
    private List g;
    private final List h;

    public f(okhttp3.a arg2, d arg3, e arg4, p arg5) {
        super();
        this.e = Collections.emptyList();
        this.g = Collections.emptyList();
        this.h = new ArrayList();
        this.a = arg2;
        this.b = arg3;
        this.c = arg4;
        this.d = arg5;
        this.a(arg2.a(), arg2.h());
    }

    private void a(t arg2, Proxy arg3) {
        List v2;
        if(arg3 != null) {
            v2 = Collections.singletonList(arg3);
        }
        else {
            v2 = this.a.g().select(arg2.a());
            if(v2 != null && !v2.isEmpty()) {
                v2 = c.a(v2);
                goto label_3;
            }

            v2 = c.a(new Proxy[]{Proxy.NO_PROXY});
        }

    label_3:
        this.e = v2;
        this.f = 0;
    }

    static String a(InetSocketAddress arg1) {
        InetAddress v0 = arg1.getAddress();
        if(v0 == null) {
            return arg1.getHostName();
        }

        return v0.getHostAddress();
    }

    private void a(Proxy arg7) {
        int v0_1;
        String v1;
        this.g = new ArrayList();
        if(arg7.type() == Proxy$Type.DIRECT || arg7.type() == Proxy$Type.SOCKS) {
            v1 = this.a.a().f();
            v0_1 = this.a.a().g();
        }
        else {
            SocketAddress v0 = arg7.address();
            if((v0 instanceof InetSocketAddress)) {
                v1 = f.a(((InetSocketAddress)v0));
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
            if(arg7.type() == Proxy$Type.SOCKS) {
                this.g.add(InetSocketAddress.createUnresolved(v1, v0_1));
            }
            else {
                this.d.a(this.c, v1);
                List v7 = this.a.b().a(v1);
                if(!v7.isEmpty()) {
                    this.d.a(this.c, v1, v7);
                    int v1_2 = 0;
                    int v2 = v7.size();
                    while(v1_2 < v2) {
                        this.g.add(new InetSocketAddress(v7.get(v1_2), v0_1));
                        ++v1_2;
                    }
                }
                else {
                    goto label_65;
                }
            }

            return;
        label_65:
            StringBuilder v0_2 = new StringBuilder();
            v0_2.append(this.a.b());
            v0_2.append(" returned no addresses for ");
            v0_2.append(v1);
            throw new UnknownHostException(v0_2.toString());
        }

        StringBuilder v2_1 = new StringBuilder();
        v2_1.append("No route to ");
        v2_1.append(v1);
        v2_1.append(":");
        v2_1.append(v0_1);
        v2_1.append("; port is out of range");
        throw new SocketException(v2_1.toString());
    }

    public void a(ae arg4, IOException arg5) {
        if(arg4.b().type() != Proxy$Type.DIRECT && this.a.g() != null) {
            this.a.g().connectFailed(this.a.a().a(), arg4.b().address(), arg5);
        }

        this.b.a(arg4);
    }

    public boolean a() {
        boolean v0 = (this.c()) || !this.h.isEmpty() ? true : false;
        return v0;
    }

    public a b() {
        if(this.a()) {
            ArrayList v0 = new ArrayList();
            do {
                if(!this.c()) {
                    break;
                }

                Proxy v1 = this.d();
                int v2 = 0;
                int v3 = this.g.size();
                while(v2 < v3) {
                    ae v4 = new ae(this.a, v1, this.g.get(v2));
                    if(this.b.c(v4)) {
                        this.h.add(v4);
                    }
                    else {
                        ((List)v0).add(v4);
                    }

                    ++v2;
                }
            }
            while(((List)v0).isEmpty());

            if(((List)v0).isEmpty()) {
                ((List)v0).addAll(this.h);
                this.h.clear();
            }

            return new a(((List)v0));
        }

        throw new NoSuchElementException();
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
}

