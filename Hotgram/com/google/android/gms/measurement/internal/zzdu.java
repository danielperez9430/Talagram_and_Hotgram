package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import java.util.concurrent.atomic.AtomicReference;

final class zzdu implements Runnable {
    zzdu(zzdr arg1, AtomicReference arg2, zzh arg3) {
        this.zzasg = arg1;
        this.zzash = arg2;
        this.zzaqn = arg3;
        super();
    }

    public final void run() {
        AtomicReference v1_4;
        zzag v1_2;
        AtomicReference v0 = this.zzash;
        __monitor_enter(v0);
        try {
            v1_2 = zzdr.zzd(this.zzasg);
            if(v1_2 != null) {
                goto label_14;
            }

            this.zzasg.zzgo().zzjd().zzbx("Failed to get app instance id");
        }
        catch(Throwable v1) {
            goto label_34;
        }
        catch(RemoteException v1_1) {
            goto label_36;
        }

        try {
            this.zzash.notify();
            __monitor_exit(v0);
            return;
        }
        catch(Throwable v1) {
            goto label_49;
        }

        try {
        label_14:
            this.zzash.set(v1_2.zzc(this.zzaqn));
            Object v1_3 = this.zzash.get();
            if(v1_3 != null) {
                this.zzasg.zzge().zzcm(((String)v1_3));
                this.zzasg.zzgp().zzanl.zzcc(((String)v1_3));
            }

            zzdr.zze(this.zzasg);
            goto label_30;
        }
        catch(Throwable v1) {
        label_34:
            try {
                this.zzash.notify();
                throw v1;
            label_30:
                v1_4 = this.zzash;
            label_31:
                v1_4.notify();
                __monitor_exit(v0);
                return;
            label_49:
                __monitor_exit(v0);
            }
            catch(Throwable v1) {
                goto label_49;
            }
        }
        catch(RemoteException v1_1) {
            try {
            label_36:
                this.zzasg.zzgo().zzjd().zzg("Failed to get app instance id", v1_1);
            }
            catch(Throwable v1) {
                goto label_34;
            }

            try {
                v1_4 = this.zzash;
                goto label_31;
            }
            catch(Throwable v1) {
                goto label_49;
            }
        }

        throw v1;
    }
}

