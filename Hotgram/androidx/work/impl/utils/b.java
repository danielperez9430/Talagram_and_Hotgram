package androidx.work.impl.utils;

import android.os.Build$VERSION;
import android.text.TextUtils;
import androidx.work.c;
import androidx.work.e$a;
import androidx.work.h;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.b.j;
import androidx.work.impl.b.k;
import androidx.work.impl.b.m;
import androidx.work.impl.background.systemalarm.RescheduleReceiver;
import androidx.work.impl.d;
import androidx.work.impl.e;
import androidx.work.impl.g;
import androidx.work.impl.workers.ConstraintTrackingWorker;
import androidx.work.o;
import androidx.work.r;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class b implements Runnable {
    private final e a;

    public b(e arg1) {
        super();
        this.a = arg1;
    }

    private static void a(j arg4) {
        c v0 = arg4.j;
        if((v0.d()) || (v0.e())) {
            String v0_1 = arg4.c;
            a v1 = new a();
            v1.a(arg4.e).a("androidx.work.impl.workers.ConstraintTrackingWorker.ARGUMENT_CLASS_NAME", v0_1);
            arg4.c = ConstraintTrackingWorker.class.getName();
            arg4.e = v1.a();
        }
    }

    private static boolean a(e arg8) {
        List v0 = arg8.h();
        int v1 = 0;
        if(v0 != null) {
            Iterator v0_1 = v0.iterator();
            int v2 = 0;
            while(v0_1.hasNext()) {
                Object v3 = v0_1.next();
                if(!((e)v3).f()) {
                    v2 |= b.a(((e)v3));
                }
                else {
                    androidx.work.j.d("EnqueueRunnable", String.format("Already enqueued work ids (%s).", TextUtils.join(", ", ((e)v3).e())), new Throwable[0]);
                }
            }

            v1 = v2;
        }

        return b.b(arg8) | v1;
    }

    private static boolean a(g arg18, List arg19, String[] arg20, String arg21, h arg22) {
        o v15_2;
        boolean v2_4;
        Iterator v7_2;
        int v13;
        int v12;
        int v11;
        int v10;
        int v9;
        String[] v0 = arg20;
        String v1 = arg21;
        h v2 = arg22;
        long v3 = System.currentTimeMillis();
        WorkDatabase v5 = arg18.d();
        int v8 = v0 == null || v0.length <= 0 ? 0 : 1;
        if(v8 != 0) {
            v9 = v0.length;
            v10 = 0;
            v11 = 1;
            v12 = 0;
            v13 = 0;
            while(v10 < v9) {
                String v14 = v0[v10];
                j v15 = v5.m().b(v14);
                if(v15 == null) {
                    androidx.work.j.e("EnqueueRunnable", String.format("Prerequisite %s doesn\'t exist; not enqueuing", v14), new Throwable[0]);
                    return 0;
                }
                else {
                    o v14_1 = v15.b;
                    int v15_1 = v14_1 == o.c ? 1 : 0;
                    v11 &= v15_1;
                    if(v14_1 == o.d) {
                        v12 = 1;
                    }
                    else if(v14_1 == o.f) {
                        v13 = 1;
                    }

                    ++v10;
                    continue;
                }

                break;
            }
        }
        else {
            v11 = 1;
            v12 = 0;
            v13 = 0;
        }

        v9 = TextUtils.isEmpty(((CharSequence)arg21)) ^ 1;
        v10 = v9 == 0 || v8 != 0 ? 0 : 1;
        if(v10 != 0) {
            List v10_1 = v5.m().c(v1);
            if(v10_1.isEmpty()) {
                goto label_129;
            }
            else if(v2 == h.c) {
                androidx.work.impl.b.b v2_1 = v5.n();
                ArrayList v8_1 = new ArrayList();
                Iterator v10_2 = v10_1.iterator();
                while(v10_2.hasNext()) {
                    Object v14_2 = v10_2.next();
                    if(v2_1.c(((androidx.work.impl.b.j$a)v14_2).a)) {
                        continue;
                    }

                    int v7 = ((androidx.work.impl.b.j$a)v14_2).b == o.c ? 1 : 0;
                    v7 &= v11;
                    if(((androidx.work.impl.b.j$a)v14_2).b == o.d) {
                        v12 = 1;
                    }
                    else if(((androidx.work.impl.b.j$a)v14_2).b == o.f) {
                        v13 = 1;
                    }

                    ((List)v8_1).add(((androidx.work.impl.b.j$a)v14_2).a);
                    v11 = v7;
                }

                Object[] v0_1 = ((List)v8_1).toArray(((Object[])v0));
                if(v0_1.length > 0) {
                    v8 = 1;
                    goto label_129;
                }

                v8 = 0;
                goto label_129;
            }
            else {
                if(v2 == h.b) {
                    Iterator v2_2 = v10_1.iterator();
                    do {
                        if(v2_2.hasNext()) {
                            Object v7_1 = v2_2.next();
                            if(((androidx.work.impl.b.j$a)v7_1).b != o.a && ((androidx.work.impl.b.j$a)v7_1).b != o.b) {
                                continue;
                            }

                            return 0;
                        }

                        goto label_116;
                    }
                    while(true);

                    return 0;
                }

            label_116:
                androidx.work.impl.utils.a.a(v1, arg18, false).run();
                k v2_3 = v5.m();
                v7_2 = v10_1.iterator();
                while(v7_2.hasNext()) {
                    v2_3.a(v7_2.next().a);
                }

                v2_4 = true;
            }
        }
        else {
        label_129:
            v2_4 = false;
        }

        v7_2 = arg19.iterator();
        while(v7_2.hasNext()) {
            Object v10_3 = v7_2.next();
            j v14_3 = ((r)v10_3).b();
            if(v8 == 0 || v11 != 0) {
                v14_3.n = v3;
            }
            else {
                if(v12 != 0) {
                    v15_2 = o.d;
                }
                else if(v13 != 0) {
                    v15_2 = o.f;
                }
                else {
                    v15_2 = o.e;
                }

                v14_3.b = v15_2;
            }

            if(Build$VERSION.SDK_INT >= 23 && Build$VERSION.SDK_INT <= 25) {
                b.a(v14_3);
            }

            if(v14_3.b == o.a) {
                v2_4 = true;
            }

            v5.m().a(v14_3);
            if(v8 != 0) {
                int v6 = v0.length;
                int v14_4 = 0;
                while(v14_4 < v6) {
                    v5.n().a(new androidx.work.impl.b.a(((r)v10_3).a(), v0[v14_4]));
                    ++v14_4;
                    v0 = v0;
                    v2_4 = v2_4;
                }
            }

            String[] v16 = v0;
            boolean v17 = v2_4;
            Iterator v0_2 = ((r)v10_3).c().iterator();
            while(v0_2.hasNext()) {
                v5.o().a(new m(v0_2.next(), ((r)v10_3).a()));
            }

            if(v9 != 0) {
                v5.q().a(new androidx.work.impl.b.g(v1, ((r)v10_3).a()));
            }

            v0 = v16;
            v2_4 = v17;
        }

        return v2_4;
    }

    public boolean a() {
        boolean v1_1;
        WorkDatabase v0 = this.a.a().d();
        v0.f();
        try {
            v1_1 = b.a(this.a);
            v0.h();
        }
        catch(Throwable v1) {
            v0.g();
            throw v1;
        }

        v0.g();
        return v1_1;
    }

    private static boolean b(e arg5) {
        boolean v0 = b.a(arg5.a(), arg5.d(), e.a(arg5).toArray(new String[0]), arg5.b(), arg5.c());
        arg5.g();
        return v0;
    }

    public void b() {
        g v0 = this.a.a();
        d.a(v0.e(), v0.d(), v0.f());
    }

    public void run() {
        if(!this.a.j()) {
            if(this.a()) {
                androidx.work.impl.utils.d.a(this.a.a().c(), RescheduleReceiver.class, true);
                this.b();
            }

            return;
        }

        throw new IllegalStateException(String.format("WorkContinuation has cycles (%s)", this.a));
    }
}

