package com.crashlytics.android.c;

import c.a.a.a.a.b.a;
import c.a.a.a.a.b.s;
import c.a.a.a.a.e.c;
import c.a.a.a.a.e.d;
import c.a.a.a.a.e.e;
import c.a.a.a.i;
import c.a.a.a.l;
import java.io.File;
import java.util.Iterator;

class p extends a implements o {
    public p(i arg7, String arg8, String arg9, e arg10) {
        super(arg7, arg8, arg9, arg10, c.b);
    }

    private d a(d arg11, ae arg12) {
        arg11.e("report[identifier]", arg12.b());
        if(arg12.d().length == 1) {
            l v0 = c.a.a.a.c.h();
            v0.a("CrashlyticsCore", "Adding single file " + arg12.a() + " to report " + arg12.b());
            return arg11.a("report[file]", arg12.a(), "application/octet-stream", arg12.c());
        }

        File[] v0_1 = arg12.d();
        int v2_1 = v0_1.length;
        int v3 = 0;
        int v4 = 0;
        while(v3 < v2_1) {
            File v5 = v0_1[v3];
            l v6 = c.a.a.a.c.h();
            v6.a("CrashlyticsCore", "Adding file " + v5.getName() + " to report " + arg12.b());
            arg11.a("report[file" + v4 + "]", v5.getName(), "application/octet-stream", v5);
            ++v4;
            ++v3;
        }

        return arg11;
    }

    private d a(d arg3, n arg4) {
        arg3 = arg3.a("X-CRASHLYTICS-API-KEY", arg4.a).a("X-CRASHLYTICS-API-CLIENT-TYPE", "android").a("X-CRASHLYTICS-API-CLIENT-VERSION", this.a.a());
        Iterator v4 = arg4.b.e().entrySet().iterator();
        while(v4.hasNext()) {
            arg3 = arg3.a(v4.next());
        }

        return arg3;
    }

    public boolean a(n arg6) {
        d v6 = this.a(this.a(this.b(), arg6), arg6.b);
        l v0 = c.a.a.a.c.h();
        v0.a("CrashlyticsCore", "Sending report to: " + this.a());
        int v0_1 = v6.b();
        l v1 = c.a.a.a.c.h();
        v1.a("CrashlyticsCore", "Create report request ID: " + v6.b("X-REQUEST-ID"));
        l v6_1 = c.a.a.a.c.h();
        v6_1.a("CrashlyticsCore", "Result was: " + v0_1);
        boolean v6_2 = s.a(v0_1) == 0 ? true : false;
        return v6_2;
    }
}

