package com.google.android.gms.measurement.internal;

import android.os.RemoteException;

final class zzdv implements Runnable {
    zzdv(zzdr arg1, zzh arg2) {
        this.zzasg = arg1;
        this.zzaqn = arg2;
        super();
    }

    public final void run() {
        zzag v0 = zzdr.zzd(this.zzasg);
        if(v0 == null) {
            this.zzasg.zzgo().zzjd().zzbx("Discarding data. Failed to send app launch");
            return;
        }

        try {
            v0.zza(this.zzaqn);
            this.zzasg.zza(v0, null, this.zzaqn);
            zzdr.zze(this.zzasg);
            return;
        }
        catch(RemoteException v0_1) {
            this.zzasg.zzgo().zzjd().zzg("Failed to send app launch to the service", v0_1);
            return;
        }
    }
}

