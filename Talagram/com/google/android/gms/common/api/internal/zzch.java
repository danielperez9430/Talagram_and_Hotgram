package com.google.android.gms.common.api.internal;

import android.os.Looper;
import android.util.Log;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.ResultCallbacks;
import com.google.android.gms.common.api.ResultTransform;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.TransformedResult;
import com.google.android.gms.common.internal.Preconditions;
import java.lang.ref.WeakReference;
import javax.annotation.concurrent.GuardedBy;

public final class zzch extends TransformedResult implements ResultCallback {
    private final Object zzfa;
    private final WeakReference zzfc;
    private ResultTransform zzmd;
    private zzch zzme;
    private volatile ResultCallbacks zzmf;
    private PendingResult zzmg;
    private Status zzmh;
    private final zzcj zzmi;
    private boolean zzmj;

    public zzch(WeakReference arg3) {
        super();
        this.zzmd = null;
        this.zzme = null;
        this.zzmf = null;
        this.zzmg = null;
        this.zzfa = new Object();
        this.zzmh = null;
        this.zzmj = false;
        Preconditions.checkNotNull(arg3, "GoogleApiClient reference must not be null");
        this.zzfc = arg3;
        Object v3 = this.zzfc.get();
        Looper v3_1 = v3 != null ? ((GoogleApiClient)v3).getLooper() : Looper.getMainLooper();
        this.zzmi = new zzcj(this, v3_1);
    }

    public final void andFinally(ResultCallbacks arg6) {
        Object v0 = this.zzfa;
        __monitor_enter(v0);
        try {
            boolean v2 = false;
            boolean v1 = this.zzmf == null ? true : false;
            Preconditions.checkState(v1, "Cannot call andFinally() twice.");
            if(this.zzmd == null) {
                v2 = true;
            }

            Preconditions.checkState(v2, "Cannot call then() and andFinally() on the same TransformedResult.");
            this.zzmf = arg6;
            this.zzcb();
            __monitor_exit(v0);
            return;
        label_21:
            __monitor_exit(v0);
        }
        catch(Throwable v6) {
            goto label_21;
        }

        throw v6;
    }

    public final void onResult(Result arg4) {
        Object v0 = this.zzfa;
        __monitor_enter(v0);
        try {
            if(!arg4.getStatus().isSuccess()) {
                this.zzd(arg4.getStatus());
                zzch.zzb(arg4);
            }
            else if(this.zzmd != null) {
                zzbw.zzbe().submit(new zzci(this, arg4));
            }
            else if(this.zzcd()) {
                this.zzmf.onSuccess(arg4);
            }

            __monitor_exit(v0);
            return;
        label_23:
            __monitor_exit(v0);
        }
        catch(Throwable v4) {
            goto label_23;
        }

        throw v4;
    }

    public final TransformedResult then(ResultTransform arg6) {
        Object v0 = this.zzfa;
        __monitor_enter(v0);
        try {
            boolean v2 = false;
            boolean v1 = this.zzmd == null ? true : false;
            Preconditions.checkState(v1, "Cannot call then() twice.");
            if(this.zzmf == null) {
                v2 = true;
            }

            Preconditions.checkState(v2, "Cannot call then() and andFinally() on the same TransformedResult.");
            this.zzmd = arg6;
            zzch v6_1 = new zzch(this.zzfc);
            this.zzme = v6_1;
            this.zzcb();
            __monitor_exit(v0);
            return ((TransformedResult)v6_1);
        label_25:
            __monitor_exit(v0);
        }
        catch(Throwable v6) {
            goto label_25;
        }

        throw v6;
    }

    static void zza(zzch arg0, Result arg1) {
        zzch.zzb(arg1);
    }

    static void zza(zzch arg0, Status arg1) {
        arg0.zzd(arg1);
    }

    public final void zza(PendingResult arg2) {
        Object v0 = this.zzfa;
        __monitor_enter(v0);
        try {
            this.zzmg = arg2;
            this.zzcb();
            __monitor_exit(v0);
            return;
        label_7:
            __monitor_exit(v0);
        }
        catch(Throwable v2) {
            goto label_7;
        }

        throw v2;
    }

    private static void zzb(Result arg4) {
        if((arg4 instanceof Releasable)) {
            try {
                arg4.release();
                return;
            }
            catch(RuntimeException v0) {
                String v4 = String.valueOf(arg4);
                StringBuilder v3 = new StringBuilder(String.valueOf(v4).length() + 18);
                v3.append("Unable to release ");
                v3.append(v4);
                Log.w("TransformedResultImpl", v3.toString(), ((Throwable)v0));
            }
        }
    }

    static ResultTransform zzc(zzch arg0) {
        return arg0.zzmd;
    }

    @GuardedBy(value="mSyncToken") private final void zzcb() {
        if(this.zzmd == null && this.zzmf == null) {
            return;
        }

        Object v0 = this.zzfc.get();
        if(!this.zzmj && this.zzmd != null && v0 != null) {
            ((GoogleApiClient)v0).zza(this);
            this.zzmj = true;
        }

        if(this.zzmh != null) {
            this.zze(this.zzmh);
            return;
        }

        if(this.zzmg != null) {
            this.zzmg.setResultCallback(((ResultCallback)this));
        }
    }

    final void zzcc() {
        this.zzmf = null;
    }

    @GuardedBy(value="mSyncToken") private final boolean zzcd() {
        Object v0 = this.zzfc.get();
        if(this.zzmf != null && v0 != null) {
            return 1;
        }

        return 0;
    }

    static zzcj zzd(zzch arg0) {
        return arg0.zzmi;
    }

    private final void zzd(Status arg2) {
        Object v0 = this.zzfa;
        __monitor_enter(v0);
        try {
            this.zzmh = arg2;
            this.zze(this.zzmh);
            __monitor_exit(v0);
            return;
        label_8:
            __monitor_exit(v0);
        }
        catch(Throwable v2) {
            goto label_8;
        }

        throw v2;
    }

    static WeakReference zze(zzch arg0) {
        return arg0.zzfc;
    }

    private final void zze(Status arg3) {
        Object v0 = this.zzfa;
        __monitor_enter(v0);
        try {
            if(this.zzmd != null) {
                arg3 = this.zzmd.onFailure(arg3);
                Preconditions.checkNotNull(arg3, "onFailure must not return null");
                this.zzme.zzd(arg3);
            }
            else if(this.zzcd()) {
                this.zzmf.onFailure(arg3);
            }

            __monitor_exit(v0);
            return;
        label_18:
            __monitor_exit(v0);
        }
        catch(Throwable v3) {
            goto label_18;
        }

        throw v3;
    }

    static Object zzf(zzch arg0) {
        return arg0.zzfa;
    }

    static zzch zzg(zzch arg0) {
        return arg0.zzme;
    }
}

