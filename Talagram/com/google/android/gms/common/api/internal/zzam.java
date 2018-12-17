package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api$Client;
import com.google.android.gms.common.internal.BaseGmsClient$ConnectionProgressReportCallbacks;
import com.google.android.gms.common.internal.GoogleApiAvailabilityCache;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

final class zzam extends zzat {
    private final Map zzhx;

    public zzam(zzaj arg2, Map arg3) {
        this.zzhv = arg2;
        super(arg2, null);
        this.zzhx = arg3;
    }

    public final void zzaq() {
        Object v3_2;
        GoogleApiAvailabilityCache v0 = new GoogleApiAvailabilityCache(zzaj.zzb(this.zzhv));
        ArrayList v1 = new ArrayList();
        ArrayList v2 = new ArrayList();
        Iterator v3 = this.zzhx.keySet().iterator();
        while(v3.hasNext()) {
            Object v4 = v3.next();
            if((((Client)v4).requiresGooglePlayServices()) && !zzal.zza(this.zzhx.get(v4))) {
                ((List)v1).add(v4);
                continue;
            }

            ((List)v2).add(v4);
        }

        int v3_1 = -1;
        int v5 = 0;
        if(((List)v1).isEmpty()) {
            int v1_1 = v2.size();
            goto label_29;
        }
        else {
            int v2_1 = v1.size();
            do {
                if(v5 < v2_1) {
                    v3_2 = v1.get(v5);
                    ++v5;
                    v3_1 = v0.getClientAvailability(zzaj.zza(this.zzhv), ((Client)v3_2));
                    if(v3_1 == 0) {
                        continue;
                    }
                }

                goto label_45;
            }
            while(true);

            do {
            label_29:
                if(v5 < v1_1) {
                    v3_2 = v2.get(v5);
                    ++v5;
                    v3_1 = v0.getClientAvailability(zzaj.zza(this.zzhv), ((Client)v3_2));
                    if(v3_1 != 0) {
                        continue;
                    }

                    break;
                }

                break;
            }
            while(true);
        }

    label_45:
        if(v3_1 != 0) {
            zzaj.zzd(this.zzhv).zza(new zzan(this, this.zzhv, new ConnectionResult(v3_1, null)));
            return;
        }

        if(zzaj.zze(this.zzhv)) {
            zzaj.zzf(this.zzhv).connect();
        }

        Iterator v1_2 = this.zzhx.keySet().iterator();
        while(v1_2.hasNext()) {
            Object v2_2 = v1_2.next();
            v3_2 = this.zzhx.get(v2_2);
            if((((Client)v2_2).requiresGooglePlayServices()) && v0.getClientAvailability(zzaj.zza(this.zzhv), ((Client)v2_2)) != 0) {
                zzaj.zzd(this.zzhv).zza(new zzao(this, this.zzhv, ((ConnectionProgressReportCallbacks)v3_2)));
                continue;
            }

            ((Client)v2_2).connect(((ConnectionProgressReportCallbacks)v3_2));
        }
    }
}

