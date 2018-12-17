package okhttp3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public final class h {
    final class okhttp3.h$1 implements Comparator {
        okhttp3.h$1() {
            super();
        }

        public int a(String arg7, String arg8) {
            int v2;
            int v0 = Math.min(arg7.length(), arg8.length());
            int v1;
            for(v1 = 4; true; ++v1) {
                v2 = -1;
                if(v1 >= v0) {
                    break;
                }

                int v4 = arg7.charAt(v1);
                int v5 = arg8.charAt(v1);
                if(v4 != v5) {
                    if(v4 < v5) {
                    }
                    else {
                        v2 = 1;
                    }

                    return v2;
                }
            }

            int v7 = arg7.length();
            int v8 = arg8.length();
            if(v7 != v8) {
                if(v7 < v8) {
                }
                else {
                    v2 = 1;
                }

                return v2;
            }

            return 0;
        }

        public int compare(Object arg1, Object arg2) {
            return this.a(((String)arg1), ((String)arg2));
        }
    }

    public static final h A;
    public static final h B;
    public static final h C;
    public static final h D;
    public static final h E;
    public static final h F;
    public static final h G;
    public static final h H;
    public static final h I;
    public static final h J;
    public static final h K;
    public static final h L;
    public static final h M;
    public static final h N;
    public static final h O;
    public static final h P;
    public static final h Q;
    public static final h R;
    public static final h S;
    public static final h T;
    public static final h U;
    public static final h V;
    public static final h W;
    public static final h X;
    public static final h Y;
    public static final h Z;
    static final Comparator a;
    public static final h aA;
    public static final h aB;
    public static final h aC;
    public static final h aD;
    public static final h aE;
    public static final h aF;
    public static final h aG;
    public static final h aH;
    public static final h aI;
    public static final h aJ;
    public static final h aK;
    public static final h aL;
    public static final h aM;
    public static final h aN;
    public static final h aO;
    public static final h aP;
    public static final h aQ;
    public static final h aR;
    public static final h aS;
    public static final h aT;
    public static final h aU;
    public static final h aV;
    public static final h aW;
    public static final h aX;
    public static final h aY;
    public static final h aZ;
    public static final h aa;
    public static final h ab;
    public static final h ac;
    public static final h ad;
    public static final h ae;
    public static final h af;
    public static final h ag;
    public static final h ah;
    public static final h ai;
    public static final h aj;
    public static final h ak;
    public static final h al;
    public static final h am;
    public static final h an;
    public static final h ao;
    public static final h ap;
    public static final h aq;
    public static final h ar;
    public static final h as;
    public static final h at;
    public static final h au;
    public static final h av;
    public static final h aw;
    public static final h ax;
    public static final h ay;
    public static final h az;
    public static final h b;
    public static final h ba;
    public static final h bb;
    public static final h bc;
    public static final h bd;
    public static final h be;
    public static final h bf;
    public static final h bg;
    public static final h bh;
    public static final h bi;
    final String bj;
    private static final Map bk;
    public static final h c;
    public static final h d;
    public static final h e;
    public static final h f;
    public static final h g;
    public static final h h;
    public static final h i;
    public static final h j;
    public static final h k;
    public static final h l;
    public static final h m;
    public static final h n;
    public static final h o;
    public static final h p;
    public static final h q;
    public static final h r;
    public static final h s;
    public static final h t;
    public static final h u;
    public static final h v;
    public static final h w;
    public static final h x;
    public static final h y;
    public static final h z;

    static {
        h.a = new okhttp3.h$1();
        h.bk = new TreeMap(h.a);
        h.b = h.a("SSL_RSA_WITH_NULL_MD5", 1);
        h.c = h.a("SSL_RSA_WITH_NULL_SHA", 2);
        h.d = h.a("SSL_RSA_EXPORT_WITH_RC4_40_MD5", 3);
        h.e = h.a("SSL_RSA_WITH_RC4_128_MD5", 4);
        h.f = h.a("SSL_RSA_WITH_RC4_128_SHA", 5);
        h.g = h.a("SSL_RSA_EXPORT_WITH_DES40_CBC_SHA", 8);
        h.h = h.a("SSL_RSA_WITH_DES_CBC_SHA", 9);
        h.i = h.a("SSL_RSA_WITH_3DES_EDE_CBC_SHA", 10);
        h.j = h.a("SSL_DHE_DSS_EXPORT_WITH_DES40_CBC_SHA", 17);
        h.k = h.a("SSL_DHE_DSS_WITH_DES_CBC_SHA", 18);
        h.l = h.a("SSL_DHE_DSS_WITH_3DES_EDE_CBC_SHA", 19);
        h.m = h.a("SSL_DHE_RSA_EXPORT_WITH_DES40_CBC_SHA", 20);
        h.n = h.a("SSL_DHE_RSA_WITH_DES_CBC_SHA", 21);
        h.o = h.a("SSL_DHE_RSA_WITH_3DES_EDE_CBC_SHA", 22);
        h.p = h.a("SSL_DH_anon_EXPORT_WITH_RC4_40_MD5", 23);
        h.q = h.a("SSL_DH_anon_WITH_RC4_128_MD5", 24);
        h.r = h.a("SSL_DH_anon_EXPORT_WITH_DES40_CBC_SHA", 25);
        h.s = h.a("SSL_DH_anon_WITH_DES_CBC_SHA", 26);
        h.t = h.a("SSL_DH_anon_WITH_3DES_EDE_CBC_SHA", 27);
        h.u = h.a("TLS_KRB5_WITH_DES_CBC_SHA", 30);
        h.v = h.a("TLS_KRB5_WITH_3DES_EDE_CBC_SHA", 31);
        h.w = h.a("TLS_KRB5_WITH_RC4_128_SHA", 32);
        h.x = h.a("TLS_KRB5_WITH_DES_CBC_MD5", 34);
        h.y = h.a("TLS_KRB5_WITH_3DES_EDE_CBC_MD5", 35);
        h.z = h.a("TLS_KRB5_WITH_RC4_128_MD5", 36);
        h.A = h.a("TLS_KRB5_EXPORT_WITH_DES_CBC_40_SHA", 38);
        h.B = h.a("TLS_KRB5_EXPORT_WITH_RC4_40_SHA", 40);
        h.C = h.a("TLS_KRB5_EXPORT_WITH_DES_CBC_40_MD5", 41);
        h.D = h.a("TLS_KRB5_EXPORT_WITH_RC4_40_MD5", 43);
        h.E = h.a("TLS_RSA_WITH_AES_128_CBC_SHA", 47);
        h.F = h.a("TLS_DHE_DSS_WITH_AES_128_CBC_SHA", 50);
        h.G = h.a("TLS_DHE_RSA_WITH_AES_128_CBC_SHA", 51);
        h.H = h.a("TLS_DH_anon_WITH_AES_128_CBC_SHA", 52);
        h.I = h.a("TLS_RSA_WITH_AES_256_CBC_SHA", 53);
        h.J = h.a("TLS_DHE_DSS_WITH_AES_256_CBC_SHA", 56);
        h.K = h.a("TLS_DHE_RSA_WITH_AES_256_CBC_SHA", 57);
        h.L = h.a("TLS_DH_anon_WITH_AES_256_CBC_SHA", 58);
        h.M = h.a("TLS_RSA_WITH_NULL_SHA256", 59);
        h.N = h.a("TLS_RSA_WITH_AES_128_CBC_SHA256", 60);
        h.O = h.a("TLS_RSA_WITH_AES_256_CBC_SHA256", 61);
        h.P = h.a("TLS_DHE_DSS_WITH_AES_128_CBC_SHA256", 64);
        h.Q = h.a("TLS_RSA_WITH_CAMELLIA_128_CBC_SHA", 65);
        h.R = h.a("TLS_DHE_DSS_WITH_CAMELLIA_128_CBC_SHA", 68);
        h.S = h.a("TLS_DHE_RSA_WITH_CAMELLIA_128_CBC_SHA", 69);
        h.T = h.a("TLS_DHE_RSA_WITH_AES_128_CBC_SHA256", 103);
        h.U = h.a("TLS_DHE_DSS_WITH_AES_256_CBC_SHA256", 106);
        h.V = h.a("TLS_DHE_RSA_WITH_AES_256_CBC_SHA256", 107);
        h.W = h.a("TLS_DH_anon_WITH_AES_128_CBC_SHA256", 108);
        h.X = h.a("TLS_DH_anon_WITH_AES_256_CBC_SHA256", 109);
        h.Y = h.a("TLS_RSA_WITH_CAMELLIA_256_CBC_SHA", 132);
        h.Z = h.a("TLS_DHE_DSS_WITH_CAMELLIA_256_CBC_SHA", 135);
        h.aa = h.a("TLS_DHE_RSA_WITH_CAMELLIA_256_CBC_SHA", 136);
        h.ab = h.a("TLS_PSK_WITH_RC4_128_SHA", 138);
        h.ac = h.a("TLS_PSK_WITH_3DES_EDE_CBC_SHA", 139);
        h.ad = h.a("TLS_PSK_WITH_AES_128_CBC_SHA", 140);
        h.ae = h.a("TLS_PSK_WITH_AES_256_CBC_SHA", 141);
        h.af = h.a("TLS_RSA_WITH_SEED_CBC_SHA", 150);
        h.ag = h.a("TLS_RSA_WITH_AES_128_GCM_SHA256", 156);
        h.ah = h.a("TLS_RSA_WITH_AES_256_GCM_SHA384", 157);
        h.ai = h.a("TLS_DHE_RSA_WITH_AES_128_GCM_SHA256", 158);
        h.aj = h.a("TLS_DHE_RSA_WITH_AES_256_GCM_SHA384", 159);
        h.ak = h.a("TLS_DHE_DSS_WITH_AES_128_GCM_SHA256", 162);
        h.al = h.a("TLS_DHE_DSS_WITH_AES_256_GCM_SHA384", 163);
        h.am = h.a("TLS_DH_anon_WITH_AES_128_GCM_SHA256", 166);
        h.an = h.a("TLS_DH_anon_WITH_AES_256_GCM_SHA384", 167);
        h.ao = h.a("TLS_EMPTY_RENEGOTIATION_INFO_SCSV", 255);
        h.ap = h.a("TLS_FALLBACK_SCSV", 22016);
        h.aq = h.a("TLS_ECDH_ECDSA_WITH_NULL_SHA", 49153);
        h.ar = h.a("TLS_ECDH_ECDSA_WITH_RC4_128_SHA", 49154);
        h.as = h.a("TLS_ECDH_ECDSA_WITH_3DES_EDE_CBC_SHA", 49155);
        h.at = h.a("TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA", 49156);
        h.au = h.a("TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA", 49157);
        h.av = h.a("TLS_ECDHE_ECDSA_WITH_NULL_SHA", 49158);
        h.aw = h.a("TLS_ECDHE_ECDSA_WITH_RC4_128_SHA", 49159);
        h.ax = h.a("TLS_ECDHE_ECDSA_WITH_3DES_EDE_CBC_SHA", 49160);
        h.ay = h.a("TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA", 49161);
        h.az = h.a("TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA", 49162);
        h.aA = h.a("TLS_ECDH_RSA_WITH_NULL_SHA", 49163);
        h.aB = h.a("TLS_ECDH_RSA_WITH_RC4_128_SHA", 49164);
        h.aC = h.a("TLS_ECDH_RSA_WITH_3DES_EDE_CBC_SHA", 49165);
        h.aD = h.a("TLS_ECDH_RSA_WITH_AES_128_CBC_SHA", 49166);
        h.aE = h.a("TLS_ECDH_RSA_WITH_AES_256_CBC_SHA", 49167);
        h.aF = h.a("TLS_ECDHE_RSA_WITH_NULL_SHA", 49168);
        h.aG = h.a("TLS_ECDHE_RSA_WITH_RC4_128_SHA", 49169);
        h.aH = h.a("TLS_ECDHE_RSA_WITH_3DES_EDE_CBC_SHA", 49170);
        h.aI = h.a("TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA", 49171);
        h.aJ = h.a("TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA", 49172);
        h.aK = h.a("TLS_ECDH_anon_WITH_NULL_SHA", 49173);
        h.aL = h.a("TLS_ECDH_anon_WITH_RC4_128_SHA", 49174);
        h.aM = h.a("TLS_ECDH_anon_WITH_3DES_EDE_CBC_SHA", 49175);
        h.aN = h.a("TLS_ECDH_anon_WITH_AES_128_CBC_SHA", 49176);
        h.aO = h.a("TLS_ECDH_anon_WITH_AES_256_CBC_SHA", 49177);
        h.aP = h.a("TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA256", 49187);
        h.aQ = h.a("TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA384", 49188);
        h.aR = h.a("TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA256", 49189);
        h.aS = h.a("TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA384", 49190);
        h.aT = h.a("TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256", 49191);
        h.aU = h.a("TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA384", 49192);
        h.aV = h.a("TLS_ECDH_RSA_WITH_AES_128_CBC_SHA256", 49193);
        h.aW = h.a("TLS_ECDH_RSA_WITH_AES_256_CBC_SHA384", 49194);
        h.aX = h.a("TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256", 49195);
        h.aY = h.a("TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384", 49196);
        h.aZ = h.a("TLS_ECDH_ECDSA_WITH_AES_128_GCM_SHA256", 49197);
        h.ba = h.a("TLS_ECDH_ECDSA_WITH_AES_256_GCM_SHA384", 49198);
        h.bb = h.a("TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256", 49199);
        h.bc = h.a("TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384", 49200);
        h.bd = h.a("TLS_ECDH_RSA_WITH_AES_128_GCM_SHA256", 49201);
        h.be = h.a("TLS_ECDH_RSA_WITH_AES_256_GCM_SHA384", 49202);
        h.bf = h.a("TLS_ECDHE_PSK_WITH_AES_128_CBC_SHA", 49205);
        h.bg = h.a("TLS_ECDHE_PSK_WITH_AES_256_CBC_SHA", 49206);
        h.bh = h.a("TLS_ECDHE_RSA_WITH_CHACHA20_POLY1305_SHA256", 52392);
        h.bi = h.a("TLS_ECDHE_ECDSA_WITH_CHACHA20_POLY1305_SHA256", 52393);
    }

    private h(String arg1) {
        super();
        if(arg1 != null) {
            this.bj = arg1;
            return;
        }

        throw new NullPointerException();
    }

    static List a(String[] arg4) {
        ArrayList v0 = new ArrayList(arg4.length);
        int v1 = arg4.length;
        int v2;
        for(v2 = 0; v2 < v1; ++v2) {
            ((List)v0).add(h.a(arg4[v2]));
        }

        return Collections.unmodifiableList(((List)v0));
    }

    public static h a(String arg3) {
        h v1_1;
        Class v0 = h.class;
        __monitor_enter(v0);
        try {
            Object v1 = h.bk.get(arg3);
            if(v1 == null) {
                v1_1 = new h(arg3);
                h.bk.put(arg3, v1_1);
            }
        }
        catch(Throwable v3) {
            __monitor_exit(v0);
            throw v3;
        }

        __monitor_exit(v0);
        return v1_1;
    }

    private static h a(String arg0, int arg1) {
        return h.a(arg0);
    }

    public String toString() {
        return this.bj;
    }
}

