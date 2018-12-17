package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder;

final class zzr extends zzn {
    zzr(zzo arg1, GoogleApiClient arg2, String arg3) {
        this.zzbp = arg3;
        super(arg2);
    }

    protected final Result createFailedResult(Status arg2) {
        return new zzu(arg2);
    }

    protected final void doExecute(AnyClient arg3) {
        ((zzhg)arg3).getService().zza(new zzgl(((ResultHolder)this)), this.zzbp);
    }
}

