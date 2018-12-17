package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.tasks.TaskCompletionSource;

@KeepForSdk public abstract class RegisterListenerMethod {
    private final ListenerHolder zzls;

    @KeepForSdk protected RegisterListenerMethod(ListenerHolder arg1) {
        super();
        this.zzls = arg1;
    }

    @KeepForSdk public void clearListener() {
        this.zzls.clear();
    }

    @KeepForSdk public ListenerKey getListenerKey() {
        return this.zzls.getListenerKey();
    }

    @KeepForSdk protected abstract void registerListener(AnyClient arg1, TaskCompletionSource arg2);
}

