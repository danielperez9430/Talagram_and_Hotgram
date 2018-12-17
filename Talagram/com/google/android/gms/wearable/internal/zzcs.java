package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.PendingResultUtil$ResultConverter;
import com.google.android.gms.wearable.DataApi$GetFdForAssetResult;

final class zzcs implements ResultConverter {
    static final ResultConverter zzbx;

    static {
        zzcs.zzbx = new zzcs();
    }

    private zzcs() {
        super();
    }

    public final Object convert(Result arg2) {
        return new zzcu(((GetFdForAssetResult)arg2));
    }
}

