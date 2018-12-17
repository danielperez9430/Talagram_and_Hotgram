package com.crashlytics.android;

import c.a.a.a.i;
import c.a.a.a.j;
import com.crashlytics.android.a.b;
import com.crashlytics.android.b.c;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class a extends i implements j {
    public final b a;
    public final c b;
    public final com.crashlytics.android.c.i c;
    public final Collection d;

    public a() {
        this(new b(), new c(), new com.crashlytics.android.c.i());
    }

    a(b arg3, c arg4, com.crashlytics.android.c.i arg5) {
        super();
        this.a = arg3;
        this.b = arg4;
        this.c = arg5;
        this.d = Collections.unmodifiableCollection(Arrays.asList(new i[]{arg3, arg4, arg5}));
    }

    public String a() {
        return "2.7.1.19";
    }

    public String b() {
        return "com.crashlytics.sdk.android:crashlytics";
    }

    public Collection c() {
        return this.d;
    }

    protected Void d() {
        return null;
    }

    protected Object e() {
        return this.d();
    }
}

