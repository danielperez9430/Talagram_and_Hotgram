package com.google.android.gms.internal.wallet;

import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.common.api.BooleanResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder;
import com.google.android.gms.wallet.IsReadyToPayRequest;
import com.google.android.gms.wallet.Wallet$zza;

final class zzad extends zza {
    zzad(zzy arg1, GoogleApiClient arg2) {
        super(arg2);
    }

    protected final Result createFailedResult(Status arg3) {
        return new BooleanResult(arg3, false);
    }

    protected final void doExecute(AnyClient arg1) {
        ((zza)this).zza(((zzaf)arg1));
    }

    protected final void zza(zzaf arg2) {
        arg2.zza(IsReadyToPayRequest.newBuilder().build(), ((ResultHolder)this));
    }
}

