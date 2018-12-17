package com.google.android.gms.internal.location;

import android.app.PendingIntent;
import android.util.Log;
import com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder;
import com.google.android.gms.location.LocationStatusCodes;

final class zzbb extends zzan {
    private ResultHolder zzdf;

    public zzbb(ResultHolder arg1) {
        super();
        this.zzdf = arg1;
    }

    public final void zza(int arg1, PendingIntent arg2) {
        this.zze(arg1);
    }

    public final void zza(int arg1, String[] arg2) {
        Log.wtf("LocationClientImpl", "Unexpected call to onAddGeofencesResult");
    }

    public final void zzb(int arg1, String[] arg2) {
        this.zze(arg1);
    }

    private final void zze(int arg2) {
        if(this.zzdf == null) {
            Log.wtf("LocationClientImpl", "onRemoveGeofencesResult called multiple times");
            return;
        }

        this.zzdf.setResult(LocationStatusCodes.zzd(LocationStatusCodes.zzc(arg2)));
        this.zzdf = null;
    }
}

