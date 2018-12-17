package org.telegram.messenger;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class RefererReceiver extends BroadcastReceiver {
    public RefererReceiver() {
        super();
    }

    public void onReceive(Context arg2, Intent arg3) {
        try {
            MessagesController.getInstance(UserConfig.selectedAccount).setReferer(arg3.getExtras().getString("referrer"));
            return;
        }
        catch(Exception ) {
            return;
        }
    }
}

