package com.google.android.gms.maps.model;

import android.os.RemoteException;
import com.google.android.gms.internal.maps.zzaf;

final class zzs implements TileProvider {
    private final zzaf zzek;

    zzs(TileOverlayOptions arg1) {
        this.zzel = arg1;
        super();
        this.zzek = TileOverlayOptions.zza(this.zzel);
    }

    public final Tile getTile(int arg2, int arg3, int arg4) {
        try {
            return this.zzek.getTile(arg2, arg3, arg4);
        }
        catch(RemoteException ) {
            return null;
        }
    }
}

