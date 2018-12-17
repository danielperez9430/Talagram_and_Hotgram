package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzgg implements ResultHolder {
    private final TaskCompletionSource zzes;

    zzgg(TaskCompletionSource arg1) {
        super();
        this.zzes = arg1;
    }

    public final void setFailedResult(Status arg3) {
        this.zzes.setException(new ApiException(arg3));
    }

    public final void setResult(Object arg3) {
        boolean v0_1;
        TaskCompletionSource v3;
        int v0 = ((Status)arg3).getStatusCode();
        if(v0 == 0) {
            v3 = this.zzes;
            v0_1 = true;
        }
        else if(v0 == 4002) {
            v3 = this.zzes;
            v0_1 = false;
        }
        else {
            goto label_12;
        }

        v3.setResult(Boolean.valueOf(v0_1));
        return;
    label_12:
        this.setFailedResult(((Status)arg3));
    }
}

