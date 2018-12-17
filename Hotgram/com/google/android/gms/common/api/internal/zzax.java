package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks;
import java.util.concurrent.atomic.AtomicReference;

final class zzax implements ConnectionCallbacks {
    zzax(zzav arg1, AtomicReference arg2, StatusPendingResult arg3) {
        this.zzit = arg1;
        this.zziu = arg2;
        this.zziv = arg3;
        super();
    }

    public final void onConnected(Bundle arg4) {
        zzav.zza(this.zzit, this.zziu.get(), this.zziv, true);
    }

    public final void onConnectionSuspended(int arg1) {
    }
}

