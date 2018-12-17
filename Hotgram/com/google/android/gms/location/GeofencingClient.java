package com.google.android.gms.location;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.internal.ApiExceptionMapper;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.tasks.Task;
import java.util.List;

public class GeofencingClient extends GoogleApi {
    public GeofencingClient(Activity arg4) {
        super(arg4, LocationServices.API, null, new ApiExceptionMapper());
    }

    public GeofencingClient(Context arg4) {
        super(arg4, LocationServices.API, null, new ApiExceptionMapper());
    }

    public Task addGeofences(GeofencingRequest arg3, PendingIntent arg4) {
        return PendingResultUtil.toVoidTask(LocationServices.GeofencingApi.addGeofences(this.asGoogleApiClient(), arg3, arg4));
    }

    public Task removeGeofences(PendingIntent arg3) {
        return PendingResultUtil.toVoidTask(LocationServices.GeofencingApi.removeGeofences(this.asGoogleApiClient(), arg3));
    }

    public Task removeGeofences(List arg3) {
        return PendingResultUtil.toVoidTask(LocationServices.GeofencingApi.removeGeofences(this.asGoogleApiClient(), arg3));
    }
}

