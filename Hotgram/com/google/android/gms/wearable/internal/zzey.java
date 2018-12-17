package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.wearable.MessageApi$SendMessageResult;

public final class zzey implements SendMessageResult {
    private final int zzeh;
    private final Status zzp;

    public zzey(Status arg1, int arg2) {
        super();
        this.zzp = arg1;
        this.zzeh = arg2;
    }

    public final int getRequestId() {
        return this.zzeh;
    }

    public final Status getStatus() {
        return this.zzp;
    }
}

