package com.google.android.gms.common.api;

import android.app.Activity;
import android.app.PendingIntent;

public class ResolvableApiException extends ApiException {
    public ResolvableApiException(Status arg1) {
        super(arg1);
    }

    public PendingIntent getResolution() {
        return this.mStatus.getResolution();
    }

    public void startResolutionForResult(Activity arg2, int arg3) {
        this.mStatus.startResolutionForResult(arg2, arg3);
    }
}

