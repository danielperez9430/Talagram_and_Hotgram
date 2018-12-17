package com.google.android.gms.common.api.internal;

import com.google.android.gms.tasks.TaskCompletionSource;

final class zzae {
    private final zzh zzhc;
    private final TaskCompletionSource zzhd;

    public zzae(zzh arg2) {
        super();
        this.zzhd = new TaskCompletionSource();
        this.zzhc = arg2;
    }

    public final TaskCompletionSource zzao() {
        return this.zzhd;
    }

    public final zzh zzm() {
        return this.zzhc;
    }
}

