package com.google.android.gms.internal.location;

import android.app.PendingIntent;
import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder;
import com.google.android.gms.location.ActivityTransitionRequest;

final class zzh extends zzj {
    zzh(zze arg1, GoogleApiClient arg2, ActivityTransitionRequest arg3, PendingIntent arg4) {
        this.zzby = arg3;
        this.zzbz = arg4;
        super(arg2);
    }

    protected final void doExecute(AnyClient arg3) {
        ((zzaz)arg3).zza(this.zzby, this.zzbz, ((ResultHolder)this));
    }
}

