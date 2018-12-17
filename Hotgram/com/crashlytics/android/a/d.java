package com.crashlytics.android.a;

import java.util.Map;

public abstract class d {
    final e b;
    final c c;

    public d() {
        super();
        this.b = new e(20, 100, c.a.a.a.c.i());
        this.c = new c(this.b);
    }

    public d a(String arg2, Number arg3) {
        this.c.a(arg2, arg3);
        return this;
    }

    public d a(String arg2, String arg3) {
        this.c.a(arg2, arg3);
        return this;
    }

    Map b() {
        return this.c.b;
    }
}

