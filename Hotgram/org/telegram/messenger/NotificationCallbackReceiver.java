package org.telegram.messenger;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class NotificationCallbackReceiver extends BroadcastReceiver {
    public NotificationCallbackReceiver() {
        super();
    }

    public void onReceive(Context arg6, Intent arg7) {
        if(arg7 == null) {
            return;
        }

        ApplicationLoader.postInitApplication();
        SendMessagesHelper.getInstance(arg7.getIntExtra("currentAccount", UserConfig.selectedAccount)).sendNotificationCallback(arg7.getLongExtra("did", 777000), arg7.getIntExtra("mid", 0), arg7.getByteArrayExtra("data"));
    }
}

