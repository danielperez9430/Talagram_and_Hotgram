package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.PendingResultUtil$ResultConverter;
import com.google.android.gms.wearable.DataApi$DeleteDataItemsResult;

final class zzcq implements ResultConverter {
    static final ResultConverter zzbx;

    static {
        zzcq.zzbx = new zzcq();
    }

    private zzcq() {
        super();
    }

    public final Object convert(Result arg1) {
        return Integer.valueOf(((DeleteDataItemsResult)arg1).getNumDeleted());
    }
}

