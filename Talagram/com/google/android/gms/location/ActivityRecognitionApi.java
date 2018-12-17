package com.google.android.gms.location;

import android.app.PendingIntent;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;

@Deprecated public interface ActivityRecognitionApi {
    PendingResult removeActivityUpdates(GoogleApiClient arg1, PendingIntent arg2);

    PendingResult requestActivityUpdates(GoogleApiClient arg1, long arg2, PendingIntent arg3);

    PendingResult zza(GoogleApiClient arg1, PendingIntent arg2);

    PendingResult zza(GoogleApiClient arg1, ActivityTransitionRequest arg2, PendingIntent arg3);
}

