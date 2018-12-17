package com.google.android.gms.common.internal;

import com.google.android.gms.common.api.PendingResult$StatusListener;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.concurrent.TimeUnit;

final class zzl implements StatusListener {
    zzl(PendingResult arg1, TaskCompletionSource arg2, ResultConverter arg3, StatusConverter arg4) {
        this.zzuo = arg1;
        this.zzup = arg2;
        this.zzuq = arg3;
        this.zzur = arg4;
        super();
    }

    public final void onComplete(Status arg4) {
        if(arg4.isSuccess()) {
            this.zzup.setResult(this.zzuq.convert(this.zzuo.await(0, TimeUnit.MILLISECONDS)));
            return;
        }

        this.zzup.setException(this.zzur.convert(arg4));
    }
}

