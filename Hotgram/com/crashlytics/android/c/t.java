package com.crashlytics.android.c;

import c.a.a.a.c;
import c.a.a.a.l;
import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

class t implements ae {
    private final File[] a;
    private final Map b;
    private final String c;

    public t(String arg2, File[] arg3) {
        super();
        this.a = arg3;
        this.b = new HashMap(af.a);
        this.c = arg2;
    }

    public String a() {
        return this.a[0].getName();
    }

    public String b() {
        return this.c;
    }

    public File c() {
        return this.a[0];
    }

    public File[] d() {
        return this.a;
    }

    public Map e() {
        return Collections.unmodifiableMap(this.b);
    }

    public void f() {
        File[] v0 = this.a;
        int v1 = v0.length;
        int v2;
        for(v2 = 0; v2 < v1; ++v2) {
            File v3 = v0[v2];
            l v4 = c.h();
            v4.a("CrashlyticsCore", "Removing invalid report file at " + v3.getPath());
            v3.delete();
        }
    }
}

