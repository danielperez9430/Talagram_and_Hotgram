package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder;
import com.google.android.gms.wearable.Asset;
import com.google.android.gms.wearable.DataItemAsset;

final class zzcd extends zzn {
    zzcd(zzbw arg1, GoogleApiClient arg2, DataItemAsset arg3) {
        this.zzde = arg3;
        super(arg2);
    }

    protected final Result createFailedResult(Status arg3) {
        return new zzci(arg3, null);
    }

    protected final void doExecute(AnyClient arg2) {
        ((zzhg)arg2).zza(((ResultHolder)this), Asset.createFromRef(this.zzde.getId()));
    }
}

