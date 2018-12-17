package com.google.android.gms.wearable;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.common.api.GoogleApi$Settings;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.tasks.Task;

public abstract class NodeClient extends GoogleApi {
    public NodeClient(Activity arg3, Settings arg4) {
        super(arg3, Wearable.API, null, arg4);
    }

    public NodeClient(Context arg3, Settings arg4) {
        super(arg3, Wearable.API, null, arg4);
    }

    public abstract Task getConnectedNodes();

    public abstract Task getLocalNode();
}

