package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import java.util.Collections;
import java.util.Iterator;

public final class zzau implements zzbc {
    private final zzbd zzhf;

    public zzau(zzbd arg1) {
        super();
        this.zzhf = arg1;
    }

    public final void begin() {
        Iterator v0 = this.zzhf.zzil.values().iterator();
        while(v0.hasNext()) {
            v0.next().disconnect();
        }

        this.zzhf.zzfq.zzim = Collections.emptySet();
    }

    public final void connect() {
        this.zzhf.zzbc();
    }

    public final boolean disconnect() {
        return 1;
    }

    public final ApiMethodImpl enqueue(ApiMethodImpl arg2) {
        this.zzhf.zzfq.zzgo.add(arg2);
        return arg2;
    }

    public final ApiMethodImpl execute(ApiMethodImpl arg2) {
        throw new IllegalStateException("GoogleApiClient is not connected yet.");
    }

    public final void onConnected(Bundle arg1) {
    }

    public final void onConnectionSuspended(int arg1) {
    }

    public final void zza(ConnectionResult arg1, Api arg2, boolean arg3) {
    }
}

