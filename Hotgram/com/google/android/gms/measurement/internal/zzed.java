package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

final class zzed implements Runnable {
    zzed(zzdr arg1, boolean arg2, zzfh arg3, zzh arg4) {
        this.zzasg = arg1;
        this.zzasj = arg2;
        this.zzaqs = arg3;
        this.zzaqn = arg4;
        super();
    }

    public final void run() {
        zzfh v2_1;
        zzag v0 = zzdr.zzd(this.zzasg);
        if(v0 == null) {
            this.zzasg.zzgo().zzjd().zzbx("Discarding data. Failed to set user attribute");
            return;
        }

        zzdr v1 = this.zzasg;
        if(this.zzasj) {
            AbstractSafeParcelable v2 = null;
        }
        else {
            v2_1 = this.zzaqs;
        }

        v1.zza(v0, ((AbstractSafeParcelable)v2_1), this.zzaqn);
        zzdr.zze(this.zzasg);
    }
}

