package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;
import java.util.Map;

final class zzaw implements Runnable {
    private final String packageName;
    private final int status;
    private final zzav zzamr;
    private final Throwable zzams;
    private final byte[] zzamt;
    private final Map zzamu;

    private zzaw(String arg1, zzav arg2, int arg3, Throwable arg4, byte[] arg5, Map arg6) {
        super();
        Preconditions.checkNotNull(arg2);
        this.zzamr = arg2;
        this.status = arg3;
        this.zzams = arg4;
        this.zzamt = arg5;
        this.packageName = arg1;
        this.zzamu = arg6;
    }

    zzaw(String arg1, zzav arg2, int arg3, Throwable arg4, byte[] arg5, Map arg6, zzau arg7) {
        this(arg1, arg2, arg3, arg4, arg5, arg6);
    }

    public final void run() {
        this.zzamr.zza(this.packageName, this.status, this.zzams, this.zzamt, this.zzamu);
    }
}

