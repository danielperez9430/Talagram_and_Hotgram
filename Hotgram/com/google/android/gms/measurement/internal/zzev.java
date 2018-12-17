package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;

final class zzev {
    private long startTime;
    private final Clock zzrz;

    public zzev(Clock arg1) {
        super();
        Preconditions.checkNotNull(arg1);
        this.zzrz = arg1;
    }

    public final void clear() {
        this.startTime = 0;
    }

    public final void start() {
        this.startTime = this.zzrz.elapsedRealtime();
    }

    public final boolean zzj(long arg5) {
        // Method was not decompiled
    }
}

