package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder;
import com.google.android.gms.wearable.CapabilityInfo;

final class zzgr extends zzgm {
    public zzgr(ResultHolder arg1) {
        super(arg1);
    }

    public final void zza(zzdk arg4) {
        CapabilityInfo v4;
        Status v1 = zzgd.zzb(arg4.statusCode);
        if(arg4.zzdq == null) {
            v4 = null;
        }
        else {
            zzw v4_1 = new zzw(arg4.zzdq);
        }

        ((zzgm)this).zza(new zzy(v1, v4));
    }
}

