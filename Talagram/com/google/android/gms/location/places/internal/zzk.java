package com.google.android.gms.location.places.internal;

import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.zze;
import com.google.android.gms.location.places.zzg;

final class zzk extends zzg {
    zzk(zzi arg1, Api arg2, GoogleApiClient arg3, String arg4) {
        this.zzft = arg4;
        super(arg2, arg3);
    }

    protected final void doExecute(AnyClient arg3) {
        ((zzp)arg3).zzb(new zze(((zzg)this)), this.zzft);
    }
}

