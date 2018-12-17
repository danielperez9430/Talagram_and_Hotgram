package org.telegram.customization.voip.linphoneSip.linphone;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class KeepAliveHandler extends BroadcastReceiver {
    private static final String TAG = "KeepAliveHandler";

    public KeepAliveHandler() {
        super();
    }

    public void onReceive(Context arg2, Intent arg3) {
        Log.e("KeepAliveHandler", "Keep alive handler invoked");
        if(LinphoneManager.getLcIfManagerNotDestroyOrNull() != null) {
            LinphoneManager.getLc().refreshRegisters();
            long v2 = 2000;
            try {
                Thread.sleep(v2);
            }
            catch(InterruptedException v2_1) {
                Log.e("KeepAliveHandler", "Cannot sleep for 2s", ((Throwable)v2_1));
            }
        }
    }
}

