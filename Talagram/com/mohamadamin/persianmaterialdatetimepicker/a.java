package com.mohamadamin.persianmaterialdatetimepicker;

import android.content.Context;
import android.database.ContentObserver;
import android.os.SystemClock;
import android.os.Vibrator;
import android.provider.Settings$System;

public class a {
    private final Context a;
    private final ContentObserver b;
    private Vibrator c;
    private boolean d;
    private long e;

    public a(Context arg2) {
        super();
        this.a = arg2;
        this.b = new ContentObserver(null) {
            public void onChange(boolean arg2) {
                a.a(this.a, a.a(a.a(this.a)));
            }
        };
    }

    static boolean a(a arg0, boolean arg1) {
        arg0.d = arg1;
        return arg1;
    }

    static boolean a(Context arg0) {
        return a.b(arg0);
    }

    static Context a(a arg0) {
        return arg0.a;
    }

    public void a() {
        this.c = this.a.getSystemService("vibrator");
        this.d = a.b(this.a);
        this.a.getContentResolver().registerContentObserver(Settings$System.getUriFor("haptic_feedback_enabled"), false, this.b);
    }

    private static boolean b(Context arg2) {
        boolean v0 = true;
        if(Settings$System.getInt(arg2.getContentResolver(), "haptic_feedback_enabled", 0) == 1) {
        }
        else {
            v0 = false;
        }

        return v0;
    }

    public void b() {
        this.c = null;
        this.a.getContentResolver().unregisterContentObserver(this.b);
    }

    public void c() {
        if(this.c != null && (this.d)) {
            long v0 = SystemClock.uptimeMillis();
            if(v0 - this.e >= 125) {
                this.c.vibrate(5);
                this.e = v0;
            }
        }
    }
}

