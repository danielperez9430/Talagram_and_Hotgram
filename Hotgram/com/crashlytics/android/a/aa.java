package com.crashlytics.android.a;

import c.a.a.a.a.b.a;
import c.a.a.a.a.b.s;
import c.a.a.a.a.d.f;
import c.a.a.a.a.e.c;
import c.a.a.a.a.e.d;
import c.a.a.a.a.e.e;
import c.a.a.a.i;
import c.a.a.a.l;
import java.io.File;
import java.util.Iterator;
import java.util.List;

class aa extends a implements f {
    private final String b;

    public aa(i arg7, String arg8, String arg9, e arg10, String arg11) {
        super(arg7, arg8, arg9, arg10, c.b);
        this.b = arg11;
    }

    public boolean a(List arg10) {
        d v0 = this.b().a("X-CRASHLYTICS-API-CLIENT-TYPE", "android").a("X-CRASHLYTICS-API-CLIENT-VERSION", this.a.a()).a("X-CRASHLYTICS-API-KEY", this.b);
        Iterator v1 = arg10.iterator();
        boolean v2 = false;
        int v3;
        for(v3 = 0; v1.hasNext(); ++v3) {
            Object v4 = v1.next();
            v0.a("session_analytics_file_" + v3, ((File)v4).getName(), "application/vnd.crashlytics.android.events", ((File)v4));
        }

        l v1_1 = c.a.a.a.c.h();
        v1_1.a("Answers", "Sending " + arg10.size() + " analytics files to " + this.a());
        int v10 = v0.b();
        l v0_1 = c.a.a.a.c.h();
        v0_1.a("Answers", "Response code for analytics file send is " + v10);
        if(s.a(v10) == 0) {
            v2 = true;
        }

        return v2;
    }
}

