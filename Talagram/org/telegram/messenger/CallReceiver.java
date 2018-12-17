package org.telegram.messenger;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import org.telegram.a.b;

public class CallReceiver extends BroadcastReceiver {
    public CallReceiver() {
        super();
    }

    public void onReceive(Context arg4, Intent arg5) {
        if((arg5.getAction().equals("android.intent.action.PHONE_STATE")) && (TelephonyManager.EXTRA_STATE_RINGING.equals(arg5.getStringExtra("state")))) {
            NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.didReceiveCall, new Object[]{b.b(arg5.getStringExtra("incoming_number"))});
        }
    }
}

