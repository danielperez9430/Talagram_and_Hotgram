package androidx.work.impl.background.systemjob;

import android.app.Application;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.Build$VERSION;
import android.text.TextUtils;
import androidx.work.impl.a;
import androidx.work.impl.g;
import androidx.work.j;
import java.util.HashMap;
import java.util.Map;

public class SystemJobService extends JobService implements a {
    private g a;
    private final Map b;

    public SystemJobService() {
        super();
        this.b = new HashMap();
    }

    public void a(String arg5, boolean arg6) {
        Object v5_1;
        j.b("SystemJobService", String.format("%s executed on JobScheduler", arg5), new Throwable[0]);
        Map v0 = this.b;
        __monitor_enter(v0);
        try {
            v5_1 = this.b.remove(arg5);
            __monitor_exit(v0);
            if(v5_1 == null) {
                return;
            }
        }
        catch(Throwable v5) {
            try {
            label_18:
                __monitor_exit(v0);
            }
            catch(Throwable v5) {
                goto label_18;
            }

            throw v5;
        }

        this.jobFinished(((JobParameters)v5_1), arg6);
    }

    public void onCreate() {
        super.onCreate();
        this.a = g.b();
        if(this.a != null) {
            this.a.g().a(((a)this));
        }
        else if(Application.class.equals(this.getApplication().getClass())) {
            j.d("SystemJobService", "Could not find WorkManager instance; this may be because an auto-backup is in progress. Ignoring JobScheduler commands for now. Please make sure that you are initializing WorkManager if you have manually disabled WorkManagerInitializer.", new Throwable[0]);
        }
        else {
            throw new IllegalStateException("WorkManager needs to be initialized via a ContentProvider#onCreate() or an Application#onCreate().");
        }
    }

    public void onDestroy() {
        super.onDestroy();
        if(this.a != null) {
            this.a.g().b(((a)this));
        }
    }

    public boolean onStartJob(JobParameters arg8) {
        if(this.a == null) {
            j.b("SystemJobService", "WorkManager is not initialized; requesting retry.", new Throwable[0]);
            this.jobFinished(arg8, true);
            return 0;
        }

        String v0 = arg8.getExtras().getString("EXTRA_WORK_SPEC_ID");
        if(TextUtils.isEmpty(((CharSequence)v0))) {
            j.e("SystemJobService", "WorkSpec id not found!", new Throwable[0]);
            return 0;
        }

        Map v3 = this.b;
        __monitor_enter(v3);
        try {
            if(this.b.containsKey(v0)) {
                j.b("SystemJobService", String.format("Job is already being executed by SystemJobService: %s", v0), new Throwable[0]);
                __monitor_exit(v3);
                return 0;
            }

            j.b("SystemJobService", String.format("onStartJob for %s", v0), new Throwable[0]);
            this.b.put(v0, arg8);
            __monitor_exit(v3);
        }
        catch(Throwable v8) {
            try {
            label_67:
                __monitor_exit(v3);
            }
            catch(Throwable v8) {
                goto label_67;
            }

            throw v8;
        }

        androidx.work.u$a v2 = null;
        if(Build$VERSION.SDK_INT >= 24) {
            v2 = new androidx.work.u$a();
            if(arg8.getTriggeredContentUris() != null || arg8.getTriggeredContentAuthorities() != null) {
                v2.b = arg8.getTriggeredContentUris();
                v2.a = arg8.getTriggeredContentAuthorities();
            }

            if(Build$VERSION.SDK_INT < 28) {
                goto label_63;
            }

            v2.c = arg8.getNetwork();
        }

    label_63:
        this.a.a(v0, v2);
        return 1;
    }

    public boolean onStopJob(JobParameters arg6) {
        if(this.a == null) {
            j.b("SystemJobService", "WorkManager is not initialized; requesting retry.", new Throwable[0]);
            return 1;
        }

        String v6 = arg6.getExtras().getString("EXTRA_WORK_SPEC_ID");
        if(TextUtils.isEmpty(((CharSequence)v6))) {
            j.e("SystemJobService", "WorkSpec id not found!", new Throwable[0]);
            return 0;
        }

        j.b("SystemJobService", String.format("onStopJob for %s", v6), new Throwable[0]);
        Map v0 = this.b;
        __monitor_enter(v0);
        try {
            this.b.remove(v6);
            __monitor_exit(v0);
        }
        catch(Throwable v6_1) {
            try {
            label_39:
                __monitor_exit(v0);
            }
            catch(Throwable v6_1) {
                goto label_39;
            }

            throw v6_1;
        }

        this.a.c(v6);
        return this.a.g().d(v6) ^ 1;
    }
}

