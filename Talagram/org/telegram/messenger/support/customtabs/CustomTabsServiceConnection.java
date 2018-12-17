package org.telegram.messenger.support.customtabs;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;

public abstract class CustomTabsServiceConnection implements ServiceConnection {
    public CustomTabsServiceConnection() {
        super();
    }

    public abstract void onCustomTabsServiceConnected(ComponentName arg1, CustomTabsClient arg2);

    public final void onServiceConnected(ComponentName arg2, IBinder arg3) {
        this.onCustomTabsServiceConnected(arg2, new CustomTabsClient(Stub.asInterface(arg3), arg2) {
        });
    }
}

