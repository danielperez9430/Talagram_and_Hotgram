package com.google.android.gms.vision.face.internal.client;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.vision.zza;

public final class zzh extends zza implements zzg {
    zzh(IBinder arg2) {
        super(arg2, "com.google.android.gms.vision.face.internal.client.INativeFaceDetectorCreator");
    }

    public final zze zza(IObjectWrapper arg3, zzc arg4) {
        zzf v4_3;
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.vision.zzc.zza(v0, ((IInterface)arg3));
        com.google.android.gms.internal.vision.zzc.zza(v0, ((Parcelable)arg4));
        Parcel v3 = ((zza)this).transactAndReadException(1, v0);
        IBinder v4 = v3.readStrongBinder();
        if(v4 == null) {
            zze v4_1 = null;
        }
        else {
            IInterface v0_1 = v4.queryLocalInterface("com.google.android.gms.vision.face.internal.client.INativeFaceDetector");
            if((v0_1 instanceof zze)) {
                IInterface v4_2 = v0_1;
            }
            else {
                v4_3 = new zzf(v4);
            }
        }

        v3.recycle();
        return ((zze)v4_3);
    }
}

