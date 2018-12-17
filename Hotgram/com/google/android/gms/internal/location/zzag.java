package com.google.android.gms.internal.location;

import android.app.PendingIntent;
import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder;
import com.google.android.gms.location.GeofencingRequest;

final class zzag extends zzai {
    zzag(zzaf arg1, GoogleApiClient arg2, GeofencingRequest arg3, PendingIntent arg4) {
        this.zzcs = arg3;
        this.zzbz = arg4;
        super(arg2);
    }

    protected final void doExecute(AnyClient arg3) {
        ((zzaz)arg3).zza(this.zzcs, this.zzbz, ((ResultHolder)this));
    }
}

