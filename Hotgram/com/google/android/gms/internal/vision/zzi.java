package com.google.android.gms.internal.vision;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.vision.barcode.Barcode;

public final class zzi extends zza implements zzh {
    zzi(IBinder arg2) {
        super(arg2, "com.google.android.gms.vision.barcode.internal.client.INativeBarcodeDetector");
    }

    public final Barcode[] zza(IObjectWrapper arg2, zzm arg3) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((IInterface)arg2));
        zzc.zza(v0, ((Parcelable)arg3));
        Parcel v2 = ((zza)this).transactAndReadException(1, v0);
        Object[] v3 = v2.createTypedArray(Barcode.CREATOR);
        v2.recycle();
        return ((Barcode[])v3);
    }

    public final Barcode[] zzb(IObjectWrapper arg2, zzm arg3) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((IInterface)arg2));
        zzc.zza(v0, ((Parcelable)arg3));
        Parcel v2 = ((zza)this).transactAndReadException(2, v0);
        Object[] v3 = v2.createTypedArray(Barcode.CREATOR);
        v2.recycle();
        return ((Barcode[])v3);
    }

    public final void zzn() {
        ((zza)this).transactAndReadExceptionReturnVoid(3, ((zza)this).obtainAndWriteInterfaceToken());
    }
}

