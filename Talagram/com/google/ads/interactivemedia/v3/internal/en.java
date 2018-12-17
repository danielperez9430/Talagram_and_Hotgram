package com.google.ads.interactivemedia.v3.internal;

import android.util.Pair;
import android.util.SparseArray;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public final class en implements cc {
    class com.google.ads.interactivemedia.v3.internal.en$1 {
    }

    final class a implements ek {
        a(en arg1, com.google.ads.interactivemedia.v3.internal.en$1 arg2) {
            this(arg1);
        }

        private a(en arg1) {
            this.a = arg1;
            super();
        }

        public int a(int arg2) {
            return this.a.a(arg2);
        }

        public void a(int arg2, double arg3) {
            this.a.a(arg2, arg3);
        }

        public void a(int arg2, int arg3, cd arg4) {
            this.a.a(arg2, arg3, arg4);
        }

        public void a(int arg2, long arg3) {
            this.a.a(arg2, arg3);
        }

        public void a(int arg7, long arg8, long arg10) {
            this.a.a(arg7, arg8, arg10);
        }

        public void a(int arg2, String arg3) {
            this.a.a(arg2, arg3);
        }

        public boolean b(int arg2) {
            return this.a.b(arg2);
        }

        public void c(int arg2) {
            this.a.c(arg2);
        }
    }

    final class b {
        public float A;
        public float B;
        public float C;
        public float D;
        public float E;
        public int F;
        public int G;
        public int H;
        public long I;
        public long J;
        public ck K;
        public int L;
        private String M;
        public String a;
        public int b;
        public int c;
        public int d;
        public boolean e;
        public byte[] f;
        public byte[] g;
        public byte[] h;
        public int i;
        public int j;
        public int k;
        public int l;
        public int m;
        public byte[] n;
        public int o;
        public boolean p;
        public int q;
        public int r;
        public int s;
        public int t;
        public int u;
        public float v;
        public float w;
        public float x;
        public float y;
        public float z;

        b(com.google.ads.interactivemedia.v3.internal.en$1 arg1) {
            this();
        }

        private b() {
            super();
            this.i = -1;
            this.j = -1;
            this.k = -1;
            this.l = -1;
            this.m = 0;
            this.n = null;
            this.o = -1;
            this.p = false;
            this.q = -1;
            this.r = -1;
            this.s = -1;
            this.t = 1000;
            this.u = 200;
            this.v = -1f;
            this.w = -1f;
            this.x = -1f;
            this.y = -1f;
            this.z = -1f;
            this.A = -1f;
            this.B = -1f;
            this.C = -1f;
            this.D = -1f;
            this.E = -1f;
            this.F = 1;
            this.G = -1;
            this.H = 8000;
            this.I = 0;
            this.J = 0;
            this.M = "eng";
        }

        static String a(b arg0, String arg1) {
            arg0.M = arg1;
            return arg1;
        }

        public void a(ce arg22, int arg23, long arg24) {
            bj v1_2;
            ArrayList v2_4;
            int v17;
            StringBuilder v4_1;
            int v1_1;
            b v0 = this;
            String v1 = v0.a;
            int v3 = 3;
            int v4 = 8;
            int v5 = -1;
            switch(v1.hashCode()) {
                case 855502857: {
                    if(!v1.equals("V_MPEGH/ISO/HEVC")) {
                        goto label_133;
                    }

                    v1_1 = 7;
                    break;
                }
                case 1422270023: {
                    if(!v1.equals("S_TEXT/UTF8")) {
                        goto label_133;
                    }

                    v1_1 = 22;
                    break;
                }
                case 1809237540: {
                    if(!v1.equals("V_MPEG2")) {
                        goto label_133;
                    }

                    v1_1 = 2;
                    break;
                }
                case 1950749482: {
                    if(!v1.equals("A_EAC3")) {
                        goto label_133;
                    }

                    v1_1 = 14;
                    break;
                }
                case 1950789798: {
                    if(!v1.equals("A_FLAC")) {
                        goto label_133;
                    }

                    v1_1 = 19;
                    break;
                }
                case 1951062397: {
                    if(!v1.equals("A_OPUS")) {
                        goto label_133;
                    }

                    v1_1 = 10;
                    break;
                }
                case -2095576542: {
                    if(!v1.equals("V_MPEG4/ISO/AP")) {
                        goto label_133;
                    }

                    v1_1 = 5;
                    break;
                }
                case -2095575984: {
                    if(!v1.equals("V_MPEG4/ISO/SP")) {
                        goto label_133;
                    }

                    v1_1 = 3;
                    break;
                }
                case -1985379776: {
                    if(!v1.equals("A_MS/ACM")) {
                        goto label_133;
                    }

                    v1_1 = 20;
                    break;
                }
                case -1784763192: {
                    if(!v1.equals("A_TRUEHD")) {
                        goto label_133;
                    }

                    v1_1 = 15;
                    break;
                }
                case -1373388978: {
                    if(!v1.equals("V_MS/VFW/FOURCC")) {
                        goto label_133;
                    }

                    v1_1 = 8;
                    break;
                }
                case 725957860: {
                    if(!v1.equals("A_PCM/INT/LIT")) {
                        goto label_133;
                    }

                    v1_1 = 21;
                    break;
                }
                case -1482641357: {
                    if(!v1.equals("A_MPEG/L3")) {
                        goto label_133;
                    }

                    v1_1 = 12;
                    break;
                }
                case 542569478: {
                    if(!v1.equals("A_DTS/EXPRESS")) {
                        goto label_133;
                    }

                    v1_1 = 17;
                    break;
                }
                case -1730367663: {
                    if(!v1.equals("A_VORBIS")) {
                        goto label_133;
                    }

                    v1_1 = 9;
                    break;
                }
                case -538363189: {
                    if(v1.equals("V_MPEG4/ISO/ASP")) {
                        v1_1 = 4;
                        goto label_134;
                    }

                label_133:
                    v1_1 = -1;
                    break;
                }
                case -538363109: {
                    if(!v1.equals("V_MPEG4/ISO/AVC")) {
                        goto label_133;
                    }

                    v1_1 = 6;
                    break;
                }
                case -425012669: {
                    if(!v1.equals("S_VOBSUB")) {
                        goto label_133;
                    }

                    v1_1 = 23;
                    break;
                }
                case -356037306: {
                    if(!v1.equals("A_DTS/LOSSLESS")) {
                        goto label_133;
                    }

                    v1_1 = 18;
                    break;
                }
                case 62923557: {
                    if(!v1.equals("A_AAC")) {
                        goto label_133;
                    }

                    v1_1 = 11;
                    break;
                }
                case 62923603: {
                    if(!v1.equals("A_AC3")) {
                        goto label_133;
                    }

                    v1_1 = 13;
                    break;
                }
                case 62927045: {
                    if(!v1.equals("A_DTS")) {
                        goto label_133;
                    }

                    v1_1 = 16;
                    break;
                }
                case 82338133: {
                    if(!v1.equals("V_VP8")) {
                        goto label_133;
                    }

                    v1_1 = 0;
                    break;
                }
                case 82338134: {
                    if(!v1.equals("V_VP9")) {
                        goto label_133;
                    }

                    v1_1 = 1;
                    break;
                }
                case 99146302: {
                    if(!v1.equals("S_HDMV/PGS")) {
                        goto label_133;
                    }

                    v1_1 = 24;
                    break;
                }
                default: {
                    goto label_133;
                }
            }

        label_134:
            int v2 = 38;
            aw v6 = null;
            switch(v1_1) {
                case 0: {
                    goto label_278;
                }
                case 1: {
                    goto label_276;
                }
                case 2: {
                    goto label_274;
                }
                case 3: 
                case 4: 
                case 5: {
                    goto label_269;
                }
                case 6: {
                    goto label_257;
                }
                case 7: {
                    goto label_251;
                }
                case 8: {
                    goto label_244;
                }
                case 9: {
                    goto label_236;
                }
                case 10: {
                    goto label_212;
                }
                case 11: {
                    goto label_208;
                }
                case 12: {
                    goto label_202;
                }
                case 13: {
                    goto label_200;
                }
                case 14: {
                    goto label_198;
                }
                case 15: {
                    goto label_196;
                }
                case 16: 
                case 17: {
                    goto label_194;
                }
                case 18: {
                    goto label_192;
                }
                case 19: {
                    goto label_190;
                }
                case 20: {
                    goto label_166;
                }
                case 21: {
                    goto label_147;
                }
                case 22: {
                    goto label_145;
                }
                case 23: {
                    goto label_143;
                }
                case 24: {
                    goto label_141;
                }
            }

            throw new bl("Unrecognized codec identifier.");
        label_166:
            v1 = "audio/raw";
            if(b.d(new fp(v0.h))) {
                v3 = ft.a(v0.G);
                if(v3 != 0) {
                    goto label_151;
                }

                v3 = v0.G;
                v4_1 = new StringBuilder(v2);
                v4_1.append("Unsupported PCM bit depth: ");
                v4_1.append(v3);
                throw new bl(v4_1.toString());
            }

            throw new bl("Non-PCM MS/ACM is unsupported");
        label_236:
            String v8 = "audio/vorbis";
            List v2_1 = b.a(v0.h);
            int v10 = 8192;
            goto label_282;
        label_244:
            v1 = "video/wvc1";
            v2_1 = b.a(new fp(v0.h));
            goto label_249;
        label_251:
            v1 = "video/hevc";
            Pair v2_2 = b.c(new fp(v0.h));
            goto label_262;
        label_190:
            v1 = "audio/x-flac";
            goto label_209;
        label_192:
            v1 = "audio/vnd.dts.hd";
            goto label_279;
        label_257:
            v1 = "video/avc";
            v2_2 = b.b(new fp(v0.h));
        label_262:
            Object v3_1 = v2_2.first;
            v0.L = v2_2.second.intValue();
            v8 = v1;
            Object v2_3 = v3_1;
            goto label_281;
        label_194:
            v1 = "audio/vnd.dts";
            goto label_279;
        label_196:
            v1 = "audio/true-hd";
            goto label_279;
        label_198:
            v1 = "audio/eac3";
            goto label_279;
        label_200:
            v1 = "audio/ac3";
            goto label_279;
        label_202:
            v8 = "audio/mpeg";
            v2_1 = ((List)v6);
            v10 = 4096;
            goto label_282;
        label_269:
            v1 = "video/mp4v-es";
            if(v0.h != null) {
            }
            else {
                v2_1 = ((List)v6);
                goto label_249;
            label_141:
                v1 = "application/pgs";
                goto label_279;
            label_143:
                v1 = "application/vobsub";
                goto label_209;
            label_208:
                v1 = "audio/mp4a-latm";
            }

        label_209:
            v2_1 = Collections.singletonList(v0.h);
        label_249:
            v8 = v1;
            goto label_281;
        label_145:
            v1 = "application/x-subrip";
            goto label_279;
        label_274:
            v1 = "video/mpeg2";
            goto label_279;
        label_147:
            v1 = "audio/raw";
            v3 = ft.a(v0.G);
            if(v3 != 0) {
            label_151:
                v8 = v1;
                v17 = v3;
                v2_1 = ((List)v6);
                v10 = -1;
            }
            else {
                v3 = v0.G;
                v4_1 = new StringBuilder(v2);
                v4_1.append("Unsupported PCM bit depth: ");
                v4_1.append(v3);
                throw new bl(v4_1.toString());
            label_276:
                v1 = "video/x-vnd.on2.vp9";
                goto label_279;
            label_212:
                ArrayList v7 = new ArrayList(v3);
                ((List)v7).add(v0.h);
                ((List)v7).add(ByteBuffer.allocate(v4).order(ByteOrder.nativeOrder()).putLong(v0.I).array());
                ((List)v7).add(ByteBuffer.allocate(v4).order(ByteOrder.nativeOrder()).putLong(v0.J).array());
                v8 = "audio/opus";
                v2_4 = v7;
                v10 = 5760;
                goto label_282;
            label_278:
                v1 = "video/x-vnd.on2.vp8";
            label_279:
                v8 = v1;
                v2_1 = ((List)v6);
            label_281:
                v10 = -1;
            label_282:
                v17 = -1;
            }

            if(fl.a(v8)) {
                v1_2 = bj.a(Integer.toString(arg23), v8, -1, v10, arg24, v0.F, v0.H, ((List)v2_4), v0.M, v17);
            }
            else if(fl.b(v8)) {
                if(v0.m == 0) {
                    v1_1 = v0.k == v5 ? v0.i : v0.k;
                    v0.k = v1_1;
                    v1_1 = v0.l == v5 ? v0.j : v0.l;
                    v0.l = v1_1;
                }

                float v17_1 = v0.k == v5 || v0.l == v5 ? -1f : (((float)(v0.j * v0.k))) / (((float)(v0.i * v0.l)));
                if(v0.p) {
                    v6 = new aw(v0.q, v0.s, v0.r, this.a());
                }

                v1_2 = bj.a(Integer.toString(arg23), v8, -1, v10, arg24, v0.i, v0.j, ((List)v2_4), -1, v17_1, v0.n, v0.o, v6);
            }
            else {
                if("application/x-subrip".equals(v8)) {
                    v1_2 = bj.a(Integer.toString(arg23), v8, -1, arg24, v0.M);
                    goto label_376;
                }

                if(!"application/vobsub".equals(v8)) {
                    if("application/pgs".equals(v8)) {
                    }
                    else {
                        throw new bl("Unexpected MIME type.");
                    }
                }

                v1_2 = bj.a(Integer.toString(arg23), v8, -1, arg24, ((List)v2_4), v0.M);
            }

        label_376:
            v0.K = arg22.d(v0.b);
            v0.K.a(v1_2);
        }

        private static List a(fp arg5) {
            int v0 = 16;
            try {
                arg5.d(v0);
                long v0_1 = arg5.l();
                if(v0_1 == 826496599) {
                    v0 = arg5.d() + 20;
                    byte[] v5 = arg5.a;
                    while(v0 < v5.length - 4) {
                        if(v5[v0] == 0 && v5[v0 + 1] == 0 && v5[v0 + 2] == 1 && v5[v0 + 3] == 15) {
                            return Collections.singletonList(Arrays.copyOfRange(v5, v0, v5.length));
                        }

                        ++v0;
                    }

                    throw new bl("Failed to find FourCC VC1 initialization data");
                }

                StringBuilder v3 = new StringBuilder(57);
                v3.append("Unsupported FourCC compression type: ");
                v3.append(v0_1);
                throw new bl(v3.toString());
            }
            catch(ArrayIndexOutOfBoundsException ) {
                throw new bl("Error parsing FourCC VC1 codec private");
            }
        }

        private static List a(byte[] arg8) {
            int v6;
            try {
                int v2 = 2;
                if(arg8[0] == v2) {
                    int v3 = 1;
                    int v4 = 0;
                    while(true) {
                        v6 = -1;
                        if(arg8[v3] != v6) {
                            break;
                        }

                        v4 += 255;
                        ++v3;
                    }

                    int v5 = v3 + 1;
                    v4 += arg8[v3];
                    v3 = 0;
                    while(arg8[v5] == v6) {
                        v3 += 255;
                        ++v5;
                    }

                    v6 = v5 + 1;
                    v3 += arg8[v5];
                    if(arg8[v6] == 1) {
                        byte[] v1 = new byte[v4];
                        System.arraycopy(arg8, v6, v1, 0, v4);
                        v6 += v4;
                        if(arg8[v6] == 3) {
                            v6 += v3;
                            if(arg8[v6] == 5) {
                                byte[] v3_1 = new byte[arg8.length - v6];
                                System.arraycopy(arg8, v6, v3_1, 0, arg8.length - v6);
                                ArrayList v8 = new ArrayList(v2);
                                ((List)v8).add(v1);
                                ((List)v8).add(v3_1);
                                return ((List)v8);
                            }

                            throw new bl("Error parsing vorbis codec private");
                        }

                        throw new bl("Error parsing vorbis codec private");
                    }

                    throw new bl("Error parsing vorbis codec private");
                }

                throw new bl("Error parsing vorbis codec private");
            }
            catch(ArrayIndexOutOfBoundsException ) {
                throw new bl("Error parsing vorbis codec private");
            }
        }

        private byte[] a() {
            float v1 = -1f;
            if(this.v != v1 && this.w != v1 && this.x != v1 && this.y != v1 && this.z != v1 && this.A != v1 && this.B != v1 && this.C != v1 && this.D != v1) {
                if(this.E == v1) {
                }
                else {
                    byte[] v0 = new byte[25];
                    ByteBuffer v1_1 = ByteBuffer.wrap(v0);
                    v1_1.put(0);
                    v1_1.putShort(((short)(((int)(this.v * 50000f + 0.5f)))));
                    v1_1.putShort(((short)(((int)(this.w * 50000f + 0.5f)))));
                    v1_1.putShort(((short)(((int)(this.x * 50000f + 0.5f)))));
                    v1_1.putShort(((short)(((int)(this.y * 50000f + 0.5f)))));
                    v1_1.putShort(((short)(((int)(this.z * 50000f + 0.5f)))));
                    v1_1.putShort(((short)(((int)(this.A * 50000f + 0.5f)))));
                    v1_1.putShort(((short)(((int)(this.B * 50000f + 0.5f)))));
                    v1_1.putShort(((short)(((int)(this.C * 50000f + 0.5f)))));
                    v1_1.putShort(((short)(((int)(this.D + 0.5f)))));
                    v1_1.putShort(((short)(((int)(this.E + 0.5f)))));
                    v1_1.putShort(((short)this.t));
                    v1_1.putShort(((short)this.u));
                    return v0;
                }
            }

            return null;
        }

        private static Pair b(fp arg6) {
            int v0 = 4;
            try {
                arg6.c(v0);
                v0 = (arg6.f() & 3) + 1;
                if(v0 != 3) {
                    ArrayList v1 = new ArrayList();
                    int v2 = arg6.f() & 31;
                    int v3 = 0;
                    int v4;
                    for(v4 = 0; v4 < v2; ++v4) {
                        ((List)v1).add(fn.a(arg6));
                    }

                    v2 = arg6.f();
                    while(v3 < v2) {
                        ((List)v1).add(fn.a(arg6));
                        ++v3;
                    }

                    return Pair.create(v1, Integer.valueOf(v0));
                }

                throw new bl();
            }
            catch(ArrayIndexOutOfBoundsException ) {
                throw new bl("Error parsing AVC codec private");
            }
        }

        private static Pair c(fp arg13) {
            List v13_1;
            int v8;
            int v7;
            int v0 = 21;
            try {
                arg13.c(v0);
                v0 = arg13.f() & 3;
                int v1 = arg13.f();
                int v2 = arg13.d();
                int v4 = 0;
                int v5;
                for(v5 = 0; v4 < v1; v5 = v7) {
                    arg13.d(1);
                    int v6 = arg13.g();
                    v7 = v5;
                    for(v5 = 0; v5 < v6; ++v5) {
                        v8 = arg13.g();
                        v7 += v8 + 4;
                        arg13.d(v8);
                    }

                    ++v4;
                }

                arg13.c(v2);
                byte[] v2_1 = new byte[v5];
                v4 = 0;
                for(v7 = 0; v4 < v1; v7 = v9) {
                    arg13.d(1);
                    v8 = arg13.g();
                    int v9 = v7;
                    for(v7 = 0; v7 < v8; ++v7) {
                        int v10 = arg13.g();
                        System.arraycopy(fn.a, 0, v2_1, v9, fn.a.length);
                        v9 += fn.a.length;
                        System.arraycopy(arg13.a, arg13.d(), v2_1, v9, v10);
                        v9 += v10;
                        arg13.d(v10);
                    }

                    ++v4;
                }

                if(v5 == 0) {
                    Object v13 = null;
                }
                else {
                    v13_1 = Collections.singletonList(v2_1);
                }

                return Pair.create(v13_1, Integer.valueOf(v0 + 1));
            }
            catch(ArrayIndexOutOfBoundsException ) {
                throw new bl("Error parsing HEVC codec private");
            }
        }

        private static boolean d(fp arg8) {
            boolean v1;
            try {
                int v0 = arg8.h();
                v1 = true;
                if(v0 == 1) {
                    return 1;
                }

                if(v0 != 65534) {
                    return 0;
                }

                arg8.c(24);
                if(arg8.o() != en.a().getMostSignificantBits()) {
                    return false;
                }
                else if(arg8.o() != en.a().getLeastSignificantBits()) {
                    return false;
                }
            }
            catch(ArrayIndexOutOfBoundsException ) {
                throw new bl("Error parsing MS/ACM codec private");
            }

            return v1;
        }
    }

    private long A;
    private boolean B;
    private long C;
    private long D;
    private long E;
    private fk F;
    private fk G;
    private boolean H;
    private int I;
    private long J;
    private long K;
    private int L;
    private int M;
    private int[] N;
    private int O;
    private int P;
    private int Q;
    private int R;
    private boolean S;
    private boolean T;
    private boolean U;
    private boolean V;
    private byte W;
    private int X;
    private int Y;
    private int Z;
    private static final byte[] a;
    private boolean aa;
    private boolean ab;
    private ce ac;
    private static final byte[] b;
    private static final UUID c;
    private final ej d;
    private final em e;
    private final SparseArray f;
    private final boolean g;
    private final fp h;
    private final fp i;
    private final fp j;
    private final fp k;
    private final fp l;
    private final fp m;
    private final fp n;
    private final fp o;
    private final fp p;
    private ByteBuffer q;
    private long r;
    private long s;
    private long t;
    private long u;
    private long v;
    private b w;
    private boolean x;
    private boolean y;
    private int z;

    static {
        en.a = new byte[]{49, 10, 48, 48, 58, 48, 48, 58, 48, 48, 44, 48, 48, 48, 32, 45, 45, 62, 32, 48, 48, 58, 48, 48, 58, 48, 48, 44, 48, 48, 48, 10};
        en.b = new byte[]{32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32};
        en.c = new UUID(72057594037932032L, -9223371306706625679L);
    }

    public en() {
        this(new ei(), 0);
    }

    en(ej arg3, int arg4) {
        super();
        this.r = -1;
        this.s = -1;
        this.t = -1;
        this.u = -1;
        this.v = -1;
        this.C = -1;
        this.D = -1;
        this.E = -1;
        this.d = arg3;
        this.d.a(new a(this, null));
        boolean v3 = true;
        if((arg4 & 1) == 0) {
        }
        else {
            v3 = false;
        }

        this.g = v3;
        this.e = new em();
        this.f = new SparseArray();
        this.j = new fp(4);
        this.k = new fp(ByteBuffer.allocate(4).putInt(-1).array());
        this.l = new fp(4);
        this.h = new fp(fn.a);
        this.i = new fp(4);
        this.m = new fp();
        this.n = new fp();
        this.o = new fp(8);
        this.p = new fp();
    }

    private int a(cd arg2, ck arg3, int arg4) {
        int v2;
        int v0 = this.m.b();
        if(v0 > 0) {
            v2 = Math.min(arg4, v0);
            arg3.a(this.m, v2);
        }
        else {
            v2 = arg3.a(arg2, arg4, false);
        }

        this.R += v2;
        this.Z += v2;
        return v2;
    }

    private long a(long arg12) {
        if(this.t != -1) {
            return ft.a(arg12, this.t, 1000);
        }

        throw new bl("Can\'t scale timecode prior to timecodeScale being set.");
    }

    static UUID a() {
        return en.c;
    }

    private void a(cd arg4, int arg5) {
        if(this.j.c() >= arg5) {
            return;
        }

        if(this.j.e() < arg5) {
            this.j.a(Arrays.copyOf(this.j.a, Math.max(this.j.a.length * 2, arg5)), this.j.c());
        }

        arg4.b(this.j.a, this.j.c(), arg5 - this.j.c());
        this.j.b(arg5);
    }

    private void a(cd arg11, b arg12, int arg13) {
        int v8;
        if("S_TEXT/UTF8".equals(arg12.a)) {
            int v12 = en.a.length + arg13;
            if(this.n.e() < v12) {
                this.n.a = Arrays.copyOf(en.a, v12 + arg13);
            }

            arg11.b(this.n.a, en.a.length, arg13);
            this.n.c(0);
            this.n.b(v12);
            return;
        }

        ck v0 = arg12.K;
        int v3 = 4;
        int v4 = 2;
        if(!this.S) {
            if(arg12.e) {
                this.Q &= -3;
                int v6 = 128;
                if(!this.T) {
                    arg11.b(this.j.a, 0, 1);
                    ++this.R;
                    if((this.j.a[0] & v6) != v6) {
                        this.W = this.j.a[0];
                        this.T = true;
                    }
                    else {
                        throw new bl("Extension bit is set in signal byte");
                    }
                }

                int v2 = (this.W & 1) == 1 ? 1 : 0;
                if(v2 == 0) {
                    goto label_209;
                }

                v2 = (this.W & v4) == v4 ? 1 : 0;
                this.Q |= v4;
                if(!this.U) {
                    v8 = 8;
                    arg11.b(this.o.a, 0, v8);
                    this.R += v8;
                    this.U = true;
                    byte[] v7 = this.j.a;
                    if(v2 != 0) {
                    }
                    else {
                        v6 = 0;
                    }

                    v7[0] = ((byte)(v6 | v8));
                    this.j.c(0);
                    v0.a(this.j, 1);
                    ++this.Z;
                    this.o.c(0);
                    v0.a(this.o, v8);
                    this.Z += v8;
                }

                if(v2 == 0) {
                    goto label_209;
                }

                if(!this.V) {
                    arg11.b(this.j.a, 0, 1);
                    ++this.R;
                    this.j.c(0);
                    this.X = this.j.f();
                    this.V = true;
                }

                v2 = this.X * 4;
                if(this.j.c() < v2) {
                    this.j.a(new byte[v2], v2);
                }

                arg11.b(this.j.a, 0, v2);
                this.R += v2;
                this.j.c(0);
                this.j.b(v2);
                short v2_1 = ((short)(this.X / v4 + 1));
                v6 = v2_1 * 6 + v4;
                if(this.q == null || this.q.capacity() < v6) {
                    this.q = ByteBuffer.allocate(v6);
                }

                this.q.position(0);
                this.q.putShort(v2_1);
                v2 = 0;
                int v7_1;
                for(v7_1 = 0; v2 < this.X; v7_1 = v8) {
                    v8 = this.j.s();
                    if(v2 % 2 == 0) {
                        this.q.putShort(((short)(v8 - v7_1)));
                    }
                    else {
                        this.q.putInt(v8 - v7_1);
                    }

                    ++v2;
                }

                v2 = arg13 - this.R - v7_1;
                if(this.X % v4 == 1) {
                    this.q.putInt(v2);
                }
                else {
                    this.q.putShort(((short)v2));
                    this.q.putInt(0);
                }

                this.p.a(this.q.array(), v6);
                v0.a(this.p, v6);
                this.Z += v6;
            }
            else {
                if(arg12.f == null) {
                    goto label_209;
                }

                this.m.a(arg12.f, arg12.f.length);
            }

        label_209:
            this.S = true;
        }

        arg13 += this.m.c();
        if(!"V_MPEG4/ISO/AVC".equals(arg12.a)) {
            if("V_MPEGH/ISO/HEVC".equals(arg12.a)) {
                goto label_228;
            }

            while(this.R < arg13) {
                this.a(arg11, v0, arg13 - this.R);
            }
        }
        else {
        label_228:
            byte[] v2_2 = this.i.a;
            v2_2[0] = 0;
            v2_2[1] = 0;
            v2_2[v4] = 0;
            v4 = arg12.L;
            int v5 = 4 - arg12.L;
            while(this.R < arg13) {
                if(this.Y == 0) {
                    this.a(arg11, v2_2, v5, v4);
                    this.i.c(0);
                    this.Y = this.i.s();
                    this.h.c(0);
                    v0.a(this.h, v3);
                    this.Z += v3;
                    continue;
                }

                this.Y -= this.a(arg11, v0, this.Y);
            }
        }

        if("A_VORBIS".equals(arg12.a)) {
            this.k.c(0);
            v0.a(this.k, v3);
            this.Z += v3;
        }
    }

    private void a(cd arg4, byte[] arg5, int arg6, int arg7) {
        int v0 = Math.min(arg7, this.m.b());
        arg4.b(arg5, arg6 + v0, arg7 - v0);
        if(v0 > 0) {
            this.m.a(arg5, arg6, v0);
        }

        this.R += arg7;
    }

    private void a(b arg4) {
        en.a(this.n.a, this.K);
        arg4.K.a(this.n, this.n.c());
        this.Z += this.n.c();
    }

    private static void a(byte[] arg6, long arg7) {
        // Method was not decompiled
    }

    private void a(b arg9, long arg10) {
        if("S_TEXT/UTF8".equals(arg9.a)) {
            this.a(arg9);
        }

        arg9.K.a(arg10, this.Q, this.Z, 0, arg9.g);
        this.aa = true;
        this.d();
    }

    private boolean a(ch arg6, long arg7) {
        if(this.B) {
            this.D = arg7;
            arg6.a = this.C;
            this.B = false;
            return 1;
        }

        if(this.y) {
            long v3 = -1;
            if(this.D != v3) {
                arg6.a = this.D;
                this.D = v3;
                return 1;
            }
        }

        return 0;
    }

    private static boolean a(String arg1) {
        boolean v1 = ("V_VP8".equals(arg1)) || ("V_VP9".equals(arg1)) || ("V_MPEG2".equals(arg1)) || ("V_MPEG4/ISO/SP".equals(arg1)) || ("V_MPEG4/ISO/ASP".equals(arg1)) || ("V_MPEG4/ISO/AP".equals(arg1)) || ("V_MPEG4/ISO/AVC".equals(arg1)) || ("V_MPEGH/ISO/HEVC".equals(arg1)) || ("V_MS/VFW/FOURCC".equals(arg1)) || ("A_OPUS".equals(arg1)) || ("A_VORBIS".equals(arg1)) || ("A_AAC".equals(arg1)) || ("A_MPEG/L3".equals(arg1)) || ("A_AC3".equals(arg1)) || ("A_EAC3".equals(arg1)) || ("A_TRUEHD".equals(arg1)) || ("A_DTS".equals(arg1)) || ("A_DTS/EXPRESS".equals(arg1)) || ("A_DTS/LOSSLESS".equals(arg1)) || ("A_FLAC".equals(arg1)) || ("A_MS/ACM".equals(arg1)) || ("A_PCM/INT/LIT".equals(arg1)) || ("S_TEXT/UTF8".equals(arg1)) || ("S_VOBSUB".equals(arg1)) || ("S_HDMV/PGS".equals(arg1)) ? true : false;
        return v1;
    }

    private static int[] a(int[] arg1, int arg2) {
        if(arg1 == null) {
            return new int[arg2];
        }

        if(arg1.length >= arg2) {
            return arg1;
        }

        return new int[Math.max(arg1.length * 2, arg2)];
    }

    int a(int arg1) {
        switch(arg1) {
            case 181: 
            case 17545: 
            case 21969: 
            case 21970: 
            case 21971: 
            case 21972: 
            case 21973: 
            case 21974: 
            case 21975: 
            case 21976: 
            case 21977: 
            case 21978: {
                return 5;
            }
            case 161: 
            case 163: 
            case 16981: 
            case 18402: 
            case 21419: 
            case 25506: 
            case 30322: {
                return 4;
            }
            case 134: 
            case 17026: 
            case 2274716: {
                return 3;
            }
            case 131: 
            case 155: 
            case 159: 
            case 176: 
            case 179: 
            case 186: 
            case 215: 
            case 231: 
            case 241: 
            case 251: 
            case 16980: 
            case 17029: 
            case 17143: 
            case 18401: 
            case 18408: 
            case 20529: 
            case 20530: 
            case 21420: 
            case 21432: 
            case 21680: 
            case 21682: 
            case 21690: 
            case 21945: 
            case 21946: 
            case 21947: 
            case 21948: 
            case 21949: 
            case 22186: 
            case 22203: 
            case 25188: 
            case 2352003: 
            case 2807729: {
                return 2;
            }
            case 160: 
            case 174: 
            case 183: 
            case 187: 
            case 224: 
            case 225: 
            case 18407: 
            case 19899: 
            case 20532: 
            case 20533: 
            case 21936: 
            case 21968: 
            case 25152: 
            case 28032: 
            case 30320: 
            case 290298740: 
            case 357149030: 
            case 374648427: 
            case 408125543: 
            case 440786851: 
            case 475249515: 
            case 524531317: {
                return 1;
            }
        }

        return 0;
    }

    public int a(cd arg6, ch arg7) {
        int v0 = 0;
        this.aa = false;
        boolean v2 = true;
        do {
            if((v2) && !this.aa) {
                v2 = this.d.a(arg6);
                if(!v2) {
                    continue;
                }

                if(!this.a(arg7, arg6.c())) {
                    continue;
                }

                return 1;
            }

            goto label_14;
        }
        while(true);

        return 1;
    label_14:
        if(v2) {
        }
        else {
            v0 = -1;
        }

        return v0;
    }

    void a(int arg2, double arg3) {
        if(arg2 == 181) {
            goto label_49;
        }

        if(arg2 == 17545) {
            goto label_46;
        }

        switch(arg2) {
            case 21969: {
                goto label_42;
            }
            case 21970: {
                goto label_38;
            }
            case 21971: {
                goto label_34;
            }
            case 21972: {
                goto label_30;
            }
            case 21973: {
                goto label_26;
            }
            case 21974: {
                goto label_22;
            }
            case 21975: {
                goto label_18;
            }
            case 21976: {
                goto label_14;
            }
            case 21977: {
                goto label_10;
            }
            case 21978: {
                goto label_6;
            }
        }

        return;
    label_34:
        this.w.x = ((float)arg3);
        return;
    label_18:
        this.w.B = ((float)arg3);
        return;
    label_38:
        this.w.w = ((float)arg3);
        return;
    label_22:
        this.w.A = ((float)arg3);
        return;
    label_6:
        this.w.E = ((float)arg3);
        return;
    label_42:
        this.w.v = ((float)arg3);
        return;
    label_26:
        this.w.z = ((float)arg3);
        return;
    label_10:
        this.w.D = ((float)arg3);
        return;
    label_30:
        this.w.y = ((float)arg3);
        return;
    label_14:
        this.w.C = ((float)arg3);
        return;
    label_46:
        this.u = ((long)arg3);
        return;
    label_49:
        this.w.H = ((int)arg3);
    }

    void a(int arg20, int arg21, cd arg22) {
        long v7_1;
        int v14;
        en v0 = this;
        int v1 = arg20;
        int v2 = arg21;
        cd v3 = arg22;
        int v5 = 163;
        int v6 = 4;
        int v7 = 0;
        if(v1 != 161 && v1 != v5) {
            if(v1 == 16981) {
                v0.w.f = new byte[v2];
                v3.b(v0.w.f, 0, v2);
                return;
            }
            else if(v1 == 18402) {
                v0.w.g = new byte[v2];
                v3.b(v0.w.g, 0, v2);
                return;
            }
            else if(v1 == 21419) {
                Arrays.fill(v0.l.a, 0);
                v3.b(v0.l.a, v6 - v2, v2);
                v0.l.c(0);
                v0.z = ((int)v0.l.k());
                return;
            }
            else if(v1 == 25506) {
                v0.w.h = new byte[v2];
                v3.b(v0.w.h, 0, v2);
                return;
            }
            else if(v1 == 30322) {
                v0.w.n = new byte[v2];
                v3.b(v0.w.n, 0, v2);
                return;
            }
            else {
                StringBuilder v4 = new StringBuilder(26);
                v4.append("Unexpected id: ");
                v4.append(v1);
                throw new bl(v4.toString());
            }
        }

        int v8 = 8;
        if(v0.I == 0) {
            v0.O = ((int)v0.e.a(v3, false, true, v8));
            v0.P = v0.e.b();
            v0.K = -1;
            v0.I = 1;
            v0.j.a();
        }

        Object v4_1 = v0.f.get(v0.O);
        if(v4_1 == null) {
            v3.b(v2 - v0.P);
            v0.I = 0;
            return;
        }

        if(v0.I == 1) {
            int v10 = 3;
            v0.a(v3, v10);
            int v12 = 2;
            int v11 = (v0.j.a[v12] & 6) >> 1;
            int v13 = 255;
            if(v11 == 0) {
                v0.M = 1;
                v0.N = en.a(v0.N, 1);
                v0.N[0] = v2 - v0.P - v10;
            }
            else if(v1 == v5) {
                v0.a(v3, v6);
                v0.M = (v0.j.a[v10] & v13) + 1;
                v0.N = en.a(v0.N, v0.M);
                if(v11 == v12) {
                    Arrays.fill(v0.N, 0, v0.M, (v2 - v0.P - v6) / v0.M);
                }
                else if(v11 == 1) {
                    v6 = 0;
                    v10 = 4;
                    v11 = 0;
                    while(v6 < v0.M - 1) {
                        v0.N[v6] = 0;
                        do {
                            ++v10;
                            v0.a(v3, v10);
                            v14 = v0.j.a[v10 - 1] & v13;
                            v0.N[v6] += v14;
                        }
                        while(v14 == v13);

                        v11 += v0.N[v6];
                        ++v6;
                    }

                    v0.N[v0.M - 1] = v2 - v0.P - v10 - v11;
                }
                else if(v11 == v10) {
                    v6 = 0;
                    v10 = 4;
                    v11 = 0;
                    while(true) {
                        if(v6 < v0.M - 1) {
                            v0.N[v6] = v7;
                            ++v10;
                            v0.a(v3, v10);
                            int v15 = v10 - 1;
                            if(v0.j.a[v15] != 0) {
                                long v16 = 0;
                                v14 = 0;
                                while(true) {
                                    if(v14 < v8) {
                                        int v18 = 1 << 7 - v14;
                                        if((v0.j.a[v15] & v18) != 0) {
                                            v10 += v14;
                                            v0.a(v3, v10);
                                            v5 = v15 + 1;
                                            v16 = ((long)(v0.j.a[v15] & v13 & (v18 ^ -1)));
                                            break;
                                        }
                                        else {
                                            ++v14;
                                            v8 = 8;
                                            v13 = 255;
                                            continue;
                                        }
                                    }

                                    goto label_244;
                                }

                                while(v5 < v10) {
                                    v7_1 = (((long)(v0.j.a[v5] & 255))) | v16 << v8;
                                    ++v5;
                                    v16 = v7_1;
                                    v8 = 8;
                                }

                                if(v6 > 0) {
                                    v16 -= (1 << v14 * 7 + 6) - 1;
                                }

                            label_244:
                                v7_1 = v16;
                                if(v7_1 >= -2147483648 && v7_1 <= 2147483647) {
                                    v5 = ((int)v7_1);
                                    int[] v7_2 = v0.N;
                                    if(v6 == 0) {
                                    }
                                    else {
                                        v5 += v0.N[v6 - 1];
                                    }

                                    v7_2[v6] = v5;
                                    v11 += v0.N[v6];
                                    ++v6;
                                    v7 = 0;
                                    v8 = 8;
                                    v13 = 255;
                                    continue;
                                }

                                break;
                            }
                            else {
                                goto label_272;
                            }
                        }
                        else {
                            goto label_276;
                        }

                        goto label_284;
                    }

                    throw new bl("EBML lacing sample size out of range.");
                label_272:
                    throw new bl("No valid varint length mask found");
                label_276:
                    v0.N[v0.M - 1] = v2 - v0.P - v10 - v11;
                }
                else {
                    goto label_336;
                }
            }
            else {
                goto label_346;
            }

        label_284:
            v0.J = v0.E + v0.a(((long)(v0.j.a[0] << 8 | v0.j.a[1] & 255)));
            v5 = 2;
            v2 = (v0.j.a[v5] & 8) == 8 ? 1 : 0;
            if(((b)v4_1).c != v5) {
                if(v1 == 163 && (v0.j.a[v5] & 128) == 128) {
                    goto label_324;
                }

                v5 = 0;
            }
            else {
            label_324:
                v5 = 1;
            }

            v7 = v2 != 0 ? 134217728 : 0;
            v0.Q = v5 | v7;
            v0.I = 2;
            v0.L = 0;
            goto label_350;
        label_336:
            StringBuilder v3_1 = new StringBuilder(36);
            v3_1.append("Unexpected lacing value: ");
            v3_1.append(v11);
            throw new bl(v3_1.toString());
        label_346:
            throw new bl("Lacing only supported in SimpleBlocks.");
        }

    label_350:
        if(v1 == 163) {
            while(v0.L < v0.M) {
                v0.a(v3, ((b)v4_1), v0.N[v0.L]);
                v0.a(((b)v4_1), v0.J + (((long)(v0.L * ((b)v4_1).d / 1000))));
                ++v0.L;
            }

            v0.I = 0;
        }
        else {
            v0.a(v3, ((b)v4_1), v0.N[0]);
        }
    }

    void a(int arg9, long arg10) {
        int v0 = 6;
        int v1 = 55;
        int v2 = 50;
        int v3 = 2;
        int v4 = 3;
        long v5 = 1;
        switch(arg9) {
            case 131: {
                goto label_245;
            }
            case 155: {
                goto label_242;
            }
            case 159: {
                goto label_238;
            }
            case 176: {
                goto label_234;
            }
            case 179: {
                goto label_230;
            }
            case 186: {
                goto label_226;
            }
            case 215: {
                goto label_222;
            }
            case 231: {
                goto label_219;
            }
            case 241: {
                goto label_213;
            }
            case 251: {
                goto label_211;
            }
            case 16980: {
                goto label_197;
            }
            case 17029: {
                goto label_181;
            }
            case 17143: {
                goto label_168;
            }
            case 18401: {
                goto label_153;
            }
            case 18408: {
                goto label_139;
            }
            case 20529: {
                goto label_125;
            }
            case 20530: {
                goto label_112;
            }
            case 21420: {
                goto label_108;
            }
            case 21432: {
                goto label_89;
            }
            case 21680: {
                goto label_85;
            }
            case 21682: {
                goto label_81;
            }
            case 21690: {
                goto label_77;
            }
            case 21945: {
                goto label_68;
            }
            case 21946: {
                goto label_50;
            }
            case 21947: {
                goto label_33;
            }
            case 21948: {
                goto label_29;
            }
            case 21949: {
                goto label_25;
            }
            case 22186: {
                goto label_22;
            }
            case 22203: {
                goto label_19;
            }
            case 25188: {
                goto label_15;
            }
            case 2352003: {
                goto label_11;
            }
            case 2807729: {
                goto label_9;
            }
        }

        return;
    label_33:
        this.w.p = true;
        arg9 = ((int)arg10);
        if(arg9 == 1) {
            goto label_47;
        }

        if(arg9 == 9) {
            goto label_44;
        }

        switch(arg9) {
            case 4: 
            case 5: 
            case 6: 
            case 7: {
                goto label_41;
            }
        }

        return;
    label_41:
        this.w.q = v3;
        return;
    label_44:
        this.w.q = v0;
        return;
    label_47:
        this.w.q = 1;
        return;
    label_226:
        this.w.j = ((int)arg10);
        return;
    label_230:
        this.F.a(this.a(arg10));
        return;
    label_168:
        if(arg10 == v5) {
            return;
        }

        StringBuilder v0_1 = new StringBuilder(v2);
        v0_1.append("EBMLReadVersion ");
        v0_1.append(arg10);
        v0_1.append(" not supported");
        throw new bl(v0_1.toString());
    label_234:
        this.w.i = ((int)arg10);
        return;
    label_108:
        this.A = arg10 + this.r;
        return;
    label_238:
        this.w.F = ((int)arg10);
        return;
    label_112:
        if(arg10 == v5) {
            return;
        }

        v0_1 = new StringBuilder(v1);
        v0_1.append("ContentEncodingScope ");
        v0_1.append(arg10);
        v0_1.append(" not supported");
        throw new bl(v0_1.toString());
    label_242:
        this.K = this.a(arg10);
        return;
    label_50:
        arg9 = ((int)arg10);
        if(arg9 != 1) {
            if(arg9 == 16) {
                goto label_62;
            }
            else if(arg9 != 18) {
                switch(arg9) {
                    case 6: 
                    case 7: {
                        goto label_65;
                    }
                    default: {
                        return;
                    label_58:
                        this.w.r = 7;
                        return;
                    label_62:
                        this.w.r = v0;
                        return;
                    }
                }
            }
            else {
                goto label_58;
            }
        }

    label_65:
        this.w.r = v4;
        return;
    label_245:
        this.w.c = ((int)arg10);
        return;
    label_181:
        if(arg10 >= v5 && arg10 <= 2) {
            return;
        }

        StringBuilder v1_1 = new StringBuilder(53);
        v1_1.append("DocTypeReadVersion ");
        v1_1.append(arg10);
        v1_1.append(" not supported");
        throw new bl(v1_1.toString());
    label_125:
        if(arg10 == 0) {
            return;
        }

        v0_1 = new StringBuilder(v1);
        v0_1.append("ContentEncodingOrder ");
        v0_1.append(arg10);
        v0_1.append(" not supported");
        throw new bl(v0_1.toString());
    label_68:
        switch(((int)arg10)) {
            case 1: {
                goto label_74;
            }
            case 2: {
                goto label_71;
            }
        }

        return;
    label_71:
        this.w.s = 1;
        return;
    label_74:
        this.w.s = v3;
        return;
    label_197:
        if(arg10 == 3) {
            return;
        }

        v0_1 = new StringBuilder(v2);
        v0_1.append("ContentCompAlgo ");
        v0_1.append(arg10);
        v0_1.append(" not supported");
        throw new bl(v0_1.toString());
    label_9:
        this.t = arg10;
        return;
    label_11:
        this.w.d = ((int)arg10);
        return;
    label_139:
        if(arg10 == v5) {
            return;
        }

        v1_1 = new StringBuilder(56);
        v1_1.append("AESSettingsCipherMode ");
        v1_1.append(arg10);
        v1_1.append(" not supported");
        throw new bl(v1_1.toString());
    label_77:
        this.w.l = ((int)arg10);
        return;
    label_15:
        this.w.G = ((int)arg10);
        return;
    label_81:
        this.w.m = ((int)arg10);
        return;
    label_211:
        this.ab = true;
        return;
    label_19:
        this.w.J = arg10;
        return;
    label_85:
        this.w.k = ((int)arg10);
        return;
    label_213:
        if(!this.H) {
            this.G.a(arg10);
            this.H = true;
        }

        return;
    label_22:
        this.w.I = arg10;
        return;
    label_153:
        if(arg10 == 5) {
            return;
        }

        v1_1 = new StringBuilder(49);
        v1_1.append("ContentEncAlgo ");
        v1_1.append(arg10);
        v1_1.append(" not supported");
        throw new bl(v1_1.toString());
    label_89:
        arg9 = ((int)arg10);
        if(arg9 == v4) {
            this.w.o = 1;
        }
        else if(arg9 != 15) {
            switch(arg9) {
                case 0: {
                    goto label_98;
                }
                case 1: {
                    goto label_95;
                }
            }

            return;
        label_98:
            this.w.o = 0;
            return;
        label_95:
            this.w.o = v3;
        }
        else {
            this.w.o = v4;
        }

        return;
    label_25:
        this.w.u = ((int)arg10);
        return;
    label_219:
        this.E = this.a(arg10);
        return;
    label_29:
        this.w.t = ((int)arg10);
        return;
    label_222:
        this.w.b = ((int)arg10);
    }

    void a(int arg6, long arg7, long arg9) {
        if(arg6 != 160) {
            if(arg6 != 174) {
                if(arg6 != 187) {
                    long v1 = -1;
                    if(arg6 != 19899) {
                        if(arg6 != 20533) {
                            if(arg6 != 21968) {
                                if(arg6 != 25152) {
                                    if(arg6 == 408125543) {
                                        if(this.r != v1) {
                                            if(this.r == arg7) {
                                            }
                                            else {
                                                throw new bl("Multiple Segment elements not supported");
                                            }
                                        }

                                        this.r = arg7;
                                        this.s = arg9;
                                    }
                                    else if(arg6 == 475249515) {
                                        this.F = new fk();
                                        this.G = new fk();
                                        return;
                                    }
                                    else if(arg6 != 524531317) {
                                        return;
                                    }
                                    else {
                                        if(!this.y) {
                                            if((this.g) && this.C != v1) {
                                                this.B = true;
                                                return;
                                            }

                                            this.ac.a(cj.f);
                                            this.y = true;
                                        }

                                        return;
                                    }
                                }

                                return;
                            }

                            this.w.p = true;
                            return;
                        }

                        this.w.e = true;
                        return;
                    }

                    this.z = -1;
                    this.A = v1;
                    return;
                }

                this.H = false;
                return;
            }

            this.w = new b(null);
            return;
        }

        this.ab = false;
    }

    void a(int arg3, String arg4) {
        if(arg3 != 134) {
            if(arg3 != 17026) {
                if(arg3 != 2274716) {
                    return;
                }

                b.a(this.w, arg4);
                return;
            }

            if(!"webm".equals(arg4)) {
                if("matroska".equals(arg4)) {
                }
                else {
                    StringBuilder v1 = new StringBuilder(String.valueOf(arg4).length() + 22);
                    v1.append("DocType ");
                    v1.append(arg4);
                    v1.append(" not supported");
                    throw new bl(v1.toString());
                }
            }

            return;
        }

        this.w.a = arg4;
    }

    public void a(ce arg1) {
        this.ac = arg1;
    }

    public boolean a(cd arg2) {
        return new el().a(arg2);
    }

    public void b() {
        this.E = -1;
        this.I = 0;
        this.d.a();
        this.e.a();
        this.d();
    }

    boolean b(int arg2) {
        boolean v2 = arg2 == 357149030 || arg2 == 524531317 || arg2 == 475249515 || arg2 == 374648427 ? true : false;
        return v2;
    }

    public void c() {
    }

    void c(int arg6) {
        if(arg6 != 160) {
            if(arg6 != 174) {
                int v2 = 475249515;
                long v3 = -1;
                if(arg6 != 19899) {
                    if(arg6 != 25152) {
                        if(arg6 != 28032) {
                            if(arg6 != 357149030) {
                                if(arg6 != 374648427) {
                                    if(arg6 != v2) {
                                        return;
                                    }

                                    if(!this.y) {
                                        this.ac.a(this.e());
                                        this.y = true;
                                    }

                                    return;
                                }

                                if(this.f.size() != 0) {
                                    this.ac.f();
                                    return;
                                }

                                throw new bl("No valid tracks were found");
                            }

                            if(this.t == v3) {
                                this.t = 1000000;
                            }

                            if(this.u != v3) {
                                this.v = this.a(this.u);
                            }

                            return;
                        }

                        if(this.w.e) {
                            if(this.w.f == null) {
                            }
                            else {
                                throw new bl("Combining encryption and compression is not supported");
                            }
                        }

                        return;
                    }

                    if(this.w.e) {
                        if(this.w.g == null) {
                            throw new bl("Encrypted Track found but ContentEncKeyID was not found");
                        }
                        else if(!this.x) {
                            this.ac.a(new c(new com.google.ads.interactivemedia.v3.internal.bu$b("video/webm", this.w.g)));
                            this.x = true;
                        }
                    }

                    return;
                }

                if(this.z != -1 && this.A != v3) {
                    if(this.z == v2) {
                        this.C = this.A;
                    }

                    return;
                }

                throw new bl("Mandatory element SeekID or SeekPosition not found");
            }

            if(en.a(this.w.a)) {
                this.w.a(this.ac, this.w.b, this.v);
                this.f.put(this.w.b, this.w);
            }

            this.w = null;
            return;
        }

        if(this.I != 2) {
            return;
        }

        if(!this.ab) {
            this.Q |= 1;
        }

        this.a(this.f.get(this.O), this.J);
        this.I = 0;
    }

    private void d() {
        this.R = 0;
        this.Z = 0;
        this.Y = 0;
        this.S = false;
        this.T = false;
        this.V = false;
        this.X = 0;
        this.W = 0;
        this.U = false;
        this.m.a();
    }

    private cj e() {
        // Method was not decompiled
    }
}

