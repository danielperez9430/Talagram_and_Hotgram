package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.common.api.internal.ListenerHolder$ListenerKey;
import com.google.android.gms.common.api.internal.UnregisterListenerMethod;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.wearable.CapabilityClient$OnCapabilityChangedListener;

final class zzag extends UnregisterListenerMethod {
    private final OnCapabilityChangedListener zzby;

    private zzag(OnCapabilityChangedListener arg1, ListenerKey arg2) {
        super(arg2);
        this.zzby = arg1;
    }

    zzag(OnCapabilityChangedListener arg1, ListenerKey arg2, zzad arg3) {
        this(arg1, arg2);
    }

    protected final void unregisterListener(AnyClient arg2, TaskCompletionSource arg3) {
        ((zzhg)arg2).zza(new zzgg(arg3), this.zzby);
    }
}

