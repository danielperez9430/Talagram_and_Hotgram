package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import android.text.TextUtils;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

final class zzec implements Runnable {
    zzec(zzdr arg1, AtomicReference arg2, String arg3, String arg4, String arg5, boolean arg6, zzh arg7) {
        this.zzasg = arg1;
        this.zzash = arg2;
        this.zzaqq = arg3;
        this.zzaeh = arg4;
        this.zzaeo = arg5;
        this.zzaev = arg6;
        this.zzaqn = arg7;
        super();
    }

    public final void run() {
        AtomicReference v1_4;
        List v1_3;
        AtomicReference v2;
        zzag v1_2;
        AtomicReference v0 = this.zzash;
        __monitor_enter(v0);
        try {
            v1_2 = zzdr.zzd(this.zzasg);
            if(v1_2 != null) {
                goto label_21;
            }

            this.zzasg.zzgo().zzjd().zzd("Failed to get user properties", zzap.zzbv(this.zzaqq), this.zzaeh, this.zzaeo);
            this.zzash.set(Collections.emptyList());
        }
        catch(Throwable v1) {
            goto label_45;
        }
        catch(RemoteException v1_1) {
            goto label_47;
        }

        try {
            this.zzash.notify();
            __monitor_exit(v0);
            return;
        }
        catch(Throwable v1) {
            goto label_66;
        }

        try {
        label_21:
            if(TextUtils.isEmpty(this.zzaqq)) {
                v2 = this.zzash;
                v1_3 = v1_2.zza(this.zzaeh, this.zzaeo, this.zzaev, this.zzaqn);
            }
            else {
                v2 = this.zzash;
                v1_3 = v1_2.zza(this.zzaqq, this.zzaeh, this.zzaeo, this.zzaev);
            }

            v2.set(v1_3);
            zzdr.zze(this.zzasg);
            goto label_41;
        }
        catch(Throwable v1) {
        label_45:
            try {
                this.zzash.notify();
                throw v1;
            label_41:
                v1_4 = this.zzash;
            label_42:
                v1_4.notify();
                __monitor_exit(v0);
                return;
            label_66:
                __monitor_exit(v0);
            }
            catch(Throwable v1) {
                goto label_66;
            }
        }
        catch(RemoteException v1_1) {
            try {
            label_47:
                this.zzasg.zzgo().zzjd().zzd("Failed to get user properties", zzap.zzbv(this.zzaqq), this.zzaeh, v1_1);
                this.zzash.set(Collections.emptyList());
            }
            catch(Throwable v1) {
                goto label_45;
            }

            try {
                v1_4 = this.zzash;
                goto label_42;
            }
            catch(Throwable v1) {
                goto label_66;
            }
        }

        throw v1;
    }
}

