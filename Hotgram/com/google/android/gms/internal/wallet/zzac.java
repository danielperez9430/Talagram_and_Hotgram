package com.google.android.gms.internal.wallet;

import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.wallet.Wallet$zza;
import com.google.android.gms.wallet.Wallet$zzb;

final class zzac extends zzb {
    zzac(zzy arg1, GoogleApiClient arg2, String arg3, String arg4, int arg5) {
        this.zzgh = arg3;
        this.zzgi = arg4;
        this.val$requestCode = arg5;
        super(arg2);
    }

    protected final void doExecute(AnyClient arg1) {
        ((zza)this).zza(((zzaf)arg1));
    }

    protected final void zza(zzaf arg4) {
        arg4.zza(this.zzgh, this.zzgi, this.val$requestCode);
        this.setResult(Status.RESULT_SUCCESS);
    }
}

