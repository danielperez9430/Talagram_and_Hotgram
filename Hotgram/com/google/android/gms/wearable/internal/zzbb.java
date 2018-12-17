package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder;

final class zzbb extends zzn {
    zzbb(zzay arg1, GoogleApiClient arg2) {
        this.zzcm = arg1;
        super(arg2);
    }

    public final Result createFailedResult(Status arg3) {
        return new zzbg(arg3, null);
    }

    protected final void doExecute(AnyClient arg4) {
        String v0 = zzay.zza(this.zzcm);
        zzbr v1 = new zzbr();
        ((zzhg)arg4).getService().zza(new zzgs(((ResultHolder)this), v1), ((zzei)v1), v0);
    }
}

