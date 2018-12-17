package com.google.android.gms.common.internal;

import com.google.android.gms.common.api.Response;
import com.google.android.gms.common.api.Result;

final class zzm implements ResultConverter {
    zzm(Response arg1) {
        this.zzus = arg1;
        super();
    }

    public final Object convert(Result arg2) {
        this.zzus.setResult(arg2);
        return this.zzus;
    }
}

