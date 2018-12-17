package com.google.android.gms.wearable.internal;

import android.content.IntentFilter;
import com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.wearable.CapabilityApi$CapabilityListener;

final class zzt implements zzc {
    zzt(IntentFilter[] arg1) {
        this.zzbr = arg1;
        super();
    }

    public final void zza(zzhg arg2, ResultHolder arg3, Object arg4, ListenerHolder arg5) {
        arg2.zza(arg3, ((CapabilityListener)arg4), arg5, this.zzbr);
    }
}

