package com.persianswitch.a;

import com.persianswitch.a.a.l;
import java.security.cert.Certificate;
import java.util.Collections;
import java.util.List;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;

public final class p {
    private final ac a;
    private final h b;
    private final List c;
    private final List d;

    private p(ac arg1, h arg2, List arg3, List arg4) {
        super();
        this.a = arg1;
        this.b = arg2;
        this.c = arg3;
        this.d = arg4;
    }

    public static p a(SSLSession arg4) {
        Certificate[] v2;
        String v0 = arg4.getCipherSuite();
        if(v0 != null) {
            h v0_1 = h.a(v0);
            String v1 = arg4.getProtocol();
            if(v1 != null) {
                ac v1_1 = ac.a(v1);
                try {
                    v2 = arg4.getPeerCertificates();
                }
                catch(SSLPeerUnverifiedException ) {
                    v2 = null;
                }

                List v2_1 = v2 != null ? l.a(((Object[])v2)) : Collections.emptyList();
                Certificate[] v4 = arg4.getLocalCertificates();
                List v4_1 = v4 != null ? l.a(((Object[])v4)) : Collections.emptyList();
                return new p(v1_1, v0_1, v2_1, v4_1);
            }

            throw new IllegalStateException("tlsVersion == null");
        }

        throw new IllegalStateException("cipherSuite == null");
    }

    public h a() {
        return this.b;
    }

    public List b() {
        return this.c;
    }

    public boolean equals(Object arg4) {
        boolean v1 = false;
        if(!(arg4 instanceof p)) {
            return 0;
        }

        if((l.a(this.b, ((p)arg4).b)) && (this.b.equals(((p)arg4).b)) && (this.c.equals(((p)arg4).c)) && (this.d.equals(((p)arg4).d))) {
            v1 = true;
        }

        return v1;
    }

    public int hashCode() {
        int v0 = this.a != null ? this.a.hashCode() : 0;
        return (((527 + v0) * 31 + this.b.hashCode()) * 31 + this.c.hashCode()) * 31 + this.d.hashCode();
    }
}

