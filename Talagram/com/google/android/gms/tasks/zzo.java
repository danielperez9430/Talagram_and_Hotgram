package com.google.android.gms.tasks;

import java.util.concurrent.Executor;

final class zzo implements OnCanceledListener, OnFailureListener, OnSuccessListener, zzq {
    private final Executor zzafk;
    private final zzu zzafm;
    private final SuccessContinuation zzafy;

    public zzo(Executor arg1, SuccessContinuation arg2, zzu arg3) {
        super();
        this.zzafk = arg1;
        this.zzafy = arg2;
        this.zzafm = arg3;
    }

    public final void cancel() {
        throw new UnsupportedOperationException();
    }

    public final void onCanceled() {
        this.zzafm.zzdp();
    }

    public final void onComplete(Task arg3) {
        this.zzafk.execute(new zzp(this, arg3));
    }

    public final void onFailure(Exception arg2) {
        this.zzafm.setException(arg2);
    }

    public final void onSuccess(Object arg2) {
        this.zzafm.setResult(arg2);
    }

    static SuccessContinuation zza(zzo arg0) {
        return arg0.zzafy;
    }
}

