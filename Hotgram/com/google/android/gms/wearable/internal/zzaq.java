package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.PendingResultUtil$ResultConverter;
import com.google.android.gms.wearable.Channel$GetInputStreamResult;

final class zzaq implements ResultConverter {
    static final ResultConverter zzbx;

    static {
        zzaq.zzbx = new zzaq();
    }

    private zzaq() {
        super();
    }

    public final Object convert(Result arg1) {
        return ((GetInputStreamResult)arg1).getInputStream();
    }
}

