package com.crashlytics.android.a;

import android.content.Context;
import c.a.a.a.a.b.i;
import c.a.a.a.a.b.p$a;
import c.a.a.a.a.b.p;
import java.util.Map;
import java.util.UUID;

class ag {
    private final Context a;
    private final p b;
    private final String c;
    private final String d;

    public ag(Context arg1, p arg2, String arg3, String arg4) {
        super();
        this.a = arg1;
        this.b = arg2;
        this.c = arg3;
        this.d = arg4;
    }

    public ae a() {
        Map v0 = this.b.h();
        return new ae(this.b.c(), UUID.randomUUID().toString(), this.b.b(), v0.get(a.d), v0.get(a.g), this.b.j(), v0.get(a.c), i.m(this.a), this.b.d(), this.b.g(), this.c, this.d);
    }
}

