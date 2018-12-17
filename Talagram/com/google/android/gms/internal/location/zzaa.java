package com.google.android.gms.internal.location;

import android.app.PendingIntent;
import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder;

final class zzaa extends zzab {
    zzaa(zzq arg1, GoogleApiClient arg2, PendingIntent arg3) {
        this.zzbx = arg3;
        super(arg2);
    }

    protected final void doExecute(AnyClient arg3) {
        ((zzaz)arg3).zza(this.zzbx, new zzac(((ResultHolder)this)));
    }
}

