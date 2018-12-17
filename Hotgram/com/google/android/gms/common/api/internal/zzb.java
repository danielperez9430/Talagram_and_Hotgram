package com.google.android.gms.common.api.internal;

import android.os.RemoteException;
import android.os.TransactionTooLargeException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.util.PlatformVersion;

public abstract class zzb {
    private final int type;

    public zzb(int arg1) {
        super();
        this.type = arg1;
    }

    public abstract void zza(Status arg1);

    public abstract void zza(RuntimeException arg1);

    public abstract void zza(zzaa arg1, boolean arg2);

    public abstract void zza(zza arg1);

    private static Status zza(RemoteException arg2) {
        StringBuilder v0 = new StringBuilder();
        if((PlatformVersion.isAtLeastIceCreamSandwichMR1()) && ((arg2 instanceof TransactionTooLargeException))) {
            v0.append("TransactionTooLargeException: ");
        }

        v0.append(arg2.getLocalizedMessage());
        return new Status(8, v0.toString());
    }

    static Status zzb(RemoteException arg0) {
        return zzb.zza(arg0);
    }
}

