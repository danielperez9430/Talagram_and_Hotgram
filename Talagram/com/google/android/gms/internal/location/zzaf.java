package com.google.android.gms.internal.location;

import android.app.PendingIntent;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.location.GeofencingApi;
import com.google.android.gms.location.GeofencingRequest$Builder;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.zzal;
import java.util.List;

public final class zzaf implements GeofencingApi {
    public zzaf() {
        super();
    }

    public final PendingResult addGeofences(GoogleApiClient arg2, GeofencingRequest arg3, PendingIntent arg4) {
        return arg2.execute(new zzag(this, arg2, arg3, arg4));
    }

    @Deprecated public final PendingResult addGeofences(GoogleApiClient arg2, List arg3, PendingIntent arg4) {
        Builder v0 = new Builder();
        v0.addGeofences(arg3);
        v0.setInitialTrigger(5);
        return this.addGeofences(arg2, v0.build(), arg4);
    }

    public final PendingResult removeGeofences(GoogleApiClient arg1, PendingIntent arg2) {
        return this.zza(arg1, zzal.zza(arg2));
    }

    public final PendingResult removeGeofences(GoogleApiClient arg1, List arg2) {
        return this.zza(arg1, zzal.zza(arg2));
    }

    private final PendingResult zza(GoogleApiClient arg2, zzal arg3) {
        return arg2.execute(new zzah(this, arg2, arg3));
    }
}

