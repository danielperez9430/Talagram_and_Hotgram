package com.google.android.gms.common.api;

import com.google.android.gms.common.api.internal.zzbx;

public abstract class ResultTransform {
    public ResultTransform() {
        super();
    }

    public final PendingResult createFailedResult(Status arg2) {
        return new zzbx(arg2);
    }

    public Status onFailure(Status arg1) {
        return arg1;
    }

    public abstract PendingResult onSuccess(Result arg1);
}

