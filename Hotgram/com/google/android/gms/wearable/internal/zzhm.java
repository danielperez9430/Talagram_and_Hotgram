package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.api.internal.ListenerHolder$Notifier;
import com.google.android.gms.wearable.MessageApi$MessageListener;

final class zzhm implements Notifier {
    zzhm(zzfe arg1) {
        this.zzap = arg1;
        super();
    }

    public final void notifyListener(Object arg2) {
        ((MessageListener)arg2).onMessageReceived(this.zzap);
    }

    public final void onNotifyListenerFailed() {
    }
}

