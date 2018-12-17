package com.google.android.gms.internal.maps;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

public final class zzae extends zza implements zzac {
    zzae(IBinder arg2) {
        super(arg2, "com.google.android.gms.maps.model.internal.ITileOverlayDelegate");
    }

    public final void clearTileCache() {
        ((zza)this).transactAndReadExceptionReturnVoid(2, ((zza)this).obtainAndWriteInterfaceToken());
    }

    public final boolean getFadeIn() {
        Parcel v0 = ((zza)this).transactAndReadException(11, ((zza)this).obtainAndWriteInterfaceToken());
        boolean v1 = zzc.zza(v0);
        v0.recycle();
        return v1;
    }

    public final String getId() {
        Parcel v0 = ((zza)this).transactAndReadException(3, ((zza)this).obtainAndWriteInterfaceToken());
        String v1 = v0.readString();
        v0.recycle();
        return v1;
    }

    public final float getTransparency() {
        Parcel v0 = ((zza)this).transactAndReadException(13, ((zza)this).obtainAndWriteInterfaceToken());
        float v1 = v0.readFloat();
        v0.recycle();
        return v1;
    }

    public final float getZIndex() {
        Parcel v0 = ((zza)this).transactAndReadException(5, ((zza)this).obtainAndWriteInterfaceToken());
        float v1 = v0.readFloat();
        v0.recycle();
        return v1;
    }

    public final boolean isVisible() {
        Parcel v0 = ((zza)this).transactAndReadException(7, ((zza)this).obtainAndWriteInterfaceToken());
        boolean v1 = zzc.zza(v0);
        v0.recycle();
        return v1;
    }

    public final void remove() {
        ((zza)this).transactAndReadExceptionReturnVoid(1, ((zza)this).obtainAndWriteInterfaceToken());
    }

    public final void setFadeIn(boolean arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, arg2);
        ((zza)this).transactAndReadExceptionReturnVoid(10, v0);
    }

    public final void setTransparency(float arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        v0.writeFloat(arg2);
        ((zza)this).transactAndReadExceptionReturnVoid(12, v0);
    }

    public final void setVisible(boolean arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, arg2);
        ((zza)this).transactAndReadExceptionReturnVoid(6, v0);
    }

    public final void setZIndex(float arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        v0.writeFloat(arg2);
        ((zza)this).transactAndReadExceptionReturnVoid(4, v0);
    }

    public final boolean zza(zzac arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((IInterface)arg2));
        Parcel v2 = ((zza)this).transactAndReadException(8, v0);
        boolean v0_1 = zzc.zza(v2);
        v2.recycle();
        return v0_1;
    }

    public final int zzi() {
        Parcel v0 = ((zza)this).transactAndReadException(9, ((zza)this).obtainAndWriteInterfaceToken());
        int v1 = v0.readInt();
        v0.recycle();
        return v1;
    }
}

