package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.PendingResultUtil$ResultConverter;
import com.google.android.gms.wearable.CapabilityApi$GetCapabilityResult;

final class zzab implements ResultConverter {
    static final ResultConverter zzbx;

    static {
        zzab.zzbx = new zzab();
    }

    private zzab() {
        super();
    }

    public final Object convert(Result arg1) {
        return ((GetCapabilityResult)arg1).getCapability();
    }
}

