package com.google.android.gms.wearable.internal;

import android.net.Uri;
import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder;

final class zzbd extends zzn {
    zzbd(zzay arg1, GoogleApiClient arg2, Uri arg3, boolean arg4) {
        this.zzcm = arg1;
        this.zzco = arg3;
        this.zzcp = arg4;
        super(arg2);
    }

    public final Result createFailedResult(Status arg1) {
        return arg1;
    }

    protected final void doExecute(AnyClient arg4) {
        ((zzhg)arg4).zza(((ResultHolder)this), zzay.zza(this.zzcm), this.zzco, this.zzcp);
    }
}

