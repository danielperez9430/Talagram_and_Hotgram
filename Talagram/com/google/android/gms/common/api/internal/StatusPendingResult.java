package com.google.android.gms.common.api.internal;

import android.os.Looper;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

@KeepForSdk public class StatusPendingResult extends BasePendingResult {
    @Deprecated public StatusPendingResult(Looper arg1) {
        super(arg1);
    }

    public StatusPendingResult(GoogleApiClient arg1) {
        super(arg1);
    }

    protected Result createFailedResult(Status arg1) {
        return arg1;
    }
}

