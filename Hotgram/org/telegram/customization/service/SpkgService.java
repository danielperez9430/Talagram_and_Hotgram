package org.telegram.customization.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class SpkgService extends Service {
    public SpkgService() {
        super();
    }

    public IBinder onBind(Intent arg1) {
        return null;
    }

    public void onCreate() {
        super.onCreate();
    }

    public int onStartCommand(Intent arg1, int arg2, int arg3) {
        arg1 = new Intent("android.intent.action.MAIN", null);
        arg1.addCategory("android.intent.category.LAUNCHER");
        this.getPackageManager().queryIntentActivities(arg1, 0);
        return 2;
    }
}

