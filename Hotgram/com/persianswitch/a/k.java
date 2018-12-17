package com.persianswitch.a;

import com.persianswitch.a.a.l;
import java.util.Arrays;
import java.util.List;
import javax.net.ssl.SSLSocket;

public final class k {
    class com.persianswitch.a.k$1 {
    }

    public final class a {
        private boolean a;
        private String[] b;
        private String[] c;
        private boolean d;

        a(boolean arg1) {
            super();
            this.a = arg1;
        }

        public a(k arg2) {
            super();
            this.a = k.a(arg2);
            this.b = k.b(arg2);
            this.c = k.c(arg2);
            this.d = k.d(arg2);
        }

        public k a() {
            return new k(this, null);
        }

        public a a(boolean arg2) {
            if(this.a) {
                this.d = arg2;
                return this;
            }

            throw new IllegalStateException("no TLS extensions for cleartext connections");
        }

        public a a(ac[] arg4) {
            if(this.a) {
                String[] v0 = new String[arg4.length];
                int v1;
                for(v1 = 0; v1 < arg4.length; ++v1) {
                    v0[v1] = arg4[v1].e;
                }

                return this.b(v0);
            }

            throw new IllegalStateException("no TLS versions for cleartext connections");
        }

        public a a(h[] arg4) {
            if(this.a) {
                String[] v0 = new String[arg4.length];
                int v1;
                for(v1 = 0; v1 < arg4.length; ++v1) {
                    v0[v1] = arg4[v1].aS;
                }

                return this.a(v0);
            }

            throw new IllegalStateException("no cipher suites for cleartext connections");
        }

        static boolean a(a arg0) {
            return arg0.a;
        }

        public a a(String[] arg2) {
            // Method was not decompiled
        }

        static String[] b(a arg0) {
            return arg0.b;
        }

        public a b(String[] arg2) {
            // Method was not decompiled
        }

        static String[] c(a arg0) {
            return arg0.c;
        }

        static boolean d(a arg0) {
            return arg0.d;
        }
    }

    public static final k a;
    public static final k b;
    public static final k c;
    private static final h[] d;
    private final boolean e;
    private final boolean f;
    private final String[] g;
    private final String[] h;

    static {
        k.d = new h[]{h.aK, h.aO, h.W, h.am, h.al, h.av, h.aw, h.F, h.J, h.U, h.D, h.H, h.h};
        k.a = new a(true).a(k.d).a(new ac[]{ac.a, ac.b, ac.c}).a(true).a();
        k.b = new a(k.a).a(new ac[]{ac.c}).a(true).a();
        k.c = new a(false).a();
    }

    private k(a arg2) {
        super();
        this.e = a.a(arg2);
        this.g = a.b(arg2);
        this.h = a.c(arg2);
        this.f = a.d(arg2);
    }

    k(a arg1, com.persianswitch.a.k$1 arg2) {
        this(arg1);
    }

    public boolean a(SSLSocket arg4) {
        if(!this.e) {
            return 0;
        }

        if(this.h != null && !k.a(this.h, arg4.getEnabledProtocols())) {
            return 0;
        }

        if(this.g != null && !k.a(this.g, arg4.getEnabledCipherSuites())) {
            return 0;
        }

        return 1;
    }

    static boolean a(k arg0) {
        return arg0.e;
    }

    private static boolean a(String[] arg4, String[] arg5) {
        if(arg4 != null && arg5 != null && arg4.length != 0) {
            if(arg5.length == 0) {
            }
            else {
                int v1 = arg4.length;
                int v2 = 0;
                while(v2 < v1) {
                    if(l.a(arg5, arg4[v2])) {
                        return 1;
                    }
                    else {
                        ++v2;
                        continue;
                    }

                    return 0;
                }
            }
        }

        return 0;
    }

    void a(SSLSocket arg2, boolean arg3) {
        k v3 = this.b(arg2, arg3);
        if(v3.h != null) {
            arg2.setEnabledProtocols(v3.h);
        }

        if(v3.g != null) {
            arg2.setEnabledCipherSuites(v3.g);
        }
    }

    public boolean a() {
        return this.e;
    }

    private k b(SSLSocket arg5, boolean arg6) {
        Object[] v1;
        String[] v0_1;
        if(this.g != null) {
            Object[] v0 = l.a(String.class, this.g, arg5.getEnabledCipherSuites());
        }
        else {
            v0_1 = arg5.getEnabledCipherSuites();
        }

        if(this.h != null) {
            v1 = l.a(String.class, this.h, arg5.getEnabledProtocols());
        }
        else {
            String[] v1_1 = arg5.getEnabledProtocols();
        }

        if((arg6) && (l.a(arg5.getSupportedCipherSuites(), "TLS_FALLBACK_SCSV"))) {
            v0_1 = l.b(v0_1, "TLS_FALLBACK_SCSV");
        }

        return new a(this).a(v0_1).b(((String[])v1)).a();
    }

    static String[] b(k arg0) {
        return arg0.g;
    }

    public List b() {
        if(this.g == null) {
            return null;
        }

        h[] v0 = new h[this.g.length];
        int v1;
        for(v1 = 0; v1 < this.g.length; ++v1) {
            v0[v1] = h.a(this.g[v1]);
        }

        return l.a(((Object[])v0));
    }

    static String[] c(k arg0) {
        return arg0.h;
    }

    public List c() {
        if(this.h == null) {
            return null;
        }

        ac[] v0 = new ac[this.h.length];
        int v1;
        for(v1 = 0; v1 < this.h.length; ++v1) {
            v0[v1] = ac.a(this.h[v1]);
        }

        return l.a(((Object[])v0));
    }

    static boolean d(k arg0) {
        return arg0.f;
    }

    public boolean d() {
        return this.f;
    }

    public boolean equals(Object arg5) {
        if(!(arg5 instanceof k)) {
            return 0;
        }

        if((((k)arg5)) == this) {
            return 1;
        }

        if(this.e != ((k)arg5).e) {
            return 0;
        }

        if(this.e) {
            if(!Arrays.equals(this.g, ((k)arg5).g)) {
                return 0;
            }
            else if(!Arrays.equals(this.h, ((k)arg5).h)) {
                return 0;
            }
            else if(this.f != ((k)arg5).f) {
                return 0;
            }
        }

        return 1;
    }

    public int hashCode() {
        int v0 = this.e ? ((527 + Arrays.hashCode(this.g)) * 31 + Arrays.hashCode(this.h)) * 31 + (this.f ^ 1) : 17;
        return v0;
    }

    public String toString() {
        if(!this.e) {
            return "ConnectionSpec()";
        }

        String v0 = this.g != null ? this.b().toString() : "[all enabled]";
        String v1 = this.h != null ? this.c().toString() : "[all enabled]";
        return "ConnectionSpec(cipherSuites=" + v0 + ", tlsVersions=" + v1 + ", supportsTlsExtensions=" + this.f + ")";
    }
}

