package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.PendingResultUtil$ResultConverter;
import com.google.android.gms.wearable.NodeApi$GetLocalNodeResult;

final class zzfm implements ResultConverter {
    static final ResultConverter zzbx;

    static {
        zzfm.zzbx = new zzfm();
    }

    private zzfm() {
        super();
    }

    public final Object convert(Result arg1) {
        return ((GetLocalNodeResult)arg1).getNode();
    }
}

