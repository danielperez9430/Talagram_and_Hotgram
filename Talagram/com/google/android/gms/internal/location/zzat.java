package com.google.android.gms.internal.location;

import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.zzv;

final class zzat extends zzv {
    private final ListenerHolder zzda;

    zzat(ListenerHolder arg1) {
        super();
        this.zzda = arg1;
    }

    public final void onLocationAvailability(LocationAvailability arg3) {
        this.zzda.notifyListener(new zzav(this, arg3));
    }

    public final void onLocationResult(LocationResult arg3) {
        this.zzda.notifyListener(new zzau(this, arg3));
    }

    public final void release() {
        __monitor_enter(this);
        try {
            this.zzda.clear();
        }
        catch(Throwable v0) {
            __monitor_exit(this);
            throw v0;
        }

        __monitor_exit(this);
    }
}

