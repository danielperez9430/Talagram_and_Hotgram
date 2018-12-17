package com.google.android.gms.location;

import android.app.PendingIntent;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import java.util.List;

@Deprecated public interface GeofencingApi {
    PendingResult addGeofences(GoogleApiClient arg1, GeofencingRequest arg2, PendingIntent arg3);

    @Deprecated PendingResult addGeofences(GoogleApiClient arg1, List arg2, PendingIntent arg3);

    PendingResult removeGeofences(GoogleApiClient arg1, PendingIntent arg2);

    PendingResult removeGeofences(GoogleApiClient arg1, List arg2);
}

