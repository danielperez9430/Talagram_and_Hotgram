package com.google.android.gms.common.internal;

import android.os.Bundle;
import com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks;

final class zzf implements BaseConnectionCallbacks {
    zzf(ConnectionCallbacks arg1) {
        this.zztd = arg1;
        super();
    }

    public final void onConnected(Bundle arg2) {
        this.zztd.onConnected(arg2);
    }

    public final void onConnectionSuspended(int arg2) {
        this.zztd.onConnectionSuspended(arg2);
    }
}

