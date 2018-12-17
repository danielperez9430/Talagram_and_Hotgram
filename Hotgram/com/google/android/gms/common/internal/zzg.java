package com.google.android.gms.common.internal;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener;

final class zzg implements BaseOnConnectionFailedListener {
    zzg(OnConnectionFailedListener arg1) {
        this.zzte = arg1;
        super();
    }

    public final void onConnectionFailed(ConnectionResult arg2) {
        this.zzte.onConnectionFailed(arg2);
    }
}

