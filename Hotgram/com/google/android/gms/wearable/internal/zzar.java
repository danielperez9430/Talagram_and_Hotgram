package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.PendingResultUtil$ResultConverter;
import com.google.android.gms.wearable.Channel$GetOutputStreamResult;

final class zzar implements ResultConverter {
    static final ResultConverter zzbx;

    static {
        zzar.zzbx = new zzar();
    }

    private zzar() {
        super();
    }

    public final Object convert(Result arg1) {
        return ((GetOutputStreamResult)arg1).getOutputStream();
    }
}

