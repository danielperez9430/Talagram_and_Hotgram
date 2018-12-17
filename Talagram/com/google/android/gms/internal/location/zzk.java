package com.google.android.gms.internal.location;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.GmsClient;

public class zzk extends GmsClient {
    private final String zzca;
    protected final zzbj zzcb;

    public zzk(Context arg8, Looper arg9, ConnectionCallbacks arg10, OnConnectionFailedListener arg11, String arg12, ClientSettings arg13) {
        super(arg8, arg9, 23, arg13, arg10, arg11);
        this.zzcb = new zzl(this);
        this.zzca = arg12;
    }

    protected IInterface createServiceInterface(IBinder arg3) {
        if(arg3 == null) {
            return null;
        }

        IInterface v0 = arg3.queryLocalInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
        if((v0 instanceof zzao)) {
            return v0;
        }

        return new zzap(arg3);
    }

    protected Bundle getGetServiceRequestExtraArgs() {
        Bundle v0 = new Bundle();
        v0.putString("client_name", this.zzca);
        return v0;
    }

    public int getMinApkVersion() {
        return 11925000;
    }

    protected String getServiceDescriptor() {
        return "com.google.android.gms.location.internal.IGoogleLocationManagerService";
    }

    protected String getStartServiceAction() {
        return "com.google.android.location.internal.GoogleLocationManagerService.START";
    }

    static void zza(zzk arg0) {
        arg0.checkConnected();
    }
}

