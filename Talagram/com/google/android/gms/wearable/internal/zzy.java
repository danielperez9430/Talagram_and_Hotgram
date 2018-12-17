package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.wearable.CapabilityApi$GetCapabilityResult;
import com.google.android.gms.wearable.CapabilityInfo;

public final class zzy implements GetCapabilityResult {
    private final CapabilityInfo zzbv;
    private final Status zzp;

    public zzy(Status arg1, CapabilityInfo arg2) {
        super();
        this.zzp = arg1;
        this.zzbv = arg2;
    }

    public final CapabilityInfo getCapability() {
        return this.zzbv;
    }

    public final Status getStatus() {
        return this.zzp;
    }
}

