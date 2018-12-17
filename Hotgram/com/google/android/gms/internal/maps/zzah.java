package com.google.android.gms.internal.maps;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.maps.model.Tile;

public final class zzah extends zza implements zzaf {
    zzah(IBinder arg2) {
        super(arg2, "com.google.android.gms.maps.model.internal.ITileProviderDelegate");
    }

    public final Tile getTile(int arg2, int arg3, int arg4) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        v0.writeInt(arg2);
        v0.writeInt(arg3);
        v0.writeInt(arg4);
        Parcel v2 = ((zza)this).transactAndReadException(1, v0);
        Parcelable v3 = zzc.zza(v2, Tile.CREATOR);
        v2.recycle();
        return ((Tile)v3);
    }
}

