package com.google.android.gms.common.api.internal;

final class zzak implements Runnable {
    zzak(zzaj arg1) {
        this.zzhv = arg1;
        super();
    }

    public final void run() {
        zzaj.zzb(this.zzhv).cancelAvailabilityErrorNotifications(zzaj.zza(this.zzhv));
    }
}

