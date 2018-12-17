package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.ConnectionResult;

final class zzbk implements Runnable {
    zzbk(zza arg1, ConnectionResult arg2) {
        this.zzkk = arg1;
        this.zzkl = arg2;
        super();
    }

    public final void run() {
        this.zzkk.onConnectionFailed(this.zzkl);
    }
}

