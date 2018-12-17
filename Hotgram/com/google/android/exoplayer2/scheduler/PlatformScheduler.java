package com.google.android.exoplayer2.scheduler;

import android.annotation.TargetApi;
import android.app.job.JobInfo$Builder;
import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.PersistableBundle;
import com.google.android.exoplayer2.util.Util;

@TargetApi(value=21) public final class PlatformScheduler implements Scheduler {
    public final class PlatformSchedulerService extends JobService {
        public PlatformSchedulerService() {
            super();
        }

        public boolean onStartJob(JobParameters arg5) {
            PlatformScheduler.logd("PlatformSchedulerService started");
            PersistableBundle v0 = arg5.getExtras();
            if(new Requirements(v0.getInt("requirements")).checkRequirements(((Context)this))) {
                PlatformScheduler.logd("Requirements are met");
                String v5 = v0.getString("service_action");
                String v0_1 = v0.getString("service_package");
                Intent v1 = new Intent(v5).setPackage(v0_1);
                PlatformScheduler.logd("Starting service action: " + v5 + " package: " + v0_1);
                Util.startForegroundService(((Context)this), v1);
            }
            else {
                PlatformScheduler.logd("Requirements are not met");
                this.jobFinished(arg5, true);
            }

            return 0;
        }

        public boolean onStopJob(JobParameters arg1) {
            return 0;
        }
    }

    private static final String KEY_REQUIREMENTS = "requirements";
    private static final String KEY_SERVICE_ACTION = "service_action";
    private static final String KEY_SERVICE_PACKAGE = "service_package";
    private static final String TAG = "PlatformScheduler";
    private final int jobId;
    private final JobScheduler jobScheduler;
    private final ComponentName jobServiceComponentName;

    public PlatformScheduler(Context arg2, int arg3) {
        super();
        this.jobId = arg3;
        this.jobServiceComponentName = new ComponentName(arg2, PlatformSchedulerService.class);
        this.jobScheduler = arg2.getSystemService("jobscheduler");
    }

    static void access$000(String arg0) {
        PlatformScheduler.logd(arg0);
    }

    private static JobInfo buildJobInfo(int arg2, ComponentName arg3, Requirements arg4, String arg5, String arg6) {
        JobInfo$Builder v0 = new JobInfo$Builder(arg2, arg3);
        switch(arg4.getRequiredNetworkType()) {
            case 0: {
                goto label_28;
            }
            case 1: {
                goto label_26;
            }
            case 2: {
                goto label_24;
            }
            case 3: {
                goto label_16;
            }
            case 4: {
                goto label_8;
            }
        }

        throw new UnsupportedOperationException();
    label_24:
        arg2 = 2;
        goto label_29;
    label_8:
        if(Util.SDK_INT >= 26) {
            arg2 = 4;
            goto label_29;
        }

        throw new UnsupportedOperationException();
    label_26:
        arg2 = 1;
        goto label_29;
    label_28:
        arg2 = 0;
        goto label_29;
    label_16:
        if(Util.SDK_INT >= 24) {
            arg2 = 3;
        label_29:
            v0.setRequiredNetworkType(arg2);
            v0.setRequiresDeviceIdle(arg4.isIdleRequired());
            v0.setRequiresCharging(arg4.isChargingRequired());
            v0.setPersisted(true);
            PersistableBundle v2 = new PersistableBundle();
            v2.putString("service_action", arg5);
            v2.putString("service_package", arg6);
            v2.putInt("requirements", arg4.getRequirementsData());
            v0.setExtras(v2);
            return v0.build();
        }

        throw new UnsupportedOperationException();
    }

    public boolean cancel() {
        PlatformScheduler.logd("Canceling job: " + this.jobId);
        this.jobScheduler.cancel(this.jobId);
        return 1;
    }

    private static void logd(String arg0) {
    }

    public boolean schedule(Requirements arg3, String arg4, String arg5) {
        int v3 = this.jobScheduler.schedule(PlatformScheduler.buildJobInfo(this.jobId, this.jobServiceComponentName, arg3, arg5, arg4));
        PlatformScheduler.logd("Scheduling job: " + this.jobId + " result: " + v3);
        boolean v4_1 = true;
        if(v3 == 1) {
        }
        else {
            v4_1 = false;
        }

        return v4_1;
    }
}

