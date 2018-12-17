package com.google.android.gms.wearable.internal;

import android.content.IntentFilter;
import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.RegisterListenerMethod;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.wearable.CapabilityClient$OnCapabilityChangedListener;

final class zzaf extends RegisterListenerMethod {
    private final IntentFilter[] zzba;
    private final OnCapabilityChangedListener zzby;
    private final ListenerHolder zzbz;

    private zzaf(OnCapabilityChangedListener arg1, IntentFilter[] arg2, ListenerHolder arg3) {
        super(arg3);
        this.zzby = arg1;
        this.zzba = arg2;
        this.zzbz = arg3;
    }

    zzaf(OnCapabilityChangedListener arg1, IntentFilter[] arg2, ListenerHolder arg3, zzad arg4) {
        this(arg1, arg2, arg3);
    }

    protected final void registerListener(AnyClient arg4, TaskCompletionSource arg5) {
        ((zzhg)arg4).zza(new zzgh(arg5), this.zzby, this.zzbz, this.zzba);
    }
}

