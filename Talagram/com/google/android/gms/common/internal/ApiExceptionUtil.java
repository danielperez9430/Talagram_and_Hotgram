package com.google.android.gms.common.internal;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.common.api.Status;

public class ApiExceptionUtil {
    public ApiExceptionUtil() {
        super();
    }

    public static ApiException fromConnectionResult(ConnectionResult arg3) {
        return ApiExceptionUtil.fromStatus(new Status(arg3.getErrorCode(), arg3.getErrorMessage(), arg3.getResolution()));
    }

    public static ApiException fromStatus(Status arg1) {
        if(arg1.hasResolution()) {
            return new ResolvableApiException(arg1);
        }

        return new ApiException(arg1);
    }
}

