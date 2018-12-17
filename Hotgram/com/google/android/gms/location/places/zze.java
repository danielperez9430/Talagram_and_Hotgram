package com.google.android.gms.location.places;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.location.places.internal.zzx;

public final class zze extends zzx {
    private final zzg zzdx;
    private final zzf zzdy;

    public zze(zzf arg2) {
        super();
        this.zzdx = null;
        this.zzdy = arg2;
    }

    public zze(zzg arg1) {
        super();
        this.zzdx = arg1;
        this.zzdy = null;
    }

    public final void zzb(PlacePhotoMetadataResult arg2) {
        this.zzdx.setResult(((Result)arg2));
    }

    public final void zzb(PlacePhotoResult arg2) {
        this.zzdy.setResult(((Result)arg2));
    }
}

