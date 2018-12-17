package com.google.android.gms.common.api;

import android.support.v4.f.a;
import android.text.TextUtils;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.internal.zzh;
import com.google.android.gms.common.internal.Preconditions;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AvailabilityException extends Exception {
    private final a zzcc;

    public AvailabilityException(a arg1) {
        super();
        this.zzcc = arg1;
    }

    public ConnectionResult getConnectionResult(GoogleApi arg3) {
        zzh v3 = arg3.zzm();
        boolean v0 = this.zzcc.get(v3) != null ? true : false;
        Preconditions.checkArgument(v0, "The given API was not part of the availability request.");
        return this.zzcc.get(v3);
    }

    public String getMessage() {
        ArrayList v0 = new ArrayList();
        Iterator v1 = this.zzcc.keySet().iterator();
        int v2 = 1;
        while(v1.hasNext()) {
            Object v3 = v1.next();
            Object v4 = this.zzcc.get(v3);
            if(((ConnectionResult)v4).isSuccess()) {
                v2 = 0;
            }

            String v3_1 = ((zzh)v3).zzq();
            String v4_1 = String.valueOf(v4);
            StringBuilder v6 = new StringBuilder(String.valueOf(v3_1).length() + 2 + String.valueOf(v4_1).length());
            v6.append(v3_1);
            v6.append(": ");
            v6.append(v4_1);
            ((List)v0).add(v6.toString());
        }

        StringBuilder v1_1 = new StringBuilder();
        String v2_1 = v2 != 0 ? "None of the queried APIs are available. " : "Some of the queried APIs are unavailable. ";
        v1_1.append(v2_1);
        v1_1.append(TextUtils.join("; ", ((Iterable)v0)));
        return v1_1.toString();
    }

    public final a zzl() {
        return this.zzcc;
    }
}

