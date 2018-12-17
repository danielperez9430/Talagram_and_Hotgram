package org.telegram.messenger;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class StopLiveLocationReceiver extends BroadcastReceiver {
    public StopLiveLocationReceiver() {
        super();
    }

    public void onReceive(Context arg1, Intent arg2) {
        int v1;
        for(v1 = 0; v1 < 3; ++v1) {
            LocationController.getInstance(v1).removeAllLocationSharings();
        }
    }
}

