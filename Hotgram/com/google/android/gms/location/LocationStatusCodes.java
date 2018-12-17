package com.google.android.gms.location;

import com.google.android.gms.common.api.Status;

@Deprecated public final class LocationStatusCodes {
    public static final int ERROR = 1;
    public static final int GEOFENCE_NOT_AVAILABLE = 1000;
    public static final int GEOFENCE_TOO_MANY_GEOFENCES = 1001;
    public static final int GEOFENCE_TOO_MANY_PENDING_INTENTS = 1002;
    public static final int SUCCESS;

    private LocationStatusCodes() {
        super();
    }

    public static int zzc(int arg2) {
        if(arg2 >= 0 && arg2 <= 1 || 1000 <= arg2 && arg2 <= 1002) {
            return arg2;
        }

        return 1;
    }

    public static Status zzd(int arg1) {
        if(arg1 != 1) {
        }
        else {
            arg1 = 13;
        }

        return new Status(arg1);
    }
}

