package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.PendingResultUtil$ResultConverter;
import com.google.android.gms.wearable.NodeApi$GetConnectedNodesResult;

final class zzfn implements ResultConverter {
    static final ResultConverter zzbx;

    static {
        zzfn.zzbx = new zzfn();
    }

    private zzfn() {
        super();
    }

    public final Object convert(Result arg1) {
        return ((GetConnectedNodesResult)arg1).getNodes();
    }
}

