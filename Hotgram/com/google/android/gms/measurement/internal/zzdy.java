package com.google.android.gms.measurement.internal;

import android.os.RemoteException;

final class zzdy implements Runnable {
    zzdy(zzdr arg1, zzh arg2) {
        this.zzasg = arg1;
        this.zzaqn = arg2;
        super();
    }

    public final void run() {
        zzag v0 = zzdr.zzd(this.zzasg);
        if(v0 == null) {
            this.zzasg.zzgo().zzjd().zzbx("Failed to send measurementEnabled to service");
            return;
        }

        try {
            v0.zzb(this.zzaqn);
            zzdr.zze(this.zzasg);
            return;
        }
        catch(RemoteException v0_1) {
            this.zzasg.zzgo().zzjd().zzg("Failed to send measurementEnabled to the service", v0_1);
            return;
        }
    }
}

