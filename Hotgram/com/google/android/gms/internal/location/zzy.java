package com.google.android.gms.internal.location;

import android.app.PendingIntent;
import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder;
import com.google.android.gms.location.LocationRequest;

final class zzy extends zzab {
    zzy(zzq arg1, GoogleApiClient arg2, LocationRequest arg3, PendingIntent arg4) {
        this.zzck = arg3;
        this.zzbx = arg4;
        super(arg2);
    }

    protected final void doExecute(AnyClient arg4) {
        ((zzaz)arg4).zza(this.zzck, this.zzbx, new zzac(((ResultHolder)this)));
    }
}

