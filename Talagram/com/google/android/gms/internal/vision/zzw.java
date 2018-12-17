package com.google.android.gms.internal.vision;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.dynamic.IObjectWrapper;

public final class zzw extends zza implements zzv {
    zzw(IBinder arg2) {
        super(arg2, "com.google.android.gms.vision.text.internal.client.INativeTextRecognizerCreator");
    }

    public final zzt zza(IObjectWrapper arg3, zzae arg4) {
        zzt v4_1;
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((IInterface)arg3));
        zzc.zza(v0, ((Parcelable)arg4));
        Parcel v3 = ((zza)this).transactAndReadException(1, v0);
        IBinder v4 = v3.readStrongBinder();
        if(v4 == null) {
            v4_1 = null;
        }
        else {
            IInterface v0_1 = v4.queryLocalInterface("com.google.android.gms.vision.text.internal.client.INativeTextRecognizer");
            if((v0_1 instanceof zzt)) {
                IInterface v4_2 = v0_1;
            }
            else {
                zzu v4_3 = new zzu(v4);
            }
        }

        v3.recycle();
        return v4_1;
    }
}

