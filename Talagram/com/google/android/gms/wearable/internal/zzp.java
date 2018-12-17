package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder;

final class zzp extends zzn {
    zzp(zzo arg1, GoogleApiClient arg2, String arg3, int arg4) {
        this.zzbp = arg3;
        this.zzbq = arg4;
        super(arg2);
    }

    protected final Result createFailedResult(Status arg3) {
        return new zzy(arg3, null);
    }

    protected final void doExecute(AnyClient arg4) {
        ((zzhg)arg4).getService().zza(new zzgr(((ResultHolder)this)), this.zzbp, this.zzbq);
    }
}

