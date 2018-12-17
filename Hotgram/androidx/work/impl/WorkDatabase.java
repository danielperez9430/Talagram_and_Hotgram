package androidx.work.impl;

import android.arch.b.b.d;
import android.arch.b.b.e$a;
import android.arch.b.b.e$b;
import android.arch.b.b.e;
import android.content.Context;
import androidx.work.impl.b.h;
import androidx.work.impl.b.k;
import androidx.work.impl.b.n;
import java.util.concurrent.TimeUnit;

public abstract class WorkDatabase extends e {
    private static final long d;

    static {
        WorkDatabase.d = TimeUnit.DAYS.toMillis(7);
    }

    public WorkDatabase() {
        super();
    }

    public static WorkDatabase a(Context arg6, boolean arg7) {
        a v7 = arg7 ? d.a(arg6, WorkDatabase.class).a() : d.a(arg6, WorkDatabase.class, "androidx.work.workdb");
        return v7.a(WorkDatabase.j()).a(new android.arch.b.b.a.a[]{f.a}).a(new android.arch.b.b.a.a[]{new androidx.work.impl.f$a(arg6, 2, 3)}).a(new android.arch.b.b.a.a[]{f.b}).b().c();
    }

    static b j() {
        return new b() {
            public void b(android.arch.b.a.b arg2) {
                super.b(arg2);
                arg2.a();
                try {
                    arg2.c("UPDATE workspec SET state=0, schedule_requested_at=-1 WHERE state=1");
                    arg2.c(WorkDatabase.k());
                    arg2.c();
                }
                catch(Throwable v0) {
                    arg2.b();
                    throw v0;
                }

                arg2.b();
            }
        };
    }

    static String k() {
        return "DELETE FROM workspec WHERE state IN (2, 3, 5) AND (period_start_time + minimum_retention_duration) < " + WorkDatabase.l() + " AND (SELECT COUNT(*)=0 FROM dependency WHERE     prerequisite_id=id AND     work_spec_id NOT IN         (SELECT id FROM workspec WHERE state IN (2, 3, 5)))";
    }

    static long l() {
        return System.currentTimeMillis() - WorkDatabase.d;
    }

    public abstract k m();

    public abstract androidx.work.impl.b.b n();

    public abstract n o();

    public abstract androidx.work.impl.b.e p();

    public abstract h q();
}

