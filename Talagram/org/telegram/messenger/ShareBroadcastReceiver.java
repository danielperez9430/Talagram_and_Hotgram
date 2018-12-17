package org.telegram.messenger;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class ShareBroadcastReceiver extends BroadcastReceiver {
    public ShareBroadcastReceiver() {
        super();
    }

    public void onReceive(Context arg3, Intent arg4) {
        String v4 = arg4.getDataString();
        if(v4 != null) {
            Intent v0 = new Intent("android.intent.action.SEND");
            v0.setType("text/plain");
            v0.putExtra("android.intent.extra.TEXT", v4);
            arg4 = Intent.createChooser(v0, LocaleController.getString("ShareLink", 2131626061));
            arg4.setFlags(268435456);
            arg3.startActivity(arg4);
        }
    }
}

