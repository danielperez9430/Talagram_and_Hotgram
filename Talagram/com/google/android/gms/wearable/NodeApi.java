package com.google.android.gms.wearable;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import java.util.List;

@Deprecated public interface NodeApi {
    @Deprecated public interface GetConnectedNodesResult extends Result {
        List getNodes();
    }

    @Deprecated public interface GetLocalNodeResult extends Result {
        Node getNode();
    }

    PendingResult getConnectedNodes(GoogleApiClient arg1);

    PendingResult getLocalNode(GoogleApiClient arg1);
}

