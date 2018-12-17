package com.google.android.gms.internal.location;

import com.google.android.gms.common.api.internal.ListenerHolder$Notifier;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationCallback;

final class zzav implements Notifier {
    zzav(zzat arg1, LocationAvailability arg2) {
        this.zzdc = arg2;
        super();
    }

    public final void notifyListener(Object arg2) {
        ((LocationCallback)arg2).onLocationAvailability(this.zzdc);
    }

    public final void onNotifyListenerFailed() {
    }
}

