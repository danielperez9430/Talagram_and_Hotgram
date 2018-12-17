package com.google.android.gms.maps;

import com.google.android.gms.maps.internal.ILocationSourceDelegate$zza;
import com.google.android.gms.maps.internal.zzah;

final class zzl extends zza {
    zzl(GoogleMap arg1, LocationSource arg2) {
        this.zzt = arg2;
        super();
    }

    public final void activate(zzah arg3) {
        this.zzt.activate(new zzm(this, arg3));
    }

    public final void deactivate() {
        this.zzt.deactivate();
    }
}

