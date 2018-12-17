package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder;
import com.google.android.gms.wearable.CapabilityApi$CapabilityListener;

final class zzz extends zzn {
    private CapabilityListener zzbs;

    zzz(GoogleApiClient arg1, CapabilityListener arg2, zzp arg3) {
        this(arg1, arg2);
    }

    private zzz(GoogleApiClient arg1, CapabilityListener arg2) {
        super(arg1);
        this.zzbs = arg2;
    }

    public final Result createFailedResult(Status arg2) {
        this.zzbs = null;
        return arg2;
    }

    protected final void doExecute(AnyClient arg2) {
        ((zzhg)arg2).zza(((ResultHolder)this), this.zzbs);
        this.zzbs = null;
    }
}

