package org.telegram.messenger;

import android.app.IntentService;
import android.content.Intent;

public class NotificationRepeat extends IntentService {
    public NotificationRepeat() {
        super("NotificationRepeat");
    }

    protected void onHandleIntent(Intent arg3) {
        if(arg3 == null) {
            return;
        }

        AndroidUtilities.runOnUIThread(new Runnable(arg3.getIntExtra("currentAccount", UserConfig.selectedAccount)) {
            public void run() {
                NotificationsController.getInstance(this.val$currentAccount).repeatNotificationMaybe();
            }
        });
    }
}

