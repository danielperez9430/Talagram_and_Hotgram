package com.google.android.gms.tasks;

final class zzb implements OnSuccessListener {
    zzb(zza arg1, OnTokenCanceledListener arg2) {
        this.zzafi = arg2;
        super();
    }

    public final void onSuccess(Object arg1) {
        this.zzafi.onCanceled();
    }
}

