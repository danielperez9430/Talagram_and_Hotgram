package com.google.android.gms.measurement.internal;

import android.os.RemoteException;

final class zzdw implements Runnable {
    zzdw(zzdr arg1, zzdn arg2) {
        this.zzasg = arg1;
        this.zzary = arg2;
        super();
    }

    public final void run() {
        String v6;
        String v5;
        String v4;
        long v2;
        zzag v1 = zzdr.zzd(this.zzasg);
        if(v1 == null) {
            this.zzasg.zzgo().zzjd().zzbx("Failed to send current screen to service");
            return;
        }

        try {
            if(this.zzary == null) {
                v2 = 0;
                v4 = null;
                v5 = null;
                v6 = this.zzasg.getContext().getPackageName();
            }
            else {
                v2 = this.zzary.zzarm;
                v4 = this.zzary.zzuw;
                v5 = this.zzary.zzarl;
                v6 = this.zzasg.getContext().getPackageName();
            }

            v1.zza(v2, v4, v5, v6);
            zzdr.zze(this.zzasg);
            return;
        }
        catch(RemoteException v0) {
            this.zzasg.zzgo().zzjd().zzg("Failed to send current screen to the service", v0);
            return;
        }
    }
}

