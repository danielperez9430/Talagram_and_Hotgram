package com.google.android.gms.internal.location;

import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder;
import com.google.android.gms.location.zzal;

final class zzah extends zzai {
    zzah(zzaf arg1, GoogleApiClient arg2, zzal arg3) {
        this.zzct = arg3;
        super(arg2);
    }

    protected final void doExecute(AnyClient arg2) {
        ((zzaz)arg2).zza(this.zzct, ((ResultHolder)this));
    }
}

