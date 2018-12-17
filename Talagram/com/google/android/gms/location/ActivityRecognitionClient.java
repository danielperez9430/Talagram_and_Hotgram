package com.google.android.gms.location;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.internal.ApiExceptionMapper;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.tasks.Task;

public class ActivityRecognitionClient extends GoogleApi {
    public ActivityRecognitionClient(Activity arg4) {
        super(arg4, LocationServices.API, null, new ApiExceptionMapper());
    }

    public ActivityRecognitionClient(Context arg4) {
        super(arg4, LocationServices.API, null, new ApiExceptionMapper());
    }

    public Task removeActivityTransitionUpdates(PendingIntent arg3) {
        return PendingResultUtil.toVoidTask(ActivityRecognition.ActivityRecognitionApi.zza(this.asGoogleApiClient(), arg3));
    }

    public Task removeActivityUpdates(PendingIntent arg3) {
        return PendingResultUtil.toVoidTask(ActivityRecognition.ActivityRecognitionApi.removeActivityUpdates(this.asGoogleApiClient(), arg3));
    }

    public Task requestActivityTransitionUpdates(ActivityTransitionRequest arg3, PendingIntent arg4) {
        return PendingResultUtil.toVoidTask(ActivityRecognition.ActivityRecognitionApi.zza(this.asGoogleApiClient(), arg3, arg4));
    }

    public Task requestActivityUpdates(long arg3, PendingIntent arg5) {
        return PendingResultUtil.toVoidTask(ActivityRecognition.ActivityRecognitionApi.requestActivityUpdates(this.asGoogleApiClient(), arg3, arg5));
    }
}

