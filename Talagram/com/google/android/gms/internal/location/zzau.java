package com.google.android.gms.internal.location;

import com.google.android.gms.common.api.internal.ListenerHolder$Notifier;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationResult;

final class zzau implements Notifier {
    zzau(zzat arg1, LocationResult arg2) {
        this.zzdb = arg2;
        super();
    }

    public final void notifyListener(Object arg2) {
        ((LocationCallback)arg2).onLocationResult(this.zzdb);
    }

    public final void onNotifyListenerFailed() {
    }
}

