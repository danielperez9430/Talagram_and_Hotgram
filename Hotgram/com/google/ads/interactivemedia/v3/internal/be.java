package com.google.ads.interactivemedia.v3.internal;

import android.annotation.TargetApi;
import android.media.MediaCodec$BufferInfo;
import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.media.PlaybackParams;
import android.os.Handler;
import android.os.SystemClock;
import android.view.Surface;
import java.nio.ByteBuffer;

@TargetApi(value=16) public class be extends bg implements bd {
    public interface a extends b {
        void a(int arg1, long arg2, long arg3);

        void a(d arg1);

        void a(f arg1);
    }

    private final a c;
    private final bt d;
    private boolean e;
    private MediaFormat f;
    private int g;
    private int h;
    private long i;
    private boolean j;
    private boolean k;
    private long l;

    public be(bn arg11, bf arg12, bv arg13, boolean arg14, Handler arg15, a arg16, bs arg17, int arg18) {
        this(new bn[]{arg11}, arg12, arg13, arg14, arg15, arg16, arg17, arg18);
    }

    public be(bn[] arg1, bf arg2, bv arg3, boolean arg4, Handler arg5, a arg6, bs arg7, int arg8) {
        super(arg1, arg2, arg3, arg4, arg5, ((b)arg6));
        this.c = arg6;
        this.h = 0;
        this.d = new bt(arg7, arg8);
    }

    static a a(be arg0) {
        return arg0.c;
    }

    private void a(int arg10, long arg11, long arg13) {
        if(this.b != null && this.c != null) {
            this.b.post(new Runnable(arg10, arg11, arg13) {
                public void run() {
                    be.a(this.d).a(this.a, this.b, this.c);
                }
            });
        }
    }

    private void a(d arg3) {
        if(this.b != null && this.c != null) {
            this.b.post(new Runnable(arg3) {
                public void run() {
                    be.a(this.b).a(this.a);
                }
            });
        }
    }

    private void a(f arg3) {
        if(this.b != null && this.c != null) {
            this.b.post(new Runnable(arg3) {
                public void run() {
                    be.a(this.b).a(this.a);
                }
            });
        }
    }

    public long a() {
        long v0 = this.d.a(this.e());
        if(v0 != -9223372036854775808L) {
            if(this.j) {
            }
            else {
                v0 = Math.max(this.i, v0);
            }

            this.i = v0;
            this.j = false;
        }

        return this.i;
    }

    protected ay a(bf arg2, String arg3, boolean arg4) {
        if(this.a(arg3)) {
            ay v0 = arg2.a();
            if(v0 != null) {
                this.e = true;
                return v0;
            }
        }

        this.e = false;
        return super.a(arg2, arg3, arg4);
    }

    protected boolean a(String arg2) {
        return this.d.a(arg2);
    }

    protected void a(int arg1) {
    }

    public void a(int arg1, Object arg2) {
        switch(arg1) {
            case 1: {
                this.d.a(((Float)arg2).floatValue());
                break;
            }
            case 2: {
                this.d.a(((PlaybackParams)arg2));
                break;
            }
            case 3: {
                if(!this.d.b(((Integer)arg2).intValue())) {
                    return;
                }

                this.h = 0;
                break;
            }
            default: {
                super.a(arg1, arg2);
                break;
            }
        }
    }

    protected void a(long arg2) {
        super.a(arg2);
        this.d.j();
        this.i = arg2;
        this.j = true;
    }

    protected void a(MediaCodec arg4, MediaFormat arg5) {
        int v4 = this.f != null ? 1 : 0;
        String v0 = v4 != 0 ? this.f.getString("mime") : "audio/raw";
        if(v4 != 0) {
            arg5 = this.f;
        }

        this.d.a(v0, arg5.getInteger("channel-count"), arg5.getInteger("sample-rate"), this.g);
    }

    protected void a(MediaCodec arg5, boolean arg6, MediaFormat arg7, MediaCrypto arg8) {
        String v6 = arg7.getString("mime");
        Surface v2 = null;
        if(this.e) {
            arg7.setString("mime", "audio/raw");
            arg5.configure(arg7, v2, arg8, 0);
            arg7.setString("mime", v6);
            this.f = arg7;
        }
        else {
            arg5.configure(arg7, v2, arg8, 0);
            this.f = ((MediaFormat)v2);
        }
    }

    protected void a(bk arg3) {
        super.a(arg3);
        int v3 = "audio/raw".equals(arg3.a.b) ? arg3.a.s : 2;
        this.g = v3;
    }

    protected boolean a(long arg19, long arg21, MediaCodec arg23, ByteBuffer arg24, MediaCodec$BufferInfo arg25, int arg26, boolean arg27) {
        be v7 = this;
        MediaCodec v0 = arg23;
        MediaCodec$BufferInfo v8 = arg25;
        int v9 = arg26;
        if((v7.e) && (v8.flags & 2) != 0) {
            v0.releaseOutputBuffer(v9, false);
            return 1;
        }

        if(arg27) {
            v0.releaseOutputBuffer(v9, false);
            ++v7.a.g;
            v7.d.f();
            return 1;
        }

        int v2 = 3;
        if(!v7.d.a()) {
            try {
                if(v7.h != 0) {
                    v7.d.a(v7.h);
                }
                else {
                    v7.h = v7.d.b();
                    v7.a(v7.h);
                }

                v7.k = false;
            }
            catch(d v0_1) {
                v7.a(v0_1);
                throw new az(((Throwable)v0_1));
            }

            if(this.v() != v2) {
                goto label_72;
            }

            v7.d.e();
        }
        else {
            boolean v1 = v7.k;
            v7.k = v7.d.h();
            if(!v1) {
                goto label_72;
            }

            if(v7.k) {
                goto label_72;
            }

            if(this.v() != v2) {
                goto label_72;
            }

            long v5 = SystemClock.elapsedRealtime() - v7.l;
            long v1_1 = v7.d.d();
            long v3 = -1;
            if(v1_1 == v3) {
            }
            else {
                v3 = v1_1 / 1000;
            }

            this.a(v7.d.c(), v3, v5);
        }

        try {
        label_72:
            int v1_2 = v7.d.a(arg24, v8.offset, v8.size, v8.presentationTimeUs);
            v7.l = SystemClock.elapsedRealtime();
            if((v1_2 & 1) == 0) {
                goto label_85;
            }
        }
        catch(f v0_2) {
            v7.a(v0_2);
            throw new az(((Throwable)v0_2));
        }

        this.i();
        v7.j = true;
    label_85:
        if((v1_2 & 2) != 0) {
            v0.releaseOutputBuffer(v9, false);
            ++v7.a.f;
            return 1;
        }

        return 0;
    }

    protected boolean a(bf arg3, bj arg4) {
        String v4 = arg4.b;
        boolean v1 = false;
        if((fl.a(v4)) && (("audio/x-unknown".equals(v4)) || (this.a(v4)) && arg3.a() != null || arg3.a(v4, false) != null)) {
            v1 = true;
        }

        return v1;
    }

    protected bd b() {
        return this;
    }

    protected void c() {
        super.c();
        this.d.e();
    }

    protected void d() {
        this.d.i();
        super.d();
    }

    protected boolean e() {
        boolean v0 = !super.e() || (this.d.h()) ? false : true;
        return v0;
    }

    protected boolean f() {
        boolean v0 = (this.d.h()) || (super.f()) ? true : false;
        return v0;
    }

    protected void g() {
        this.h = 0;
        try {
            this.d.k();
        }
        catch(Throwable v0) {
            super.g();
            throw v0;
        }

        super.g();
    }

    protected void h() {
        this.d.g();
    }

    protected void i() {
    }
}

