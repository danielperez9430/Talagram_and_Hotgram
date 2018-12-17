package com.google.android.gms.location;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.internal.ApiExceptionMapper;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.tasks.Task;

public class SettingsClient extends GoogleApi {
    public SettingsClient(Activity arg4) {
        super(arg4, LocationServices.API, null, new ApiExceptionMapper());
    }

    public SettingsClient(Context arg4) {
        super(arg4, LocationServices.API, null, new ApiExceptionMapper());
    }

    public Task checkLocationSettings(LocationSettingsRequest arg3) {
        return PendingResultUtil.toResponseTask(LocationServices.SettingsApi.checkLocationSettings(this.asGoogleApiClient(), arg3), new LocationSettingsResponse());
    }
}

