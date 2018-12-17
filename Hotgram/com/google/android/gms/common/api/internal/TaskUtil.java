package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;

@KeepForSdk public class TaskUtil {
    public TaskUtil() {
        super();
    }

    @KeepForSdk public static void setResultOrApiException(Status arg1, TaskCompletionSource arg2) {
        TaskUtil.setResultOrApiException(arg1, null, arg2);
    }

    @KeepForSdk public static void setResultOrApiException(Status arg1, Object arg2, TaskCompletionSource arg3) {
        if(arg1.isSuccess()) {
            arg3.setResult(arg2);
            return;
        }

        arg3.setException(new ApiException(arg1));
    }

    @KeepForSdk @Deprecated public static Task toVoidTaskThatFailsOnFalse(Task arg1) {
        return arg1.continueWith(new zzcg());
    }
}

