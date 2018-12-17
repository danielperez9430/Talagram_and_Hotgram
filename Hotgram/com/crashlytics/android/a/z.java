package com.crashlytics.android.a;

import android.content.Context;
import c.a.a.a.a.b.k;
import c.a.a.a.a.d.b;
import c.a.a.a.a.d.c;
import java.util.UUID;

class z extends b {
    private c.a.a.a.a.g.b g;

    z(Context arg7, af arg8, k arg9, c arg10) {
        super(arg7, arg8, arg9, arg10, 100);
    }

    protected String a() {
        UUID v0 = UUID.randomUUID();
        return "sa" + "_" + v0.toString() + "_" + this.c.a() + ".tap";
    }

    void a(c.a.a.a.a.g.b arg1) {
        this.g = arg1;
    }

    protected int b() {
        int v0 = this.g == null ? super.b() : this.g.e;
        return v0;
    }

    protected int c() {
        int v0 = this.g == null ? super.c() : this.g.c;
        return v0;
    }
}

