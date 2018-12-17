package com.google.android.gms.common.api;

import android.app.Activity;
import android.content.IntentSender$SendIntentException;
import android.util.Log;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;

public abstract class ResolvingResultCallbacks extends ResultCallbacks {
    private final Activity mActivity;
    private final int zzdn;

    protected ResolvingResultCallbacks(Activity arg2, int arg3) {
        super();
        this.mActivity = Preconditions.checkNotNull(arg2, "Activity must not be null");
        this.zzdn = arg3;
    }

    @KeepForSdk public final void onFailure(Status arg3) {
        if(arg3.hasResolution()) {
            try {
                arg3.startResolutionForResult(this.mActivity, this.zzdn);
                return;
            }
            catch(IntentSender$SendIntentException v3) {
                Log.e("ResolvingResultCallback", "Failed to start resolution", ((Throwable)v3));
                this.onUnresolvableFailure(new Status(8));
                return;
            }
        }

        this.onUnresolvableFailure(arg3);
    }

    public abstract void onSuccess(Result arg1);

    public abstract void onUnresolvableFailure(Status arg1);
}

