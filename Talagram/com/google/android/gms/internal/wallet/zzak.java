package com.google.android.gms.internal.wallet;

import android.os.Bundle;
import com.google.android.gms.common.api.BooleanResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder;

final class zzak extends zzah {
    private final ResultHolder zzgm;

    public zzak(ResultHolder arg1) {
        super();
        this.zzgm = arg1;
    }

    public final void zza(Status arg2, boolean arg3, Bundle arg4) {
        this.zzgm.setResult(new BooleanResult(arg2, arg3));
    }
}

