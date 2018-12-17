package com.google.android.gms.common.internal;

import android.content.Context;
import android.os.IInterface;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener;

@Deprecated public abstract class LegacyInternalGmsClient extends GmsClient {
    private final GmsClientEventManager zzie;

    public LegacyInternalGmsClient(Context arg2, int arg3, ClientSettings arg4, ConnectionCallbacks arg5, OnConnectionFailedListener arg6) {
        super(arg2, arg2.getMainLooper(), arg3, arg4);
        this.zzie = new GmsClientEventManager(arg2.getMainLooper(), ((GmsClientEventState)this));
        this.zzie.registerConnectionCallbacks(arg5);
        this.zzie.registerConnectionFailedListener(arg6);
    }

    public void checkAvailabilityAndConnect() {
        this.zzie.enableCallbacks();
        super.checkAvailabilityAndConnect();
    }

    public void disconnect() {
        this.zzie.disableCallbacks();
        super.disconnect();
    }

    public int getMinApkVersion() {
        return super.getMinApkVersion();
    }

    public boolean isConnectionCallbacksRegistered(ConnectionCallbacks arg2) {
        return this.zzie.isConnectionCallbacksRegistered(arg2);
    }

    public boolean isConnectionFailedListenerRegistered(OnConnectionFailedListener arg2) {
        return this.zzie.isConnectionFailedListenerRegistered(arg2);
    }

    public void onConnectedLocked(IInterface arg2) {
        super.onConnectedLocked(arg2);
        this.zzie.onConnectionSuccess(((BaseGmsClient)this).getConnectionHint());
    }

    public void onConnectionFailed(ConnectionResult arg2) {
        super.onConnectionFailed(arg2);
        this.zzie.onConnectionFailure(arg2);
    }

    public void onConnectionSuspended(int arg2) {
        super.onConnectionSuspended(arg2);
        this.zzie.onUnintentionalDisconnection(arg2);
    }

    public void registerConnectionCallbacks(ConnectionCallbacks arg2) {
        this.zzie.registerConnectionCallbacks(arg2);
    }

    public void registerConnectionFailedListener(OnConnectionFailedListener arg2) {
        this.zzie.registerConnectionFailedListener(arg2);
    }

    public void unregisterConnectionCallbacks(ConnectionCallbacks arg2) {
        this.zzie.unregisterConnectionCallbacks(arg2);
    }

    public void unregisterConnectionFailedListener(OnConnectionFailedListener arg2) {
        this.zzie.unregisterConnectionFailedListener(arg2);
    }
}

