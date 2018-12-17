package com.google.android.gms.internal.location;

import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder;
import com.google.android.gms.location.LocationServices$zza;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;

final class zzbl extends zza {
    zzbl(zzbk arg1, GoogleApiClient arg2, LocationSettingsRequest arg3, String arg4) {
        this.zzdp = arg3;
        this.zzdq = null;
        super(arg2);
    }

    public final Result createFailedResult(Status arg2) {
        return new LocationSettingsResult(arg2);
    }

    protected final void doExecute(AnyClient arg3) {
        ((zzaz)arg3).zza(this.zzdp, ((ResultHolder)this), this.zzdq);
    }
}

