package com.google.android.gms.internal.location;

import android.app.PendingIntent;
import android.util.Log;
import com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder;
import com.google.android.gms.location.LocationStatusCodes;

final class zzba extends zzan {
    private ResultHolder zzdf;

    public zzba(ResultHolder arg1) {
        super();
        this.zzdf = arg1;
    }

    public final void zza(int arg1, PendingIntent arg2) {
        Log.wtf("LocationClientImpl", "Unexpected call to onRemoveGeofencesByPendingIntentResult");
    }

    public final void zza(int arg1, String[] arg2) {
        if(this.zzdf == null) {
            Log.wtf("LocationClientImpl", "onAddGeofenceResult called multiple times");
            return;
        }

        this.zzdf.setResult(LocationStatusCodes.zzd(LocationStatusCodes.zzc(arg1)));
        this.zzdf = null;
    }

    public final void zzb(int arg1, String[] arg2) {
        Log.wtf("LocationClientImpl", "Unexpected call to onRemoveGeofencesByRequestIdsResult");
    }
}

