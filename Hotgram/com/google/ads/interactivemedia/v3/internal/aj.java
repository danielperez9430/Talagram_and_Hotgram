package com.google.ads.interactivemedia.v3.internal;

import android.os.Handler;
import android.os.Looper;
import android.view.View;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

public class aj implements a {
    class com.google.ads.interactivemedia.v3.internal.aj$2 implements Runnable {
        com.google.ads.interactivemedia.v3.internal.aj$2() {
            super();
        }

        public void run() {
            aj.b(aj.a());
        }
    }

    class com.google.ads.interactivemedia.v3.internal.aj$3 implements Runnable {
        com.google.ads.interactivemedia.v3.internal.aj$3() {
            super();
        }

        public void run() {
            if(aj.f() != null) {
                aj.f().post(aj.g());
                aj.f().postDelayed(aj.h(), 200);
            }
        }
    }

    public interface com.google.ads.interactivemedia.v3.internal.aj$a {
        void a(int arg1, long arg2);
    }

    private static aj a;
    private static Handler b;
    private static Handler c;
    private List d;
    private int e;
    private v f;
    private ah g;
    private ai h;
    private double i;
    private static final Runnable j;
    private static final Runnable k;

    static {
        aj.a = new aj();
        aj.b = new Handler(Looper.getMainLooper());
        aj.c = null;
        aj.j = new com.google.ads.interactivemedia.v3.internal.aj$2();
        aj.k = new com.google.ads.interactivemedia.v3.internal.aj$3();
    }

    aj() {
        super();
        this.d = new ArrayList();
        this.g = new ah();
        this.f = new v();
        this.h = new ai(new an());
    }

    static ai a(aj arg0) {
        return arg0.h;
    }

    public static aj a() {
        return aj.a;
    }

    private void a(long arg4) {
        if(this.d.size() > 0) {
            Iterator v0 = this.d.iterator();
            while(v0.hasNext()) {
                v0.next().a(this.e, arg4);
            }
        }
    }

    private void a(View arg2, u arg3, JSONObject arg4, ak arg5) {
        boolean v5 = arg5 == ak.a ? true : false;
        arg3.a(arg2, arg4, ((a)this), v5);
    }

    private boolean a(View arg2, JSONObject arg3) {
        String v2 = this.g.a(arg2);
        if(v2 != null) {
            ac.a(arg3, v2);
            this.g.e();
            return 1;
        }

        return 0;
    }

    public void a(View arg3, u arg4, JSONObject arg5) {
        if(!ag.d(arg3)) {
            return;
        }

        ak v0 = this.g.c(arg3);
        if(v0 == ak.c) {
            return;
        }

        JSONObject v1 = arg4.a(arg3);
        ac.a(arg5, v1);
        if(!this.a(arg3, v1)) {
            this.b(arg3, v1);
            this.a(arg3, arg4, v1, v0);
        }

        ++this.e;
    }

    private void b(View arg2, JSONObject arg3) {
        ArrayList v2 = this.g.b(arg2);
        if(v2 != null) {
            ac.a(arg3, ((List)v2));
        }
    }

    static void b(aj arg0) {
        arg0.i();
    }

    public void b() {
        this.l();
    }

    public void c() {
        this.d();
        this.d.clear();
        aj.b.post(new Runnable() {
            public void run() {
                aj.a(this.a).a();
            }
        });
    }

    public void d() {
        this.m();
    }

    void e() {
        this.g.c();
        double v0 = ae.a();
        u v2 = this.f.a();
        View v4 = null;
        if(this.g.b().size() > 0) {
            this.h.b(v2.a(v4), this.g.b(), v0);
        }

        if(this.g.a().size() > 0) {
            JSONObject v3 = v2.a(v4);
            this.a(v4, v2, v3, ak.a);
            ac.a(v3);
            this.h.a(v3, this.g.a(), v0);
        }
        else {
            this.h.a();
        }

        this.g.d();
    }

    static Handler f() {
        return aj.c;
    }

    static Runnable g() {
        return aj.j;
    }

    static Runnable h() {
        return aj.k;
    }

    private void i() {
        this.j();
        this.e();
        this.k();
    }

    private void j() {
        this.e = 0;
        this.i = ae.a();
    }

    private void k() {
        this.a(((long)(ae.a() - this.i)));
    }

    private void l() {
        if(aj.c == null) {
            aj.c = new Handler(Looper.getMainLooper());
            aj.c.post(aj.j);
            aj.c.postDelayed(aj.k, 200);
        }
    }

    private void m() {
        if(aj.c != null) {
            aj.c.removeCallbacks(aj.k);
            aj.c = null;
        }
    }
}

