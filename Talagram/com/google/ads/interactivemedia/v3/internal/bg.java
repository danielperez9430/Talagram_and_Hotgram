package com.google.ads.interactivemedia.v3.internal;

import android.annotation.TargetApi;
import android.media.MediaCodec$BufferInfo;
import android.media.MediaCodec$CodecException;
import android.media.MediaCodec$CryptoException;
import android.media.MediaCodec$CryptoInfo;
import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.os.Handler;
import android.os.SystemClock;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

@TargetApi(value=16) public abstract class bg extends bo {
    public class a extends Exception {
        public final String a;
        public final boolean b;
        public final String c;
        public final String d;

        public a(bj arg4, Throwable arg5, boolean arg6, int arg7) {
            String v0 = String.valueOf(arg4);
            StringBuilder v2 = new StringBuilder(String.valueOf(v0).length() + 36);
            v2.append("Decoder init failed: [");
            v2.append(arg7);
            v2.append("], ");
            v2.append(v0);
            super(v2.toString(), arg5);
            this.a = arg4.b;
            this.b = arg6;
            this.c = null;
            this.d = a.a(arg7);
        }

        public a(bj arg4, Throwable arg5, boolean arg6, String arg7) {
            String v0 = String.valueOf(arg4);
            StringBuilder v2 = new StringBuilder(String.valueOf(arg7).length() + 23 + String.valueOf(v0).length());
            v2.append("Decoder init failed: ");
            v2.append(arg7);
            v2.append(", ");
            v2.append(v0);
            super(v2.toString(), arg5);
            this.a = arg4.b;
            this.b = arg6;
            this.c = arg7;
            String v4 = ft.a >= 21 ? a.a(arg5) : null;
            this.d = v4;
        }

        private static String a(int arg3) {
            String v0 = arg3 < 0 ? "neg_" : "";
            arg3 = Math.abs(arg3);
            StringBuilder v2 = new StringBuilder(String.valueOf(v0).length() + 64);
            v2.append("com.google.ads.interactivemedia.v3.exoplayer.MediaCodecTrackRenderer_");
            v2.append(v0);
            v2.append(arg3);
            return v2.toString();
        }

        @TargetApi(value=21) private static String a(Throwable arg1) {
            if((arg1 instanceof MediaCodec$CodecException)) {
                return ((MediaCodec$CodecException)arg1).getDiagnosticInfo();
            }

            return null;
        }
    }

    public interface b {
        void a(MediaCodec$CryptoException arg1);

        void a(String arg1, long arg2, long arg3);

        void a(a arg1);
    }

    private ByteBuffer[] A;
    private long B;
    private int C;
    private int D;
    private boolean E;
    private boolean F;
    private int G;
    private int H;
    private boolean I;
    private boolean J;
    private int K;
    private boolean L;
    private boolean M;
    private boolean N;
    private boolean O;
    public final av a;
    protected final Handler b;
    private static final byte[] c;
    private final bf d;
    private final bv e;
    private final boolean f;
    private final bm g;
    private final bk h;
    private final List i;
    private final MediaCodec$BufferInfo j;
    private final b k;
    private final boolean l;
    private bj m;
    private bu n;
    private MediaCodec o;
    private boolean p;
    private boolean q;
    private boolean r;
    private boolean s;
    private boolean t;
    private boolean u;
    private boolean v;
    private boolean w;
    private boolean x;
    private boolean y;
    private ByteBuffer[] z;

    static {
        bg.c = ft.d("0000016742C00BDA259000000168CE0F13200000016588840DCE7118A0002FBF1C31C3275D78");
    }

    public bg(bn arg9, bf arg10, bv arg11, boolean arg12, Handler arg13, b arg14) {
        this(new bn[]{arg9}, arg10, arg11, arg12, arg13, arg14);
    }

    public bg(bn[] arg3, bf arg4, bv arg5, boolean arg6, Handler arg7, b arg8) {
        super(arg3);
        boolean v3 = ft.a >= 16 ? true : false;
        fe.b(v3);
        this.d = fe.a(arg4);
        this.e = arg5;
        this.f = arg6;
        this.b = arg7;
        this.k = arg8;
        this.l = bg.B();
        this.a = new av();
        this.g = new bm(0);
        this.h = new bk();
        this.i = new ArrayList();
        this.j = new MediaCodec$BufferInfo();
        this.G = 0;
        this.H = 0;
    }

    private void A() {
        if(this.H == 2) {
            this.m();
            this.j();
        }
        else {
            this.M = true;
            this.h();
        }
    }

    private static boolean B() {
        boolean v0 = ft.a > 22 || !"foster".equals(ft.b) || !"NVIDIA".equals(ft.c) ? false : true;
        return v0;
    }

    private static MediaCodec$CryptoInfo a(bm arg3, int arg4) {
        MediaCodec$CryptoInfo v3 = arg3.a.a();
        if(arg4 == 0) {
            return v3;
        }

        if(v3.numBytesOfClearData == null) {
            v3.numBytesOfClearData = new int[1];
        }

        v3.numBytesOfClearData[0] += arg4;
        return v3;
    }

    static b a(bg arg0) {
        return arg0.k;
    }

    private void a(MediaCodec$CryptoException arg3) {
        if(this.b != null && this.k != null) {
            this.b.post(new Runnable(arg3) {
                public void run() {
                    bg.a(this.b).a(this.a);
                }
            });
        }
    }

    private void a(a arg2) {
        this.b(arg2);
        throw new az(((Throwable)arg2));
    }

    private void a(String arg10, long arg11, long arg13) {
        if(this.b != null && this.k != null) {
            this.b.post(new Runnable(arg10, arg11, arg13) {
                public void run() {
                    bg.a(this.d).a(this.a, this.b, this.c);
                }
            });
        }
    }

    private boolean a() {
        boolean v0 = SystemClock.elapsedRealtime() < this.B + 1000 ? true : false;
        return v0;
    }

    private boolean a(long arg16, long arg18) {
        boolean v0;
        boolean v9;
        int v8;
        MediaCodec$BufferInfo v7;
        ByteBuffer v6;
        MediaCodec v5;
        bg v10 = this;
        if(v10.M) {
            return 0;
        }

        if(v10.D < 0) {
            if((v10.v) && (v10.J)) {
                try {
                    v10.D = v10.o.dequeueOutputBuffer(v10.j, this.p());
                    goto label_27;
                }
                catch(IllegalStateException ) {
                    this.A();
                    if(v10.M) {
                        this.m();
                    }

                    return 0;
                }
            }

            v10.D = v10.o.dequeueOutputBuffer(v10.j, this.p());
        }

    label_27:
        if(v10.D == -2) {
            this.i();
            return 1;
        }

        if(v10.D == -3) {
            v10.A = v10.o.getOutputBuffers();
            ++v10.a.e;
            return 1;
        }

        if(v10.D < 0) {
            if((v10.t) && ((v10.L) || v10.H == 2)) {
                this.A();
                return 1;
            }

            return 0;
        }

        int v13 = -1;
        if(v10.y) {
            v10.y = false;
            v10.o.releaseOutputBuffer(v10.D, false);
            v10.D = v13;
            return 1;
        }

        if((v10.j.flags & 4) != 0) {
            this.A();
            return 0;
        }

        int v14 = this.h(v10.j.presentationTimeUs);
        if(!v10.v || !v10.J) {
            v5 = v10.o;
            v6 = v10.A[v10.D];
            v7 = v10.j;
            v8 = v10.D;
            v9 = v14 != v13 ? true : false;
            v0 = this.a(arg16, arg18, v5, v6, v7, v8, v9);
        }
        else {
            try {
                v5 = v10.o;
                v6 = v10.A[v10.D];
                v7 = v10.j;
                v8 = v10.D;
                v9 = v14 != v13 ? true : false;
                v0 = this.a(arg16, arg18, v5, v6, v7, v8, v9);
            }
            catch(IllegalStateException ) {
                this.A();
                if(v10.M) {
                    this.m();
                }

                return 0;
            }
        }

        if(v0) {
            this.b(v10.j.presentationTimeUs);
            if(v14 != v13) {
                v10.i.remove(v14);
            }

            v10.D = v13;
            return 1;
        }

        return 0;
    }

    protected abstract boolean a(long arg1, long arg2, MediaCodec arg3, ByteBuffer arg4, MediaCodec$BufferInfo arg5, int arg6, boolean arg7);

    private boolean a(long arg23, boolean arg25) {
        int v0;
        bg v7 = this;
        if(!v7.L) {
            int v1 = 2;
            if(v7.H == v1) {
            }
            else {
                if(v7.C < 0) {
                    v7.C = v7.o.dequeueInputBuffer(0);
                    if(v7.C < 0) {
                        return 0;
                    }
                    else {
                        v7.g.b = v7.z[v7.C];
                        v7.g.d();
                    }
                }

                int v9 = -1;
                if(v7.H == 1) {
                    if(v7.t) {
                    }
                    else {
                        v7.J = true;
                        v7.o.queueInputBuffer(v7.C, 0, 0, 0, 4);
                        v7.C = v9;
                    }

                    v7.H = v1;
                    return 0;
                }

                if(v7.x) {
                    v7.x = false;
                    v7.g.b.put(bg.c);
                    v7.o.queueInputBuffer(v7.C, 0, bg.c.length, 0, 0);
                    v7.C = v9;
                    v7.I = true;
                    return 1;
                }

                int v2 = -2;
                if(v7.N) {
                    v0 = -3;
                }
                else {
                    if(v7.G == 1) {
                        for(v0 = 0; v0 < v7.m.f.size(); ++v0) {
                            v7.g.b.put(v7.m.f.get(v0));
                        }

                        v7.G = v1;
                    }

                    v0 = v7.a(arg23, v7.h, v7.g);
                    if(!arg25) {
                        goto label_90;
                    }

                    if(v7.K != 1) {
                        goto label_90;
                    }

                    if(v0 != v2) {
                        goto label_90;
                    }

                    v7.K = v1;
                }

            label_90:
                if(v0 == v2) {
                    return 0;
                }

                if(v0 == -4) {
                    if(v7.G == v1) {
                        v7.g.d();
                        v7.G = 1;
                    }

                    v7.a(v7.h);
                    return 1;
                }

                if(v0 == v9) {
                    if(v7.G == v1) {
                        v7.g.d();
                        v7.G = 1;
                    }

                    v7.L = true;
                    if(!v7.I) {
                        this.A();
                        return 0;
                    }

                    try {
                        if(v7.t) {
                        }
                        else {
                            v7.J = true;
                            v7.o.queueInputBuffer(v7.C, 0, 0, 0, 4);
                            v7.C = v9;
                        }
                    }
                    catch(MediaCodec$CryptoException v0_1) {
                        v7.a(v0_1);
                        throw new az(((Throwable)v0_1));
                    }

                    return 0;
                }

                if(v7.O) {
                    if(!v7.g.c()) {
                        v7.g.d();
                        if(v7.G == v1) {
                            v7.G = 1;
                        }

                        return 1;
                    }
                    else {
                        v7.O = false;
                    }
                }

                boolean v0_2 = v7.g.a();
                v7.N = v7.a(v0_2);
                if(v7.N) {
                    return 0;
                }

                if((v7.q) && !v0_2) {
                    fn.a(v7.g.b);
                    if(v7.g.b.position() == 0) {
                        return 1;
                    }
                    else {
                        v7.q = false;
                    }
                }

                try {
                    int v14 = v7.g.b.position();
                    int v11 = v14 - v7.g.c;
                    long v12 = v7.g.e;
                    if(v7.g.b()) {
                        v7.i.add(Long.valueOf(v12));
                    }

                    this.a(v12, v7.g.b, v14, v0_2);
                    if(v0_2) {
                        v7.o.queueSecureInputBuffer(v7.C, 0, bg.a(v7.g, v11), v12, 0);
                    }
                    else {
                        v7.o.queueInputBuffer(v7.C, 0, v14, v12, 0);
                    }

                    v7.C = v9;
                    v7.I = true;
                    v7.G = 0;
                    ++v7.a.c;
                    return 1;
                }
                catch(MediaCodec$CryptoException v0_1) {
                    v7.a(v0_1);
                    throw new az(((Throwable)v0_1));
                }
            }
        }

        return 0;
    }

    protected void a(bk arg6) {
        bj v0 = this.m;
        this.m = arg6.a;
        this.n = arg6.b;
        boolean v1 = false;
        int v6 = this.n == null || (this.E) ? 0 : 1;
        if((ft.a(this.m, v0)) && v6 == 0) {
            return;
        }

        if(this.o == null || v6 != 0 || !this.a(this.o, this.p, v0, this.m)) {
            if(this.I) {
                this.H = 1;
                return;
            }

            this.m();
            this.j();
        }
        else {
            this.F = true;
            this.G = 1;
            if((this.s) && this.m.h == v0.h && this.m.i == v0.i) {
                v1 = true;
            }

            this.x = v1;
        }
    }

    private boolean a(boolean arg4) {
        if(!this.E) {
            return 0;
        }

        int v0 = this.e.b();
        if(v0 != 0) {
            if(v0 != 4 && ((arg4) || !this.f)) {
                return 1;
            }

            return 0;
        }

        throw new az(this.e.d());
    }

    protected void a(long arg1, ByteBuffer arg3, int arg4, boolean arg5) {
    }

    private static boolean a(String arg2) {
        boolean v2;
        int v1 = 18;
        if(ft.a >= v1) {
            if(ft.a == v1) {
                if("OMX.SEC.avc.dec".equals(arg2)) {
                }
                else if(!"OMX.SEC.avc.dec.secure".equals(arg2)) {
                    goto label_11;
                }

                goto label_27;
            }

        label_11:
            if(ft.a == 19 && (ft.d.startsWith("SM-G800"))) {
                if("OMX.Exynos.avc.dec".equals(arg2)) {
                    goto label_27;
                }
                else if("OMX.Exynos.avc.dec.secure".equals(arg2)) {
                    goto label_27;
                }
            }

            v2 = false;
        }
        else {
        label_27:
            v2 = true;
        }

        return v2;
    }

    private static boolean a(String arg2, bj arg3) {
        boolean v2 = ft.a >= 21 || !arg3.f.isEmpty() || !"OMX.MTK.VIDEO.DECODER.AVC".equals(arg2) ? false : true;
        return v2;
    }

    protected void a(MediaCodec arg1, MediaFormat arg2) {
    }

    protected ay a(bf arg1, String arg2, boolean arg3) {
        return arg1.a(arg2, arg3);
    }

    protected void a(long arg1) {
        this.K = 0;
        this.L = false;
        this.M = false;
        if(this.o != null) {
            this.n();
        }
    }

    protected void a(long arg3, long arg5, boolean arg7) {
        int v7;
        if(!arg7) {
            v7 = 0;
        }
        else if(this.K == 0) {
            v7 = 1;
        }
        else {
            v7 = this.K;
        }

        this.K = v7;
        if(this.m == null) {
            this.g(arg3);
        }

        this.j();
        if(this.o != null) {
            fs.a("drainAndFeed");
            while(this.a(arg3, arg5)) {
            }

            if(this.a(arg3, true)) {
                while(this.a(arg3, false)) {
                }
            }

            fs.a();
        }

        this.a.a();
    }

    protected abstract void a(MediaCodec arg1, boolean arg2, MediaFormat arg3, MediaCrypto arg4);

    protected boolean a(MediaCodec arg1, boolean arg2, bj arg3, bj arg4) {
        return 0;
    }

    protected abstract boolean a(bf arg1, bj arg2);

    protected final boolean a(bj arg2) {
        return this.a(this.d, arg2);
    }

    private void b(a arg3) {
        if(this.b != null && this.k != null) {
            this.b.post(new Runnable(arg3) {
                public void run() {
                    bg.a(this.b).a(this.a);
                }
            });
        }
    }

    protected void b(long arg1) {
    }

    private MediaFormat b(bj arg3) {
        MediaFormat v3 = arg3.b();
        if(this.l) {
            v3.setInteger("auto-frc", 0);
        }

        return v3;
    }

    private static boolean b(String arg2) {
        boolean v2;
        if(ft.a < 24) {
            if(!"OMX.Nvidia.h264.decode".equals(arg2) && !"OMX.Nvidia.h264.decode.secure".equals(arg2)) {
                goto label_27;
            }

            if(!ft.b.equals("flounder") && !ft.b.equals("flounder_lte") && !ft.b.equals("grouper") && !ft.b.equals("tilapia")) {
                goto label_27;
            }

            v2 = true;
        }
        else {
        label_27:
            v2 = false;
        }

        return v2;
    }

    private static boolean b(String arg3, bj arg4) {
        boolean v1 = true;
        if(ft.a > 18 || arg4.q != 1 || !"OMX.MTK.AUDIO.DECODER.MP3".equals(arg3)) {
            v1 = false;
        }
        else {
        }

        return v1;
    }

    private static boolean c(String arg2) {
        boolean v2;
        if(ft.a <= 17) {
            if(!"OMX.rk.video_decoder.avc".equals(arg2) && !"OMX.allwinner.video.decoder.avc".equals(arg2)) {
                goto label_11;
            }

            v2 = true;
        }
        else {
        label_11:
            v2 = false;
        }

        return v2;
    }

    protected void c() {
    }

    private static boolean d(String arg2) {
        boolean v2;
        if(ft.a > 23 || !"OMX.google.vorbis.decoder".equals(arg2)) {
            if(ft.a <= 19 && ("hb2000".equals(ft.b)) && (("OMX.amlogic.avc.decoder.awesome".equals(arg2)) || ("OMX.amlogic.avc.decoder.awesome.secure".equals(arg2)))) {
            label_19:
                v2 = true;
                return v2;
            }

            v2 = false;
        }
        else {
            goto label_19;
        }

        return v2;
    }

    protected void d() {
    }

    private static boolean e(String arg2) {
        boolean v2 = ft.a != 21 || !"OMX.google.aac.decoder".equals(arg2) ? false : true;
        return v2;
    }

    protected boolean e() {
        return this.M;
    }

    private static boolean f(String arg2) {
        boolean v2;
        if(ft.a > 19 || !ft.d.equals("ODROID-XU3")) {
        label_15:
            v2 = false;
        }
        else {
            if(!"OMX.Exynos.AVC.Decoder".equals(arg2) && !"OMX.Exynos.AVC.Decoder.secure".equals(arg2)) {
                goto label_15;
            }

            v2 = true;
        }

        return v2;
    }

    protected boolean f() {
        boolean v0;
        if(this.m == null || (this.N)) {
        label_12:
            v0 = false;
        }
        else {
            if(this.K == 0 && this.D < 0 && !this.a()) {
                goto label_12;
            }

            v0 = true;
        }

        return v0;
    }

    private void g(long arg3) {
        if(this.a(arg3, this.h, null) == -4) {
            this.a(this.h);
        }
    }

    protected void g() {
        this.m = null;
        this.n = null;
        try {
            this.m();
        }
        catch(Throwable v1) {
            try {
                if(this.E) {
                    this.e.a();
                    this.E = false;
                }
            }
            catch(Throwable v0) {
                super.g();
                throw v0;
            }

            super.g();
            throw v1;
        }

        try {
            if(this.E) {
                this.e.a();
                this.E = false;
            }
        }
        catch(Throwable v0) {
            super.g();
            throw v0;
        }

        super.g();
    }

    protected void h() {
    }

    private int h(long arg6) {
        int v0 = this.i.size();
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            if(this.i.get(v1).longValue() == arg6) {
                return v1;
            }
        }

        return -1;
    }

    private void i() {
        MediaFormat v0 = this.o.getOutputFormat();
        if(this.s) {
            int v3 = 32;
            if(v0.getInteger("width") == v3 && v0.getInteger("height") == v3) {
                this.y = true;
                return;
            }
        }

        if(this.w) {
            v0.setInteger("channel-count", 1);
        }

        this.a(this.o, v0);
        ++this.a.d;
    }

    protected final void j() {
        ay v0_2;
        boolean v9;
        MediaCrypto v1_1;
        if(!this.k()) {
            return;
        }

        String v0 = this.m.b;
        int v7 = 3;
        boolean v2 = false;
        Throwable v3 = null;
        if(this.n == null) {
            v1_1 = ((MediaCrypto)v3);
            v9 = false;
        }
        else if(this.e != null) {
            if(!this.E) {
                this.e.a(this.n);
                this.E = true;
            }

            int v1 = this.e.b();
            if(v1 != 0) {
                if(v1 != v7) {
                    if(v1 == 4) {
                    }
                    else {
                        return;
                    }
                }

                v1_1 = this.e.c().a();
                v9 = this.e.a(v0);
                goto label_45;
            }

            throw new az(this.e.d());
        }
        else {
            throw new az("Media requires a DrmSessionManager");
        }

        try {
        label_45:
            v0_2 = this.a(this.d, v0, v9);
        }
        catch(com.google.ads.interactivemedia.v3.internal.bh$b v0_1) {
            this.a(new a(this.m, ((Throwable)v0_1), v9, -49998));
            v0_2 = ((ay)v3);
        }

        if(v0_2 == null) {
            this.a(new a(this.m, v3, v9, -49999));
        }

        String v10 = v0_2.a;
        if((v0_2.c) && !bg.f(v10)) {
            v2 = true;
        }

        this.p = v2;
        this.q = bg.a(v10, this.m);
        this.r = bg.a(v10);
        this.s = bg.b(v10);
        this.t = bg.c(v10);
        this.u = bg.d(v10);
        this.v = bg.e(v10);
        this.w = bg.b(v10, this.m);
        try {
            long v2_1 = SystemClock.elapsedRealtime();
            StringBuilder v5 = new StringBuilder(String.valueOf(v10).length() + 19);
            v5.append("createByCodecName(");
            v5.append(v10);
            v5.append(")");
            fs.a(v5.toString());
            this.o = MediaCodec.createByCodecName(v10);
            fs.a();
            fs.a("configureCodec");
            this.a(this.o, v0_2.c, this.b(this.m), v1_1);
            fs.a();
            fs.a("codec.start()");
            this.o.start();
            fs.a();
            long v4 = SystemClock.elapsedRealtime();
            this.a(v10, v4, v4 - v2_1);
            this.z = this.o.getInputBuffers();
            this.A = this.o.getOutputBuffers();
        }
        catch(Exception v0_3) {
            this.a(new a(this.m, ((Throwable)v0_3), v9, v10));
        }

        long v0_4 = this.v() == v7 ? SystemClock.elapsedRealtime() : -1;
        this.B = v0_4;
        this.C = -1;
        this.D = -1;
        this.O = true;
        ++this.a.a;
    }

    protected boolean k() {
        boolean v0 = this.o != null || this.m == null ? false : true;
        return v0;
    }

    protected final boolean l() {
        boolean v0 = this.o != null ? true : false;
        return v0;
    }

    protected void m() {
        if(this.o != null) {
            this.B = -1;
            this.C = -1;
            this.D = -1;
            this.N = false;
            this.i.clear();
            ByteBuffer[] v1 = null;
            this.z = v1;
            this.A = v1;
            this.F = false;
            this.I = false;
            this.p = false;
            this.q = false;
            this.r = false;
            this.s = false;
            this.t = false;
            this.u = false;
            this.w = false;
            this.x = false;
            this.y = false;
            this.J = false;
            this.G = 0;
            this.H = 0;
            ++this.a.b;
            try {
                this.o.stop();
            }
            catch(Throwable v0) {
                try {
                    this.o.release();
                }
                catch(Throwable v0) {
                    this.o = ((MediaCodec)v1);
                    throw v0;
                }

                this.o = ((MediaCodec)v1);
                throw v0;
            }

            try {
                this.o.release();
            }
            catch(Throwable v0) {
                this.o = ((MediaCodec)v1);
                throw v0;
            }

            this.o = ((MediaCodec)v1);
        }
    }

    protected void n() {
        this.B = -1;
        this.C = -1;
        this.D = -1;
        this.O = true;
        this.N = false;
        this.i.clear();
        this.x = false;
        this.y = false;
        if(!this.r) {
            if((this.u) && (this.J)) {
                goto label_27;
            }

            if(this.H != 0) {
                goto label_27;
            }

            this.o.flush();
            this.I = false;
        }
        else {
        label_27:
            this.m();
            this.j();
        }

        if((this.F) && this.m != null) {
            this.G = 1;
        }
    }

    protected final int o() {
        return this.K;
    }

    protected long p() {
        return 0;
    }
}

