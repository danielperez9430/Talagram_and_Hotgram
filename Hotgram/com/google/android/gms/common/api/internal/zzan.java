package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.ConnectionResult;
import javax.annotation.concurrent.GuardedBy;

final class zzan extends zzbe {
    zzan(zzam arg1, zzbc arg2, ConnectionResult arg3) {
        this.zzhz = arg1;
        this.zzhy = arg3;
        super(arg2);
    }

    @GuardedBy(value="mLock") public final void zzaq() {
        zzaj.zza(this.zzhz.zzhv, this.zzhy);
    }
}

