package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder;

final class zzaz extends zzn {
    zzaz(zzay arg1, GoogleApiClient arg2) {
        this.zzcm = arg1;
        super(arg2);
    }

    protected final Result createFailedResult(Status arg1) {
        return arg1;
    }

    protected final void doExecute(AnyClient arg3) {
        ((zzhg)arg3).getService().zzc(new zzgn(((ResultHolder)this)), zzay.zza(this.zzcm));
    }
}

