package org.telegram.messenger.support.customtabs;

import android.os.Bundle;

public class CustomTabsCallback {
    public static final int NAVIGATION_ABORTED = 4;
    public static final int NAVIGATION_FAILED = 3;
    public static final int NAVIGATION_FINISHED = 2;
    public static final int NAVIGATION_STARTED = 1;
    public static final int TAB_HIDDEN = 6;
    public static final int TAB_SHOWN = 5;

    public CustomTabsCallback() {
        super();
    }

    public void extraCallback(String arg1, Bundle arg2) {
    }

    public void onMessageChannelReady(Bundle arg1) {
    }

    public void onNavigationEvent(int arg1, Bundle arg2) {
    }

    public void onPostMessage(String arg1, Bundle arg2) {
    }
}

