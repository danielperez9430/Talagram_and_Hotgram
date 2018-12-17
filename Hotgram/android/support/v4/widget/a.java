package android.support.v4.widget;

import android.content.res.Resources;
import android.os.SystemClock;
import android.support.v4.view.t;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View$OnTouchListener;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;

public abstract class a implements View$OnTouchListener {
    class android.support.v4.widget.a$a {
        private int a;
        private int b;
        private float c;
        private float d;
        private long e;
        private long f;
        private int g;
        private int h;
        private long i;
        private float j;
        private int k;

        android.support.v4.widget.a$a() {
            super();
            this.e = -9223372036854775808L;
            this.i = -1;
            this.f = 0;
            this.g = 0;
            this.h = 0;
        }

        public void a(int arg1) {
            this.a = arg1;
        }

        public void a(float arg1, float arg2) {
            this.c = arg1;
            this.d = arg2;
        }

        private float a(float arg3) {
            return -4f * arg3 * arg3 + arg3 * 4f;
        }

        private float a(long arg7) {
            // Method was not decompiled
        }

        public void a() {
            this.e = AnimationUtils.currentAnimationTimeMillis();
            this.i = -1;
            this.f = this.e;
            this.j = 0.5f;
            this.g = 0;
            this.h = 0;
        }

        public void b() {
            long v0 = AnimationUtils.currentAnimationTimeMillis();
            this.k = a.a(((int)(v0 - this.e)), 0, this.b);
            this.j = this.a(v0);
            this.i = v0;
        }

        public void b(int arg1) {
            this.b = arg1;
        }

        public boolean c() {
            boolean v0 = this.i <= 0 || AnimationUtils.currentAnimationTimeMillis() <= this.i + (((long)this.k)) ? false : true;
            return v0;
        }

        public void d() {
            if(this.f != 0) {
                long v0 = AnimationUtils.currentAnimationTimeMillis();
                float v2 = this.a(this.a(v0));
                long v3 = v0 - this.f;
                this.f = v0;
                float v0_1 = (((float)v3)) * v2;
                this.g = ((int)(this.c * v0_1));
                this.h = ((int)(v0_1 * this.d));
                return;
            }

            throw new RuntimeException("Cannot compute scroll delta before calling start()");
        }

        public int e() {
            return ((int)(this.c / Math.abs(this.c)));
        }

        public int f() {
            return ((int)(this.d / Math.abs(this.d)));
        }

        public int g() {
            return this.g;
        }

        public int h() {
            return this.h;
        }
    }

    class b implements Runnable {
        b(a arg1) {
            this.a = arg1;
            super();
        }

        public void run() {
            if(!this.a.e) {
                return;
            }

            if(this.a.c) {
                this.a.c = false;
                this.a.a.a();
            }

            android.support.v4.widget.a$a v0 = this.a.a;
            if(!v0.c()) {
                if(!this.a.a()) {
                }
                else {
                    if(this.a.d) {
                        this.a.d = false;
                        this.a.b();
                    }

                    v0.d();
                    this.a.a(v0.g(), v0.h());
                    t.a(this.a.b, ((Runnable)this));
                    return;
                }
            }

            this.a.e = false;
        }
    }

    final android.support.v4.widget.a$a a;
    final View b;
    boolean c;
    boolean d;
    boolean e;
    private final Interpolator f;
    private Runnable g;
    private float[] h;
    private float[] i;
    private int j;
    private int k;
    private float[] l;
    private float[] m;
    private float[] n;
    private boolean o;
    private boolean p;
    private boolean q;
    private static final int r;

    static {
        a.r = ViewConfiguration.getTapTimeout();
    }

    public a(View arg4) {
        super();
        this.a = new android.support.v4.widget.a$a();
        this.f = new AccelerateInterpolator();
        this.h = new float[]{0f, 0f};
        this.i = new float[]{340282346638528860000000000000000000000f, 340282346638528860000000000000000000000f};
        this.l = new float[]{0f, 0f};
        this.m = new float[]{0f, 0f};
        this.n = new float[]{340282346638528860000000000000000000000f, 340282346638528860000000000000000000000f};
        this.b = arg4;
        DisplayMetrics v4 = Resources.getSystem().getDisplayMetrics();
        int v0 = ((int)(v4.density * 1575f + 0.5f));
        int v4_1 = ((int)(v4.density * 315f + 0.5f));
        float v0_1 = ((float)v0);
        this.a(v0_1, v0_1);
        float v4_2 = ((float)v4_1);
        this.b(v4_2, v4_2);
        this.a(1);
        this.e(340282346638528860000000000000000000000f, 340282346638528860000000000000000000000f);
        this.d(0.2f, 0.2f);
        this.c(1f, 1f);
        this.b(a.r);
        this.c(500);
        this.d(500);
    }

    public a a(float arg4, float arg5) {
        this.n[0] = arg4 / 1000f;
        this.n[1] = arg5 / 1000f;
        return this;
    }

    public a a(int arg1) {
        this.j = arg1;
        return this;
    }

    static float a(float arg1, float arg2, float arg3) {
        if(arg1 > arg3) {
            return arg3;
        }

        if(arg1 < arg2) {
            return arg2;
        }

        return arg1;
    }

    private float a(float arg2, float arg3, float arg4, float arg5) {
        arg2 = a.a(arg2 * arg3, 0f, arg4);
        arg2 = this.f(arg3 - arg5, arg2) - this.f(arg5, arg2);
        if(arg2 < 0f) {
            arg2 = -this.f.getInterpolation(-arg2);
        }
        else if(arg2 > 0f) {
            arg2 = this.f.getInterpolation(arg2);
        }
        else {
            return 0;
        }

        return a.a(arg2, -1f, 1f);
    }

    private float a(int arg4, float arg5, float arg6, float arg7) {
        arg5 = this.a(this.h[arg4], arg6, this.i[arg4], arg5);
        if(arg5 == 0f) {
            return 0;
        }

        float v0 = this.l[arg4];
        float v1 = this.m[arg4];
        float v4 = this.n[arg4];
        v0 *= arg7;
        if(arg5 > 0f) {
            return a.a(arg5 * v0, v1, v4);
        }

        return -a.a(-arg5 * v0, v1, v4);
    }

    static int a(int arg0, int arg1, int arg2) {
        if(arg0 > arg2) {
            return arg2;
        }

        if(arg0 < arg1) {
            return arg1;
        }

        return arg0;
    }

    public a a(boolean arg2) {
        if((this.p) && !arg2) {
            this.d();
        }

        this.p = arg2;
        return this;
    }

    public abstract void a(int arg1, int arg2);

    boolean a() {
        boolean v0_2;
        android.support.v4.widget.a$a v0 = this.a;
        int v1 = v0.f();
        int v0_1 = v0.e();
        if(v1 == 0 || !this.f(v1)) {
            if(v0_1 != 0 && (this.e(v0_1))) {
            label_9:
                v0_2 = true;
                return v0_2;
            }

            v0_2 = false;
        }
        else {
            goto label_9;
        }

        return v0_2;
    }

    public a b(float arg4, float arg5) {
        this.m[0] = arg4 / 1000f;
        this.m[1] = arg5 / 1000f;
        return this;
    }

    public a b(int arg1) {
        this.k = arg1;
        return this;
    }

    void b() {
        long v2 = SystemClock.uptimeMillis();
        MotionEvent v0 = MotionEvent.obtain(v2, v2, 3, 0f, 0f, 0);
        this.b.onTouchEvent(v0);
        v0.recycle();
    }

    public a c(float arg4, float arg5) {
        this.l[0] = arg4 / 1000f;
        this.l[1] = arg5 / 1000f;
        return this;
    }

    public a c(int arg2) {
        this.a.a(arg2);
        return this;
    }

    private void c() {
        if(this.g == null) {
            this.g = new b(this);
        }

        this.e = true;
        this.c = true;
        if((this.o) || this.k <= 0) {
            this.g.run();
        }
        else {
            t.a(this.b, this.g, ((long)this.k));
        }

        this.o = true;
    }

    public a d(float arg3, float arg4) {
        this.h[0] = arg3;
        this.h[1] = arg4;
        return this;
    }

    public a d(int arg2) {
        this.a.b(arg2);
        return this;
    }

    private void d() {
        if(this.c) {
            this.e = false;
        }
        else {
            this.a.b();
        }
    }

    public a e(float arg3, float arg4) {
        this.i[0] = arg3;
        this.i[1] = arg4;
        return this;
    }

    public abstract boolean e(int arg1);

    private float f(float arg4, float arg5) {
        // Method was not decompiled
    }

    public abstract boolean f(int arg1);

    public boolean onTouch(View arg6, MotionEvent arg7) {
        boolean v1 = false;
        if(!this.p) {
            return 0;
        }

        switch(arg7.getActionMasked()) {
            case 0: {
                this.d = true;
                this.o = false;
                goto label_12;
            }
            case 2: {
            label_12:
                this.a.a(this.a(0, arg7.getX(), ((float)arg6.getWidth()), ((float)this.b.getWidth())), this.a(1, arg7.getY(), ((float)arg6.getHeight()), ((float)this.b.getHeight())));
                if(this.e) {
                    goto label_33;
                }

                if(!this.a()) {
                    goto label_33;
                }

                this.c();
                break;
            }
            case 1: 
            case 3: {
                this.d();
                break;
            }
            default: {
                break;
            }
        }

    label_33:
        if((this.q) && (this.e)) {
            v1 = true;
        }

        return v1;
    }
}

