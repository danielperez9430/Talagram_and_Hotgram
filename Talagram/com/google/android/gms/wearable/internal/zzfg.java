package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.wearable.NodeApi;

public final class zzfg implements NodeApi {
    public zzfg() {
        super();
    }

    public final PendingResult getConnectedNodes(GoogleApiClient arg2) {
        return arg2.enqueue(new zzfi(this, arg2));
    }

    public final PendingResult getLocalNode(GoogleApiClient arg2) {
        return arg2.enqueue(new zzfh(this, arg2));
    }
}

