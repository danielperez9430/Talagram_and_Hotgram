package okhttp3;

import java.util.Arrays;
import java.util.List;
import javax.annotation.Nullable;
import javax.net.ssl.SSLSocket;
import okhttp3.internal.c;

public final class k {
    public final class a {
        boolean a;
        @Nullable String[] b;
        @Nullable String[] c;
        boolean d;

        a(boolean arg1) {
            super();
            this.a = arg1;
        }

        public a(k arg2) {
            super();
            this.a = arg2.d;
            this.b = arg2.f;
            this.c = arg2.g;
            this.d = arg2.e;
        }

        public k a() {
            return new k(this);
        }

        public a a(boolean arg2) {
            if(this.a) {
                this.d = arg2;
                return this;
            }

            throw new IllegalStateException("no TLS extensions for cleartext connections");
        }

        public a a(af[] arg4) {
            if(this.a) {
                String[] v0 = new String[arg4.length];
                int v1;
                for(v1 = 0; v1 < arg4.length; ++v1) {
                    v0[v1] = arg4[v1].f;
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
                    v0[v1] = arg4[v1].bj;
                }

                return this.a(v0);
            }

            throw new IllegalStateException("no cipher suites for cleartext connections");
        }

        public a a(String[] arg2) {
            // Method was not decompiled
        }

        public a b(String[] arg2) {
            // Method was not decompiled
        }
    }

    public static final k a;
    public static final k b;
    public static final k c;
    final boolean d;
    final boolean e;
    @Nullable final String[] f;
    @Nullable final String[] g;
    private static final h[] h;

    static {
        k.h = new h[]{h.aX, h.bb, h.aY, h.bc, h.bi, h.bh, h.aI, h.aJ, h.ag, h.ah, h.E, h.I, h.i};
        k.a = new a(true).a(k.h).a(new af[]{af.a, af.b, af.c, af.d}).a(true).a();
        k.b = new a(k.a).a(new af[]{af.d}).a(true).a();
        k.c = new a(false).a();
    }

    k(a arg2) {
        super();
        this.d = arg2.a;
        this.f = arg2.b;
        this.g = arg2.c;
        this.e = arg2.d;
    }

    void a(SSLSocket arg2, boolean arg3) {
        k v3 = this.b(arg2, arg3);
        if(v3.g != null) {
            arg2.setEnabledProtocols(v3.g);
        }

        if(v3.f != null) {
            arg2.setEnabledCipherSuites(v3.f);
        }
    }

    public boolean a() {
        return this.d;
    }

    public boolean a(SSLSocket arg5) {
        if(!this.d) {
            return 0;
        }

        if(this.g != null && !c.b(c.h, this.g, arg5.getEnabledProtocols())) {
            return 0;
        }

        if(this.f != null && !c.b(h.a, this.f, arg5.getEnabledCipherSuites())) {
            return 0;
        }

        return 1;
    }

    private k b(SSLSocket arg5, boolean arg6) {
        String[] v0 = this.f != null ? c.a(h.a, arg5.getEnabledCipherSuites(), this.f) : arg5.getEnabledCipherSuites();
        String[] v1 = this.g != null ? c.a(c.h, arg5.getEnabledProtocols(), this.g) : arg5.getEnabledProtocols();
        String[] v5 = arg5.getSupportedCipherSuites();
        int v2 = c.a(h.a, v5, "TLS_FALLBACK_SCSV");
        if((arg6) && v2 != -1) {
            v0 = c.a(v0, v5[v2]);
        }

        return new a(this).a(v0).b(v1).a();
    }

    @Nullable public List b() {
        List v0 = this.f != null ? h.a(this.f) : null;
        return v0;
    }

    @Nullable public List c() {
        List v0 = this.g != null ? af.a(this.g) : null;
        return v0;
    }

    public boolean d() {
        return this.e;
    }

    public boolean equals(@Nullable Object arg5) {
        if(!(arg5 instanceof k)) {
            return 0;
        }

        if((((k)arg5)) == this) {
            return 1;
        }

        if(this.d != ((k)arg5).d) {
            return 0;
        }

        if(this.d) {
            if(!Arrays.equals(this.f, ((k)arg5).f)) {
                return 0;
            }
            else if(!Arrays.equals(this.g, ((k)arg5).g)) {
                return 0;
            }
            else if(this.e != ((k)arg5).e) {
                return 0;
            }
        }

        return 1;
    }

    public int hashCode() {
        int v0 = this.d ? ((527 + Arrays.hashCode(this.f)) * 31 + Arrays.hashCode(this.g)) * 31 + (this.e ^ 1) : 17;
        return v0;
    }

    public String toString() {
        if(!this.d) {
            return "ConnectionSpec()";
        }

        String v0 = this.f != null ? this.b().toString() : "[all enabled]";
        String v1 = this.g != null ? this.c().toString() : "[all enabled]";
        return "ConnectionSpec(cipherSuites=" + v0 + ", tlsVersions=" + v1 + ", supportsTlsExtensions=" + this.e + ")";
    }
}

