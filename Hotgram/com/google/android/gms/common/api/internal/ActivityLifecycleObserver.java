package com.google.android.gms.common.api.internal;

import android.app.Activity;
import com.google.android.gms.common.annotation.KeepForSdkWithMembers;

@KeepForSdkWithMembers public abstract class ActivityLifecycleObserver {
    public ActivityLifecycleObserver() {
        super();
    }

    public static final ActivityLifecycleObserver of(Activity arg1) {
        return new zza(arg1);
    }

    public abstract ActivityLifecycleObserver onStopCallOnce(Runnable arg1);
}

