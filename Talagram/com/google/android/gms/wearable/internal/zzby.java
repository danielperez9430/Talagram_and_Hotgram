package com.google.android.gms.wearable.internal;

import android.net.Uri;
import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder;

final class zzby extends zzn {
    zzby(zzbw arg1, GoogleApiClient arg2, Uri arg3) {
        this.zzco = arg3;
        super(arg2);
    }

    protected final Result createFailedResult(Status arg3) {
        return new zzcg(arg3, null);
    }

    protected final void doExecute(AnyClient arg3) {
        ((zzhg)arg3).getService().zza(new zzgv(((ResultHolder)this)), this.zzco);
    }
}

