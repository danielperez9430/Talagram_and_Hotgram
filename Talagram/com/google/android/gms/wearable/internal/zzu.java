package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.wearable.CapabilityApi$AddLocalCapabilityResult;
import com.google.android.gms.wearable.CapabilityApi$RemoveLocalCapabilityResult;

public final class zzu implements AddLocalCapabilityResult, RemoveLocalCapabilityResult {
    private final Status zzp;

    public zzu(Status arg1) {
        super();
        this.zzp = arg1;
    }

    public final Status getStatus() {
        return this.zzp;
    }
}

