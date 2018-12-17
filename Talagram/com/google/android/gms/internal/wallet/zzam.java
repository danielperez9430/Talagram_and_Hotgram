package com.google.android.gms.internal.wallet;

import android.app.Activity;
import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.dynamic.IFragmentWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamic.RemoteCreator$RemoteCreatorException;
import com.google.android.gms.dynamic.RemoteCreator;
import com.google.android.gms.wallet.fragment.WalletFragmentOptions;

public final class zzam extends RemoteCreator {
    private static zzam zzgn;

    protected zzam() {
        super("com.google.android.gms.wallet.dynamite.WalletDynamiteCreatorImpl");
    }

    protected final Object getRemoteCreator(IBinder arg3) {
        if(arg3 == null) {
            return null;
        }

        IInterface v0 = arg3.queryLocalInterface("com.google.android.gms.wallet.internal.IWalletDynamiteCreator");
        if((v0 instanceof zzu)) {
            return v0;
        }

        return new zzv(arg3);
    }

    public static zzn zza(Activity arg1, IFragmentWrapper arg2, WalletFragmentOptions arg3, zzq arg4) {
        int v0 = GooglePlayServicesUtil.isGooglePlayServicesAvailable(((Context)arg1), 12451000);
        if(v0 == 0) {
            try {
                if(zzam.zzgn == null) {
                    zzam.zzgn = new zzam();
                }

                return zzam.zzgn.getRemoteCreatorInstance(((Context)arg1)).zza(ObjectWrapper.wrap(arg1), arg2, arg3, arg4);
            }
            catch(RemoteCreatorException v1) {
                throw new RuntimeException(((Throwable)v1));
            }
            catch(RemoteException v1_1) {
                throw new RuntimeException(((Throwable)v1_1));
            }
        }

        throw new GooglePlayServicesNotAvailableException(v0);
    }
}

