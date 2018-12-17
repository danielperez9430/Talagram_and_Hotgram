package com.google.android.gms.internal.location;

import android.app.PendingIntent;
import android.location.Location;
import android.os.Looper;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.location.FusedLocationProviderApi;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

@VisibleForTesting public final class zzq implements FusedLocationProviderApi {
    public zzq() {
        super();
    }

    public final PendingResult flushLocations(GoogleApiClient arg2) {
        return arg2.execute(new zzv(this, arg2));
    }

    public final Location getLastLocation(GoogleApiClient arg1) {
        zzaz v1 = LocationServices.zza(arg1);
        try {
            return v1.getLastLocation();
        }
        catch(Exception ) {
            return null;
        }
    }

    public final LocationAvailability getLocationAvailability(GoogleApiClient arg1) {
        zzaz v1 = LocationServices.zza(arg1);
        try {
            return v1.zza();
        }
        catch(Exception ) {
            return null;
        }
    }

    public final PendingResult removeLocationUpdates(GoogleApiClient arg2, PendingIntent arg3) {
        return arg2.execute(new zzaa(this, arg2, arg3));
    }

    public final PendingResult removeLocationUpdates(GoogleApiClient arg2, LocationCallback arg3) {
        return arg2.execute(new zzs(this, arg2, arg3));
    }

    public final PendingResult removeLocationUpdates(GoogleApiClient arg2, LocationListener arg3) {
        return arg2.execute(new zzz(this, arg2, arg3));
    }

    public final PendingResult requestLocationUpdates(GoogleApiClient arg2, LocationRequest arg3, PendingIntent arg4) {
        return arg2.execute(new zzy(this, arg2, arg3, arg4));
    }

    public final PendingResult requestLocationUpdates(GoogleApiClient arg8, LocationRequest arg9, LocationCallback arg10, Looper arg11) {
        return arg8.execute(new zzx(this, arg8, arg9, arg10, arg11));
    }

    public final PendingResult requestLocationUpdates(GoogleApiClient arg3, LocationRequest arg4, LocationListener arg5) {
        Preconditions.checkNotNull(Looper.myLooper(), "Calling thread must be a prepared Looper thread.");
        return arg3.execute(new zzr(this, arg3, arg4, arg5));
    }

    public final PendingResult requestLocationUpdates(GoogleApiClient arg8, LocationRequest arg9, LocationListener arg10, Looper arg11) {
        return arg8.execute(new zzw(this, arg8, arg9, arg10, arg11));
    }

    public final PendingResult setMockLocation(GoogleApiClient arg2, Location arg3) {
        return arg2.execute(new zzu(this, arg2, arg3));
    }

    public final PendingResult setMockMode(GoogleApiClient arg2, boolean arg3) {
        return arg2.execute(new zzt(this, arg2, arg3));
    }
}

