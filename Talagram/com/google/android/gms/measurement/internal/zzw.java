package com.google.android.gms.measurement.internal;

final class zzw implements Runnable {
    zzw(zzv arg1, zzcq arg2) {
        this.zzahy = arg1;
        this.zzahx = arg2;
        super();
    }

    public final void run() {
        this.zzahx.zzgr();
        if(zzk.isMainThread()) {
            this.zzahx.zzgn().zzc(((Runnable)this));
            return;
        }

        boolean v0 = this.zzahy.zzej();
        zzv.zza(this.zzahy, 0);
        if(v0) {
            this.zzahy.run();
        }
    }
}

