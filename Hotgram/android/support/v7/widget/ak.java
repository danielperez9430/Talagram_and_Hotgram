package android.support.v7.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator$AnimatorUpdateListener;
import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.view.MotionEvent;

class ak extends h implements m {
    class android.support.v7.widget.ak$1 implements Runnable {
        android.support.v7.widget.ak$1(ak arg1) {
            this.a = arg1;
            super();
        }

        public void run() {
            this.a.b(500);
        }
    }

    class android.support.v7.widget.ak$2 extends n {
        android.support.v7.widget.ak$2(ak arg1) {
            this.a = arg1;
            super();
        }

        public void a(RecyclerView arg1, int arg2, int arg3) {
            this.a.a(arg1.computeHorizontalScrollOffset(), arg1.computeVerticalScrollOffset());
        }
    }

    class a extends AnimatorListenerAdapter {
        private boolean b;

        a(ak arg1) {
            this.a = arg1;
            super();
            this.b = false;
        }

        public void onAnimationCancel(Animator arg1) {
            this.b = true;
        }

        public void onAnimationEnd(Animator arg3) {
            if(this.b) {
                this.b = false;
                return;
            }

            if(this.a.i.getAnimatedValue().floatValue() == 0f) {
                this.a.j = 0;
                this.a.a(0);
            }
            else {
                this.a.j = 2;
                this.a.a();
            }
        }
    }

    class b implements ValueAnimator$AnimatorUpdateListener {
        b(ak arg1) {
            this.a = arg1;
            super();
        }

        public void onAnimationUpdate(ValueAnimator arg2) {
            int v2 = ((int)(arg2.getAnimatedValue().floatValue() * 255f));
            this.a.a.setAlpha(v2);
            this.a.b.setAlpha(v2);
            this.a.a();
        }
    }

    private int A;
    private final int[] B;
    private final int[] C;
    private final Runnable D;
    private final n E;
    final StateListDrawable a;
    final Drawable b;
    int c;
    int d;
    float e;
    int f;
    int g;
    float h;
    final ValueAnimator i;
    int j;
    private static final int[] k;
    private static final int[] l;
    private final int m;
    private final int n;
    private final int o;
    private final int p;
    private final StateListDrawable q;
    private final Drawable r;
    private final int s;
    private final int t;
    private int u;
    private int v;
    private RecyclerView w;
    private boolean x;
    private boolean y;
    private int z;

    static {
        ak.k = new int[]{16842919};
        ak.l = new int[0];
    }

    ak(RecyclerView arg4, StateListDrawable arg5, Drawable arg6, StateListDrawable arg7, Drawable arg8, int arg9, int arg10, int arg11) {
        super();
        this.u = 0;
        this.v = 0;
        this.x = false;
        this.y = false;
        this.z = 0;
        this.A = 0;
        this.B = new int[2];
        this.C = new int[2];
        this.i = ValueAnimator.ofFloat(new float[]{0f, 1f});
        this.j = 0;
        this.D = new android.support.v7.widget.ak$1(this);
        this.E = new android.support.v7.widget.ak$2(this);
        this.a = arg5;
        this.b = arg6;
        this.q = arg7;
        this.r = arg8;
        this.o = Math.max(arg9, arg5.getIntrinsicWidth());
        this.p = Math.max(arg9, arg6.getIntrinsicWidth());
        this.s = Math.max(arg9, arg7.getIntrinsicWidth());
        this.t = Math.max(arg9, arg8.getIntrinsicWidth());
        this.m = arg10;
        this.n = arg11;
        this.a.setAlpha(255);
        this.b.setAlpha(255);
        this.i.addListener(new a(this));
        this.i.addUpdateListener(new b(this));
        this.a(arg4);
    }

    public void a(RecyclerView arg2) {
        if(this.w == arg2) {
            return;
        }

        if(this.w != null) {
            this.d();
        }

        this.w = arg2;
        if(this.w != null) {
            this.c();
        }
    }

    private int a(float arg3, float arg4, int[] arg5, int arg6, int arg7, int arg8) {
        int v0 = arg5[1] - arg5[0];
        if(v0 == 0) {
            return 0;
        }

        arg6 -= arg8;
        int v3 = ((int)((arg4 - arg3) / (((float)v0)) * (((float)arg6))));
        arg7 += v3;
        if(arg7 < arg6 && arg7 >= 0) {
            return v3;
        }

        return 0;
    }

    private void a(float arg9) {
        int[] v3 = this.g();
        arg9 = Math.max(((float)v3[0]), Math.min(((float)v3[1]), arg9));
        if(Math.abs((((float)this.d)) - arg9) < 2f) {
            return;
        }

        int v0 = this.a(this.e, arg9, v3, this.w.computeVerticalScrollRange(), this.w.computeVerticalScrollOffset(), this.v);
        if(v0 != 0) {
            this.w.scrollBy(0, v0);
        }

        this.e = arg9;
    }

    private void a(Canvas arg7) {
        int v0 = this.u - this.o;
        int v1 = this.d - this.c / 2;
        this.a.setBounds(0, 0, this.o, this.c);
        this.b.setBounds(0, 0, this.p, this.v);
        if(this.e()) {
            this.b.draw(arg7);
            arg7.translate(((float)this.o), ((float)v1));
            arg7.scale(-1f, 1f);
            this.a.draw(arg7);
            arg7.scale(1f, 1f);
            v0 = this.o;
        }
        else {
            arg7.translate(((float)v0), 0f);
            this.b.draw(arg7);
            arg7.translate(0f, ((float)v1));
            this.a.draw(arg7);
        }

        arg7.translate(((float)(-v0)), ((float)(-v1)));
    }

    void a() {
        this.w.invalidate();
    }

    void a(int arg4) {
        int v0 = 2;
        if(arg4 == v0 && this.z != v0) {
            this.a.setState(ak.k);
            this.f();
        }

        if(arg4 == 0) {
            this.a();
        }
        else {
            this.b();
        }

        if(this.z == v0 && arg4 != v0) {
            this.a.setState(ak.l);
            v0 = 1200;
            goto label_19;
        }
        else if(arg4 == 1) {
            v0 = 1500;
        label_19:
            this.c(v0);
        }

        this.z = arg4;
    }

    void a(int arg9, int arg10) {
        int v0 = this.w.computeVerticalScrollRange();
        int v1 = this.v;
        boolean v2 = v0 - v1 <= 0 || this.v < this.m ? false : true;
        this.x = v2;
        int v2_1 = this.w.computeHorizontalScrollRange();
        int v5 = this.u;
        boolean v6 = v2_1 - v5 <= 0 || this.u < this.m ? false : true;
        this.y = v6;
        if(!this.x && !this.y) {
            if(this.z != 0) {
                this.a(0);
            }

            return;
        }

        float v6_1 = 2f;
        if(this.x) {
            float v3 = ((float)v1);
            this.d = ((int)(v3 * ((((float)arg10)) + v3 / v6_1) / (((float)v0))));
            this.c = Math.min(v1, v1 * v1 / v0);
        }

        if(this.y) {
            float v10 = ((float)v5);
            this.g = ((int)(v10 * ((((float)arg9)) + v10 / v6_1) / (((float)v2_1))));
            this.f = Math.min(v5, v5 * v5 / v2_1);
        }

        if(this.z == 0 || this.z == 1) {
            this.a(1);
        }
    }

    public void a(Canvas arg1, RecyclerView arg2, t arg3) {
        if(this.u == this.w.getWidth()) {
            if(this.v != this.w.getHeight()) {
            }
            else {
                if(this.j != 0) {
                    if(this.x) {
                        this.a(arg1);
                    }

                    if(!this.y) {
                        return;
                    }

                    this.b(arg1);
                }

                return;
            }
        }

        this.u = this.w.getWidth();
        this.v = this.w.getHeight();
        this.a(0);
    }

    public void a(boolean arg1) {
    }

    boolean a(float arg3, float arg4) {
        boolean v3;
        if(this.e()) {
            if(arg3 <= (((float)(this.o / 2)))) {
                goto label_12;
            }
            else {
                goto label_26;
            }
        }
        else if(arg3 >= (((float)(this.u - this.o)))) {
        label_12:
            if(arg4 < (((float)(this.d - this.c / 2)))) {
                goto label_26;
            }
            else if(arg4 <= (((float)(this.d + this.c / 2)))) {
                v3 = true;
            }
            else {
                goto label_26;
            }
        }
        else {
        label_26:
            v3 = false;
        }

        return v3;
    }

    public boolean a(RecyclerView arg6, MotionEvent arg7) {
        boolean v0 = false;
        int v1 = 2;
        if(this.z == 1) {
            boolean v6 = this.a(arg7.getX(), arg7.getY());
            boolean v3 = this.b(arg7.getX(), arg7.getY());
            if(arg7.getAction() == 0) {
                if(!v6 && !v3) {
                    return v0;
                }

                if(v3) {
                    this.A = 1;
                    this.h = ((float)(((int)arg7.getX())));
                }
                else if(v6) {
                    this.A = v1;
                    this.e = ((float)(((int)arg7.getY())));
                }

                this.a(v1);
                goto label_32;
            }
        }
        else {
            if(this.z != v1) {
                return v0;
            }

        label_32:
            v0 = true;
        }

        return v0;
    }

    private void b(float arg9) {
        int[] v3 = this.h();
        arg9 = Math.max(((float)v3[0]), Math.min(((float)v3[1]), arg9));
        if(Math.abs((((float)this.g)) - arg9) < 2f) {
            return;
        }

        int v0 = this.a(this.h, arg9, v3, this.w.computeHorizontalScrollRange(), this.w.computeHorizontalScrollOffset(), this.u);
        if(v0 != 0) {
            this.w.scrollBy(v0, 0);
        }

        this.h = arg9;
    }

    private void b(Canvas arg7) {
        int v0 = this.v - this.s;
        int v1 = this.g - this.f / 2;
        this.q.setBounds(0, 0, this.f, this.s);
        this.r.setBounds(0, 0, this.u, this.t);
        arg7.translate(0f, ((float)v0));
        this.r.draw(arg7);
        arg7.translate(((float)v1), 0f);
        this.q.draw(arg7);
        arg7.translate(((float)(-v1)), ((float)(-v0)));
    }

    public void b() {
        int v0 = this.j;
        if(v0 == 0) {
        label_7:
            this.j = 1;
            this.i.setFloatValues(new float[]{this.i.getAnimatedValue().floatValue(), 1f});
            this.i.setDuration(500);
            this.i.setStartDelay(0);
            this.i.start();
        }
        else if(v0 != 3) {
        }
        else {
            this.i.cancel();
            goto label_7;
        }
    }

    boolean b(float arg3, float arg4) {
        boolean v3 = arg4 < (((float)(this.v - this.s))) || arg3 < (((float)(this.g - this.f / 2))) || arg3 > (((float)(this.g + this.f / 2))) ? false : true;
        return v3;
    }

    void b(int arg5) {
        switch(this.j) {
            case 1: {
                goto label_3;
            }
            case 2: {
                goto label_5;
            }
        }

        return;
    label_3:
        this.i.cancel();
    label_5:
        this.j = 3;
        this.i.setFloatValues(new float[]{this.i.getAnimatedValue().floatValue(), 0f});
        this.i.setDuration(((long)arg5));
        this.i.start();
    }

    public void b(RecyclerView arg5, MotionEvent arg6) {
        if(this.z == 0) {
            return;
        }

        int v1 = 2;
        if(arg6.getAction() == 0) {
            boolean v5 = this.a(arg6.getX(), arg6.getY());
            boolean v2 = this.b(arg6.getX(), arg6.getY());
            if(!v5 && !v2) {
                return;
            }

            if(v2) {
                this.A = 1;
                this.h = ((float)(((int)arg6.getX())));
            }
            else if(v5) {
                this.A = v1;
                this.e = ((float)(((int)arg6.getY())));
            }

            this.a(v1);
        }
        else {
            if(arg6.getAction() == 1 && this.z == v1) {
                this.e = 0f;
                this.h = 0f;
                this.a(1);
                this.A = 0;
                return;
            }

            if(arg6.getAction() != v1) {
                return;
            }

            if(this.z != v1) {
                return;
            }

            this.b();
            if(this.A == 1) {
                this.b(arg6.getX());
            }

            if(this.A != v1) {
                return;
            }

            this.a(arg6.getY());
        }
    }

    private void c() {
        this.w.a(((h)this));
        this.w.a(((m)this));
        this.w.a(this.E);
    }

    private void c(int arg5) {
        this.f();
        this.w.postDelayed(this.D, ((long)arg5));
    }

    private void d() {
        this.w.b(((h)this));
        this.w.b(((m)this));
        this.w.b(this.E);
        this.f();
    }

    private boolean e() {
        boolean v1 = true;
        if(android.support.v4.view.t.f(this.w) == 1) {
        }
        else {
            v1 = false;
        }

        return v1;
    }

    private void f() {
        this.w.removeCallbacks(this.D);
    }

    private int[] g() {
        this.B[0] = this.n;
        this.B[1] = this.v - this.n;
        return this.B;
    }

    private int[] h() {
        this.C[0] = this.n;
        this.C[1] = this.u - this.n;
        return this.C;
    }
}

