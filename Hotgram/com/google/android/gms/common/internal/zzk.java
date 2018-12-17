package com.google.android.gms.common.internal;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;

final class zzk implements StatusConverter {
    zzk() {
        super();
    }

    public final ApiException convert(Status arg1) {
        return ApiExceptionUtil.fromStatus(arg1);
    }
}

