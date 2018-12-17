package com.google.android.gms.internal.wallet;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.wallet.CreateWalletObjectsRequest;
import com.google.android.gms.wallet.FullWalletRequest;
import com.google.android.gms.wallet.IsReadyToPayRequest;
import com.google.android.gms.wallet.MaskedWalletRequest;
import com.google.android.gms.wallet.PaymentDataRequest;

public final class zzt extends zza implements zzs {
    zzt(IBinder arg2) {
        super(arg2, "com.google.android.gms.wallet.internal.IOwService");
    }

    public final void zza(Bundle arg2, zzw arg3) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((Parcelable)arg2));
        zzc.zza(v0, ((IInterface)arg3));
        ((zza)this).transactOneway(5, v0);
    }

    public final void zza(CreateWalletObjectsRequest arg2, Bundle arg3, zzw arg4) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((Parcelable)arg2));
        zzc.zza(v0, ((Parcelable)arg3));
        zzc.zza(v0, ((IInterface)arg4));
        ((zza)this).transactOneway(6, v0);
    }

    public final void zza(FullWalletRequest arg2, Bundle arg3, zzw arg4) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((Parcelable)arg2));
        zzc.zza(v0, ((Parcelable)arg3));
        zzc.zza(v0, ((IInterface)arg4));
        ((zza)this).transactOneway(2, v0);
    }

    public final void zza(IsReadyToPayRequest arg2, Bundle arg3, zzw arg4) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((Parcelable)arg2));
        zzc.zza(v0, ((Parcelable)arg3));
        zzc.zza(v0, ((IInterface)arg4));
        ((zza)this).transactOneway(14, v0);
    }

    public final void zza(MaskedWalletRequest arg2, Bundle arg3, zzw arg4) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((Parcelable)arg2));
        zzc.zza(v0, ((Parcelable)arg3));
        zzc.zza(v0, ((IInterface)arg4));
        ((zza)this).transactOneway(1, v0);
    }

    public final void zza(PaymentDataRequest arg2, Bundle arg3, zzw arg4) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((Parcelable)arg2));
        zzc.zza(v0, ((Parcelable)arg3));
        zzc.zza(v0, ((IInterface)arg4));
        ((zza)this).transactOneway(19, v0);
    }

    public final void zza(String arg2, String arg3, Bundle arg4, zzw arg5) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        v0.writeString(arg2);
        v0.writeString(arg3);
        zzc.zza(v0, ((Parcelable)arg4));
        zzc.zza(v0, ((IInterface)arg5));
        ((zza)this).transactOneway(3, v0);
    }
}

