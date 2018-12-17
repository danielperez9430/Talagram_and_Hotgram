package com.crashlytics.android.a;

import android.annotation.SuppressLint;
import android.content.Context;
import c.a.a.a.a.f.c;
import c.a.a.a.a.f.d;

class i {
    private final c a;

    i(c arg1) {
        super();
        this.a = arg1;
    }

    public static i a(Context arg2) {
        return new i(new d(arg2, "settings"));
    }

    @SuppressLint(value={"CommitPrefEdits"}) public void a() {
        this.a.a(this.a.b().putBoolean("analytics_launched", true));
    }

    @SuppressLint(value={"CommitPrefEdits"}) public boolean b() {
        return this.a.a().getBoolean("analytics_launched", false);
    }
}

