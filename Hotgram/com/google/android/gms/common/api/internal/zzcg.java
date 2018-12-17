package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;

final class zzcg implements Continuation {
    zzcg() {
        super();
    }

    public final Object then(Task arg4) {
        if(arg4.getResult().booleanValue()) {
            return null;
        }

        throw new ApiException(new Status(13, "listener already unregistered"));
    }
}

