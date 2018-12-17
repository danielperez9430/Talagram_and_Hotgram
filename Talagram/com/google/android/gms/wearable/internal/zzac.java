package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.PendingResultUtil$ResultConverter;
import com.google.android.gms.wearable.CapabilityApi$GetAllCapabilitiesResult;

final class zzac implements ResultConverter {
    static final ResultConverter zzbx;

    static {
        zzac.zzbx = new zzac();
    }

    private zzac() {
        super();
    }

    public final Object convert(Result arg1) {
        return ((GetAllCapabilitiesResult)arg1).getAllCapabilities();
    }
}

