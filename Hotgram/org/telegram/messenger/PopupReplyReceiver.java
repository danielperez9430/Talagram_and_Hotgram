package org.telegram.messenger;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class PopupReplyReceiver extends BroadcastReceiver {
    public PopupReplyReceiver() {
        super();
    }

    public void onReceive(Context arg2, Intent arg3) {
        if(arg3 == null) {
            return;
        }

        ApplicationLoader.postInitApplication();
        NotificationsController.getInstance(arg3.getIntExtra("currentAccount", UserConfig.selectedAccount)).forceShowPopupForReply();
    }
}

