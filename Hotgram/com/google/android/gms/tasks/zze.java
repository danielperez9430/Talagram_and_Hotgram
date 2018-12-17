package com.google.android.gms.tasks;

import java.util.concurrent.Executor;

final class zze implements OnCanceledListener, OnFailureListener, OnSuccessListener, zzq {
    private final Executor zzafk;
    private final Continuation zzafl;
    private final zzu zzafm;

    public zze(Executor arg1, Continuation arg2, zzu arg3) {
        super();
        this.zzafk = arg1;
        this.zzafl = arg2;
        this.zzafm = arg3;
    }

    public final void cancel() {
        throw new UnsupportedOperationException();
    }

    public final void onCanceled() {
        this.zzafm.zzdp();
    }

    public final void onComplete(Task arg3) {
        this.zzafk.execute(new zzf(this, arg3));
    }

    public final void onFailure(Exception arg2) {
        this.zzafm.setException(arg2);
    }

    public final void onSuccess(Object arg2) {
        this.zzafm.setResult(arg2);
    }

    static Continuation zza(zze arg0) {
        return arg0.zzafl;
    }

    static zzu zzb(zze arg0) {
        return arg0.zzafm;
    }
}

