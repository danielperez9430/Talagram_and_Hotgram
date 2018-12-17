package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.PendingResult$StatusListener;
import com.google.android.gms.common.api.Status;

final class zzab implements StatusListener {
    zzab(zzaa arg1, BasePendingResult arg2) {
        this.zzgz = arg1;
        this.zzgy = arg2;
        super();
    }

    public final void onComplete(Status arg2) {
        zzaa.zza(this.zzgz).remove(this.zzgy);
    }
}

