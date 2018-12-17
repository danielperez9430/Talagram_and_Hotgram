package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener;
import com.google.android.gms.common.api.Status;

final class zzay implements OnConnectionFailedListener {
    zzay(zzav arg1, StatusPendingResult arg2) {
        this.zziv = arg2;
        super();
    }

    public final void onConnectionFailed(ConnectionResult arg3) {
        this.zziv.setResult(new Status(8));
    }
}

