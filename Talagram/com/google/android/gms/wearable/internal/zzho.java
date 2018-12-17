package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.api.internal.ListenerHolder$Notifier;
import com.google.android.gms.wearable.CapabilityApi$CapabilityListener;

final class zzho implements Notifier {
    zzho(zzah arg1) {
        this.zzfr = arg1;
        super();
    }

    public final void notifyListener(Object arg2) {
        ((CapabilityListener)arg2).onCapabilityChanged(this.zzfr);
    }

    public final void onNotifyListenerFailed() {
    }
}

