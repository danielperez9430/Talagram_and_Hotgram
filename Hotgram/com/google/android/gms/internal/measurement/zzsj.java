package com.google.android.gms.internal.measurement;

import android.database.ContentObserver;
import android.os.Handler;

final class zzsj extends ContentObserver {
    zzsj(zzsi arg1, Handler arg2) {
        this.zzbqx = arg1;
        super(null);
    }

    public final void onChange(boolean arg1) {
        this.zzbqx.zzta();
        zzsi.zza(this.zzbqx);
    }
}

