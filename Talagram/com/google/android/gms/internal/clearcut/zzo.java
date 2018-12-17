package com.google.android.gms.internal.clearcut;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.clearcut.zze;

public final class zzo extends zza implements zzn {
    zzo(IBinder arg2) {
        super(arg2, "com.google.android.gms.clearcut.internal.IClearcutLoggerService");
    }

    public final void zza(zzl arg2, zze arg3) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((IInterface)arg2));
        zzc.zza(v0, ((Parcelable)arg3));
        ((zza)this).transactOneway(1, v0);
    }
}

