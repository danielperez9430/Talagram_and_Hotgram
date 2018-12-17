package com.google.android.gms.wearable.internal;

import android.net.Uri;
import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder;

final class zzcb extends zzn {
    zzcb(zzbw arg1, GoogleApiClient arg2, Uri arg3, int arg4) {
        this.zzco = arg3;
        this.zzdc = arg4;
        super(arg2);
    }

    protected final Result createFailedResult(Status arg3) {
        return new zzch(arg3, 0);
    }

    protected final void doExecute(AnyClient arg4) {
        ((zzhg)arg4).getService().zzb(new zzgp(((ResultHolder)this)), this.zzco, this.zzdc);
    }
}

