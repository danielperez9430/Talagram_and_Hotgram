package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;

final class zzfj {
    final String name;
    final String origin;
    final Object value;
    final long zzaue;
    final String zztt;

    zzfj(String arg1, String arg2, String arg3, long arg4, Object arg6) {
        super();
        Preconditions.checkNotEmpty(arg1);
        Preconditions.checkNotEmpty(arg3);
        Preconditions.checkNotNull(arg6);
        this.zztt = arg1;
        this.origin = arg2;
        this.name = arg3;
        this.zzaue = arg4;
        this.value = arg6;
    }
}

