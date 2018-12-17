package androidx.work.impl.background.systemjob;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.Context;
import android.os.Build$VERSION;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.b.d;
import androidx.work.impl.b.j;
import androidx.work.impl.c;
import androidx.work.impl.g;
import androidx.work.o;
import java.util.Iterator;
import java.util.List;

public class b implements c {
    private final JobScheduler a;
    private final g b;
    private final androidx.work.impl.utils.c c;
    private final a d;

    public b(Context arg3, g arg4) {
        this(arg3, arg4, arg3.getSystemService("jobscheduler"), new a(arg3));
    }

    public b(Context arg1, g arg2, JobScheduler arg3, a arg4) {
        super();
        this.b = arg2;
        this.a = arg3;
        this.c = new androidx.work.impl.utils.c(arg1);
        this.d = arg4;
    }

    public static void a(Context arg4) {
        Object v4 = arg4.getSystemService("jobscheduler");
        if(v4 != null) {
            List v0 = ((JobScheduler)v4).getAllPendingJobs();
            if(v0 != null) {
                Iterator v0_1 = v0.iterator();
                while(v0_1.hasNext()) {
                    Object v1 = v0_1.next();
                    if(!((JobInfo)v1).getExtras().containsKey("EXTRA_WORK_SPEC_ID")) {
                        continue;
                    }

                    ((JobScheduler)v4).cancel(((JobInfo)v1).getId());
                }
            }
        }
    }

    public void a(j arg6, int arg7) {
        JobInfo v0 = this.d.a(arg6, arg7);
        androidx.work.j.b("SystemJobScheduler", String.format("Scheduling work ID %s Job ID %s", arg6.a, Integer.valueOf(arg7)), new Throwable[0]);
        this.a.schedule(v0);
    }

    public void a(String arg5) {
        List v0 = this.a.getAllPendingJobs();
        if(v0 != null) {
            Iterator v0_1 = v0.iterator();
            do {
                if(v0_1.hasNext()) {
                    Object v1 = v0_1.next();
                    if(!arg5.equals(((JobInfo)v1).getExtras().getString("EXTRA_WORK_SPEC_ID"))) {
                        continue;
                    }

                    this.b.d().p().b(arg5);
                    this.a.cancel(((JobInfo)v1).getId());
                    if(Build$VERSION.SDK_INT == 23) {
                        continue;
                    }
                }

                return;
            }
            while(true);
        }
    }

    public void a(j[] arg10) {
        Throwable[] v6_1;
        String v4_1;
        String v5_1;
        WorkDatabase v0 = this.b.d();
        int v1 = arg10.length;
        int v3;
        for(v3 = 0; v3 < v1; ++v3) {
            j v4 = arg10[v3];
            v0.f();
            try {
                j v5 = v0.m().b(v4.a);
                if(v5 == null) {
                    v5_1 = "SystemJobScheduler";
                    v4_1 = "Skipping scheduling " + v4.a + " because it\'s no longer in the DB";
                    v6_1 = new Throwable[0];
                    goto label_23;
                }
                else if(v5.b != o.a) {
                    v5_1 = "SystemJobScheduler";
                    v4_1 = "Skipping scheduling " + v4.a + " because it is no longer enqueued";
                    v6_1 = new Throwable[0];
                label_23:
                    androidx.work.j.d(v5_1, v4_1, v6_1);
                }
                else {
                    d v5_2 = v0.p().a(v4.a);
                    int v6_2 = v5_2 != null ? v5_2.b : this.c.a(this.b.e().d(), this.b.e().e());
                    if(v5_2 == null) {
                        this.b.d().p().a(new d(v4.a, v6_2));
                    }

                    this.a(v4, v6_2);
                    if(Build$VERSION.SDK_INT == 23) {
                        this.a(v4, this.c.a(this.b.e().d(), this.b.e().e()));
                    }

                    v0.h();
                }
            }
            catch(Throwable v10) {
                v0.g();
                throw v10;
            }

            v0.g();
        }
    }
}

