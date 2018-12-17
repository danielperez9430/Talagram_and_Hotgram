package com.google.ads.interactivemedia.v3.internal;

import android.content.Context;
import android.database.ContentObserver;
import android.media.AudioManager;
import android.os.Handler;
import android.provider.Settings$System;

public final class n extends ContentObserver {
    private final Context a;
    private final AudioManager b;
    private final k c;
    private final m d;
    private float e;

    public n(Handler arg1, Context arg2, k arg3, m arg4) {
        super(arg1);
        this.a = arg2;
        this.b = arg2.getSystemService("audio");
        this.c = arg3;
        this.d = arg4;
    }

    private boolean a(float arg2) {
        boolean v2 = arg2 != this.e ? true : false;
        return v2;
    }

    public void a() {
        this.e = this.c();
        this.d();
        this.a.getContentResolver().registerContentObserver(Settings$System.CONTENT_URI, true, ((ContentObserver)this));
    }

    public void b() {
        this.a.getContentResolver().unregisterContentObserver(((ContentObserver)this));
    }

    private float c() {
        return this.c.a(this.b.getStreamVolume(3), this.b.getStreamMaxVolume(3));
    }

    private void d() {
        this.d.a(this.e);
    }

    public void onChange(boolean arg2) {
        super.onChange(arg2);
        float v2 = this.c();
        if(this.a(v2)) {
            this.e = v2;
            this.d();
        }
    }
}

