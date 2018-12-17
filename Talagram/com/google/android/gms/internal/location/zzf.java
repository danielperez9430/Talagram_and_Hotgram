package com.google.android.gms.internal.location;

import android.app.PendingIntent;
import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;

final class zzf extends zzj {
    zzf(zze arg1, GoogleApiClient arg2, long arg3, PendingIntent arg5) {
        this.zzbw = arg3;
        this.zzbx = arg5;
        super(arg2);
    }

    protected final void doExecute(AnyClient arg4) {
        ((zzaz)arg4).zza(this.zzbw, this.zzbx);
        this.setResult(Status.RESULT_SUCCESS);
    }
}

