package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.PendingResultUtil$ResultConverter;
import com.google.android.gms.wearable.DataApi$GetFdForAssetResult;

final class zzcr implements ResultConverter {
    static final ResultConverter zzbx;

    static {
        zzcr.zzbx = new zzcr();
    }

    private zzcr() {
        super();
    }

    public final Object convert(Result arg2) {
        return new zzcu(((GetFdForAssetResult)arg2));
    }
}

