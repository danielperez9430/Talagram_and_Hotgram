package android.arch.lifecycle;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;

public class q extends Fragment {
    interface a {
        void a();

        void b();

        void c();
    }

    private a a;

    public q() {
        super();
    }

    public static void a(Activity arg3) {
        FragmentManager v3 = arg3.getFragmentManager();
        if(v3.findFragmentByTag("android.arch.lifecycle.LifecycleDispatcher.report_fragment_tag") == null) {
            v3.beginTransaction().add(new q(), "android.arch.lifecycle.LifecycleDispatcher.report_fragment_tag").commit();
            v3.executePendingTransactions();
        }
    }

    private void a(android.arch.lifecycle.d$a arg3) {
        Activity v0 = this.getActivity();
        if((v0 instanceof i)) {
            ((i)v0).a().a(arg3);
            return;
        }

        if((v0 instanceof g)) {
            d v0_1 = ((g)v0).getLifecycle();
            if((v0_1 instanceof h)) {
                ((h)v0_1).a(arg3);
            }
        }
    }

    void a(a arg1) {
        this.a = arg1;
    }

    static q b(Activity arg1) {
        return arg1.getFragmentManager().findFragmentByTag("android.arch.lifecycle.LifecycleDispatcher.report_fragment_tag");
    }

    private void b(a arg1) {
        if(arg1 != null) {
            arg1.a();
        }
    }

    private void c(a arg1) {
        if(arg1 != null) {
            arg1.b();
        }
    }

    private void d(a arg1) {
        if(arg1 != null) {
            arg1.c();
        }
    }

    public void onActivityCreated(Bundle arg1) {
        super.onActivityCreated(arg1);
        this.b(this.a);
        this.a(android.arch.lifecycle.d$a.ON_CREATE);
    }

    public void onDestroy() {
        super.onDestroy();
        this.a(android.arch.lifecycle.d$a.ON_DESTROY);
        this.a = null;
    }

    public void onPause() {
        super.onPause();
        this.a(android.arch.lifecycle.d$a.ON_PAUSE);
    }

    public void onResume() {
        super.onResume();
        this.d(this.a);
        this.a(android.arch.lifecycle.d$a.ON_RESUME);
    }

    public void onStart() {
        super.onStart();
        this.c(this.a);
        this.a(android.arch.lifecycle.d$a.ON_START);
    }

    public void onStop() {
        super.onStop();
        this.a(android.arch.lifecycle.d$a.ON_STOP);
    }
}

