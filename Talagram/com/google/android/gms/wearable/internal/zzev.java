package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder;

final class zzev extends zzn {
    zzev(zzeu arg1, GoogleApiClient arg2, String arg3, String arg4, byte[] arg5) {
        this.zzcb = arg3;
        this.val$action = arg4;
        this.zzee = arg5;
        super(arg2);
    }

    protected final Result createFailedResult(Status arg3) {
        return new zzey(arg3, -1);
    }

    protected final void doExecute(AnyClient arg5) {
        ((zzhg)arg5).getService().zza(new zzhe(((ResultHolder)this)), this.zzcb, this.val$action, this.zzee);
    }
}

