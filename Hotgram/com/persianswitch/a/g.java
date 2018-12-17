package com.persianswitch.a;

import com.persianswitch.a.a.l;
import com.persianswitch.b.f;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import javax.net.ssl.SSLPeerUnverifiedException;

public final class g {
    class com.persianswitch.a.g$1 {
    }

    public final class a {
        private final List a;

        public a() {
            super();
            this.a = new ArrayList();
        }

        public g a() {
            return new g(l.a(this.a), null, null);
        }
    }

    final class b {
        final String a;
        final String b;
        final String c;
        final f d;

        boolean a(String arg8) {
            if(this.a.startsWith("*.")) {
                return arg8.regionMatches(false, arg8.indexOf(46) + 1, this.b, 0, this.b.length());
            }

            return arg8.equals(this.b);
        }

        public boolean equals(Object arg3) {
            boolean v3 = !(arg3 instanceof b) || !this.a.equals(((b)arg3).a) || !this.c.equals(((b)arg3).c) || !this.d.equals(((b)arg3).d) ? false : true;
            return v3;
        }

        public int hashCode() {
            return ((527 + this.a.hashCode()) * 31 + this.c.hashCode()) * 31 + this.d.hashCode();
        }

        public String toString() {
            return this.c + this.d.b();
        }
    }

    public static final g a;
    private final List b;
    private final com.persianswitch.a.a.d.b c;

    static {
        g.a = new a().a();
    }

    private g(List arg1, com.persianswitch.a.a.d.b arg2) {
        super();
        this.b = arg1;
        this.c = arg2;
    }

    g(List arg1, com.persianswitch.a.a.d.b arg2, com.persianswitch.a.g$1 arg3) {
        this(arg1, arg2);
    }

    static f a(X509Certificate arg0) {
        return l.a(f.a(arg0.getPublicKey().getEncoded()));
    }

    public static String a(Certificate arg2) {
        if((arg2 instanceof X509Certificate)) {
            return "sha256/" + g.b(((X509Certificate)arg2)).b();
        }

        throw new IllegalArgumentException("Certificate pinning requires X509 certificates");
    }

    g a(com.persianswitch.a.a.d.b arg3) {
        g v0 = this.c != arg3 ? new g(this.b, arg3) : this;
        return v0;
    }

    List a(String arg5) {
        ArrayList v0_1;
        List v0 = Collections.emptyList();
        Iterator v1 = this.b.iterator();
        while(v1.hasNext()) {
            Object v2 = v1.next();
            if(!((b)v2).a(arg5)) {
                continue;
            }

            if(v0.isEmpty()) {
                v0_1 = new ArrayList();
            }

            ((List)v0_1).add(v2);
        }

        return ((List)v0_1);
    }

    public void a(String arg13, List arg14) {
        List v0 = this.a(arg13);
        if(v0.isEmpty()) {
            return;
        }

        if(this.c != null) {
            arg14 = this.c.a(arg14, arg13);
        }

        int v1 = arg14.size();
        int v2 = 0;
        int v3;
        for(v3 = 0; v3 < v1; ++v3) {
            Object v4 = arg14.get(v3);
            int v5 = v0.size();
            f v7 = null;
            f v8 = v7;
            int v6;
            for(v6 = 0; true; ++v6) {
                if(v6 >= v5) {
                    goto label_45;
                }

                Object v9 = v0.get(v6);
                if(((b)v9).c.equals("sha256/")) {
                    if(v7 == null) {
                        v7 = g.b(((X509Certificate)v4));
                    }

                    if(!((b)v9).d.equals(v7)) {
                        goto label_40;
                    }

                    return;
                }
                else {
                    if(!((b)v9).c.equals("sha1/")) {
                        break;
                    }

                    if(v8 == null) {
                        v8 = g.a(((X509Certificate)v4));
                    }

                    if(!((b)v9).d.equals(v8)) {
                        goto label_40;
                    }

                    return;
                }

            label_40:
            }

            throw new AssertionError();
        label_45:
        }

        StringBuilder v1_1 = new StringBuilder();
        v1_1.append("Certificate pinning failure!");
        v1_1.append("\n  Peer certificate chain:");
        v3 = arg14.size();
        int v4_1;
        for(v4_1 = 0; v4_1 < v3; ++v4_1) {
            Object v5_1 = arg14.get(v4_1);
            v1_1.append("\n    ");
            v1_1.append(g.a(((Certificate)v5_1)));
            v1_1.append(": ");
            v1_1.append(((X509Certificate)v5_1).getSubjectDN().getName());
        }

        v1_1.append("\n  Pinned certificates for ");
        v1_1.append(arg13);
        v1_1.append(":");
        int v13 = v0.size();
        while(v2 < v13) {
            Object v14 = v0.get(v2);
            v1_1.append("\n    ");
            v1_1.append(v14);
            ++v2;
        }

        throw new SSLPeerUnverifiedException(v1_1.toString());
    }

    static f b(X509Certificate arg0) {
        return l.b(f.a(arg0.getPublicKey().getEncoded()));
    }
}

