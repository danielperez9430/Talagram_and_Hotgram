package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder;
import java.util.ArrayList;
import java.util.List;

final class zzgu extends zzgm {
    public zzgu(ResultHolder arg1) {
        super(arg1);
    }

    public final void zza(zzea arg3) {
        ArrayList v0 = new ArrayList();
        if(arg3.zzdx != null) {
            ((List)v0).addAll(arg3.zzdx);
        }

        ((zzgm)this).zza(new zzfj(zzgd.zzb(arg3.statusCode), ((List)v0)));
    }
}

