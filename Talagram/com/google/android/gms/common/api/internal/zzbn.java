package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.ConnectionResult;
import java.util.Collections;

final class zzbn implements Runnable {
    zzbn(zzc arg1, ConnectionResult arg2) {
        this.zzkr = arg1;
        this.zzkl = arg2;
        super();
    }

    public final void run() {
        if(this.zzkl.isSuccess()) {
            zzc.zza(this.zzkr, true);
            if(zzc.zza(this.zzkr).requiresSignIn()) {
                zzc.zzb(this.zzkr);
                return;
            }

            zzc.zza(this.zzkr).getRemoteService(null, Collections.emptySet());
            return;
        }

        GoogleApiManager.zzj(this.zzkr.zzjy).get(zzc.zzc(this.zzkr)).onConnectionFailed(this.zzkl);
    }
}

