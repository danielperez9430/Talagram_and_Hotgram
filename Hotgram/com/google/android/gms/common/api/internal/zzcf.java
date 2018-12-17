package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzcf extends TaskApiCall {
    zzcf(Builder arg1, Feature[] arg2, boolean arg3) {
        this.zzmc = arg1;
        super(arg2, arg3, null);
    }

    protected final void doExecute(AnyClient arg2, TaskCompletionSource arg3) {
        Builder.zza(this.zzmc).accept(arg2, arg3);
    }
}

