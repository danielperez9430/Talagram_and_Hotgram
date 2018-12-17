package org.telegram.messenger.voip;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class VoIPMediaButtonReceiver extends BroadcastReceiver {
    public VoIPMediaButtonReceiver() {
        super();
    }

    public void onReceive(Context arg2, Intent arg3) {
        if("android.intent.action.MEDIA_BUTTON".equals(arg3.getAction())) {
            if(VoIPService.getSharedInstance() == null) {
                return;
            }
            else {
                VoIPService.getSharedInstance().onMediaButtonEvent(arg3.getParcelableExtra("android.intent.extra.KEY_EVENT"));
            }
        }
    }
}

