package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.PendingResultUtil$ResultConverter;
import com.google.android.gms.wearable.ChannelApi$OpenChannelResult;

final class zzap implements ResultConverter {
    static final ResultConverter zzbx;

    static {
        zzap.zzbx = new zzap();
    }

    private zzap() {
        super();
    }

    public final Object convert(Result arg1) {
        return zzao.zza(((OpenChannelResult)arg1));
    }
}

