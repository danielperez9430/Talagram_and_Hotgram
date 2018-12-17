package com.google.android.gms.internal.wallet;

import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.wallet.MaskedWalletRequest;
import com.google.android.gms.wallet.Wallet$zza;
import com.google.android.gms.wallet.Wallet$zzb;

final class zzaa extends zzb {
    zzaa(zzy arg1, GoogleApiClient arg2, MaskedWalletRequest arg3, int arg4) {
        this.zzgf = arg3;
        this.val$requestCode = arg4;
        super(arg2);
    }

    protected final void doExecute(AnyClient arg1) {
        ((zza)this).zza(((zzaf)arg1));
    }

    protected final void zza(zzaf arg3) {
        arg3.zza(this.zzgf, this.val$requestCode);
        this.setResult(Status.RESULT_SUCCESS);
    }
}

