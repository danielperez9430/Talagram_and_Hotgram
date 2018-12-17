package org.telegram.messenger.support.customtabs;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;

public class PostMessageService extends Service {
    class org.telegram.messenger.support.customtabs.PostMessageService$1 extends Stub {
        org.telegram.messenger.support.customtabs.PostMessageService$1(PostMessageService arg1) {
            PostMessageService.this = arg1;
            super();
        }

        public void onMessageChannelReady(ICustomTabsCallback arg1, Bundle arg2) {
            arg1.onMessageChannelReady(arg2);
        }

        public void onPostMessage(ICustomTabsCallback arg1, String arg2, Bundle arg3) {
            arg1.onPostMessage(arg2, arg3);
        }
    }

    private Stub mBinder;

    public PostMessageService() {
        super();
        this.mBinder = new org.telegram.messenger.support.customtabs.PostMessageService$1(this);
    }

    public IBinder onBind(Intent arg1) {
        return this.mBinder;
    }
}

