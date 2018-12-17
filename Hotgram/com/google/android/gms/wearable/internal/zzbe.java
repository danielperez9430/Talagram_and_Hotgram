package com.google.android.gms.wearable.internal;

import android.net.Uri;
import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

final class zzbe extends zzn {
    zzbe(zzay arg1, GoogleApiClient arg2, Uri arg3, long arg4, long arg6) {
        this.zzcm = arg1;
        this.zzco = arg3;
        this.zzcq = arg4;
        this.zzcr = arg6;
        super(arg2);
    }

    public final Result createFailedResult(Status arg1) {
        return arg1;
    }

    protected final void doExecute(AnyClient arg9) {
        arg9.zza(this, zzay.zza(this.zzcm), this.zzco, this.zzcq, this.zzcr);
    }
}

