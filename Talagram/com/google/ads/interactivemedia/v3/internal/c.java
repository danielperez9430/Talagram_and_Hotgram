package com.google.ads.interactivemedia.v3.internal;

import android.view.View;

public abstract class c {
    public c() {
        super();
    }

    public static c a(d arg1, e arg2) {
        af.a();
        af.a(arg1, "AdSessionConfiguration is null");
        af.a(arg2, "AdSessionContext is null");
        return new g(arg1, arg2);
    }

    public abstract void a();

    public abstract void a(View arg1);

    public abstract void b();

    public abstract void b(View arg1);

    public abstract void c();
}

