package com.google.android.gms.vision.face.internal.client;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.vision.zza;
import com.google.android.gms.internal.vision.zzc;
import com.google.android.gms.internal.vision.zzm;

public final class zzf extends zza implements zze {
    zzf(IBinder arg2) {
        super(arg2, "com.google.android.gms.vision.face.internal.client.INativeFaceDetector");
    }

    public final FaceParcel[] zzc(IObjectWrapper arg2, zzm arg3) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((IInterface)arg2));
        zzc.zza(v0, ((Parcelable)arg3));
        Parcel v2 = ((zza)this).transactAndReadException(1, v0);
        Object[] v3 = v2.createTypedArray(FaceParcel.CREATOR);
        v2.recycle();
        return ((FaceParcel[])v3);
    }

    public final boolean zzd(int arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        v0.writeInt(arg2);
        Parcel v2 = ((zza)this).transactAndReadException(2, v0);
        boolean v0_1 = zzc.zza(v2);
        v2.recycle();
        return v0_1;
    }

    public final void zzn() {
        ((zza)this).transactAndReadExceptionReturnVoid(3, ((zza)this).obtainAndWriteInterfaceToken());
    }
}

