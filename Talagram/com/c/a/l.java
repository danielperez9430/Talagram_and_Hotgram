package com.c.a;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AndroidRuntimeException;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import java.util.ArrayList;
import java.util.HashMap;

public class l extends a {
    final class com.c.a.l$1 extends ThreadLocal {
        com.c.a.l$1() {
            super();
        }

        protected ArrayList a() {
            return new ArrayList();
        }

        protected Object initialValue() {
            return this.a();
        }
    }

    final class com.c.a.l$2 extends ThreadLocal {
        com.c.a.l$2() {
            super();
        }

        protected ArrayList a() {
            return new ArrayList();
        }

        protected Object initialValue() {
            return this.a();
        }
    }

    final class com.c.a.l$3 extends ThreadLocal {
        com.c.a.l$3() {
            super();
        }

        protected ArrayList a() {
            return new ArrayList();
        }

        protected Object initialValue() {
            return this.a();
        }
    }

    final class com.c.a.l$4 extends ThreadLocal {
        com.c.a.l$4() {
            super();
        }

        protected ArrayList a() {
            return new ArrayList();
        }

        protected Object initialValue() {
            return this.a();
        }
    }

    final class com.c.a.l$5 extends ThreadLocal {
        com.c.a.l$5() {
            super();
        }

        protected ArrayList a() {
            return new ArrayList();
        }

        protected Object initialValue() {
            return this.a();
        }
    }

    class com.c.a.l$a extends Handler {
        com.c.a.l$a(com.c.a.l$1 arg1) {
            this();
        }

        private com.c.a.l$a() {
            super();
        }

        public void handleMessage(Message arg15) {
            Object v12;
            int v6;
            Object v0 = l.j().get();
            Object v1 = l.k().get();
            long v2 = 0;
            int v4 = 0;
            switch(arg15.what) {
                case 0: {
                    Object v15 = l.l().get();
                    v6 = ((ArrayList)v0).size() > 0 || ((ArrayList)v1).size() > 0 ? 0 : 1;
                label_22:
                    if(((ArrayList)v15).size() > 0) {
                        Object v7 = ((ArrayList)v15).clone();
                        ((ArrayList)v15).clear();
                        int v8 = ((ArrayList)v7).size();
                        int v9;
                        for(v9 = 0; true; ++v9) {
                            if(v9 >= v8) {
                                goto label_22;
                            }

                            Object v10 = ((ArrayList)v7).get(v9);
                            if(l.a(((l)v10)) == v2) {
                                l.b(((l)v10));
                            }
                            else {
                                ((ArrayList)v1).add(v10);
                            }
                        }
                    }

                label_37:
                    long v7_1 = AnimationUtils.currentAnimationTimeMillis();
                    v15 = l.m().get();
                    Object v9_1 = l.n().get();
                    int v10_1 = ((ArrayList)v1).size();
                    int v11;
                    for(v11 = 0; v11 < v10_1; ++v11) {
                        v12 = ((ArrayList)v1).get(v11);
                        if(l.a(((l)v12), v7_1)) {
                            ((ArrayList)v15).add(v12);
                        }
                    }

                    v10_1 = ((ArrayList)v15).size();
                    if(v10_1 > 0) {
                        for(v11 = 0; v11 < v10_1; ++v11) {
                            v12 = ((ArrayList)v15).get(v11);
                            l.b(((l)v12));
                            l.a(((l)v12), true);
                            ((ArrayList)v1).remove(v12);
                        }

                        ((ArrayList)v15).clear();
                    }

                    v10_1 = ((ArrayList)v0).size();
                    int v15_1 = 0;
                    while(v15_1 < v10_1) {
                        Object v11_1 = ((ArrayList)v0).get(v15_1);
                        if(((l)v11_1).e(v7_1)) {
                            ((ArrayList)v9_1).add(v11_1);
                        }

                        if(((ArrayList)v0).size() == v10_1) {
                            ++v15_1;
                            continue;
                        }

                        --v10_1;
                        ((ArrayList)v9_1).remove(v11_1);
                    }

                    if(((ArrayList)v9_1).size() > 0) {
                        while(v4 < ((ArrayList)v9_1).size()) {
                            l.c(((ArrayList)v9_1).get(v4));
                            ++v4;
                        }

                        ((ArrayList)v9_1).clear();
                    }

                    if(v6 == 0) {
                        return;
                    }

                    if((((ArrayList)v0).isEmpty()) && (((ArrayList)v1).isEmpty())) {
                        return;
                    }

                    this.sendEmptyMessageDelayed(1, Math.max(v2, l.o() - (AnimationUtils.currentAnimationTimeMillis() - v7_1)));
                    break;
                }
                case 1: {
                    v6 = 1;
                    goto label_37;
                }
                default: {
                    break;
                }
            }
        }
    }

    public interface b {
        void a(l arg1);
    }

    private int A;
    private int B;
    private Interpolator C;
    private ArrayList D;
    long b;
    long c;
    int d;
    boolean e;
    j[] f;
    HashMap g;
    private static ThreadLocal h;
    private static final ThreadLocal i;
    private static final ThreadLocal j;
    private static final ThreadLocal k;
    private static final ThreadLocal l;
    private static final ThreadLocal m;
    private static final Interpolator n;
    private static final k o;
    private static final k p;
    private boolean q;
    private int r;
    private float s;
    private boolean t;
    private long u;
    private boolean v;
    private boolean w;
    private long x;
    private long y;
    private static long z;

    static {
        l.h = new ThreadLocal();
        l.i = new com.c.a.l$1();
        l.j = new com.c.a.l$2();
        l.k = new com.c.a.l$3();
        l.l = new com.c.a.l$4();
        l.m = new com.c.a.l$5();
        l.n = new AccelerateDecelerateInterpolator();
        l.o = new d();
        l.p = new com.c.a.b();
        l.z = 10;
    }

    public l() {
        super();
        this.c = -1;
        this.q = false;
        this.r = 0;
        this.s = 0f;
        this.t = false;
        this.d = 0;
        this.v = false;
        this.w = false;
        this.e = false;
        this.x = 300;
        this.y = 0;
        this.A = 0;
        this.B = 1;
        this.C = l.n;
        this.D = null;
    }

    static long a(l arg2) {
        return arg2.y;
    }

    private void a(boolean arg7) {
        com.c.a.l$a v0_1;
        Object v0;
        if(Looper.myLooper() != null) {
            this.q = arg7;
            this.r = 0;
            this.d = 0;
            this.w = true;
            this.t = false;
            l.j.get().add(this);
            if(this.y == 0) {
                this.c(this.g());
                this.d = 0;
                this.v = true;
                if(this.a != null) {
                    v0 = this.a.clone();
                    int v1 = ((ArrayList)v0).size();
                    int v2;
                    for(v2 = 0; v2 < v1; ++v2) {
                        ((ArrayList)v0).get(v2).a(((a)this));
                    }
                }
            }

            v0 = l.h.get();
            if(v0 == null) {
                v0_1 = new com.c.a.l$a(null);
                l.h.set(v0_1);
            }

            v0_1.sendEmptyMessage(0);
            return;
        }

        throw new AndroidRuntimeException("Animators may only be run on Looper threads");
    }

    private boolean a(long arg7) {
        if(!this.t) {
            this.t = true;
            this.u = arg7;
        }
        else {
            long v2 = arg7 - this.u;
            if(v2 > this.y) {
                this.b = arg7 - (v2 - this.y);
                this.d = 1;
                return 1;
            }
        }

        return 0;
    }

    static boolean a(l arg0, long arg1) {
        return arg0.a(arg1);
    }

    static boolean a(l arg0, boolean arg1) {
        arg0.v = arg1;
        return arg1;
    }

    public void a() {
        this.a(false);
    }

    void a(float arg5) {
        arg5 = this.C.getInterpolation(arg5);
        this.s = arg5;
        int v0 = this.f.length;
        int v1 = 0;
        int v2;
        for(v2 = 0; v2 < v0; ++v2) {
            this.f[v2].a(arg5);
        }

        if(this.D != null) {
            int v5 = this.D.size();
            while(v1 < v5) {
                this.D.get(v1).a(this);
                ++v1;
            }
        }
    }

    public void a(int arg1) {
        this.A = arg1;
    }

    public void a(Interpolator arg1) {
        LinearInterpolator v1;
        if(arg1 == null) {
            v1 = new LinearInterpolator();
        }

        this.C = ((Interpolator)v1);
    }

    public void a(b arg2) {
        if(this.D == null) {
            this.D = new ArrayList();
        }

        this.D.add(arg2);
    }

    public void a(float[] arg4) {
        if(arg4 != null) {
            if(arg4.length == 0) {
            }
            else {
                if(this.f == null || this.f.length == 0) {
                    this.a(new j[]{j.a("", arg4)});
                }
                else {
                    this.f[0].a(arg4);
                }

                this.e = false;
            }
        }
    }

    public void a(j[] arg7) {
        int v0 = arg7.length;
        this.f = arg7;
        this.g = new HashMap(v0);
        int v2;
        for(v2 = 0; v2 < v0; ++v2) {
            this.g.put(arg7[v2].c(), arg7[v2]);
        }

        this.e = false;
    }

    public void a(int[] arg4) {
        if(arg4 != null) {
            if(arg4.length == 0) {
            }
            else {
                if(this.f == null || this.f.length == 0) {
                    this.a(new j[]{j.a("", arg4)});
                }
                else {
                    this.f[0].a(arg4);
                }

                this.e = false;
            }
        }
    }

    static void b(l arg0) {
        arg0.p();
    }

    public l b(long arg4) {
        if(arg4 >= 0) {
            this.x = arg4;
            return this;
        }

        StringBuilder v1 = new StringBuilder();
        v1.append("Animators cannot have negative duration: ");
        v1.append(arg4);
        throw new IllegalArgumentException(v1.toString());
    }

    public void b() {
        if(!l.i.get().contains(this) && !l.j.get().contains(this)) {
            this.t = false;
            this.p();
        }
        else if(!this.e) {
            this.d();
        }

        float v0 = this.A <= 0 || (this.A & 1) != 1 ? 1f : 0f;
        this.a(v0);
        this.e();
    }

    public void c(long arg5) {
        this.d();
        long v0 = AnimationUtils.currentAnimationTimeMillis();
        if(this.d != 1) {
            this.c = arg5;
            this.d = 2;
        }

        this.b = v0 - arg5;
        this.e(v0);
    }

    static void c(l arg0) {
        arg0.e();
    }

    public a c() {
        return this.f();
    }

    public Object clone() {
        return this.f();
    }

    void d() {
        if(!this.e) {
            int v0 = this.f.length;
            int v1;
            for(v1 = 0; v1 < v0; ++v1) {
                this.f[v1].b();
            }

            this.e = true;
        }
    }

    public void d(long arg1) {
        this.y = arg1;
    }

    private void e() {
        l.i.get().remove(this);
        l.j.get().remove(this);
        l.k.get().remove(this);
        this.d = 0;
        if((this.v) && this.a != null) {
            Object v1 = this.a.clone();
            int v2 = ((ArrayList)v1).size();
            int v3;
            for(v3 = 0; v3 < v2; ++v3) {
                ((ArrayList)v1).get(v3).b(((a)this));
            }
        }

        this.v = false;
        this.w = false;
    }

    boolean e(long arg8) {
        // Method was not decompiled
    }

    public l f() {
        int v3;
        a v0 = super.c();
        int v2 = 0;
        if(this.D != null) {
            ArrayList v1 = this.D;
            ((l)v0).D = new ArrayList();
            v3 = v1.size();
            int v4;
            for(v4 = 0; v4 < v3; ++v4) {
                ((l)v0).D.add(v1.get(v4));
            }
        }

        ((l)v0).c = -1;
        ((l)v0).q = false;
        ((l)v0).r = 0;
        ((l)v0).e = false;
        ((l)v0).d = 0;
        ((l)v0).t = false;
        j[] v1_1 = this.f;
        if(v1_1 != null) {
            v3 = v1_1.length;
            ((l)v0).f = new j[v3];
            ((l)v0).g = new HashMap(v3);
            while(v2 < v3) {
                j v4_1 = v1_1[v2].a();
                ((l)v0).f[v2] = v4_1;
                ((l)v0).g.put(v4_1.c(), v4_1);
                ++v2;
            }
        }

        return ((l)v0);
    }

    public long g() {
        if(this.e) {
            if(this.d == 0) {
            }
            else {
                return AnimationUtils.currentAnimationTimeMillis() - this.b;
            }
        }

        return 0;
    }

    public boolean h() {
        boolean v1 = true;
        if(this.d != 1) {
            if(this.v) {
            }
            else {
                v1 = false;
            }
        }

        return v1;
    }

    public void i() {
        this.q ^= 1;
        if(this.d == 1) {
            long v0 = AnimationUtils.currentAnimationTimeMillis();
            this.b = v0 - (this.x - (v0 - this.b));
        }
        else {
            this.a(true);
        }
    }

    static ThreadLocal j() {
        return l.i;
    }

    static ThreadLocal k() {
        return l.k;
    }

    static ThreadLocal l() {
        return l.j;
    }

    static ThreadLocal m() {
        return l.m;
    }

    static ThreadLocal n() {
        return l.l;
    }

    static long o() {
        return l.z;
    }

    private void p() {
        this.d();
        l.i.get().add(this);
        if(this.y > 0 && this.a != null) {
            Object v0 = this.a.clone();
            int v1 = ((ArrayList)v0).size();
            int v2;
            for(v2 = 0; v2 < v1; ++v2) {
                ((ArrayList)v0).get(v2).a(((a)this));
            }
        }
    }

    public String toString() {
        String v0_1 = "ValueAnimator@" + Integer.toHexString(this.hashCode());
        if(this.f != null) {
            int v1;
            for(v1 = 0; v1 < this.f.length; ++v1) {
                v0_1 = v0_1 + "\n    " + this.f[v1].toString();
            }
        }

        return v0_1;
    }
}

