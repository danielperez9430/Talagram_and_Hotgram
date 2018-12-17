package com.google.android.gms.measurement;

import android.app.Service;
import android.app.job.JobParameters;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import com.google.android.gms.measurement.internal.zzel;
import com.google.android.gms.measurement.internal.zzep;

public final class AppMeasurementService extends Service implements zzep {
    private zzel zzadr;

    public AppMeasurementService() {
        super();
    }

    public final boolean callServiceStopSelfResult(int arg1) {
        return this.stopSelfResult(arg1);
    }

    public final IBinder onBind(Intent arg2) {
        return this.zzfu().onBind(arg2);
    }

    public final void onCreate() {
        super.onCreate();
        this.zzfu().onCreate();
    }

    public final void onDestroy() {
        this.zzfu().onDestroy();
        super.onDestroy();
    }

    public final void onRebind(Intent arg2) {
        this.zzfu().onRebind(arg2);
    }

    public final int onStartCommand(Intent arg2, int arg3, int arg4) {
        return this.zzfu().onStartCommand(arg2, arg3, arg4);
    }

    public final boolean onUnbind(Intent arg2) {
        return this.zzfu().onUnbind(arg2);
    }

    public final void zza(JobParameters arg1, boolean arg2) {
        throw new UnsupportedOperationException();
    }

    public final void zzb(Intent arg1) {
        AppMeasurementReceiver.completeWakefulIntent(arg1);
    }

    private final zzel zzfu() {
        if(this.zzadr == null) {
            this.zzadr = new zzel(((Context)this));
        }

        return this.zzadr;
    }
}

