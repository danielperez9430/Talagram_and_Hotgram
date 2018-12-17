package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Status;

@KeepForSdk public class StatusCallback extends Stub {
    @KeepForSdk private final ResultHolder mResultHolder;

    @KeepForSdk public StatusCallback(ResultHolder arg1) {
        super();
        this.mResultHolder = arg1;
    }

    @KeepForSdk public void onResult(Status arg2) {
        this.mResultHolder.setResult(arg2);
    }
}

