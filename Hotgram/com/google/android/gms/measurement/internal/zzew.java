package com.google.android.gms.measurement.internal;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.job.JobInfo$Builder;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build$VERSION;
import android.os.PersistableBundle;
import com.google.android.gms.common.util.Clock;

public final class zzew extends zzez {
    private final zzv zzata;
    private final AlarmManager zzyt;
    private Integer zzyu;

    protected zzew(zzfa arg3) {
        super(arg3);
        this.zzyt = ((zzco)this).getContext().getSystemService("alarm");
        this.zzata = new zzex(this, arg3.zzmb(), arg3);
    }

    public final void cancel() {
        ((zzez)this).zzcl();
        this.zzyt.cancel(this.zzeo());
        this.zzata.cancel();
        if(Build$VERSION.SDK_INT >= 24) {
            this.zzlm();
        }
    }

    public final Context getContext() {
        return super.getContext();
    }

    private final int getJobId() {
        if(this.zzyu == null) {
            String v0 = "measurement";
            String v1 = String.valueOf(((zzco)this).getContext().getPackageName());
            v0 = v1.length() != 0 ? v0.concat(v1) : new String(v0);
            this.zzyu = Integer.valueOf(v0.hashCode());
        }

        return this.zzyu.intValue();
    }

    public final void zzaf() {
        super.zzaf();
    }

    public final Clock zzbx() {
        return super.zzbx();
    }

    private final PendingIntent zzeo() {
        Intent v0 = new Intent().setClassName(((zzco)this).getContext(), "com.google.android.gms.measurement.AppMeasurementReceiver");
        v0.setAction("com.google.android.gms.measurement.UPLOAD");
        return PendingIntent.getBroadcast(((zzco)this).getContext(), 0, v0, 0);
    }

    public final void zzga() {
        super.zzga();
    }

    public final void zzgb() {
        super.zzgb();
    }

    public final void zzgc() {
        super.zzgc();
    }

    public final zzx zzgk() {
        return super.zzgk();
    }

    public final zzan zzgl() {
        return super.zzgl();
    }

    public final zzfk zzgm() {
        return super.zzgm();
    }

    public final zzbo zzgn() {
        return super.zzgn();
    }

    public final zzap zzgo() {
        return super.zzgo();
    }

    public final zzba zzgp() {
        return super.zzgp();
    }

    public final zzn zzgq() {
        return super.zzgq();
    }

    public final zzk zzgr() {
        return super.zzgr();
    }

    protected final boolean zzgt() {
        this.zzyt.cancel(this.zzeo());
        if(Build$VERSION.SDK_INT >= 24) {
            this.zzlm();
        }

        return 0;
    }

    public final void zzh(long arg10) {
        ((zzez)this).zzcl();
        ((zzco)this).zzgr();
        if(!zzbj.zza(((zzco)this).getContext())) {
            ((zzco)this).zzgo().zzjk().zzbx("Receiver not registered/enabled");
        }

        ((zzco)this).zzgr();
        if(!zzfk.zza(((zzco)this).getContext(), false)) {
            ((zzco)this).zzgo().zzjk().zzbx("Service not registered/enabled");
        }

        this.cancel();
        long v4 = ((zzco)this).zzbx().elapsedRealtime() + arg10;
        if(arg10 < Math.max(0, zzaf.zzaka.get().longValue()) && !this.zzata.zzej()) {
            ((zzco)this).zzgo().zzjl().zzbx("Scheduling upload with DelayedRunnable");
            this.zzata.zzh(arg10);
        }

        ((zzco)this).zzgr();
        if(Build$VERSION.SDK_INT >= 24) {
            ((zzco)this).zzgo().zzjl().zzbx("Scheduling upload with JobScheduler");
            ComponentName v0 = new ComponentName(((zzco)this).getContext(), "com.google.android.gms.measurement.AppMeasurementJobService");
            Object v1 = ((zzco)this).getContext().getSystemService("jobscheduler");
            JobInfo$Builder v2 = new JobInfo$Builder(this.getJobId(), v0);
            v2.setMinimumLatency(arg10);
            v2.setOverrideDeadline(arg10 << 1);
            PersistableBundle v10 = new PersistableBundle();
            v10.putString("action", "com.google.android.gms.measurement.UPLOAD");
            v2.setExtras(v10);
            JobInfo v10_1 = v2.build();
            ((zzco)this).zzgo().zzjl().zzg("Scheduling job. JobID", Integer.valueOf(this.getJobId()));
            ((JobScheduler)v1).schedule(v10_1);
            return;
        }

        ((zzco)this).zzgo().zzjl().zzbx("Scheduling upload with AlarmManager");
        this.zzyt.setInexactRepeating(2, v4, Math.max(zzaf.zzajv.get().longValue(), arg10), this.zzeo());
    }

    public final zzfg zzjo() {
        return super.zzjo();
    }

    public final zzj zzjp() {
        return super.zzjp();
    }

    public final zzq zzjq() {
        return super.zzjq();
    }

    @TargetApi(value=24) private final void zzlm() {
        Object v0 = ((zzco)this).getContext().getSystemService("jobscheduler");
        ((zzco)this).zzgo().zzjl().zzg("Cancelling job. JobID", Integer.valueOf(this.getJobId()));
        ((JobScheduler)v0).cancel(this.getJobId());
    }
}

