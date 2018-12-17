package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.wearable.DataApi$DeleteDataItemsResult;

public final class zzch implements DeleteDataItemsResult {
    private final int zzdh;
    private final Status zzp;

    public zzch(Status arg1, int arg2) {
        super();
        this.zzp = arg1;
        this.zzdh = arg2;
    }

    public final int getNumDeleted() {
        return this.zzdh;
    }

    public final Status getStatus() {
        return this.zzp;
    }
}

