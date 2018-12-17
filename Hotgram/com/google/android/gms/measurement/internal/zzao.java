package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import com.google.android.gms.common.internal.BaseGmsClient$BaseConnectionCallbacks;
import com.google.android.gms.common.internal.BaseGmsClient$BaseOnConnectionFailedListener;
import com.google.android.gms.common.internal.BaseGmsClient;

public final class zzao extends BaseGmsClient {
    public zzao(Context arg8, Looper arg9, BaseConnectionCallbacks arg10, BaseOnConnectionFailedListener arg11) {
        super(arg8, arg9, 93, arg10, arg11, null);
    }

    public final IInterface createServiceInterface(IBinder arg3) {
        if(arg3 == null) {
            return null;
        }

        IInterface v0 = arg3.queryLocalInterface("com.google.android.gms.measurement.internal.IMeasurementService");
        if((v0 instanceof zzag)) {
            return v0;
        }

        return new zzai(arg3);
    }

    public final int getMinApkVersion() {
        return 12451000;
    }

    protected final String getServiceDescriptor() {
        return "com.google.android.gms.measurement.internal.IMeasurementService";
    }

    protected final String getStartServiceAction() {
        return "com.google.android.gms.measurement.START";
    }
}

