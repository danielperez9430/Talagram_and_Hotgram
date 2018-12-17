package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.tasks.TaskCompletionSource;

@KeepForSdk public abstract class UnregisterListenerMethod {
    private final ListenerKey zzlj;

    @KeepForSdk protected UnregisterListenerMethod(ListenerKey arg1) {
        super();
        this.zzlj = arg1;
    }

    @KeepForSdk public ListenerKey getListenerKey() {
        return this.zzlj;
    }

    @KeepForSdk protected abstract void unregisterListener(AnyClient arg1, TaskCompletionSource arg2);
}

