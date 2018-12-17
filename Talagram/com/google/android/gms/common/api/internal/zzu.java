package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;

final class zzu implements zzbq {
    zzu(zzr arg1, zzs arg2) {
        this(arg1);
    }

    private zzu(zzr arg1) {
        this.zzgc = arg1;
        super();
    }

    public final void zzb(int arg3, boolean arg4) {
        zzr.zza(this.zzgc).lock();
        try {
            if(zzr.zzc(this.zzgc)) {
                zzr.zza(this.zzgc, false);
                zzr.zza(this.zzgc, arg3, arg4);
            }
            else {
                zzr.zza(this.zzgc, true);
                zzr.zzf(this.zzgc).onConnectionSuspended(arg3);
            }
        }
        catch(Throwable v3) {
            zzr.zza(this.zzgc).unlock();
            throw v3;
        }

        zzr.zza(this.zzgc).unlock();
    }

    public final void zzb(Bundle arg2) {
        zzr.zza(this.zzgc).lock();
        try {
            zzr.zzb(this.zzgc, ConnectionResult.RESULT_SUCCESS);
            zzr.zzb(this.zzgc);
        }
        catch(Throwable v2) {
            zzr.zza(this.zzgc).unlock();
            throw v2;
        }

        zzr.zza(this.zzgc).unlock();
    }

    public final void zzc(ConnectionResult arg2) {
        zzr.zza(this.zzgc).lock();
        try {
            zzr.zzb(this.zzgc, arg2);
            zzr.zzb(this.zzgc);
        }
        catch(Throwable v2) {
            zzr.zza(this.zzgc).unlock();
            throw v2;
        }

        zzr.zza(this.zzgc).unlock();
    }
}

