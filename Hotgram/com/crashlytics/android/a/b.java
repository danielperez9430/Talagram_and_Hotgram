package com.crashlytics.android.a;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build$VERSION;
import c.a.a.a.a.b.j$a;
import c.a.a.a.a.b.o;
import c.a.a.a.a.g.q;
import c.a.a.a.a.g.t;
import c.a.a.a.c;
import c.a.a.a.i;
import c.a.a.a.l;
import java.io.File;

public class b extends i {
    boolean a;
    ab b;

    public b() {
        super();
        this.a = false;
    }

    public void a(a arg3) {
        if(this.b != null) {
            this.b.a(arg3.a(), arg3.b());
        }
    }

    public void a(m arg2) {
        if(arg2 != null) {
            if(this.a) {
                this.a("logCustom");
                return;
            }

            if(this.b != null) {
                this.b.a(arg2);
            }

            return;
        }

        throw new NullPointerException("event must not be null");
    }

    private void a(String arg5) {
        l v0 = c.h();
        v0.d("Answers", "Method " + arg5 + " is not supported when using Crashlytics through Firebase.");
    }

    public String a() {
        return "1.4.1.19";
    }

    public String b() {
        return "com.crashlytics.sdk.android:answers";
    }

    @SuppressLint(value={"NewApi"}) protected boolean b_() {
        boolean v0 = false;
        try {
            Context v8 = this.q();
            PackageManager v1_1 = v8.getPackageManager();
            String v2 = v8.getPackageName();
            PackageInfo v3 = v1_1.getPackageInfo(v2, 0);
            String v4 = Integer.toString(v3.versionCode);
            String v5 = v3.versionName == null ? "0.0" : v3.versionName;
            long v1_2 = Build$VERSION.SDK_INT >= 9 ? v3.firstInstallTime : new File(v1_1.getApplicationInfo(v2, 0).sourceDir).lastModified();
            long v6 = v1_2;
            this.b = ab.a(this, v8, this.p(), v4, v5, v6);
            this.b.b();
            this.a = new o().b(v8);
            v0 = true;
            return 1;
        }
        catch(Exception v1) {
            c.h().e("Answers", "Error retrieving app properties", ((Throwable)v1));
            return v0;
        }
    }

    public static b c() {
        return c.a(b.class);
    }

    protected Object e() {
        return this.f();
    }

    protected Boolean f() {
        try {
            t v1_1 = q.a().b();
            if(v1_1 == null) {
                c.h().e("Answers", "Failed to retrieve settings");
                return Boolean.valueOf(false);
            }

            if(v1_1.d.d) {
                c.h().a("Answers", "Analytics collection enabled");
                this.b.a(v1_1.e, this.g());
                return Boolean.valueOf(true);
            }

            c.h().a("Answers", "Analytics collection disabled");
            this.b.c();
            return Boolean.valueOf(false);
        }
        catch(Exception v1) {
            c.h().e("Answers", "Error dealing with settings", ((Throwable)v1));
            return Boolean.valueOf(false);
        }
    }

    String g() {
        return c.a.a.a.a.b.i.b(this.q(), "com.crashlytics.ApiEndpoint");
    }
}

