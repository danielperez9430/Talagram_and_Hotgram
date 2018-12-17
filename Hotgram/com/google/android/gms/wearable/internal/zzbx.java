package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder;
import com.google.android.gms.wearable.PutDataRequest;

final class zzbx extends zzn {
    zzbx(zzbw arg1, GoogleApiClient arg2, PutDataRequest arg3) {
        this.zzdb = arg3;
        super(arg2);
    }

    public final Result createFailedResult(Status arg3) {
        return new zzcg(arg3, null);
    }

    protected final void doExecute(AnyClient arg2) {
        ((zzhg)arg2).zza(((ResultHolder)this), this.zzdb);
    }
}

