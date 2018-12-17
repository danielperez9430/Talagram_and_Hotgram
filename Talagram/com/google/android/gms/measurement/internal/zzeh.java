package com.google.android.gms.measurement.internal;

import android.content.ComponentName;

final class zzeh implements Runnable {
    zzeh(zzef arg1, ComponentName arg2) {
        this.zzasp = arg1;
        this.val$name = arg2;
        super();
    }

    public final void run() {
        zzdr.zza(this.zzasp.zzasg, this.val$name);
    }
}

