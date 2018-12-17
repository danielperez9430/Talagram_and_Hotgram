package okhttp3.internal.b;

import e.d;
import e.e;
import e.l;
import e.s;
import java.io.IOException;
import java.net.ConnectException;
import java.net.ProtocolException;
import java.net.Proxy$Type;
import java.net.Proxy;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownServiceException;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import okhttp3.a;
import okhttp3.aa;
import okhttp3.ac;
import okhttp3.ae;
import okhttp3.h;
import okhttp3.i;
import okhttp3.internal.e.g$b;
import okhttp3.internal.e.g;
import okhttp3.internal.g.f;
import okhttp3.j;
import okhttp3.k;
import okhttp3.p;
import okhttp3.r;
import okhttp3.t;
import okhttp3.x;
import okhttp3.y;

public final class c extends b implements i {
    public boolean a;
    public int b;
    public int c;
    public final List d;
    public long e;
    private final j g;
    private final ae h;
    private Socket i;
    private Socket j;
    private r k;
    private y l;
    private g m;
    private e n;
    private d o;

    public c(j arg3, ae arg4) {
        super();
        this.c = 1;
        this.d = new ArrayList();
        this.e = 9223372036854775807L;
        this.g = arg3;
        this.h = arg4;
    }

    public ae a() {
        return this.h;
    }

    public boolean a(a arg5, @Nullable ae arg6) {
        if(this.d.size() < this.c) {
            if(this.a) {
            }
            else if(!okhttp3.internal.a.a.a(this.h.a(), arg5)) {
                return 0;
            }
            else if(arg5.a().f().equals(this.a().a().a().f())) {
                return 1;
            }
            else if(this.m == null) {
                return 0;
            }
            else if(arg6 == null) {
                return 0;
            }
            else if(arg6.b().type() != Proxy$Type.DIRECT) {
                return 0;
            }
            else if(this.h.b().type() != Proxy$Type.DIRECT) {
                return 0;
            }
            else if(!this.h.c().equals(arg6.c())) {
                return 0;
            }
            else if(arg6.a().j() != okhttp3.internal.h.d.a) {
                return 0;
            }
            else if(!this.a(arg5.a())) {
                return 0;
            }
            else {
                try {
                    arg5.k().a(arg5.a().f(), this.d().b());
                    return 1;
                }
                catch(SSLPeerUnverifiedException ) {
                    return 0;
                }
            }
        }

        return 0;
    }

    private aa a(int arg8, int arg9, aa arg10, t arg11) {
        ac v10;
        x v3;
        String v11 = "CONNECT " + okhttp3.internal.c.a(arg11, true) + " HTTP/1.1";
        while(true) {
            v3 = null;
            okhttp3.internal.d.a v0_1 = new okhttp3.internal.d.a(v3, ((okhttp3.internal.b.g)v3), this.n, this.o);
            this.n.a().a(((long)arg8), TimeUnit.MILLISECONDS);
            this.o.a().a(((long)arg9), TimeUnit.MILLISECONDS);
            v0_1.a(arg10.c(), v11);
            v0_1.b();
            v10 = v0_1.a(false).a(arg10).a();
            long v1 = okhttp3.internal.c.e.a(v10);
            if(v1 == -1) {
                v1 = 0;
            }

            s v0_2 = v0_1.b(v1);
            okhttp3.internal.c.b(v0_2, 2147483647, TimeUnit.MILLISECONDS);
            v0_2.close();
            int v0_3 = v10.b();
            if(v0_3 == 200) {
                goto label_74;
            }

            if(v0_3 != 407) {
                goto label_64;
            }

            aa v0_4 = this.h.a().d().a(this.h, v10);
            if(v0_4 == null) {
                break;
            }

            if("close".equalsIgnoreCase(v10.a("Connection"))) {
                return v0_4;
            }

            arg10 = v0_4;
        }

        throw new IOException("Failed to authenticate with proxy");
    label_64:
        StringBuilder v9 = new StringBuilder();
        v9.append("Unexpected response code for CONNECT: ");
        v9.append(v10.b());
        throw new IOException(v9.toString());
    label_74:
        if((this.n.c().f()) && (this.o.c().f())) {
            return ((aa)v3);
        }

        throw new IOException("TLS tunnel buffered too many bytes!");
    }

    private void a(int arg7, int arg8, int arg9, okhttp3.e arg10, p arg11) {
        aa v0 = this.f();
        t v1 = v0.a();
        int v2 = 0;
        while(v2 < 21) {
            this.a(arg7, arg8, arg10, arg11);
            v0 = this.a(arg8, arg9, v0, v1);
            if(v0 == null) {
            }
            else {
                okhttp3.internal.c.a(this.i);
                this.i = null;
                this.o = null;
                this.n = null;
                arg11.a(arg10, this.h.c(), this.h.b(), null);
                ++v2;
                continue;
            }

            return;
        }
    }

    private void a(int arg5, int arg6, okhttp3.e arg7, p arg8) {
        Proxy v0 = this.h.b();
        a v1 = this.h.a();
        Socket v1_1 = v0.type() == Proxy$Type.DIRECT || v0.type() == Proxy$Type.HTTP ? v1.c().createSocket() : new Socket(v0);
        this.i = v1_1;
        arg8.a(arg7, this.h.c(), v0);
        this.i.setSoTimeout(arg6);
        try {
            f.c().a(this.i, this.h.c(), arg5);
        }
        catch(ConnectException v5) {
            StringBuilder v7 = new StringBuilder();
            v7.append("Failed to connect to ");
            v7.append(this.h.c());
            ConnectException v6 = new ConnectException(v7.toString());
            v6.initCause(((Throwable)v5));
            throw v6;
        }

        try {
            this.n = l.a(l.b(this.i));
            this.o = l.a(l.a(this.i));
        }
        catch(NullPointerException v5_1) {
            if(!"throw with null exception".equals(v5_1.getMessage())) {
                return;
            }

            throw new IOException(((Throwable)v5_1));
        }
    }

    private void a(okhttp3.internal.b.b arg8) {
        r v4;
        Socket v1_1;
        a v0 = this.h.a();
        SSLSocketFactory v1 = v0.i();
        String v2 = null;
        try {
            v1_1 = v1.createSocket(this.i, v0.a().f(), v0.a().g(), true);
        }
        catch(AssertionError v8) {
            goto label_97;
        }
        catch(Throwable v8_1) {
            goto label_94;
        }

        try {
            k v8_2 = arg8.a(((SSLSocket)v1_1));
            goto label_12;
        }
        catch(Throwable v8_1) {
        }
        catch(Throwable v8_1) {
        }
        catch(AssertionError v8) {
        }
        catch(AssertionError v8) {
            goto label_97;
            try {
            label_12:
                if(v8_2.d()) {
                    f.c().a(((SSLSocket)v1_1), v0.a().f(), v0.e());
                }

                ((SSLSocket)v1_1).startHandshake();
                SSLSession v3 = ((SSLSocket)v1_1).getSession();
                if(!this.a(v3)) {
                    goto label_84;
                }

                v4 = r.a(v3);
                if(!v0.j().verify(v0.a().f(), v3)) {
                    goto label_57;
                }

                v0.k().a(v0.a().f(), v4.b());
                if(v8_2.d()) {
                    v2 = f.c().a(((SSLSocket)v1_1));
                }

                this.j = v1_1;
                this.n = l.a(l.b(this.j));
                this.o = l.a(l.a(this.j));
                this.k = v4;
                y v8_3 = v2 != null ? y.a(v2) : y.b;
                this.l = v8_3;
                if(v1_1 != null) {
                    goto label_54;
                }

                return;
            }
            catch(AssertionError v8) {
                goto label_91;
            }
            catch(Throwable v8_1) {
                goto label_89;
            }

        label_54:
            f.c().b(((SSLSocket)v1_1));
            return;
            try {
            label_57:
                Object v8_4 = v4.b().get(0);
                StringBuilder v3_1 = new StringBuilder();
                v3_1.append("Hostname ");
                v3_1.append(v0.a().f());
                v3_1.append(" not verified:\n    certificate: ");
                v3_1.append(okhttp3.g.a(((Certificate)v8_4)));
                v3_1.append("\n    DN: ");
                v3_1.append(((X509Certificate)v8_4).getSubjectDN().getName());
                v3_1.append("\n    subjectAltNames: ");
                v3_1.append(okhttp3.internal.h.d.a(((X509Certificate)v8_4)));
                throw new SSLPeerUnverifiedException(v3_1.toString());
            label_84:
                throw new IOException("a valid ssl session was not established");
            }
            catch(Throwable v8_1) {
            label_89:
            }
            catch(AssertionError v8) {
            label_91:
                Socket v2_1 = v1_1;
                try {
                label_97:
                    if(okhttp3.internal.c.a(v8)) {
                        throw new IOException(((Throwable)v8));
                    }

                    throw v8;
                }
                catch(Throwable v8_1) {
                label_94:
                    v1_1 = ((Socket)v2);
                }
            }
        }

        if(v1_1 != null) {
            f.c().b(((SSLSocket)v1_1));
        }

        okhttp3.internal.c.a(v1_1);
        throw v8_1;
    }

    private boolean a(SSLSession arg3) {
        boolean v3 = ("NONE".equals(arg3.getProtocol())) || ("SSL_NULL_WITH_NULL_NULL".equals(arg3.getCipherSuite())) ? false : true;
        return v3;
    }

    private void a(okhttp3.internal.b.b arg3, int arg4, okhttp3.e arg5, p arg6) {
        if(this.h.a().i() == null) {
            this.l = y.b;
            this.j = this.i;
            return;
        }

        arg6.b(arg5);
        this.a(arg3);
        arg6.a(arg5, this.k);
        if(this.l == y.d) {
            this.j.setSoTimeout(0);
            this.m = new okhttp3.internal.e.g$a(true).a(this.j, this.h.a().a().f(), this.n, this.o).a(((b)this)).a(arg4).a();
            this.m.c();
        }
    }

    public okhttp3.internal.c.c a(x arg5, okhttp3.u$a arg6, okhttp3.internal.b.g arg7) {
        if(this.m != null) {
            return new okhttp3.internal.e.f(arg5, arg6, arg7, this.m);
        }

        this.j.setSoTimeout(arg6.c());
        this.n.a().a(((long)arg6.c()), TimeUnit.MILLISECONDS);
        this.o.a().a(((long)arg6.d()), TimeUnit.MILLISECONDS);
        return new okhttp3.internal.d.a(arg5, arg7, this.n, this.o);
    }

    public void a(int arg17, int arg18, int arg19, int arg20, boolean arg21, okhttp3.e arg22, p arg23) {
        j v1;
        c v7 = this;
        okhttp3.e v8 = arg22;
        p v9 = arg23;
        if(v7.l != null) {
            goto label_137;
        }

        List v0 = v7.h.a().f();
        okhttp3.internal.b.b v10 = new okhttp3.internal.b.b(v0);
        if(v7.h.a().i() == null) {
            if(v0.contains(k.c)) {
                String v0_1 = v7.h.a().a().f();
                if(f.c().b(v0_1)) {
                }
                else {
                    StringBuilder v3 = new StringBuilder();
                    v3.append("CLEARTEXT communication to ");
                    v3.append(v0_1);
                    v3.append(" not permitted by network security policy");
                    throw new okhttp3.internal.b.e(new UnknownServiceException(v3.toString()));
                }
            }
            else {
                throw new okhttp3.internal.b.e(new UnknownServiceException("CLEARTEXT communication not enabled for client"));
            }
        }

        Socket v11 = null;
        okhttp3.internal.b.e v12 = ((okhttp3.internal.b.e)v11);
        try {
            while(true) {
            label_46:
                if(v7.h.d()) {
                    this.a(arg17, arg18, arg19, arg22, arg23);
                    if(v7.i != null) {
                        goto label_65;
                    }

                    break;
                }
                else {
                    goto label_62;
                }
            }
        }
        catch(IOException v0_2) {
            goto label_106;
        }

        goto label_73;
    label_62:
        int v13 = arg17;
        int v14 = arg18;
        try {
            v7.a(v13, v14, v8, v9);
        }
        catch(IOException v0_2) {
            goto label_106;
        }

    label_65:
        int v15 = arg20;
        try {
            v7.a(v10, v15, v8, v9);
            v9.a(v8, v7.h.c(), v7.h.b(), v7.l);
            goto label_73;
        }
        catch(IOException v0_2) {
        }

    label_106:
        okhttp3.internal.c.a(v7.j);
        okhttp3.internal.c.a(v7.i);
        v7.j = v11;
        v7.i = v11;
        v7.n = ((e)v11);
        v7.o = ((d)v11);
        v7.k = ((r)v11);
        v7.l = ((y)v11);
        v7.m = ((g)v11);
        arg23.a(arg22, v7.h.c(), v7.h.b(), null, v0_2);
        if(v12 == null) {
            v12 = new okhttp3.internal.b.e(v0_2);
        }
        else {
            v12.a(v0_2);
        }

        if((arg21) && (v10.a(v0_2))) {
            goto label_46;
        }

        throw v12;
    label_73:
        if(v7.h.d()) {
            if(v7.i != null) {
            }
            else {
                throw new okhttp3.internal.b.e(new ProtocolException("Too many tunnel connections attempted: 21"));
            }
        }

        if(v7.m != null) {
            v1 = v7.g;
            __monitor_enter(v1);
            try {
                v7.c = v7.m.a();
                __monitor_exit(v1);
            }
            catch(Throwable v0_3) {
                goto label_95;
            }
        }

        return;
        try {
        label_95:
            __monitor_exit(v1);
        }
        catch(Throwable v0_3) {
            goto label_95;
        }

        throw v0_3;
    label_137:
        throw new IllegalStateException("already connected");
    }

    public void a(g arg2) {
        j v0 = this.g;
        __monitor_enter(v0);
        try {
            this.c = arg2.a();
            __monitor_exit(v0);
            return;
        label_7:
            __monitor_exit(v0);
        }
        catch(Throwable v2) {
            goto label_7;
        }

        throw v2;
    }

    public void a(okhttp3.internal.e.i arg2) {
        arg2.a(okhttp3.internal.e.b.e);
    }

    public boolean a(t arg5) {
        if(arg5.g() != this.h.a().a().g()) {
            return 0;
        }

        boolean v1 = true;
        if(!arg5.f().equals(this.h.a().a().f()) && (this.k == null || !okhttp3.internal.h.d.a.a(arg5.f(), this.k.b().get(0)))) {
            v1 = false;
        }

        return v1;
    }

    public boolean a(boolean arg5) {
        int v5;
        if(!this.j.isClosed() && !this.j.isInputShutdown()) {
            if(this.j.isOutputShutdown()) {
            }
            else if(this.m != null) {
                return this.m.d() ^ 1;
            }
            else if(arg5) {
                try {
                    v5 = this.j.getSoTimeout();
                }
                catch(IOException ) {
                    return 0;
                }
                catch(SocketTimeoutException ) {
                    return 1;
                }

                try {
                    this.j.setSoTimeout(1);
                    if(!this.n.f()) {
                        goto label_29;
                    }

                    goto label_26;
                }
                catch(Throwable v0) {
                    try {
                        this.j.setSoTimeout(v5);
                        throw v0;
                    label_26:
                        this.j.setSoTimeout(v5);
                        return 0;
                    label_29:
                        this.j.setSoTimeout(v5);
                        return 1;
                    }
                    catch(SocketTimeoutException ) {
                        return 1;
                    }
                    catch(IOException ) {
                        return 0;
                    }
                }
            }
            else {
                return 1;
            }
        }

        return 0;
    }

    public void b() {
        okhttp3.internal.c.a(this.i);
    }

    public Socket c() {
        return this.j;
    }

    public r d() {
        return this.k;
    }

    public boolean e() {
        boolean v0 = this.m != null ? true : false;
        return v0;
    }

    private aa f() {
        return new okhttp3.aa$a().a(this.h.a().a()).a("Host", okhttp3.internal.c.a(this.h.a().a(), true)).a("Proxy-Connection", "Keep-Alive").a("User-Agent", okhttp3.internal.d.a()).b();
    }

    public String toString() {
        String v1_1;
        StringBuilder v0 = new StringBuilder();
        v0.append("Connection{");
        v0.append(this.h.a().a().f());
        v0.append(":");
        v0.append(this.h.a().a().g());
        v0.append(", proxy=");
        v0.append(this.h.b());
        v0.append(" hostAddress=");
        v0.append(this.h.c());
        v0.append(" cipherSuite=");
        if(this.k != null) {
            h v1 = this.k.a();
        }
        else {
            v1_1 = "none";
        }

        v0.append(v1_1);
        v0.append(" protocol=");
        v0.append(this.l);
        v0.append('}');
        return v0.toString();
    }
}

