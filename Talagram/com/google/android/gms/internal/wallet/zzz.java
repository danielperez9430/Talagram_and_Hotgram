package com.google.android.gms.internal.wallet;

import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.wallet.Wallet$zza;
import com.google.android.gms.wallet.Wallet$zzb;

final class zzz extends zzb {
    zzz(zzy arg1, GoogleApiClient arg2, int arg3) {
        this.val$requestCode = arg3;
        super(arg2);
    }

    protected final void doExecute(AnyClient arg1) {
        ((zza)this).zza(((zzaf)arg1));
    }

    protected final void zza(zzaf arg2) {
        arg2.zzb(this.val$requestCode);
        this.setResult(Status.RESULT_SUCCESS);
    }
}

