package com.google.android.gms.measurement.internal;

import android.content.Context;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;

@VisibleForTesting public final class zzff {
    final Context zzri;

    @VisibleForTesting public zzff(Context arg1) {
        super();
        Preconditions.checkNotNull(arg1);
        arg1 = arg1.getApplicationContext();
        Preconditions.checkNotNull(arg1);
        this.zzri = arg1;
    }
}

