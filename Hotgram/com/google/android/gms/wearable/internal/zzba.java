package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder;

final class zzba extends zzn {
    zzba(zzay arg1, GoogleApiClient arg2, int arg3) {
        this.zzcm = arg1;
        this.zzcn = arg3;
        super(arg2);
    }

    protected final Result createFailedResult(Status arg1) {
        return arg1;
    }

    protected final void doExecute(AnyClient arg4) {
        ((zzhg)arg4).getService().zzb(new zzgo(((ResultHolder)this)), zzay.zza(this.zzcm), this.zzcn);
    }
}

