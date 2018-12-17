package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.wearable.NodeApi$GetConnectedNodesResult;
import java.util.List;

public final class zzfj implements GetConnectedNodesResult {
    private final List zzdx;
    private final Status zzp;

    public zzfj(Status arg1, List arg2) {
        super();
        this.zzp = arg1;
        this.zzdx = arg2;
    }

    public final List getNodes() {
        return this.zzdx;
    }

    public final Status getStatus() {
        return this.zzp;
    }
}

