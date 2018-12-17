package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.wearable.CapabilityApi$GetAllCapabilitiesResult;
import java.util.Map;

public final class zzx implements GetAllCapabilitiesResult {
    private final Map zzbu;
    private final Status zzp;

    public zzx(Status arg1, Map arg2) {
        super();
        this.zzp = arg1;
        this.zzbu = arg2;
    }

    public final Map getAllCapabilities() {
        return this.zzbu;
    }

    public final Status getStatus() {
        return this.zzp;
    }
}

