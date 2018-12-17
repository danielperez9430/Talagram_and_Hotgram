package com.google.android.gms.location;

import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.common.api.internal.ListenerHolder$ListenerKey;
import com.google.android.gms.common.api.internal.UnregisterListenerMethod;
import com.google.android.gms.internal.location.zzaj;
import com.google.android.gms.internal.location.zzaz;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzo extends UnregisterListenerMethod {
    zzo(FusedLocationProviderClient arg1, ListenerKey arg2) {
        this.zzaa = arg1;
        super(arg2);
    }

    protected final void unregisterListener(AnyClient arg3, TaskCompletionSource arg4) {
        zzaj v0 = FusedLocationProviderClient.zza(this.zzaa, arg4);
        try {
            ((zzaz)arg3).zzb(this.getListenerKey(), v0);
            return;
        }
        catch(RuntimeException v3) {
            arg4.trySetException(((Exception)v3));
            return;
        }
    }
}

