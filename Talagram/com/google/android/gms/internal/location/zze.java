package com.google.android.gms.internal.location;

import android.app.PendingIntent;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.location.ActivityRecognitionApi;
import com.google.android.gms.location.ActivityTransitionRequest;

public final class zze implements ActivityRecognitionApi {
    public zze() {
        super();
    }

    public final PendingResult removeActivityUpdates(GoogleApiClient arg2, PendingIntent arg3) {
        return arg2.execute(new zzg(this, arg2, arg3));
    }

    public final PendingResult requestActivityUpdates(GoogleApiClient arg8, long arg9, PendingIntent arg11) {
        return arg8.execute(new zzf(this, arg8, arg9, arg11));
    }

    public final PendingResult zza(GoogleApiClient arg2, PendingIntent arg3) {
        return arg2.execute(new zzi(this, arg2, arg3));
    }

    public final PendingResult zza(GoogleApiClient arg2, ActivityTransitionRequest arg3, PendingIntent arg4) {
        return arg2.execute(new zzh(this, arg2, arg3, arg4));
    }
}

