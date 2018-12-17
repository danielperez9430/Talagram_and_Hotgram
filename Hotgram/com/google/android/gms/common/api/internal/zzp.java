package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener;
import com.google.android.gms.common.internal.Preconditions;

public final class zzp implements ConnectionCallbacks, OnConnectionFailedListener {
    public final Api mApi;
    private final boolean zzfo;
    private zzq zzfp;

    public zzp(Api arg1, boolean arg2) {
        super();
        this.mApi = arg1;
        this.zzfo = arg2;
    }

    public final void onConnected(Bundle arg2) {
        this.zzy();
        this.zzfp.onConnected(arg2);
    }

    public final void onConnectionFailed(ConnectionResult arg4) {
        this.zzy();
        this.zzfp.zza(arg4, this.mApi, this.zzfo);
    }

    public final void onConnectionSuspended(int arg2) {
        this.zzy();
        this.zzfp.onConnectionSuspended(arg2);
    }

    public final void zza(zzq arg1) {
        this.zzfp = arg1;
    }

    private final void zzy() {
        Preconditions.checkNotNull(this.zzfp, "Callbacks must be attached to a ClientConnectionHelper instance before connecting the client.");
    }
}

