package android.support.f;

import android.support.v4.view.t;
import android.view.View$OnAttachStateChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver$OnPreDrawListener;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class o {
    class a implements View$OnAttachStateChangeListener, ViewTreeObserver$OnPreDrawListener {
        m a;
        ViewGroup b;

        a(m arg1, ViewGroup arg2) {
            super();
            this.a = arg1;
            this.b = arg2;
        }

        private void a() {
            this.b.getViewTreeObserver().removeOnPreDrawListener(((ViewTreeObserver$OnPreDrawListener)this));
            this.b.removeOnAttachStateChangeListener(((View$OnAttachStateChangeListener)this));
        }

        public boolean onPreDraw() {
            ArrayList v2_1;
            this.a();
            if(!o.a.remove(this.b)) {
                return 1;
            }

            android.support.v4.f.a v0 = o.a();
            Object v2 = v0.get(this.b);
            ArrayList v3 = null;
            if(v2 == null) {
                v2_1 = new ArrayList();
                v0.put(this.b, v2_1);
            }
            else if(((ArrayList)v2).size() > 0) {
                v3 = new ArrayList(((Collection)v2));
            }

            v2_1.add(this.a);
            this.a.a(new n(v0) {
                public void a(m arg3) {
                    this.a.get(this.b.b).remove(arg3);
                }
            });
            this.a.a(this.b, false);
            if(v3 != null) {
                Iterator v0_1 = v3.iterator();
                while(v0_1.hasNext()) {
                    v0_1.next().e(this.b);
                }
            }

            this.a.a(this.b);
            return 1;
        }

        public void onViewAttachedToWindow(View arg1) {
        }

        public void onViewDetachedFromWindow(View arg3) {
            this.a();
            o.a.remove(this.b);
            Object v3 = o.a().get(this.b);
            if(v3 != null && ((ArrayList)v3).size() > 0) {
                Iterator v3_1 = ((ArrayList)v3).iterator();
                while(v3_1.hasNext()) {
                    v3_1.next().e(this.b);
                }
            }

            this.a.a(true);
        }
    }

    static ArrayList a;
    private static m b;
    private static ThreadLocal c;

    static {
        o.b = new b();
        o.c = new ThreadLocal();
        o.a = new ArrayList();
    }

    static android.support.v4.f.a a() {
        Object v0 = o.c.get();
        if(v0 != null) {
            v0 = ((WeakReference)v0).get();
            if(v0 != null) {
                return ((android.support.v4.f.a)v0);
            }
        }

        android.support.v4.f.a v0_1 = new android.support.v4.f.a();
        o.c.set(new WeakReference(v0_1));
        return v0_1;
    }

    public static void a(ViewGroup arg1, m arg2) {
        if(!o.a.contains(arg1) && (t.A(((View)arg1)))) {
            o.a.add(arg1);
            if(arg2 == null) {
                arg2 = o.b;
            }

            arg2 = arg2.o();
            o.c(arg1, arg2);
            l.a(((View)arg1), null);
            o.b(arg1, arg2);
        }
    }

    private static void b(ViewGroup arg1, m arg2) {
        if(arg2 != null && arg1 != null) {
            a v0 = new a(arg2, arg1);
            arg1.addOnAttachStateChangeListener(((View$OnAttachStateChangeListener)v0));
            arg1.getViewTreeObserver().addOnPreDrawListener(((ViewTreeObserver$OnPreDrawListener)v0));
        }
    }

    private static void c(ViewGroup arg2, m arg3) {
        Object v0 = o.a().get(arg2);
        if(v0 != null && ((ArrayList)v0).size() > 0) {
            Iterator v0_1 = ((ArrayList)v0).iterator();
            while(v0_1.hasNext()) {
                v0_1.next().d(((View)arg2));
            }
        }

        if(arg3 != null) {
            arg3.a(arg2, true);
        }

        l v2 = l.a(((View)arg2));
        if(v2 != null) {
            v2.a();
        }
    }
}

