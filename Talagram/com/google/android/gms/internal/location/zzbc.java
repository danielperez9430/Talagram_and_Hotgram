package com.google.android.gms.internal.location;

import com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.location.LocationSettingsResult;

final class zzbc extends zzar {
    private ResultHolder zzdf;

    public zzbc(ResultHolder arg3) {
        super();
        boolean v0 = arg3 != null ? true : false;
        Preconditions.checkArgument(v0, "listener can\'t be null.");
        this.zzdf = arg3;
    }

    public final void zza(LocationSettingsResult arg2) {
        this.zzdf.setResult(arg2);
        this.zzdf = null;
    }
}

