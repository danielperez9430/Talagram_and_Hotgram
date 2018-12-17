package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.api.internal.ListenerHolder$Notifier;
import com.google.android.gms.wearable.ChannelApi$ChannelListener;

final class zzhn implements Notifier {
    zzhn(zzaw arg1) {
        this.zzav = arg1;
        super();
    }

    public final void notifyListener(Object arg2) {
        this.zzav.zza(((ChannelListener)arg2));
    }

    public final void onNotifyListenerFailed() {
    }
}

