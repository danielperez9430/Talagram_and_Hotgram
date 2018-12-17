package org.telegram.messenger.support.customtabs;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;

public abstract class PostMessageServiceConnection implements ServiceConnection {
    private final Object mLock;
    private IPostMessageService mService;
    private final ICustomTabsCallback mSessionBinder;

    public PostMessageServiceConnection(CustomTabsSessionToken arg2) {
        super();
        this.mLock = new Object();
        this.mSessionBinder = Stub.asInterface(arg2.getCallbackBinder());
    }

    public boolean bindSessionToPostMessageService(Context arg3, String arg4) {
        Intent v0 = new Intent();
        v0.setClassName(arg4, PostMessageService.class.getName());
        return arg3.bindService(v0, ((ServiceConnection)this), 1);
    }

    public final boolean notifyMessageChannelReady(Bundle arg5) {
        if(this.mService == null) {
            return 0;
        }

        Object v0 = this.mLock;
        __monitor_enter(v0);
        try {
            this.mService.onMessageChannelReady(this.mSessionBinder, arg5);
        }
        catch(RemoteException ) {
            __monitor_exit(v0);
            return 0;
        }

        __monitor_exit(v0);
        return 1;
        __monitor_exit(v0);
        throw v5;
    }

    public void onPostMessageServiceConnected() {
    }

    public void onPostMessageServiceDisconnected() {
    }

    public final void onServiceConnected(ComponentName arg1, IBinder arg2) {
        this.mService = org.telegram.messenger.support.customtabs.IPostMessageService$Stub.asInterface(arg2);
        this.onPostMessageServiceConnected();
    }

    public final void onServiceDisconnected(ComponentName arg1) {
        this.mService = null;
        this.onPostMessageServiceDisconnected();
    }

    public final boolean postMessage(String arg5, Bundle arg6) {
        if(this.mService == null) {
            return 0;
        }

        Object v0 = this.mLock;
        __monitor_enter(v0);
        try {
            this.mService.onPostMessage(this.mSessionBinder, arg5, arg6);
        }
        catch(RemoteException ) {
            __monitor_exit(v0);
            return 0;
        }

        __monitor_exit(v0);
        return 1;
        __monitor_exit(v0);
        throw v5;
    }

    public void unbindFromContext(Context arg1) {
        arg1.unbindService(((ServiceConnection)this));
    }
}

