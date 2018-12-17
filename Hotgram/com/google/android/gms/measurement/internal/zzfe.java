package com.google.android.gms.measurement.internal;

import java.util.concurrent.Callable;

final class zzfe implements Callable {
    zzfe(zzfa arg1, zzh arg2) {
        this.zzaty = arg1;
        this.zzaqn = arg2;
        super();
    }

    public final Object call() {
        zzg v0 = this.zzaty.zzgq().zzbd(this.zzaqn.packageName) ? zzfa.zza(this.zzaty, this.zzaqn) : this.zzaty.zzjq().zzbl(this.zzaqn.packageName);
        if(v0 == null) {
            this.zzaty.zzgo().zzjg().zzbx("App info was null when attempting to get app instance id");
            return null;
        }

        return v0.getAppInstanceId();
    }
}

