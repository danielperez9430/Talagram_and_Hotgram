package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder;
import java.util.ArrayList;

final class zzfi extends zzn {
    zzfi(zzfg arg1, GoogleApiClient arg2) {
        super(arg2);
    }

    protected final Result createFailedResult(Status arg3) {
        return new zzfj(arg3, new ArrayList());
    }

    protected final void doExecute(AnyClient arg2) {
        ((zzhg)arg2).getService().zzc(new zzgu(((ResultHolder)this)));
    }
}

