package com.google.android.gms.wearable.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.internal.wearable.zza;
import com.google.android.gms.internal.wearable.zzc;
import java.util.List;

public final class zzeo extends zza implements zzem {
    zzeo(IBinder arg2) {
        super(arg2, "com.google.android.gms.wearable.internal.IWearableListener");
    }

    public final void onConnectedNodes(List arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        v0.writeTypedList(arg2);
        ((zza)this).transactOneway(5, v0);
    }

    public final void zza(DataHolder arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((Parcelable)arg2));
        ((zza)this).transactOneway(1, v0);
    }

    public final void zza(zzah arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((Parcelable)arg2));
        ((zza)this).transactOneway(8, v0);
    }

    public final void zza(zzaw arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((Parcelable)arg2));
        ((zza)this).transactOneway(7, v0);
    }

    public final void zza(zzfe arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((Parcelable)arg2));
        ((zza)this).transactOneway(2, v0);
    }

    public final void zza(zzfo arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((Parcelable)arg2));
        ((zza)this).transactOneway(3, v0);
    }

    public final void zza(zzi arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((Parcelable)arg2));
        ((zza)this).transactOneway(9, v0);
    }

    public final void zza(zzl arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((Parcelable)arg2));
        ((zza)this).transactOneway(6, v0);
    }

    public final void zzb(zzfo arg2) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((Parcelable)arg2));
        ((zza)this).transactOneway(4, v0);
    }
}

