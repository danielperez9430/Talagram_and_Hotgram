package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder;

final class zzfh extends zzn {
    zzfh(zzfg arg1, GoogleApiClient arg2) {
        super(arg2);
    }

    protected final Result createFailedResult(Status arg3) {
        return new zzfk(arg3, null);
    }

    protected final void doExecute(AnyClient arg2) {
        ((zzhg)arg2).getService().zzb(new zzgy(((ResultHolder)this)));
    }
}

