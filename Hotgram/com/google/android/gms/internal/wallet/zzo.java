package com.google.android.gms.internal.wallet;

import android.os.IBinder;
import android.os.IInterface;

public abstract class zzo extends zzb implements zzn {
    public static zzn zza(IBinder arg2) {
        if(arg2 == null) {
            return null;
        }

        IInterface v0 = arg2.queryLocalInterface("com.google.android.gms.wallet.fragment.internal.IWalletFragmentDelegate");
        if((v0 instanceof zzn)) {
            return ((zzn)v0);
        }

        return new zzp(arg2);
    }
}

