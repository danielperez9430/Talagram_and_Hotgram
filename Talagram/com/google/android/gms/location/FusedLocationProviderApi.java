package com.google.android.gms.location;

import android.app.PendingIntent;
import android.location.Location;
import android.os.Looper;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;

@Deprecated public interface FusedLocationProviderApi {
    @Deprecated public static final String KEY_LOCATION_CHANGED = "com.google.android.location.LOCATION";
    public static final String KEY_MOCK_LOCATION = "mockLocation";

    PendingResult flushLocations(GoogleApiClient arg1);

    Location getLastLocation(GoogleApiClient arg1);

    LocationAvailability getLocationAvailability(GoogleApiClient arg1);

    PendingResult removeLocationUpdates(GoogleApiClient arg1, PendingIntent arg2);

    PendingResult removeLocationUpdates(GoogleApiClient arg1, LocationCallback arg2);

    PendingResult removeLocationUpdates(GoogleApiClient arg1, LocationListener arg2);

    PendingResult requestLocationUpdates(GoogleApiClient arg1, LocationRequest arg2, PendingIntent arg3);

    PendingResult requestLocationUpdates(GoogleApiClient arg1, LocationRequest arg2, LocationCallback arg3, Looper arg4);

    PendingResult requestLocationUpdates(GoogleApiClient arg1, LocationRequest arg2, LocationListener arg3);

    PendingResult requestLocationUpdates(GoogleApiClient arg1, LocationRequest arg2, LocationListener arg3, Looper arg4);

    PendingResult setMockLocation(GoogleApiClient arg1, Location arg2);

    PendingResult setMockMode(GoogleApiClient arg1, boolean arg2);
}

