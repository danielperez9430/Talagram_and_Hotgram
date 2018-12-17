package com.google.android.gms.wearable.internal;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.common.api.GoogleApi$Settings;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.wearable.NodeApi;
import com.google.android.gms.wearable.NodeClient;

public final class zzfl extends NodeClient {
    private final NodeApi zzem;

    public zzfl(Activity arg1, Settings arg2) {
        super(arg1, arg2);
        this.zzem = new zzfg();
    }

    public zzfl(Context arg1, Settings arg2) {
        super(arg1, arg2);
        this.zzem = new zzfg();
    }

    public final Task getConnectedNodes() {
        return PendingResultUtil.toTask(this.zzem.getConnectedNodes(this.asGoogleApiClient()), zzfn.zzbx);
    }

    public final Task getLocalNode() {
        return PendingResultUtil.toTask(this.zzem.getLocalNode(this.asGoogleApiClient()), zzfm.zzbx);
    }
}

