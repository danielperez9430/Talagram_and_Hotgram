package com.google.android.gms.maps;

import com.google.android.gms.internal.maps.zzn;
import com.google.android.gms.maps.internal.zzaa;
import com.google.android.gms.maps.model.IndoorBuilding;

final class zza extends zzaa {
    zza(GoogleMap arg1, OnIndoorStateChangeListener arg2) {
        this.zzi = arg2;
        super();
    }

    public final void onIndoorBuildingFocused() {
        this.zzi.onIndoorBuildingFocused();
    }

    public final void zza(zzn arg3) {
        this.zzi.onIndoorLevelActivated(new IndoorBuilding(arg3));
    }
}

