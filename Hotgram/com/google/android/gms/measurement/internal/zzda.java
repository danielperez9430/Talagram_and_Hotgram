package com.google.android.gms.measurement.internal;

import com.google.android.gms.measurement.AppMeasurement$ConditionalUserProperty;

final class zzda implements Runnable {
    zzda(zzcs arg1, ConditionalUserProperty arg2) {
        this.zzarc = arg1;
        this.zzarj = arg2;
        super();
    }

    public final void run() {
        zzcs.zza(this.zzarc, this.zzarj);
    }
}

