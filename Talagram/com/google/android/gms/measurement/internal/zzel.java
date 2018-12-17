package com.google.android.gms.measurement.internal;

import android.annotation.TargetApi;
import android.app.job.JobParameters;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import com.google.android.gms.common.internal.Preconditions;

public final class zzel {
    private final Context zzaby;

    public zzel(Context arg1) {
        super();
        Preconditions.checkNotNull(arg1);
        this.zzaby = arg1;
    }

    public final IBinder onBind(Intent arg4) {
        IBinder v0 = null;
        if(arg4 == null) {
            this.zzgo().zzjd().zzbx("onBind called with null intent");
            return v0;
        }

        String v4 = arg4.getAction();
        if("com.google.android.gms.measurement.START".equals(v4)) {
            return new zzbv(zzfa.zzm(this.zzaby));
        }

        this.zzgo().zzjg().zzg("onBind received unknown action", v4);
        return v0;
    }

    public final void onCreate() {
        zzbt v0 = zzbt.zza(this.zzaby, null);
        zzap v1 = v0.zzgo();
        v0.zzgr();
        v1.zzjl().zzbx("Local AppMeasurementService is starting up");
    }

    public final void onDestroy() {
        zzbt v0 = zzbt.zza(this.zzaby, null);
        zzap v1 = v0.zzgo();
        v0.zzgr();
        v1.zzjl().zzbx("Local AppMeasurementService is shutting down");
    }

    public final void onRebind(Intent arg3) {
        if(arg3 == null) {
            this.zzgo().zzjd().zzbx("onRebind called with null intent");
            return;
        }

        this.zzgo().zzjl().zzg("onRebind called. action", arg3.getAction());
    }

    public final int onStartCommand(Intent arg6, int arg7, int arg8) {
        zzbt v7 = zzbt.zza(this.zzaby, null);
        zzap v0 = v7.zzgo();
        int v1 = 2;
        if(arg6 == null) {
            v0.zzjg().zzbx("AppMeasurementService started with null intent");
            return v1;
        }

        String v2 = arg6.getAction();
        v7.zzgr();
        v0.zzjl().zze("Local AppMeasurementService called. startId, action", Integer.valueOf(arg8), v2);
        if("com.google.android.gms.measurement.UPLOAD".equals(v2)) {
            this.zzb(new zzem(this, arg8, v0, arg6));
        }

        return v1;
    }

    @TargetApi(value=24) public final boolean onStartJob(JobParameters arg5) {
        zzbt v0 = zzbt.zza(this.zzaby, null);
        zzap v1 = v0.zzgo();
        String v2 = arg5.getExtras().getString("action");
        v0.zzgr();
        v1.zzjl().zzg("Local AppMeasurementJobService called. action", v2);
        if("com.google.android.gms.measurement.UPLOAD".equals(v2)) {
            this.zzb(new zzen(this, v1, arg5));
        }

        return 1;
    }

    public final boolean onUnbind(Intent arg4) {
        if(arg4 == null) {
            this.zzgo().zzjd().zzbx("onUnbind called with null intent");
            return 1;
        }

        this.zzgo().zzjl().zzg("onUnbind called for intent. action", arg4.getAction());
        return 1;
    }

    final void zza(int arg2, zzap arg3, Intent arg4) {
        if(this.zzaby.callServiceStopSelfResult(arg2)) {
            arg3.zzjl().zzg("Local AppMeasurementService processed last upload request. StartId", Integer.valueOf(arg2));
            this.zzgo().zzjl().zzbx("Completed wakeful intent.");
            this.zzaby.zzb(arg4);
        }
    }

    final void zza(zzap arg2, JobParameters arg3) {
        arg2.zzjl().zzbx("AppMeasurementJobService processed last upload request.");
        this.zzaby.zza(arg3, false);
    }

    private final void zzb(Runnable arg4) {
        zzfa v0 = zzfa.zzm(this.zzaby);
        v0.zzgn().zzc(new zzeo(this, v0, arg4));
    }

    private final zzap zzgo() {
        return zzbt.zza(this.zzaby, null).zzgo();
    }
}

