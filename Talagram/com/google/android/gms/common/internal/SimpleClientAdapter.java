package com.google.android.gms.common.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import com.google.android.gms.common.api.Api$SimpleClient;
import com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener;

public class SimpleClientAdapter extends GmsClient {
    private final SimpleClient zzva;

    public SimpleClientAdapter(Context arg8, Looper arg9, int arg10, ConnectionCallbacks arg11, OnConnectionFailedListener arg12, ClientSettings arg13, SimpleClient arg14) {
        super(arg8, arg9, arg10, arg13, arg11, arg12);
        this.zzva = arg14;
    }

    protected IInterface createServiceInterface(IBinder arg2) {
        return this.zzva.createServiceInterface(arg2);
    }

    public SimpleClient getClient() {
        return this.zzva;
    }

    public int getMinApkVersion() {
        return super.getMinApkVersion();
    }

    protected String getServiceDescriptor() {
        return this.zzva.getServiceDescriptor();
    }

    protected String getStartServiceAction() {
        return this.zzva.getStartServiceAction();
    }

    protected void onSetConnectState(int arg2, IInterface arg3) {
        this.zzva.setState(arg2, arg3);
    }
}

