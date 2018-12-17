package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import android.text.TextUtils;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

final class zzea implements Runnable {
    zzea(zzdr arg1, boolean arg2, boolean arg3, zzl arg4, zzh arg5, zzl arg6) {
        this.zzasg = arg1;
        this.zzasi = arg2;
        this.zzasj = arg3;
        this.zzask = arg4;
        this.zzaqn = arg5;
        this.zzasl = arg6;
        super();
    }

    public final void run() {
        AbstractSafeParcelable v2;
        zzag v0 = zzdr.zzd(this.zzasg);
        if(v0 == null) {
            this.zzasg.zzgo().zzjd().zzbx("Discarding data. Failed to send conditional user property to service");
            return;
        }

        if(this.zzasi) {
            zzdr v1 = this.zzasg;
            if(this.zzasj) {
                v2 = null;
            }
            else {
                zzl v2_1 = this.zzask;
            }

            v1.zza(v0, v2, this.zzaqn);
            goto label_37;
        }

        try {
            if(TextUtils.isEmpty(this.zzasl.packageName)) {
                v0.zza(this.zzask, this.zzaqn);
                goto label_37;
            }

            v0.zzb(this.zzask);
        }
        catch(RemoteException v0_1) {
            this.zzasg.zzgo().zzjd().zzg("Failed to send conditional user property to the service", v0_1);
        }

    label_37:
        zzdr.zze(this.zzasg);
    }
}

