package com.google.android.gms.internal.location;

import android.app.PendingIntent;
import android.location.Location;
import android.os.IInterface;
import com.google.android.gms.common.api.internal.IStatusCallback;
import com.google.android.gms.location.ActivityTransitionRequest;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.zzal;

public interface zzao extends IInterface {
    Location zza(String arg1);

    void zza(long arg1, boolean arg2, PendingIntent arg3);

    void zza(PendingIntent arg1, IStatusCallback arg2);

    void zza(Location arg1);

    void zza(zzaj arg1);

    void zza(zzbf arg1);

    void zza(zzo arg1);

    void zza(ActivityTransitionRequest arg1, PendingIntent arg2, IStatusCallback arg3);

    void zza(GeofencingRequest arg1, PendingIntent arg2, zzam arg3);

    void zza(LocationSettingsRequest arg1, zzaq arg2, String arg3);

    void zza(zzal arg1, zzam arg2);

    void zza(boolean arg1);

    LocationAvailability zzb(String arg1);

    void zzb(PendingIntent arg1);
}

