package android.arch.lifecycle;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.h;
import android.support.v4.app.l;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

class e {
    public class a extends Fragment {
        public a() {
            super();
        }

        protected void a(android.arch.lifecycle.d$a arg2) {
            e.a(this.getParentFragment(), arg2);
        }

        public void onDestroy() {
            super.onDestroy();
            this.a(android.arch.lifecycle.d$a.ON_DESTROY);
        }

        public void onPause() {
            super.onPause();
            this.a(android.arch.lifecycle.d$a.ON_PAUSE);
        }

        public void onStop() {
            super.onStop();
            this.a(android.arch.lifecycle.d$a.ON_STOP);
        }
    }

    class b extends android.arch.lifecycle.b {
        private final c a;

        b() {
            super();
            this.a = new c();
        }

        public void onActivityCreated(Activity arg3, Bundle arg4) {
            if((arg3 instanceof h)) {
                arg3.d().a(this.a, true);
            }

            q.a(arg3);
        }

        public void onActivitySaveInstanceState(Activity arg1, Bundle arg2) {
            if((arg1 instanceof h)) {
                e.a(((h)arg1), android.arch.lifecycle.d$b.c);
            }
        }

        public void onActivityStopped(Activity arg2) {
            if((arg2 instanceof h)) {
                e.a(((h)arg2), android.arch.lifecycle.d$b.c);
            }
        }
    }

    class c extends android.support.v4.app.l$a {
        c() {
            super();
        }

        public void a(l arg1, Fragment arg2) {
            e.a(arg2, android.arch.lifecycle.d$a.ON_START);
        }

        public void a(l arg1, Fragment arg2, Bundle arg3) {
            e.a(arg2, android.arch.lifecycle.d$a.ON_CREATE);
            if(!(arg2 instanceof i)) {
                return;
            }

            if(arg2.getChildFragmentManager().a("android.arch.lifecycle.LifecycleDispatcher.report_fragment_tag") == null) {
                arg2.getChildFragmentManager().a().a(new a(), "android.arch.lifecycle.LifecycleDispatcher.report_fragment_tag").c();
            }
        }

        public void b(l arg1, Fragment arg2) {
            e.a(arg2, android.arch.lifecycle.d$a.ON_RESUME);
        }
    }

    private static AtomicBoolean a;

    static {
        e.a = new AtomicBoolean(false);
    }

    static void a(Context arg2) {
        if(e.a.getAndSet(true)) {
            return;
        }

        arg2.getApplicationContext().registerActivityLifecycleCallbacks(new b());
    }

    static void a(Fragment arg0, android.arch.lifecycle.d$a arg1) {
        e.b(arg0, arg1);
    }

    static void a(h arg0, android.arch.lifecycle.d$b arg1) {
        e.b(arg0, arg1);
    }

    private static void a(l arg2, android.arch.lifecycle.d$b arg3) {
        List v2 = arg2.c();
        if(v2 == null) {
            return;
        }

        Iterator v2_1 = ((Collection)v2).iterator();
        while(v2_1.hasNext()) {
            Object v0 = v2_1.next();
            if(v0 == null) {
                continue;
            }

            e.a(v0, arg3);
            if(!((Fragment)v0).isAdded()) {
                continue;
            }

            e.a(((Fragment)v0).getChildFragmentManager(), arg3);
        }
    }

    private static void a(Object arg1, android.arch.lifecycle.d$b arg2) {
        if((arg1 instanceof i)) {
            ((i)arg1).a().a(arg2);
        }
    }

    private static void b(Fragment arg1, android.arch.lifecycle.d$a arg2) {
        if((arg1 instanceof i)) {
            ((i)arg1).a().a(arg2);
        }
    }

    private static void b(h arg0, android.arch.lifecycle.d$b arg1) {
        e.a(arg0, arg1);
        e.a(arg0.d(), arg1);
    }
}

