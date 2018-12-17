package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder;
import java.util.Iterator;
import java.util.List;

final class zzhb extends zzgm {
    private final List zzev;

    zzhb(ResultHolder arg1, List arg2) {
        super(arg1);
        this.zzev = arg2;
    }

    public final void zza(zzfu arg4) {
        ((zzgm)this).zza(new zzcg(zzgd.zzb(arg4.statusCode), arg4.zzdy));
        if(arg4.statusCode != 0) {
            Iterator v4 = this.zzev.iterator();
            while(v4.hasNext()) {
                v4.next().cancel(true);
            }
        }
    }
}

