package com.google.android.gms.internal.phenotype;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.GmsClient;

public final class zze extends GmsClient {
    public zze(Context arg8, Looper arg9, ClientSettings arg10, ConnectionCallbacks arg11, OnConnectionFailedListener arg12) {
        super(arg8, arg9, 51, arg10, arg11, arg12);
    }

    protected final IInterface createServiceInterface(IBinder arg3) {
        if(arg3 == null) {
            return null;
        }

        IInterface v0 = arg3.queryLocalInterface("com.google.android.gms.phenotype.internal.IPhenotypeService");
        if((v0 instanceof zzb)) {
            return v0;
        }

        return new zzc(arg3);
    }

    public final int getMinApkVersion() {
        return 11925000;
    }

    protected final String getServiceDescriptor() {
        return "com.google.android.gms.phenotype.internal.IPhenotypeService";
    }

    protected final String getStartServiceAction() {
        return "com.google.android.gms.phenotype.service.START";
    }
}

