package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import android.os.DeadObjectException;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.common.api.Api$Client;
import com.google.android.gms.common.api.Api$SimpleClient;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.SimpleClientAdapter;
import java.util.Iterator;

public final class zzag implements zzbc {
    private final zzbd zzhf;
    private boolean zzhg;

    public zzag(zzbd arg2) {
        super();
        this.zzhg = false;
        this.zzhf = arg2;
    }

    public final void begin() {
    }

    public final void connect() {
        if(this.zzhg) {
            this.zzhg = false;
            this.zzhf.zza(new zzai(this, ((zzbc)this)));
        }
    }

    public final boolean disconnect() {
        if(this.zzhg) {
            return 0;
        }

        if(this.zzhf.zzfq.zzba()) {
            this.zzhg = true;
            Iterator v0 = this.zzhf.zzfq.zziq.iterator();
            while(v0.hasNext()) {
                v0.next().zzcc();
            }

            return 0;
        }

        this.zzhf.zzf(null);
        return 1;
    }

    public final ApiMethodImpl enqueue(ApiMethodImpl arg1) {
        return this.execute(arg1);
    }

    public final ApiMethodImpl execute(ApiMethodImpl arg4) {
        try {
            this.zzhf.zzfq.zzir.zzb(((BasePendingResult)arg4));
            Object v0 = this.zzhf.zzfq.zzil.get(arg4.getClientKey());
            Preconditions.checkNotNull(v0, "Appropriate Api was not requested.");
            if(!((Client)v0).isConnected() && (this.zzhf.zzjb.containsKey(arg4.getClientKey()))) {
                arg4.setFailedResult(new Status(17));
                return arg4;
            }

            if((v0 instanceof SimpleClientAdapter)) {
                SimpleClient v0_1 = ((SimpleClientAdapter)v0).getClient();
            }

            arg4.run(((AnyClient)v0));
        }
        catch(DeadObjectException ) {
            this.zzhf.zza(new zzah(this, ((zzbc)this)));
        }

        return arg4;
    }

    public final void onConnected(Bundle arg1) {
    }

    public final void onConnectionSuspended(int arg3) {
        this.zzhf.zzf(null);
        this.zzhf.zzjf.zzb(arg3, this.zzhg);
    }

    static zzbd zza(zzag arg0) {
        return arg0.zzhf;
    }

    public final void zza(ConnectionResult arg1, Api arg2, boolean arg3) {
    }

    final void zzap() {
        if(this.zzhg) {
            this.zzhg = false;
            this.zzhf.zzfq.zzir.release();
            this.disconnect();
        }
    }
}

