package com.google.android.gms.internal.maps;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.maps.model.Tile;

public abstract class zzag extends zzb implements zzaf {
    public zzag() {
        super("com.google.android.gms.maps.model.internal.ITileProviderDelegate");
    }

    protected final boolean dispatchTransaction(int arg2, Parcel arg3, Parcel arg4, int arg5) {
        if(arg2 == 1) {
            Tile v2 = this.getTile(arg3.readInt(), arg3.readInt(), arg3.readInt());
            arg4.writeNoException();
            zzc.zzb(arg4, ((Parcelable)v2));
            return 1;
        }

        return 0;
    }

    public static zzaf zzk(IBinder arg2) {
        if(arg2 == null) {
            return null;
        }

        IInterface v0 = arg2.queryLocalInterface("com.google.android.gms.maps.model.internal.ITileProviderDelegate");
        if((v0 instanceof zzaf)) {
            return ((zzaf)v0);
        }

        return new zzah(arg2);
    }
}

