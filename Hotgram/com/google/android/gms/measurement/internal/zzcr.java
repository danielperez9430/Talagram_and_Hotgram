package com.google.android.gms.measurement.internal;

import android.content.Context;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;

@VisibleForTesting public final class zzcr {
    boolean zzadv;
    String zzadx;
    String zzapm;
    String zzapn;
    Boolean zzaqg;
    final Context zzri;

    @VisibleForTesting public zzcr(Context arg2, zzak arg3) {
        super();
        this.zzadv = true;
        Preconditions.checkNotNull(arg2);
        arg2 = arg2.getApplicationContext();
        Preconditions.checkNotNull(arg2);
        this.zzri = arg2;
        if(arg3 != null) {
            this.zzadx = arg3.zzadx;
            this.zzapm = arg3.origin;
            this.zzapn = arg3.zzadw;
            this.zzadv = arg3.zzadv;
            if(arg3.zzady != null) {
                this.zzaqg = Boolean.valueOf(arg3.zzady.getBoolean("dataCollectionDefaultEnabled", true));
            }
        }
    }
}

