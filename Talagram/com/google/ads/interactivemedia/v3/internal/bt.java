package com.google.ads.interactivemedia.v3.internal;

import android.annotation.TargetApi;
import android.media.AudioTimestamp;
import android.media.AudioTrack;
import android.media.PlaybackParams;
import android.os.ConditionVariable;
import android.os.SystemClock;
import android.util.Log;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;

public final class bt {
    class a {
        protected AudioTrack a;
        private boolean b;
        private int c;
        private long d;
        private long e;
        private long f;
        private long g;
        private long h;
        private long i;
        private long j;

        a(com.google.ads.interactivemedia.v3.internal.bt$1 arg1) {
            this();
        }

        private a() {
            super();
        }

        public void a(AudioTrack arg3, boolean arg4) {
            this.a = arg3;
            this.b = arg4;
            this.g = -1;
            this.h = -1;
            this.d = 0;
            this.e = 0;
            this.f = 0;
            if(arg3 != null) {
                this.c = arg3.getSampleRate();
            }
        }

        public void a(PlaybackParams arg1) {
            throw new UnsupportedOperationException();
        }

        public void a(long arg5) {
            this.i = this.b();
            this.g = SystemClock.elapsedRealtime() * 1000;
            this.j = arg5;
            this.a.stop();
        }

        public void a() {
            if(this.g != -1) {
                return;
            }

            this.a.pause();
        }

        public long b() {
            long v2 = -1;
            if(this.g != v2) {
                return Math.min(this.j, this.i + (SystemClock.elapsedRealtime() * 1000 - this.g) * (((long)this.c)) / 1000000);
            }

            int v0 = this.a.getPlayState();
            long v4 = 0;
            if(v0 == 1) {
                return v4;
            }

            long v6 = 4294967295L & (((long)this.a.getPlaybackHeadPosition()));
            if(this.b) {
                if(v0 == 2 && v6 == v4) {
                    this.f = this.d;
                }

                v6 += this.f;
            }

            if(ft.a <= 26) {
                if(v6 == v4 && this.d > v4 && v0 == 3) {
                    if(this.h == v2) {
                        this.h = SystemClock.elapsedRealtime();
                    }

                    return this.d;
                }

                this.h = v2;
            }

            if(this.d > v6) {
                ++this.e;
            }

            this.d = v6;
            return v6 + (this.e << 32);
        }

        public boolean b(long arg6) {
            boolean v6 = this.h == -1 || arg6 <= 0 || SystemClock.elapsedRealtime() - this.h < 200 ? false : true;
            return v6;
        }

        public long c() {
            return this.b() * 1000000 / (((long)this.c));
        }

        public boolean d() {
            return 0;
        }

        public long e() {
            throw new UnsupportedOperationException();
        }

        public long f() {
            throw new UnsupportedOperationException();
        }

        public float g() {
            return 1f;
        }
    }

    @TargetApi(value=19) class b extends a {
        private final AudioTimestamp b;
        private long c;
        private long d;
        private long e;

        public b() {
            super(null);
            this.b = new AudioTimestamp();
        }

        public void a(AudioTrack arg1, boolean arg2) {
            super.a(arg1, arg2);
            this.c = 0;
            this.d = 0;
            this.e = 0;
        }

        public boolean d() {
            boolean v0 = this.a.getTimestamp(this.b);
            if(v0) {
                long v1 = this.b.framePosition;
                if(this.d > v1) {
                    ++this.c;
                }

                this.d = v1;
                this.e = v1 + (this.c << 32);
            }

            return v0;
        }

        public long e() {
            return this.b.nanoTime;
        }

        public long f() {
            return this.e;
        }
    }

    @TargetApi(value=23) class c extends b {
        private PlaybackParams b;
        private float c;

        public c() {
            super();
            this.c = 1f;
        }

        public void a(AudioTrack arg1, boolean arg2) {
            super.a(arg1, arg2);
            this.h();
        }

        public void a(PlaybackParams arg1) {
            if(arg1 != null) {
            }
            else {
                arg1 = new PlaybackParams();
            }

            arg1 = arg1.allowDefaults();
            this.b = arg1;
            this.c = arg1.getSpeed();
            this.h();
        }

        public float g() {
            return this.c;
        }

        private void h() {
            if(this.a != null && this.b != null) {
                this.a.setPlaybackParams(this.b);
            }
        }
    }

    public final class d extends Exception {
        public final int a;

        public d(int arg3, int arg4, int arg5, int arg6) {
            StringBuilder v0 = new StringBuilder(82);
            v0.append("AudioTrack init failed: ");
            v0.append(arg3);
            v0.append(", Config(");
            v0.append(arg4);
            v0.append(", ");
            v0.append(arg5);
            v0.append(", ");
            v0.append(arg6);
            v0.append(")");
            super(v0.toString());
            this.a = arg3;
        }
    }

    public final class e extends RuntimeException {
        public e(String arg1) {
            super(arg1);
        }
    }

    public final class f extends Exception {
        public final int a;

        public f(int arg3) {
            StringBuilder v0 = new StringBuilder(36);
            v0.append("AudioTrack write failed: ");
            v0.append(arg3);
            super(v0.toString());
            this.a = arg3;
        }
    }

    private int A;
    private int B;
    private long C;
    private long D;
    private long E;
    private float F;
    private byte[] G;
    private int H;
    private int I;
    private ByteBuffer J;
    private boolean K;
    public static boolean a = false;
    public static boolean b = false;
    private final bs c;
    private final ConditionVariable d;
    private final long[] e;
    private final a f;
    private AudioTrack g;
    private AudioTrack h;
    private int i;
    private int j;
    private int k;
    private int l;
    private int m;
    private boolean n;
    private int o;
    private int p;
    private long q;
    private int r;
    private int s;
    private long t;
    private long u;
    private boolean v;
    private long w;
    private Method x;
    private long y;
    private long z;

    static {
    }

    public bt() {
        this(null, 3);
    }

    public bt(bs arg4, int arg5) {
        b v4_1;
        super();
        this.c = arg4;
        this.d = new ConditionVariable(true);
        com.google.ads.interactivemedia.v3.internal.bt$1 v0 = null;
        if(ft.a >= 18) {
            try {
                this.x = AudioTrack.class.getMethod("getLatency", ((Class[])v0));
                goto label_15;
            }
            catch(NoSuchMethodException ) {
            label_15:
                if(ft.a >= 23) {
                    c v4 = new c();
                }
                else if(ft.a >= 19) {
                    v4_1 = new b();
                }
                else {
                    a v4_2 = new a(v0);
                }

                this.f = ((a)v4_1);
                this.e = new long[10];
                this.k = arg5;
                this.F = 1f;
                this.B = 0;
                return;
            }
        }

        goto label_15;
    }

    private static int a(int arg2, ByteBuffer arg3) {
        if(arg2 != 7) {
            if(arg2 == 8) {
            }
            else if(arg2 == 5) {
                return fd.a();
            }
            else if(arg2 == 6) {
                return fd.a(arg3);
            }
            else {
                StringBuilder v1 = new StringBuilder(38);
                v1.append("Unexpected audio encoding: ");
                v1.append(arg2);
                throw new IllegalStateException(v1.toString());
            }
        }

        return fg.a(arg3);
    }

    @TargetApi(value=21) private static int a(AudioTrack arg1, ByteBuffer arg2, int arg3) {
        return arg1.write(arg2, arg3, 1);
    }

    private long a(long arg3) {
        return arg3 / (((long)this.o));
    }

    static ConditionVariable a(bt arg0) {
        return arg0.d;
    }

    private static ByteBuffer a(ByteBuffer arg5, int arg6, int arg7, int arg8, ByteBuffer arg9) {
        int v3;
        int v0 = 1073741824;
        int v1 = -2147483648;
        int v2 = 3;
        if(arg8 == v1) {
            v3 = arg7 / 3 * 2;
        }
        else if(arg8 == v2) {
            v3 = arg7 * 2;
        }
        else if(arg8 == v0) {
            v3 = arg7 / 2;
        }
        else {
            throw new IllegalStateException();
        }

        if(arg9 == null || arg9.capacity() < v3) {
            arg9 = ByteBuffer.allocateDirect(v3);
        }

        arg9.position(0);
        arg9.limit(v3);
        arg7 += arg6;
        if(arg8 != v1) {
            if(arg8 != v2) {
                if(arg8 == v0) {
                    while(true) {
                        if(arg6 >= arg7) {
                            goto label_56;
                        }

                        arg9.put(arg5.get(arg6 + 2));
                        arg9.put(arg5.get(arg6 + 3));
                        arg6 += 4;
                    }
                }

                throw new IllegalStateException();
            }

            while(arg6 < arg7) {
                arg9.put(0);
                arg9.put(((byte)((arg5.get(arg6) & 255) - 128)));
                ++arg6;
            }
        }
        else {
            while(arg6 < arg7) {
                arg9.put(arg5.get(arg6 + 1));
                arg9.put(arg5.get(arg6 + 2));
                arg6 += 3;
            }
        }

    label_56:
        arg9.position(0);
        return arg9;
    }

    @TargetApi(value=21) private static void a(AudioTrack arg0, float arg1) {
        arg0.setVolume(arg1);
    }

    public boolean a() {
        boolean v0 = this.h != null ? true : false;
        return v0;
    }

    public int a(int arg11) {
        this.d.block();
        this.h = arg11 == 0 ? new AudioTrack(this.k, this.i, this.j, this.m, this.p, 1) : new AudioTrack(this.k, this.i, this.j, this.m, this.p, 1, arg11);
        this.p();
        int v0 = this.h.getAudioSessionId();
        if((bt.a) && ft.a < 21) {
            if(this.g != null && v0 != this.g.getAudioSessionId()) {
                this.m();
            }

            if(this.g != null) {
                goto label_55;
            }

            this.g = new AudioTrack(this.k, 4000, 4, 2, 2, 0, v0);
        }

    label_55:
        this.f.a(this.h, this.s());
        this.l();
        return v0;
    }

    public int a(ByteBuffer arg18, int arg19, int arg20, long arg21) {
        int v11;
        ByteBuffer v10;
        bt v0 = this;
        int v1 = arg20;
        long v2 = arg21;
        int v5 = 1;
        int v6 = 2;
        int v7 = 0;
        if(this.s()) {
            if(v0.h.getPlayState() == v6) {
                return 0;
            }
            else if(v0.h.getPlayState() == 1 && v0.h.getPlaybackHeadPosition() != 0) {
                return 0;
            }
        }

        int v8 = 21;
        if(v0.I != 0) {
            v10 = arg18;
            v5 = 0;
        }
        else if(v1 == 0) {
            return v6;
        }
        else {
            boolean v4 = v0.m != v0.l ? true : false;
            v0.K = v4;
            if(v0.K) {
                v4 = v0.m == v6 ? true : false;
                fe.b(v4);
                v0.J = bt.a(arg18, arg19, v1, v0.l, v0.J);
                v10 = v0.J;
                v11 = v0.J.position();
                v1 = v0.J.limit();
            }
            else {
                v10 = arg18;
                v11 = arg19;
            }

            v0.I = v1;
            v10.position(v11);
            if((v0.n) && v0.A == 0) {
                v0.A = bt.a(v0.m, v10);
            }

            if(v0.B == 0) {
                v0.C = Math.max(0, v2);
                v0.B = 1;
                goto label_105;
            }
            else {
                long v11_1 = v0.C + v0.b(this.q());
                if(v0.B == 1 && Math.abs(v11_1 - v2) > 200000) {
                    StringBuilder v13 = new StringBuilder(80);
                    v13.append("Discontinuity detected [expected ");
                    v13.append(v11_1);
                    v13.append(", got ");
                    v13.append(v2);
                    v13.append("]");
                    Log.e("AudioTrack", v13.toString());
                    v0.B = v6;
                }

                if(v0.B == v6) {
                    v0.C += v2 - v11_1;
                    v0.B = 1;
                    goto label_106;
                }

            label_105:
                v5 = 0;
            }

        label_106:
            if(ft.a >= v8) {
                goto label_121;
            }

            if(v0.G == null || v0.G.length < v1) {
                v0.G = new byte[v1];
            }

            v10.get(v0.G, 0, v1);
            v0.H = 0;
        }

    label_121:
        if(ft.a < v8) {
            int v2_1 = v0.p - (((int)(v0.y - v0.f.b() * (((long)v0.o)))));
            if(v2_1 > 0) {
                v7 = v0.h.write(v0.G, v0.H, Math.min(v0.I, v2_1));
                if(v7 >= 0) {
                    v0.H += v7;
                }
            }
        }
        else {
            if(v0.K) {
                v10 = v0.J;
            }

            v7 = bt.a(v0.h, v10, v0.I);
        }

        if(v7 >= 0) {
            v0.I -= v7;
            if(!v0.n) {
                v0.y += ((long)v7);
            }

            if(v0.I == 0) {
                if(v0.n) {
                    v0.z += ((long)v0.A);
                }

                v5 |= 2;
            }

            if(v0.f.b(this.q())) {
                Log.w("AudioTrack", "Resetting stalled audio track");
                this.j();
                v5 |= 2;
            }

            return v5;
        }

        throw new f(v7);
    }

    public long a(boolean arg7) {
        if(!this.n()) {
            return -9223372036854775808L;
        }

        if(this.h.getPlayState() == 3) {
            this.o();
        }

        long v2 = 1000;
        long v0 = System.nanoTime() / v2;
        if(this.v) {
            v0 = this.b(this.f.f() + this.c(((long)((((float)(v0 - this.f.e() / v2))) * this.f.g())))) + this.C;
        }
        else {
            if(this.s == 0) {
                v0 = this.f.c();
            }
            else {
                v0 += this.t;
            }

            v0 += this.C;
            if(arg7) {
                return v0;
            }

            v0 -= this.E;
        }

        return v0;
    }

    public void a(float arg2) {
        if(this.F != arg2) {
            this.F = arg2;
            this.l();
        }
    }

    public void a(PlaybackParams arg2) {
        this.f.a(arg2);
    }

    public void a(String arg7, int arg8, int arg9, int arg10) {
        this.a(arg7, arg8, arg9, arg10, 0);
    }

    public void a(String arg9, int arg10, int arg11, int arg12, int arg13) {
        int v9;
        int v0 = 252;
        switch(arg10) {
            case 1: {
                goto label_27;
            }
            case 2: {
                goto label_25;
            }
            case 3: {
                goto label_23;
            }
            case 4: {
                goto label_21;
            }
            case 5: {
                goto label_19;
            }
            case 6: {
                goto label_17;
            }
            case 7: {
                goto label_15;
            }
            case 8: {
                goto label_13;
            }
        }

        StringBuilder v12 = new StringBuilder(38);
        v12.append("Unsupported channel count: ");
        v12.append(arg10);
        throw new IllegalArgumentException(v12.toString());
    label_17:
        int v2 = 252;
        goto label_28;
    label_19:
        v2 = 220;
        goto label_28;
    label_21:
        v2 = 204;
        goto label_28;
    label_23:
        v2 = 28;
        goto label_28;
    label_25:
        v2 = 12;
        goto label_28;
    label_27:
        v2 = 4;
        goto label_28;
    label_13:
        v2 = au.a;
        goto label_28;
    label_15:
        v2 = 1276;
    label_28:
        int v5 = 5;
        int v6 = 3;
        if(ft.a > 23 || !"foster".equals(ft.b) || !"NVIDIA".equals(ft.c)) {
        label_48:
            v0 = v2;
        }
        else if(arg10 != v6 && arg10 != v5) {
            if(arg10 != 7) {
                goto label_48;
            }
            else {
                v0 = au.a;
            }
        }

        boolean v3 = true;
        v2 = "audio/raw".equals(arg9) ^ 1;
        if(ft.a <= 25 && ("fugu".equals(ft.b)) && v2 != 0 && arg10 == 1) {
            v0 = 12;
        }

        int v1 = 2;
        if(v2 != 0) {
            arg12 = bt.b(arg9);
        }
        else if(arg12 != v6 && arg12 != v1 && arg12 != -2147483648) {
            if(arg12 == 1073741824) {
            }
            else {
                StringBuilder v11 = new StringBuilder(37);
                v11.append("Unsupported PCM encoding: ");
                v11.append(arg12);
                throw new IllegalArgumentException(v11.toString());
            }
        }

        if((this.a()) && this.l == arg12 && this.i == arg11 && this.j == v0) {
            return;
        }

        this.j();
        this.l = arg12;
        this.n = ((boolean)v2);
        this.i = arg11;
        this.j = v0;
        if(v2 != 0) {
        }
        else {
            arg12 = 2;
        }

        this.m = arg12;
        this.o = arg10 * 2;
        if(arg13 != 0) {
            this.p = arg13;
        }
        else {
            if(v2 != 0) {
                if(this.m != v5) {
                    if(this.m == 6) {
                    }
                    else {
                        v9 = 49152;
                        goto label_117;
                    }
                }

                v9 = 20480;
            }
            else {
                v9 = AudioTrack.getMinBufferSize(arg11, v0, this.m);
                if(v9 != -2) {
                }
                else {
                    v3 = false;
                }

                fe.b(v3);
                arg10 = v9 * 4;
                arg11 = (((int)this.c(250000))) * this.o;
                v9 = ((int)Math.max(((long)v9), this.c(750000) * (((long)this.o))));
                if(arg10 < arg11) {
                    v9 = arg11;
                    goto label_117;
                }

                if(arg10 > v9) {
                    goto label_117;
                }

                v9 = arg10;
            }

        label_117:
            this.p = v9;
        }

        long v9_1 = v2 != 0 ? -1 : this.b(this.a(((long)this.p)));
        this.q = v9_1;
    }

    public boolean a(String arg2) {
        boolean v2 = this.c == null || !this.c.a(bt.b(arg2)) ? false : true;
        return v2;
    }

    private static int b(String arg3) {
        int v3;
        int v0 = arg3.hashCode();
        if(v0 != -1095064472) {
            if(v0 != 187078296) {
                if(v0 != 1504578661) {
                    if(v0 != 1505942594) {
                        goto label_31;
                    }
                    else if(arg3.equals("audio/vnd.dts.hd")) {
                        v3 = 3;
                    }
                    else {
                        goto label_31;
                    }
                }
                else if(arg3.equals("audio/eac3")) {
                    v3 = 1;
                }
                else {
                    goto label_31;
                }
            }
            else if(arg3.equals("audio/ac3")) {
                v3 = 0;
            }
            else {
                goto label_31;
            }
        }
        else if(arg3.equals("audio/vnd.dts")) {
            v3 = 2;
        }
        else {
        label_31:
            v3 = -1;
        }

        switch(v3) {
            case 0: {
                return 5;
            }
            case 1: {
                return 6;
            }
            case 2: {
                return 7;
            }
            case 3: {
                return 8;
            }
        }

        return 0;
    }

    private long b(long arg3) {
        return arg3 * 1000000 / (((long)this.i));
    }

    private static void b(AudioTrack arg0, float arg1) {
        arg0.setStereoVolume(arg1, arg1);
    }

    public int b() {
        return this.a(0);
    }

    public boolean b(int arg2) {
        if(this.k == arg2) {
            return 0;
        }

        this.k = arg2;
        this.j();
        return 1;
    }

    private long c(long arg3) {
        return arg3 * (((long)this.i)) / 1000000;
    }

    public int c() {
        return this.p;
    }

    public long d() {
        return this.q;
    }

    public void e() {
        if(this.a()) {
            this.D = System.nanoTime() / 1000;
            this.h.play();
        }
    }

    public void f() {
        if(this.B == 1) {
            this.B = 2;
        }
    }

    public void g() {
        if(this.a()) {
            this.f.a(this.q());
        }
    }

    public boolean h() {
        boolean v0;
        if(this.a()) {
            if(this.q() <= this.f.b() && !this.t()) {
                goto label_10;
            }

            v0 = true;
        }
        else {
        label_10:
            v0 = false;
        }

        return v0;
    }

    public void i() {
        if(this.a()) {
            this.r();
            this.f.a();
        }
    }

    public void j() {
        if(this.a()) {
            this.y = 0;
            this.z = 0;
            this.A = 0;
            this.I = 0;
            this.B = 0;
            this.E = 0;
            this.r();
            if(this.h.getPlayState() == 3) {
                this.h.pause();
            }

            AudioTrack v0 = this.h;
            this.h = null;
            this.f.a(null, false);
            this.d.close();
            new Thread(v0) {
                public void run() {
                    try {
                        this.a.flush();
                        this.a.release();
                    }
                    catch(Throwable v0) {
                        bt.a(this.b).open();
                        throw v0;
                    }

                    bt.a(this.b).open();
                }
            }.start();
        }
    }

    public void k() {
        this.j();
        this.m();
    }

    private void l() {
        if(!this.a()) {
        }
        else if(ft.a >= 21) {
            bt.a(this.h, this.F);
        }
        else {
            bt.b(this.h, this.F);
        }
    }

    private void m() {
        if(this.g == null) {
            return;
        }

        AudioTrack v0 = this.g;
        this.g = null;
        new Thread(v0) {
            public void run() {
                this.a.release();
            }
        }.start();
    }

    private boolean n() {
        boolean v0 = !this.a() || this.B == 0 ? false : true;
        return v0;
    }

    private void o() {
        // Method was not decompiled
    }

    private void p() {
        int v0 = this.h.getState();
        if(v0 == 1) {
            return;
        }

        AudioTrack v1 = null;
        try {
            this.h.release();
            goto label_12;
        }
        catch(Exception ) {
        label_12:
            this.h = v1;
            throw new d(v0, this.i, this.j, this.p);
        }
        catch(Throwable v0_1) {
            this.h = v1;
            throw v0_1;
        }
    }

    private long q() {
        long v0 = this.n ? this.z : this.a(this.y);
        return v0;
    }

    private void r() {
        this.t = 0;
        this.s = 0;
        this.r = 0;
        this.u = 0;
        this.v = false;
        this.w = 0;
    }

    private boolean s() {
        boolean v0;
        if(ft.a < 23) {
            if(this.m != 5 && this.m != 6) {
                goto label_11;
            }

            v0 = true;
        }
        else {
        label_11:
            v0 = false;
        }

        return v0;
    }

    private boolean t() {
        boolean v0 = !this.s() || this.h.getPlayState() != 2 || this.h.getPlaybackHeadPosition() != 0 ? false : true;
        return v0;
    }
}

