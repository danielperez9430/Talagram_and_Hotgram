package android.support.f;

import android.animation.Animator$AnimatorListener;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.graphics.Path;
import android.support.v4.f.f;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class m implements Cloneable {
    final class android.support.f.m$1 extends g {
        android.support.f.m$1() {
            super();
        }

        public Path a(float arg2, float arg3, float arg4, float arg5) {
            Path v0 = new Path();
            v0.moveTo(arg2, arg3);
            v0.lineTo(arg4, arg5);
            return v0;
        }
    }

    class a {
        View a;
        String b;
        s c;
        al d;
        m e;

        a(View arg1, String arg2, m arg3, al arg4, s arg5) {
            super();
            this.a = arg1;
            this.b = arg2;
            this.c = arg5;
            this.d = arg4;
            this.e = arg3;
        }
    }

    public abstract class b {
        public b() {
            super();
        }
    }

    public interface c {
        void a(m arg1);

        void b(m arg1);

        void c(m arg1);

        void d(m arg1);
    }

    private static ThreadLocal A;
    private ViewGroup B;
    private int C;
    private boolean D;
    private boolean E;
    private ArrayList F;
    private ArrayList G;
    private b H;
    private android.support.v4.f.a I;
    private g J;
    long a;
    ArrayList b;
    ArrayList c;
    q d;
    boolean e;
    ArrayList f;
    p g;
    private static final int[] h;
    private static final g i;
    private String j;
    private long k;
    private TimeInterpolator l;
    private ArrayList m;
    private ArrayList n;
    private ArrayList o;
    private ArrayList p;
    private ArrayList q;
    private ArrayList r;
    private ArrayList s;
    private ArrayList t;
    private ArrayList u;
    private t v;
    private t w;
    private int[] x;
    private ArrayList y;
    private ArrayList z;

    static {
        m.h = new int[]{2, 1, 3, 4};
        m.i = new android.support.f.m$1();
        m.A = new ThreadLocal();
    }

    public m() {
        super();
        this.j = this.getClass().getName();
        this.k = -1;
        this.a = -1;
        this.l = null;
        this.b = new ArrayList();
        this.c = new ArrayList();
        this.m = null;
        this.n = null;
        this.o = null;
        this.p = null;
        this.q = null;
        this.r = null;
        this.s = null;
        this.t = null;
        this.u = null;
        this.v = new t();
        this.w = new t();
        this.d = null;
        this.x = m.h;
        this.B = null;
        this.e = false;
        this.f = new ArrayList();
        this.C = 0;
        this.D = false;
        this.E = false;
        this.F = null;
        this.G = new ArrayList();
        this.J = m.i;
    }

    private void a(Animator arg2, android.support.v4.f.a arg3) {
        if(arg2 != null) {
            arg2.addListener(new AnimatorListenerAdapter(arg3) {
                public void onAnimationEnd(Animator arg2) {
                    this.a.remove(arg2);
                    this.b.f.remove(arg2);
                }

                public void onAnimationStart(Animator arg2) {
                    this.b.f.add(arg2);
                }
            });
            this.a(arg2);
        }
    }

    protected void a(Animator arg6) {
        if(arg6 == null) {
            this.k();
        }
        else {
            long v2 = 0;
            if(this.b() >= v2) {
                arg6.setDuration(this.b());
            }

            if(this.c() >= v2) {
                arg6.setStartDelay(this.c());
            }

            if(this.d() != null) {
                arg6.setInterpolator(this.d());
            }

            arg6.addListener(new AnimatorListenerAdapter() {
                public void onAnimationEnd(Animator arg2) {
                    this.a.k();
                    arg2.removeListener(((Animator$AnimatorListener)this));
                }
            });
            arg6.start();
        }
    }

    private void a(t arg6, t arg7) {
        android.support.v4.f.a v0 = new android.support.v4.f.a(arg6.a);
        android.support.v4.f.a v1 = new android.support.v4.f.a(arg7.a);
        int v2;
        for(v2 = 0; v2 < this.x.length; ++v2) {
            switch(this.x[v2]) {
                case 1: {
                    this.a(v0, v1);
                    break;
                }
                case 2: {
                    this.a(v0, v1, arg6.d, arg7.d);
                    break;
                }
                case 3: {
                    this.a(v0, v1, arg6.b, arg7.b);
                    break;
                }
                case 4: {
                    this.a(v0, v1, arg6.c, arg7.c);
                    break;
                }
                default: {
                    break;
                }
            }
        }

        this.b(v0, v1);
    }

    private void a(android.support.v4.f.a arg8, android.support.v4.f.a arg9, SparseArray arg10, SparseArray arg11) {
        int v0 = arg10.size();
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            Object v2 = arg10.valueAt(v1);
            if(v2 != null && (this.a(((View)v2)))) {
                Object v3 = arg11.get(arg10.keyAt(v1));
                if(v3 != null && (this.a(((View)v3)))) {
                    Object v4 = arg8.get(v2);
                    Object v5 = arg9.get(v3);
                    if(v4 != null && v5 != null) {
                        this.y.add(v4);
                        this.z.add(v5);
                        arg8.remove(v2);
                        arg9.remove(v3);
                    }
                }
            }
        }
    }

    private void a(android.support.v4.f.a arg8, android.support.v4.f.a arg9, android.support.v4.f.a arg10, android.support.v4.f.a arg11) {
        int v0 = arg10.size();
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            Object v2 = arg10.c(v1);
            if(v2 != null && (this.a(((View)v2)))) {
                Object v3 = arg11.get(arg10.b(v1));
                if(v3 != null && (this.a(((View)v3)))) {
                    Object v4 = arg8.get(v2);
                    Object v5 = arg9.get(v3);
                    if(v4 != null && v5 != null) {
                        this.y.add(v4);
                        this.z.add(v5);
                        arg8.remove(v2);
                        arg9.remove(v3);
                    }
                }
            }
        }
    }

    private void a(android.support.v4.f.a arg5, android.support.v4.f.a arg6) {
        int v0;
        for(v0 = arg5.size() - 1; v0 >= 0; --v0) {
            Object v1 = arg5.b(v0);
            if(v1 != null && (this.a(((View)v1)))) {
                v1 = arg6.remove(v1);
                if(v1 != null && ((s)v1).b != null && (this.a(((s)v1).b))) {
                    this.y.add(arg5.d(v0));
                    this.z.add(v1);
                }
            }
        }
    }

    private void a(android.support.v4.f.a arg8, android.support.v4.f.a arg9, f arg10, f arg11) {
        int v0 = arg10.b();
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            Object v2 = arg10.c(v1);
            if(v2 != null && (this.a(((View)v2)))) {
                Object v3 = arg11.a(arg10.b(v1));
                if(v3 != null && (this.a(((View)v3)))) {
                    Object v4 = arg8.get(v2);
                    Object v5 = arg9.get(v3);
                    if(v4 != null && v5 != null) {
                        this.y.add(v4);
                        this.z.add(v5);
                        arg8.remove(v2);
                        arg9.remove(v3);
                    }
                }
            }
        }
    }

    private static void a(t arg3, View arg4, s arg5) {
        arg3.a.put(arg4, arg5);
        int v5 = arg4.getId();
        Object v0 = null;
        if(v5 >= 0) {
            if(arg3.b.indexOfKey(v5) >= 0) {
                arg3.b.put(v5, v0);
            }
            else {
                arg3.b.put(v5, arg4);
            }
        }

        String v5_1 = android.support.v4.view.t.q(arg4);
        if(v5_1 != null) {
            if(arg3.d.containsKey(v5_1)) {
                arg3.d.put(v5_1, v0);
            }
            else {
                arg3.d.put(v5_1, arg4);
            }
        }

        if((arg4.getParent() instanceof ListView)) {
            ViewParent v5_2 = arg4.getParent();
            if(((ListView)v5_2).getAdapter().hasStableIds()) {
                long v1 = ((ListView)v5_2).getItemIdAtPosition(((ListView)v5_2).getPositionForView(arg4));
                if(arg3.c.c(v1) >= 0) {
                    Object v4 = arg3.c.a(v1);
                    if(v4 != null) {
                        android.support.v4.view.t.a(((View)v4), false);
                        arg3.c.b(v1, v0);
                    }
                }
                else {
                    android.support.v4.view.t.a(arg4, true);
                    arg3.c.b(v1, arg4);
                }
            }
        }
    }

    boolean a(View arg6) {
        int v0 = arg6.getId();
        if(this.o != null && (this.o.contains(Integer.valueOf(v0)))) {
            return 0;
        }

        if(this.p != null && (this.p.contains(arg6))) {
            return 0;
        }

        if(this.q != null) {
            int v1 = this.q.size();
            int v3 = 0;
            while(v3 < v1) {
                if(this.q.get(v3).isInstance(arg6)) {
                    return 0;
                }
                else {
                    ++v3;
                    continue;
                }

                break;
            }
        }

        if(this.r != null && android.support.v4.view.t.q(arg6) != null && (this.r.contains(android.support.v4.view.t.q(arg6)))) {
            return 0;
        }

        if(this.b.size() == 0 && this.c.size() == 0 && (this.n == null || (this.n.isEmpty())) && (this.m == null || (this.m.isEmpty()))) {
            return 1;
        }

        if(!this.b.contains(Integer.valueOf(v0))) {
            if(this.c.contains(arg6)) {
            }
            else {
                if(this.m != null && (this.m.contains(android.support.v4.view.t.q(arg6)))) {
                    return 1;
                }

                if(this.n != null) {
                    v0 = 0;
                    while(v0 < this.n.size()) {
                        if(this.n.get(v0).isInstance(arg6)) {
                            return 1;
                        }
                        else {
                            ++v0;
                            continue;
                        }

                        return 0;
                    }
                }

                return 0;
            }
        }

        return 1;
    }

    private static boolean a(s arg0, s arg1, String arg2) {
        int v2_1;
        Object v0 = arg0.a.get(arg2);
        Object v1 = arg1.a.get(arg2);
        boolean v2 = true;
        if(v0 == null && v1 == null) {
            v2 = false;
        }
        else if(v0 != null) {
            if(v1 == null) {
            }
            else {
                v2_1 = 1 ^ v0.equals(v1);
            }
        }

        return ((boolean)v2_1);
    }

    public abstract void a(s arg1);

    public Animator a(ViewGroup arg1, s arg2, s arg3) {
        return null;
    }

    public m a(long arg1) {
        this.a = arg1;
        return this;
    }

    public m a(TimeInterpolator arg1) {
        this.l = arg1;
        return this;
    }

    public m a(c arg2) {
        if(this.F == null) {
            this.F = new ArrayList();
        }

        this.F.add(arg2);
        return this;
    }

    public s a(View arg2, boolean arg3) {
        if(this.d != null) {
            return this.d.a(arg2, arg3);
        }

        t v3 = arg3 ? this.v : this.w;
        return v3.a.get(arg2);
    }

    String a(String arg7) {
        arg7 = arg7 + this.getClass().getSimpleName() + "@" + Integer.toHexString(this.hashCode()) + ": ";
        long v2 = -1;
        if(this.a != v2) {
            arg7 = arg7 + "dur(" + this.a + ") ";
        }

        if(this.k != v2) {
            arg7 = arg7 + "dly(" + this.k + ") ";
        }

        if(this.l != null) {
            arg7 = arg7 + "interp(" + this.l + ") ";
        }

        if(this.b.size() > 0 || this.c.size() > 0) {
            arg7 = arg7 + "tgts(";
            int v1 = 0;
            if(this.b.size() > 0) {
                String v0_1 = arg7;
                int v7;
                for(v7 = 0; v7 < this.b.size(); ++v7) {
                    if(v7 > 0) {
                        v0_1 = v0_1 + ", ";
                    }

                    v0_1 = v0_1 + this.b.get(v7);
                }

                arg7 = v0_1;
            }

            if(this.c.size() > 0) {
                while(v1 < this.c.size()) {
                    if(v1 > 0) {
                        arg7 = arg7 + ", ";
                    }

                    arg7 = arg7 + this.c.get(v1);
                    ++v1;
                }
            }

            arg7 = arg7 + ")";
        }

        return arg7;
    }

    public void a(g arg1) {
        if(arg1 == null) {
            arg1 = m.i;
        }

        this.J = arg1;
    }

    public void a(b arg1) {
        this.H = arg1;
    }

    public void a(p arg1) {
        this.g = arg1;
    }

    void a(ViewGroup arg11) {
        int v5_1;
        this.y = new ArrayList();
        this.z = new ArrayList();
        this.a(this.v, this.w);
        android.support.v4.f.a v0 = m.q();
        int v1 = v0.size();
        al v2 = ad.b(((View)arg11));
        --v1;
        while(v1 >= 0) {
            Object v4 = v0.b(v1);
            if(v4 != null) {
                Object v5 = v0.get(v4);
                if(v5 != null && ((a)v5).a != null && (v2.equals(((a)v5).d))) {
                    s v6 = ((a)v5).c;
                    View v7 = ((a)v5).a;
                    s v8 = this.a(v7, true);
                    s v7_1 = this.b(v7, true);
                    if(v8 == null && v7_1 == null) {
                        goto label_35;
                    }
                    else if(((a)v5).e.a(v6, v7_1)) {
                        v5_1 = 1;
                    }
                    else {
                    label_35:
                        v5_1 = 0;
                    }

                    if(v5_1 == 0) {
                        goto label_45;
                    }

                    if(!((Animator)v4).isRunning()) {
                        if(((Animator)v4).isStarted()) {
                        }
                        else {
                            v0.remove(v4);
                            goto label_45;
                        }
                    }

                    ((Animator)v4).cancel();
                }
            }

        label_45:
            --v1;
        }

        this.a(arg11, this.v, this.w, this.y, this.z);
        this.e();
    }

    public boolean a(s arg7, s arg8) {
        boolean v0 = false;
        if(arg7 != null && arg8 != null) {
            String[] v2 = this.a();
            if(v2 != null) {
                int v3 = v2.length;
                int v4 = 0;
                while(true) {
                    if(v4 >= v3) {
                        return v0;
                    }
                    else if(m.a(arg7, arg8, v2[v4])) {
                    }
                    else {
                        ++v4;
                        continue;
                    }

                    break;
                }
            }
            else {
                Iterator v2_1 = arg7.a.keySet().iterator();
                do {
                    if(!v2_1.hasNext()) {
                        return v0;
                    }
                    else if(!m.a(arg7, arg8, v2_1.next())) {
                        continue;
                    }

                    break;
                }
                while(true);
            }

            v0 = true;
        }

        return v0;
    }

    protected void a(ViewGroup arg21, t arg22, t arg23, ArrayList arg24, ArrayList arg25) {
        s v5_3;
        Animator v10_2;
        int v18;
        int v16;
        s v11;
        View v15;
        Object v3;
        m v6 = this;
        ViewGroup v7 = arg21;
        android.support.v4.f.a v8 = m.q();
        SparseIntArray v9 = new SparseIntArray();
        int v10 = arg24.size();
        long v0 = 9223372036854775807L;
        int v12 = 0;
        while(v12 < v10) {
            Object v2 = arg24.get(v12);
            v3 = arg25.get(v12);
            if(v2 != null && !((s)v2).c.contains(v6)) {
                v2 = null;
            }

            if(v3 != null && !((s)v3).c.contains(v6)) {
                v3 = null;
            }

            if(v2 != null || v3 != null) {
                int v5 = v2 == null || v3 == null || (v6.a(((s)v2), ((s)v3))) ? 1 : 0;
                if(v5 != 0) {
                    Animator v5_1 = v6.a(v7, ((s)v2), ((s)v3));
                    if(v5_1 == null) {
                        goto label_25;
                    }

                    if(v3 != null) {
                        v15 = ((s)v3).b;
                        String[] v4 = this.a();
                        if(v15 == null || v4 == null || v4.length <= 0) {
                            v16 = v10;
                            v18 = v12;
                            v5_1 = v5_1;
                            v11 = null;
                        }
                        else {
                            v11 = new s();
                            v11.b = v15;
                            Animator v17 = v5_1;
                            v16 = v10;
                            Object v5_2 = arg23.a.get(v15);
                            if(v5_2 != null) {
                                v10 = 0;
                                while(v10 < v4.length) {
                                    v11.a.put(v4[v10], ((s)v5_2).a.get(v4[v10]));
                                    ++v10;
                                    v12 = v12;
                                    v5_2 = v5_2;
                                }
                            }

                            v18 = v12;
                            int v4_1 = v8.size();
                            for(v5 = 0; v5 < v4_1; ++v5) {
                                Object v10_1 = v8.get(v8.b(v5));
                                if(((a)v10_1).c != null && ((a)v10_1).a == v15 && (((a)v10_1).b.equals(this.p())) && (((a)v10_1).c.equals(v11))) {
                                    v5_1 = null;
                                    goto label_99;
                                }
                            }

                            v5_1 = v17;
                        }

                    label_99:
                        v10_2 = v5_1;
                        v5_3 = v11;
                    }
                    else {
                        v16 = v10;
                        v18 = v12;
                        v15 = ((s)v2).b;
                        v10_2 = v5_1;
                        v5_3 = null;
                    }

                    if(v10_2 == null) {
                        goto label_132;
                    }

                    if(v6.g != null) {
                        long v2_1 = v6.g.a(v7, v6, ((s)v2), ((s)v3));
                        v9.put(v6.G.size(), ((int)v2_1));
                        v0 = Math.min(v2_1, v0);
                    }

                    v8.put(v10_2, new a(v15, this.p(), this, ad.b(((View)arg21)), v5_3));
                    v6.G.add(v10_2);
                    v0 = v0;
                    goto label_132;
                }

            label_25:
                v16 = v10;
                v18 = v12;
            }
            else {
                goto label_25;
            }

        label_132:
            v12 = v18 + 1;
            v10 = v16;
        }

        if(v0 != 0) {
            int v2_2;
            for(v2_2 = 0; v2_2 < v9.size(); ++v2_2) {
                v3 = v6.G.get(v9.keyAt(v2_2));
                ((Animator)v3).setStartDelay((((long)v9.valueAt(v2_2))) - v0 + ((Animator)v3).getStartDelay());
            }
        }
    }

    public String[] a() {
        return null;
    }

    void a(ViewGroup arg6, boolean arg7) {
        Object v0_1;
        this.a(arg7);
        int v1 = 0;
        if(this.b.size() > 0 || this.c.size() > 0) {
            if(this.m != null && !this.m.isEmpty()) {
                goto label_19;
            }

            if(this.n != null) {
                if(this.n.isEmpty()) {
                }
                else {
                label_19:
                    this.c(((View)arg6), arg7);
                    goto label_72;
                }
            }

            int v0;
            for(v0 = 0; v0 < this.b.size(); ++v0) {
                View v2 = arg6.findViewById(this.b.get(v0).intValue());
                if(v2 != null) {
                    s v3 = new s();
                    v3.b = v2;
                    if(arg7) {
                        this.a(v3);
                    }
                    else {
                        this.b(v3);
                    }

                    v3.c.add(this);
                    this.c(v3);
                    t v4 = arg7 ? this.v : this.w;
                    m.a(v4, v2, v3);
                }
            }

            int v6;
            for(v6 = 0; v6 < this.c.size(); ++v6) {
                v0_1 = this.c.get(v6);
                s v2_1 = new s();
                v2_1.b = ((View)v0_1);
                if(arg7) {
                    this.a(v2_1);
                }
                else {
                    this.b(v2_1);
                }

                v2_1.c.add(this);
                this.c(v2_1);
                t v3_1 = arg7 ? this.v : this.w;
                m.a(v3_1, ((View)v0_1), v2_1);
            }
        }
        else {
            goto label_19;
        }

    label_72:
        if(!arg7 && this.I != null) {
            v6 = this.I.size();
            ArrayList v7 = new ArrayList(v6);
            for(v0 = 0; v0 < v6; ++v0) {
                v7.add(this.v.d.remove(this.I.b(v0)));
            }

            while(v1 < v6) {
                v0_1 = v7.get(v1);
                if(v0_1 != null) {
                    this.v.d.put(this.I.c(v1), v0_1);
                }

                ++v1;
            }
        }
    }

    void a(boolean arg1) {
        t v1;
        if(arg1) {
            this.v.a.clear();
            this.v.b.clear();
            v1 = this.v;
        }
        else {
            this.w.a.clear();
            this.w.b.clear();
            v1 = this.w;
        }

        v1.c.c();
    }

    private void b(android.support.v4.f.a arg6, android.support.v4.f.a arg7) {
        Object v3;
        int v0 = 0;
        int v1 = 0;
        while(true) {
            v3 = null;
            if(v1 < arg6.size()) {
                Object v2 = arg6.c(v1);
                if(this.a(((s)v2).b)) {
                    this.y.add(v2);
                    this.z.add(v3);
                }

                ++v1;
                continue;
            }

            break;
        }

        while(v0 < arg7.size()) {
            Object v6 = arg7.c(v0);
            if(this.a(((s)v6).b)) {
                this.z.add(v6);
                this.y.add(v3);
            }

            ++v0;
        }
    }

    public abstract void b(s arg1);

    public long b() {
        return this.a;
    }

    s b(View arg7, boolean arg8) {
        if(this.d != null) {
            return this.d.b(arg7, arg8);
        }

        ArrayList v0 = arg8 ? this.y : this.z;
        s v1 = null;
        if(v0 == null) {
            return v1;
        }

        int v2 = v0.size();
        int v3 = -1;
        int v4 = 0;
        while(v4 < v2) {
            Object v5 = v0.get(v4);
            if(v5 == null) {
                return v1;
            }
            else if(((s)v5).b == arg7) {
                v3 = v4;
            }
            else {
                ++v4;
                continue;
            }

            break;
        }

        if(v3 >= 0) {
            ArrayList v7 = arg8 ? this.z : this.y;
            Object v1_1 = v7.get(v3);
        }

        return v1;
    }

    public m b(long arg1) {
        this.k = arg1;
        return this;
    }

    public m b(c arg2) {
        if(this.F == null) {
            return this;
        }

        this.F.remove(arg2);
        if(this.F.size() == 0) {
            this.F = null;
        }

        return this;
    }

    public m b(View arg2) {
        this.c.add(arg2);
        return this;
    }

    private void c(View arg6, boolean arg7) {
        int v1;
        if(arg6 == null) {
            return;
        }

        int v0 = arg6.getId();
        if(this.o != null && (this.o.contains(Integer.valueOf(v0)))) {
            return;
        }

        if(this.p != null && (this.p.contains(arg6))) {
            return;
        }

        int v2 = 0;
        if(this.q != null) {
            v1 = this.q.size();
            int v3 = 0;
            while(v3 < v1) {
                if(this.q.get(v3).isInstance(arg6)) {
                    return;
                }
                else {
                    ++v3;
                    continue;
                }

                break;
            }
        }

        if((arg6.getParent() instanceof ViewGroup)) {
            s v1_1 = new s();
            v1_1.b = arg6;
            if(arg7) {
                this.a(v1_1);
            }
            else {
                this.b(v1_1);
            }

            v1_1.c.add(this);
            this.c(v1_1);
            t v3_1 = arg7 ? this.v : this.w;
            m.a(v3_1, arg6, v1_1);
        }

        if((arg6 instanceof ViewGroup)) {
            if(this.s != null && (this.s.contains(Integer.valueOf(v0)))) {
                return;
            }

            if(this.t != null && (this.t.contains(arg6))) {
                return;
            }

            if(this.u != null) {
                v0 = this.u.size();
                v1 = 0;
                while(v1 < v0) {
                    if(this.u.get(v1).isInstance(arg6)) {
                        return;
                    }
                    else {
                        ++v1;
                        continue;
                    }

                    break;
                }
            }

            while(v2 < ((ViewGroup)arg6).getChildCount()) {
                this.c(((ViewGroup)arg6).getChildAt(v2), arg7);
                ++v2;
            }
        }
    }

    void c(s arg6) {
        if(this.g != null && !arg6.a.isEmpty()) {
            String[] v0 = this.g.a();
            if(v0 == null) {
                return;
            }
            else {
                int v1 = 0;
                int v2 = 0;
                while(true) {
                    if(v2 >= v0.length) {
                        break;
                    }
                    else if(!arg6.a.containsKey(v0[v2])) {
                    }
                    else {
                        ++v2;
                        continue;
                    }

                    goto label_21;
                }

                v1 = 1;
            label_21:
                if(v1 != 0) {
                    return;
                }

                this.g.a(arg6);
            }
        }
    }

    public long c() {
        return this.k;
    }

    public m c(View arg2) {
        this.c.remove(arg2);
        return this;
    }

    public Object clone() {
        return this.o();
    }

    public TimeInterpolator d() {
        return this.l;
    }

    public void d(View arg6) {
        if(!this.E) {
            android.support.v4.f.a v0 = m.q();
            int v1 = v0.size();
            al v6 = ad.b(arg6);
            --v1;
            while(v1 >= 0) {
                Object v3 = v0.c(v1);
                if(((a)v3).a != null && (v6.equals(((a)v3).d))) {
                    android.support.f.a.a(v0.b(v1));
                }

                --v1;
            }

            if(this.F != null && this.F.size() > 0) {
                Object v6_1 = this.F.clone();
                int v0_1 = ((ArrayList)v6_1).size();
                for(v1 = 0; v1 < v0_1; ++v1) {
                    ((ArrayList)v6_1).get(v1).b(this);
                }
            }

            this.D = true;
        }
    }

    protected void e() {
        this.j();
        android.support.v4.f.a v0 = m.q();
        Iterator v1 = this.G.iterator();
        while(v1.hasNext()) {
            Object v2 = v1.next();
            if(!v0.containsKey(v2)) {
                continue;
            }

            this.j();
            this.a(((Animator)v2), v0);
        }

        this.G.clear();
        this.k();
    }

    public void e(View arg6) {
        if(this.D) {
            if(!this.E) {
                android.support.v4.f.a v0 = m.q();
                int v2 = v0.size();
                al v6 = ad.b(arg6);
                --v2;
                while(v2 >= 0) {
                    Object v3 = v0.c(v2);
                    if(((a)v3).a != null && (v6.equals(((a)v3).d))) {
                        android.support.f.a.b(v0.b(v2));
                    }

                    --v2;
                }

                if(this.F == null) {
                    goto label_34;
                }

                if(this.F.size() <= 0) {
                    goto label_34;
                }

                Object v6_1 = this.F.clone();
                int v0_1 = ((ArrayList)v6_1).size();
                for(v2 = 0; v2 < v0_1; ++v2) {
                    ((ArrayList)v6_1).get(v2).c(this);
                }
            }

        label_34:
            this.D = false;
        }
    }

    public List f() {
        return this.b;
    }

    public List g() {
        return this.c;
    }

    public List h() {
        return this.m;
    }

    public List i() {
        return this.n;
    }

    protected void j() {
        if(this.C == 0) {
            if(this.F != null && this.F.size() > 0) {
                Object v0 = this.F.clone();
                int v2 = ((ArrayList)v0).size();
                int v3;
                for(v3 = 0; v3 < v2; ++v3) {
                    ((ArrayList)v0).get(v3).d(this);
                }
            }

            this.E = false;
        }

        ++this.C;
    }

    protected void k() {
        Object v3_1;
        --this.C;
        if(this.C == 0) {
            if(this.F != null && this.F.size() > 0) {
                Object v0 = this.F.clone();
                int v3 = ((ArrayList)v0).size();
                int v4;
                for(v4 = 0; v4 < v3; ++v4) {
                    ((ArrayList)v0).get(v4).a(this);
                }
            }

            int v0_1;
            for(v0_1 = 0; v0_1 < this.v.c.b(); ++v0_1) {
                v3_1 = this.v.c.c(v0_1);
                if(v3_1 != null) {
                    android.support.v4.view.t.a(((View)v3_1), false);
                }
            }

            for(v0_1 = 0; v0_1 < this.w.c.b(); ++v0_1) {
                v3_1 = this.w.c.c(v0_1);
                if(v3_1 != null) {
                    android.support.v4.view.t.a(((View)v3_1), false);
                }
            }

            this.E = true;
        }
    }

    public g l() {
        return this.J;
    }

    public b m() {
        return this.H;
    }

    public p n() {
        return this.g;
    }

    public m o() {
        ArrayList v0 = null;
        try {
            Object v1 = super.clone();
            ((m)v1).G = new ArrayList();
            ((m)v1).v = new t();
            ((m)v1).w = new t();
            ((m)v1).y = v0;
            ((m)v1).z = v0;
            return ((m)v1);
        }
        catch(CloneNotSupportedException ) {
            return ((m)v0);
        }
    }

    public String p() {
        return this.j;
    }

    private static android.support.v4.f.a q() {
        Object v0 = m.A.get();
        if(v0 == null) {
            android.support.v4.f.a v0_1 = new android.support.v4.f.a();
            m.A.set(v0_1);
        }

        return ((android.support.v4.f.a)v0);
    }

    public String toString() {
        return this.a("");
    }
}

