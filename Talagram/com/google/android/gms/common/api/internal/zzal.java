package com.google.android.gms.common.api.internal;

import android.os.Looper;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.internal.BaseGmsClient$ConnectionProgressReportCallbacks;
import com.google.android.gms.common.internal.Preconditions;
import java.lang.ref.WeakReference;

final class zzal implements ConnectionProgressReportCallbacks {
    private final Api mApi;
    private final boolean zzfo;
    private final WeakReference zzhw;

    public zzal(zzaj arg2, Api arg3, boolean arg4) {
        super();
        this.zzhw = new WeakReference(arg2);
        this.mApi = arg3;
        this.zzfo = arg4;
    }

    public final void onReportServiceBinding(ConnectionResult arg5) {
        Object v0 = this.zzhw.get();
        if(v0 == null) {
            return;
        }

        boolean v1 = Looper.myLooper() == zzaj.zzd(((zzaj)v0)).zzfq.getLooper() ? true : false;
        Preconditions.checkState(v1, "onReportServiceBinding must be called on the GoogleApiClient handler thread");
        zzaj.zzc(((zzaj)v0)).lock();
        try {
            if(zzaj.zza(((zzaj)v0), 0)) {
                if(!arg5.isSuccess()) {
                    zzaj.zza(((zzaj)v0), arg5, this.mApi, this.zzfo);
                }

                if(!zzaj.zzk(((zzaj)v0))) {
                    goto label_19;
                }

                zzaj.zzj(((zzaj)v0));
            }
        }
        catch(Throwable v5) {
            zzaj.zzc(((zzaj)v0)).unlock();
            throw v5;
        }

    label_19:
        zzaj.zzc(((zzaj)v0)).unlock();
    }

    static boolean zza(zzal arg0) {
        return arg0.zzfo;
    }
}

