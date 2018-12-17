package com.crashlytics.android.c;

import android.annotation.SuppressLint;
import c.a.a.a.a.f.c;
import c.a.a.a.a.f.d;

@SuppressLint(value={"CommitPrefEdits"}) class ab {
    private final c a;

    public ab(c arg1) {
        super();
        this.a = arg1;
    }

    public static ab a(c arg4, i arg5) {
        if(!arg4.a().getBoolean("preferences_migration_complete", false)) {
            d v0 = new d(((c.a.a.a.i)arg5));
            int v5 = (arg4.a().contains("always_send_reports_opt_in")) || !((c)v0).a().contains("always_send_reports_opt_in") ? 0 : 1;
            if(v5 != 0) {
                arg4.a(arg4.b().putBoolean("always_send_reports_opt_in", ((c)v0).a().getBoolean("always_send_reports_opt_in", false)));
            }

            arg4.a(arg4.b().putBoolean("preferences_migration_complete", true));
        }

        return new ab(arg4);
    }

    void a(boolean arg4) {
        this.a.a(this.a.b().putBoolean("always_send_reports_opt_in", arg4));
    }

    boolean a() {
        return this.a.a().getBoolean("always_send_reports_opt_in", false);
    }
}

