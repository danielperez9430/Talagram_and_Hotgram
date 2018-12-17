package org.telegram.messenger;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class CustomTabsCopyReceiver extends BroadcastReceiver {
    public CustomTabsCopyReceiver() {
        super();
    }

    public void onReceive(Context arg2, Intent arg3) {
        String v3 = arg3.getDataString();
        if(v3 != null) {
            AndroidUtilities.addToClipboard(((CharSequence)v3));
            Toast.makeText(arg2, LocaleController.getString("LinkCopied", 2131625087), 0).show();
        }
    }
}

