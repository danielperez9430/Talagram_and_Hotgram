package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.Preconditions;

final class zzl {
    private final int zzet;
    private final ConnectionResult zzeu;

    zzl(ConnectionResult arg1, int arg2) {
        super();
        Preconditions.checkNotNull(arg1);
        this.zzeu = arg1;
        this.zzet = arg2;
    }

    final ConnectionResult getConnectionResult() {
        return this.zzeu;
    }

    final int zzu() {
        return this.zzet;
    }
}

