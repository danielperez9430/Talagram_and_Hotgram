package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.wearable.DataItemBuffer;

final class zzbz extends zzn {
    zzbz(zzbw arg1, GoogleApiClient arg2) {
        super(arg2);
    }

    protected final Result createFailedResult(Status arg2) {
        return new DataItemBuffer(DataHolder.empty(arg2.getStatusCode()));
    }

    protected final void doExecute(AnyClient arg2) {
        ((zzhg)arg2).getService().zza(new zzgw(((ResultHolder)this)));
    }
}

