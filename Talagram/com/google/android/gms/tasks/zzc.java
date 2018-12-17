package com.google.android.gms.tasks;

import java.util.concurrent.Executor;

final class zzc implements zzq {
    private final Executor zzafk;
    private final Continuation zzafl;
    private final zzu zzafm;

    public zzc(Executor arg1, Continuation arg2, zzu arg3) {
        super();
        this.zzafk = arg1;
        this.zzafl = arg2;
        this.zzafm = arg3;
    }

    public final void cancel() {
        throw new UnsupportedOperationException();
    }

    public final void onComplete(Task arg3) {
        this.zzafk.execute(new zzd(this, arg3));
    }

    static zzu zza(zzc arg0) {
        return arg0.zzafm;
    }

    static Continuation zzb(zzc arg0) {
        return arg0.zzafl;
    }
}

