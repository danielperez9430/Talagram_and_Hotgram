package com.crashlytics.android.b;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.Context;
import android.os.Build$VERSION;
import android.text.TextUtils;
import c.a.a.a.a.a.b;
import c.a.a.a.a.b.m;
import c.a.a.a.a.b.p$a;
import c.a.a.a.a.b.p;
import c.a.a.a.a.b.t;
import c.a.a.a.a.g.f;
import c.a.a.a.a.g.q;
import c.a.a.a.i;
import c.a.a.a.l;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class c extends i implements m {
    private final b a;
    private final h b;
    private j c;

    public c() {
        super();
        this.a = new b();
        this.b = new h();
    }

    private d a(Context arg7) {
        Exception v0_1;
        d v1;
        Throwable v0_2;
        InputStream v7_2;
        d v0 = null;
        try {
            v7_2 = arg7.getAssets().open("crashlytics-build.properties");
            if(v7_2 == null) {
                goto label_35;
            }
        }
        catch(Throwable v7) {
            d v5 = v0;
            v0_2 = v7;
            v7_2 = ((InputStream)v5);
            goto label_68;
        }
        catch(Exception v7_1) {
            v1 = v0;
            v0_1 = v7_1;
            v7_2 = ((InputStream)v1);
            goto label_53;
        }

        try {
            v1 = d.a(v7_2);
        }
        catch(Exception v1_1) {
            Exception v5_1 = v1_1;
            v1 = v0;
            v0_1 = v5_1;
            goto label_53;
        }

        try {
            l v0_3 = c.a.a.a.c.h();
            v0_3.a("Beta", v1.d + " build properties: " + v1.b + " (" + v1.a + ") - " + v1.c);
            v0 = v1;
            goto label_35;
        }
        catch(Exception v0_1) {
        }

        try {
        label_53:
            c.a.a.a.c.h().e("Beta", "Error reading Beta build properties", ((Throwable)v0_1));
            if(v7_2 != null) {
            }
            else {
                return v1;
            }
        }
        catch(Throwable v0_2) {
            goto label_68;
        }

        try {
            v7_2.close();
        }
        catch(IOException v7_3) {
            c.a.a.a.c.h().e("Beta", "Error closing Beta build properties asset", ((Throwable)v7_3));
        }

        return v1;
    label_68:
        if(v7_2 != null) {
            try {
                v7_2.close();
            }
            catch(IOException v7_3) {
                c.a.a.a.c.h().e("Beta", "Error closing Beta build properties asset", ((Throwable)v7_3));
            }

            goto label_76;
        }
        else {
        label_76:
            throw v0_2;
        }

    label_35:
        if(v7_2 != null) {
            try {
                v7_2.close();
            }
            catch(IOException v7_3) {
                c.a.a.a.c.h().e("Beta", "Error closing Beta build properties asset", ((Throwable)v7_3));
            }
        }

        return v0;
    }

    private String a(Context arg4, String arg5) {
        Object v4_1;
        CharSequence v5 = null;
        try {
            v4_1 = this.a.a(arg4, this.b);
            if(!"".equals(v4_1)) {
                goto label_8;
            }
        }
        catch(Exception v4) {
            c.a.a.a.c.h().e("Beta", "Failed to load the Beta device token", ((Throwable)v4));
            goto label_15;
        }

        goto label_15;
    label_8:
        Object v5_1 = v4_1;
    label_15:
        l v4_2 = c.a.a.a.c.h();
        v4_2.a("Beta", "Beta device token present: " + (TextUtils.isEmpty(v5) ^ 1));
        return ((String)v5);
    }

    @TargetApi(value=14) j a(int arg2, Application arg3) {
        if(arg2 >= 14) {
            return new com.crashlytics.android.b.b(this.r().e(), this.r().f());
        }

        return new com.crashlytics.android.b.i();
    }

    public String a() {
        return "1.2.7.19";
    }

    boolean a(f arg1, d arg2) {
        boolean v1 = arg1 == null || (TextUtils.isEmpty(arg1.a)) || arg2 == null ? false : true;
        return v1;
    }

    public String b() {
        return "com.crashlytics.sdk.android:beta";
    }

    @TargetApi(value=14) protected boolean b_() {
        this.c = this.a(Build$VERSION.SDK_INT, this.q().getApplicationContext());
        return 1;
    }

    protected Boolean c() {
        boolean v0;
        c.a.a.a.c.h().a("Beta", "Beta kit initializing...");
        Context v4 = this.q();
        p v6 = this.p();
        if(TextUtils.isEmpty(this.a(v4, v6.i()))) {
            c.a.a.a.c.h().a("Beta", "A Beta device token was not found for this app");
            v0 = false;
        }
        else {
            c.a.a.a.c.h().a("Beta", "Beta device token is present, checking for app updates.");
            f v7 = this.h();
            d v8 = this.a(v4);
            if(this.a(v7, v8)) {
                this.c.a(v4, this, v6, v7, v8, new c.a.a.a.a.f.d(((i)this)), new t(), new c.a.a.a.a.e.b(c.a.a.a.c.h()));
            }

            v0 = true;
        }

        return Boolean.valueOf(v0);
    }

    protected Object e() {
        return this.c();
    }

    public Map f() {
        String v0 = this.a(this.q(), this.p().i());
        HashMap v1 = new HashMap();
        if(!TextUtils.isEmpty(((CharSequence)v0))) {
            ((Map)v1).put(a.c, v0);
        }

        return ((Map)v1);
    }

    String g() {
        return c.a.a.a.a.b.i.b(this.q(), "com.crashlytics.ApiEndpoint");
    }

    private f h() {
        c.a.a.a.a.g.t v0 = q.a().b();
        if(v0 != null) {
            return v0.f;
        }

        return null;
    }
}

