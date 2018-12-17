package com.google.android.gms.internal.vision;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.dynamic.IObjectWrapper;

public final class zzk extends zza implements zzj {
    zzk(IBinder arg2) {
        super(arg2, "com.google.android.gms.vision.barcode.internal.client.INativeBarcodeDetectorCreator");
    }

    public final zzh zza(IObjectWrapper arg3, zze arg4) {
        zzh v4_1;
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((IInterface)arg3));
        zzc.zza(v0, ((Parcelable)arg4));
        Parcel v3 = ((zza)this).transactAndReadException(1, v0);
        IBinder v4 = v3.readStrongBinder();
        if(v4 == null) {
            v4_1 = null;
        }
        else {
            IInterface v0_1 = v4.queryLocalInterface("com.google.android.gms.vision.barcode.internal.client.INativeBarcodeDetector");
            if((v0_1 instanceof zzh)) {
                IInterface v4_2 = v0_1;
            }
            else {
                zzi v4_3 = new zzi(v4);
            }
        }

        v3.recycle();
        return v4_1;
    }
}

