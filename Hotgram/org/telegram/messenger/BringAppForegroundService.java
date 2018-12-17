package org.telegram.messenger;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import org.telegram.ui.LaunchActivity;

public class BringAppForegroundService extends IntentService {
    public BringAppForegroundService() {
        super("BringAppForegroundService");
    }

    protected void onHandleIntent(Intent arg2) {
        arg2 = new Intent(((Context)this), LaunchActivity.class);
        arg2.setFlags(268435456);
        this.startActivity(arg2);
    }
}

