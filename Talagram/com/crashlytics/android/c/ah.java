package com.crashlytics.android.c;

import c.a.a.a.c;
import c.a.a.a.l;
import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

class ah implements ae {
    private final File a;
    private final File[] b;
    private final Map c;

    public ah(File arg2) {
        this(arg2, Collections.emptyMap());
    }

    public ah(File arg4, Map arg5) {
        super();
        this.a = arg4;
        this.b = new File[]{arg4};
        this.c = new HashMap(arg5);
        if(this.a.length() == 0) {
            this.c.putAll(af.a);
        }
    }

    public String a() {
        return this.c().getName();
    }

    public String b() {
        String v0 = this.a();
        return v0.substring(0, v0.lastIndexOf(46));
    }

    public File c() {
        return this.a;
    }

    public File[] d() {
        return this.b;
    }

    public Map e() {
        return Collections.unmodifiableMap(this.c);
    }

    public void f() {
        l v0 = c.h();
        v0.a("CrashlyticsCore", "Removing report at " + this.a.getPath());
        this.a.delete();
    }
}

