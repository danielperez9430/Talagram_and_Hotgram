package com.google.android.gms.internal.config;

import android.content.Context;
import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.BaseImplementation$ApiMethodImpl;

abstract class zzr extends ApiMethodImpl {
    public zzr(GoogleApiClient arg2) {
        super(zze.API, arg2);
    }

    protected void doExecute(AnyClient arg2) {
        this.zza(((zzw)arg2).getContext(), ((zzw)arg2).getService());
    }

    protected abstract void zza(Context arg1, zzah arg2);
}

