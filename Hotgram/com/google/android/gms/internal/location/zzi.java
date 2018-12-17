package com.google.android.gms.internal.location;

import android.app.PendingIntent;
import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder;

final class zzi extends zzj {
    zzi(zze arg1, GoogleApiClient arg2, PendingIntent arg3) {
        this.zzbz = arg3;
        super(arg2);
    }

    protected final void doExecute(AnyClient arg2) {
        ((zzaz)arg2).zza(this.zzbz, ((ResultHolder)this));
    }
}

