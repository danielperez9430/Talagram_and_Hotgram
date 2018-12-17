package com.google.android.gms.common.util.concurrent;

import android.os.Process;

final class zza implements Runnable {
    private final int priority;
    private final Runnable zzaax;

    public zza(Runnable arg1, int arg2) {
        super();
        this.zzaax = arg1;
        this.priority = arg2;
    }

    public final void run() {
        Process.setThreadPriority(this.priority);
        this.zzaax.run();
    }
}

