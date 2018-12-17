package androidx.work.impl;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build$VERSION;
import androidx.work.impl.b.k;
import androidx.work.impl.background.systemalarm.SystemAlarmService;
import androidx.work.impl.background.systemalarm.f;
import androidx.work.impl.background.systemjob.SystemJobService;
import androidx.work.impl.background.systemjob.b;
import androidx.work.j;
import java.util.Iterator;
import java.util.List;

public class d {
    private static c a(Context arg5) {
        return Class.forName("androidx.work.impl.background.firebase.FirebaseJobScheduler").getConstructor(Context.class).newInstance(arg5);
    }

    @SuppressLint(value={"NewApi"}) static c a(Context arg5, g arg6) {
        c v0_1;
        boolean v6;
        boolean v2 = false;
        if(Build$VERSION.SDK_INT >= 23) {
            b v0 = new b(arg5, arg6);
            androidx.work.impl.utils.d.a(arg5, SystemJobService.class, true);
            j.b("Schedulers", "Created SystemJobScheduler and enabled SystemJobService", new Throwable[0]);
            v6 = false;
            try {
            label_32:
                androidx.work.impl.utils.d.a(arg5, Class.forName("androidx.work.impl.background.firebase.FirebaseJobService"), v6);
            }
            catch(ClassNotFoundException ) {
            }

            goto label_35;
        }

        try {
            v0_1 = d.a(arg5);
        }
        catch(Exception ) {
            v6 = false;
            goto label_25;
        }

        try {
            j.b("Schedulers", "Created FirebaseJobScheduler", new Throwable[0]);
            v6 = true;
            goto label_32;
        }
        catch(Exception ) {
            v6 = true;
        }

    label_25:
        f v0_2 = new f(arg5);
        j.b("Schedulers", "Created SystemAlarmScheduler", new Throwable[0]);
        v2 = true;
        goto label_32;
    label_35:
        androidx.work.impl.utils.d.a(arg5, SystemAlarmService.class, v2);
        return v0_1;
    }

    public static void a(androidx.work.b arg5, WorkDatabase arg6, List arg7) {
        List v5_1;
        if(arg7 != null) {
            if(arg7.size() == 0) {
            }
            else {
                k v0 = arg6.m();
                arg6.f();
                try {
                    v5_1 = v0.a(arg5.f());
                    if(v5_1 != null && v5_1.size() > 0) {
                        long v1 = System.currentTimeMillis();
                        Iterator v3 = v5_1.iterator();
                        while(v3.hasNext()) {
                            v0.b(v3.next().a, v1);
                        }
                    }

                    arg6.h();
                }
                catch(Throwable v5) {
                    arg6.g();
                    throw v5;
                }

                arg6.g();
                if(v5_1 != null && v5_1.size() > 0) {
                    Object[] v5_2 = v5_1.toArray(new androidx.work.impl.b.j[0]);
                    Iterator v6 = arg7.iterator();
                    while(v6.hasNext()) {
                        v6.next().a(((androidx.work.impl.b.j[])v5_2));
                    }
                }

                return;
            }
        }
    }
}

