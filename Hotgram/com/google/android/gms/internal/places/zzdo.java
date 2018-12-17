package com.google.android.gms.internal.places;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataBufferSafeParcelable;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.location.places.PlacesStatusCodes;

@Deprecated public final class zzdo extends DataBufferSafeParcelable implements Result {
    private final Status zzdz;

    public zzdo(DataHolder arg2) {
        this(arg2, PlacesStatusCodes.zze(arg2.getStatusCode()));
    }

    private zzdo(DataHolder arg2, Status arg3) {
        super(arg2, zzdn.CREATOR);
        boolean v2 = arg2 == null || arg2.getStatusCode() == arg3.getStatusCode() ? true : false;
        Preconditions.checkArgument(v2);
        this.zzdz = arg3;
    }

    public final Status getStatus() {
        return this.zzdz;
    }
}

