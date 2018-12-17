package androidx.work.impl.background.systemjob;

import android.app.job.JobInfo$Builder;
import android.app.job.JobInfo$TriggerContentUri;
import android.app.job.JobInfo;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build$VERSION;
import android.os.PersistableBundle;
import androidx.work.c;
import androidx.work.j;
import androidx.work.k;
import java.util.Iterator;

class a {
    class androidx.work.impl.background.systemjob.a$1 {
        static {
            androidx.work.impl.background.systemjob.a$1.a = new int[k.values().length];
            try {
                androidx.work.impl.background.systemjob.a$1.a[k.a.ordinal()] = 1;
                goto label_9;
            }
            catch(NoSuchFieldError ) {
                try {
                label_9:
                    androidx.work.impl.background.systemjob.a$1.a[k.b.ordinal()] = 2;
                    goto label_14;
                }
                catch(NoSuchFieldError ) {
                    try {
                    label_14:
                        androidx.work.impl.background.systemjob.a$1.a[k.c.ordinal()] = 3;
                        goto label_19;
                    }
                    catch(NoSuchFieldError ) {
                        try {
                        label_19:
                            androidx.work.impl.background.systemjob.a$1.a[k.d.ordinal()] = 4;
                            goto label_24;
                        }
                        catch(NoSuchFieldError ) {
                            try {
                            label_24:
                                androidx.work.impl.background.systemjob.a$1.a[k.e.ordinal()] = 5;
                                return;
                            }
                            catch(NoSuchFieldError ) {
                                return;
                            }
                        }
                    }
                }
            }
        }
    }

    private final ComponentName a;

    a(Context arg3) {
        super();
        this.a = new ComponentName(arg3.getApplicationContext(), SystemJobService.class);
    }

    static int a(k arg5) {
        switch(androidx.work.impl.background.systemjob.a$1.a[arg5.ordinal()]) {
            case 1: {
                return 0;
            }
            case 2: {
                return 1;
            }
            case 3: {
                return 2;
            }
            case 4: {
                goto label_12;
            }
            case 5: {
                goto label_7;
            }
        }

        goto label_21;
        return 2;
    label_7:
        if(Build$VERSION.SDK_INT < 26) {
            goto label_21;
        }

        return 4;
    label_12:
        if(Build$VERSION.SDK_INT >= 24) {
            return 3;
        }

    label_21:
        j.b("SystemJobInfoConverter", String.format("API version too low. Cannot convert network type value %s", arg5), new Throwable[0]);
        return 1;
    }

    private static JobInfo$TriggerContentUri a(androidx.work.d$a arg2) {
        return new JobInfo$TriggerContentUri(arg2.a(), arg2.b());
    }

    JobInfo a(androidx.work.impl.b.j arg9, int arg10) {
        c v0 = arg9.j;
        int v1 = a.a(v0.a());
        PersistableBundle v2 = new PersistableBundle();
        v2.putString("EXTRA_WORK_SPEC_ID", arg9.a);
        v2.putBoolean("EXTRA_IS_PERIODIC", arg9.a());
        JobInfo$Builder v10 = new JobInfo$Builder(arg10, this.a).setRequiredNetworkType(v1).setRequiresCharging(v0.b()).setRequiresDeviceIdle(v0.c()).setExtras(v2);
        if(!v0.c()) {
            v1 = arg9.l == androidx.work.a.b ? 0 : 1;
            v10.setBackoffCriteria(arg9.m, v1);
        }

        int v3 = 24;
        if(!arg9.a()) {
            v10.setMinimumLatency(arg9.g);
        }
        else if(Build$VERSION.SDK_INT >= v3) {
            v10.setPeriodic(arg9.h, arg9.i);
        }
        else {
            j.b("SystemJobInfoConverter", "Flex duration is currently not supported before API 24. Ignoring.", new Throwable[0]);
            v10.setPeriodic(arg9.h);
        }

        if(Build$VERSION.SDK_INT >= v3 && (v0.g())) {
            Iterator v9 = v0.f().iterator();
            while(v9.hasNext()) {
                v10.addTriggerContentUri(a.a(v9.next()));
            }
        }

        v10.setPersisted(false);
        if(Build$VERSION.SDK_INT >= 26) {
            v10.setRequiresBatteryNotLow(v0.d());
            v10.setRequiresStorageNotLow(v0.e());
        }

        return v10.build();
    }
}

