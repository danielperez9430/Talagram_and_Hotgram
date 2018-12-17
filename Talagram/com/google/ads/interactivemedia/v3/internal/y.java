package com.google.ads.interactivemedia.v3.internal;

import android.webkit.WebView;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONObject;

public abstract class y {
    enum a {
        public static final enum a a;
        public static final enum a b;
        public static final enum a c;

        static {
            a.a = new a("AD_STATE_IDLE", 0);
            a.b = new a("AD_STATE_VISIBLE", 1);
            a.c = new a("AD_STATE_HIDDEN", 2);
            a.d = new a[]{a.a, a.b, a.c};
        }

        private a(String arg1, int arg2) {
            super(arg1, arg2);
        }

        public static a valueOf(String arg1) {
            return Enum.valueOf(a.class, arg1);
        }

        public static a[] values() {
            // Method was not decompiled
        }
    }

    private as a;
    private a b;
    private double c;

    public y() {
        super();
        this.f();
        this.a = new as(null);
    }

    public void a(boolean arg3) {
        if(this.d()) {
            String v3 = arg3 ? "foregrounded" : "backgrounded";
            s.a().d(this.c(), v3);
        }
    }

    public void a() {
    }

    public void a(float arg3) {
        s.a().a(this.c(), arg3);
    }

    void a(WebView arg2) {
        this.a = new as(arg2);
    }

    public void a(d arg3) {
        s.a().a(this.c(), arg3.a());
    }

    public void a(g arg5, e arg6) {
        String v5 = arg5.f();
        JSONObject v0 = new JSONObject();
        ac.a(v0, "environment", "app");
        ac.a(v0, "adSessionType", arg6.f());
        ac.a(v0, "deviceInfo", ab.d());
        JSONArray v1 = new JSONArray();
        v1.put("clid");
        v1.put("vlid");
        ac.a(v0, "supports", v1);
        JSONObject v1_1 = new JSONObject();
        ac.a(v1_1, "partnerName", arg6.a().a());
        ac.a(v1_1, "partnerVersion", arg6.a().b());
        ac.a(v0, "omidNativeInfo", v1_1);
        v1_1 = new JSONObject();
        ac.a(v1_1, "libraryVersion", "1.2.4-google_20180831");
        ac.a(v1_1, "appId", r.a().b().getApplicationContext().getPackageName());
        ac.a(v0, "app", v1_1);
        if(arg6.d() != null) {
            ac.a(v0, "customReferenceData", arg6.d());
        }

        v1_1 = new JSONObject();
        Iterator v6 = arg6.b().iterator();
        while(v6.hasNext()) {
            Object v2 = v6.next();
            ac.a(v1_1, ((j)v2).a(), ((j)v2).c());
        }

        s.a().a(this.c(), v5, v0, v1_1);
    }

    public void a(String arg4, double arg5) {
        if(arg5 > this.c) {
            this.b = a.b;
            s.a().c(this.c(), arg4);
        }
    }

    public void b() {
        this.a.clear();
    }

    public void b(String arg4, double arg5) {
        if(arg5 > this.c && this.b != a.c) {
            this.b = a.c;
            s.a().c(this.c(), arg4);
        }
    }

    public WebView c() {
        return this.a.get();
    }

    public boolean d() {
        boolean v0 = this.a.get() != null ? true : false;
        return v0;
    }

    public void e() {
        s.a().a(this.c());
    }

    public void f() {
        this.c = ae.a();
        this.b = a.a;
    }
}

