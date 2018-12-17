package org.telegram.messenger.voip;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class VoIPActionsReceiver extends BroadcastReceiver {
    public VoIPActionsReceiver() {
        super();
    }

    public void onReceive(Context arg1, Intent arg2) {
        if(VoIPBaseService.getSharedInstance() != null) {
            VoIPBaseService.getSharedInstance().handleNotificationAction(arg2);
        }
    }
}

