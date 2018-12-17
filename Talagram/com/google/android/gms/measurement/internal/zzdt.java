package com.google.android.gms.measurement.internal;

import android.os.RemoteException;

final class zzdt implements Runnable {
    zzdt(zzdr arg1, zzh arg2) {
        this.zzasg = arg1;
        this.zzaqn = arg2;
        super();
    }

    public final void run() {
        zzag v0 = zzdr.zzd(this.zzasg);
        if(v0 == null) {
            this.zzasg.zzgo().zzjd().zzbx("Failed to reset data on the service; null service");
            return;
        }

        try {
            v0.zzd(this.zzaqn);
        }
        catch(RemoteException v0_1) {
            this.zzasg.zzgo().zzjd().zzg("Failed to reset data on the service", v0_1);
        }

        zzdr.zze(this.zzasg);
    }
}

