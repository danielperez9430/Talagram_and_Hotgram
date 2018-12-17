package org.telegram.messenger;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class NotificationDismissReceiver extends BroadcastReceiver {
    public NotificationDismissReceiver() {
        super();
    }

    public void onReceive(Context arg4, Intent arg5) {
        if(arg5 == null) {
            return;
        }

        MessagesController.getNotificationsSettings(arg5.getIntExtra("currentAccount", UserConfig.selectedAccount)).edit().putInt("dismissDate", arg5.getIntExtra("messageDate", 0)).commit();
    }
}

