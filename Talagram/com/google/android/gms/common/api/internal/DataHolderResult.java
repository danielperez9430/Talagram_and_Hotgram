package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;

@KeepForSdk public class DataHolderResult implements Releasable, Result {
    @KeepForSdk protected final DataHolder mDataHolder;
    @KeepForSdk protected final Status mStatus;

    @KeepForSdk protected DataHolderResult(DataHolder arg3) {
        this(arg3, new Status(arg3.getStatusCode()));
    }

    @KeepForSdk protected DataHolderResult(DataHolder arg1, Status arg2) {
        super();
        this.mStatus = arg2;
        this.mDataHolder = arg1;
    }

    @KeepForSdk public Status getStatus() {
        return this.mStatus;
    }

    @KeepForSdk public void release() {
        if(this.mDataHolder != null) {
            this.mDataHolder.close();
        }
    }
}

