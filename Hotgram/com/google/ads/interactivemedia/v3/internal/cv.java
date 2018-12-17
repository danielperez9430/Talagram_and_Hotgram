package com.google.ads.interactivemedia.v3.internal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

abstract class cv {
    final class a extends cv {
        public final long aP;
        public final List aQ;
        public final List aR;

        public a(int arg1, long arg2) {
            super(arg1);
            this.aP = arg2;
            this.aQ = new ArrayList();
            this.aR = new ArrayList();
        }

        public void a(a arg2) {
            this.aR.add(arg2);
        }

        public void a(b arg2) {
            this.aQ.add(arg2);
        }

        public b d(int arg5) {
            int v0 = this.aQ.size();
            int v1;
            for(v1 = 0; v1 < v0; ++v1) {
                Object v2 = this.aQ.get(v1);
                if(((b)v2).aO == arg5) {
                    return ((b)v2);
                }
            }

            return null;
        }

        public a e(int arg5) {
            int v0 = this.aR.size();
            int v1;
            for(v1 = 0; v1 < v0; ++v1) {
                Object v2 = this.aR.get(v1);
                if(((a)v2).aO == arg5) {
                    return ((a)v2);
                }
            }

            return null;
        }

        public int f(int arg6) {
            int v0 = this.aQ.size();
            int v1 = 0;
            int v2 = 0;
            int v3 = 0;
            while(v2 < v0) {
                if(this.aQ.get(v2).aO == arg6) {
                    ++v3;
                }

                ++v2;
            }

            v0 = this.aR.size();
            while(v1 < v0) {
                if(this.aR.get(v1).aO == arg6) {
                    ++v3;
                }

                ++v1;
            }

            return v3;
        }

        public String toString() {
            String v0 = a.c(this.aO);
            String v1 = Arrays.toString(this.aQ.toArray(new b[0]));
            String v2 = Arrays.toString(this.aR.toArray(new a[0]));
            StringBuilder v4 = new StringBuilder(String.valueOf(v0).length() + 22 + String.valueOf(v1).length() + String.valueOf(v2).length());
            v4.append(v0);
            v4.append(" leaves: ");
            v4.append(v1);
            v4.append(" containers: ");
            v4.append(v2);
            return v4.toString();
        }
    }

    final class b extends cv {
        public final fp aP;

        public b(int arg1, fp arg2) {
            super(arg1);
            this.aP = arg2;
        }
    }

    public static final int A;
    public static final int B;
    public static final int C;
    public static final int D;
    public static final int E;
    public static final int F;
    public static final int G;
    public static final int H;
    public static final int I;
    public static final int J;
    public static final int K;
    public static final int L;
    public static final int M;
    public static final int N;
    public static final int O;
    public static final int P;
    public static final int Q;
    public static final int R;
    public static final int S;
    public static final int T;
    public static final int U;
    public static final int V;
    public static final int W;
    public static final int X;
    public static final int Y;
    public static final int Z;
    public static final int a;
    public static final int aA;
    public static final int aB;
    public static final int aC;
    public static final int aD;
    public static final int aE;
    public static final int aF;
    public static final int aG;
    public static final int aH;
    public static final int aI;
    public static final int aJ;
    public static final int aK;
    public static final int aL;
    public static final int aM;
    public static final int aN;
    public final int aO;
    public static final int aa;
    public static final int ab;
    public static final int ac;
    public static final int ad;
    public static final int ae;
    public static final int af;
    public static final int ag;
    public static final int ah;
    public static final int ai;
    public static final int aj;
    public static final int ak;
    public static final int al;
    public static final int am;
    public static final int an;
    public static final int ao;
    public static final int ap;
    public static final int aq;
    public static final int ar;
    public static final int as;
    public static final int at;
    public static final int au;
    public static final int av;
    public static final int aw;
    public static final int ax;
    public static final int ay;
    public static final int az;
    public static final int b;
    public static final int c;
    public static final int d;
    public static final int e;
    public static final int f;
    public static final int g;
    public static final int h;
    public static final int i;
    public static final int j;
    public static final int k;
    public static final int l;
    public static final int m;
    public static final int n;
    public static final int o;
    public static final int p;
    public static final int q;
    public static final int r;
    public static final int s;
    public static final int t;
    public static final int u;
    public static final int v;
    public static final int w;
    public static final int x;
    public static final int y;
    public static final int z;

    static {
        cv.a = ft.c("ftyp");
        cv.b = ft.c("avc1");
        cv.c = ft.c("avc3");
        cv.d = ft.c("hvc1");
        cv.e = ft.c("hev1");
        cv.f = ft.c("s263");
        cv.g = ft.c("d263");
        cv.h = ft.c("mdat");
        cv.i = ft.c("mp4a");
        cv.j = ft.c(".mp3");
        cv.k = ft.c("wave");
        cv.l = ft.c("lpcm");
        cv.m = ft.c("sowt");
        cv.n = ft.c("ac-3");
        cv.o = ft.c("dac3");
        cv.p = ft.c("ec-3");
        cv.q = ft.c("dec3");
        cv.r = ft.c("dtsc");
        cv.s = ft.c("dtsh");
        cv.t = ft.c("dtsl");
        cv.u = ft.c("dtse");
        cv.v = ft.c("ddts");
        cv.w = ft.c("tfdt");
        cv.x = ft.c("tfhd");
        cv.y = ft.c("trex");
        cv.z = ft.c("trun");
        cv.A = ft.c("sidx");
        cv.B = ft.c("moov");
        cv.C = ft.c("mvhd");
        cv.D = ft.c("trak");
        cv.E = ft.c("mdia");
        cv.F = ft.c("minf");
        cv.G = ft.c("stbl");
        cv.H = ft.c("avcC");
        cv.I = ft.c("hvcC");
        cv.J = ft.c("esds");
        cv.K = ft.c("moof");
        cv.L = ft.c("traf");
        cv.M = ft.c("mvex");
        cv.N = ft.c("mehd");
        cv.O = ft.c("tkhd");
        cv.P = ft.c("edts");
        cv.Q = ft.c("elst");
        cv.R = ft.c("mdhd");
        cv.S = ft.c("hdlr");
        cv.T = ft.c("stsd");
        cv.U = ft.c("pssh");
        cv.V = ft.c("sinf");
        cv.W = ft.c("schm");
        cv.X = ft.c("schi");
        cv.Y = ft.c("tenc");
        cv.Z = ft.c("encv");
        cv.aa = ft.c("enca");
        cv.ab = ft.c("frma");
        cv.ac = ft.c("saiz");
        cv.ad = ft.c("saio");
        cv.ae = ft.c("sbgp");
        cv.af = ft.c("sgpd");
        cv.ag = ft.c("uuid");
        cv.ah = ft.c("senc");
        cv.ai = ft.c("pasp");
        cv.aj = ft.c("TTML");
        cv.ak = ft.c("vmhd");
        cv.al = ft.c("mp4v");
        cv.am = ft.c("stts");
        cv.an = ft.c("stss");
        cv.ao = ft.c("ctts");
        cv.ap = ft.c("stsc");
        cv.aq = ft.c("stsz");
        cv.ar = ft.c("stz2");
        cv.as = ft.c("stco");
        cv.at = ft.c("co64");
        cv.au = ft.c("tx3g");
        cv.av = ft.c("wvtt");
        cv.aw = ft.c("stpp");
        cv.ax = ft.c("samr");
        cv.ay = ft.c("sawb");
        cv.az = ft.c("udta");
        cv.aA = ft.c("meta");
        cv.aB = ft.c("ilst");
        cv.aC = ft.c("mean");
        cv.aD = ft.c("name");
        cv.aE = ft.c("data");
        cv.aF = ft.c("emsg");
        cv.aG = ft.c("st3d");
        cv.aH = ft.c("sv3d");
        cv.aI = ft.c("proj");
        cv.aJ = ft.c("vp08");
        cv.aK = ft.c("vp09");
        cv.aL = ft.c("vpcC");
        cv.aM = ft.c("camm");
        cv.aN = ft.c("----");
    }

    public cv(int arg1) {
        super();
        this.aO = arg1;
    }

    public static int a(int arg0) {
        return arg0 >> 24 & 255;
    }

    public static int b(int arg1) {
        return arg1 & 16777215;
    }

    public static String c(int arg5) {
        StringBuilder v3 = new StringBuilder(4);
        v3.append(((char)(arg5 >> 24)));
        v3.append(((char)(arg5 >> 16 & 255)));
        v3.append(((char)(arg5 >> 8 & 255)));
        v3.append(((char)(arg5 & 255)));
        return v3.toString();
    }

    public String toString() {
        return cv.c(this.aO);
    }
}

