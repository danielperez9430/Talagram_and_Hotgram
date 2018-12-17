package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.PendingResultUtil$ResultConverter;
import com.google.android.gms.wearable.DataApi$DataItemResult;

final class zzck implements ResultConverter {
    static final ResultConverter zzbx;

    static {
        zzck.zzbx = new zzck();
    }

    private zzck() {
        super();
    }

    public final Object convert(Result arg1) {
        return ((DataItemResult)arg1).getDataItem();
    }
}

