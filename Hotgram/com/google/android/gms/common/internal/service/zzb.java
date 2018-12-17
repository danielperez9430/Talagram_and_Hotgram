package com.google.android.gms.common.internal.service;

import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder;
import com.google.android.gms.common.internal.BaseGmsClient;

final class zzb extends zzd {
    zzb(CommonApiImpl arg1, GoogleApiClient arg2) {
        super(arg2);
    }

    protected final void doExecute(AnyClient arg2) {
        ((BaseGmsClient)arg2).getService().clearDefaultAccount(new zza(((ResultHolder)this)));
    }
}

