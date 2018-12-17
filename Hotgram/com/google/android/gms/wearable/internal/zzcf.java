package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder;
import com.google.android.gms.wearable.DataApi$DataListener;

final class zzcf extends zzn {
    zzcf(zzbw arg1, GoogleApiClient arg2, DataListener arg3) {
        this.zzdf = arg3;
        super(arg2);
    }

    public final Result createFailedResult(Status arg1) {
        return arg1;
    }

    protected final void doExecute(AnyClient arg2) {
        ((zzhg)arg2).zza(((ResultHolder)this), this.zzdf);
    }
}

