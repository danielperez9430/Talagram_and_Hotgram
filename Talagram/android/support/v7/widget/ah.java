package android.support.v7.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.support.v4.view.t;
import android.view.View;
import android.view.ViewPropertyAnimator;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ah extends bd {
    class a {
        public w a;
        public w b;
        public int c;
        public int d;
        public int e;
        public int f;

        a(w arg1, w arg2, int arg3, int arg4, int arg5, int arg6) {
            this(arg1, arg2);
            this.c = arg3;
            this.d = arg4;
            this.e = arg5;
            this.f = arg6;
        }

        private a(w arg1, w arg2) {
            super();
            this.a = arg1;
            this.b = arg2;
        }

        public String toString() {
            return "ChangeInfo{oldHolder=" + this.a + ", newHolder=" + this.b + ", fromX=" + this.c + ", fromY=" + this.d + ", toX=" + this.e + ", toY=" + this.f + '}';
        }
    }

    class b {
        public w a;
        public int b;
        public int c;
        public int d;
        public int e;

        b(w arg1, int arg2, int arg3, int arg4, int arg5) {
            super();
            this.a = arg1;
            this.b = arg2;
            this.c = arg3;
            this.d = arg4;
            this.e = arg5;
        }
    }

    ArrayList a;
    ArrayList b;
    ArrayList c;
    ArrayList d;
    ArrayList e;
    ArrayList f;
    ArrayList g;
    private static TimeInterpolator i;
    private ArrayList j;
    private ArrayList k;
    private ArrayList l;
    private ArrayList m;

    public ah() {
        super();
        this.j = new ArrayList();
        this.k = new ArrayList();
        this.l = new ArrayList();
        this.m = new ArrayList();
        this.a = new ArrayList();
        this.b = new ArrayList();
        this.c = new ArrayList();
        this.d = new ArrayList();
        this.e = new ArrayList();
        this.f = new ArrayList();
        this.g = new ArrayList();
    }

    private void a(List arg4, w arg5) {
        int v0;
        for(v0 = arg4.size() - 1; v0 >= 0; --v0) {
            Object v1 = arg4.get(v0);
            if((this.a(((a)v1), arg5)) && ((a)v1).a == null && ((a)v1).b == null) {
                arg4.remove(v1);
            }
        }
    }

    private boolean a(a arg5, w arg6) {
        w v2 = null;
        boolean v3 = false;
        if(arg5.b == arg6) {
            arg5.b = v2;
        }
        else if(arg5.a == arg6) {
            arg5.a = v2;
            v3 = true;
        }
        else {
            return 0;
        }

        arg6.itemView.setAlpha(1f);
        arg6.itemView.setTranslationX(0f);
        arg6.itemView.setTranslationY(0f);
        this.a(arg6, v3);
        return 1;
    }

    public void a() {
        ArrayList v5;
        int v0 = this.j.isEmpty() ^ 1;
        int v1 = this.l.isEmpty() ^ 1;
        int v2 = this.m.isEmpty() ^ 1;
        int v3 = this.k.isEmpty() ^ 1;
        if(v0 == 0 && v1 == 0 && v3 == 0 && v2 == 0) {
            return;
        }

        Iterator v4 = this.j.iterator();
        while(v4.hasNext()) {
            this.u(v4.next());
        }

        this.j.clear();
        if(v1 != 0) {
            v5 = new ArrayList();
            v5.addAll(this.l);
            this.b.add(v5);
            this.l.clear();
            android.support.v7.widget.ah$1 v6 = new Runnable(v5) {
                public void run() {
                    Iterator v0 = this.a.iterator();
                    while(v0.hasNext()) {
                        Object v1 = v0.next();
                        this.b.b(((b)v1).a, ((b)v1).b, ((b)v1).c, ((b)v1).d, ((b)v1).e);
                    }

                    this.a.clear();
                    this.b.b.remove(this.a);
                }
            };
            if(v0 != 0) {
                t.a(v5.get(0).a.itemView, ((Runnable)v6), this.g());
            }
            else {
                ((Runnable)v6).run();
            }
        }

        if(v2 != 0) {
            v5 = new ArrayList();
            v5.addAll(this.m);
            this.c.add(v5);
            this.m.clear();
            android.support.v7.widget.ah$2 v6_1 = new Runnable(v5) {
                public void run() {
                    Iterator v0 = this.a.iterator();
                    while(v0.hasNext()) {
                        this.b.a(v0.next());
                    }

                    this.a.clear();
                    this.b.c.remove(this.a);
                }
            };
            if(v0 != 0) {
                t.a(v5.get(0).a.itemView, ((Runnable)v6_1), this.g());
            }
            else {
                ((Runnable)v6_1).run();
            }
        }

        if(v3 != 0) {
            ArrayList v3_1 = new ArrayList();
            v3_1.addAll(this.k);
            this.a.add(v3_1);
            this.k.clear();
            android.support.v7.widget.ah$3 v5_1 = new Runnable(v3_1) {
                public void run() {
                    Iterator v0 = this.a.iterator();
                    while(v0.hasNext()) {
                        this.b.c(v0.next());
                    }

                    this.a.clear();
                    this.b.a.remove(this.a);
                }
            };
            if(v0 == 0 && v1 == 0) {
                if(v2 != 0) {
                }
                else {
                    ((Runnable)v5_1).run();
                    return;
                }
            }

            long v6_2 = 0;
            long v8 = v0 != 0 ? this.g() : v6_2;
            long v0_1 = v1 != 0 ? this.e() : v6_2;
            if(v2 != 0) {
                v6_2 = this.h();
            }

            t.a(v3_1.get(0).itemView, ((Runnable)v5_1), v8 + Math.max(v0_1, v6_2));
        }
    }

    void a(a arg7) {
        w v0 = arg7.a;
        View v1 = null;
        View v0_1 = v0 == null ? v1 : v0.itemView;
        w v2 = arg7.b;
        if(v2 != null) {
            v1 = v2.itemView;
        }

        if(v0_1 != null) {
            ViewPropertyAnimator v3 = v0_1.animate().setDuration(this.h());
            this.g.add(arg7.a);
            v3.translationX(((float)(arg7.e - arg7.c)));
            v3.translationY(((float)(arg7.f - arg7.d)));
            v3.alpha(0f).setListener(new AnimatorListenerAdapter(arg7, v3, v0_1) {
                public void onAnimationEnd(Animator arg3) {
                    this.b.setListener(null);
                    this.c.setAlpha(1f);
                    this.c.setTranslationX(0f);
                    this.c.setTranslationY(0f);
                    this.d.a(this.a.a, true);
                    this.d.g.remove(this.a.a);
                    this.d.c();
                }

                public void onAnimationStart(Animator arg3) {
                    this.d.b(this.a.a, true);
                }
            }).start();
        }

        if(v1 != null) {
            ViewPropertyAnimator v0_2 = v1.animate();
            this.g.add(arg7.b);
            v0_2.translationX(0f).translationY(0f).setDuration(this.h()).alpha(1f).setListener(new AnimatorListenerAdapter(arg7, v0_2, v1) {
                public void onAnimationEnd(Animator arg3) {
                    this.b.setListener(null);
                    this.c.setAlpha(1f);
                    this.c.setTranslationX(0f);
                    this.c.setTranslationY(0f);
                    this.d.a(this.a.b, false);
                    this.d.g.remove(this.a.b);
                    this.d.c();
                }

                public void onAnimationStart(Animator arg3) {
                    this.d.b(this.a.b, false);
                }
            }).start();
        }
    }

    void a(List arg3) {
        int v0;
        for(v0 = arg3.size() - 1; v0 >= 0; --v0) {
            arg3.get(v0).itemView.animate().cancel();
        }
    }

    public boolean a(w arg2) {
        this.v(arg2);
        this.j.add(arg2);
        return 1;
    }

    public boolean a(w arg9, int arg10, int arg11, int arg12, int arg13) {
        View v0 = arg9.itemView;
        int v4 = arg10 + (((int)arg9.itemView.getTranslationX()));
        int v5 = arg11 + (((int)arg9.itemView.getTranslationY()));
        this.v(arg9);
        arg10 = arg12 - v4;
        arg11 = arg13 - v5;
        if(arg10 == 0 && arg11 == 0) {
            this.j(arg9);
            return 0;
        }

        if(arg10 != 0) {
            v0.setTranslationX(((float)(-arg10)));
        }

        if(arg11 != 0) {
            v0.setTranslationY(((float)(-arg11)));
        }

        this.l.add(new b(arg9, v4, v5, arg12, arg13));
        return 1;
    }

    public boolean a(w arg10, w arg11, int arg12, int arg13, int arg14, int arg15) {
        if(arg10 == arg11) {
            return this.a(arg10, arg12, arg13, arg14, arg15);
        }

        float v0 = arg10.itemView.getTranslationX();
        float v1 = arg10.itemView.getTranslationY();
        float v2 = arg10.itemView.getAlpha();
        this.v(arg10);
        int v3 = ((int)((((float)(arg14 - arg12))) - v0));
        int v4 = ((int)((((float)(arg15 - arg13))) - v1));
        arg10.itemView.setTranslationX(v0);
        arg10.itemView.setTranslationY(v1);
        arg10.itemView.setAlpha(v2);
        if(arg11 != null) {
            this.v(arg11);
            arg11.itemView.setTranslationX(((float)(-v3)));
            arg11.itemView.setTranslationY(((float)(-v4)));
            arg11.itemView.setAlpha(0f);
        }

        this.m.add(new a(arg10, arg11, arg12, arg13, arg14, arg15));
        return 1;
    }

    public boolean a(w arg2, List arg3) {
        boolean v2 = !arg3.isEmpty() || (super.a(arg2, arg3)) ? true : false;
        return v2;
    }

    private void b(a arg2) {
        if(arg2.a != null) {
            this.a(arg2, arg2.a);
        }

        if(arg2.b != null) {
            this.a(arg2, arg2.b);
        }
    }

    void b(w arg8, int arg9, int arg10, int arg11, int arg12) {
        View v4 = arg8.itemView;
        int v3 = arg11 - arg9;
        int v5 = arg12 - arg10;
        if(v3 != 0) {
            v4.animate().translationX(0f);
        }

        if(v5 != 0) {
            v4.animate().translationY(0f);
        }

        ViewPropertyAnimator v6 = v4.animate();
        this.e.add(arg8);
        v6.setDuration(this.e()).setListener(new AnimatorListenerAdapter(arg8, v3, v4, v5, v6) {
            public void onAnimationCancel(Animator arg2) {
                if(this.b != 0) {
                    this.c.setTranslationX(0f);
                }

                if(this.d != 0) {
                    this.c.setTranslationY(0f);
                }
            }

            public void onAnimationEnd(Animator arg2) {
                this.e.setListener(null);
                this.f.j(this.a);
                this.f.e.remove(this.a);
                this.f.c();
            }

            public void onAnimationStart(Animator arg2) {
                this.f.m(this.a);
            }
        }).start();
    }

    public boolean b() {
        boolean v0 = !this.k.isEmpty() || !this.m.isEmpty() || !this.l.isEmpty() || !this.j.isEmpty() || !this.e.isEmpty() || !this.f.isEmpty() || !this.d.isEmpty() || !this.g.isEmpty() || !this.b.isEmpty() || !this.a.isEmpty() || !this.c.isEmpty() ? true : false;
        return v0;
    }

    public boolean b(w arg3) {
        this.v(arg3);
        arg3.itemView.setAlpha(0f);
        this.k.add(arg3);
        return 1;
    }

    void c() {
        if(!this.b()) {
            this.i();
        }
    }

    void c(w arg6) {
        View v0 = arg6.itemView;
        ViewPropertyAnimator v1 = v0.animate();
        this.d.add(arg6);
        v1.alpha(1f).setDuration(this.f()).setListener(new AnimatorListenerAdapter(arg6, v0, v1) {
            public void onAnimationCancel(Animator arg2) {
                this.b.setAlpha(1f);
            }

            public void onAnimationEnd(Animator arg2) {
                this.c.setListener(null);
                this.d.k(this.a);
                this.d.d.remove(this.a);
                this.d.c();
            }

            public void onAnimationStart(Animator arg2) {
                this.d.n(this.a);
            }
        }).start();
    }

    public void d(w arg8) {
        Object v4;
        View v0 = arg8.itemView;
        v0.animate().cancel();
        int v1;
        for(v1 = this.l.size() - 1; v1 >= 0; --v1) {
            if(this.l.get(v1).a == arg8) {
                v0.setTranslationY(0f);
                v0.setTranslationX(0f);
                this.j(arg8);
                this.l.remove(v1);
            }
        }

        this.a(this.m, arg8);
        float v3 = 1f;
        if(this.j.remove(arg8)) {
            v0.setAlpha(v3);
            this.i(arg8);
        }

        if(this.k.remove(arg8)) {
            v0.setAlpha(v3);
            this.k(arg8);
        }

        for(v1 = this.c.size() - 1; v1 >= 0; --v1) {
            v4 = this.c.get(v1);
            this.a(((List)v4), arg8);
            if(((ArrayList)v4).isEmpty()) {
                this.c.remove(v1);
            }
        }

        for(v1 = this.b.size() - 1; v1 >= 0; --v1) {
            v4 = this.b.get(v1);
            int v5 = ((ArrayList)v4).size() - 1;
            while(v5 >= 0) {
                if(((ArrayList)v4).get(v5).a == arg8) {
                    v0.setTranslationY(0f);
                    v0.setTranslationX(0f);
                    this.j(arg8);
                    ((ArrayList)v4).remove(v5);
                    if(((ArrayList)v4).isEmpty()) {
                        this.b.remove(v1);
                    }
                }
                else {
                    --v5;
                    continue;
                }

                break;
            }
        }

        for(v1 = this.a.size() - 1; v1 >= 0; --v1) {
            Object v2 = this.a.get(v1);
            if(((ArrayList)v2).remove(arg8)) {
                v0.setAlpha(v3);
                this.k(arg8);
                if(((ArrayList)v2).isEmpty()) {
                    this.a.remove(v1);
                }
            }
        }

        this.f.remove(arg8);
        this.d.remove(arg8);
        this.g.remove(arg8);
        this.e.remove(arg8);
        this.c();
    }

    public void d() {
        Object v1;
        Object v3_1;
        float v2_1;
        int v0;
        for(v0 = this.l.size() - 1; v0 >= 0; --v0) {
            Object v2 = this.l.get(v0);
            View v3 = ((b)v2).a.itemView;
            v3.setTranslationY(0f);
            v3.setTranslationX(0f);
            this.j(((b)v2).a);
            this.l.remove(v0);
        }

        for(v0 = this.j.size() - 1; v0 >= 0; --v0) {
            this.i(this.j.get(v0));
            this.j.remove(v0);
        }

        for(v0 = this.k.size() - 1; true; --v0) {
            v2_1 = 1f;
            if(v0 < 0) {
                break;
            }

            v3_1 = this.k.get(v0);
            ((w)v3_1).itemView.setAlpha(v2_1);
            this.k(((w)v3_1));
            this.k.remove(v0);
        }

        for(v0 = this.m.size() - 1; v0 >= 0; --v0) {
            this.b(this.m.get(v0));
        }

        this.m.clear();
        if(!this.b()) {
            return;
        }

        for(v0 = this.b.size() - 1; v0 >= 0; --v0) {
            v3_1 = this.b.get(v0);
            int v4;
            for(v4 = ((ArrayList)v3_1).size() - 1; v4 >= 0; --v4) {
                Object v5 = ((ArrayList)v3_1).get(v4);
                View v6 = ((b)v5).a.itemView;
                v6.setTranslationY(0f);
                v6.setTranslationX(0f);
                this.j(((b)v5).a);
                ((ArrayList)v3_1).remove(v4);
                if(((ArrayList)v3_1).isEmpty()) {
                    this.b.remove(v3_1);
                }
            }
        }

        for(v0 = this.a.size() - 1; v0 >= 0; --v0) {
            v1 = this.a.get(v0);
            int v3_2;
            for(v3_2 = ((ArrayList)v1).size() - 1; v3_2 >= 0; --v3_2) {
                Object v4_1 = ((ArrayList)v1).get(v3_2);
                ((w)v4_1).itemView.setAlpha(v2_1);
                this.k(((w)v4_1));
                ((ArrayList)v1).remove(v3_2);
                if(((ArrayList)v1).isEmpty()) {
                    this.a.remove(v1);
                }
            }
        }

        for(v0 = this.c.size() - 1; v0 >= 0; --v0) {
            v1 = this.c.get(v0);
            int v2_2;
            for(v2_2 = ((ArrayList)v1).size() - 1; v2_2 >= 0; --v2_2) {
                this.b(((ArrayList)v1).get(v2_2));
                if(((ArrayList)v1).isEmpty()) {
                    this.c.remove(v1);
                }
            }
        }

        this.a(this.f);
        this.a(this.e);
        this.a(this.d);
        this.a(this.g);
        this.i();
    }

    private void u(w arg5) {
        View v0 = arg5.itemView;
        ViewPropertyAnimator v1 = v0.animate();
        this.f.add(arg5);
        v1.setDuration(this.g()).alpha(0f).setListener(new AnimatorListenerAdapter(arg5, v1, v0) {
            public void onAnimationEnd(Animator arg2) {
                this.b.setListener(null);
                this.c.setAlpha(1f);
                this.d.i(this.a);
                this.d.f.remove(this.a);
                this.d.c();
            }

            public void onAnimationStart(Animator arg2) {
                this.d.l(this.a);
            }
        }).start();
    }

    private void v(w arg3) {
        if(ah.i == null) {
            ah.i = new ValueAnimator().getInterpolator();
        }

        arg3.itemView.animate().setInterpolator(ah.i);
        this.d(arg3);
    }
}

