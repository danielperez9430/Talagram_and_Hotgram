package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import java.util.concurrent.atomic.AtomicReference;

final class zzee implements Runnable {
    zzee(zzdr arg1, AtomicReference arg2, zzh arg3, boolean arg4) {
        this.zzasg = arg1;
        this.zzash = arg2;
        this.zzaqn = arg3;
        this.zzaev = arg4;
        super();
    }

    public final void run() {
        AtomicReference v1_3;
        zzag v1_2;
        AtomicReference v0 = this.zzash;
        __monitor_enter(v0);
        try {
            v1_2 = zzdr.zzd(this.zzasg);
            if(v1_2 != null) {
                goto label_14;
            }

            this.zzasg.zzgo().zzjd().zzbx("Failed to get user properties");
        }
        catch(Throwable v1) {
            goto label_25;
        }
        catch(RemoteException v1_1) {
            goto label_27;
        }

        try {
            this.zzash.notify();
            __monitor_exit(v0);
            return;
        }
        catch(Throwable v1) {
            goto label_40;
        }

        try {
        label_14:
            this.zzash.set(v1_2.zza(this.zzaqn, this.zzaev));
            zzdr.zze(this.zzasg);
            goto label_21;
        }
        catch(Throwable v1) {
        label_25:
            try {
                this.zzash.notify();
                throw v1;
            label_21:
                v1_3 = this.zzash;
            label_22:
                v1_3.notify();
                __monitor_exit(v0);
                return;
            label_40:
                __monitor_exit(v0);
            }
            catch(Throwable v1) {
                goto label_40;
            }
        }
        catch(RemoteException v1_1) {
            try {
            label_27:
                this.zzasg.zzgo().zzjd().zzg("Failed to get user properties", v1_1);
            }
            catch(Throwable v1) {
                goto label_25;
            }

            try {
                v1_3 = this.zzash;
                goto label_22;
            }
            catch(Throwable v1) {
                goto label_40;
            }
        }

        throw v1;
    }
}

