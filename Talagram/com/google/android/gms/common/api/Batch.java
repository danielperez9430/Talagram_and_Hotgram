package com.google.android.gms.common.api;

import com.google.android.gms.common.api.internal.BasePendingResult;
import java.util.ArrayList;
import java.util.List;

public final class Batch extends BasePendingResult {
    public final class Builder {
        private List zzci;
        private GoogleApiClient zzcj;

        public Builder(GoogleApiClient arg2) {
            super();
            this.zzci = new ArrayList();
            this.zzcj = arg2;
        }

        public final BatchResultToken add(PendingResult arg3) {
            BatchResultToken v0 = new BatchResultToken(this.zzci.size());
            this.zzci.add(arg3);
            return v0;
        }

        public final Batch build() {
            return new Batch(this.zzci, this.zzcj, null);
        }
    }

    private final Object mLock;
    private int zzcd;
    private boolean zzce;
    private boolean zzcf;
    private final PendingResult[] zzcg;

    private Batch(List arg3, GoogleApiClient arg4) {
        super(arg4);
        this.mLock = new Object();
        this.zzcd = arg3.size();
        this.zzcg = new PendingResult[this.zzcd];
        if(arg3.isEmpty()) {
            ((BasePendingResult)this).setResult(new BatchResult(Status.RESULT_SUCCESS, this.zzcg));
            return;
        }

        int v4;
        for(v4 = 0; v4 < arg3.size(); ++v4) {
            Object v0 = arg3.get(v4);
            this.zzcg[v4] = v0;
            ((PendingResult)v0).addStatusListener(new zza(this));
        }
    }

    Batch(List arg1, GoogleApiClient arg2, zza arg3) {
        this(arg1, arg2);
    }

    public final void cancel() {
        super.cancel();
        PendingResult[] v0 = this.zzcg;
        int v1 = v0.length;
        int v2;
        for(v2 = 0; v2 < v1; ++v2) {
            v0[v2].cancel();
        }
    }

    public final BatchResult createFailedResult(Status arg3) {
        return new BatchResult(arg3, this.zzcg);
    }

    public final Result createFailedResult(Status arg1) {
        return this.createFailedResult(arg1);
    }

    static Object zza(Batch arg0) {
        return arg0.mLock;
    }

    static boolean zza(Batch arg0, boolean arg1) {
        arg0.zzcf = true;
        return 1;
    }

    static boolean zzb(Batch arg0, boolean arg1) {
        arg0.zzce = true;
        return 1;
    }

    static int zzb(Batch arg2) {
        int v0 = arg2.zzcd;
        arg2.zzcd = v0 - 1;
        return v0;
    }

    static int zzc(Batch arg0) {
        return arg0.zzcd;
    }

    static boolean zzd(Batch arg0) {
        return arg0.zzcf;
    }

    static void zze(Batch arg0) {
        super.cancel();
    }

    static boolean zzf(Batch arg0) {
        return arg0.zzce;
    }

    static PendingResult[] zzg(Batch arg0) {
        return arg0.zzcg;
    }
}

