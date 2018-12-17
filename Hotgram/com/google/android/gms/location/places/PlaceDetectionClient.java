package com.google.android.gms.location.places;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.common.api.Api$ApiOptions;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi$Settings;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.tasks.Task;

public class PlaceDetectionClient extends GoogleApi {
    PlaceDetectionClient(Activity arg3, PlacesOptions arg4) {
        super(arg3, Places.PLACE_DETECTION_API, ((ApiOptions)arg4), Settings.DEFAULT_SETTINGS);
    }

    PlaceDetectionClient(Context arg2, PlacesOptions arg3) {
        this(arg2, Places.PLACE_DETECTION_API, arg3);
    }

    @VisibleForTesting private PlaceDetectionClient(Context arg2, Api arg3, PlacesOptions arg4) {
        super(arg2, arg3, ((ApiOptions)arg4), Settings.DEFAULT_SETTINGS);
    }

    public Task getCurrentPlace(PlaceFilter arg3) {
        return PendingResultUtil.toResponseTask(Places.PlaceDetectionApi.getCurrentPlace(this.asGoogleApiClient(), arg3), new PlaceLikelihoodBufferResponse());
    }

    public Task reportDeviceAtPlace(PlaceReport arg3) {
        return PendingResultUtil.toVoidTask(Places.PlaceDetectionApi.reportDeviceAtPlace(this.asGoogleApiClient(), arg3));
    }
}

