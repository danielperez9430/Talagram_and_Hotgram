package android.support.v4.widget;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.OverScroller;
import java.util.Arrays;

public class t {
    final class android.support.v4.widget.t$1 implements Interpolator {
        android.support.v4.widget.t$1() {
            super();
        }

        public float getInterpolation(float arg3) {
            --arg3;
            return arg3 * arg3 * arg3 * arg3 * arg3 + 1f;
        }
    }

    class android.support.v4.widget.t$2 implements Runnable {
        android.support.v4.widget.t$2(t arg1) {
            this.a = arg1;
            super();
        }

        public void run() {
            this.a.b(0);
        }
    }

    public abstract class a {
        public a() {
            super();
        }

        public int a(View arg1) {
            return 0;
        }

        public void a(View arg1, float arg2, float arg3) {
        }

        public int a(View arg1, int arg2, int arg3) {
            return 0;
        }

        public void a(View arg1, int arg2, int arg3, int arg4, int arg5) {
        }

        public void a(int arg1, int arg2) {
        }

        public void a(int arg1) {
        }

        public abstract boolean a(View arg1, int arg2);

        public int b(View arg1) {
            return 0;
        }

        public boolean b(int arg1) {
            return 0;
        }

        public void b(int arg1, int arg2) {
        }

        public int b(View arg1, int arg2, int arg3) {
            return 0;
        }

        public void b(View arg1, int arg2) {
        }

        public int c(int arg1) {
            return arg1;
        }
    }

    private int a;
    private int b;
    private int c;
    private float[] d;
    private float[] e;
    private float[] f;
    private float[] g;
    private int[] h;
    private int[] i;
    private int[] j;
    private int k;
    private VelocityTracker l;
    private float m;
    private float n;
    private int o;
    private int p;
    private OverScroller q;
    private final a r;
    private View s;
    private boolean t;
    private final ViewGroup u;
    private static final Interpolator v;
    private final Runnable w;

    static {
        t.v = new android.support.v4.widget.t$1();
    }

    private t(Context arg2, ViewGroup arg3, a arg4) {
        super();
        this.c = -1;
        this.w = new android.support.v4.widget.t$2(this);
        if(arg3 != null) {
            if(arg4 != null) {
                this.u = arg3;
                this.r = arg4;
                ViewConfiguration v3 = ViewConfiguration.get(arg2);
                this.o = ((int)(arg2.getResources().getDisplayMetrics().density * 20f + 0.5f));
                this.b = v3.getScaledTouchSlop();
                this.m = ((float)v3.getScaledMaximumFlingVelocity());
                this.n = ((float)v3.getScaledMinimumFlingVelocity());
                this.q = new OverScroller(arg2, t.v);
                return;
            }

            throw new IllegalArgumentException("Callback may not be null");
        }

        throw new IllegalArgumentException("Parent view may not be null");
    }

    private float a(float arg3) {
        return ((float)Math.sin(((double)((arg3 - 0.5f) * 0.471239f))));
    }

    private float a(float arg3, float arg4, float arg5) {
        float v0 = Math.abs(arg3);
        if(Float.compare(v0, arg4) < 0) {
            return 0;
        }

        if(v0 > arg5) {
            if(arg3 > 0f) {
            }
            else {
                arg5 = -arg5;
            }

            return arg5;
        }

        return arg3;
    }

    private int a(int arg4, int arg5, int arg6) {
        if(arg4 == 0) {
            return 0;
        }

        int v0 = this.u.getWidth();
        int v1 = v0 / 2;
        float v2 = (((float)Math.abs(arg4))) / (((float)v0));
        float v0_1 = 1f;
        float v1_1 = ((float)v1);
        v1_1 += this.a(Math.min(v0_1, v2)) * v1_1;
        arg5 = Math.abs(arg5);
        arg4 = arg5 > 0 ? Math.round(Math.abs(v1_1 / (((float)arg5))) * 1000f) * 4 : ((int)(((((float)Math.abs(arg4))) / (((float)arg6)) + v0_1) * 256f));
        return Math.min(arg4, 600);
    }

    private int a(View arg7, int arg8, int arg9, int arg10, int arg11) {
        float v1_1;
        float v2_1;
        float v0_1;
        arg10 = this.b(arg10, ((int)this.n), ((int)this.m));
        arg11 = this.b(arg11, ((int)this.n), ((int)this.m));
        int v0 = Math.abs(arg8);
        int v1 = Math.abs(arg9);
        int v2 = Math.abs(arg10);
        int v3 = Math.abs(arg11);
        int v4 = v2 + v3;
        int v5 = v0 + v1;
        if(arg10 != 0) {
            v0_1 = ((float)v2);
            v2_1 = ((float)v4);
        }
        else {
            v0_1 = ((float)v0);
            v2_1 = ((float)v5);
        }

        v0_1 /= v2_1;
        if(arg11 != 0) {
            v1_1 = ((float)v3);
            v2_1 = ((float)v4);
        }
        else {
            v1_1 = ((float)v1);
            v2_1 = ((float)v5);
        }

        v1_1 /= v2_1;
        return ((int)((((float)this.a(arg8, arg10, this.r.b(arg7)))) * v0_1 + (((float)this.a(arg9, arg11, this.r.a(arg7)))) * v1_1));
    }

    public static t a(ViewGroup arg1, float arg2, a arg3) {
        t v1 = t.a(arg1, arg3);
        v1.b = ((int)((((float)v1.b)) * (1f / arg2)));
        return v1;
    }

    public static t a(ViewGroup arg2, a arg3) {
        return new t(arg2.getContext(), arg2, arg3);
    }

    private void a(float arg4, float arg5) {
        this.t = true;
        this.r.a(this.s, arg4, arg5);
        this.t = false;
        if(this.a == 1) {
            this.b(0);
        }
    }

    private void a(float arg3, float arg4, int arg5) {
        this.d(arg5);
        float[] v0 = this.d;
        this.f[arg5] = arg3;
        v0[arg5] = arg3;
        v0 = this.e;
        this.g[arg5] = arg4;
        v0[arg5] = arg4;
        this.h[arg5] = this.d(((int)arg3), ((int)arg4));
        this.k |= 1 << arg5;
    }

    private boolean a(float arg3, float arg4, int arg5, int arg6) {
        arg3 = Math.abs(arg3);
        arg4 = Math.abs(arg4);
        boolean v1 = false;
        if((this.h[arg5] & arg6) == arg6 && (this.p & arg6) != 0 && (this.j[arg5] & arg6) != arg6 && (this.i[arg5] & arg6) != arg6 && (arg3 > (((float)this.b)) || arg4 > (((float)this.b)))) {
            if(arg3 < arg4 * 0.5f && (this.r.b(arg6))) {
                this.j[arg5] |= arg6;
                return 0;
            }

            if((this.i[arg5] & arg6) != 0) {
                return v1;
            }

            if(arg3 <= (((float)this.b))) {
                return v1;
            }

            v1 = true;
        }

        return v1;
    }

    private boolean a(int arg11, int arg12, int arg13, int arg14) {
        int v2 = this.s.getLeft();
        int v3 = this.s.getTop();
        arg11 -= v2;
        arg12 -= v3;
        if(arg11 == 0 && arg12 == 0) {
            this.q.abortAnimation();
            this.b(0);
            return 0;
        }

        this.q.startScroll(v2, v3, arg11, arg12, this.a(this.s, arg11, arg12, arg13, arg14));
        this.b(2);
        return 1;
    }

    private boolean a(View arg5, float arg6, float arg7) {
        boolean v0 = false;
        if(arg5 == null) {
            return 0;
        }

        int v1 = this.r.b(arg5) > 0 ? 1 : 0;
        int v5 = this.r.a(arg5) > 0 ? 1 : 0;
        if(v1 != 0 && v5 != 0) {
            if(arg6 * arg6 + arg7 * arg7 > (((float)(this.b * this.b)))) {
                v0 = true;
            }

            return v0;
        }

        if(v1 != 0) {
            if(Math.abs(arg6) > (((float)this.b))) {
                v0 = true;
            }

            return v0;
        }

        if(v5 != 0 && Math.abs(arg7) > (((float)this.b))) {
            v0 = true;
        }

        return v0;
    }

    public boolean a(int arg3) {
        boolean v1 = true;
        if((1 << arg3 & this.k) != 0) {
        }
        else {
            v1 = false;
        }

        return v1;
    }

    public int a() {
        return this.b;
    }

    public void a(View arg3, int arg4) {
        if(arg3.getParent() == this.u) {
            this.s = arg3;
            this.c = arg4;
            this.r.b(arg3, arg4);
            this.b(1);
            return;
        }

        StringBuilder v4 = new StringBuilder();
        v4.append("captureChildView: parameter must be a descendant of the ViewDragHelper\'s tracked parent view (");
        v4.append(this.u);
        v4.append(")");
        throw new IllegalArgumentException(v4.toString());
    }

    public boolean a(int arg4, int arg5) {
        if(this.t) {
            return this.a(arg4, arg5, ((int)this.l.getXVelocity(this.c)), ((int)this.l.getYVelocity(this.c)));
        }

        throw new IllegalStateException("Cannot settleCapturedViewAt outside of a call to Callback#onViewReleased");
    }

    public boolean a(MotionEvent arg17) {
        int v1_2;
        float v7;
        t v0 = this;
        MotionEvent v1 = arg17;
        int v2 = arg17.getActionMasked();
        int v3 = arg17.getActionIndex();
        if(v2 == 0) {
            this.b();
        }

        if(v0.l == null) {
            v0.l = VelocityTracker.obtain();
        }

        v0.l.addMovement(v1);
        int v4 = 2;
        switch(v2) {
            case 0: {
                goto label_110;
            }
            case 2: {
                if(v0.d == null) {
                    goto label_15;
                }

                if(v0.e == null) {
                    goto label_15;
                }

                v2 = arg17.getPointerCount();
                for(v3 = 0; v3 < v2; ++v3) {
                    v4 = v1.getPointerId(v3);
                    if(!v0.e(v4)) {
                    }
                    else {
                        v7 = v1.getX(v3);
                        float v8 = v1.getY(v3);
                        float v9 = v7 - v0.d[v4];
                        float v10 = v8 - v0.e[v4];
                        View v7_1 = v0.c(((int)v7), ((int)v8));
                        int v8_1 = v7_1 == null || !v0.a(v7_1, v9, v10) ? 0 : 1;
                        if(v8_1 != 0) {
                            int v11 = v7_1.getLeft();
                            int v12 = ((int)v9);
                            v12 = v0.r.b(v7_1, v11 + v12, v12);
                            int v13 = v7_1.getTop();
                            int v14 = ((int)v10);
                            int v5 = v0.r.a(v7_1, v13 + v14, v14);
                            v14 = v0.r.b(v7_1);
                            int v15 = v0.r.a(v7_1);
                            if(v14 != 0) {
                                if(v14 <= 0) {
                                }
                                else if(v12 == v11) {
                                    goto label_92;
                                }

                                goto label_96;
                            }

                        label_92:
                            if(v15 == 0) {
                                break;
                            }

                            if(v15 <= 0) {
                                goto label_96;
                            }

                            if(v5 != v13) {
                                goto label_96;
                            }

                            break;
                        }

                    label_96:
                        v0.b(v9, v10, v4);
                        if(v0.a == 1) {
                            break;
                        }

                        if(v8_1 == 0) {
                            goto label_104;
                        }

                        if(!v0.b(v7_1, v4)) {
                            goto label_104;
                        }

                        break;
                    }

                label_104:
                }

                this.c(arg17);
                break;
            }
            case 1: 
            case 3: {
                this.b();
                break;
            }
            case 5: {
                v2 = v1.getPointerId(v3);
                v7 = v1.getX(v3);
                float v1_1 = v1.getY(v3);
                v0.a(v7, v1_1, v2);
                if(v0.a == 0) {
                    v1_2 = v0.h[v2];
                    if((v0.p & v1_2) == 0) {
                        goto label_15;
                    }

                    v0.r.a(v1_2 & v0.p, v2);
                    goto label_15;
                }

                if(v0.a != v4) {
                    goto label_15;
                }

                View v1_3 = v0.c(((int)v7), ((int)v1_1));
                if(v1_3 != v0.s) {
                    goto label_15;
                }

                v0.b(v1_3, v2);
                break;
            }
            case 6: {
                v0.c(v1.getPointerId(v3));
                break;
            }
        }

    label_15:
        boolean v5_1 = false;
        goto label_132;
    label_110:
        float v2_1 = arg17.getX();
        float v3_1 = arg17.getY();
        v5_1 = false;
        v1_2 = v1.getPointerId(0);
        v0.a(v2_1, v3_1, v1_2);
        View v2_2 = v0.c(((int)v2_1), ((int)v3_1));
        if(v2_2 == v0.s && v0.a == v4) {
            v0.b(v2_2, v1_2);
        }

        v2 = v0.h[v1_2];
        if((v0.p & v2) != 0) {
            v0.r.a(v2 & v0.p, v1_2);
        }

    label_132:
        if(v0.a == 1) {
            v5_1 = true;
        }

        return v5_1;
    }

    public boolean a(View arg1, int arg2, int arg3) {
        this.s = arg1;
        this.c = -1;
        boolean v1 = this.a(arg2, arg3, 0, 0);
        if(!v1 && this.a == 0 && this.s != null) {
            this.s = null;
        }

        return v1;
    }

    public boolean a(boolean arg12) {
        int v1 = 2;
        boolean v2 = false;
        if(this.a == v1) {
            boolean v0 = this.q.computeScrollOffset();
            int v3 = this.q.getCurrX();
            int v10 = this.q.getCurrY();
            int v8 = v3 - this.s.getLeft();
            int v9 = v10 - this.s.getTop();
            if(v8 != 0) {
                android.support.v4.view.t.f(this.s, v8);
            }

            if(v9 != 0) {
                android.support.v4.view.t.e(this.s, v9);
            }

            if(v8 != 0 || v9 != 0) {
                this.r.a(this.s, v3, v10, v8, v9);
            }

            if((v0) && v3 == this.q.getFinalX() && v10 == this.q.getFinalY()) {
                this.q.abortAnimation();
                v0 = false;
            }

            if(v0) {
                goto label_46;
            }

            if(arg12) {
                this.u.post(this.w);
                goto label_46;
            }

            this.b(0);
        }

    label_46:
        if(this.a == v1) {
            v2 = true;
        }

        return v2;
    }

    private int b(int arg2, int arg3, int arg4) {
        int v0 = Math.abs(arg2);
        if(v0 < arg3) {
            return 0;
        }

        if(v0 > arg4) {
            if(arg2 > 0) {
            }
            else {
                arg4 = -arg4;
            }

            return arg4;
        }

        return arg2;
    }

    void b(int arg3) {
        this.u.removeCallbacks(this.w);
        if(this.a != arg3) {
            this.a = arg3;
            this.r.a(arg3);
            if(this.a == 0) {
                this.s = null;
            }
        }
    }

    private void b(float arg3, float arg4, int arg5) {
        int v0 = 1;
        if(this.a(arg3, arg4, arg5, 1)) {
        }
        else {
            v0 = 0;
        }

        if(this.a(arg4, arg3, arg5, 4)) {
            v0 |= 4;
        }

        if(this.a(arg3, arg4, arg5, 2)) {
            v0 |= 2;
        }

        if(this.a(arg4, arg3, arg5, 8)) {
            v0 |= 8;
        }

        if(v0 != 0) {
            this.i[arg5] |= v0;
            this.r.b(v0, arg5);
        }
    }

    private void b(int arg11, int arg12, int arg13, int arg14) {
        int v0 = this.s.getLeft();
        int v1 = this.s.getTop();
        if(arg13 != 0) {
            arg11 = this.r.b(this.s, arg11, arg13);
            android.support.v4.view.t.f(this.s, arg11 - v0);
        }

        int v6 = arg11;
        if(arg14 != 0) {
            arg12 = this.r.a(this.s, arg12, arg14);
            android.support.v4.view.t.e(this.s, arg12 - v1);
        }

        int v7 = arg12;
        if(arg13 != 0 || arg14 != 0) {
            this.r.a(this.s, v6, v7, v6 - v0, v7 - v1);
        }
    }

    public void b() {
        this.c = -1;
        this.c();
        if(this.l != null) {
            this.l.recycle();
            this.l = null;
        }
    }

    boolean b(View arg3, int arg4) {
        if(arg3 == this.s && this.c == arg4) {
            return 1;
        }

        if(arg3 != null && (this.r.a(arg3, arg4))) {
            this.c = arg4;
            this.a(arg3, arg4);
            return 1;
        }

        return 0;
    }

    public void b(MotionEvent arg10) {
        int v10;
        float v1_1;
        float v0_1;
        int v0 = arg10.getActionMasked();
        int v1 = arg10.getActionIndex();
        if(v0 == 0) {
            this.b();
        }

        if(this.l == null) {
            this.l = VelocityTracker.obtain();
        }

        this.l.addMovement(arg10);
        int v2 = 0;
        switch(v0) {
            case 0: {
                v0_1 = arg10.getX();
                v1_1 = arg10.getY();
                v10 = arg10.getPointerId(0);
                View v2_1 = this.c(((int)v0_1), ((int)v1_1));
                this.a(v0_1, v1_1, v10);
                this.b(v2_1, v10);
                v0 = this.h[v10];
                if((this.p & v0) == 0) {
                    return;
                }

                this.r.a(v0 & this.p, v10);
                break;
            }
            case 1: {
                if(this.a != 1) {
                    goto label_137;
                }

                this.d();
                goto label_137;
            }
            case 2: {
                if(this.a != 1) {
                    v0 = arg10.getPointerCount();
                    while(v2 < v0) {
                        v1 = arg10.getPointerId(v2);
                        if(!this.e(v1)) {
                        }
                        else {
                            float v4 = arg10.getX(v2);
                            float v5 = arg10.getY(v2);
                            float v6 = v4 - this.d[v1];
                            float v7 = v5 - this.e[v1];
                            this.b(v6, v7, v1);
                            if(this.a == 1) {
                                break;
                            }
                            else {
                                View v4_1 = this.c(((int)v4), ((int)v5));
                                if((this.a(v4_1, v6, v7)) && (this.b(v4_1, v1))) {
                                    break;
                                }
                            }
                        }

                        ++v2;
                    }
                }
                else if(!this.e(this.c)) {
                    return;
                }
                else {
                    v0 = arg10.findPointerIndex(this.c);
                    v1_1 = arg10.getX(v0);
                    v0_1 = arg10.getY(v0);
                    v1 = ((int)(v1_1 - this.f[this.c]));
                    v0 = ((int)(v0_1 - this.g[this.c]));
                    this.b(this.s.getLeft() + v1, this.s.getTop() + v0, v1, v0);
                }

                this.c(arg10);
                break;
            }
            case 3: {
                if(this.a == 1) {
                    this.a(0f, 0f);
                }

            label_137:
                this.b();
                break;
            }
            case 5: {
                v0 = arg10.getPointerId(v1);
                float v2_2 = arg10.getX(v1);
                float v10_1 = arg10.getY(v1);
                this.a(v2_2, v10_1, v0);
                if(this.a == 0) {
                    this.b(this.c(((int)v2_2), ((int)v10_1)), v0);
                    v10 = this.h[v0];
                    if((this.p & v10) == 0) {
                        return;
                    }

                    this.r.a(v10 & this.p, v0);
                    return;
                }

                if(!this.b(((int)v2_2), ((int)v10_1))) {
                    return;
                }

                this.b(this.s, v0);
                break;
            }
            case 6: {
                v0 = arg10.getPointerId(v1);
                if(this.a == 1 && v0 == this.c) {
                    v1 = arg10.getPointerCount();
                    while(true) {
                        int v3 = -1;
                        if(v2 >= v1) {
                            break;
                        }

                        int v4_2 = arg10.getPointerId(v2);
                        if(v4_2 == this.c) {
                        }
                        else if(this.c(((int)arg10.getX(v2)), ((int)arg10.getY(v2))) == this.s && (this.b(this.s, v4_2))) {
                            v10 = this.c;
                            goto label_41;
                        }

                        ++v2;
                    }

                    v10 = -1;
                label_41:
                    if(v10 != v3) {
                        goto label_43;
                    }

                    this.d();
                }

            label_43:
                this.c(v0);
                break;
            }
            default: {
                break;
            }
        }
    }

    public boolean b(int arg2, int arg3) {
        return this.b(this.s, arg2, arg3);
    }

    public boolean b(View arg3, int arg4, int arg5) {
        boolean v0 = false;
        if(arg3 == null) {
            return 0;
        }

        if(arg4 >= arg3.getLeft() && arg4 < arg3.getRight() && arg5 >= arg3.getTop() && arg5 < arg3.getBottom()) {
            v0 = true;
        }

        return v0;
    }

    private void c() {
        if(this.d == null) {
            return;
        }

        Arrays.fill(this.d, 0f);
        Arrays.fill(this.e, 0f);
        Arrays.fill(this.f, 0f);
        Arrays.fill(this.g, 0f);
        Arrays.fill(this.h, 0);
        Arrays.fill(this.i, 0);
        Arrays.fill(this.j, 0);
        this.k = 0;
    }

    private void c(int arg3) {
        if(this.d != null) {
            if(!this.a(arg3)) {
            }
            else {
                this.d[arg3] = 0f;
                this.e[arg3] = 0f;
                this.f[arg3] = 0f;
                this.g[arg3] = 0f;
                this.h[arg3] = 0;
                this.i[arg3] = 0;
                this.j[arg3] = 0;
                this.k &= 1 << arg3 ^ -1;
            }
        }
    }

    private void c(MotionEvent arg7) {
        int v0 = arg7.getPointerCount();
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            int v2 = arg7.getPointerId(v1);
            if(!this.e(v2)) {
            }
            else {
                float v3 = arg7.getX(v1);
                float v4 = arg7.getY(v1);
                this.f[v2] = v3;
                this.g[v2] = v4;
            }
        }
    }

    public View c(int arg4, int arg5) {
        int v0;
        for(v0 = this.u.getChildCount() - 1; v0 >= 0; --v0) {
            View v1 = this.u.getChildAt(this.r.c(v0));
            if(arg4 >= v1.getLeft() && arg4 < v1.getRight() && arg5 >= v1.getTop() && arg5 < v1.getBottom()) {
                return v1;
            }
        }

        return null;
    }

    private void d(int arg10) {
        if(this.d == null || this.d.length <= arg10) {
            ++arg10;
            float[] v0 = new float[arg10];
            float[] v1 = new float[arg10];
            float[] v2 = new float[arg10];
            float[] v3 = new float[arg10];
            int[] v4 = new int[arg10];
            int[] v5 = new int[arg10];
            int[] v10 = new int[arg10];
            if(this.d != null) {
                System.arraycopy(this.d, 0, v0, 0, this.d.length);
                System.arraycopy(this.e, 0, v1, 0, this.e.length);
                System.arraycopy(this.f, 0, v2, 0, this.f.length);
                System.arraycopy(this.g, 0, v3, 0, this.g.length);
                System.arraycopy(this.h, 0, v4, 0, this.h.length);
                System.arraycopy(this.i, 0, v5, 0, this.i.length);
                System.arraycopy(this.j, 0, v10, 0, this.j.length);
            }

            this.d = v0;
            this.e = v1;
            this.f = v2;
            this.g = v3;
            this.h = v4;
            this.i = v5;
            this.j = v10;
        }
    }

    private int d(int arg4, int arg5) {
        int v0 = arg4 < this.u.getLeft() + this.o ? 1 : 0;
        if(arg5 < this.u.getTop() + this.o) {
            v0 |= 4;
        }

        if(arg4 > this.u.getRight() - this.o) {
            v0 |= 2;
        }

        if(arg5 > this.u.getBottom() - this.o) {
            v0 |= 8;
        }

        return v0;
    }

    private void d() {
        this.l.computeCurrentVelocity(1000, this.m);
        this.a(this.a(this.l.getXVelocity(this.c), this.n, this.m), this.a(this.l.getYVelocity(this.c), this.n, this.m));
    }

    private boolean e(int arg4) {
        if(!this.a(arg4)) {
            Log.e("ViewDragHelper", "Ignoring pointerId=" + arg4 + " because ACTION_DOWN was not received " + "for this pointer before ACTION_MOVE. It likely happened because " + " ViewDragHelper did not receive all the events in the event stream.");
            return 0;
        }

        return 1;
    }
}

