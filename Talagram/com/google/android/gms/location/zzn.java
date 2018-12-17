package com.google.android.gms.location;

import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.RegisterListenerMethod;
import com.google.android.gms.internal.location.zzaz;
import com.google.android.gms.internal.location.zzbd;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzn extends RegisterListenerMethod {
    zzn(FusedLocationProviderClient arg1, ListenerHolder arg2, zzbd arg3, ListenerHolder arg4) {
        this.zzy = arg3;
        this.zzz = arg4;
        super(arg2);
    }

    protected final void registerListener(AnyClient arg3, TaskCompletionSource arg4) {
        ((zzaz)arg3).zza(this.zzy, this.zzz, new zza(arg4));
    }
}

