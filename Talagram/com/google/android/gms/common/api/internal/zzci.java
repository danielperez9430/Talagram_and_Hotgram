package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;

final class zzci implements Runnable {
    zzci(zzch arg1, Result arg2) {
        this.zzml = arg1;
        this.zzmk = arg2;
        super();
    }

    public final void run() {
        Object v0_1;
        try {
            BasePendingResult.zzez.set(Boolean.valueOf(true));
            zzch.zzd(this.zzml).sendMessage(zzch.zzd(this.zzml).obtainMessage(0, zzch.zzc(this.zzml).onSuccess(this.zzmk)));
        }
        catch(Throwable v0) {
        label_50:
            BasePendingResult.zzez.set(Boolean.valueOf(false));
            zzch.zza(this.zzml, this.zzmk);
            Object v1 = zzch.zze(this.zzml).get();
            if(v1 != null) {
                ((GoogleApiClient)v1).zzb(this.zzml);
            }

            throw v0;
        }
        catch(RuntimeException v2) {
            try {
                zzch.zzd(this.zzml).sendMessage(zzch.zzd(this.zzml).obtainMessage(1, v2));
            }
            catch(Throwable v0) {
                goto label_50;
            }

            BasePendingResult.zzez.set(Boolean.valueOf(false));
            zzch.zza(this.zzml, this.zzmk);
            v0_1 = zzch.zze(this.zzml).get();
            if(v0_1 != null) {
                ((GoogleApiClient)v0_1).zzb(this.zzml);
            }

            return;
        }

        BasePendingResult.zzez.set(Boolean.valueOf(false));
        zzch.zza(this.zzml, this.zzmk);
        v0_1 = zzch.zze(this.zzml).get();
        if(v0_1 != null) {
            ((GoogleApiClient)v0_1).zzb(this.zzml);
        }
    }
}

