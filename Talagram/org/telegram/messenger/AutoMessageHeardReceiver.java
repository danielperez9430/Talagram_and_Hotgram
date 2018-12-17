package org.telegram.messenger;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AutoMessageHeardReceiver extends BroadcastReceiver {
    public AutoMessageHeardReceiver() {
        super();
    }

    public void onReceive(Context arg12, Intent arg13) {
        ApplicationLoader.postInitApplication();
        long v3 = arg13.getLongExtra("dialog_id", 0);
        int v6 = arg13.getIntExtra("max_id", 0);
        int v12 = arg13.getIntExtra("currentAccount", 0);
        if(v3 != 0) {
            if(v6 == 0) {
            }
            else {
                MessagesController.getInstance(v12).markDialogAsRead(v3, v6, v6, 0, false, 0, true);
            }
        }
    }
}

