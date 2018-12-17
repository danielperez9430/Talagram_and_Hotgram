package org.telegram.messenger;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AppStartReceiver extends BroadcastReceiver {
    public AppStartReceiver() {
        super();
    }

    public void onReceive(Context arg1, Intent arg2) {
        AndroidUtilities.runOnUIThread(new Runnable() {
            public void run() {
                ApplicationLoader.startPushService();
            }
        });
    }
}

