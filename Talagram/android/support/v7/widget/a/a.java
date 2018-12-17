package android.support.v7.widget.a;

import android.animation.Animator$AnimatorListener;
import android.animation.Animator;
import android.animation.ValueAnimator$AnimatorUpdateListener;
import android.animation.ValueAnimator;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Build$VERSION;
import android.support.v4.view.t;
import android.support.v7.widget.RecyclerView$f;
import android.support.v7.widget.RecyclerView$h;
import android.support.v7.widget.RecyclerView$i;
import android.support.v7.widget.RecyclerView$k;
import android.support.v7.widget.RecyclerView$m;
import android.support.v7.widget.RecyclerView$w;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector$SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewParent;
import android.view.animation.Interpolator;
import java.util.ArrayList;
import java.util.List;

public class a extends h implements k {
    class android.support.v7.widget.a.a$1 implements Runnable {
        android.support.v7.widget.a.a$1(a arg1) {
            this.a = arg1;
            super();
        }

        public void run() {
            if(this.a.b != null && (this.a.b())) {
                if(this.a.b != null) {
                    this.a.a(this.a.b);
                }

                this.a.k.removeCallbacks(this.a.l);
                t.a(this.a.k, ((Runnable)this));
            }
        }
    }

    class android.support.v7.widget.a.a$2 implements m {
        android.support.v7.widget.a.a$2(a arg1) {
            this.a = arg1;
            super();
        }

        public void a(boolean arg3) {
            if(!arg3) {
                return;
            }

            this.a.a(null, 0);
        }

        public boolean a(RecyclerView arg6, MotionEvent arg7) {
            this.a.p.a(arg7);
            int v6 = arg7.getActionMasked();
            boolean v0 = true;
            if(v6 == 0) {
                this.a.g = arg7.getPointerId(0);
                this.a.c = arg7.getX();
                this.a.d = arg7.getY();
                this.a.c();
                if(this.a.b == null) {
                    c v6_1 = this.a.b(arg7);
                    if(v6_1 != null) {
                        this.a.c -= v6_1.l;
                        this.a.d -= v6_1.m;
                        this.a.a(v6_1.h, true);
                        if(this.a.a.remove(v6_1.h.itemView)) {
                            this.a.h.d(this.a.k, v6_1.h);
                        }

                        this.a.a(v6_1.h, v6_1.i);
                        this.a.a(arg7, this.a.i, 0);
                    }
                }
            }
            else {
                int v3 = -1;
                if(v6 != 3) {
                    if(v6 == 1) {
                    }
                    else {
                        if(this.a.g != v3) {
                            int v2 = arg7.findPointerIndex(this.a.g);
                            if(v2 >= 0) {
                                this.a.a(v6, arg7, v2);
                            }
                            else {
                            }
                        }
                        else {
                        }

                        goto label_78;
                    }
                }

                this.a.g = v3;
                this.a.a(null, 0);
            }

        label_78:
            if(this.a.m != null) {
                this.a.m.addMovement(arg7);
            }

            if(this.a.b != null) {
            }
            else {
                v0 = false;
            }

            return v0;
        }

        public void b(RecyclerView arg6, MotionEvent arg7) {
            this.a.p.a(arg7);
            if(this.a.m != null) {
                this.a.m.addMovement(arg7);
            }

            int v0 = -1;
            if(this.a.g == v0) {
                return;
            }

            int v6 = arg7.getActionMasked();
            int v1 = arg7.findPointerIndex(this.a.g);
            if(v1 >= 0) {
                this.a.a(v6, arg7, v1);
            }

            w v2 = this.a.b;
            if(v2 == null) {
                return;
            }

            int v4 = 0;
            if(v6 != 6) {
                switch(v6) {
                    case 1: {
                        goto label_56;
                    }
                    case 2: {
                        goto label_37;
                    }
                    case 3: {
                        goto label_30;
                    }
                }

                return;
            label_37:
                if(v1 >= 0) {
                    this.a.a(arg7, this.a.i, v1);
                    this.a.a(v2);
                    this.a.k.removeCallbacks(this.a.l);
                    this.a.l.run();
                    this.a.k.invalidate();
                    return;
                label_30:
                    if(this.a.m != null) {
                        this.a.m.clear();
                        goto label_56;
                    }
                    else {
                    label_56:
                        this.a.a(null, 0);
                        this.a.g = v0;
                    }
                }
            }
            else {
                v6 = arg7.getActionIndex();
                if(arg7.getPointerId(v6) == this.a.g) {
                    if(v6 == 0) {
                        v4 = 1;
                    }

                    this.a.g = arg7.getPointerId(v4);
                    this.a.a(arg7, this.a.i, v6);
                }
            }
        }
    }

    public abstract class android.support.v7.widget.a.a$a {
        final class android.support.v7.widget.a.a$a$1 implements Interpolator {
            android.support.v7.widget.a.a$a$1() {
                super();
            }

            public float getInterpolation(float arg2) {
                return arg2 * arg2 * arg2 * arg2 * arg2;
            }
        }

        final class android.support.v7.widget.a.a$a$2 implements Interpolator {
            android.support.v7.widget.a.a$a$2() {
                super();
            }

            public float getInterpolation(float arg3) {
                --arg3;
                return arg3 * arg3 * arg3 * arg3 * arg3 + 1f;
            }
        }

        private static final Interpolator a;
        private static final Interpolator b;
        private int c;

        static {
            android.support.v7.widget.a.a$a.a = new android.support.v7.widget.a.a$a$1();
            android.support.v7.widget.a.a$a.b = new android.support.v7.widget.a.a$a$2();
        }

        public android.support.v7.widget.a.a$a() {
            super();
            this.c = -1;
        }

        public boolean a(RecyclerView arg1, w arg2, w arg3) {
            return 1;
        }

        public abstract int a(RecyclerView arg1, w arg2);

        public static int a(int arg3, int arg4) {
            int v0 = 789516;
            int v1 = arg3 & v0;
            if(v1 == 0) {
                return arg3;
            }

            arg3 &= v1 ^ -1;
            if(arg4 == 0) {
                arg4 = v1 << 2;
            }
            else {
                arg4 = v1 << 1;
                arg3 |= -789517 & arg4;
                arg4 = (arg4 & v0) << 2;
            }

            return arg3 | arg4;
        }

        public w a(w arg15, List arg16, int arg17, int arg18) {
            Object v6_1;
            int v11;
            w v0 = arg15;
            int v1 = arg17 + v0.itemView.getWidth();
            int v2 = arg18 + v0.itemView.getHeight();
            int v3 = arg17 - v0.itemView.getLeft();
            int v4 = arg18 - v0.itemView.getTop();
            int v5 = arg16.size();
            w v6 = null;
            int v7 = -1;
            int v8;
            for(v8 = 0; v8 < v5; ++v8) {
                Object v10 = arg16.get(v8);
                if(v3 > 0) {
                    v11 = ((w)v10).itemView.getRight() - v1;
                    if(v11 >= 0) {
                        goto label_34;
                    }
                    else if(((w)v10).itemView.getRight() > v0.itemView.getRight()) {
                        v11 = Math.abs(v11);
                        if(v11 > v7) {
                            v6_1 = v10;
                        }
                        else {
                            goto label_34;
                        }
                    }
                    else {
                        goto label_34;
                    }
                }
                else {
                label_34:
                    v11 = v7;
                }

                if(v3 < 0) {
                    v7 = ((w)v10).itemView.getLeft() - arg17;
                    if(v7 > 0 && ((w)v10).itemView.getLeft() < v0.itemView.getLeft()) {
                        v7 = Math.abs(v7);
                        if(v7 > v11) {
                            v11 = v7;
                            v6_1 = v10;
                        }
                    }
                }

                if(v4 < 0) {
                    v7 = ((w)v10).itemView.getTop() - arg18;
                    if(v7 > 0 && ((w)v10).itemView.getTop() < v0.itemView.getTop()) {
                        v7 = Math.abs(v7);
                        if(v7 > v11) {
                            v11 = v7;
                            v6_1 = v10;
                        }
                    }
                }

                if(v4 > 0) {
                    v7 = ((w)v10).itemView.getBottom() - v2;
                    if(v7 >= 0) {
                        goto label_77;
                    }
                    else if(((w)v10).itemView.getBottom() > v0.itemView.getBottom()) {
                        v7 = Math.abs(v7);
                        if(v7 > v11) {
                            v6_1 = v10;
                        }
                        else {
                            goto label_77;
                        }
                    }
                    else {
                        goto label_77;
                    }
                }
                else {
                label_77:
                    v7 = v11;
                }
            }

            return ((w)v6_1);
        }

        public void a(RecyclerView arg2, w arg3, int arg4, w arg5, int arg6, int arg7, int arg8) {
            i v4 = arg2.getLayoutManager();
            if((v4 instanceof d)) {
                ((d)v4).a(arg3.itemView, arg5.itemView, arg7, arg8);
                return;
            }

            if(v4.e()) {
                if(v4.h(arg5.itemView) <= arg2.getPaddingLeft()) {
                    arg2.a(arg6);
                }

                if(v4.j(arg5.itemView) < arg2.getWidth() - arg2.getPaddingRight()) {
                    goto label_21;
                }

                arg2.a(arg6);
            }

        label_21:
            if(v4.f()) {
                if(v4.i(arg5.itemView) <= arg2.getPaddingTop()) {
                    arg2.a(arg6);
                }

                if(v4.k(arg5.itemView) < arg2.getHeight() - arg2.getPaddingBottom()) {
                    return;
                }

                arg2.a(arg6);
            }
        }

        public long a(RecyclerView arg1, int arg2, float arg3, float arg4) {
            f v1 = arg1.getItemAnimator();
            int v3 = 8;
            if(v1 == null) {
                long v1_1 = arg2 == v3 ? 200 : 250;
                return v1_1;
            }

            return arg2 == v3 ? v1.e() : v1.g();
        }

        public abstract void a(w arg1, int arg2);

        void a(Canvas arg14, RecyclerView arg15, w arg16, List arg17, int arg18, float arg19, float arg20) {
            int v9 = arg17.size();
            int v10;
            for(v10 = 0; v10 < v9; ++v10) {
                Object v0 = arg17.get(v10);
                ((c)v0).c();
                int v12 = arg14.save();
                this.a(arg14, arg15, ((c)v0).h, ((c)v0).l, ((c)v0).m, ((c)v0).i, false);
                arg14.restoreToCount(v12);
            }

            if(arg16 != null) {
                v9 = arg14.save();
                this.a(arg14, arg15, arg16, arg19, arg20, arg18, true);
                arg14.restoreToCount(v9);
            }
        }

        public int a(RecyclerView arg5, int arg6, int arg7, int arg8, long arg9) {
            float v1 = 1f;
            int v5 = ((int)((((float)((((int)Math.signum(((float)arg7)))) * this.a(arg5)))) * android.support.v7.widget.a.a$a.b.getInterpolation(Math.min(v1, (((float)Math.abs(arg7))) * v1 / (((float)arg6))))));
            if(arg9 > 2000) {
            }
            else {
                v1 = (((float)arg9)) / 2000f;
            }

            v5 = ((int)((((float)v5)) * android.support.v7.widget.a.a$a.a.getInterpolation(v1)));
            if(v5 == 0) {
                v5 = arg7 > 0 ? 1 : -1;
            }

            return v5;
        }

        private int a(RecyclerView arg3) {
            if(this.c == -1) {
                this.c = arg3.getResources().getDimensionPixelSize(android.support.v7.e.a$a.item_touch_helper_max_drag_scroll_per_frame);
            }

            return this.c;
        }

        public float a(float arg1) {
            return arg1;
        }

        public float a(w arg1) {
            return 0.5f;
        }

        public void a(Canvas arg9, RecyclerView arg10, w arg11, float arg12, float arg13, int arg14, boolean arg15) {
            android.support.v7.widget.a.c.a.a(arg9, arg10, arg11.itemView, arg12, arg13, arg14, arg15);
        }

        public boolean a() {
            return 1;
        }

        public boolean b() {
            return 1;
        }

        final int b(RecyclerView arg1, w arg2) {
            return this.d(this.a(arg1, arg2), t.f(((View)arg1)));
        }

        void b(Canvas arg15, RecyclerView arg16, w arg17, List arg18, int arg19, float arg20, float arg21) {
            List v9 = arg18;
            int v10 = arg18.size();
            int v11 = 0;
            int v12;
            for(v12 = 0; v12 < v10; ++v12) {
                Object v0 = v9.get(v12);
                int v13 = arg15.save();
                this.b(arg15, arg16, ((c)v0).h, ((c)v0).l, ((c)v0).m, ((c)v0).i, false);
                arg15.restoreToCount(v13);
            }

            if(arg17 != null) {
                v12 = arg15.save();
                this.b(arg15, arg16, arg17, arg20, arg21, arg19, true);
                arg15.restoreToCount(v12);
            }

            --v10;
            while(v10 >= 0) {
                Object v1 = v9.get(v10);
                if((((c)v1).o) && !((c)v1).k) {
                    v9.remove(v10);
                }
                else if(!((c)v1).o) {
                    v11 = 1;
                }

                --v10;
            }

            if(v11 != 0) {
                arg16.invalidate();
            }
        }

        public float b(w arg1) {
            return 0.5f;
        }

        public abstract boolean b(RecyclerView arg1, w arg2, w arg3);

        public void b(w arg1, int arg2) {
            if(arg1 != null) {
                android.support.v7.widget.a.c.a.b(arg1.itemView);
            }
        }

        public static int b(int arg2, int arg3) {
            return android.support.v7.widget.a.a$a.c(2, arg2) | (android.support.v7.widget.a.a$a.c(1, arg3) | android.support.v7.widget.a.a$a.c(0, arg3 | arg2));
        }

        public float b(float arg1) {
            return arg1;
        }

        public void b(Canvas arg9, RecyclerView arg10, w arg11, float arg12, float arg13, int arg14, boolean arg15) {
            android.support.v7.widget.a.c.a.b(arg9, arg10, arg11.itemView, arg12, arg13, arg14, arg15);
        }

        public int c() {
            return 0;
        }

        boolean c(RecyclerView arg1, w arg2) {
            boolean v1 = (this.b(arg1, arg2) & 16711680) != 0 ? true : false;
            return v1;
        }

        public static int c(int arg0, int arg1) {
            return arg1 << arg0 * 8;
        }

        public int d(int arg4, int arg5) {
            int v0 = 3158064;
            int v1 = arg4 & v0;
            if(v1 == 0) {
                return arg4;
            }

            arg4 &= v1 ^ -1;
            if(arg5 == 0) {
                arg5 = v1 >> 2;
            }
            else {
                arg5 = v1 >> 1;
                arg4 |= -3158065 & arg5;
                arg5 = (arg5 & v0) >> 2;
            }

            return arg4 | arg5;
        }

        public void d(RecyclerView arg1, w arg2) {
            android.support.v7.widget.a.c.a.a(arg2.itemView);
        }
    }

    class b extends GestureDetector$SimpleOnGestureListener {
        private boolean b;

        b(a arg1) {
            this.a = arg1;
            super();
            this.b = true;
        }

        void a() {
            this.b = false;
        }

        public boolean onDown(MotionEvent arg1) {
            return 1;
        }

        public void onLongPress(MotionEvent arg4) {
            if(!this.b) {
                return;
            }

            View v0 = this.a.a(arg4);
            if(v0 != null) {
                w v0_1 = this.a.k.b(v0);
                if(v0_1 != null) {
                    if(!this.a.h.c(this.a.k, v0_1)) {
                        return;
                    }
                    else if(arg4.getPointerId(0) == this.a.g) {
                        int v1 = arg4.findPointerIndex(this.a.g);
                        float v2 = arg4.getX(v1);
                        float v4 = arg4.getY(v1);
                        this.a.c = v2;
                        this.a.d = v4;
                        a v4_1 = this.a;
                        this.a.f = 0f;
                        v4_1.e = 0f;
                        if(this.a.h.a()) {
                            this.a.a(v0_1, 2);
                        }
                    }
                }
            }
        }
    }

    class c implements Animator$AnimatorListener {
        private final ValueAnimator a;
        private float b;
        final float d;
        final float e;
        final float f;
        final float g;
        final w h;
        final int i;
        final int j;
        boolean k;
        float l;
        float m;
        boolean n;
        boolean o;

        c(w arg2, int arg3, int arg4, float arg5, float arg6, float arg7, float arg8) {
            super();
            this.n = false;
            this.o = false;
            this.i = arg4;
            this.j = arg3;
            this.h = arg2;
            this.d = arg5;
            this.e = arg6;
            this.f = arg7;
            this.g = arg8;
            this.a = ValueAnimator.ofFloat(new float[]{0f, 1f});
            this.a.addUpdateListener(new ValueAnimator$AnimatorUpdateListener() {
                public void onAnimationUpdate(ValueAnimator arg2) {
                    this.a.a(arg2.getAnimatedFraction());
                }
            });
            this.a.setTarget(arg2.itemView);
            this.a.addListener(((Animator$AnimatorListener)this));
            this.a(0f);
        }

        public void a(long arg2) {
            this.a.setDuration(arg2);
        }

        public void a() {
            this.h.setIsRecyclable(false);
            this.a.start();
        }

        public void a(float arg1) {
            this.b = arg1;
        }

        public void b() {
            this.a.cancel();
        }

        public void c() {
            float v0 = this.d == this.f ? this.h.itemView.getTranslationX() : this.d + this.b * (this.f - this.d);
            this.l = v0;
            v0 = this.e == this.g ? this.h.itemView.getTranslationY() : this.e + this.b * (this.g - this.e);
            this.m = v0;
        }

        public void onAnimationCancel(Animator arg1) {
            this.a(1f);
        }

        public void onAnimationEnd(Animator arg2) {
            if(!this.o) {
                this.h.setIsRecyclable(true);
            }

            this.o = true;
        }

        public void onAnimationRepeat(Animator arg1) {
        }

        public void onAnimationStart(Animator arg1) {
        }
    }

    public interface d {
        void a(View arg1, View arg2, int arg3, int arg4);
    }

    private b A;
    private final m B;
    private Rect C;
    private long D;
    final List a;
    w b;
    float c;
    float d;
    float e;
    float f;
    int g;
    android.support.v7.widget.a.a$a h;
    int i;
    List j;
    RecyclerView k;
    final Runnable l;
    VelocityTracker m;
    View n;
    int o;
    android.support.v4.view.c p;
    private final float[] q;
    private float r;
    private float s;
    private float t;
    private float u;
    private int v;
    private int w;
    private List x;
    private List y;
    private android.support.v7.widget.RecyclerView$d z;

    public a(android.support.v7.widget.a.a$a arg4) {
        super();
        this.a = new ArrayList();
        this.q = new float[2];
        this.b = null;
        this.g = -1;
        this.v = 0;
        this.j = new ArrayList();
        this.l = new android.support.v7.widget.a.a$1(this);
        this.z = null;
        this.n = null;
        this.o = -1;
        this.B = new android.support.v7.widget.a.a$2(this);
        this.h = arg4;
    }

    private void a(float[] arg4) {
        arg4[0] = (this.i & 12) != 0 ? this.t + this.e - (((float)this.b.itemView.getLeft())) : this.b.itemView.getTranslationX();
        arg4[1] = (this.i & 3) != 0 ? this.u + this.f - (((float)this.b.itemView.getTop())) : this.b.itemView.getTranslationY();
    }

    private static boolean a(View arg1, float arg2, float arg3, float arg4, float arg5) {
        boolean v1 = arg2 < arg4 || arg2 > arg4 + (((float)arg1.getWidth())) || arg3 < arg5 || arg3 > arg5 + (((float)arg1.getHeight())) ? false : true;
        return v1;
    }

    View a(MotionEvent arg6) {
        float v0 = arg6.getX();
        float v6 = arg6.getY();
        if(this.b != null) {
            View v1 = this.b.itemView;
            if(a.a(v1, v0, v6, this.t + this.e, this.u + this.f)) {
                return v1;
            }
        }

        int v1_1;
        for(v1_1 = this.j.size() - 1; v1_1 >= 0; --v1_1) {
            Object v2 = this.j.get(v1_1);
            View v3 = ((c)v2).h.itemView;
            if(a.a(v3, v0, v6, ((c)v2).l, ((c)v2).m)) {
                return v3;
            }
        }

        return this.k.a(v0, v6);
    }

    void a(int arg8, MotionEvent arg9, int arg10) {
        if(this.b == null) {
            int v0 = 2;
            if(arg8 == v0 && this.v != v0) {
                if(!this.h.b()) {
                }
                else if(this.k.getScrollState() == 1) {
                    return;
                }
                else {
                    w v8 = this.c(arg9);
                    if(v8 == null) {
                        return;
                    }
                    else {
                        int v2 = (this.h.b(this.k, v8) & 65280) >> 8;
                        if(v2 == 0) {
                            return;
                        }
                        else {
                            float v3 = arg9.getX(arg10);
                            float v10 = arg9.getY(arg10);
                            v3 -= this.c;
                            v10 -= this.d;
                            float v4 = Math.abs(v3);
                            float v5 = Math.abs(v10);
                            if(v4 < (((float)this.w)) && v5 < (((float)this.w))) {
                                return;
                            }

                            if(Float.compare(v4, v5) > 0) {
                                if(v3 < 0f && (v2 & 4) == 0) {
                                    return;
                                }

                                if(v3 <= 0f) {
                                    goto label_60;
                                }

                                if((v2 & 8) != 0) {
                                    goto label_60;
                                }

                                return;
                            }
                            else {
                                if(v10 < 0f && (v2 & 1) == 0) {
                                    return;
                                }

                                if(v10 <= 0f) {
                                    goto label_60;
                                }

                                if((v2 & 2) != 0) {
                                    goto label_60;
                                }

                                return;
                            }

                        label_60:
                            this.f = 0f;
                            this.e = 0f;
                            this.g = arg9.getPointerId(0);
                            this.a(v8, 1);
                        }
                    }
                }
            }
        }
    }

    void a(w arg24, int arg25) {
        boolean v1;
        int v9;
        int v6;
        float v17;
        float v18;
        a v11 = this;
        w v12 = arg24;
        int v13 = arg25;
        if(v12 == v11.b && v13 == v11.v) {
            return;
        }

        v11.D = -9223372036854775808L;
        int v4 = v11.v;
        v11.a(v12, true);
        v11.v = v13;
        int v15 = 2;
        if(v13 == v15) {
            if(v12 != null) {
                v11.n = v12.itemView;
                this.i();
            }
            else {
                throw new IllegalArgumentException("Must pass a ViewHolder when dragging");
            }
        }

        int v10 = 8;
        int v16 = (1 << v13 * 8 + v10) - 1;
        if(v11.b != null) {
            w v8 = v11.b;
            if(v8.itemView.getParent() != null) {
                int v7 = v4 == v15 ? 0 : v11.d(v8);
                this.h();
                if(v7 == 4) {
                    goto label_64;
                }
                else if(v7 == v10) {
                    goto label_64;
                }
                else if(v7 == 16) {
                    goto label_64;
                }
                else if(v7 != 32) {
                    switch(v7) {
                        case 1: 
                        case 2: {
                            v18 = Math.signum(v11.f) * (((float)v11.k.getHeight()));
                            v17 = 0f;
                            goto label_72;
                        label_64:
                            v17 = Math.signum(v11.e) * (((float)v11.k.getWidth()));
                        label_53:
                            v18 = 0f;
                            goto label_72;
                        }
                        default: {
                            v17 = 0f;
                            goto label_53;
                        }
                    }
                }
                else {
                    goto label_64;
                }

            label_72:
                if(v4 == v15) {
                    v6 = 8;
                }
                else if(v7 > 0) {
                    v6 = 2;
                }
                else {
                    v6 = 4;
                }

                v11.a(v11.q);
                float v19 = v11.q[0];
                float v20 = v11.q[1];
                android.support.v7.widget.a.a$3 v14 = new c(v8, v6, v4, v19, v20, v17, v18, v7, v8) {
                    public void onAnimationEnd(Animator arg3) {
                        super.onAnimationEnd(arg3);
                        if(this.n) {
                            return;
                        }

                        if(this.a <= 0) {
                            this.c.h.d(this.c.k, this.b);
                        }
                        else {
                            this.c.a.add(this.b.itemView);
                            this.k = true;
                            if(this.a > 0) {
                                this.c.a(((c)this), this.a);
                            }
                        }

                        if(this.c.n == this.b.itemView) {
                            this.c.c(this.b.itemView);
                        }
                    }
                };
                ((c)v14).a(v11.h.a(v11.k, v6, v17 - v19, v18 - v20));
                v11.j.add(v14);
                ((c)v14).a();
                v9 = 1;
            }
            else {
                v11.c(v8.itemView);
                v11.h.d(v11.k, v8);
                v9 = 0;
            }

            v11.b = null;
        }
        else {
            v9 = 0;
        }

        if(v12 != null) {
            v11.i = (v11.h.b(v11.k, v12) & v16) >> v11.v * 8;
            v11.t = ((float)v12.itemView.getLeft());
            v11.u = ((float)v12.itemView.getTop());
            v11.b = v12;
            if(v13 == 2) {
                v1 = false;
                v11.b.itemView.performHapticFeedback(0);
            }
            else {
                goto label_153;
            }
        }
        else {
        label_153:
            v1 = false;
        }

        ViewParent v0 = v11.k.getParent();
        if(v0 != null) {
            if(v11.b != null) {
                v1 = true;
            }

            v0.requestDisallowInterceptTouchEvent(v1);
        }

        if(v9 == 0) {
            v11.k.getLayoutManager().L();
        }

        v11.h.b(v11.b, v11.v);
        v11.k.invalidate();
    }

    public void a(Canvas arg10, RecyclerView arg11, android.support.v7.widget.RecyclerView$t arg12) {
        float v7;
        float v8;
        if(this.b != null) {
            this.a(this.q);
            float v0 = this.q[0];
            v8 = this.q[1];
            v7 = v0;
        }
        else {
            v7 = 0f;
            v8 = 0f;
        }

        this.h.b(arg10, arg11, this.b, this.j, this.v, v7, v8);
    }

    public void a(Rect arg1, View arg2, RecyclerView arg3, android.support.v7.widget.RecyclerView$t arg4) {
        arg1.setEmpty();
    }

    void a(w arg11) {
        if(this.k.isLayoutRequested()) {
            return;
        }

        if(this.v != 2) {
            return;
        }

        float v0 = this.h.b(arg11);
        int v8 = ((int)(this.t + this.e));
        int v9 = ((int)(this.u + this.f));
        if((((float)Math.abs(v9 - arg11.itemView.getTop()))) < (((float)arg11.itemView.getHeight())) * v0 && (((float)Math.abs(v8 - arg11.itemView.getLeft()))) < (((float)arg11.itemView.getWidth())) * v0) {
            return;
        }

        List v0_1 = this.c(arg11);
        if(v0_1.size() == 0) {
            return;
        }

        w v6 = this.h.a(arg11, v0_1, v8, v9);
        if(v6 == null) {
            this.x.clear();
            this.y.clear();
            return;
        }

        int v7 = v6.getAdapterPosition();
        int v5 = arg11.getAdapterPosition();
        if(this.h.b(this.k, arg11, v6)) {
            this.h.a(this.k, arg11, v5, v6, v7, v8, v9);
        }
    }

    void a(w arg4, boolean arg5) {
        int v0;
        for(v0 = this.j.size() - 1; v0 >= 0; --v0) {
            Object v1 = this.j.get(v0);
            if(((c)v1).h == arg4) {
                ((c)v1).n |= ((int)arg5);
                if(!((c)v1).o) {
                    ((c)v1).b();
                }

                this.j.remove(v0);
                return;
            }
        }
    }

    void a(c arg3, int arg4) {
        this.k.post(new Runnable(arg3, arg4) {
            public void run() {
                if(this.c.k != null && (this.c.k.isAttachedToWindow()) && !this.a.n && this.a.h.getAdapterPosition() != -1) {
                    f v0 = this.c.k.getItemAnimator();
                    if((v0 == null || !v0.a(null)) && !this.c.a()) {
                        this.c.h.a(this.a.h, this.b);
                        return;
                    }

                    this.c.k.post(((Runnable)this));
                }
            }
        });
    }

    public void a(RecyclerView arg2) {
        if(this.k == arg2) {
            return;
        }

        if(this.k != null) {
            this.e();
        }

        this.k = arg2;
        if(arg2 != null) {
            Resources v2 = arg2.getResources();
            this.r = v2.getDimension(android.support.v7.e.a$a.item_touch_helper_swipe_escape_velocity);
            this.s = v2.getDimension(android.support.v7.e.a$a.item_touch_helper_swipe_escape_max_velocity);
            this.d();
        }
    }

    boolean a() {
        int v0 = this.j.size();
        int v2;
        for(v2 = 0; v2 < v0; ++v2) {
            if(!this.j.get(v2).o) {
                return 1;
            }
        }

        return 0;
    }

    void a(MotionEvent arg2, int arg3, int arg4) {
        float v0 = arg2.getX(arg4);
        float v2 = arg2.getY(arg4);
        this.e = v0 - this.c;
        this.f = v2 - this.d;
        if((arg3 & 4) == 0) {
            this.e = Math.max(0f, this.e);
        }

        if((arg3 & 8) == 0) {
            this.e = Math.min(0f, this.e);
        }

        if((arg3 & 1) == 0) {
            this.f = Math.max(0f, this.f);
        }

        if((arg3 & 2) == 0) {
            this.f = Math.min(0f, this.f);
        }
    }

    public void a(View arg1) {
    }

    private int b(w arg9, int arg10) {
        // Method was not decompiled
    }

    c b(MotionEvent arg5) {
        c v1 = null;
        if(this.j.isEmpty()) {
            return v1;
        }

        View v5 = this.a(arg5);
        int v0;
        for(v0 = this.j.size() - 1; v0 >= 0; --v0) {
            Object v2 = this.j.get(v0);
            if(((c)v2).h.itemView == v5) {
                return ((c)v2);
            }
        }

        return v1;
    }

    public void b(Canvas arg10, RecyclerView arg11, android.support.v7.widget.RecyclerView$t arg12) {
        float v7;
        float v8;
        this.o = -1;
        if(this.b != null) {
            this.a(this.q);
            float v0 = this.q[0];
            v8 = this.q[1];
            v7 = v0;
        }
        else {
            v7 = 0f;
            v8 = 0f;
        }

        this.h.a(arg10, arg11, this.b, this.j, this.v, v7, v8);
    }

    public void b(w arg3) {
        if(!this.h.c(this.k, arg3)) {
            Log.e("ItemTouchHelper", "Start drag has been called but dragging is not enabled");
            return;
        }

        if(arg3.itemView.getParent() != this.k) {
            Log.e("ItemTouchHelper", "Start drag has been called with a view holder which is not a child of the RecyclerView which is controlled by this ItemTouchHelper.");
            return;
        }

        this.c();
        this.f = 0f;
        this.e = 0f;
        this.a(arg3, 2);
    }

    public void b(View arg3) {
        this.c(arg3);
        w v3 = this.k.b(arg3);
        if(v3 == null) {
            return;
        }

        if(this.b == null || v3 != this.b) {
            this.a(v3, false);
            if(this.a.remove(v3.itemView)) {
                this.h.d(this.k, v3);
            }
        }
        else {
            this.a(null, 0);
        }
    }

    boolean b() {
        int v1_1;
        int v12;
        int v9;
        a v0 = this;
        long v3 = -9223372036854775808L;
        if(v0.b == null) {
            v0.D = v3;
            return 0;
        }

        long v5 = System.currentTimeMillis();
        long v7 = v0.D == v3 ? 0 : v5 - v0.D;
        i v1 = v0.k.getLayoutManager();
        if(v0.C == null) {
            v0.C = new Rect();
        }

        v1.b(v0.b.itemView, v0.C);
        if(v1.e()) {
            v9 = ((int)(v0.t + v0.e));
            int v11 = v9 - v0.C.left - v0.k.getPaddingLeft();
            if(v0.e < 0f && v11 < 0) {
                v12 = v11;
                goto label_62;
            }

            if(v0.e <= 0f) {
                goto label_61;
            }

            v9 = v9 + v0.b.itemView.getWidth() + v0.C.right - (v0.k.getWidth() - v0.k.getPaddingRight());
            if(v9 <= 0) {
                goto label_61;
            }

            v12 = v9;
        }
        else {
        label_61:
            v12 = 0;
        }

    label_62:
        if(v1.f()) {
            v1_1 = ((int)(v0.u + v0.f));
            v9 = v1_1 - v0.C.top - v0.k.getPaddingTop();
            if(v0.f < 0f && v9 < 0) {
                v1_1 = v9;
                goto label_97;
            }

            if(v0.f <= 0f) {
                goto label_96;
            }

            v1_1 = v1_1 + v0.b.itemView.getHeight() + v0.C.bottom - (v0.k.getHeight() - v0.k.getPaddingBottom());
            if(v1_1 <= 0) {
                goto label_96;
            }
        }
        else {
        label_96:
            v1_1 = 0;
        }

    label_97:
        if(v12 != 0) {
            v12 = v0.h.a(v0.k, v0.b.itemView.getWidth(), v12, v0.k.getWidth(), v7);
        }

        int v14 = v12;
        if(v1_1 != 0) {
            v12 = v14;
            v1_1 = v0.h.a(v0.k, v0.b.itemView.getHeight(), v1_1, v0.k.getHeight(), v7);
        }
        else {
            v12 = v14;
        }

        if(v12 == 0) {
            if(v1_1 != 0) {
            }
            else {
                v0.D = v3;
                return 0;
            }
        }

        if(v0.D == v3) {
            v0.D = v5;
        }

        v0.k.scrollBy(v12, v1_1);
        return 1;
    }

    private int c(w arg9, int arg10) {
        // Method was not decompiled
    }

    private w c(MotionEvent arg6) {
        i v0 = this.k.getLayoutManager();
        w v2 = null;
        if(this.g == -1) {
            return v2;
        }

        int v1 = arg6.findPointerIndex(this.g);
        float v3 = arg6.getX(v1) - this.c;
        float v1_1 = arg6.getY(v1) - this.d;
        v3 = Math.abs(v3);
        v1_1 = Math.abs(v1_1);
        if(v3 < (((float)this.w)) && v1_1 < (((float)this.w))) {
            return v2;
        }

        if(v3 > v1_1 && (v0.e())) {
            return v2;
        }

        if(v1_1 > v3 && (v0.f())) {
            return v2;
        }

        View v6 = this.a(arg6);
        if(v6 == null) {
            return v2;
        }

        return this.k.b(v6);
    }

    private List c(w arg17) {
        a v0 = this;
        w v1 = arg17;
        if(v0.x == null) {
            v0.x = new ArrayList();
            v0.y = new ArrayList();
        }
        else {
            v0.x.clear();
            v0.y.clear();
        }

        int v2 = v0.h.c();
        int v3 = Math.round(v0.t + v0.e) - v2;
        int v4 = Math.round(v0.u + v0.f) - v2;
        v2 *= 2;
        int v5 = v1.itemView.getWidth() + v3 + v2;
        int v6 = v1.itemView.getHeight() + v4 + v2;
        v2 = (v3 + v5) / 2;
        int v7 = (v4 + v6) / 2;
        i v8 = v0.k.getLayoutManager();
        int v9 = v8.x();
        int v11 = 0;
        while(v11 < v9) {
            View v12 = v8.i(v11);
            if(v12 == v1.itemView) {
            }
            else if(v12.getBottom() >= v4 && v12.getTop() <= v6 && v12.getRight() >= v3) {
                if(v12.getLeft() > v5) {
                }
                else {
                    w v13 = v0.k.b(v12);
                    if(v0.h.a(v0.k, v0.b, v13)) {
                        int v10 = Math.abs(v2 - (v12.getLeft() + v12.getRight()) / 2);
                        int v12_1 = Math.abs(v7 - (v12.getTop() + v12.getBottom()) / 2);
                        v10 = v10 * v10 + v12_1 * v12_1;
                        v12_1 = v0.x.size();
                        int v14 = 0;
                        int v15 = 0;
                        while(v14 < v12_1) {
                            if(v10 <= v0.y.get(v14).intValue()) {
                                break;
                            }

                            ++v15;
                            ++v14;
                        }

                        v0.x.add(v15, v13);
                        v0.y.add(v15, Integer.valueOf(v10));
                    }
                }
            }

            ++v11;
            v1 = arg17;
        }

        return v0.x;
    }

    void c(View arg2) {
        if(arg2 == this.n) {
            arg2 = null;
            this.n = arg2;
            if(this.z != null) {
                this.k.setChildDrawingOrderCallback(((android.support.v7.widget.RecyclerView$d)arg2));
            }
        }
    }

    void c() {
        if(this.m != null) {
            this.m.recycle();
        }

        this.m = VelocityTracker.obtain();
    }

    private int d(w arg6) {
        int v6;
        if(this.v == 2) {
            return 0;
        }

        int v0 = this.h.a(this.k, arg6);
        int v3 = 65280;
        int v2 = (this.h.d(v0, t.f(this.k)) & v3) >> 8;
        if(v2 == 0) {
            return 0;
        }

        v0 = (v0 & v3) >> 8;
        if(Math.abs(this.e) > Math.abs(this.f)) {
            v3 = this.b(arg6, v2);
            if(v3 <= 0) {
                v6 = this.c(arg6, v2);
                if(v6 > 0) {
                    return v6;
                }
            }
            else if((v0 & v3) == 0) {
                return android.support.v7.widget.a.a$a.a(v3, t.f(this.k));
            }
            else {
                return v3;
            }
        }
        else {
            v3 = this.c(arg6, v2);
            if(v3 > 0) {
                return v3;
            }
            else {
                v6 = this.b(arg6, v2);
                if(v6 > 0) {
                    if((v0 & v6) == 0) {
                        v6 = android.support.v7.widget.a.a$a.a(v6, t.f(this.k));
                    }

                    return v6;
                }
            }
        }

        return 0;
    }

    private void d() {
        this.w = ViewConfiguration.get(this.k.getContext()).getScaledTouchSlop();
        this.k.a(((h)this));
        this.k.a(this.B);
        this.k.a(((k)this));
        this.f();
    }

    private void e() {
        this.k.b(((h)this));
        this.k.b(this.B);
        this.k.b(((k)this));
        int v0;
        for(v0 = this.j.size() - 1; v0 >= 0; --v0) {
            this.h.d(this.k, this.j.get(0).h);
        }

        this.j.clear();
        this.n = null;
        this.o = -1;
        this.h();
        this.g();
    }

    private void f() {
        this.A = new b(this);
        this.p = new android.support.v4.view.c(this.k.getContext(), this.A);
    }

    private void g() {
        b v1 = null;
        if(this.A != null) {
            this.A.a();
            this.A = v1;
        }

        if(this.p != null) {
            this.p = ((android.support.v4.view.c)v1);
        }
    }

    private void h() {
        if(this.m != null) {
            this.m.recycle();
            this.m = null;
        }
    }

    private void i() {
        if(Build$VERSION.SDK_INT >= 21) {
            return;
        }

        if(this.z == null) {
            this.z = new android.support.v7.widget.RecyclerView$d() {
                public int a(int arg3, int arg4) {
                    if(this.a.n == null) {
                        return arg4;
                    }

                    int v0 = this.a.o;
                    if(v0 == -1) {
                        v0 = this.a.k.indexOfChild(this.a.n);
                        this.a.o = v0;
                    }

                    if(arg4 == arg3 - 1) {
                        return v0;
                    }

                    if(arg4 < v0) {
                    }
                    else {
                        ++arg4;
                    }

                    return arg4;
                }
            };
        }

        this.k.setChildDrawingOrderCallback(this.z);
    }
}

