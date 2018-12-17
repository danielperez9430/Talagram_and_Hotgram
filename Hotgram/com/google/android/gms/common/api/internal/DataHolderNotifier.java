package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.data.DataHolder;

@KeepForSdk public abstract class DataHolderNotifier implements Notifier {
    private final DataHolder mDataHolder;

    @KeepForSdk protected DataHolderNotifier(DataHolder arg1) {
        super();
        this.mDataHolder = arg1;
    }

    @KeepForSdk public final void notifyListener(Object arg2) {
        this.notifyListener(arg2, this.mDataHolder);
    }

    @KeepForSdk protected abstract void notifyListener(Object arg1, DataHolder arg2);

    @KeepForSdk public void onNotifyListenerFailed() {
        if(this.mDataHolder != null) {
            this.mDataHolder.close();
        }
    }
}

