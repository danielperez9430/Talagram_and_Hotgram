package com.google.android.gms.tasks;

final class zza extends CancellationToken {
    private final zzu zzafh;

    zza() {
        super();
        this.zzafh = new zzu();
    }

    public final void cancel() {
        this.zzafh.trySetResult(null);
    }

    public final boolean isCancellationRequested() {
        return this.zzafh.isComplete();
    }

    public final CancellationToken onCanceledRequested(OnTokenCanceledListener arg3) {
        this.zzafh.addOnSuccessListener(new zzb(this, arg3));
        return this;
    }
}

