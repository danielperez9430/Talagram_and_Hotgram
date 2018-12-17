package com.google.android.gms.internal.vision;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.dynamic.IObjectWrapper;

public final class zzu extends zza implements zzt {
    zzu(IBinder arg2) {
        super(arg2, "com.google.android.gms.vision.text.internal.client.INativeTextRecognizer");
    }

    public final zzx[] zza(IObjectWrapper arg2, zzm arg3, zzz arg4) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((IInterface)arg2));
        zzc.zza(v0, ((Parcelable)arg3));
        zzc.zza(v0, ((Parcelable)arg4));
        Parcel v2 = ((zza)this).transactAndReadException(3, v0);
        Object[] v3 = v2.createTypedArray(zzx.CREATOR);
        v2.recycle();
        return ((zzx[])v3);
    }

    public final void zzq() {
        ((zza)this).transactAndReadExceptionReturnVoid(2, ((zza)this).obtainAndWriteInterfaceToken());
    }
}

