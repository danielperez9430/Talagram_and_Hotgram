package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.api.internal.ListenerHolder$Notifier;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.wearable.DataApi$DataListener;
import com.google.android.gms.wearable.DataEventBuffer;

final class zzhl implements Notifier {
    zzhl(DataHolder arg1) {
        this.zzan = arg1;
        super();
    }

    public final void notifyListener(Object arg3) {
        try {
            ((DataListener)arg3).onDataChanged(new DataEventBuffer(this.zzan));
        }
        catch(Throwable v3) {
            this.zzan.close();
            throw v3;
        }

        this.zzan.close();
    }

    public final void onNotifyListenerFailed() {
        this.zzan.close();
    }
}

