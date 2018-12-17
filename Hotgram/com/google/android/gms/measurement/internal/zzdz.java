package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import android.text.TextUtils;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

final class zzdz implements Runnable {
    zzdz(zzdr arg1, boolean arg2, boolean arg3, zzad arg4, zzh arg5, String arg6) {
        this.zzasg = arg1;
        this.zzasi = arg2;
        this.zzasj = arg3;
        this.zzaqr = arg4;
        this.zzaqn = arg5;
        this.zzaqq = arg6;
        super();
    }

    public final void run() {
        zzad v2_1;
        zzag v0 = zzdr.zzd(this.zzasg);
        if(v0 == null) {
            this.zzasg.zzgo().zzjd().zzbx("Discarding data. Failed to send event to service");
            return;
        }

        if(this.zzasi) {
            zzdr v1 = this.zzasg;
            if(this.zzasj) {
                AbstractSafeParcelable v2 = null;
            }
            else {
                v2_1 = this.zzaqr;
            }

            v1.zza(v0, ((AbstractSafeParcelable)v2_1), this.zzaqn);
            goto label_40;
        }

        try {
            if(TextUtils.isEmpty(this.zzaqq)) {
                v0.zza(this.zzaqr, this.zzaqn);
                goto label_40;
            }

            v0.zza(this.zzaqr, this.zzaqq, this.zzasg.zzgo().zzjn());
        }
        catch(RemoteException v0_1) {
            this.zzasg.zzgo().zzjd().zzg("Failed to send event to the service", v0_1);
        }

    label_40:
        zzdr.zze(this.zzasg);
    }
}

