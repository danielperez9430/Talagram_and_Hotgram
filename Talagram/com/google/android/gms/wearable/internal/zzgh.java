package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzgh implements ResultHolder {
    private final TaskCompletionSource zzes;

    zzgh(TaskCompletionSource arg1) {
        super();
        this.zzes = arg1;
    }

    public final void setFailedResult(Status arg3) {
        this.zzes.setException(new ApiException(arg3));
    }

    public final void setResult(Object arg3) {
        int v0 = ((Status)arg3).getStatusCode();
        if(v0 != 0) {
            if(v0 == 4001) {
            }
            else {
                this.setFailedResult(((Status)arg3));
                return;
            }
        }

        this.zzes.setResult(null);
    }
}

