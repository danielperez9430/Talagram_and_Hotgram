package com.google.android.gms.maps.model;

import com.google.android.gms.internal.maps.zzag;

final class zzt extends zzag {
    zzt(TileOverlayOptions arg1, TileProvider arg2) {
        this.zzem = arg2;
        super();
    }

    public final Tile getTile(int arg2, int arg3, int arg4) {
        return this.zzem.getTile(arg2, arg3, arg4);
    }
}

