package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.ApiExceptionUtil;

@KeepForSdk public class ApiExceptionMapper implements StatusExceptionMapper {
    public ApiExceptionMapper() {
        super();
    }

    public Exception getException(Status arg1) {
        return ApiExceptionUtil.fromStatus(arg1);
    }
}

