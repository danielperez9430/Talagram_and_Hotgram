package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.wearable.DataApi$DataItemResult;
import com.google.android.gms.wearable.DataItem;

public final class zzcg implements DataItemResult {
    private final DataItem zzdg;
    private final Status zzp;

    public zzcg(Status arg1, DataItem arg2) {
        super();
        this.zzp = arg1;
        this.zzdg = arg2;
    }

    public final DataItem getDataItem() {
        return this.zzdg;
    }

    public final Status getStatus() {
        return this.zzp;
    }
}

