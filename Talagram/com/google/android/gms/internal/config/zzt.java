package com.google.android.gms.internal.config;

import com.google.android.gms.common.api.Status;

final class zzt extends zzq {
    zzt(zzs arg1) {
        this.zzp = arg1;
        super();
    }

    public final void zza(Status arg8, zzad arg9) {
        if(arg9.getStatusCode() != 6502) {
            if(arg9.getStatusCode() == 6507) {
            }
            else {
                this.zzp.setResult(new zzu(zzo.zze(arg9.getStatusCode()), zzo.zzc(arg9), zzo.zzb(arg9)));
                return;
            }
        }

        this.zzp.setResult(new zzu(zzo.zze(arg9.getStatusCode()), zzo.zzc(arg9), arg9.getThrottleEndTimeMillis(), zzo.zzb(arg9)));
    }
}

