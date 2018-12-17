package com.google.ads.interactivemedia.v3.internal;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application$ActivityLifecycleCallbacks;
import android.app.Application;
import android.content.Context;
import android.graphics.Rect;
import android.media.AudioManager;
import android.os.Build$VERSION;
import android.os.Bundle;
import android.support.v4.view.t;
import android.util.DisplayMetrics;
import android.view.View;
import com.google.ads.interactivemedia.v3.impl.data.m;

public class ib implements a {
    class com.google.ads.interactivemedia.v3.internal.ib$1 {
    }

    @TargetApi(value=14) public class com.google.ads.interactivemedia.v3.internal.ib$a implements Application$ActivityLifecycleCallbacks {
        protected com.google.ads.interactivemedia.v3.internal.ib$a(ib arg1) {
            this.a = arg1;
            super();
        }

        public void onActivityCreated(Activity arg1, Bundle arg2) {
        }

        public void onActivityDestroyed(Activity arg2) {
            if(ib.a(this.a) == arg2) {
                ib.a(this.a, null);
                Application v2 = ib.d(this.a);
                if(v2 != null) {
                    v2.unregisterActivityLifecycleCallbacks(ib.e(this.a));
                }
            }
        }

        public void onActivityPaused(Activity arg6) {
            if(ib.a(this.a) == null || ib.a(this.a) == arg6) {
                ib.a(this.a, arg6);
                ib.c(this.a).b(new jc(com.google.ads.interactivemedia.v3.internal.jc$a.activityMonitor, b.appStateChanged, ib.b(this.a), this.a.a("", "", "", "inactive")));
            }
        }

        public void onActivityResumed(Activity arg6) {
            if(ib.a(this.a) == arg6) {
                ib.c(this.a).b(new jc(com.google.ads.interactivemedia.v3.internal.jc$a.activityMonitor, b.appStateChanged, ib.b(this.a), this.a.a("", "", "", "active")));
            }
        }

        public void onActivitySaveInstanceState(Activity arg1, Bundle arg2) {
        }

        public void onActivityStarted(Activity arg1) {
        }

        public void onActivityStopped(Activity arg1) {
        }
    }

    public interface com.google.ads.interactivemedia.v3.internal.ib$b {
        long a();
    }

    class c implements com.google.ads.interactivemedia.v3.internal.ib$b {
        c(com.google.ads.interactivemedia.v3.internal.ib$1 arg1) {
            this();
        }

        private c() {
            super();
        }

        public long a() {
            return System.currentTimeMillis();
        }
    }

    private final jd a;
    private String b;
    private View c;
    private com.google.ads.interactivemedia.v3.internal.ib$b d;
    private com.google.ads.interactivemedia.v3.internal.ib$a e;
    private Activity f;
    private boolean g;

    public ib(String arg3, jd arg4, View arg5) {
        this(arg3, arg4, arg5, new c(null));
    }

    protected ib(String arg1, jd arg2, View arg3, com.google.ads.interactivemedia.v3.internal.ib$b arg4) {
        super();
        this.b = arg1;
        this.a = arg2;
        this.c = arg3;
        this.d = arg4;
        this.f = null;
        this.e = null;
        this.g = false;
    }

    private static int a(int arg0, float arg1) {
        return ((int)Math.ceil(((double)((((float)arg0)) / arg1))));
    }

    static Activity a(ib arg0) {
        return arg0.f;
    }

    static Activity a(ib arg0, Activity arg1) {
        arg0.f = arg1;
        return arg1;
    }

    private static m a(m arg2, float arg3) {
        return m.builder().left(ib.a(arg2.left(), arg3)).top(ib.a(arg2.top(), arg3)).height(ib.a(arg2.height(), arg3)).width(ib.a(arg2.width(), arg3)).build();
    }

    public com.google.ads.interactivemedia.v3.impl.data.a a(String arg10, String arg11, String arg12, String arg13) {
        return com.google.ads.interactivemedia.v3.impl.data.a.builder().queryId(arg10).eventId(arg11).vastEvent(arg12).appState(arg13).nativeTime(this.d.a()).nativeVolume(this.e()).nativeViewAttached(t.D(this.c)).nativeViewHidden(this.f()).nativeViewBounds(this.g()).nativeViewVisibleBounds(this.h()).build();
    }

    public void a() {
        this.a.a(((a)this), this.b);
    }

    public void a(String arg5, String arg6) {
        this.a.b(new jc(com.google.ads.interactivemedia.v3.internal.jc$a.activityMonitor, b.viewability, this.b, this.a(arg5, arg6, "", "")));
    }

    public void a(String arg4, String arg5, String arg6) {
        this.a.b(new jc(com.google.ads.interactivemedia.v3.internal.jc$a.activityMonitor, b.viewability, this.b, this.a(arg4, arg5, arg6, "")));
    }

    protected void a(boolean arg1) {
        this.g = arg1;
    }

    static String b(ib arg0) {
        return arg0.b;
    }

    public void b() {
        this.a.a(this.b);
    }

    static jd c(ib arg0) {
        return arg0.a;
    }

    @TargetApi(value=14) public void c() {
        if(Build$VERSION.SDK_INT >= 14 && (this.g)) {
            Application v0 = this.j();
            if(v0 != null) {
                this.e = new com.google.ads.interactivemedia.v3.internal.ib$a(this);
                v0.registerActivityLifecycleCallbacks(this.e);
            }
        }
    }

    static Application d(ib arg0) {
        return arg0.j();
    }

    @TargetApi(value=14) public void d() {
        if(Build$VERSION.SDK_INT >= 14) {
            Application v0 = this.j();
            if(v0 != null && this.e != null) {
                v0.unregisterActivityLifecycleCallbacks(this.e);
            }
        }
    }

    static com.google.ads.interactivemedia.v3.internal.ib$a e(ib arg0) {
        return arg0.e;
    }

    public double e() {
        double v1;
        Object v0 = this.c.getContext().getSystemService("audio");
        if(v0 != null) {
            v1 = ((double)((AudioManager)v0).getStreamVolume(3));
            double v3 = ((double)((AudioManager)v0).getStreamMaxVolume(3));
            Double.isNaN(v1);
            Double.isNaN(v3);
            v1 /= v3;
        }
        else {
            v1 = 0;
        }

        return v1;
    }

    public boolean f() {
        boolean v0 = !this.c.getGlobalVisibleRect(new Rect()) || !this.c.isShown() ? true : false;
        return v0;
    }

    public m g() {
        return ib.a(m.builder().locationOnScreenOfView(this.c).build(), this.i().density);
    }

    public m h() {
        Rect v0 = new Rect();
        boolean v1 = this.c.getGlobalVisibleRect(v0);
        int v2 = this.c.getWindowToken() != null ? 1 : 0;
        if(!v1 || v2 == 0 || !this.c.isShown()) {
            v0.set(0, 0, 0, 0);
        }

        return ib.a(m.builder().left(v0.left).top(v0.top).height(v0.height()).width(v0.width()).build(), this.i().density);
    }

    private DisplayMetrics i() {
        return this.c.getContext().getResources().getDisplayMetrics();
    }

    private Application j() {
        Context v0 = this.c.getContext().getApplicationContext();
        if((v0 instanceof Application)) {
            return ((Application)v0);
        }

        return null;
    }
}

