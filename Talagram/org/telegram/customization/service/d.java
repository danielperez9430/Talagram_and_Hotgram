package org.telegram.customization.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import org.telegram.customization.g.c;

public class d extends Service {
    public d() {
        super();
    }

    public IBinder onBind(Intent arg1) {
        return null;
    }

    public void onCreate() {
        super.onCreate();
    }

    public int onStartCommand(Intent arg1, int arg2, int arg3) {
        c.a(this.getApplicationContext(), new org.telegram.customization.g.d() {
            public void onResult(Object arg1, int arg2) {
                this.a.stopSelf();
            }
        }).c();
        return 2;
    }
}

