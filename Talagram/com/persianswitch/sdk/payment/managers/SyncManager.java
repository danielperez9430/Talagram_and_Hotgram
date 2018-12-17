package com.persianswitch.sdk.payment.managers;

import android.content.Context;
import com.persianswitch.sdk.payment.model.SyncType;
import com.persianswitch.sdk.payment.model.SyncableData;
import com.persianswitch.sdk.payment.model.req.ClientSyncRequest$SyncVersion;
import com.persianswitch.sdk.payment.model.req.ClientSyncRequest;
import com.persianswitch.sdk.payment.model.res.ClientSyncResponse$SyncData;
import com.persianswitch.sdk.payment.model.res.ClientSyncResponse;
import com.persianswitch.sdk.payment.repo.SyncRepo;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class SyncManager {
    private final Context a;

    public SyncManager(Context arg1) {
        super();
        this.a = arg1;
    }

    public ClientSyncRequest a(String arg2) {
        return new ClientSyncRequest(this.b(arg2));
    }

    public boolean a(String arg5, String arg6) {
        ClientSyncResponse v5 = ClientSyncResponse.a(arg5);
        SyncRepo v0 = new SyncRepo(this.a);
        if(v5 != null) {
            List v5_1 = v5.a();
            if(v5_1 != null) {
                Iterator v5_2 = v5_1.iterator();
                while(v5_2.hasNext()) {
                    Object v1 = v5_2.next();
                    SyncableData v2 = new SyncableData();
                    v2.a(SyncType.a(((SyncData)v1).a()));
                    v2.b(((SyncData)v1).b());
                    v2.c(((SyncData)v1).c());
                    v2.a(arg6);
                    v0.a(v2);
                }
            }

            return 1;
        }

        return 0;
    }

    private ArrayList b(String arg9) {
        ArrayList v0 = new ArrayList(SyncType.c.length);
        SyncRepo v1 = new SyncRepo(this.a);
        SyncType[] v2 = SyncType.c;
        int v3 = v2.length;
        int v4;
        for(v4 = 0; v4 < v3; ++v4) {
            SyncType v5 = v2[v4];
            SyncableData v6 = v1.a(v5, arg9);
            if(v6 != null) {
                v0.add(new SyncVersion(v5.d(), v6.e()));
            }
            else {
                v0.add(new SyncVersion(v5.d(), "0.0.0"));
            }
        }

        return v0;
    }
}

