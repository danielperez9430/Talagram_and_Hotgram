package com.google.android.gms.internal.location;

import android.location.Location;
import com.google.android.gms.common.api.internal.ListenerHolder$Notifier;
import com.google.android.gms.location.LocationListener;

final class zzay implements Notifier {
    zzay(zzax arg1, Location arg2) {
        this.zzdd = arg2;
        super();
    }

    public final void notifyListener(Object arg2) {
        ((LocationListener)arg2).onLocationChanged(this.zzdd);
    }

    public final void onNotifyListenerFailed() {
    }
}

