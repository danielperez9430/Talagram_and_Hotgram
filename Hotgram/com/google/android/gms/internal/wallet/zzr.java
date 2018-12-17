package com.google.android.gms.internal.wallet;

import android.os.Bundle;
import android.os.Parcel;

public abstract class zzr extends zzb implements zzq {
    public zzr() {
        super("com.google.android.gms.wallet.fragment.internal.IWalletFragmentStateListener");
    }

    protected final boolean dispatchTransaction(int arg2, Parcel arg3, Parcel arg4, int arg5) {
        if(arg2 == 2) {
            this.zza(arg3.readInt(), arg3.readInt(), zzc.zza(arg3, Bundle.CREATOR));
            arg4.writeNoException();
            return 1;
        }

        return 0;
    }
}

