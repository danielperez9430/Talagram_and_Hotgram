package com.google.android.gms.internal.config;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.GmsClient;

public final class zzw extends GmsClient {
    public zzw(Context arg8, Looper arg9, ClientSettings arg10, ConnectionCallbacks arg11, OnConnectionFailedListener arg12) {
        super(arg8, arg9, 64, arg10, arg11, arg12);
    }

    protected final IInterface createServiceInterface(IBinder arg3) {
        if(arg3 == null) {
            return null;
        }

        IInterface v0 = arg3.queryLocalInterface("com.google.android.gms.config.internal.IConfigService");
        if((v0 instanceof zzah)) {
            return v0;
        }

        return new zzai(arg3);
    }

    public final int getMinApkVersion() {
        return 12451000;
    }

    protected final String getServiceDescriptor() {
        return "com.google.android.gms.config.internal.IConfigService";
    }

    protected final String getStartServiceAction() {
        return "com.google.android.gms.config.START";
    }
}

