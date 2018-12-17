package okhttp3;

import e.f;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Nullable;
import javax.net.ssl.SSLPeerUnverifiedException;
import okhttp3.internal.h.c;

public final class g {
    public final class a {
        private final List a;

        public a() {
            super();
            this.a = new ArrayList();
        }

        public g a() {
            return new g(new LinkedHashSet(this.a), null);
        }
    }

    final class b {
        final String a;
        final String b;
        final String c;
        final f d;

        boolean a(String arg11) {
            if(this.a.startsWith("*.")) {
                int v0 = arg11.indexOf(46);
                boolean v2 = true;
                if(arg11.length() - v0 - 1 != this.b.length() || !arg11.regionMatches(false, v0 + 1, this.b, 0, this.b.length())) {
                    v2 = false;
                }
                else {
                }

                return v2;
            }

            return arg11.equals(this.b);
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
    private final Set b;
    @Nullable private final c c;

    static {
        g.a = new a().a();
    }

    g(Set arg1, @Nullable c arg2) {
        super();
        this.b = arg1;
        this.c = arg2;
    }

    static f a(X509Certificate arg0) {
        return f.a(arg0.getPublicKey().getEncoded()).c();
    }

    public static String a(Certificate arg2) {
        if((arg2 instanceof X509Certificate)) {
            return "sha256/" + g.b(((X509Certificate)arg2)).b();
        }

        throw new IllegalArgumentException("Certificate pinning requires X509 certificates");
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

    g a(@Nullable c arg3) {
        g v0 = okhttp3.internal.c.a(this.c, arg3) ? this : new g(this.b, arg3);
        return v0;
    }

    public void a(String arg13, List arg14) {
        Object v9;
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
                    goto label_52;
                }

                v9 = v0.get(v6);
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

            StringBuilder v14 = new StringBuilder();
            v14.append("unsupported hashAlgorithm: ");
            v14.append(((b)v9).c);
            throw new AssertionError(v14.toString());
        label_52:
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
            Object v14_1 = v0.get(v2);
            v1_1.append("\n    ");
            v1_1.append(v14_1);
            ++v2;
        }

        throw new SSLPeerUnverifiedException(v1_1.toString());
    }

    static f b(X509Certificate arg0) {
        return f.a(arg0.getPublicKey().getEncoded()).d();
    }

    public boolean equals(@Nullable Object arg4) {
        boolean v0 = true;
        if((((g)arg4)) == this) {
            return 1;
        }

        if(!(arg4 instanceof g) || !okhttp3.internal.c.a(this.c, ((g)arg4).c) || !this.b.equals(((g)arg4).b)) {
            v0 = false;
        }
        else {
        }

        return v0;
    }

    public int hashCode() {
        int v0 = this.c != null ? this.c.hashCode() : 0;
        return v0 * 31 + this.b.hashCode();
    }
}

