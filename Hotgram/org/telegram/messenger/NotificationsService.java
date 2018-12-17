package org.telegram.messenger;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class NotificationsService extends Service {
    public NotificationsService() {
        super();
    }

    public IBinder onBind(Intent arg1) {
        return null;
    }

    public void onCreate() {
        ApplicationLoader.postInitApplication();
    }

    public void onDestroy() {
        if(MessagesController.getGlobalNotificationsSettings().getBoolean("pushService", true)) {
            this.sendBroadcast(new Intent("org.telegram.start"));
        }
    }

    public int onStartCommand(Intent arg1, int arg2, int arg3) {
        return 1;
    }
}

