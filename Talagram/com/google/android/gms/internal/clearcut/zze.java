package com.google.android.gms.internal.clearcut;

import android.content.Context;
import com.google.android.gms.clearcut.ClearcutLogger;
import com.google.android.gms.clearcut.zzb;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.internal.ApiExceptionMapper;
import com.google.android.gms.common.util.VisibleForTesting;

public final class zze extends GoogleApi implements zzb {
    @VisibleForTesting private zze(Context arg4) {
        super(arg4, ClearcutLogger.API, null, new ApiExceptionMapper());
    }

    public static zzb zzb(Context arg1) {
        return new zze(arg1);
    }

    public final PendingResult zzb(com.google.android.gms.clearcut.zze arg3) {
        return this.doBestEffortWrite(new zzh(arg3, this.asGoogleApiClient()));
    }
}

