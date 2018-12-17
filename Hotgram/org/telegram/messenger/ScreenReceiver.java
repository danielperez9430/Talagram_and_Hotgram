package org.telegram.messenger;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import org.telegram.tgnet.ConnectionsManager;

public class ScreenReceiver extends BroadcastReceiver {
    public ScreenReceiver() {
        super();
    }

    public void onReceive(Context arg3, Intent arg4) {
        if(arg4.getAction().equals("android.intent.action.SCREEN_OFF")) {
            if(BuildVars.LOGS_ENABLED) {
                FileLog.d("screen off");
            }

            ConnectionsManager.getInstance(UserConfig.selectedAccount).setAppPaused(true, true);
            ApplicationLoader.isScreenOn = false;
        }
        else {
            if(!arg4.getAction().equals("android.intent.action.SCREEN_ON")) {
                return;
            }

            if(BuildVars.LOGS_ENABLED) {
                FileLog.d("screen on");
            }

            ConnectionsManager.getInstance(UserConfig.selectedAccount).setAppPaused(false, true);
            ApplicationLoader.isScreenOn = true;
        }
    }
}

