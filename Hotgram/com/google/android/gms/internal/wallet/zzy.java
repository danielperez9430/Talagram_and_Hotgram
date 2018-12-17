package com.google.android.gms.internal.wallet;

import android.annotation.SuppressLint;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.wallet.FullWalletRequest;
import com.google.android.gms.wallet.IsReadyToPayRequest;
import com.google.android.gms.wallet.MaskedWalletRequest;
import com.google.android.gms.wallet.Payments;

@SuppressLint(value={"MissingRemoteException"}) public final class zzy implements Payments {
    public zzy() {
        super();
    }

    public final void changeMaskedWallet(GoogleApiClient arg8, String arg9, String arg10, int arg11) {
        arg8.enqueue(new zzac(this, arg8, arg9, arg10, arg11));
    }

    public final void checkForPreAuthorization(GoogleApiClient arg2, int arg3) {
        arg2.enqueue(new zzz(this, arg2, arg3));
    }

    public final PendingResult isReadyToPay(GoogleApiClient arg2) {
        return arg2.enqueue(new zzad(this, arg2));
    }

    public final PendingResult isReadyToPay(GoogleApiClient arg2, IsReadyToPayRequest arg3) {
        return arg2.enqueue(new zzae(this, arg2, arg3));
    }

    public final void loadFullWallet(GoogleApiClient arg2, FullWalletRequest arg3, int arg4) {
        arg2.enqueue(new zzab(this, arg2, arg3, arg4));
    }

    public final void loadMaskedWallet(GoogleApiClient arg2, MaskedWalletRequest arg3, int arg4) {
        arg2.enqueue(new zzaa(this, arg2, arg3, arg4));
    }
}

