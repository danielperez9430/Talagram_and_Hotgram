package com.google.ads.interactivemedia.v3.internal;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.media.MediaCodec$BufferInfo;
import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.os.Handler;
import android.os.SystemClock;
import android.view.Surface;
import java.nio.ByteBuffer;

@TargetApi(value=16) public class bi extends bg {
    public interface a extends b {
        void a(int arg1, long arg2);

        void a(int arg1, int arg2, int arg3, float arg4);

        void a(Surface arg1);
    }

    private final br c;
    private final a d;
    private final long e;
    private final int f;
    private final int g;
    private Surface h;
    private boolean i;
    private boolean j;
    private long k;
    private long l;
    private int m;
    private int n;
    private int o;
    private float p;
    private int q;
    private int r;
    private int s;
    private float t;
    private int u;
    private int v;
    private int w;
    private float x;

    public bi(Context arg13, bn arg14, bf arg15, int arg16, long arg17, Handler arg19, a arg20, int arg21) {
        this(arg13, arg14, arg15, arg16, arg17, null, false, arg19, arg20, arg21);
    }

    public bi(Context arg9, bn arg10, bf arg11, int arg12, long arg13, bv arg15, boolean arg16, Handler arg17, a arg18, int arg19) {
        super(arg10, arg11, arg15, arg16, arg17, arg18);
        this.c = new br(arg9);
        this.f = arg12;
        this.e = 1000 * arg13;
        this.d = arg18;
        this.g = arg19;
        this.k = -1;
        this.q = -1;
        this.r = -1;
        this.t = -1f;
        this.p = -1f;
        this.u = -1;
        this.v = -1;
        this.x = -1f;
    }

    private void A() {
        if(this.b != null && this.d != null) {
            if(this.m == 0) {
            }
            else {
                long v0 = SystemClock.elapsedRealtime();
                this.b.post(new Runnable(this.m, v0 - this.l) {
                    public void run() {
                        bi.a(this.c).a(this.a, this.b);
                    }
                });
                this.m = 0;
                this.l = v0;
            }
        }
    }

    static a a(bi arg0) {
        return arg0.d;
    }

    private void a() {
        if(this.b != null && this.d != null && (this.u != this.q || this.v != this.r || this.w != this.s || this.x != this.t)) {
            int v0 = this.q;
            int v7 = this.r;
            int v8 = this.s;
            float v9 = this.t;
            this.b.post(new Runnable(v0, v7, v8, v9) {
                public void run() {
                    bi.a(this.e).a(this.a, this.b, this.c, this.d);
                }
            });
            this.u = v0;
            this.v = v7;
            this.w = v8;
            this.x = v9;
        }
    }

    @SuppressLint(value={"InlinedApi"}) private void a(MediaFormat arg8, boolean arg9) {
        if(arg8.containsKey("max-input-size")) {
            return;
        }

        int v0 = arg8.getInteger("height");
        if((arg9) && (arg8.containsKey("max-height"))) {
            v0 = Math.max(v0, arg8.getInteger("max-height"));
        }

        int v1 = arg8.getInteger("width");
        if((arg9) && (arg8.containsKey("max-width"))) {
            v1 = Math.max(v0, arg8.getInteger("max-width"));
        }

        String v9 = arg8.getString("mime");
        int v2 = -1;
        int v5 = 4;
        switch(v9.hashCode()) {
            case 1187890754: {
                if(!v9.equals("video/mp4v-es")) {
                    goto label_60;
                }

                v2 = 1;
                break;
            }
            case 1331836730: {
                if(!v9.equals("video/avc")) {
                    goto label_60;
                }

                v2 = 2;
                break;
            }
            case 1599127256: {
                if(!v9.equals("video/x-vnd.on2.vp8")) {
                    goto label_60;
                }

                v2 = 3;
                break;
            }
            case 1599127257: {
                if(!v9.equals("video/x-vnd.on2.vp9")) {
                    goto label_60;
                }

                v2 = 5;
                break;
            }
            case -1664118616: {
                if(!v9.equals("video/3gpp")) {
                    goto label_60;
                }

                v2 = 0;
                break;
            }
            case -1662541442: {
                if(!v9.equals("video/hevc")) {
                    goto label_60;
                }

                v2 = 4;
                break;
            }
            default: {
                break;
            }
        }

    label_60:
        switch(v2) {
            case 2: {
                goto label_64;
            }
            case 0: 
            case 1: 
            case 3: {
                goto label_77;
            }
            case 4: 
            case 5: {
                goto label_62;
            }
        }

        return;
    label_77:
        v1 *= v0;
        goto label_78;
    label_62:
        v1 *= v0;
        goto label_79;
    label_64:
        if("BRAVIA 4K 2015".equals(ft.d)) {
            return;
        }

        v1 = (v1 + 15) / 16 * ((v0 + 15) / 16) * 16 * 16;
    label_78:
        v5 = 2;
    label_79:
        arg8.setInteger("max-input-size", v1 * 3 / (v5 * 2));
    }

    private void a(Surface arg2) {
        if(this.h == arg2) {
            return;
        }

        this.h = arg2;
        this.i = false;
        int v2 = this.v();
        if(v2 == 2 || v2 == 3) {
            this.m();
            this.j();
        }
    }

    protected void a(int arg2, long arg3, boolean arg5) {
        super.a(arg2, arg3, arg5);
        if((arg5) && this.e > 0) {
            this.k = SystemClock.elapsedRealtime() * 1000 + this.e;
        }

        this.c.a();
    }

    public void a(int arg2, Object arg3) {
        if(arg2 == 1) {
            this.a(((Surface)arg3));
        }
        else {
            super.a(arg2, arg3);
        }
    }

    protected void a(long arg1) {
        super.a(arg1);
        this.j = false;
        this.n = 0;
        this.k = -1;
    }

    protected void a(MediaCodec arg2, int arg3) {
        fs.a("skipVideoBuffer");
        arg2.releaseOutputBuffer(arg3, false);
        fs.a();
        ++this.a.g;
    }

    @TargetApi(value=21) protected void a(MediaCodec arg2, int arg3, long arg4) {
        this.a();
        fs.a("releaseOutputBuffer");
        arg2.releaseOutputBuffer(arg3, arg4);
        fs.a();
        ++this.a.f;
        this.j = true;
        this.i();
    }

    protected void a(MediaCodec arg5, MediaFormat arg6) {
        int v0 = !arg6.containsKey("crop-right") || !arg6.containsKey("crop-left") || !arg6.containsKey("crop-bottom") || !arg6.containsKey("crop-top") ? 0 : 1;
        int v2 = v0 != 0 ? arg6.getInteger("crop-right") - arg6.getInteger("crop-left") + 1 : arg6.getInteger("width");
        this.q = v2;
        v0 = v0 != 0 ? arg6.getInteger("crop-bottom") - arg6.getInteger("crop-top") + 1 : arg6.getInteger("height");
        this.r = v0;
        this.t = this.p;
        if(ft.a >= 21) {
            if(this.o != 90 && this.o != 270) {
                goto label_60;
            }

            int v6 = this.q;
            this.q = this.r;
            this.r = v6;
            this.t = 1f / this.t;
        }
        else {
            this.s = this.o;
        }

    label_60:
        arg5.setVideoScalingMode(this.f);
    }

    protected void a(MediaCodec arg2, boolean arg3, MediaFormat arg4, MediaCrypto arg5) {
        this.a(arg4, arg3);
        arg2.configure(arg4, this.h, arg5, 0);
    }

    protected void a(bk arg3) {
        super.a(arg3);
        float v0 = arg3.a.m == -1f ? 1f : arg3.a.m;
        this.p = v0;
        int v3 = arg3.a.l == -1 ? 0 : arg3.a.l;
        this.o = v3;
    }

    protected boolean a(long arg2, long arg4) {
        boolean v2 = arg2 < -30000 ? true : false;
        return v2;
    }

    protected boolean a(long arg17, long arg19, MediaCodec arg21, ByteBuffer arg22, MediaCodec$BufferInfo arg23, int arg24, boolean arg25) {
        bi v0 = this;
        long v1 = arg19;
        MediaCodec v3 = arg21;
        MediaCodec$BufferInfo v4 = arg23;
        int v5 = arg24;
        if(arg25) {
            v0.a(v3, v5);
            v0.n = 0;
            return 1;
        }

        int v9 = 21;
        if(!v0.j) {
            if(ft.a >= v9) {
                v0.a(v3, v5, System.nanoTime());
            }
            else {
                v0.c(v3, v5);
            }

            v0.n = 0;
            return 1;
        }

        if(this.v() != 3) {
            return 0;
        }

        long v12 = 1000;
        long v14 = v4.presentationTimeUs - arg17 - (SystemClock.elapsedRealtime() * v12 - v1);
        long v10 = System.nanoTime();
        long v6 = v0.c.a(v4.presentationTimeUs, v14 * v12 + v10);
        v10 = (v6 - v10) / v12;
        if(v0.a(v10, v1)) {
            v0.b(v3, v5);
            return 1;
        }

        if(ft.a >= v9) {
            if(v10 < 50000) {
                v0.a(v3, v5, v6);
                v0.n = 0;
                return 1;
            }
        }
        else if(v10 < 30000) {
            if(v10 > 11000) {
                v10 -= 10000;
                try {
                    Thread.sleep(v10 / v12);
                }
                catch(InterruptedException ) {
                    Thread.currentThread().interrupt();
                }

                goto label_68;
            }
            else {
            label_68:
                v0.c(v3, v5);
                v0.n = 0;
                boolean v1_1 = true;
                return v1_1;
            }
        }

        return false;
    }

    protected boolean a(MediaCodec arg2, boolean arg3, bj arg4, bj arg5) {
        boolean v2;
        if(arg5.b.equals(arg4.b)) {
            if(!arg3) {
                if(arg4.h != arg5.h) {
                }
                else if(arg4.i == arg5.i) {
                    goto label_11;
                }

                goto label_13;
            }

        label_11:
            v2 = true;
        }
        else {
        label_13:
            v2 = false;
        }

        return v2;
    }

    protected boolean a(bf arg3, bj arg4) {
        String v4 = arg4.b;
        boolean v1 = false;
        if((fl.b(v4)) && (("video/x-unknown".equals(v4)) || arg3.a(v4, false) != null)) {
            v1 = true;
        }

        return v1;
    }

    protected void b(MediaCodec arg2, int arg3) {
        fs.a("dropVideoBuffer");
        arg2.releaseOutputBuffer(arg3, false);
        fs.a();
        ++this.a.h;
        ++this.m;
        ++this.n;
        this.a.i = Math.max(this.n, this.a.i);
        if(this.m == this.g) {
            this.A();
        }
    }

    protected void c(MediaCodec arg2, int arg3) {
        this.a();
        fs.a("releaseOutputBuffer");
        arg2.releaseOutputBuffer(arg3, true);
        fs.a();
        ++this.a.f;
        this.j = true;
        this.i();
    }

    protected void c() {
        super.c();
        this.m = 0;
        this.l = SystemClock.elapsedRealtime();
    }

    protected void d() {
        this.k = -1;
        this.A();
        super.d();
    }

    protected boolean f() {
        // Method was not decompiled
    }

    protected void g() {
        this.q = -1;
        this.r = -1;
        this.t = -1f;
        this.p = -1f;
        this.u = -1;
        this.v = -1;
        this.x = -1f;
        this.c.b();
        super.g();
    }

    private void i() {
        if(this.b != null && this.d != null) {
            if(this.i) {
            }
            else {
                this.b.post(new Runnable(this.h) {
                    public void run() {
                        bi.a(this.b).a(this.a);
                    }
                });
                this.i = true;
            }
        }
    }

    protected boolean k() {
        boolean v0 = !super.k() || this.h == null || !this.h.isValid() ? false : true;
        return v0;
    }
}

