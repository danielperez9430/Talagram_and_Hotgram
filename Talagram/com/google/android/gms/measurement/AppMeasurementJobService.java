package com.google.android.gms.measurement;

import android.annotation.TargetApi;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Context;
import android.content.Intent;
import com.google.android.gms.measurement.internal.zzel;
import com.google.android.gms.measurement.internal.zzep;

@TargetApi(value=24) public final class AppMeasurementJobService extends JobService implements zzep {
    private zzel zzadr;

    public AppMeasurementJobService() {
        super();
    }

    public final boolean callServiceStopSelfResult(int arg1) {
        throw new UnsupportedOperationException();
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

    public final boolean onStartJob(JobParameters arg2) {
        return this.zzfu().onStartJob(arg2);
    }

    public final boolean onStopJob(JobParameters arg1) {
        return 0;
    }

    public final boolean onUnbind(Intent arg2) {
        return this.zzfu().onUnbind(arg2);
    }

    @TargetApi(value=24) public final void zza(JobParameters arg1, boolean arg2) {
        this.jobFinished(arg1, false);
    }

    public final void zzb(Intent arg1) {
    }

    private final zzel zzfu() {
        if(this.zzadr == null) {
            this.zzadr = new zzel(((Context)this));
        }

        return this.zzadr;
    }
}

