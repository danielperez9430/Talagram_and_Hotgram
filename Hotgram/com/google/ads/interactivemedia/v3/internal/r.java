package com.google.ads.interactivemedia.v3.internal;

import android.annotation.SuppressLint;
import android.content.Context;

public class r {
    @SuppressLint(value={"StaticFieldLeak"}) private static r a;
    private Context b;

    static {
        r.a = new r();
    }

    private r() {
        super();
    }

    public void a(Context arg1) {
        arg1 = arg1 != null ? arg1.getApplicationContext() : null;
        this.b = arg1;
    }

    public static r a() {
        return r.a;
    }

    public Context b() {
        return this.b;
    }
}

