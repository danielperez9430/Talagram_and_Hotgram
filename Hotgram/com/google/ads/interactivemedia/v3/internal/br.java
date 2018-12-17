package com.google.ads.interactivemedia.v3.internal;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Handler$Callback;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.view.Choreographer$FrameCallback;
import android.view.Choreographer;

@TargetApi(value=16) public final class br {
    final class a implements Handler$Callback, Choreographer$FrameCallback {
        public volatile long a;
        private static final a b;
        private final Handler c;
        private final HandlerThread d;
        private Choreographer e;
        private int f;

        static {
            a.b = new a();
        }

        private a() {
            super();
            this.d = new HandlerThread("ChoreographerOwner:Handler");
            this.d.start();
            this.c = new Handler(this.d.getLooper(), ((Handler$Callback)this));
            this.c.sendEmptyMessage(0);
        }

        public static a a() {
            return a.b;
        }

        public void b() {
            this.c.sendEmptyMessage(1);
        }

        public void c() {
            this.c.sendEmptyMessage(2);
        }

        private void d() {
            this.e = Choreographer.getInstance();
        }

        public void doFrame(long arg3) {
            this.a = arg3;
            this.e.postFrameCallbackDelayed(((Choreographer$FrameCallback)this), 500);
        }

        private void e() {
            ++this.f;
            if(this.f == 1) {
                this.e.postFrameCallback(((Choreographer$FrameCallback)this));
            }
        }

        private void f() {
            --this.f;
            if(this.f == 0) {
                this.e.removeFrameCallback(((Choreographer$FrameCallback)this));
                this.a = 0;
            }
        }

        public boolean handleMessage(Message arg2) {
            switch(arg2.what) {
                case 0: {
                    goto label_9;
                }
                case 1: {
                    goto label_7;
                }
                case 2: {
                    goto label_5;
                }
            }

            return 0;
        label_5:
            this.f();
            return 1;
        label_7:
            this.e();
            return 1;
        label_9:
            this.d();
            return 1;
        }
    }

    private final a a;
    private final boolean b;
    private final long c;
    private final long d;
    private long e;
    private long f;
    private long g;
    private boolean h;
    private long i;
    private long j;
    private long k;

    public br() {
        this(-1f, false);
    }

    private br(float arg3, boolean arg4) {
        long v3_1;
        super();
        this.b = arg4;
        if(arg4) {
            this.a = a.a();
            double v3 = ((double)arg3);
            Double.isNaN(v3);
            this.c = ((long)(1000000000 / v3));
            v3_1 = this.c * 80 / 100;
        }
        else {
            this.a = null;
            v3_1 = -1;
            this.c = v3_1;
        }

        this.d = v3_1;
    }

    public br(Context arg2) {
        this(br.a(arg2), true);
    }

    private static float a(Context arg1) {
        return arg1.getSystemService("window").getDefaultDisplay().getRefreshRate();
    }

    private static long a(long arg5, long arg7, long arg9) {
        arg7 += (arg5 - arg7) / arg9 * arg9;
        if(arg5 <= arg7) {
            arg9 = arg7 - arg9;
        }
        else {
            long v3 = arg7;
            arg7 = arg9 + arg7;
            arg9 = v3;
        }

        if(arg7 - arg5 < arg5 - arg9) {
        }
        else {
            arg7 = arg9;
        }

        return arg7;
    }

    public long a(long arg19, long arg21) {
        // Method was not decompiled
    }

    public void a() {
        this.h = false;
        if(this.b) {
            this.a.b();
        }
    }

    private boolean b(long arg3, long arg5) {
        boolean v3 = Math.abs(arg5 - this.i - (arg3 - this.j)) > 20000000 ? true : false;
        return v3;
    }

    public void b() {
        if(this.b) {
            this.a.c();
        }
    }

    protected void c() {
    }
}

