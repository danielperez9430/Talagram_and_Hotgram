package com.google.android.gms.measurement.internal;

import android.content.ComponentName;
import android.content.Context;

final class zzej implements Runnable {
    zzej(zzef arg1) {
        this.zzasp = arg1;
        super();
    }

    public final void run() {
        zzdr v0 = this.zzasp.zzasg;
        Context v2 = this.zzasp.zzasg.getContext();
        this.zzasp.zzasg.zzgr();
        zzdr.zza(v0, new ComponentName(v2, "com.google.android.gms.measurement.AppMeasurementService"));
    }
}

