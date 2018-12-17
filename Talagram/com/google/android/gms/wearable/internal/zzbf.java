package com.google.android.gms.wearable.internal;

import android.content.IntentFilter;
import com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder;
import com.google.android.gms.common.api.internal.ListenerHolder;

final class zzbf implements zzc {
    zzbf(String arg1, IntentFilter[] arg2) {
        this.zzcs = arg1;
        this.zzbr = arg2;
        super();
    }

    public final void zza(zzhg arg7, ResultHolder arg8, Object arg9, ListenerHolder arg10) {
        arg7.zza(arg8, arg9, arg10, this.zzcs, this.zzbr);
    }
}

