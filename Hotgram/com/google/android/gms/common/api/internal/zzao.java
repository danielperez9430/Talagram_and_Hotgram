package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.BaseGmsClient$ConnectionProgressReportCallbacks;
import javax.annotation.concurrent.GuardedBy;

final class zzao extends zzbe {
    zzao(zzam arg1, zzbc arg2, ConnectionProgressReportCallbacks arg3) {
        this.zzia = arg3;
        super(arg2);
    }

    @GuardedBy(value="mLock") public final void zzaq() {
        this.zzia.onReportServiceBinding(new ConnectionResult(16, null));
    }
}

