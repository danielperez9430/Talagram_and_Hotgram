package org.telegram.customization.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import java.util.Calendar;
import org.telegram.customization.i.h;
import org.telegram.customization.i.i;
import org.telegram.customization.i.j;
import org.telegram.customization.voip.SipWhatsUp;
import org.telegram.customization.voip.SipWrapper;
import org.telegram.messenger.ApplicationLoader;

public abstract class a extends Service {
    public j a;
    public volatile SipWrapper b;
    public SipWhatsUp c;
    private i d;

    public a() {
        super();
    }

    public static void a(Context arg8, Intent arg9, Calendar arg10, long arg11) {
        PendingIntent v7 = PendingIntent.getService(arg8, 0, arg9, 0);
        Object v1 = arg8.getSystemService("alarm");
        try {
            ((AlarmManager)v1).cancel(v7);
            goto label_6;
        }
        catch(Exception ) {
        label_6:
            ((AlarmManager)v1).setRepeating(0, arg10.getTimeInMillis(), arg11, v7);
            return;
        }
    }

    protected h a() {
        return null;
    }

    public void b() {
        h v0 = this.a();
        if(v0 != null) {
            this.d = new i(v0);
            this.a.a(this.d);
        }
    }

    public void c() {
        if(this.d != null) {
            this.a.b(this.d);
        }
    }

    public void onCreate() {
        super.onCreate();
        ApplicationLoader.getComponent().a(this);
        this.b();
    }

    public void onDestroy() {
        super.onDestroy();
        this.c();
    }
}

