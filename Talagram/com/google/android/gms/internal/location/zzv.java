package com.google.android.gms.internal.location;

import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder;

final class zzv extends zzab {
    zzv(zzq arg1, GoogleApiClient arg2) {
        super(arg2);
    }

    protected final void doExecute(AnyClient arg2) {
        ((zzaz)arg2).zza(new zzac(((ResultHolder)this)));
    }
}

