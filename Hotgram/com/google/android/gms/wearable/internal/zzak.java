package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder;

final class zzak extends zzn {
    zzak(zzaj arg1, GoogleApiClient arg2, String arg3, String arg4) {
        this.zzcb = arg3;
        this.zzcc = arg4;
        super(arg2);
    }

    public final Result createFailedResult(Status arg3) {
        return new zzam(arg3, null);
    }

    protected final void doExecute(AnyClient arg4) {
        ((zzhg)arg4).getService().zza(new zzha(((ResultHolder)this)), this.zzcb, this.zzcc);
    }
}

