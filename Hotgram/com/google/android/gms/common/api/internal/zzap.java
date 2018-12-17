package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.Api$Client;
import java.util.ArrayList;

final class zzap extends zzat {
    private final ArrayList zzib;

    public zzap(zzaj arg2, ArrayList arg3) {
        this.zzhv = arg2;
        super(arg2, null);
        this.zzib = arg3;
    }

    public final void zzaq() {
        zzaj.zzd(this.zzhv).zzfq.zzim = zzaj.zzg(this.zzhv);
        ArrayList v0 = this.zzib;
        int v1 = v0.size();
        int v2 = 0;
        while(v2 < v1) {
            Object v3 = v0.get(v2);
            ++v2;
            ((Client)v3).getRemoteService(zzaj.zzh(this.zzhv), zzaj.zzd(this.zzhv).zzfq.zzim);
        }
    }
}

