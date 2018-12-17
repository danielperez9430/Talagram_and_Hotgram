package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.PendingResultUtil$ResultConverter;
import com.google.android.gms.wearable.MessageApi$SendMessageResult;

final class zzfa implements ResultConverter {
    static final ResultConverter zzbx;

    static {
        zzfa.zzbx = new zzfa();
    }

    private zzfa() {
        super();
    }

    public final Object convert(Result arg1) {
        return Integer.valueOf(((SendMessageResult)arg1).getRequestId());
    }
}

