package com.google.android.gms.internal.wallet;

import android.annotation.SuppressLint;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wallet.CreateWalletObjectsRequest;
import com.google.android.gms.wallet.wobs.WalletObjects;

public final class zzao implements WalletObjects {
    public zzao() {
        super();
    }

    @SuppressLint(value={"MissingRemoteException"}) public final void createWalletObjects(GoogleApiClient arg2, CreateWalletObjectsRequest arg3, int arg4) {
        arg2.enqueue(new zzap(this, arg2, arg3, arg4));
    }
}

