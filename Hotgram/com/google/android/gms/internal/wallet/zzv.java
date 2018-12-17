package com.google.android.gms.internal.wallet;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.dynamic.IFragmentWrapper;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.wallet.fragment.WalletFragmentOptions;

public final class zzv extends zza implements zzu {
    zzv(IBinder arg2) {
        super(arg2, "com.google.android.gms.wallet.internal.IWalletDynamiteCreator");
    }

    public final zzn zza(IObjectWrapper arg2, IFragmentWrapper arg3, WalletFragmentOptions arg4, zzq arg5) {
        Parcel v0 = ((zza)this).obtainAndWriteInterfaceToken();
        zzc.zza(v0, ((IInterface)arg2));
        zzc.zza(v0, ((IInterface)arg3));
        zzc.zza(v0, ((Parcelable)arg4));
        zzc.zza(v0, ((IInterface)arg5));
        Parcel v2 = ((zza)this).transactAndReadException(1, v0);
        zzn v3 = zzo.zza(v2.readStrongBinder());
        v2.recycle();
        return v3;
    }
}

