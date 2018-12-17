package com.google.android.gms.wearable;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.EntityBuffer;
import com.google.android.gms.wearable.internal.zzcy;

public class DataEventBuffer extends EntityBuffer implements Result {
    private final Status zzp;

    public DataEventBuffer(DataHolder arg2) {
        super(arg2);
        this.zzp = new Status(arg2.getStatusCode());
    }

    protected Object getEntry(int arg3, int arg4) {
        return new zzcy(this.mDataHolder, arg3, arg4);
    }

    protected String getPrimaryDataMarkerColumn() {
        return "path";
    }

    public Status getStatus() {
        return this.zzp;
    }
}

